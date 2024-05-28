package com.rxee.client.service;

import com.rxee.qqcommon.Message;
import com.rxee.qqcommon.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConnectServerThread extends Thread {

    // 该类用于管理客户端和服务器端的连接线程
    // 需要一个属性，表示和服务器端连接的Socket
    private Socket socket;

    @Override
    public void run() {
        while (true) {
            System.out.println("客户端线程，等待读取从服务器端发送的消息");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                if (MessageType.MESSAGE_RET_ONLINE_FRIEND.equals(message.getMsgType())) {
                    // 客户端处理在线用户列表
                    String[] s = message.getContent().split(" ");
                    System.out.println("\n============= 当前在线用户列表 ==============");
                    for (int i = 0; i < s.length; i++) {
                        System.out.println("用户：" + s[i]);
                    }
                } else if (MessageType.MESSAGE_COMM_MSG.equals(message.getMsgType())) {
                    // 把从服务器端发来的消息显示到控制台
                    System.out.println("（" + message.getSendTime() + "）" + message.getSender() + " 对你说： " + message.getContent());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ClientConnectServerThread(Socket socket) {
        this.socket = socket;
    }

    public ClientConnectServerThread() {}

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
