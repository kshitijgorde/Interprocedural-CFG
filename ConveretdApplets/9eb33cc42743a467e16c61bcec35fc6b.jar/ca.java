import java.util.Random;
import java.math.BigInteger;
import java.io.IOException;
import mindbright.security.KeyPair;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class ca
{
    public static boolean mo;
    public static boolean mn;
    public static final String[][] mm;
    public static final String[] ml;
    public static final int mk;
    public static final String[] mj;
    public static final int[] mi;
    public static final int mh;
    public static cd mg;
    public byte[] mf;
    public byte[] me;
    public ci f7;
    public ci mc;
    public int g6;
    public byte[] mb;
    public KeyPair ma;
    public KeyPair l9;
    public int l8;
    public int l7;
    public int l6;
    public boolean l5;
    
    public static String ms(final boolean b) {
        return String.valueOf(new StringBuffer("SSH-").append(1).append(".").append(5).append("-").toString()) + (b ? "MindTerm v1.2.1" : "MindTunnel v1.2.1");
    }
    
    public static int mr(final String s) throws IllegalArgumentException {
        int n;
        for (n = 0; n < ca.mj.length && !ca.mj[n].equalsIgnoreCase(s); ++n) {}
        if (n == ca.mh) {
            throw new IllegalArgumentException("Proxytype " + s + " not supported");
        }
        return n;
    }
    
    public static String mq() {
        String string = "";
        for (int i = 0; i < ca.mj.length; ++i) {
            string = String.valueOf(string) + ca.mj[i] + " ";
        }
        return string;
    }
    
    public static int mp(final String s) {
        int i = 0;
        while (i < ca.mm.length) {
            final String s2 = ca.mm[i][0];
            if (ca.mm[i][1].equalsIgnoreCase(s)) {
                if (ca.mm[i][0] == null) {
                    i = ca.mm.length;
                    break;
                }
                break;
            }
            else {
                ++i;
            }
        }
        return i;
    }
    
    public static int mo(final String s) throws IllegalArgumentException {
        int n;
        for (n = 1; n < ca.ml.length && !ca.ml[n].equalsIgnoreCase(s); ++n) {}
        if (n == ca.mk) {
            throw new IllegalArgumentException("Authtype " + s + " not supported");
        }
        return n;
    }
    
    public static int mn(final String s) {
        int n = 1;
        int index;
        for (int n2 = 0; n2 < s.length() && (index = s.indexOf(44, n2)) != -1; n2 = index + 1, ++n) {}
        return n;
    }
    
    public static int[] gj(final String s) throws IllegalArgumentException {
        final int mn = mn(s);
        final int[] array = new int[mn];
        int n = 0;
        for (int i = 0; i < mn; ++i) {
            int n2 = s.indexOf(44, n);
            if (n2 == -1) {
                n2 = s.length();
            }
            array[i] = mo(s.substring(n, n2).trim());
            n = n2 + 1;
        }
        return array;
    }
    
    public static String mm() {
        String string = "";
        for (int i = 0; i < ca.mm.length; ++i) {
            if (ca.mm[i][0] != null) {
                string = String.valueOf(string) + ca.mm[i][1] + " ";
            }
        }
        return string;
    }
    
    public static String[] ml() {
        int n = 0;
        for (int i = 0; i < ca.mm.length; ++i) {
            if (ca.mm[i][0] != null) {
                ++n;
            }
        }
        final String[] array = new String[n];
        int n2 = 0;
        for (int j = 0; j < ca.mm.length; ++j) {
            if (ca.mm[j][0] != null) {
                array[n2++] = ca.mm[j][1];
            }
        }
        return array;
    }
    
    public static String mk() {
        String string = "";
        for (int i = 1; i < ca.ml.length; ++i) {
            string = String.valueOf(string) + ca.ml[i] + " ";
        }
        return string;
    }
    
    public final boolean mj(final int n) {
        return (1 << n & this.l7) != 0x0;
    }
    
    public final boolean mi(final int n) {
        return (1 << n & this.l6) != 0x0;
    }
    
    public final boolean mh(final int n) {
        return (1 << n & this.l8) != 0x0;
    }
    
    public static void mg() {
        if (ca.mg != null) {
            return;
        }
        ca.mg = new cd();
    }
    
    public static cd mf() {
        if (ca.mg == null) {
            ca.mg = new cd();
        }
        return ca.mg;
    }
    
    public static void me(final String s) {
        if (ca.mo) {
            System.out.println(s);
        }
    }
    
    public static void md(final String s) {
        if (ca.mn) {
            System.out.println(s);
        }
    }
    
    public static void mc(final String s) {
        if (ca.mo) {
            System.out.println(s);
        }
    }
    
    public static void mb(final ax ax) {
        if (ca.mo) {
            System.out.println("MSG_IGNORE received...(len = " + ax.h5 + ")");
        }
    }
    
    public final void ma() throws IOException {
        final byte[] byteArray = this.ma.fs().m2().toByteArray();
        final byte[] byteArray2 = this.l9.fs().m2().toByteArray();
        int n = byteArray.length + byteArray2.length + this.mb.length;
        if (byteArray[0] == 0) {
            --n;
        }
        if (byteArray2[0] == 0) {
            --n;
        }
        final byte[] array = new byte[n];
        int length;
        if (byteArray2[0] == 0) {
            System.arraycopy(byteArray2, 1, array, 0, byteArray2.length - 1);
            length = byteArray2.length - 1;
        }
        else {
            System.arraycopy(byteArray2, 0, array, 0, byteArray2.length);
            length = byteArray2.length;
        }
        int n2;
        if (byteArray[0] == 0) {
            System.arraycopy(byteArray, 1, array, length, byteArray.length - 1);
            n2 = length + (byteArray.length - 1);
        }
        else {
            System.arraycopy(byteArray, 0, array, length, byteArray.length);
            n2 = length + byteArray.length;
        }
        System.arraycopy(this.mb, 0, array, n2, this.mb.length);
        try {
            final ch ne = ch.ne("MD5");
            ne.c3(array);
            this.me = ne.nd();
        }
        catch (Exception ex) {
            throw new IOException("MD5 not implemented, can't generate session-id");
        }
    }
    
    public final void l9() throws IOException {
        this.l8(false);
    }
    
    public final void l8(final boolean b) throws IOException {
        this.f7 = ci.ne(ca.mm[this.g6][0]);
        this.mc = ci.ne(ca.mm[this.g6][0]);
        if (this.f7 == null) {
            throw new IOException("Cipher " + ca.mm[this.g6][1] + " not found, can't use it");
        }
        if (this.g6 == 5) {
            if (b) {
                final int n = this.mf.length / 2;
                final byte[] array = new byte[n];
                System.arraycopy(this.mf, 0, array, 0, n);
                this.f7.m9(array);
                System.arraycopy(this.mf, n, array, 0, n);
                this.mc.m9(array);
            }
            else {
                final int n2 = this.mf.length / 2;
                final byte[] array2 = new byte[n2];
                System.arraycopy(this.mf, 0, array2, 0, n2);
                this.mc.m9(array2);
                System.arraycopy(this.mf, n2, array2, 0, n2);
                this.f7.m9(array2);
            }
        }
        else {
            this.f7.m9(this.mf);
            this.mc.m9(this.mf);
        }
    }
    
    public static String l7(final KeyPair keyPair, final String s, final String s2, final String s3) throws IOException {
        an.fu(keyPair, s2, s, s3);
        final ce ce = keyPair.fs();
        final al al = new al("", s3, ce.m3(), ce.m2());
        al.fj(String.valueOf(s) + ".pub");
        return al.toString();
    }
    
    public static KeyPair l6(final int n, final cd cd) {
        final BigInteger bigInteger = new BigInteger("1");
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        while (true) {
            final int mu = cd.mu;
            cd.mu = 2;
            bigInteger2 = new BigInteger(n / 2, 64, cd);
            bigInteger3 = new BigInteger(n - n / 2, 64, cd);
            cd.mu = mu;
            if (bigInteger2.compareTo(bigInteger3) == 0) {
                continue;
            }
            if (bigInteger3.compareTo(bigInteger2) < 0) {
                final BigInteger bigInteger4 = bigInteger3;
                bigInteger3 = bigInteger2;
                bigInteger2 = bigInteger4;
            }
            if (bigInteger2.gcd(bigInteger3).compareTo(bigInteger) == 0) {
                break;
            }
        }
        final BigInteger subtract = bigInteger2.subtract(bigInteger);
        final BigInteger subtract2 = bigInteger3.subtract(bigInteger);
        final BigInteger multiply = subtract.multiply(subtract2);
        multiply.divide(subtract.gcd(subtract2));
        BigInteger bigInteger5 = bigInteger.shiftLeft(5).subtract(bigInteger);
        do {
            bigInteger5 = bigInteger5.add(bigInteger.add(bigInteger));
        } while (bigInteger5.gcd(multiply).compareTo(bigInteger) != 0);
        final BigInteger modInverse = bigInteger5.modInverse(multiply);
        final BigInteger multiply2 = bigInteger2.multiply(bigInteger3);
        return new KeyPair(new ce(bigInteger5, multiply2), new cf(bigInteger5, multiply2, modInverse, bigInteger2.modInverse(bigInteger3), bigInteger2, bigInteger3));
    }
    
    public ca() {
        this.l5 = true;
    }
    
    static {
        mm = new String[][] { { "NoEncrypt", "none" }, { "IDEA", "idea" }, { "DES", "des" }, { "DES3", "3des" }, { null, "tss" }, { "RC4", "rc4" }, { "Blowfish", "blowfish" }, { null, "reserved" } };
        ml = new String[] { "_N/A_", "rhosts", "rsa", "passwd", "rhostsrsa", "tis", "kerberos", "kerbtgt", "sdi-token" };
        mk = ca.ml.length;
        mj = new String[] { "none", "http", "socks4", "socks5-proxy-dns", "socks5-local-dns" };
        mi = new int[] { 0, 8080, 1080, 1080, 1080 };
        mh = ca.mj.length;
    }
}
