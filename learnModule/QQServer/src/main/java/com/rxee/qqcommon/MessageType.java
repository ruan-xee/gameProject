package com.rxee.qqcommon;

public interface MessageType {
    String MESSAGE_LOGIN_SUCCEED = "1"; // 登陆成功
    String MESSAGE_LOGIN_FAIL = "2"; // 登陆失败
    String MESSAGE_COMM_MSG = "3"; // 普通信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4"; // 获取在线用户请求
    String MESSAGE_RET_ONLINE_FRIEND = "5"; // 返回在线用户列表
    String MESSAGE_CLIENT_EXIT = "6"; // 客户端退出
}
