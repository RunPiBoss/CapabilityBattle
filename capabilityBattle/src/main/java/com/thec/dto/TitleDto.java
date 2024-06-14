package com.thec.dto;

import com.thec.entity.TestData;
import com.thec.entity.Title;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

/**
 * @author thec
 */
@Data
public class TitleDto {

    private Title title;

    private List<TestData> testData;

    private List<String> result = new ArrayList<>();

    boolean isSuccess;
}
