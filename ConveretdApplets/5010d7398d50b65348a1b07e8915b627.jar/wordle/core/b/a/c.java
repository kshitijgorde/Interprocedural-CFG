// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.b.a;

import java.util.Arrays;
import java.awt.Color;
import java.io.Serializable;

public abstract class c implements Serializable, e
{
    protected final Color[] a;
    private final Color b;
    
    public c(final Color[] array, final Color b) {
        this.a = array.clone();
        this.b = b;
    }
    
    public final Color[] a() {
        return this.a.clone();
    }
    
    public final Color b() {
        return this.b;
    }
    
    public boolean equals(final Object o) {
        return o instanceof c && (this.b.equals(((c)o).b) && Arrays.deepEquals(this.a, ((c)o).a));
    }
    
    public int hashCode() {
        return (527 + this.b.hashCode()) * 31 + Arrays.hashCode(this.a);
    }
}
