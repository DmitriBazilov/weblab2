package com.dmitri.ifmo_web_lab_2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class Table implements Serializable {
    private List<HitCheck> hits = new ArrayList<>();

    public Table() {}

    public List<HitCheck> getHits() {
        return hits;
    }

    public void setHits(List<HitCheck> hits) {
        this.hits = hits;
    }
}
