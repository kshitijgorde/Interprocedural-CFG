import java.util.EventObject;

// 
// Decompiled by Procyon v0.5.30
// 

class UBBImageListenerEvent extends EventObject
{
    private UBBImageProxy imageUpdated;
    
    public UBBImageListenerEvent(final Object o) {
        super(o);
        this.imageUpdated = (UBBImageProxy)o;
    }
    
    public UBBImageProxy getImageUpdated() {
        return this.imageUpdated;
    }
}
