// 
// Decompiled by Procyon v0.5.30
// 

package com.nuspectra.viewer;

interface SessionListener
{
    void stateChanged(final int p0, final int p1, final int p2);
    
    void cameraChanged(final int p0, final int p1, final int p2, final int p3);
    
    void moveRequested(final int p0, final int p1, final int p2, final int p3);
    
    void setSession(final ControlSession p0);
}
