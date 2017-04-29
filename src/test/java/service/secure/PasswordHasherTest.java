package service.secure;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Samuel Butta
 */
public class PasswordHasherTest {


    @Test
    public void testHashPassword() {
        String pass = "Ahoj123";

        PasswordHasher h = new PasswordHasherImpl();

        String hash = h.hashPassword(pass);

        System.out.println(hash);

        Assert.assertTrue(h.verifyPassword(pass, hash));

    }

}
