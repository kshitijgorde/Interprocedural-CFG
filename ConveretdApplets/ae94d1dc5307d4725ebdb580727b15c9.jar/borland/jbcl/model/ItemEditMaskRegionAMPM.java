// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskRegionAMPM extends ItemEditMaskRegionAlpha
{
    ItemEditMaskRegionAMPM(final ItemEditMaskStr ems, final char c, final boolean optional) {
        super(ems, 1, optional);
        this.extentsOfResource("AmPmMarkers");
        super.charObjects.addElement(new ItemEditMaskCharAlpha(this, optional));
        super.c = c;
    }
}
