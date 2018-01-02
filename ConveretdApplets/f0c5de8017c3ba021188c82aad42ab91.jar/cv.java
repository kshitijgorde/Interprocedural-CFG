// 
// Decompiled by Procyon v0.5.30
// 

public class cv
{
    public static void a(final long n) {
        final long currentTimeMillis = System.currentTimeMillis();
        long n2 = n;
        while (System.currentTimeMillis() - currentTimeMillis < n) {
            try {
                Thread.currentThread();
                Thread.sleep(n2);
            }
            catch (InterruptedException ex) {
                if (n.b()) {
                    n.b("Sleeper: sleeping thread was interrupted with " + ex);
                }
                n2 -= System.currentTimeMillis() - currentTimeMillis;
            }
        }
    }
}
