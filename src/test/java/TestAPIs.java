import api.entity.CreateUser.CreateUserRequest;
import api.entity.CreateUser.CreateUserResponse;
import api.entity.ListUsers.ListUserResponse;
import clients.ReqresClient;
import helpers.UserHelper;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.util.stream.Collectors;

public class TestAPIs extends TestBase {
    private ReqresClient reqresClient;
    public TestAPIs(){
        reqresClient = new ReqresClient();
    }


    @Test(priority = -3,groups = "TEST")
    @Description("get list of users")
    @Story("story name: validate users")
    @Severity(SeverityLevel.NORMAL)
    public void testGetListOfUsers(){
        ListUserResponse listUserResponse = reqresClient.listUsers();
        ListUserResponse.Data dataofByron = listUserResponse.getData().stream().filter(i->i.getFirstName().equalsIgnoreCase("byron")).findAny().get();
        Assert.assertEquals(listUserResponse.getPage(),2);
        Assert.assertEquals(listUserResponse.getPerPage(),6);
        Assert.assertEquals(listUserResponse.getTotalPages(),2);
        Assert.assertEquals(dataofByron.getId(),10);
        Assert.assertEquals(dataofByron.getEmail(),"byron.fields@reqres.in");
    }

    @Test(dataProvider = "readCSV", priority = -2,groups = "TEST")
    @Severity(SeverityLevel.CRITICAL)
    public void testCreateUsers(Object[] users){
        CreateUserRequest createUserRequest = UserHelper.createRequest(users);
        CreateUserResponse createUserResponse = reqresClient.createUser(createUserRequest);
        Assert.assertEquals(createUserResponse.getName(),createUserRequest.getName());
        Assert.assertEquals(createUserResponse.getJob(),createUserRequest.getJob());
        Assert.assertNotNull(createUserResponse.getId());
    }

    @Test
    public void testUpdateUser(){

    }


}
