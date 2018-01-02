// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.Variant;

public class BooleanFormatter extends VariantFormatter
{
    public final String format(final Variant value) {
        if (value == null || value.isNull()) {
            return "";
        }
        if (value.getBoolean()) {
            return Res.getString(0);
        }
        return Res.getString(1);
    }
    
    public final void parse(final String stringValue, final Variant value) {
        if (stringValue.length() > 0) {
            value.setBoolean(stringValue.equalsIgnoreCase(Res.getString(0)));
        }
        else {
            value.setAssignedNull();
        }
    }
    
    public int getVariantType() {
        return 11;
    }
}
