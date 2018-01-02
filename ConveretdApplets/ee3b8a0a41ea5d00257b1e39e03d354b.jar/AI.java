import I.I;

// 
// Decompiled by Procyon v0.5.30
// 

class AI extends Thread
{
    private final ztmPlayer Z;
    
    AI(final ztmPlayer z) {
        this.Z = z;
    }
    
    public final void run() {
        int n = 0;
        do {
            if (0 == n % 25) {
                this.Z.I((0 == n % 50) ? I.I(479) : null);
            }
            else {
                try {
                    Thread.currentThread();
                    Thread.sleep(20L);
                }
                catch (InterruptedException ex) {}
            }
            ++n;
        } while (this.Z.a);
        this.Z.I((String)null);
    }
}
