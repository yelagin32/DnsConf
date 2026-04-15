package com.novibe.common.exception;

import lombok.Getter;

@Getter
public class NextDnsHttpError extends RuntimeException {

    private final int code;
    private final String reason;

    public NextDnsHttpError(int code, String reason) {
        super("NextDNS HttpError code " + code + ", reason: " + reason);
        this.code = code;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "NextDNS HttpError code " + code + ", reason: " + reason;
    }

}
