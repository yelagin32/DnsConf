package com.novibe.dns.next_dns.http.dto.response.deny;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class DenyDto {

    private final String id;
    private final boolean active;

}
