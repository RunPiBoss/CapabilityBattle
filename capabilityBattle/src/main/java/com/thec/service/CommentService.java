package com.thec.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.thec.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {
    List<Comment> findCommentDetail();
}
