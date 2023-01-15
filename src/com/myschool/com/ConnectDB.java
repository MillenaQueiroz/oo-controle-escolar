package com.myschool.com;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public static void main(String[] args) {

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");
            connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","20projectoo");

            if(connection!=null){
                System.out.println("Connection OK");
            }else{
                System.out.println("Connection Failed");
            }

        }catch(Exception e){
            System.out.printf(String.valueOf(e));
        }
    }
}
