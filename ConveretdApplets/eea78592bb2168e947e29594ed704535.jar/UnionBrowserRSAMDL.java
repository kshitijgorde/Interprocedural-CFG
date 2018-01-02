import java.math.BigInteger;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class UnionBrowserRSAMDL extends Applet
{
    BigInteger e;
    BigInteger n;
    BigInteger in;
    int pinMaxLen;
    byte[] pinBlock;
    
    public UnionBrowserRSAMDL() {
        this.e = new BigInteger("65537");
        this.pinMaxLen = 128;
    }
    
    private String genRandom(final int n) {
        final byte[] array = new byte[n];
        for (int i = 0; i < n; ++i) {
            array[i] = (byte)(Object)new Integer((int)((float)Math.random() * 57.0f) + 65);
        }
        return new String(array);
    }
    
    public void init() {
        this.pinMaxLen = 128;
        this.e = new BigInteger("65537");
    }
    
    private BigInteger mod(final BigInteger bigInteger, final BigInteger bigInteger2, final BigInteger bigInteger3) {
        BigInteger modPow;
        try {
            modPow = bigInteger.modPow(bigInteger2, bigInteger3);
        }
        catch (ArithmeticException ex) {
            return null;
        }
        return modPow;
    }
    
    public String PkEncryptAPin(final String s, String string) {
        if (string.length() < 6) {
            for (int length = string.length(), i = 0; i < 6 - length; ++i) {
                string += " ";
            }
        }
        final int length2 = string.length();
        if (length2 >= this.pinMaxLen - 2) {
            return null;
        }
        this.pinBlock = (new String(new byte[] { (byte)(Object)new Integer(length2 / 10 + 48), (byte)(Object)new Integer(length2 % 10 + 48) }) + string + this.genRandom(this.pinMaxLen - length2 - 2)).getBytes();
        this.in = new BigInteger(this.pinBlock);
        final String string2 = this.mod(this.in, this.e, new BigInteger(s, 16)).toString(16);
        final int length3 = string2.length();
        if (length3 < 256) {
            final int n = 256 - length3;
            String string3 = "";
            for (int j = 0; j < n; ++j) {
                string3 += "0";
            }
            return string3 + string2;
        }
        return string2;
    }
    
    public String PkEncryptEPin(final String s, final String s2, final String s3) {
        final int n = 2 + s2.length() + 2 + s3.length();
        if (n >= this.pinMaxLen - 2) {
            return null;
        }
        final byte[] array = { (byte)(Object)new Integer(n / 256), (byte)(Object)new Integer(n % 256) };
        final int length = s2.length();
        final byte[] array2 = { (byte)(Object)new Integer(length / 10 + 48), (byte)(Object)new Integer(length % 10 + 48) };
        final int length2 = s3.length();
        this.pinBlock = (new String(array) + new String(array2) + s2 + new String(new byte[] { (byte)(Object)new Integer(length2 / 10 + 48), (byte)(Object)new Integer(length2 % 10 + 48) }) + s3 + this.genRandom(this.pinMaxLen - n - 2)).getBytes();
        this.in = new BigInteger(this.pinBlock);
        final String string = this.mod(this.in, this.e, new BigInteger(s, 16)).toString(16);
        final int length3 = string.length();
        if (length3 < 256) {
            final int n2 = 256 - length3;
            String string2 = "";
            for (int i = 0; i < n2; ++i) {
                string2 += "0";
            }
            return string2 + string;
        }
        return string;
    }
}
