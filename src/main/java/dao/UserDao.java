package dao;

import entity.User;

/**
 * @author Samuel Butta
 */
public class UserDao extends AbstractDao<User, Long> {


    public UserDao() {
        super(User.class, Long.class);
    }

}
