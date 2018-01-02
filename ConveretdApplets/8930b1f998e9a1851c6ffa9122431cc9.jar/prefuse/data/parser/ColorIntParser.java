// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

import prefuse.util.ColorLib;

public class ColorIntParser implements DataParser
{
    public Class getType() {
        return Integer.TYPE;
    }
    
    public String format(final Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof Number)) {
            throw new IllegalArgumentException("This class can only format Objects of type Number.");
        }
        return String.valueOf(((Number)o).intValue());
    }
    
    public boolean canParse(final String s) {
        try {
            if (s.charAt(0) == '#') {
                parseInt(s);
                return true;
            }
            return false;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public Object parse(final String s) throws DataParseException {
        return new Integer(parseInt(s));
    }
    
    public static int parseInt(final String s) throws DataParseException {
        try {
            return ColorLib.hex(s);
        }
        catch (Exception ex) {
            throw new DataParseException(ex);
        }
    }
}
