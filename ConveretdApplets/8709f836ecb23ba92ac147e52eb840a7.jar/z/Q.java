// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

final class Q implements KeyListener
{
    private /* synthetic */ af a;
    
    Q(final af af, final af a) {
        this.a = a;
    }
    
    public final void keyReleased(final KeyEvent keyEvent) {
    }
    
    public final void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 27) {
            this.a.setVisible(false);
        }
    }
    
    public final void keyTyped(final KeyEvent keyEvent) {
    }
}
