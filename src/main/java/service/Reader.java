package service;

import cvut.fit.di.anotation.scope.Prototype;

import java.util.Scanner;

/**
 * Zpracovava vstupny od uzivatele
 *
 * @author Samuel Butta
 */

@Prototype
public class Reader {


    /**
     * Ceka na vstup od uzivatele
     *
     * @return vstup od uzivatele
     */
    public String readInput() {

        Scanner sc = new Scanner(System.in);
        return sc.next();
    }


}
