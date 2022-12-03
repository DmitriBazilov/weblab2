package com.dmitri.ifmo_web_lab_2.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

public class HitCheckDTO implements Serializable {
    private Long id;
    private Double x;
    private Double y;
    private Double r;
    private ZonedDateTime hitDate;
    private Long executeTime;
    private Boolean result;
    private String sessionId;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getR() {
        return r;
    }

    public void setR(Double r) {
        this.r = r;
    }

    public ZonedDateTime getHitDate() {
        return hitDate;
    }

    public void setHitDate(ZonedDateTime hitDate) {
        this.hitDate = hitDate;
    }

    public Long getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Long executeTime) {
        this.executeTime = executeTime;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HitCheckDTO that = (HitCheckDTO) o;
        return id.equals(that.id) && Objects.equals(x, that.x) && Objects.equals(y, that.y) && Objects.equals(r, that.r) && Objects.equals(hitDate, that.hitDate) && Objects.equals(executeTime, that.executeTime) && Objects.equals(result, that.result);
    }

    @Override
    public String toString() {
        return "HitCheckDTO{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", hitDate=" + hitDate +
                ", executeTime=" + executeTime +
                ", result=" + result +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, r, hitDate, executeTime, result);
    }
}
