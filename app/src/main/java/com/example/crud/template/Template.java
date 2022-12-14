package com.example.crud.template;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Template implements Serializable {

    @SerializedName("_id")
    public String id;
    @SerializedName("messageText")
    public String messageTxt;

    public Template() {
    }

    public Template(String messageTxt) {
        this.messageTxt = messageTxt;
    }
}
