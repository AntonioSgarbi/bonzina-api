package com.antoniosgarbi.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("personal.security")
public class SecurityEnvironment {

    private final long TOKEN_DURATION = 36_000_000;
    private final String HEADER_ATTRIBUTE = "Authorization";
    private final String ATTRIBUTE_PREFIX = "Bearer ";

    private String tokenkey;

    public long getTOKEN_DURATION() {
        return TOKEN_DURATION;
    }

    public String getHEADER_ATTRIBUTE() {
        return HEADER_ATTRIBUTE;
    }

    public String getATTRIBUTE_PREFIX() {
        return ATTRIBUTE_PREFIX;
    }

    public String getTokenkey() {
        return tokenkey;
    }

    public void setTokenkey(String tokenkey) {
        this.tokenkey = tokenkey;
    }
}
