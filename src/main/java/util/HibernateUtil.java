package util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Pomocna trida pro praci nad Hibernate.
 *
 * @author Samuel Butta
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Inicializuje a vrati instanci SessionFactory,
     * pokud jeste nebyla inicializovana.
     *
     * @return instance SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // A SessionFactory is set up once for an application!
            final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                    .configure() // configures settings from hibernate.cfg.xml
                    .build();
            try {
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            } catch (Exception e) {
                // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
                // so destroy it manually.
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }

        return sessionFactory;
    }
}
