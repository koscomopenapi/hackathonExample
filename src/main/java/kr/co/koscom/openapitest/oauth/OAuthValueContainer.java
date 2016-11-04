/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.co.koscom.openapitest.oauth;

/**
 *
 * @author heungjae
 */
public class OAuthValueContainer {
    
    String sessionId;
    String accessToken;
    String refreshToken;
    String scope;
    long accessTokenLifeTime;

    public OAuthValueContainer(String sessionId, String accessToken, String refreshToken, String scope, long accessTokenLifeTime) {
        this.sessionId = sessionId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.scope = scope;
        this.accessTokenLifeTime = accessTokenLifeTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public long getAccessTokenLifeTime() {
        return accessTokenLifeTime;
    }

    public void setAccessTokenLifeTime(long accessTokenLifeTime) {
        this.accessTokenLifeTime = accessTokenLifeTime;
    }

    @Override
    public String toString() {
        return "OAuthValueContainer{" + "sessionId=" + sessionId + ", accessToken=" + accessToken + ", refreshToken=" + refreshToken + ", scope=" + scope + ", accessTokenLifeTime=" + accessTokenLifeTime + '}';
    }


    
    
}
