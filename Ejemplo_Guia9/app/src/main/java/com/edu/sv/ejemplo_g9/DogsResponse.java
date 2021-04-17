package com.edu.sv.ejemplo_g9;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DogsResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private List<String> images;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<String> getImages() {
        return images;
    }
    public void setImages(List<String> images) {
        this.images = images;
    }
}

