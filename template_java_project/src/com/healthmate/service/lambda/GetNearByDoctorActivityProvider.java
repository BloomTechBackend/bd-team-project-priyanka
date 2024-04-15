package com.healthmate.service.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.dynamodb.models.TimeRange;
import com.healthmate.service.models.requests.GetDoctorRequest;

import java.util.List;
import java.util.Map;

public class GetNearByDoctorActivityProvider implements RequestHandler<GetDoctorRequest, Map<String,List<TimeRange>>> {
    public GetNearByDoctorActivityProvider() {
    }

    @Override
    public Map<String,List<TimeRange>> handleRequest(final GetDoctorRequest getDoctorRequest, Context context) {
        return getServiceComponent().provideGetNearByDoctorActivity().handleRequest(getDoctorRequest, context);
    }

    private ServiceComponent getServiceComponent() {
        ServiceComponent dagger = DaggerServiceComponent.create();
        return dagger;
    }
}
