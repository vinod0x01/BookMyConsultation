package com.bookmyconsultation.appointmentservice.controller;

import com.bookmyconsultation.appointmentservice.model.dto.AppointmentReturnDTO;
import com.bookmyconsultation.appointmentservice.model.dto.AvailabilityDTO;
import com.bookmyconsultation.appointmentservice.model.dto.AvailabilityReturnDTO;
import com.bookmyconsultation.appointmentservice.model.entity.AppointmentEntity;
import com.bookmyconsultation.appointmentservice.model.entity.AvailabilityEntity;
import com.bookmyconsultation.appointmentservice.model.entity.Prescription;
import com.bookmyconsultation.appointmentservice.exceptions.PaymentException;
import com.bookmyconsultation.appointmentservice.service.AppointmentService;
import com.bookmyconsultation.appointmentservice.service.AvailabilityService;
import com.bookmyconsultation.appointmentservice.model.dto.AppointmentDTO;
import com.bookmyconsultation.appointmentservice.service.KafkaServiceImpl;
import com.bookmyconsultation.appointmentservice.service.MainService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class AppointmentServiceController {

    @Autowired
    public MainService mainService;

    @Autowired
    public AppointmentService appointmentService;

    @Autowired
    public AvailabilityService availabilityService;

    @Autowired
    private KafkaServiceImpl kafkaService;


    /*Endpoint 1: This endpoint is responsible for updating the availability of the doctors.

    URI: /doctor/{doctorId}/availability*/
    @PostMapping(value = "doctor/{doctorId}/availability", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newAvailability(@PathVariable("doctorId") String doctorId,
                                         @RequestBody AvailabilityDTO availabilityDTO) {


        List<AvailabilityEntity> availabilityEntities= availabilityService.getAvailabilityByDoctorId(doctorId);

        //Deletes all availabilities
        for (AvailabilityEntity savedAvailability : availabilityEntities) {
            availabilityService.deleteAvailability(savedAvailability.getId());
        }

        boolean ret = mainService.saveAvailability(doctorId, availabilityDTO);
        if(ret)
        {
            AvailabilityReturnDTO newDTO = new AvailabilityReturnDTO();
            newDTO.setDoctorId(doctorId);
            newDTO.setAvailabilityMap(availabilityDTO.getAvailabilityMap());
            return new ResponseEntity<AvailabilityReturnDTO>(newDTO, HttpStatus.OK);
        }

        String errorOutput = "{\"errorCode\" : \"ERR_INVALID_DOCTOR_ID\" }";
        return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
    }

 /*   Endpoint 2: This endpoint is responsible for returning the availability of the doctors.

    URI: /doctor/{doctorId}/availability*/

    @GetMapping(value = "doctor/{doctorId}/availability", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAvailability(@PathVariable("doctorId") String doctorId) {

        AvailabilityReturnDTO returnDTO = availabilityService.findAllAvailabilityByDoctorId(doctorId);

        return new ResponseEntity(returnDTO, HttpStatus.OK);
    }

    /*Endpoint 3: This endpoint is responsible for booking an appointment.

      URI: /appointments*/
    @PostMapping(value = "appointments", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newAppointment(@RequestBody AppointmentDTO inputDTO) throws IOException {

        AppointmentEntity appointment = mainService.saveAppointment(inputDTO);


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(appointment);

        kafkaService.produceMessage("app_confirm", appointment.getAppointment_id(), json);
        return new ResponseEntity(appointment, HttpStatus.OK);
    }


    /*Endpoint 4: This endpoint is responsible for retrieving the details of an appointment.

    URI: /appointments/{appointmentId}*/
    @GetMapping(value = "appointments/{appId}", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fetchAppointment(@PathVariable("appId") String appId) {

        AppointmentEntity appointment = mainService.findById(appId);

        if(appointment == null)
        {
            String errorOutput = "{\"errorCode\" : \"ERR_INVALID_APPOINTMENT_ID\" }";
            return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(appointment, HttpStatus.OK);
    }

   /* Endpoint 5: This endpoint is responsible for retrieving the details of all the appointments corresponding to a userId.

    URI: /users/{userId}/appointments*/
    @GetMapping(value = "users/{userId}/appointments")
    public ResponseEntity fetchAppointmentByUserId(@PathVariable("userId") String userId) {

        List<AppointmentEntity> appointment = appointmentService.findByUserId(userId);

        if(appointment == null)
        {
            String errorOutput = "{\"errorCode\" : \"ERR_INVALID_APPOINTMENT\" }";
            return new ResponseEntity(errorOutput, HttpStatus.BAD_REQUEST);
        }

        List<AppointmentReturnDTO> returnDTO = new ArrayList<>();

        for(AppointmentEntity e : appointment)
        {
            returnDTO.add(new AppointmentReturnDTO(e));
        }

        return new ResponseEntity(returnDTO, HttpStatus.OK);
    }

    /*Endpoint 6: This endpoint is responsible for sending the prescriptions for the appointment.
    URI: /prescriptions*/
    @PostMapping(value = "prescriptions", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity newPrescription(@RequestBody Prescription prescription) throws IOException {

        prescription =  mainService.newPrescription(prescription);

        if(prescription == null)
            throw new PaymentException("ERROR_IN_CONNECTION", 400);



        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(prescription);

        kafkaService.produceMessage("prescription", prescription.getAppointmentId(), json);

        return new ResponseEntity(prescription, HttpStatus.OK);
    }


}
