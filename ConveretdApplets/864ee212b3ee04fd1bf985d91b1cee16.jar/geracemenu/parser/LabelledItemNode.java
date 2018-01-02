// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.LabelledItem;
import geracemenu.MenuItem;
import java.util.Hashtable;

public class LabelledItemNode extends ItemNode
{
    public MenuItem cloneNode(final String s, final Hashtable hashtable) {
        return new LabelledItem(s, hashtable);
    }
    
    public LabelledItemNode(final String s) {
        super(s);
    }
}
