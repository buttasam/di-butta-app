package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Butta
 */
@Entity
public class Car implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy="cars")
    private List<Driver> drivers = new ArrayList<>();

    @Column
    private String registrationNumber;


    public Long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> people) {
        this.drivers = people;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
