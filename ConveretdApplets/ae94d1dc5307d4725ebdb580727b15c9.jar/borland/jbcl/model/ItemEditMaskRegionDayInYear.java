// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskRegionDayInYear extends ItemEditMaskRegionDigit
{
    ItemEditMaskRegionDayInYear(final ItemEditMaskStr ems, final char c, final int charCount, final boolean optional) {
        super(ems, charCount, optional);
        super.c = c;
    }
}