package com.healthmate.service.dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.healthmate.service.dynamodb.models.Hospital;

import javax.inject.Inject;
import java.util.List;

public class HospitalDao {
    private final DynamoDBMapper dynamoDBMapper;
    @Inject
    public  HospitalDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }
    public List<Hospital> getHospitalByPincode(String pincode) {
        return dynamoDBMapper.query(Hospital.class, new DynamoDBQueryExpression().withHashKeyValues(pincode));
    }
    public Hospital save(Hospital hospital) {
        dynamoDBMapper.save(hospital);
        return hospital;
    }

}
