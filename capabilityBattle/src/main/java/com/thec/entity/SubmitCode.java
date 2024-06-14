package com.thec.entity;

import lombok.Data;

/**
 * @BelongsProject: capabilityBattle
 * @BelongsPackage: com.thec.entity
 * @Author: thec
 * @CreateTime: 2023-04-30  15:03
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SubmitCode {
    private long roomId;
    private String code;
    private String codeType;
}
