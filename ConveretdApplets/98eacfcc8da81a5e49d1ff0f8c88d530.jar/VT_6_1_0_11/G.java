// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.StringTokenizer;

public final class G implements ag
{
    private static final byte[] a;
    private static byte[] b;
    private static dr c;
    private static boolean d;
    
    public final aJ a(final aJ aj, final I i, cn a) {
        if (aF.a(8)) {
            new StringBuffer().append("Auth:  Requesting Authorization for host ").append(aj.a()).append(":").append(aj.b()).append("; scheme: ").append(aj.c()).append("; realm: ").append(aj.d()).toString();
        }
        if (!aj.c().equalsIgnoreCase("Basic") && !aj.c().equalsIgnoreCase("Digest") && !aj.c().equalsIgnoreCase("SOCKS5")) {
            throw new cL(aj.c());
        }
        final aJ b;
        if (aj.c().equalsIgnoreCase("Digest") && (b = b(aj, i, a)) != null) {
            return b;
        }
        synchronized (this.getClass()) {
            if (!i.g()) {
                return null;
            }
            if (G.c == null) {
                final String property;
                if (((property = System.getProperty("os.name")).indexOf("Linux") < 0 && property.indexOf("SunOS") < 0 && property.indexOf("Solaris") < 0 && property.indexOf("BSD") < 0 && property.indexOf("AIX") < 0 && property.indexOf("HP-UX") < 0 && property.indexOf("IRIX") < 0 && property.indexOf("OSF") < 0 && property.indexOf("A/UX") < 0 && property.indexOf("VMS") < 0) || a()) {
                    G.c = new bo();
                }
                else {
                    G.c = new ab();
                }
            }
            final dr c = G.c;
            a.a();
            a = (cn)c.a(aj);
        }
        if (a == null) {
            return null;
        }
        final aJ aj2;
        aJ a2;
        if (aj2.c().equalsIgnoreCase("basic")) {
            a2 = new aJ(aj2.a(), aj2.b(), aj2.c(), aj2.d(), ci.a(((cU)a).a() + ":" + ((cU)a).b()));
        }
        else if (aj2.c().equalsIgnoreCase("Digest")) {
            a2 = a(a(aj2.a(), aj2.b(), aj2.d(), ((cU)a).a(), ((cU)a).b(), i.a().g()), i, aj2);
        }
        else {
            a2 = new aJ(aj2.a(), aj2.b(), aj2.c(), aj2.d(), new cU[] { a }, null);
        }
        System.gc();
        return a2;
    }
    
    private static aJ a(final String s, final int n, final String s2, final String s3, String string, final Object o) {
        string = s3 + ":" + s2 + ":" + string;
        final String[] array = { bM.a(string), null, null };
        final aJ a;
        cU[] e;
        if ((a = aJ.a(s, n, "Digest", s2, o)) == null) {
            (e = new cU[4])[0] = new cU("username", s3);
            e[1] = new cU("uri", "");
            e[2] = new cU("nonce", "");
            e[3] = new cU("response", "");
        }
        else {
            e = a.e();
            for (int i = 0; i < e.length; ++i) {
                if (e[i].a().equalsIgnoreCase("username")) {
                    e[i] = new cU("username", s3);
                    break;
                }
            }
        }
        return new aJ(s, n, "Digest", s2, e, array);
    }
    
    private static aJ a(final aJ aj, final I i, aJ aj2) {
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        int n6 = -1;
        int n7 = -1;
        cU[] e = null;
        if (aj2 != null) {
            e = aj2.e();
            for (int j = 0; j < e.length; ++j) {
                final String lowerCase;
                if ((lowerCase = e[j].a().toLowerCase()).equals("domain")) {
                    n = j;
                }
                else if (lowerCase.equals("nonce")) {
                    n2 = j;
                }
                else if (lowerCase.equals("opaque")) {
                    n4 = j;
                }
                else if (lowerCase.equals("algorithm")) {
                    n3 = j;
                }
                else if (lowerCase.equals("stale")) {
                    n5 = j;
                }
                else if (lowerCase.equals("digest-required")) {
                    n6 = j;
                }
                else if (lowerCase.equals("qop")) {
                    n7 = j;
                }
            }
        }
        int n8 = -1;
        int n9 = -1;
        int n10 = -1;
        int n11 = -1;
        int n12 = -1;
        int n13 = -1;
        int n14 = -1;
        int length = -1;
        int n15 = -1;
        int n16 = -1;
        cU[] array;
        final String[] array2;
        synchronized (aj) {
            array = aj.e();
            for (int k = 0; k < array.length; ++k) {
                final String lowerCase2;
                if ((lowerCase2 = array[k].a().toLowerCase()).equals("uri")) {
                    n8 = k;
                }
                else if (!lowerCase2.equals("username")) {
                    if (lowerCase2.equals("algorithm")) {
                        n9 = k;
                    }
                    else if (lowerCase2.equals("nonce")) {
                        n11 = k;
                    }
                    else if (lowerCase2.equals("cnonce")) {
                        n12 = k;
                    }
                    else if (lowerCase2.equals("nc")) {
                        n13 = k;
                    }
                    else if (lowerCase2.equals("response")) {
                        n10 = k;
                    }
                    else if (lowerCase2.equals("opaque")) {
                        n14 = k;
                    }
                    else if (lowerCase2.equals("digest")) {
                        length = k;
                    }
                    else if (lowerCase2.equals("digest-required")) {
                        n15 = k;
                    }
                    else if (lowerCase2.equals("qop")) {
                        n16 = k;
                    }
                }
            }
            array2 = (String[])aj.f();
            if (n9 != -1 && !array[n9].b().equalsIgnoreCase("MD5") && !array[n9].b().equalsIgnoreCase("MD5-sess")) {
                throw new cL("Digest auth scheme: Algorithm " + array[n9].b() + " not implemented");
            }
            if (n3 != -1 && !e[n3].b().equalsIgnoreCase("MD5") && !e[n3].b().equalsIgnoreCase("MD5-sess")) {
                throw new cL("Digest auth scheme: Algorithm " + e[n3].b() + " not implemented");
            }
            array[n8] = new cU("uri", bf.a(i.c(), bf.a, false));
            final String b = array[n11].b();
            if (n2 != -1 && !b.equals(e[n2].b())) {
                array[n11] = e[n2];
            }
            if (n4 != -1) {
                if (n14 == -1) {
                    n14 = (array = bz.a(array, array.length + 1)).length - 1;
                }
                array[n14] = e[n4];
            }
            if (n3 != -1) {
                if (n9 == -1) {
                    n9 = (array = bz.a(array, array.length + 1)).length - 1;
                }
                array[n9] = e[n3];
            }
            if (n7 != -1 || (n3 != -1 && e[n3].b().equalsIgnoreCase("MD5-sess"))) {
                if (n12 == -1) {
                    n12 = (array = bz.a(array, array.length + 1)).length - 1;
                }
                if (G.b == null) {
                    G.b = a(20);
                }
                final long currentTimeMillis = System.currentTimeMillis();
                final byte[] array3;
                (array3 = new byte[8])[0] = (byte)(currentTimeMillis & 0xFFL);
                array3[1] = (byte)(currentTimeMillis >> 8 & 0xFFL);
                array3[2] = (byte)(currentTimeMillis >> 16 & 0xFFL);
                array3[3] = (byte)(currentTimeMillis >> 24 & 0xFFL);
                array3[4] = (byte)(currentTimeMillis >> 32 & 0xFFL);
                array3[5] = (byte)(currentTimeMillis >> 40 & 0xFFL);
                array3[6] = (byte)(currentTimeMillis >> 48 & 0xFFL);
                array3[7] = (byte)(currentTimeMillis >> 56 & 0xFFL);
                array[n12] = new cU("cnonce", bM.a(G.b, array3));
            }
            if (n7 != -1) {
                if (n16 == -1) {
                    n16 = (array = bz.a(array, array.length + 1)).length - 1;
                }
                array2[2] = e[n7].b();
                final String[] a = a(array2[2], ",");
                String s = null;
                for (int l = 0; l < a.length; ++l) {
                    if (a[l].equalsIgnoreCase("auth-int") && (i.f() == null || (i.a().b && i.a().a >= 65537))) {
                        s = "auth-int";
                        break;
                    }
                    if (a[l].equalsIgnoreCase("auth")) {
                        s = "auth";
                    }
                }
                if (s == null) {
                    for (int n17 = 0; n17 < a.length; ++n17) {
                        if (a[n17].equalsIgnoreCase("auth-int")) {
                            throw new cL("Digest auth scheme: Can't comply with qop option 'auth-int' because an HttpOutputStream is being used and the server doesn't speak HTTP/1.1");
                        }
                    }
                    throw new cL("Digest auth scheme: None of the available qop options '" + e[n7].b() + "' implemented");
                }
                array[n16] = new cU("qop", s);
            }
            if (n16 != -1) {
                if (n13 == -1) {
                    n13 = (array = bz.a(array, array.length + 1)).length - 1;
                    array[n13] = new cU("nc", "00000001");
                }
                else if (b.equals(array[n11].b())) {
                    final String hexString = Long.toHexString(Long.parseLong(array[n13].b(), 16) + 1L);
                    array[n13] = new cU("nc", "00000000".substring(hexString.length()) + hexString);
                }
                else {
                    array[n13] = new cU("nc", "00000001");
                }
            }
            if (aj2 != null && (n5 == -1 || !e[n5].b().equalsIgnoreCase("true")) && n9 != -1 && array[n9].b().equalsIgnoreCase("MD5-sess")) {
                array2[1] = bM.a(array2[0] + ":" + array[n11].b() + ":" + array[n12].b());
            }
            aj.a(array);
            aj.a(array2);
        }
        String a2 = null;
        if (n16 != -1 && array[n16].b().equalsIgnoreCase("auth-int") && i.f() == null) {
            a2 = bM.a((i.e() == null) ? G.a : i.e());
        }
        if (i.f() == null) {
            final cU[] array4 = array;
            final int n18 = n10;
            final String s2 = "response";
            final String s3 = a2;
            final String[] array5 = array2;
            final cU[] array6 = array;
            final int n19 = n9;
            final int n20 = n8;
            final int n21 = n16;
            final int n22 = n11;
            final int n23 = n13;
            final int n24 = n12;
            final String b2 = i.b();
            final int n25 = n24;
            final int n26 = n23;
            final int n27 = n22;
            final int n28 = n21;
            final int n29 = n20;
            final int n30 = n19;
            final cU[] array7 = array6;
            final String[] array8 = array5;
            final String s4 = s3;
            String s5;
            if (n30 != -1 && array7[n30].b().equalsIgnoreCase("MD5-sess")) {
                s5 = array8[1];
            }
            else {
                s5 = array8[0];
            }
            String s6 = b2 + ":" + array7[n29].b();
            if (n28 != -1 && array7[n28].b().equalsIgnoreCase("auth-int")) {
                s6 = s6 + ":" + s4;
            }
            final String a3 = bM.a(s6);
            String s7;
            if (n28 == -1) {
                s7 = bM.a(s5 + ":" + array7[n27].b() + ":" + a3);
            }
            else {
                s7 = bM.a(s5 + ":" + array7[n27].b() + ":" + array7[n26].b() + ":" + array7[n25].b() + ":" + array7[n28].b() + ":" + a3);
            }
            array4[n18] = new cU(s2, s7);
        }
        boolean b3 = false;
        if (n6 != -1 && (e[n6].b() == null || e[n6].b().equalsIgnoreCase("true"))) {
            b3 = true;
        }
        aJ aj3;
        if ((b3 || length != -1) && i.f() == null) {
            cU[] array9;
            if (length == -1) {
                array9 = bz.a(array, array.length + 1);
                length = array.length;
            }
            else {
                array9 = array;
            }
            array9[length] = new cU("digest", a(i, array2[0], array[n11].b()));
            if (n15 == -1) {
                (array9 = bz.a(array9, array9.length + 1))[array9.length] = new cU("digest-required", "true");
            }
            aj3 = new aJ(aj.a(), aj.b(), aj.c(), aj.d(), array9, array2);
        }
        else if (b3) {
            aj3 = null;
        }
        else {
            aj3 = new aJ(aj.a(), aj.b(), aj.c(), aj.d(), array, array2);
        }
        final aJ aj4;
        final boolean b4 = aj4 != null && aj4.a().equalsIgnoreCase(i.a().c());
        if (n != -1) {
            bf bf = null;
            try {
                bf = new bf(i.a().b(), i.a().c(), i.a().d(), i.c());
            }
            catch (dh dh) {}
            aj2 = (aJ)new StringTokenizer(e[n].b());
            while (((StringTokenizer)aj2).hasMoreTokens()) {
                bf bf2;
                try {
                    bf2 = new bf(bf, ((StringTokenizer)aj2).nextToken());
                }
                catch (dh dh2) {
                    continue;
                }
                if (bf2.b() != null) {
                    aJ a4;
                    if ((a4 = aJ.a(bf2.b(), bf2.c(), aj.c(), aj.d(), i.a().g())) == null) {
                        array[n8] = new cU("uri", bf2.d());
                        aJ.a(a4 = new aJ(bf2.b(), bf2.c(), aj.c(), aj.d(), array, array2));
                    }
                    if (!b4) {
                        continue;
                    }
                    a4.a(bf2.d());
                }
            }
        }
        else {
            final aJ a5;
            if (b4 && aj4 != null && (a5 = aJ.a(aj4.a(), aj4.b(), aj.c(), aj.d(), i.a().g())) != null) {
                a5.a("/");
            }
        }
        return aj3;
    }
    
    private static aJ b(final aJ aj, final I i, final cn cn) {
        aJ a = null;
        final cU[] e = aj.e();
        int j = 0;
        while (j < e.length) {
            if (e[j].a().equalsIgnoreCase("stale") && e[j].b().equalsIgnoreCase("true")) {
                if ((a = aJ.a(aj, i, cn, false)) != null) {
                    return a(a, i, aj);
                }
                break;
            }
            else {
                ++j;
            }
        }
        return a;
    }
    
    private static String a(final I i, String string, final String s) {
        if (i.f() != null) {
            return "";
        }
        int n = -1;
        int n2 = -1;
        int n3 = -1;
        int n4 = -1;
        int n5 = -1;
        for (int j = 0; j < i.d().length; ++j) {
            final String a;
            if ((a = i.d()[j].a()).equalsIgnoreCase("Content-type")) {
                n = j;
            }
            else if (a.equalsIgnoreCase("Content-Encoding")) {
                n2 = j;
            }
            else if (a.equalsIgnoreCase("Last-Modified")) {
                n3 = j;
            }
            else if (a.equalsIgnoreCase("Expires")) {
                n4 = j;
            }
            else if (a.equalsIgnoreCase("Date")) {
                n5 = j;
            }
        }
        final cU[] d = i.d();
        final byte[] array;
        final String a2 = bM.a(array = ((i.e() == null) ? G.a : i.e()));
        string = string + ":" + s + ":" + i.b() + ":" + ((n5 == -1) ? "" : d[n5].b()) + ":" + bM.a(i.c() + ":" + ((n == -1) ? "" : d[n].b()) + ":" + array.length + ":" + ((n2 == -1) ? "" : d[n2].b()) + ":" + ((n3 == -1) ? "" : d[n3].b()) + ":" + ((n4 == -1) ? "" : d[n4].b())) + ":" + a2;
        if (aF.a(8)) {
            new StringBuffer().append("Auth:  Entity-Info: '").append(i.c()).append(":").append((n == -1) ? "" : d[n].b()).append(":").append(array.length).append(":").append((n2 == -1) ? "" : d[n2].b()).append(":").append((n3 == -1) ? "" : d[n3].b()).append(":").append((n4 == -1) ? "" : d[n4].b()).append("'").toString();
            new StringBuffer().append("Auth:  Entity-Body: '").append(a2).append("'").toString();
            new StringBuffer().append("Auth:  Entity-Digest: '").append(string).append("'").toString();
        }
        return bM.a(string);
    }
    
    private static byte[] a(int n) {
        try {
            final DataInputStream dataInputStream = new DataInputStream(new FileInputStream("/dev/random"));
            final byte[] array = new byte[20];
            dataInputStream.readFully(array);
            try {
                dataInputStream.close();
            }
            catch (IOException ex) {}
            return array;
        }
        catch (Throwable t) {
            n = (int)(Object)new byte[20];
            try {
                final long freeMemory = Runtime.getRuntime().freeMemory();
                n[0] = (byte)(freeMemory & 0xFFL);
                n[1] = (byte)(freeMemory >> 8 & 0xFFL);
                final int hashCode = n.hashCode();
                n[2] = (byte)hashCode;
                n[3] = (byte)(hashCode >> 8);
                n[4] = (byte)(hashCode >> 16);
                n[5] = (byte)(hashCode >>> 24);
                final long currentTimeMillis = System.currentTimeMillis();
                n[6] = (byte)(currentTimeMillis & 0xFFL);
                n[7] = (byte)(currentTimeMillis >> 8 & 0xFFL);
            }
            catch (ArrayIndexOutOfBoundsException ex2) {}
            return (byte[])(Object)n;
        }
    }
    
    private static String[] a(final String s, final String s2) {
        if (s == null) {
            return new String[0];
        }
        final StringTokenizer stringTokenizer;
        final String[] array = new String[(stringTokenizer = new StringTokenizer(s, s2)).countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken().trim();
        }
        return array;
    }
    
    private static final boolean a() {
        ThreadGroup threadGroup;
        for (threadGroup = Thread.currentThread().getThreadGroup(); threadGroup.getParent() != null; threadGroup = threadGroup.getParent()) {}
        final Thread[] array = new Thread[threadGroup.activeCount() + 5];
        for (int enumerate = threadGroup.enumerate(array), i = 0; i < enumerate; ++i) {
            if (array[i].getName().startsWith("AWT-")) {
                return true;
            }
        }
        return false;
    }
    
    static {
        a = new byte[0];
        G.b = null;
        G.c = null;
        G.d = false;
    }
}
