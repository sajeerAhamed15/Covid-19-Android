package com.example.acmcovidapplication.db;

public class AppDataModel {
    int id;
    boolean isAllowed;
    String  my_user_id;



    public void setId(int id) {
        this.id = id;
    }

    public void setAllowed(boolean allowed) {
        isAllowed = allowed;
    }

    public void setMy_user_id(String my_user_id) {
        this.my_user_id = my_user_id;
    }

    public int getId() {
        return id;
    }

    public boolean isAllowed() {
        return isAllowed;
    }

    public String getMy_user_id() {
        return my_user_id;
    }
}
