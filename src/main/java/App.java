import dao.UserDao;

/**
 * @author Samuel Butta
 */
public class App {

    public static void main(String[] args) {


        try {
            UserDao userDao = new UserDao();

            userDao.saveUser();

            userDao.getUsers().forEach(u -> System.out.println(u.getName()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("test");
    }

}
