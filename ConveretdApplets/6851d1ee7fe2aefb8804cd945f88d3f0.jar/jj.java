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

class jj implements ItemListener, ActionListener, MouseListener, KeyListener
{
    private final n ta;
    
    jj(final n ta) {
        this.ta = ta;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        synchronized (this.ta) {
            this.ta.b(false);
            new Tj(this, itemEvent).start();
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            synchronized (this.ta) {
                this.ta.b(false);
                new Uj(this).start();
            }
        }
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.ta) {
            if (!n.b(this.ta).isMultipleMode()) {
                return;
            }
            this.ta.b(false);
            new Vj(this).start();
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            n.d(this.ta).setEnabled(n.b(this.ta).isMultipleMode() && n.a(this.ta) != null);
            n.m(this.ta).setEnabled(n.g(this.ta) && n.a(this.ta) != null && !"".equals(n.b(this.ta).get(n.b(this.ta).getSelectedItem())));
            n.n(this.ta).setEnabled(n.g(this.ta) && n.a(this.ta) != null);
            n.c(this.ta).setEnabled(n.g(this.ta));
            n.a(this.ta).show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.isPopupTrigger()) {
            n.d(this.ta).setEnabled(n.b(this.ta).isMultipleMode() && n.a(this.ta) != null);
            n.m(this.ta).setEnabled(n.g(this.ta) && n.a(this.ta) != null && !"".equals(n.b(this.ta).get(n.b(this.ta).getSelectedItem())));
            n.n(this.ta).setEnabled(n.g(this.ta) && n.a(this.ta) != null);
            n.c(this.ta).setEnabled(n.g(this.ta));
            n.a(this.ta).show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    static n _(final jj jj) {
        return jj.ta;
    }
}
