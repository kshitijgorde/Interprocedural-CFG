// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import wordle.core.c.f;
import wordle.core.fitness.OverallShape;
import javax.swing.JRadioButtonMenuItem;
import wordle.core.c.d;

final class t implements d
{
    private final /* synthetic */ JRadioButtonMenuItem a;
    private final /* synthetic */ OverallShape b;
    
    t(final l l, final JRadioButtonMenuItem a, final OverallShape b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a(final f f) {
        this.a.setSelected(f.b.c.equals(this.b));
    }
}
