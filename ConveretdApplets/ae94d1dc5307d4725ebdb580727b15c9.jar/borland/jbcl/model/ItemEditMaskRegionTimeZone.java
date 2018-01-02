// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskRegionTimeZone extends ItemEditMaskRegionAlpha
{
    ItemEditMaskRegionTimeZone(final ItemEditMaskStr ems, final char c, final int charCount, final boolean optional) {
        super(ems, charCount, optional);
        super.c = c;
        if (charCount <= 3) {
            super.capacity = 3;
        }
        else {
            super.capacity = 30;
        }
        super.minRequired = 3;
        super.charObjects.addElement(new ItemEditMaskCharAlpha(this, optional));
    }
}
