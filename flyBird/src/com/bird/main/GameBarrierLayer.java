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
    public void draw(Graphics g) {
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

        barriers.add(barrier);
    }

    private int getRandomGap() {
        // 间距为50-200;
        return 50 + (int) (Math.random() * 150);
    }

    private int getRandomHeight() {
        return 20 + (int) (Math.random() * Constant.FRAME_HEIGHT);
    }
}
