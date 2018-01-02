// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.Variant;

public class DoubleFormatter extends VariantFormatter
{
    private int type;
    
    public DoubleFormatter(final int type) {
        this.type = type;
    }
    
    public final String format(final Variant value) {
        return (value == null || value.isNull()) ? "" : new Double(value.getAsDouble()).toString();
    }
    
    public final void parse(final String stringValue, final Variant value) {
        if (stringValue == null || stringValue.length() == 0) {
            value.setUnassignedNull();
            return;
        }
        value.setAsDouble(this.type, new Double(stringValue));
    }
    
    public int getVariantType() {
        return this.type;
    }
}
