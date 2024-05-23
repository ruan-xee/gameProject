package com.rxee.socket;

import java.io.*;
import java.net.Socket;

public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {

        // 获取输出流，向服务器发送信息
        try {
            // 创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("192.168.2.10", 9999);
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter writer = new BufferedWriter(outputStreamWriter);
            writer.write("Hello, Server!\nHELLO,SERVER!");
            writer.newLine();
            writer.flush();

            Util.readInputByReader(new BufferedReader(new InputStreamReader(socket.getInputStream())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
