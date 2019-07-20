package com.example.transaction.threads;

import com.example.transaction.service.TransactionIsolationService;

public class ReadComittedThread1 implements Runnable {

    private  TransactionIsolationService transactionIsolationService;

    public ReadComittedThread1(TransactionIsolationService transactionIsolationService) {
        this.transactionIsolationService = transactionIsolationService;
    }

    @Override
    public void run() {
        try {
            transactionIsolationService.USER1ReadComitted();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
