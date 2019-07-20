package com.example.transaction.threads;

import com.example.transaction.service.TransactionIsolationService;

public class RepeatableReadThread1 implements Runnable {

    private TransactionIsolationService transactionIsolationService;

    public RepeatableReadThread1(TransactionIsolationService transactionIsolationService) {
        this.transactionIsolationService = transactionIsolationService;
    }

    @Override
    public void run() {
        try {
            transactionIsolationService.USER1RepeatableRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
