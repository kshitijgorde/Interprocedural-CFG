// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class Interpolator extends Node implements FieldObserver
{
    public final FloatField fraction;
    
    public Interpolator() {
        (this.fraction = new FloatField(this, "fraction", 0, 0.0f)).addFieldObserver(this, null);
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (field == this.fraction) {
            this.update();
        }
    }
    
    public void update() {
    }
    
    protected void finalize() throws Throwable {
        this.fraction.removeFieldObserver(this);
        super.finalize();
    }
    
    protected void a(final float n) {
    }
}
