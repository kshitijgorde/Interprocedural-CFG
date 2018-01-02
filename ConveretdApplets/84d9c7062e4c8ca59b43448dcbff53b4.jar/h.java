import java.io.IOException;
import java.awt.Graphics;
import java.net.URLConnection;
import java.io.Serializable;
import java.util.Date;
import java.net.URL;
import java.io.DataOutputStream;
import java.net.InetAddress;
import netscape.javascript.JSObject;
import java.awt.Component;
import java.util.StringTokenizer;
import java.net.Socket;
import java.util.Vector;
import java.io.InputStream;
import java.applet.Applet;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public final class h extends Canvas implements Runnable
{
    private static long t;
    static boolean j;
    private static Applet b;
    private final String n;
    private Thread h;
    private boolean i;
    private InputStream o;
    private final Vector m;
    private final Vector a;
    private final g e;
    private String p;
    private String c;
    private final Vector l;
    private final Vector f;
    private int[] d;
    private int u;
    private int k;
    static boolean r;
    static long s;
    private boolean q;
    private Socket g;
    
    public h(final Applet b) {
        this.m = new Vector();
        this.a = new Vector();
        this.e = new g("/");
        this.l = new Vector();
        this.f = new Vector();
        this.u = 0;
        this.k = 0;
        h.b = b;
        this.n = b.getCodeBase().getHost();
        final String parameter = b.getParameter("port");
        h.r = false;
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ",", false);
            this.d = new int[stringTokenizer.countTokens()];
            while (stringTokenizer.hasMoreTokens()) {
                try {
                    final int int1 = Integer.parseInt(stringTokenizer.nextToken());
                    if (int1 <= 0) {
                        continue;
                    }
                    this.d[this.k++] = int1;
                }
                catch (Exception ex) {}
            }
        }
        else {
            this.d = new int[1];
        }
        if (this.k == 0) {
            this.d[this.k++] = 80;
        }
        try {
            final long long1 = Long.parseLong(b.getParameter("maxIdle"));
            if (long1 >= 40L) {
                h.s = long1 * 1000L;
            }
        }
        catch (Exception ex2) {
            h.s = 60000L;
        }
        try {
            if (Boolean.valueOf(b.getParameter("debug"))) {
                h.j = true;
            }
        }
        catch (Exception ex3) {}
        final String host = b.getDocumentBase().getHost();
        System.out.println("Host Name: " + host);
        if (!host.endsWith("sportsline.com") && !host.endsWith("nflplayers.com") && !host.endsWith("nfl.com") && !host.endsWith("superbowl.com") && !host.endsWith("commissioner.com") && !host.endsWith("ncaasports.com") && !host.endsWith("cbssports.com") && !host.endsWith("cbsgames.com") && !host.endsWith("cbsnews.com") && !host.endsWith("finalfour.net") && !host.endsWith("si.cnn.com")) {
            return;
        }
        this.reshape(-10, -10, 1, 1);
        b.add(this);
        this.i = true;
        (this.h = new Thread(this)).start();
    }
    
    public final synchronized void c() {
        if (this.h != null) {
            this.i = false;
            if (this.h != Thread.currentThread()) {
                this.h.stop();
            }
            this.h = null;
            this.a();
        }
    }
    
    public final synchronized void a(final String s, final b b) {
        g a = this.a(b);
        int n = 0;
        if (a == null) {
            synchronized (this.l) {
                this.l.addElement(b);
                a = new g("/");
                this.f.addElement(a);
                n = ((this.l.size() == 1) ? 1 : 0);
            }
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken(",");
            if (!this.e.e(nextToken)) {
                this.e.c(nextToken);
            }
            if (a != null) {
                a.c(nextToken);
            }
        }
        if (n != 0) {
            synchronized (this.l) {
                this.l.notifyAll();
            }
        }
        else {
            final c c = new c();
            c.b = new String(s);
            c.a = "/_s1";
            this.a(c);
        }
    }
    
    public final synchronized boolean b(final String s, final b b) {
        boolean b2 = false;
        final g a = this.a(b);
        final Vector vector;
        synchronized (this.l) {
            vector = (Vector)this.l.clone();
            final Vector vector2 = (Vector)this.f.clone();
        }
        final int size = vector.size();
        final StringBuffer sb = new StringBuffer();
        int n = 0;
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        while (stringTokenizer.hasMoreTokens()) {
            final String nextToken = stringTokenizer.nextToken(",");
            boolean b3 = true;
            for (int i = 0; i < size; ++i) {
                if (((g)this.f.elementAt(i)).d(nextToken) && this.l.elementAt(i) != b) {
                    b3 = false;
                    break;
                }
            }
            if (b3) {
                this.e.a(nextToken);
                if (n > 0) {
                    sb.append(',');
                }
                sb.append(nextToken);
                ++n;
            }
            if (a != null) {
                a.a(nextToken);
            }
        }
        if (a.b()) {
            synchronized (this.l) {
                final int index = this.l.indexOf(b);
                if (index != -1) {
                    this.l.removeElementAt(index);
                    this.f.removeElementAt(index);
                }
                if (this.l.size() == 0) {
                    this.a();
                }
            }
            b2 = true;
        }
        if (n > 0 && this.o != null) {
            final c c = new c();
            c.b = new String(sb);
            c.a = "/_u";
            this.a(c);
        }
        return b2;
    }
    
    public final void run() {
        int n = 0;
        while (this.i) {
            synchronized (this.l) {
                if (this.l.size() == 0) {
                    try {
                        this.l.wait();
                    }
                    catch (InterruptedException ex4) {}
                }
            }
            long n2 = 30000L;
            final JSObject window = JSObject.getWindow(h.b);
            if (this.o == null) {
                ++n;
                a("Starting attempts to make socket connection");
                try {
                    final StringBuffer sb = new StringBuffer();
                    sb.append("_n1");
                    final String[] c = this.e.c();
                    for (int i = 0; i < c.length; ++i) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        sb.append(c[i]);
                    }
                    final Serializable a = new String(sb);
                    a("Attempting raw socket with message: " + (String)a);
                    try {
                        final String[] array = { this.n + ":" + this.d[this.u] };
                        window.call("live_connection_attempt", (Object[])array);
                        this.g = new Socket(InetAddress.getByName(this.n), this.d[this.u]);
                        new DataOutputStream(this.g.getOutputStream()).writeBytes("POST /_n1 HTTP/1.0\r\n\r\n" + (String)a + "\r\n");
                        this.o = this.g.getInputStream();
                        this.q = true;
                        a("Successful raw socket connection");
                        window.call("live_connection_success", (Object[])array);
                    }
                    catch (Exception ex) {
                        final String[] array2 = { this.n + ":" + this.d[this.u] };
                        window.call("live_connection_fail", (Object[])array2);
                        a("Raw socket connection failed " + ex);
                        array2[0] += " (GET)";
                        window.call("live_connection_attempt", (Object[])array2);
                        a("Attempting HTTP GET connection...");
                        this.a();
                        final URLConnection openConnection = new URL("http://" + this.n + ":" + this.d[this.u] + "/" + (String)a).openConnection();
                        openConnection.setUseCaches(false);
                        this.o = openConnection.getInputStream();
                        this.q = false;
                        window.call("live_connection_success", (Object[])array2);
                    }
                }
                catch (Exception ex2) {
                    final String[] array3 = { this.n + ":" + this.d[this.u] };
                    window.call("live_connection_fail", (Object[])array3);
                    a("Raw socket connection failed " + ex2);
                    a("Sending connectionFailed message to subscribers");
                    this.a();
                    if (this.u == this.k - 1) {
                        a("Raw socket connection failed " + ex2);
                        if (n == 2) {
                            window.call("live_connection_quit", (Object[])array3);
                        }
                    }
                }
            }
            if (this.o != null) {
                try {
                    while (this.i) {
                        final f[] a2 = a(this.o);
                        for (int j = 0; j < a2.length; ++j) {
                            if (a2[j].a.equals("set_machine_name")) {
                                final Serializable a = a(a2[j].c[0]);
                                final String a3 = a(a2[j].c[1]);
                                this.a((String)a, a3);
                                a(new Date() + " " + "set_machine_name(" + (String)a + "," + a3 + " " + h.b.getDocumentBase());
                            }
                            else if (a2[j].a.equals("connectionBanned")) {
                                final Serializable a;
                                synchronized (this.l) {
                                    a = (Vector)this.l.clone();
                                }
                                for (int size = ((Vector)a).size(), k = 0; k < size; ++k) {
                                    ((Vector<b>)a).elementAt(k).a(a2[j]);
                                }
                                this.c();
                            }
                            else {
                                if (!a2[j].a.equalsIgnoreCase("LPS_ADMIN")) {
                                    a("Received event: " + a2[j].a + ", Topic: " + a2[j].b);
                                }
                                this.m.addElement(a2[j]);
                            }
                        }
                        this.repaint();
                    }
                }
                catch (Exception ex3) {
                    a("While reading socket ", ex3);
                    this.a();
                }
                finally {
                    n2 = 1000L * (long)(20.0 + 40.0 * Math.random());
                }
            }
            if (this.i && this.l.size() > 0) {
                boolean b = true;
                if (!h.r) {
                    if (this.u < this.k - 1) {
                        ++this.u;
                        b = false;
                    }
                    else {
                        this.u = 0;
                    }
                    a("changing port to: " + this.d[this.u]);
                }
                if (!b) {
                    continue;
                }
                a("attempting socket reconnect in " + n2 / 1000L + " seconds");
                try {
                    Thread.sleep(n2);
                }
                catch (InterruptedException ex5) {}
            }
        }
        this.c();
    }
    
    public final void finalize() {
        this.c();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        final Vector vector;
        synchronized (this.m) {
            vector = (Vector)this.m.clone();
            this.m.removeAllElements();
        }
        final Vector vector2;
        final Vector vector3;
        synchronized (this.l) {
            vector2 = (Vector)this.l.clone();
            vector3 = (Vector)this.f.clone();
        }
        final int size = vector2.size();
        for (int size2 = vector.size(), i = 0; i < size2; ++i) {
            final f f = vector.elementAt(i);
            for (int j = 0; j < size; ++j) {
                final b b = vector2.elementAt(j);
                if (vector3.elementAt(j).e(f.b)) {
                    b.a(f);
                }
            }
        }
    }
    
    private synchronized void a() {
        a("Attempting to close socket...");
        if (this.o != null) {
            if (!this.q) {
                final c c = new c();
                c.a = "/_c";
                this.a(c);
            }
            try {
                if (this.g != null) {
                    this.g.close();
                }
                this.o.close();
                a("Socket closed.");
            }
            catch (IOException ex) {
                a(ex);
            }
            finally {
                this.o = null;
                this.p = null;
                this.c = null;
                this.g = null;
            }
        }
    }
    
    private synchronized void a(final c c) {
        if (this.p != null) {
            this.b(c);
        }
        else {
            this.a.addElement(c);
        }
    }
    
    private void b(final c c) {
        final StringBuffer sb = new StringBuffer();
        sb.append("http://");
        sb.append(this.p);
        sb.append(":");
        sb.append(String.valueOf(this.d[this.u]));
        sb.append(c.a);
        sb.append(this.c);
        if (!c.a.equals("/_c")) {
            sb.append(",");
            sb.append(c.b);
        }
        sb.append("?");
        sb.append(String.valueOf(Math.random()));
        final String s = new String(sb);
        try {
            final JSObject window = JSObject.getWindow(h.b);
            final String[] array = { s };
            a("sendCommandViaJS(" + s);
            window.call("live_swap_image", (Object[])array);
        }
        catch (Exception ex) {
            a("", ex);
        }
    }
    
    private synchronized void a(final String s, final String s2) {
        this.p = s.trim();
        this.c = s2.trim();
        JSObject.getWindow(h.b).call("live_host_clientid", (Object[])new String[] { this.p, this.c });
        for (int size = this.a.size(), i = 0; i < size; ++i) {
            this.b((c)this.a.elementAt(i));
        }
        this.a.removeAllElements();
    }
    
    private g a(final b b) {
        if (b == null) {
            return null;
        }
        synchronized (this.l) {
            final int index = this.l.indexOf(b);
            if (index != -1) {
                return (g)this.f.elementAt(index);
            }
        }
        return null;
    }
    
    public static final void a(final String s, final Throwable t) {
        System.err.println(new Date() + "|" + s);
        System.err.println(t.toString());
        t.printStackTrace();
    }
    
    public static final void a(final Throwable t) {
        System.err.println(new Date() + "|" + t.toString());
        t.printStackTrace();
    }
    
    private static final f[] a(final InputStream inputStream) throws IOException {
        byte[] array = null;
        int n = 0;
        final byte[] array2 = new byte[8];
        int n2 = 0;
        long currentTimeMillis = System.currentTimeMillis();
        while (true) {
            final long currentTimeMillis2 = System.currentTimeMillis();
            int available = inputStream.available();
            if (available > 0) {
                h.r = true;
                currentTimeMillis = currentTimeMillis2;
                if (n2 < 8) {
                    final int read = inputStream.read(array2, n2, Math.min(available, 8 - n2));
                    if (read == -1) {
                        throw new IOException("Read returned -1 A");
                    }
                    n2 += read;
                    if (n2 == 8) {
                        array = new byte[Integer.valueOf(new String(array2, 0, 8).trim()) - 8];
                        available -= read;
                    }
                }
                if (array == null || n >= array.length) {
                    continue;
                }
                final int read2 = inputStream.read(array, n, Math.min(available, array.length - n));
                if (read2 == -1) {
                    throw new IOException("Read returned -1 B");
                }
                n += read2;
                if (n == array.length) {
                    return b(array);
                }
                continue;
            }
            else {
                if (!h.r && currentTimeMillis2 - currentTimeMillis > h.s) {
                    throw new IOException("Excessive Idle Time");
                }
                if (h.r && currentTimeMillis2 - currentTimeMillis > 60000L) {
                    throw new IOException("Excessive Idle Time");
                }
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    private static final f[] b(final byte[] array) throws IOException {
        final boolean b = array[0] != 48;
        a("compression flag: " + b);
        final String trim = new String(array, 1, 64).trim();
        a("topic: " + trim);
        final int intValue = Integer.valueOf(new String(array, 65, 4).trim());
        a("nEvents: " + intValue);
        if (intValue == 0 && trim.equals("")) {
            a("Pinged by frontend.  (Occurs after about 30 seconds of inactivity).");
        }
        byte[] a = new byte[array.length - 70];
        System.arraycopy(array, 69, a, 0, a.length);
        if (b) {
            a("calling decompress");
            a = e.a(a);
        }
        int n = 0;
        final f[] array2 = new f[intValue];
        for (int i = 0; i < intValue; ++i) {
            array2[i] = new f();
            array2[i].b = trim;
            array2[i].a = new String(a, 0, 16).trim();
            n += 16;
            final int intValue2 = Integer.valueOf(new String(a, 16, 4).trim());
            n += 4;
            array2[i].c = new byte[intValue2][];
            for (int j = 0; j < intValue2; ++j) {
                final int intValue3 = Integer.valueOf(new String(a, n, 8).trim());
                n += 8;
                System.arraycopy(a, n, array2[i].c[j] = new byte[intValue3], 0, intValue3);
                n += intValue3;
            }
            if (intValue2 == 2 && array2[i].a.equals("setState")) {
                final byte[][] c = { null };
                if (array2[i].c[0][0] == 49) {
                    c[0] = e.a(array2[i].c[1]);
                }
                else {
                    c[0] = array2[i].c[1];
                }
                array2[i].c = c;
            }
        }
        return array2;
    }
    
    private static final String a(final byte[] array) {
        final String s = new String(array);
        final int index = s.indexOf(0);
        if (index == -1) {
            return s;
        }
        return s.substring(0, index);
    }
    
    public void b() {
        if (h.j) {
            a("Debug Mode disabled");
            h.j = false;
        }
        else {
            h.j = true;
            a("Debug mode enabled");
        }
    }
    
    private static void a(final String s) {
        if (h.j) {
            System.err.println(new Date() + ", " + s);
            JSObject.getWindow(h.b).call("live_debug", (Object[])new String[] { s });
        }
    }
    
    static {
        h.t = (long)Math.pow(2.0, 32.0);
        h.j = false;
    }
}
