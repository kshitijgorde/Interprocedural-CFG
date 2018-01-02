// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.FlatMenu;
import geracemenu.MenuItem;
import java.util.Hashtable;

public class FlatMenuNode extends MenuNode
{
    public MenuItem cloneNode(final String s, final Hashtable hashtable) {
        return new FlatMenu(s, hashtable);
    }
    
    public FlatMenuNode(final String s) {
        super(s);
    }
}
