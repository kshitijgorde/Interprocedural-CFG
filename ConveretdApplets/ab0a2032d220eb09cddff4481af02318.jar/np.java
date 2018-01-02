import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class np implements ActionListener
{
    private final var n;
    
    np(final var n) {
        this.n = n;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.n) {
            if (actionEvent.getSource() == var.V(this.n)) {
                if (var.a(this.n) != null) {
                    new uq(this).start();
                }
                return;
            }
            if (actionEvent.getSource() == var.W(this.n)) {
                if (var.a(this.n) != null) {
                    new vq(this).start();
                }
                return;
            }
            if (actionEvent.getSource() == var.X(this.n)) {
                if (var.X(this.n).getState()) {
                    var.a(this.n).setVisible(false);
                    var._(this.n).setVisible(false);
                    this.n.invalidate();
                    this.n.validate();
                }
                else {
                    var.a(this.n).setVisible(true);
                    var._(this.n).setVisible(var._(this.n));
                    this.n.invalidate();
                    this.n.validate();
                }
            }
        }
    }
    
    static var _(final np np) {
        return np.n;
    }
}
