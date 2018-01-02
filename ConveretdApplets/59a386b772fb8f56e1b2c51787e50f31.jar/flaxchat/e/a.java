// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.e;

import java.awt.event.KeyEvent;
import java.awt.Component;
import java.awt.event.KeyAdapter;

final class a extends KeyAdapter
{
    private final Component a;
    private final Component b;
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() != 10) {
            return;
        }
        if (keyEvent.getSource() != this.b) {
            return;
        }
        if (keyEvent.isConsumed()) {
            return;
        }
        this.a.requestFocus();
    }
    
    a(final Component b, final Component a) {
        this.b = b;
        this.a = a;
    }
}
