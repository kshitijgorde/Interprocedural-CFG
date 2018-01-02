// 
// Decompiled by Procyon v0.5.30
// 

package edu.davidson.display;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class SGraph_mouseAdapter extends MouseAdapter
{
    SGraph adaptee;
    
    SGraph_mouseAdapter(final SGraph adaptee) {
        this.adaptee = adaptee;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.adaptee.sGraph_mouseEntered(mouseEvent);
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.adaptee.sGraph_mouseExited(mouseEvent);
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.adaptee.sGraph_mousePressed(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.adaptee.sGraph_mouseReleased(mouseEvent);
    }
}
