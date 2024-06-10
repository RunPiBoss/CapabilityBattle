package com.thec.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import reactor.util.annotation.Nullable;
=======
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973

@Data
@RequiredArgsConstructor
@TableName("cb_test_data")
public class TestData {
    @NonNull
    private Long titleId;
<<<<<<< HEAD
    @Nullable
=======
    @NonNull
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
    private String input;
    @NonNull
    private String output;

    @TableField(exist = false)

    private int result;
<<<<<<< HEAD

    public TestData(@NonNull Long id, @Nullable String sampleInput, @NonNull String sampleOutput) {
        // 构造函数的实现，允许输入为空
    }

=======
>>>>>>> 7abda4eb891bf5278e9eb922b809366a41d02973
}
