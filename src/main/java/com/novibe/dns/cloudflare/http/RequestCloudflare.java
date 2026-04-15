package com.novibe.dns.cloudflare.http;

import com.novibe.common.HttpRequestSender;
import com.novibe.common.util.Log;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.novibe.common.config.EnvironmentVariables.AUTH_SECRET;
import static com.novibe.common.config.EnvironmentVariables.CLIENT_ID;

@Service
@RequiredArgsConstructor
public class RequestCloudflare extends HttpRequestSender {

    @Override
    protected String apiUrl() {
        return "https://api.cloudflare.com/client/v4/accounts/%s/gateway".formatted(CLIENT_ID);
    }

    @Override
    protected String authHeaderName() {
        return "Authorization";
    }

    @Override
    protected String authHeaderValue() {
        return "Bearer " + AUTH_SECRET;
    }

    @Override
    protected final void react401() {
        Log.fail("Invalid API Token!");
    }

    @Override
    protected void react403() {
        Log.fail("""
                Token doesn't have necessary permissions!
                Generate a token with permissions:
                1) Zero Trust:Edit
                2) Account Firewall Access Rules:Edit""");
    }
}
