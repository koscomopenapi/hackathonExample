/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.balance;

import java.util.Objects;
import org.openapi.common.AccInfo;
import org.openapi.common.CommonHeader;
import org.openapi.common.PlatformFields;
import org.openapi.common.Response;

/**
 *
 * @author heungjae
 */
public class BalanceResponse {
    
    CommonHeader commonHeader;
    PlatformFields platformFields;   //option
    AccInfo accInfo;
    BalanceResponseBody balanceResponseBody;
    BalanceList balanceList;
    Response resp;

    public BalanceResponse() {
    }

    public CommonHeader getCommonHeader() {
        return commonHeader;
    }

    public void setCommonHeader(CommonHeader commonHeader) {
        this.commonHeader = commonHeader;
    }

    public PlatformFields getPlatformFields() {
        return platformFields;
    }

    public void setPlatformFields(PlatformFields platformFields) {
        this.platformFields = platformFields;
    }

    public BalanceResponseBody getBalanceResponseBody() {
        return balanceResponseBody;
    }

    public void setBalanceResponseBody(BalanceResponseBody balanceResponseBody) {
        this.balanceResponseBody = balanceResponseBody;
    }

    public BalanceList getBalanceList() {
        return balanceList;
    }

    public void setBalanceList(BalanceList balanceList) {
        this.balanceList = balanceList;
    }

    public Response getResp() {
        return resp;
    }

    public void setResp(Response resp) {
        this.resp = resp;
    }

    public AccInfo getAccInfo() {
        return accInfo;
    }

    public void setAccInfo(AccInfo accInfo) {
        this.accInfo = accInfo;
    }

    
    @Override
    public String toString() {
        return "BalanceResponse{" + "commonHeader=" + commonHeader + ", platformFields=" + platformFields + ", balanceResponseBody=" + balanceResponseBody + ", balanceList=" + balanceList + ", resp=" + resp + '}';
    }



    
    
}
