// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

public class FloatParser implements DataParser
{
    public Class getType() {
        return Float.TYPE;
    }
    
    public String format(final Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof Number)) {
            throw new IllegalArgumentException("This class can only format Objects of type Number.");
        }
        return String.valueOf(((Number)o).floatValue()) + "f";
    }
    
    public boolean canParse(final String s) {
        try {
            Float.parseFloat(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public Object parse(final String s) throws DataParseException {
        return new Float(parseFloat(s));
    }
    
    public static float parseFloat(final String s) throws DataParseException {
        try {
            return Float.parseFloat(s);
        }
        catch (NumberFormatException ex) {
            throw new DataParseException(ex);
        }
    }
}
