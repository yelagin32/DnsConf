package com.novibe.dns.cloudflare.http.dto;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

    private String value;
    private String description;

    @SerializedName("created_at")
    private String createdAt;

    public Item(String value) {
        this.value = value;
    }

}
