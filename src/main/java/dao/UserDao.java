package dao;

import entity.User;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class UserDao extends AbstractDao<User, Long> {


    public UserDao() {
        super(User.class, Long.class);
    }

}
