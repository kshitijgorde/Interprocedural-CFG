import java.security.Key;
import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.KeySpec;
import java.security.KeyFactory;
import java.security.spec.X509EncodedKeySpec;
import java.security.SignatureException;
import java.security.PublicKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.net.MalformedURLException;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class eb
{
    protected Applet a;
    protected String b;
    protected String c;
    protected xb d;
    protected String e;
    protected int f;
    protected RSAPublicKey g;
    protected RSAPrivateKey h;
    protected Signature i;
    private static final String[] z;
    
    public eb(final Applet applet, final String s, final String s2) {
        this(applet, s, s2, eb.z[1], eb.z[2], eb.z[0], 1024);
    }
    
    public eb(final Applet a, final String s, final String s2, final String b, final String c, final String e, final int f) {
        this.a = a;
        Security.addProvider(this.d = new xb());
        this.b = b;
        this.c = c;
        this.e = e;
        this.f = f;
        try {
            this.g = this.a(db.a(db.a(s, eb.z[7])));
        }
        catch (MalformedURLException ex) {
            System.out.println(eb.z[10]);
            System.out.println(eb.z[9] + s);
            ex.printStackTrace();
            return;
        }
        System.out.println(eb.z[6] + s2);
        try {
            this.h = this.c(s2);
        }
        catch (InvalidKeySpecException ex2) {
            System.out.println(eb.z[8]);
            ex2.printStackTrace();
            return;
        }
        final String s3 = eb.z[5];
        try {
            (this.i = Signature.getInstance(s3, this.d)).initSign(this.h);
        }
        catch (NoSuchAlgorithmException ex3) {
            System.out.println(eb.z[12] + s3);
            ex3.printStackTrace();
        }
        catch (InvalidKeyException ex4) {
            System.out.println(eb.z[11]);
            ex4.printStackTrace();
        }
    }
    
    public String a(final String s) {
        String a;
        try {
            a = this.a(s, this.g);
        }
        catch (Exception ex) {
            System.out.println(eb.z[3]);
            ex.printStackTrace();
            return null;
        }
        return a;
    }
    
    public byte[] b(final String s) {
        try {
            this.i.update(s.getBytes());
            return this.i.sign();
        }
        catch (SignatureException ex) {
            System.out.println(eb.z[4]);
            ex.printStackTrace();
            return null;
        }
    }
    
    private RSAPublicKey a(final byte[] array) {
        final X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(array);
        KeyFactory instance = null;
        try {
            instance = KeyFactory.getInstance(this.b);
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        try {
            return (RSAPublicKey)instance.generatePublic(x509EncodedKeySpec);
        }
        catch (InvalidKeySpecException ex2) {
            ex2.printStackTrace();
            return null;
        }
    }
    
    private RSAPrivateKey b(final byte[] array) throws InvalidKeySpecException {
        final PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(array);
        KeyFactory instance = null;
        try {
            instance = KeyFactory.getInstance(this.b);
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return (RSAPrivateKey)instance.generatePrivate(pkcs8EncodedKeySpec);
    }
    
    private RSAPrivateKey c(final String s) throws InvalidKeySpecException {
        try {
            return this.b(db.a(s));
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private String a(final String s, final PublicKey publicKey) {
        byte[] a = null;
        try {
            a = this.a(s.getBytes(eb.z[0]), publicKey);
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
        return db.a(a);
    }
    
    private byte[] a(final byte[] array, final PublicKey publicKey) throws Exception {
        byte[] doFinal;
        try {
            final Cipher instance = Cipher.getInstance(this.c);
            instance.init(1, publicKey);
            doFinal = instance.doFinal(array);
        }
        catch (Exception ex) {
            throw ex;
        }
        return doFinal;
    }
    
    static {
        final String[] z2 = new String[13];
        final int n = 0;
        final char[] charArray = "5\u0015;\t".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '`';
                    break;
                }
                case 1: {
                    c2 = 'A';
                    break;
                }
                case 2: {
                    c2 = '}';
                    break;
                }
                case 3: {
                    c2 = '1';
                    break;
                }
                default: {
                    c2 = 'X';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "2\u0012<".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '`';
                    break;
                }
                case 1: {
                    c4 = 'A';
                    break;
                }
                case 2: {
                    c4 = '}';
                    break;
                }
                case 3: {
                    c4 = '1';
                    break;
                }
                default: {
                    c4 = 'X';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "2\u0012<\u001e\u001d#\u0003Ra\u0013#\u0012La9\u0004%\u0014_?".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '`';
                    break;
                }
                case 1: {
                    c6 = 'A';
                    break;
                }
                case 2: {
                    c6 = '}';
                    break;
                }
                case 3: {
                    c6 = '1';
                    break;
                }
                default: {
                    c6 = 'X';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "%\u0013/~\nZa>^-\f%]_7\u0014a\u0018_;\u00128\rEx\u0003.\u0013E=\u000e5]F1\u0014)]B=\u00127\u0018Cx\u00104\u001f]1\u0003a\u0016T!N".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '`';
                    break;
                }
                case 1: {
                    c8 = 'A';
                    break;
                }
                case 2: {
                    c8 = '}';
                    break;
                }
                case 3: {
                    c8 = '1';
                    break;
                }
                default: {
                    c8 = 'X';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "%\u0013/~\nZa(_9\u0002-\u0018\u0011,\u000fa\u000eX?\u000ea\u0018_;\u00128\rE=\u0004a\u001e^6\u0014$\u0013Ev".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '`';
                    break;
                }
                case 1: {
                    c10 = 'A';
                    break;
                }
                case 2: {
                    c10 = '}';
                    break;
                }
                case 3: {
                    c10 = '1';
                    break;
                }
                default: {
                    c10 = 'X';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "3\t<\u0000/\t5\u0015c\u000b!".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '`';
                    break;
                }
                case 1: {
                    c12 = 'A';
                    break;
                }
                case 2: {
                    c12 = '}';
                    break;
                }
                case 3: {
                    c12 = '1';
                    break;
                }
                default: {
                    c12 = 'X';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "\u00011\r]=\u0014\u0011\u000fX.\u00015\u0018z=\u0019a@\u0011".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '`';
                    break;
                }
                case 1: {
                    c14 = 'A';
                    break;
                }
                case 2: {
                    c14 = '}';
                    break;
                }
                case 3: {
                    c14 = '1';
                    break;
                }
                default: {
                    c14 = 'X';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\u00104\u001f]1\u0003o\u0019T*".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '`';
                    break;
                }
                case 1: {
                    c16 = 'A';
                    break;
                }
                case 2: {
                    c16 = '}';
                    break;
                }
                case 3: {
                    c16 = '1';
                    break;
                }
                default: {
                    c16 = 'X';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z2[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "#.\b]<@/\u0012Ex\u00033\u0018P,\u0005a\rC1\u0016 \tTx\u000b$\u0004\u0011>\u000f3]B1\u0007/\u0014_?N".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '`';
                    break;
                }
                case 1: {
                    c18 = 'A';
                    break;
                }
                case 2: {
                    c18 = '}';
                    break;
                }
                case 3: {
                    c18 = '1';
                    break;
                }
                default: {
                    c18 = 'X';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z2[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "- \u0011W7\u0012,\u0018Ux5\u00131\u0011,\u000fa\u001cE,\u0005,\rEx\u0013\"\u000fX(\u0014a".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '`';
                    break;
                }
                case 1: {
                    c20 = 'A';
                    break;
                }
                case 2: {
                    c20 = '}';
                    break;
                }
                case 3: {
                    c20 = '1';
                    break;
                }
                default: {
                    c20 = 'X';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z2[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "5/\u001cS4\u0005a\t^x\u0006.\u000f\\x5\u00131\u0011,\u000fa\u000eT*\u0016$\u000f\u0011(\u0015#\u0011X;@*\u0018Hv".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '`';
                    break;
                }
                case 1: {
                    c22 = 'A';
                    break;
                }
                case 2: {
                    c22 = '}';
                    break;
                }
                case 3: {
                    c22 = '1';
                    break;
                }
                default: {
                    c22 = 'X';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z2[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "#.\b]<@/\u0012Ex\r \u0016Tx\u0013(\u001a_9\u00144\u000fTx\u000f#\u0017T;\u0014a\u001b^*@2\u0014V6\t/\u001a\u0011;\u000f/\tT6\u0014o]\u0011\u0011\u000e7\u001c]1\u0004a\rC1\u0016 \tTx\u000b$\u0004\u001fx".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '`';
                    break;
                }
                case 1: {
                    c24 = 'A';
                    break;
                }
                case 2: {
                    c24 = '}';
                    break;
                }
                case 3: {
                    c24 = '1';
                    break;
                }
                default: {
                    c24 = 'X';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z2[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "#.\b]<@/\u0012Ex\r \u0016Tx\u0013(\u001a_9\u00144\u000fTx\u000f#\u0017T;\u0014a\u001b^*@2\u0014V6\t/\u001a\u0011;\u000f/\tT6\u0014o]\u0011\u0016\u000fa\u000eD;\ba\u001c]?\u000f3\u0014E0\r{]".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '`';
                    break;
                }
                case 1: {
                    c26 = 'A';
                    break;
                }
                case 2: {
                    c26 = '}';
                    break;
                }
                case 3: {
                    c26 = '1';
                    break;
                }
                default: {
                    c26 = 'X';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z2[n37] = new String(charArray13).intern();
        z = z2;
    }
}
