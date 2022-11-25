package com.dmitri.ifmo_web_lab_2.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.dmitri.ifmo_web_lab_2.dto.HitCheckDTO;
import java.sql.SQLException;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class HitCheckDao {
    private EntityTransaction entityTransaction;
    private EntityManager entityManager;

    protected EntityManager getEntityManager() {
        if (this.entityManager == null) {
            this.entityManager = Persistence.createEntityManagerFactory("postgres").createEntityManager();
        }
        return entityManager;
    }

    private EntityTransaction getEntityTransaction() {
        entityTransaction = getEntityManager().getTransaction();
        return entityTransaction;
    }

    public HitCheckDTO createHitCheck(HitCheckDTO dto) throws SQLException {
        if (!this.getEntityTransaction().isActive()) {
            getEntityTransaction().begin();
        }
        try {
            getEntityManager().persist(dto);
            getEntityManager().flush();
            getEntityTransaction().commit();
            return dto;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            if (!this.getEntityTransaction().isActive()) {
                getEntityTransaction().begin();
            }
            getEntityTransaction().rollback();
            throw new SQLException(e.getMessage());
        } 
    }
}
