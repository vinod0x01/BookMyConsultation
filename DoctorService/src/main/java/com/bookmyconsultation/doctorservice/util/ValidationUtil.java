package com.bookmyconsultation.doctorservice.util;

import com.bookmyconsultation.doctorservice.model.dto.DoctorDTO;
import org.apache.commons.lang.BooleanUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class ValidationUtil {

    public static List<String> validateInput(DoctorDTO info){

        List<String> errorList = new ArrayList<>();

        if(info.getFirstName().length() > 20 || info.getFirstName().length()<1)
            errorList.add("First Name");

        if(info.getLastName().length() > 20)
            errorList.add("Last Name");

        if(info.getMobile().length() != 10 || !info.getMobile().matches("[0-9]+"))
            errorList.add("Mobile");

        String emailRegex = "^(.+)@(.+)$";

        Pattern emailPattern = Pattern.compile(emailRegex);

        if(BooleanUtils.isNotTrue(emailPattern.matcher(info.getEmailId()).matches()))
            errorList.add("Email Id");

        Pattern panPattern = Pattern.compile("[A-Z]{5}[0-9]{4}[A-Z]{1}");

        if(BooleanUtils.isNotTrue(panPattern.matcher(info.getPan()).matches()))
            errorList.add("PAN");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        sdf.setLenient(false);
        try {
            Date d1 = sdf.parse(info.getDob());
        } catch (Exception e) {
            errorList.add("Date Of Birth");
        }

        return errorList;

    }

}
