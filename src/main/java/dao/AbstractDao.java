package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.util.List;

/**
 * @author Samuel Butta
 */
abstract class AbstractDao<T, K> {

    protected SessionFactory sessionFactory;

    protected Class<T> tClass;
    protected Class<K> pkClass;

    private AbstractDao() {
        // prazdny konstruktor
    }

    public AbstractDao(Class<T> tClass, Class<K> pkClass) {
        this.tClass = tClass;
        this.pkClass = pkClass;

        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public K save(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        K id = (K) session.save(entity);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public List<T> getAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List result = session.createQuery( "from " + tClass.getName() ).list();
        session.getTransaction().commit();
        session.close();

        return result;
    }
}
