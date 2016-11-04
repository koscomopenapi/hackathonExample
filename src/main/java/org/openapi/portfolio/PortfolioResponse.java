/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.portfolio;

import org.openapi.common.AccInfo;
import org.openapi.common.CommonHeader;
import org.openapi.common.PlatformFields;
import org.openapi.common.Response;

/**
 *
 * @author heungjae
 */
public class PortfolioResponse {
    CommonHeader commonHeader;
    PlatformFields platformFields;   //option
    AccInfo accInfo;
    PortfolioResponseBody portfolioResponseBody;
    PortfolioList portfolioList;
    Response resp;

    public PortfolioResponse() {
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

    public PortfolioResponseBody getPortfolioResponseBody() {
        return portfolioResponseBody;
    }

    public void setPortfolioResponseBody(PortfolioResponseBody portfolioResponseBody) {
        this.portfolioResponseBody = portfolioResponseBody;
    }

    public PortfolioList getPortfolioList() {
        return portfolioList;
    }

    public void setPortfolioList(PortfolioList portfolioList) {
        this.portfolioList = portfolioList;
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

  


    
}
