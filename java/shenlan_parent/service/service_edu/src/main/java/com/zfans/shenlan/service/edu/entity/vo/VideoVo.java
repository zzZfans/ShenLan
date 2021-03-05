package com.zfans.shenlan.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zfans
 * @DateTime 2021/02/19 18:36
 */
@Data
public class VideoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Boolean free;
    private Integer sort;

    private String videoSourceId;
}
