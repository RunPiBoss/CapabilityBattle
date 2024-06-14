package com.thec.dto;

import lombok.Data;

@Data
public class TitleAddRequest {

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
