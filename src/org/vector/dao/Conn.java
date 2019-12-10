/**
 * Company
 * Copyright (C) 2004-2019 All Rights Reserved.
 */
package org.vector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn {
    static Connection conn;
    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false","root","123456");
        }catch(SQLException e){
            e.printStackTrace();
        }
        return conn;
    }
}
