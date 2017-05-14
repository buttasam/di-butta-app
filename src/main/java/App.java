import cvut.fit.di.builder.injector.FieldInjector;
import cvut.fit.di.container.DIContainer;
import dao.CarDao;
import dao.DriverDao;
import dao.UserDao;
import entity.Car;
import entity.Driver;
import entity.Role;
import entity.User;
import service.logic.CarRegisterService;
import service.secure.PasswordService;
import terminal.MainBoard;

/**
 * Hlavni spousteci trida.
 *
 * @author Samuel Butta
 */
public class App {

    public static void main(String[] args) {
        System.out.println("DI kontejner - field injection");
        DIContainer container = new DIContainer(new FieldInjector());

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
        DriverDao driverDao = container.getInstance(DriverDao.class);
        CarDao carDao = container.getInstance(CarDao.class);

        CarRegisterService registerService = container.getInstance(CarRegisterService.class);

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

        Driver driver = new Driver();
        driver.setName("Test user");
        driver.setUser(user);

        Car car = new Car();
        car.setRegistrationNumber("ABC123");


        carDao.save(car);
        userDao.save(admin);
        userDao.save(user);
        driverDao.save(driver);

        registerService.addCarToDriver(car, driver);
    }

}
