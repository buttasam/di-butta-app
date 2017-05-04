package service.ui;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public interface Printer {

    /**
     * Vypis pri prihlaseni
     */
    void printLogin();

    /**
     * Vypis hlavicky
     */
    void printHeader();

    /**
     * Vypis uzivatelskeho menu
     */
    void printUserMenu();


    /**
     * Vypis administratorskeho menu
     */
    void printAdminMenu();


    /**
     * Obecny vypis dat
     * @param data data pro vypis
     */
    void print(String data);


    /**
     * Vypis spatneho prihlaseni
     */
    void printErrorLogin();


    /**
     * Vypis zadani neplatne akce
     */
    void printErrorAction();
}
