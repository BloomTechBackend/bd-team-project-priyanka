package com.healthmate.service.models.requests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterHospitalRequest {
    private String hospitalId;
    private String name;
    private String address;
    private String pincode;


    private Map<String, List<String>> doctorsInDept = new HashMap<>();
    private  String contact;
    public  RegisterHospitalRequest(){}

    public RegisterHospitalRequest(String hospitalId, String name, String address, String pincode, Map<String, List<String>> doctorsInDept, String contact) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.address = address;
        this.pincode = pincode;
        this.doctorsInDept = doctorsInDept;
        this.contact = contact;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Map<String, List<String>> getDoctorsInDept() {
        return doctorsInDept;
    }

    public void setDoctorsInDept(Map<String, List<String>> doctorsInDept) {
        this.doctorsInDept = doctorsInDept;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
