import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class oj implements ActionListener
{
    private final n ta;
    
    oj(final n ta) {
        this.ta = ta;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private String[] m() {
        String[] a;
        final String[] array = a = n.b(this.ta).a();
        if (n.a(this.ta) != null && n.a(this.ta).length() > 0) {
            a = new String[array.length + 1];
            System.arraycopy(array, 0, a, 0, array.length);
            a[a.length - 1] = n.a(this.ta);
        }
        return a;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.ta.b(false);
        synchronized (this.ta) {
            try {
                if (actionEvent.getSource() == n.m(this.ta)) {
                    final String[] m = this.m();
                    for (int i = 0; i < m.length; ++i) {
                        n._(this.ta).put(m[i], n.a(this.ta)._()._(m[i]));
                    }
                }
                else if (actionEvent.getSource() == n.n(this.ta)) {
                    final String[] j = this.m();
                    for (int k = 0; k < j.length; ++k) {
                        n._(this.ta).remove(j[k]);
                    }
                    n.b(this.ta, j, "");
                    if ("".equals(n.b(this.ta).get(n.b(this.ta).getSelectedItem()))) {
                        n._(this.ta, j);
                    }
                    n.b(this.ta).removeAll();
                    n.a(this.ta)._().a(n.b(this.ta).a());
                    n.a(this.ta)._().a(n.a(this.ta));
                }
                else if (actionEvent.getSource() == n.c(this.ta)) {
                    n._(this.ta).clear();
                    n.b(this.ta, null, "");
                    if ("".equals(n.b(this.ta).get(n.b(this.ta).getSelectedItem()))) {
                        for (int l = 0; l < n.b(this.ta).getItemCount(); ++l) {
                            n.b(this.ta).deselect(l);
                        }
                        n.b(this.ta).removeAll();
                    }
                    n.b(this.ta).removeAll();
                    n.a(this.ta)._().a(n.b(this.ta).a());
                    n.a(this.ta)._().a(n.a(this.ta));
                }
            }
            finally {
                n._(this.ta);
                n._(this.ta)._();
                n._(this.ta).repaint();
                this.ta.b(true);
            }
        }
    }
}
