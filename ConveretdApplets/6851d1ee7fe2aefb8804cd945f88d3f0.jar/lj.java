import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class lj implements ActionListener
{
    private final n ta;
    
    lj(final n ta) {
        this.ta = ta;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.ta) {
            if (actionEvent.getSource() == n.W(this.ta)) {
                new Pj(this).start();
                return;
            }
            if (actionEvent.getSource() == n.X(this.ta)) {
                new Qj(this).start();
                return;
            }
            if (actionEvent.getSource() == n.Y(this.ta)) {
                new Rj(this).start();
                return;
            }
            if (actionEvent.getSource() == n.U(this.ta)) {
                new Sj(this).start();
                return;
            }
            if (actionEvent.getSource() == n.Z(this.ta)) {
                if (n.Z(this.ta).getState()) {
                    n.a(this.ta).setVisible(false);
                    n.a(this.ta).setVisible(false);
                    this.ta.invalidate();
                    this.ta.validate();
                }
                else {
                    n.a(this.ta).setVisible(true);
                    n.a(this.ta).setVisible(n.b(this.ta));
                    this.ta.invalidate();
                    this.ta.validate();
                }
            }
        }
    }
    
    static n a(final lj lj) {
        return lj.ta;
    }
}
