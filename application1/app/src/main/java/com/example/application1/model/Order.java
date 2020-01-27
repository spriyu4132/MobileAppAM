package com.example.application1.model;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable
{
    int order_code;
    int p_id;
    int user_id;
    String order_date;
    String deliverd_date;

    //int p_id;
    String lc_product_code;
    //String category_id;
    float weight_per_garm;
    //int available_quantity;
    //String description;
    String lc_product_name;
    float lc_charge;
    //float gst;
    //String type_of_making;
    float purity;
    float Total;
    String image;
    double daily_gold_rate_per_gram;

    public Order() {
    }

    public Order(int order_code,int p_id, int user_id, String order_date, String deliverd_date) {
        this.order_code=order_code;
        this.p_id = p_id;
        this.user_id = user_id;
        this.order_date = order_date;
        this.deliverd_date = deliverd_date;
    }

    public Order(int order_code, String order_date, String deliverd_date) {
        this.order_code = order_code;
        this.order_date = order_date;
        this.deliverd_date = deliverd_date;
    }

    public Order(int order_code, int p_id, String order_date, String deliverd_date, String lc_product_code, float weight_per_garm, String lc_product_name, float lc_charge, float purity, float total, String image) {
        this.order_code = order_code;
        this.p_id = p_id;
        //this.user_id = user_id;
        this.order_date = order_date;
        this.deliverd_date = deliverd_date;
        this.lc_product_code = lc_product_code;
        this.weight_per_garm = weight_per_garm;
        this.lc_product_name = lc_product_name;
        this.lc_charge = lc_charge;
        this.purity = purity;
        Total = total;
        this.image = image;
        //this.daily_gold_rate_per_gram = daily_gold_rate_per_gram;
    }

    public Order(int order_code, String order_date, String deliverd_date, String lc_product_name, float total, String image) {
        this.order_code = order_code;
        this.order_date = order_date;
        this.deliverd_date = deliverd_date;
        this.lc_product_name = lc_product_name;
        Total = total;
        this.image = image;
    }

    public String getLc_product_code() {
        return lc_product_code;
    }

    public void setLc_product_code(String lc_product_code) {
        this.lc_product_code = lc_product_code;
    }

    public float getWeight_per_garm() {
        return weight_per_garm;
    }

    public void setWeight_per_garm(float weight_per_garm) {
        this.weight_per_garm = weight_per_garm;
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

    public double getDaily_gold_rate_per_gram() {
        return daily_gold_rate_per_gram;
    }

    public void setDaily_gold_rate_per_gram(double daily_gold_rate_per_gram) {
        this.daily_gold_rate_per_gram = daily_gold_rate_per_gram;
    }

    public int getOrder_code() {
        return order_code;
    }

    public void setOrder_code(int order_code) {
        this.order_code = order_code;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getDeliverd_date() {
        return deliverd_date;
    }

    public void setDeliverd_date(String deliverd_date) {
        this.deliverd_date = deliverd_date;
    }
}
