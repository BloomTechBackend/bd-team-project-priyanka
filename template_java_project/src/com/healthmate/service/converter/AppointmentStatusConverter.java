package com.healthmate.service.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthmate.service.dynamodb.models.AppointmentStatus;

import java.io.IOException;

public class AppointmentStatusConverter implements DynamoDBTypeConverter<String, AppointmentStatus> {

    @Override
    public String convert(AppointmentStatus status) {
        return status.name();
    }

    @Override
    public AppointmentStatus unconvert(String s) {
       return AppointmentStatus.valueOf(s);
    }
}
