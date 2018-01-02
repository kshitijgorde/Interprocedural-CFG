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
import java.util.Hashtable;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.Observable;

public class by extends Observable implements Runnable
{
    static final int sv = 200;
    static final int tv = 400;
    static final int uv = 500;
    static final int vv = 503;
    private static final String wv = "Receiver-";
    private static final String xv = "Sender-";
    private static final int yv = 11;
    private static final int zv = 3000;
    public static boolean dw;
    public static boolean ew;
    private static ThreadGroup fw;
    private static ThreadGroup gw;
    private static Object hw;
    private static int iw;
    private static Object jw;
    private static int nd;
    private static Object kw;
    private static int lw;
    private static boolean mw;
    private static Object nw;
    private static long ow;
    private static Object pw;
    private static long qw;
    private Socket rw;
    private boolean sw;
    private DataInputStream tw;
    private DataOutputStream uw;
    private String vw;
    private ca ww;
    private boolean xw;
    private boolean hf;
    private int yw;
    private long zw;
    private long dx;
    private Thread ex;
    private Thread fx;
    private int gx;
    private int nb;
    private String hx;
    private Hashtable ix;
    
    public static void wj() {
        final Thread[] array = new Thread[by.gw.activeCount()];
        by.gw.enumerate(array);
        by.fw.stop();
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != null) {
                try {
                    array[i].join(3000L);
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    static void hh(final String s, final Throwable t) {
        if (by.ew) {
            synchronized (System.err) {
                System.err.println("[" + new Date() + "] " + s);
                t.printStackTrace(System.err);
                // monitorexit(System.err)
                return;
            }
        }
        System.err.println("[" + new Date() + "] " + s + " (" + t + ")");
    }
    
    private static int hj() {
        synchronized (by.hw) {
            // monitorexit(by.hw)
            return ++by.iw;
        }
    }
    
    private static int kj() {
        synchronized (by.jw) {
            // monitorexit(by.jw)
            return ++by.nd;
        }
    }
    
    private static int yj() {
        synchronized (by.jw) {
            // monitorexit(by.jw)
            return --by.nd;
        }
    }
    
    public static int sb() {
        return by.nd;
    }
    
    public static void zi(final int n) throws InterruptedException {
        synchronized (by.kw) {
            while (by.lw >= n && n != 0) {
                by.kw.wait();
            }
            ++by.lw;
        }
        // monitorexit(by.kw)
    }
    
    public static void nj() {
        synchronized (by.kw) {
            --by.lw;
            by.kw.notify();
        }
        // monitorexit(by.kw)
    }
    
    public static void ti(final boolean mw) {
        by.mw = mw;
    }
    
    public static long vi() {
        return by.ow;
    }
    
    public static long ej() {
        return by.qw;
    }
    
    private static void si() {
        synchronized (by.nw) {
            ++by.ow;
        }
        // monitorexit(by.nw)
    }
    
    private static void vj() {
        synchronized (by.pw) {
            ++by.qw;
        }
        // monitorexit(by.pw)
    }
    
    public by(final Socket rw, final boolean sw) throws IOException {
        this.hf = true;
        this.yw = hj();
        this.zw = System.currentTimeMillis();
        this.nb = 200;
        this.hx = "";
        this.ix = new Hashtable(11);
        this.rw = rw;
        this.sw = sw;
        this.tw = new DataInputStream(new BufferedInputStream(rw.getInputStream()));
        this.uw = new DataOutputStream(new BufferedOutputStream(rw.getOutputStream()));
        this.vw = rw.getInetAddress().getHostAddress();
        this.ww = new ca(this);
        this.xw = true;
        kj();
    }
    
    public by(final Socket socket) throws IOException {
        this(socket, false);
    }
    
    public by(final String vw, final int n, final boolean b) throws IOException {
        this.hf = true;
        this.yw = hj();
        this.zw = System.currentTimeMillis();
        this.nb = 200;
        this.hx = "";
        this.ix = new Hashtable(11);
        this.rw = new Socket(vw, n);
        if (b) {
            this.tw = new DataInputStream(new BufferedInputStream(this.rw.getInputStream()));
        }
        else {
            this.tw = new DataInputStream(this.rw.getInputStream());
        }
        this.uw = new DataOutputStream(new BufferedOutputStream(this.rw.getOutputStream()));
        this.vw = vw;
        this.ww = new ca(this);
        kj();
    }
    
    public by(final String s, final int n) throws IOException {
        this(s, n, true);
    }
    
    public Socket qj() {
        return this.rw;
    }
    
    public String oj() {
        return this.vw;
    }
    
    public int xi() {
        return this.yw;
    }
    
    public long uj() {
        return this.zw;
    }
    
    public long pj() {
        return this.dx;
    }
    
    public long ij() {
        return this.uw.size();
    }
    
    public int jj() {
        return this.gx;
    }
    
    public void mb(final int nb) {
        this.nb = nb;
    }
    
    public int zj() {
        return this.nb;
    }
    
    public String mj() {
        return this.hx;
    }
    
    public Object gj(final String s) {
        return this.ix.get(s);
    }
    
    public Object lj(final String s, final Object o) {
        return this.ix.put(s, o);
    }
    
    public boolean wi(final String s) {
        final Boolean b = this.ix.get(s);
        return b != null && b;
    }
    
    public boolean tj(final String s) {
        final Boolean b = this.ix.put(s, new Boolean(true));
        return b != null && b;
    }
    
    public synchronized void yi(final int priority) {
        if (this.fx == null) {
            if (this.xw && by.dw) {
                this.fx = new Thread(by.fw, this.ww, "Sender-" + this.yw);
            }
            else {
                this.fx = new Thread(this.ww, "Sender-" + this.yw);
            }
            this.fx.setPriority(priority);
            this.fx.start();
        }
    }
    
    public synchronized void ui(final int priority) {
        if (this.ex == null) {
            if (this.xw && by.dw) {
                this.ex = new Thread(by.gw, this, "Receiver-" + this.yw);
            }
            else {
                this.ex = new Thread(this, "Receiver-" + this.yw);
            }
            this.ex.setPriority(priority);
            this.ex.start();
        }
    }
    
    public void t(final cb cb) throws IOException {
        if (this.ww.id(cb) == 0) {
            throw new IOException("connection is closed");
        }
    }
    
    public void xj(final cb cb) throws IOException {
        this.uw.writeUTF(cb.getClass().getName());
        cb.og(this.uw);
        this.uw.flush();
        if (by.mw) {
            si();
        }
    }
    
    private void rj(final Throwable t) throws IOException {
        hh("Error handling packet from " + this.vw + ".", t);
        this.t(new cc(t));
    }
    
    private void sj(final Object o) throws IOException {
        try {
            this.notifyObservers(o);
        }
        catch (RuntimeException ex) {
            this.rj(ex);
        }
        catch (Error error) {
            this.rj(error);
        }
    }
    
    public Object fj() throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        try {
            this.tw.mark(0);
            final cb cb = (cb)Class.forName(this.tw.readUTF()).newInstance();
            cb.pg(this.tw);
            this.gx = 0;
            if (by.mw) {
                vj();
            }
            return cb;
        }
        catch (InterruptedIOException ex) {
            this.tw.reset();
            ++this.gx;
            return ex;
        }
    }
    
    public void run() {
        try {
            do {
                final Object fj = this.fj();
                this.setChanged();
                this.sj(fj);
                Thread.yield();
            } while (this.hf);
        }
        catch (EOFException ex3) {}
        catch (SocketException ex) {
            if (this.hf) {
                hh("Error reading from " + this.vw + ".", ex);
                this.nb = 500;
            }
        }
        catch (IOException ex2) {
            hh("Error reading from " + this.vw + ".", ex2);
            this.nb = 400;
        }
        catch (ThreadDeath threadDeath) {
            this.nb = 503;
            throw threadDeath;
        }
        catch (Throwable t) {
            hh("Error reading from " + this.vw + ".", t);
            this.nb = 500;
        }
        finally {
            this.dx = System.currentTimeMillis();
            yj();
            if (this.sw) {
                nj();
            }
            this.setChanged();
            this.notifyObservers(null);
            this.deleteObservers();
            try {
                this.fx.interrupt();
            }
            catch (Throwable t2) {}
            this.ww.c();
            try {
                this.uw.close();
            }
            catch (IOException ex4) {}
            try {
                this.rw.close();
            }
            catch (IOException ex5) {}
        }
    }
    
    public void c() {
        this.hf = false;
        if (Thread.currentThread() != this.ex) {
            try {
                this.ex.interrupt();
            }
            catch (Throwable t) {}
            try {
                this.rw.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public void c(final int nb) {
        if (this.hf) {
            this.nb = nb;
            this.c();
        }
    }
    
    public void c(final int nb, final String hx) {
        if (this.hf) {
            this.nb = nb;
            this.hx = hx;
            this.c();
        }
    }
    
    protected void finalize() throws Throwable {
        super.finalize();
        this.c();
    }
    
    static {
        by.dw = true;
        by.fw = new ThreadGroup("Senders");
        by.gw = new ThreadGroup("Receivers");
        by.hw = new Object();
        by.jw = new Object();
        by.kw = new Object();
        by.nw = new Object();
        by.pw = new Object();
    }
}
