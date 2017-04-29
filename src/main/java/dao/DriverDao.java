package dao;

import entity.Driver;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class DriverDao extends AbstractDao<Driver, Long> {


    public DriverDao() {
        super(Driver.class, Long.class);
    }

}
