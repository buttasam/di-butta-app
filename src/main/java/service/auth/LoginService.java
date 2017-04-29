package service.auth;

import dao.UserDao;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class LoginService {

    private UserDao userDao;


    public void loginUser() {

    }

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
