package com.example.transaction.threads;

import com.example.transaction.service.TransactionIsolationService;

public class ReadComittedThread2 implements Runnable {

    private TransactionIsolationService transactionIsolationService;

    public ReadComittedThread2(TransactionIsolationService transactionIsolationService) {
        this.transactionIsolationService = transactionIsolationService;
    }

    @Override
    public void run() {
        try {
            transactionIsolationService.USER2ReadComitted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
