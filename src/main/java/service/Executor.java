package service;

import cvut.fit.di.testEntity.field.CarDao;
import dao.DriverDao;
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

        }

    }

    private void listAllCars() {

    }

    private void listAllDrivers() {
        printer.print("Vypis vsech ridicu:");

        driverDao.getAll().forEach(d -> {
            printer.print(d.getName());
        });
    }

    private void addDriver() {
        printer.print("Zadejte ridicovo jmeno:");

        String name = inputer.readInput();
        carRegisterService.addDriver(name);

        printer.print("Ridic s jmenem " + name + " byl pridan");
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
}
