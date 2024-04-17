package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird {
    // 小鸟矩形形象
    private Rectangle rect;

    // 存放小鸟图片
    private BufferedImage[] images;
    public static final int BIRD_COUNT = 3;

    // 鸟状态
    private int state;
    public static final int STATE_NORMAL = 0; // 平着飞
    public static final int STATE_UP = 1; // 向上飞
    public static final int STATE_DOWN = 2; // 向下飞

    // 小鸟移动方向
    private boolean up = false;
    private boolean down = false;

    // 小鸟的移动速度
    private int speed = 5;

    // 小鸟的位置
    private int bird_x = 150;
    private int bird_y = 200;

    public Bird() {
        images = new BufferedImage[BIRD_COUNT];
        for (int i = 0; i <BIRD_COUNT; i++) {
            images[i] = GameUtil.loadBufferedImgage(Constant.BIRD_IMGS[i]);
        }

        int w = images[0].getWidth() - 10;
        int h = images[0].getHeight() - 10;
        rect = new Rectangle(w, h);
    }

    public void draw(Graphics g) {
        this.flyLogic();
        g.drawImage(images[state], bird_x, bird_y, null);

        g.drawRect(bird_x + 5, bird_y + 5, rect.width, rect.height);
        rect.x = bird_x + 5;
        rect.y = bird_y + 5;
    }

    // 控制小鸟移动方向
    public void flyLogic() {
        if (up) {
            bird_y -= speed;
            if (bird_y < 20) {
                bird_y = 20;
            }
        } else {
            bird_y += speed;
            if (bird_y > 465) {
                bird_y = 465;
            }
        }
    }

    public void fly(int fly) {
        switch (fly) {
            case 1:
                state = STATE_UP;
                up = true;
                break;
            case 5:
                state = STATE_DOWN;
                up = false;
                break;

        }
    }
}
