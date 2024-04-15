package com.healthmate.service.dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import org.joda.time.DateTime;

@DynamoDBTable(tableName = "appointments")
public class Appointment {
    private String email;
    private String doctorId;
    private String hospitalId;
    private String appointmentTime;  //(appointmentDate/time)Eg:2024-04-08/10:15:00
    private String status;
    @DynamoDBHashKey(attributeName = "user_id")
    public String getEmail() {
        return email;
    }

    public void setUserId(String email) {
        this.email = email;
    }
    @DynamoDBAttribute(attributeName = "doctor_id")
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }
    @DynamoDBAttribute(attributeName = "hospital_id")
    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    @DynamoDBRangeKey(attributeName = "appointment_time")
    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String time) {
        this.appointmentTime = time;
    }
    @DynamoDBAttribute
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
