/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kha Gia
 */
public class DBUtils {
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=ProjectPRJ321", "sa", "123");
    }
}