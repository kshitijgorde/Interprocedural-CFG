// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

public class StringParser implements DataParser
{
    public Class getType() {
        return String.class;
    }
    
    public String format(final Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof String)) {
            throw new IllegalArgumentException("This class can only format Objects of type String.");
        }
        return (String)o;
    }
    
    public boolean canParse(final String s) {
        return true;
    }
    
    public Object parse(final String s) throws DataParseException {
        return s;
    }
    
    public String parseString(final String s) throws DataParseException {
        return s;
    }
}
