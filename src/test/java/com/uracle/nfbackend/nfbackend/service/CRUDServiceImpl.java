package com.uracle.nfbackend.nfbackend.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.uracle.nfbackend.nfbackend.entity.CRUD;

@Service
public class CRUDServiceImpl implements CRUDService{
    @Override
    public String createCRUD(CRUD crud) throws ExecutionException, InterruptedException{
        if (crud.getName() == null || crud.getName().isEmpty()) {
            throw new IllegalArgumentException("Name must be a non-empty String");
        }
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("crud_user").document(crud.getName()).set(crud);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    @Override
    public CRUD getCRUD(String document_id) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("crud_user").document(document_id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot documentSnapshot = future.get();
        CRUD crud;
        if(documentSnapshot.exists()){
            crud = documentSnapshot.toObject(CRUD.class);
            return crud;
        }else{
            return null;
        }
    }
    @Override
    public String updateCRUD(CRUD crud) throws ExecutionException, InterruptedException{
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection("crud_user").document(crud.getName()).set(crud);

        return collectionsApiFuture.get().getUpdateTime().toString();
    }
    @Override
    public String deleteCRUD(String document_id){
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> writeResult = dbFirestore.collection("crud_user").document(document_id).delete();

        return "Success for delete" + document_id ;
    }
}
