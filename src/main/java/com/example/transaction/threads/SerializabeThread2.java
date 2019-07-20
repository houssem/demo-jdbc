package com.example.transaction.threads;

import com.example.transaction.service.TransactionIsolationService;

public class SerializabeThread2 implements Runnable {

    private TransactionIsolationService transactionIsolationService;

    public SerializabeThread2(TransactionIsolationService transactionIsolationService) {
        this.transactionIsolationService = transactionIsolationService;
    }

    @Override
    public void run() {
        try {
            transactionIsolationService.USER2Serializable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
