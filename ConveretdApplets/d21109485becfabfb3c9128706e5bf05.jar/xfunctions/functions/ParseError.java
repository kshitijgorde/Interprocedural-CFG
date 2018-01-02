// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public class ParseError extends Exception
{
    public String dataString;
    public int errorPosition;
    public int errorInStringNumber;
    
    ParseError(final String s, final int errorPosition, final String dataString) {
        super(s);
        this.errorPosition = errorPosition;
        this.dataString = dataString;
    }
}
