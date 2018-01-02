import java.awt.Color;
import java.awt.image.ImageObserver;
import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class LemonadeAnim extends Canvas
{
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private Image weatherImages;
    private Image thoughts;
    private Image people;
    private Image soldout;
    private Image stand;
    private int weatherToday;
    private Font f;
    
    public void getLemonadeGraphics(final Applet applet) {
        this.offScreenImage = this.createImage(372, 216);
        this.offScreenGraphics = this.offScreenImage.getGraphics();
        this.f = new Font("TimesRoman", 1, 12);
        this.offScreenGraphics.setFont(this.f);
        this.people = applet.getImage(applet.getCodeBase(), "people.gif");
        this.weatherImages = applet.getImage(applet.getCodeBase(), "weather.jpg");
        this.stand = applet.getImage(applet.getCodeBase(), "stand.gif");
        this.soldout = applet.getImage(applet.getCodeBase(), "soldout.gif");
        this.thoughts = applet.getImage(applet.getCodeBase(), "bubbles.gif");
        new Thread(new QueueMedia(new Image[] { this.people, this.weatherImages, this.stand, this.soldout, this.thoughts }, this)).start();
    }
    
    public void setWeather(final int weatherToday) {
        this.weatherToday = weatherToday;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offScreenImage, 0, 0, this);
    }
    
    public void prepareNextImage() {
        this.offScreenGraphics.drawImage(this.weatherImages, -(this.weatherToday * 372), 0, this);
        this.offScreenGraphics.drawImage(this.stand, 120, 85, this);
    }
    
    public void SoldOut() {
        this.offScreenGraphics.drawImage(this.soldout, 140, 125, this);
    }
    
    public void addBubble(final int n, final int n2) {
        this.offScreenGraphics.setClip(n2 - 3, 0, 80, 40);
        this.offScreenGraphics.drawImage(this.thoughts, n2 - 3 - n * 80, 0, this);
        this.offScreenGraphics.setClip(0, 0, 372, 216);
    }
    
    public void addPerson(final int n, final int n2, final int n3, final int n4) {
        this.offScreenGraphics.setClip(n4, 50, 75, 150);
        this.offScreenGraphics.drawImage(this.people, n4 - (n * 300 + n2 * 150 + n3 * 75), 60, this);
        this.offScreenGraphics.setClip(0, 0, 372, 216);
    }
    
    public void addRain() {
        this.offScreenGraphics.setColor(Color.gray);
        for (int n = 100 + (int)(Math.random() * 100.0), i = 0; i < n; ++i) {
            final int n2 = (int)(Math.random() * 372.0);
            final int n3 = (int)(Math.random() * 216.0);
            this.offScreenGraphics.drawLine(n2, n3, n2 + 2, n3 + 4);
        }
    }
    
    public void addText(final String s) {
        this.offScreenGraphics.setColor(Color.white);
        this.offScreenGraphics.drawString(s, 10, 205);
    }
}
