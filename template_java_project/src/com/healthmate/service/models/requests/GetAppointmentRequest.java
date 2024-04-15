package com.healthmate.service.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAppointmentRequest {
    private String email;
    private DateTime appointmentTime;
    private GetAppointmentRequest(Builder builder) {
        this.email = builder.email;
        this.appointmentTime = builder.appointmentTime;
    }
    public Builder builder() {
        return new Builder();
    }


    public static class  Builder {
        private String email;
        private DateTime appointmentTime;

        public Builder() {

        }
        public Builder setAppointmentTime(DateTime appointmentTime) {
            this.appointmentTime = appointmentTime;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public  GetAppointmentRequest build() {
            return new GetAppointmentRequest(this);
        }


    }
}
