package com.healthmate.service.models.requests;

import com.healthmate.service.dynamodb.models.LocalDateTimeMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelAppointmentRequest {
    private String email;
    private String appointmentTime;
    private CancelAppointmentRequest(Builder builder) {
        this.email = builder.email;
        this.appointmentTime = builder.appointmentTime;
    }
    public Builder builder() {
        return new Builder();
    }
    public static class  Builder {
        private String email;
        private String appointmentTime;  //(appointmentdate//time)Eg:2024-04-07/10:15:00

        public Builder() {

        }
        public Builder setAppointmentTime(String appointmentTime) {
            this.appointmentTime = appointmentTime;
            return this;
        }
        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }
        public CancelAppointmentRequest build() {
            return new CancelAppointmentRequest(this);
        }
    }
}
