// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.model;

public class ItemEditMaskState
{
    public StringBuffer displayString;
    public int cursorPos;
    int variantType;
    Object privateObject;
    
    public ItemEditMaskState() {
        this(16, 0);
    }
    
    public ItemEditMaskState(final int size, final int cursorPos) {
        this.displayString = new StringBuffer(size);
        this.cursorPos = cursorPos;
        this.privateObject = null;
    }
}
