package config;

import controlador.ControladorConexion;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;
    private static StandardServiceRegistry serviceRegistry;

    public static SessionFactory buildSessionFactory() {
        try {
            serviceRegistry = new StandardServiceRegistryBuilder()
                    .configure("hibernate.cfg.xml")
                    .applySetting("hibernate.connection.username", ControladorConexion.user)
                    .applySetting("hibernate.connection.password", ControladorConexion.pass)
                    .applySetting("hibernate.connection.url", "jdbc:mariadb://172.18.1.241:3306/" + ControladorConexion.user).build();
            Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
            return metadata.getSessionFactoryBuilder().build();
        } catch (HibernateException e) {
            if(serviceRegistry != null){
                StandardServiceRegistryBuilder.destroy(serviceRegistry);
            }
            return null;
        }

    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            throw new IllegalStateException("La SessionFactory aún no está inicializada. " + "Debe llamar al método buildSessionFactory() primero");
        }
        return sessionFactory;
    }

    public static void close() {
        if ((sessionFactory != null) && (sessionFactory.isClosed() == false)) {
            sessionFactory.close();
        }
    }
}
