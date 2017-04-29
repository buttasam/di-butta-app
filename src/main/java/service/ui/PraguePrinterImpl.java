package service.ui;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */

@Singleton
public class PraguePrinterImpl implements Printer {


    public void printHeader() {
        System.out.println("--- Registr vozidel ---");
    }

    @Override
    public void printMenu() {
        String menu = "Nabidka - napiste dane cislo: \n" +
                "1) Zobrazit vsechny auta \n" +
                "2) Pridej auto";

        System.out.println(menu);
    }

    @Override
    public void printWaitingForInput() {
        System.out.println("Zadejte vstup:");
    }

}
