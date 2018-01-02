// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.InvalidFormatException;
import borland.jbcl.util.Variant;

public class ByteFormatter extends IntegerFormatter
{
    public ByteFormatter(final int type) {
        super(type);
    }
    
    public final void parse(final String stringValue, final Variant value) throws InvalidFormatException {
        if (stringValue == null || stringValue.length() == 0) {
            value.setUnassignedNull();
            return;
        }
        final int i = new Integer(stringValue);
        if (i > 127 || i < -128) {
            throw new InvalidFormatException(Res.getString(33));
        }
        value.setAsInt(super.type, i);
    }
    
    public final void parse(final Variant variant, final char[] value, int offset, int len) throws InvalidFormatException {
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
        final int i = negative ? (-result) : result;
        if (i > 127 || i < -128) {
            throw new InvalidFormatException(Res.getString(33));
        }
        variant.setAsInt(super.type, i);
    }
}
