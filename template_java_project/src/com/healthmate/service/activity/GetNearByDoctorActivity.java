package com.healthmate.service.activity;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.healthmate.service.dynamodb.HospitalDao;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.dynamodb.models.Hospital;
import com.healthmate.service.dynamodb.models.TimeRange;
import com.healthmate.service.exceptions.InvalidAttributeValueException;
import com.healthmate.service.models.requests.GetDoctorRequest;
import com.healthmate.service.models.response.DoctorDetails;
import com.healthmate.service.models.response.GetDoctorResponse;
import com.healthmate.service.util.DoctorServiceUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetNearByDoctorActivity implements RequestHandler<GetDoctorRequest,GetDoctorResponse> {
    private HospitalDao hospitalDao;
    private GetDoctorAvailability getDoctorAvailability;

    @Inject
    public GetNearByDoctorActivity(HospitalDao hospitalDao, GetDoctorAvailability getDoctorAvailability) {
        this.hospitalDao = hospitalDao;
        this.getDoctorAvailability = getDoctorAvailability;
    }
    @Override
    public GetDoctorResponse handleRequest(final GetDoctorRequest getDoctorRequest, Context context) {
        if (!DoctorServiceUtils.isValidRequest(getDoctorRequest)) {
            throw new InvalidAttributeValueException("All fields are mandatory");
        }
        List<Hospital> hospitals = hospitalDao.getHospitalByPincode(getDoctorRequest.getPincode());
        List<String> nearByDoctorsId = new ArrayList<>();
        for (Hospital hospital:hospitals) {
            if (hospital.getDoctorInDept().containsKey(getDoctorRequest.getDepartment())) {
                List<String> doctorIds = hospital.getDoctorInDept().get(getDoctorRequest.getDepartment());
                for (String id:doctorIds) {
                    nearByDoctorsId.add(id);
                }
            }
        }
        Map<String,List<TimeRange>> nearByAvailableDoctors = getDoctorAvailability.getAvailableDoctors(getDoctorRequest.getDate(),nearByDoctorsId);
        List<DoctorDetails> doctorDetails = new ArrayList<>();
        for (String s:nearByAvailableDoctors.keySet()) {
            Doctor doctor = getDoctorAvailability.getDoctorDao().getDoctor(s);
            doctorDetails.add(new DoctorDetails(s, doctor.getFirstName(), doctor.getLastName(), doctor.getDepartment(), doctor.getAbout(), nearByAvailableDoctors.get(s)));
        }
        return new GetDoctorResponse(doctorDetails);
    }


}
