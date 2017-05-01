package service.auth;

import dao.UserDao;
import entity.User;
import service.secure.PasswordHasher;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class LoginService {

    private UserDao userDao;
    private PasswordHasher passwordHasher;


    public User loginUser(String email, String password) {
        User user = userDao.getByEmail(email);

        if (user != null && passwordHasher.verifyPassword(password, user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }

    @Inject
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Inject
    public void setPasswordHasher(PasswordHasher passwordHasher) {
        this.passwordHasher = passwordHasher;
    }
}
