package com.healthmate.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.dynamodb.models.Appointment;
import com.healthmate.service.models.requests.GetAppointmentRequest;

import java.util.List;

public class GetAppointmentActivityProvider implements RequestHandler<GetAppointmentRequest, List<Appointment>> {
    public GetAppointmentActivityProvider() {
    }

    @Override
    public List<Appointment> handleRequest(final GetAppointmentRequest getAppointmentRequest, Context context) {
        return getServiceComponent().provideGetAppointmentActivity().handleRequest(getAppointmentRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
