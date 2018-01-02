// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.net.SocketException;
import java.io.EOFException;
import java.io.InterruptedIOException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.util.Date;
import java.util.Vector;
import java.net.InetAddress;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Observable;

public class mc extends Observable implements Runnable
{
    public static boolean a;
    public static boolean b;
    private static mk c;
    private static ThreadGroup d;
    private static ThreadGroup e;
    private static Hashtable f;
    private static Object g;
    private static int h;
    private static Object i;
    private static int j;
    private static Object k;
    private static int l;
    private static boolean m;
    private static Object n;
    private static long o;
    private static Object p;
    private static long q;
    private Socket r;
    private boolean s;
    private DataInputStream t;
    private DataOutputStream u;
    private InetAddress v;
    private mar w;
    private boolean x;
    private boolean y;
    public int z;
    private long aa;
    private long ab;
    private Thread ac;
    private Thread ad;
    private int ae;
    private int af;
    private String ag;
    private Hashtable ah;
    
    private static int e() {
        synchronized (mc.g) {
            // monitorexit(mc.g)
            return ++mc.h;
        }
    }
    
    private static int a(final mc mc) {
        final String b = mc.b();
        synchronized (mc.i) {
            Vector<mc> vector = mc.f.get(b);
            if (vector == null) {
                vector = new Vector<mc>(1);
            }
            vector.addElement(mc);
            mc.f.put(b, vector);
            // monitorexit(mc.i)
            return ++mc.j;
        }
    }
    
    private static int b(final mc mc) {
        final String b = mc.b();
        synchronized (mc.i) {
            final Vector vector = mc.f.get(b);
            if (vector != null) {
                vector.removeElement(mc);
                if (vector.isEmpty()) {
                    mc.f.remove(b);
                }
            }
            // monitorexit(mc.i)
            return --mc.j;
        }
    }
    
    private static void f() {
        synchronized (mc.n) {
            ++mc.o;
        }
        // monitorexit(mc.n)
    }
    
    private static void g() {
        synchronized (mc.p) {
            ++mc.q;
        }
        // monitorexit(mc.p)
    }
    
    public static void a(final String s, final Throwable t) {
        if (mc.b) {
            synchronized (System.err) {
                System.err.println("[" + new Date() + "] " + s);
                t.printStackTrace(System.err);
                // monitorexit(System.err)
                return;
            }
        }
        System.err.println("[" + new Date() + "] " + s + " (" + t + ")");
    }
    
    public static void a(final mk c) {
        mc.c = c;
    }
    
    public static void a() {
        synchronized (mc.k) {
            --mc.l;
            mc.k.notify();
        }
        // monitorexit(mc.k)
    }
    
    public mc(final String s, final int n, final boolean b) throws IOException {
        this.y = true;
        this.z = e();
        this.aa = System.currentTimeMillis();
        this.af = 200;
        this.ag = "";
        this.ah = new Hashtable(11);
        this.r = new Socket(s, n);
        if (b) {
            this.t = new DataInputStream(new BufferedInputStream(this.r.getInputStream()));
        }
        else {
            this.t = new DataInputStream(this.r.getInputStream());
        }
        this.u = new DataOutputStream(new BufferedOutputStream(this.r.getOutputStream()));
        this.v = this.r.getInetAddress();
        this.w = new mar(this);
        a(this);
    }
    
    public String b() {
        return this.v.getHostAddress();
    }
    
    public synchronized void a(final int priority) {
        if (this.ad == null) {
            if (this.x && mc.a) {
                this.ad = new Thread(mc.d, this.w, "Sender-" + this.z);
            }
            else {
                this.ad = new Thread(this.w, "Sender-" + this.z);
            }
            this.ad.setPriority(priority);
            this.ad.start();
        }
    }
    
    public synchronized void b(final int priority) {
        if (this.ac == null) {
            if (this.x && mc.a) {
                this.ac = new Thread(mc.e, this, "Receiver-" + this.z);
            }
            else {
                this.ac = new Thread(this, "Receiver-" + this.z);
            }
            this.ac.setPriority(priority);
            this.ac.start();
        }
    }
    
    public void a(final mn mn) throws IOException {
        if (this.w.a(mn) == 0) {
            throw new IOException("connection is closed");
        }
    }
    
    public void b(final mn mn) throws IOException {
        this.u.writeShort(mn.c());
        mn.a(this.u);
        this.u.flush();
        if (mc.m) {
            f();
        }
    }
    
    private void a(final Throwable t) throws IOException {
        a("Error handling packet from " + this.b() + ".", t);
        this.a(new maq(t));
    }
    
    private void a(final Object o) throws InterruptedException {
        if (o instanceof mn) {
            final long a = ((mn)o).a();
            if (a > 0L) {
                Thread.sleep(a);
                return;
            }
            Thread.yield();
        }
    }
    
    private void b(final Object o) throws IOException {
        try {
            this.notifyObservers(o);
        }
        catch (RuntimeException ex) {
            this.a(ex);
        }
        catch (Error error) {
            this.a(error);
        }
    }
    
    public Object c() throws ClassNotFoundException, IOException, InterruptedException {
        try {
            this.t.mark(0);
            final mm a = mc.c.a(this.t.readUnsignedShort());
            a.a(this.t);
            this.ae = 0;
            if (mc.m) {
                g();
            }
            return a;
        }
        catch (InterruptedIOException ex) {
            this.t.reset();
            ++this.ae;
            return ex;
        }
    }
    
    public void run() {
        try {
            do {
                final Object c = this.c();
                this.setChanged();
                this.b(c);
                this.a(c);
            } while (this.y);
        }
        catch (EOFException ex5) {}
        catch (SocketException ex) {
            if (this.y) {
                a("Error reading from " + this.b() + ".", ex);
            }
        }
        catch (IOException ex2) {
            a("Error reading from " + this.b() + ".", ex2);
            this.af = 400;
        }
        catch (InterruptedException ex3) {
            if (this.y) {
                a("Error reading from " + this.b() + ".", ex3);
            }
        }
        catch (ClassNotFoundException ex4) {
            a("Error reading from " + this.b() + ".", ex4);
            this.af = 400;
        }
        catch (ThreadDeath threadDeath) {
            this.af = 503;
            throw threadDeath;
        }
        catch (Throwable t) {
            a("Error reading from " + this.b() + ".", t);
            this.af = 500;
        }
        finally {
            this.ab = System.currentTimeMillis();
            b(this);
            if (this.s) {
                a();
            }
            this.setChanged();
            this.notifyObservers(null);
            this.deleteObservers();
            try {
                this.ad.interrupt();
            }
            catch (Throwable t2) {}
            this.w.a();
            try {
                this.u.close();
            }
            catch (IOException ex6) {}
            try {
                this.r.close();
            }
            catch (IOException ex7) {}
        }
    }
    
    public synchronized void d() {
        if (this.y) {
            this.y = false;
            if (Thread.currentThread() != this.ac) {
                try {
                    this.r.close();
                }
                catch (IOException ex) {}
            }
        }
    }
    
    public void c(final int af) {
        this.af = af;
        this.d();
    }
    
    public void finalize() throws Throwable {
        super.finalize();
        this.d();
    }
    
    public String toString() {
        return String.valueOf(this.b()) + "-" + this.z;
    }
    
    static {
        mc.a = true;
        mc.c = new mk();
        mc.d = new ThreadGroup("Senders");
        mc.e = new ThreadGroup("Receivers");
        mc.f = new Hashtable();
        mc.g = new Object();
        mc.i = new Object();
        mc.k = new Object();
        mc.n = new Object();
        mc.p = new Object();
    }
}
