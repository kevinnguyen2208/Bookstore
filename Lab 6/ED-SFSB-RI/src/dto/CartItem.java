/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.io.Serializable;

/**
 *
 * @author kevinnguyen2208
 */
public class CartItem implements Serializable {

    private String itemId;
    private String description;
    private double unitPrice;
    private int quantity;

    public String getItemId() {
        return itemId;
    }

    public String getDescription() {
        return description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    

    public CartItem(String id, String desc, double price, int qty) {
        itemId = id;
        description = desc;
        unitPrice = price;
        quantity = qty;
    }
}
