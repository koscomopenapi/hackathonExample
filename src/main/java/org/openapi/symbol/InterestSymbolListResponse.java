/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.symbol;

import org.openapi.common.AccInfo;
import org.openapi.common.CommonHeader;
import org.openapi.common.PlatformFields;
import org.openapi.common.Response;

/**
 *
 * @author heungjae
 */
public class InterestSymbolListResponse {
    CommonHeader commonHeader;
    PlatformFields platformFields;   //option
    AccInfo accInfo;
    InterestSymbolListResponseBody interestSymbolListResponseBody;
    Response resp;

    public InterestSymbolListResponse() {
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

    public InterestSymbolListResponseBody getInterestSymbolListResponseBody() {
        return interestSymbolListResponseBody;
    }

    public void setInterestSymbolListResponseBody(InterestSymbolListResponseBody interestSymbolListResponseBody) {
        this.interestSymbolListResponseBody = interestSymbolListResponseBody;
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
        return "InterestSymbolListResponse{" +
                "commonHeader=" + commonHeader +
                ", platformFields=" + platformFields +
                ", interestSymbolListResponseBody=" + interestSymbolListResponseBody +
                ", resp=" + resp +
                '}';
    }
}
