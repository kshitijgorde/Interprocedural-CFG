// 
// Decompiled by Procyon v0.5.30
// 

package ManPack;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class Applet1_numPointsSlider_mouseAdapter extends MouseAdapter
{
    Applet1 adaptee;
    
    Applet1_numPointsSlider_mouseAdapter(final Applet1 adaptee) {
        this.adaptee = adaptee;
    }
    
    public void mousePressed(final MouseEvent e) {
        this.adaptee.numPointsSlider_mousePressed(e);
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.adaptee.numPointsSlider_mouseReleased(e);
    }
}
