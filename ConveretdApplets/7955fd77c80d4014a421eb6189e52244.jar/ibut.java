import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class ibut extends Canvas
{
    protected Image m_imageOff;
    protected Image m_imageOn;
    protected Image m_imageClik;
    protected AudioClip m_audioclip;
    protected String name;
    protected boolean selected;
    protected boolean clicked;
    
    public ibut(final Image imageOn, final Image imageOff, final Image imageClik, final AudioClip audioclip, final String name) {
        this.selected = false;
        this.clicked = false;
        this.m_imageOff = imageOff;
        this.m_imageOn = imageOn;
        this.m_imageClik = imageClik;
        this.m_audioclip = audioclip;
        this.name = name;
    }
    
    public void paint(final Graphics graphics) {
        if (this.clicked) {
            this.paintClicked(graphics);
            return;
        }
        if (this.selected) {
            this.paintSelected(graphics);
            return;
        }
        this.paintUnSelected(graphics);
    }
    
    protected void paintClicked(final Graphics graphics) {
        this.setBackground(Color.black);
        graphics.drawImage(this.m_imageClik, 1, 1, this);
    }
    
    protected void paintSelected(final Graphics graphics) {
        this.setBackground(Color.black);
        graphics.drawImage(this.m_imageOn, 1, 1, this);
    }
    
    protected void paintUnSelected(final Graphics graphics) {
        this.setBackground(Color.black);
        graphics.drawImage(this.m_imageOff, 1, 1, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.selected) {
            this.selected = false;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.selected) {
            this.selected = true;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.selected) {
            this.clicked = true;
            this.repaint();
            this.deliverEvent(new Event(this, 1001, this.name));
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.clicked) {
            this.clicked = false;
            this.repaint();
        }
        return true;
    }
}
