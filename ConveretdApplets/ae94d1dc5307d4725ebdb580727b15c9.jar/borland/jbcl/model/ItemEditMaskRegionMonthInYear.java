// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskRegionMonthInYear extends ItemEditMaskRegion
{
    ItemEditMaskRegionMonthInYear(final ItemEditMaskStr ems, final char c, final int charCount, final boolean optional) {
        super(ems, optional);
        super.c = c;
        super.charCount = charCount;
        if (charCount <= 2) {
            super.capacity = 2;
            super.minRequired = 1;
            super.charObjects.addElement(new ItemEditMaskCharDigit(this, optional));
        }
        else {
            if (charCount == 3) {
                this.extentsOfResource("MonthAbbreviations");
            }
            else {
                this.extentsOfResource("MonthNames");
            }
            super.charObjects.addElement(new ItemEditMaskCharAlpha(this, optional));
            super.rightToLeft = false;
        }
    }
}
