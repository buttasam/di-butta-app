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
 * @author Samuel Butta
 */
@Singleton
public class Executor {

    /**
     * Sluzby
     */
    private Printer printer;
    private Inputer inputer;

    private CarRegisterService carRegisterService;
    private LoginService loginService;

    /**
     * DAO objekty
     */
    private DriverDao driverDao;
    private CarDao carDao;

    public void executeAction(Action action) {

        switch(action) {
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
        }
    }

    public User loginUser() {
        printer.print("Zadejte email:");
        String email = inputer.readInput();

        printer.print("Zadejte heslo:");
        String password = inputer.readInput();

        return loginService.loginUser(email, password);
    }

    private void listAllCars() {
        printer.print("Vypis vsech aut:");

        carDao.getAll().forEach(c -> printer.print(c.getRegistrationNumber() + " pocet ridicu: " + c.getDrivers().size()));
    }

    private void listAllDrivers() {
        printer.print("Vypis vsech ridicu:");

        driverDao.getAll().forEach(d -> printer.print(d.getName() + " pocet aut: " + d.getCars().size()));
    }

    private void addDriver() {
        printer.print("Zadejte ridicovo jmeno:");

        String name = inputer.readInput();
        carRegisterService.addDriver(name);

        printer.print("Ridic s jmenem " + name + " byl pridan");
    }

    private void addCar() {
        printer.print("Zadejte SPZ auta:");

        String registrationNumber = inputer.readInput();
        carRegisterService.addCar(registrationNumber);

        printer.print("Auto s SPZ " + registrationNumber + " bylo pridano");
    }

    private void addCarToDriver() {
        printer.print("Zadejte id auta:");
        String carId = inputer.readInput();

        Car car = carDao.getById(new Long(carId));

        printer.print("Zadejte id ridice:");
        String driverId = inputer.readInput();

        Driver driver = driverDao.getById(new Long(driverId));

        carRegisterService.addCarToDriver(car, driver);

        printer.print("Auto s id " + carId+ " bylo pridano k ridici s id " + driverId);
    }

    private void deleteDriver() {
        printer.print("Zadejte id ridice:");

        String driverId = inputer.readInput();
        Driver driver = driverDao.getById(new Long(driverId));

        carRegisterService.deleteDriver(driver);
        printer.print("Ridic s id " + driverId + " byl smazan");
    }

    private void deleteCar() {
        printer.print("Zadejte id auta:");

        String carId = inputer.readInput();
        Car car = carDao.getById(new Long(carId));

        carRegisterService.deleteCar(car);
        printer.print("Auto s id " + carId + " bylo smazano");
    }

    private void removeCarFromDriver() {

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
    public void setInputer(Inputer inputer) {
        this.inputer = inputer;
    }

    @Inject
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
}
