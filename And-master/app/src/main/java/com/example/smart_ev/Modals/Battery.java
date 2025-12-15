package com.example.smart_ev.Modals;

public class Battery {
    private int id, user_id;
    private String status;

    public Battery(int id, int user_id, String status) {
        this.id = id;
        this.user_id = user_id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getStatus() {
        return status;
    }
}
