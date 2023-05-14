import api.entity.CreateUser.CreateUserRequest;
import api.entity.CreateUser.CreateUserResponse;
import api.entity.ListUsers.ListUserResponse;
import clients.ReqresClient;
import helpers.UserHelper;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBase;

public class TestAPIs extends TestBase {
    private ReqresClient reqresClient;
    public TestAPIs(){
        reqresClient = new ReqresClient();
    }
    @Test
    public void testGetListOfUsers(){
        ListUserResponse listUserResponse = reqresClient.listUsers();
        ListUserResponse.Data dataofByron = listUserResponse.getData().stream().filter(i->i.getFirstName().equalsIgnoreCase("byron")).findAny().get();
        Assert.assertEquals(listUserResponse.getPage(),2);
        Assert.assertEquals(listUserResponse.getPerPage(),6);
        Assert.assertEquals(listUserResponse.getTotalPages(),2);
        Assert.assertEquals(dataofByron.getId(),10);
        Assert.assertEquals(dataofByron.getEmail(),"byron.fields@reqres.in");
    }

    @Test(dataProvider = "readCSV")
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
