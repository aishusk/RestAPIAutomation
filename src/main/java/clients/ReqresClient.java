package clients;

import api.entity.CreateUser.CreateUserAPI;
import api.entity.CreateUser.CreateUserRequest;
import api.entity.CreateUser.CreateUserResponse;
import api.entity.ListUsers.ListUserAPI;
import api.entity.ListUsers.ListUserResponse;
import io.restassured.response.Response;
import utilities.ApiLogger;
import utilities.RequestHandler;

public class ReqresClient {

    public ListUserResponse listUsers(){
        ListUserAPI listUserAPI = new ListUserAPI();
        Response response = new RequestHandler().processHttpRequest(listUserAPI);
        ListUserResponse listUserResponse = response.as(ListUserResponse.class);
        ApiLogger.log(listUserResponse.toString());
        listUserResponse.setHttpStatusCode(response.statusCode());
        return listUserResponse;
    }

    public CreateUserResponse createUser(CreateUserRequest createUserRequest){
        CreateUserAPI createUserAPI = new CreateUserAPI(createUserRequest);
        Response response = new RequestHandler().processHttpRequest(createUserAPI);
        CreateUserResponse createUserResponse = response.as(CreateUserResponse.class);
        ApiLogger.log(createUserResponse.toString());
        createUserResponse.setHttpStatusCode(response.statusCode());
        return createUserResponse;
    }
}
