// 
// Decompiled by Procyon v0.5.30
// 

package util102.gui;

import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;

public class BorderPanel extends Panel
{
    int topInsets;
    int bottomInsets;
    int leftInsets;
    int rightInsets;
    public static final int NO_BORDER = 0;
    public static final int RELIEF = 1;
    public static final int HOLLOW = 2;
    public static final int BIG_RELIEF = 3;
    public static final int BIG_HOLLOW = 4;
    public static final int BIG_BEVEL = 5;
    public static final int BIG_GUTTER = 6;
    public static final int BEVEL = 7;
    public static final int GUTTER = 8;
    int borderType;
    Image offscreenImg;
    String label;
    Font font;
    Image image;
    boolean textured;
    int ipadX;
    
    public BorderPanel() {
        this(5, 5, 5, 5, 3, null);
    }
    
    public BorderPanel(final int n, final int n2, final int n3, final int n4) {
        this(n, n2, n3, n4, 3, null, false);
    }
    
    public BorderPanel(final int n, final int n2, final int n3, final int n4, final int n5) {
        this(n, n2, n3, n4, n5, null, false);
    }
    
    public BorderPanel(final int n, final int n2, final int n3, final int n4, final int n5, final Image image) {
        this(n, n2, n3, n4, n5, image, false);
    }
    
    public BorderPanel(final int topInsets, final int leftInsets, final int bottomInsets, final int rightInsets, final int borderType, final Image image, final boolean textured) {
        this.topInsets = 5;
        this.bottomInsets = 5;
        this.leftInsets = 5;
        this.rightInsets = 5;
        this.borderType = 3;
        this.font = new Font("Dialog", 0, 12);
        this.textured = false;
        this.ipadX = 10;
        this.topInsets = topInsets;
        this.leftInsets = leftInsets;
        this.bottomInsets = bottomInsets;
        this.rightInsets = rightInsets;
        this.borderType = borderType;
        this.image = image;
        this.textured = textured;
    }
    
    public Insets insets() {
        return new Insets(this.topInsets, this.leftInsets, this.bottomInsets, this.rightInsets);
    }
    
    public void paintPanel(final Graphics graphics) {
        final Dimension size = this.size();
        final Color background = this.getBackground();
        graphics.setColor(background);
        graphics.fillRect(0, 0, size.width, size.height);
        int n = 0;
        if (this.label != null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.font);
            n = (fontMetrics.getAscent() + fontMetrics.getDescent()) / 2;
        }
        if (this.borderType == 1) {
            graphics.draw3DRect(0, n, size.width - 1, size.height - 1 - n, true);
        }
        else if (this.borderType == 2) {
            graphics.draw3DRect(0, n, size.width - 1, size.height - 1 - n, false);
        }
        else if (this.borderType == 6) {
            graphics.draw3DRect(0, n, size.width - 1, size.height - 1 - n, false);
            graphics.draw3DRect(3, n + 3, size.width - 7, size.height - 7 - n, true);
        }
        else if (this.borderType == 5) {
            graphics.draw3DRect(0, n, size.width - 1, size.height - 1 - n, true);
            graphics.draw3DRect(3, n + 3, size.width - 7, size.height - 7 - n, false);
        }
        else if (this.borderType == 7) {
            graphics.setColor(background.darker());
            graphics.drawRect(1, n + 1, size.width - 2, size.height - 2 - n);
            graphics.setColor(background.brighter());
            graphics.drawRect(0, n, size.width - 2, size.height - 2 - n);
        }
        else if (this.borderType == 8) {
            graphics.setColor(background.brighter());
            graphics.drawRect(1, n + 1, size.width - 2, size.height - 2 - n);
            graphics.setColor(background.darker());
            graphics.drawRect(0, n, size.width - 2, size.height - 2 - n);
        }
        else if (this.borderType == 3) {
            graphics.setColor(background.brighter());
            graphics.drawLine(0, n, size.width - 2, n);
            graphics.setColor(Color.black);
            graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
            graphics.drawLine(size.width - 1, n, size.width - 1, size.height);
            graphics.setColor(background.darker());
            graphics.drawLine(0, size.height - 2, size.width - 2, size.height - 2);
            graphics.drawLine(size.width - 2, n, size.width - 2, size.height - 2);
            graphics.setColor(background.brighter());
            graphics.drawLine(0, n, 0, size.height);
        }
        else if (this.borderType == 4) {
            graphics.setColor(Color.black);
            graphics.drawLine(0, n, size.width - 2, n);
            graphics.setColor(background.brighter());
            graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
            graphics.drawLine(size.width - 1, n, size.width - 1, size.height);
            graphics.setColor(background.darker());
            graphics.drawLine(1, 1 + n, size.width - 2, 1 + n);
            graphics.drawLine(1, 1 + n, 1, size.height - 2);
            graphics.setColor(Color.black);
            graphics.drawLine(0, n, 0, size.height);
        }
        if (this.image != null) {
            final int width = this.image.getWidth(this);
            final int height = this.image.getHeight(this);
            if (width > 0 && height > 0) {
                if (this.textured) {
                    for (int i = 0; i < size.width; i += width) {
                        for (int j = 0; j < size.height; j += height) {
                            graphics.drawImage(this.image, i, j, this);
                        }
                    }
                    return;
                }
                graphics.drawImage(this.image, (size.width - width) / 2, (size.height - height) / 2, this);
            }
        }
        else if (this.label != null) {
            final FontMetrics fontMetrics2 = this.getFontMetrics(this.font);
            graphics.setColor(background);
            graphics.fillRect(this.ipadX - 2, 0, fontMetrics2.stringWidth(this.label) + 4, fontMetrics2.getAscent() + fontMetrics2.getDescent());
            graphics.setFont(this.font);
            graphics.setColor(this.getForeground());
            graphics.drawString(this.label, this.ipadX, fontMetrics2.getAscent());
        }
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offscreenImg == null) {
            this.offscreenImg = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.offscreenImg.getGraphics();
        this.paintPanel(graphics2);
        graphics.drawImage(this.offscreenImg, 0, 0, this);
        graphics2.dispose();
    }
    
    public void invalidate() {
        super.invalidate();
        this.offscreenImg = null;
    }
    
    public Dimension minimumSize() {
        if (!this.textured && this.image != null) {
            final int width = this.image.getWidth(this);
            final int height = this.image.getHeight(this);
            if (width > 0 && height > 0) {
                return new Dimension(width + this.leftInsets + this.rightInsets, height + this.topInsets + this.bottomInsets);
            }
        }
        return super.minimumSize();
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void setLabel(final String label) {
        this.label = label;
        this.repaint();
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setFont(final Font font) {
        this.font = font;
        this.repaint();
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setImage(final Image image) {
        this.image = image;
        this.repaint();
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public void setLabelIpadX(final int ipadX) {
        this.ipadX = ipadX;
        this.repaint();
    }
    
    public int getLabelIpadX() {
        return this.ipadX;
    }
}
