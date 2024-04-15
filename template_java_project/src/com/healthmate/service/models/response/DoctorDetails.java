package com.healthmate.service.models.response;

import com.healthmate.service.dynamodb.models.TimeRange;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDetails {
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private String department;
    private String about;
    private List<TimeRange> availableSlots;
}
