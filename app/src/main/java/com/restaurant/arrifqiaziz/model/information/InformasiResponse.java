
package com.restaurant.arrifqiaziz.model.information;

import java.util.List;
import com.google.gson.annotations.Expose;

@SuppressWarnings("unused")
public class InformasiResponse {

    @Expose
    private List<Informasi> data;

    public List<Informasi> getData() {
        return data;
    }

    public void setData(List<Informasi> data) {
        this.data = data;
    }

}
