// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.f;
import com.hw.client.util.a;
import com.hw.client.util.e;

public final class do
{
    private v a;
    private int b;
    private int c;
    private e d;
    private int e;
    
    public do(final v a, final int b, final int n) {
        this.a = a;
        this.b = b;
        this.c = n - b;
        this.d = (e)a.getSource();
    }
    
    protected final void a(final int e) {
        if (this.d == null) {
            com.hw.client.util.a.d("unable to fire progress event, m_source => " + this.d);
            return;
        }
        final int n = this.b + this.c * e / 100;
        if (this.e != e) {
            com.hw.client.util.a.b("DoorProgressAdapter.fireProgressEvent(), percent => " + e + ", scaled_percent => " + n);
            this.e = e;
        }
        this.d.a(this.a.a(n), true);
    }
}
