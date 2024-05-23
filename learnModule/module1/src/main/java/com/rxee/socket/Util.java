package com.rxee.socket;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class Util {
    public static void readInput(InputStream is) {
        byte[] buf = new byte[1024];
        int readLen = 0;
        try {
            while ((readLen = is.read(buf)) != -1) {
                System.out.println(new String(buf, 0, readLen));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readInputByReader(BufferedReader reader) {
        try {
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 功能：将输入流转换成byte[]，即可以把文件的内容读入到byte[]中
     */
    public static byte[] streamToByteArray(InputStream is) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] result = baos.toByteArray();
        baos.close();
        return result;
    }
}
