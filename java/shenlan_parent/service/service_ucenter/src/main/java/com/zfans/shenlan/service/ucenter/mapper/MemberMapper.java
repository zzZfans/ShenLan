package com.zfans.shenlan.service.ucenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfans.shenlan.service.ucenter.entity.Member;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author Zfans
 * @since 2021-03-25
 */
public interface MemberMapper extends BaseMapper<Member> {
    Integer selectRegisterNumByDay(String day);
}
