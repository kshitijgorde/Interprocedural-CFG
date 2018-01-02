// 
// Decompiled by Procyon v0.5.30
// 

package hhapplet;

import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.Font;

public class TabButton extends CanvasButton
{
    protected String label;
    protected int x_offset;
    protected int y_offset;
    protected Font active;
    protected Font inactive;
    protected int tab_x;
    protected int tab_y;
    protected int tab_width;
    protected int tab_height;
    protected boolean m_bDrawLeft;
    protected boolean m_bDrawRight;
    protected boolean m_bActive;
    protected boolean m_bFocused;
    protected TabManagerAdaper m_manager;
    protected boolean m_bCenterBroken;
    
    protected void centerText(final Font font) {
        final Rectangle bounds = this.bounds();
        final FontMetrics fontMetrics = this.getFontMetrics(font);
        final int stringWidth = fontMetrics.stringWidth(this.label);
        final int maxAscent = fontMetrics.getMaxAscent();
        this.y_offset = bounds.height - maxAscent;
        this.y_offset = this.y_offset / 2 + maxAscent;
        this.x_offset = bounds.width - stringWidth;
        this.x_offset /= 2;
        if (this.m_bCenterBroken && this.isActived()) {
            this.x_offset += 3;
        }
    }
    
    public void reshape(final int tab_x, final int tab_y, final int tab_width, final int tab_height) {
        super.reshape(tab_x, tab_y, tab_width, tab_height);
        this.tab_x = tab_x;
        this.tab_y = tab_y;
        this.tab_width = tab_width;
        this.tab_height = tab_height;
        this.centerText(this.getFont());
    }
    
    public void disactive() {
        if (this.isActived()) {
            this.setFont(this.inactive);
            super.reshape(this.tab_x, this.tab_y, this.tab_width, this.tab_height);
            this.centerText(this.inactive);
            this.repaint();
            this.m_bActive = false;
        }
    }
    
    public boolean isFocusTraversable() {
        return this.isActived();
    }
    
    public TabButton(final String label, final TabManagerAdaper manager) {
        this.x_offset = -1;
        this.y_offset = -1;
        this.m_bDrawLeft = true;
        this.m_bDrawRight = true;
        this.m_bActive = false;
        this.m_bFocused = false;
        this.m_manager = null;
        this.m_bCenterBroken = false;
        this.m_manager = manager;
        int getFontSize = BsscFontFixPatch.GetFontSize();
        this.active = new Font(BsscFontFixPatch.GetFontName(), 1, getFontSize);
        while (this.active.getSize() != getFontSize && getFontSize < BsscFontFixPatch.GetFontSize() + 10) {
            ++getFontSize;
            this.active = new Font(BsscFontFixPatch.GetFontName(), 1, getFontSize);
        }
        this.setFont(this.inactive = new Font(BsscFontFixPatch.GetFontName(), 0, getFontSize));
        this.label = label;
        if (System.getProperty("java.vendor").startsWith("Netscape") && System.getProperty("os.name").startsWith("Windows 95")) {
            try {
                final String property = System.getProperty("java.version");
                if (property.startsWith("1.02")) {
                    this.m_bCenterBroken = true;
                }
                final Integer n = new Integer(property.substring(0, 1));
                final Integer n2 = new Integer(property.substring(2, 3));
                final Integer n3 = new Integer(property.substring(4, 5));
                if (n <= 1 && n2 <= 1 && n3 <= 2) {
                    this.m_bCenterBroken = true;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        try {
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.bounds().width, this.bounds().height);
            if (super.img != null) {
                graphics.drawImage(super.img, 2, 2, null);
            }
            else if (this.label != null) {
                this.centerText(this.getFont());
                graphics.setColor(this.getForeground());
                graphics.setFont(this.getFont());
                graphics.drawString(this.label, this.x_offset, this.y_offset);
                if (this.m_bFocused) {
                    graphics.drawRect(3, 3, this.bounds().width - 7, this.bounds().height - 7);
                }
            }
            this.paintBorderOut(graphics);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isActived() {
        return this.m_bActive;
    }
    
    public void paintBorderOut(final Graphics graphics) {
        final Rectangle bounds = this.bounds();
        if (this.isActived()) {
            graphics.setColor(Color.white);
            graphics.drawLine(2, 0, bounds.width - 3, 0);
            graphics.drawLine(1, 1, 1, 1);
            graphics.drawLine(0, 2, 0, bounds.height - 1);
            graphics.setColor(Color.white);
            graphics.drawLine(2, 0, bounds.width - 3, 0);
        }
        else if (this.m_bDrawLeft) {
            graphics.setColor(Color.white);
            graphics.drawLine(2, 3, bounds.width - 3, 3);
            graphics.drawLine(1, 4, 1, 4);
            graphics.drawLine(0, 5, 0, bounds.height - 1);
        }
        else {
            graphics.setColor(Color.white);
            graphics.drawLine(0, 3, bounds.width - 3, 3);
        }
        if (this.isActived()) {
            graphics.setColor(Color.darkGray);
            graphics.drawLine(bounds.width - 2, 2, bounds.width - 2, bounds.height - 1);
            graphics.setColor(Color.black);
            graphics.drawLine(bounds.width - 2, 1, bounds.width - 2, 1);
            graphics.drawLine(bounds.width - 1, 2, bounds.width - 1, bounds.height - 1);
        }
        else if (this.m_bDrawRight) {
            graphics.setColor(Color.darkGray);
            graphics.drawLine(bounds.width - 2, 5, bounds.width - 2, bounds.height - 1);
            graphics.setColor(Color.black);
            graphics.drawLine(bounds.width - 2, 4, bounds.width - 2, 4);
            graphics.drawLine(bounds.width - 1, 5, bounds.width - 1, bounds.height - 1);
        }
        else {
            graphics.setColor(Color.white);
            graphics.drawLine(2, 3, bounds.width - 1, 3);
        }
        if (!this.isActived()) {
            graphics.setColor(Color.white);
            graphics.drawLine(0, bounds.height - 1, bounds.width - 1, bounds.height - 1);
            graphics.setColor(this.getBackground());
            graphics.drawLine(0, 0, bounds.width - 1, 0);
            graphics.drawLine(0, 1, bounds.width - 1, 1);
            graphics.drawLine(0, 2, bounds.width - 1, 2);
        }
    }
    
    public void active() {
        if (!this.isActived()) {
            this.setFont(this.active);
            super.reshape(this.tab_x, this.tab_y, this.tab_width, this.tab_height);
            this.centerText(this.active);
            this.repaint();
            this.m_bActive = true;
        }
    }
    
    public void SetDrawRight(final boolean bDrawRight) {
        this.m_bDrawRight = bDrawRight;
        this.repaint();
    }
    
    public Dimension preferredSize() {
        try {
            if (super.img != null) {
                return new Dimension(super.img.getWidth(this) + 4, super.img.getHeight(this) + 4);
            }
            if (this.label != null) {
                final FontMetrics fontMetrics = this.getFontMetrics(this.active);
                final FontMetrics fontMetrics2 = this.getFontMetrics(this.inactive);
                int n = (fontMetrics.stringWidth(this.label) > fontMetrics2.stringWidth(this.label)) ? fontMetrics.stringWidth(this.label) : fontMetrics2.stringWidth(this.label);
                final int n2 = (fontMetrics.getMaxAscent() > fontMetrics2.getMaxAscent()) ? fontMetrics.stringWidth(this.label) : fontMetrics2.stringWidth(this.label);
                final int n3 = (fontMetrics.getDescent() > fontMetrics2.getDescent()) ? fontMetrics.getDescent() : fontMetrics2.getDescent();
                n += 20;
                return new Dimension(n, n2 + (8 + n3));
            }
            return new Dimension(20, 20);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return new Dimension(20, 20);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this) {
            if (event.id == 501) {
                this.requestFocus();
                return super.handleEvent(event);
            }
            if (event.id == 1005) {
                this.m_bFocused = false;
                this.repaint();
            }
            else if (event.id == 1004) {
                this.m_bFocused = true;
                this.repaint();
            }
            else if (event.key == 1006 && event.id == 403) {
                if (this.m_manager != null) {
                    return this.m_manager.GoPrev(this);
                }
            }
            else {
                if (event.key != 1007 || event.id != 403) {
                    return super.handleEvent(event);
                }
                if (this.m_manager != null) {
                    return this.m_manager.GoNext(this);
                }
            }
        }
        return true;
    }
    
    public void SetDrawLeft(final boolean bDrawLeft) {
        this.m_bDrawLeft = bDrawLeft;
        this.repaint();
    }
}
