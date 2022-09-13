package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class HibernateTest {
    
    @Test void TestsHibernate() {
        Session session;

        Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = sessionFactory.openSession();
        assertNotNull(session);
    }
    
}

