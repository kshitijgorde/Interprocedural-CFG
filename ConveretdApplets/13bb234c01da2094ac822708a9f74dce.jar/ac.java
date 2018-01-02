import java.util.Map;
import java.util.LinkedHashMap;

// 
// Decompiled by Procyon v0.5.30
// 

class ac extends LinkedHashMap
{
    final /* synthetic */ s a;
    
    ac(final s a) {
        this.a = a;
    }
    
    protected boolean removeEldestEntry(final Map.Entry entry) {
        return this.size() > 10;
    }
}
