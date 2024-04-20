package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.AppointmentDao;
import com.healthmate.service.models.requests.GetAppointmentRequest;
import com.healthmate.service.models.response.GetAppointmentResponse;
import com.healthmate.service.util.JwtUtils;

import javax.inject.Inject;

public class GetAppointmentActivity implements RequestHandler<GetAppointmentRequest, GetAppointmentResponse> {
    private AppointmentDao appointmentDao;
    @Inject
    public GetAppointmentActivity(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }
    @Override
    public GetAppointmentResponse handleRequest(final GetAppointmentRequest getAppointmentRequest, Context context) {
        if(!JwtUtils.validateToken(getAppointmentRequest.getToken())) {
            throw new IllegalArgumentException("Invalid User Credentials.Please Login");
        }
        String email = JwtUtils.extractEmail(getAppointmentRequest.getToken());
        return new GetAppointmentResponse(email,appointmentDao.getAppointmentsByUser(email));

    }

}
