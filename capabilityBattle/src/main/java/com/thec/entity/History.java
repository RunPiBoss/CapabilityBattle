package com.thec.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.entity
 * @Author: thec
 * @CreateTime: 2023-04-25  00:06
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cb_history")
public class History {

    private int playerOneId;

    private int playerTwoId;

    private int num;

    private String time;

    @TableField(exist = false)
    private Long title;
    @TableField(exist = false)
    private List<LadderHistory> details;

    private int playAdd;
<<<<<<< HEAD
    // 战力值
=======

>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
    private int rating;

}
