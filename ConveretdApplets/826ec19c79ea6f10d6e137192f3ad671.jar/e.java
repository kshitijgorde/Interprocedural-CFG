import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

// 
// Decompiled by Procyon v0.5.30
// 

final class e extends HashMap
{
    private /* synthetic */ HashSet a;
    
    e(final Jino jino, final HashSet a) {
        this.a = a;
    }
    
    @Override
    public final Set entrySet() {
        return this.a;
    }
}
