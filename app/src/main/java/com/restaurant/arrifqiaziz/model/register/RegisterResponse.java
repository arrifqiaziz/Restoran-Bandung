
package com.restaurant.arrifqiaziz.model.register;


import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class RegisterResponse {

    @Expose
    private String data;
    @Expose
    private String message;
    @Expose
    private String status;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
