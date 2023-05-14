package core.templates;

import core.entities.Param;
import core.entities.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IServiceEndpoint {
    HttpMethods httpMethod();
    String url();
    List<Param> headers();
    List<Param> pathParameters();
    List<Param> queryParameters();
    RequestBody body();
    default Map<Object,Object> authType(){
        Map<Object,Object> map = new HashMap<>();
        map.put("type",AuthType.NONE);
        return map;
    }
}
