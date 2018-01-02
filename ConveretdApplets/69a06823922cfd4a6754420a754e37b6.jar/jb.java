import java.util.StringTokenizer;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.net.URL;
import java.util.Hashtable;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jb
{
    private static final String[] z;
    
    public static Hashtable a(final Applet applet) {
        return a(applet.getClass().getResource(jb.z[0]));
    }
    
    private static Hashtable a(final URL url) {
        final boolean j = hb.j;
        final Hashtable hashtable = new Hashtable<String, String>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(url.openStream())));
        }
        catch (IOException ex) {
            System.out.println(jb.z[8] + url.toString() + jb.z[2]);
            ex.printStackTrace();
            return null;
        }
        catch (NullPointerException ex2) {
            System.out.println(jb.z[10]);
            ex2.printStackTrace();
            return null;
        }
        int n = 0;
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().length() > 0) {
                    ++n;
                    final StringTokenizer stringTokenizer = new StringTokenizer(line, jb.z[3]);
                    if (stringTokenizer.countTokens() != 2) {
                        System.out.println(jb.z[13] + url.toString() + jb.z[11]);
                        System.out.println(jb.z[14] + stringTokenizer.countTokens() + jb.z[9]);
                        System.out.println(line);
                        System.out.println(jb.z[12] + n);
                        System.out.println(jb.z[6]);
                        return null;
                    }
                    hashtable.put(stringTokenizer.nextToken().trim(), stringTokenizer.nextToken().trim());
                    if (j) {
                        break;
                    }
                    continue;
                }
            }
        }
        catch (IOException ex3) {
            System.out.println(jb.z[4] + url.toString());
            System.out.println(ex3.getMessage());
            ex3.printStackTrace();
            return null;
        }
        catch (Exception ex4) {
            System.out.println(ex4.getMessage());
            ex4.printStackTrace();
            return null;
        }
        try {
            bufferedReader.close();
        }
        catch (IOException ex5) {
            System.out.println(jb.z[5]);
            System.out.println(ex5.getMessage());
            ex5.printStackTrace();
            return null;
        }
        if (hashtable.isEmpty()) {
            System.out.println(jb.z[7]);
            return null;
        }
        if (hashtable.size() < 3) {
            System.out.println(jb.z[1]);
            return null;
        }
        return hashtable;
    }
    
    static {
        final String[] z2 = new String[15];
        final int n = 0;
        final char[] charArray = "i\u0007\u000e{i|\u0012\u0019hw7\u0012\u0004n".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = '\u0019';
                    break;
                }
                case 1: {
                    c2 = 'f';
                    break;
                }
                case 2: {
                    c2 = '|';
                    break;
                }
                case 3: {
                    c2 = '\u001a';
                    break;
                }
                default: {
                    c2 = '\u0004';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z2[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "]#*:AK43H>91\u0015~pqJ\\rap\u0001\u0014n(9\u0007\u0012~$m\u000f\bva9\u0016\u001dhet\u0003\b\u007fvjF\u001dha9\u0014\u0019kqp\u0014\u0019~$p\b\\jek\u0007\u0011\u007fp|\u0014\u000f4pa\u0012R:$I\u0003\u001dia9\u0005\u0014\u007fgrF\u0005uqkF\f{vx\u000b\u0019nak\u0015Rn|mF\u001ash|H".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = '\u0019';
                    break;
                }
                case 1: {
                    c4 = 'f';
                    break;
                }
                case 2: {
                    c4 = '|';
                    break;
                }
                case 3: {
                    c4 = '\u001a';
                    break;
                }
                default: {
                    c4 = '\u0004';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z2[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "9\u0005\u001dtjv\u0012\\xa9\u0000\u0013oj}H\\:V|\u0012\thjp\b\u001b:jl\n\u0010:tx\u0014\u001dwam\u0003\u000e:w|\u0012R:$I\n\u0019{w|F\u000f\u007fa9,\u001dle9%\u0013twv\n\u00194".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = '\u0019';
                    break;
                }
                case 1: {
                    c6 = 'f';
                    break;
                }
                case 2: {
                    c6 = '|';
                    break;
                }
                case 3: {
                    c6 = '\u001a';
                    break;
                }
                default: {
                    c6 = '\u0004';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z2[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "$o".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = '\u0019';
                    break;
                }
                case 1: {
                    c8 = 'f';
                    break;
                }
                case 2: {
                    c8 = '|';
                    break;
                }
                case 3: {
                    c8 = '\u001a';
                    break;
                }
                default: {
                    c8 = '\u0004';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z2[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "\\4.UV#F,{vj\u000f\u0012}$}\u0007\b{$\u007f\u000f\u0010\u007f$".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = '\u0019';
                    break;
                }
                case 1: {
                    c10 = 'f';
                    break;
                }
                case 2: {
                    c10 = '|';
                    break;
                }
                case 3: {
                    c10 = '\u001a';
                    break;
                }
                default: {
                    c10 = '\u0004';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z2[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\\\u0014\u000euv9\u0005\u0010uwp\b\u001b:qk\n\\ipk\u0003\u001dw*".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = '\u0019';
                    break;
                }
                case 1: {
                    c12 = 'f';
                    break;
                }
                case 2: {
                    c12 = '|';
                    break;
                }
                case 3: {
                    c12 = '\u001a';
                    break;
                }
                default: {
                    c12 = '\u0004';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z2[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "J\u0003\u0019:lp\u0015\buck\u0007\u00115tx\u0014\u001dwam\u0003\u000ei*m\u001e\b:bv\u0014\\{j9\u0003\u0004{ii\n\u00194".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = '\u0019';
                    break;
                }
                case 1: {
                    c14 = 'f';
                    break;
                }
                case 2: {
                    c14 = '|';
                    break;
                }
                case 3: {
                    c14 = '\u001a';
                    break;
                }
                default: {
                    c14 = '\u0004';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z2[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "]#*:AK43H>9F%uqkF\f{vx\u000b\u0019nak\u0015Rn|mF\u001ash|F\u001fujm\u0007\u0015ta}F\u0012u$r\u0003\u0005'rx\n\t\u007f$i\u0007\u0015hw7F\\Jh|\u0007\u000f\u007f$z\u000e\u0019yo9\u001f\u0013ov9\u0016\u001dhet\u0003\b\u007fvjH\bbp9\u0000\u0015va7".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = '\u0019';
                    break;
                }
                case 1: {
                    c16 = 'f';
                    break;
                }
                case 2: {
                    c16 = '|';
                    break;
                }
                case 3: {
                    c16 = '\u001a';
                    break;
                }
                default: {
                    c16 = '\u0004';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z2[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "M\u000e\u0019:bp\n\u0019 $".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = '\u0019';
                    break;
                }
                case 1: {
                    c18 = 'f';
                    break;
                }
                case 2: {
                    c18 = '|';
                    break;
                }
                case 3: {
                    c18 = '\u001a';
                    break;
                }
                default: {
                    c18 = '\u0004';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z2[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "9\u0016\u001dhet\u0003\b\u007fvjH".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = '\u0019';
                    break;
                }
                case 1: {
                    c20 = 'f';
                    break;
                }
                case 2: {
                    c20 = '|';
                    break;
                }
                case 3: {
                    c20 = '\u001a';
                    break;
                }
                default: {
                    c20 = '\u0004';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z2[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "M\u000e\u0019:bp\n\u0019 $i\u0007\u000e{i|\u0012\u0019hw7\u0012\u0004n$z\u0007\u0012tkmF\u001e\u007f$\u007f\t\tt`7F\\Ham\u0013\u000etmw\u0001\\tqu\n\\jek\u0007\u0011\u007fp|\u0014\\iamH\\:Tu\u0003\u001dia9\u0015\u0019\u007f$S\u0007\n{$Z\t\u0012iku\u0003R".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = '\u0019';
                    break;
                }
                case 1: {
                    c22 = 'f';
                    break;
                }
                case 2: {
                    c22 = '|';
                    break;
                }
                case 3: {
                    c22 = '\u001a';
                    break;
                }
                default: {
                    c22 = '\u0004';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z2[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "9\u000b\tip9\u0004\u0019:mwF\u0017\u007f}4\u0010\u001dvq|F\f{mk\u0015P:kw\u0003\\jep\u0014\\jakF\u0010sj|J\\mmm\u000e\\nl|F\u0017\u007f}9\u0007\u0012~$o\u0007\u0010oa9\u0015\u0019jek\u0007\b\u007f`9\u0004\u0005:e9\u0012\u001dx*".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = '\u0019';
                    break;
                }
                case 1: {
                    c24 = 'f';
                    break;
                }
                case 2: {
                    c24 = '|';
                    break;
                }
                case 3: {
                    c24 = '\u001a';
                    break;
                }
                default: {
                    c24 = '\u0004';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z2[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "V\b\\vmw\u0003\\tqt\u0004\u0019h>9".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = '\u0019';
                    break;
                }
                case 1: {
                    c26 = 'f';
                    break;
                }
                case 2: {
                    c26 = '|';
                    break;
                }
                case 3: {
                    c26 = '\u001a';
                    break;
                }
                default: {
                    c26 = '\u0004';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z2[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "I\u0007\u000e{i|\u0012\u0019hw9\u000f\u0012:pq\u0003\\|mu\u0003\\".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = '\u0019';
                    break;
                }
                case 1: {
                    c28 = 'f';
                    break;
                }
                case 2: {
                    c28 = '|';
                    break;
                }
                case 3: {
                    c28 = '\u001a';
                    break;
                }
                default: {
                    c28 = '\u0004';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        z2[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "M\u000e\u0019:bv\n\u0010usp\b\u001b:hp\b\u0019:mjF\u0015t$m\u000e\u0019:mw\u0005\u0013hv|\u0005\b:bv\u0014\u0011{p9\u0011\u0015nl9".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = '\u0019';
                    break;
                }
                case 1: {
                    c30 = 'f';
                    break;
                }
                case 2: {
                    c30 = '|';
                    break;
                }
                case 3: {
                    c30 = '\u001a';
                    break;
                }
                default: {
                    c30 = '\u0004';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        z2[n43] = new String(charArray15).intern();
        z = z2;
    }
}
