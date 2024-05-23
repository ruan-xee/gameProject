package com.rxee.udp;

import java.io.IOException;
import java.net.*;

public class UDPSenderB {
    public static void main(String[] args) throws IOException {
        // 1.创建DatagramSocket对象，指定端口号，准备接受数据
        DatagramSocket datagramSocket = new DatagramSocket(9998);

        // 2.将需要发送的数据封装到DatagramPacket对象中
        byte[] bytes = "hello 明天吃火锅".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 9999);

        datagramSocket.send(datagramPacket);

        // 3.构建一个DatagramPacket对象，准备接收数据
        byte[] bys = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bys, bys.length);


        // 4.调用ds对象的方法接收数据
        System.out.println("B 端等待接收数据...");
        datagramSocket.receive(dp);

        // 5.解析数据
        System.out.println("B 端接收到数据：" + new String(dp.getData(), 0, dp.getLength()));

        // 关闭资源
        datagramSocket.close();
    }
}
