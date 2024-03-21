package com.upload.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.upload.model.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
