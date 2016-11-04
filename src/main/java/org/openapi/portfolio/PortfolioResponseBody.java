/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.portfolio;

import java.util.Objects;
import org.openapi.common.AccInfo;
import org.openapi.transaction.QueryResult;

/**
 *
 * @author heungjae
 */
public class PortfolioResponseBody {

  //  AccInfo accInfo;
    QueryType queryType;
    QueryResult queryResult;

    public PortfolioResponseBody() {
    }

//    public AccInfo getAccInfo() {
//        return accInfo;
//    }
//
//    public void setAccInfo(AccInfo accInfo) {
//        this.accInfo = accInfo;
//    }

    public QueryType getQueryType() {
        return queryType;
    }

    public void setQueryType(QueryType queryType) {
        this.queryType = queryType;
    }

    public QueryResult getQueryResult() {
        return queryResult;
    }

    public void setQueryResult(QueryResult queryResult) {
        this.queryResult = queryResult;
    }

    @Override
    public String toString() {
        return "PortfolioResponseBody{" + "queryType=" + queryType + ", queryResult=" + queryResult + '}';
    }


}
