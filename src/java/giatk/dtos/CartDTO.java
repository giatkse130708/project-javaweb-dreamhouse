/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Kha Gia
 */
public class CartDTO implements Serializable{
    private int ID;
    private String userID;
    private ArrayList<Integer> itemList;

    public CartDTO(int ID, String userID, ArrayList<Integer> itemList) {
        this.ID = ID;
        this.userID = userID;
        this.itemList = itemList;
    }

    public CartDTO() {
    }
    
    public CartDTO(int ID, String userID) {
        this.ID = ID;
        this.userID = userID;
    }

    public ArrayList<Integer> getItemList() {
        return itemList;
    }

    public void setItemList(ArrayList<Integer> itemList) {
        this.itemList = itemList;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}
