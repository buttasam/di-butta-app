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
    @Inject
    private Printer printer;
    @Inject
    private Reader reader;

    @Inject
    private CarRegisterService carRegisterService;

    @Inject
    private LoginService loginService;

    /**
     * DAO objekty
     */
    @Inject
    private DriverDao driverDao;

    @Inject
    private CarDao carDao;


    /**
     * Rozlisi prislusnou akci
     *
     * @param action typ akce
     * @param user   uzivatel
     */
    public void executeAction(Action action, User user) {
        switch (user.getRole()) {
            case ADMIN:
                try {
                    adminActions(action);
                } catch (Exception ex) {
                    printer.print("Pri pozadovane akci doslo k chybe.");
                }
                break;
            case USER:
                try {
                    userActions(action, user);
                } catch (Exception ex) {
                    printer.print("Pri pozadovane akci doslo k chybe.");
                }
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

    /**
     * Podle akce vykona prislusnou akci pro administratora
     *
     * @param action akce
     */
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


    /**
     * Podle akce vykona prislusnou akci pro uzivatele
     *
     * @param action akce
     */
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
        printer.print("Vypis aut pro uzivatele " + user.getEmail() + " :");

        Driver driver = user.getDriver();

        if (driver != null) {
            driver.getCars()
                    .forEach(c -> {
                                printer.print(c.getRegistrationNumber() + " pocet ridicu: " + c.getDrivers().size());
                            }
                    );
        } else {
            printer.print("Prihlaseny uzivatel neni zaroven ridicem.");
        }
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

        carDao.getAll().forEach(c -> printer.print(c.getRegistrationNumber()
                + " (id: " + c.getId()
                + ") pocet ridicu: "
                + c.getDrivers().size()));
    }

    /**
     * Vypise vsechny ridice
     */
    private void listAllDrivers() {
        printer.print("Vypis vsech ridicu:");

        driverDao.getAll().forEach(d -> printer.print(d.getName()
                + " (id: " + d.getId()
                + ") pocet aut: "
                + d.getCars().size()));
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

        try {
            Driver driver = driverDao.getById(new Long(driverId));

            if (carRegisterService.deleteDriver(driver)) {
                printer.print("Ridic s id " + driverId + " byl smazan");
            } else {
                printer.print("Nejprve ridici odeberte vsechny auta, pak ho muzete smazat!");
            }
        } catch (Exception ex) {
            printer.print("Ridice s id " + driverId + " se nepodarilo smazat.");
        }
    }

    /**
     * Smaze auto
     */
    private void deleteCar() {
        printer.print("Zadejte id auta:");
        String carId = reader.readInput();

        try {
            Car car = carDao.getById(new Long(carId));

            if (carRegisterService.deleteCar(car)) {
                printer.print("Auto s id " + carId + " bylo smazano");
            } else {
                printer.print("Nejprve autu odeberte vsechny ridice, pak ho muzete smazat!");
            }
        } catch (Exception ex) {
            printer.print("Auto s id " + carId + " se nepodarilo smazat.");
        }
    }

    /**
     * Smaze auto od ridice
     */
    private void removeCarFromDriver() {
        try {
            printer.print("Zadejte id auta:");
            String carId = reader.readInput();

            Car car = carDao.getById(new Long(carId));

            printer.print("Zadejte id ridice:");
            String driverId = reader.readInput();

            Driver driver = driverDao.getById(new Long(driverId));

            carRegisterService.removeCarFromDriver(car, driver);

            printer.print("Auto s id " + carId + " bylo odebrano od ridice s id " + driverId);
        } catch (Exception ex) {
            printer.print("Auto nelze odebrat od ridice.");
        }
    }

}
