import java.awt.Event;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class MojaNit extends Canvas
{
    String str;
    Color bojanorm;
    Color bojaosvj;
    Color bojaslova;
    int sirina;
    int visina;
    int cm;
    int vel;
    int f;
    int ss;
    int vs;
    int xpoc;
    int ypoc;
    Font pismo;
    FontMetrics info;
    URL url;
    String tr;
    
    public MojaNit(final String str, final String s, final String tr, final Color bojanorm, final Color bojaslova, final Color bojaosvj, final int vel, final int sirina, final int visina) {
        this.vel = vel;
        this.pismo = new Font("Arial", 1, this.vel);
        this.info = this.getFontMetrics(this.pismo);
        this.str = str;
        this.sirina = sirina;
        this.visina = visina;
        this.f = 1;
        this.ss = this.info.stringWidth(this.str);
        this.xpoc = (this.sirina - this.ss) / 2;
        this.ypoc = (this.visina - this.vel) / 2 + this.vel;
        this.bojanorm = bojanorm;
        this.bojaosvj = bojaosvj;
        this.bojaslova = bojaslova;
        this.tr = tr;
        try {
            this.url = new URL(s);
        }
        catch (MalformedURLException ex) {
            this.url = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.f == 1) {
            graphics.setColor(this.bojanorm);
            graphics.fill3DRect(0, 0, this.sirina, this.visina, true);
            graphics.setFont(this.pismo);
            graphics.setColor(this.bojaslova);
            graphics.drawString(this.str, this.xpoc, this.ypoc);
            return;
        }
        graphics.setColor(this.bojaosvj);
        graphics.fill3DRect(0, 0, this.sirina, this.visina, true);
        graphics.setFont(this.pismo);
        graphics.setColor(this.bojaslova);
        graphics.drawString(this.str, this.xpoc, this.ypoc);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.f = 2;
        this.repaint();
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.f = 1;
        this.repaint();
        return true;
    }
}
