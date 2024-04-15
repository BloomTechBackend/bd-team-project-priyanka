package com.healthmate.service.dependency;

import com.healthmate.service.activity.CancelAppointmentActivity;
import com.healthmate.service.activity.CreateUserActivity;
import com.healthmate.service.activity.GetAppointmentActivity;
import com.healthmate.service.activity.GetNearByDoctorActivity;
import com.healthmate.service.activity.RegisterDoctorActivity;
import com.healthmate.service.activity.RegisterHospitalActivity;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {DaoModule.class})
public interface ServiceComponent {
    CreateUserActivity provideCreateUserActivity();
    RegisterDoctorActivity provideRegisterDoctorActivity();
    RegisterHospitalActivity provideRegisterHospitalActivity();
    GetNearByDoctorActivity provideGetNearByDoctorActivity();
    GetAppointmentActivity provideGetAppointmentActivity();
    CancelAppointmentActivity provideCancelAppointmentActivity();
}
