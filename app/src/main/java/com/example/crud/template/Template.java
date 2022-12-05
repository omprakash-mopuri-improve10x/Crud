package com.example.crud.template;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Template implements Serializable {

    @SerializedName("_id")
    public String id;
    @SerializedName("messageText")
    public String message;

    public Template() {

    }

    public Template(String message) {
        this.message = message;
    }
}
