// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.internal.SWTEventListener;

public interface ControlListener extends SWTEventListener
{
    void controlMoved(final ControlEvent p0);
    
    void controlResized(final ControlEvent p0);
}
