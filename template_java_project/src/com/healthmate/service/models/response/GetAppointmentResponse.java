package com.healthmate.service.models.response;

import com.healthmate.service.dynamodb.models.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetAppointmentResponse {
    private String email;
    private List<Appointment> appointmentList;
}
