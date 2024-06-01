package com.rxee.client.service;

import com.rxee.qqcommon.Message;
import com.rxee.qqcommon.MessageType;

import java.io.*;

/**
 * 该类完成文件传输服务
 */
public class FileClientService {
    /**
     * 传输文件
     * @param src 源文件路径
     * @param dest 目标文件路径
     * @param senderId 发送者
     * @param receiverId 接收者
     */
    public static void sendFileToSomeone(String src, String dest, String senderId, String receiverId) {
        Message msg = new Message();
        msg.setMsgType(MessageType.MESSAGE_FILE_MSG);
        msg.setSender(senderId);
        msg.setReciever(receiverId);
        msg.setSrc(src);
        msg.setDest(dest);

        FileInputStream fin = null;
        byte[] bytes = new byte[Math.toIntExact(new File(src).length())];
        try {
            fin = new FileInputStream(src);
            fin.read(bytes);
            // 将文件对应的字节数组设置到msg对象中
            msg.setFileBytes(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fin != null) {
                try {
                    fin.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        // 发送
        try {
            ObjectOutputStream oos = new ObjectOutputStream(ManageClientConnectServerThread.getClientConnectServerThread(senderId).getSocket().getOutputStream());
            oos.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 提示信息
        System.out.println("\n 你 向 " + receiverId + " 发送了文件：" + src + " 到对方的目录：" + dest);
    }
}
