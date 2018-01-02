// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventListener;

public interface AccessibleActionListener extends SWTEventListener
{
    void getActionCount(final AccessibleActionEvent p0);
    
    void doAction(final AccessibleActionEvent p0);
    
    void getDescription(final AccessibleActionEvent p0);
    
    void getKeyBinding(final AccessibleActionEvent p0);
    
    void getName(final AccessibleActionEvent p0);
}
