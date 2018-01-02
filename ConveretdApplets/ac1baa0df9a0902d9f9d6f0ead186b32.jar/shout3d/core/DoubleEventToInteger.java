// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class DoubleEventToInteger extends Node implements FieldObserver
{
    public final IntField intField;
    public final DoubleField doubleField;
    
    public DoubleEventToInteger() {
        this.intField = new IntField(this, "intField", 0, -1);
        (this.doubleField = new DoubleField(this, "doubleField", 0, 0.0)).addFieldObserver(this, null);
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (field == this.doubleField) {
            this.intField.setValue(this.intField.getValue());
        }
    }
    
    protected void finalize() throws Throwable {
        this.doubleField.removeFieldObserver(this);
        super.finalize();
    }
}
