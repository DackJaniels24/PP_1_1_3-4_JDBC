package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private Transaction transaction = null;
    public UserDaoHibernateImpl() {
    }
    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age TINYINT)")
                    .executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица создана с помощью Hibernate");
        }  catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            session.getTransaction().commit();
            System.out.println("Таблица удалена с помощью Hibernate");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            System.out.println("Пользователь добавлен с помощью Hibernate");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
            System.out.println("Пользователь с ID " + id + " удалён с помощью Hibernate");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            TypedQuery<User> query = session.createQuery("from User");
           List<User> list = query.getResultList();
            System.out.println(list);
            return list;
        }
    }
    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users").executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищена с помощью Hibernate");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
