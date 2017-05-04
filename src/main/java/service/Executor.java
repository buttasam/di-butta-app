package service;

import dao.CarDao;
import dao.DriverDao;
import entity.Car;
import entity.Driver;
import entity.User;
import service.auth.LoginService;
import service.logic.CarRegisterService;
import service.ui.Printer;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Trida pro rozlisuje a vykonava prislusnou akci
 *
 * @author Samuel Butta
 */
@Singleton
public class Executor {

    /**
     * Sluzby
     */
    private Printer printer;
    private Reader reader;

    private CarRegisterService carRegisterService;
    private LoginService loginService;

    /**
     * DAO objekty
     */
    private DriverDao driverDao;
    private CarDao carDao;

    /**
     * Rozlisi prislusnou akci
     *
     * @param action typ akce
     */
    public void executeAction(Action action, User user) {
        switch (user.getRole()) {
            case ADMIN:
                adminActions(action);
                break;
            case USER:
                userActions(action, user);
                break;
        }
    }

    /**
     * Pokusi se prihlasit uzivatele
     *
     * @return prislusny uzivatel, null pokud neexistuje, nebo se nepodarilo overit
     */
    public User loginUser() {
        printer.print("Zadejte email:");
        String email = reader.readInput();

        printer.print("Zadejte heslo:");
        String password = reader.readInput();

        return loginService.loginUser(email, password);
    }

    private void adminActions(Action action) {
        switch (action) {
            case EXIT_APPLICATION:
                exitApplication();
                break;
            case LIST_ALL_CARS:
                listAllCars();
                break;
            case ADD_DRIVER:
                addDriver();
                break;
            case LIST_ALL_DRIVERS:
                listAllDrivers();
                break;
            case ADD_CAR:
                addCar();
                break;
            case ADD_CAR_TO_DRIVER:
                addCarToDriver();
                break;
            case REMOVE_CAR_FROM_DRIVER:
                removeCarFromDriver();
                break;
            case DELETE_DRIVER:
                deleteDriver();
                break;
            case DELETE_CAR:
                deleteCar();
                break;
            default:
                printer.printErrorAction();
        }
    }

    private void userActions(Action action, User user) {
        switch (action) {
            case EXIT_APPLICATION:
                exitApplication();
                break;
            case SHOW_USER_CARS:
                showUserCars(user);
                break;
            default:
                printer.printErrorAction();
        }
    }

    private void showUserCars(User user) {
        printer.print("Vypis aut pro uzivatele " +  user.getEmail() + " :");
    }

    /**
     * Ukonci aplikaci
     */
    private void exitApplication() {
        printer.print("Aplikace bude ukoncena ...");
        System.exit(0);
    }

    /**
     * Vypise vsechny auta
     */
    private void listAllCars() {
        printer.print("Vypis vsech aut:");

        carDao.getAll().forEach(c -> printer.print(c.getRegistrationNumber() + " pocet ridicu: " + c.getDrivers().size()));
    }

    /**
     * Vypise vsechny ridice
     */
    private void listAllDrivers() {
        printer.print("Vypis vsech ridicu:");

        driverDao.getAll().forEach(d -> printer.print(d.getName() + " pocet aut: " + d.getCars().size()));
    }

    /**
     * Prida ridice
     */
    private void addDriver() {
        printer.print("Zadejte ridicovo jmeno:");

        String name = reader.readInput();
        carRegisterService.addDriver(name);

        printer.print("Ridic s jmenem " + name + " byl pridan");
    }

    /**
     * Prida auto
     */
    private void addCar() {
        printer.print("Zadejte SPZ auta:");

        String registrationNumber = reader.readInput();
        carRegisterService.addCar(registrationNumber);

        printer.print("Auto s SPZ " + registrationNumber + " bylo pridano");
    }

    /**
     * Priradi auto k ridici
     */
    private void addCarToDriver() {
        printer.print("Zadejte id auta:");
        String carId = reader.readInput();

        Car car = carDao.getById(new Long(carId));

        printer.print("Zadejte id ridice:");
        String driverId = reader.readInput();

        Driver driver = driverDao.getById(new Long(driverId));

        carRegisterService.addCarToDriver(car, driver);

        printer.print("Auto s id " + carId + " bylo pridano k ridici s id " + driverId);
    }

    /**
     * Smaze ridice
     */
    private void deleteDriver() {
        printer.print("Zadejte id ridice:");

        String driverId = reader.readInput();
        Driver driver = driverDao.getById(new Long(driverId));

        carRegisterService.deleteDriver(driver);
        printer.print("Ridic s id " + driverId + " byl smazan");
    }

    /**
     * Smaze auto
     */
    private void deleteCar() {
        printer.print("Zadejte id auta:");

        String carId = reader.readInput();
        Car car = carDao.getById(new Long(carId));

        carRegisterService.deleteCar(car);
        printer.print("Auto s id " + carId + " bylo smazano");
    }

    /**
     * Smaze auto od ridice
     */
    private void removeCarFromDriver() {
        printer.print("Zadejte id auta:");
        String carId = reader.readInput();

        Car car = carDao.getById(new Long(carId));

        printer.print("Zadejte id ridice:");
        String driverId = reader.readInput();

        Driver driver = driverDao.getById(new Long(driverId));

        carRegisterService.removeCarFromDriver(car, driver);

        printer.print("Auto s id " + carId + " bylo odebrano od ridice s id " + driverId);
    }

    @Inject
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    @Inject
    public void setDriverDao(DriverDao driverDao) {
        this.driverDao = driverDao;
    }

    @Inject
    public void setCarDao(CarDao carDao) {
        this.carDao = carDao;
    }

    @Inject
    public void setCarRegisterService(CarRegisterService carRegisterService) {
        this.carRegisterService = carRegisterService;
    }

    @Inject
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
