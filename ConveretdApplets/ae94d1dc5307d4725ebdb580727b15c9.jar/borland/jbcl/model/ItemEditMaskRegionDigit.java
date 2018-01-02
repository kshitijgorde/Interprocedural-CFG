// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskRegionDigit extends ItemEditMaskRegion
{
    ItemEditMaskRegionDigit(final ItemEditMaskStr ems, final int charCount, final boolean optional) {
        super(ems, optional);
        super.charCount = charCount;
        super.capacity = Math.max(2, charCount);
        super.minRequired = 1;
        super.charObjects.addElement(new ItemEditMaskCharDigit(this, optional));
    }
}
