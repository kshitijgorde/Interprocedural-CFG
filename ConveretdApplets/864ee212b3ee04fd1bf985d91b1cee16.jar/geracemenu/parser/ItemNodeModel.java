// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu.parser;

import geracemenu.MenuItem;
import java.util.Hashtable;

public interface ItemNodeModel
{
    String getItemData();
    
    ItemNode[] getChildren();
    
    MenuItem cloneNode(final String p0, final Hashtable p1);
}
