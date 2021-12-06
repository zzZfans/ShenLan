package com.zfans.shenlan.service.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zfans.shenlan.service.base.dto.MemberDto;
import com.zfans.shenlan.service.ucenter.entity.Member;
import com.zfans.shenlan.service.ucenter.entity.vo.LoginVo;
import com.zfans.shenlan.service.ucenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Zfans
 * @since 2021-03-25
 */
public interface MemberService extends IService<Member> {

    void register(RegisterVo registerVo);

    String login(LoginVo loginVo);

    /**
     * 根据 openid 返回用户信息
     * @param openid
     * @return
     */
    Member getByOpenid(String openid);

    MemberDto getMemberDtoByMemberId(String memberId);
}
