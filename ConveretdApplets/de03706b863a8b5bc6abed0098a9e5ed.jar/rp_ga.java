import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_ga extends SoftReference
{
    final Object a;
    
    private rp_ga(final Object o, final Object a, final ReferenceQueue referenceQueue, final byte b) {
        super(o, referenceQueue);
        this.a = a;
    }
}
