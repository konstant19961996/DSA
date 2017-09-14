import  java.math.BigInteger;
import  java.security.KeyPair;
import  java.security.KeyPairGenerator;
import  java.security.NoSuchAlgorithmException;
import  java.security.interfaces.DSAParams;
import  java.security.interfaces.DSAPrivateKey;
import  java.security.interfaces.DSAPublicKey;
import  java.util.Random;
import  java.security.MessageDigest;
/**
 * Created by 803100 on 13.11.2016.
 */
public class DSA {
    private BigInteger p;
    private BigInteger q;
    private BigInteger g;
    private BigInteger x;
    private BigInteger y;

    private static BigInteger randbint(BigInteger n){
        Random rnd = new Random();
        int maxNumBitLength = n.bitLength();
        BigInteger aRandomBigInt;
        do{
            aRandomBigInt = new BigInteger(maxNumBitLength, rnd);
        }while (aRandomBigInt.compareTo(n) > 0);
        return aRandomBigInt;
    }

    private  static  BigInteger hash(String msg) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(msg.getBytes(),0,msg.length());
        BigInteger res = new BigInteger(1,md.digest());
        return res;
    }

    public  DSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
        keyGen.initialize(512);
        KeyPair keyPair = keyGen.genKeyPair();
        DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
        DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();

        DSAParams dsaParams = privateKey.getParams();
        p = dsaParams.getP();
        q = dsaParams.getQ();
       /* Miller_Rabin m = new Miller_Rabin();
        boolean a=false;
        while(!a){
            p=randbint(BigInteger.valueOf(2).pow(1024));
            if (m.isPrime(p)|| ((p.intValue()-1) % q.intValue()) == 0)
                a=true;
        }*/
        g = dsaParams.getG();
        x = randbint(q);
        y = g.modPow(x, p);
    }

    public BigInteger[] sign(String msg) throws NoSuchAlgorithmException{
        BigInteger k,r,s;
        do{
            k = randbint(q);
            r = g.modPow(k, p).mod(q);
            s = (k.modInverse(q).multiply(x.multiply(r).add(hash(msg))));
        }while(r.compareTo(BigInteger.ZERO)==0 || s.compareTo(BigInteger.ZERO)==0);
        return new BigInteger[]{r,s};
    }

    public Boolean verify(String msg, BigInteger r, BigInteger s) throws NoSuchAlgorithmException{
        BigInteger w = s.modInverse(q);
        BigInteger u1 = hash(msg).multiply(w).mod(q);
        BigInteger u2 = r.multiply(w).mod(q);
        //(ab) mod m=((a mod m)∗(b mod m)) mod m
        BigInteger v = (g.modPow(u1, p).multiply(y.modPow(u2, p))).mod(p).mod(q);
        return v.compareTo(r)==0;
    }
}
