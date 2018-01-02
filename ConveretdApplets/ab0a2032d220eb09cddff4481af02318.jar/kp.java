import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class kp implements ItemListener, ActionListener, MouseListener, KeyListener
{
    private final var n;
    
    kp(final var n) {
        this.n = n;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        synchronized (this.n) {
            var._(this.n).a(true);
            new zq(this, itemEvent).start();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            synchronized (this.n) {
                var._(this.n).a(true);
                new Aq(this).start();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.n) {
            if (!var.a(this.n).isMultipleMode()) {
                return;
            }
            var._(this.n).a(true);
            new Bq(this).start();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            var.l(this.n).setEnabled(var.a(this.n).isMultipleMode() && var.a(this.n) != null);
            var.j(this.n).setEnabled(var.a(this.n) != null);
            var._(this.n).show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            var.l(this.n).setEnabled(var.a(this.n).isMultipleMode() && var.a(this.n) != null);
            var.j(this.n).setEnabled(var.a(this.n) != null);
            var._(this.n).show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    static var b(final kp kp) {
        return kp.n;
    }
}
