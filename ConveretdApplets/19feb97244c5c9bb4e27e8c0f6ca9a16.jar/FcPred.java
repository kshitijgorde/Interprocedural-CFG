import java.io.OutputStream;
import java.net.URLConnection;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.net.URL;
import java.util.Hashtable;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class FcPred extends Applet implements Runnable
{
    private String \u00c0;
    private boolean \u00c1;
    private static double \u00c2;
    private static double \u00c3;
    private static final char[] \u00c4;
    private boolean \u00c5;
    private boolean \u00c6;
    private Thread \u00c7;
    private String[] \u00c8;
    private static final Hashtable \u00c9;
    private Hashtable \u00ca;
    private static boolean \u00cb;
    private static String \u00cc;
    private static boolean \u00cd;
    private static int \u00ce;
    private URL \u00cf;
    private String \u00d0;
    private String \u00d1;
    private int \u00d2;
    private int \u00d3;
    private int \u00d4;
    private Hashtable \u00d5;
    private StringBuffer[] \u00d6;
    private Hashtable \u00d8;
    private int \u00d9;
    private String[] \u00da;
    private boolean[] \u00db;
    private boolean[] \u00dc;
    private int \u00dd;
    private int \u00de;
    private Hashtable \u00df;
    private boolean \u00e0;
    private int \u00e1;
    private Hashtable \u00e2;
    private int \u00e3;
    private int \u00e4;
    private static int \u00e5;
    private static final byte[] \u00e6;
    private boolean \u00e7;
    
    public FcPred() {
        this.\u00c0 = "18";
        this.\u00c1 = false;
        this.\u00c5 = false;
        this.\u00c6 = false;
        this.\u00c7 = null;
        this.\u00d6 = null;
        this.\u00d8 = null;
        this.\u00da = new String[100];
        this.\u00db = new boolean[100];
        this.\u00dc = new boolean[100];
        this.\u00e0 = true;
    }
    
    private final void \u00c0() {
        this.\u00c1 = true;
        this.\u00c6("k", System.currentTimeMillis());
    }
    
    private synchronized void \u00c1() {
        if (this.\u00c7 != null) {
            this.\u00e0 = true;
            this.notify();
            try {
                this.\u00c7.join(100L);
            }
            catch (Throwable t) {}
            this.\u00c7 = null;
        }
    }
    
    private void \u00c2() {
        this.\u00e7 = false;
        this.\u00e4 = -1;
        this.\u00e3 = 0;
        this.\u00c1 = false;
        this.\u00c1();
        this.\u00d9 = 0;
        this.\u00d5 = new Hashtable();
        this.\u00e2 = new Hashtable();
        final boolean b = false;
        this.\u00d3 = (b ? 1 : 0);
        this.\u00d2 = (b ? 1 : 0);
        this.\u00d4 = 0;
        this.\u00c6 = false;
        this.\u00c8 = new String[36];
        this.\u00de = 0;
        this.\u00df = new Hashtable();
        this.\u00e0 = false;
    }
    
    private void \u00c3() {
        this.\u00ca = new Hashtable();
        this.\u00c6("d", new Hashtable());
        this.\u00c6("e", new Hashtable());
        this.\u00c6("x", new Vector());
        this.\u00c6("v", new Vector());
        this.\u00c6("w", new Vector());
        this.\u00c6("f", -1L);
        this.\u00c6("g", -1L);
        this.\u00c6("j", -1L);
        this.\u00c6("k", -1L);
        this.\u00c6("q", -1L);
        this.\u00c6("s", -1L);
    }
    
    private String \u00c4(final String s) {
        String s2 = null;
        try {
            s2 = (String)this.\u00c5(s);
        }
        catch (Exception ex) {}
        return s2;
    }
    
    private Object \u00c5(final String s) {
        return this.\u00ca.get(s);
    }
    
    private void \u00c6(final String s, final long n) {
        this.\u00c6(s, "" + n);
    }
    
    private void \u00c6(final String s, final Object o) {
        if (o == null) {
            this.\u00ca.remove(s);
        }
        else {
            this.\u00ca.put(s, o);
        }
    }
    
    private long \u00c7(final String s) {
        final String \u00e4 = this.\u00c4(s);
        if (\u00e4 != null) {
            try {
                return Long.parseLong(\u00e4);
            }
            catch (Exception ex) {}
        }
        return 0L;
    }
    
    private int \u00c8(final String s) {
        return (int)this.\u00c7(s);
    }
    
    private int \u00c9(final String s) {
        final int n = this.\u00c8(s) + 1;
        this.\u00c6(s, n);
        return n;
    }
    
    public final Object fcCall(final int n, final String s, final Object o) {
        Object o2 = null;
        try {
            if (System.getProperty("java.version").indexOf("1.1") == 0) {
                String[] array = null;
                int n2 = 0;
                if (s != null) {
                    array = new String[30];
                    int n3 = 0;
                    int n5;
                    for (int n4 = 0; n4 == 0 && n2 < 30; ++n2, n3 = n5 + 1) {
                        n5 = s.indexOf(10, n3);
                        if (n5 == -1) {
                            n4 = 1;
                            n5 = s.length();
                        }
                        array[n2] = s.substring(n3, n5);
                    }
                }
                if (n == 0) {
                    if (!FcPred.\u00cd) {
                        if (n2 == 19) {
                            array[19] = "";
                        }
                        o2 = this.\u00ca(array[0], array[1].equals("true"), array[2], array[3], array[4].equals("true"), array[5], this.\u00e8(array[6]), this.\u00e9(array[7]), this.\u00e9(array[8]), array[9], this.\u00e8(array[10]), array[11], array[12], array[13], array[14], array[15], array[16], array[17], array[18], array[19], array[20], array[21]);
                    }
                }
                else if (n == 1) {
                    if ("lan".equalsIgnoreCase(s)) {
                        FcPred.\u00ce = 2;
                    }
                    else if ("modem".equalsIgnoreCase(s)) {
                        FcPred.\u00ce = 1;
                    }
                }
                else if (n == 2) {
                    this.\u00cd(o);
                }
                else if (n == 3) {
                    o2 = (this.\u00c0.equals(s) ? "" : null);
                }
                else if (n == 4) {
                    o2 = this.\u00d5.get(s);
                }
                else if (n == 5) {
                    final int int1 = Integer.parseInt(array[0]);
                    final int int2 = Integer.parseInt(array[1]);
                    if (int2 > 0) {
                        this.\u00c6("q", this.\u00d3(this.\u00c8("q"), 8 * int1 / int2));
                    }
                }
                else if (n == 6) {
                    final int \u00e8 = this.\u00c8("q");
                    if (\u00e8 != -1) {
                        o2 = "" + \u00e8;
                    }
                }
                else if (n == 7) {
                    this.\u00d1 = s;
                }
                else if (n == 8) {
                    this.\u00cb(array[0], (n2 == 2) ? Integer.parseInt(array[1]) : -3);
                }
                else if (n != 9) {
                    if (n == 10) {
                        try {
                            this.\u00dd();
                        }
                        catch (Exception ex) {}
                        FcPred.\u00cc = s;
                        synchronized (this) {
                            (this.\u00c7 = new Thread(this)).start();
                        }
                    }
                    else if (n == 11) {
                        o2 = (this.\u00e7 ? "" : null);
                    }
                    else if (n == 12) {
                        o2 = this.\u00ce();
                    }
                    else if (n == 13) {
                        o2 = this.\u00cf();
                    }
                    else if (n == 14) {
                        this.\u00d0(array[0], array[1].equals("true"));
                    }
                }
            }
        }
        catch (Exception ex2) {}
        return o2;
    }
    
    private final String \u00ca(final String s, final boolean \u00eb, final String s2, String \u00df, final boolean b, String substring, final int n, final long n2, final long n3, final String s3, final int n4, final String s4, final String s5, final String s6, final String s7, final String s8, final String s9, final String s10, final String s11, final String s12, final String s13, final String s14) {
        try {
            this.\u00c2();
            FcPred.\u00cb = \u00eb;
            this.\u00c8[28] = s4;
            this.\u00c8[29] = s5;
            this.\u00c8[30] = s9;
            this.\u00c8[31] = s10;
            this.\u00c8[32] = s11;
            this.\u00c8[33] = s12;
            this.\u00c8[34] = s13;
            this.\u00c8[35] = s14;
            String s15;
            if (s6.equals("X0")) {
                s15 = this.\u00db(n, n2, n3);
            }
            else {
                s15 = this.\u00dc(s6, s7, s8);
            }
            this.\u00d6 = new StringBuffer[3];
            this.\u00c8[25] = s3;
            if (n4 >= 0) {
                this.\u00c8[24] = "" + n4;
            }
            final String \u00e4 = this.\u00c4("o");
            final String \u00e42;
            String s16 = \u00e42 = this.\u00c4("n");
            this.\u00cf = new URL(s2);
            String s17 = this.\u00df(this.\u00cf.toString());
            if (s17.indexOf("/", "http://".length()) < 0) {
                s17 += "/";
            }
            this.\u00c6("n", s17);
            this.\u00d0 = this.\u00d4(this.\u00cf);
            final Hashtable hashtable = (Hashtable)this.\u00c5("d");
            final Hashtable hashtable2 = (Hashtable)this.\u00c5("e");
            final boolean \u00e43 = this.\u00e4(s17, hashtable);
            final boolean \u00e44 = this.\u00e4(s17, hashtable2);
            String s18;
            if (s17.equals(\u00e4)) {
                s18 = "d";
            }
            else {
                s18 = "a";
            }
            this.\u00c8[0] = s18;
            if (\u00e43) {
                this.\u00d4 = 2;
            }
            else if (\u00e44) {
                this.\u00d4 = 1;
                this.\u00e3(s17, hashtable2);
            }
            else {
                this.\u00d4 = 0;
            }
            this.\u00e1(s17, "", hashtable, 4000);
            if (\u00df != null) {
                if (\u00df.length() == 0) {
                    if (b) {
                        this.\u00c6("k", -1L);
                    }
                }
                else {
                    Object \u00f4 = null;
                    try {
                        \u00f4 = this.\u00d4(new URL(\u00df));
                    }
                    catch (Exception ex) {}
                    \u00df = this.\u00df(\u00df);
                    if (s16 == null) {
                        s16 = \u00df;
                    }
                    else if (this.\u00c8("p") > 0) {
                        if (!s16.equals(\u00df) && !s16.equals(\u00df + "/")) {
                            s16 = \u00df;
                            this.\u00c6("k", -1L);
                        }
                    }
                    else if (b && \u00f4 != null && !this.\u00d0.equals(\u00f4)) {
                        s16 = \u00df;
                        this.\u00c6("k", -1L);
                    }
                }
                this.\u00c6("p", b ? 1 : 0);
            }
            if (s18.equals("d")) {
                this.\u00c8[2] = \u00e42;
            }
            else {
                this.\u00c8[2] = s16;
            }
            this.\u00c6("o", s16);
            this.\u00c8[1] = s17;
            if (Math.random() <= FcPred.\u00c2) {
                substring = substring.substring(0, Math.min(80, substring.length()));
                this.\u00c8[6] = "t" + this.\u00df(substring);
            }
            final String \u00e45 = this.\u00c4("l");
            if (\u00e45 != null) {
                this.\u00e7(6, \u00e45, "|");
                this.\u00c6("l", null);
            }
            final int \u00e8 = this.\u00c8("g");
            if (\u00e8 >= 0) {
                final int \u00e82 = this.\u00c8("f");
                if (\u00e82 >= 0) {
                    this.\u00c8[11] = "" + \u00e82;
                }
                this.\u00c8[12] = "" + \u00e8;
                this.\u00c8[13] = "" + this.\u00c8("i");
                this.\u00c8[14] = "" + this.\u00c8("h");
            }
            this.\u00c6("f", -1L);
            this.\u00c6("g", 0L);
            this.\u00c6("i", 0L);
            this.\u00c6("h", 0L);
            final long currentTimeMillis = System.currentTimeMillis();
            final long \u00e7 = this.\u00c7("k");
            if (\u00e7 > 0L) {
                int n5 = (int)((currentTimeMillis - \u00e7 + 500L) / 1000L);
                if (n5 >= 0) {
                    if (n5 > 1800) {
                        n5 = 1800;
                    }
                    this.\u00c8[7] = "" + n5;
                }
                final long \u00e72 = this.\u00c7("j");
                final int n6 = (int)((\u00e7 - \u00e72 + 500L) / 1000L);
                if (\u00e72 > 0L && n6 >= 0) {
                    this.\u00c8[8] = "" + n6;
                }
            }
            this.\u00c6("j", currentTimeMillis);
            this.\u00c5 = (Math.random() < FcPred.\u00c2);
            this.\u00c8[10] = s;
            final int \u00f9 = this.\u00d9(this.\u00c8("q"));
            this.\u00c8[18] = this.\u00da(\u00f9);
            this.\u00c8[20] = this.\u00da(this.\u00c8("s"));
            this.\u00c6("s", \u00f9);
            this.\u00c8[19] = "" + FcPred.\u00ce;
            return s15;
        }
        catch (Exception ex2) {
            return "";
        }
    }
    
    private final void \u00cb(String s, final int n) {
        try {
            if (this.\u00e4(s, this.\u00e2)) {
                return;
            }
            this.\u00e1(s, "", this.\u00e2, 4000);
            final URL url = new URL(this.getDocumentBase(), \u00cc(s));
            s = url.toString();
            final Hashtable hashtable = (Hashtable)this.\u00c5("d");
            final Hashtable hashtable2 = (Hashtable)this.\u00c5("e");
            final boolean \u00e4 = this.\u00e4(s, hashtable2);
            final boolean \u00e42 = this.\u00e4(s, hashtable);
            if (\u00e4) {
                ++this.\u00d2;
                this.\u00e3(s, hashtable2);
            }
            else if (!\u00e42 && n != -1) {
                ++this.\u00d3;
            }
            this.\u00e1(s, "", hashtable, 4000);
            if (n == -1 || n == -2) {
                return;
            }
            if (!this.\u00c5) {
                return;
            }
            final String s2 = this.\u00c8[9];
            if (s2 != null) {}
            if ((s2 == null || s2.length() < 1000) && Math.random() < FcPred.\u00c3) {
                if (this.\u00d0.equals(this.\u00d4(url))) {
                    s = url.getFile();
                }
                String s3 = '|' + this.\u00df(s);
                if (n > -1) {
                    s3 = s3 + "`" + n;
                }
                if (s2 == null) {
                    this.\u00c8[9] = "";
                }
                final StringBuffer sb = new StringBuffer();
                final String[] \u00e8 = this.\u00c8;
                final int n2 = 9;
                \u00e8[n2] = sb.append(\u00e8[n2]).append(s3).toString();
                this.\u00c6 = true;
            }
        }
        catch (Exception ex) {}
    }
    
    private static String \u00cc(final String s) {
        return s.replace('\\', '/');
    }
    
    private void \u00cd(final Object o) {
        try {
            final StringBuffer sb = (StringBuffer)o;
            synchronized (this.\u00d6) {
                this.\u00d6[this.\u00dd] = sb;
                this.\u00d6.notify();
            }
        }
        catch (Exception ex) {}
    }
    
    private String \u00ce() {
        ++this.\u00e4;
        if (this.\u00e4 == this.\u00de) {
            synchronized (this) {
                this.notify();
            }
            return null;
        }
        return this.\u00da[this.\u00e4];
    }
    
    private String \u00cf() {
        try {
            this.\u00e3 = 2;
            final String \u00f4 = this.\u00d4(new URL(this.\u00da[this.\u00e4]));
            Object value = this.\u00d8.get(\u00f4);
            String s = "";
            this.\u00dd = -1;
            if (value == null) {
                if (!this.\u00dc[this.\u00e4]) {
                    return "";
                }
                value = \u00f4;
            }
            else if (value instanceof Integer) {
                this.\u00dd = (int)value;
            }
            if (this.\u00dd == -1) {
                if (!this.\u00e0()) {
                    return "";
                }
                this.\u00dd = this.\u00d9;
                s = (String)value;
                this.\u00d8.put(\u00f4, new Integer(this.\u00dd));
                ++this.\u00d9;
            }
            this.\u00e3 = 0;
            synchronized (this) {
                this.notify();
            }
            return s;
        }
        catch (Exception ex) {
            this.\u00e3 = -1;
            return "";
        }
    }
    
    public int dDl() {
        if (this.\u00e3 == -1) {
            synchronized (this) {
                this.notify();
            }
        }
        return this.\u00e3;
    }
    
    public void run() {
        this.\u00d5(this.\u00ea());
        this.\u00e7 = true;
        if (this.\u00d5.get("fcOff") != null) {
            this.\u00e0 = true;
            FcPred.\u00cd = true;
            return;
        }
        while (true) {
            synchronized (this) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
            if (this.\u00e0) {
                return;
            }
            if (this.\u00e3 == -1 || this.\u00e4 == this.\u00de) {
                this.\u00d1();
                return;
            }
            final String string = (this.\u00db[this.\u00e4] ? "+" : "") + this.\u00da[this.\u00e4];
            this.\u00d8(string, this.\u00dd);
            if (this.\u00e0) {
                return;
            }
            this.\u00e3 = (this.\u00d2(this.\u00dd, string) ? 1 : -1);
        }
    }
    
    private void \u00d0(final String s, final boolean b) {
        if (this.\u00e3 == 2) {
            this.\u00e3 = 0;
            return;
        }
        this.\u00c9("g");
        if (b) {
            this.\u00e1(s, "", (Hashtable)this.\u00c5("e"), 4000);
            return;
        }
        this.\u00e1(s, "", (Hashtable)this.\u00c5("d"), 4000);
        this.\u00d1();
    }
    
    private final void \u00d1() {
        for (int i = 0; i < this.\u00d9; ++i) {
            if (this.\u00e0) {
                return;
            }
            if (this.\u00d6[i] != null) {
                this.\u00d8(";", i);
            }
        }
        synchronized (this) {
            this.\u00c7 = null;
        }
    }
    
    private final boolean \u00d2(final int n, final String s) {
        final StringBuffer sb = this.\u00d6[n];
        synchronized (sb) {
            while (this.\u00c7 != null) {
                if (sb.length() >= 2 && sb.charAt(0) == 'L') {
                    if (sb.charAt(1) != '0') {
                        return false;
                    }
                    final String string = sb.toString();
                    final int lastIndex = string.lastIndexOf(124);
                    if (lastIndex > 0) {
                        try {
                            int int1 = Integer.parseInt(string.substring(lastIndex + 1));
                            if (int1 > 500) {
                                int1 = -1;
                            }
                            if (FcPred.\u00ce == 1 && int1 >= 56) {
                                int1 = -1;
                            }
                            this.\u00c6("q", this.\u00d3(this.\u00c8("q"), int1));
                        }
                        catch (Exception ex) {}
                        sb.setLength(lastIndex);
                    }
                    return true;
                }
                else {
                    try {
                        sb.wait(200L);
                    }
                    catch (InterruptedException ex2) {}
                }
            }
            return false;
        }
    }
    
    private final int \u00d3(final int n, final int n2) {
        if (n2 <= 0) {
            return n;
        }
        if (n < 0) {
            return n2;
        }
        return (Math.min(n2, n) * 9 + Math.max(n2, n)) / 10;
    }
    
    private final String \u00d4(final URL url) {
        final int port = url.getPort();
        return url.getHost() + ((port == -1) ? "" : (":" + port));
    }
    
    private final void \u00d5(String replace) {
        (this.\u00d8 = new Hashtable()).put(this.\u00d0, this.\u00d0);
        String \u00e4 = this.\u00c4("m");
        if (\u00e4 == null) {
            \u00e4 = this.\u00d5.get("fcAk");
            if (\u00e4 == null) {
                \u00e4 = "";
            }
            this.\u00c6("m", \u00e4);
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(\u00e4, ";");
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            final int index = nextToken.indexOf(47);
            if (index >= 0) {
                this.\u00d8.put(nextToken.substring(0, index), nextToken);
            }
            else {
                this.\u00d8.put(nextToken, nextToken);
            }
        }
        replace = replace.replace('|', ';');
        final StringTokenizer stringTokenizer2 = new StringTokenizer(replace, ";");
        while (stringTokenizer2.hasMoreTokens()) {
            this.\u00d6(stringTokenizer2.nextToken());
        }
    }
    
    private final void \u00d6(final String s) {
        this.\u00c9("i");
        try {
            final boolean b = true;
            final boolean b2 = false;
            int n = 0;
            if (s.charAt(n++) == '!') {
                return;
            }
            final String string = new URL(this.\u00cf, s).toString();
            if (this.\u00e4(string, (Hashtable)this.\u00c5("d")) || this.\u00e4(string, (Hashtable)this.\u00c5("e"))) {
                this.\u00c9("h");
                return;
            }
            if (this.\u00e4(string, this.\u00df)) {
                return;
            }
            this.\u00e1(string, "", this.\u00df, 100);
            this.\u00da[this.\u00de] = string;
            this.\u00db[this.\u00de] = b2;
            this.\u00dc[this.\u00de] = b;
            ++this.\u00de;
        }
        catch (MalformedURLException ex) {}
    }
    
    private final void \u00d8(final String s, final int n) {
        StringBuffer sb;
        synchronized (this.\u00d6) {
            while (true) {
                sb = this.\u00d6[n];
                if (sb != null) {
                    break;
                }
                try {
                    this.\u00d6.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
        synchronized (sb) {
            sb.setLength(0);
            sb.append('P' + s);
            sb.notify();
        }
    }
    
    private final int \u00d9(int n) {
        if (FcPred.\u00ce == 1 && n >= 56) {
            n = 56;
            this.\u00d9("q");
        }
        else if (n < 0) {}
        return n;
    }
    
    private void \u00d9(final String s) {
        if (this.\u00c8(s) >= 56) {
            this.\u00c6(s, 56L);
        }
    }
    
    private final String \u00da(final int n) {
        return (n == -1) ? null : ("" + n);
    }
    
    private final String \u00db(int n, final long n2, final long n3) {
        String string = "";
        if (this.\u00d1 == null) {
            return null;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        this.\u00ca = FcPred.\u00c9.get(this.\u00d1);
        int \u00e9 = 0;
        String s;
        if (this.\u00ca != null && currentTimeMillis - this.\u00c7("b") < FcPred.\u00e5 * 60 * 1000) {
            s = this.\u00c4("a");
            \u00e9 = this.\u00c9("c");
        }
        else {
            this.\u00c3();
            ++n;
            string += n;
            if (n2 > 0L) {
                this.\u00c8[23] = "" + (n3 - n2) / 3600000L;
            }
            s = "" + (int)(Math.random() * 2.147483647E9);
            if (n > 0) {
                s = s + "R" + n;
            }
            this.\u00c6("a", s);
        }
        this.\u00c8[4] = s;
        this.\u00c8[5] = "" + \u00e9;
        this.\u00c6("b", currentTimeMillis);
        return string;
    }
    
    private final String \u00dc(final String s, final String s2, final String s3) {
        final String s4 = "";
        if (this.\u00d1 == null) {
            return null;
        }
        this.\u00ca = FcPred.\u00c9.get(this.\u00d1);
        if (this.\u00ca == null) {
            this.\u00c3();
        }
        this.\u00c8[4] = s;
        this.\u00c8[5] = s2;
        this.\u00c8[23] = s3;
        return s4;
    }
    
    private final void \u00dd() {
        if (this.\u00c8[12] != null) {
            this.\u00c8[15] = "" + this.\u00d2;
            this.\u00c8[16] = "" + this.\u00d3;
            this.\u00c8[17] = "" + this.\u00d4;
        }
    }
    
    private final String \u00de() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.\u00c8.length; ++i) {
            sb.append('}');
            if (this.\u00c8[i] != null) {
                sb.append(this.\u00c8[i]);
            }
        }
        return "3/u/" + sb.toString();
    }
    
    private final String \u00df(final String s) {
        final int length = s.length();
        final StringBuffer sb = new StringBuffer(length);
        int i = 0;
    Label_0017:
        while (i < length) {
            final char char1 = s.charAt(i);
            int n = 0;
            while (true) {
                while (char1 != FcPred.\u00c4[n]) {
                    if (++n == FcPred.\u00c4.length) {
                        if (Character.isWhitespace(char1)) {
                            sb.append(' ');
                        }
                        else {
                            sb.append(char1);
                        }
                        ++i;
                        continue Label_0017;
                    }
                }
                sb.append('{');
                sb.append(n);
                continue;
            }
        }
        return sb.toString();
    }
    
    private final boolean \u00e0() {
        return this.\u00d9 < 3;
    }
    
    private final void \u00e1(final String s, final String s2, final Hashtable hashtable, final int n) {
        hashtable.put(new Integer(this.\u00e5(s)), s2);
        if (n > 0 && hashtable.size() > n) {
            hashtable.clear();
        }
    }
    
    private final String \u00e2(final String s, final Hashtable hashtable) {
        return hashtable.get(new Integer(this.\u00e5(s)));
    }
    
    private final String \u00e3(final String s, final Hashtable hashtable) {
        return hashtable.remove(new Integer(this.\u00e5(s)));
    }
    
    private final boolean \u00e4(final String s, final Hashtable hashtable) {
        return this.\u00e2(s, hashtable) != null;
    }
    
    private final int \u00e5(final String s) {
        try {
            final byte[] bytes = s.getBytes();
            int n = 0;
            for (int length = s.length(), i = 0; i < length; ++i) {
                byte b = bytes[i];
                if (b == 123) {
                    if (++i == length) {
                        break;
                    }
                    final byte b2 = (byte)(bytes[i] - 48);
                    if (b2 < 0) {
                        continue;
                    }
                    if (b2 >= FcPred.\u00c4.length) {
                        continue;
                    }
                    b = (byte)FcPred.\u00c4[b2];
                }
                n = 31 * n + b;
            }
            return n;
        }
        catch (Exception ex) {
            return 2;
        }
    }
    
    private String \u00e6(final byte[] array, final String s) {
        if (array.length == 0) {
            return "";
        }
        final byte[] array2 = new byte[array.length + FcPred.\u00e6.length];
        System.arraycopy(FcPred.\u00e6, 0, array2, 0, FcPred.\u00e6.length);
        System.arraycopy(array, 0, array2, FcPred.\u00e6.length, array.length);
        final byte[] array3 = new byte[15000];
        int n = 0;
        try {
            final GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(array2));
            while (true) {
                final int read = gzipInputStream.read(array3, n, array3.length - n);
                if (read <= 0) {
                    break;
                }
                n += read;
            }
        }
        catch (Exception ex) {}
        final String s2 = new String(array3, 0, n);
        String s3;
        if (s == null || s.length() == 0) {
            s3 = s2;
        }
        else {
            s3 = s2;
        }
        return s3;
    }
    
    private final void \u00e7(final int n, final String s, final String s2) {
        if (this.\u00c8[n] == null) {
            this.\u00c8[n] = s;
        }
        else {
            final StringBuffer sb = new StringBuffer();
            final String[] \u00e8 = this.\u00c8;
            \u00e8[n] = sb.append(\u00e8[n]).append(s2).append(s).toString();
        }
    }
    
    private final int \u00e8(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (Exception ex) {
            return -1;
        }
    }
    
    private final long \u00e9(final String s) {
        try {
            return Long.parseLong(s);
        }
        catch (Exception ex) {
            return -1L;
        }
    }
    
    public final boolean booleanValue() {
        return true;
    }
    
    public final void stop() {
        if (FcPred.\u00cb && this.\u00e1 == 0) {
            ++this.\u00e1;
            return;
        }
        try {
            if (this.\u00e0) {
                return;
            }
            this.\u00e0 = true;
            if (!this.\u00c1) {
                this.\u00c0();
            }
            if (this.\u00c7 != null) {
                this.\u00c1();
            }
        }
        catch (Exception ex) {}
    }
    
    private String \u00ea() {
        String \u00e6 = "";
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            String string = "/service/hint";
            if (this.\u00c4("m") != null) {
                string += "?nA=f";
            }
            final URL url = new URL(this.getCodeBase(), string);
            final byte[] array = new byte[10000];
            final URLConnection openConnection = url.openConnection();
            openConnection.setUseCaches(false);
            openConnection.setDoOutput(true);
            final String \u00fe = this.\u00de();
            openConnection.setRequestProperty("content-type", "application/vnd.fireclick.report.1");
            if (FcPred.\u00cb && FcPred.\u00cc != null) {
                openConnection.setRequestProperty("User-agent", FcPred.\u00cc);
            }
            final int \u00eb = this.\u00eb(array, 0, \u00fe);
            openConnection.setRequestProperty("content-length", "" + \u00eb);
            final OutputStream outputStream = openConnection.getOutputStream();
            outputStream.write(array, 0, \u00eb);
            FcPred.\u00c9.put(this.\u00d1, this.\u00ca);
            outputStream.close();
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
            final byte[] array2 = new byte[5000];
            int n = 0;
            while (true) {
                final int read = bufferedInputStream.read(array2, n, array2.length - n);
                if (read < 0 || array2.length == n) {
                    break;
                }
                n += read;
            }
            this.\u00c6("f", (int)((System.currentTimeMillis() - currentTimeMillis) / 1000L));
            bufferedInputStream.close();
            this.\u00d5.put("fcBD", "");
            this.\u00ec(array2, n);
            final byte[] value = this.\u00d5.get("fcPreds");
            if (value != null) {
                \u00e6 = this.\u00e6(value, this.\u00d5.get("fcBD"));
            }
            if (\u00e6.length() == 0) {}
        }
        catch (Exception ex) {}
        return \u00e6;
    }
    
    private final int \u00eb(final byte[] array, final int n, final String s) {
        final int length = s.length();
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            final byte b = (byte)char1;
            array[n + i] = (byte)(((char)b == char1) ? b : 32);
        }
        return n + length;
    }
    
    private void \u00ec(final byte[] array, final int n) {
        int n2 = 0;
        final String s = new String(array, 0, n);
        while (true) {
            final int index = s.indexOf("=\"", n2);
            if (index < 0) {
                break;
            }
            final String trim = s.substring(n2, index).trim();
            n2 = index + "=\"".length();
            final String trim2 = trim.substring(Math.max(trim.lastIndexOf(";"), trim.lastIndexOf("\n")) + 1).trim();
            if (trim2.equals("")) {
                continue;
            }
            if (trim2.equals("fcPreds")) {
                final int index2 = s.indexOf("\n", n2);
                if (index2 < 0) {
                    break;
                }
                this.\u00d5.put("fcBD", s.substring(n2, index2));
                final int n3 = index2 + 1;
                final int n4 = n - n3;
                final byte[] array2 = new byte[n4];
                System.arraycopy(array, n3, array2, 0, n4);
                this.\u00d5.put(trim2, array2);
                break;
            }
            else {
                final int index3 = s.indexOf("\"", n2);
                if (index3 < 0) {
                    break;
                }
                this.\u00d5.put(trim2, s.substring(n2, index3));
                n2 = index3 + 1;
            }
        }
    }
    
    static {
        FcPred.\u00c2 = 1.0;
        FcPred.\u00c3 = 0.08;
        \u00c4 = new char[] { ';', '|', '`', '}', '{', '\'', '\"' };
        \u00c9 = new Hashtable();
        FcPred.\u00cb = true;
        FcPred.\u00cc = null;
        FcPred.\u00cd = false;
        FcPred.\u00ce = 0;
        FcPred.\u00e5 = 30;
        \u00e6 = new byte[] { 31, -117, 8, 0, 0, 0, 0, 0, 0, 0 };
    }
}
