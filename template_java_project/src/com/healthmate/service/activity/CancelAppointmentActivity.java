package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.AppointmentDao;
import com.healthmate.service.dynamodb.UserDao;
import com.healthmate.service.dynamodb.models.Appointment;
import com.healthmate.service.dynamodb.models.Availability;
import com.healthmate.service.dynamodb.models.User;
import com.healthmate.service.exceptions.InvalidAttributeException;
import com.healthmate.service.exceptions.UserNotFoundException;
import com.healthmate.service.models.requests.CancelAppointmentRequest;
import com.healthmate.service.models.response.CancelAppointmentResponse;

import javax.inject.Inject;
import javax.management.AttributeNotFoundException;
import java.util.List;

public class CancelAppointmentActivity implements RequestHandler<CancelAppointmentRequest, CancelAppointmentResponse> {
    private UserDao userDao;
    private AppointmentDao appointmentDao;
    private GetDoctorAvailability getDoctorAvailability;
    @Inject
    public CancelAppointmentActivity(UserDao userDao, AppointmentDao appointmentDao, GetDoctorAvailability getDoctorAvailability) {
        this.userDao = userDao;
        this.appointmentDao = appointmentDao;
        this.getDoctorAvailability = getDoctorAvailability;
    }
    @Override
    public CancelAppointmentResponse handleRequest(final CancelAppointmentRequest cancelAppointmentRequest, Context context) {
        User user = userDao.getUser(cancelAppointmentRequest.getEmail());
        if (user == null) {
            throw new UserNotFoundException();
        }
        Appointment appointment = appointmentDao.getAppointment(cancelAppointmentRequest.getEmail(), cancelAppointmentRequest.getAppointmentTime());
        if (appointment == null) {
            throw new InvalidAttributeException("Appointment not exist");
        }
        getDoctorAvailability.updateAvailability(appointment, Availability.BOOKED, Availability.AVAILABLE);
        appointmentDao.deleteAppointment(appointment);

        return new CancelAppointmentResponse("Appointment got cancelled successfully");

    }


}
