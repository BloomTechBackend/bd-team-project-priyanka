package com.healthmate.service.activity;

import com.healthmate.service.dynamodb.DoctorDao;
import com.healthmate.service.dynamodb.models.Appointment;
import com.healthmate.service.dynamodb.models.Availability;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.dynamodb.models.LocalDateMapper;
import com.healthmate.service.dynamodb.models.LocalDateTimeMapper;
import com.healthmate.service.dynamodb.models.TimeRange;
import lombok.Data;

import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Data
public class GetDoctorAvailability {
    private DoctorDao doctorDao;
    @Inject
    public GetDoctorAvailability(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    public Map<String,List<TimeRange>> getAvailableDoctors(LocalDateMapper date, List<String> doctorId) {
        Map<String,List<TimeRange>> availableSlotOfDoctors = new HashMap<>();
        for (String id:doctorId) {
            Doctor doctor = doctorDao.getDoctor(id);
            Map<LocalDateMapper,List<Availability>> slots = doctor.getAvailableSlot();
            List<Availability> availability = slots.get(date);
            List<TimeRange> timeRangeList = new ArrayList<>();
            for (int i = 0; i < availability.size(); i++) {
                if (availability.get(i).equals(Availability.AVAILABLE)) {
                    int startTime = 15 * i * 60;
                    int endTime = startTime + 900;
                    TimeRange time = new TimeRange(LocalTime.ofSecondOfDay(startTime).toString(), LocalTime.ofSecondOfDay(endTime).toString());
                    timeRangeList.add(time);
                }
            }
            if (timeRangeList.size() > 0) {
                availableSlotOfDoctors.put(id,timeRangeList);
            }

        }
        return availableSlotOfDoctors;
    }
    public void updateAvailability(Appointment appointment) {
        Doctor doctor = doctorDao.getDoctor(appointment.getDoctorId());
        String appointmentTime = appointment.getAppointmentTime();
        String[] dateTime = appointmentTime.split("#");
        List<Availability> availabilities = doctor.getAvailableSlot().get(new LocalDateMapper(dateTime[0]));
        int startTime =LocalTime.parse(dateTime[1]).getSecond()/900;
        if (availabilities.get(startTime) == Availability.BOOKED) {
            availabilities.set(startTime,Availability.NOT_AVAILABLE);
        }




    }
}