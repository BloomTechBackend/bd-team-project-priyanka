package com.healthmate.service.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAppointmentRequest {
    private String doctorId;
    private String email;
    private String appointmentTime;
    private CreateAppointmentRequest(CreateAppointmentRequest.Builder builder) {
        this.email = builder.email;
        this.appointmentTime = builder.appointmentTime;
        this.doctorId = builder.doctorId;
    }
    public CreateAppointmentRequest.Builder builder() {
        return new CreateAppointmentRequest.Builder();
    }
    public static class  Builder {
        private String email;
        private String appointmentTime;//(appointmentdate//time)Eg:2024-04-07/10:15:00
        private String doctorId;

        public Builder() {

        }
        public CreateAppointmentRequest.Builder setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
            return this;
        }
        public CreateAppointmentRequest.Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public CreateAppointmentRequest.Builder setDoctorId(String doctorI) {
            this.doctorId = doctorId;
            return this;
        }
        public CreateAppointmentRequest build() {
            return new CreateAppointmentRequest(this);
        }
    }
    
}
