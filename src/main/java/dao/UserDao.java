package dao;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * @author Samuel Butta
 */
public class UserDao {


    protected SessionFactory sessionFactory;


    public UserDao() throws Exception {
        setUp();
    }


    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    public void saveUser() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save( new User("Sam"));
        session.getTransaction().commit();
        session.close();
    }

    public List<User> getUsers() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from User" ).list();
        session.getTransaction().commit();
        session.close();

        return result;

    }



}
