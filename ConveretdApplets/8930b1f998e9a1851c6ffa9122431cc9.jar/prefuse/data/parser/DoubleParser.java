// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.parser;

public class DoubleParser implements DataParser
{
    private boolean m_blockExplicitFloats;
    
    public DoubleParser() {
        this.m_blockExplicitFloats = true;
    }
    
    public Class getType() {
        return Double.TYPE;
    }
    
    public String format(final Object o) {
        if (o == null) {
            return null;
        }
        if (!(o instanceof Number)) {
            throw new IllegalArgumentException("This class can only format Objects of type Number.");
        }
        return String.valueOf(((Number)o).doubleValue());
    }
    
    public boolean canParse(final String s) {
        try {
            if (this.m_blockExplicitFloats && s.endsWith("f")) {
                return false;
            }
            Double.parseDouble(s);
            return true;
        }
        catch (NumberFormatException ex) {
            return false;
        }
    }
    
    public Object parse(final String s) throws DataParseException {
        return new Double(parseDouble(s));
    }
    
    public static double parseDouble(final String s) throws DataParseException {
        try {
            return Double.parseDouble(s);
        }
        catch (NumberFormatException ex) {
            throw new DataParseException(ex);
        }
    }
}
