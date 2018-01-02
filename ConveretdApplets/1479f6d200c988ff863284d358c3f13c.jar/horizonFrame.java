import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class horizonFrame extends Frame
{
    boolean isOnline;
    Image horizonImg;
    int xSun;
    int hS;
    double hSun;
    double ph;
    int NorthSouth;
    
    public horizonFrame(final String vStr, final Image MAP, final boolean online, final double hoehe, final double azim, final double phase, final int north) {
        this.setTitle(vStr);
        this.isOnline = online;
        this.horizonImg = MAP;
        this.hSun = hoehe;
        this.xSun = (int)azim;
        this.ph = phase;
        this.NorthSouth = north;
        this.setLocation(200, 400);
        this.repaint();
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public void paint(final Graphics g) {
        final int oben = 40;
        final int y90 = 40;
        final int unten = 230;
        final int xL = 30;
        final int x0 = 256;
        final int y91 = 202;
        g.drawImage(this.horizonImg, 30, 40, this);
        final int n = (int)Math.round(113.0);
        g.setFont(new Font("Chicago", 0, 12));
        g.setColor(Color.red);
        if (this.NorthSouth > 0) {
            g.drawString("S", 253, 37);
            g.drawString("E", 256 - n - 3, 37);
            g.drawString("W", 256 + n - 5, 37);
        }
        else {
            g.drawString("N", 253, 37);
            g.drawString("W", 256 - n - 5, 37);
            g.drawString("E", 256 + n - 3, 37);
        }
        if (this.hSun > -18.0) {
            this.hS = (int)Math.round(162.0 * this.hSun / 90.0);
            final int Radius = 8;
            final int c = (int)Math.round(this.ph * 255.0);
            g.setColor(new Color(c, c, c));
            g.fillOval(this.xSun - Radius, 202 - this.hS - Radius, 2 * Radius, 2 * Radius);
            g.setColor(Color.black);
            g.drawOval(this.xSun - Radius - 1, 202 - this.hS - Radius - 1, 2 * Radius + 2, 2 * Radius + 2);
        }
        else {
            g.setFont(new Font("Helvetica", 0, 12));
            g.setColor(Color.white);
            g.drawString("MOON", this.xSun - 17, 230);
            g.drawString("V", this.xSun - 2, 240);
        }
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 200, 200);
        }
    }
}
