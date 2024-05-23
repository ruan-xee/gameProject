package com.rxee.socket;

import java.io.*;
import java.net.Socket;

public class TCPFileUploadClient {
    public static void main(String[] args) throws IOException {

        // 获取输出流，向服务器发送信息
        try {
            // 创建客户端Socket，指定服务器地址和端口
            Socket socket = new Socket("192.168.2.10", 9999);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream("module1/src/main/resources/A.jpg"));

            byte[] bytes = Util.streamToByteArray(bis);
            BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
            bos.write(bytes);
            bos.flush();
            socket.shutdownOutput();

            // 获取输入流，接收服务器响应的信息
            InputStream inputStream = socket.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.equals("上传成功")) {
                    System.out.println("文件上传成功");
                } else {
                    System.out.println("文件上传失败");
                }
            }

            // 关闭资源
            bis.close();
            bos.close();
            reader.close();
            inputStream.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
