package dao;

import entity.Car;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class CarDao extends AbstractDao<Car, Long> {


    public CarDao() {
        super(Car.class, Long.class);
    }

}
