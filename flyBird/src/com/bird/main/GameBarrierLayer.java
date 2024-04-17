package com.bird.main;

import com.bird.util.Constant;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 游戏中障碍物层
 */
public class GameBarrierLayer {
    private List<Barrier> barriers;

    public GameBarrierLayer() {
        barriers = new ArrayList<>();
    }

    // 绘制障碍物
    public void draw(Graphics g, Bird bird) {
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if (barrier.isOutFrame()) {
                Barrierpool.setPool(barrier);
                barriers.remove(i);
                i--;
                continue;
            }
            barrier.draw(g);
        }
        collideBird(bird);
        logic();
    }

    public void logic() {
        if (barriers.size() == 0) {
            insert(Constant.FRAME_WIDTH, 0, getRandomHeight(), getRandomGap());
        } else {
            Barrier last = barriers.get(barriers.size() - 1);
            if (last.isInFrame()) {
                insert(Constant.FRAME_WIDTH, 0, getRandomHeight(), getRandomGap());
            }

        }
    }

    public void insert(int x, int y, int height, int gap) {
        Barrier barrier = Barrierpool.getPool();
        barrier.setX(x);
        barrier.setY(y);
        barrier.setHeight(height);
        barrier.setGap(gap);
        barrier.getRectTop().setSize(Barrier.BARRIRE_TOP_WIDTH, -Barrier.BARRIRE_TOP_HEIGHT + height);
        barrier.getRectBottom().setSize(Barrier.BARRIRE_BOTTOM_WIDTH, height + gap);
        barriers.add(barrier);
    }

    private int getRandomGap() {
        // 间距为50-200;
        return 50 + (int) (Math.random() * 150);
    }

    private int getRandomHeight() {
        return 20 + (int) (Math.random() * Constant.FRAME_HEIGHT);
    }

    /**
     * 判断障碍物和小鸟发生碰撞
     */
    public boolean collideBird(Bird bird) {
        for (int i = 0; i < barriers.size(); i++) {
            Barrier barrier = barriers.get(i);
            if (barrier.getRectBottom().intersects(bird.getRect()) || barrier.getRectTop().intersects(bird.getRect())) {
                System.out.println("撞上了");
            }
        }
        return false;
    }
}
