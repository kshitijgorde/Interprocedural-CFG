// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.InvalidFormatException;
import borland.jbcl.util.Variant;

public class IntegerFormatter extends VariantFormatter
{
    protected int type;
    
    public IntegerFormatter(final int type) {
        this.type = type;
    }
    
    public final String format(final Variant value) {
        return (value == null || value.isNull()) ? "" : new Integer(value.getAsInt()).toString();
    }
    
    public void parse(final String stringValue, final Variant value) throws InvalidFormatException {
        if (stringValue == null || stringValue.length() == 0) {
            value.setUnassignedNull();
            return;
        }
        value.setAsInt(this.type, new Integer(stringValue));
    }
    
    public void parse(final Variant variant, final char[] value, int offset, int len) throws InvalidFormatException {
        if (value == null || len == 0 || value.length == 0) {
            variant.setUnassignedNull();
            return;
        }
        final boolean negative = value[offset] == '-';
        int result = 0;
        if (negative) {
            ++offset;
            --len;
        }
        int index = offset;
        while (len > 0) {
            int digit = value[index];
            if (digit >= 48 && digit <= 57) {
                digit -= 48;
            }
            else {
                digit = Character.digit((char)digit, 10);
                if (digit < 0) {
                    throw new InvalidFormatException(new String(value, offset, len));
                }
            }
            result = result * 10 + digit;
            ++index;
            --len;
        }
        variant.setAsInt(this.type, negative ? (-result) : result);
    }
    
    public int getVariantType() {
        return this.type;
    }
}
