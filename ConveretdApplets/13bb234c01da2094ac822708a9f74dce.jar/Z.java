import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

// 
// Decompiled by Procyon v0.5.30
// 

public class Z extends DefaultTableCellRenderer
{
    public Z(final int horizontalAlignment) {
        this.setHorizontalAlignment(horizontalAlignment);
    }
    
    public void setBorder(final Border border) {
        if (!(this.getBorder() instanceof CompoundBorder)) {
            super.setBorder(new CompoundBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3), this.getBorder()));
        }
    }
    
    public Component getTableCellRendererComponent(final JTable table, final Object o, final boolean b, final boolean b2, final int n, final int n2) {
        super.getTableCellRendererComponent(table, o, b, b2, n, n2);
        if (this.getHorizontalAlignment() == 4) {
            final int n3 = (int)(table.getColumnModel().getColumn(n2).getWidth() - table.getIntercellSpacing().getWidth());
            final Insets borderInsets = this.getBorder().getBorderInsets(this);
            final int n4 = n3 - (borderInsets.left + borderInsets.right);
            final String text = this.getText();
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            if (fontMetrics.stringWidth(text) > n4) {
                final String s = "\u2026";
                int stringWidth = fontMetrics.stringWidth(s);
                int i;
                for (i = text.length() - 1; i > 0; --i) {
                    stringWidth += fontMetrics.charWidth(text.charAt(i));
                    if (stringWidth > n4) {
                        break;
                    }
                }
                this.setText(s + text.substring(i + 1));
            }
        }
        return this;
    }
}
