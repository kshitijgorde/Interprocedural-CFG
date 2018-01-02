// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskCharAlpha extends ItemEditMaskCharObj
{
    public ItemEditMaskCharAlpha(final ItemEditMaskRegion region, final boolean optional) {
        super(region, optional);
    }
    
    public boolean isValid(final char c) {
        return Character.isLetter(c);
    }
}
