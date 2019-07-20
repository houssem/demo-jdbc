package com.example.transaction.service;

public interface TransactionIsolationService {

    public void USER1ReadUnComitted() throws Exception;
    public void USER2ReadUnComitted() throws Exception;

    public void USER1ReadComitted() throws Exception;
    public void USER2ReadComitted() throws Exception;

    void USER1RepeatableRead() throws Exception;
    void USER2RepeatableRead() throws Exception;

    void USER1Serializable() throws Exception;
    void USER2Serializable() throws Exception;
}
