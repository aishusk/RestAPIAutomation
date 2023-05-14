package utilities;

import core.templates.AuthType;
import core.templates.HttpMethods;
import core.templates.IServiceEndpoint;
import helpers.TestHelper;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.client.config.RequestConfig;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RequestHandler {

    public Response processHttpRequest(IServiceEndpoint iServiceEndpoint){
        Response response = processIServiceEndpoint(iServiceEndpoint);
        String noOfRetries = System.getenv("NoOfRetries");
        int retries = (noOfRetries == null || noOfRetries.isEmpty() )? 0 : Integer.parseInt(noOfRetries);
        if(response.getStatusCode() == 500 || response.getStatusCode() == 505) {
            for (int i = 0; i < retries; i++) {
                TestHelper.wait(5000);
                processIServiceEndpoint(iServiceEndpoint);
            }
        }
//        ApiLogger.logResponse(this.getClass().getName(),iServiceEndpoint.getClass().getName(),response);
        return response;
    }

    private Response processIServiceEndpoint(IServiceEndpoint iServiceEndpoint) {
        RestAssured.registerParser("text/plain", Parser.JSON);
        RestAssured.registerParser("application/grpc", Parser.JSON);
        RestAssured.registerParser("text/xml", Parser.JSON);
        RestAssured.registerParser("text/json", Parser.JSON);
        RestAssured.defaultParser = Parser.JSON;
        String url = iServiceEndpoint.url();
        HttpMethods httpMethod = iServiceEndpoint.httpMethod();
        RequestSpecification requestSpecification = createRequestSpecification(iServiceEndpoint);
        ApiLogger.logRequest(this.getClass().getName(),iServiceEndpoint.getClass().getName(),iServiceEndpoint.body());
        return makeHttpCall(requestSpecification, url, httpMethod);
    }

    private RequestSpecification createRequestSpecification(IServiceEndpoint iServiceEndpoint) {
        RestAssuredConfig config = RestAssuredConfig.config().encoderConfig(new EncoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));
        RequestSpecification requestSpecification = given().config(config);
       if(iServiceEndpoint.headers()!=null){
           iServiceEndpoint.headers().forEach(i->requestSpecification.header(i.getKey(),i.getValue()));
       }
       if(iServiceEndpoint.pathParameters()!=null){
           iServiceEndpoint.pathParameters().forEach(i->requestSpecification.pathParam(i.getKey(),i.getValue()));
       }
       if(iServiceEndpoint.queryParameters()!=null){
           iServiceEndpoint.queryParameters().forEach(i->requestSpecification.queryParam(i.getKey(),i.getValue()));
       }
       if(iServiceEndpoint.body()!=null){
           requestSpecification.body(iServiceEndpoint.body().getBodyAsString());
       }

       Map<Object,Object> map = iServiceEndpoint.authType();
       Object authType = map.get("type");
       if(!authType.equals(AuthType.NONE)){
           requestSpecification.auth().basic(map.get("username").toString(), map.get("password").toString());
       }
       return requestSpecification;

    }


    private Response makeHttpCall(RequestSpecification requestSpecification, String url, HttpMethods httpMethod) {
           Response response = null;
           switch (httpMethod){
               case GET:
                   response = requestSpecification.get(url);
               break;
               case POST:
                   response = requestSpecification.post(url);
               break;
               case PUT:
                   response = requestSpecification.put(url);
               break;
               case DELETE:
                   response = requestSpecification.delete(url);
               break;
               case PATCH:
                   response = requestSpecification.patch(url);
               break;

           }
        return response;
    }
}
