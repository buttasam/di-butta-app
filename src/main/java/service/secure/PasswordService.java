package service.secure;

/**
 * @author Samuel Butta
 */
public interface PasswordService {


    /**
     * Zahashuje heslo a vrati prislusny hash
     *
     * @param password heslo
     * @return hash
     */
    String hashPassword(String password);

    /**
     * Overi zadane heslo
     *
     * @param password zadane heslo
     * @param hash     zadany hash
     * @return true je heslo stejne
     */
    boolean verifyPassword(String password, String hash);

}
