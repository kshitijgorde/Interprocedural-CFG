// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

public interface ItemEditMaskRegionChar
{
    boolean isValid(final int p0, final char p1);
    
    boolean isOptional(final int p0);
    
    char setCharAt(final StringBuffer p0, final int p1, final char p2);
    
    char getCharAt(final StringBuffer p0, final int p1);
    
    boolean isLiteral(final int p0);
    
    void deleteCharAt(final StringBuffer p0, final int p1, final char p2);
}
