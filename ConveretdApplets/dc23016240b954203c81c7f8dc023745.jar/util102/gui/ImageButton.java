// 
// Decompiled by Procyon v0.5.30
// 

package util102.gui;

import java.awt.Insets;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Event;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Canvas;

public class ImageButton extends Canvas
{
    boolean down;
    boolean sticky;
    String name;
    public static final int RELIEF = 0;
    public static final int BIG_RELIEF = 1;
    public static final int BIG_BEVEL = 2;
    public static final int BEVEL = 3;
    public static final int ROUNDED = 4;
    public static final int NO_BORDER = 5;
    int borderType;
    boolean focus;
    Image image;
    Image downImage;
    Image focusImage;
    String label;
    Font font;
    Font focusFont;
    Color labelFocusColor;
    Image offscreenImg;
    boolean enabled;
    boolean showBorderOnFocus;
    int topInsets;
    int bottomInsets;
    int leftInsets;
    int rightInsets;
    
    public ImageButton(final Image image) {
        this(image, null, null, 1);
    }
    
    public ImageButton(final String label) {
        this(null, null, null, 1);
        this.label = label;
    }
    
    public ImageButton(final String label, final int n) {
        this(null, null, null, n);
        this.label = label;
    }
    
    public ImageButton(final Image image, final Image image2, final Image image3) {
        this(image, image2, image3, 1);
    }
    
    public ImageButton(final Image image, final Image downImage, final Image focusImage, final int borderType) {
        this.down = false;
        this.sticky = false;
        this.name = "ImageButton";
        this.borderType = 1;
        this.focus = false;
        this.font = new Font("Dialog", 0, 12);
        this.focusFont = new Font("Dialog", 1, 12);
        this.labelFocusColor = Color.black;
        this.enabled = true;
        this.showBorderOnFocus = false;
        this.topInsets = 4;
        this.bottomInsets = 4;
        this.leftInsets = 4;
        this.rightInsets = 4;
        this.image = image;
        this.downImage = downImage;
        this.focusImage = focusImage;
        this.borderType = borderType;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                if (!this.enabled) {
                    return true;
                }
                if (this.sticky) {
                    final Component[] components = this.getParent().getComponents();
                    for (int i = 0; i < components.length; ++i) {
                        if (components[i] instanceof ImageButton) {
                            ((ImageButton)components[i]).setDown(false);
                        }
                    }
                }
                this.down = true;
                this.repaint();
                return true;
            }
            case 502: {
                if (!this.enabled) {
                    return true;
                }
                if (!this.sticky) {
                    this.down = false;
                    this.repaint();
                }
                this.postEvent(new Event(this, 1001, null));
                return true;
            }
            case 504: {
                if (!this.enabled) {
                    return true;
                }
                if (this.focusImage != null || this.focusFont != null || this.showBorderOnFocus) {
                    this.focus = true;
                    this.repaint();
                    return true;
                }
                break;
            }
            case 505: {
                if (!this.enabled) {
                    return true;
                }
                if (!this.sticky) {
                    this.down = false;
                    this.repaint();
                }
                if (this.focusImage != null || this.focusFont != null || this.showBorderOnFocus) {
                    this.focus = false;
                    this.repaint();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paintButton(final Graphics graphics) {
        final Dimension size = this.size();
        final Color background = this.getBackground();
        graphics.setColor(background);
        graphics.fillRect(0, 0, size.width, size.height);
        if (this.down) {
            this.paintDownBorder(graphics);
            Image image;
            if (this.downImage != null) {
                image = this.downImage;
            }
            else {
                image = this.image;
            }
            if (image != null) {
                final int width = image.getWidth(this);
                final int height = image.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(image, (size.width - width) / 2 + 1, (size.height - height) / 2 + 1, this);
                }
            }
            else {
                if (this.label == null) {
                    return;
                }
                Font font;
                Color color;
                if (this.focus) {
                    font = this.focusFont;
                    color = this.labelFocusColor;
                }
                else {
                    font = this.font;
                    color = this.getForeground();
                }
                graphics.setColor(color);
                final FontMetrics fontMetrics = this.getFontMetrics(font);
                graphics.setFont(font);
                graphics.drawString(this.label, (size.width - fontMetrics.stringWidth(this.label)) / 2 + 1, (size.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent() + 1);
            }
            return;
        }
        if (!this.showBorderOnFocus || (this.showBorderOnFocus && this.focus)) {
            this.paintRaisedBorder(graphics);
        }
        Image image2;
        if (this.focus && this.focusImage != null) {
            image2 = this.focusImage;
        }
        else {
            image2 = this.image;
        }
        if (image2 != null) {
            final int width2 = image2.getWidth(this);
            final int height2 = image2.getHeight(this);
            if (width2 > 0 && height2 > 0) {
                graphics.drawImage(image2, (size.width - width2) / 2, (size.height - height2) / 2, this);
            }
        }
        else if (this.label != null) {
            Font font2;
            Color color2;
            if (this.focus) {
                font2 = this.focusFont;
                color2 = this.labelFocusColor;
            }
            else {
                font2 = this.font;
                color2 = this.getForeground();
            }
            graphics.setColor(color2);
            final FontMetrics fontMetrics2 = this.getFontMetrics(font2);
            graphics.setFont(font2);
            final int n = (size.width - fontMetrics2.stringWidth(this.label)) / 2;
            final int n2 = (size.height - fontMetrics2.getHeight()) / 2 + fontMetrics2.getAscent();
            if (this.enabled) {
                graphics.drawString(this.label, n, n2);
                return;
            }
            graphics.setColor(background.brighter());
            graphics.drawString(this.label, n + 1, n2 + 1);
            graphics.setColor(background.darker());
            graphics.drawString(this.label, n, n2);
        }
    }
    
    public void paintDownBorder(final Graphics graphics) {
        final Dimension size = this.size();
        final Color background = this.getBackground();
        if (this.borderType == 0) {
            graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, false);
            return;
        }
        if (this.borderType == 1) {
            graphics.setColor(Color.black);
            graphics.drawLine(0, 0, size.width - 2, 0);
            graphics.setColor(background.brighter());
            graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
            graphics.drawLine(size.width - 1, 0, size.width - 1, size.height);
            graphics.setColor(background.darker());
            graphics.drawLine(1, 1, size.width - 2, 1);
            graphics.drawLine(1, 1, 1, size.height - 2);
            graphics.setColor(Color.black);
            graphics.drawLine(0, 0, 0, size.height);
            return;
        }
        if (this.borderType == 3) {
            graphics.setColor(background.brighter());
            graphics.drawRect(1, 1, size.width - 2, size.height - 2);
            graphics.setColor(background.darker());
            graphics.drawRect(0, 0, size.width - 2, size.height - 2);
            return;
        }
        if (this.borderType == 2) {
            graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, false);
            graphics.draw3DRect(3, 3, size.width - 7, size.height - 7, true);
            return;
        }
        if (this.borderType == 4) {
            graphics.setColor(background.darker());
            graphics.fillRoundRect(0, 0, size.width - 1, size.height - 1, 15, 15);
            graphics.setColor(Color.black);
            graphics.drawRoundRect(0, 0, size.width - 1, size.height - 1, 15, 15);
        }
    }
    
    public void paintRaisedBorder(final Graphics graphics) {
        final Dimension size = this.size();
        final Color background = this.getBackground();
        if (this.borderType == 0) {
            graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, true);
            return;
        }
        if (this.borderType == 1) {
            graphics.setColor(background.brighter());
            graphics.drawLine(0, 0, size.width - 2, 0);
            graphics.setColor(Color.black);
            graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
            graphics.drawLine(size.width - 1, 0, size.width - 1, size.height);
            graphics.setColor(background.darker());
            graphics.drawLine(0, size.height - 2, size.width - 2, size.height - 2);
            graphics.drawLine(size.width - 2, 0, size.width - 2, size.height - 2);
            graphics.setColor(background.brighter());
            graphics.drawLine(0, 0, 0, size.height);
            return;
        }
        if (this.borderType == 3) {
            graphics.setColor(background.darker());
            graphics.drawRect(1, 1, size.width - 2, size.height - 2);
            graphics.setColor(background.brighter());
            graphics.drawRect(0, 0, size.width - 2, size.height - 2);
            return;
        }
        if (this.borderType == 2) {
            graphics.draw3DRect(0, 0, size.width - 1, size.height - 1, true);
            graphics.draw3DRect(3, 3, size.width - 7, size.height - 7, false);
            return;
        }
        if (this.borderType == 4) {
            graphics.setColor(Color.black);
            graphics.drawRoundRect(0, 0, size.width - 1, size.height - 1, 15, 15);
        }
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offscreenImg == null) {
            this.offscreenImg = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.offscreenImg.getGraphics();
        this.paintButton(graphics2);
        graphics.drawImage(this.offscreenImg, 0, 0, this);
        graphics2.dispose();
    }
    
    public void invalidate() {
        super.invalidate();
        this.offscreenImg = null;
    }
    
    public Dimension minimumSize() {
        int width = 0;
        int height = 0;
        if (this.image != null) {
            width = this.image.getWidth(this);
            height = this.image.getHeight(this);
        }
        int width2 = 0;
        int height2 = 0;
        if (this.downImage != null) {
            width2 = this.downImage.getWidth(this);
            height2 = this.downImage.getHeight(this);
        }
        int width3 = 0;
        int height3 = 0;
        if (this.focusImage != null) {
            width3 = this.focusImage.getWidth(this);
            height3 = this.focusImage.getHeight(this);
        }
        int n = Math.max(Math.max(width, width2), width3);
        int n2 = Math.max(Math.max(height, height2), height3);
        if (this.label != null && this.image == null && this.downImage == null && this.focusImage == null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.font);
            final FontMetrics fontMetrics2 = this.getFontMetrics(this.focusFont);
            final int stringWidth = fontMetrics.stringWidth(this.label);
            final int height4 = fontMetrics.getHeight();
            final int stringWidth2 = fontMetrics2.stringWidth(this.label);
            final int height5 = fontMetrics2.getHeight();
            n = Math.max(stringWidth, stringWidth2);
            n2 = Math.max(height4, height5);
        }
        if (n > 0 && n2 > 0) {
            return new Dimension(n + this.leftInsets + this.rightInsets, n2 + this.topInsets + this.bottomInsets);
        }
        return super.minimumSize();
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        this.repaint();
    }
    
    public boolean isDown() {
        return this.down;
    }
    
    public void setDown(final boolean down) {
        if (this.sticky && down) {
            final Component[] components = this.getParent().getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof ImageButton) {
                    ((ImageButton)components[i]).setDown(false);
                }
            }
        }
        this.down = down;
        this.repaint();
    }
    
    public boolean isSticky() {
        return this.sticky;
    }
    
    public void setSticky(final boolean sticky) {
        this.sticky = sticky;
        this.repaint();
    }
    
    public boolean isShowBorderOnFocus() {
        return this.showBorderOnFocus;
    }
    
    public void setShowBorderOnFocus(final boolean showBorderOnFocus) {
        this.showBorderOnFocus = showBorderOnFocus;
        this.repaint();
    }
    
    public void setInsets(final int topInsets, final int leftInsets, final int bottomInsets, final int rightInsets) {
        this.topInsets = topInsets;
        this.leftInsets = leftInsets;
        this.bottomInsets = bottomInsets;
        this.rightInsets = rightInsets;
    }
    
    public void setInsets(final Insets insets) {
        this.topInsets = insets.top;
        this.leftInsets = insets.left;
        this.bottomInsets = insets.bottom;
        this.rightInsets = insets.right;
    }
    
    public Insets getInsets() {
        return new Insets(this.topInsets, this.leftInsets, this.bottomInsets, this.rightInsets);
    }
    
    public void setImages(final Image image, final Image downImage, final Image focusImage) {
        this.image = image;
        this.downImage = downImage;
        this.focusImage = focusImage;
    }
    
    public void setLabel(final String label) {
        this.label = label;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setFont(final Font font) {
        this.font = font;
    }
    
    public Font getFont() {
        return this.font;
    }
    
    public void setFocusFont(final Font focusFont) {
        this.focusFont = focusFont;
    }
    
    public Font getFocusFont() {
        return this.focusFont;
    }
    
    public void setLabelFocusColor(final Color labelFocusColor) {
        this.labelFocusColor = labelFocusColor;
    }
    
    public Color getLabelFocusColor() {
        return this.labelFocusColor;
    }
    
    public void setBorderType(final int borderType) {
        this.borderType = borderType;
        this.repaint();
    }
    
    public int getBorderType() {
        return this.borderType;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
