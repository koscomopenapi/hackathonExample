/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.balance;

import java.util.Objects;
import org.openapi.common.AccInfo;
import org.openapi.portfolio.QueryType;
import org.openapi.transaction.QueryResult;

/**
 *
 * @author heungjae
 */
public class BalanceResponseBody {

   // AccInfo accInfo;
    QueryType queryType;
    QueryResult queryResult;

    public BalanceResponseBody() {
    }

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
        return "BalanceResponseBody{" + "queryType=" + queryType + ", queryResult=" + queryResult + '}';
    }

    

}
