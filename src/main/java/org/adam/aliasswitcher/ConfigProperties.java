package org.adam.aliasswitcher;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConfigProperties {

    @Value("${auth.apikey}")
    private String apiKey;

    @Value("${auth.apisecret}")
    private String apiSecret;

    @Value("${auth.host}")
    private String host;

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public String getHost() {
        return host;
    }
}
