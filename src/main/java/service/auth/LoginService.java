package service.auth;

import dao.UserDao;
import entity.User;
import service.secure.PasswordService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class LoginService {

    private UserDao userDao;
    private PasswordService passwordService;


    /**
     * Pokusi se najit a overit uzivatele.
     *
     * @param email    email
     * @param password heslo
     * @return instance uzivatele, null pokud neexistuje nebo se heslo neshoduje
     */
    public User loginUser(String email, String password) {
        User user = userDao.getByEmail(email);

        if (user != null && passwordService.verifyPassword(password, user.getPassword())) {
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
    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }
}
