// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.JComponent;

public class JFastLabel extends JComponent
{
    private String m_text;
    private int m_valign;
    private int m_halign;
    private int m_fheight;
    private boolean m_quality;
    
    public JFastLabel() {
        this(null);
    }
    
    public JFastLabel(final String text) {
        this.m_valign = 1;
        this.m_halign = 2;
        this.m_fheight = -1;
        this.m_quality = false;
        this.m_text = text;
        this.setFont(this.getFont());
    }
    
    public String getText() {
        return this.m_text;
    }
    
    public void setText(final String text) {
        this.m_text = text;
        this.repaint();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.m_fheight = -1;
    }
    
    public void setVerticalAlignment(final int valign) {
        this.m_valign = valign;
        this.m_fheight = -1;
    }
    
    public void setHorizontalAlignment(final int halign) {
        this.m_halign = halign;
    }
    
    public boolean getHighQuality() {
        return this.m_quality;
    }
    
    public void setHighQuality(final boolean quality) {
        this.m_quality = quality;
    }
    
    public void paintComponent(final Graphics graphics) {
        final Insets insets = this.getInsets();
        final int n = this.getWidth() - insets.left - insets.right;
        final int n2 = this.getHeight() - insets.top - insets.bottom;
        if (this.m_fheight == -1) {
            final FontMetrics fontMetrics = graphics.getFontMetrics(this.getFont());
            if (this.m_valign == 3) {
                this.m_fheight = fontMetrics.getDescent();
            }
            else if (this.m_valign == 1) {
                this.m_fheight = fontMetrics.getAscent();
            }
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(insets.left, insets.top, n, n2);
        if (this.m_text == null) {
            return;
        }
        graphics.setFont(this.getFont());
        graphics.setColor(this.getForeground());
        int n3;
        if (this.m_valign == 3) {
            n3 = n2 - this.m_fheight - insets.bottom;
        }
        else {
            n3 = insets.top + this.m_fheight;
        }
        int left = 0;
        switch (this.m_halign) {
            case 4: {
                left = n - insets.right - graphics.getFontMetrics(this.getFont()).stringWidth(this.m_text);
                break;
            }
            case 0: {
                left = insets.left + n / 2 - graphics.getFontMetrics(this.getFont()).stringWidth(this.m_text) / 2;
                break;
            }
            default: {
                left = insets.left;
                break;
            }
        }
        if (this.m_quality) {
            ((Graphics2D)graphics).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        }
        graphics.drawString(this.m_text, left, n3);
    }
}
