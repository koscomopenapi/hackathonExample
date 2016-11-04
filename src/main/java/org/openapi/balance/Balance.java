/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.balance;

import java.util.ArrayList;

/**
 *
 * @author heungjae
 */
public class Balance {


    private BalanceSummary summary;
    private ArrayList<EquityBalance>  equityList;
    private ArrayList<FundBalance> fundList;
    private ArrayList<EtcBalance> etcList;

    public Balance(BalanceSummary summary, ArrayList<EquityBalance> equityList, ArrayList<FundBalance> fundList, ArrayList<EtcBalance> etcList) {
        this.summary = summary;
        this.equityList = equityList;
        this.fundList = fundList;
        this.etcList = etcList;
    }

    public BalanceSummary getSummary() {
        return summary;
    }

    public void setSummary(BalanceSummary summary) {
        this.summary = summary;
    }

    public ArrayList<EquityBalance> getEquityList() {
        return equityList;
    }

    public void setEquityList(ArrayList<EquityBalance> equityList) {
        this.equityList = equityList;
    }

    public ArrayList<FundBalance> getFundList() {
        return fundList;
    }

    public void setFundList(ArrayList<FundBalance> fundList) {
        this.fundList = fundList;
    }

    public ArrayList<EtcBalance> getEtcList() {
        return etcList;
    }

    public void setEtcList(ArrayList<EtcBalance> etcList) {
        this.etcList = etcList;
    }

    @Override
    public String toString() {
        return "BalanceList{" +
                "summary=" + summary +
                ", equityList=" + equityList +
                ", fundList=" + fundList +
                ", etcList=" + etcList +
                '}';
    }
}
