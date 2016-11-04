/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kr.co.koscom.openapitest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import kr.co.koscom.openapitest.oauth.OAuthValueContainer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.openapi.jsonview.AccountBalanceViewer;
import org.openapi.jsonview.IntrestSymbolListViewer;
import org.openapi.jsonview.PortfolioViewer;
import org.openapi.jsonview.TransactionViewer;

/**
 *
 * @author heungjae
 */
public class ExecuteQuery2 {

    private String queryString;
    private String result;
    private final OAuthValueContainer ovc;
    private final String company;
    private final String querytype;
    private final String assettype;
    private final String rsptype;
    private final String accountNo;
    private final String ci;
    private final ResultSet resultSet;
    private final String pageno;

    ExecuteQuery2(String securitiesCompany, String querytype, String assettype, String querycondition, OAuthValueContainer oAuthValueContainer, String accountCi, String accountCi0, String pageno) {
        this.company = securitiesCompany;
        this.querytype = querytype;
        this.assettype = assettype;
        this.rsptype = querycondition;
        this.ovc = oAuthValueContainer;
        this.accountNo = accountCi;
        this.ci = accountCi0;
        this.pageno = pageno;
        this.resultSet = new ResultSet();
    }

    public ResultSet ExecuteQuery() throws UnsupportedEncodingException, IOException {
        String accessTokenCached;
        accessTokenCached = ovc.getAccessToken();
        System.out.println("AccessToken2 = [" + accessTokenCached + "]");
        // Request feed data with access token
        String requestUrl;
        String JSON_STRING;
        switch (querytype) {
            case "account":
                requestUrl = HttpConstants.API_GW_ENDPOINT + company + HttpConstants.API_ACCOUNT_BALANCE_TAIL;
                JSON_STRING = "{"
                        + "  \"accInfo\": {"
                        + "    \"vtAccNo\": \"" + accountNo + "\""
                        + "  },"
                        + "  \"balanceRequestBody\": {"
                        + "    \"queryType\": {"
                        + "      \"assetType\": \"" + assettype + "\","
                        + "      \"count\": 0,"
                        + "      \"page\": \""+pageno+"\""
                        + "    }"
                        + "  },"
                        + "  \"commonHeader\": {"
                        + "    \"ci\": \"" + ci + "\","
                        + "    \"reqIdConsumer\": \"string\""
                        + "  },"
                        + "  \"devInfo\": {"
                        + "    \"ipAddr\": \"IP\","
                        + "    \"macAddr\": \"string\""
                        + "  },"
                        + "  \"partner\": {"
                        + "    \"comId\": \"F0000\","
                        + "    \"srvId\": \"Mock\""
                        + "  }"
                        + "}";
                break;
            case "portfolio":
                requestUrl = HttpConstants.API_GW_ENDPOINT + company + HttpConstants.API_ACCOUNT_PORTFOLIO_TAIL;
                JSON_STRING = "{\"accInfo\":{\"vtAccNo\":\"" + accountNo + "\"},\"commonHeader\":{\"ci\":\"" + ci + "\",\"reqIdConsumer\":\"string\"},\"devInfo\":{\"ipAddr\":\"string\",\"macAddr\":\"string\"},\"partner\":{\"comId\":\"string\",\"srvId\":\"string\"},\"portfolioRequestBody\":{\"queryType\":{\"assetType\":\""+assettype+"\",\"count\":0,\"page\":\""+pageno+"\",\"rspType\":\""+rsptype+"\"}}}";
                break;
            case "transaction":
                requestUrl = HttpConstants.API_GW_ENDPOINT + company + HttpConstants.API_ACCOUNT_TRANSACTION_TAIL;
                JSON_STRING = "{\"accInfo\":{\"vtAccNo\":\"" + accountNo + "\"},\"commonHeader\":{\"ci\":\"" + ci + "\",\"reqIdConsumer\":\"string\"},\"devInfo\":{\"ipAddr\":\"string\",\"macAddr\":\"string\"},\"partner\":{\"comId\":\"string\",\"srvId\":\"string\"},\"transactionHistoryRequestBody\":{\"queryParams\":{\"conunt\":0,\"fromDate\":\"20160301\",\"isinCode\":\"\",\"page\":\""+pageno+"\",\"side\":\"ALL\",\"toDate\":\"20160330\"}}}";
                break;
            case "interest":
                requestUrl = HttpConstants.API_GW_ENDPOINT + company + HttpConstants.API_ACCOUNT_INTEREST_TAIL;
                JSON_STRING = "{\"accInfo\":{\"vtAccNo\":\"" + accountNo + "\"},\"commonHeader\":{\"ci\":\"" + ci + "\",\"reqIdConsumer\":\"string\"},\"devInfo\":{\"ipAddr\":\"string\",\"macAddr\":\"string\"},\"partner\":{\"comId\":\"string\",\"srvId\":\"string\"}}";
                break;
            default:
                requestUrl = HttpConstants.API_GW_ENDPOINT + company + HttpConstants.API_ACCOUNT_BALANCE_TAIL;
                JSON_STRING = "{"
                        + "  \"accInfo\": {"
                        + "    \"vtAccNo\": \"" + accountNo + "\""
                        + "  },"
                        + "  \"balanceRequestBody\": {"
                        + "    \"queryType\": {"
                        + "      \"assetType\": \""+assettype+"\","
                        + "      \"count\": 0,"
                        + "      \"page\": \""+pageno+"\""
                        + "    }"
                        + "  },"
                        + "  \"commonHeader\": {"
                        + "    \"ci\": \"" + ci + "\","
                        + "    \"reqIdConsumer\": \"string\""
                        + "  },"
                        + "  \"devInfo\": {"
                        + "    \"ipAddr\": \"IP\","
                        + "    \"macAddr\": \"string\""
                        + "  },"
                        + "  \"partner\": {"
                        + "    \"comId\": \"F0000\","
                        + "    \"srvId\": \"Mock\""
                        + "  }"
                        + "}";
                break;
        }

        HttpResponse httpResponse;
        StringBuffer responseBody;
        CloseableHttpClient httpClient;

        httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestUrl);
        httpPost.setHeader("Authorization", "Bearer " + accessTokenCached);
        httpPost.setHeader("Content-Type", "application/json;charset=utf-8");
        httpPost.addHeader("apikey", HttpConstants.THIS_APP_CLIENT_ID);
        StringEntity requestEntity = new StringEntity(JSON_STRING);
        System.out.println("[QueryString       ] - ["+JSON_STRING+"]");
        httpPost.setEntity(requestEntity);
        httpResponse = httpClient.execute(httpPost);
        // do streamed by response body

        responseBody = new StringBuffer();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent(), "UTF-8"))) {
            String lineBuffer = null;
            while ((lineBuffer = br.readLine()) != null) {
                responseBody.append(lineBuffer);
                responseBody.append("\n");
            }
        }
        httpClient.close();

        int respCode = httpResponse.getStatusLine().getStatusCode();
        if (respCode != HttpConstants.HTTP_OK) {
            resultSet.setMessage(responseBody.toString());
            resultSet.setStatusCode(respCode);
            resultSet.setStatus(httpResponse.getStatusLine().toString());
          
            return resultSet;
        }
        resultSet.setStatusCode(respCode);
        resultSet.setMessage(httpResponse.getStatusLine().getReasonPhrase());
        String resultMsg;
        System.out.println("Body - ["+responseBody+"]");
        resultMsg = getResultMsg(responseBody);
        resultSet.setMessage(resultMsg);
        return resultSet;
    }

    private String getResultMsg(StringBuffer responseBody) {
        String resultMsg;
        switch (querytype) {
            case "account":
                resultMsg = new AccountBalanceViewer().getMessage(responseBody.toString());
                break;
            case "portfolio":
                resultMsg = new PortfolioViewer().getMessage(responseBody.toString());
                break;
            case "transaction":
                resultMsg = new TransactionViewer().getMessage(responseBody.toString());
                break;
            case "interest":
                resultMsg = new IntrestSymbolListViewer().getMessage(responseBody.toString());
                break;
            default:
                resultMsg = "지원되지 않는 조회유형 - ["+querytype+"]";
                break;
        }
        return resultMsg;
    }
}
