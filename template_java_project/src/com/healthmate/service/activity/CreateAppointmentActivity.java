package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.AppointmentDao;
import com.healthmate.service.dynamodb.UserDao;
import com.healthmate.service.dynamodb.models.Appointment;
import com.healthmate.service.dynamodb.models.User;
import com.healthmate.service.exceptions.UserNotFoundException;
import com.healthmate.service.models.requests.CancelAppointmentRequest;
import com.healthmate.service.models.requests.CreateAppointmentRequest;
import com.healthmate.service.models.response.CancelAppointmentResponse;

import javax.inject.Inject;

public class CreateAppointmentActivity implements RequestHandler<CreateAppointmentRequest, CreateAppointmentResponse> {
    private UserDao userDao;
    private AppointmentDao appointmentDao;
    private GetDoctorAvailability getDoctorAvailability;
    @Inject
    public CreateAppointmentActivity(UserDao userDao, AppointmentDao appointmentDao, GetDoctorAvailability getDoctorAvailability) {
        this.userDao = userDao;
        this.appointmentDao = appointmentDao;
        this.getDoctorAvailability = getDoctorAvailability;
    }
    @Override
    public CreateAppointmentResponse handleRequest(final CreateAppointmentRequest createAppointmentRequest, Context context) {
        User user = userDao.getUser(createAppointmentRequest.getEmail());
        if (user == null) {
            throw new UserNotFoundException();
        }
        Appointment appointment = appointmentDao.getAppointment(cancelAppointmentRequest.getEmail(), cancelAppointmentRequest.getAppointmentTime());
        getDoctorAvailability.updateAvailability(appointment);
        appointmentDao.deleteAppointment(appointment);

        return new CancelAppointmentResponse("Appointment got cancelled successfully");

    }

}
