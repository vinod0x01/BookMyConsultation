package com.bookmyconsultation.userservice.services;

import com.bookmyconsultation.userservice.model.entity.User;
import com.bookmyconsultation.userservice.model.entity.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveOrUpdateUser(User usr){
        return userRepository.save(usr);
    }


}
