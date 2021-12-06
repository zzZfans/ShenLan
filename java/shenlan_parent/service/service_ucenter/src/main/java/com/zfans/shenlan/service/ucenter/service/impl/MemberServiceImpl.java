package com.zfans.shenlan.service.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zfans.shenlan.common.base.result.ResultCodeEnum;
import com.zfans.shenlan.common.base.util.FormUtils;
import com.zfans.shenlan.common.base.util.JwtInfo;
import com.zfans.shenlan.common.base.util.JwtUtils;
import com.zfans.shenlan.common.base.util.MD5;
import com.zfans.shenlan.service.base.dto.MemberDto;
import com.zfans.shenlan.service.base.exception.ShenlanException;
import com.zfans.shenlan.service.ucenter.entity.Member;
import com.zfans.shenlan.service.ucenter.entity.vo.LoginVo;
import com.zfans.shenlan.service.ucenter.entity.vo.RegisterVo;
import com.zfans.shenlan.service.ucenter.mapper.MemberMapper;
import com.zfans.shenlan.service.ucenter.service.MemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Zfans
 * @since 2021-03-25
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 会员注册
     *
     * @param registerVo
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterVo registerVo) {

        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        //校验手机号
        if (StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)) {
            throw new ShenlanException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        //校验参数
        if (StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code)
                || StringUtils.isEmpty(nickname)) {
            throw new ShenlanException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验验证码
        String checkCode = (String) redisTemplate.opsForValue().get("SMS::".concat(mobile));
        if (!code.equals(checkCode)) {
            throw new ShenlanException(ResultCodeEnum.CODE_ERROR);
        }

        //是否被注册
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new ShenlanException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }

        //注册
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setDisabled(false);
        member.setAvatar("https://shenlan-file.oss-cn-beijing.aliyuncs.com/avatar/2021/02/05/ff168e5f-b6f9-4e4e-9427-5df6a9ed422e.jpg");
        baseMapper.insert(member);
    }

    @Override
    public String login(LoginVo loginVo) {

        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        //校验手机号
        if (StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)) {
            throw new ShenlanException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        //校验参数
        if (StringUtils.isEmpty(password)) {
            throw new ShenlanException(ResultCodeEnum.PARAM_ERROR);
        }

        //校验手机号
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile", mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if (member == null) {
            throw new ShenlanException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        //校验密码
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new ShenlanException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        //检验用户是否被禁用
        if (member.getDisabled()) {
            throw new ShenlanException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());

        return JwtUtils.getJwtToken(jwtInfo, 1800);
    }

    @Override
    public Member getByOpenid(String openid) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public MemberDto getMemberDtoByMemberId(String memberId) {
        Member member = baseMapper.selectById(memberId);
        MemberDto memberDto = new MemberDto();
        BeanUtils.copyProperties(member, memberDto);
        return memberDto;
    }
}
