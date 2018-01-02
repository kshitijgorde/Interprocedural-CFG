// 
// Decompiled by Procyon v0.5.30
// 

public class k
{
    private int a;
    
    public k() {
        this.a = 0;
    }
    
    public synchronized void a() {
        final boolean dj = p.dJ;
        while (true) {
            if (this.a != 0) {
                --this.a;
                if (!dj && !dj) {
                    break;
                }
                continue;
            }
            else {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public synchronized void b() {
        ++this.a;
        this.notify();
    }
}
