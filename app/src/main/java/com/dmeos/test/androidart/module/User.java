package com.dmeos.test.androidart.module;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable{

    @SerializedName("user_id")
    public String userId;

    @SerializedName("user_name")
    public String userName;

    @SerializedName("user_phone")
    public String userPhone;

    @SerializedName("user_email")
    public String userEmail;

    @SerializedName("user_avatar")
    public String userAvatar;
}
