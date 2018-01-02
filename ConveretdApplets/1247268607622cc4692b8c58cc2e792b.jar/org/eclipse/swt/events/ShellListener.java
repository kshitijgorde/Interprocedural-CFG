// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.events;

import org.eclipse.swt.internal.SWTEventListener;

public interface ShellListener extends SWTEventListener
{
    void shellActivated(final ShellEvent p0);
    
    void shellClosed(final ShellEvent p0);
    
    void shellDeactivated(final ShellEvent p0);
    
    void shellDeiconified(final ShellEvent p0);
    
    void shellIconified(final ShellEvent p0);
}
