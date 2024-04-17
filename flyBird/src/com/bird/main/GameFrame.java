package com.bird.main;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import static com.bird.util.Constant.*;

public class GameFrame extends Frame {

    // 实例化GameBackGround类
    private GameBackGround gameBackGround;

    // 实例化Bird类
    private Bird bird;

    // 实例化前景
    private GameFrontGround gameFrontGround;

    private GameBarrierLayer gameBarrierLayer;

    //
    private BufferedImage buffImg = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);

    public GameFrame() throws HeadlessException {
        setVisible(true);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setTitle(FRAME_TITLE);
        setLocation(FRAME_X, FRAME_Y);
        setResizable(false);
        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.initGame();
        new run().start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                add(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                minu(e);
            }
        });
    }

    public void initGame() {
        gameBackGround = new GameBackGround();
        gameFrontGround = new GameFrontGround();
        gameBarrierLayer = new GameBarrierLayer();
        bird = new Bird();
    }

    class run extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void update(Graphics g) {
        // 得到图片的画笔
        Graphics graphics = buffImg.getGraphics();
        gameBackGround.draw(graphics);
        gameFrontGround.draw(graphics);
        gameBarrierLayer.draw(graphics, bird);
        bird.draw(graphics);

        // 一次性将图片绘制到图片上
        g.drawImage(buffImg, 0, 0, null);
    }

    // 按键
    public void add(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                bird.fly(1);
                break;
        }
    }

    // 抬键
    public void minu(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                bird.fly(5);
                break;
        }
    }
}
