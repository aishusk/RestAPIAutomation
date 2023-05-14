package helpers;

import api.entity.CreateUser.CreateUserRequest;
import utilities.ApiLogger;

import java.util.Map;
import java.util.Random;

public class UserHelper {

    public static CreateUserRequest createRequest(Object[] users){
        ApiLogger.log("---------Users--------");
        ApiLogger.log("Name :"+ users[0]);
        ApiLogger.log("Job :"+users[1]);
        return CreateUserRequest.builder()
                .name(String.valueOf(users[0]))
                .job(String.valueOf(users[1]))
                .build();
    }
}
