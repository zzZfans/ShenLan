package com.zfans.shenlan.service.edu.service.impl;

import com.zfans.shenlan.service.edu.entity.Comment;
import com.zfans.shenlan.service.edu.mapper.CommentMapper;
import com.zfans.shenlan.service.edu.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author Zfans
 * @since 2021-01-25
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

}
