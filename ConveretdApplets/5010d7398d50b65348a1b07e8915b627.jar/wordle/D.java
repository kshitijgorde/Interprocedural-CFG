// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import wordle.core.c.f;
import cue.lang.stop.StopWords;
import javax.swing.JRadioButtonMenuItem;
import wordle.core.c.d;

final class D implements d
{
    private final /* synthetic */ JRadioButtonMenuItem a;
    private final /* synthetic */ StopWords b;
    
    D(final k k, final JRadioButtonMenuItem a, final StopWords b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a(final f f) {
        this.a.setSelected(f.b.h == this.b);
    }
}
