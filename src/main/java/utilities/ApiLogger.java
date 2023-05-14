package utilities;

import core.entities.RequestBody;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;

public class ApiLogger {
    public static Logger logger;

    public static void logRequest(String className, String endpoint, RequestBody requestBody ){
        logger = LogManager.getLogger(className);
//        Reporter.log(className+"---Api request for "+endpoint,true);
        Reporter.log("---------------Request body is --------------",true);
        Reporter.log(requestBody.getBodyAsString(),true);
    }

    public static void logResponse(String className, String endpoint, Response response ){
        logger = LogManager.getLogger(className);
        Reporter.log("Api response is " ,true);
        Reporter.log("---------------Response for endpoint" +endpoint+ "is--------------",true);
        Reporter.log(response.toString(),true);
    }

    public static void log(String logStatement){
//        logger = LogManager.getLogger(classname);
        Reporter.log(logStatement,true);
    }

    public static void logDebug(String classname, String logStatement){
        logger = LogManager.getLogger(classname);
        Reporter.log(logStatement);
    }

    public static void logError(String classname, String logStatement){
        logger = LogManager.getLogger(classname);
        logger.error(logStatement);
    }
}
