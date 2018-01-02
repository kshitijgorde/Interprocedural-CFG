import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class mapFrame extends Frame
{
    boolean isOnline;
    double latitude;
    double longitude;
    final char deg = '°';
    String latStr;
    String longStr;
    String versStr;
    Image worldMap;
    double dec;
    double gha;
    
    public mapFrame(final String vStr, final Image MAP, final double DEC, final double GHA, final double LAT, final double LONG, final boolean online) {
        this.setTitle(vStr);
        this.isOnline = online;
        this.worldMap = MAP;
        this.dec = DEC;
        this.gha = GHA;
        this.latitude = LAT;
        this.longitude = LONG;
        this.setLocation(200, 400);
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public double computeLat(final double longitude, final double dec) {
        final double K = 0.017453292519943295;
        final double tan = -Math.cos(longitude * 0.017453292519943295) / Math.tan(dec * 0.017453292519943295);
        return Math.atan(tan) / 0.017453292519943295;
    }
    
    public void paint(final Graphics g) {
        final int xL = 30;
        final int oben = 40;
        final double xFaktor = 1.2555555555555555;
        final double yFaktor = 1.2444444444444445;
        g.drawImage(this.worldMap, xL, oben, this);
        final int x0 = (int)Math.round(226.0);
        final int y0 = (int)Math.round(112.0) + oben;
        g.setColor(Color.gray);
        g.drawLine(xL, y0, xL + 2 * x0 - 2, y0);
        g.drawLine(xL + x0, oben, xL + x0, oben + 224);
        final int yy1 = (int)Math.round(y0 - 29.244444444444444);
        final int yy2 = (int)Math.round(y0 + 29.244444444444444);
        final int y2 = (int)Math.round(oben + 224 - 29.244444444444444);
        int yy3 = (int)Math.round(oben + 29.244444444444444);
        int xx = xL;
        for (int i = 0; i < 75; ++i) {
            g.drawLine(xx, yy1, xx + 2, yy1);
            g.drawLine(xx, yy2, xx + 2, yy2);
            g.drawLine(xx, y2, xx + 2, y2);
            g.drawLine(xx, yy3, xx + 2, yy3);
            xx += 6;
        }
        final int moonMapRadius = 10;
        final int x0Map = (int)Math.round(226.0);
        final int y0Map = (int)Math.round(112.0) + oben;
        int xMoonMap = xL + x0Map - (int)Math.round(1.2555555555555555 * this.gha);
        if (xMoonMap < xL) {
            xMoonMap = xL + x0Map - (int)Math.round(1.2555555555555555 * (this.gha - 360.0));
        }
        int yMoonMap = y0Map - (int)Math.round(1.2444444444444445 * this.dec);
        g.setColor(Color.yellow);
        g.fillOval(xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
        g.setColor(Color.black);
        g.drawOval(xMoonMap - moonMapRadius, yMoonMap - moonMapRadius, 2 * moonMapRadius, 2 * moonMapRadius);
        int a;
        if (this.dec > 0.0) {
            a = y0Map + 112;
        }
        else {
            a = y0Map - 112;
        }
        g.setColor(Color.gray);
        for (int j = 1; j <= 450; ++j) {
            if (j % 2 == 0) {
                final double LL = -180.0 + this.gha + j / 1.2555555555555555;
                yy3 = (int)Math.round(1.2444444444444445 * this.computeLat(LL, this.dec));
                g.drawLine(xL + j, y0Map - yy3, xL + j, a);
            }
        }
        xMoonMap = xL + x0Map + (int)Math.round(1.2555555555555555 * this.longitude);
        yMoonMap = y0Map - (int)Math.round(1.2444444444444445 * this.latitude);
        g.setColor(Color.red);
        g.fillOval(xMoonMap - 3, yMoonMap - 3, 6, 6);
        g.setColor(Color.black);
        g.drawOval(xMoonMap - 3, yMoonMap - 3, 6, 6);
        g.setFont(new Font("Helvetica", 0, 9));
        g.drawString("Map (c) Apple Computers Inc.", 200, this.size().height - 12);
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 200, 200);
        }
    }
}
