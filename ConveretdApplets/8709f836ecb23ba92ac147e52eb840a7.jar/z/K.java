// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

public final class K extends JLabel implements MouseListener
{
    private I b;
    String a;
    
    public K() {
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.addMouseListener(this);
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        if (this.b != null) {
            this.b.a(new T(this, this.a));
        }
    }
    
    public final void a(final I b) {
        this.b = b;
    }
}
