import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Mapcanvas extends Canvas
{
    public final int mapOben = 445;
    public final int mapRechts = 490;
    final int xL = 30;
    final double xFaktor = 1.2555555555555555;
    final double yFaktor = 1.2444444444444445;
    int x0;
    int y0;
    int left;
    final double K = 0.017453292519943295;
    Image myMap;
    Date dat;
    int day;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    int locOffset;
    int browserOffset;
    double dec;
    double GHA;
    double longitude;
    double latitude;
    int xMouse;
    int yMouse;
    boolean clicked;
    boolean myConsole;
    public Compute comp;
    
    public Mapcanvas(final Image myMap, final Date dat, final double latitude, final double longitude, final boolean clicked, final int locOffset) {
        this.x0 = (int)Math.round(226.0);
        this.y0 = (int)Math.round(112.0) + 445;
        this.left = 400;
        this.xMouse = 0;
        this.yMouse = 0;
        this.myMap = myMap;
        this.dat = dat;
        this.longitude = longitude;
        this.latitude = latitude;
        this.clicked = clicked;
        this.locOffset = locOffset;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.myMap, 30, 445, this);
        this.comp = new Compute();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        final double n = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, n);
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, n);
        graphics.setColor(Color.black);
        graphics.setColor(Color.red);
        int n2 = 180 - (int)Math.round(this.GHA);
        if (n2 < 0) {
            n2 += 360;
        }
        if (n2 > 360) {
            n2 -= 360;
        }
        int n3 = 30;
        graphics.setColor(Color.gray);
        graphics.drawLine(30, this.y0, 30 + 2 * this.x0 - 2, this.y0);
        graphics.drawLine(30 + this.x0, 445, 30 + this.x0, 669);
        final int n4 = (int)Math.round(this.y0 - 29.244444444444444);
        final int n5 = (int)Math.round(this.y0 + 29.244444444444444);
        final int n6 = (int)Math.round(639.7555555555556);
        final int n7 = (int)Math.round(474.24444444444447);
        for (int i = 0; i < 75; ++i) {
            graphics.drawLine(n3, n4, n3 + 2, n4);
            graphics.drawLine(n3, n5, n3 + 2, n5);
            graphics.drawLine(n3, n6, n3 + 2, n6);
            graphics.drawLine(n3, n7, n3 + 2, n7);
            n3 += 6;
        }
        final int n8 = (int)Math.round(this.y0 - 1.2444444444444445 * this.dec);
        graphics.setColor(Color.yellow);
        graphics.fillOval(30 + (int)Math.round(1.2555555555555555 * n2) - 8, n8 - 8, 16, 16);
        graphics.setColor(Color.red);
        graphics.drawOval(30 + (int)Math.round(1.2555555555555555 * n2) - 8 - 1, n8 - 8 - 1, 18, 18);
        graphics.setColor(Color.blue);
        int n9;
        if (this.dec > 0.0) {
            n9 = 1;
        }
        else {
            n9 = -1;
        }
        graphics.setXORMode(Color.white);
        final int n10 = this.y0 + n9 * 112;
        final double n11 = -180.0 + this.GHA;
        for (int j = 0; j < 451; ++j) {
            final int n12 = (int)Math.round(n11 + j / 1.2555555555555555);
            final int n13 = (int)Math.round(n11 + (j + 1) / 1.2555555555555555);
            final int n14 = (int)Math.round(1.2444444444444445 * this.comp.computeLat(n12, this.dec));
            final int n15 = (int)Math.round(1.2444444444444445 * this.comp.computeLat(n13, this.dec));
            final int n16 = 30 + (int)Math.round((-n11 + j) * 1.2555555555555555);
            final int n17 = 30 + (int)Math.round((-n11 + j + 1.0) * 1.2555555555555555);
            graphics.drawLine(30 + j, this.y0 - n14, 30 + j + 1, this.y0 - n15);
            graphics.drawLine(30 + j, this.y0 - n14, 30 + j, n10);
        }
        graphics.setPaintMode();
        graphics.setColor(Color.red);
        int n18 = 180 - (int)Math.round(this.longitude);
        if (n18 < 0) {
            n18 += 360;
        }
        if (n18 > 360) {}
        final int n19 = this.x0 + (int)Math.round(1.2555555555555555 * this.longitude);
        final int n20 = this.y0 - (int)Math.round(1.2444444444444445 * this.latitude);
        graphics.fillOval(30 + n19 - 4, n20 - 4, 8, 8);
        graphics.setColor(Color.yellow);
        graphics.drawLine(30 + n19, n20 - 5, 30 + n19, n20 + 5);
        graphics.drawLine(30 + n19 - 5, n20, 30 + n19 + 5, n20);
        graphics.setColor(Color.red);
        final boolean clicked = false;
        this.clicked = clicked;
        if (clicked) {
            graphics.drawString("Click the map to see", 490, 625);
            graphics.drawString("the shadow", 490, 640);
            graphics.drawString("and more !", 490, 655);
        }
        final double computeHeight = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
        if (computeHeight >= 0.0) {
            final double n21 = this.comp.computeAzimut(this.dec, this.latitude, this.longitude, this.GHA, computeHeight) - 180.0;
            final double n22 = 50.0 / Math.tan(0.017453292519943295 * computeHeight);
            this.xMouse = 30 + this.x0 + (int)Math.round(this.longitude * 452.0 / 360.0);
            this.yMouse = this.y0 - (int)Math.round(this.latitude * 224.0 / 180.0);
            final int n23 = (int)Math.round(n22 * Math.cos(0.017453292519943295 * n21));
            final int n24 = (int)Math.round(n22 * Math.sin(0.017453292519943295 * n21));
            final double n25 = n23 / n24;
            if (this.yMouse - n23 > 445 && n24 + this.xMouse < 482) {
                graphics.drawLine(this.xMouse, this.yMouse, this.xMouse + n24, this.yMouse - n23);
            }
            if (this.yMouse - n23 < 445 && n24 + this.xMouse < 482) {
                graphics.drawLine(this.xMouse, this.yMouse, this.xMouse + (int)((this.yMouse - 445) / n25), 445);
            }
            if (this.yMouse - n23 > 445 && n24 + this.xMouse > 482) {
                graphics.drawLine(this.xMouse, this.yMouse, 482, this.yMouse - (int)((482 - this.xMouse) * n25));
            }
            if (this.yMouse - n23 < 445 && n24 + this.xMouse > 482) {
                final double n26 = (this.yMouse - 445) / (482 - this.xMouse);
                if (n25 > n26) {
                    graphics.drawLine(this.xMouse, this.yMouse, this.xMouse + (int)((this.yMouse - 445) / n25), 445);
                }
                if (n25 < n26) {
                    graphics.drawLine(this.xMouse, this.yMouse, 482, this.yMouse - (int)((482 - this.xMouse) * n25));
                }
            }
        }
        graphics.setColor(Color.red);
        graphics.drawRect(30, 445, 2 * this.x0 - 1, 224);
    }
}
