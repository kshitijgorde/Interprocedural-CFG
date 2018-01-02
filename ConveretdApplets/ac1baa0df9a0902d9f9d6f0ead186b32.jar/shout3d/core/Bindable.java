// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public abstract class Bindable extends Node implements FieldObserver
{
    public final BooleanField isBound;
    protected static final short a = 1;
    
    public Bindable() {
        this(null);
    }
    
    public Bindable(final Shout3DViewer viewer) {
        this.isBound = new BooleanField(this, "isBound", 0, true);
        this.setViewer(viewer);
        this.isBound.addFieldObserver(this, null);
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (field == this.isBound && super.c != null) {
            super.c.a(this);
        }
    }
    
    protected void finalize() throws Throwable {
        this.isBound.removeFieldObserver(this);
        super.finalize();
    }
}
