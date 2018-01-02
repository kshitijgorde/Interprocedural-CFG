// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.console.navtree;

import org.jboss.console.manager.interfaces.TreeNodeMenuEntry;
import org.jboss.console.manager.interfaces.TreeAction;

public interface NodeWrapper
{
    Object getChild(final int p0);
    
    int getChildCount();
    
    int getIndexOfChild(final Object p0);
    
    boolean isLeaf();
    
    String getIconUrl();
    
    TreeAction getAssociatedAction();
    
    String getDescription();
    
    TreeNodeMenuEntry[] getMenuEntries();
    
    String getPath();
}
