import java.net.URLEncoder;
import java.net.ProtocolException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.io.InputStream;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import java.util.Enumeration;
import java.awt.Component;
import java.io.IOException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class r implements Runnable
{
    public static final Hashtable a;
    public d b;
    public String c;
    public Hashtable d;
    public Vector e;
    public Vector f;
    public Thread g;
    public v h;
    public static final String i;
    public String j;
    public int k;
    
    public r(final d b, final String s, final String s2, final String s3) {
        this.d = new Hashtable();
        this.e = new Vector();
        this.f = new Vector();
        this.j = null;
        this.k = 1;
        this.b = b;
        final j h = this.b.h();
        String s4 = h.b;
        this.a("server", s4);
        int n = h.c;
        this.a("port", new Integer(n));
        String d;
        if ((d = h.d) == null) {
            d = "/";
        }
        else {
            s4 = this.b.r.getHost();
            if ((n = this.b.r.getPort()) < 0) {
                n = 80;
            }
        }
        this.c = "http://" + s4 + ":" + n + d;
        this.a("hm", s);
        if (s2 != null) {
            this.a("inittoken", s2);
        }
        if (s3 != null) {
            final Vector a = f.a(s3, ";");
            for (int i = 0; i < a.size(); ++i) {
                this.a("keylist1".substring(0, "keylist1".length() - 1) + (i + 1), a.elementAt(i));
            }
        }
        try {
            final ResourceBundle bundle = ResourceBundle.getBundle("resources.s");
            final String string = bundle.getString("Key");
            final String string2 = bundle.getString("EncodeAll");
            this.j = this.b.d().b(string, r.i, 5);
            this.k = ((string2.compareTo(Integer.toString(0)) != 0) ? 1 : 0);
        }
        catch (Exception ex) {}
    }
    
    public final void a(final String s, final Object o) {
        this.d.put(s, (o != null) ? o.toString() : "");
    }
    
    public final String a(final String s) {
        String s2;
        if ((s2 = this.d.get(s)) != null && s2.length() == 0) {
            s2 = null;
        }
        return s2;
    }
    
    public final void a(final p p) {
        synchronized (this) {
            if (!this.e.contains(p) && !this.f.contains(p)) {
                this.e.addElement(p);
                if (this.e.size() + this.f.size() == 1) {
                    (this.g = new Thread(this)).start();
                }
            }
        }
    }
    
    public final void b(final p p) {
        boolean b = false;
        this.a(p, 0, null);
        synchronized (this) {
            if (this.e.contains(p) || this.f.contains(p)) {
                if (this.e.contains(p)) {
                    this.e.removeElement(p);
                }
                if (this.f.contains(p)) {
                    this.f.removeElement(p);
                }
                if (this.e.size() + this.f.size() == 0) {
                    b = true;
                }
            }
        }
        if (b) {
            this.a(false);
        }
    }
    
    public final void run() {
        v d = null;
        if (this.h != null) {
            this.h.d();
            this.h = null;
        }
        while (this.g == Thread.currentThread()) {
            if (this.e.size() > 0) {
                IOException ex = null;
                if (this.h == null) {
                    try {
                        this.a("init", this.h = this.d("init"));
                    }
                    catch (IOException ex2) {
                        ex = ex2;
                    }
                }
                if (this.g != Thread.currentThread()) {
                    break;
                }
                this.a("init", this.h, ex);
            }
            final long g = this.b.h().g;
            if (g > 0L) {
                try {
                    Thread.currentThread();
                    Thread.sleep(g);
                }
                catch (InterruptedException ex5) {}
            }
            else {
                Thread.currentThread();
                Thread.yield();
            }
            if (this.g != Thread.currentThread()) {
                break;
            }
            this.b.e().a();
            if (this.g != Thread.currentThread()) {
                break;
            }
            if (this.f.size() > 0) {
                if (d != null) {
                    d.d();
                    d = null;
                }
                IOException ex3 = null;
                boolean a = false;
                try {
                    d = this.d("update");
                    a = this.a("update", d);
                }
                catch (IOException ex4) {
                    ex3 = ex4;
                }
                if (a) {
                    this.b("reinit");
                }
                else {
                    this.a("update", d, ex3);
                }
                if (d != null) {
                    d.d();
                    d = null;
                }
            }
            this.b.d();
            f.b();
        }
        this.a(true);
    }
    
    private boolean a(final String s, final v v) {
        boolean b = false;
        this.h.a(v.f());
        if (s.equals("init")) {
            this.a("subid", v.a("Su", 0, "I", 0, 0));
        }
        final String a;
        if ((a = v.a("Su", 0, "U", 0, 0)) != null) {
            try {
                final Integer c = this.b.d().c(a);
                if (c >= 0) {
                    this.b.h().g = 1000L * c;
                }
                else {
                    this.b.c().a((Component)this.b.s, 7, new Object[] { c, "Su", "0", "U", "0", "0", "negative" });
                }
            }
            catch (Exception ex) {
                this.b.c().a((Component)this.b.s, 7, new Object[] { a, "Su", "0", "U", "0", "0", ex.getMessage() });
            }
        }
        else if (s.equals("init")) {
            this.b.c().a((Component)this.b.s, 8, new Object[] { "Su", "0", "U", "0", "0" });
        }
        final String a2 = v.a("B", 0, null, 0, 0);
        if (a2 != null && a2.equals("reinit")) {
            this.h.a("R", v.a("R"));
        }
        else if (a2 != null && a2.equals("update")) {
            this.a(v);
        }
        if (s.equals("init") || s.equals("reinit") || s.equals("update")) {
            this.a("seqNum", v.a("Su", 0, "S", 0, 0));
            final String a3;
            if ((a3 = v.a("Su", 0, "B", 0, 0)) != null && !a3.equals(this.a("server"))) {
                this.a("server", a3);
                if (s.equals("update")) {
                    b = (a2 != null && a2.equals("update"));
                }
            }
        }
        return b;
    }
    
    private void a(final v v) {
        if (this.h != null) {
            final v a = v.a("R");
            for (int i = 0; i < a.b(); ++i) {
                final v a2 = a.a(i);
                final Vector a3 = this.h.a("R", a2.a((String)null).a(0).b(0), a2.a((String)null).a(0).b(1));
                for (int j = 0; j < a3.size(); ++j) {
                    final v v2 = a3.elementAt(j);
                    final Enumeration a4 = a2.a();
                    while (a4.hasMoreElements()) {
                        final String s;
                        if ((s = a4.nextElement()) != null && s.length() > 0 && v2.b(s)) {
                            v2.a(s, a2.a(s));
                        }
                    }
                }
            }
        }
    }
    
    public final void b(final String s) {
        v d = null;
        IOException ex = null;
        if (s.equals("init") && this.h != null) {
            this.h.d();
            this.h = null;
        }
        try {
            d = this.d(s);
            if (s.equals("init")) {
                if (this.h != null) {
                    this.h.d();
                    this.h = null;
                }
                this.h = d;
            }
            this.a(s, d);
        }
        catch (IOException ex2) {
            ex = ex2;
        }
        this.a(s, d, ex);
    }
    
    private void a(final String s, final v v, final IOException ex) {
        synchronized (this) {
            Vector vector;
            Vector<p> f;
            if (s.equals("init")) {
                vector = this.e;
                f = (Vector<p>)this.f;
            }
            else {
                vector = this.f;
                f = null;
            }
            for (int n = 0; n < vector.size() && this.g == Thread.currentThread(); ++n) {
                if (this.a(vector.elementAt(n), s, v, ex)) {
                    this.b(vector.elementAt(n));
                    --n;
                }
                else if (f != null) {
                    if (v != null) {
                        this.a(vector.elementAt(n), 2, v.f());
                    }
                    f.addElement(vector.elementAt(n));
                    vector.removeElementAt(n);
                    --n;
                }
                else if (v != null) {
                    this.a(vector.elementAt(n), 2, v.f());
                }
            }
        }
    }
    
    private boolean a(final p p4, final String s, final v v, IOException ex) {
        boolean a = false;
        String s2 = null;
        try {
            if (v != null) {
                p4.a(s, v);
            }
            else {
                if (ex == null) {
                    ex = new IOException(s);
                }
                if (a = p4.a(s, ex)) {
                    s2 = ex.getMessage();
                }
            }
        }
        catch (Throwable t) {
            this.b.c().a((Component)this.b.s, 10, new Object[] { s, "" + t.getMessage() + " (not stopping requests)" });
            s2 = t.getMessage();
            a = false;
        }
        if (a) {
            this.b.c().a((Component)this.b.s, 10, new Object[] { s, "" + s2 + " (stopping updates)" });
        }
        return a;
    }
    
    public final String c(final String s) throws IOException, MalformedURLException {
        final byte[] array = new byte[1024];
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(array.length);
        final URL e = this.e(s);
        InputStream a = null;
        try {
            final URLConnection openConnection;
            if ((openConnection = e.openConnection()) == null) {
                throw new IOException("Failed to open connection: " + e.toString());
            }
            openConnection.setUseCaches(false);
            openConnection.setAllowUserInteraction(true);
            if (this.b == null) {
                throw new IOException("Aborting request...");
            }
            a = this.b.f().a(openConnection);
            byteArrayOutputStream.reset();
            int read;
            while ((read = a.read(array)) > 0) {
                byteArrayOutputStream.write(array, 0, read);
            }
        }
        catch (Throwable t) {
            if (t instanceof IOException) {
                throw (IOException)t;
            }
            throw new IOException("Unknown failure: " + t.getMessage());
        }
        finally {
            if (a != null) {
                a.close();
            }
        }
        return byteArrayOutputStream.toString();
    }
    
    public final v d(final String s) throws IOException {
        final int e = this.b.h().e;
        v v = null;
        this.a(this.f, 3, null);
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        int n5 = 0;
    Label_0081_Outer:
        while (n5 < e || n3 != 0) {
            if (this.g != Thread.currentThread()) {
                throw new IOException("access halted");
            }
            while (true) {
                if (n3 != 0) {
                    n5 = 0;
                    n3 = 0;
                    try {
                        try {
                            v = new v(this.b, this.c(s));
                        }
                        catch (Throwable t) {
                            if (t instanceof IOException) {
                                throw new ProtocolException(t.getMessage());
                            }
                            throw new IOException(t.getMessage());
                        }
                        final String a;
                        if ((a = v.a("E", 0, null, 0, 0)) != null) {
                            final String a2 = v.a("E", 0, "C", 0, 0);
                            if (a2 != null && a2.compareTo("0") != 0) {
                                throw new IOException(a) {
                                    public final String[] a = { "This web page is not licensed", "to display this Heatmap®!", "Please contact", "legal@neovision.com", "and let us know." };
                                    public final String b = "mailto:legal@neovision.com";
                                    
                                    public String[] a() {
                                        return this.a;
                                    }
                                    
                                    public String b() {
                                        return "mailto:legal@neovision.com";
                                    }
                                };
                            }
                            final String a3;
                            if ((a3 = v.a("E", 0, "B", 0, 0)) != null) {
                                throw new IOException(a);
                            }
                        }
                        final String a4 = v.a("B", 0, null, 0, 0);
                        if (a4 == null || (!a4.equals(s) && !s.equals("update") && !a4.equals("reinit"))) {
                            throw new IOException("expected \"" + s + "\", but found " + ((a4 == null) ? "no response" : ("\"" + a4 + "\"")));
                        }
                    }
                    catch (ar ioException) {
                        throw ioException;
                    }
                    catch (IOException ex) {
                        this.b.c().a((Component)this.b.s, 20, new Object[] { s, ex.getMessage(), new Integer(n5 + 1), new Integer(e) });
                        if (n2 == 0 && ++n >= 1) {
                            this.a(this.e, 1, null);
                            this.a(this.f, 1, null);
                            n2 = 1;
                        }
                        if (n5 + 1 >= e) {
                            if ((this.b.c().g && ex instanceof ProtocolException) || this.g != Thread.currentThread() || (s != null && s.equals("init"))) {
                                throw ex;
                            }
                            n3 = 1;
                        }
                        final long a5;
                        if ((a5 = this.a(Math.min(e - 1, Math.max(0, n4)))) > 0L) {
                            try {
                                Thread.currentThread();
                                Thread.sleep(a5);
                            }
                            catch (InterruptedException ex2) {}
                        }
                        else {
                            Thread.currentThread();
                            Thread.yield();
                        }
                        ++n5;
                        ++n4;
                        continue Label_0081_Outer;
                    }
                    break;
                }
                continue;
            }
        }
        if (n2 != 0) {
            this.a(this.e, 3, null);
            this.a(this.f, 3, null);
        }
        return v;
    }
    
    public final void a(final boolean b) {
        if (!b) {
            this.g = null;
        }
        else {
            if (this.d != null) {
                this.d.clear();
                this.d = null;
            }
            if (this.h != null) {
                this.h.d();
                this.h = null;
            }
            this.b = null;
            if (this.e != null) {
                this.e.removeAllElements();
                this.e = null;
            }
            this.c = null;
            if (this.f != null) {
                this.f.removeAllElements();
                this.f = null;
            }
        }
    }
    
    private URL e(final String s) throws MalformedURLException {
        final Vector<String> vector = r.a.get(s);
        final boolean b = (this.j != null && this.j.length() > 0) & (this.k != 0 || s.equals("init"));
        final StringBuffer sb = new StringBuffer("d");
        sb.append("=");
        sb.append(this.b.r);
        final StringBuffer sb2 = new StringBuffer(this.c);
        sb2.append(s + "?");
        int i = 0;
    Label_0135:
        while (i < vector.size()) {
            String substring = vector.elementAt(i);
            int n;
            if ((n = (substring.endsWith("1") ? 1 : -1)) > 0) {
                substring = substring.substring(0, substring.length() - 1);
            }
            while (true) {
                String s2;
                while ((s2 = this.d.get((n < 0) ? substring : (substring + n))) != null) {
                    final StringBuffer sb3 = (!b || substring.equals("server") || substring.equals("port")) ? sb2 : sb;
                    if (sb3.charAt(sb3.length() - 1) != '?') {
                        sb3.append("&");
                    }
                    sb3.append((n < 0) ? substring : (substring + n));
                    sb3.append("=");
                    sb3.append(URLEncoder.encode(s2));
                    if (n < 0) {
                        ++i;
                        continue Label_0135;
                    }
                    ++n;
                }
                continue;
            }
        }
        if (b) {
            try {
                final String a = this.b.d().a(sb.toString(), this.j, 5);
                if (sb2.charAt(sb2.length() - 1) != '?') {
                    sb2.append("&");
                }
                sb2.append("e");
                sb2.append("=");
                sb2.append(a);
            }
            catch (Exception ex) {}
        }
        return new URL(sb2.toString());
    }
    
    private long a(final int n) {
        long n2;
        if (n < this.b.h().e - 1) {
            n2 = this.b.h().f;
        }
        else {
            final long g = this.b.h().g;
            this.b.h();
            n2 = Math.min(g, 60000L);
        }
        return n2;
    }
    
    private void a(final p p3, final int n, final Date date) {
        final Vector<p> vector = new Vector<p>(1);
        vector.addElement(p3);
        this.a(vector, n, date);
    }
    
    private void a(final Vector vector, final int n, final Date date) {
        for (int i = 0; i < vector.size(); ++i) {
            vector.elementAt(i).a(n, date);
        }
    }
    
    static {
        (a = new Hashtable()).put("init", new Vector());
        r.a.put("reinit", new Vector());
        r.a.put("update", new Vector());
        r.a.get("init").addElement("server");
        r.a.get("update").addElement("server");
        r.a.get("reinit").addElement("server");
        r.a.get("init").addElement("port");
        r.a.get("update").addElement("port");
        r.a.get("reinit").addElement("port");
        r.a.get("init").addElement("hm");
        r.a.get("init").addElement("inittoken");
        r.a.get("init").addElement("keylist1");
        r.a.get("update").addElement("subid");
        r.a.get("reinit").addElement("subid");
        r.a.get("update").addElement("seqNum");
        final StringBuffer sb = new StringBuffer("");
        sb.append((char)83);
        sb.append((char)101);
        sb.append((char)99);
        sb.append((char)114);
        sb.append((char)101);
        sb.append((char)116);
        i = sb.toString();
    }
}
