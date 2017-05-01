package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class UserDao extends AbstractDao<User, Long> {


    public UserDao() {
        super(User.class, Long.class);
    }


    public User getByEmail(String email) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("from User u where u.email = :email");
        query.setParameter("email", email);

        User result = (User) query.uniqueResult();
        session.getTransaction().commit();
        session.close();

        return result;
    }

}
