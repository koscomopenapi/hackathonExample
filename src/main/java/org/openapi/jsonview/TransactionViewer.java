package org.openapi.jsonview;

import com.google.gson.Gson;
import org.openapi.common.AccInfo;
import org.openapi.common.CommonHeader;
import org.openapi.common.Response;
import org.openapi.transaction.*;

import java.util.ArrayList;

/**
 * Created by heungjae on 2016-07-07.
 */
public class TransactionViewer {

    public String getMessage(String jsonMsg) {

        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            TransactionHistoryResponse transactionHistoryResponse = gson.fromJson(jsonMsg, TransactionHistoryResponse.class);

            try {
                CommonHeader commonHeader = transactionHistoryResponse.getCommonHeader();
                stringBuilder.append("-------------------------------------\n");
                stringBuilder.append("CI = ").append(commonHeader.getCi()).append("\n");
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - CI값 이상 {" + ex.toString() + "}";
            }

            TransactionHistoryResponseBody transactionHistoryResponseBody;
            try {
                transactionHistoryResponseBody = transactionHistoryResponse.getTransactionHistoryResponseBody();
                AccInfo accInfo = transactionHistoryResponse.getAccInfo();

                stringBuilder.append("실계좌번호 = ").append(accInfo.getRealAccNo()).append("\n");
                stringBuilder.append("가상계좌번호 = ").append(accInfo.getVtAccNp()).append("\n");
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 계좌번호 영역 이상 {" + ex.toString() + "}";
            }

            QueryResult queryResult;
            QueryParams queryParams;
            try {
                queryResult = transactionHistoryResponseBody.getQueryResult();
                stringBuilder.append("-------------------------------------").append("\n");
                stringBuilder.append("조회결과:총건수= ").append(queryResult.getTotalCnt()).append("\n");
                stringBuilder.append("조회결과:금번건수= ").append(queryResult.getCount()).append("\n");
                if (queryResult.getPage() == null || queryResult.getPage().equals("null")) {
                    stringBuilder.append("더이상 조회할 페이지가 없습니다.").append("\n");
                } else if (queryResult.getPage() != null) {
                    stringBuilder.append("다음 페이지의 키 = [").append(queryResult.getPage()).append("]").append("\n");
                } else {
                    stringBuilder.append("더이상 조회할 페이지가 없습니다.").append("\n");
                }

                queryParams = transactionHistoryResponseBody.getQueryParameter();
                stringBuilder.append("-------------------------------------").append("\n");
                stringBuilder.append("조회시작일 = ").append(queryParams.getFromDate()).append("\n");
                stringBuilder.append("조회종료일 = ").append(queryParams.getToDate()).append("\n");
                stringBuilder.append("조회종목코드 = ").append(queryParams.getIsinCode()).append("\n");
                stringBuilder.append("거래유형 = ").append(queryParams.getSide()).append("\n");
                stringBuilder.append("응답건수/이번 메시지 내 = ").append(queryParams.getCount()).append("\n");
                stringBuilder.append("요청시 페이지 키 = ").append(queryParams.getPage()).append("\n");

            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 조회결과(총건수/금번건수/다음페이지 등) 영역 이상 {" + ex.toString() + "}";
            }

            TransactionList transactionList = transactionHistoryResponse.getTransList();
            ArrayList<Transaction> transactionArray;
            transactionArray = transactionList.getTransaction();
            int transactionCnt;

            try {
                stringBuilder.append("-------------------------------------").append("\n");
                transactionCnt = transactionArray.size();
                stringBuilder.append("거래내역 건수 = [").append(transactionCnt).append("]").append("\n");

                for (Object object : transactionArray) {
                    Transaction transaction = (Transaction) object;
                    stringBuilder.append("-------------------------------------").append("\n");
                    stringBuilder.append("종목코드 - [").append(transaction.getIsinCode()).append("]").append("\n");
                    stringBuilder.append("거래일자 - [").append(transaction.getTransDate()).append("]").append("\n");
                    stringBuilder.append("거래유형 - [").append(transaction.getTransType()).append("]").append("\n");
                    stringBuilder.append("금액증감 - [").append(transaction.getChangeAmt()).append("]").append("\n");
                    stringBuilder.append("증감수량 - [").append(transaction.getQty()).append("]").append("\n");
                }
                stringBuilder.append("-------------------------------------").append("\n");
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 거래내역 영역 이상 {" + ex.toString() + "}";
            }
            {
                int totalCntInMsg = transactionCnt;
                stringBuilder.append("-------------------------------------").append("\n");;
                stringBuilder.append("이번 메시지 내 총 종목건수 - [").append(totalCntInMsg).append("]\n");
                if (totalCntInMsg - queryResult.getCount() != 0) {
                    stringBuilder.append("보낼 종목수와 받은 종목수가 차이가 있음").append("\n");
                }
            }
            Response response = transactionHistoryResponse.getResp();
            stringBuilder.append("-------------------------------------").append("\n");
            stringBuilder.append("증권사응답코드 - [").append(response.getRespCode()).append("]").append("\n");;
            stringBuilder.append("응답메시지 - [").append(response.getRespMsg()).append("]").append("\n");;


        } catch (Exception ex) {
            return jsonMsg + "Json message is abnormal  {" + ex.toString() + "}";
        }
        return stringBuilder.toString();
    }
}
