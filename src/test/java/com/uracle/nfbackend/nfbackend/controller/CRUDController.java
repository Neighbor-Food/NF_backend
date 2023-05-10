package com.uracle.nfbackend.nfbackend.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uracle.nfbackend.nfbackend.entity.CRUD;
import com.uracle.nfbackend.nfbackend.service.CRUDService;

@RestController
public class CRUDController {
    public CRUDService crudService;

    public CRUDController(CRUDService crudService){
        this.crudService = crudService;

    }

    @PostMapping("/create")
    public String createCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException{
        return crudService.createCRUD(crud);
    }

    @GetMapping("/read")
    public CRUD readCRUD(@RequestParam String document_id) throws InterruptedException, ExecutionException{
        return crudService.getCRUD(document_id);
    }

    @PutMapping("/update")
    public String updateCRUD(@RequestBody CRUD crud) throws InterruptedException, ExecutionException{
        return crudService.updateCRUD(crud);
    }

    @DeleteMapping("/delete")
    public String deleteCRUD(@RequestParam String document_id) throws InterruptedException, ExecutionException{
        return crudService.deleteCRUD(document_id);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testGetEndPoint(){
        return ResponseEntity.ok("Test");
    }

}
