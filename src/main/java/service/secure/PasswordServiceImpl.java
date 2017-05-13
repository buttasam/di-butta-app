package service.secure;

import cvut.fit.di.anotation.scope.Prototype;

import javax.inject.Singleton;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Samuel Butta
 */
@Prototype
public class PasswordServiceImpl implements PasswordService {

    private static final String SALT = "IODsasd/80@2MC465";

    @Override
    public String hashPassword(String password) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(SALT.getBytes("UTF-8"));
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }

    @Override
    public boolean verifyPassword(String password, String hash) {
        return hash.equals(hashPassword(password));
    }

}
