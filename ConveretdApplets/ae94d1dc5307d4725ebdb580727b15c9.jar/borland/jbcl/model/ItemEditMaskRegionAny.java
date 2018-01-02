// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

import borland.jbcl.util.FastStringBuffer;

class ItemEditMaskRegionAny extends ItemEditMaskRegion
{
    private boolean blockDelete;
    
    public ItemEditMaskRegionAny(final ItemEditMaskStr ems) {
        super(ems, true);
    }
    
    public ItemEditMaskRegionAny(final ItemEditMaskStr ems, final int charCount, final boolean blockDelete) {
        this(ems);
        super.capacity = charCount;
        this.blockDelete = blockDelete;
    }
    
    public boolean isValid(final int charPosition, final char c) {
        return true;
    }
    
    public boolean isOptional(final int charPosition) {
        return true;
    }
    
    public char setCharAt(final StringBuffer str, final int charPosition, final char c) {
        if (charPosition >= str.length()) {
            str.append(c);
        }
        else {
            str.setCharAt(charPosition, c);
        }
        super.ems.setLastEditPosition(str);
        return c;
    }
    
    public void deleteCharAt(final StringBuffer str, final int charPosition, final char blankChar) {
        if (this.blockDelete) {
            super.deleteCharAt(str, charPosition, blankChar);
        }
        else {
            final FastStringBuffer fsb = new FastStringBuffer(str.toString());
            try {
                fsb.removeCharAt(charPosition);
            }
            catch (StringIndexOutOfBoundsException ex) {}
            synchronized (str) {
                str.setLength(0);
                str.append(fsb.toString());
            }
        }
        super.ems.setLastEditPosition(str);
    }
}
