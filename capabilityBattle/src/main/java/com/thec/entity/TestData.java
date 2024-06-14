package com.thec.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import reactor.util.annotation.Nullable;

@Data
@RequiredArgsConstructor
@TableName("cb_test_data")
public class TestData {
    @NonNull
    private Long titleId;
    @Nullable
    private String input;
    @NonNull
    private String output;

    @TableField(exist = false)

    private int result;

    public TestData(@NonNull Long id, @Nullable String sampleInput, @NonNull String sampleOutput) {
        // 构造函数的实现，允许输入为空
    }

}
