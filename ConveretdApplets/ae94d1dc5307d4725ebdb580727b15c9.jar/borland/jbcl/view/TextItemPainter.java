// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import borland.jbcl.util.InvalidFormatException;
import borland.jbcl.util.Variant;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import borland.jbcl.model.ItemPaintSite;
import java.awt.Graphics;
import java.awt.SystemColor;
import borland.jbcl.model.ItemFormatter;
import java.awt.Color;
import java.awt.Insets;
import borland.jbcl.model.ItemPainter;

public class TextItemPainter implements ItemPainter
{
    protected int alignment;
    protected Insets margins;
    protected Color foreground;
    protected Color background;
    protected boolean paintBackground;
    protected ItemFormatter formatter;
    
    public TextItemPainter() {
        this.alignment = 33;
        this.margins = new Insets(1, 1, 1, 1);
        this.foreground = SystemColor.controlText;
        this.paintBackground = true;
    }
    
    public TextItemPainter(final int alignment) {
        this();
        this.setAlignment(alignment);
    }
    
    public TextItemPainter(final Insets margins) {
        this();
        this.setMargins(margins);
    }
    
    public TextItemPainter(final ItemFormatter formatter) {
        this();
        this.setFormatter(formatter);
    }
    
    public TextItemPainter(final int alignment, final Insets margins) {
        this();
        this.setAlignment(alignment);
        this.setMargins(margins);
    }
    
    public TextItemPainter(final int alignment, final Insets margins, final ItemFormatter formatter) {
        this();
        this.setAlignment(alignment);
        this.setMargins(margins);
        this.setFormatter(formatter);
    }
    
    public void setAlignment(final int a) {
        if ((this.alignment & 0xF0) < 0 || (this.alignment & 0xF0) > 48) {
            throw new IllegalArgumentException(Res.format(1, new Integer[] { new Integer(this.alignment & 0xF0) }));
        }
        if ((this.alignment & 0xF) < 0 || (this.alignment & 0xF) > 3) {
            throw new IllegalArgumentException(Res.format(2, new Integer[] { new Integer(this.alignment & 0xF) }));
        }
        this.alignment = a;
    }
    
    public int getAlignment() {
        return this.alignment;
    }
    
    public void setMargins(final Insets margins) {
        this.margins = margins;
    }
    
    public Insets getMargins() {
        return this.margins;
    }
    
    public void setBackground(final Color background) {
        this.background = background;
    }
    
    public Color getBackground() {
        return this.background;
    }
    
    public void setPaintBackground(final boolean paintBackground) {
        this.paintBackground = paintBackground;
    }
    
    public boolean isPaintBackground() {
        return this.paintBackground;
    }
    
    public void setForeground(final Color c) {
        this.foreground = c;
    }
    
    public Color getForeground() {
        return this.foreground;
    }
    
    public void setFormatter(final ItemFormatter formatter) {
        this.formatter = formatter;
    }
    
    public ItemFormatter getFormatter() {
        return this.formatter;
    }
    
    public Dimension getPreferredSize(final Object data, final Graphics g, final int state, final ItemPaintSite site) {
        if (g == null) {
            return new Dimension();
        }
        final Font of = g.getFont();
        Font f = (site != null) ? site.getFont() : of;
        if (f == null) {
            f = of;
        }
        if (f != null) {
            g.setFont(f);
        }
        final FontMetrics fm = g.getFontMetrics(f);
        final String text = this.getText(data);
        final Insets m = (site != null) ? site.getItemMargins() : this.margins;
        g.setFont(of);
        return new Dimension(m.left + m.right + fm.stringWidth(text), m.top + m.bottom + fm.getHeight());
    }
    
    public void paint(final Object data, final Graphics g, final Rectangle r, final int state, final ItemPaintSite site) {
        final Font of = g.getFont();
        final Color oc = g.getColor();
        Font f = (site != null) ? site.getFont() : g.getFont();
        if (f == null) {
            f = g.getFont();
        }
        g.setFont(f);
        Insets m = (site != null) ? site.getItemMargins() : this.getMargins();
        if (m == null) {
            m = this.getMargins();
        }
        int a = (site != null) ? site.getAlignment() : this.getAlignment();
        if (a == 0) {
            a = this.getAlignment();
        }
        Color bg = (site != null) ? site.getBackground() : this.getBackground();
        if (bg == null) {
            bg = this.getBackground();
        }
        if (bg == null) {
            bg = g.getColor();
        }
        Color fg = (site != null) ? site.getForeground() : this.getForeground();
        if (fg == null) {
            fg = this.getForeground();
        }
        if (fg == null) {
            fg = SystemColor.windowText;
        }
        final String text = this.getText(data);
        final FontMetrics fm = g.getFontMetrics(f);
        int xOffset = 0;
        switch (a & 0xF) {
            default: {
                xOffset = m.left;
                break;
            }
            case 2: {
                xOffset = (r.width - fm.stringWidth(text)) / 2;
                break;
            }
            case 3: {
                xOffset = r.width - fm.stringWidth(text) - m.right;
                break;
            }
        }
        int yOffset = 0;
        switch (a & 0xF0) {
            default: {
                yOffset = m.top;
                break;
            }
            case 32: {
                yOffset = (r.height - fm.getHeight()) / 2;
                break;
            }
            case 48: {
                yOffset = r.height - m.bottom - fm.getHeight();
                break;
            }
        }
        yOffset += fm.getLeading() + fm.getAscent();
        if (this.paintBackground) {
            g.setColor(bg);
            g.fillRect(r.x, r.y, r.width, r.height);
        }
        if (text != null) {
            if ((state & 0x1) != 0x0) {
                g.setColor(SystemColor.controlLtHighlight);
                g.drawString(text, r.x + xOffset + 1, r.y + yOffset + 1);
                g.setColor(SystemColor.controlShadow);
            }
            else {
                g.setColor(fg);
            }
            g.drawString(text, r.x + xOffset, r.y + yOffset);
        }
        g.setFont(of);
        g.setColor(oc);
    }
    
    protected String getText(final Object data) {
        if (data != null) {
            if (this.formatter != null && data instanceof Variant) {
                try {
                    return this.formatter.format(data);
                }
                catch (InvalidFormatException ex) {
                    return "";
                }
            }
            return data.toString();
        }
        return "";
    }
}
