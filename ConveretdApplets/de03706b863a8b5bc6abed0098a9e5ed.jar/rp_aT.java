import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JButton;

// 
// Decompiled by Procyon v0.5.30
// 

final class rp_aT extends JButton
{
    public rp_aT(final rp_fX rp_fX, final Color background) {
        this.setText("");
        final Dimension minimumSize = new Dimension(22, 22);
        this.setSize(minimumSize);
        this.setPreferredSize(minimumSize);
        this.setMinimumSize(minimumSize);
        this.setBorderPainted(true);
        this.setBackground(background);
    }
}
