package com.dmitri.ifmo_web_lab_2.database;

public enum DatabaseQueries {

    CREATE_SEQUENCE("CREATE SEQUENCE IF NOT EXISTS ids START 1"),

    GENERATE_NEXT_ID("SELECT nextval('ids')"),

    CREATE_HITS_TABLE("CREATE TABLE IF NOT EXISTS s335102hits\n" 
        + "(\n" 
        + "id bigint primary key default nextval('ids') not null, \n"
        + "x double precision, \n"
        + "y double precision, \n"
        + "r double precision, \n"
        + "hit_date timestamp, \n"
        + "execute_time bigint, \n"
        + "result boolean, \n" 
        + "session_id varchar(255), \n"
        + "); \n"
    ),

    CLEAR_HITS_BY_SESSIONID("delete from s335102hits where session_id = ?"),

    ADD_HIT("insert into s335102hits" 
        + "(id, x, y, r, hit_date, execute_time, result, session_id "
        + "values(?, ?, ?, ?, ?, ?, ?, ?)"
    );

    private final String query;

    DatabaseQueries(String query) {
        this.query = query;
    }
        
    public String getQuery() {
        return query;
    }  
}   
