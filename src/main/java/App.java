import cvut.fit.di.container.DIContainer;
import terminal.MainBoard;

/**
 * @author Samuel Butta
 */
public class App {

    public static void main(String[] args) {

/*        try {
            UserDao userDao = new UserDao();

            System.out.println(userDao.save(new User("Sam")));

            userDao.getAll().forEach(u -> System.out.println(u.getName()));


            DriverDao personDao = new DriverDao();
            CarDao carDao = new CarDao();

            List<Car> cars = new ArrayList<>();
            cars.add(new Car());
            cars.add(new Car());
            cars.add(new Car());

            Driver person = new Driver();
            person.setCars(cars);

            personDao.save(person);

            personDao.getAll().forEach(p -> {
                System.out.println(p.getCars().size());
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("test");*/

        DIContainer container = new DIContainer();

        MainBoard mainBoard = container.getInstance(MainBoard.class);
        mainBoard.mainLoop();

    }

}
