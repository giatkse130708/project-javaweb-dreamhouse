/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.daos;

import giatk.dtos.UserDTO;
import giatk.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Kha Gia
 */
public class UserDAO {
    
    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;
    
    private static void closeConnection() throws SQLException {
        if(rs != null)
            rs.close();
        if(ps != null)
            ps.close();
        if(conn != null)
            conn.close();
    }
    
    public static UserDTO checkLogin(String userID, String password) throws SQLException, ClassNotFoundException{
        UserDTO dto = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT UserID, Password, Username, PhoneNumber, Email, IdentifyCard, Deleted, Role FROM tblUsers WHERE UserID=? AND Password=?");
            ps.setString(1, userID);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if(rs.next()){
                dto = new UserDTO();
                dto.setUserID(rs.getString(1));
                dto.setPassword(rs.getString(2));
                dto.setUsername(rs.getString(3));
                dto.setPhoneNumber(rs.getString(4));
                dto.setEmail(rs.getString(5));
                dto.setIdentifyCard(rs.getString(6));
                dto.setDeleted(rs.getBoolean(7));
                dto.setRole(rs.getString(8));
            }
        } finally{
            closeConnection();
        }
        return dto;
    }
    
    public static boolean addUser(UserDTO dto) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("INSERT INTO tblUsers(UserID, Password, Username, PhoneNumber, Email, IdentifyCard, Deleted, Role) VALUES(?,?,?,?,?,?,?,?)");
            ps.setString(1, dto.getUserID());
            ps.setString(2, dto.getPassword());
            ps.setString(3, dto.getUsername());
            ps.setString(4, dto.getPhoneNumber());
            ps.setString(5, dto.getEmail());
            ps.setString(6, dto.getIdentifyCard());
            ps.setBoolean(7, false);
            ps.setString(8, "member");
            
            result = ps.executeUpdate() > 0;
            
        } finally {
            closeConnection();
        }
        return result;
    }
    
}
