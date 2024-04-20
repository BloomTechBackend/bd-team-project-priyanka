import com.healthmate.service.activity.CancelAppointmentActivity;
import com.healthmate.service.activity.CreateAppointmentActivity;
import com.healthmate.service.activity.GetAppointmentActivity;
import com.healthmate.service.activity.GetNearByDoctorActivity;
import com.healthmate.service.activity.RegisterDoctorActivity;
import com.healthmate.service.dependency.DaggerServiceComponent;
import com.healthmate.service.dependency.ServiceComponent;
import com.healthmate.service.dynamodb.DoctorDao;
import com.healthmate.service.dynamodb.models.Day;
import com.healthmate.service.dynamodb.models.Doctor;
import com.healthmate.service.dynamodb.models.LocalDateMapper;
import com.healthmate.service.dynamodb.models.TimeRange;
import com.healthmate.service.models.requests.CancelAppointmentRequest;
import com.healthmate.service.models.requests.CreateAppointmentRequest;
import com.healthmate.service.models.requests.GetAppointmentRequest;
import com.healthmate.service.models.requests.GetDoctorRequest;
import com.healthmate.service.models.requests.RegisterDoctorRequest;
import com.healthmate.service.models.response.CancelAppointmentResponse;
import com.healthmate.service.models.response.CreateAppointmentResponse;
import com.healthmate.service.models.response.GetDoctorResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    public static void main(String[] args) {
        ServiceComponent serviceComponent = DaggerServiceComponent.create();
        RegisterDoctorActivity activity = serviceComponent.provideRegisterDoctorActivity();
        Map<Day, List<TimeRange>> schedules = new HashMap<>();
        List<TimeRange> timeRanges = new ArrayList<>();
        timeRanges.add(new TimeRange("11:00:00","13:00:00"));
        schedules.put(Day.WEDNESDAY,timeRanges);
        RegisterDoctorRequest request = RegisterDoctorRequest.builder()
                .licenseNumber("LJ1129").email("david1@gmail.com").department("Cardiology").firstName("David")
                .lastName("Roy").about("Certifying Board of Nuclear Cardiology.").contact("2065552391")
                .schedule(schedules).build();
        Doctor doctor = activity.handleRequest(request,null);
        System.out.println(doctor);
//        CancelAppointmentActivity appointmentActivity = serviceComponent.provideCancelAppointmentActivity();
//        CancelAppointmentRequest request = CancelAppointmentRequest.builder()
//                .token("pri@gmail.com")
//                .appointmentTime("2024-04-19#14:45:00").build();
//        CancelAppointmentResponse response = appointmentActivity.handleRequest(request,null);
//        System.out.println(response);
//        CreateAppointmentActivity appointmentActivity = serviceComponent.provideCreateAppointmentActivity();
//        CreateAppointmentRequest request = CreateAppointmentRequest.builder()
//                .doctorId("SD1029")
//                .appointmentTime("2024-04-19#14:45:00")
//                .token("pri@gmail.com")
//                .build();
//        CreateAppointmentResponse response = appointmentActivity.handleRequest(request,null);
//        System.out.println(response);
//       GetNearByDoctorActivity getNearByDoctorActivity = serviceComponent.provideGetNearByDoctorActivity();
//       GetDoctorRequest getDoctorRequest = GetDoctorRequest.builder()
//                .pincode("98122")
//                .department("Pediatric Neurology")
//                .date(new LocalDateMapper("2024-04-19"))
//                .build();
//       GetDoctorResponse getDoctorResponse = getNearByDoctorActivity.handleRequest(getDoctorRequest, null);
//       System.out.println(getDoctorResponse);

    }
}
