package com.healthmate.service.models.requests;

import com.healthmate.service.dynamodb.models.LocalDateTimeMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.joda.time.DateTime;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CancelAppointmentRequest {
    private String token;
    private String appointmentTime;
    private String appointmentDate;
    private String bookingTime;
}
