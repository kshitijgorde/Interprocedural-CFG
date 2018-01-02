// 
// Decompiled by Procyon v0.5.30
// 

package UTIL;

import java.awt.Font;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Dimension;

public class SplashScreen
{
    private Dimension a;
    private int b;
    private Graphics2D c;
    private BufferedImage d;
    private String e;
    
    public SplashScreen(final Dimension a, final String e) {
        this.a = a;
        this.d = new BufferedImage(this.a.width, this.a.height, 2);
        this.c = (Graphics2D)this.d.getGraphics();
        this.e = e;
        System.out.println(this.e + " © 2010 KryptonWare Solutions LLC");
        System.out.println("Copyright © 2009-2010 I-Console Team");
        System.out.println("For updates, see www.KryptonWare.com");
        System.out.println("For games playable online visit, www.I-Console.com");
    }
    
    public void setStatus(final int b) {
        this.b = b;
    }
    
    public BufferedImage getImage() {
        final String s = "Loading Game...";
        final String string = ((this.b < 10) ? ("0" + Integer.toString(this.b)) : Integer.toString(this.b)) + "%";
        final String string2 = this.e + " © 2009-2010 I-Console Team";
        final String s2 = "I-Console Team © 2009-2010 Kryptonware Solutions, LLC";
        this.c.setColor(Color.BLACK);
        this.c.clearRect(0, 0, this.a.width, this.a.width);
        this.c.fillRect(0, 0, this.a.width, this.a.width);
        this.c.setFont(new Font("Verdana", 0, 128));
        this.c.setColor(Color.WHITE);
        this.c.drawString(string, this.a.width / 2 - this.c.getFontMetrics().stringWidth(string) / 2, this.a.height / 2 + 10);
        this.c.setFont(new Font("Verdana", 0, 18));
        this.c.drawString(s, this.a.width / 2 - this.c.getFontMetrics().stringWidth(s) / 2 - 65, this.a.height / 2 - 96);
        this.c.setFont(new Font("Verdana", 1, 13));
        this.c.drawString(string2, 10, this.a.height - 30);
        this.c.drawString(s2, 10, this.a.height - 15);
        return this.d;
    }
}
