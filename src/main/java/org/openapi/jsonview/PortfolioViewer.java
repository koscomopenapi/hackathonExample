package org.openapi.jsonview;

import com.google.gson.*;
import org.openapi.common.AccInfo;
import org.openapi.common.CommonHeader;
import org.openapi.portfolio.Portfolio;
import org.openapi.portfolio.PortfolioResponse;
import org.openapi.portfolio.PortfolioResponseBody;
import org.openapi.portfolio.QueryType;
import org.openapi.portfolio.asset.Equity;
import org.openapi.portfolio.asset.Etc;
import org.openapi.portfolio.asset.Fund;
import org.openapi.transaction.QueryResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.openapi.portfolio.PortfolioList;
import org.openapi.portfolio.asset.Cash;

/**
 * Created by heungjae on 2016-07-07.
 */
public class PortfolioViewer {

    public String getMessage(String jsonMsg) {

        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            PortfolioResponse portfolioResponse = gson.fromJson(jsonMsg, PortfolioResponse.class);

            try {
                CommonHeader commonHeader = portfolioResponse.getCommonHeader();
                stringBuilder.append("-------------------------------------\n");
                stringBuilder.append("CI = ").append(commonHeader.getCi()).append("\n");
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - CI값 이상 {" + ex.toString() + "}";
            }

            PortfolioResponseBody portfolioResponseBody;
            try {
                portfolioResponseBody = portfolioResponse.getPortfolioResponseBody();
                AccInfo accInfo = portfolioResponse.getAccInfo();

                stringBuilder.append("실계좌번호 = ").append(accInfo.getRealAccNo()).append("\n");
                stringBuilder.append("가상계좌번호 = ").append(accInfo.getVtAccNp()).append("\n");
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 계좌번호 영역 이상 {" + ex.toString() + "}";
            }
            boolean isRatType = false;
            try {
                QueryType queryType = portfolioResponseBody.getQueryType();

                if (queryType.getRspType().equals("RAT")) {
                    isRatType = true;
                }
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 응답 유형(RAT/QTY) 영역 이상 {" + ex.toString() + "}";
            }

            QueryResult queryResult;
            try {
                queryResult = portfolioResponseBody.getQueryResult();
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
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 조회결과(총건수/금번건수/다음페이지 등) 영역 이상 {" + ex.toString() + "}";
            }

            PortfolioList portfolioList = portfolioResponse.getPortfolioList();
            Portfolio portfolio = portfolioList.getPortfolio();

            Cash cash = portfolio.getCash();
            try {
                stringBuilder.append("-------------------------------------").append("\n");
                stringBuilder.append("계좌내현금 - ").append(new BigDecimal(cash.getAmt())).append("\n");
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 현금 amount 영역 이상 {" + ex.toString() + "}";
            }

            int equityCnt, fundCnt, etcCnt;

            try {
                ArrayList equityList = portfolio.getEquityList();
                stringBuilder.append("-------------------------------------").append("\n");;
                equityCnt = equityList.size();

                stringBuilder.append("Equity 상품군 종목수 = [").append(equityCnt).append("]").append("\n");

                double sum = 0.0;

                for (Object object : equityList) {
                    Equity equity = (Equity) object;
                    stringBuilder.append("-------------------------------------").append("\n");;
                    stringBuilder.append("상품구분 - [").append(equity.getAssetType()).append("]").append("\n");
                    stringBuilder.append("종목코드 - [").append(equity.getIsinCode()).append("]").append("\n");
                    if (isRatType) {
                        stringBuilder.append("보유비율 - [").append(equity.getQty()).append("]%").append("\n");
                    } else {
                        stringBuilder.append("보유수량 - [").append(new BigDecimal(equity.getQty())).append("]주").append("\n");
                    }
                    stringBuilder.append("수익률 - [").append(equity.getEarningRate()).append("%]").append("\n");
                    sum += equity.getQty();
                }
                stringBuilder.append("-------------------------------------").append("\n");
                if (isRatType) {
                    stringBuilder.append("Equity 상품군 누적보유비율 = [").append(sum).append("]").append("\n");
                }
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - Equity 영역 이상 {" + ex.toString() + "}";
            }

            try {
                ArrayList fundList = portfolio.getFundList();
                stringBuilder.append("-------------------------------------").append("\n");
                fundCnt = fundList.size();

                stringBuilder.append("Fund 상품군 종목수 = [").append(fundCnt).append("]").append("\n");

                double sum = 0.0;
                for (Object object : fundList) {
                    Fund fund = (Fund) object;
                    stringBuilder.append("-------------------------------------").append("\n");
                    stringBuilder.append("펀드코드 - [").append(fund.getFundCode()).append("]").append("\n");
                    stringBuilder.append("펀드이름 - [").append(fund.getFundName()).append("]").append("\n");
                    if (isRatType) {
                        stringBuilder.append("보유비율 - [").append(fund.getQty()).append("]%").append("\n");
                    } else {
                        stringBuilder.append("보유수량 - [").append(new BigDecimal(fund.getQty())).append("]주").append("\n");
                    }
                    stringBuilder.append("수익률 - [").append(fund.getEarningRate()).append("%]").append("\n");
                    stringBuilder.append("만기일 - [").append(fund.getMaturity()).append("]").append("\n");
                    sum += fund.getQty();
                }
                stringBuilder.append("-------------------------------------").append("\n");
                if (isRatType) {
                    stringBuilder.append("Fund 상품군 누적보유비율 = [").append(sum).append("]").append("\n");
                }
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 펀드  영역 이상 {" + ex.toString() + "}";
            }

            try {
                ArrayList etcList = portfolio.getEtcList();
                stringBuilder.append("-------------------------------------").append("\n");;
                etcCnt = etcList.size();

                stringBuilder.append("ETC 상품군 종목수 = [").append(fundCnt).append("]").append("\n");
                double sum = 0.0;
                for (Object object : etcList) {
                    Etc etc = (Etc) object;
                    stringBuilder.append("-------------------------------------").append("\n");
                    stringBuilder.append("상품구분 - [").append(etc.getAssetType()).append("]").append("\n");
                    stringBuilder.append("상품이름 - [").append(etc.getAssetName()).append("]").append("\n");

                    if (isRatType) {
                        stringBuilder.append("보유비율 - [").append(etc.getQty()).append("]%").append("\n");
                    } else {
                        stringBuilder.append("보유수량 - [").append(new BigDecimal(etc.getQty())).append("]주").append("\n");
                    }
                    stringBuilder.append("수익률 - [").append(etc.getEarningRate()).append("%]").append("\n");
                    sum += etc.getQty();
                }
                stringBuilder.append("-------------------------------------").append("\n");
                if (isRatType) {
                    stringBuilder.append("ETC 상품군 누적보유비율 = [").append(sum).append("]").append("\n");
                }

            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - ETC(기타상품) 영역 이상 {" + ex.toString() + "}";
            }

            {
                int totalCntInMsg = equityCnt + fundCnt + etcCnt;
                stringBuilder.append("-------------------------------------").append("\n");
                stringBuilder.append("이번 메시지 내 총 종목건수 - [").append(totalCntInMsg).append("]").append("\n");
                if (totalCntInMsg - queryResult.getCount() != 0) {
                    stringBuilder.append("보낼 종목수와 받은 종목수가 차이가 있음").append("\n");
                }
            }

        } catch (Exception ex) {
            return jsonMsg + "Json message is abnormal  {" + ex.toString() + "}";
        }
        return stringBuilder.toString();
    }
}
