// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

public class BooleanEventToInteger extends Node implements FieldObserver
{
    public final IntField intField;
    public final BooleanField booleanField;
    public final BooleanField trueFilter;
    public final BooleanField falseFilter;
    static final int e = 0;
    static final int a = 1;
    static final int b = 2;
    
    public BooleanEventToInteger() {
        this.intField = new IntField(this, "intField", 0, -1);
        this.booleanField = new BooleanField(this, "booleanField", 0, false);
        this.trueFilter = new BooleanField(this, "trueFilter", 0, true);
        this.falseFilter = new BooleanField(this, "falseFilter", 0, false);
        this.booleanField.addFieldObserver(this, new Integer(0));
        this.trueFilter.addFieldObserver(this, new Integer(1));
        this.falseFilter.addFieldObserver(this, new Integer(2));
    }
    
    public void onFieldChange(final Field field, final Object o) {
        final int intValue = (int)o;
        final boolean value = ((BooleanField)field).getValue();
        switch (intValue) {
            case 0: {
                this.intField.setValue(this.intField.getValue());
            }
            case 1: {
                if (value) {
                    this.booleanField.setValue(true);
                    return;
                }
                break;
            }
            case 2: {
                if (!value) {
                    this.booleanField.setValue(false);
                    return;
                }
                break;
            }
        }
    }
    
    protected void finalize() throws Throwable {
        this.booleanField.removeFieldObserver(this);
        this.trueFilter.removeFieldObserver(this);
        this.falseFilter.removeFieldObserver(this);
        super.finalize();
    }
}
