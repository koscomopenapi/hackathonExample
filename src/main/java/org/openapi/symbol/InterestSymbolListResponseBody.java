/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.symbol;

import java.util.ArrayList;
import java.util.Objects;
import org.openapi.common.AccInfo;
import org.openapi.common.Response;
import org.openapi.portfolio.QueryType;
import org.openapi.transaction.QueryResult;

/**
 *
 * @author heungjae
 */
public class InterestSymbolListResponseBody {
   // AccInfo accInfo;
    QueryType queryType;
    QueryResult queryResult;
    GroupList groupList;
    Response resp;

    public InterestSymbolListResponseBody() {
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

    public GroupList getGroupList() {
        return groupList;
    }

    public void setGroupList(GroupList groupList) {
        this.groupList = groupList;
    }



    public Response getResp() {
        return resp;
    }

    public void setResp(Response resp) {
        this.resp = resp;
    }

}
