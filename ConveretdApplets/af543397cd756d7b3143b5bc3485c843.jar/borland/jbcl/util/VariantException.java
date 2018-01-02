// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

public class VariantException extends RuntimeException
{
    public VariantException(final String s) {
        super(s);
    }
    
    public static final void fire(final String s) throws VariantException {
        throw new VariantException(s);
    }
}
