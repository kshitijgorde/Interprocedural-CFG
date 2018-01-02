// 
// Decompiled by Procyon v0.5.30
// 

package soundOut;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

class SoundCanvas_this_mouseMotionAdapter extends MouseMotionAdapter
{
    SoundCanvas adaptee;
    
    SoundCanvas_this_mouseMotionAdapter(final SoundCanvas adaptee) {
        this.adaptee = adaptee;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.adaptee.this_mouseMoved(mouseEvent);
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.adaptee.this_mouseDragged(mouseEvent);
    }
}
