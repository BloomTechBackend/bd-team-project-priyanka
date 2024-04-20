package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.AppointmentDao;
import com.healthmate.service.dynamodb.UserDao;
import com.healthmate.service.dynamodb.models.Appointment;
import com.healthmate.service.dynamodb.models.Availability;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.dynamodb.models.User;
import com.healthmate.service.exceptions.AppointmentAlreadyExistException;
import com.healthmate.service.exceptions.DoctorNotFoundException;
import com.healthmate.service.exceptions.UserNotFoundException;
import com.healthmate.service.models.requests.CancelAppointmentRequest;
import com.healthmate.service.models.requests.CreateAppointmentRequest;
import com.healthmate.service.models.response.CancelAppointmentResponse;
import com.healthmate.service.models.response.CreateAppointmentResponse;
import com.healthmate.service.util.JwtUtils;

import javax.inject.Inject;

public class CreateAppointmentActivity implements RequestHandler<CreateAppointmentRequest, CreateAppointmentResponse> {
    private AppointmentDao appointmentDao;
    private GetDoctorAvailability getDoctorAvailability;
    @Inject
    public CreateAppointmentActivity(AppointmentDao appointmentDao, GetDoctorAvailability getDoctorAvailability) {
        this.appointmentDao = appointmentDao;
        this.getDoctorAvailability = getDoctorAvailability;
    }
    @Override
    public CreateAppointmentResponse handleRequest(final CreateAppointmentRequest createAppointmentRequest, Context context) {
        if(!JwtUtils.validateToken(createAppointmentRequest.getToken())) {
            throw new IllegalArgumentException("Invalid User Credentials.Please Login");
        }
        Doctor doctor = getDoctorAvailability.getDoctorDao().getDoctor(createAppointmentRequest.getDoctorId());
        if (doctor == null) {
            throw new DoctorNotFoundException();
        }
        Appointment appointment = appointmentDao.getAppointment(JwtUtils.extractEmail(createAppointmentRequest.getToken()), createAppointmentRequest.getAppointmentTime());
        if (appointment != null) {
            throw new AppointmentAlreadyExistException("Appointment already exists");
        }
        Appointment appointment1 = new Appointment();
        appointment1.setDoctorId(doctor.getDoctorId());
        appointment1.setUserId(JwtUtils.extractEmail(createAppointmentRequest.getToken()));
        appointment1.setAppointmentTime(createAppointmentRequest.getAppointmentTime());
        getDoctorAvailability.updateAvailability(appointment1, Availability.AVAILABLE, Availability.BOOKED);

        appointmentDao.saveAppointment(appointment1);

        return new CreateAppointmentResponse("Appointment got created successfully");
    }

}
