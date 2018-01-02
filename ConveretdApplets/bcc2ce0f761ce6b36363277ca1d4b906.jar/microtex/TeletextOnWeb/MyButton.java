// 
// Decompiled by Procyon v0.5.30
// 

package microtex.TeletextOnWeb;

import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.MediaTracker;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Image;
import java.awt.Canvas;

class MyButton extends Canvas
{
    private Image[] m_ButtonImages;
    private Container m_parent;
    private Image m_image;
    private Dimension m_size;
    private int w;
    private int h;
    private int xx;
    private int yy;
    private boolean m_bTrueSizeKnown;
    public boolean m_bEnabled;
    private boolean m_mouseIn;
    private MediaTracker tracker;
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public Dimension minimumSize() {
        return this.m_size;
    }
    
    public void NewLocate(final int x, final int y) {
        this.xx = x;
        this.yy = y;
        this.repaint();
    }
    
    public void setEnabled(final boolean Abilitato) {
        this.m_bEnabled = Abilitato;
        if (this.m_bEnabled) {
            if (this.m_mouseIn) {
                this.m_image = this.m_ButtonImages[2];
            }
            else {
                this.m_image = this.m_ButtonImages[1];
            }
        }
        else {
            this.m_image = this.m_ButtonImages[0];
        }
        this.repaint();
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (this.m_bEnabled) {
            this.m_image = this.m_ButtonImages[3];
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseUp(final Event evt, final int x, final int y) {
        if (this.m_bEnabled) {
            this.m_image = this.m_ButtonImages[2];
            this.repaint();
            return false;
        }
        return true;
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        if (this.m_bEnabled) {
            this.m_image = this.m_ButtonImages[2];
            this.repaint();
        }
        return this.m_mouseIn = true;
    }
    
    public boolean mouseExit(final Event evt, final int x, final int y) {
        if (this.m_bEnabled) {
            this.m_image = this.m_ButtonImages[1];
            this.repaint();
        }
        this.m_mouseIn = false;
        return true;
    }
    
    public void paint(final Graphics g) {
        if (this.m_image != null && !this.m_bTrueSizeKnown) {
            final int imageWidth = this.m_image.getWidth(this);
            final int imageHeight = this.m_image.getHeight(this);
            if (this.tracker.checkAll(true)) {
                this.m_bTrueSizeKnown = true;
                if (this.tracker.isErrorAny()) {
                    System.err.println("Error loading image: " + this.m_image);
                }
            }
            if ((imageWidth > 0 && this.w != imageWidth) || (imageHeight > 0 && this.h != imageHeight)) {
                this.w = imageWidth;
                this.h = imageHeight;
                this.m_size = new Dimension(this.w, this.h);
                this.m_parent.validate();
                g.drawString("ddd", 20, 20);
            }
        }
        g.drawImage(this.m_image, 0, 0, this);
    }
    
    public MyButton(final Image[] immagini, final Container highestContainer, final int initialWidth, final int initialHeight, final int x, final int y, final boolean Initiallym_bEnabled) {
        this.m_ButtonImages = new Image[4];
        this.m_mouseIn = false;
        this.tracker = new MediaTracker(this);
        for (int k = 0; k < this.m_ButtonImages.length; ++k) {
            this.m_ButtonImages[k] = immagini[k];
        }
        this.m_parent = highestContainer;
        if (Initiallym_bEnabled) {
            this.m_image = this.m_ButtonImages[1];
        }
        else {
            this.m_image = this.m_ButtonImages[0];
        }
        this.w = initialWidth;
        this.h = initialHeight;
        this.xx = x;
        this.yy = y;
        this.m_bEnabled = Initiallym_bEnabled;
        this.m_bTrueSizeKnown = false;
        this.m_size = new Dimension(this.w, this.h);
    }
}
