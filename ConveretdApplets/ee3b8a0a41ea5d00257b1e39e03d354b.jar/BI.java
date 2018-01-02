import I.I;

// 
// Decompiled by Procyon v0.5.30
// 

public final class BI extends P
{
    private static String D;
    volatile long I;
    private ZI currentThread;
    private DI isAlive;
    private long join;
    private int newPixels;
    private CI notify;
    private int notifyAll;
    private int sleep;
    private ztmPlayer start;
    volatile boolean Z;
    
    BI(final ztmPlayer start) {
        this.currentThread = start.V;
        this.notifyAll = this.currentThread.J;
        this.sleep = this.notifyAll - 1;
        this.notify = start.Q;
        this.newPixels = 100000;
        this.join = (long)(2000.0 * start.P);
        if (this.newPixels < this.join) {
            this.newPixels = (int)this.join;
        }
        this.join >>= 1;
        this.isAlive = new DI();
        this.start = start;
        (super.A = new Thread(this, BI.D)).start();
    }
    
    private int I() {
        return (int)(this.start.W.B() - this.currentThread.M[super.E]);
    }
    
    final void D() {
        synchronized (this) {
            super.J = true;
        }
    }
    
    private void currentThread(final long n) {
        int n2 = (int)(n / this.currentThread.C);
        if (n % this.currentThread.C > this.currentThread.C >> 1) {
            ++n2;
        }
        while (n2 > 0 && !super.S) {
            synchronized (this.currentThread.L[super.E]) {
                while (true) {
                    while (0 >= this.currentThread.N[super.E]) {
                        if (!this.currentThread.P) {
                            return;
                        }
                        this.start.I();
                        this.currentThread.L[super.E].wait();
                        if (super.S || -1 != this.currentThread.N[super.E]) {
                            continue Label_0162;
                        }
                    }
                    this.isAlive();
                    --n2;
                    continue;
                }
            }
            Label_0162:;
        }
    }
    
    final void F() {
        while (!this.Z) {
            Thread.currentThread();
            Thread.sleep(20L);
        }
        this.J();
    }
    
    final void J() {
        super.J = false;
    }
    
    private void isAlive() {
        this.I = this.currentThread.M[super.E];
        synchronized (this.currentThread.L[this.sleep]) {
            this.currentThread.N[this.sleep] = -1;
            this.currentThread.L[this.sleep].notify();
        }
        this.sleep = super.E;
        if (this.notifyAll == ++super.E) {
            super.E = 0;
        }
    }
    
    private void join() {
        final ztmPlayer start = this.start;
        if (ztmPlayer.Z) {
            this.currentThread.D[super.E].newPixels();
        }
        this.notify.I(this.currentThread.B[super.E]);
        this.isAlive();
    }
    
    public final void run() {
        final ztmPlayer start = this.start;
        final boolean b = ztmPlayer.C > 0;
        try {
            if ((this.start.G || this.start.X) && !this.start.a) {
                if (null == this.start.d) {
                    if (!this.start.X && !this.start.A) {
                        synchronized (this.currentThread.L[super.E]) {
                            while (!super.S && 0 >= this.currentThread.N[super.E] && this.currentThread.P) {
                                this.currentThread.L[super.E].wait(15L);
                            }
                            if (0 < this.currentThread.N[super.E]) {
                                final ztmPlayer start2 = this.start;
                                if (ztmPlayer.Z) {
                                    this.currentThread.D[super.E].newPixels();
                                }
                                this.notify.I(this.currentThread.B[super.E]);
                            }
                        }
                    }
                }
                else {
                    this.notify.I(this.start.d);
                    if (this.start.B == 1) {
                        Thread.currentThread();
                        final ztmPlayer start3 = this.start;
                        Thread.sleep(2000L);
                    }
                }
            }
            this.Z = true;
            this.isAlive.I(this.start.P);
        Label_0589:
            while (!super.S) {
                if (super.J) {
                    this.isAlive.I();
                    synchronized (this.start.Y) {
                        while (!super.S && super.J) {
                            this.start.Y.wait();
                        }
                        this.start.X = false;
                    }
                    this.isAlive.I(this.start.P);
                }
                synchronized (this.currentThread.L[super.E]) {
                    while (true) {
                        while (0 >= this.currentThread.N[super.E]) {
                            if (!this.currentThread.P) {
                                if (null != this.start.f) {
                                    this.notify.I(this.start.f);
                                    if (this.start.G || this.start.A) {
                                        Thread.currentThread();
                                        Thread.sleep(2000L);
                                    }
                                }
                                break Label_0589;
                            }
                            this.start.I();
                            this.currentThread.L[super.E].wait();
                            if (super.S || -1 != this.currentThread.N[super.E]) {
                                continue Label_0586;
                            }
                        }
                        if (b) {
                            final int i = this.I();
                            if (i < this.newPixels) {
                                this.isAlive.I(-i);
                                this.join();
                            }
                            else {
                                this.currentThread(i);
                            }
                            continue;
                        }
                        this.isAlive.Z();
                        this.join();
                        continue;
                    }
                }
                Label_0586:;
            }
        }
        catch (Exception ex) {}
        synchronized (this) {
            if (super.S) {
                super.S = false;
                this.notify();
            }
            else {
                super.A = null;
                this.C();
            }
        }
    }
    
    final synchronized void C() {
        super.S = true;
        if (null != super.A && super.A.isAlive()) {
            final int e = super.E;
            synchronized (this.currentThread.L[e]) {
                this.currentThread.L[e].notifyAll();
            }
            if (null != this.start) {
                synchronized (this.start.Y) {
                    this.start.Y.notifyAll();
                }
            }
            this.isAlive.I();
            while (super.S) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
            try {
                super.A.join();
            }
            catch (InterruptedException ex2) {}
            this.currentThread.B = null;
            this.currentThread.D = null;
        }
        super.A = null;
        super.F = true;
        this.notifyAll();
        this.start = null;
        this.currentThread = null;
        this.isAlive = null;
        this.notify = null;
    }
    
    static {
        BI.D = I.I(346);
    }
}
