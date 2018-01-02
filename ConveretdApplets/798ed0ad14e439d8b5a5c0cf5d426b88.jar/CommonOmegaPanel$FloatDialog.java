import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

class CommonOmegaPanel$FloatDialog
{
    Dialog I;
    CommonOmegaPanel add;
    
    CommonOmegaPanel$FloatDialog(final CommonOmegaPanel add, final Component component, final String s) {
        this.add = add;
        this.I = this.createDialog(component, s);
    }
    
    private Dialog createDialog(final Component component, final String s) {
        final Dialog dialog = new Dialog(this.add.getMainFrame(), s);
        dialog.setLayout(new GridLayout(1, 1));
        dialog.add(new WToolTipPanel(component));
        dialog.pack();
        dialog.addWindowListener(new WindowListenerWithClose());
        final Dimension size = dialog.getSize();
        if (size.width < 30 && size.height < 30) {
            dialog.setSize(500, 120);
        }
        return dialog;
    }
    
    final boolean I() {
        return this.I.isVisible();
    }
    
    final void I(final boolean b, final CommonOmegaPanel add) {
        if (b) {
            if (add != this.add) {
                this.add = add;
                final Component component = this.I.getComponent(0);
                this.I.dispose();
                final Dialog i = this.I;
                (this.I = this.createDialog(component, i.getTitle())).setSize(i.getSize());
                this.I.setLocation(i.getLocation());
            }
            this.I.show();
        }
        else {
            this.I.dispose();
        }
    }
}
