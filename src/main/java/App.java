import dao.CarDao;
import dao.PersonDao;
import dao.UserDao;
import entity.Car;
import entity.Person;
import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Butta
 */
public class App {

    public static void main(String[] args) {

        try {
            UserDao userDao = new UserDao();

            System.out.println(userDao.save(new User("Sam")));

            userDao.getAll().forEach(u -> System.out.println(u.getName()));


            PersonDao personDao = new PersonDao();
            CarDao carDao = new CarDao();

            List<Car> cars = new ArrayList<>();
            cars.add(new Car());
            cars.add(new Car());
            cars.add(new Car());

            Person person = new Person();
            person.setCars(cars);

            personDao.save(person);

            personDao.getAll().forEach(p -> {
                System.out.println(p.getCars().size());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("test");

/*
        DIContainer container = new DIContainer();

        MainBoard mainBoard = container.getInstance(MainBoard.class);
        mainBoard.mainLoop();
*/

    }

}
