// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

class SoundCanvas_this_mouseAdapter extends MouseAdapter
{
    SoundCanvas adaptee;
    
    SoundCanvas_this_mouseAdapter(final SoundCanvas adaptee) {
        this.adaptee = adaptee;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.adaptee.this_mousePressed(mouseEvent);
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.adaptee.this_mouseReleased(mouseEvent);
    }
}
