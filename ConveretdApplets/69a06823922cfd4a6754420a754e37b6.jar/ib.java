import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ib extends hb
{
    private eb k;
    private static final String[] z;
    
    public ib(final String s, final int n, final String s2, final int n2, final int n3) {
        this(ib.z[9], s, n, s2, n2, n3);
    }
    
    public ib(final String s, final String s2, final int n, final String s3, final int n2, final int n3) {
        super(s, s2, n, s3, n2, n3, ib.z[8]);
    }
    
    public void a(final Applet applet, final String s) {
        final boolean a = db.a;
        this.k = new eb(applet, this.a(true).toString(), s);
        if (q.a != 0) {
            db.a = !a;
        }
    }
    
    public void a(final int n, final boolean b, final int n2) {
        if (this.k == null) {
            System.out.println(ib.z[4]);
        }
        final String a = this.k.a(this.a(n, b, n2));
        final String a2 = db.a(this.k.b(a));
        String encode;
        String encode2;
        try {
            encode = URLEncoder.encode(a, ib.z[7]);
            encode2 = URLEncoder.encode(a2, ib.z[7]);
        }
        catch (UnsupportedEncodingException ex) {
            System.out.println(ib.z[6]);
            ex.printStackTrace();
            return;
        }
        this.a(this.a(), ib.z[1] + encode + ib.z[5] + encode2 + ib.z[2] + this.g());
        System.out.println(ib.z[3] + encode + ib.z[5] + encode2 + ib.z[2] + this.g());
        System.out.println(ib.z[0]);
    }
    
    static {
        final String[] z2 = new String[10];
        final int n = 0;
        final char[] charArray = "Vi\u000f[\"}vF].p~\u0014K\nge\u0003B;g".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0013';
                    break;
                }
                case 1: {
                    c2 = '\u0011';
                    break;
                }
                case 2: {
                    c2 = 'f';
                    break;
                }
                case 3: {
                    c2 = '/';
                    break;
                }
                default: {
                    c2 = 'K';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "v\u007f\u0005]2ce\u0003K\b|\u007f\u0012J%g,".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0013';
                    break;
                }
                case 1: {
                    c4 = '\u0011';
                    break;
                }
                case 2: {
                    c4 = 'f';
                    break;
                }
                case 3: {
                    c4 = '/';
                    break;
                }
                default: {
                    c4 = 'K';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "5p\u000fKv".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0013';
                    break;
                }
                case 1: {
                    c6 = '\u0011';
                    break;
                }
                case 2: {
                    c6 = 'f';
                    break;
                }
                case 3: {
                    c6 = '/';
                    break;
                }
                default: {
                    c6 = 'K';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "@t\bK\"}vFL$}e\u0003A?)1\u0003A(ah\u0016[.wR\tA?v\u007f\u0012\u0012".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0013';
                    break;
                }
                case 1: {
                    c8 = '\u0011';
                    break;
                }
                case 2: {
                    c8 = 'f';
                    break;
                }
                case 3: {
                    c8 = '/';
                    break;
                }
                default: {
                    c8 = 'K';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "WT0\u000f\u000eAC)}q31(@kQ~\u0013A(jR\u0007\\?\u007ftFJ%pc\u001f_?z~\b\u000f$q{\u0003L?=1Fm.3b\u0013].3e\t\u000f(r}\n\u000f9va\t]?vcH\\.gB\u0003L>ax\u0012Vcgy\u000f\\b3x\u0000\u000f8v\u007f\u0002F%t1\u0003A(ah\u0016[.w1\u000bJ8`p\u0001J8=".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0013';
                    break;
                }
                case 1: {
                    c10 = '\u0011';
                    break;
                }
                case 2: {
                    c10 = 'f';
                    break;
                }
                case 3: {
                    c10 = '/';
                    break;
                }
                default: {
                    c10 = 'K';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "5b\u000fH%re\u0013]..".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0013';
                    break;
                }
                case 1: {
                    c12 = '\u0011';
                    break;
                }
                case 2: {
                    c12 = 'f';
                    break;
                }
                case 3: {
                    c12 = '/';
                    break;
                }
                default: {
                    c12 = 'K';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "F\u007f\u0007M'v1\u0012@kFC*j%p~\u0002Jkv\u007f\u0005]2ce\u0003KkGt\u001e[e".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0013';
                    break;
                }
                case 1: {
                    c14 = '\u0011';
                    break;
                }
                case 2: {
                    c14 = 'f';
                    break;
                }
                case 3: {
                    c14 = '/';
                    break;
                }
                default: {
                    c14 = 'K';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "FE \u0002s".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0013';
                    break;
                }
                case 1: {
                    c16 = '\u0011';
                    break;
                }
                case 2: {
                    c16 = 'f';
                    break;
                }
                case 3: {
                    c16 = '/';
                    break;
                }
                default: {
                    c16 = 'K';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z2[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "At\u0005@9wT\bL9ja\u0012J/Re\u0012J&ceH_#c$".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0013';
                    break;
                }
                case 1: {
                    c18 = '\u0011';
                    break;
                }
                case 2: {
                    c18 = 'f';
                    break;
                }
                case 3: {
                    c18 = '/';
                    break;
                }
                default: {
                    c18 = 'K';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z2[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "{e\u0012_8".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0013';
                    break;
                }
                case 1: {
                    c20 = '\u0011';
                    break;
                }
                case 2: {
                    c20 = 'f';
                    break;
                }
                case 3: {
                    c20 = '/';
                    break;
                }
                default: {
                    c20 = 'K';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z2[n28] = new String(charArray10).intern();
        z = z2;
    }
}
