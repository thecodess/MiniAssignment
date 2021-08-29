package com.apps.foodorderapp;

public class Ingredient {
    int id;
    String title, tag, image, quantity, created_at, alerted_at, expired_at;

public  Ingredient(){

}
    public Ingredient(int id, String title, String tag, String image,  String quantity, String created_at, String alerted_at, String expired_at) {
        this.id = id;
        this.title = title;
        this.tag = tag;
        this.image = image;

        this.quantity = quantity;
        this.created_at = created_at;
        this.alerted_at = alerted_at;
        this.expired_at = expired_at;

    }
}
