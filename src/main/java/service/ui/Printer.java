package service.ui;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public interface Printer {

    void printLogin();

    void printHeader();

    void printMenu();

    void printWaitingForInput();

    void print(String data);

    void printErrorLogin();
}
