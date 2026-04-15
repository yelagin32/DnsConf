package com.novibe.dns.next_dns.http.dto.request;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public final class CreateRewriteDto {

    private final String name;

    @EqualsAndHashCode.Exclude
    private final String content;

}
