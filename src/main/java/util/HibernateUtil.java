package util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * @author aignat
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {

        SessionFactory sessionFactory = null;

        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch(HibernateException e) {
            e.printStackTrace();
        }

        return sessionFactory;

    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
