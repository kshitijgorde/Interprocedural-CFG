// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.util;

import java.util.Date;
import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Hashtable;
import java.io.PrintStream;

public class NFDebug
{
    public static final long ALL = -1L;
    public static final long LICENSE = 1L;
    public static final long PARAM = 2L;
    public static final long SERVER = 4L;
    public static final long JDBC = 8L;
    public static final long DWELL = 16L;
    public static final long SYMBOL = 32L;
    public static final long FILE = 64L;
    public static final long BEANS = 128L;
    public static final long AXIS = 256L;
    public static final long GRAPH = 512L;
    public static final long LEGEND = 1024L;
    public static final long WINDOW = 2048L;
    public static final long ACTION = 4096L;
    public static final long NOTES = 8192L;
    public static final long THREAD = 16384L;
    public static final long CACHE = 32768L;
    public static final long IMAGE = 65536L;
    public static final long AGENT = 131072L;
    public static final long HTTP = 262144L;
    public static final long SECURITY = 524288L;
    public static final long REMOTE = 1048576L;
    public static PrintStream printStream;
    private static long a;
    private static Hashtable b;
    private static Vector c;
    private static String d;
    private static long e;
    private static Runtime f;
    private static int g;
    
    public static Hashtable getOptions() {
        if (NFDebug.b == null) {
            (NFDebug.b = new Hashtable()).put("ALL", new Long(-1L));
            NFDebug.b.put("LICENSE", new Long(1L));
            NFDebug.b.put("PARAM", new Long(2L));
            NFDebug.b.put("SERVER", new Long(4L));
            NFDebug.b.put("JDBC", new Long(8L));
            NFDebug.b.put("DWELL", new Long(16L));
            NFDebug.b.put("SYMBOL", new Long(32L));
            NFDebug.b.put("FILE", new Long(64L));
            NFDebug.b.put("BEANS", new Long(128L));
            NFDebug.b.put("AXIS", new Long(256L));
            NFDebug.b.put("GRAPH", new Long(512L));
            NFDebug.b.put("LEGEND", new Long(1024L));
            NFDebug.b.put("WINDOW", new Long(2048L));
            NFDebug.b.put("ACTION", new Long(4096L));
            NFDebug.b.put("NOTES", new Long(8192L));
            NFDebug.b.put("THREAD", new Long(16384L));
            NFDebug.b.put("CACHE", new Long(32768L));
            NFDebug.b.put("IMAGE", new Long(65536L));
            NFDebug.b.put("AGENT", new Long(131072L));
            NFDebug.b.put("HTTP", new Long(262144L));
            NFDebug.b.put("SECURITY", new Long(524288L));
            NFDebug.b.put("REMOTE", new Long(1048576L));
        }
        return NFDebug.b;
    }
    
    public static void set(final long n) {
        NFDebug.a |= n;
        a();
    }
    
    private static void a() {
        if (NFDebug.c == null) {
            return;
        }
        for (int i = 0; i < NFDebug.c.size(); ++i) {
            ((NFDebugObserver)NFDebug.c.elementAt(i)).debugMask(NFDebug.a);
        }
    }
    
    public static void set(final String s) {
        final Hashtable options = getOptions();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (stringTokenizer.hasMoreTokens()) {
            final Long n = options.get(stringTokenizer.nextToken().trim().toUpperCase());
            if (n != null) {
                NFDebug.a |= n;
            }
        }
        a();
    }
    
    public static void set(final Vector vector) {
        if (vector == null) {
            return;
        }
        final Enumeration<Number> elements = vector.elements();
        while (elements.hasMoreElements()) {
            NFDebug.a |= elements.nextElement().longValue();
        }
        a();
    }
    
    public static void clear(final long n) {
        NFDebug.a &= ~n;
        a();
    }
    
    public static void clear(final String s) {
        final Hashtable options = getOptions();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
        while (stringTokenizer.hasMoreTokens()) {
            final Long n = options.get(stringTokenizer.nextToken().trim().toUpperCase());
            if (n != null) {
                NFDebug.a &= ~n;
            }
        }
        a();
    }
    
    public static void clear(final Vector vector) {
        if (vector == null) {
            return;
        }
        final Enumeration<Number> elements = vector.elements();
        while (elements.hasMoreElements()) {
            NFDebug.a &= ~elements.nextElement().longValue();
        }
        a();
    }
    
    public static boolean enabled(final long n) {
        return (NFDebug.a & n) != 0x0L;
    }
    
    public static void print(final String s) {
        print(NFDebug.printStream, s);
    }
    
    public static void print(final long n, final String s) {
        if (enabled(n)) {
            print(NFDebug.printStream, s);
        }
    }
    
    public static void print(final PrintStream printStream, final String s) {
        if (printStream != null) {
            println(printStream, s);
        }
        if (NFDebug.c == null) {
            return;
        }
        for (int i = 0; i < NFDebug.c.size(); ++i) {
            ((NFDebugObserver)NFDebug.c.elementAt(i)).debugMessage(s);
        }
    }
    
    public static void exception(final Exception ex) {
        exception(NFDebug.printStream, "", ex);
    }
    
    public static void exception(final String s, final Exception ex) {
        exception(NFDebug.printStream, s, ex);
    }
    
    public static void exception(final PrintStream printStream, final String s, final Exception ex) {
        if (printStream != null) {
            println(printStream, "EXCEPTION: " + s + "\n\tMessage: " + ex.getMessage() + "\n\tStack Trace: ");
            ex.printStackTrace(printStream);
        }
        if (NFDebug.c == null) {
            return;
        }
        for (int i = 0; i < NFDebug.c.size(); ++i) {
            ((NFDebugObserver)NFDebug.c.elementAt(i)).debugException(s, ex);
        }
    }
    
    public static void warning(final Object o) {
        warning(NFDebug.printStream, o);
    }
    
    public static void warning(final PrintStream printStream, final Object o) {
        if (printStream != null) {
            println(printStream, "WARNING: " + o.toString());
        }
        if (NFDebug.c == null) {
            return;
        }
        for (int i = 0; i < NFDebug.c.size(); ++i) {
            ((NFDebugObserver)NFDebug.c.elementAt(i)).debugWarning(o);
        }
    }
    
    public static void information(final Object o) {
        information(NFDebug.printStream, o);
    }
    
    public static void information(final PrintStream printStream, final Object o) {
        if (printStream != null) {
            println(printStream, "INFORMATION: " + o.toString());
        }
        if (NFDebug.c == null) {
            return;
        }
        for (int i = 0; i < NFDebug.c.size(); ++i) {
            ((NFDebugObserver)NFDebug.c.elementAt(i)).debugInfo(o);
        }
    }
    
    public static void addObserver(final NFDebugObserver nfDebugObserver) {
        if (NFDebug.c == null) {
            NFDebug.c = new Vector();
        }
        NFDebug.c.removeElement(nfDebugObserver);
        NFDebug.c.addElement(nfDebugObserver);
    }
    
    public static void removeObserver(final NFDebugObserver nfDebugObserver) {
        if (NFDebug.c == null) {
            return;
        }
        NFDebug.c.removeElement(nfDebugObserver);
    }
    
    public static void timerReset() {
        NFDebug.e = System.currentTimeMillis();
    }
    
    public static void timerSplit(final String s) {
        final long currentTimeMillis = System.currentTimeMillis();
        print(s + " = " + (currentTimeMillis - NFDebug.e));
        NFDebug.e = currentTimeMillis;
    }
    
    public static void memCheck(final String s, final boolean b) {
        if (NFDebug.f == null) {
            NFDebug.f = Runtime.getRuntime();
        }
        ++NFDebug.g;
        if (b) {
            NFDebug.f.gc();
        }
        print(s + " call = " + NFDebug.g + ", total = " + NFDebug.f.totalMemory() + " free = " + NFDebug.f.freeMemory());
    }
    
    public static void println(final PrintStream printStream, String string) {
        if (printStream != null) {
            if (NFDebug.d != null && NFUtil.getJDKVersion() >= 1.1) {
                string = NF11Util.formatDate(new Date(), NFDebug.d) + string;
            }
            printStream.println(string);
        }
    }
    
    public static void setDateFormat(final String d) {
        NFDebug.d = d;
    }
    
    static {
        NFDebug.printStream = System.out;
        NFDebug.a = 0L;
        NFDebug.b = null;
        NFDebug.c = null;
        NFDebug.d = null;
        NFDebug.f = null;
        NFDebug.g = 0;
    }
}
