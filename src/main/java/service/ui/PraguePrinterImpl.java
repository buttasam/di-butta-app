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

}
