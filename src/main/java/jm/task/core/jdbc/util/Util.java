package jm.task.core.jdbc.util;
import java.util.Properties;
import jm.task.core.jdbc.model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "08062006PaveL";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
            System.out.println("Соединение установлено");
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            System.out.println("Connection Error");
        }
    return connection;
    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();
                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
    public static Configuration getConfiguration() {
        Configuration config = new Configuration();
        Properties prop = new Properties();
        prop.put(Environment.DRIVER, DB_DRIVER);
        prop.put(Environment.URL, URL);
        prop.put(Environment.USER, LOGIN);
        prop.put(Environment.PASS, PASSWORD);
        prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        config.setProperties(prop);
        return config;
    }
//    public static void close() {
//        try {
//            Util.getSessionFactory().close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
}
//}


    // реализуйте настройку соеденения с БД

