// 
// Decompiled by Procyon v0.5.30
// 

package ji.net.cookie;

import ji.io.h;
import java.util.StringTokenizer;
import ji.document.ad;
import ji.util.i;
import ji.document.bd;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import ji.util.d;
import java.util.Map;
import java.util.Enumeration;
import java.io.IOException;
import java.net.URL;
import ji.io.jiLogger;
import java.util.Vector;
import java.util.Hashtable;

public class bc
{
    private static Hashtable a;
    private Vector b;
    private jiLogger c;
    
    public bc(final jiLogger c) {
        this.b = new Vector();
        this.c = c;
    }
    
    public void a(final URL url, final Hashtable hashtable, final String s) throws IOException {
        if (hashtable == null) {
            return;
        }
        final Vector a = this.a(hashtable, "Set-Cookie");
        if (a != null) {
            for (int i = 0; i < a.size(); ++i) {
                final String s2 = a.elementAt(i);
                this.a(s, String.valueOf(String.valueOf(new StringBuffer("Found Set-Cookie header on connection ").append(url).append(": ").append(s2))));
                final be be = new be(url, s2);
                for (int j = 0; j < this.b.size(); ++j) {
                    final be be2 = this.b.elementAt(j);
                    if (be.c().equals(be2.c()) && be.b().equals(be2.b())) {
                        this.b.remove(be2);
                        break;
                    }
                }
                this.b.add(be);
            }
        }
    }
    
    private Vector a(final Hashtable hashtable, final String s) {
        final Vector<String> vector = new Vector<String>();
        final Enumeration<String> keys = (Enumeration<String>)hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s2 = keys.nextElement();
            if (s2.equalsIgnoreCase("set-cookie")) {
                final String s3 = hashtable.get(s2);
                if (s3 == null) {
                    continue;
                }
                vector.addElement(s3);
            }
        }
        return vector;
    }
    
    public Hashtable b(final URL url, final Hashtable hashtable, final String s) throws IOException {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < this.b.size(); ++i) {
            final be be = this.b.elementAt(i);
            if (be.a()) {
                this.a(s, String.valueOf(String.valueOf(new StringBuffer("Expiring cookie from connection ").append(url).append(": ").append(be))));
                this.b.remove(be);
            }
            else if (be.a(url)) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(be.toString());
                this.a(s, String.valueOf(String.valueOf(new StringBuffer("Adding cookie to connection ").append(url).append(": ").append(be))));
            }
        }
        Hashtable<String, Vector<String>> hashtable2;
        if (hashtable != null) {
            hashtable2 = new Hashtable<String, Vector<String>>(hashtable);
        }
        else {
            hashtable2 = new Hashtable<String, Vector<String>>();
        }
        if (sb.length() > 0) {
            final Vector<String> vector = new Vector<String>(1);
            vector.addElement(sb.toString());
            hashtable2.put("Cookie", vector);
        }
        return hashtable2;
    }
    
    private void a(final String s, final String s2) {
        if (this.c != null) {
            this.c.log(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append(": ").append(s2))));
        }
    }
    
    public void a() {
        this.b.removeAllElements();
        this.c = null;
    }
    
    private static synchronized bc a(final String s, final boolean b) {
        if (bc.a == null) {
            bc.a = new Hashtable();
        }
        if (bc.a != null) {
            final bc value = bc.a.get(s);
            if (value != null && value instanceof bc) {
                return value;
            }
            if (b) {
                final bc bc = new bc(new sw(s, new int[] { 79 }));
                ji.net.cookie.bc.a.put(s, bc);
                return bc;
            }
        }
        return null;
    }
    
    public static void a(final String s) {
        try {
            final bc a = a(s, false);
            if (a != null) {
                a.a();
            }
        }
        catch (Exception ex) {
            d.a(ex);
        }
    }
    
    private static boolean a(final URLConnection urlConnection) {
        return urlConnection != null && urlConnection instanceof HttpURLConnection;
    }
    
    public static final void a(final URLConnection urlConnection, final String s) {
        try {
            if (bd.a(s, 42) && a(urlConnection)) {
                final Hashtable d = d(urlConnection, s);
                if (d != null) {
                    final bc a = a(s, true);
                    if (a != null) {
                        synchronized (a) {
                            a.a(urlConnection.getURL(), d, s);
                        }
                        // monitorexit(a)
                    }
                }
            }
        }
        catch (Exception ex) {
            d.a(ex);
        }
    }
    
    public static final void b(final URLConnection urlConnection, final String s) {
        try {
            if (bd.a(s, 42) && a(urlConnection)) {
                final bc a = a(s, false);
                if (a != null) {
                    synchronized (a) {
                        a(urlConnection, a.b(urlConnection.getURL(), null, s), s);
                    }
                    // monitorexit(a)
                }
            }
        }
        catch (Exception ex) {
            d.a(ex);
        }
    }
    
    private static final void a(final URLConnection urlConnection, final Hashtable hashtable, final String s) {
        try {
            if (a(urlConnection) && hashtable != null) {
                final Enumeration keys = hashtable.keys();
                while (keys.hasMoreElements()) {
                    final String nextElement = keys.nextElement();
                    final Vector<Object> value = hashtable.get(nextElement);
                    if (nextElement instanceof String && value instanceof Vector) {
                        Object element = null;
                        if (value.size() > 0) {
                            element = value.elementAt(0);
                        }
                        if (!(element instanceof String) || ((String)element).equals(urlConnection.getRequestProperty(nextElement))) {
                            continue;
                        }
                        urlConnection.setRequestProperty(nextElement, (String)element);
                    }
                }
            }
        }
        catch (Exception ex) {
            d.a(ex);
        }
    }
    
    private static final Hashtable d(final URLConnection urlConnection, final String s) {
        try {
            if (a(urlConnection)) {
                int n = 0;
                final Hashtable<String, String> hashtable = new Hashtable<String, String>();
                for (String s2 = urlConnection.getHeaderFieldKey(n); n == 0 || (s2 != null && n < 1000); ++n, s2 = urlConnection.getHeaderFieldKey(n)) {
                    if (s2 != null) {
                        hashtable.put(s2, urlConnection.getHeaderField(n));
                    }
                }
                return hashtable;
            }
        }
        catch (Exception ex) {
            d.a(ex);
        }
        return null;
    }
    
    public static final void c(final URLConnection urlConnection, final String s) {
        try {
            final ad x = d.x(s);
            if (x != null) {
                final Vector kv = x.kv();
                final Hashtable ku = x.ku();
                a(urlConnection, kv, ku, "CookieClone", new sw(s));
                if (i.c(260)) {
                    a(urlConnection, kv, ku, "Cookie", new sw(s));
                }
            }
        }
        catch (Exception ex) {
            d.a(ex);
        }
    }
    
    public static final void a(final URLConnection urlConnection, final Vector vector, final Hashtable hashtable, final String s, final jiLogger jiLogger) {
        try {
            if (a(urlConnection)) {
                final URL url = urlConnection.getURL();
                if (url != null) {
                    final String host = url.getHost();
                    if (!d.by(host) && vector != null) {
                        for (int i = 0; i < vector.size(); ++i) {
                            final String s2 = vector.elementAt(i);
                            if (!d.by(s2) && host.toLowerCase().endsWith(s2.toLowerCase()) && true && hashtable != null && hashtable.size() > 0) {
                                final StringBuffer sb = new StringBuffer();
                                int n = 1;
                                final String requestProperty = urlConnection.getRequestProperty(s);
                                if (!d.by(requestProperty)) {
                                    if (d.cy()) {
                                        jiLogger.log("Cookie already on connection: ".concat(String.valueOf(String.valueOf(requestProperty))));
                                    }
                                    sb.append(requestProperty);
                                    n = 0;
                                }
                                final Enumeration keys = hashtable.keys();
                                while (keys.hasMoreElements()) {
                                    if (n != 0) {
                                        n = 0;
                                    }
                                    else {
                                        sb.append("; ");
                                    }
                                    final String s3 = (String)keys.nextElement();
                                    final Vector<String> vector2 = hashtable.get(s3);
                                    if (!d.by(s3) && vector2 != null) {
                                        for (int j = 0; j < vector2.size(); ++j) {
                                            final String s4 = vector2.elementAt(j);
                                            if (d.cy()) {
                                                jiLogger.log(String.valueOf(String.valueOf(new StringBuffer("Adding cookie: name=").append(s3).append(" value=").append(s4))));
                                            }
                                            sb.append(s3);
                                            sb.append("=");
                                            sb.append(s4);
                                        }
                                    }
                                    else {
                                        if (!d.cy()) {
                                            continue;
                                        }
                                        jiLogger.log(String.valueOf(String.valueOf(new StringBuffer("Name or value null: name=").append(s3).append(" value=").append(vector2))));
                                    }
                                }
                                if (sb.length() > 0) {
                                    if (d.cy()) {
                                        jiLogger.log(String.valueOf(String.valueOf(new StringBuffer("Adding cookie request property (").append(s).append("): ").append(sb.toString()))));
                                    }
                                    urlConnection.setRequestProperty(s, sb.toString());
                                }
                                else if (d.cy()) {
                                    jiLogger.log("No cookies to add");
                                }
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            d.a(ex);
        }
    }
    
    public static final Hashtable a(final String s, final jiLogger jiLogger) {
        Hashtable<String, Vector<?>> hashtable = null;
        if (!d.by(s)) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, "; ");
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (!d.by(nextToken)) {
                    final int index = nextToken.indexOf(61);
                    if (index <= 0) {
                        continue;
                    }
                    final String substring = nextToken.substring(0, index);
                    final String substring2 = nextToken.substring(index + 1, nextToken.length());
                    if (hashtable == null) {
                        hashtable = new Hashtable<String, Vector<?>>();
                    }
                    Vector<?> vector = hashtable.get(substring);
                    if (vector == null) {
                        vector = new Vector<Object>();
                        hashtable.put(substring, vector);
                    }
                    if (jiLogger != null) {
                        jiLogger.log(String.valueOf(String.valueOf(new StringBuffer("Creating cookie name=").append(substring).append(" value=").append(substring2))));
                    }
                    vector.addElement(substring2);
                }
            }
        }
        return hashtable;
    }
    
    static {
        bc.a = new Hashtable();
    }
    
    static class sw implements jiLogger
    {
        String a;
        int[] b;
        
        sw(final String s) {
            this(s, null);
        }
        
        sw(final String a, final int[] b) {
            this.b = null;
            this.a = a;
            this.b = b;
        }
        
        public void log(final String s, final Throwable t) {
            if (this.a()) {
                h.d(this.a, s);
                d.a(t);
            }
        }
        
        public void log(final String s) {
            if (this.a()) {
                h.d(this.a, s);
            }
        }
        
        private boolean a() {
            if (this.b == null) {
                return true;
            }
            for (int i = 0; i < this.b.length; ++i) {
                if (i.c(this.b[i])) {
                    return true;
                }
            }
            return false;
        }
    }
}
