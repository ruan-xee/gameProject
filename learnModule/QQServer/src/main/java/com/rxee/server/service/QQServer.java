package com.rxee.server.service;

import com.rxee.qqcommon.Message;
import com.rxee.qqcommon.MessageType;
import com.rxee.qqcommon.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 这是服务器，端口9999，等待客户端的连接，并保持通信
 */
public class QQServer {
    private ServerSocket ss = null;

    // 创建一个集合存放多个用户，如果是这些用户登陆，就认为是合法的
    private static ConcurrentHashMap<String, User> validUsers = new ConcurrentHashMap<>();
    static {
        validUsers.put("100", new User("100", "123456"));
        validUsers.put("200", new User("200", "123456"));
        validUsers.put("300", new User("300", "123456"));
        validUsers.put("Tian", new User("Tian", "123456"));
        validUsers.put("Doinb", new User("Doinb", "123456"));
        validUsers.put("Uzi", new User("Uzi", "123456"));
    }

    // 验证用户是否有效的方法
    private boolean userValid(User user) {
        User user1 = validUsers.get(user.getUserId());
        if(user1 == null) {
            return false;
        }
        return user1.getPasswd().equals(user.getPasswd());
    }

    public QQServer() {
        try {
            System.out.println("服务端在9999端口监听，等待客户端连接...");
            // 启动推送新闻的线程
            new Thread(new SendNewsToAllService()).start();
            this.ss = new ServerSocket(9999);
            while (true) { // 当和某个客户端建立连接后会继续监听
                Socket socket = ss.accept();
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                // 得到socket关联的对象输出流
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

                User user = (User) ois.readObject();
                Message msg = new Message();
                // 验证用户
                if(this.userValid(user)) {
                    // 登陆成功
                    msg.setMsgType(MessageType.MESSAGE_LOGIN_SUCCEED);
                    // 将msg对象回复
                    oos.writeObject(msg);
                    // 创建一个线程和客户端保持通信，该线程需要持有socket对象
                    ServerConnectClientThread scct = new ServerConnectClientThread(user.getUserId(), socket);
                    scct.start();
                    // 把该线程对象放入到一个集合中，进行管理
                    ManageServerConnectClientThread.addServerConnectClientThread(user.getUserId(), scct);
                } else {
                    // 登陆失败
                    System.out.println("用户id：" + user.getUserId() + " 验证失败！");
                    msg.setMsgType(MessageType.MESSAGE_LOGIN_FAIL);
                    oos.writeObject(msg);
                    // 关闭socket
                    socket.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 如果服务端退出while循环，说明服务端不再监听
            // 因此我们需要关闭serversocket
            try {
                if (ss != null) {
                    ss.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
