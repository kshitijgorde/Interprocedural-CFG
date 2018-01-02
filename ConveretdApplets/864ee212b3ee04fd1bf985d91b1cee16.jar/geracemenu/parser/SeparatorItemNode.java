// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.SeparatorItem;
import geracemenu.MenuItem;
import java.util.Hashtable;

public class SeparatorItemNode extends ItemNode
{
    public MenuItem cloneNode(final String s, final Hashtable hashtable) {
        return new SeparatorItem(hashtable);
    }
    
    public SeparatorItemNode(final String s) {
        super(s);
    }
}
