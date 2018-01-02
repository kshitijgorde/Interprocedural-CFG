// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

public final class F extends MouseAdapter implements MouseListener
{
    private Z a;
    
    public F(final Z a) {
        this.a = a;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        Z.a(this.a, true);
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        Z.a(this.a, false);
    }
}
