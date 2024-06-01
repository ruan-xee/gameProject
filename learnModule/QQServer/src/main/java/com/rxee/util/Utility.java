package com.rxee.util;

import java.util.Scanner;

public class Utility {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readString(int i){
        // 读取i个用户输入的字符，返回字符串
        String s = scanner.nextLine();
        return s.substring(0, Math.min(s.length(), i));
    }
}
