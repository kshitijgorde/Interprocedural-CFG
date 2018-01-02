// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.b;

import java.util.Map;
import java.util.LinkedHashMap;

final class j extends LinkedHashMap
{
    private final /* synthetic */ int a;
    
    j(final i i, final int n, final float n2, final boolean b, final int a) {
        this.a = a;
        super(n, n2, true);
    }
    
    protected final boolean removeEldestEntry(final Map.Entry entry) {
        return this.size() > this.a;
    }
}
