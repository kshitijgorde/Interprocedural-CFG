// 
// Decompiled by Procyon v0.5.30
// 

public final class R
{
    public final void a(final long n) {
        try {
            Thread.yield();
            long n2;
            if ((n2 = n - n / 1000L * 1000L) > 999999L) {
                n2 = 999999L;
            }
            Thread.sleep(n / 1000L, (int)n2);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void a(int n) {
        n = (n /= 10) * 10;
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public final void a() {
        Thread.yield();
    }
}
