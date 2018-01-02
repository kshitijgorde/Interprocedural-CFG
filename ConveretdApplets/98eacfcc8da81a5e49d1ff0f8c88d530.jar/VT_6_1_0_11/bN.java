// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import com.hw.client.util.f;
import com.hw.client.util.d;

final class bN implements d
{
    private final cJ a;
    
    private bN(final cJ a, final byte b) {
        this.a = a;
    }
    
    public final void a(final f f) {
        if (f instanceof cG) {
            final cG cg;
            if ((cg = (cG)f).a() == cG.a) {
                if (cJ.a(this.a) == 0) {
                    cJ.b(this.a).a(false, true, '\0', '\0', 8000);
                    cJ.b(this.a).a(dx.g());
                    cJ.b(this.a).a(dx.f());
                }
            }
            else if (cg.a() == cG.c && cJ.a(this.a) == 0) {
                cJ.b(this.a).c();
                cJ.a(this.a, 0);
                if (cJ.c(this.a) != null) {
                    cJ.c(this.a).j();
                }
            }
        }
    }
    
    bN(final cJ cj) {
        this(cj, (byte)0);
    }
}
