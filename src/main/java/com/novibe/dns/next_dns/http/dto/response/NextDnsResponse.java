package com.novibe.dns.next_dns.http.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class NextDnsResponse<T> {

    private T data;
    private List<NextDnsApiError> errors;

    public record NextDnsApiError(String code) {
    }
}
