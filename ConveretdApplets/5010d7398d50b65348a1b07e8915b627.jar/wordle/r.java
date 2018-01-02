// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import wordle.core.c.f;
import wordle.core.z;
import javax.swing.JMenuItem;
import wordle.core.c.d;

final class r implements d
{
    private final /* synthetic */ JMenuItem a;
    private final /* synthetic */ z b;
    
    r(final l l, final JMenuItem a, final z b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a(final f f) {
        this.a.setSelected(f.b.d == this.b);
    }
}
