import java.util.Enumeration;
import java.util.StringTokenizer;

// 
// Decompiled by Procyon v0.5.30
// 

public class y
{
    public static h a;
    public static String b;
    public static g c;
    public static /* synthetic */ Class d;
    
    public static final void a(final h h) {
        f.e.a(h);
    }
    
    public static final void a(final String s) {
        if (f.e.i()) {
            f.e.g("levelSetup:" + s);
        }
        if (s == null || s.length() == 0) {
            if (f.e.g()) {
                f.e.d("null or empty setup string int levelSetup");
            }
            return;
        }
        if (s.indexOf(":") > -1) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
            int n = 0;
            while (stringTokenizer.hasMoreTokens()) {
                ++n;
                final String nextToken = stringTokenizer.nextToken();
                final int index = nextToken.indexOf(":");
                if (index > -1) {
                    try {
                        final String substring = nextToken.substring(0, index);
                        final String substring2 = nextToken.substring(index + 1, nextToken.length());
                        if (substring2.equals(h.l.b())) {
                            continue;
                        }
                        f.a(substring, h.a(substring2));
                        continue;
                    }
                    catch (Exception ex) {
                        if (f.e.g()) {
                            f.e.b("cannot create name:level pair from setup item", ex);
                        }
                        return;
                    }
                }
                if (n == 1) {
                    d(nextToken);
                }
            }
        }
        else {
            try {
                d(s);
            }
            catch (NumberFormatException ex2) {
                if (f.e.i()) {
                    f.e.e("cannot convert level setup to level integer", ex2);
                }
            }
        }
    }
    
    private static void d(final String s) {
        final h a = h.a(s);
        f.a().a(a);
        final Enumeration b = f.b();
        while (b.hasMoreElements()) {
            f.a(b.nextElement(), a);
        }
    }
    
    public static final void b(final String b) {
        y.b = b;
    }
    
    public static final String a() {
        return y.b;
    }
    
    public static final boolean c(String substring) {
        if (substring == null) {
            return false;
        }
        try {
            boolean b = false;
            if (substring.startsWith("+")) {
                b = true;
                substring = substring.substring(1, substring.length());
            }
            final int index = substring.indexOf(";");
            if (index > 0) {
                final i i = (i)Class.forName(substring.substring(0, index)).getConstructor((y.d == null) ? (y.d = class$("java.lang.String")) : y.d).newInstance(substring.substring(index + 1, substring.length()));
                if (b) {
                    f.a().b(i);
                }
                else {
                    f.a().a(i);
                }
                return true;
            }
            if (f.e.g()) {
                f.e.d("Setup no semicolon found in appender setup");
            }
        }
        catch (Exception ex) {
            if (f.e.g()) {
                f.e.b("cannot perform appender setup with " + substring, ex);
            }
        }
        return false;
    }
    
    public static final void a(final v v) {
        final String a = v.a("LOGLOG_LEVEL", null);
        if (a != null) {
            a(h.a(a));
        }
        final String a2 = v.a("LOG", null);
        if (a2 != null) {
            a(a2);
        }
        final String a3 = v.a("APPENDER_SETUP", null);
        if (a3 != null) {
            c(a3);
        }
        final String a4 = v.a("LOG_APPLICATION", null);
        if (a4 != null) {
            b(a4);
        }
        final String a5 = v.a("ENABLE_ASSERTIONS", null);
        if (a5 != null) {
            f.a(a5.equalsIgnoreCase("true"));
        }
        final String a6 = v.a("STACKTRACE_LEVEL", null);
        if (a6 != null) {
            b(h.a(a6));
        }
    }
    
    public static final void b(final h a) {
        y.a = a;
    }
    
    public static final h b() {
        return y.a;
    }
    
    public static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        y.a = h.i;
        y.b = "";
        y.c = f.a();
    }
}
