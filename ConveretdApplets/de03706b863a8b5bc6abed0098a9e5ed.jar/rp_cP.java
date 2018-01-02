import java.awt.Dimension;
import javax.swing.JLabel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_cP extends JLabel
{
    public int a;
    
    public rp_cP(final String s) {
        super(s);
    }
    
    public final Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        return new Dimension((int)Math.min(this.a, preferredSize.getWidth()), (int)preferredSize.getHeight());
    }
}
