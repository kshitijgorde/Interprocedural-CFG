// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import java.text.Format;
import java.util.Locale;
import borland.jbcl.util.InvalidFormatException;

public abstract class ItemFormatter
{
    static final int NUMERIC = 1;
    static final int DECIMAL = 2;
    static final int DATETIME = 3;
    static final int TEXT = 4;
    static final int BOOLEAN = 5;
    static final char NOTACHAR = '\uffff';
    static final int FILLCHARACTER = 1;
    static final int REPLACECHARACTER = 2;
    
    public abstract String format(final Object p0) throws InvalidFormatException;
    
    public abstract Object parse(final String p0) throws InvalidFormatException;
    
    public String getPattern() {
        return null;
    }
    
    public String setPattern(final String pattern) {
        return pattern;
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
}
