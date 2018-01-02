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

class yp implements ItemListener, FocusListener, ActionListener, MouseListener
{
    private final var n;
    
    yp(final var n) {
        this.n = n;
    }
    
    public void itemStateChanged(final ItemEvent itemEvent) {
        var._(this.n).a(true);
        new Cp(this, itemEvent).start();
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (!(focusEvent.getSource() instanceof Choice)) {
            return;
        }
        var._(this.n, (Choice)focusEvent.getSource());
        var.b(this.n);
    }
    
    public void focusLost(final FocusEvent focusEvent) {
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.n) {
            if (actionEvent.getSource() == var._(this.n)) {
                var.m(this.n);
            }
            else if (actionEvent.getSource() == var.a(this.n)) {
                var._(this.n, false);
            }
            else if (actionEvent.getSource() == var.b(this.n)) {
                var._(this.n, true);
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == var._(this.n)) {
            var._(this.n, var._(this.n));
        }
        else if (mouseEvent.getSource() == var.a(this.n)) {
            var._(this.n, var.a(this.n));
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getSource() == var._(this.n)) {
            var._(this.n, var._(this.n));
        }
        else if (mouseEvent.getSource() == var.a(this.n)) {
            var._(this.n, var.a(this.n));
        }
        if (mouseEvent.isPopupTrigger()) {
            o o = null;
            if (mouseEvent.getSource() == var._(this.n)) {
                o = var._(this.n).b();
                var._(this.n, var._(this.n));
            }
            else if (mouseEvent.getSource() == var.a(this.n)) {
                o = var._(this.n)._();
                var._(this.n, var.a(this.n));
            }
            var._(this.n).setEnabled(o != null && o instanceof n);
            var.b(this.n).setEnabled(o != null && o instanceof n);
            var.b(this.n).show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (!var.b(this.n)) {
            return;
        }
        if (mouseEvent.isPopupTrigger()) {
            o o = null;
            if (mouseEvent.getSource() == var._(this.n)) {
                o = var._(this.n).b();
                var._(this.n, var._(this.n));
            }
            else if (mouseEvent.getSource() == var.a(this.n)) {
                o = var._(this.n)._();
                var._(this.n, var.a(this.n));
            }
            var._(this.n).setEnabled(o != null && o instanceof n);
            var.b(this.n).setEnabled(o != null && o instanceof n);
            var.b(this.n).show(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    static var a(final yp yp) {
        return yp.n;
    }
}
