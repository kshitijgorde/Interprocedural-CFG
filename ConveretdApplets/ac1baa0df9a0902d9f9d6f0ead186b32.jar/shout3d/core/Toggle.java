// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class Toggle extends Node implements FieldObserver
{
    public final DoubleField toggleTime;
    public final DoubleField trueTime;
    public final DoubleField falseTime;
    public final BooleanField toggleValue;
    
    public Toggle() {
        this.toggleTime = new DoubleField(this, "toggleTime", 0, 0.0);
        this.trueTime = new DoubleField(this, "trueTime", 0, 0.0);
        this.falseTime = new DoubleField(this, "falseTime", 0, 0.0);
        this.toggleValue = new BooleanField(this, "toggleValue", 0, false);
        this.toggleTime.addFieldObserver(this, null);
    }
    
    public void onFieldChange(final Field field, final Object o) {
        this.toggleValue.setValue(!this.toggleValue.getValue());
        if (this.getViewer() == null) {
            return;
        }
        if (this.toggleValue.getValue()) {
            this.trueTime.setValue(this.getViewer().getClock().getAbsoluteTime());
            return;
        }
        this.falseTime.setValue(this.getViewer().getClock().getAbsoluteTime());
    }
}
