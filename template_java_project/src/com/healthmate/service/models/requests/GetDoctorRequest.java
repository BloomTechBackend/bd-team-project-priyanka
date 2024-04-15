package com.healthmate.service.models.requests;

import com.healthmate.service.dynamodb.models.LocalDateMapper;

public class GetDoctorRequest {
    private String pincode;
    private  String department;
    private LocalDateMapper date;
    public GetDoctorRequest(){}
    public GetDoctorRequest(String pincode, String department, LocalDateMapper date) {
        this.pincode = pincode;
        this.department = department;
        this.date = date;
    }

    private GetDoctorRequest(Builder builder) {
        this.pincode = builder.pincode;
        this.date = builder.date;
        this.department = builder.department;
    }
    public String getPincode() {
        return pincode;
    }
    public String getDepartment() {
        return department;
    }
    public LocalDateMapper getDate() {
        return date;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDate(LocalDateMapper date) {
        this.date = date;
    }

    public static class  Builder {
        private String pincode;
        private  String department;
        private LocalDateMapper date;

        public Builder setPincode(String pincode) {
            this.pincode = pincode;
            return this;
        }

        public Builder setDepartment(String department) {
            this.department = department;
            return this;
        }

        public Builder setDate(LocalDateMapper date) {
            this.date = date;
            return this;
        }
        public  GetDoctorRequest build() {
            return new GetDoctorRequest(this);
        }


    }
}
