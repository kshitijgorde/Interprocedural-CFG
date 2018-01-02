// 
// Decompiled by Procyon v0.5.30
// 

package a.b.n;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class a
{
    private static boolean a;
    private static boolean b;
    private static long c;
    private long d;
    private String e;
    static /* synthetic */ Class f;
    private static String[] z;
    
    protected abstract String a();
    
    protected abstract Class b();
    
    protected String c() {
        return a.b.n.a.z[0];
    }
    
    protected long d() {
        return 30000L;
    }
    
    public void a(final boolean b) {
        if (a.b.n.a.a) {
            return;
        }
        a.b.n.a.a = true;
        final InputStream resourceAsStream = this.b().getResourceAsStream(this.a());
        Properties properties = null;
        a.b.n.a.b = false;
        if (resourceAsStream != null) {
            properties = new Properties();
            try {
                properties.load(resourceAsStream);
            }
            catch (IOException ex) {
                properties = null;
            }
        }
        if (properties != null) {
            a.b.n.a.b = new Boolean(properties.getProperty(a.b.n.a.z[6]));
        }
        if (a.b.n.a.b) {
            final b b2 = new b(this.a());
            b2.b(properties.getProperty(a.b.n.a.z[3]));
            a.b.g.b.a().a(b2);
            a.b.n.a.c = System.currentTimeMillis();
            this.d();
            long d;
            try {
                d = Integer.parseInt(properties.getProperty(a.b.n.a.z[1]));
            }
            catch (NumberFormatException ex2) {
                d = this.d();
            }
            this.d = d;
            this.e = properties.getProperty(a.b.n.a.z[4]);
            if (this.e == null || this.e.length() == 0) {
                this.e = this.c();
            }
            b2.a((Object)((a.b.n.a.f == null) ? (a.b.n.a.f = a(a.b.n.a.z[5])) : a.b.n.a.f).getName()).a(a.b.n.a.z[2]);
            if (b) {
                this.e();
            }
        }
    }
    
    protected void e() {
        if (a.b.n.a.b) {
            final e e = new e(a.b.g.b.a().c().a(), this.e, a.b.n.a.c, this.d, this.a(), this.b());
            e.setPriority(e.getPriority() - 2);
            e.start();
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z = new String[7];
        final int n = 0;
        final char[] charArray = "\u0012-Zx9\u0002".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'g';
                    break;
                }
                case 1: {
                    c2 = ']';
                    break;
                }
                case 2: {
                    c2 = '>';
                    break;
                }
                case 3: {
                    c2 = '\u0019';
                    break;
                }
                default: {
                    c2 = 'M';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "+2Y~(\u0015\u001eQw+\u000e:Kk,\u00134Qwc2-Zx9\u0002\r[k$\b9".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'g';
                    break;
                }
                case 1: {
                    c4 = ']';
                    break;
                }
                case 2: {
                    c4 = '>';
                    break;
                }
                case 3: {
                    c4 = '\u0019';
                    break;
                }
                default: {
                    c4 = 'M';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "+2Y~$\t:\u001ep>G)Kk#\u00029\u001eV\u0003I".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'g';
                    break;
                }
                case 1: {
                    c6 = ']';
                    break;
                }
                case 2: {
                    c6 = '>';
                    break;
                }
                case 3: {
                    c6 = '\u0019';
                    break;
                }
                default: {
                    c6 = 'M';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "+2Y~(\u0015\u001eQw+\u000e:Kk,\u00134Qwc+2YP)7/[".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'g';
                    break;
                }
                case 1: {
                    c8 = ']';
                    break;
                }
                case 2: {
                    c8 = '>';
                    break;
                }
                case 3: {
                    c8 = '\u0019';
                    break;
                }
                default: {
                    c8 = 'M';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "+2Y~(\u0015\u001eQw+\u000e:Kk,\u00134Qwc2-Zx9\u0002\tVk(\u00069px \u0002".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'g';
                    break;
                }
                case 1: {
                    c10 = ']';
                    break;
                }
                case 2: {
                    c10 = '>';
                    break;
                }
                case 3: {
                    c10 = '\u0019';
                    break;
                }
                default: {
                    c10 = 'M';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\u0006s\\7#I<".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'g';
                    break;
                }
                case 1: {
                    c12 = ']';
                    break;
                }
                case 2: {
                    c12 = '>';
                    break;
                }
                case 3: {
                    c12 = '\u0019';
                    break;
                }
                default: {
                    c12 = 'M';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "+2Y~(\u0015\u001eQw+\u000e:Kk,\u00134Qwc..rv*\u00004P~".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'g';
                    break;
                }
                case 1: {
                    c14 = ']';
                    break;
                }
                case 2: {
                    c14 = '>';
                    break;
                }
                case 3: {
                    c14 = '\u0019';
                    break;
                }
                default: {
                    c14 = 'M';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        a.b.n.a.z = z;
        a.b.n.a.b = false;
        a.b.n.a.c = 0L;
    }
}
