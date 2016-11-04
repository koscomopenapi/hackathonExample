package org.openapi.jsonview;

import com.google.gson.*;
import org.openapi.balance.*;
import org.openapi.common.AccInfo;
import org.openapi.common.CommonHeader;
import org.openapi.common.Response;
import org.openapi.transaction.QueryResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import kr.co.koscom.openapitest.ApiQueryResult;

/**
 * Created by heungjae on 2016-07-07.
 */
public class AccountBalanceViewer {

    public String getMessage(String jsonMsg) {

        //   jsonMsg = "{\"commonHeader\":{\"reqIdPlatform\":\"test1\",\"reqIdConsumer\":\"string\",\"ci\":\"S1V7HGXBV1EPGBJasoZf4fQV+eOpOc1pfizByV6UIEYJHM/PF9QKu+PU2OThEog7QmVKSZNibNGg+/k0XB/9jQ\\u003d\\u003d\"},\"balanceResponseBody\":{\"accInfo\":{\"vtAccNo\":\"160678007213500001\"},\"queryType\":{\"assetType\":\"ALL\",\"count\":\"0\",\"page\":\"null\"},\"queryResult\":{\"totalCnt\":157.0,\"count\":157.0,\"page\":\"null\"}},\"balanceList\":{\"summary\":{\"cashBalance\":6976542.0,\"d1\":6976542.0,\"d2\":6976542.0,\"substitute\":9.816358E7,\"receivable\":0.0,\"subsMargin\":762635.0,\"loanCredit\":0.0,\"valAtTrade\":3.69827563509E11,\"valueAtCur\":3.89165300498E11,\"proLoss\":1.9337736989E10,\"totalAccVal\":3.8917227704E11,\"cashAvWithdraw\":6976542.0},\"equityList\":[{\"assetType\":\"KSP\",\"isinCode\":\"HK0000050325\",\"qty\":120.0,\"tradeType\":\"SUM\",\"valAtTrade\":281764.0,\"valAtCur\":245400.0,\"proLoss\":-36364.0,\"earningRate\":-12.9},{\"assetType\":\"KDQ\",\"isinCode\":\"HK0000054723\",\"qty\":1186.0,\"tradeType\":\"SUM\",\"valAtTrade\":2326871.0,\"valAtCur\":1867950.0,\"proLoss\":-458921.0,\"earningRate\":-19.72}],\"fundList\":[{\"fundCode\":\"KRZ500395135\",\"fundName\":\"삼성중소형FOCUS증권자1호[주식]\",\"valAtTrade\":0.0,\"valAtCur\":7.2329965E7,\"proLoss\":7.2329965E7,\"firstDateBuy\":\"20160112\",\"lastDateBuy\":\"20160113\",\"maturity\":\"00000000\",\"earningRate\":-9.58},{\"fundCode\":\"KRZ501831185\",\"fundName\":\"메리츠코리아증권투자신탁1호[주\",\"valAtTrade\":0.0,\"valAtCur\":8416030.0,\"proLoss\":8416030.0,\"firstDateBuy\":\"20150818\",\"lastDateBuy\":\"20150819\",\"maturity\":\"00000000\",\"earningRate\":-15.83}],\"etcList\":[{\"assetType\":\"BOND\",\"assetName\":\"국민주택1종11-07\",\"qty\":1.0E10,\"tradeType\":\"SUM\",\"valAtTrade\":9.438E9,\"valueAtCur\":1.1584E10,\"earningRate\":22.73},{\"assetType\":\"BOND\",\"assetName\":\"국고03000-2409(14-5)\",\"qty\":1.0E10,\"tradeType\":\"SUM\",\"valAtTrade\":1.05552E10,\"valueAtCur\":1.1366E10,\"earningRate\":7.68}]},\"resp\":{\"respCode\":\"200\",\"respMsg\":\"OK\"}}";
        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        ApiQueryResult apiQueryResult = new ApiQueryResult();
        QueryResult queryResult = null;
        BalanceResponse balanceResponse = gson.fromJson(jsonMsg, BalanceResponse.class);

        try {
            try {
                CommonHeader commonHeader = balanceResponse.getCommonHeader();
                if (commonHeader != null) {
                    stringBuilder.append("-------------------------------------").append("\n");
                    stringBuilder.append("CI = ").append(commonHeader.getCi()).append("\n");
                }
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - CI값 이상 {" + ex.toString() + "}";
            }

            BalanceResponseBody balanceResponseBody = balanceResponse.getBalanceResponseBody();
            if (balanceResponseBody != null) {
                AccInfo accInfo = balanceResponse.getAccInfo();

                try {
                    if (accInfo != null) {
                        stringBuilder.append("실계좌번호 = ").append(accInfo.getRealAccNo()).append("\n");
                        stringBuilder.append("가상계좌번호 = ").append(accInfo.getVtAccNp()).append("\n");
                    }
                } catch (Exception ex) {
                    return jsonMsg + "Json message is abnormal - 계좌번호 영역 이상 {" + ex.toString() + "}";
                }

                try {
                    queryResult = balanceResponseBody.getQueryResult();
                    if (queryResult != null) {
                        stringBuilder.append("-------------------------------------").append("\n");
                        stringBuilder.append("조회결과:총건수= ").append(queryResult.getTotalCnt()).append("\n");
                        stringBuilder.append("조회결과:금번건수= ").append(queryResult.getCount()).append("\n");
                        if (queryResult.getPage() == null || queryResult.getPage().equals("null")) {
                            stringBuilder.append("더이상 조회할 페이지가 없습니다.").append("\n");
                        } else {
                            stringBuilder.append("다음 페이지의 키 = [").append(queryResult.getPage()).append("]").append("\n");
                        }
                    }
                } catch (Exception ex) {
                    return jsonMsg + "Json message is abnormal - 조회결과, 건수, 다음페이지키 등 영역 이상 {" + ex.toString() + "}";
                }
            }

            BalanceList balanceList = balanceResponse.getBalanceList();
            Balance balance =  balanceList.getBalance();
            BalanceSummary balanceSummary = balance.getSummary();
            if (balanceSummary != null) {

                try {

                    stringBuilder.append("-------------------------------------").append("\n");
                    stringBuilder.append("계좌내현금 - [").append(new BigDecimal(balanceSummary.getCashBalance())).append("]").append("\n");
                    stringBuilder.append("d+1현금 - [").append(new BigDecimal(balanceSummary.getD1())).append("]").append("\n");
                    stringBuilder.append("d+2현금 - [").append(new BigDecimal(balanceSummary.getD2())).append("]").append("\n");
                    stringBuilder.append("대용금 - [").append(new BigDecimal(balanceSummary.getSubstiture())).append("]").append("\n");
                    stringBuilder.append("미수/미납금 - [").append(new BigDecimal(balanceSummary.getReceivable())).append("]").append("\n");
                    stringBuilder.append("대용증거금 - [").append(new BigDecimal(balanceSummary.getSubsMargin())).append("]").append("\n");
                    stringBuilder.append("대출/신용금 - [").append(new BigDecimal(balanceSummary.getLoanCredit())).append("]").append("\n");
                    stringBuilder.append("유가증권매수금액 - [").append(new BigDecimal(balanceSummary.getValAtTrade())).append("]").append("\n");
                    stringBuilder.append("유가증권평가금액 - [").append(new BigDecimal(balanceSummary.getValueAtCur())).append("]").append("\n");
                    stringBuilder.append("유가증권평가손익 - [").append(new BigDecimal(balanceSummary.getProLoss())).append("]").append("\n");
                    stringBuilder.append("총평가금액 - [").append(new BigDecimal(balanceSummary.getTotalAccVal())).append("]").append("\n");
                    stringBuilder.append("출금가능액 - [").append(new BigDecimal(balanceSummary.getCashAvWithdraw())).append("]").append("\n");
                } catch (Exception ex) {
                    return jsonMsg + "Json message is abnormal - 현금잔고요약부 이상 {" + ex.toString() + "}";
                }
            }
            ArrayList<EquityBalance> equityList = balance.getEquityList();
            stringBuilder.append("-------------------------------------").append("\n");
            int equityCnt = equityList.size();
            stringBuilder.append("Equity 상품군 종목수 = [").append(equityCnt).append("]").append("\n");
            try {
                for (EquityBalance equityBalance : equityList) {

                    stringBuilder.append("-------------------------------------").append("\n");

                    stringBuilder.append("상품구분 - [").append(equityBalance.getAssetType()).append("]").append("\n");
                    stringBuilder.append("종목코드 - [").append(equityBalance.getIsinCode()).append("]").append("\n");
                    stringBuilder.append("보유수량 - [").append(equityBalance.getQty()).append("]").append("\n");
                    stringBuilder.append("거래유형 - [").append(equityBalance.getTradeType()).append("]").append("\n");
                    stringBuilder.append("매수금액 - [").append(new BigDecimal(equityBalance.getValAtTrade())).append("]").append("\n");
                    stringBuilder.append("평가금액 - [").append(new BigDecimal(equityBalance.getValAtCur())).append("]").append("\n");
                    stringBuilder.append("평가손익 - [").append(new BigDecimal(equityBalance.getProLoss())).append("]").append("\n");
                    stringBuilder.append("수익률 - [").append(equityBalance.getEarningRate()).append("%]").append("\n");
                }
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 유가증권,코스닥,KRX파생상품 영역 이상 {" + ex.toString() + "}";
            }

            ArrayList<FundBalance> fundList = balance.getFundList();
            stringBuilder.append("-------------------------------------").append("\n");
            int fundCnt = fundList.size();
            stringBuilder.append("Fund 상품군 종목수 = [").append(fundCnt).append("]").append("\n");
            try {
                for (FundBalance fundBalance : fundList) {
                    stringBuilder.append("-------------------------------------").append("\n");
                    stringBuilder.append("펀드코드 - [").append(fundBalance.getFundCode()).append("]").append("\n");
                    stringBuilder.append("펀드이름 - [").append(fundBalance.getFundName()).append("]").append("\n");
                    stringBuilder.append("매수금액 - [").append(new BigDecimal(fundBalance.getValAtTrade())).append("]").append("\n");
                    stringBuilder.append("평가금액 - [").append(new BigDecimal(fundBalance.getValAtCur())).append("]").append("\n");
                    stringBuilder.append("평가손익 - [").append(new BigDecimal(fundBalance.getProLoss())).append("]").append("\n");
                    stringBuilder.append("최초매수일 - [").append(fundBalance.getFirstDateBuy()).append("]").append("\n");
                    stringBuilder.append("최종매수일 - [").append(fundBalance.getLastDateBuy()).append("]").append("\n");
                    stringBuilder.append("만기일 - [").append(fundBalance.getMaturity()).append("]").append("\n");
                    stringBuilder.append("수익률 - [").append(fundBalance.getEarningRate()).append("%]").append("\n");
                }
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 펀드영역 이상 {" + ex.toString() + "}";
            }

            ArrayList<EtcBalance> etcList = balance.getEtcList();
            stringBuilder.append("-------------------------------------").append("\n");
            int etcCnt = etcList.size();
            stringBuilder.append("ETC 상품군 종목수 = [").append(etcCnt).append("]").append("\n");

            try {
                for (EtcBalance etcBalance : etcList) {
                    stringBuilder.append("-------------------------------------").append("\n");
                    stringBuilder.append("상품구분 - [").append(etcBalance.getAssetType()).append("]").append("\n");
                    stringBuilder.append("상품이름 - [").append(etcBalance.getAssetName()).append("]").append("\n");
                    stringBuilder.append("보유수량 - [").append(new BigDecimal(etcBalance.getQty())).append("]").append("\n");
                    stringBuilder.append("거래유형 - [").append(etcBalance.getTradeType()).append("]").append("\n");
                    stringBuilder.append("매수금액 - [").append(new BigDecimal(etcBalance.getValAtTrade())).append("]").append("\n");
                    stringBuilder.append("평가금액 - [").append(new BigDecimal(etcBalance.getValAtCur())).append("]").append("\n");
                    stringBuilder.append("수익률 - [").append(etcBalance.getEarningRate()).append("%]").append("\n");
                }
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 기타상품 영역 이상 {" + ex.toString() + "}";
            }

            {
                int totalCntInMsg = equityCnt + fundCnt + etcCnt;
                stringBuilder.append("-------------------------------------").append("\n");
                stringBuilder.append("이번 메시지 내 총 종목건수 - [").append(totalCntInMsg).append("]").append("\n");
                if (totalCntInMsg - queryResult.getCount() != 0) {
                    stringBuilder.append("보낼 종목수와 받은 종목수가 차이가 있음");
                }
            }

            Response response = balanceResponse.getResp();
            stringBuilder.append("-------------------------------------").append("\n");
            stringBuilder.append("증권사응답코드 - [").append(response.getRespCode()).append("]").append("\n");
            stringBuilder.append("응답메시지 - [").append(response.getRespMsg()).append("]").append("\n");

        } catch (Exception ex) {
            return jsonMsg + "Json message is abnormal  {" + ex.toString() + "}";
        }
        return stringBuilder.toString();
    }
    
}
