package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.AppointmentDao;
import com.healthmate.service.dynamodb.UserDao;
import com.healthmate.service.dynamodb.models.Appointment;
import com.healthmate.service.dynamodb.models.User;
import com.healthmate.service.exceptions.UserNotFoundException;
import com.healthmate.service.models.requests.GetAppointmentRequest;
import com.healthmate.service.models.response.GetAppointmentResponse;

import javax.inject.Inject;
import java.util.List;

public class GetAppointmentActivity implements RequestHandler<GetAppointmentRequest, GetAppointmentResponse> {
    private UserDao userDao;
    private AppointmentDao appointmentDao;
    @Inject
    public GetAppointmentActivity(UserDao userDao, AppointmentDao appointmentDao) {
        this.userDao = userDao;
        this.appointmentDao = appointmentDao;
    }
    @Override
    public GetAppointmentResponse handleRequest(final GetAppointmentRequest getAppointmentRequest, Context context) {
        User user = userDao.getUser(getAppointmentRequest.getEmail());
        if (user == null) {
            throw new UserNotFoundException();
        }
        return new GetAppointmentResponse(getAppointmentRequest.getEmail(),appointmentDao.getAppointmentsByUser(user.getUserId()));

    }

}
