package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.DoctorDao;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.exceptions.InvalidAttributeValueException;
import com.healthmate.service.models.requests.CreateObjectFromRequest;
import com.healthmate.service.models.requests.RegisterDoctorRequest;
import com.healthmate.service.util.DoctorServiceUtils;

import javax.inject.Inject;

public class RegisterDoctorActivity implements RequestHandler<RegisterDoctorRequest, Doctor> {
    private DoctorDao doctorDao;
    @Inject
    public RegisterDoctorActivity(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public Doctor handleRequest(final RegisterDoctorRequest registerDoctorRequest, Context context) {
        if (!DoctorServiceUtils.isValidCreateRequest(registerDoctorRequest)) {
            throw new InvalidAttributeValueException("Detailed info is not given to register doctor");
        }
        Doctor doctor = CreateObjectFromRequest.createdoctor(registerDoctorRequest);
        PopulateDoctorAvailability.setAvailability(doctor);
        return doctorDao.save(doctor);
    }
}
