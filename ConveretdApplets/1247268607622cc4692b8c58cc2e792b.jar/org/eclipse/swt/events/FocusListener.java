// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.internal.SWTEventListener;

public interface FocusListener extends SWTEventListener
{
    void focusGained(final FocusEvent p0);
    
    void focusLost(final FocusEvent p0);
}
