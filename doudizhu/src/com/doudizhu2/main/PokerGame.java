package com.doudizhu2.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

public class PokerGame {
    // 牌-值对应表
    static HashMap<Integer, String> hm = new HashMap<>();
    // 牌盒（只有值）
    static ArrayList<Integer> pokerNums = new ArrayList<>();

    static {
        // 准备牌
        String[] color = {"♦", "♣", "♥", "♠"};
        String[] num = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        int serialNum = 1;

        for (String n: num) {
            for (String c: color) {
                hm.put(serialNum, c + n);
                pokerNums.add(serialNum++);
            }
        }

        hm.put(serialNum, "小王");
        pokerNums.add(serialNum++);
        hm.put(serialNum, "大王");
        pokerNums.add(serialNum);
    }

    public PokerGame() {
        // 洗牌
        Collections.shuffle(pokerNums);
        // 发牌
        TreeSet<Integer> lord = new TreeSet<>();
        TreeSet<Integer> player1 = new TreeSet<>();
        TreeSet<Integer> player2 = new TreeSet<>();
        TreeSet<Integer> player3 = new TreeSet<>();

        for (int i = 0; i < pokerNums.size(); i++) {
            if (i < 3) {
                lord.add(pokerNums.get(i));
                continue;
            }
            if (i % 3 == 0) {
                player1.add(pokerNums.get(i));
            } else if (i %3 == 1) {
                player2.add(pokerNums.get(i));
            } else {
                player3.add(pokerNums.get(i));
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
    public void loodPokers(String name, TreeSet<Integer> list) {
        System.out.print(name + ":");
        for (Integer i: list) {
            System.out.print(hm.get(i) + "  ");
        }
        System.out.println();
    }
}
