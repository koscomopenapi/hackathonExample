/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.portfolio;

import java.util.ArrayList;
import java.util.Objects;
import org.openapi.portfolio.asset.Cash;
import org.openapi.portfolio.asset.Equity;
import org.openapi.portfolio.asset.Etc;
import org.openapi.portfolio.asset.Fund;

/**
 *
 * @author heungjae
 */
public class Portfolio {

    private Cash cash;
    private ArrayList <Equity> equityList;
    private ArrayList <Fund> fundList;
    private ArrayList <Etc> etcList;

    public Portfolio() {
    }

    
    public Cash getCash() {
        return cash;
    }

    public void setCash(Cash cash) {
        this.cash = cash;
    }

    public ArrayList<Equity> getEquityList() {
        return equityList;
    }

    public void setEquityList(ArrayList<Equity> equityList) {
        this.equityList = equityList;
    }

    public ArrayList<Fund> getFundList() {
        return fundList;
    }

    public void setFundList(ArrayList<Fund> fundList) {
        this.fundList = fundList;
    }

    public ArrayList<Etc> getEtcList() {
        return etcList;
    }

    public void setEtcList(ArrayList<Etc> etcList) {
        this.etcList = etcList;
    }

    @Override
    public String toString() {
        return "PortfolioList{" + "cash=" + cash + ", equityList=" + equityList + ", fundList=" + fundList + ", etcList=" + etcList + '}';
    }
    
    
}
