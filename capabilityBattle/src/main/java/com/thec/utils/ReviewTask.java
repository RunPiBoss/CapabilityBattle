package com.thec.utils;

import com.thec.dto.TitleDto;
import com.thec.entity.TestData;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import static com.thec.utils.SystemConstants.*;

public class ReviewTask implements Callable<TitleDto> {
    private TitleDto titleDto;
    private String code;
    private String codeFile;
    private String compileCommand;
    private String runCommand;

    public ReviewTask(TitleDto titleDto, String code, String codeType) {
        this.titleDto = titleDto;
        this.code = code;
        if (codeType.equals("java")) {
            codeFile = JAVA_CODE_FILE;
            compileCommand = JAVA_COMPILE_COMMAND;
            runCommand = JAVA_RUN_COMMAND;
        } else if (codeType.equals("c")) {
            codeFile = C_CODE_FILE;
            compileCommand = C_COMPILE_COMMAND;
            runCommand = C_RUN_COMMAND;
        } else if (codeType.equals("cpp")) {
            codeFile = CPP_CODE_FILE;
            compileCommand = CPP_COMPILE_COMMAND;
            runCommand = CPP_RUN_COMMAND;


        }
    }

    @Override
    public TitleDto call() throws Exception {
        //拷贝代码到文件
        ReviewUtil.writeCode(code, codeFile);
        //编译

        boolean success = ReviewUtil.CompileCode(compileCommand);
        if (!success) {
            for (TestData testDatum : titleDto.getTestData()) {
                titleDto.getResult().add("编译错误");
            }
            return titleDto;
        }

        //运行并评测
        int flag = 0;
        for (TestData testDatum : titleDto.getTestData()) {
            String s = ReviewUtil.runCode(runCommand, testDatum, titleDto.getTitle().getTimeLimit(), TimeUnit.MILLISECONDS);
            if (testDatum.getResult() != 1) {
                flag = 1;
            }
            titleDto.getResult().add(s);
        }
        titleDto.setSuccess(flag == 0);

        return titleDto;
    }


}
