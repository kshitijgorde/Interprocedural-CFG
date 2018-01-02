// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

public class LongParser implements DataParser
{
    public Class getType() {
        return Long.TYPE;
    }
    
    public String format(final Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof Number)) {
            throw new IllegalArgumentException("This class can only format Objects of type Number.");
        }
        return String.valueOf(((Number)o).longValue()) + "L";
    }
    
    public boolean canParse(final String s) {
        try {
            parseLong(s);
            return true;
        }
        catch (DataParseException ex) {
            return false;
        }
    }
    
    public Object parse(final String s) throws DataParseException {
        return new Long(parseLong(s));
    }
    
    public static long parseLong(String substring) throws DataParseException {
        try {
            if (substring.length() > 0) {
                final char char1 = substring.charAt(substring.length() - 1);
                if (char1 == 'l' || char1 == 'L') {
                    substring = substring.substring(0, substring.length() - 1);
                }
            }
            return Long.parseLong(substring);
        }
        catch (NumberFormatException ex) {
            throw new DataParseException(ex);
        }
    }
}
