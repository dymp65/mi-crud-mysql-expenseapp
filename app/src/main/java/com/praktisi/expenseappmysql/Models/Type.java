package com.praktisi.expenseappmysql.Models;

public class Type {
    private int id;
    private String type;
    private String desc;

    public Type() {}

    public Type(int id, String type, String desc) {
        this.id = id;
        this.type = type;
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
