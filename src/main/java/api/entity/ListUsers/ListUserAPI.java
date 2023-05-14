package api.entity.ListUsers;

import core.entities.Param;
import core.entities.RequestBody;
import core.templates.HttpMethods;
import core.templates.IServiceEndpoint;
import helpers.APIHelper;

import java.util.ArrayList;
import java.util.List;

public class ListUserAPI implements IServiceEndpoint {
    @Override
    public HttpMethods httpMethod() {
        return HttpMethods.GET;
    }

    @Override
    public String url() {
        return APIHelper.hostUrl;
    }

    @Override
    public List<Param> headers() {
        List<Param> header = new ArrayList<>();
        Param param = new Param("Accept","application/json");
        header.add(param);
        return header;
    }

    @Override
    public List<Param> pathParameters() {
        return null;
    }

    @Override
    public List<Param> queryParameters() {
        List<Param> queryParam = new ArrayList<>();
        queryParam.add(new Param("page","2"));
        return queryParam;
    }

    @Override
    public RequestBody body() {
        return null;
    }
}
