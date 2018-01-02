// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.b.a;

import wordle.core.e.b;
import wordle.core.C;
import java.awt.Color;

public final class d extends c
{
    public d(final Color[] array, final Color color) {
        super(array, color);
    }
    
    public final Color a(final C c) {
        return this.a[wordle.core.e.b.a(this.a.length)];
    }
    
    public final boolean equals(final Object o) {
        return super.equals(o) && o instanceof d;
    }
}
