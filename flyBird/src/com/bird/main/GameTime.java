package com.bird.main;

public class GameTime {
    // 开始时间
    private long beginTime;
    // 结束时间
    private long endTime;
    // 时间差
    private long differ;

    public void begin() {
        this.beginTime = System.currentTimeMillis();
    }

    public long diff() {
        this.endTime = System.currentTimeMillis();
        return this.differ = (this.endTime - this.beginTime) / 1000;
    }
}
