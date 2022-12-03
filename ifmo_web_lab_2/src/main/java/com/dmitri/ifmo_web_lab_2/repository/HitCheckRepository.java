package com.dmitri.ifmo_web_lab_2.repository;

import com.dmitri.ifmo_web_lab_2.dto.HitCheckDTO;

public interface HitCheckRepository {
    
    public void add(HitCheckDTO hit);
    public long remove(HitCheckDTO hit);
    
}
