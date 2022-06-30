package com.roshka.bootcamp.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    public static Connection getConnection(){
        Connection con=null;
        try{
            Class.forName("org.postgresql.Driver");
            con= DriverManager
                    .getConnection("jdbc:postgresql://localhost:5432/bootcamp_market",
                            "postgres", "postgres");

            if(con != null){
                System.out.println("---> CONNECTED TO SERVER");
            }else {
                System.out.println("---> UNABLE TO CONNECTED TO SERVER");
            }
        }catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }
        return con;
    }
}