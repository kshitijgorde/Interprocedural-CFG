import java.awt.Color;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.JLabel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fF extends JLabel implements ListCellRenderer
{
    public rp_fF() {
        this.setOpaque(true);
    }
    
    public final Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        if (o != null) {
            this.setText(o.toString());
        }
        this.setForeground(b ? rp_aJ.r : Color.BLACK);
        this.setBackground(b ? rp_aJ.w : Color.WHITE);
        return this;
    }
}
