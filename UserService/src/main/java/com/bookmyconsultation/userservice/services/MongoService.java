package com.bookmyconsultation.userservice.services;

import com.bookmyconsultation.userservice.model.entity.User;

public interface MongoService {

    User saveOrUpdateUser(User usr);

}
