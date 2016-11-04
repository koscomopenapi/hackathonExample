package org.openapi.jsonview;

import com.google.gson.Gson;
import org.openapi.common.AccInfo;
import org.openapi.common.CommonHeader;
import org.openapi.common.Response;
import org.openapi.portfolio.QueryType;
import org.openapi.symbol.Group;
import org.openapi.symbol.InterestSymbolListResponse;
import org.openapi.symbol.InterestSymbolListResponseBody;
import org.openapi.transaction.*;

import java.util.ArrayList;
import org.openapi.symbol.GroupList;

/**
 * Created by heungjae on 2016-07-07.
 */
public class IntrestSymbolListViewer {

    public String getMessage(String jsonMsg) {

        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();

        try {
            InterestSymbolListResponse interestSymbolListResponse = gson.fromJson(jsonMsg, InterestSymbolListResponse.class);

            try {
                CommonHeader commonHeader = interestSymbolListResponse.getCommonHeader();
                stringBuilder.append("-------------------------------------\n");
                stringBuilder.append("CI = ").append(commonHeader.getCi()).append("\n");
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - CI값 이상 {" + ex.toString() + "}";
            }

            InterestSymbolListResponseBody interestSymbolListResponseBody;
            try {
                interestSymbolListResponseBody = interestSymbolListResponse.getInterestSymbolListResponseBody();
                AccInfo accInfo = interestSymbolListResponse.getAccInfo();

                stringBuilder.append("실계좌번호 = ").append(accInfo.getRealAccNo()).append("\n");
                stringBuilder.append("가상계좌번호 = ").append(accInfo.getVtAccNp()).append("\n");
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 계좌번호 영역 이상 {" + ex.toString() + "}";
            }

            QueryResult queryResult;
            QueryType queryType;
            try {
                queryResult = interestSymbolListResponseBody.getQueryResult();
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

                queryType = interestSymbolListResponseBody.getQueryType();
                stringBuilder.append("-------------------------------------").append("\n");
                stringBuilder.append("상품유형 = ").append(queryType.getAssetType()).append("\n");
                stringBuilder.append("응답유형 = ").append(queryType.getRspType()).append("\n");
                stringBuilder.append("요청건수 = ").append(queryType.getCount()).append("\n");
                stringBuilder.append("요청 페이지 = ").append(queryType.getPage()).append("\n");

            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 조회결과(총건수/금번건수/다음페이지 등) 영역 이상 {" + ex.toString() + "}";
            }

            GroupList groupList = interestSymbolListResponseBody.getGroupList();
            ArrayList<Group> groups = groupList.getGroup();

            int groupCnt;

            try {
                stringBuilder.append("-------------------------------------").append("\n");
                groupCnt = groups.size();
                stringBuilder.append("그룹건수 건수 = [").append(groupCnt).append("]").append("\n");

                for (Group object : groups) {
                    Group group = (Group)object;
                    stringBuilder.append("-------------------------------------").append("\n");
                    stringBuilder.append("관심종목 그룹이름 - [").append(group.getGroupName()).append("]").append("\n");
                    stringBuilder.append("수정일자 - [").append(group.getModifyDate()).append("]").append("\n");
                    ArrayList <String> isinList = group.getIsinList();
                    for (String isinCode : isinList) {
                        stringBuilder.append("종목코드 - [").append(isinCode).append("]").append("\n");
                    }
                }
                stringBuilder.append("-------------------------------------").append("\n");
            } catch (Exception ex) {
                return jsonMsg + "Json message is abnormal - 관싱종목그룹 영역 이상 {" + ex.toString() + "}";
            }
            Response response = interestSymbolListResponse.getResp();
            stringBuilder.append("-------------------------------------").append("\n");
            stringBuilder.append("증권사응답코드 - [").append(response.getRespCode()).append("]").append("\n");;
            stringBuilder.append("응답메시지 - [").append(response.getRespMsg()).append("]").append("\n");;


        } catch (Exception ex) {
            return jsonMsg + "Json message is abnormal  {" + ex.toString() + "}";
        }
        return stringBuilder.toString();
    }
}
