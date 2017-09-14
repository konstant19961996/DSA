import  java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import  java.security.NoSuchAlgorithmException;
import  java.security.interfaces.DSAParams;
import  java.security.interfaces.DSAPrivateKey;

/**
 * Created by 803100 on 13.11.2016.
 */
public class mainforDSA {
    public static void main(String... args) throws NoSuchAlgorithmException{
        DSA dsa = new DSA();
        String msg="Hello world!";
        BigInteger[] res = dsa.sign(msg);
        BigInteger r,s,z;
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(512);
        KeyPair keyPair = keyGen.genKeyPair();
        DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
        DSAParams dsaParams1 = privateKey.getParams();
        BigInteger p1 = dsaParams1.getP();
        System.out.println("r="+r+"\ns="+s+"\n");
        System.out.println("Checking:"+dsa.verify(msg,r,s).toString())
    }
}
