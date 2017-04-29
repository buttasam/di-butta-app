package service;

import javax.inject.Singleton;

/**
 * @author Samuel Butta
 */
@Singleton
public class Parser {


    /**
     * Na zaklade vstupu rozhodne o jakou akci se jedna
     * @param input
     * @return
     */
    public Action resolveAction(String input) {
        switch (input) {
            case "1":
                return Action.LIST_ALL_CARS;
            case "2":
                return Action.ADD_CAR;
            default:
                return Action.NOT_FOUND;
        }
    }


}
