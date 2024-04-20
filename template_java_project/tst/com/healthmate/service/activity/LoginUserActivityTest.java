package com.healthmate.service.activity;

import com.healthmate.service.dynamodb.UserDao;
import com.healthmate.service.dynamodb.models.User;
import com.healthmate.service.models.requests.LoginUserRequest;
import com.healthmate.service.models.response.LoginUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LoginUserActivityTest {
    private LoginUserActivity loginUserActivity;
    @Mock
    private UserDao userDao;
    @BeforeEach
    public void setUp() {
        initMocks(this);
        loginUserActivity = new LoginUserActivity(userDao);
    }
    @Test
    public void handleRequest_validUser_returnsToken() {
        String email ="abc@gmail.com";
        String password = "123@qwer";
        LoginUserRequest loginUserRequest = LoginUserRequest.builder().setEmail(email).setPassword(password).build();
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        when(userDao.getUser(email)).thenReturn(user);
        LoginUserResponse response = loginUserActivity.handleRequest(loginUserRequest,null);

    }
}
