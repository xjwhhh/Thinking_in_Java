package p12;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

public class MySign {
    /*
     * @param keyStorePath 密钥库存储路径
     * @param alias 密钥库别名
     * @param password 密钥库密码
     */
    private static String keyStorePath, alias, password;

    private static Certificate getCertificate()
            throws Exception {
        KeyStore keyStore = KeyStoreTool.getKeyStore(keyStorePath, password);
        Certificate certificate = keyStore.getCertificate(alias);
        return certificate;
    }

    public static void setKeyStorePath(String path) {
        MySign.keyStorePath = path;
    }

    public static void setAlias(String alias) {
        MySign.alias = alias;
    }

    public static void setPassword(String password) {
        MySign.password = password;
    }

    /*
     * 生成数据签名
     * @param data 源数据
     */
    public static byte[] sign(byte[] data)
            throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate) getCertificate();
        // 获取KeyStore
        KeyStore keyStore = KeyStoreTool.getKeyStore(keyStorePath, password);
        // 取得私钥
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, password.toCharArray());
        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    /*
     * 生成数据签名并以BASE64编码
     * @param data 源数据
     */
    public static String signToBase64(String data)
            throws Exception {
        byte[] byteData = data.getBytes();
        return Base64.encode(sign(byteData));
    }

    /*
     * 对二进制数据进行验签
     * @param data 已加密数据
     * @param sign 数据签名[BASE64]
     */
    public static boolean verifySign(byte[] data, String sign)
            throws Exception {
        // 获得证书
        X509Certificate x509Certificate = (X509Certificate) getCertificate();
        // 获得公钥
        PublicKey publicKey = x509Certificate.getPublicKey();
        // 构建签名
        Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(Base64.decode(sign));
    }

    /*
     * 对String数据进行验签
     * @param data 字符串
     * @param sign 数据签名[BASE64]
     */
    public static boolean verifySginString(String data, String sign)
            throws Exception {
        byte[] byteData = data.getBytes();
        return verifySign(byteData, sign);
    }

    public static void main(String[] args) throws Exception {

        MySign.setKeyStorePath("d://leslie.keystore");
        MySign.setPassword("123456");
        MySign.setAlias("everygold");
        String sign = "驴友的天空俱乐部";
        String base64 = MySign.signToBase64(sign);
        System.out.println("签名为：" + sign + "\n\n签名后数据：\n" + base64);
        boolean isRight = MySign.verifySginString(sign, base64);
        System.out.println("\n验签结果：" + isRight);
    }
}