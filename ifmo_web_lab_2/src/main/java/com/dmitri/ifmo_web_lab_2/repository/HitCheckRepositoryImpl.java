package com.dmitri.ifmo_web_lab_2.repository;

import com.dmitri.ifmo_web_lab_2.database.DatabaseConnector;
import com.dmitri.ifmo_web_lab_2.dto.HitCheckDTO;
import com.dmitri.ifmo_web_lab_2.database.DatabaseQueries;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;

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
        String tableQuery = DatabaseQueries.CREATE_HITS_TABLE.getQuery();
        String idQuery = DatabaseQueries.CREATE_SEQUENCE.getQuery();
        try (
                Connection connection = databaseConnector.getConnection();
                PreparedStatement tableCreate = connection.prepareStatement(tableQuery);
                PreparedStatement idCreate = connection.prepareStatement(idQuery);
            ) {
            idCreate.execute();
            tableCreate.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void add(HitCheckDTO hit) {
        String addQuery = DatabaseQueries.ADD_HIT.getQuery();
        try (
                Connection connection = databaseConnector.getConnection();
                PreparedStatement hitAdd = connection.prepareStatement(addQuery);
            ) {
            long id = generateId();
            hit.setId(id);
            int idx = fillHitQuery(hitAdd, hit);
            hitAdd.executeUpdate();
            System.out.println(id);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeHitsBySessionId(String sessionId) {
        String removeQuery = DatabaseQueries.DELETE_HITS_BY_SESSION_ID.getQuery();
        try (
                Connection connection = databaseConnector.getConnection();
                PreparedStatement removeHits = connection.prepareStatement(removeQuery);
            ) {
            removeHits.setString(1, sessionId);
            removeHits.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } 
    }

    public long remove(HitCheckDTO hit) {
        return 0;
    }

    private int generateId() {
        try (Connection connection = databaseConnector.getConnection();
             Statement statement = connection.createStatement()
        ) {
            ResultSet rs = statement.executeQuery(DatabaseQueries.GENERATE_NEXT_ID.getQuery());
            if (rs.next()) {
                return rs.getInt("nextval");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            return -1;
        }
    }

    public int fillHitQuery(PreparedStatement state, HitCheckDTO hit) throws SQLException {
        int idx = 1;
        state.setLong(idx++, hit.getId());
        state.setDouble(idx++, hit.getX());
        state.setDouble(idx++, hit.getY());
        state.setDouble(idx++, hit.getR());
        state.setTimestamp(idx++, Timestamp.valueOf(hit.getHitDate().toLocalDateTime()));
        state.setLong(idx++, hit.getExecuteTime());
        state.setBoolean(idx++, hit.getResult());
        state.setString(idx++, hit.getSessionId());
        return idx;
    }
}
