import cvut.fit.di.container.DIContainer;
import dao.UserDao;
import entity.User;
import sun.applet.Main;
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("test");*/

        DIContainer container = new DIContainer();

        MainBoard mainBoard = container.getInstance(MainBoard.class);
        mainBoard.mainLoop();

    }

}
