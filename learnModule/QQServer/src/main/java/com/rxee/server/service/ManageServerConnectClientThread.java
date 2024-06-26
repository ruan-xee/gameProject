package com.rxee.server.service;

import com.rxee.qqcommon.Message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 该类用于管理和客户端通信的线程
 */
public class ManageServerConnectClientThread {
    private static HashMap<String, ServerConnectClientThread> hm = new HashMap<>();

    // 离线消息缓存，key为接受者的userid
    private static HashMap<String, List<Message>> hmMsg = new HashMap<>();

    /**
     * 添加线程对象
     */
    public static void addServerConnectClientThread(String userId, ServerConnectClientThread serverConnectClientThread) {
        hm.put(userId, serverConnectClientThread);
    }

    /**
     * 获取一个线程对象
     */
    public static ServerConnectClientThread getServerConnectClientThread(String userId) {
        return hm.get(userId);
    }

    /**
     * 可以返回在线用户列表
     */
    public static String getOnlineUser() {
        // 遍历hashMap的key
        Set<String> strings = hm.keySet();
        StringBuilder sb = new StringBuilder();
        for (String string : strings) {
            sb.append(string).append(" ");
        }
        return sb.toString();
    }

    public static void removeServerConnectClientThread(String userId) {
        hm.remove(userId);
    }

    public static HashMap<String, ServerConnectClientThread> getHm() {
        return hm;
    }

    public static HashMap<String, List<Message>> getHmMsg() {
        return hmMsg;
    }

    public static void addMsg(String userId, Message message) {
        List<Message> messages = hmMsg.computeIfAbsent(userId, k -> new ArrayList<>());
        messages.add(message);
    }

    public static List<Message> readAndRemoveMsg(String userId) {
        return hmMsg.remove(userId);
    }
}
