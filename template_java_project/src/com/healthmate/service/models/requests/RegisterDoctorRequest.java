package com.healthmate.service.models.requests;

import com.healthmate.service.dynamodb.models.Day;
import com.healthmate.service.dynamodb.models.TimeRange;

import java.util.List;
import java.util.Map;

public class RegisterDoctorRequest {
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String about;
    private  String contact;
    private Map<Day, List<TimeRange>> schedule;
    public RegisterDoctorRequest() {}

    public RegisterDoctorRequest(String licenseNumber, String email, String firstName, String lastName, String department, String about, String contact, Map<Day, List<TimeRange>> schedule) {
        this.licenseNumber = licenseNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.about = about;
        this.contact = contact;
        this.schedule = schedule;
        this.email = email;
    }

    private RegisterDoctorRequest(Builder builder) {
        this.about = builder.about;
        this.department = builder.department;
        this.schedule = builder.schedule;
        this.licenseNumber = builder.licenseNumber;
        this.contact = builder.contact;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }

    public String getAbout() {
        return about;
    }

    public String getContact() {
        return contact;
    }

    public Map<Day, List<TimeRange>> getSchedule() {
        return schedule;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setSchedule(Map<Day, List<TimeRange>> schedule) {
        this.schedule = schedule;
    }
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private String licenseNumber;
        private String firstName;
        private String lastName;
        private String department;
        private String about;
        private  String contact;
        private Map<Day, List<TimeRange>> schedule;
        public Builder setLicenseNumber(String licenseNumber) {
            this.licenseNumber = licenseNumber;
            return this;
        }
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }
        public Builder setAbout(String about) {
            this.about = about;
            return this;
        }
        public Builder setContact(String contact) {
            this.contact = contact;
            return this;
        }
        public  Builder setSchedule(Map<Day,List<TimeRange>> schedule) {
            this.schedule = schedule;
            return this;
        }
        public RegisterDoctorRequest build() {
            return new RegisterDoctorRequest(this);
        }

    }
}
