package service.logic;

import cvut.fit.di.testEntity.field.CarDao;
import dao.DriverDao;
import entity.Driver;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class CarRegisterService {


    private DriverDao driverDao;
    private CarDao carDao ;

    public void addDriver(String name) {
        Driver driver = new Driver();
        driver.setName(name);

        driverDao.save(driver);
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
