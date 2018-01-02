// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

public class InvalidFormatException extends Exception
{
    int errorOffset;
    
    public InvalidFormatException(final String format) {
        super(format);
    }
    
    public InvalidFormatException(final String format, final int errorOffset) {
        super(format);
        this.errorOffset = errorOffset;
    }
    
    public int getErrorOffset() {
        return this.errorOffset;
    }
    
    public static final void fire(final String string) throws InvalidFormatException {
        throw new InvalidFormatException(string);
    }
}
