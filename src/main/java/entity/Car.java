package entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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

    @ManyToMany(mappedBy="cars")
    private List<Driver> drivers = new ArrayList<>();


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
}
