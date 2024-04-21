package com.doudizhu.main;

import java.util.ArrayList;
import java.util.Collections;

public class PokerGame {
    static ArrayList<String> pokerList = new ArrayList<>();
    static {
        // 准备牌
        String[] color = {"♦", "♣", "♠", "♥"};
        String[] num = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (String c: color) {
            for (String n: num) {
                pokerList.add(c + n);
            }
        }
        pokerList.add("大王");
        pokerList.add("小王");
    }
    public PokerGame() {
        // 洗牌
        Collections.shuffle(pokerList);
        // 发牌
        ArrayList<String> lord = new ArrayList<>();
        ArrayList<String> player1 = new ArrayList<>();
        ArrayList<String> player2 = new ArrayList<>();
        ArrayList<String> player3 = new ArrayList<>();

        for (int i = 0; i < pokerList.size(); i++) {
            if (i < 3) {
                lord.add(pokerList.get(i));
                continue;
            }
            if (i % 3 == 0) {
                player1.add(pokerList.get(i));
            } else if (i %3 == 1) {
                player2.add(pokerList.get(i));
            } else {
                player3.add(pokerList.get(i));
            }
        }

        // 看牌
        this.loodPokers("底牌", lord);
        this.loodPokers("玩家1", player1);
        this.loodPokers("玩家2", player2);
        this.loodPokers("玩家3", player3);

    }

    /**
     * 看牌
     * @param name 玩家名字
     * @param list 玩家手牌
     */
    public void loodPokers(String name, ArrayList<String> list) {
        System.out.print(name + ":");
        for (String s: list) {
            System.out.print(s + " ");
        }
        System.out.println();
    }
}
