package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        util.getConnection();
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("Chandler", "Bing", (byte) 27);
        userDao.saveUser("Joseph", "Tribbiani", (byte) 25);
        userDao.saveUser("Rose", "Geller", (byte) 28);
        userDao.saveUser("Monica", "Geller", (byte) 30);
        System.out.println("Юзеры добавлены");

       // userDao.removeUserById(2);
        userDao.getAllUsers();
       // userDao.cleanUsersTable();
       // userDao.dropUsersTable();
        // реализуйте алгоритм здесь
    }
}
