/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package giatk.dtos;

import java.io.Serializable;

/**
 *
 * @author Kha Gia
 */
public class UserDTO implements Serializable{
    private String userID;
    private String password;
    private String username;
    private String phoneNumber;
    private String email;
    private String identifyCard;
    private boolean deleted;
    private String role;

    public UserDTO(String userID, String password, String username, String phoneNumber, String email, String identifyCard, boolean deleted, String role) {
        this.userID = userID;
        this.password = password;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.identifyCard = identifyCard;
        this.deleted = deleted;
        this.role = role;
    }

    public UserDTO() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdentifyCard() {
        return identifyCard;
    }

    public void setIdentifyCard(String identifyCard) {
        this.identifyCard = identifyCard;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}
