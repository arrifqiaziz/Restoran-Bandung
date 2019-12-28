
package com.restaurant.arrifqiaziz.model.restaurant;

import java.util.List;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class RestaurantResponse {

    @Expose
    private List<Restaurant> data;
    @Expose
    private String status;

    public List<Restaurant> getData() {
        return data;
    }

    public void setData(List<Restaurant> data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
