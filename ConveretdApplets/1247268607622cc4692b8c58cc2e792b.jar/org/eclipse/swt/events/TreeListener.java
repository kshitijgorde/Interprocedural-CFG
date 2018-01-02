// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.internal.SWTEventListener;

public interface TreeListener extends SWTEventListener
{
    void treeCollapsed(final TreeEvent p0);
    
    void treeExpanded(final TreeEvent p0);
}
