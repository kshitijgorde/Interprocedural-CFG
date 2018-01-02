// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.text.Format;
import java.util.Locale;
import borland.jbcl.util.InvalidFormatException;
import borland.jbcl.util.Variant;

public class BinaryFormatter extends VariantFormatter
{
    public String format(final Variant value) {
        return null;
    }
    
    public void parse(final String stringValue, final Variant value) throws InvalidFormatException {
    }
    
    public void parse(final String stringValue, final Variant value, final int variantType) throws InvalidFormatException {
    }
    
    public int getVariantType() {
        return 12;
    }
    
    public String getPattern() {
        return null;
    }
    
    public String setPattern(final String pattern) {
        return null;
    }
    
    public Object setSpecialObject(final int objType, final Object obj) {
        return null;
    }
    
    public Object getSpecialObject(final int objType) {
        return null;
    }
    
    public Locale getLocale() {
        return null;
    }
    
    public Format getFormatObj() {
        return null;
    }
}
