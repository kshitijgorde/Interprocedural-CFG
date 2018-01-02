// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import wordle.core.b.a.a;
import wordle.core.c.f;
import javax.swing.JRadioButtonMenuItem;
import wordle.core.c.d;

final class ad implements d
{
    private final /* synthetic */ JRadioButtonMenuItem a;
    private final /* synthetic */ double b;
    
    ad(final w w, final JRadioButtonMenuItem a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a(final f f) {
        this.a.setSelected(((a)f.c).d() == this.b);
    }
}
