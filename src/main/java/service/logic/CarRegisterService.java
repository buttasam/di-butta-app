package service.logic;

import dao.CarDao;
import dao.DriverDao;
import entity.Car;
import entity.Driver;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servisni trida pracujici nad DAO objekty
 *
 * @author Samuel Butta
 */
@Singleton
public class CarRegisterService {


    /**
     * DAO objekty
     */
    private DriverDao driverDao;
    private CarDao carDao;

    /**
     * Prida ridice do database
     *
     * @param name jmeno ridice
     */
    public void addDriver(String name) {
        Driver driver = new Driver();
        driver.setName(name);

        driverDao.save(driver);
    }

    /**
     * Prida auto do database
     *
     * @param registrationNumber poznavaci znacka
     */
    public void addCar(String registrationNumber) {
        Car car = new Car();
        car.setRegistrationNumber(registrationNumber);

        carDao.save(car);
    }

    /**
     * Prida k autu ridice
     *
     * @param car    instance auta
     * @param driver instance ridice
     */
    public void addCarToDriver(Car car, Driver driver) {
        List<Car> cars = driver.getCars();
        cars.add(car);

        driver.setCars(cars);

        driverDao.update(driver);
    }

    /**
     * Odebere auto od ridice
     *
     * @param car    instance auta
     * @param driver instance ridice
     */
    public void removeCarFromDriver(Car car, Driver driver) {
        List<Car> cars = driver.getCars();

        List<Car>  updatedCars = cars.stream().map(c -> {
            if(!c.getId().equals(car.getId())) {
                return c;
            } else {
                return null;
            }
        }).collect(Collectors.toList());

        driver.setCars(updatedCars);

        driverDao.update(driver);
    }

    /**
     * Odstani ridice z databaze
     *
     * @param driver instance ridice
     */
    public void deleteDriver(Driver driver) {
        driverDao.delete(driver);
    }

    /**
     * Odstani auto z databaze
     *
     * @param car intance auta
     */
    public void deleteCar(Car car) {
        carDao.delete(car);
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
