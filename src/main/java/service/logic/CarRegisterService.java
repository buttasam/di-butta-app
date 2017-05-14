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
    @Inject
    private DriverDao driverDao;

    @Inject
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

        List<Car> updatedCars = cars.stream().map(c -> {
            if (!c.getId().equals(car.getId())) {
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
     * @return true pokud je mozne ridice smazat,
     * tedy neni k nemu prirazeno zadne auto
     */
    public boolean deleteDriver(Driver driver) {
        if (!driver.getCars().isEmpty()) {
            return false;
        }
        driverDao.delete(driver);

        return true;
    }

    /**
     * Odstani auto z databaze
     *
     * @param car intance auta
     * @return true pokud je mozne auto smazat,
     * tedy neni k nemu prirazen zadny ridic
     */
    public boolean deleteCar(Car car) {
        if (!car.getDrivers().isEmpty()) {
            return false;
        }
        carDao.delete(car);

        return true;
    }

}
