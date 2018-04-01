package bookstore.auth;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

public class RSA {
    private static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALqnieGDTgPSbrI8" +
            "3M8ZIDLHGtvszwLMYfpLWrSTDRleCqR4evrDRcfor0qUCiF/pPVIemSfb0DjTtV9" +
            "IA7PeHZmIiGzdgdzpobzRX2KNxOoVt7xbWtdRZOZnAosxj6XMp/DfQujWU9cGxDm" +
            "Mma+DWVdrEG14HWUHjkvo/QGQpbzAgMBAAECgYAhJKCqB3ux2ArJ4V6/81ExUwNk" +
            "676yW4JGRF0d1UKtL3KqU65ampPodFuz3WeTHSw4VZ9OALVrizEzXV1048gax9Jc" +
            "aaszgkdy7DhKyHgpAtwRkoBThRy4HrqeahVRGtZc7PJZMDBNS/HBXShfqcwDr+BF" +
            "ZWsyzCMnatgI8JEyQQJBAPUXqp+XbDEstPWCRXSL2VQoHFGFoIPOnmvVqCJi6jtn" +
            "LJ69wtWbRvKYZvTOnmPsngzVp0uYCpwC072qovsKs6ECQQDC9hbPavR0SDVjnL49" +
            "EehcYu6Q0QXtuGYnnvrfbiYXemlTRMUmrDQCIwzfmTi9e1CiegxakAqKgpSWs7H1" +
            "EAITAkApgPp50BCYOzPhp0/Prqb841X4UCKgb2rQY/v25r03lf8uWoAV4BehmUFG" +
            "D38u+LDDB6Mu3+5cAPaOVckHrgZBAkBx4qUCdtzjjEa7sJUkQXC1fwkSyTcoXT43" +
            "SNQNbl+L+XLLNHHiHFepI+ZiOzP846r/rkHHKTsvHAZGXiPHHpnHAkB+5SLwjFGm" +
            "Sr79wYaEcig4/XwHFoNKLQauldb6BuA5YS3EnAjIiTbVG222sE5jhiUHdmxEjh3n" +
            "76MRyyvO4M+0";

    public static byte[] decrypt(String data) throws NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException {
        byte[] dataBytes = Base64.decodeBase64(data);
        byte[] privateKeyBytes = Base64.decodeBase64(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(dataBytes);
    }
}
