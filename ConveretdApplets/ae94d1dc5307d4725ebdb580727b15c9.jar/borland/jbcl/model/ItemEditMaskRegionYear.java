// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskRegionYear extends ItemEditMaskRegionDigit
{
    ItemEditMaskRegionYear(final ItemEditMaskStr ems, final char c, final int charCount, final boolean optional) {
        super(ems, 2, optional);
        super.c = c;
        if (charCount <= 2) {
            super.capacity = 2;
            super.minRequired = 1;
        }
        else {
            super.capacity = 4;
            super.minRequired = 2;
        }
        super.c = c;
    }
}
