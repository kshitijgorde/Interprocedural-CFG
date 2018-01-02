// 
// Decompiled by Procyon v0.5.30
// 

public abstract class Z extends P
{
    private static String I;
    boolean Z;
    I C;
    ztmPlayer B;
    
    final synchronized void I(final ztmPlayer b) {
        this.C = b.U;
        this.Z = b.E;
        this.B = b;
        (super.A = new Thread(this, Z.I)).start();
    }
    
    void I() {
        this.Z = true;
    }
    
    void Z() {
        this.Z = false;
    }
    
    synchronized void C() {
        super.S = true;
        if (null != this.B) {
            synchronized (this.B.Y) {
                this.B.Y.notifyAll();
            }
        }
        if (null != super.A && super.A.isAlive()) {
            this.notifyAll();
            final int e = super.E;
            if (null != this.C.L) {
                synchronized (this.C.L[e]) {
                    this.C.L[e].notifyAll();
                }
            }
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
        }
        this.B = null;
        this.C = null;
        super.A = null;
        super.F = true;
    }
    
    protected abstract long B();
    
    static {
        Z.I = I.I.I(394);
    }
}
