// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.util.Date;
import borland.jbcl.util.Variant;

public class DateFormatter extends VariantFormatter
{
    public final String format(final Variant value) {
        return (value == null || value.isNull()) ? "" : new Date(value.getDate().getTime()).toGMTString();
    }
    
    public final void parse(final String stringValue, final Variant value) {
        if (stringValue == null || stringValue.length() == 0) {
            value.setUnassignedNull();
            return;
        }
        final java.sql.Date date = new java.sql.Date(70, 0, 1);
        try {
            date.setTime(new Date(stringValue).getTime());
        }
        catch (RuntimeException ex) {
            System.out.println(String.valueOf("Format error ").concat(String.valueOf(stringValue)));
            throw ex;
        }
        value.setDate(date);
    }
    
    public int getVariantType() {
        return 13;
    }
}
