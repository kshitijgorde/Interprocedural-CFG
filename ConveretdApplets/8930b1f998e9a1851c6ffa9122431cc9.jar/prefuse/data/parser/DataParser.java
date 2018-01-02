// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

public interface DataParser
{
    Class getType();
    
    String format(final Object p0);
    
    boolean canParse(final String p0);
    
    Object parse(final String p0) throws DataParseException;
}
