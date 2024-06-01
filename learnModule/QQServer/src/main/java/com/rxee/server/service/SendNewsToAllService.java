package com.rxee.server.service;

import com.rxee.qqcommon.Message;
import com.rxee.qqcommon.MessageType;
import com.rxee.util.Utility;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class SendNewsToAllService implements Runnable{
    @Override
    public void run() {
        boolean flag = true;
        // 为了可以推送多次，使用while
        while(flag) {
            System.out.print("请输入服务器要推送的新闻/消息[输入exit退出推送服务]：");
            String news = Utility.readString(100);
            if ("exit".equals(news)) {
                flag = false;
                continue;
            }
            Message msg = new Message();
            msg.setMsgType(MessageType.MESSAGE_COMM_MSG_TO_ALL);
            msg.setSender("服务器");
            msg.setContent(news);
            msg.setSendTime(new Date().toString());
            System.out.println("服务器推送消息给所有人：" + news);

            // 遍历当前所有的通讯线程
            for (ServerConnectClientThread serverConnectClientThread: ManageServerConnectClientThread.getHm().values()) {
                // 推送消息
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(serverConnectClientThread.getSocket().getOutputStream());
                    oos.writeObject(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
