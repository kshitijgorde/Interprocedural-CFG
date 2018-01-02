// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.sql.Time;
import borland.jbcl.util.Variant;

public class TimeFormatter extends VariantFormatter
{
    public final String format(final Variant value) {
        return (value == null || value.isNull()) ? "" : value.getTime().toString();
    }
    
    public final void parse(final String stringValue, final Variant value) {
        if (stringValue == null || stringValue.length() == 0) {
            value.setUnassignedNull();
            return;
        }
        final Time t = Time.valueOf(stringValue);
        value.setTime(t);
    }
    
    public void parse(final Variant variant, final char[] value, final int offset, final int len) {
        if (len == 0) {
            variant.setUnassignedNull();
        }
        else {
            variant.setTime(Time.valueOf(new String(value, offset, len)));
        }
    }
    
    public int getVariantType() {
        return 14;
    }
}
