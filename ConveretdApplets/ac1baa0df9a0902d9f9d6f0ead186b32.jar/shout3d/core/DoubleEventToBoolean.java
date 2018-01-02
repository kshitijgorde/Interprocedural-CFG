// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class DoubleEventToBoolean extends Node implements FieldObserver
{
    public final BooleanField booleanTrueField;
    public final BooleanField booleanFalseField;
    public final DoubleField doubleField;
    
    public DoubleEventToBoolean() {
        this.booleanTrueField = new BooleanField(this, "booleanTrueField", 0, true);
        this.booleanFalseField = new BooleanField(this, "booleanFalseField", 0, false);
        (this.doubleField = new DoubleField(this, "doubleField", 0, 0.0)).addFieldObserver(this, null);
    }
    
    public void onFieldChange(final Field field, final Object o) {
        if (field == this.doubleField) {
            this.booleanTrueField.setValue(true);
            this.booleanFalseField.setValue(false);
        }
    }
    
    protected void finalize() throws Throwable {
        this.doubleField.removeFieldObserver(this);
        super.finalize();
    }
}
