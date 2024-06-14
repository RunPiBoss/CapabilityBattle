package com.thec.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("cb_title")
public class Title {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;

    private String titleDescribe;

    private String inputFormat;

    private String outputFormat;

    private String sampleInput;

    private String sampleOutput;

    private int lengthLimit;

    private int timeLimit;

    private int memoryLimit;
}
