import dao.UserDao;
import entity.User;

/**
 * @author Samuel Butta
 */
public class App {

    public static void main(String[] args) {


        try {
            UserDao userDao = new UserDao();

            System.out.println(userDao.save(new User("Sam")));

            userDao.getAll(User.class).forEach(u -> System.out.println(u.getName()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("test");
    }

}
