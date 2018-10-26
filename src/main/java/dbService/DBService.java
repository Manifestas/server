package dbService;

import dataSets.UsersDataSet;
import dbService.dao.UsersDAO;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DBService {

    private static final String hibernate_show_sql = "true";
    // With create-drop, the database schema will be dropped when the SessionFactory is closed explicitly.
    private static final String hibernate_hbm2ddl_auto = "create";

    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration configuration = getMySqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        StandardServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UsersDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example")
                .setProperty("hibernate.connection.username", "tully")
                .setProperty("hibernate.connection.password", "tully")
                .setProperty("hibernate.show_sql", hibernate_show_sql)
                .setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }
    public void printConnectInfo() {
        try (Session session = sessionFactory.openSession()) {
            session.doWork(connection -> {
                System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
                System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
                System.out.println("Driver: " + connection.getMetaData().getDriverName());
                System.out.println("Autocommit: " + connection.getAutoCommit());
            });
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    public UsersDataSet getUser(long id) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            UsersDAO dao = new UsersDAO(session);
            return dao.get(id);
        }
    }

    public long addUser(String name) throws HibernateException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            long id = dao.insertUser(name);
            transaction.commit();
            return  id;
        }
    }
}
