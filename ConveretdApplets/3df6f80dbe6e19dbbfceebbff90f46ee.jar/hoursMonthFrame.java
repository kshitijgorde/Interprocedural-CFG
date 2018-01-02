import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class hoursMonthFrame extends Frame
{
    boolean isOnline;
    double latitude;
    double longitude;
    final char deg = '°';
    String latStr;
    String longStr;
    String versStr;
    double[] hours;
    double mittel;
    
    public hoursMonthFrame(final String vStr, final double LAT, final double LONG, final double[] h, final boolean online) {
        this.mittel = 0.0;
        this.latStr = String.valueOf(Math.abs(LAT));
        if (LAT >= 0.0) {
            this.latStr = String.valueOf(this.latStr) + "°N";
        }
        else {
            this.latStr = String.valueOf(this.latStr) + "°S";
        }
        this.longStr = String.valueOf(Math.abs(LONG));
        if (LONG >= 0.0) {
            this.longStr = String.valueOf(this.longStr) + "°E";
        }
        else {
            this.longStr = String.valueOf(this.longStr) + "°W";
        }
        this.setTitle(String.valueOf(vStr) + " at " + this.latStr + ", " + this.longStr);
        this.isOnline = online;
        this.latitude = LAT;
        this.longitude = LONG;
        this.hours = h;
        this.setLocation(200, 400);
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(e);
    }
    
    public void paint(final Graphics g) {
        final int xL = 40;
        final int y0 = 380;
        final int hoehe = 320;
        final int dx = 40;
        final int dy = 40;
        final int breite = 12 * dx;
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        g.setFont(new Font("Courier", 0, 10));
        double summe = 0.0;
        for (int i = 0; i < 12; ++i) {
            g.drawString(monthArray[i], xL + 10 + i * dx, y0 + 20);
            summe += this.hours[i];
        }
        this.mittel = summe / 12.0;
        for (int j = 0; j < 12; ++j) {
            g.drawString(String.valueOf(Math.round(10.0 * this.hours[j]) / 10.0), xL + 5 + j * dx, 37);
            if (j < 11) {
                g.drawString(String.valueOf(Math.round(10.0 * (this.hours[j + 1] - this.hours[j])) / 10.0), xL + 25 + j * dx, 50);
            }
            else {
                g.drawString(String.valueOf(Math.round(10.0 * (this.hours[0] - this.hours[11])) / 10.0), xL + 25 + j * dx, 50);
            }
        }
        g.setColor(Color.yellow);
        for (int k = 0; k < 12; ++k) {
            g.fillRect(xL + k * dx, y0 - (int)Math.round(this.hours[k] / 2.5), dx, (int)Math.round(this.hours[k] / 2.5));
        }
        g.setColor(Color.black);
        for (int l = 0; l <= 8; ++l) {
            g.drawString(l * 100 + "h", 12, y0 - l * dx + 5);
        }
        g.setColor(Color.gray);
        for (int m = 0; m <= 11; ++m) {
            g.drawLine(xL + m * dx, 60, xL + m * dx, y0);
        }
        for (int i2 = 1; i2 <= 8; ++i2) {
            g.drawLine(xL, y0 - i2 * dy, xL + breite, y0 - i2 * dy);
        }
        g.setColor(Color.red);
        g.drawRect(xL, 60, breite, hoehe);
        g.setColor(Color.blue);
        g.drawLine(xL, y0 - (int)Math.round(this.mittel / 2.5), xL + breite, y0 - (int)Math.round(this.mittel / 2.5));
        g.drawString(Math.round(1000.0 * this.mittel) / 1000.0 + "h", xL + 10, y0 - (int)Math.round(this.mittel / 2.5) - 5);
        if (this.isOnline) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("D E M O", 200, 200);
        }
    }
}
