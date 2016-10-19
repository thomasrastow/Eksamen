package Encryption;

/**
 * Created by mikkelaltmann on 19/10/16.
 */

public class Test {

    public static void main(String[] args) throws Exception {

        String password = "mypassword";
        String passwordEnc = Encryption.encrypt(password);
        String passwordDec = Encryption.decrypt(passwordEnc);

        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted Text : " + passwordEnc);
        System.out.println("Decrypted Text : " + passwordDec);
    }
}
