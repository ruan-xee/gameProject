package com.bird.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GameUtil {
    // 装载图片
    public static BufferedImage loadBufferedImgage(String imgPath) {
        try {
            return ImageIO.read(Files.newInputStream(Paths.get(imgPath)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
