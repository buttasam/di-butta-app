package service;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class Printer {


    public void printHeader() {
        System.out.println("--- Registr vozidel ---");
    }


}
