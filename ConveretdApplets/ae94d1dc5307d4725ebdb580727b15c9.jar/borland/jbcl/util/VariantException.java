// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.util;

public class VariantException extends RuntimeException
{
    public VariantException(final String error) {
        super(error);
    }
    
    public static final void fire(final String error) throws VariantException {
        throw new VariantException(error);
    }
}
