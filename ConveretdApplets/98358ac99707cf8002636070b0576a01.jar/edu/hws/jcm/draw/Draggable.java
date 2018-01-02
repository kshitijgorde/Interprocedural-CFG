// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.draw;

import java.awt.event.MouseEvent;

public interface Draggable
{
    boolean startDrag(final MouseEvent p0);
    
    void continueDrag(final MouseEvent p0);
    
    void finishDrag(final MouseEvent p0);
}
