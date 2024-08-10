package util;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.IOException;
import java.util.Properties;

public final class Util {
    private static final Properties PROPERTIES = new Properties();

    private static final SessionFactory SESSION_FACTORY = buildSessionFactory();

    static {
        loadProperties();
    }

    private Util(){}

    private static SessionFactory buildSessionFactory(){
       try {

           PROPERTIES.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/users");
           PROPERTIES.put("hibernate.connection.username", "postgres");
           PROPERTIES.put("hibernate.connection.password", "psql16");
           PROPERTIES.put("javax.persistence.schema-generation.database.action", "create");
           PROPERTIES.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
           PROPERTIES.put("hibernate.connection.driver_class", "org.postgresql.Driver");
           PROPERTIES.put("hibernate.connection.pool_size", 5);
           PROPERTIES.put("hibernate.show_sql", true);
           PROPERTIES.put("hibernate.format_sql", true);
           PROPERTIES.put("hibernate.hbm2ddl.auto", "update");

           Configuration configuration = new Configuration();
           configuration.setProperties(PROPERTIES);
           configuration.addAnnotatedClass(User.class);

           ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                   .applySettings(configuration.getProperties())
                   .build();

           return configuration.buildSessionFactory(serviceRegistry).unwrap(SessionFactory.class);

       } catch (HibernateException e){
           e.printStackTrace();
           throw new RuntimeException("Ошибка при создании сессий Hibernate");
       }
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    public static SessionFactory getSessionFactory(){
        return SESSION_FACTORY;
    }

    private static void loadProperties() {
        try (var inputStream = Util.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (inputStream != null) {
                PROPERTIES.load(inputStream);
            } else {
                throw new RuntimeException("Не удалось найти файл application.properties");
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке файла application.properties", e);
        }
    }
}
