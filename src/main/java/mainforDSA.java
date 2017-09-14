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
        //z=BigInteger.valueOf(2152302898747);
        z=new BigInteger("9999973");
        r=res[0];
        s=res[1];
        /*KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(512);
        KeyPair keyPair = keyGen.genKeyPair();
        DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
        DSAParams dsaParams1 = privateKey.getParams();
        BigInteger p1 = dsaParams1.getP();*/
        Miller_Rabin a = new Miller_Rabin();
       // System.out.println("r="+r+"\ns="+s+"\n");
       // System.out.println("Checking:"+dsa.verify(msg,r,s).toString());
        System.out.println(z);
        if (a.isPrime(z))
            System.out.println("Число "+z+" простое");
        else
            System.out.println("Число "+z+" составное");
    }
}
