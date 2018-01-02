// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskRegionSign extends ItemEditMaskRegion
{
    boolean prefix;
    char c;
    char blankChar;
    
    ItemEditMaskRegionSign(final ItemEditMaskStr ems, final char c, final boolean prefix, final char blankChar) {
        super(ems, true);
        this.c = c;
        this.blankChar = blankChar;
        this.prefix = prefix;
        super.capacity = 1;
    }
    
    public boolean isValid(final int charPosition, final char c) {
        return c == ' ' || c == '+' || c == '-' || (c == '(' && this.prefix) || (c == ')' && !this.prefix) || c == this.c || c == this.blankChar;
    }
    
    public boolean isOptional(final int charPosition) {
        return true;
    }
    
    public char setCharAt(final StringBuffer str, final int charPosition, char c) {
        if (c == '-' || c == '(' || c == ')') {
            c = this.c;
        }
        else if (c == '+' || c == ' ') {
            c = this.blankChar;
        }
        str.setCharAt(charPosition, c);
        return c;
    }
}
