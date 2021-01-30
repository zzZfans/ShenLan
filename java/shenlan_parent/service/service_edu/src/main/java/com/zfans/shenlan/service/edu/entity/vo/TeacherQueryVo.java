package com.zfans.shenlan.service.edu.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TeacherQueryVo
 * @Description Todo
 * @Author Zfans
 * @DateTime 2021/01/26 13:19
 */
@Data
public class TeacherQueryVo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Integer level;
    private String joinDateBegin;
    private String joinDateEnd;
}
