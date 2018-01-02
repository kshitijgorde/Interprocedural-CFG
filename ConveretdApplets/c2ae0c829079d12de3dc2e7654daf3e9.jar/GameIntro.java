import java.applet.Applet;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;

// 
// Decompiled by Procyon v0.5.30
// 

class GameIntro extends Thread
{
    Thread theIntro;
    Graphics offscreen;
    Image image;
    Font mediumFont;
    FontMetrics fm;
    String Intro;
    int width;
    int height;
    Applet si;
    
    public GameIntro(final int n, final int n2, final Applet si) {
        this.mediumFont = new Font("Helvetica", 1, 14);
        this.Intro = "Hello";
        this.si = si;
    }
    
    public void doIntro() {
    }
    
    public void run() {
    }
    
    public void update(final Graphics graphics) {
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawString(this.Intro, 10, 13);
    }
}
