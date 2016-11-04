/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.transaction;

import org.openapi.common.CommonHeader;
import org.openapi.common.PlatformFields;
import org.openapi.common.Response;

import java.util.ArrayList;
import org.openapi.common.AccInfo;

/**
 *
 * @author heungjae
 */
public class TransactionHistoryResponse {

    CommonHeader commonHeader;
    PlatformFields platformFields;   //option
    AccInfo accInfo;
    TransactionHistoryResponseBody transactionHistoryResponseBody;
    TransactionList transList;
    Response resp;

    public TransactionHistoryResponse() {
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

    public TransactionHistoryResponseBody getTransactionHistoryResponseBody() {
        return transactionHistoryResponseBody;
    }

    public void setTransactionHistoryResponseBody(TransactionHistoryResponseBody transactionHistoryResponseBody) {
        this.transactionHistoryResponseBody = transactionHistoryResponseBody;
    }

    public TransactionList getTransList() {
        return transList;
    }

    public void setTransList(TransactionList transList) {
        this.transList = transList;
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

    public TransactionList getTransactionList() {
        return transList;
    }

    public void setTransactionList(TransactionList transactionList) {
        this.transList = transactionList;
    }

  
}
