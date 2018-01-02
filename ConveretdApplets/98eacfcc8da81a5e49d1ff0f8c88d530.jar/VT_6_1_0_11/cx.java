// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.f;
import com.hw.client.util.e;
import com.hw.client.util.a;

public final class cx
{
    private static final ds a;
    
    public static final void a(final int n) {
        a(n, (String)null);
    }
    
    public static final synchronized void a(final int n, final Exception ex) {
        com.hw.client.util.a.e("DoorError.report(), code => " + n + ", title => " + cR.a(n));
        if (ex != null) {
            ex.printStackTrace();
        }
        cx.a.a(new dw(cx.a, n, ex), true);
    }
    
    private static synchronized void a(final int n, final String s) {
        com.hw.client.util.a.e("DoorError.report(), code => " + n + ", msg => " + (String)null);
        cx.a.a(new dw(cx.a, n, (String)null), true);
    }
    
    static {
        a = new ds();
    }
}
