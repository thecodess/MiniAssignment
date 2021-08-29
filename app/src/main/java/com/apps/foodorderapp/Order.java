package com.apps.foodorderapp;

import com.apps.foodorderapp.AddOn;

import java.util.List;

public class Order {
    int id;
    String title, tag, quantity, created_at, alerted_at, expired_at;
  List<AddOn> addOnList;
AddOn addOn;
    public Order() {

    }

    public Order(int id, String title, String tag, String quantity, String created_at, String alerted_at, String expired_at, List<AddOn> addOnList, AddOn addOn) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.quantity = quantity;
        this.created_at = created_at;
        this.alerted_at = alerted_at;
        this.expired_at = expired_at;
        this.addOnList = addOnList;
        this.addOn = addOn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getAlerted_at() {
        return alerted_at;
    }

    public void setAlerted_at(String alerted_at) {
        this.alerted_at = alerted_at;
    }

    public String getExpired_at() {
        return expired_at;
    }

    public void setExpired_at(String expired_at) {
        this.expired_at = expired_at;
    }

    public List<AddOn> getAddOnList() {
        return addOnList;
    }

    public void setAddOnList(List<AddOn> addOnList) {
        this.addOnList = addOnList;
    }

    public AddOn getAddOn() {
        return addOn;
    }

    public void setAddOn(AddOn addOn) {
        this.addOn = addOn;
    }
}
