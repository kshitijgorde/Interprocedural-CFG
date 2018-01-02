import java.awt.Event;
import java.util.Date;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Clock extends Applet
{
    Image[] images;
    int count;
    int hrs;
    int min;
    int sec;
    int hrs2;
    int min2;
    int sec2;
    int ohrs;
    int omin;
    int osec;
    int ohrs2;
    int omin2;
    int osec2;
    boolean cs;
    boolean painted1;
    
    public void init() {
        this.setBackground(Color.white);
        this.count = 0;
        while (this.count < 11) {
            this.images[this.count] = this.getImage(this.getDocumentBase(), this.count + ".gif");
            ++this.count;
        }
        this.images[11] = this.getImage(this.getDocumentBase(), "colon.gif");
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.images[11], 34, 0, this);
        graphics.drawImage(this.images[11], 85, 0, this);
        if (this.ohrs != this.hrs || !this.painted1) {
            graphics.drawImage(this.images[this.hrs], 0, 0, this);
        }
        if (this.ohrs2 != this.hrs2 || !this.painted1) {
            graphics.drawImage(this.images[this.hrs2], 17, 0, this);
        }
        if (this.omin != this.min || !this.painted1) {
            graphics.drawImage(this.images[this.min], 51, 0, this);
        }
        if (this.omin2 != this.min2 || !this.painted1) {
            graphics.drawImage(this.images[this.min2], 68, 0, this);
        }
        if (this.osec != this.sec || !this.painted1) {
            graphics.drawImage(this.images[this.sec], 102, 0, this);
        }
        if (this.osec2 != this.sec2 || !this.painted1) {
            graphics.drawImage(this.images[this.sec2], 119, 0, this);
        }
        this.getClockData();
    }
    
    public void getClockData() {
        final Date date = new Date();
        this.ohrs = this.hrs;
        this.omin = this.min;
        this.osec = this.sec;
        this.ohrs2 = this.hrs2;
        this.omin2 = this.min2;
        this.osec2 = this.sec2;
        this.hrs = date.getHours() / 10;
        this.min = date.getMinutes() / 10;
        this.sec = date.getSeconds() / 10;
        this.hrs2 = date.getHours() % 10;
        this.min2 = date.getMinutes() % 10;
        this.sec2 = date.getSeconds() % 10;
        if (this.hrs == 1 && this.hrs2 > 2) {
            --this.hrs;
            this.hrs2 -= 2;
        }
        System.out.println(this.hrs + "" + this.hrs2 + ":" + this.min + "" + this.min2 + ":" + this.sec + "" + this.sec2);
        this.pause(1000);
        this.repaint();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.repaint();
        return true;
    }
    
    public void pause(final int n) {
        try {
            Thread.sleep(n);
        }
        catch (Exception ex) {}
    }
    
    public void start() {
        this.repaint();
    }
    
    public Clock() {
        this.images = new Image[12];
        this.cs = false;
        this.painted1 = false;
    }
}
