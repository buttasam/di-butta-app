package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import java.io.Serializable;
import java.util.List;

/**
 * @author Samuel Butta
 */
abstract class AbstractDao<T, K extends Serializable> {

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


    public T getById(K id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        T result = (T) session.get(tClass.getName(), id);

        session.getTransaction().commit();
        session.close();
        return result;
    }

    public K save(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        K id = (K) session.save(entity);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public void update(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
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
