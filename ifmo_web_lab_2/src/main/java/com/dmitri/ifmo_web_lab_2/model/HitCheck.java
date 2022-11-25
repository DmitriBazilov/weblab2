package com.dmitri.ifmo_web_lab_2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;
import com.dmitri.ifmo_web_lab_2.dao.HitCheckDao;
import com.dmitri.ifmo_web_lab_2.dto.HitCheckDTO;
import java.time.ZonedDateTime;

@ManagedBean
@RequestScoped
public class HitCheck implements Serializable {

    @ManagedProperty(value = "#{table}", name = "table")
    private Table table;

    @ManagedProperty(value = "#{hitCheckDao}", name = "hitCheckDao")
    private HitCheckDao hitCheckDao;

    private Double x;
    private Double y;
    private Double r;

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

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public HitCheckDao getHitCheckDao() {
        return hitCheckDao;
    }

    public void setHitCheckDao(HitCheckDao hitCheckDao) {
        this.hitCheckDao = hitCheckDao;
    }

    public void save() {
        HitCheckDTO dto = new HitCheckDTO();
        dto.setX(x);
        dto.setY(y);
        dto.setR(r);
        dto.setHitDate(ZonedDateTime.now());
        dto.setExecuteTime(2L);
        dto.setResult(false);
        try {
            hitCheckDao.createHitCheck(dto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(dto);
        table.getHits().add(this);
    }
}
