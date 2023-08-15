package com.bookmyconsultation.appointmentservice.service;

import com.bookmyconsultation.appointmentservice.model.dto.AvailabilityDTO;
import com.bookmyconsultation.appointmentservice.model.entity.AppointmentEntity;
import com.bookmyconsultation.appointmentservice.model.entity.AvailabilityEntity;
import com.bookmyconsultation.appointmentservice.model.entity.Prescription;
import com.bookmyconsultation.appointmentservice.model.dto.AppointmentDTO;
import com.bookmyconsultation.appointmentservice.exceptions.PaymentException;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.bookmyconsultation.appointmentservice.model.dto.UserInfoDTO;
import org.springframework.web.client.RestTemplate;

@Service
public class MainServiceImpl implements MainService {

    @Autowired
    private AvailabilityService availabilityService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppointmentService appointmentService;

    @Value("${user.service}")
    private String userService;

    @Autowired
    private EurekaClient eurekaClient;

    public boolean saveAvailability(String doctorId, AvailabilityDTO availabilityDTO)
    {

        Map<String, List<String>> availabilityMap = availabilityDTO.getAvailabilityMap();

        System.out.println(doctorId);

        availabilityMap.forEach((date, timingList) -> {

            for (String time : timingList) {

                AvailabilityEntity availability = new AvailabilityEntity(
                        date, doctorId, false, time);

                availability = availabilityService.saveAvailability(availability);

                System.out.println(availability.toString());
            }

        });

        return true;
    }

    @Override
    public List<AvailabilityEntity> getAppointments(String doctorId) {
        return null;
    }

    @Override
    public AppointmentEntity saveAppointment(AppointmentDTO appointmentDTO) {

        String docId = appointmentDTO.getDoctorId();
        String userId = appointmentDTO.getUserId();

        String url = eurekaClient.getNextServerFromEureka(userService, false).getHomePageUrl();
        String userUri = url +"users/" + appointmentDTO.getUserId();

        UserInfoDTO userDTO;

        HttpHeaders headers = new HttpHeaders();

        headers.set("Authorization", "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhc3dhbnRoIiwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfQURNSU4ifSx7ImF1dGhvcml0eSI6IkFETUlOX0FDQ0VTUyJ9XSwiaWF0IjoxNjkxODQ3NDQ4LCJleHAiOjE2OTE4NjUwMDB9.e54uwaQUBXBlsQkPIPYgfOqkJ0zS-jn_RX0QzUh1ELg");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);



        try {
            ResponseEntity<UserInfoDTO> response = restTemplate.exchange(userUri, HttpMethod.GET, entity, UserInfoDTO.class);
           userDTO = response.getBody();
        }
        catch(Exception e)
        {
            throw new PaymentException("Invalid User", 400);
        }


        if( userDTO == null)
            return null;

        AppointmentEntity appointment = new AppointmentEntity(
                appointmentDTO.getAppointmentDate(),
                appointmentDTO.getDoctorId(),
                appointmentDTO.getTimeSlot(),
                appointmentDTO.getUserId(),
                userDTO.getEmailId(),
                userDTO.getFirstName()+" "+userDTO.getLastName(),
                appointmentDTO.getDoctorName());

        appointment = appointmentService.saveAppointment(appointment);

        return appointment;
    }

    @Override
    public AppointmentEntity findById(String id) {
        return appointmentService.findById(id);
    }

    @Override
    public Prescription newPrescription(Prescription prescription) {

        AppointmentEntity appointment = findById(prescription.getAppointmentId());

        if(appointment == null)
            throw new PaymentException("INVALID_APPOINTMENT_ID", 400);

        if(appointment.getStatus().equals("PAYMENT_PENDING"))
            throw new PaymentException("Prescription cannot be sent since the payment is pending", 400);

        prescription.setEmailId(appointment.getUser_email_id());
        return appointmentService.saveOrUpdatePrescription(prescription);
    }

}
