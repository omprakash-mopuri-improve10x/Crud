package com.example.crud.message;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Message implements Serializable {

    @SerializedName("_id")
    public String id;
    public String name;
    public String phoneNumber;
    @SerializedName("messageText")
    public String message;

    public Message() {

    }

    public Message(String name, String phoneNumber, String message) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.message = message;
    }
}
