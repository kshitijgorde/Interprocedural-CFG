// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Dimension;
import java.awt.Color;
import lotus.notes.util.Bidi;
import java.awt.Font;
import java.awt.Component;
import java.awt.Point;
import java.awt.Graphics;

public class MultiLineTextCell extends TextCell
{
    public static final int MAXROWS = 10;
    int rows;
    private String[] chunks;
    private int lineheight;
    private int width;
    public int maxRows;
    public int interlineSpacing;
    public boolean drawLine;
    
    public MultiLineTextCell(final String s) {
        this.rows = 0;
        this.width = 0;
        this.drawLine = false;
        this.SetText(s);
        this.interlineSpacing = 0;
        this.setMaxRows(10);
    }
    
    public void setInterlineSpacing(final int interlineSpacing) {
        this.interlineSpacing = interlineSpacing;
    }
    
    public void setMaxRows(final int maxRows) {
        this.maxRows = maxRows;
        this.chunks = new String[this.maxRows];
    }
    
    public void Paint(final Graphics graphics, final Point point, final Component component) {
        if (null == super.TextFont) {
            super.TextFont = super.ColInfo.GetFont();
            if (null == super.TextFont) {
                super.TextFont = new Font("Helvetica", 0, 10);
            }
        }
        Color color;
        if (null == super.fgColor) {
            color = super.ColInfo.GetColor();
        }
        else {
            color = super.fgColor;
        }
        final Font font = graphics.getFont();
        final Color color2 = graphics.getColor();
        graphics.setFont(super.TextFont);
        graphics.setColor(color);
        final int width = component.size().width;
        final int getxPos = super.ColInfo.GetxPos();
        if (width > super.padding + point.x + getxPos && width < super.padding + point.x + getxPos + super.ClipExtents.width) {
            super.ClipExtents.width = width - (point.x + getxPos);
            super.fm = graphics.getFontMetrics();
            this.formatRows();
        }
        int n = point.y + super.fm.getMaxAscent();
        if (super.bgColor != null) {
            final Color color3 = graphics.getColor();
            graphics.setColor(super.bgColor);
            final int n2 = point.x + super.ColInfo.GetxPos() + super.padding;
            if (super.direction == 0) {
                graphics.fillRect(n2, point.y, super.ClipExtents.width, super.ClipExtents.height);
            }
            else {
                graphics.fillRect(Bidi.toggleHorzPos(n2, super.ClipExtents.width - 2, component.size().width), point.y, super.ClipExtents.width, super.ClipExtents.height);
            }
            graphics.setColor(color3);
        }
        for (int i = 0; i < this.rows; ++i) {
            int n3;
            if ((super.direction == 0 && super.hAlignment == 0) || (super.direction == 1 && super.hAlignment == 1)) {
                n3 = 0;
            }
            else if (super.hAlignment == 2) {
                n3 = (super.ClipExtents.width - super.fm.stringWidth(this.chunks[i])) / 2;
            }
            else {
                n3 = super.ClipExtents.width - super.fm.stringWidth(this.chunks[i]);
            }
            int toggleHorzPos = n3 + point.x + super.ColInfo.GetxPos() + super.padding;
            if (super.direction == 1) {
                toggleHorzPos = Bidi.toggleHorzPos(toggleHorzPos, super.fm.stringWidth(this.chunks[i]), graphics.getClipRect().width);
            }
            if (this.chunks[i] != null) {
                graphics.drawString(this.chunks[i], toggleHorzPos, n);
            }
            n += super.fm.getMaxAscent() + this.interlineSpacing;
        }
        if (this.drawLine) {
            final int n4 = point.x + super.ColInfo.GetxPos() + super.padding;
            if (super.direction == 0) {
                graphics.drawLine(n4, point.y, n4 + super.ClipExtents.width - 2, point.y);
            }
            else {
                graphics.drawLine(Bidi.toggleHorzPos(n4, super.ClipExtents.width - 2, component.size().width), point.y, n4 + super.ClipExtents.width - 2, point.y);
            }
        }
        graphics.setFont(font);
        graphics.setColor(color2);
    }
    
    protected void UpdateExtents(final Graphics graphics, final Component component) {
        super.UpdateExtents(graphics, component);
        final int width = component.size().width;
        final int getxPos = super.ColInfo.GetxPos();
        if (width + super.ColInfo.hOffset > getxPos && width + super.ColInfo.hOffset < getxPos + super.ClipExtents.width && !super.ColInfo.isLastColumn) {
            super.ClipExtents.width = width + super.ColInfo.hOffset - getxPos;
        }
        if (super.ColInfo.isLastColumn) {
            super.ClipExtents.width = width + super.ColInfo.hOffset - getxPos;
        }
        final Dimension clipExtents = super.ClipExtents;
        clipExtents.width -= super.padding;
        if (super.ClipExtents.width < 0) {
            super.ClipExtents.width = 0;
        }
        if (super.ClipExtents.height < 0) {
            super.ClipExtents.height = 0;
        }
        this.formatRows();
        super.bExtentsValid = true;
    }
    
    private int formatRows() {
        this.lineheight = super.fm.getHeight();
        final int length = super.Text.length();
        int i = 0;
        this.rows = 0;
        while (i < length) {
            int n = -1;
            int j;
            for (j = i; j < length; ++j) {
                if (super.Text.charAt(j) == '\n') {
                    n = j + 1;
                    break;
                }
                if (super.Text.charAt(j) == ' ') {
                    n = j + 1;
                }
                if (super.fm.stringWidth(super.Text.substring(i, j + 1)) > super.ClipExtents.width) {
                    if (n == -1) {
                        n = j;
                    }
                    break;
                }
            }
            if (j == length) {
                n = length;
            }
            if (n != -1) {
                final String s = new String(super.Text.substring(i, n));
                this.chunks[this.rows] = ((super.readingOrder == 0) ? s : Bidi.BidiString(s));
                i = n;
                ++this.rows;
            }
            if (this.rows == this.maxRows) {
                break;
            }
        }
        super.MaxExtents.height = this.rows * this.lineheight + (this.rows - 1) * this.interlineSpacing;
        super.ClipExtents.height = super.MaxExtents.height + super.fm.getMaxDescent();
        return this.rows;
    }
}
