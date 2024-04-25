package com.healthmate.service.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAppointmentRequest {
    private String token;
}
