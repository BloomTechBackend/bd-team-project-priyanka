package com.healthmate.service.activity;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.healthmate.service.activity.RegisterDoctorActivity;
import com.healthmate.service.dynamodb.DoctorDao;
import com.healthmate.service.dynamodb.models.Availability;
import com.healthmate.service.dynamodb.models.Day;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.dynamodb.models.LocalDateMapper;
import com.healthmate.service.exceptions.InvalidAttributeValueException;
import com.healthmate.service.models.requests.CreateObjectFromRequest;
import com.healthmate.service.models.requests.RegisterDoctorRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RegisterDoctorActivityTest {

    private RegisterDoctorActivity registerDoctorActivity;
    @Mock
    private DoctorDao doctorDao;
    @BeforeEach
    public void setUp() {
        initMocks(this);
        registerDoctorActivity = new RegisterDoctorActivity(doctorDao);
    }
    @Test
    public void handleRequest_invalidLicenseNumber_throwsInvalidAttributeValueException() {
        String licenseNumber = "";
        RegisterDoctorRequest request = RegisterDoctorRequest.builder().setLicenseNumber(licenseNumber).build();
        assertThrows(InvalidAttributeValueException.class, () -> registerDoctorActivity.handleRequest(request, null));

    }
    @Test
    public void handleRequest_validRequest_getDoctorObject() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("E:\\bloomtech\\project_main\\bd-team-project-priyanka\\template_java_project\\tst\\com\\healthmate\\service\\activity\\data.json")) {
            RegisterDoctorRequest request = gson.fromJson(reader, RegisterDoctorRequest.class);
            when(doctorDao.save(any())).thenAnswer(invocation -> invocation.getArguments()[0]);
            Doctor doctor = registerDoctorActivity.handleRequest(request, null);
            assertEquals(doctor.getDoctorId(), request.getLicenseNumber());
            assertEquals(doctor.getSchedule(), request.getSchedule());
            if (request.getSchedule().size() > 0) {
                assertFalse(doctor.getAvailableSlot().isEmpty());
                for (LocalDateMapper key:doctor.getAvailableSlot().keySet()) {
                    if (request.getSchedule().containsKey(key.getDayOfWeek())) {
                        assertTrue(doctor.getAvailableSlot().get(key).contains(Availability.AVAILABLE));
                        List<Availability> returnedAvailableSlot = doctor.getAvailableSlot().get(key);
                        for (int i =0; i< returnedAvailableSlot.size(); i++) {
                            final Integer seconds = i * 900;
                            if (returnedAvailableSlot.get(i)  == Availability.AVAILABLE) {
                                assertTrue(doctor.getSchedule().get(key.getDayOfWeek()).stream().anyMatch(timeRange -> timeRange.isTimeWithinRange(seconds)));
                            }
                        }
                    }
                }
            }


        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}
