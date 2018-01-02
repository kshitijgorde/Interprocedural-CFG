// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Rectangle;
import java.awt.Dimension;
import borland.jbcl.model.ItemPaintSite;
import java.awt.Graphics;
import java.awt.Insets;

public class WrappedTextItemPainter extends TextItemPainter
{
    protected int minWidth;
    
    public WrappedTextItemPainter() {
        this.minWidth = 0;
    }
    
    public WrappedTextItemPainter(final int alignment) {
        super(alignment);
        this.minWidth = 0;
    }
    
    public WrappedTextItemPainter(final int alignment, final Insets margins) {
        super(alignment, margins);
        this.minWidth = 0;
    }
    
    public WrappedTextItemPainter(final Insets margins, final int alignment, final int minWidth) {
        super(alignment, margins);
        this.minWidth = 0;
        this.minWidth = minWidth;
    }
    
    public void setMinWidth(final int minWidth) {
        this.minWidth = minWidth;
    }
    
    public int getMinWidth() {
        return this.minWidth;
    }
    
    public Dimension getPreferredSize(final Object object, final Graphics g, final int state, final ItemPaintSite site) {
        final Dimension textDim = new TextWrapper(g.getFont(), this.getText(object), super.alignment, (this.minWidth == 0) ? 300 : this.minWidth).getSize(g);
        Insets m = (site != null) ? site.getItemMargins() : super.margins;
        if (m == null) {
            m = super.margins;
        }
        return new Dimension(textDim.width + m.left + m.right, textDim.height + m.top + m.bottom);
    }
    
    public void paint(final Object object, final Graphics g, final Rectangle r, final int state, final ItemPaintSite site) {
        final Font of = g.getFont();
        final Color oc = g.getColor();
        Font f = (site != null) ? site.getFont() : g.getFont();
        if (f == null) {
            f = g.getFont();
        }
        g.setFont(f);
        int a = (site != null) ? site.getAlignment() : this.getAlignment();
        if (a == 0) {
            a = this.getAlignment();
        }
        Insets m = (site != null) ? site.getItemMargins() : this.getMargins();
        if (m == null) {
            m = this.getMargins();
        }
        Color bg = (site != null) ? site.getBackground() : this.getBackground();
        if (bg == null) {
            bg = this.getBackground();
        }
        if (super.paintBackground) {
            g.setColor(bg);
            g.fillRect(r.x, r.y, r.width, r.height);
        }
        Color fg = (site != null) ? site.getForeground() : this.getForeground();
        if (fg == null) {
            fg = this.getForeground();
        }
        final int width = r.width - m.left - m.right;
        final TextWrapper textWrapper = new TextWrapper(f, this.getText(object), a, (this.minWidth > width) ? this.minWidth : width);
        final Dimension textDim = textWrapper.getSize(g);
        if ((state & 0x1) != 0x0) {
            g.setColor(SystemColor.controlLtHighlight);
            textWrapper.paint(g, r.x + m.left + 1, r.y + m.top + 1, r.width - m.left - m.right, r.height - m.top - m.bottom);
            g.setColor(SystemColor.controlShadow);
        }
        else {
            g.setColor(fg);
        }
        textWrapper.paint(g, r.x + m.left, r.y + m.top, r.width - m.left - m.right, r.height - m.top - m.bottom);
        g.setFont(of);
        g.setColor(oc);
    }
}
