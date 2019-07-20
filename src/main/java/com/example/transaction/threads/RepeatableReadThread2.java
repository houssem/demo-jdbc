package com.example.transaction.threads;

import com.example.transaction.service.TransactionIsolationService;

public class RepeatableReadThread2 implements Runnable {
    private TransactionIsolationService transactionIsolationService;

    public RepeatableReadThread2(TransactionIsolationService transactionIsolationService) {
        this.transactionIsolationService = transactionIsolationService;
    }

    @Override
    public void run() {
        try {
            transactionIsolationService.USER2RepeatableRead();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
