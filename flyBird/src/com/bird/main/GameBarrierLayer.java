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

    private GameTime gameTime;

    public GameBarrierLayer() {
        barriers = new ArrayList<>();
        gameTime = new GameTime();
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
        logic(g);
    }

    public void logic(Graphics g) {
        if (barriers.size() == 0) {
            insert(Constant.FRAME_WIDTH, 0, getRandomHeight(), getRandomGap());
            gameTime.begin();
        } else {
            Barrier last = barriers.get(barriers.size() - 1);
            if (last.isInFrame()) {
                insert(Constant.FRAME_WIDTH, 0, getRandomHeight(), getRandomGap());
            }
            long diff = gameTime.diff();
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g.drawString("坚持时长：" + diff + "秒", 100, 50);

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
                bird.setLive(false);
            }
        }
        return false;
    }

    /**
     * 用于清空障碍物的池子
     */
    public void restant() {
        barriers.clear();
    }
}
