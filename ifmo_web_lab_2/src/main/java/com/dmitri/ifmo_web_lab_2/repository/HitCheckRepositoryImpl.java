package com.dmitri.ifmo_web_lab_2.repository;

import com.dmitri.ifmo_web_lab_2.model.Table;
import com.dmitri.ifmo_web_lab_2.model.HitCheck;
import com.dmitri.ifmo_web_lab_2.dto.HitCheckDTO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;

@ManagedBean
@ApplicationScoped
public class HitCheckRepositoryImpl implements HitCheckRepository, Serializable {

    public HitCheckRepositoryImpl() {}
    
    public void add(HitCheckDTO hit) {
        System.out.println(123);
    }

    public long remove(HitCheckDTO hit) {
        return 0;
    }
}
