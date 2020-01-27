package com.example.application1.model;

public class CartItem {
    int p_id;
    int sr_no;
    String lc_product_name;
    String description;
    String image;
    float Total;
    String category_id;
    int quantity;

    public CartItem() {
    }

    public CartItem(int p_id, int sr_no, String lc_product_name, String description, String image, float total, String category_id, int quantity) {
        this.p_id = p_id;
        this.sr_no = sr_no;
        this.lc_product_name = lc_product_name;
        this.description = description;
        this.image = image;
        Total = total;
        this.category_id = category_id;
        this.quantity = quantity;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getSr_no() {
        return sr_no;
    }

    public void setSr_no(int sr_no) {
        this.sr_no = sr_no;
    }

    public String getLc_product_name() {
        return lc_product_name;
    }

    public void setLc_product_name(String lc_product_name) {
        this.lc_product_name = lc_product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getTotal() {
        return Total;
    }

    public void setTotal(float total) {
        Total = total;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
