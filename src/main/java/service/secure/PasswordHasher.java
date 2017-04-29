package service.secure;

/**
 * @author Samuel Butta
 */
public interface PasswordHasher {


    String hashPassword(String password);

    boolean verifyPassword(String password, String hash);

}
