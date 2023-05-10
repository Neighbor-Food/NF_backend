package com.uracle.nfbackend.nfbackend.service;

import java.util.concurrent.ExecutionException;

import com.uracle.nfbackend.nfbackend.entity.CRUD;

public interface CRUDService {
    public String createCRUD(CRUD crud) throws ExecutionException, InterruptedException;
    public CRUD getCRUD(String document_id) throws ExecutionException, InterruptedException;
    public String updateCRUD(CRUD crud);
    public String deleteCRUD(String document_id);
}
