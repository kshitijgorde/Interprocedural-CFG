// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class co implements MouseListener
{
    private final bq a;
    
    co(final bq a) {
        this.a = a;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        bq.a(this.a).setEnabled(true);
        bq.a(this.a).setEnabled(false);
    }
}
