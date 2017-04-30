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
                return Action.LIST_ALL_DRIVERS;
            case "2":
                return Action.ADD_DRIVER;
            case "3":
                return Action.LIST_ALL_CARS;
            case "4":
                return Action.ADD_CAR;
            case "5":
                return Action.ADD_CAR_TO_DRIVER;
            case "6":
                return Action.REMOVE_CAR_FROM_DRIVER;
            case "7":
                return Action.DELETE_CAR;
            case "8":
                return Action.DELETE_DRIVER;
            default:
                return Action.NOT_FOUND;
        }
    }


}
