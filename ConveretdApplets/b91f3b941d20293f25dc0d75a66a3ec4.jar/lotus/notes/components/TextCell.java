// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Rectangle;
import lotus.notes.util.Bidi;
import java.awt.Component;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;

public class TextCell extends CellEntry
{
    private static boolean DEBUG;
    public String Text;
    public Font TextFont;
    public FontMetrics fm;
    
    public TextCell() {
    }
    
    public TextCell(final String s) {
        this.TextFont = null;
        this.Text = new String(s);
        super.bExtentsValid = false;
    }
    
    public void SetText(final String s) {
        this.Text = new String(s);
        super.bExtentsValid = false;
    }
    
    public void SetFont(final Font textFont) {
        this.TextFont = textFont;
    }
    
    public void Paint(final Graphics graphics, final Point point, final Component component) {
        if (null == this.TextFont) {
            this.TextFont = super.ColInfo.GetFont();
            if (null == this.TextFont) {
                this.TextFont = new Font("Helvetica", 0, 10);
            }
        }
        final Color color = graphics.getColor();
        final Font font = graphics.getFont();
        graphics.setFont(this.TextFont);
        graphics.setColor(super.ColInfo.GetColor());
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        final int maxDescent = fontMetrics.getMaxDescent();
        fontMetrics.stringWidth(this.Text);
        final Dimension getLineBounds = super.Line.GetLineBounds();
        int n = CellEntry.handleHAlignment(super.ColInfo, point.x + super.ColInfo.GetxPos(), fontMetrics.stringWidth(this.Text), component.size().width);
        if (super.direction == 1) {
            n = Bidi.toggleHorzPos(n, fontMetrics.stringWidth(this.Text), component.size().width);
        }
        final Rectangle rectangle = new Rectangle(n, point.y, getLineBounds.width, getLineBounds.height);
        int getyOffset = super.ColInfo.GetyOffset();
        final int vAlignment = super.ColInfo.vAlignment;
        final ColDesc colInfo = super.ColInfo;
        if (vAlignment == 3) {
            getyOffset += fontMetrics.getMaxAscent();
        }
        final int vAlignment2 = super.ColInfo.vAlignment;
        final ColDesc colInfo2 = super.ColInfo;
        if (vAlignment2 == 2) {
            getyOffset = (getLineBounds.height - height) / 2 + fontMetrics.getMaxAscent();
        }
        final int vAlignment3 = super.ColInfo.vAlignment;
        final ColDesc colInfo3 = super.ColInfo;
        if (vAlignment3 == 4) {
            getyOffset += getLineBounds.height - maxDescent;
        }
        Graphics graphics2;
        if (super.ColInfo.bClip) {
            int n2 = CellEntry.handleHAlignment(super.ColInfo, rectangle.x, fontMetrics.stringWidth(this.Text), component.size().width);
            if (super.direction == 1) {
                n2 = Bidi.toggleHorzPos(n2, fontMetrics.stringWidth(this.Text), component.size().width);
            }
            graphics2 = graphics.create(n2, rectangle.y, super.ColInfo.GetWidth(), rectangle.height);
            if (null != graphics2) {
                final int handleHAlignment = CellEntry.handleHAlignment(super.ColInfo, rectangle.x, fontMetrics.stringWidth(this.Text), component.size().width);
                if (super.direction == 1) {
                    Bidi.toggleHorzPos(handleHAlignment, fontMetrics.stringWidth(this.Text), component.size().width);
                }
                final String s = (super.readingOrder == 0) ? this.Text : Bidi.BidiString(this.Text);
                graphics.clearRect(handleHAlignment, rectangle.y, rectangle.width, rectangle.height);
                graphics2.drawString(s, 0, getyOffset);
            }
        }
        else {
            graphics2 = graphics.create((super.direction == 0) ? rectangle.x : Bidi.toggleHorzPos(rectangle.x, fontMetrics.stringWidth(this.Text), component.size().width), rectangle.y, rectangle.width, rectangle.height);
            graphics2.drawString((super.readingOrder == 0) ? this.Text : Bidi.BidiString(this.Text), (super.direction == 0) ? 0 : Bidi.toggleHorzPos(0, fontMetrics.stringWidth(this.Text), component.size().width), getyOffset);
        }
        graphics.setFont(font);
        graphics.setColor(color);
        graphics2.dispose();
    }
    
    protected void UpdateExtents(final Graphics graphics, final Component component) {
        if (null == this.TextFont) {
            this.TextFont = super.ColInfo.GetFont();
            if (null == this.TextFont) {
                this.TextFont = new Font("Helvetica", 0, 10);
            }
        }
        final Font font = graphics.getFont();
        graphics.setFont(this.TextFont);
        this.fm = graphics.getFontMetrics();
        super.MaxExtents.width = this.fm.stringWidth(this.Text) + super.padding;
        super.MaxExtents.height = this.fm.getHeight();
        super.ClipExtents.height = super.MaxExtents.height + this.fm.getMaxDescent();
        graphics.setFont(font);
        if (super.ColInfo.bClip || super.bUseAllWidth) {
            super.ClipExtents.width = super.ColInfo.GetWidth();
        }
        else {
            super.ClipExtents.width = super.MaxExtents.width;
        }
        super.bExtentsValid = true;
    }
    
    public String toString() {
        return this.Text;
    }
    
    static {
        TextCell.DEBUG = false;
    }
}
