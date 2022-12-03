package com.dmitri.ifmo_web_lab_2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import com.dmitri.ifmo_web_lab_2.dto.HitCheckDTO;
import com.dmitri.ifmo_web_lab_2.util.SessionGetter;
import com.dmitri.ifmo_web_lab_2.repository.*;
import java.time.ZonedDateTime;
import java.util.List;

@ManagedBean
@RequestScoped
public class HitCheck implements Serializable {
    
    @ManagedProperty(value = "#{hitCheckRepositoryImpl}", name = "hitCheckRepository")
    private HitCheckRepository hitCheckRepository;

    @ManagedProperty(value = "#{table}", name = "table")
    private Table table;

    @ManagedProperty(value = "#{sessionGetter}", name = "sessionGetter")
    private SessionGetter sessionGetter;

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

    public HitCheckRepository getHitCheckRepository() {
        return hitCheckRepository;
    }

    public void setHitCheckRepository(HitCheckRepository hitCheckRepository) {
        this.hitCheckRepository = hitCheckRepository;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public SessionGetter getSessionGetter() {
        return sessionGetter;
    }

    public void setSessionGetter(SessionGetter sessionGetter) {
        this.sessionGetter = sessionGetter;
    }

    public void save() {
        HitCheckDTO dto = new HitCheckDTO();
        dto.setX(x);
        dto.setY(y);
        dto.setR(r);
        dto.setHitDate(ZonedDateTime.now());
        dto.setExecuteTime(2L);
        dto.setResult(false);
        System.out.println(dto);
        List<HitCheck> hits = table.getHitsBySessionId(sessionGetter.getSessionId());
        hits.add(this);
        table.getHits().put(sessionGetter.getSessionId(), hits);
        hitCheckRepository.add(dto);
    }
}
