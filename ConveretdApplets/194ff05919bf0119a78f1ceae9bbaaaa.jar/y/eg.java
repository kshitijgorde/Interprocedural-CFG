// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.util.Vector;

public final class eg extends Thread implements bo
{
    private boolean a;
    private boolean b;
    private long a;
    private boolean c;
    private long b;
    private Vector a;
    public cb a;
    private r a;
    
    public eg(final long a) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.a = new Vector();
        this.a = null;
        this.a = new r();
        this.a = a;
    }
    
    public final synchronized fh a(final fe fe, final int n) {
        final fh fh = new fh(this, fe, n);
        this.a.addElement(fh);
        return fh;
    }
    
    public final synchronized void a(final fh fh) {
        this.a.removeElement(fh);
    }
    
    public final void run() {
        this.b = System.currentTimeMillis() + this.a;
        final eg eg;
        while (!eg.a) {
            synchronized (eg) {
                final long currentTimeMillis = System.currentTimeMillis();
                eg.c = true;
                final long n;
                if ((n = eg.b - currentTimeMillis) > 0L) {
                    try {
                        eg.wait(n);
                    }
                    catch (InterruptedException ex) {}
                }
                eg.c = false;
                final Throwable t = (Throwable)eg;
                ((eg)t).b += eg.a;
            }
            if (eg.a == null) {
                final eg eg3;
                final eg eg2 = eg3 = eg;
                synchronized (eg2) {
                    eg3.a.a(eg3.a);
                }
                for (int size = eg3.a.size(), i = 0; i < size; ++i) {
                    final fh fh2;
                    final fh fh = fh2 = eg3.a.elementAt(i);
                    fh.a += eg3.a;
                    eg3.b(fh2);
                }
            }
            else {
                final eg eg5;
                final eg eg4 = eg5 = eg;
                synchronized (eg4) {
                    eg5.a.a(eg5.a);
                }
                for (int size2 = eg5.a.size(), j = 0; j < size2; ++j) {
                    final fh fh4;
                    final fh fh3 = fh4 = eg5.a.elementAt(j);
                    fh3.a += eg5.a;
                    if (fh4.a instanceof ac) {
                        eg5.b(fh4);
                    }
                    else {
                        eg5.a.a.a(eg5, 0, fh4);
                        if (fh4.a + eg5.a > fh4.c && eg5.a.a()) {
                            eg5.a.a().l();
                        }
                    }
                }
            }
        }
        synchronized (eg) {
            eg.b = true;
            eg.notify();
        }
    }
    
    private void b(final fh fh) {
        try {
            if (fh.b == -1L) {
                fh.a.m();
                return;
            }
            while (fh.a > fh.c) {
                fh.c += fh.b;
                fh.a.m();
            }
        }
        catch (Throwable t) {
            if (this.a != null) {
                this.a.a(t);
                return;
            }
            t.printStackTrace();
        }
    }
    
    public final void a(final int n, final Object o) {
        this.b((fh)o);
    }
    
    private synchronized void b() {
        this.b = System.currentTimeMillis();
        if (this.c) {
            this.notify();
        }
    }
    
    public final synchronized void a() {
        this.a = true;
        while (!this.b) {
            this.b();
            try {
                this.wait();
            }
            catch (InterruptedException ex) {}
        }
    }
}
