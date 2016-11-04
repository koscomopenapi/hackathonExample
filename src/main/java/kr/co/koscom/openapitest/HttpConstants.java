
package kr.co.koscom.openapitest;

public class HttpConstants {
    
        public static final String API_GW_ENDPOINT = "https://sandbox-apigw.koscom.co.kr/v1/";
        public static final String OAUTH_TOKEN_REQUEST_URI = "https://sandbox-apigw.koscom.co.kr/auth/oauth/v2/token";
        public static final String OAUTH_REDIRECT_URI ="http://localhost:8084/callbacknew";
        public static final String THIS_APP_CLIENT_ID ="l7xxf234248b6fbd42a1a6844861524b2320";
        public static final String THIS_APP_CLIENT_SECRET ="12f7d5f8bac34a769696098425f3b1cb";
        
        public static final String API_ACCOUNT_BALANCE_TAIL = "/account/balance/search";
        public static final String API_ACCOUNT_PORTFOLIO_TAIL = "/account/portfolio/search";
        public static final String API_ACCOUNT_TRANSACTION_TAIL = "/account/transaction/search";
        public static final String API_ACCOUNT_INTEREST_TAIL = "/account/interest/search";
	
	public static final String ACCESS_TOKEN = "access_token";
        public static final String EXPIRES_IN = "expires_in";
        
	public static final String CLIENT_ID = "client_id";
	public static final String CLIENT_SECRET = "client_secret";
	public static final String REFRESH_TOKEN = "refresh_token";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String CODE = "code";
	public static final String CALLER = "caller";
	public static final String TOKEN = "token";
	public static final String APPROVAL_PROMPT_KEY = "approval_prompt_key";
	public static final String ACCESS_TYPE_KEY = "access_type_key";
	public static final String REDIRECT_URI = "redirect_uri";
	public static final String RESPONSE_TYPE = "response_type";
	public static final String APPROVAL_PROMPT_VALUE = "approval_prompt_value";
	public static final String ACCESS_TYPE_VALUE = "access_type_value";
	public static final String AUTHENTICATION_SERVER_URL = "authentication_server_url";
	public static final String TOKEN_ENDPOINT_URL = "token_endpoint_url";
	public static final String RESOURCE_SERVER_URL = "resource_server_url";
	public static final String GRANT_TYPE = "grant_type";
	public static final String GRANT_TYPE_PASSWORD = "password";
	public static final String GRANT_TYPE_AUTHORIZATION_CODE = "authorization_code";
	public static final String GRANT_TYPE_CLIENT_CREDENTIALS = "client_credentials";
	public static final String SCOPE = "scope";
	public static final String LOCATION_HEADER = "Location";
	public static final String STATE = "state";
	public static final String AUTHORIZATION = "Authorization";
	public static final String BEARER = "Bearer";
	public static final String BASIC = "Basic";
	public static final String JSON_CONTENT = "application/json";
	public static final String XML_CONTENT = "application/xml";
	public static final String URL_ENCODED_CONTENT = "application/x-www-form-urlencoded";
	
	public static final int HTTP_OK = 200;
	public static final int HTTP_FORBIDDEN = 403;
	public static final int HTTP_UNAUTHORIZED = 401;
	public static final int HTTP_SEND_REDIRECT = 302;
	

}
