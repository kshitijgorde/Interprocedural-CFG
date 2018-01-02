// 
// Decompiled by Procyon v0.5.30
// 

package parser;

public class ParseException extends Exception
{
    public String errorMessage;
    public int errorPosition;
    
    public ParseException(final String errorMessage, final int errorPosition) {
        this.errorMessage = errorMessage;
        this.errorPosition = errorPosition;
    }
    
    public static void throwParseException(final String s, final int n) throws ParseException {
        throw new ParseException(s, n);
    }
}
