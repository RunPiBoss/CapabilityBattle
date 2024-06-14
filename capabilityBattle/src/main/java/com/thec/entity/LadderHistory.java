package com.thec.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.entity
 * @Author: thec
 * @CreateTime: 2023-04-25  00:10
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cb_ladder_history")
public class LadderHistory {

    private int ownerId;

    private int myId;

    private int winnerId;

    private String time;

    private int playAdd;
    // 战力值
    private int rating;
}
