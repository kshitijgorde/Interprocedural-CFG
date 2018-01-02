// 
// Decompiled by Procyon v0.5.30
// 

package util102.gui;

import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

public class LineSeparator extends Canvas
{
    private boolean horizontal;
    public static final int FILLED_3D = 0;
    public static final int FILLED = 1;
    public static final int GUTTER = 2;
    public static final int RELIEF = 3;
    private int lineType;
    private int lineWidth;
    Image offscreenImg;
    
    public LineSeparator() {
        this(0, 2, true);
    }
    
    public LineSeparator(final int n) {
        this(0, n, true);
    }
    
    public LineSeparator(final int n, final boolean b) {
        this(0, n, b);
    }
    
    public LineSeparator(final int lineType, final int lineWidth, final boolean horizontal) {
        this.horizontal = true;
        this.lineWidth = 2;
        this.lineType = lineType;
        this.lineWidth = lineWidth;
        this.horizontal = horizontal;
    }
    
    public final void paintLine(final Graphics graphics) {
        final Dimension size = this.size();
        final Color background = this.getBackground();
        graphics.setColor(background);
        graphics.fillRect(0, 0, size.width, size.height);
        final int n = (size.height - this.lineWidth) / 2;
        final int n2 = (size.width - this.lineWidth) / 2;
        if (this.lineType == 0) {
            if (this.horizontal) {
                graphics.setColor(background.brighter());
                graphics.fillRect(1, n + 1, size.width - 1, this.lineWidth);
                graphics.setColor(background.darker());
                graphics.fillRect(0, n, size.width - 1, this.lineWidth);
                return;
            }
            graphics.setColor(background.brighter());
            graphics.fillRect(n2 + 1, 1, this.lineWidth, size.height - 1);
            graphics.setColor(background.darker());
            graphics.fillRect(n2, 0, this.lineWidth, size.height - 1);
        }
        else {
            if (this.lineType != 1) {
                if (this.lineType == 3 || this.lineType == 2) {
                    final boolean b = this.lineType == 3;
                    if (this.horizontal) {
                        graphics.setColor(background);
                        graphics.draw3DRect(0, n, size.width - 1, this.lineWidth, b);
                        return;
                    }
                    graphics.setColor(background);
                    graphics.draw3DRect(n2, 0, this.lineWidth, size.height - 1, b);
                }
                return;
            }
            if (this.horizontal) {
                graphics.setColor(this.getForeground());
                graphics.fillRect(0, n, size.width - 1, this.lineWidth);
                return;
            }
            graphics.setColor(this.getForeground());
            graphics.fillRect(n2, 0, this.lineWidth, size.height - 1);
        }
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offscreenImg == null) {
            this.offscreenImg = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.offscreenImg.getGraphics();
        this.paintLine(graphics2);
        graphics.drawImage(this.offscreenImg, 0, 0, this);
        graphics2.dispose();
    }
    
    public void invalidate() {
        super.invalidate();
        this.offscreenImg = null;
    }
    
    public Dimension minimumSize() {
        if (this.lineWidth <= 0) {
            return super.minimumSize();
        }
        if (this.horizontal) {
            return new Dimension(10, this.lineWidth + 2);
        }
        return new Dimension(this.lineWidth + 2, 10);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void setOrientation(final boolean horizontal) {
        this.horizontal = horizontal;
    }
    
    public boolean getOrientation() {
        return this.horizontal;
    }
    
    public void setWidth(final int lineWidth) {
        this.lineWidth = lineWidth;
    }
    
    public int getWidth() {
        return this.lineWidth;
    }
}
