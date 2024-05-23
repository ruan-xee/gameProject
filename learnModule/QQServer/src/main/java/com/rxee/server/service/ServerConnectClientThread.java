package com.rxee.server.service;

import com.rxee.qqcommon.Message;
import com.rxee.qqcommon.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerConnectClientThread extends Thread{
    private Socket socket;
    private String userId;

    public ServerConnectClientThread() {
    }

    public ServerConnectClientThread(String userId, Socket socket) {
        this.userId = userId;
        this.socket = socket;
    }

    @Override
    public void run() { // 这里线程处于run状态，可以发送/接收消息
        while (true) {
            System.out.println("服务端和客户端 " + userId + " 保持通信，读取数据。。。");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                // 判断msg类型，做相应的业务处理
                if (MessageType.MESSAGE_GET_ONLINE_FRIEND.equals(message.getMsgType())) {
                    // 客户端获取在线列表
                    System.out.println(message.getSender() + " 请求在线用户列表。。。");
                    String onlineUser = ManageServerConnectClientThread.getOnlineUser();
                    Message retMsg = new Message();
                    retMsg.setMsgType(MessageType.MESSAGE_RET_ONLINE_FRIEND);
                    retMsg.setContent(onlineUser);
                    retMsg.setReciever(message.getSender());
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(retMsg);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}