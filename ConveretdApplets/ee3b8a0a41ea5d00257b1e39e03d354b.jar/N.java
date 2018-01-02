import I.I;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class N implements Runnable
{
    int J;
    String S;
    String A;
    Thread E;
    Thread G;
    boolean H;
    boolean K;
    byte[][] L;
    long[] M;
    int[] N;
    byte[] O;
    volatile boolean P;
    Q Q;
    int R;
    int append;
    int T;
    double currentThread;
    volatile int U;
    
    N(final Q q, final double currentThread, final String s) {
        this.J = 2;
        this.S = I.I(413);
        this.A = I.I(422);
        this.P = true;
        this.E = this.G;
        this.currentThread = currentThread;
        this.S = s + this.S;
        this.A = s + this.A;
        synchronized (q) {
            this.Q = q;
            this.R = this.Q.C.length;
            this.append = this.R >> 1;
        }
        this.O = new byte[12 + this.R];
    }
    
    abstract void I();
    
    abstract int Z();
    
    abstract void B();
    
    public final void run() {
        if (Thread.currentThread().getName().equals(this.A)) {
            this.I();
            synchronized (this) {
                if (this.K) {
                    this.K = false;
                    this.notifyAll();
                }
                else {
                    this.G = null;
                }
            }
        }
        else {
            final int z = this.Z();
            synchronized (this) {
                this.P = false;
                if (null != this.L) {
                    synchronized (this.L[z]) {
                        this.L[z].notify();
                    }
                }
                if (this.H) {
                    this.H = false;
                    this.notifyAll();
                }
                else {
                    this.E = null;
                    this.B();
                }
            }
        }
    }
}
