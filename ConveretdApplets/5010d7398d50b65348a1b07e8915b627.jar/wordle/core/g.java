// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import wordle.core.b.c;
import java.util.Iterator;

final class g implements Iterator
{
    private int a;
    private final c[] b;
    
    g(final K k) {
        this.a = 0;
        this.b = k.e();
    }
    
    public final void remove() {
        throw new UnsupportedOperationException();
    }
    
    public final boolean hasNext() {
        return this.a < this.b.length;
    }
}
