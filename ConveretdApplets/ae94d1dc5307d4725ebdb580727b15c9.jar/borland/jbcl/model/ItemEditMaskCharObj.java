// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

class ItemEditMaskCharObj implements ItemEditMaskChar
{
    protected ItemEditMaskRegion region;
    protected boolean optional;
    
    public ItemEditMaskCharObj(final ItemEditMaskRegion region, final boolean optional) {
        this.region = region;
        this.optional = optional;
    }
    
    public boolean isOptional() {
        return this.optional;
    }
    
    public boolean isValid(final char c) {
        return true;
    }
}
