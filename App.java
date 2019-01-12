package lab4;

import lab4.RSA.RSAKey;

import java.math.BigInteger;

public class App {

    public static void main(String[] args) {
        RSAKey test = new RSAKey(3557, 2579, 17);

        BigInteger cryptotext = test.crypt(BigInteger.valueOf(111111)); // 6108255
        BigInteger message = test.decrypt(cryptotext);                  // 111111

        System.out.println("Hello World!");
    }
}
