// 
// Decompiled by Procyon v0.5.30
// 

public class b2 extends Thread
{
    public final b5 cv;
    public int ld;
    
    public b2(final b5 b5, final int ld) {
        this.cv = b5;
        this.cv = b5;
        this.ld = ld;
    }
    
    public final synchronized void km(final int ld) {
        this.ld = ld;
    }
    
    public final void run() {
        while (true) {
            try {
                while (true) {
                    final int ld;
                    synchronized (this) {
                        ld = this.ld;
                    }
                    Thread.sleep(1000 * ld);
                    if (this.cv.fq != null) {
                        final av av = new av(36, this.cv.fq.f7);
                        av.jt("heartbeat");
                        this.cv.fq.ee(av);
                    }
                }
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
}
