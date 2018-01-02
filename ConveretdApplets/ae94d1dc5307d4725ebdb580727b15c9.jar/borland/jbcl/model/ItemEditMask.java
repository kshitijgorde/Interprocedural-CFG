// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.InvalidFormatException;
import borland.jbcl.util.Variant;

public interface ItemEditMask
{
    ItemEditMaskState prepare(final Variant p0);
    
    boolean move(final ItemEditMaskState p0, final int p1);
    
    boolean insert(final ItemEditMaskState p0, final char p1);
    
    boolean delete(final ItemEditMaskState p0, final int p1, final int p2);
    
    boolean isComplete(final ItemEditMaskState p0);
    
    void getFinalValue(final ItemEditMaskState p0, final Variant p1) throws InvalidFormatException;
    
    void getFinalValue(final ItemEditMaskState p0, final Variant p1, final int p2) throws InvalidFormatException;
}
