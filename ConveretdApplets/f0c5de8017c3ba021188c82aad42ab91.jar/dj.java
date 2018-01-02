// 
// Decompiled by Procyon v0.5.30
// 

public class dj implements ak
{
    public static void a(final bf bf, final j j, final Exception ex, final int n) {
        a(bf, j, ex, n, null);
    }
    
    public static void a(final bf bf, final j j, final Exception ex, final int n, final String s) {
        if (ak.a.l()) {
            ak.a.j("ErrorHandler.handleError " + ak.a.a(bf, j, ex, new Integer(n)) + ((s != null) ? s : ""));
        }
        bf.b(ex, n, s);
        if (bf.c() || j == null || (j != null && j.b())) {
            if (ak.a.k()) {
                ak.a.g("handle error blocking with ", ex);
            }
            bf.c(ex, n, s);
        }
        else {
            if (ak.a.k()) {
                ak.a.g("ErrorHandler.handleError.produce() with " + ak.a.a(bf, ex, new Integer(n)), ex);
            }
            j.a(new dk(bf, ex, n, s), "ErrorHandler.handleError(" + s + ")");
        }
    }
}
