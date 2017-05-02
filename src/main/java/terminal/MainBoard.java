package terminal;

import entity.User;
import service.Action;
import service.Executor;
import service.Reader;
import service.Parser;
import service.ui.Printer;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Trida reprezentuje zakladni
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

        verifyUser();

        printer.printMenu();
        while(true) {

            String input = reader.readInput();

            // vstup se preda parseru
            Action action = parser.resolveAction(input);

            executor.executeAction(action);

            // vystup z parseru se preda executorovi
        }

    }

    private void verifyUser() {
        User user = null;

        while(user == null) {
            printer.printLogin();
            user = executor.loginUser();
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
