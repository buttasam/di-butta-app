package service;

import cvut.fit.di.anotation.scope.Prototype;
import entity.User;

/**
 * @author Samuel Butta
 */
@Prototype
public class Parser {


    /**
     * Na zaklade vstupu rozhodne o jakou akci se jedna
     *
     * @param input vstupni retezec
     * @param user  uzivatel
     * @return typ akce
     */
    public Action resolveAction(String input, User user) {
        switch (user.getRole()) {
            case ADMIN:
                return resolveAdminActions(input);
            case USER:
                return resolveUserActions(input);
            default:
                return Action.NOT_FOUND;
        }
    }

    public Action resolveAdminActions(String input) {
        switch (input) {
            case "0":
                return Action.EXIT_APPLICATION;
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

    public Action resolveUserActions(String input) {
        switch (input) {
            case "0":
                return Action.EXIT_APPLICATION;
            case "1":
                return Action.SHOW_USER_CARS;
            default:
                return Action.NOT_FOUND;
        }
    }


}
