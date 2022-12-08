package com.dmitri.ifmo_web_lab_2.util;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.dmitri.ifmo_web_lab_2.model.HitCheck;

@ManagedBean
@ApplicationScoped
public class AreaChecker {
    
    public boolean checkHitInArea(HitCheck hitCheck) {
        double x = hitCheck.getX();
        double y = hitCheck.getY();
        double r = hitCheck.getR();
        return checkInCircle(x, y, r) || checkInTriangle(x, y, r) || checkInRectangle(x, y, r);
    }

    public boolean checkInRectangle(double x, double y, double r) {
        return x <= 0 && y >= 0 && Math.abs(x) <= r && y <= r/2;
    }

    public boolean checkInTriangle(double x, double y, double r) {
        return x >= 0 && y >= 0 && (-x + r) / 2 - y >= 0;
    }

    public boolean checkInCircle(double x, double y, double r) {
        return x >= 0 && y <= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2));
    }
}
