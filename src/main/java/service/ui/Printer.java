package service.ui;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public interface Printer {

    void printHeader();

    void printMenu();

    void printWaitingForInput();
}
