// 
// Decompiled by Procyon v0.5.30
// 

package util102.gui;

import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.Canvas;

public class Tab extends Canvas
{
    String label;
    boolean selected;
    boolean focus;
    Font font;
    Font focusFont;
    Color labelColor;
    Color labelFocusColor;
    boolean notab;
    Image offscreenImg;
    boolean enabled;
    
    public Tab() {
        this("Label");
    }
    
    public Tab(final String label) {
        this.selected = false;
        this.focus = false;
        this.font = new Font("Dialog", 0, 12);
        this.focusFont = new Font("Dialog", 1, 12);
        this.labelColor = Color.black;
        this.labelFocusColor = Color.black;
        this.notab = false;
        this.enabled = true;
        this.label = label;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                if (!this.enabled) {
                    return true;
                }
                break;
            }
            case 502: {
                if (!this.enabled) {
                    return true;
                }
                break;
            }
            case 504: {
                if (!this.enabled) {
                    return true;
                }
                this.focus = true;
                this.repaint();
                break;
            }
            case 505: {
                if (!this.enabled) {
                    return true;
                }
                this.focus = false;
                this.repaint();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paintTab(final Graphics graphics) {
        final Dimension size = this.size();
        final Color background = this.getBackground();
        graphics.setColor(background);
        graphics.fillRect(0, 0, size.width, size.height);
        if (this.notab) {
            graphics.setColor(background.brighter());
            graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
            return;
        }
        if (!this.enabled) {
            graphics.setColor(background.brighter());
            graphics.drawRoundRect(1, 2, size.width - 2, size.height + 10, 6, 6);
            graphics.setColor(Color.black);
            graphics.drawLine(size.width - 1, 4, size.width - 1, size.height);
            graphics.fillRect(size.width - 2, 3, 1, 1);
            graphics.setColor(background.darker());
            graphics.drawLine(size.width - 2, 4, size.width - 2, size.height);
            graphics.setColor(background.brighter());
            graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
            final FontMetrics fontMetrics = this.getFontMetrics(this.font);
            graphics.setFont(this.font);
            final int n = (size.width - fontMetrics.stringWidth(this.label)) / 2;
            final int n2 = (size.height - fontMetrics.getHeight()) / 2 + fontMetrics.getAscent();
            graphics.setColor(background.brighter());
            graphics.drawString(this.label, n + 3, n2 + 3);
            graphics.setColor(background.darker());
            graphics.drawString(this.label, n + 2, n2 + 2);
            return;
        }
        if (this.selected) {
            graphics.setColor(background.brighter());
            graphics.drawRoundRect(0, 0, size.width - 2, size.height + 10, 6, 6);
            graphics.drawLine(size.width - 2, size.height - 1, size.width, size.height - 1);
            graphics.setColor(Color.black);
            graphics.drawLine(size.width - 2, 2, size.width - 2, size.height);
            graphics.fillRect(size.width - 3, 1, 1, 1);
            graphics.setColor(background.darker());
            graphics.drawLine(size.width - 3, 2, size.width - 3, size.height);
            FontMetrics fontMetrics2;
            if (this.focus) {
                graphics.setColor(this.labelFocusColor);
                fontMetrics2 = this.getFontMetrics(this.focusFont);
                graphics.setFont(this.focusFont);
            }
            else {
                graphics.setColor(this.labelColor);
                fontMetrics2 = this.getFontMetrics(this.font);
                graphics.setFont(this.font);
            }
            graphics.drawString(this.label, (size.width - fontMetrics2.stringWidth(this.label)) / 2, (size.height - fontMetrics2.getHeight()) / 2 + fontMetrics2.getAscent());
            return;
        }
        graphics.setColor(background.brighter());
        graphics.drawRoundRect(1, 2, size.width - 2, size.height + 10, 6, 6);
        graphics.setColor(Color.black);
        graphics.drawLine(size.width - 1, 4, size.width - 1, size.height);
        graphics.fillRect(size.width - 2, 3, 1, 1);
        graphics.setColor(background.darker());
        graphics.drawLine(size.width - 2, 4, size.width - 2, size.height);
        graphics.setColor(background.brighter());
        graphics.drawLine(0, size.height - 1, size.width, size.height - 1);
        FontMetrics fontMetrics3;
        if (this.focus) {
            graphics.setColor(this.labelFocusColor);
            fontMetrics3 = this.getFontMetrics(this.focusFont);
            graphics.setFont(this.focusFont);
        }
        else {
            graphics.setColor(this.labelColor);
            fontMetrics3 = this.getFontMetrics(this.font);
            graphics.setFont(this.font);
        }
        graphics.drawString(this.label, (size.width - fontMetrics3.stringWidth(this.label)) / 2 + 2, (size.height - fontMetrics3.getHeight()) / 2 + fontMetrics3.getAscent() + 2);
    }
    
    public final void paint(final Graphics graphics) {
        final Dimension size = this.size();
        if (this.offscreenImg == null) {
            this.offscreenImg = this.createImage(size.width, size.height);
        }
        final Graphics graphics2 = this.offscreenImg.getGraphics();
        this.paintTab(graphics2);
        graphics.drawImage(this.offscreenImg, 0, 0, this);
        graphics2.dispose();
    }
    
    public void invalidate() {
        super.invalidate();
        this.offscreenImg = null;
    }
    
    public Dimension minimumSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.font);
        final FontMetrics fontMetrics2 = this.getFontMetrics(this.focusFont);
        int n;
        if (fontMetrics.stringWidth(this.label) > fontMetrics2.stringWidth(this.label)) {
            n = fontMetrics.stringWidth(this.label);
        }
        else {
            n = fontMetrics2.stringWidth(this.label);
        }
        int n2;
        if (fontMetrics.getHeight() > fontMetrics2.getHeight()) {
            n2 = fontMetrics.getHeight();
        }
        else {
            n2 = fontMetrics2.getHeight();
        }
        return new Dimension(n + 20, n2 + 6);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void setNotab(final boolean notab) {
        this.notab = notab;
        this.repaint();
    }
    
    public boolean getNotab() {
        return this.notab;
    }
    
    public void setSelected(final boolean selected) {
        this.selected = selected;
        this.repaint();
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
    
    public void setLabelColor(final Color labelColor) {
        this.labelColor = labelColor;
    }
    
    public void setLabelFocusColor(final Color labelFocusColor) {
        this.labelFocusColor = labelFocusColor;
    }
    
    public Color getLabelFocusColor() {
        return this.labelFocusColor;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        this.repaint();
    }
}
