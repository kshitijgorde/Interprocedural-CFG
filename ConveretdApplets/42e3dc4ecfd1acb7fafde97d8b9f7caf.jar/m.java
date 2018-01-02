import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.DataInputStream;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

public class m extends Thread
{
    private Socket a;
    private DataInputStream b;
    private OutputStream c;
    private boolean d;
    private int e;
    private long f;
    private o g;
    private static m[] h;
    
    static {
        m.h = new m[10];
    }
    
    public synchronized void run() {
        try {
            if (this.d) {
                System.out.println("Running " + this.getName());
            }
            while (true) {
                this.wait(15000L);
                if (this.d) {
                    System.out.println("CHECKING " + this.getName());
                }
                if (this.f + 30000L < System.currentTimeMillis()) {
                    if (this.d) {
                        System.out.println("DISCONNCTING TIME OUT " + this.getName());
                    }
                    a(this);
                }
            }
        }
        catch (InterruptedException ex) {
            if (this.d) {
                ex.printStackTrace();
            }
            if (this.d) {
                System.out.println("Ending  " + this.getName());
            }
        }
    }
    
    public static synchronized m a(final o o, final int n, final boolean b) {
        if (m.h[n] == null) {
            m.h[n] = new m(o, n, b);
        }
        return m.h[n];
    }
    
    private m(final o g, final int n, final boolean d) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = 10000;
        this.f = System.currentTimeMillis();
        this.d = d;
        this.g = g;
        if (d) {
            this.setName("Cam Connection Manager " + n);
        }
    }
    
    public boolean a() {
        return this.a != null;
    }
    
    private synchronized boolean c() throws InterruptedException {
        try {
            if (this.d) {
                System.out.println("Connecting " + this.getName());
            }
            (this.a = new Socket(this.g.e(), this.g.d())).setSoTimeout(this.e);
            this.a.setTcpNoDelay(true);
            this.c = this.a.getOutputStream();
            this.b = new DataInputStream(new BufferedInputStream(this.a.getInputStream(), 6072));
            this.c.write(this.a(this.g.g()));
            this.c.write(this.g.f().getBytes());
            this.c.write(this.g.h());
            this.c.flush();
            this.f = System.currentTimeMillis();
            if (!this.isAlive()) {
                this.start();
            }
            if (this.d) {
                System.out.println("Connected " + this.getName());
            }
            return true;
        }
        catch (Exception ex) {
            if (this.d) {
                ex.printStackTrace();
            }
            if (this.isInterrupted()) {
                return false;
            }
            this.wait(5000L);
            return false;
        }
    }
    
    public synchronized b5 a(final bd bd) {
        final b5 c = bd.c();
        try {
            final byte[] array = new byte[2];
            while (!this.a() && !this.isInterrupted()) {
                this.c();
            }
            this.c.write(this.a(bd.q()));
            this.c.write(this.a(c.f()));
            this.c.write(this.a(bd.j()));
            this.c.flush();
            this.b.readFully(array);
            this.f = System.currentTimeMillis();
            if (this.d) {
                System.out.println("stat[0] " + array[0] + " stat[1] " + array[1]);
            }
            if (array[1] != -1) {
                c.a(this.b.readInt());
                final byte[] array2 = new byte[c.f()];
                this.b.readFully(array2, 0, c.f());
                c.a(array2);
                c.a(array[0], array[1]);
                if (this.d) {
                    System.out.println("image for " + bd.toString() + " " + this.getName());
                }
                c.b(0);
            }
            else if (array[0] == 0) {
                c.b(2);
            }
            else if (array[0] == -1) {
                c.b(1);
            }
        }
        catch (InterruptedException ex) {
            c.b(3);
            if (this.d) {
                ex.printStackTrace();
            }
        }
        catch (IOException ex2) {
            c.b(3);
            this.a = null;
            if (this.d) {
                System.out.println(String.valueOf(ex2.getMessage()) + " for " + bd.toString());
                ex2.printStackTrace();
            }
            try {
                this.c();
            }
            catch (InterruptedException ex3) {
                if (!this.d) {
                    return c;
                }
                ex3.printStackTrace();
            }
        }
        return c;
    }
    
    public static synchronized void a(final m m) {
        if (m != null) {
            m.interrupt();
        }
    }
    
    public static synchronized void b() {
        for (int i = 0; i < m.h.length; ++i) {
            final m m = m.h[i];
            if (m != null) {
                m.interrupt();
            }
        }
    }
    
    public void interrupt() {
        super.interrupt();
        try {
            if (this.a != null) {
                this.b.close();
                this.c.close();
                this.a.close();
                this.a = null;
            }
        }
        catch (IOException ex) {
            if (this.d) {
                ex.printStackTrace();
            }
        }
    }
    
    private byte[] a(final int n) {
        return new byte[] { (byte)(n >>> 24 & 0xFF), (byte)(n >>> 16 & 0xFF), (byte)(n >>> 8 & 0xFF), (byte)(n >>> 0 & 0xFF) };
    }
}
