import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class carimg extends Applet
{
    Image img;
    
    public void init() {
        final int[] array = { 15, 15, 0, 60, 45, 45 };
        final int[] array2 = { 45, 50, 58, 58, 50, 45 };
        this.setBackground(Color.magenta);
        this.img = this.createImage(60, 60);
        final Graphics graphics = this.img.getGraphics();
        graphics.setColor(Color.magenta);
        graphics.fillRect(0, 0, 60, 60);
        graphics.setColor(Color.green);
        graphics.fillRect(12, 20, 36, 7);
        graphics.fillRect(8, 15, 4, 17);
        graphics.fillRect(48, 15, 4, 17);
        graphics.fillRect(5, 40, 50, 7);
        graphics.fillRect(0, 35, 5, 17);
        graphics.fillRect(55, 35, 5, 17);
        graphics.setColor(Color.red);
        graphics.fillRect(20, 0, 20, 15);
        graphics.fillRect(15, 15, 30, 40);
        graphics.setColor(Color.blue);
        graphics.fillRect(20, 20, 7, 10);
        graphics.fillRect(33, 20, 7, 10);
        graphics.setColor(Color.red);
        graphics.fillRect(22, 22, 3, 6);
        graphics.fillRect(35, 22, 3, 6);
        graphics.setFont(new Font("TimesRoman", 0, 7));
        graphics.setColor(Color.white);
        graphics.fillPolygon(array, array2, 6);
        graphics.setColor(Color.black);
        graphics.drawString("YAMAHA", 15, 52);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.img, 0, 0, this);
    }
}
