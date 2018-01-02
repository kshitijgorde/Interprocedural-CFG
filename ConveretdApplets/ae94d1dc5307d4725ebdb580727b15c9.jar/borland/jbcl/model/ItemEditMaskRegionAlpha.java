// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskRegionAlpha extends ItemEditMaskRegion
{
    ItemEditMaskRegionAlpha(final ItemEditMaskStr ems, final int charCount, final boolean optional) {
        super(ems, optional);
        super.charCount = charCount;
        super.capacity = Math.max(2, charCount);
        super.charObjects.addElement(new ItemEditMaskCharAlpha(this, optional));
        super.rightToLeft = false;
    }
}
