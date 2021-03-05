package com.zfans.shenlan.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Zfans
 * @DateTime 2021/02/19 18:37
 */
@Data
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private Integer sort;
    private List<VideoVo> children = new ArrayList<>();
}
