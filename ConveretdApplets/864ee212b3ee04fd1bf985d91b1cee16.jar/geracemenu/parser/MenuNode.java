// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.Menu;
import geracemenu.MenuItem;
import java.util.Hashtable;

public class MenuNode extends ItemNode
{
    public MenuItem cloneNode(final String s, final Hashtable hashtable) {
        return new Menu(s, hashtable);
    }
    
    public MenuNode(final String s) {
        super(s);
    }
}
