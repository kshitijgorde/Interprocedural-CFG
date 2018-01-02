import java.util.StringTokenizer;
import java.util.Locale;
import java.net.URLConnection;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class do extends Hashtable implements ak
{
    private static int a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private String g;
    private String h;
    private int i;
    private StringBuffer j;
    private String k;
    public int l;
    
    public do(final URLConnection urlConnection) throws dp {
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.f = 200;
        this.g = null;
        this.h = "OK";
        this.i = ++do.a;
        this.j = new StringBuffer();
        this.k = null;
        this.l = 0;
        this.d(this.g = urlConnection.getHeaderField(0));
        int n = 1;
        while (true) {
            final String headerFieldKey = urlConnection.getHeaderFieldKey(n);
            if (headerFieldKey == null) {
                break;
            }
            final String headerField = urlConnection.getHeaderField(headerFieldKey);
            if (headerFieldKey.equalsIgnoreCase("Content-Type")) {
                this.b(headerField);
            }
            if (headerFieldKey.equalsIgnoreCase("Error-Msg")) {
                this.c(headerField);
            }
            this.l += headerField.length() + headerFieldKey.length() + 4;
            final String upperCase = headerFieldKey.toUpperCase(Locale.ENGLISH);
            this.put(upperCase, headerField);
            this.a(upperCase, headerField);
            ++n;
        }
        if (ak.a.j()) {
            ak.a.h("ctor " + this.toString() + " from url=" + urlConnection.getURL() + " encoding=" + this.a() + " [" + (Object)this.j + "]");
        }
    }
    
    public do(final String s) throws dp {
        this(s, true);
    }
    
    public do(final String s, final boolean b) throws dp {
        this.b = -1;
        this.c = -1;
        this.d = -1;
        this.e = -1;
        this.f = 200;
        this.g = null;
        this.h = "OK";
        this.i = ++do.a;
        this.j = new StringBuffer();
        this.k = null;
        this.l = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\r\n");
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (b && n == 0) {
                n = 1;
                if (this.d(nextToken)) {
                    this.g = nextToken;
                    continue;
                }
            }
            final int index = nextToken.indexOf(":");
            if (index > -1) {
                final String trim = nextToken.substring(0, index).trim();
                if (this.g == null && trim.equalsIgnoreCase("CL")) {
                    this.b = 3;
                }
                final String trim2 = nextToken.substring(index + 2, nextToken.length()).trim();
                final String upperCase = trim.toUpperCase(Locale.ENGLISH);
                this.a(upperCase, trim2);
                this.put(upperCase, trim2);
                if (upperCase.equalsIgnoreCase("Content-Type")) {
                    this.b(trim2);
                }
                if (!upperCase.equalsIgnoreCase("Error-Msg")) {
                    continue;
                }
                this.c(trim2);
            }
        }
        this.l = s.length();
        if (ak.a.j()) {
            ak.a.h("ctor " + this.toString() + " from statusLine=" + this.g + " encoding=" + this.a() + " [" + (Object)this.j + "]");
        }
    }
    
    private void a(final String s, final String s2) {
        this.j.append(s + ": " + s2 + "\\r\\n");
    }
    
    private void b(final String s) {
        final int index = s.indexOf("=");
        if (index > 0) {
            this.k = s.substring(index + 1);
        }
    }
    
    private void c(final String s) {
        if (this.h == null) {
            this.h = "[Error-Msg:" + s + "]";
        }
        else {
            this.h = this.h + "; [Error-Msg:" + s + "]";
        }
    }
    
    private boolean d(final String s) throws dp {
        if (s == null) {
            return false;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        int n = 0;
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken();
            if (n == 0 && !nextToken.startsWith("HTTP")) {
                return false;
            }
            if (n == 1) {
                try {
                    this.f = Integer.parseInt(nextToken);
                }
                catch (NumberFormatException ex) {
                    throw new dp("No status code.", ex);
                }
            }
            if (n == 2) {
                this.h = nextToken;
                while (stringTokenizer.hasMoreTokens()) {
                    this.h = this.h + " " + stringTokenizer.nextToken();
                }
            }
            ++n;
        }
        return true;
    }
    
    public final int a() throws dp {
        if (this.b != -1) {
            return this.b;
        }
        final String s = this.get("OV-CE");
        if (s != null) {
            if (s.equalsIgnoreCase("http")) {
                this.b = 1;
            }
            else {
                if (!s.equalsIgnoreCase("push")) {
                    throw new dp("Unknown OV_CE value: " + s);
                }
                this.b = 2;
            }
        }
        else {
            this.b = 0;
        }
        return this.b;
    }
    
    public final int b() throws dp {
        if (this.d > -1) {
            return this.d;
        }
        String s = this.get("CONTENT-LENGTH");
        if (s == null) {
            s = this.get("CL");
        }
        if (s != null) {
            try {
                this.d = new Integer(s);
                return this.d;
            }
            catch (Exception ex) {
                throw new dp("Cannot convert content length : " + s, ex);
            }
            throw new dp("No content length header");
        }
        throw new dp("No content length header");
    }
    
    public final int c() throws dp {
        if (this.e > -1) {
            return this.e;
        }
        final String s = this.get("OV-CE-LENGTH");
        if (s != null) {
            try {
                this.e = new Integer(s);
                return this.e;
            }
            catch (Exception ex) {
                throw new dp("Cannot convert content encoding length : " + s, ex);
            }
            throw new dp("No content encoding length header");
        }
        throw new dp("No content encoding length header");
    }
    
    public final boolean d() {
        String s = this.get("CONTENT-LENGTH");
        if (s == null) {
            s = this.get("CL");
        }
        return s != null;
    }
    
    public final int e() throws dp {
        if (this.c != -1) {
            return this.c;
        }
        String s = this.get("CONTENT-ENCODING");
        if (s == null) {
            s = this.get("CE");
        }
        if (s == null) {
            this.c = 0;
        }
        else {
            if (!s.equals("gzip")) {
                throw new dp("no valid content encoding: " + s);
            }
            this.c = 1;
        }
        return this.c;
    }
    
    public final int f() {
        return this.f;
    }
    
    public final String g() {
        return this.h;
    }
    
    public final String a(final String s) {
        return this.get(s.toUpperCase(Locale.ENGLISH));
    }
    
    public final int h() {
        return this.i;
    }
    
    public String toString() {
        return "HttpResponseHeader id=" + this.i + " status=" + this.f + " msg=" + this.h;
    }
    
    public String i() {
        return this.k;
    }
    
    static {
        do.a = 0;
    }
}
