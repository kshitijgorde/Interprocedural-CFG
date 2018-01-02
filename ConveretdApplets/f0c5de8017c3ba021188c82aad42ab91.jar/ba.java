import java.io.OutputStream;
import java.net.URL;
import java.util.Enumeration;
import java.net.URLConnection;
import java.util.Vector;
import java.util.Locale;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ba extends bb implements ak
{
    private boolean a;
    public final String b;
    public final String c;
    public final String d;
    public final int e;
    public int f;
    public String[] g;
    private boolean h;
    private static l i;
    
    public ba(String s, final String c, final int e, final Hashtable hashtable, final j j, final String d, final int f, final String[] g) {
        super(hashtable, j);
        this.a = true;
        this.h = false;
        this.f = f;
        if (s.indexOf("https") > -1) {
            s = "https";
        }
        else {
            s = "http";
        }
        if ((s.equals("http") && e == 80) || (s.equals("https") && e == 443)) {
            this.b = s.toLowerCase(Locale.ENGLISH) + "://" + c;
        }
        else {
            this.b = s.toLowerCase(Locale.ENGLISH) + "://" + c + ":" + e;
        }
        this.c = c;
        this.e = e;
        this.g = g;
        if (d != null && !d.equals("")) {
            this.d = d;
        }
        else {
            this.d = null;
        }
        if (ak.a.k()) {
            ak.a.i("ctor HttpURLRequestHandler: " + this);
        }
    }
    
    public void a(final x x, final bf bf, final String s, final long n, final String s2) {
        this.a(x, bf, 0, s, n, null);
    }
    
    public void a(final x x, final bf bf, final int n, final String s, final long n2, final String s2) {
        this.a(x, bf, n, s, n2, this, null);
    }
    
    public void a(final Vector vector, final bf bf, final String s, final long n, final String s2) {
        for (int i = 0; i < vector.size(); ++i) {
            this.a(vector.elementAt(i), bf, i, s, n, this, null);
        }
    }
    
    public void a(final x x, final bf bf, final int n, final String s, final long n2, final bb bb, final Vector vector) {
        if (ak.a.l()) {
            ak.a.j("HttpURLRequestHandler.get " + ak.a.a(x, bf, new Integer(n), s, new Long(n2), bb, vector));
        }
        final long currentTimeMillis = System.currentTimeMillis();
        String s2 = x.toString();
        if (s != null) {
            s2 = s + s2;
        }
        final String s3 = s2;
        if (super.b != null) {
            final String string = "getting request=" + this.b + s3 + " with timeout=" + n2 + " " + ((vector != null) ? ("for " + vector.size() + " requests") : "");
            if (ak.a.i()) {
                ak.a.g(string);
            }
            final int a = super.b.a(new di(this, s3, vector, x, bf, bb, n), n2, string);
            if (a != 0) {
                if (ak.a.i()) {
                    ak.a.g("handle error for " + this.b + s3);
                }
                dj.a(bf, super.b, new cu(a), n, "TO=" + n2);
            }
            if (ak.a.i()) {
                ak.a.g("retrieved (nonblock) request " + x + " within " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            }
        }
        else {
            if (ak.a.i()) {
                ak.a.g("getting request=" + this.b + s3 + " without timeout");
            }
            try {
                new dm().a(this.a(s3, vector), x, bf, bb, super.b, n);
            }
            catch (Exception ex) {
                dj.a(bf, super.b, ex, n);
            }
            if (ak.a.i()) {
                ak.a.g("retrieved request within " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
            }
        }
    }
    
    public void a(final boolean h) {
        this.h = h;
    }
    
    public static final void a(final Hashtable hashtable, final URLConnection urlConnection) {
        if (hashtable != null) {
            final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
            while (keys.hasMoreElements()) {
                final String s = keys.nextElement();
                urlConnection.setRequestProperty(s, hashtable.get(s));
            }
        }
    }
    
    public URLConnection a(String s, final Vector vector) throws dl {
        try {
            final long currentTimeMillis = System.currentTimeMillis();
            if (ak.a.j()) {
                ak.a.h("opening connection to " + this.b + s);
            }
            String s2 = null;
            final int length = s.length();
            if (length >= this.f) {
                if (this.g == null || this.g.length == 0) {
                    if (ak.a.g()) {
                        ak.a.d("no sticky params defined for POST request part");
                    }
                    final int index = s.indexOf("?");
                    s2 = s.substring(index + 1, s.length());
                    s = s.substring(0, index + 1);
                }
                else {
                    final x x = new x(s);
                    final x x2 = new x(x.c());
                    x.d(null);
                    for (int i = 0; i < this.g.length; ++i) {
                        final String b = x.b(this.g[i]);
                        if (b != null) {
                            x2.a(this.g[i], b);
                        }
                    }
                    s = x2.toString();
                    s2 = x.toString();
                    if (s2.startsWith("?")) {
                        s2 = s2.substring(1);
                    }
                }
                if (ak.a.k()) {
                    ak.a.i("prepared request for POST because of length " + length + " > " + this.f + " split [" + s + "|" + s2 + "]");
                }
            }
            final URLConnection openConnection = new URL(this.b + s).openConnection();
            if (this.h) {
                openConnection.setUseCaches(false);
            }
            a(super.a, openConnection);
            if (this.d != null) {
                openConnection.setRequestProperty("Host", this.d);
            }
            if (ak.a.k()) {
                final Enumeration<String> keys = super.a.keys();
                final StringBuffer sb = new StringBuffer();
                if (this.d != null) {
                    sb.append("[Host: " + this.d + "\\r\\n");
                }
                while (keys.hasMoreElements()) {
                    final String s3 = keys.nextElement();
                    sb.append(s3 + ": " + super.a.get(s3) + "\\r\\n");
                }
                sb.append("]");
                ak.a.i("set request header " + sb.toString());
            }
            if (ak.a.j()) {
                ak.a.h("opened connection to " + this.b + s);
            }
            if (s2 != null) {
                openConnection.setDoOutput(true);
                final OutputStream outputStream = openConnection.getOutputStream();
                outputStream.write(s2.getBytes());
                outputStream.flush();
                outputStream.close();
                if (ak.a.k()) {
                    ak.a.i("wrote query " + s2 + " as POST request.");
                }
                if (ak.a.k()) {
                    ak.a.i("opened post connection within " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
                }
                return openConnection;
            }
            if (vector != null) {
                if (ak.a.k()) {
                    ak.a.i("internal requests " + vector);
                }
                try {
                    openConnection.setDoOutput(true);
                    final OutputStream outputStream2 = openConnection.getOutputStream();
                    for (int j = 0; j < vector.size(); ++j) {
                        final String string = vector.elementAt(j).toString();
                        if (ak.a.k()) {
                            ak.a.i("adding request " + string);
                        }
                        if (string.indexOf(32) != -1 || string.indexOf(10) != -1 || string.indexOf(13) != -1 || string.indexOf(9) != -1) {
                            if (ak.a.f()) {
                                ak.a.c("cannot add request [" + string + "] since it contains whitespaces or comma");
                            }
                            throw new am("request [" + string + "] contains whitespaces or comma");
                        }
                        outputStream2.write((string + " ").getBytes());
                    }
                    outputStream2.flush();
                    outputStream2.close();
                    if (ak.a.k()) {
                        ak.a.i("wrote " + vector.size() + " post requests. opened post connection within " + (System.currentTimeMillis() - currentTimeMillis) + "ms");
                    }
                    return openConnection;
                }
                catch (Exception ex) {
                    throw new dl("could not write POST requests. ", ex);
                }
            }
            return openConnection;
        }
        catch (Exception ex2) {
            throw new dl("cannot open URLConnection for " + s, ex2);
        }
    }
    
    public String toString() {
        return "HttpURLRequestHandler[" + this.b + ":" + super.a + "]";
    }
    
    static {
        ba.i = new l();
    }
}
