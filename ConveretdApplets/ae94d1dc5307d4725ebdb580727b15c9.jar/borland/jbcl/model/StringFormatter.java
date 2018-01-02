// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.Variant;

public class StringFormatter extends VariantFormatter
{
    public final String format(final Variant value) {
        return value.getString();
    }
    
    public void parse(final String stringValue, final Variant value) {
        value.setString(stringValue);
    }
    
    public void parse(final Variant variant, final char[] value, final int offset, final int len) {
        variant.setString(new String(value, offset, len));
    }
    
    public int getVariantType() {
        return 16;
    }
}
