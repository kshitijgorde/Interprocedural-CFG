// 
// Decompiled by Procyon v0.5.30
// 

package com.jafar.ui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.AWTEventMulticaster;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Canvas;

public class ImageButton extends Canvas
{
    public static final int NORMAL = 0;
    public static final int PRESSED = 1;
    public static final int OVER = 2;
    public static final int DISABLED = 3;
    private Image disImage;
    private Image enImage;
    private Image overImage;
    private String label;
    private Font labelFont;
    private FontMetrics fm;
    private Color labelColor;
    private int buttonState;
    private boolean isToggle;
    private boolean toggleState;
    private boolean isDisabled;
    private boolean isButtonSquare;
    private boolean isBorderPainted;
    private int borderType;
    private int prevBorderType;
    private int borderWidth;
    private int prevBorderWidth;
    private int buttonWidth;
    private int buttonHeight;
    private Insets margin;
    private Insets defaultMargin;
    private Dimension marginAvg;
    private int hLabelPos;
    private int vLabelPos;
    private int labelWidth;
    private int labelHeight;
    private int labelDescent;
    public static final int BORDER_NONE = 0;
    public static final int BORDER_RAISED = 1;
    public static final int BORDER_LOWERED = 2;
    public static final int BORDER_SIMPLE = 3;
    public static final int LEFT = 0;
    public static final int CENTER = 1;
    public static final int RIGHT = 2;
    public static final int TOP = 0;
    public static final int BOTTOM = 2;
    private static final double BRIGHTNESS_FACTOR = 0.8;
    private static final double DARKNESS_FACTOR = 0.6;
    private MediaTracker tracker;
    private Image dBuf;
    private Graphics gBuf;
    ActionListener actionListener;
    
    public ImageButton() {
        this.isToggle = false;
        this.isDisabled = false;
        this.isButtonSquare = false;
        this.isBorderPainted = true;
        this.defaultMargin = new Insets(5, 5, 5, 5);
        this.tracker = new MediaTracker(this);
        this.buttonState = 0;
        this.borderType = 0;
        this.borderWidth = 1;
        this.margin = this.defaultMargin;
        this.marginAvg = new Dimension((this.margin.left + this.margin.right) / 2, (this.margin.top + this.margin.bottom) / 2);
        this.label = "";
        this.labelFont = new Font("Monospace", 0, 12);
        this.labelColor = Color.black;
        this.hLabelPos = 1;
        this.vLabelPos = 2;
        this.enableEvents(16L);
    }
    
    public ImageButton(final Image image) {
        this();
        this.setImage(image);
    }
    
    public ImageButton(final Image image, final String label) {
        this();
        this.label = label;
        this.setImage(image);
    }
    
    public void setEnabled(final boolean b) {
        this.isDisabled = !b;
        if (this.isDisabled) {
            this.buttonState = 3;
        }
        else {
            this.buttonState = 0;
        }
        if (this.disImage == null && this.enImage != null) {
            this.disImage = this.createImage(new FilteredImageSource(this.enImage.getSource(), new DisableImageFilter()));
        }
        this.repaint();
    }
    
    public void setActive(final boolean b) {
        if (b) {
            this.borderType = 0;
            this.borderWidth = 0;
        }
        this.repaint();
    }
    
    public void setImage(final Image enImage) {
        this.enImage = enImage;
        this.tracker.addImage(this.enImage, 0);
        this.tracker.checkID(0, true);
        if (this.disImage == null) {
            this.disImage = this.createImage(new FilteredImageSource(this.enImage.getSource(), new DisableImageFilter()));
        }
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        if (this.dBuf == null || this.gBuf == null || bounds.width != this.dBuf.getWidth(this) || bounds.height != this.dBuf.getHeight(this)) {
            this.dBuf = this.createImage(bounds.width, bounds.height);
            this.gBuf = this.dBuf.getGraphics();
        }
        this.paintButton(this.gBuf);
        graphics.drawImage(this.dBuf, 0, 0, this);
    }
    
    private void paintButton(final Graphics graphics) {
        int width = 0;
        int height = 0;
        int n = 0;
        int n2 = 0;
        int n3 = 0;
        int n4 = 0;
        final int width2 = this.getBounds().width;
        final int height2 = this.getBounds().height;
        final Color background = this.getBackground();
        this.getSize();
        if (this.enImage != null) {
            try {
                if (!this.tracker.checkID(0)) {
                    this.tracker.waitForID(0);
                }
                if (!this.tracker.isErrorID(0)) {
                    width = this.enImage.getWidth(this);
                    height = this.enImage.getHeight(this);
                }
            }
            catch (InterruptedException ex) {}
        }
        else {
            width = 10;
            height = 10;
        }
        graphics.setColor(background);
        graphics.fillRect(0, 0, width2, height2);
        this.drawBorder(graphics, background, 0, 0, width2, height2);
        if (this.hLabelPos == 1) {
            n = (width2 - width) / 2;
            n3 = (width2 - this.labelWidth) / 2;
            if (this.vLabelPos == 0) {
                n2 = this.borderWidth + this.margin.top + this.labelHeight + this.marginAvg.height / 2;
                n4 = this.borderWidth + this.margin.top + this.labelHeight - this.labelDescent;
            }
            else if (this.vLabelPos == 1 || this.vLabelPos == 2) {
                n2 = this.borderWidth + this.margin.top;
                n4 = n2 + height + this.marginAvg.height / 2 + this.labelHeight - this.labelDescent;
            }
        }
        else if (this.hLabelPos == 0) {
            n3 = this.borderWidth + this.margin.left;
            n = n3 + this.labelWidth + this.marginAvg.width / 2;
            if (this.vLabelPos == 0) {
                n4 = this.borderWidth + this.margin.top + this.labelHeight - this.labelDescent;
                n2 = n4 + this.marginAvg.height / 2;
            }
            else if (this.vLabelPos == 1) {
                n2 = (height2 - height) / 2;
                n4 = (height2 + this.labelHeight) / 2 - this.labelDescent;
            }
            else if (this.vLabelPos == 2) {
                n2 = this.borderWidth + this.margin.top;
                n4 = n2 + height + this.marginAvg.height / 2 + this.labelHeight - this.labelDescent;
            }
        }
        else if (this.hLabelPos == 2) {
            n = this.borderWidth + this.margin.left;
            n3 = n + width + this.marginAvg.width / 2;
            if (this.vLabelPos == 0) {
                n4 = this.borderWidth + this.margin.top + this.labelHeight - this.labelDescent;
                n2 = n4 + this.marginAvg.height / 2;
            }
            else if (this.vLabelPos == 1) {
                n2 = (height2 - height) / 2;
                n4 = (height2 + this.labelHeight) / 2;
            }
            else if (this.vLabelPos == 2) {
                n2 = this.borderWidth + this.margin.top;
                n4 = n2 + height + this.marginAvg.height / 2 + this.labelHeight - this.labelDescent;
            }
        }
        if (this.isDisabled && this.disImage != null) {
            graphics.drawImage(this.disImage, n, n2, this);
        }
        else if (this.enImage != null) {
            graphics.drawImage(this.enImage, n, n2, this);
        }
        if (this.label != null && this.label.length() > 0) {
            graphics.setFont(this.labelFont);
            if (this.isDisabled) {
                graphics.setColor(Color.lightGray);
            }
            else {
                graphics.setColor(this.labelColor);
            }
            graphics.drawString(this.label, n3, n4);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void drawBorder(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4) {
        int n5 = this.borderType;
        switch (this.buttonState) {
            case 2: {
                n5 = 1;
                break;
            }
            case 0: {
                n5 = this.borderType;
                break;
            }
            case 1: {
                n5 = 2;
                break;
            }
            case 3: {
                n5 = 0;
                break;
            }
        }
        switch (n5) {
            case 0: {
                graphics.setColor(color);
                graphics.fillRect(n, n2, n + n3, n2 + n4);
            }
            case 3: {
                graphics.setColor(darker(color));
                for (int i = 0; i < this.borderWidth; ++i) {
                    graphics.drawRect(n + i, n2 + i, n3 - i * 2 - 1, n4 - i * 2 - 1);
                }
            }
            case 1: {
                final Color brighter = brighter(color);
                final Color darker = darker(color);
                graphics.setColor(brighter);
                for (int j = 0; j < this.borderWidth; ++j) {
                    graphics.drawLine(n + j, n2 + j, n + n3 - j, n2 + j);
                    graphics.drawLine(n + j, n2 + j, n + j, n2 + n4 - j);
                }
                graphics.setColor(darker);
                for (int k = 0; k < this.borderWidth; ++k) {
                    graphics.drawLine(n + k, n2 + n4 - k - 1, n + n3 - k, n2 + n4 - k - 1);
                    graphics.drawLine(n + n3 - k - 1, n2 + k, n + n3 - k - 1, n2 + n4 - k);
                }
            }
            case 2: {
                final Color brighter2 = brighter(color);
                graphics.setColor(darker(color));
                for (int l = 0; l < this.borderWidth; ++l) {
                    graphics.drawLine(n + l, n2 + l, n + n3 - l, n2 + l);
                    graphics.drawLine(n + l, n2 + l, n + l, n2 + n4 - l);
                }
                graphics.setColor(brighter2);
                for (int n6 = 0; n6 < this.borderWidth; ++n6) {
                    graphics.drawLine(n + n6, n2 + n4 - n6 - 1, n + n3 - n6, n2 + n4 - n6 - 1);
                    graphics.drawLine(n + n3 - n6 - 1, n2 + n6, n + n3 - n6 - 1, n2 + n4 - n6);
                }
            }
            default: {}
        }
    }
    
    public Dimension getPreferredSize() {
        int width = 0;
        int height = 0;
        int n = 0;
        final Dimension dimension = new Dimension();
        if (this.enImage != null) {
            try {
                if (!this.tracker.checkID(0)) {
                    this.tracker.waitForID(0);
                }
                if (!this.tracker.isErrorID(0)) {
                    width = this.enImage.getWidth(this);
                    height = this.enImage.getHeight(this);
                }
            }
            catch (InterruptedException ex) {}
        }
        else {
            width = 10;
            height = 10;
        }
        if (this.label != null && this.label.length() > 0) {
            this.fm = this.getFontMetrics(this.labelFont);
            this.labelWidth = this.fm.stringWidth(this.label);
            this.labelHeight = this.fm.getHeight();
            this.labelDescent = this.fm.getDescent();
        }
        else {
            this.labelWidth = 0;
            this.labelHeight = 0;
        }
        dimension.width = this.borderWidth * 2 + this.margin.left + this.margin.right;
        int labelWidth = 0;
        if (this.hLabelPos == 1 && (this.vLabelPos == 1 || this.vLabelPos == 0 || this.vLabelPos == 2)) {
            if (this.labelWidth < width) {
                labelWidth = width;
            }
            else {
                labelWidth = this.labelWidth;
            }
            n = height + this.marginAvg.height / 2 + this.labelHeight;
        }
        if ((this.hLabelPos == 0 || this.hLabelPos == 2) && (this.vLabelPos == 0 || this.vLabelPos == 2)) {
            labelWidth = width + this.marginAvg.width / 2 + this.labelWidth;
            n = height + this.marginAvg.height / 2 + this.labelHeight;
        }
        if ((this.hLabelPos == 0 || this.hLabelPos == 2) && this.vLabelPos == 1) {
            n = height;
            labelWidth = width + this.marginAvg.width / 2 + this.labelWidth;
        }
        dimension.width = this.borderWidth + this.margin.left + labelWidth + this.margin.right + this.borderWidth + 1;
        dimension.height = this.borderWidth + this.margin.top + n + this.margin.bottom + this.borderWidth + 1;
        if (this.isButtonSquare && dimension.height > dimension.width) {
            dimension.width = dimension.height;
        }
        return dimension;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void addActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.add(this.actionListener, actionListener);
    }
    
    public void removeActionListener(final ActionListener actionListener) {
        this.actionListener = AWTEventMulticaster.remove(this.actionListener, actionListener);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (this.processIBMouseEvent(mouseEvent) && this.actionListener != null) {
            this.actionListener.actionPerformed(new ActionEvent(this, 1001, null));
        }
    }
    
    private boolean processIBMouseEvent(final MouseEvent mouseEvent) {
        if (this.isDisabled) {
            return false;
        }
        boolean b = false;
        switch (mouseEvent.getID()) {
            case 501: {
                this.setButtonState(1);
                break;
            }
            case 504: {
                this.setButtonState(2);
                break;
            }
            case 505: {
                this.setButtonState(0);
                break;
            }
            case 502: {
                final int x = mouseEvent.getX();
                final int y = mouseEvent.getY();
                final Dimension size = this.getSize();
                if (x >= 0 && y >= 0 && x <= size.width && y <= size.height) {
                    this.setButtonState(2);
                    b = true;
                    break;
                }
                this.setButtonState(0);
                break;
            }
        }
        super.processMouseEvent(mouseEvent);
        return b;
    }
    
    protected void setButtonState(final int buttonState) {
        if (!this.isToggle) {
            if (buttonState != this.buttonState) {
                this.buttonState = buttonState;
                this.repaint();
            }
        }
        else {
            this.setToggleState();
            this.repaint();
        }
    }
    
    private void setToggleState() {
        if (this.toggleState) {
            this.buttonState = 1;
            return;
        }
        this.buttonState = 0;
    }
    
    public void setBorderType(final int borderType) {
        this.borderType = borderType;
        this.repaint();
    }
    
    public int getBorderType() {
        return this.borderType;
    }
    
    public void setBorderWidth(final int borderWidth) {
        if (borderWidth >= 0) {
            this.borderWidth = borderWidth;
            this.setSize(this.getPreferredSize());
            this.invalidate();
            final Container parent = this.getParent();
            if (parent != null) {
                parent.doLayout();
            }
            this.repaint();
        }
    }
    
    public int getBorderWidth() {
        return this.borderWidth;
    }
    
    public Insets getMargin() {
        return this.margin;
    }
    
    public void setMargin(final Insets margin) {
        this.margin = margin;
        this.marginAvg.width = (margin.left + margin.right) / 2;
        this.marginAvg.height = (margin.top + margin.bottom) / 2;
        this.setSize(this.getPreferredSize());
        this.invalidate();
        final Container parent = this.getParent();
        if (parent != null) {
            parent.doLayout();
        }
        this.repaint();
    }
    
    public boolean getState() {
        return this.toggleState;
    }
    
    public void setState(final boolean toggleState) {
        if (toggleState != this.toggleState) {
            this.toggleState = toggleState;
            this.setToggleState();
        }
        this.repaint();
    }
    
    public boolean isToggleButton() {
        return this.isToggle;
    }
    
    public void setToggle(final boolean isToggle) {
        this.isToggle = isToggle;
        this.borderWidth = 1;
        this.setToggleState();
    }
    
    public void setLabel(final String label) {
        this.label = label;
        this.repaint();
    }
    
    public String getLabel() {
        return this.label;
    }
    
    public void setLabelFont(final Font labelFont) {
        this.labelFont = labelFont;
        this.repaint();
    }
    
    public Font getLabelFont() {
        return this.labelFont;
    }
    
    public void setHorizontalLabelPosition(final int hLabelPos) {
        this.hLabelPos = hLabelPos;
        this.setSize(this.getPreferredSize());
        this.invalidate();
        final Container parent = this.getParent();
        if (parent != null) {
            parent.doLayout();
        }
        this.repaint();
    }
    
    public int gethLabelPos() {
        return this.hLabelPos;
    }
    
    public void setVerticalLabelPosition(final int vLabelPos) {
        this.vLabelPos = vLabelPos;
        this.setSize(this.getPreferredSize());
        this.invalidate();
        final Container parent = this.getParent();
        if (parent != null) {
            parent.doLayout();
        }
        this.repaint();
    }
    
    public int getvLabelPos() {
        return this.vLabelPos;
    }
    
    public boolean isButtonSquare() {
        return this.isButtonSquare;
    }
    
    public void setButtonSquare(final boolean isButtonSquare) {
        this.isButtonSquare = isButtonSquare;
        this.repaint();
    }
    
    public boolean isBorderPainted() {
        return this.isBorderPainted;
    }
    
    public void setBorderPainted(final boolean isBorderPainted) {
        this.isBorderPainted = isBorderPainted;
        this.repaint();
    }
    
    private static Color brighter(final Color color) {
        final Color color2 = new Color(Math.min((int)(color.getRed() * 1.25), 255), Math.min((int)(color.getGreen() * 1.25), 255), Math.min((int)(color.getBlue() * 1.25), 255));
        if (color2.equals(color)) {
            return Color.white;
        }
        return color2;
    }
    
    private static Color darker(final Color color) {
        final Color color2 = new Color(Math.max((int)(color.getRed() * 0.6), 0), Math.max((int)(color.getGreen() * 0.6), 0), Math.max((int)(color.getBlue() * 0.6), 0));
        if (color2.equals(color)) {
            return Color.black;
        }
        return color2;
    }
}
