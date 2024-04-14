package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Util util = new Util();
        util.getSessionFactory();
        UserService userDao = new UserServiceImpl();
        System.out.println("Cоединение установлено");

        userDao.createUsersTable();

        userDao.saveUser("Chandler", "Bing", (byte) 27);
        userDao.saveUser("Joseph", "Tribbiani", (byte) 25);
        userDao.saveUser("Rose", "Geller", (byte) 28);
        userDao.saveUser("Monica", "Geller", (byte) 30);

 //     userDao.removeUserById(2);
        userDao.getAllUsers();
 //     userDao.cleanUsersTable();
 //     userDao.dropUsersTable();

    }
}
