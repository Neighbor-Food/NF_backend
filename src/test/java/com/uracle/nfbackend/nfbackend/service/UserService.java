package com.uracle.nfbackend.nfbackend.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.uracle.nfbackend.nfbackend.entity.User;

public interface UserService {
    public List<User> getUsers() throws ExecutionException, InterruptedException;
}
