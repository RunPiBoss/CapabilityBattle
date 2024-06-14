package com.thec.dto;

import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.dto
 * @Author: thec
 * @CreateTime: 2023-04-27  14:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class UserDto {
    /**
     * id
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 天梯分数
     */
    private Integer rating;
}
