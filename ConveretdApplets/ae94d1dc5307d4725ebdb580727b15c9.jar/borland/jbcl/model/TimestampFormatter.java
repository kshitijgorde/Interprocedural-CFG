// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.sql.Timestamp;
import borland.jbcl.util.Variant;

public class TimestampFormatter extends VariantFormatter
{
    public final String format(final Variant value) {
        return (value == null || value.isNull()) ? "" : value.getTimestamp().toString();
    }
    
    public final void parse(final String stringValue, final Variant value) {
        final Timestamp t = Timestamp.valueOf(stringValue);
        value.setTimestamp(t);
    }
    
    public void parse(final Variant variant, final char[] value, final int offset, final int len) {
        if (len == 0) {
            variant.setUnassignedNull();
        }
        else {
            variant.setTimestamp(Timestamp.valueOf(new String(value, offset, len)));
        }
    }
    
    public int getVariantType() {
        return 14;
    }
}
