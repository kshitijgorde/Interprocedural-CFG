import java.util.Random;
import java.math.BigInteger;

// 
// Decompiled by Procyon v0.5.30
// 

public class RX
{
    private BigInteger w;
    private Random rnd;
    private Random rnd1;
    private Random rnd2;
    private BigInteger p;
    private BigInteger q;
    private BigInteger ee;
    private BigInteger n;
    private BigInteger p1;
    private BigInteger q1;
    private BigInteger pq1;
    private BigInteger gcd;
    private BigInteger d;
    
    public RX() {
        this.w = new BigInteger("1");
    }
    
    public void initialize() {
        do {
            this.rnd = new Random();
            this.rnd1 = new Random();
            this.rnd2 = new Random();
            this.p = new BigInteger(128, 20, this.rnd);
            this.q = new BigInteger(128, 20, this.rnd);
            this.ee = new BigInteger(128, 20, this.rnd);
            this.n = this.p.multiply(this.q);
            this.p1 = this.p.subtract(this.w);
            this.q1 = this.q.subtract(this.w);
            this.pq1 = this.p1.multiply(this.q1);
            this.gcd = this.ee.gcd(this.pq1);
            this.d = this.ee.modInverse(this.pq1);
        } while (this.gcd.compareTo(this.w) != 0);
    }
    
    public String getPublicKey() {
        return this.ee.toString();
    }
    
    public BigInteger getBIPublicKey() {
        return this.ee;
    }
    
    public String getPrivateKey() {
        return this.d.toString();
    }
    
    public BigInteger getBIPrivateKey() {
        return this.d;
    }
    
    public String getFactor() {
        return this.n.toString();
    }
    
    public BigInteger getBIFactor() {
        return this.n;
    }
    
    public String encrypt(final BigInteger nPlain, final BigInteger nPublicKey, final BigInteger nFactor) {
        return nPlain.modPow(nPublicKey, nFactor).toString();
    }
    
    public String decrypt(final BigInteger nCipher, final BigInteger nPrivateKey, final BigInteger nFactor) {
        return nCipher.modPow(nPrivateKey, nFactor).toString();
    }
    
    public String encrypt(final String sPlain, final BigInteger nPublicKey, final BigInteger nFactor) {
        String sTemp = "";
        String sFragment = "";
        String sEncFragment = "";
        String sFinal = "";
        for (int i = 0; i < sPlain.length(); ++i) {
            if (sPlain.charAt(i) < '\n') {
                sTemp = sTemp + "00" + String.valueOf((int)sPlain.charAt(i));
            }
            else if (sPlain.charAt(i) < 'd') {
                sTemp = sTemp + "0" + String.valueOf((int)sPlain.charAt(i));
            }
            else {
                sTemp += String.valueOf((int)sPlain.charAt(i));
            }
        }
        while (sTemp.length() > 21) {
            sFragment = sTemp.substring(0, 21);
            sTemp = sTemp.substring(21);
            final BigInteger nPlain = new BigInteger(sFragment);
            sEncFragment = nPlain.modPow(nPublicKey, nFactor).toString();
            if (sEncFragment.length() < 9) {
                sFinal = sFinal + "00" + sEncFragment.length();
            }
            else if (sEncFragment.length() < 100) {
                sFinal = sFinal + "0" + sEncFragment.length();
            }
            else {
                sFinal += sEncFragment.length();
            }
            sFinal += sEncFragment;
        }
        if (sTemp.length() > 0) {
            final BigInteger nPlain = new BigInteger(sTemp);
            sEncFragment = nPlain.modPow(nPublicKey, nFactor).toString();
            if (sEncFragment.length() < 9) {
                sFinal = sFinal + "00" + sEncFragment.length();
            }
            else if (sEncFragment.length() < 100) {
                sFinal = sFinal + "0" + sEncFragment.length();
            }
            else {
                sFinal += sEncFragment.length();
            }
            sFinal += sEncFragment;
        }
        return sFinal;
    }
    
    public String encrypt(final String sPlain, final String sPublicKey, final String sFactor) {
        final BigInteger nPublicKey = new BigInteger(sPublicKey);
        final BigInteger nFactor = new BigInteger(sFactor);
        String sTemp = "";
        String sFragment = "";
        String sEncFragment = "";
        String sFinal = "";
        for (int i = 0; i < sPlain.length(); ++i) {
            if (sPlain.charAt(i) < '\n') {
                sTemp = sTemp + "00" + String.valueOf((int)sPlain.charAt(i));
            }
            else if (sPlain.charAt(i) < 'd') {
                sTemp = sTemp + "0" + String.valueOf((int)sPlain.charAt(i));
            }
            else {
                sTemp += String.valueOf((int)sPlain.charAt(i));
            }
        }
        while (sTemp.length() > 21) {
            sFragment = sTemp.substring(0, 21);
            sTemp = sTemp.substring(21);
            final BigInteger nPlain = new BigInteger(sFragment);
            sEncFragment = nPlain.modPow(nPublicKey, nFactor).toString();
            if (sEncFragment.length() < 9) {
                sFinal = sFinal + "00" + sEncFragment.length();
            }
            else if (sEncFragment.length() < 100) {
                sFinal = sFinal + "0" + sEncFragment.length();
            }
            else {
                sFinal += sEncFragment.length();
            }
            sFinal += sEncFragment;
        }
        if (sTemp.length() > 0) {
            final BigInteger nPlain = new BigInteger(sTemp);
            sEncFragment = nPlain.modPow(nPublicKey, nFactor).toString();
            if (sEncFragment.length() < 9) {
                sFinal = sFinal + "00" + sEncFragment.length();
            }
            else if (sEncFragment.length() < 100) {
                sFinal = sFinal + "0" + sEncFragment.length();
            }
            else {
                sFinal += sEncFragment.length();
            }
            sFinal += sEncFragment;
        }
        return sFinal;
    }
    
    public String decrypt(String sCipher, final BigInteger nPrivateKey, final BigInteger nFactor) {
        String sFragment = "";
        String sFinal = "";
        while (sCipher.length() > 0) {
            final int nBlockLen = Integer.valueOf(sCipher.substring(0, 3));
            sFragment = sCipher.substring(3, 3 + nBlockLen);
            sCipher = sCipher.substring(3 + nBlockLen);
            final BigInteger nCipher = new BigInteger(sFragment);
            String sCoded = nCipher.modPow(nPrivateKey, nFactor).toString();
            if (sCoded.length() % 3 == 1) {
                sCoded = "00" + sCoded;
            }
            else if (sCoded.length() % 3 == 2) {
                sCoded = "0" + sCoded;
            }
            final char[] chDecoded = new char[sCoded.length() / 3];
            for (int i = 0; i < sCoded.length() / 3; ++i) {
                chDecoded[i] = (char)(int)Integer.valueOf(sCoded.substring(i * 3, i * 3 + 3));
            }
            sFinal += new String(chDecoded);
        }
        return sFinal;
    }
    
    public String decrypt(String sCipher, final String sPrivateKey, final String sFactor) {
        final BigInteger nPrivateKey = new BigInteger(sPrivateKey);
        final BigInteger nFactor = new BigInteger(sFactor);
        String sFragment = "";
        String sFinal = "";
        while (sCipher.length() > 0) {
            final int nBlockLen = Integer.valueOf(sCipher.substring(0, 3));
            sFragment = sCipher.substring(3, 3 + nBlockLen);
            sCipher = sCipher.substring(3 + nBlockLen);
            final BigInteger nCipher = new BigInteger(sFragment);
            String sCoded = nCipher.modPow(nPrivateKey, nFactor).toString();
            if (sCoded.length() % 3 == 1) {
                sCoded = "00" + sCoded;
            }
            else if (sCoded.length() % 3 == 2) {
                sCoded = "0" + sCoded;
            }
            final char[] chDecoded = new char[sCoded.length() / 3];
            for (int i = 0; i < sCoded.length() / 3; ++i) {
                chDecoded[i] = (char)(int)Integer.valueOf(sCoded.substring(i * 3, i * 3 + 3));
            }
            sFinal += new String(chDecoded);
        }
        return sFinal;
    }
}
