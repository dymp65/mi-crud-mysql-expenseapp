package com.praktisi.expenseappmysql.Models;

public class Expense {
    private int id;
    private int user_id;
    private int type_id;
    private String date;
    private Double amount;

    private String title;

    private String desc;

    public Expense() {}

    public Expense(int id, int user_id, int type_id, String date, Double amount, String title, String desc) {
        this.id = id;
        this.user_id = user_id;
        this.type_id = type_id;
        this.date = date;
        this.amount = amount;
        this.title = title;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
