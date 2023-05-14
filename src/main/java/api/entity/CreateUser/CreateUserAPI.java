package api.entity.CreateUser;

import core.entities.Param;
import core.entities.RequestBody;
import core.templates.HttpMethods;
import core.templates.IServiceEndpoint;
import helpers.APIHelper;

import java.util.ArrayList;
import java.util.List;

public class CreateUserAPI implements IServiceEndpoint {
    private final CreateUserRequest createUserRequest;

    public CreateUserAPI(CreateUserRequest createUserRequest){
        this.createUserRequest = createUserRequest;
    }

    @Override
    public HttpMethods httpMethod() {
         return HttpMethods.POST;
    }

    @Override
    public String url() {
        return APIHelper.hostUrl;
    }

    @Override
    public List<Param> headers() {
        List<Param> headers = new ArrayList<>();
        headers.add(new Param("Content-Type","application/json"));
        return headers;
    }

    @Override
    public List<Param> pathParameters() {
        return null;
    }

    @Override
    public List<Param> queryParameters() {
        return null;
    }

    @Override
    public RequestBody body() {
        return new RequestBody(CreateUserRequest.class,createUserRequest);
    }
}
