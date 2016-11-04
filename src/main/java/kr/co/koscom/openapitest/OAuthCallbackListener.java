package kr.co.koscom.openapitest;


import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static kr.co.koscom.openapitest.oauth.OAuthUtils.handleResponse;
import kr.co.koscom.openapitest.oauth.OAuthValueContainer;
import org.apache.commons.codec.binary.Base64;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.protocol.Protocol;


public class OAuthCallbackListener extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static HashMap<String, OAuthValueContainer> oAuthMap;

    @Override
    public void init() {
        oAuthMap = new HashMap();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletConfig().getServletContext().setAttribute("oAuthMap", oAuthMap); 
        // Detect the presence of an authorization code
        String authorizationCode = request.getParameter("code");
        String sessionID = request.getParameter("state");

        if (authorizationCode != null && authorizationCode.length() > 0) {

            // Generate POST request
            PostMethod postMethod = new PostMethod(HttpConstants.OAUTH_TOKEN_REQUEST_URI
                    + "?grant_type=" + URLEncoder.encode(HttpConstants.GRANT_TYPE_AUTHORIZATION_CODE, StandardCharsets.UTF_8.name())
                    + "&code=" + URLEncoder.encode(authorizationCode, StandardCharsets.UTF_8.name())
                    + "&redirect_uri=" + URLEncoder.encode(HttpConstants.OAUTH_REDIRECT_URI, StandardCharsets.UTF_8.name()));
               //     + "&client_id=" + URLEncoder.encode(HttpConstants.THIS_APP_CLIENT_ID, StandardCharsets.UTF_8.name())
                 //   + "&client_secret=" + URLEncoder.encode(HttpConstants.THIS_APP_CLIENT_SECRET, StandardCharsets.UTF_8.name()));

            // Add "Authorization" header with encoded client credentials
      String clientCredentials = HttpConstants.THIS_APP_CLIENT_ID + ":" + HttpConstants.THIS_APP_CLIENT_SECRET;
      String encodedClientCredentials = new String(Base64.encodeBase64(clientCredentials.getBytes()));
      postMethod.setRequestHeader("authorization", "Basic " + encodedClientCredentials);
            String accessToken = null, tokenType = null, expires_in = null, refreshToken = null, scope = null;
            long expire_in;

            // Make the access token request
            Map<String, Object> map;
            HttpClient httpclient = new HttpClient();

            Protocol.registerProtocol("https", new Protocol("https", new MySSLSocketFactory(), 443));
            
            int code = httpclient.executeMethod(postMethod);
            
            System.out.println(postMethod.getResponseBodyAsString());
            map = handleResponse(postMethod.getResponseBodyAsString());
            accessToken = (String) map.get(HttpConstants.ACCESS_TOKEN);
            refreshToken = (String) map.get(HttpConstants.REFRESH_TOKEN);
            scope = (String) map.get(HttpConstants.SCOPE);
            expire_in = (Long) map.get(HttpConstants.EXPIRES_IN);

            oAuthMap.put(sessionID, new OAuthValueContainer(sessionID, accessToken, refreshToken, scope, expire_in));
            
            response.sendRedirect("query.html");
        } else {
            // Handle failure
        }
    }
}
