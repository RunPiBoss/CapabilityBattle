package com.thec.utils;

import java.io.File;

public class SystemConstants {
    public static final String JAVA_CODE_FILE = "java" + File.separator + "Main.java";
    public static final String JAVA_COMPILE_COMMAND = "javac java" + File.separator + "Main.java";
    public static final String JAVA_RUN_COMMAND = "java -cp  ." + File.separator + "java Main";

    public static final String C_CODE_FILE = "c" + File.separator + "main.c";
    public static final String C_COMPILE_COMMAND = "gcc -o c" + File.separator + "main.exe c" + File.separator + "main.c";
    public static final String C_RUN_COMMAND = "c" + File.separator + "main.exe";

    public static final String CPP_CODE_FILE = "cpp" + File.separator + "main.cpp";
    public static final String CPP_COMPILE_COMMAND = "g++ -o cpp" + File.separator + "main.exe cpp" + File.separator + "main.cpp";
    public static final String CPP_RUN_COMMAND = "cpp" + File.separator + "main.exe";

    public static final String AVATAR_URL = File.separator+"root"+File.separator+"images"+File.separator;

    public static final String STD_OUT_FILE = "output" + File.separator + "stdout.txt";
    public static final String STD_ERR_FILE = "output" + File.separator + "stderr.txt";

    public static final String COMPETITION_ROOM_PREFIX = "room:";
    public static final String LADDER_BATTLE_ROOM = "ladderRoom:";

}
