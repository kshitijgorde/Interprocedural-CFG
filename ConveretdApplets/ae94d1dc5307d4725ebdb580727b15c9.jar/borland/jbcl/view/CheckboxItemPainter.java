// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.view;

import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Rectangle;
import borland.jbcl.util.Variant;
import borland.jbcl.model.ItemPaintSite;
import java.awt.Graphics;
import java.awt.Dimension;
import borland.jbcl.model.ItemPainter;

public class CheckboxItemPainter implements ItemPainter
{
    public static final int CHECKMARK = 1;
    public static final int XMARK = 2;
    protected BorderItemPainter border;
    protected Dimension boxSize;
    protected int checkStyle;
    protected boolean flat;
    
    public CheckboxItemPainter() {
        this.border = new BorderItemPainter(9);
        this.boxSize = new Dimension(13, 13);
        this.checkStyle = 1;
        this.flat = false;
    }
    
    public CheckboxItemPainter(final Dimension boxSize) {
        this.border = new BorderItemPainter(9);
        this.boxSize = new Dimension(13, 13);
        this.checkStyle = 1;
        this.flat = false;
        if (boxSize != null) {
            this.boxSize = boxSize;
        }
    }
    
    public CheckboxItemPainter(final Dimension boxSize, final int checkStyle) {
        this(boxSize);
        this.checkStyle = checkStyle;
    }
    
    public CheckboxItemPainter(final Dimension boxSize, final int checkStyle, final boolean flat) {
        this(boxSize, checkStyle);
        this.flat = flat;
    }
    
    public void setBoxSize(final Dimension boxSize) {
        if (boxSize == null) {
            throw new IllegalArgumentException();
        }
        this.boxSize = boxSize;
    }
    
    public Dimension getBoxSize() {
        return new Dimension(this.boxSize.width + 2, this.boxSize.height + 2);
    }
    
    public void setStyle(final int checkStyle) {
        if (checkStyle != 1 && checkStyle != 2) {
            throw new IllegalArgumentException();
        }
        this.checkStyle = checkStyle;
    }
    
    public int getStyle() {
        return this.checkStyle;
    }
    
    public void setFlat(final boolean flat) {
        this.flat = flat;
    }
    
    public boolean isFlat() {
        return this.flat;
    }
    
    public Dimension getPreferredSize(final Object data, final Graphics g, final int state, final ItemPaintSite site) {
        return new Dimension(this.boxSize);
    }
    
    protected boolean isChecked(final Object data, final int state, final ItemPaintSite site) {
        boolean checked = false;
        if (data instanceof Boolean) {
            checked = (boolean)data;
        }
        else if (data instanceof Variant) {
            if (((Variant)data).getType() == 11) {
                checked = ((Variant)data).getBoolean();
            }
            else {
                checked = Boolean.valueOf(data.toString());
            }
        }
        else if (data instanceof String) {
            checked = Boolean.valueOf((String)data);
        }
        else if (data instanceof Integer) {
            checked = !data.equals(new Integer(0));
        }
        return checked;
    }
    
    public void paint(final Object data, final Graphics g, final Rectangle rect, final int state, final ItemPaintSite site) {
        final Color oc = g.getColor();
        Insets m = (site != null) ? site.getItemMargins() : new Insets(1, 1, 1, 1);
        if (m == null) {
            m = new Insets(1, 1, 1, 1);
        }
        final Rectangle boxRect = new Rectangle(rect.x + m.left, rect.y + m.top, this.boxSize.width, this.boxSize.height);
        int align = (site != null) ? site.getAlignment() : 34;
        if (align == 0) {
            align = 34;
        }
        final int hAlign = 0xF & align;
        switch (hAlign) {
            case 2: {
                boxRect.x = rect.x + (rect.width - this.boxSize.width) / 2;
                break;
            }
            case 3: {
                boxRect.x = rect.x + rect.width - this.boxSize.width - m.right;
                break;
            }
            case 4: {
                boxRect.width = rect.width - m.left - m.right;
                break;
            }
        }
        final int vAlign = 0xF0 & align;
        switch (vAlign) {
            case 32: {
                boxRect.y = rect.y + (rect.height - this.boxSize.height) / 2;
                break;
            }
            case 48: {
                boxRect.y = rect.y + rect.height - this.boxSize.height - m.bottom;
                break;
            }
            case 64: {
                boxRect.height = rect.height - m.top - m.bottom;
                break;
            }
        }
        if (site != null) {
            g.setColor(site.getBackground());
        }
        g.fillRect(rect.x, rect.y, rect.width, rect.height);
        this.drawBox(g, boxRect.x, boxRect.y, boxRect.width, boxRect.height, state);
        if (this.isChecked(data, state, site)) {
            if (this.flat) {
                this.drawCheck(g, boxRect.x + 1, boxRect.y + 1, boxRect.width - 2, boxRect.height - 2, state);
            }
            else {
                this.drawCheck(g, boxRect.x + 2, boxRect.y + 2, boxRect.width - 4, boxRect.height - 4, state);
            }
        }
        g.setColor(oc);
    }
    
    protected void drawBox(final Graphics g, final int x, final int y, final int w, final int h, final int state) {
        if (this.flat) {
            g.setColor(((state & 0x1) != 0x0) ? SystemColor.controlShadow : Color.black);
            g.drawRect(x, y, w - 1, h - 1);
        }
        else {
            if (SystemColor.control.equals(g.getColor())) {
                this.border.setFlags(4096);
            }
            else {
                this.border.setFlags(0);
            }
            this.border.paint(null, g, new Rectangle(x, y, w, h), 0, null);
        }
        g.setColor(((state & 0x1) != 0x0) ? SystemColor.control : SystemColor.window);
        if (this.flat) {
            g.fillRect(x + 1, y + 1, w - 2, h - 2);
        }
        else {
            g.fillRect(x + 2, y + 2, w - 4, h - 4);
        }
    }
    
    protected void drawCheck(final Graphics g, int x, int y, final int w, final int h, final int state) {
        ++x;
        ++y;
        g.setColor(((state & 0x1) != 0x0) ? SystemColor.controlShadow : SystemColor.windowText);
        if (this.checkStyle == 1) {
            final int thickness = h / 3;
            final int xbase = x + w / 3 - 1;
            for (int t = 1; t <= thickness; ++t) {
                g.drawLine(xbase, y + h - t - 2, x, y + thickness - t + h / 2 - 2);
                g.drawLine(xbase, y + h - t - 2, x + w - 3, y + thickness - t);
            }
        }
        else if (this.checkStyle == 2) {
            g.drawLine(x, y, x + w - 3, y + h - 3);
            g.drawLine(x + 1, y, x + w - 3, y + h - 4);
            g.drawLine(x, y + 1, x + w - 4, y + h - 3);
            g.drawLine(x, y + h - 3, x + w - 3, y);
            g.drawLine(x + 1, y + h - 3, x + w - 3, y + 1);
            g.drawLine(x, y + h - 4, x + w - 4, y);
        }
    }
}
