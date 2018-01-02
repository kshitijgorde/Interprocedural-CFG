// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskRegionDayInWeek extends ItemEditMaskRegionAlpha
{
    ItemEditMaskRegionDayInWeek(final ItemEditMaskStr ems, final char c, final int charCount, final boolean optional) {
        super(ems, charCount, optional);
        super.c = c;
        if (charCount <= 3) {
            this.extentsOfResource("DayAbbreviations");
        }
        else {
            this.extentsOfResource("DayNames");
        }
        super.charObjects.addElement(new ItemEditMaskCharAlpha(this, optional));
    }
}
