package com.bookmyconsultation.doctorservice.controller;


import com.bookmyconsultation.doctorservice.model.dto.ApprovalDTO;
import com.bookmyconsultation.doctorservice.model.dto.DoctorDTO;
import com.bookmyconsultation.doctorservice.model.entity.Doctor;
import com.bookmyconsultation.doctorservice.model.entity.DoctorRepository;
import com.bookmyconsultation.doctorservice.model.entity.S3Repository;
import com.bookmyconsultation.doctorservice.service.KafkaServiceImpl;
import com.bookmyconsultation.doctorservice.service.MongoService;
import com.bookmyconsultation.doctorservice.util.ValidationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/doctors")
public class DoctorServiceController {

    @Autowired
    private MongoService mongoservice;

    @Autowired
    private S3Repository s3Repository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private KafkaServiceImpl kafkaService;


    @GetMapping("/healthCheck")
    public ResponseEntity healthCheck() {

        return new ResponseEntity("Hello", HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newDoctorRegistration(@RequestBody DoctorDTO doctorObj) throws IOException, JsonProcessingException, JSONException {

        List<String> errorList = ValidationUtil.validateInput(doctorObj);
        if (!CollectionUtils.isEmpty(errorList)) {
            JSONObject errObj = new JSONObject();
            errObj.put("errorCode", "ERR_INVALID_INPUT");
            errObj.put("errorMessage", "Invalid input. Parameter Name:");
            errObj.put("errorFields", errorList);
            return new ResponseEntity(errObj.toString(), HttpStatus.BAD_REQUEST);
        }

        Doctor newDoc = new Doctor(doctorObj);
        Doctor savedDoc = mongoservice.saveOrUpdateDoctor(newDoc);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(savedDoc);
        kafkaService.produceMessage("new_doc", newDoc.getId(), json);

        return new ResponseEntity(doctorObj, HttpStatus.OK);
    }

    /*
    Endpoint 2: This endpoint is responsible for uploading the documents to an S3 bucket by the doctor.

    URI: /doctors/{doctorId}/document
    */

    @PostMapping("/{doctorId}/documents")
    public ResponseEntity uploadFiles(@PathVariable("doctorId") String doctorId, @RequestParam MultipartFile[] files) throws IOException
    {
        for(MultipartFile file: files){
            s3Repository.uploadFiles(doctorId, file);
        }

        return new ResponseEntity<String>("File(s) uploaded Successfully.", HttpStatus.OK);
    }

    /*
     Endpoint 3: This endpoint is responsible for approving the doctor’s registration request.

    URI: /doctors/{doctorId}/approve

     */
    @PutMapping("/{doctorId}/approve")
    public ResponseEntity approveDoctor(@PathVariable("doctorId") String doctorId, @RequestBody ApprovalDTO input) throws IOException {
        Doctor doctor = doctorRepository.findDoctorById(doctorId);

        if (Objects.isNull(doctor)) {
            String errorOutput = "{\"errorCode\" : \"ERR_INVALID_DOCTOR_ID\" }";
            return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
        }

        doctor.approveDoctor(input.getApprovedBy(), input.getApproverComments());
        Doctor savedDoc = mongoservice.saveOrUpdateDoctor(doctor);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(savedDoc);

        kafkaService.produceMessage("doc_approval", doctorId, json);
        return new ResponseEntity(doctor, HttpStatus.OK);
    }


    /*
    Endpoint 4: This endpoint is responsible for rejecting the registration of the doctor.

    URI: /doctors/{doctorId}/reject
     */
    @PutMapping("/{doctorId}/reject")
    public ResponseEntity rejectDoctor(@PathVariable("doctorId") String doctorId, @RequestBody ApprovalDTO input) throws IOException {

        Doctor doc = doctorRepository.findDoctorById(doctorId);

        if (Objects.isNull(doc)) {
            String errorOutput = "{\"errorCode\" : \"ERR_INVALID_DOCTOR_ID\" }";
            return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
        }

        doc.rejectDoctor(input.getApprovedBy(), input.getApproverComments());
        Doctor savedDoc = mongoservice.saveOrUpdateDoctor(doc);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(savedDoc);

        kafkaService.produceMessage("doc_rejection", doctorId, json);
        //TODO: after naresh completes, email needs to be sent
        return new ResponseEntity(doc, HttpStatus.OK);
    }


    /*
    Endpoint 5: This endpoint is responsible for returning the list of 20 doctors sorted by ratings.
    */
    @GetMapping
    public ResponseEntity<List<Doctor>> getDoctorByStatusAndSpeciality(
            @RequestParam(name = "status", required = false) String status,
            @RequestParam(name = "specialization", required = false) String speciality) {

        if (Objects.isNull(status)) {
            String errorOutput = "{\"errorCode\" : \"ERR_INVALID_STATUS\" }";
            return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
        }

        List<Doctor> docs = null;

        if (Objects.isNull(speciality)) {
            docs = mongoservice.findByStatus(status);
        } else {
            docs = mongoservice.findAllByStatusAndSpecialization(status, speciality);
        }

        return new ResponseEntity<List<Doctor>>(docs, HttpStatus.OK);
    }

    /*
    Endpoint 6: This endpoint is responsible for returning the details of the doctor based on the doctor ID.

    URI: /doctors/{doctorId}
    */
    @GetMapping("/{doctorId}")
    public ResponseEntity getDoctorById(@PathVariable("doctorId") String doctorId) {
        Doctor doc = doctorRepository.findDoctorById(doctorId);

        if (Objects.isNull(doc)) {
            String errorOutput = "{\"errorCode\" : \"ERR_INVALID_DOCTOR_ID\" }";
            return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(doc, HttpStatus.OK);
    }
}
