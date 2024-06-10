package com.thec.dto;

import lombok.Data;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.dto
 * @Author: thec
 * @CreateTime: 2023-05-07  19:07
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class HistoryQueryDto {

    private Integer playerOne;

    private Integer playerTwo;

    private String time;

    private Integer num;
}
