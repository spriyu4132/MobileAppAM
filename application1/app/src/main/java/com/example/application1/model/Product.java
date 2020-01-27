package com.example.application1.model;

import java.io.Serializable;

public class Product implements Serializable {
    int p_id;
    String lc_product_code;
    String category_id;
    float weight_per_garm;
    int available_quantity;
    String description;
    String lc_product_name;
    float lc_charge;
    float gst;
    String type_of_making;
    float purity;
    float Total;
    String image;
    String rating;
    double daily_gold_rate_per_gram;

    public Product() {
    }

    public Product(int p_id, String lc_product_code, String category_id, float weight_per_garm, int available_quantity, String description, String lc_product_name, float lc_charge, float gst, String type_of_making, float purity, float total, String image) {
        this.p_id = p_id;
        this.lc_product_code = lc_product_code;
        this.category_id = category_id;
        this.weight_per_garm = weight_per_garm;
        this.available_quantity = available_quantity;
        this.description = description;
        this.lc_product_name = lc_product_name;
        this.lc_charge = lc_charge;
        this.gst = gst;
        this.type_of_making = type_of_making;
        this.purity = purity;
        Total = total;
        this.image = image;
        //this.rating = rating;
       // this.daily_gold_rate_per_gram = daily_gold_rate_per_gram;

    }

    public Product(String lc_product_code, String category_id, float weight_per_garm, int available_quantity, String description, String image, String rating) {
        this.lc_product_code = lc_product_code;
        this.category_id = category_id;
        this.weight_per_garm = weight_per_garm;
        this.available_quantity = available_quantity;
        this.description = description;
        this.image = image;
        this.rating = rating;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getLc_product_code() {
        return lc_product_code;
    }

    public void setLc_product_code(String lc_product_code) {
        this.lc_product_code = lc_product_code;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public float getWeight_per_garm() {
        return weight_per_garm;
    }

    public void setWeight_per_garm(float weight_per_garm) {
        this.weight_per_garm = weight_per_garm;
    }

    public int getAvailable_quantity() {
        return available_quantity;
    }

    public void setAvailable_quantity(int available_quantity) {
        this.available_quantity = available_quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLc_product_name() {
        return lc_product_name;
    }

    public void setLc_product_name(String lc_product_name) {
        this.lc_product_name = lc_product_name;
    }

    public float getLc_charge() {
        return lc_charge;
    }

    public void setLc_charge(float lc_charge) {
        this.lc_charge = lc_charge;
    }

    public float getGst() {
        return gst;
    }

    public void setGst(float gst) {
        this.gst = gst;
    }

    public String getType_of_making() {
        return type_of_making;
    }

    public void setType_of_making(String type_of_making) {
        this.type_of_making = type_of_making;
    }

    public float getPurity() {
        return purity;
    }

    public void setPurity(float purity) {
        this.purity = purity;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getDaily_gold_rate_per_gram() {
        return daily_gold_rate_per_gram;
    }

    public void setDaily_gold_rate_per_gram(double daily_gold_rate_per_gram) {
        this.daily_gold_rate_per_gram = daily_gold_rate_per_gram;
    }
}
