// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.ListItem;
import geracemenu.MenuItem;
import java.util.Hashtable;

public class ListItemNode extends ItemNode
{
    public MenuItem cloneNode(final String s, final Hashtable hashtable) {
        return new ListItem(s, hashtable);
    }
    
    public ListItemNode(final String s) {
        super(s);
    }
}
