package service;

import cvut.fit.di.testEntity.field.CarDao;
import dao.DriverDao;
import service.ui.Printer;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class Executor {

    private Printer printer;

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
            case LIST_ALL_DRIVERS:
                listAllPeople();
                break;

        }

    }

    private void listAllCars() {

    }

    private void listAllPeople() {
        printer.print("vypis vsech lidi");

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
}
