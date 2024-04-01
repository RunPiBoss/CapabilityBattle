package com.thec.dto;

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
public class LadderHistoryQueryDto {

    private int myId;

    private String time;

    private int num;
}
