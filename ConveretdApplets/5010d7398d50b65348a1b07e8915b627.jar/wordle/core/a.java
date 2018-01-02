// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core;

import wordle.core.e.b;
import java.io.Serializable;

final class a implements Serializable
{
    private final double a;
    
    public a(final double a) {
        this.a = a;
    }
    
    public final double a() {
        if (b.a() < this.a) {
            return -1.5707963267948966;
        }
        return 0.0;
    }
}
