package com.example.transaction.threads;

import com.example.transaction.service.TransactionIsolationService;

public class ReadUnComittedThread1 implements Runnable {

    private TransactionIsolationService transactionIsolationService;

    public ReadUnComittedThread1(TransactionIsolationService transactionIsolationService) {
        this.transactionIsolationService = transactionIsolationService;
    }

    @Override
    public void run() {
        try {
            transactionIsolationService.USER1ReadUnComitted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
