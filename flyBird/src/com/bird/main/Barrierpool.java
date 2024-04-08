package com.bird.main;

import java.util.ArrayList;
import java.util.List;

public class Barrierpool {
    private static List<Barrier> pool = new ArrayList<Barrier>();

    public static final int initCount = 16;

    public static final int maxCount = 20;

    static {
        for (int i = 0; i < initCount; i++) {
            pool.add(new Barrier());
        }
    }

    /**
     * 从池中获取一个对象
     */

    public static Barrier getPool() {
        int size = pool.size();
        if (size > 0) {
            System.out.println("拿走一个");
            return pool.remove(size - 1);
        } else {
            System.out.println("新的对象");
            return new Barrier();
        }
    }

    /**
     * 归还对象到容器中
     */
    public static void setPool(Barrier barrier) {
        if (pool.size() < maxCount) {
            pool.add(barrier);
            System.out.println("归还一个对象至容器");
        }
    }
}
