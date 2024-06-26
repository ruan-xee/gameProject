package com.bird.main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Cloud {
    private BufferedImage img;
    private int speed;
    private int x,y;
    public Cloud() {}
    public Cloud(BufferedImage img, int speed, int x, int y) {
        this.img = img;
        this.speed = speed;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        x -= speed;
        g.drawImage(img, x, y, null);
    }

    /**
     * 用于判断云彩是否飞出屏幕以外
     */
    public boolean isOutFrame() {
        return x < -100;
    }
}
