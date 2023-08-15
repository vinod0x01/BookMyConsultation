package com.bookmyconsultation.ratingservice.controller;


import com.bookmyconsultation.ratingservice.model.dto.RatingDTO;
import com.bookmyconsultation.ratingservice.model.entity.Rating;
import com.bookmyconsultation.ratingservice.model.entity.MongoRatingRepository;
import com.bookmyconsultation.ratingservice.services.KafkaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/ratings")
public class RatingController {

    @Autowired
    public MongoRatingRepository ratingRepositoryService;

    @Autowired
    public KafkaService kafkaService;


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newRating(@RequestBody RatingDTO ratingDTO) throws IOException {

        Rating newRating = new Rating(ratingDTO);
        Rating savedRating = ratingRepositoryService.save(newRating);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(savedRating);
        kafkaService.produceMessage("message", savedRating.getId(), json);

        return new ResponseEntity<Rating>(savedRating, HttpStatus.OK) ;
    }


}
