// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.util.Hashtable;

public class DropDownMenu extends Menu
{
    public boolean isClickable() {
        return true;
    }
    
    public DropDownMenu(final String s) {
        this(s, (Hashtable)null);
    }
    
    public DropDownMenu(final String s, final Hashtable hashtable) {
        super(s, hashtable);
    }
}
