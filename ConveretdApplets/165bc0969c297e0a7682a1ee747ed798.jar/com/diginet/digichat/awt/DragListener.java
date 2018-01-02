// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Component;
import java.awt.Point;

public interface DragListener
{
    Point getPoint();
    
    void startDrag(final Component p0, final int p1, final int p2);
    
    void endDrag(final Component p0, final int p1, final int p2);
}
