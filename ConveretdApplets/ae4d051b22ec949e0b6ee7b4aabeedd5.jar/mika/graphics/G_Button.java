// 
// Decompiled by Procyon v0.5.30
// 

package mika.graphics;

import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

public class G_Button extends Canvas
{
    Image[] m_images;
    String m_strID;
    boolean m_bEnabled;
    boolean m_bPressed;
    boolean m_bFocus;
    
    public G_Button(final String strID, final Image image, final Image image2, final Image image3, final Image image4) {
        this.m_images = new Image[4];
        this.m_strID = "";
        this.m_bEnabled = true;
        this.m_bPressed = false;
        this.m_bFocus = false;
        this.setImages(image, image2, image3, image4);
        this.m_strID = strID;
    }
    
    public G_Button(final String strID) {
        this.m_images = new Image[4];
        this.m_strID = "";
        this.m_bEnabled = true;
        this.m_bPressed = false;
        this.m_bFocus = false;
        final Image[] images = this.m_images;
        final int n = 0;
        final Image[] images2 = this.m_images;
        final int n2 = 1;
        final Image[] images3 = this.m_images;
        final int n3 = 2;
        final Image[] images4 = this.m_images;
        final int n4 = 3;
        final Image image = null;
        images3[n3] = (images4[n4] = image);
        images[n] = (images2[n2] = image);
        this.m_strID = strID;
    }
    
    public G_Button() {
        this.m_images = new Image[4];
        this.m_strID = "";
        this.m_bEnabled = true;
        this.m_bPressed = false;
        this.m_bFocus = false;
        final Image[] images = this.m_images;
        final int n = 0;
        final Image[] images2 = this.m_images;
        final int n2 = 1;
        final Image[] images3 = this.m_images;
        final int n3 = 2;
        final Image[] images4 = this.m_images;
        final int n4 = 3;
        final Image image = null;
        images3[n3] = (images4[n4] = image);
        images[n] = (images2[n2] = image);
        this.m_strID = null;
    }
    
    public void setImages(final Image image, final Image image2, final Image image3, final Image image4) {
        this.m_images[0] = image;
        this.m_images[1] = image2;
        this.m_images[2] = image3;
        this.m_images[3] = image4;
    }
    
    public boolean isEnabled() {
        return this.m_bEnabled;
    }
    
    public void enable(final boolean bEnabled) {
        this.m_bEnabled = bEnabled;
        this.m_bPressed = false;
        this.m_bFocus = false;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.m_bEnabled && this.m_images[3] != null) {
            graphics.drawImage(this.m_images[3], 0, 0, this);
        }
        else if (this.m_bPressed && this.m_bFocus && this.m_images[2] != null) {
            graphics.drawImage(this.m_images[2], 0, 0, this);
        }
        else if (this.m_bFocus && this.m_images[1] != null) {
            graphics.drawImage(this.m_images[1], 0, 0, this);
        }
        else if (this.m_images[0] != null) {
            graphics.drawImage(this.m_images[0], 0, 0, this);
        }
    }
    
    public void pressed(final int n, final int n2) {
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.m_bEnabled) {
            this.m_bPressed = true;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        return this.mouseDown(event, n, n2);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.m_bEnabled) {
            if (this.m_bPressed && this.m_bFocus) {
                if (this.m_strID != null) {
                    this.postEvent(new Event(this, 1001, this.m_strID));
                }
                this.pressed(n, n2);
            }
            this.m_bPressed = false;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.m_bEnabled) {
            this.m_bFocus = true;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.m_bEnabled) {
            this.m_bFocus = false;
            this.m_bPressed = false;
            this.repaint();
        }
        return true;
    }
}
