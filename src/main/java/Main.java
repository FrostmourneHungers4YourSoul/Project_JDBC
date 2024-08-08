import dao.UserDao;
import dao.impl.UserDaoJDBCImpl;
import model.User;
import service.UserService;
import service.impl.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        System.out.println("Project JDBC started...");
        System.out.println("Home work 1");

        UserDao userDao = new UserDaoJDBCImpl();
        UserService service = new UserServiceImpl((UserDaoJDBCImpl) userDao);

        service.createUsersTable();
        service.saveUser("Alex", "Pereira", (byte) 37);
        service.saveUser("Jiri", "Prochazka", (byte) 31);
        service.saveUser("Dustin", "Poirier", (byte) 35);
        service.saveUser("Conor", "McGregor", (byte) 36);

        for (User user : service.getAllUsers())
            System.out.println(user.toString());

        service.cleanUsersTable();
        service.dropUsersTable();

    }
}
