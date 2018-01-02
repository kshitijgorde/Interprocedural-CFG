// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class cn implements MouseListener
{
    private final bq a;
    
    cn(final bq a) {
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
        bq.a(this.a).setEnabled(false);
        bq.a(this.a).setEnabled(true);
    }
}
