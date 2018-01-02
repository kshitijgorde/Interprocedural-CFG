// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelListener;

public class az implements MouseWheelListener, KeyListener
{
    l a;
    
    public void a(final l a) {
        this.a = a;
        a.void.addMouseWheelListener(this);
    }
    
    public void mouseWheelMoved(final MouseWheelEvent mouseWheelEvent) {
        this.a.a(mouseWheelEvent.getWheelRotation());
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
}
