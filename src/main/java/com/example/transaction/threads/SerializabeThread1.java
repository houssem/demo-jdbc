package com.example.transaction.threads;

import com.example.transaction.service.TransactionIsolationService;

public class SerializabeThread1 implements Runnable {

    private TransactionIsolationService transactionIsolationService;

    public SerializabeThread1(TransactionIsolationService transactionIsolationService) {
        this.transactionIsolationService = transactionIsolationService;
    }

    @Override
    public void run() {
        try {
            transactionIsolationService.USER1Serializable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
