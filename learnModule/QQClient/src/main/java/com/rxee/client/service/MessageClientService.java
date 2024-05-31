package com.rxee.client.service;

import com.rxee.qqcommon.Message;
import com.rxee.qqcommon.MessageType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 该类提供消息相关的服务方法
 */
public class MessageClientService {
    public static void sendMessageToSomebody(String content, String sendId, String receiverId) {
        // 构建msg对象
        Message msg = new Message();
        msg.setMsgType(MessageType.MESSAGE_COMM_MSG);
        msg.setSender(sendId);
        msg.setReciever(receiverId);
        msg.setSendTime(new Date().toString());
        msg.setContent(content);
        System.out.println("("+ msg.getSendTime() + "） 你 对 " + receiverId + " 说： " + content);
        ClientConnectServerThread clientConnectServerThread = ManageClientConnectServerThread.getClientConnectServerThread(sendId);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientConnectServerThread.getSocket().getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMessageToEveryOnline(String content, String sendId) {
        Message msg = new Message();
        msg.setMsgType(MessageType.MESSAGE_COMM_MSG_TO_ALL);
        msg.setSender(sendId);
        msg.setSendTime(new Date().toString());
        msg.setContent(content);
        System.out.println("("+ msg.getSendTime() + "） 你 对 所有人 说： " + content);
        ClientConnectServerThread clientConnectServerThread = ManageClientConnectServerThread.getClientConnectServerThread(sendId);
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientConnectServerThread.getSocket().getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
