package com.thec.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@TableName("cb_test_data")
public class TestData {
    @NonNull
    private Long titleId;
    @NonNull
    private String input;
    @NonNull
    private String output;

    @TableField(exist = false)

    private int result;
}
