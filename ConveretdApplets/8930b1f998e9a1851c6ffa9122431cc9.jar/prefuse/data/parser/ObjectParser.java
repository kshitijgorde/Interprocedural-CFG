// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

public class ObjectParser implements DataParser
{
    public Class getType() {
        return Object.class;
    }
    
    public String format(final Object o) {
        return (o == null) ? null : o.toString();
    }
    
    public boolean canParse(final String s) {
        return false;
    }
    
    public Object parse(final String s) throws DataParseException {
        throw new UnsupportedOperationException();
    }
}
