package com.novibe.dns.cloudflare.http.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CloudflareApiMessage {

    private int code;
    private String message;

    @SerializedName("documentation_url")
    private String documentationUrl;

    private Source source;


}
