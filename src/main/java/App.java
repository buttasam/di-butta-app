import cvut.fit.di.container.DIContainer;
import dao.UserDao;
import entity.Role;
import entity.User;
import service.secure.PasswordHasher;
import terminal.MainBoard;

/**
 * @author Samuel Butta
 */
public class App {

    public static void main(String[] args) {
        DIContainer container = new DIContainer();

        // pridej admina pro testovaci ucely
        UserDao userDao = container.getInstance(UserDao.class);
        PasswordHasher passwordHasher = container.getInstance(PasswordHasher.class);

        User admin = new User();
        admin.setName("admin");
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordHasher.hashPassword("admin"));
        admin.setEmail("admin@admin.cz");

        User user = new User();
        user.setName("user");
        user.setRole(Role.USER);
        user.setPassword(passwordHasher.hashPassword("user"));
        user.setEmail("user@user.cz");

        userDao.save(admin);
        userDao.save(user);


        MainBoard mainBoard = container.getInstance(MainBoard.class);
        mainBoard.mainLoop();

    }

}
