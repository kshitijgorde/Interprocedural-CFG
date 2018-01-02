// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import wordle.core.b.a.a;
import wordle.core.c.f;
import wordle.core.b.a.e;
import wordle.core.c.d;

final class ab implements d
{
    private final /* synthetic */ I a;
    private final /* synthetic */ e b;
    
    ab(final w w, final I a, final e b) {
        this.a = a;
        this.b = b;
    }
    
    public final void a(final f f) {
        this.a.setSelected(((a)f.c).c().equals(this.b));
    }
}
