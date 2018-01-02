// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.text.Format;
import java.util.Locale;
import borland.jbcl.util.FastStringBuffer;
import borland.jbcl.util.InvalidFormatException;
import borland.jbcl.util.Variant;

public abstract class VariantFormatter extends ItemFormatter
{
    public String format(final Object value) throws InvalidFormatException {
        if (!(value instanceof Variant)) {
            throw new InvalidFormatException(Res.getString(17));
        }
        return this.format((Variant)value);
    }
    
    public Object parse(final String stringValue) throws InvalidFormatException {
        final Variant value = new Variant();
        this.parse(stringValue, value);
        return value;
    }
    
    public abstract String format(final Variant p0);
    
    public FastStringBuffer format(final Variant value, final FastStringBuffer buffer) {
        final String s = this.format(value);
        if (s != null) {
            buffer.append(s);
            return buffer;
        }
        return null;
    }
    
    public abstract void parse(final String p0, final Variant p1) throws InvalidFormatException;
    
    public void parse(final String stringValue, final Variant value, final int variantType) throws InvalidFormatException {
        throw new InvalidFormatException(Res.getString(19));
    }
    
    public void parse(final Variant variant, final char[] value, final int offset, final int len) throws InvalidFormatException {
        if (len == 0) {
            variant.setUnassignedNull();
            return;
        }
        final String s = new String(value, offset, len);
        this.parse(s, variant);
    }
    
    public abstract int getVariantType();
    
    public String getPattern() {
        return null;
    }
    
    public String setPattern(final String pattern) {
        return pattern;
    }
    
    public Object setSpecialObject(final int charType, final Object obj) {
        return null;
    }
    
    public Object getSpecialObject(final int objType) {
        return null;
    }
    
    public Locale getLocale() {
        return Locale.getDefault();
    }
    
    public Format getFormatObj() {
        return null;
    }
    
    public int getScale() {
        return -1;
    }
}
