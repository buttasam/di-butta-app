package service.ui;

import javax.inject.Singleton;

/**
 * Specialini implementace rozhrani PrinterImpl
 *
 * @author Samuel Butta
 */
@Singleton
public class PraguePrinterImpl implements Printer {


    @Override
    public void printLogin() {
        print("--- Prihlaseni ---");
    }

    public void printHeader() {
        print("--- Registr vozidel (Praha)---");
    }

    @Override
    public void printMenu() {
        String menu = "Nabidka - napiste dane cislo: \n" +
                "1) Zobrazit vsechny ridice\n" +
                "2) Pridej ridice\n" +
                "3) Zobrazit vsechny auta \n" +
                "4) Pridej auto \n" +
                "5) Pridej auto k ridici \n" +
                "6) Odeber auto od ridice\n" +
                "7) Odstan auto \n" +
                "8) Odstan ridice";

        print(menu);
    }

    @Override
    public void print(String data) {
        System.out.println(data);
    }

    @Override
    public void printErrorLogin() {
        print("Prihlaseni nebylo uspesne.");
    }

}
