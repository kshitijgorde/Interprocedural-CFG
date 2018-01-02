import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;

// 
// Decompiled by Procyon v0.5.30
// 

class sea implements ActionListener
{
    private final super da;
    
    sea(final super da) {
        this.da = da;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    private String[] l() {
        String[] _;
        final String[] array = _ = super.b(this.da)._();
        if (super.a(this.da) != null && super.a(this.da).length() > 0) {
            _ = new String[array.length + 1];
            System.arraycopy(array, 0, _, 0, array.length);
            _[_.length - 1] = super.a(this.da);
        }
        return _;
    }
    
    public void actionPerformed(final ActionEvent actionEvent) {
        this.da.a(false);
        synchronized (this.da) {
            try {
                if (actionEvent.getSource() == super.g(this.da)) {
                    super.a(this.da, this.l());
                }
                else if (actionEvent.getSource() == super.h(this.da)) {
                    for (int i = 0; i < super.b(this.da).getItemCount(); ++i) {
                        super.b(this.da).deselect(i);
                    }
                    super.b(this.da).removeAll();
                }
                super._(this.da, (String)null);
                super.a(this.da).setText("");
                super.b(this.da).removeAll();
                super.b(this.da)._().a(super.b(this.da)._());
                super.b(this.da)._()._(super.a(this.da));
            }
            finally {
                super._(this.da).b();
                super._(this.da).repaint();
                this.da.a(true);
            }
        }
    }
}
