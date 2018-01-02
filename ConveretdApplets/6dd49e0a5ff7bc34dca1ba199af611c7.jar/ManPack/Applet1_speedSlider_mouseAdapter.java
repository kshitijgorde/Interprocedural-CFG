// 
// Decompiled by Procyon v0.5.30
// 

package ManPack;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class Applet1_speedSlider_mouseAdapter extends MouseAdapter
{
    Applet1 adaptee;
    
    Applet1_speedSlider_mouseAdapter(final Applet1 adaptee) {
        this.adaptee = adaptee;
    }
    
    public void mouseReleased(final MouseEvent e) {
        this.adaptee.speedSlider_mouseReleased(e);
    }
    
    public void mousePressed(final MouseEvent e) {
        this.adaptee.speedSlider_mousePressed(e);
    }
}
