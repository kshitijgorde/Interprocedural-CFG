// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Canvas;

public class ImageButton extends Canvas
{
    public boolean maintainState;
    public boolean lowerButton;
    public int mouseXPosition;
    public int mouseYPosition;
    boolean \u00c3;
    boolean hideString;
    final int \u00c4 = 1;
    final int \u00c5 = 2;
    private int \u00c6;
    Image \u00c7;
    Image \u00c8;
    Image \u00c9;
    Image \u00ca;
    Image \u00cb;
    String \u00cc;
    
    public ImageButton() {
        this.maintainState = false;
        this.lowerButton = false;
        this.\u00c3 = false;
        this.hideString = false;
        this.\u00c8 = this.createImage(this.size().width, this.size().height);
        this.\u00c9 = this.createImage(this.size().width, this.size().height);
        this.\u00ca = this.createImage(this.size().width, this.size().height);
        this.\u00c7 = this.createImage(this.size().width, this.size().height);
        this.\u00c6 = 1;
    }
    
    public ImageButton(final Image image) {
        this.maintainState = false;
        this.lowerButton = false;
        this.\u00c3 = false;
        this.hideString = false;
        this.\u00c8 = image;
        this.\u00c9 = image;
        this.\u00ca = image;
        this.\u00c7 = image;
        this.\u00c6 = 1;
    }
    
    public ImageButton(final Image image, final Image \u00ea, final Image \u00e8) {
        this.maintainState = false;
        this.lowerButton = false;
        this.\u00c3 = false;
        this.hideString = false;
        this.\u00c8 = \u00e8;
        this.\u00c9 = image;
        this.\u00ca = \u00ea;
        this.\u00c7 = image;
        this.\u00c6 = 1;
    }
    
    public ImageButton(final Image image, final Image \u00ea, final Image \u00e8, final Image \u00eb) {
        this.maintainState = false;
        this.lowerButton = false;
        this.\u00c3 = false;
        this.hideString = false;
        this.\u00c8 = \u00e8;
        this.\u00c9 = image;
        this.\u00ca = \u00ea;
        this.\u00cb = \u00eb;
        this.\u00c7 = image;
        this.\u00c6 = 1;
    }
    
    public void setBorder(final boolean \u00e3) {
        this.\u00c3 = \u00e3;
    }
    
    public void setString(final String \u00ec) {
        this.\u00cc = \u00ec;
    }
    
    public void hideString() {
        this.hideString = true;
    }
    
    public void setImages(final Image image, final Image \u00ea, final Image \u00e8) {
        this.\u00c8 = \u00e8;
        this.\u00c9 = image;
        this.\u00ca = \u00ea;
        this.\u00c7 = image;
        this.\u00c6 = 1;
    }
    
    public void setImages(final Image image, final Image \u00ea, final Image \u00e8, final Image \u00eb) {
        this.\u00c8 = \u00e8;
        this.\u00c9 = image;
        this.\u00ca = \u00ea;
        this.\u00cb = \u00eb;
        this.\u00c7 = image;
        this.\u00c6 = 1;
    }
    
    public void setMouseEnter() {
        if (!this.maintainState) {
            this.\u00c7 = this.\u00c8;
        }
        else if (this.\u00c6 != 0) {
            this.\u00c7 = this.\u00c8;
        }
        this.repaint();
    }
    
    public void setMouseExit() {
        if (!this.maintainState) {
            if (this.\u00c6 == 0) {
                this.\u00c7 = this.\u00c8;
            }
            if (this.\u00c6 == 2) {
                this.\u00c7 = this.\u00cb;
            }
            if (this.\u00c6 == 1) {
                this.\u00c7 = this.\u00c9;
            }
            this.repaint();
            return;
        }
        if (this.\u00c6 != 0) {
            if (this.\u00c6 == 0) {
                this.\u00c7 = this.\u00c8;
            }
            if (this.\u00c6 == 2) {
                this.\u00c7 = this.\u00cb;
            }
            if (this.\u00c6 == 1) {
                this.\u00c7 = this.\u00c9;
            }
            this.repaint();
        }
    }
    
    public void setMouseUp() {
        if (!this.maintainState) {
            this.\u00c6 = 1;
            this.\u00c7 = this.\u00c9;
        }
        this.repaint();
    }
    
    public void setMouseDown() {
        if (!this.maintainState) {
            this.\u00c6 = 0;
            this.\u00c7 = this.\u00ca;
        }
        else if (this.\u00c6 == 0) {
            this.\u00c6 = 1;
            this.\u00c7 = this.\u00c9;
        }
        else {
            this.\u00c6 = 0;
            this.\u00c7 = this.\u00ca;
        }
        this.repaint();
    }
    
    public void disable() {
        super.disable();
        this.\u00c6 = 2;
        this.\u00c7 = this.\u00cb;
        this.repaint();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        System.out.println("mouseMove ImagePanel [" + n + "][" + n2 + "]");
        this.setMouseEnter();
        return false;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        System.out.println("mouseEnter ImagePanel [" + n + "][" + n2 + "]");
        this.setMouseEnter();
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        System.out.println("mouseExit ImagePanel [" + n + "][" + n2 + "]");
        this.setMouseExit();
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        return false;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        return false;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        if (this.\u00c7 != null) {
            if (!this.lowerButton) {
                graphics.clearRect(0, 0, width, height);
                graphics.drawImage(this.\u00c7, 0, 0, width, height, this);
            }
            else {
                graphics.drawImage(this.\u00c7, 0, 0, width, height, this);
                graphics.drawImage(this.\u00c9, this.mouseXPosition - width / 2 - 1, this.mouseYPosition - height / 2 - 1, width + 1, height + 2, this);
                this.lowerButton = false;
            }
        }
        if (this.\u00cc != null && !this.hideString) {
            graphics.drawString(this.\u00cc, this.size().width / 2 - this.getFontMetrics(this.getFont()).stringWidth(this.\u00cc) / 2, this.size().height / 2 + this.getFont().getSize() / 2 - 2);
        }
        if (this.\u00c3) {
            graphics.drawRect(0, 0, width, height);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
