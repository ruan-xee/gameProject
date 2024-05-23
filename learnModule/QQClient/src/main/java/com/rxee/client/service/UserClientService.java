package com.rxee.client.service;

import com.rxee.qqcommon.Message;
import com.rxee.qqcommon.MessageType;
import com.rxee.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * 该类完成用户登陆验证和用户注册等功能
 */
public class UserClientService {
    // 因为可能在其他地方使用到User信息，因此作为成员属性
    private User u = new User();
    // 因为Socket在其他地方也可能使用，因此也作为属性
    private Socket socket;

    /**
     * 通过userId和passwd到服务器中查询
     */
    public boolean checkUser(String userId,String passwd) {
        u.setUserId(userId);
        u.setPasswd(passwd);
        // 连接到服务端 发送u对象
        try {
            socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
            // 得到ObjectOutputStream对象
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u); // 发送User对象

            // 读取从服务器端回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message) ois.readObject();
            if (MessageType.MESSAGE_LOGIN_SUCCEED.equals(message.getMsgType())) {
                // 创建一个和服务器端保持通信的线程
                ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
                ccst.start();
                // 为了客户端的一个扩展，将线程放入到集合中
                ManageClientConnectServerThread.addClientConnectServerThread(userId, ccst);
                return true;
            } else if (MessageType.MESSAGE_LOGIN_FAIL.equals(message.getMsgType())) {
                // 如果登陆失败，我们就不能启动和服务器通信的线程，关闭socket
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 向服务器端请求在线用户列表
     */
    public void onLineFriendList() {
        Message msg = new Message();
        msg.setMsgType(MessageType.MESSAGE_GET_ONLINE_FRIEND);
        msg.setSender(u.getUserId());
        // 发送消息给服务器
        // 应该得到当前线程的Socket 对应的 ObjectOutputStream对象
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    ManageClientConnectServerThread.getClientConnectServerThread(
                            u.getUserId()).getSocket().getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
