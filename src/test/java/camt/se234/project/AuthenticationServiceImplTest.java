package camt.se234.project;

import camt.se234.project.dao.UserDao;
import camt.se234.project.service.AuthenticationServiceImpl;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.hamcrest.MatcherAssert.assertThat;

public class AuthenticationServiceImplTest {
    UserDao userDao;
    AuthenticationServiceImpl authenticationService;

    @Before
    public void setup(){
        userDao = mock(UserDao.class);
        authenticationService = new AuthenticationServiceImpl();
        authenticationService.setUserDao(userDao);
    }

    @Test
    public void authenticateTest(){
        assertThat(authenticationService.authenticate("sun","1234"),is(userDao.getUser("sun", "1234")));
        assertThat(authenticationService.authenticate("dan","1234"),is(userDao.getUser("dan", "1234")));
    }
}
