package com.bookmyconsultation.userservice.controller;

import com.bookmyconsultation.userservice.model.dto.UserInfoDTO;
import com.bookmyconsultation.userservice.model.entity.S3Repository;
import com.bookmyconsultation.userservice.model.entity.User;
import com.bookmyconsultation.userservice.model.entity.UserRepository;
import com.bookmyconsultation.userservice.services.KafkaServiceImpl;
import com.bookmyconsultation.userservice.services.MongoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value ="/users")
public class UserServiceController {

    @Autowired
    private MongoService mongoservice;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private S3Repository s3Repository ;

    @Autowired
    private KafkaServiceImpl kafkaService;

    /*Endpoint 1: This endpoint is responsible for collecting information about the user.

      URI: /users*/
    @PostMapping()
    public ResponseEntity newUserRegistration(@RequestBody UserInfoDTO inputDTO) throws IOException {
        User newUser =  new User(inputDTO);
        User savedUser = mongoservice.saveOrUpdateUser(newUser);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(savedUser);
        kafkaService.produceMessage("new_user",savedUser.getId(),json);
        return new ResponseEntity(inputDTO, HttpStatus.OK);
    }

    /*Endpoint 2: This endpoint is responsible for getting information about the user.

     URI: /users/{userID}*/
    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable("userId") String userId)
    {
        User usr = userRepository.findUserById(userId);

        System.out.println(usr);

        if(usr == null)
        {
            String errorOutput = "{\"errorCode\" : \"ERR_INVALID_USER_ID\" }";
            return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(usr, HttpStatus.OK);
    }

    /*Endpoint 3: File upload endpoint: /users/{id}/documents. A POST request will be sent to this endpoint to upload the files.*/
    @PostMapping("/{userId}/documents")
    public ResponseEntity uploadFiles(@PathVariable("userId") String userId, @RequestParam MultipartFile[] files) throws IOException
    {
        for(MultipartFile file: files){
            s3Repository.uploadFiles(userId, file);
        }

        return new ResponseEntity("File(s) uploaded Successfully", HttpStatus.OK);

    }


}
