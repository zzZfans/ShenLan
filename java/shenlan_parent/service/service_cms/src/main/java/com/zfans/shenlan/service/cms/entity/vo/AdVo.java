package com.zfans.shenlan.service.cms.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zfans
 * @DateTime 2021/3/19 16:53
 */
@Data
public class AdVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Integer sort;
    private String type;
}
