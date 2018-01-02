// 
// Decompiled by Procyon v0.5.30
// 

package ji.res;

import ji.util.b6;
import java.util.Vector;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.StringTokenizer;
import ji.io.q;
import ji.io.ac;
import ji.util.i;
import ji.util.cn;
import java.net.URL;
import ji.io.h;
import ji.v1event.af;
import ji.util.e;
import ji.util.d;
import ji.util.y;
import ji.util.co;

public class aa
{
    private static byte[] a;
    private static co b;
    private static ab c;
    private static String d;
    private static long e;
    private static String f;
    private static boolean g;
    private static String h;
    
    public static final boolean a() {
        return aa.c != null;
    }
    
    public static final String a(final String s) {
        String fx = null;
        try {
            if (aa.c == null) {
                aa.c = new ab(s, "verMessages");
                final y y = new y();
                if (aa.a != null) {
                    fx = aa.c.a(null, ji.util.d.b(aa.a, f(), false, y, s), ji.util.d.ev());
                }
                else {
                    fx = aa.c.a(f(), g(), ji.util.e.an(), true, null, null, false, 0);
                }
                if (fx != null && !ji.util.d.dw()) {
                    ji.io.h.d(s, fx);
                    ji.util.d.fx = fx;
                    fx = String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(fx)).concat("\n\nPlease ensure the above file exists before using this version of viewONE"))).concat("\nPlease note, the cause of this problem can be:\n\n"))).concat("1) The file is not present on the server.\n"))).concat("2) The file on the server is corrupt.\n"))).concat("3) The mime-type settings for your web server does not include '*.v1' files\n"))).concat("(If you need to add '*.v1' as a mime type to your server, please ensure that it is set to 'application/octet-stream').");
                    ji.util.d.a(ay.a(), fx, (af)null, s);
                }
                else {
                    aa.e = aa.c.e();
                }
            }
        }
        catch (Exception ex) {
            fx = ex.toString();
        }
        return fx;
    }
    
    public static final co a(final URL url, final Object o, final String s) {
        if (aa.b != null) {
            return aa.b;
        }
        final String a = cn.a(url, ji.util.d.eg(), s);
        final co b = new co();
        if (i.c(37)) {
            ji.io.h.d(s, "PRO: getVersionInfo...");
        }
        if (i.c(37)) {
            ji.io.h.d(s, "PRO: destinationPath = ".concat(String.valueOf(String.valueOf(a))));
        }
        try {
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a))).append("/").append(f())));
            if (i.c(37)) {
                ji.io.h.d(s, "PRO: installVersionName = ".concat(String.valueOf(String.valueOf(value))));
            }
            if (i.c(37)) {
                ji.io.h.d(s, "PRO: exists = ".concat(String.valueOf(String.valueOf(ac.d(value, s)))));
            }
            a(b, value, o, s);
        }
        catch (Exception ex) {
            if (i.c(79)) {
                ji.io.h.d(s, "GetVersionInfoError: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            }
            ji.util.d.a(ex);
        }
        try {
            if (aa.a != null) {
                b.j = aa.a;
            }
            else {
                final ClassLoader classLoader = b.getClass().getClassLoader();
                final cq a2 = cp.a();
                if (a2 != null) {
                    b.j = a2.getServerVersionBytes(url, classLoader, s);
                }
                if (b.j == null) {
                    ji.util.d.ib = true;
                    if (i.c(79)) {
                        ji.io.h.d(s, "GetVersionInfoError: Could not get version file");
                    }
                    return null;
                }
                aa.a = b.j;
            }
            if (b.j != null) {
                a(b, s);
                a(s);
                q.a(o, s);
            }
        }
        catch (Exception ex2) {
            ji.util.d.a(ex2);
        }
        aa.b = b;
        if (b.h == 0 && b.f == 0 && b.g == 0) {
            if (ji.util.d.dr()) {
                ji.io.h.d(s, "Old build detected on server which may be the result of a corrupt ver.v1 or a corrupt transport (chunking)");
            }
            s.i = true;
        }
        return b;
    }
    
    public static String b() {
        if (aa.d == null && aa.c != null) {
            aa.d = ji.util.d.bf(String.valueOf(String.valueOf(e())).concat(String.valueOf(String.valueOf(s.a))));
            aa.d = ji.util.d.b(aa.d, "win_", "");
            aa.d = ji.util.d.b(aa.d, "uni_", "");
            aa.d = ji.util.d.b(aa.d, "mac_", "");
        }
        return aa.d;
    }
    
    public static final long c() {
        return aa.e;
    }
    
    public static final void b(final String s) {
        if (ji.util.d.by(aa.f)) {
            aa.f = s;
        }
        else {
            aa.g = !s.toLowerCase().equals(aa.f.toLowerCase());
            aa.f = s;
        }
    }
    
    public static final boolean a(final String s, final String s2, final String s3, final boolean b, final String s4) {
        try {
            final String concat = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(ji.util.d.em() ? ".cab" : ".jar")));
            final Class a = a(s, s2, s3, s4);
            if (a != null) {
                final int a2 = a(a, concat, 0, b, s4);
                final int a3 = a(a, concat, 1, b, s4);
                final int a4 = a(a, concat, 2, b, s4);
                if (a2 != 0 || a3 != 0 || a4 != 0) {
                    return true;
                }
            }
        }
        catch (xo xo) {
            ji.io.h.f(s4, xo.getMessage());
            return true;
        }
        return false;
    }
    
    private static final Class a(final String s, final String s2, final String s3, final String s4) throws xo {
        if (i.c(254)) {
            final String concat = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(ji.util.d.em() ? ".cab" : ".jar")));
            Class<?> forName;
            try {
                forName = Class.forName(s);
            }
            catch (ClassNotFoundException ex) {
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(concat))).append(": ").append(s.a(1275, s4)).append(" ")));
                if (!ji.util.d.by(s3)) {
                    try {
                        Class.forName(s3);
                    }
                    catch (ClassNotFoundException ex2) {
                        throw new xo(String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(s.a(1276, s4)))));
                    }
                }
                final String concat2 = String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(s.a(1280, s4))));
                if (i.c(79)) {
                    ji.io.h.d(s4, "VersionClassError: ".concat(String.valueOf(String.valueOf(concat2))));
                }
                throw new xo(concat2);
            }
            return forName;
        }
        return null;
    }
    
    private static final int a(final Class clazz, final String s, final int n, final boolean b, final String s2) throws xo {
        try {
            if (b) {
                switch (n) {
                    case 0: {
                        return clazz.getField("v4").getInt(null);
                    }
                    case 1: {
                        return clazz.getField("v5").getInt(null);
                    }
                    case 2: {
                        return clazz.getField("v6").getInt(null);
                    }
                }
            }
            else {
                switch (n) {
                    case 0: {
                        return clazz.getField("v1").getInt(null);
                    }
                    case 1: {
                        return clazz.getField("v2").getInt(null);
                    }
                    case 2: {
                        return clazz.getField("v3").getInt(null);
                    }
                }
            }
        }
        catch (Exception ex) {
            final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(": ").append(s.a(1277, s2)).append(": ")));
            String s3;
            if (ex.getMessage() != null) {
                s3 = String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(ex.getMessage())));
            }
            else {
                s3 = String.valueOf(String.valueOf(value)).concat(String.valueOf(String.valueOf(ex.getClass().getName())));
            }
            if (i.c(79)) {
                ji.io.h.d(s2, "VersionPartError: ".concat(String.valueOf(String.valueOf(s3))));
            }
            throw new xo(s3);
        }
        return 0;
    }
    
    public static final String c(final String s) throws xo {
        String value = null;
        final String s2 = "ji.res.jiv2";
        final String s3 = "ji";
        final String s4 = null;
        final boolean u = ji.util.e.u();
        final String concat = String.valueOf(String.valueOf(s3)).concat(String.valueOf(String.valueOf(ji.util.d.em() ? ".cab" : ".jar")));
        final Class a = a(s2, s3, s4, s);
        if (a != null) {
            final int a2 = a(a, concat, 0, u, s);
            final int a3 = a(a, concat, 1, u, s);
            final int a4 = a(a, concat, 2, u, s);
            if (a2 + a3 + a4 > 0) {
                value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a2))).append(".").append(a3).append(".").append(a4)));
            }
        }
        return value;
    }
    
    public static final String a(final String s, final String s2, final String s3, final boolean b, final boolean b2, final Object o, final String s4) {
        if (!i.c(254)) {
            return null;
        }
        String s5 = null;
        try {
            final String concat = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(ji.util.d.em() ? ".cab" : ".jar")));
            final Class a = a(s, s2, s3, s4);
            if (a != null) {
                final int a2 = a(a, concat, 0, b, s4);
                final int a3 = a(a, concat, 1, b, s4);
                final int a4 = a(a, concat, 2, b, s4);
                if (a2 == 0 && a3 == 0 && a4 == 0) {
                    return null;
                }
                if (!a(concat, a2, a3, a4, o, s4, s2.equals(cn.b()))) {
                    s5 = a(concat, a2, a3, a4, aa.b, s4);
                }
            }
        }
        catch (xo xo) {
            s5 = xo.getMessage();
        }
        finally {
            if (b2 && !ji.util.d.by(s5)) {
                ji.util.d.a(s5, (af)null, s4);
            }
        }
        return s5;
    }
    
    public static boolean a(final String s, final int n, final int n2, int n3, final Object o, final String s2, final boolean b) {
        if (!i.c(254)) {
            return true;
        }
        if (n == 0 && n2 == 0 && n3 == 0) {
            return true;
        }
        if (aa.b == null) {
            a(ji.util.e.j(s2), o, s2);
        }
        if (aa.b == null) {
            if (i.c(79)) {
                ji.io.h.d(s2, "ValidVersionError: Could not retrieve version info");
            }
            return false;
        }
        if (aa.b.f == 0 && aa.b.g == 0 && aa.b.h == 0) {
            return true;
        }
        if (ji.util.d.dm() && n3 % 2 == 0 && aa.b.h % 2 > 0) {
            --n3;
        }
        if (aa.b.g != n2 || aa.b.h != n3) {
            return false;
        }
        if (i.c(79)) {
            ji.io.h.d(s2, String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(": Version OK: Version ").append(n).append(".").append(n2).append(".").append(n3))));
        }
        if (i.c(79)) {
            ji.io.h.d(s2, "Java Vendor: ".concat(String.valueOf(String.valueOf(ji.util.d.ag(s2)))));
            ji.io.h.d(s2, "Java Version: ".concat(String.valueOf(String.valueOf(ji.util.d.aq(s2)))));
            ji.io.h.d(s2, "Java Plugin Version: ".concat(String.valueOf(String.valueOf(ji.util.d.ap(s2)))));
        }
        return true;
    }
    
    private static String a(final String s, final int n, final int n2, final int n3, final co co, final String s2) {
        String a = s.a(1278, s2);
        if (a.startsWith("Localization error")) {
            ji.io.h.d(s2, "Could not find message 1278 so hardcoding English");
            a = "Server Version";
        }
        String a2 = s.a(1279, s2);
        if (a2.startsWith("Localization error")) {
            ji.io.h.d(s2, "Could not find message 1279 so hardcoding English");
            a2 = "Client Archive Version";
        }
        String a3 = s.a(1280, s2);
        if (a3.startsWith("Localization error")) {
            ji.io.h.d(s2, "Could not find message 1280 so hardcoding English");
            a3 = "Viewer Java archive files cached on the client not up to date. Please clear out the client-side Java Cache.";
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(": ").append(a).append(" ").append(co.e()))))).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(", ").append(a2).append(" ").append(n).append(".").append(n2).append(".").append(n3))))))))).append("\n").append(a3)));
    }
    
    public static final boolean a(final boolean b) {
        if (b) {
            if (ji.util.d.by(aa.h)) {
                aa.h = "Pro";
            }
            else if (aa.g) {
                return true;
            }
        }
        else if (ji.util.d.by(aa.h)) {
            aa.h = "Standard";
        }
        else if (aa.h.toLowerCase().equals("pro") && aa.g) {
            return true;
        }
        return false;
    }
    
    public static final void d() {
        try {
            if (aa.c != null) {
                aa.c.f();
                aa.c = null;
            }
        }
        catch (Exception ex) {}
        aa.a = null;
        aa.b = null;
    }
    
    public static void a(final String s, final String s2) throws Exception {
        ac.c(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("/").append(f()))), s2);
    }
    
    public static boolean d(final String s) {
        return s.startsWith(String.valueOf(String.valueOf(h())).concat("."));
    }
    
    public static String e() {
        if (aa.c == null) {
            return null;
        }
        String s = "appversion";
        if (ji.util.e.u()) {
            s = "appversionpro";
        }
        if (ji.util.d.bf()) {
            return ay.a(aa.c.a(s));
        }
        return aa.c.a(s);
    }
    
    public static void a(final byte[] a) {
        aa.a = a;
    }
    
    public static final String f() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(h()))).append(".").append("v1")));
    }
    
    private static final String g() {
        return String.valueOf(String.valueOf(h())).concat(".txt");
    }
    
    private static final String h() {
        return "ver";
    }
    
    private static final String a(final byte[] array, final String s) {
        String nextToken = "0";
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(new String(array), "\"", ""), "{", ""), "}", ""), "\r", ","), "\n", ","), ",,", ","), ",");
            if (ji.util.e.u()) {
                stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                stringTokenizer.nextToken();
            }
            else {
                stringTokenizer.nextToken();
            }
            nextToken = stringTokenizer.nextToken();
        }
        catch (Exception ex) {
            if (i.c(79)) {
                ji.io.h.d(s, "VersionString: ".concat(String.valueOf(String.valueOf(ex.getMessage()))));
            }
            ji.util.d.a(ex);
        }
        return ji.util.d.bc(nextToken);
    }
    
    private static final byte[] b(final URL url, final String s) {
        try {
            final URLConnection a = ji.util.d.a(url, false, s);
            int i = a.getContentLength();
            if (i == -1) {
                return null;
            }
            final InputStream inputStream = a.getInputStream();
            final byte[] array = new byte[i];
            int n = 0;
            while (i > 0) {
                final int read = inputStream.read(array, n, i);
                n += read;
                i -= read;
            }
            inputStream.close();
            return array;
        }
        catch (Exception ex) {
            ji.io.h.d(s, "Failed to load: ".concat(String.valueOf(String.valueOf(url))));
            ex.printStackTrace();
            return null;
        }
    }
    
    private static final byte[] c(final URL url, final String s) {
        try {
            int read = -1;
            final URLConnection a = ji.util.d.a(url, false, s);
            int i = a.getContentLength();
            final InputStream inputStream = a.getInputStream();
            boolean b = false;
            if (i < 0) {
                i = inputStream.available();
                if (i == 0) {
                    read = inputStream.read();
                    if (read == -1) {
                        i = 0;
                    }
                    else {
                        i = inputStream.available();
                    }
                }
                b = true;
            }
            final int bb = ji.util.d.bb();
            if (b) {
                int j = i;
                byte[] array = null;
                int n = 0;
                final Vector vector = new Vector<byte[]>();
                if (read != -1) {
                    ++n;
                    vector.addElement(new byte[] { (byte)read });
                }
                while (j > 0) {
                    if (bb > 0) {
                        ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("> Net: Simulated delay(1) ").append(bb).append(" milliseconds: ").append(url))));
                        ji.util.d.b(bb, 3000, s);
                    }
                    if (array == null) {
                        array = new byte[j];
                    }
                    else if (array.length < j) {
                        array = new byte[j];
                    }
                    final int read2 = inputStream.read(array, 0, j);
                    n += read2;
                    if (read2 > 0) {
                        final byte[] array2 = new byte[read2];
                        System.arraycopy(array, 0, array2, 0, read2);
                        vector.addElement(array2);
                        j = inputStream.available();
                    }
                }
                final byte[] array3 = new byte[n];
                int n2 = 0;
                while (vector.size() > 0) {
                    final byte[] array4 = vector.elementAt(0);
                    System.arraycopy(array4, 0, array3, n2, array4.length);
                    n2 += array4.length;
                    vector.removeElementAt(0);
                }
                inputStream.close();
                return array3;
            }
            final byte[] array5 = new byte[i];
            int n3 = 0;
            while (i > 0) {
                if (bb > 0) {
                    ji.io.h.d(s, String.valueOf(String.valueOf(new StringBuffer("> Net: Simulated delay(2) ").append(bb).append(" milliseconds: ").append(url))));
                    ji.util.d.b(bb, 3001, s);
                }
                final int read3 = inputStream.read(array5, n3, i);
                n3 += read3;
                i -= read3;
            }
            inputStream.close();
            return array5;
        }
        catch (Exception ex) {
            ji.io.h.d(s, "Failed to load: ".concat(String.valueOf(String.valueOf(url))));
            ex.printStackTrace();
            return null;
        }
    }
    
    public static final byte[] a(final URL url, final String s) {
        if (i.c(273)) {
            return c(url, s);
        }
        return b(url, s);
    }
    
    public static String a(final Object o, final String s) {
        final StringBuffer sb = new StringBuffer("");
        if (ji.util.e.am() != null) {
            final co a = a(ji.util.e.am(), o, s);
            if (a.a > 0) {
                sb.append(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(698, s)))).append(a.d()).append("\n"))));
                sb.append(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.b(699, s)))).append(a.e()).append("\n"))));
            }
        }
        return sb.toString();
    }
    
    public static boolean b(final Object o, final String s) {
        if (ji.util.e.am() != null) {
            final co a = a(ji.util.e.am(), o, s);
            if (i.c(37)) {
                if (a != null) {
                    ji.io.h.d(s, "PRO: verInfo...".concat(String.valueOf(String.valueOf(a))));
                    ji.io.h.d(s, "PRO: currentInstallMajor...".concat(String.valueOf(String.valueOf(a.a))));
                }
                else {
                    ji.io.h.d(s, "PRO: verInfo... No VersionInfo");
                }
            }
            if (a != null && a.a > 0) {
                return true;
            }
        }
        return false;
    }
    
    public static void a(final co co, final String s) throws b6 {
        if (co.j != null) {
            final String a = a(ji.util.d.b(co.j, f(), false, new y(), s), s);
            final int index = a.indexOf(".");
            co.f = ji.util.d.c(a.substring(0, index), 1);
            final String substring = a.substring(index + 1);
            final int index2 = substring.indexOf(".");
            co.g = ji.util.d.c(substring.substring(0, index2), 1);
            co.h = ji.util.d.c(substring.substring(index2 + 1), 1);
            co.i = String.valueOf(String.valueOf(new StringBuffer("").append(co.f).append(co.g).append(co.h)));
        }
    }
    
    public static void a(final co co, final String s, final Object o, final String s2) throws Exception {
        if (ac.d(s, s2)) {
            final ac ac = new ac(s, false, false, 0, false, o, false, s2);
            ac.a(co.e = new byte[(int)ac.v()]);
            ac.a(o);
            b(co, s2);
        }
    }
    
    public static void b(final co co, final String s) throws b6 {
        final String a = a(ji.util.d.b(co.e, f(), false, new y(), s), s);
        final int index = a.indexOf(".");
        co.a = ji.util.d.c(a.substring(0, index), 1);
        final String substring = a.substring(index + 1);
        final int index2 = substring.indexOf(".");
        co.b = ji.util.d.c(substring.substring(0, index2), 1);
        co.c = ji.util.d.c(substring.substring(index2 + 1), 1);
        co.d = String.valueOf(String.valueOf(new StringBuffer("").append(co.a).append(co.b).append(co.c)));
    }
    
    public static final String a(final Object o, final String s, final boolean b) {
        if (b(o, s) && !b) {
            return ji.util.d.bc(ay.a());
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ay.a()))).append(" ").append(ji.util.d.b(100, s)).append(" ").append(e())));
    }
    
    static {
        aa.a = null;
        aa.b = null;
        aa.c = null;
        aa.d = null;
        aa.e = 0L;
        aa.f = null;
        aa.g = false;
        aa.h = null;
    }
    
    private static class xo extends Exception
    {
        public xo(final String s) {
            super(s);
        }
    }
}
