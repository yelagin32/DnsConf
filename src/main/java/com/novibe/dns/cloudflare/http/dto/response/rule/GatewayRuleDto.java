package com.novibe.dns.cloudflare.http.dto.response.rule;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GatewayRuleDto {
    String id;
    String name;
    String description;
    @SerializedName("created_at")
    String createdAt;
    String traffic;
    boolean enabled;
}
