import java.awt.MenuComponent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;

// 
// Decompiled by Procyon v0.5.30
// 

class zj implements ItemListener, FocusListener, ActionListener, MouseListener
{
    private final n ta;
    
    zj(final n ta) {
        this.ta = ta;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        this.ta.b(false);
        new Dj(this, itemEvent).start();
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (!(focusEvent.getSource() instanceof Choice)) {
            return;
        }
        n.b(this.ta, (Choice)focusEvent.getSource());
        n.n(this.ta);
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.ta) {
            if (actionEvent.getSource() == n.a(this.ta)) {
                n.f(this.ta);
            }
            else if (actionEvent.getSource() == n.b(this.ta)) {
                n.a(this.ta, false);
            }
            else if (actionEvent.getSource() == n._(this.ta)) {
                n.a(this.ta, true);
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == n._(this.ta)) {
            n.b(this.ta, n._(this.ta));
        }
        else if (mouseEvent.getSource() == n.d(this.ta)) {
            n.b(this.ta, n.d(this.ta));
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == n._(this.ta)) {
            n.b(this.ta, n._(this.ta));
        }
        else if (mouseEvent.getSource() == n.d(this.ta)) {
            n.b(this.ta, n.d(this.ta));
        }
        if (n._(this.ta) && mouseEvent.isPopupTrigger()) {
            implements implements1 = null;
            if (mouseEvent.getSource() == n._(this.ta)) {
                implements1 = n.a(this.ta).a();
                n.b(this.ta, n._(this.ta));
                n.d(this.ta).remove(n._(this.ta));
                n._(this.ta).add(n._(this.ta));
            }
            else if (mouseEvent.getSource() == n.d(this.ta)) {
                implements1 = n.a(this.ta).b();
                n.b(this.ta, n.d(this.ta));
                n._(this.ta).remove(n._(this.ta));
                n.d(this.ta).add(n._(this.ta));
            }
            n.a(this.ta).setEnabled(implements1 != null && implements1 instanceof di);
            n._(this.ta).setEnabled(implements1 != null && implements1 instanceof di);
            n._(this.ta).show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (n._(this.ta) && mouseEvent.isPopupTrigger()) {
            implements implements1 = null;
            if (mouseEvent.getSource() == n._(this.ta)) {
                implements1 = n.a(this.ta).a();
                n.b(this.ta, n._(this.ta));
                n.d(this.ta).remove(n._(this.ta));
                n._(this.ta).add(n._(this.ta));
            }
            else if (mouseEvent.getSource() == n.d(this.ta)) {
                implements1 = n.a(this.ta).b();
                n.b(this.ta, n.d(this.ta));
                n._(this.ta).remove(n._(this.ta));
                n.d(this.ta).add(n._(this.ta));
            }
            n.a(this.ta).setEnabled(implements1 != null && implements1 instanceof di);
            n._(this.ta).setEnabled(implements1 != null && implements1 instanceof di);
            n._(this.ta).show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    static n a(final zj zj) {
        return zj.ta;
    }
}
