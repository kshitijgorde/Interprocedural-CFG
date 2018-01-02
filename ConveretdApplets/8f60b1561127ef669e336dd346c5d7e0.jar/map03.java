import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Scrollbar;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class map03 extends Applet
{
    Scrollbar scrollDec;
    Scrollbar scrollTau;
    double dec;
    double tau;
    int valueTau;
    final char deg = '°';
    Label decLabel;
    Label tauLabel;
    Image map;
    
    public void init() {
        this.map = this.getImage(this.getDocumentBase(), "applet/map.gif");
        this.setLayout(new BorderLayout());
        final Panel p = new Panel();
        p.setLayout(new GridLayout(0, 2));
        this.scrollDec = new Scrollbar(0);
        p.add(this.decLabel = new Label(" Declination " + this.dec + '°'));
        p.add(this.scrollDec);
        this.scrollTau = new Scrollbar(0);
        this.tauLabel = new Label(" Noon + 0 h");
        this.scrollTau.setValue(this.valueTau);
        p.add(this.tauLabel);
        p.add(this.scrollTau);
        this.add("North", p);
        this.repaint();
    }
    
    public boolean handleEvent(final Event e) {
        if (e.target instanceof Scrollbar) {
            if (e.target == this.scrollDec) {
                final int value = ((Scrollbar)e.target).getValue();
                this.dec = 23.5 - 47.0 * value / 90.0;
                this.decLabel.setText(" Declination " + Math.round(10.0 * this.dec) / 10.0 + '°');
                this.repaint();
                return true;
            }
            if (e.target == this.scrollTau) {
                this.valueTau = ((Scrollbar)e.target).getValue();
                this.tau = 360 * (this.valueTau - 45) / 90.0;
                final double t = this.tau / 15.0;
                if (t >= 0.0) {
                    this.tauLabel.setText(" Noon + " + Math.round(10.0 * t) / 10.0 + " h");
                }
                else {
                    this.tauLabel.setText(" Noon - " + Math.round(10.0 * Math.abs(t)) / 10.0 + " h");
                }
                this.repaint();
                return true;
            }
        }
        return true;
    }
    
    public void paint(final Graphics g) {
        final double K = 0.017453292519943295;
        final int y0 = 135;
        final int links = 30;
        g.drawImage(this.map, links, y0 - 90, this);
        g.setColor(Color.gray);
        g.drawLine(links, y0, links + 360, y0);
        g.drawLine(links + 180, y0 - 90, links + 180, y0 + 90);
        g.setColor(Color.black);
        g.drawRect(links, y0 - 90, 360, 180);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("www.GeoAstro.de", 165, this.size().height - 10);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        for (int i = 0; i <= 360; ++i) {
            double longitude = i + this.tau;
            double tanLat = -Math.cos(longitude * K) / Math.tan(this.dec * K);
            double arctanLat = Math.atan(tanLat) / K;
            final int y2 = y0 + (int)Math.round(arctanLat);
            ++longitude;
            tanLat = -Math.cos(longitude * K) / Math.tan(this.dec * K);
            arctanLat = Math.atan(tanLat) / K;
            final int y3 = y0 + (int)Math.round(arctanLat);
            g.drawLine(links + i, y2, links + i + 1, y3);
        }
        g.setColor(Color.yellow);
        g.fillOval(links + 180 - (int)Math.round(this.tau) - 5, y0 - (int)Math.round(this.dec) - 5, 10, 10);
        g.setColor(Color.red);
        g.drawOval(links + 180 - (int)Math.round(this.tau) - 5, y0 - (int)Math.round(this.dec) - 5, 10, 10);
    }
    
    public map03() {
        this.dec = 23.5;
        this.tau = 0.0;
        this.valueTau = 45;
    }
}
