package com.novibe.dns.next_dns.http;

import com.novibe.common.HttpRequestSender;
import com.novibe.common.util.Log;

import static com.novibe.common.config.EnvironmentVariables.AUTH_SECRET;
import static com.novibe.common.config.EnvironmentVariables.CLIENT_ID;

public abstract class AbstractNextDnsHttpClient extends HttpRequestSender {

    protected abstract String path();

    @Override
    protected String apiUrl() {
        return "https://api.nextdns.io/profiles/%s".formatted(CLIENT_ID);
    }

    @Override
    protected String authHeaderName() {
        return "X-Api-Key";
    }

    @Override
    protected String authHeaderValue() {
        return AUTH_SECRET;
    }

    @Override
    protected final void react401() {
        Log.fail("Invalid api key!");
    }

    @Override
    protected void react403() {
        Log.fail("Invalid api key!");
    }
}
