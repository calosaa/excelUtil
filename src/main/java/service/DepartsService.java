package service;

import data.MysqlConnection;

import java.util.ArrayList;

public class DepartsService {
    private MysqlConnection connection;

    public DepartsService(MysqlConnection connection) {
        this.connection = connection;
    }

    public ArrayList<String> getDeps(){
        return this.connection.getDeps();
    }

    public void writeTotal(int month){

    }
}
