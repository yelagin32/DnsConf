package com.novibe.dns.next_dns.http.dto.request;

import lombok.Getter;

@Getter
public final class CreateDenyDto {

    private final String id;
    private final boolean active = true;

    public CreateDenyDto(String id) {
        this.id = id;
    }

}
