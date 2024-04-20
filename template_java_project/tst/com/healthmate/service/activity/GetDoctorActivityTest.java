package com.healthmate.service.activity;

import com.healthmate.service.dynamodb.HospitalDao;
import com.healthmate.service.exceptions.InvalidAttributeValueException;
import com.healthmate.service.models.requests.RegisterDoctorRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetDoctorActivityTest {
    private GetNearByDoctorActivity getNearByDoctorActivity;
    @Mock
    private HospitalDao hospitalDao;
    @Mock
    private GetDoctorAvailability getDoctorAvailability;
    @BeforeEach
    public void setUp() {
        initMocks(this);
        getNearByDoctorActivity = new GetNearByDoctorActivity(hospitalDao, getDoctorAvailability);
    }
//    @Test
//    public void handleRequest_invalidLicenseNumber_throwsInvalidAttributeValueException() {
//        String licenseNumber = "";
//        RegisterDoctorRequest request = RegisterDoctorRequest.builder().setLicenseNumber(licenseNumber).build();
//        assertThrows(InvalidAttributeValueException.class, () -> registerDoctorActivity.handleRequest(request, null));
//
//    }
}
