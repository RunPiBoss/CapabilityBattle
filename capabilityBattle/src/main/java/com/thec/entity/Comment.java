package com.thec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @version 2024/6/3 15:43
 * @autor luoxiaopi 17945868813@qq.com
 * @since JDK8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cb_comment")
public class Comment implements Serializable { // Serializable标识该类可以被序列化

    //和用户id有关的都是Long，因为数据库自增很大

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id; //评论id

    private String content; //评论内容

    private Long userId;// 用户id

    private String time;// 评论时间

    private Integer pid;// 评论父级id

    private Integer originId;//最上级评论Id

    // @TableField(exist = false)表示用来标识实体类中的字段在数据库表中不存在，用于临时存储、表示关联关系或者在代码中处理逻辑所需的属性。
    @TableField(exist = false)
    private String nickname;
    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private List<Comment> children;

    @TableField(exist = false)
    private String pNickname;  // 父节点的用户昵称
    @TableField(exist = false)
    private Long pUserId;  // 父节点的用户id

}
