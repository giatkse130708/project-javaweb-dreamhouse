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
public class ItemDTO implements Serializable{

    private int ID;
    private String name;
    private String address;
    private float square;
    private long price;
    private String category;
    private boolean Deleted;
    private boolean isAvailable;
    private String description;
    private ArrayList<String> listImages;
    private String linkImage;
    private String shortDescription;

    public ItemDTO(int ID, String name, String address, float square, long price, String category, boolean Deleted, boolean isAvailable, String description, ArrayList<String> listImages) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.square = square;
        this.price = price;
        this.category = category;
        this.Deleted = Deleted;
        this.isAvailable = isAvailable;
        this.description = description;
        this.listImages = listImages;
        this.linkImage = listImages.get(0);
        if(this.description.length() > 200)
            shortDescription = this.description.substring(0, 200) + ".....";
        else
            shortDescription = this.description;
    }

    public ItemDTO() {
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isDeleted() {
        return Deleted;
    }

    public void setDeleted(boolean Deleted) {
        this.Deleted = Deleted;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        if(this.description.length() > 200)
            shortDescription = this.description.substring(0, 200) + ".....";
        else
            shortDescription = this.description;
    }

    public ArrayList<String> getListImages() {
        return listImages;
    }

    public void setListImages(ArrayList<String> listImages) {
        this.listImages = listImages;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

}
