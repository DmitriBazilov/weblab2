package com.dmitri.ifmo_web_lab_2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@RequestScoped
public class HitCheck implements Serializable {

    @ManagedProperty(value = "#{table}", name = "table")
    private Table table;

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

    public void save() {
        System.out.println(table);
        table.getHits().add(this);
    }
}
