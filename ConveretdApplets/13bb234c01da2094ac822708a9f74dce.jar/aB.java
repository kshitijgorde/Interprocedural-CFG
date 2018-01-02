import java.util.Map;
import java.util.LinkedHashMap;

// 
// Decompiled by Procyon v0.5.30
// 

class aB extends LinkedHashMap
{
    final /* synthetic */ y a;
    
    aB(final y a) {
        this.a = a;
    }
    
    protected boolean removeEldestEntry(final Map.Entry entry) {
        return this.size() > 10;
    }
}
