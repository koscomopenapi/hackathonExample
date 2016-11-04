/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.symbol;

import java.util.Objects;
import org.openapi.common.AccInfo;
import org.openapi.portfolio.QueryType;
import org.openapi.transaction.QueryResult;

/**
 *
 * @author heungjae
 */
public class InterestSymbolListRequestBody {
    AccInfo accInfo;
    QueryType queryType;
    
    public InterestSymbolListRequestBody() {
    }

    public AccInfo getAccInfo() {
        return accInfo;
    }

    public void setAccInfo(AccInfo accInfo) {
        this.accInfo = accInfo;
    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    @Override
    public String toString() {
        return "InterestSymbolListRequestBody{" +
                "accInfo=" + accInfo +
                ", queryType=" + queryType +
                '}';
    }
}
