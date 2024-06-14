package com.thec.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.thec.entity.Comment;
import com.thec.mapper.CommentMapper;
import com.thec.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @version 2024/6/3 16:07
 * @autor luoxiaopi 1794586882163@qq.com
 * @since JDK8
 */
@Service
@Slf4j
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> findCommentDetail() {
        return commentMapper.findCommentDetail();
    }
}
