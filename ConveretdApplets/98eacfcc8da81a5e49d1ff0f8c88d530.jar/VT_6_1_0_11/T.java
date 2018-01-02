// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import java.awt.Window;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public final class T extends JPanel implements MouseListener, MouseMotionListener
{
    private boolean a;
    private Window b;
    private JComponent c;
    
    public T(final JComponent c) {
        this.a = false;
        this.c = c;
        this.setOpaque(this.a = false);
        this.setFocusable(false);
    }
    
    public final void a(final Window b) {
        this.b = b;
    }
    
    public final void a(final boolean b) {
        if (!this.a) {
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
            this.a = true;
        }
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        Toolkit.getDefaultToolkit().beep();
        if (this.b != null && this.b.isVisible()) {
            this.b.setLocationRelativeTo(this.c);
            this.b.toFront();
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public final void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public final void mouseMoved(final MouseEvent mouseEvent) {
    }
}
