// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

public class BooleanParser implements DataParser
{
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    
    public Class getType() {
        return Boolean.TYPE;
    }
    
    public String format(final Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof Boolean)) {
            throw new IllegalArgumentException("This class can only format Objects of type Boolean.");
        }
        return ((Boolean)o).toString();
    }
    
    public boolean canParse(final String s) {
        return "true".equalsIgnoreCase(s) || "false".equalsIgnoreCase(s);
    }
    
    public Object parse(final String s) throws DataParseException {
        return this.parseBoolean(s) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    public boolean parseBoolean(final String s) throws DataParseException {
        if ("true".equalsIgnoreCase(s)) {
            return true;
        }
        if ("false".equalsIgnoreCase(s)) {
            return false;
        }
        throw new DataParseException("Input does not represent a boolean value: " + s);
    }
}
