package com.dmitri.ifmo_web_lab_2.repository;

import com.dmitri.ifmo_web_lab_2.database.DatabaseConnector;
import com.dmitri.ifmo_web_lab_2.dto.HitCheckDTO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;

@ManagedBean
@ApplicationScoped
public class HitCheckRepositoryImpl implements HitCheckRepository, Serializable {
    
    @ManagedProperty(value = "#{databaseConnector}", name = "databaseConnector")
    private DatabaseConnector databaseConnector;

    public DatabaseConnector getDatabaseConnector() {
        return databaseConnector;
    }

    public void setDatabaseConnector(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public HitCheckRepositoryImpl() {}

    @PostConstruct
    public void initTable() {
        try (Connection connection = databaseConnector.getConnection()) {
            System.out.println(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void add(HitCheckDTO hit) {
        try (Connection connection = databaseConnector.getConnection()) {
            System.out.println(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public long remove(HitCheckDTO hit) {
        return 0;
    }
}
