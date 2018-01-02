// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.SWTEventListener;

public interface AccessibleListener extends SWTEventListener
{
    void getName(final AccessibleEvent p0);
    
    void getHelp(final AccessibleEvent p0);
    
    void getKeyboardShortcut(final AccessibleEvent p0);
    
    void getDescription(final AccessibleEvent p0);
}
