// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.EmbossedItem;
import geracemenu.MenuItem;
import java.util.Hashtable;

public class EmbossItemNode extends ItemNode
{
    public MenuItem cloneNode(final String s, final Hashtable hashtable) {
        return new EmbossedItem(s, hashtable);
    }
    
    public EmbossItemNode(final String s) {
        super(s);
    }
}
