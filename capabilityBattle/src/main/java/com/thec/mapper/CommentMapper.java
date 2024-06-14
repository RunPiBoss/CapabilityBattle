package com.thec.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thec.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @version 2024/6/3 16:14
 * @autor luoxiaopi 1794586882163@qq.com
 * @since JDK8
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select c.*,u.nick_name,u.avatar from cb_comment c left join cb_user u on c.user_id = u.id  order by id desc")
    List<Comment> findCommentDetail();
}
