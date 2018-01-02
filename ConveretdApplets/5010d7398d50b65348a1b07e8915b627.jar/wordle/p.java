// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import wordle.core.fitness.Alphabetical;
import wordle.core.c.f;
import javax.swing.JCheckBoxMenuItem;
import wordle.core.c.d;

final class p implements d
{
    private final /* synthetic */ JCheckBoxMenuItem a;
    
    p(final l l, final JCheckBoxMenuItem a) {
        this.a = a;
    }
    
    public final void a(final f f) {
        this.a.setSelected(f.b.b.equals(Alphabetical.class));
    }
}
