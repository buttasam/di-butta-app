import cvut.fit.di.container.DIContainer;
import dao.UserDao;
import entity.Role;
import entity.User;
import service.secure.PasswordService;
import terminal.MainBoard;

/**
 * Hlavni spousteci trida.
 *
 * @author Samuel Butta
 */
public class App {

    public static void main(String[] args) {
        DIContainer container = new DIContainer();

        initDatabaseData(container);

        MainBoard mainBoard = container.getInstance(MainBoard.class);
        mainBoard.mainLoop();
    }

    /**
     * Vlozi vstupni data do database - pro testovaci ucely.
     *
     * @param container DI kontejner
     */
    private static void initDatabaseData(DIContainer container) {
        UserDao userDao = container.getInstance(UserDao.class);
        PasswordService passwordService = container.getInstance(PasswordService.class);

        User admin = new User();
        admin.setName("admin");
        admin.setRole(Role.ADMIN);
        admin.setPassword(passwordService.hashPassword("admin"));
        admin.setEmail("admin@admin.cz");

        User user = new User();
        user.setName("user");
        user.setRole(Role.USER);
        user.setPassword(passwordService.hashPassword("user"));
        user.setEmail("user@user.cz");

        userDao.save(admin);
        userDao.save(user);
    }

}
