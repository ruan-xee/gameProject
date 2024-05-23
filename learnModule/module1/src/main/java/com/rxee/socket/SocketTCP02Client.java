package com.rxee.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketTCP02Client {
    public static void main(String[] args) throws IOException {

        // 获取输出流，向服务器发送信息
        try {
            // 创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("192.168.2.10", 9999);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Hello, Server!".getBytes());
            outputStream.flush();
            socket.shutdownOutput();

            Util.readInput(socket.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
