/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.daos;

import giatk.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kha Gia
 */
public class CartDAO implements Serializable{
        static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    private static void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public static int getCartID(String userID) throws SQLException, ClassNotFoundException {
        int cardID = -1;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT ID FROM tblCarts WHERE UserID=?");
            ps.setString(1, userID);
            rs = ps.executeQuery();
            while(rs.next()){
                cardID = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return cardID;
    }
    
    public static boolean insertNewCard(String userID) throws SQLException, ClassNotFoundException{
        boolean result = false;
        ArrayList<Integer> listCardID = new ArrayList<>();
        int cardID = -1;
        try {
            conn = DBUtils.getConnection();
            
            ps = conn.prepareStatement("SELECT ID FROM tblCarts");
            rs = ps.executeQuery();
            while(rs.next()){
                listCardID.add(rs.getInt("ID"));
            }
            if(listCardID.isEmpty()){
                cardID = 1;
            }else if(listCardID.get(0) > 1){
                cardID = listCardID.get(0) - 1;
            } else {
                cardID = listCardID.get(listCardID.size() - 1) + 1;
            }
            ps = conn.prepareStatement("INSERT INTO tblCarts(ID, UserID) VALUES(?,?)");
            ps.setInt(1, cardID);
            ps.setString(2, userID);
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public static boolean putItemInCart(int itemID, int cartID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("INSERT INTO tblItemsInCart(CartID, ItemID) VALUES(?,?)");
            ps.setInt(1, cartID);
            ps.setInt(2, itemID);
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public static void removeItemInCart(int itemID) throws SQLException, ClassNotFoundException {
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("DELETE tblItemsInCart WHERE ItemID=?");
            ps.setInt(1, itemID);
            ps.executeUpdate();
        } finally {
            closeConnection();
        }
    }
    
    public static boolean hasItemInCart(int itemID, int cartID) throws SQLException, ClassNotFoundException { //Kiểm tra đã có item trong cart hay chưa.
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT ItemID FROM tblItemsInCart WHERE ItemID=? AND cartID=?");
            ps.setInt(1, itemID);
            ps.setInt(2, cartID);
            rs = ps.executeQuery();
            if(rs.next()){
                result = true;
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
