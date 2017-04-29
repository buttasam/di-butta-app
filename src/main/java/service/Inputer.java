package service;


import javax.inject.Singleton;
import java.util.Scanner;
/**
 *
 * Zpracovava vstupny od uzivatele
 *
 * @author Samuel Butta
 */

@Singleton
public class Inputer {

    /**
     * Ceka na vstup od uzivatele
     */
    public String readInput() {

        Scanner sc = new Scanner(System.in);
        return sc.next();
    }


}
