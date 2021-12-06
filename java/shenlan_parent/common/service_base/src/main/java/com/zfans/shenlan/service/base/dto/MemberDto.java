package com.zfans.shenlan.service.base.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author 凡森
 * @Date 2021/12/4
 */
@Data
public class MemberDto implements Serializable {

    private static final long serialVersionUID = 1L;
    //会员 id
    private String id;
    // 手机号
    private String mobile;
    // 昵称
    private String nickname;
}