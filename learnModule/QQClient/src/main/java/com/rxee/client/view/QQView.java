package com.rxee.client.view;


import com.rxee.client.service.MessageClientService;
import com.rxee.client.service.UserClientService;
import com.rxee.client.util.Utility;

/**
 * 客户端菜单界面
 */
public class QQView {
    /**
     * 是否显示菜单
     */
    private boolean loop = true;
    /**
     * 用户输入
     */
    private String key = "";

    /**
     * 用于登陆服务器/注册用户
     */
    private UserClientService userClientService = new UserClientService();

    public QQView() {
    }

    public void mainMenu() {
        while (loop) {
            System.out.println("===============欢迎登录通讯客户端================");
            System.out.println("\t\t 1 登陆系统");
            System.out.println("\t\t 9 退出系统");

            System.out.print("请选择(1-9):");

            key = Utility.readString(1);
            switch (key) {
                case "1":
                    System.out.print("请输入你的用户号：");
                    String userId = Utility.readString(50);
                    System.out.print("请输入你的密  码：");
                    String pwd = Utility.readString(50);
                    // 到服务器验证账号
                    if (userClientService.checkUser(userId, pwd)) {
                        System.out.println("登陆成功！(用户：" + userId + ")\n");
                        while (loop) {
                            System.out.println("===============二级菜单(用户：" + userId + ")================");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 私聊消息");
                            System.out.println("\t\t 3 群发消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请选择(1-9):");
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    userClientService.onLineFriendList();
                                    break;
                                case "2":
                                    System.out.print("请输入想聊天的用户号（在线）：");
                                    String receiver = Utility.readString(50);
                                    System.out.print("请输入想发送的消息：");
                                    String content = Utility.readString(100);
                                    MessageClientService.sendMessageToSomebody(content, userId, receiver);
                                    break;
                                case "3":
                                    System.out.println("群发消息\n");
                                    break;
                                case "4":
                                    System.out.println("发送文件\n");
                                    break;
                                case "9":
                                    // 调用一个方法，给服务器发送一个退出系统的msg
                                    userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("登陆失败！请重试！");
                    }
                    break;
                case "9":
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }

}
