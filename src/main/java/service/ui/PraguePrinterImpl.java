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
                "1) Zobrazit vsechny ridice\n" +
                "2) Pridej ridice\n" +
                "3) Zobrazit vsechny auta \n" +
                "4) Pridej auto \n" +
                "5) Pridej auto k ridici";

        System.out.println(menu);
    }

    @Override
    public void printWaitingForInput() {
        System.out.println("Zadejte vstup:");
    }

    @Override
    public void print(String data) {
        System.out.println(data);
    }

}
