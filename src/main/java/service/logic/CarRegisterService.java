package service.logic;

import dao.CarDao;
import dao.DriverDao;
import entity.Car;
import entity.Driver;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * @author Samuel Butta
 */
@Singleton
public class CarRegisterService {


    /**
     * DAOs
     */
    private DriverDao driverDao;
    private CarDao carDao;

    public void addDriver(String name) {
        Driver driver = new Driver();
        driver.setName(name);

        driverDao.save(driver);
    }

    public void addCar(String registrationNumber) {
        Car car = new Car();
        car.setRegistrationNumber(registrationNumber);

        carDao.save(car);
    }

    public void addCarToDriver(Car car, Driver driver) {
        List<Car> cars = driver.getCars();
        cars.add(car);

        driver.setCars(cars);

        driverDao.update(driver);
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
