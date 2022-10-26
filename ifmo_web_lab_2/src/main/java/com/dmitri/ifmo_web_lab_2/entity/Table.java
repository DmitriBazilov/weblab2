package com.dmitri.ifmo_web_lab_2.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Table implements Serializable {
    private ArrayList<TableRow> tableRows;

    public Table() {
        tableRows = new ArrayList<>();
    }

    public ArrayList<TableRow> getTableRows() {
        return tableRows;
    }
}
