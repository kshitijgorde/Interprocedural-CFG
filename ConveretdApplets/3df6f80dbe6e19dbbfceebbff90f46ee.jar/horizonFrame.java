import java.awt.Color;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Cursor;
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
    double currentH;
    int xS;
    int hS;
    double latitude;
    double dec;
    int[] X;
    int[] Y;
    int xMouse;
    int yMouse;
    String clickStr;
    
    public horizonFrame(final String vStr, final Image MAP, final boolean online, final double hoehe, final int xSun, final int hSun, final double LAT, final double DEC, final int[] xArray, final int[] hArray) {
        this.X = new int[25];
        this.Y = new int[25];
        this.xMouse = 1000;
        this.yMouse = 1000;
        this.clickStr = "";
        this.setTitle(vStr);
        this.isOnline = online;
        this.horizonImg = MAP;
        this.currentH = hoehe;
        this.xS = xSun - 40;
        this.hS = hSun + 60;
        this.latitude = LAT;
        this.dec = DEC;
        for (int i = 0; i < 25; ++i) {
            this.X[i] = xArray[i] - 40;
            this.Y[i] = hArray[i] - 60;
        }
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
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        final char deg = '°';
        final int xL = 20;
        final int xR = 560;
        final int y2 = 64;
        final int y3 = 350;
        final int y4 = 276;
        final int xM = 290;
        if (x >= 20 && x <= 560 && y >= 64 && y <= 350) {
            this.xMouse = x;
            this.yMouse = y;
            final double hClick = (276 - this.yMouse) * 90.0 / 212.0;
            final double azClick = 180.0 + (this.xMouse - 290) * 180.0 / 270.0;
            this.clickStr = "h=" + Math.round(10.0 * hClick) / 10.0 + '°' + "   az=" + Math.round(10.0 * azClick) / 10.0 + '°';
        }
        else {
            this.xMouse = 1000;
            this.yMouse = 1000;
            this.clickStr = "";
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        final int xL = 20;
        final int xR = 560;
        final int y2 = 64;
        final int y3 = 350;
        final int y4 = 276;
        if (x >= 20 && x <= 560 && y >= 64 && y <= 350) {
            this.setCursor(new Cursor(1));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
    
    public void paint(final Graphics g) {
        final int y90 = 60;
        final int y91 = 336;
        final int xL = 20;
        final int oben = 40;
        final int x0 = 290;
        final int unten = 335;
        g.drawImage(this.horizonImg, 20, 40, this);
        g.drawLine(this.xMouse - 3, this.yMouse, this.xMouse + 3, this.yMouse);
        g.drawLine(this.xMouse, this.yMouse - 3, this.xMouse, this.yMouse + 3);
        g.setFont(new Font("Courier", 0, 12));
        g.drawString(this.clickStr, 225, 364);
        final int n = (int)Math.round((x0 - 20) / 2.0);
        g.setFont(new Font("Chicago", 0, 12));
        g.setColor(Color.red);
        if (this.latitude >= this.dec) {
            g.drawString("S", x0 - 3, 57);
            g.drawString("E", x0 - n - 3, 57);
            g.drawString("W", x0 + n - 5, 57);
        }
        else {
            g.drawString("N", x0 - 3, 57);
            g.drawString("W", x0 - n - 5, 57);
            g.drawString("E", x0 + n - 3, 57);
        }
        if (this.currentH > -18.0) {
            Color c = new Color(255, 255, 0);
            if (this.currentH > -0.8) {
                c = new Color(255, 255, 0);
            }
            if (this.currentH < 0.0 && this.currentH > -6.0) {
                c = new Color(255, 200, 0);
            }
            if (this.currentH < -6.0 && this.currentH > -12.0) {
                c = new Color(255, 150, 0);
            }
            if (this.currentH < -12.0 && this.currentH > -18.0) {
                c = new Color(255, 100, 0);
            }
            if (this.currentH < -18.0) {
                c = new Color(255, 50, 0);
            }
            g.setColor(c);
            final int Radius = 8;
            g.fillOval(this.xS - Radius, 336 - this.hS - Radius, 2 * Radius, 2 * Radius);
            g.setColor(Color.red);
            g.drawOval(this.xS - Radius - 1, 336 - this.hS - Radius - 1, 2 * Radius + 2, 2 * Radius + 2);
        }
        else {
            g.setFont(new Font("Helvetica", 0, 12));
            g.setColor(Color.white);
            g.drawString("SUN", this.xS - 10, unten);
            g.drawString("V", this.xS - 2, unten + 10);
        }
        for (int i = 0; i < 25; ++i) {
            if (this.Y[i] < 350) {
                g.drawOval(this.X[i] - 1, this.Y[i] - 1, 2, 2);
            }
        }
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 200, 200);
        }
    }
}
