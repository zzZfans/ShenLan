package com.zfans.shenlan.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zfans
 * @DateTime 2021/3/25 17:41
 */
@Data
public class RegisterVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nickname;
    private String mobile;
    private String password;
    private String code;
}
