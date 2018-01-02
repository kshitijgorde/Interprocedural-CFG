// 
// Decompiled by Procyon v0.5.30
// 

public final class G
{
    public static void a(final long n) {
        try {
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
}
