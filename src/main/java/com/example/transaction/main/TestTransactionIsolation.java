package com.example.transaction.main;

import com.example.config.ApplicationConfig;
import com.example.transaction.service.TransactionIsolationService;
import com.example.transaction.threads.*;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

//@Import({ApplicationConfig.class})
public class TestTransactionIsolation {
    /*public static void main(String [] args) {
        ApplicationContext context = SpringApplication.run(TestTransactionIsolation.class);

        TransactionIsolationService transactionIsolationService = context.getBean("transactionIsolationService", TransactionIsolationService.class);


//        Thread thread1_C = new Thread(new ReadUnComittedThread1(transactionIsolationService));
//        thread1_C.start();
//
//        Thread thread2_C = new Thread(new ReadUnComittedThread2(transactionIsolationService));
//        thread2_C.start();


        //Thread thread1_C = new Thread(new ReadComittedThread1(transactionIsolationService));
        //thread1_C.start();

        //Thread thread2_C = new Thread(new ReadComittedThread2(transactionIsolationService));
        //thread2_C.start();


        Thread thread1_C = new Thread(new RepeatableReadThread1(transactionIsolationService));
        thread1_C.start();

        Thread thread2_C = new Thread(new RepeatableReadThread2(transactionIsolationService));
        thread2_C.start();

    }*/

}
