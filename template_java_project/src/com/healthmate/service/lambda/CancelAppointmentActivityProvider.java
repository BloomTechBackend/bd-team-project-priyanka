package com.healthmate.service.lambda;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.dynamodb.models.Appointment;
import com.healthmate.service.models.requests.CancelAppointmentRequest;

import java.util.List;

public class CancelAppointmentActivityProvider implements RequestHandler<CancelAppointmentRequest, List<Appointment>> {
    public CancelAppointmentActivityProvider() {
    }

    @Override
    public List<Appointment> handleRequest(final CancelAppointmentRequest cancelAppointmentRequest, Context context) {
        return getServiceComponent().provideCancelAppointmentActivity().handleRequest(cancelAppointmentRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}

