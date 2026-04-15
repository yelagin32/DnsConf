package com.novibe.dns.cloudflare.http.dto.response.list;

import com.google.gson.annotations.SerializedName;
import com.novibe.dns.cloudflare.http.dto.Item;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class GatewayListDto {

    private UUID id;
    private int count;

    @SerializedName("created_at")
    private String createdAt;

    private String description;
    private List<Item> items;
    private String name;
    private String type;

    @SerializedName("updated_at")
    private String updatedAt;
}
