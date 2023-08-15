package com.bookmyconsultation.userservice.model.entity;

import com.bookmyconsultation.userservice.model.dto.UserInfoDTO;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "User")
public class User {

    @Id
    private String id;

    private String firstName;
    private String lastName;
    private String mobile;
    private String dob;
    private String emailId;
    private String createdDate;

    public User() {}

    public User(User usr)
    {
        this.id = usr.getId();
        this.firstName = usr.getFirstName();
        this.lastName = usr.getLastName();
        this.mobile = usr.getMobile();
        this.dob = usr.getDob();
        this.emailId = usr.getEmailId();
        this.createdDate = usr.getCreatedDate();
    }

    public User(UserInfoDTO usr)
    {
        this.id = usr.getId();
        this.firstName = usr.getFirstName();
        this.lastName = usr.getLastName();
        this.mobile = usr.getMobile();
        this.dob = usr.getDob();
        this.emailId = usr.getEmailId();
        this.createdDate = usr.getCreatedDate();
    }

    public User(String id, String firstName, String lastName, String mobile, String dob, String emailId, String createdDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobile = mobile;
        this.dob = dob;
        this.emailId = emailId;
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(mobile, user.mobile) && Objects.equals(dob, user.dob) && Objects.equals(emailId, user.emailId) && Objects.equals(createdDate, user.createdDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, mobile, dob, emailId, createdDate);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dob='" + dob + '\'' +
                ", emailId='" + emailId + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
