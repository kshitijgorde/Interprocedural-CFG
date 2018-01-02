// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;

public final class X extends MouseAdapter implements MouseListener
{
    private Z a;
    
    public X(final Z a) {
        this.a = a;
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        Z.b(this.a, true);
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        Z.b(this.a, false);
    }
}
