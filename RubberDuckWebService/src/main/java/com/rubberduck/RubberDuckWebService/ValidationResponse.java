package com.rubberduck.RubberDuckWebService;

public class ValidationResponse {

    private String accessToken;

    private Long userId;

    public ValidationResponse() {
    }

    public ValidationResponse(String accessToken, Long userId) {
        this.accessToken = accessToken;
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
