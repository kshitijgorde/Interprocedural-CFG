// 
// Decompiled by Procyon v0.5.30
// 

public final class DI
{
    private long currentTimeMillis;
    private double wait;
    private double I;
    private double Z;
    private int C;
    private byte[] B;
    
    DI() {
        this.B = new byte[1];
    }
    
    final void I(final double z) {
        this.currentTimeMillis = System.currentTimeMillis();
        this.I = this.currentTimeMillis - z;
        this.Z = z;
        this.C = (int)((this.Z + 0.5) / 2.0);
    }
    
    final synchronized void I() {
        final long currentTimeMillis = 0L;
        this.currentTimeMillis = currentTimeMillis;
        this.I = currentTimeMillis;
    }
    
    final void Z() {
        this.wait = this.I + this.Z;
        final long n = (long)this.wait - System.currentTimeMillis();
        if (0L < n) {
            synchronized (this.B) {
                this.B.wait(n);
            }
        }
        this.I = this.wait;
    }
    
    final void I(final int n) {
        final int n2 = (500 + n) / 1000;
        this.wait = this.I + this.Z;
        if (n2 > this.Z) {
            this.wait += this.C;
        }
        else if (n2 < -this.Z) {
            this.wait -= this.C;
        }
        final long n3 = (long)this.wait - System.currentTimeMillis();
        if (0L < n3) {
            synchronized (this.B) {
                this.B.wait(n3);
            }
        }
        this.I = this.wait;
    }
}
