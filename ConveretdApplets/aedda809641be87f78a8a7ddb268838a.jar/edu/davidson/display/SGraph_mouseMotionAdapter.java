// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class SGraph_mouseMotionAdapter extends MouseMotionAdapter
{
    SGraph adaptee;
    
    SGraph_mouseMotionAdapter(final SGraph adaptee) {
        this.adaptee = adaptee;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.adaptee.sGraph_mouseMoved(mouseEvent);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.adaptee.sGraph_mouseDragged(mouseEvent);
    }
}
