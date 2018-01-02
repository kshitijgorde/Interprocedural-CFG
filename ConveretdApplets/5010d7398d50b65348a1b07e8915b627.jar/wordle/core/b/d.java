// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.b;

import java.awt.Font;

public final class d
{
    private final Font a;
    private final String b;
    private final Double c;
    private final Double d;
    
    public d(final Font a, final double n, final String b, final Double d) {
        this.a = a;
        this.d = d;
        this.c = n;
        this.b = b;
    }
    
    public final int hashCode() {
        return (((629 + this.b.hashCode()) * 37 + this.a.hashCode()) * 37 + this.d.hashCode()) * 37 + this.c.hashCode();
    }
    
    public final boolean equals(final Object o) {
        final d d;
        return o != null && o instanceof d && (d = (d)o).a.equals(this.a) && d.d.equals(this.d) && d.b.equals(this.b) && d.c.equals(this.c);
    }
}
