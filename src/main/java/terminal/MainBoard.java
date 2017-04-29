package terminal;

import service.Action;
import service.Inputer;
import service.Parser;
import service.auth.LoginService;
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
    private Inputer inputer;
    private Parser parser;


    /**
     * Hlavni smicka aplikace
     */
    public void mainLoop() {
        // vypsani hlavicky
        printer.printHeader();

        // TODO overeni uzivatele --> v executorovi

        printer.printMenu();
        while(true) {

            String input = inputer.readInput();

            // vstup se preda parseru
            Action action = parser.resolveAction(input);

            System.out.println(action);

            // vystup z parseru se preda executorovi
        }

    }

    @Inject
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    @Inject
    public void setInputer(Inputer inputer) {
        this.inputer = inputer;
    }

    @Inject
    public void setParser(Parser parser) {
        this.parser = parser;
    }
}
