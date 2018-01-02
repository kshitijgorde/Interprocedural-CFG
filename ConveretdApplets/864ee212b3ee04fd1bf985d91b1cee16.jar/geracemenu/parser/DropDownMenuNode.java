// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.DropDownMenu;
import geracemenu.MenuItem;
import java.util.Hashtable;

public class DropDownMenuNode extends MenuNode
{
    public MenuItem cloneNode(final String s, final Hashtable hashtable) {
        return new DropDownMenu(s, hashtable);
    }
    
    public DropDownMenuNode(final String s) {
        super(s);
    }
}
