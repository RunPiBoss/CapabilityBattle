package com.thec.utils;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.thec.entity.TestData;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class ReviewUtil {

    private static final Runtime runtime;

    static {
        runtime = Runtime.getRuntime();
    }

    /**
     * 将要评测的代码写入文件
     *
     * @param code     评测代码
     * @param codeFile 代码存放文件路径
     */
    public static void writeCode(String code, String codeFile) {
        File file = new File(codeFile);
        if (!FileUtil.exist(file)) {
            FileUtil.touch(file);
        }
        FileUtil.writeBytes(code.getBytes(), file);
    }

    /**
     * 编译代码
     *
     * @param compileCommand 编译命令
     * @return 是否编译成功
     */
    public static boolean CompileCode(String compileCommand) {
        Process process;
        try {
            process = runtime.exec(compileCommand);
        } catch (IOException e) {
            return false;
        }


        try {
//            InputStream stdoutFrom = process.getInputStream();
//            String compileOut = IoUtil.read(stdoutFrom, CharsetUtil.CHARSET_UTF_8);
//            if (!StrUtil.isBlank(compileOut)) {
//                return false;
//            }

            //从编译进程错误输出流中读取内容，读取完毕后关闭流
            InputStream stderrFrom = process.getErrorStream();
            String compileErrOut = IoUtil.read(stderrFrom, CharsetUtil.CHARSET_UTF_8);
            if (!StrUtil.isBlank(compileErrOut)) {
                return false;
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     * 运行代码并返回评测结果
     *
     * @param runCommand 运行命令
     * @param testData   评测数据
     * @param timeout    超时时间
     * @param unit       时间单位
     * @return 评测结果
     */
    public static String runCode(String runCommand, TestData testData, long timeout, TimeUnit unit) {
        Process process;
        long pre = System.currentTimeMillis();
        try {
            process = runtime.exec(runCommand);
        } catch (IOException e) {
            testData.setResult(0);
            return "系统内部错误";
        }

        try {
            if (!StrUtil.isBlank(testData.getInput())) {
                OutputStream outputStream = process.getOutputStream();
                IoUtil.write(outputStream, true, testData.getInput().getBytes());

            }

            InputStream stderrFrom = process.getErrorStream();
            InputStream stdoutFrom = process.getInputStream();


            boolean timeLimit = process.waitFor(timeout - (System.currentTimeMillis() - pre), unit);
            if (!timeLimit) {
                testData.setResult(0);
                return "运行超时";
            }

            String runErrOut = IoUtil.read(stderrFrom, CharsetUtil.CHARSET_UTF_8);
            if (!StrUtil.isBlank(runErrOut)) {
                testData.setResult(0);
                return runErrOut;
            }

            String runOut = IoUtil.read(stdoutFrom, CharsetUtil.CHARSET_UTF_8);
            if (!StrUtil.equals(runOut.trim(), testData.getOutput())) {
                testData.setResult(0);
                return "答案错误";
            }

            testData.setResult(1);
            return "答案正确";

        } catch (Exception e) {
            System.out.println(e);
            testData.setResult(0);
            return "系统内部错误";

        }

    }

}
