package com.zfans.shenlan.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zfans
 * @DateTime 2021/3/30 12:27
 */
@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mobile;
    private String password;
}
