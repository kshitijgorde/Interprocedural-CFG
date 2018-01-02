// 
// Decompiled by Procyon v0.5.30
// 

final class ba extends r implements Interface9
{
    long nativeid;
    
    @Override
    public final native void w(final boolean p0);
    
    ba(final oa oa) {
    }
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
}
