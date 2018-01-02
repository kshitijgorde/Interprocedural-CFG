import java.awt.image.ImageObserver;
import java.awt.Event;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class SG1_Button extends Applet
{
    int controler;
    Image normal;
    Image hover;
    Image ofs;
    int FontSize;
    String label;
    Point position;
    Font font;
    Color color;
    Graphics g;
    Graphics b;
    AudioClip snd;
    
    public boolean mouseEnter(final Event ev, final int x, final int y) {
        this.controler = 2;
        this.repaint();
        this.snd.play();
        return true;
    }
    
    public boolean mouseExit(final Event ev, final int x, final int y) {
        this.controler = 1;
        this.repaint();
        return true;
    }
    
    public void paint(final Graphics g) {
        g.setFont(this.font);
        if (this.controler == 1) {
            g.drawImage(this.normal, 0, 0, this);
            g.setColor(Color.black);
            g.drawString(this.label, this.position.x + 1, this.position.y - 1);
            g.setColor(this.color);
            g.drawString(this.label, this.position.x, this.position.y);
        }
        if (this.controler == 2) {
            g.drawImage(this.hover, 0, 0, this);
            g.setColor(Color.black);
            g.drawString(this.label, this.position.x + 1, this.position.y - 1);
            g.setColor(this.color);
            g.drawString(this.label, this.position.x, this.position.y);
        }
    }
    
    public SG1_Button(final Image im1, final Image im2, final String str, final Point po, final Font fo, final Color col, final AudioClip adc) {
        this.controler = 1;
        this.normal = im1;
        this.hover = im2;
        this.label = str;
        this.position = po;
        this.font = fo;
        this.color = col;
        this.snd = adc;
        this.init();
    }
}
