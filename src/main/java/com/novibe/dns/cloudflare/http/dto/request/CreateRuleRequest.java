package com.novibe.dns.cloudflare.http.dto.request;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;

import java.util.List;

@Builder
public record CreateRuleRequest(
        String name,
        String description,
        String action,
        List<String> filters,
        String traffic,
        @SerializedName("rule_settings")
        RuleSettings ruleSettings,
        boolean enabled) {

    public record RuleSettings(
            @SerializedName("override_ips")
            List<String> overrideIps) {
    }
}


