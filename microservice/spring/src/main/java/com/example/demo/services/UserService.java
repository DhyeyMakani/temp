package com.example.demo.services;

import com.example.demo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public User saveUser(User user) {
        return mongoTemplate.save(user, "my_collection");
    }
}