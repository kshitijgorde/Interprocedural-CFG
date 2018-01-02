// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.EventObject;
import javax.swing.BorderFactory;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public final class bd extends JPanel implements MouseListener
{
    private I a;
    private String b;
    private Color c;
    private Color d;
    
    public bd() {
        this.setCursor(Cursor.getPredefinedCursor(12));
        this.addMouseListener(this);
    }
    
    public final void a(final String b) {
        this.b = b;
    }
    
    public final void a(final Color c) {
        this.c = c;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.d = this.getBackground();
        this.setBackground(this.c);
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.setBackground(this.d);
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.repaint();
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.setBorder(BorderFactory.createRaisedBevelBorder());
        this.repaint();
        if (this.a != null) {
            this.a.a(new T(this, this.b));
        }
    }
    
    public final void a(final I a) {
        this.a = a;
    }
}
