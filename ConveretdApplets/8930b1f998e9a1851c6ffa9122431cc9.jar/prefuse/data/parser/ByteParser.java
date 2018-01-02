// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

public class ByteParser implements DataParser
{
    public Class getType() {
        return Byte.TYPE;
    }
    
    public String format(final Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof Number)) {
            throw new IllegalArgumentException("This class can only format Objects of type Number.");
        }
        return String.valueOf(((Number)o).byteValue());
    }
    
    public boolean canParse(final String s) {
        try {
            Byte.parseByte(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public Object parse(final String s) throws DataParseException {
        return new Byte(parseByte(s));
    }
    
    public static byte parseByte(final String s) throws DataParseException {
        try {
            return Byte.parseByte(s);
        }
        catch (NumberFormatException ex) {
            throw new DataParseException(ex);
        }
    }
}
