import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class Bj implements ActionListener
{
    private final n ta;
    
    Bj(final n ta) {
        this.ta = ta;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.ta) {
            try {
                this.ta.b(false);
                final String a = ((t)actionEvent.getSource()).a();
                final implements b = n.a(this.ta).b(a);
                if (((t)actionEvent.getSource()).getState()) {
                    if (b.i()) {
                        if (b.b(n.b(this.ta), n.a(this.ta).a())) {
                            n.a(this.ta).d(a);
                            n._(this.ta)._();
                        }
                        else {
                            ((t)actionEvent.getSource()).setState(false);
                        }
                    }
                    else {
                        n.a(this.ta).d(a);
                        n._(this.ta)._();
                    }
                }
                else if (b.i()) {
                    if (b.b(n.b(this.ta), n.a(this.ta).a())) {
                        ((t)actionEvent.getSource()).setState(true);
                        n._(this.ta)._();
                    }
                    else {
                        n.a(this.ta).e(a);
                        n._(this.ta)._();
                    }
                }
                else {
                    n.a(this.ta).e(a);
                    n._(this.ta)._();
                }
            }
            finally {
                n._(this.ta).repaint();
                this.ta.b(true);
            }
        }
    }
}
