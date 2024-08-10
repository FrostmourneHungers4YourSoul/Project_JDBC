import dao.impl.UserDaoHibernateImpl;
import model.User;
import service.impl.UserServiceImpl;


public class Main {

    public static void main(String[] args) {
        System.out.println("Project JDBC started...");
        System.out.println("Home work 2");

        UserDaoHibernateImpl dao = new UserDaoHibernateImpl();
        UserServiceImpl service = new UserServiceImpl(dao);

        service.createUsersTable();

        service.saveUser("Alice", "Wonderland", (byte) 28);
        service.saveUser("Christofer", "Nolan", (byte) 32);
        service.saveUser("Vladimir", "Putin", (byte) 24);
        service.saveUser("Donald", "Tramp",(byte) 29);

        for (User user : service.getAllUsers())
            System.out.println(user);

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
