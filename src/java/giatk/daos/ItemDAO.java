/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.daos;

import giatk.dtos.ItemDTO;
import giatk.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kha Gia
 */
public class ItemDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    private static final String DEFAULT_LINK_IMAGE = "resources/items/no-image-available.jpg";

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

    public static ArrayList<ItemDTO> getListItem() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT ID, Name, Address, Square, Price, Category, Deleted, isAvailable, Description FROM tblItems WHERE Deleted=?");
            ps.setBoolean(1, false);
            rs = ps.executeQuery();
            while (rs.next()) {
                ItemDTO item = new ItemDTO();
                item.setID(rs.getInt("ID"));
                item.setName(rs.getString("Name"));
                item.setAddress(rs.getString("Address"));
                item.setSquare(rs.getFloat("Square"));
                item.setPrice(rs.getLong("Price"));
                item.setCategory(rs.getString("Category"));
                item.setDeleted(rs.getBoolean("Deleted"));
                item.setAvailable(rs.getBoolean("isAvailable"));
                item.setDescription(rs.getString("Description"));
                item.setListImages(getImageListItem(rs.getInt("ID")));
                if (item.getListImages().isEmpty()) {
                    item.setLinkImage(DEFAULT_LINK_IMAGE);
                } else {
                    item.setLinkImage(item.getListImages().get(0));
                }
                list.add(item);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public static ItemDTO getItem(int ID) throws SQLException, ClassNotFoundException {
        ItemDTO item = null;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT ID, Name, Address, Square, Price, Category, Deleted, isAvailable, Description FROM tblItems WHERE ID=?");
            ps.setInt(1, ID);
            rs = ps.executeQuery();
            if (rs.next()) {
                item = new ItemDTO();
                item.setID(rs.getInt("ID"));
                item.setName(rs.getString("Name"));
                item.setAddress(rs.getString("Address"));
                item.setSquare(rs.getFloat("Square"));
                item.setPrice(rs.getLong("Price"));
                item.setCategory(rs.getString("Category"));
                item.setDeleted(rs.getBoolean("Deleted"));
                item.setAvailable(rs.getBoolean("isAvailable"));
                item.setDescription(rs.getString("Description"));
                item.setListImages(getImageListItem(rs.getInt("ID")));
                if (item.getListImages().isEmpty()) {
                    item.setLinkImage(DEFAULT_LINK_IMAGE);
                } else {
                    item.setLinkImage(item.getListImages().get(0));
                }
            }
        } finally {
            closeConnection();
        }
        return item;
    }

    private static ArrayList<String> getImageListItem(int itemID) throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        Connection connLocal = null;
        PreparedStatement psLocal = null;
        ResultSet rsLocal = null;
        try {

            connLocal = DBUtils.getConnection(); //Sử dụng biến local để tránh xung đột biến với phương thức gọi phương thức này
            psLocal = connLocal.prepareStatement("SELECT Link FROM tblImages WHERE ItemID=?");
            psLocal.setInt(1, itemID);
            rsLocal = psLocal.executeQuery();
            while (rsLocal.next()) {
                list.add(rsLocal.getString("Link"));
            }
        } finally {
            if (rsLocal != null) {
                rsLocal.close();
            }
            if (psLocal != null) {
                psLocal.close();
            }
            if (connLocal != null) {
                connLocal.close();
            }
        }
        return list;
    }

    public static boolean deleteItem(int ID) throws SQLException, ClassNotFoundException {
        boolean result = false;

        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("UPDATE tblItems SET Deleted=? WHERE ID=?");
            ps.setBoolean(1, true);
            ps.setInt(2, ID);
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }

        return result;
    }

    public static boolean updateItem(ItemDTO dto, int ID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("UPDATE tblItems SET Name=?, Address=?, "
                    + "Square=?, Price=?, Category=?, Description=? WHERE ID=?");
            ps.setString(1, dto.getName());
            ps.setString(2, dto.getAddress());
            ps.setFloat(3, dto.getSquare());
            ps.setLong(4, dto.getPrice());
            ps.setString(5, dto.getCategory());
            ps.setString(6, dto.getDescription());
            ps.setInt(7, ID);
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean insertLinkImageItem(String urlImage, int itemID) throws SQLException, ClassNotFoundException {
        boolean result = false;
        int imageID = 0;
        ArrayList<Integer> listImageID = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT ID FROM tblImages");
            rs = ps.executeQuery();
            while (rs.next()) {
                listImageID.add(rs.getInt("ID"));
            }
            if (listImageID.get(0) > 1) {
                imageID = listImageID.get(0) - 1;
            } else {
                imageID = listImageID.get(listImageID.size() - 1) + 1;
            }
            ps = conn.prepareStatement("INSERT INTO tblImages(ID, ItemID, Link) VALUES(?,?,?)");
            ps.setInt(1, imageID);
            ps.setInt(2, itemID);
            ps.setString(3, urlImage);
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public static void deleteLinkImageItem(int itemID) throws SQLException, ClassNotFoundException {
        Connection connL = null;
        PreparedStatement psL = null;
        try {
            connL = DBUtils.getConnection();
            psL = connL.prepareStatement("DELETE tblImages WHERE ItemID=?");
            psL.setInt(1, itemID);
            psL.executeUpdate();
        } finally {

            if (psL != null) {
                psL.close();
            }
            if (connL != null) {
                connL.close();
            }
        }
    }

    public static boolean insertItem(ItemDTO item) throws SQLException, ClassNotFoundException {
        boolean result = false;
        int itemID = 0;
        ArrayList<Integer> lisItemID = new ArrayList<>();
        try {

            conn = DBUtils.getConnection();

            ps = conn.prepareStatement("SELECT ID FROM tblItems");
            rs = ps.executeQuery();
            while (rs.next()) {
                lisItemID.add(rs.getInt("ID"));
            }
            if (lisItemID.get(0) > 1) {
                itemID = lisItemID.get(0) - 1;
            } else {
                itemID = lisItemID.get(lisItemID.size() - 1) + 1;
            }

            ps = conn.prepareStatement("INSERT INTO tblItems(ID, Name, Address, Square,"
                    + " Price, Category, Deleted, isAvailable, Description) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, itemID);
            ps.setString(2, item.getName());
            ps.setString(3, item.getAddress());
            ps.setFloat(4, item.getSquare());
            ps.setLong(5, item.getPrice());
            ps.setString(6, item.getCategory());
            ps.setBoolean(7, false);
            ps.setBoolean(8, false);
            ps.setString(9, item.getDescription());
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public static int insertItemGetID(ItemDTO item) throws SQLException, ClassNotFoundException {
        boolean success = false;
        int itemID = 0;
        ArrayList<Integer> lisItemID = new ArrayList<>();
        try {

            conn = DBUtils.getConnection();

            ps = conn.prepareStatement("SELECT ID FROM tblItems");
            rs = ps.executeQuery();
            while (rs.next()) {
                lisItemID.add(rs.getInt("ID"));
            }
            itemID = lisItemID.get(lisItemID.size() - 1) + 1;

            ps = conn.prepareStatement("INSERT INTO tblItems(ID, Name, Address, Square,"
                    + " Price, Category, Deleted, isAvailable, Description) VALUES(?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, itemID);
            ps.setString(2, item.getName());
            ps.setString(3, item.getAddress());
            ps.setFloat(4, item.getSquare());
            ps.setLong(5, item.getPrice());
            ps.setString(6, item.getCategory());
            ps.setBoolean(7, item.isDeleted());
            ps.setBoolean(8, item.isAvailable());
            ps.setString(9, item.getDescription());
            success = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        if (success) {
            return itemID;
        }
        return -1;
    }

    public static ArrayList<ItemDTO> getListItemInCart(int cardID) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> list = new ArrayList<>();
        ArrayList<Integer> listItemID = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();

            //Get List itemID in tblCards
            ps = conn.prepareStatement("SELECT ItemID FROM tblItemsInCart WHERE CartID=?");
            ps.setInt(1, cardID);
            rs = ps.executeQuery();
            while (rs.next()) {
                listItemID.add(rs.getInt(1));
            }
            //Get item with each itemID and put it in your listItem
            for (int itemID : listItemID) {
                ps = conn.prepareStatement("SELECT ID, Name, Address, Square, Price, Category, Deleted, isAvailable, Description FROM tblItems WHERE ID=?");
                ps.setInt(1, itemID);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ItemDTO item = new ItemDTO();
                    item.setID(rs.getInt("ID"));
                    item.setName(rs.getString("Name"));
                    item.setAddress(rs.getString("Address"));
                    item.setSquare(rs.getFloat("Square"));
                    item.setPrice(rs.getLong("Price"));
                    item.setCategory(rs.getString("Category"));
                    item.setDeleted(rs.getBoolean("Deleted"));
                    item.setAvailable(rs.getBoolean("isAvailable"));
                    item.setDescription(rs.getString("Description"));
                    item.setListImages(getImageListItem(rs.getInt("ID")));
                    if (item.getListImages().isEmpty()) {
                        item.setLinkImage(DEFAULT_LINK_IMAGE);
                    } else {
                        item.setLinkImage(item.getListImages().get(0));
                    }
                    if (!item.isDeleted()) {
                        list.add(item);
                    }
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public static boolean updateIsAvailableItem(int itemID, boolean isAvailable) throws SQLException, ClassNotFoundException {
        boolean result = false;
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("UPDATE tblItems SET isAvailable=? WHERE ID=?");
            ps.setBoolean(1, isAvailable);
            ps.setInt(2, itemID);
            result = ps.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public static boolean isAvailable(int itemID) throws SQLException, ClassNotFoundException {
        boolean result = false; //Nên để 3 giá trị là -1 0 1 để xác định trong trường hợp không lấy được isAvailable thì trả về ko lấy được.
        try {
            conn = DBUtils.getConnection();
            ps = conn.prepareStatement("SELECT isAvailable FROM tblItems WHERE ID=?");
            ps.setInt(1, itemID);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getBoolean(1);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public static ArrayList<ItemDTO> searchItem(String city, long minPrice, long maxPrice, String category) throws SQLException, ClassNotFoundException {
        ArrayList<ItemDTO> list = new ArrayList<>();
        try {
            conn = DBUtils.getConnection();
            String sql = "SELECT ID, Name, Address, Square, Price, "
                    + "Category, Deleted, isAvailable, Description FROM tblItems "
                    + "WHERE";
            if (!city.isEmpty()) {
                sql += " Address LIKE N'%" + city + "%' AND";
            }
            if (minPrice > 0) {
                sql += " Price>=" + minPrice + " AND";
            }
            if (maxPrice > 0) {
                sql += " Price<=" + maxPrice + " AND";
            }
            if (!category.isEmpty() && !category.equals("Tất cả")) {
                sql += " Category LIKE N'%" + category + "%'";
            }
            if (sql.lastIndexOf("AND") == sql.length() - 3) {
                sql = sql.substring(0, sql.lastIndexOf("AND"));
            }
            if (sql.length() == sql.lastIndexOf("WHERE") + 5) {
                sql = sql.substring(0, sql.length() - 6);
            }
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ItemDTO item = new ItemDTO();
                item.setID(rs.getInt("ID"));
                item.setName(rs.getString("Name"));
                item.setAddress(rs.getString("Address"));
                item.setSquare(rs.getFloat("Square"));
                item.setPrice(rs.getLong("Price"));
                item.setCategory(rs.getString("Category"));
                item.setDeleted(rs.getBoolean("Deleted"));
                item.setAvailable(rs.getBoolean("isAvailable"));
                item.setDescription(rs.getString("Description"));
                item.setListImages(getImageListItem(rs.getInt("ID")));
                if (item.getListImages().isEmpty()) {
                    item.setLinkImage(DEFAULT_LINK_IMAGE);
                } else {
                    item.setLinkImage(item.getListImages().get(0));
                }
                if (!item.isDeleted()) {
                    list.add(item);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }
}
