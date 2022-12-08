package com.dmitri.ifmo_web_lab_2.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@ManagedBean
@ApplicationScoped
public class Table implements Serializable {
    Map<String, List<HitCheck>> hits = new HashMap<>();

    public Table() {}

    public Map<String, List<HitCheck>> getHits() {
        return hits;
    }

    public void setHits(Map<String, List<HitCheck>> hits) {
        this.hits = hits;
    }

    public List<HitCheck> getHitsBySessionId(String sessionId) {
        return hits.get(sessionId) == null ? new ArrayList<HitCheck>() : hits.get(sessionId);
    }

    public void removeSessionDots(String sessionId) {
        List<HitCheck> sessionHits = hits.get(sessionId);
        if (sessionHits == null) {
            sessionHits = new ArrayList<HitCheck>();
        } else {
            sessionHits.clear();
        }
    }
}
