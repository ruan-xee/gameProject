package com.bird.main;

import com.bird.util.Constant;
import com.bird.util.GameUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 游戏背景类
 */
public class GameBackGround {
    // 背景图片
    private BufferedImage bg_land;

    private BufferedImage bg_day;

    public GameBackGround() {
        bg_land = GameUtil.loadBufferedImgage(Constant.BG_LAND_PATH);
        bg_day = GameUtil.loadBufferedImgage(Constant.BG_DAY_PATH);
    }

    // 绘制图片
    public void draw(Graphics g) {
        // 绘制天空背景
        int weight_day = bg_day.getWidth();

        int count_day = Constant.FRAME_WIDTH / weight_day + 1;

        for (int i = 0; i < count_day; i++) {
            g.drawImage(bg_day, weight_day*i, 0, null);
        }


        // 绘制地板背景
        int height = bg_land.getHeight();
        int weight = bg_land.getWidth();
        int count = Constant.FRAME_WIDTH / weight + 1;

        for (int i = 0; i < count; i++) {
            g.drawImage(bg_land, weight*i, Constant.FRAME_HEIGHT - height, null);
        }
    }
}
