package com.novibe.dns.cloudflare.http.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ResultInfo {

    private int count;
    private int page;

    @SerializedName("per_page")
    private int perPage;

    @SerializedName("total_count")
    private int totalCount;

}