package edu.csumb.cst438.userservice.business;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import edu.csumb.cst438.userservice.api.users.Cart;
import edu.csumb.cst438.userservice.api.users.Credit;
import edu.csumb.cst438.userservice.api.users.Password;
import edu.csumb.cst438.userservice.api.users.User;
import edu.csumb.cst438.userservice.api.users.Username;
import edu.csumb.cst438.userservice.business.Manager;
import edu.csumb.cst438.userservice.data.UserDBClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerTester{
    @Autowired
    Manager manager;

    @MockBean
    UserDBClient userDBClient;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getUserListReturnsEmptyListWhenNullUsersInDB(){
        when(userDBClient.getUserData()).thenReturn(null);
        Assert.assertEquals(null, manager.getUserList());
    }

    @Test

    public void getUserListReturnsExpectedResults(){
        when(userDBClient.getUserData()).thenReturn(FiveStandardUsersStub());
        List<User> expectedUsers = FiveStandardUsersStub();
        List<User> actualUsers = manager.getUserList();
        //Use lib or manually implement equals() for list-to-list .equals() comparison
        Assert.assertTrue(expectedUsers.get(1).getUsername().equals(
            actualUsers.get(1).getUsername()));
    }

    @Test
    public void getUserListPercolatesExceptionWhenThrownAtLowerLevel(){
        when(userDBClient.getUserData()).thenThrow(new RuntimeException("Test"));
        expectedException.expect(RuntimeException.class);
        manager.getUserList();
    }

    private List<User> FiveStandardUsersStub(){
        List<User> result = new ArrayList<User>();
        User u1 = new User("1",new Username("admin1"),new Password("admin1"),new Credit(100),new Cart(Arrays.asList("Snip","Wallet","Glue")));
        User u2 = new User("2",new Username("admin2"),new Password("admin2"),new Credit(200),new Cart(Arrays.asList("Boke","Bizza","Barlic Bread")));
        User u3 = new User("3",new Username("admin3"),new Password("admin3"),new Credit(300),new Cart(Arrays.asList("Bacos","Bortas","Borutos")));
        User u4 = new User("4",new Username("admin4"),new Password("admin4"),new Credit(400),new Cart(Arrays.asList("Buesadilla","Bopes","Bozole")));
        User u5 = new User("5",new Username("admin5"),new Password("admin5"),new Credit(500),new Cart(Arrays.asList("Bepsi","Balsa","Bequila")));

        result = Arrays.asList(u1,u2,u3,u4,u5);
        return result;
    }
    //Right: manager returns exception results

    //Boundry Test - Error: exception bubbles up

    //Boundry Test - Error: database connection failure

    //Boundry Test - type or mapping error ... misc database error

    //Boundry Test - Existance: what happens if there are no users

    //Boundry Test - Range: too many users?

    //Performance Test: obtain list of 1000 in less than 2 secs

    //Boundry Test - Conformance: what if user has no password

    //Boundry Test - Conformance: what if user has no credit?

    //Boundry Test - Conformance: What if user has no 
}