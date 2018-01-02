// 
// Decompiled by Procyon v0.5.30
// 

package com.pzzl.utils;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

public class ImageButton extends Canvas
{
    public boolean maintainState;
    public boolean lowerButton;
    public int mouseXPosition;
    public int mouseYPosition;
    boolean \u00ef;
    boolean hideString;
    Color \u00cd;
    final int \u00f0 = 1;
    final int \u00f1 = 2;
    private int \u00f2;
    Image \u00f3;
    Image \u00f4;
    Image \u00f5;
    Image \u00f6;
    Image \u00f8;
    String \u00c3;
    
    public ImageButton() {
        this.maintainState = false;
        this.lowerButton = false;
        this.\u00ef = false;
        this.hideString = false;
        this.\u00cd = Color.black;
        this.\u00f4 = this.createImage(this.size().width, this.size().height);
        this.\u00f5 = this.createImage(this.size().width, this.size().height);
        this.\u00f6 = this.createImage(this.size().width, this.size().height);
        this.\u00f3 = this.createImage(this.size().width, this.size().height);
        this.\u00f2 = 1;
    }
    
    public ImageButton(final String string) {
        this.maintainState = false;
        this.lowerButton = false;
        this.\u00ef = false;
        this.hideString = false;
        this.\u00cd = Color.black;
        this.\u00f4 = this.createImage(this.size().width, this.size().height);
        this.\u00f5 = this.createImage(this.size().width, this.size().height);
        this.\u00f6 = this.createImage(this.size().width, this.size().height);
        this.\u00f3 = this.createImage(this.size().width, this.size().height);
        this.\u00f2 = 1;
        this.setString(string);
    }
    
    public ImageButton(final Image image) {
        this.maintainState = false;
        this.lowerButton = false;
        this.\u00ef = false;
        this.hideString = false;
        this.\u00cd = Color.black;
        this.\u00f4 = image;
        this.\u00f5 = image;
        this.\u00f6 = image;
        this.\u00f3 = image;
        this.\u00f2 = 1;
    }
    
    public ImageButton(final Image image, final Image \u00f6, final Image \u00f4) {
        this.maintainState = false;
        this.lowerButton = false;
        this.\u00ef = false;
        this.hideString = false;
        this.\u00cd = Color.black;
        this.\u00f4 = \u00f4;
        this.\u00f5 = image;
        this.\u00f6 = \u00f6;
        this.\u00f3 = image;
        this.\u00f2 = 1;
    }
    
    public ImageButton(final Image image, final Image \u00f6, final Image \u00f4, final Image \u00f8) {
        this.maintainState = false;
        this.lowerButton = false;
        this.\u00ef = false;
        this.hideString = false;
        this.\u00cd = Color.black;
        this.\u00f4 = \u00f4;
        this.\u00f5 = image;
        this.\u00f6 = \u00f6;
        this.\u00f8 = \u00f8;
        this.\u00f3 = image;
        this.\u00f2 = 1;
    }
    
    public void setBorder(final boolean \u00ef) {
        this.\u00ef = \u00ef;
    }
    
    public void setBorderColor(final Color \u00ed) {
        this.\u00cd = \u00ed;
    }
    
    public void setMaintainState(final boolean maintainState) {
        this.maintainState = maintainState;
    }
    
    public void setString(final String \u00e3) {
        this.\u00c3 = \u00e3;
    }
    
    public void hideString() {
        this.hideString = true;
    }
    
    public void setImages(final Image image, final Image \u00f6, final Image \u00f4) {
        this.\u00f4 = \u00f4;
        this.\u00f5 = image;
        this.\u00f6 = \u00f6;
        this.\u00f3 = image;
        this.\u00f2 = 1;
    }
    
    public void setImages(final Image image, final Image \u00f6, final Image \u00f4, final Image \u00f8) {
        this.\u00f4 = \u00f4;
        this.\u00f5 = image;
        this.\u00f6 = \u00f6;
        this.\u00f8 = \u00f8;
        this.\u00f3 = image;
        this.\u00f2 = 1;
    }
    
    public void setMouseEnter() {
        if (!this.maintainState) {
            this.\u00f3 = this.\u00f4;
        }
        else if (this.\u00f2 != 0) {
            this.\u00f3 = this.\u00f4;
        }
        this.repaint();
    }
    
    public void setMouseExit() {
        if (!this.maintainState) {
            if (this.\u00f2 == 0) {
                this.\u00f3 = this.\u00f4;
            }
            if (this.\u00f2 == 2) {
                this.\u00f3 = this.\u00f8;
            }
            if (this.\u00f2 == 1) {
                this.\u00f3 = this.\u00f5;
            }
            this.repaint();
            return;
        }
        if (this.\u00f2 != 0) {
            if (this.\u00f2 == 0) {
                this.\u00f3 = this.\u00f4;
            }
            if (this.\u00f2 == 2) {
                this.\u00f3 = this.\u00f8;
            }
            if (this.\u00f2 == 1) {
                this.\u00f3 = this.\u00f5;
            }
            this.repaint();
        }
    }
    
    public void setMouseUp() {
        this.\u00f2 = 1;
        this.\u00f3 = this.\u00f5;
        this.repaint();
    }
    
    public boolean mouseDown() {
        return this.\u00f2 == 0;
    }
    
    public void setMouseDown() {
        if (this.\u00f2 != 2) {
            if (!this.maintainState) {
                this.\u00f2 = 0;
                this.\u00f3 = this.\u00f6;
            }
            else if (this.\u00f2 == 0) {
                this.\u00f2 = 1;
                this.\u00f3 = this.\u00f5;
            }
            else {
                this.\u00f2 = 0;
                this.\u00f3 = this.\u00f6;
            }
            this.repaint();
        }
    }
    
    public void disable() {
        super.disable();
        this.\u00f2 = 2;
        this.\u00f3 = this.\u00f8;
        this.repaint();
    }
    
    public void enable() {
        super.enable();
        this.\u00f3 = this.\u00f5;
        this.\u00f2 = 1;
        this.repaint();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.setMouseEnter();
        return false;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
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
        if (this.\u00f3 != null) {
            if (!this.lowerButton) {
                graphics.clearRect(0, 0, width, height);
                graphics.drawImage(this.\u00f3, 0, 0, width, height, this);
            }
            else {
                graphics.drawImage(this.\u00f3, 0, 0, width, height, this);
                this.lowerButton = false;
            }
        }
        if (this.\u00c3 != null && !this.hideString) {
            graphics.drawString(this.\u00c3, this.size().width / 2 - this.getFontMetrics(this.getFont()).stringWidth(this.\u00c3) / 2, this.size().height / 2 + this.getFont().getSize() / 2 - 2);
        }
        if (this.\u00ef) {
            graphics.setColor(this.\u00cd);
            graphics.drawRect(0, 0, width - 1, height - 1);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
