// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

public final class v implements Runnable
{
    private MapCanvas a;
    
    public v(final MapCanvas a, final int n, final int n2) {
        (this.a = a).b(n, n2);
    }
    
    private void a(final int n) {
        this.a.c(n);
        this.a.b();
    }
    
    public final void run() {
        try {
            for (int i = 195; i >= 0; i -= 10) {
                this.a(i);
                Thread.sleep((220 - i) / 4);
                if (i == 15) {
                    Thread.sleep(1000L);
                }
            }
            this.a(0);
        }
        catch (InterruptedException ex) {}
    }
}
