package service.secure;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Samuel Butta
 */
public class PasswordServiceTest {


    @Test
    public void testHashPassword() {
        String pass = "Ahoj123";

        PasswordService h = new PasswordServiceImpl();

        String hash = h.hashPassword(pass);

        System.out.println(hash);

        Assert.assertTrue(h.verifyPassword(pass, hash));

    }

}
