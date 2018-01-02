import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class pp implements ActionListener
{
    private final var n;
    
    pp(final var n) {
        this.n = n;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private String[] j() {
        String[] b;
        final String[] array = b = var._(this.n).b();
        if (var.a(this.n) != null && var.a(this.n).length() > 0) {
            b = new String[array.length + 1];
            System.arraycopy(array, 0, b, 0, array.length);
            b[b.length - 1] = var.a(this.n);
        }
        return b;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        var._(this.n).a(true);
        synchronized (this.n) {
            this.n.setEnabled(false);
            this.n.setCursor(Cursor.getPredefinedCursor(3));
            if (actionEvent.getSource() == var.j(this.n)) {
                var.a(this.n, this.j());
            }
            else if (actionEvent.getSource() == var.k(this.n)) {
                for (int i = 0; i < var.a(this.n).getItemCount(); ++i) {
                    var.a(this.n).deselect(i);
                }
                var.a(this.n).removeAll();
            }
            var.a(this.n, (String)null);
            var._(this.n).setText("");
            var._(this.n).removeAll();
            var._(this.n)._().b(var._(this.n).b());
            var._(this.n)._().a(var.a(this.n));
            var.b(this.n).b();
            this.n.setCursor(Cursor.getPredefinedCursor(0));
            this.n.setEnabled(true);
            var.b(this.n).repaint();
            var._(this.n).a(!var.a(this.n).getState());
        }
    }
}
