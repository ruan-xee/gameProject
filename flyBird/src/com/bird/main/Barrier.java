package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 障碍物类
 */
public class Barrier {
    // 矩阵参数
    private Rectangle rect;

    // 障碍物需要的三个图片
    private static BufferedImage[] images;

    private static final int SPEED = 3;

    static {
        final int COUNT = 2;
        // 类加载的时候将2个图片初始化
        images = new BufferedImage[COUNT];
        for (int i = 0; i < COUNT; i++) {
            images[i] = GameUtil.loadBufferedImgage(Constant.BARRIER_IMG_PATH[i]);
        }
    }

    // 位置
    private int x, y;
    // 宽度、高度
    private int height;
    // 间隔
    int gap;

    // 获得障碍物的宽度和高度
    public static final int BARRIRE_TOP_HEIGHT = images[0].getHeight();
    public static final int BARRIRE_BOTTOM_HEIGHT = images[1].getHeight();
    public Barrier() {
        this.rect = new Rectangle();
    }

    public Barrier(int x, int y, int height, int gap) {
        this.x = x;
        this.y = y;
        this.gap = gap;
        this.height = height;
    }

    // 根据不同的类型绘制障碍物
    public void draw(Graphics g) {
        this.drawTopMormal(g);
        this.drawBottomMormal(g);
    }

    // 绘制从上向下的障碍物
    private void drawTopMormal(Graphics g) {
        g.drawImage(images[0], x, -BARRIRE_TOP_HEIGHT + height, null);
        g.drawRect(x, -BARRIRE_TOP_HEIGHT + height, images[0].getWidth(), BARRIRE_TOP_HEIGHT);
        x -= SPEED;
    }

    // 绘制从下向上的障碍物
    private void drawBottomMormal(Graphics g) {
        if (height + gap < Constant.FRAME_HEIGHT - BARRIRE_BOTTOM_HEIGHT) {
            gap = Constant.FRAME_HEIGHT - BARRIRE_BOTTOM_HEIGHT - height;
        }
        g.drawImage(images[1], x, height + gap, null);
        g.drawRect(x, height + gap, images[0].getWidth(), BARRIRE_BOTTOM_HEIGHT);
    }

    public boolean isInFrame() {
        return (Constant.FRAME_WIDTH - x) > 180;
    }

    public boolean isOutFrame() {
        return x < -150;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height > BARRIRE_TOP_HEIGHT) {
            height = BARRIRE_TOP_HEIGHT - 100;
        }
        this.height = height;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }
}
