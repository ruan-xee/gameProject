package com.rxee.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        // 1.创建一个DatagramSocket对象，准备接受9999端口数据
        DatagramSocket ds = new DatagramSocket(9999);
        // 2.构建一个DatagramPacket对象，准备接收数据
        byte[] bys = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bys, bys.length);


        // 3.调用ds对象的方法接收数据
        System.out.println("A 端已经启动，等待接收数据...");
        ds.receive(dp);

        // 4.解析数据
        System.out.println("A 端接收到数据：" + new String(dp.getData(), 0, dp.getLength()));

        // 5.将需要发送的数据封装到DatagramPacket对象中
        byte[] bytes = "好的好的".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 9998);

        ds.send(datagramPacket);

        // 5.释放资源
        ds.close();

    }
}
