
package com.restaurant.arrifqiaziz.model.login;


import com.google.gson.annotations.Expose;

import java.io.Serializable;


@SuppressWarnings("unused")
public class User implements Serializable {

    @Expose
    private String nama;
    @Expose
    private String userid;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

}
