import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class Ap implements ActionListener
{
    private final var n;
    
    Ap(final var n) {
        this.n = n;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        synchronized (this.n) {
            try {
                this.n.setEnabled(false);
                this.n.setCursor(Cursor.getPredefinedCursor(3));
                final String j = ((c)actionEvent.getSource()).j();
                if (((c)actionEvent.getSource()).getState()) {
                    var._(this.n).k(j);
                    var.b(this.n).b();
                }
                else {
                    var._(this.n).l(j);
                    var.b(this.n).b();
                }
            }
            finally {
                this.n.setCursor(Cursor.getPredefinedCursor(0));
                this.n.setEnabled(true);
                var.b(this.n).repaint();
            }
        }
    }
}
