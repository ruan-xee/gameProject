package com.rxee.qqcommon;

import java.io.Serializable;

/**
 * 客户端和服务器端通讯时的消息对象
 */
public class Message implements Serializable {
    private static final long serialVersionUID = -2594067185960263469L;

    /**
     * 发送者
     */
    private String sender;
    /**
     * 接受者
     */
    private String reciever;
    /**
     * 发送内容
     */
    private String content;
    /**
     * 发送时间
     */
    private String sendTime;
    /**
     * 消息类型
     */
    private String msgType;

    private byte[] fileBytes;
    private long fileLen = 0;
    private String dest; // 将文件传输到哪里
    private String src; // 源文件路径

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public long getFileLen() {
        return fileLen;
    }

    public void setFileLen(long fileLen) {
        this.fileLen = fileLen;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReciever() {
        return reciever;
    }

    public void setReciever(String reciever) {
        this.reciever = reciever;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
