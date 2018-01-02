// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskCharDigitSign extends ItemEditMaskCharDigit
{
    public ItemEditMaskCharDigitSign(final ItemEditMaskRegion region, final boolean optional) {
        super(region, optional);
    }
    
    public boolean isValid(final char c) {
        return super.isValid(c) || (c == '+' || c == '-') || Character.isWhitespace(c);
    }
}
