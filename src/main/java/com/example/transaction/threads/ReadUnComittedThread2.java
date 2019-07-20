package com.example.transaction.threads;

import com.example.transaction.service.TransactionIsolationService;

public class ReadUnComittedThread2 implements Runnable {

    private TransactionIsolationService transactionIsolationService;

    public ReadUnComittedThread2(TransactionIsolationService transactionIsolationService) {
        this.transactionIsolationService = transactionIsolationService;
    }

    @Override
    public void run() {
        try {
            transactionIsolationService.USER2ReadUnComitted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
