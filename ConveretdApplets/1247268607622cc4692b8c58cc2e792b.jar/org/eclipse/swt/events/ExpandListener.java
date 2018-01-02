// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.internal.SWTEventListener;

public interface ExpandListener extends SWTEventListener
{
    void itemCollapsed(final ExpandEvent p0);
    
    void itemExpanded(final ExpandEvent p0);
}
