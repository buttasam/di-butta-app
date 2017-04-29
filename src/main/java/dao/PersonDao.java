package dao;

import entity.Person;

/**
 * @author Samuel Butta
 */
public class PersonDao extends AbstractDao<Person, Long> {


    public PersonDao() {
        super(Person.class, Long.class);
    }

}
