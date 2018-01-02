// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.util.Hashtable;

public class LabelledItem extends MenuItem
{
    public boolean isSelectable() {
        return false;
    }
    
    public LabelledItem(final String s) {
        this(s, (Hashtable)null);
    }
    
    public LabelledItem(final String s, final Hashtable hashtable) {
        super(s, hashtable);
    }
}
