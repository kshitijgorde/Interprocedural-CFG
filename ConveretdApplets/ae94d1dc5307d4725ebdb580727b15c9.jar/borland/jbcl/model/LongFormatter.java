// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.InvalidFormatException;
import borland.jbcl.util.Variant;

public class LongFormatter extends VariantFormatter
{
    public final String format(final Variant value) {
        return (value == null || value.isNull()) ? "" : new Long(value.getLong()).toString();
    }
    
    public final void parse(final String stringValue, final Variant value) {
        if (stringValue == null || stringValue.length() == 0) {
            value.setUnassignedNull();
            return;
        }
        value.setLong(new Long(stringValue));
    }
    
    public final void parse(final Variant variant, final char[] value, int offset, int len) throws InvalidFormatException {
        if (value == null || len == 0 || value.length == 0) {
            variant.setUnassignedNull();
            return;
        }
        final boolean negative = value[offset] == '-';
        int result = 0;
        long longResult = 0L;
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
            if (result > 99999999) {
                if (longResult == 0) {
                    longResult = result;
                }
                longResult = longResult * 10 + digit;
            }
            else {
                result = result * 10 + digit;
            }
            ++index;
            --len;
        }
        if (longResult == 0) {
            variant.setLong(negative ? (-result) : result);
        }
        else {
            variant.setLong(negative ? (-longResult) : longResult);
        }
    }
    
    public int getVariantType() {
        return 5;
    }
}
