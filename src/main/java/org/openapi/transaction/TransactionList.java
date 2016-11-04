/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openapi.transaction;

import java.util.ArrayList;

/**
 *
 * @author heungjae
 */
public class TransactionList {
    
    private ArrayList<Transaction> transaction;

    public TransactionList() {
    }

    public ArrayList<Transaction> getTransaction() {
        return transaction;
    }

    public void setTransaction(ArrayList<Transaction> transaction) {
        this.transaction = transaction;
    }


    
    
}
