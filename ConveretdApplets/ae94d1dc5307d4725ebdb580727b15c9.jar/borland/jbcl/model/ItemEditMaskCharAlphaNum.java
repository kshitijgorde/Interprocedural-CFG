// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskCharAlphaNum extends ItemEditMaskCharObj
{
    public ItemEditMaskCharAlphaNum(final ItemEditMaskRegion region, final boolean optional) {
        super(region, optional);
    }
    
    public boolean isValid(final char c) {
        return Character.isLetterOrDigit(c);
    }
}
