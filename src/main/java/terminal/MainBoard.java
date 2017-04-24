package terminal;

import service.Inputer;
import service.Printer;

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

    /**
     * Hlavni smicka aplikace
     */
    public void mainLoop() {
        printer.printHeader();

        inputer.readInput();
    }

    @Inject
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    @Inject
    public void setInputer(Inputer inputer) {
        this.inputer = inputer;
    }
}
