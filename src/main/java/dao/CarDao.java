package dao;

import entity.Car;

/**
 * @author Samuel Butta
 */
public class CarDao extends AbstractDao<Car, Long> {


    public CarDao() {
        super(Car.class, Long.class);
    }

}
