// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.apps.viewpanel;

import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Color;
import java.awt.image.ImageObserver;
import lotus.notes.components.LineEntry;
import java.awt.Point;
import lotus.notes.util.Bidi;
import java.awt.Graphics;
import java.awt.Dimension;
import lotus.notes.components.OutlineView;

public class GutterOutlineView extends OutlineView
{
    public boolean GUTTER;
    public static final int GUTTER_WIDTH = 33;
    public boolean hideMarginBorder;
    
    public GutterOutlineView(final Dimension dimension) {
        super(dimension);
        this.GUTTER = true;
        this.hideMarginBorder = true;
    }
    
    public void paint(final Graphics graphics) {
        Graphics graphics2 = null;
        final boolean doubleBufferEnabled = this.isDoubleBufferEnabled();
        final int width = this.size().width;
        final int height = this.size().height;
        Graphics graphics3;
        if (doubleBufferEnabled) {
            if (super.offscreenBufferInvalid) {
                if (super.offscreenBuffer != null) {
                    super.offscreenBuffer.flush();
                }
                super.offscreenBuffer = this.createImage(width, height);
                super.offscreenBufferInvalid = false;
            }
            graphics3 = super.offscreenBuffer.getGraphics();
        }
        else {
            graphics3 = graphics;
        }
        final Color foreground = this.getForeground();
        graphics3.setColor(this.getBackground());
        graphics3.fillRect(0, 0, width, height);
        graphics3.setColor(foreground);
        if (super.bgImage != null) {
            final int n = this.GUTTER ? 33 : 0;
            if (this.getDirection() == 0) {
                super.bgNImage.paint(graphics3, n, 0, width, height);
            }
            else {
                super.bgNImage.paint(graphics3, Bidi.toggleHorzPos(n, 0, this.size().width), 0, width, height);
            }
        }
        this.TraverseEntries(0, graphics3, null, null);
        if (this.GUTTER && !this.hideMarginBorder) {
            if (this.getDirection() == 0) {
                graphics2 = graphics3.create(0, 0, 33, this.size().height);
                graphics2.fillRect(graphics2.getClipRect().width - 1, 0, 1, graphics2.getClipRect().height);
            }
            else {
                graphics2 = graphics3.create(Bidi.toggleHorzPos(33, 0, this.size().width), 0, 33, this.size().height);
                graphics2.fillRect(0, 0, 1, graphics2.getClipRect().height);
            }
        }
        if (doubleBufferEnabled) {
            graphics.drawImage(super.offscreenBuffer, 0, 0, null);
            if (graphics3 != null) {
                graphics3.dispose();
            }
            if (graphics2 != null) {
                graphics2.dispose();
            }
        }
    }
    
    public void DrawSelRect(final Graphics graphics, final LineEntry lineEntry, final boolean b) {
        lineEntry.setSelected(!b);
        final int direction = this.getDirection();
        Graphics graphics2;
        if (this.GUTTER) {
            if (direction == 0) {
                graphics2 = graphics.create(33, 0, this.size().width - 33 + 1, this.size().height);
            }
            else {
                graphics2 = graphics.create(0, 0, this.size().width - 33 + 1, this.size().height);
            }
        }
        else {
            graphics2 = graphics.create(0, 0, this.size().width, this.size().height);
        }
        final Rectangle getSelectionRect = lineEntry.GetSelectionRect(graphics2, this);
        if (super.selectionBorderStyle == 1) {
            graphics2.setXORMode(this.getBackground());
            int toggleHorzPos = getSelectionRect.x + super.HorzScrollOffset - 1;
            final int n = graphics2.getClipRect().width - super.HorzScrollOffset - 1;
            if (direction == 1) {
                toggleHorzPos = Bidi.toggleHorzPos(toggleHorzPos + 1, n, graphics2.getClipRect().width);
            }
            graphics2.drawRect(toggleHorzPos, getSelectionRect.y - 1, n - 1, getSelectionRect.height + 1);
            graphics2.setPaintMode();
        }
        else if (super.selectionBorderStyle == 2) {
            lineEntry.Paint(graphics2, lineEntry, new Point(super.HorzScrollOffset, getSelectionRect.y), this, super.bgImage);
        }
    }
    
    public void setPaintGutterLine(final boolean gutter) {
        this.GUTTER = gutter;
    }
}
