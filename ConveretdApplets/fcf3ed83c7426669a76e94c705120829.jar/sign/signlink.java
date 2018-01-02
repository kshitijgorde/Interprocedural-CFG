// 
// Decompiled by Procyon v0.5.30
// 

package sign;

import java.io.RandomAccessFile;
import java.io.File;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.applet.Applet;
import java.awt.EventQueue;
import java.util.Hashtable;

public final class signlink
{
    public static final int[] ports;
    public static final String[] o;
    public static String host;
    public static boolean oldgame;
    public static boolean c;
    private static int b;
    private static Hashtable e;
    private static String t;
    private static boolean m;
    private static String k;
    public static EventQueue r;
    public static int p;
    public static fileondisk n;
    public static fileondisk i;
    public static final fileondisk[] a;
    public static fileondisk h;
    public static fileondisk l;
    public static boolean f;
    public static String name;
    public static Applet s;
    public static int q;
    public static int g;
    public static int d;
    public static int j;
    private static final String[] z;
    
    public static void init(int n, int n2, final Applet s, String a, String a2, final boolean oldgame) {
        final boolean a3 = fileondisk.a;
        signlink.s = s;
        a = a(a);
        a2 = a(a2);
        final int n3 = n2;
        if (a3 || n3 < 0) {
            n2 = n3;
            goto Label_0045;
        }
        final int n5;
        final int n4 = n5 = n2;
        if (!a3 && n4 >= signlink.o.length) {
            goto Label_0043;
        }
        Label_0066: {
            final int n6;
            if (!a3) {
                if (n4 >= 0) {
                    n6 = n;
                    if (!a3) {
                        if (n6 <= 2) {
                            break Label_0066;
                        }
                    }
                }
            }
            n = n6;
        }
        final String s2 = a;
        Label_0093: {
            if (a3) {
                break Label_0093;
            }
            if (s2 == null) {
                a = signlink.o[n2];
            }
            signlink.host = a;
            signlink.c = false;
            try {
                a.toLowerCase();
                final String s3 = s2;
                final boolean equals = s3.equals(signlink.z[20]);
                Label_0180: {
                    if (!a3) {
                        if (!equals) {
                            final boolean equals2 = s3.equals(signlink.z[16]);
                            if (!a3) {
                                if (!equals2) {
                                    final boolean equals3 = s3.equals(signlink.z[14]);
                                    if (!a3) {
                                        if (!equals3) {
                                            final boolean equals4 = InetAddress.getByName(a).getHostAddress().equals(signlink.z[19]);
                                            if (!a3) {
                                                if (!equals4) {
                                                    break Label_0180;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    signlink.c = equals;
                }
            }
            catch (Exception ex) {}
        }
        final String s4 = a2;
        int c = 0;
        Label_0228: {
            Label_0214: {
                if (!a3) {
                    if (s4 != null) {
                        final boolean b = (c = (signlink.c ? 1 : 0)) != 0;
                        if (a3) {
                            break Label_0228;
                        }
                        if (!b) {
                            break Label_0214;
                        }
                    }
                    final String s5 = signlink.z[21];
                }
                a2 = s4;
            }
            signlink.oldgame = oldgame;
            signlink.name = a2;
            c = 32 + n;
        }
        signlink.p = c;
        a(signlink.z[17], 32 + n);
        b();
        try {
            signlink.r = Toolkit.getDefaultToolkit().getSystemEventQueue();
        }
        catch (Throwable t) {}
        Label_0439: {
            try {
                ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                do {
                    final ThreadGroup parent = threadGroup.getParent();
                    if (parent == null) {
                        if (a3) {
                            continue;
                        }
                        if (!a3) {
                            break;
                        }
                    }
                    threadGroup = parent;
                } while (!a3);
                final int activeCount = threadGroup.activeCount();
                if (a3 || activeCount > 0) {
                    final Thread[] array = new Thread[activeCount];
                    final int enumerate;
                    final int n7 = enumerate = threadGroup.enumerate(array);
                    if (a3 || enumerate > 0) {
                        int i = enumerate;
                        while (i != n7) {
                            final Thread thread = array[i];
                            if (a3) {
                                break Label_0439;
                            }
                            final Thread thread2 = thread;
                            Label_0422: {
                                if (!a3) {
                                    if (thread2 == null) {
                                        if (!a3) {
                                            break Label_0422;
                                        }
                                    }
                                }
                                try {
                                    final String name = thread2.getName();
                                    if ((a3 || name != null) && name.startsWith(signlink.z[12])) {
                                        thread.setPriority(1);
                                    }
                                }
                                catch (Throwable t2) {}
                            }
                            ++i;
                            if (a3) {
                                break;
                            }
                        }
                    }
                }
            }
            catch (Throwable t3) {}
            signlink.f = false;
            try {
                final String property;
                String s6 = property = System.getProperty(signlink.z[18]);
                if (!a3) {
                    if (property == null) {
                        return;
                    }
                    final String lowerCase;
                    s6 = (lowerCase = s6.toLowerCase());
                }
                final int index = property.indexOf(signlink.z[15]);
                if (!a3) {
                    if (index == -1) {
                        final int index2 = s6.indexOf(signlink.z[13]);
                        if (!a3) {
                            if (index2 == -1) {
                                return;
                            }
                        }
                    }
                }
                signlink.f = (index != 0);
            }
            catch (Throwable t4) {}
        }
    }
    
    public static String a(String trim) {
        final boolean a = fileondisk.a;
        final String s = trim;
        if (a || s != null) {
            final int length = s.length();
            if (!a) {
                if (length == 0) {
                    return null;
                }
                trim = trim.trim();
                trim.length();
            }
            return (length == 0) ? null : trim;
        }
        return null;
    }
    
    public static synchronized void a() {
        final boolean a = fileondisk.a;
        final fileondisk n = signlink.n;
        Label_0021: {
            if (a) {
                break Label_0021;
            }
            if (n == null) {
                break Label_0021;
            }
            signlink.n = null;
            try {
                n.b();
            }
            catch (Exception ex) {}
        }
        int i = 0;
        while (true) {
            while (i != signlink.a.length) {
                fileondisk fileondisk = signlink.a[i];
                if (!a) {
                    fileondisk fileondisk5;
                    fileondisk fileondisk4;
                    fileondisk fileondisk3;
                    final fileondisk fileondisk2 = fileondisk3 = (fileondisk4 = (fileondisk5 = fileondisk));
                    if (a) {
                        if (!a) {
                            if (fileondisk2 != null) {
                                signlink.i = null;
                                try {
                                    fileondisk.b();
                                }
                                catch (Exception ex2) {}
                            }
                            fileondisk = signlink.h;
                            fileondisk4 = (fileondisk3 = (fileondisk5 = fileondisk));
                        }
                        if (!a) {
                            if (fileondisk3 != null) {
                                signlink.h = null;
                                try {
                                    fileondisk.b();
                                }
                                catch (Exception ex3) {}
                            }
                            fileondisk = signlink.l;
                            fileondisk5 = (fileondisk4 = fileondisk);
                        }
                        Label_0142: {
                            if (a) {
                                break Label_0142;
                            }
                            if (fileondisk4 == null) {
                                return;
                            }
                            signlink.l = null;
                            try {
                                fileondisk5 = fileondisk;
                                fileondisk5.b();
                            }
                            catch (Exception ex4) {}
                        }
                        return;
                    }
                    if (fileondisk2 != null) {
                        signlink.a[i] = null;
                        try {
                            fileondisk.b();
                        }
                        catch (Exception ex5) {}
                    }
                    ++i;
                }
                if (a) {
                    break;
                }
            }
            fileondisk fileondisk = signlink.i;
            fileondisk fileondisk5;
            fileondisk fileondisk4;
            final fileondisk fileondisk6;
            fileondisk fileondisk3 = fileondisk6 = (fileondisk4 = (fileondisk5 = fileondisk));
            continue;
        }
    }
    
    public static void c(final String s) {
        System.out.println(s);
    }
    
    public static void b(final String s) {
        final int p = signlink.p;
        final int n = 33;
        Label_0022: {
            if (!fileondisk.a) {
                if (p == n) {
                    break Label_0022;
                }
                final int p2 = signlink.p;
            }
            if (p != n) {
                return;
            }
        }
        System.out.println(s);
    }
    
    public static synchronized void b() {
        final boolean a = fileondisk.a;
        a();
        try {
            signlink.n = new fileondisk(getFile(signlink.z[24]), signlink.z[0], 33554432L);
        }
        catch (Exception ex) {}
        int i = 0;
        Label_0136: {
            while (i < signlink.a.length) {
                try {
                    signlink.a[i] = new fileondisk(getFile(signlink.z[27] + i), signlink.z[0], 1048576L);
                    if (a) {
                        break Label_0136;
                    }
                }
                catch (Exception ex2) {}
                ++i;
                if (a) {
                    break;
                }
            }
            try {
                signlink.i = new fileondisk(getFile(signlink.z[25]), signlink.z[0], 10000L);
            }
            catch (Exception ex3) {}
        }
        try {
            signlink.l = new fileondisk(a(signlink.z[26], null), signlink.z[0], 10000L);
        }
        catch (Exception ex4) {}
        try {
            signlink.h = a("", signlink.name.trim().replace(' ', '_').toLowerCase(), signlink.p);
        }
        catch (Exception ex5) {}
    }
    
    public static synchronized File a(final int n, final String s, final String s2) {
        final boolean a = fileondisk.a;
        try {
            if (!signlink.m) {
                throw new RuntimeException("");
            }
            final File file = signlink.e.get(s);
            if (!a && file == null) {
                final String[] array = { signlink.z[11], signlink.z[10], signlink.z[4], signlink.z[8], signlink.z[1], signlink.t, signlink.z[5], "" };
                final String[] array2 = { signlink.z[28] + n, signlink.z[29] + n };
                int n2 = 0;
                do {
                    int i = 0;
                Label_0177:
                    while (i < 2) {
                        int n3 = 0;
                    Label_0560_Outer:
                        while (array2.length > n3) {
                            i = 0;
                            if (!a) {
                                int j = i;
                                while (true) {
                                    while (j < array.length) {
                                        if (!a) {
                                            final StringBuilder append = new StringBuilder().append(array[j]).append(array2[n3]).append("/");
                                            String string = s2;
                                            if (!a) {
                                                if (s2 == null) {
                                                    string = "";
                                                }
                                                else {
                                                    string = s2 + "/";
                                                }
                                            }
                                            final String string2 = append.append(string).append(s).toString();
                                            RandomAccessFile randomAccessFile = null;
                                            try {
                                                final File file2 = new File(string2);
                                                final int n4 = n2;
                                                Label_0521: {
                                                    Label_0318: {
                                                        if (!a) {
                                                            if (n4 != 0) {
                                                                break Label_0318;
                                                            }
                                                            file2.exists();
                                                        }
                                                        if (n4 == 0) {
                                                            break Label_0521;
                                                        }
                                                    }
                                                    final String s3 = array[j];
                                                    final int n5 = n2;
                                                    Label_0405: {
                                                        if (!a) {
                                                            if (n5 == 1) {
                                                                final int length = s3.length();
                                                                if (a) {
                                                                    break Label_0405;
                                                                }
                                                                if (length > 0) {
                                                                    final boolean exists = new File(s3).exists();
                                                                    if (a) {
                                                                        break Label_0405;
                                                                    }
                                                                    if (!exists) {
                                                                        break Label_0521;
                                                                    }
                                                                }
                                                            }
                                                            new File(array[j] + array2[n3]).mkdir();
                                                        }
                                                    }
                                                    if (!a) {
                                                        if (s2 != null) {
                                                            new File(array[j] + array2[n3] + "/" + s2).mkdir();
                                                        }
                                                    }
                                                    randomAccessFile = new RandomAccessFile(file2, signlink.z[0]);
                                                    final int read = randomAccessFile.read();
                                                    randomAccessFile.seek(0L);
                                                    randomAccessFile.write(read);
                                                    randomAccessFile.seek(0L);
                                                    randomAccessFile.close();
                                                    signlink.e.put(s, file2);
                                                    return file2;
                                                }
                                            }
                                            catch (Exception ex2) {
                                                try {
                                                    final RandomAccessFile randomAccessFile2 = randomAccessFile;
                                                    if (a || randomAccessFile2 != null) {
                                                        randomAccessFile2.close();
                                                    }
                                                }
                                                catch (Exception ex3) {}
                                            }
                                            ++j;
                                            if (a) {
                                                break;
                                            }
                                            continue Label_0560_Outer;
                                        }
                                        else {
                                            if (a) {
                                                break Label_0560_Outer;
                                            }
                                            continue Label_0560_Outer;
                                        }
                                    }
                                    ++n3;
                                    continue;
                                }
                            }
                            continue Label_0177;
                        }
                        ++n2;
                    }
                    break;
                } while (!a);
                throw new RuntimeException();
            }
            return file;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static void a(final String k, final int b) {
        final boolean a = fileondisk.a;
        try {
            signlink.b = b;
            signlink.k = k;
            try {
                signlink.t = System.getProperty(signlink.z[23]);
                final String t = signlink.t;
                Label_0058: {
                    if (!a) {
                        if (t == null) {
                            break Label_0058;
                        }
                        new StringBuilder().append(signlink.t).append("/").toString();
                    }
                    signlink.t = t;
                }
            }
            catch (Exception ex2) {}
            signlink.m = true;
            final String t2 = signlink.t;
            if (!a) {
                if (t2 != null && !a) {
                    return;
                }
                final String s = signlink.z[22];
            }
            signlink.t = t2;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static File b(final String s, final String s2) {
        try {
            return a(signlink.b, s, s2);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static File getFile(final String s) {
        try {
            return b(s, signlink.k);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static File a(final String s, final String s2) {
        try {
            return b(s, s2);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static final fileondisk a(final String s, final String s2, final int n) {
        final boolean a = fileondisk.a;
        int m;
        int n3;
        final int n2 = n3 = (m = (signlink.m ? 1 : 0));
        if (!a) {
            if (n2 == 0) {
                throw new RuntimeException("");
            }
            m = n;
            n3 = n;
        }
        final int n4 = 33;
        String s3 = null;
        Label_0200: {
            if (!a) {
                if (n3 == n4) {
                    s3 = signlink.z[6] + s2 + signlink.z[2] + s + signlink.z[3];
                    if (!a) {
                        break Label_0200;
                    }
                    int q = signlink.q;
                    signlink.q = ++q;
                }
                m = n;
            }
            if (m != n4) {
                s3 = signlink.z[6] + s2 + signlink.z[2] + s + signlink.z[7];
                if (!a) {
                    break Label_0200;
                }
            }
            s3 = signlink.z[6] + s2 + signlink.z[2] + s + signlink.z[9];
        }
        final String[] array = { signlink.z[11], signlink.z[10], signlink.t, signlink.z[4], signlink.z[8], signlink.z[1], signlink.z[5], "" };
        int n5 = 0;
        while (array.length > n5) {
            final String s4 = array[n5];
            final int length = s4.length();
            Label_0322: {
                if (!a) {
                    if (length <= 0) {
                        break Label_0322;
                    }
                    new File(s4).exists();
                }
                if (length == 0) {
                    if (!a) {
                        break Label_0322;
                    }
                }
                try {
                    return new fileondisk(new File(s4, s3), signlink.z[0], 10000L);
                }
                catch (Exception ex) {}
            }
            ++n5;
            if (a) {
                break;
            }
        }
        return null;
    }
    
    static {
        String[] o2;
        String[] array31;
        String[] z2;
        String[] array30;
        String[] array29;
        String[] array28;
        String[] array27;
        String[] array26;
        String[] array25;
        String[] array24;
        String[] array23;
        String[] array22;
        String[] array21;
        String[] array20;
        String[] array19;
        String[] array18;
        String[] array17;
        String[] array16;
        String[] array15;
        String[] array14;
        String[] array13;
        String[] array12;
        String[] array11;
        String[] array10;
        String[] array9;
        String[] array8;
        String[] array7;
        String[] array6;
        String[] array5;
        String[] array4;
        String[] array3;
        String[] array2;
        String[] array = array2 = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = (array11 = (array12 = (array13 = (array14 = (array15 = (array16 = (array17 = (array18 = (array19 = (array20 = (array21 = (array22 = (array23 = (array24 = (array25 = (array26 = (array27 = (array28 = (array29 = (array30 = (z2 = (array31 = (o2 = new String[30])))))))))))))))))))))))))))))));
        int n31;
        int n30;
        int n29;
        int n28;
        int n27;
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
        int n = n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 0)))))))))))))))))))))))))))));
        String s = "+Y";
        int n32 = -1;
        String z3 = null;
    Label_0527:
        while (true) {
            z3 = z(z(s));
            switch (n32) {
                default: {
                    array2[n2] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 1))))))))))))))))))))))))))))));
                    s = ":\u0014D";
                    n32 = 0;
                    continue;
                }
                case 0: {
                    array[n] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 2))))))))))))))))))))))))))))));
                    s = "\u0006^\u0019\u0012U<\\\u000e\u0019P<]";
                    n32 = 1;
                    continue;
                }
                case 1: {
                    array3[n3] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 3))))))))))))))))))))))))))))));
                    s = "\u0006\\\bYW8Z";
                    n32 = 2;
                    continue;
                }
                case 2: {
                    array4[n4] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 4))))))))))))))))))))))))))))));
                    s = ":\u0014D\u0000Z7J\u0004\u0000@v";
                    n32 = 3;
                    continue;
                }
                case 3: {
                    array5[n5] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 5))))))))))))))))))))))))))))));
                    s = "vZ\u0006\u0007\u001c";
                    n32 = 4;
                    continue;
                }
                case 4: {
                    array6[n6] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 6))))))))))))))))))))))))))))));
                    s = "*B\n\u0004[0Z4";
                    n32 = 5;
                    continue;
                }
                case 5: {
                    array7[n7] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 7))))))))))))))))))))))))))))));
                    s = "wJ\n\u0003";
                    n32 = 6;
                    continue;
                }
                case 6: {
                    array8[n8] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 8))))))))))))))))))))))))))))));
                    s = ":\u0014D\u0000Z7@\u001fX";
                    n32 = 7;
                    continue;
                }
                case 7: {
                    array9[n9] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 9))))))))))))))))))))))))))))));
                    s = "\u0006Y\u0002\u0007\u001d=O\u001f";
                    n32 = 8;
                    continue;
                }
                case 8: {
                    array10[n10] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 10))))))))))))))))))))))))))))));
                    s = "v]\u0018\u0014R:F\u000eX";
                    n32 = 9;
                    continue;
                }
                case 9: {
                    array11[n11] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 11))))))))))))))))))))))))))))));
                    s = ":\u0014D\u0004@:O\b\u001fVv";
                    n32 = 10;
                    continue;
                }
                case 10: {
                    array12[n12] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 12))))))))))))))))))))))))))))));
                    s = "\u0018y?";
                    n32 = 11;
                    continue;
                }
                case 11: {
                    array13[n13] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 13))))))))))))))))))))))))))))));
                    s = "8^\u001b\u001bV";
                    n32 = 12;
                    continue;
                }
                case 12: {
                    array14[n14] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 14))))))))))))))))))))))))))))));
                    s = "=A\u0006\u0016Z7]\u0007\u0016@1\u0000\u0004\u0005T";
                    n32 = 13;
                    continue;
                }
                case 13: {
                    array15[n15] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 15))))))))))))))))))))))))))))));
                    s = "*[\u0005";
                    n32 = 14;
                    continue;
                }
                case 14: {
                    array16[n16] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 16))))))))))))))))))))))))))))));
                    s = "*B\n\u0004[*M\n\u0007VwM\u0004\u001a";
                    n32 = 15;
                    continue;
                }
                case 15: {
                    array17[n17] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 17))))))))))))))))))))))))))))));
                    s = "*B\n\u0004[*M\n\u0007V";
                    n32 = 16;
                    continue;
                }
                case 16: {
                    array18[n18] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 18))))))))))))))))))))))))))))));
                    s = "3O\u001d\u0016\u001d/K\u0005\u0013\\+";
                    n32 = 17;
                    continue;
                }
                case 17: {
                    array19[n19] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 19))))))))))))))))))))))))))))));
                    s = "h\u0019_Y\u0002k\u001dED\u0007w\u0018]";
                    n32 = 18;
                    continue;
                }
                case 18: {
                    array20[n20] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 20))))))))))))))))))))))))))))));
                    s = ":B\u0002\u0012]-]\u0007\u0016@1\u0000\b\u0018^";
                    n32 = 19;
                    continue;
                }
                case 19: {
                    array21[n21] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 21))))))))))))))))))))))))))))));
                    s = "\nB\n\u0004[\nM\n\u0007V";
                    n32 = 20;
                    continue;
                }
                case 20: {
                    array22[n22] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 22))))))))))))))))))))))))))))));
                    s = "'\u0001";
                    n32 = 21;
                    continue;
                }
                case 21: {
                    array23[n23] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 23))))))))))))))))))))))))))))));
                    s = ",]\u000e\u0005\u001d1A\u0006\u0012";
                    n32 = 22;
                    continue;
                }
                case 22: {
                    array24[n24] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 24))))))))))))))))))))))))))))));
                    s = "4O\u0002\u0019l?G\u0007\u0012l:O\b\u001fVwJ\n\u0003\u0001";
                    n32 = 23;
                    continue;
                }
                case 23: {
                    array25[n25] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 25))))))))))))))))))))))))))))));
                    s = "4O\u0002\u0019l?G\u0007\u0012l:O\b\u001fVwG\u000f\u000f\u0001l\u001b";
                    n32 = 24;
                    continue;
                }
                case 24: {
                    array26[n26] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 26))))))))))))))))))))))))))))));
                    s = "+O\u0005\u0013\\4\u0000\u000f\u0016G";
                    n32 = 25;
                    continue;
                }
                case 25: {
                    array27[n27] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 27))))))))))))))))))))))))))))));
                    s = "4O\u0002\u0019l?G\u0007\u0012l:O\b\u001fVwG\u000f\u000f";
                    n32 = 26;
                    continue;
                }
                case 26: {
                    array28[n28] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 28))))))))))))))))))))))))))))));
                    s = "w]\u0007\u0016@1G\u001f(P8M\u0003\u0012l";
                    n32 = 27;
                    continue;
                }
                case 27: {
                    array29[n29] = z3;
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 29))))))))))))))))))))))))))))));
                    s = "wH\u0002\u001bV\u0006]\u001f\u0018A<q";
                    n32 = 28;
                    continue;
                }
                case 28: {
                    array30[n30] = z3;
                    z = z2;
                    ports = new int[] { 2087, 3000, 43594, 43594, 43560 };
                    (array2 = (array = (array3 = (array4 = (array5 = (array6 = (array7 = (array8 = (array9 = (array10 = (array11 = (array12 = (array13 = (array14 = (array15 = (array16 = (array17 = (array18 = (array19 = (array20 = (array21 = (array22 = (array23 = (array24 = (array25 = (array26 = (array27 = (array28 = (array29 = (array30 = (z2 = (array31 = (o2 = new String[2])))))))))))))))))))))))))))))))))[0] = signlink.z[16];
                    n = (n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = (n11 = (n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = (n21 = (n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = (n31 = 1))))))))))))))))))))))))))))));
                    s = "h\u001c\\Y\u0003w\u001eEF";
                    n32 = 29;
                    continue;
                }
                case 29: {
                    break Label_0527;
                }
            }
        }
        array31[n31] = z3;
        o = o2;
        signlink.e = new Hashtable(10);
        signlink.p = 32;
        a = new fileondisk[5];
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= '3';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'Y';
                            break;
                        }
                        case 1: {
                            c2 = '.';
                            break;
                        }
                        case 2: {
                            c2 = 'k';
                            break;
                        }
                        case 3: {
                            c2 = 'w';
                            break;
                        }
                        default: {
                            c2 = '3';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
