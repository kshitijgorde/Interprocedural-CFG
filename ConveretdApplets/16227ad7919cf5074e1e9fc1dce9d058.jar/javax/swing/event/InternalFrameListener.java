// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.event;

import java.util.EventListener;

public interface InternalFrameListener extends EventListener
{
    void internalFrameActivated(final InternalFrameEvent p0);
    
    void internalFrameClosed(final InternalFrameEvent p0);
    
    void internalFrameClosing(final InternalFrameEvent p0);
    
    void internalFrameDeactivated(final InternalFrameEvent p0);
    
    void internalFrameDeiconified(final InternalFrameEvent p0);
    
    void internalFrameIconified(final InternalFrameEvent p0);
    
    void internalFrameOpened(final InternalFrameEvent p0);
}
