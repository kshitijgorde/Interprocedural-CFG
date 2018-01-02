// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.internal.SWTEventListener;

public interface MovementListener extends SWTEventListener
{
    void getNextOffset(final MovementEvent p0);
    
    void getPreviousOffset(final MovementEvent p0);
}
