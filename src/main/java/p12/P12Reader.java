package p12;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Enumeration;

public class P12Reader {
    public static void main(String[] args) {
        final String KEYSTORE_FILE = "C:\\Users\\i343740\\Desktop\\test1.p12";
        final String KEYSTORE_PASSWORD = "notasecret";
        final String KEYSTORE_ALIAS = "alias";

        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            InputStream fis = new FileInputStream(KEYSTORE_FILE);

            // If the keystore password is empty(""), then we have to set
            // to null, otherwise it won't work!!!
            char[] nPassword = null;
            if ((KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("")) {
                nPassword = null;
            } else {
                nPassword = KEYSTORE_PASSWORD.toCharArray();
            }

            ks.load(fis, nPassword);
            fis.close();

//
//            // Now we loop all the aliases, we need the alias to get keys.
//            // It seems that this value is the "Friendly name" field in the
//            // detals tab <-- Certificate window <-- view <-- Certificate
//            // Button <-- Content tab <-- Internet Options <-- Tools menu
//            // In MS IE 6.
            Enumeration enumm = ks.aliases();
            String keyAlias = null;
            if (enumm.hasMoreElements()) // we are readin just one certificate.
            {
                keyAlias = (String) enumm.nextElement();
                System.out.println("alias=[" + keyAlias + "]");
            }

            // Now once we know the alias, we could get the keys.
            System.out.println("is key entry=" + ks.isKeyEntry("privatekey"));
            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
            Certificate cert = ks.getCertificate(keyAlias);
            PublicKey pubkey = cert.getPublicKey();

            System.out.println("cert class = " + cert.getClass().getName());
            System.out.println("cert = " + cert);
            System.out.println("public key = " + pubkey);
            System.out.println("private key = " + prikey);
            KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(nPassword);
            KeyStore.PrivateKeyEntry pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry("privatekey", protParam);
            PrivateKey myPrivateKey = pkEntry.getPrivateKey();
            System.out.println(myPrivateKey);
            byte[] a = myPrivateKey.getEncoded();
            for (int i = 0; i < a.length; i++) {
                System.out.print(a[i]);
            }
            System.out.println(a.length);
            System.out.println(new BASE64Encoder().encode(a));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}