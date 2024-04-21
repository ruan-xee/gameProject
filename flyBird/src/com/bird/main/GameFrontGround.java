package com.bird.main;

import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameFrontGround {
    // 云彩的个数
    private static final int CLOUD_COUNT = 2;
    // 存放云彩的容器
    private List<Cloud> clouds;
    // 云彩的飞行速度
    private final static int CLOUD_SPEED = 1;
    // 使用到的图片资源
    private BufferedImage[] img;
    // 用于产生随机数
    private Random random;

    public GameFrontGround() {
        clouds = new ArrayList<>();
        img = new BufferedImage[CLOUD_COUNT];
        random = new Random();

        // 容器中添加云彩图片
        for (int i= 0; i < CLOUD_COUNT; i++) {
            img[i] = GameUtil.loadBufferedImgage("img/medals_" + i + ".png");
        }
    }


    public void draw(Graphics g) {
        this.logic();
        for (int i = 0; i < clouds.size(); i++) {
            clouds.get(i).draw(g);
        }
    }

    /**
     * 用于云彩的个数控制
     */
    private void logic() {
        if ((int)(500 *Math.random()) < 10) {
            Cloud cloud = new Cloud(img[random.nextInt(CLOUD_COUNT)], CLOUD_SPEED, 600, random.nextInt(150));
            clouds.add(cloud);
        }
        for (int i = 0; i < clouds.size(); i++) {
            Cloud cloud = clouds.get(i);
            if (cloud.isOutFrame()) {
                clouds.remove(i);
                i--;
            }
        }
    }
}
