package com.dmitri.ifmo_web_lab_2.entity;

import java.time.LocalDateTime;

public final class TableRow {
    private final float x;
    private final float y;
    private final float r;
    private final boolean hit;
    private final String clientDate;
    private final double scriptWorkingTime;

    public TableRow(float x, float y, float r, boolean hit, String clientDate, double scriptWorkingTime) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
        this.clientDate = clientDate;
        this.scriptWorkingTime = scriptWorkingTime;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public boolean isHit() {
        return hit;
    }

    public String getClientDate() {
        return clientDate;
    }

    public double getScriptWorkingTime() {
        return scriptWorkingTime;
    }
}
