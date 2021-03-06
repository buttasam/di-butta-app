package terminal;

import entity.Role;
import entity.User;
import service.Action;
import service.Executor;
import service.Reader;
import service.Parser;
import service.ui.Printer;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Trida reprezentuje zakladni okno terminalu.
 *
 * @author Samuel Butta
 */
@Singleton
public class MainBoard {

    private Printer printer;
    private Reader reader;
    private Parser parser;
    private Executor executor;

    /**
     * Hlavni smicka aplikace
     */
    public void mainLoop() {
        // vypsani hlavicky
        printer.printHeader();

        User user = verifyUser();

        while (true) {
            printMenu(user);
            String input = reader.readInput();

            // vstup se preda parseru
            Action action = parser.resolveAction(input, user);

            // vystup z parseru se preda executorovi
            executor.executeAction(action, user);
        }

    }

    /**
     * Metoda overuje uzivatele.
     * Smycka trva, dokud neni overen.
     */
    private User verifyUser() {
        User user = null;

        while (user == null) {
            printer.printLogin();
            user = executor.loginUser();
        }
        return user;
    }

    private void printMenu(User user) {
        switch (user.getRole()) {
            case ADMIN:
                printer.printAdminMenu();
                break;
            case USER:
                printer.printUserMenu();
                break;
        }
    }

    @Inject
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    @Inject
    public void setReader(Reader reader) {
        this.reader = reader;
    }

    @Inject
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    @Inject
    public void setExecutor(Executor executor) {
        this.executor = executor;
    }
}
