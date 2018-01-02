// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.FastStringBuffer;

class ItemEditMaskRegionLiteral extends ItemEditMaskRegion
{
    FastStringBuffer literal;
    
    ItemEditMaskRegionLiteral(final ItemEditMaskStr ems, final FastStringBuffer str) {
        super(ems, false);
        this.literal = new FastStringBuffer(str.value(), 0, str.length());
        super.capacity = this.literal.length();
    }
    
    public boolean isValid(final int charPosition, final char c) {
        return false;
    }
    
    public boolean isOptional(final int charPosition) {
        return true;
    }
    
    public boolean isLiteral(final int charPosition) {
        return true;
    }
}
