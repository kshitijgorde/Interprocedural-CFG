import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import javax.swing.JLabel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class rp_fw extends JLabel
{
    public rp_fw(final String toolTipText, final String s) {
        super("<html><a href=\"" + toolTipText + "\">" + s + "</a></html");
        this.addMouseListener(new rp_cC(toolTipText));
        this.setToolTipText(toolTipText);
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        this.setPreferredSize(new Dimension(fontMetrics.stringWidth(s) + 3, fontMetrics.getHeight()));
    }
}
