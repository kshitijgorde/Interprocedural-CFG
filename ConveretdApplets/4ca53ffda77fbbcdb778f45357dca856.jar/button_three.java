import java.awt.Component;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.LayoutManager;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class button_three extends Applet
{
    Thread t;
    int iw;
    int ih;
    int d;
    int c;
    int inc;
    int dr;
    menu_three ma;
    Graphics og;
    Image mimg;
    Image oimg;
    Image cimg;
    Image oi;
    boolean bas;
    URL u;
    
    public button_three(final menu_three ma, final Image mimg, final Image oimg, final Image cimg, final int iw, final int ih, final int dr) {
        this.bas = false;
        this.setLayout(null);
        this.ma = ma;
        this.iw = iw;
        this.ih = ih;
        this.mimg = mimg;
        this.oimg = oimg;
        this.cimg = cimg;
        this.inc = ma.anim_speed;
        this.dr = dr;
        this.oi = ((Component)this.ma).createImage(this.iw, this.ih);
        this.og = this.oi.getGraphics();
        this.resize(this.iw, this.ih);
        this.show();
        this.repaint();
        this.bas = false;
    }
    
    public void update(final Graphics graphics) {
        if (!this.bas) {
            this.og.drawImage(this.mimg, 0, -this.c, (ImageObserver)this.ma);
            this.og.drawImage(this.oimg, 0, this.ih - this.c, (ImageObserver)this.ma);
        }
        else {
            this.og.drawImage(this.cimg, 0, 0, (ImageObserver)this.ma);
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.oi, 0, 0, (ImageObserver)this.ma);
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.d = 1;
        if (this.ma.dur[this.dr] == 1) {
            this.ma.frm.setCursor(12);
            if (!this.ma.rg) {
                ((Applet)this.ma).showStatus("--- Unregistered version of Animated Menu Three Java applet,(www.secretplus.com) ---");
            }
            else {
                ((Applet)this.ma).showStatus("" + this.ma.stb[this.dr]);
            }
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.d = 2;
        this.bas = false;
        this.repaint();
        this.ma.frm.setCursor(0);
        if (!this.ma.rg) {
            ((Applet)this.ma).showStatus("--- Unregistered version of Animated Menu Three Java applet,(www.secretplus.com) ---");
        }
        else {
            ((Applet)this.ma).showStatus("");
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.bas = false;
        this.repaint();
        if (this.ma.dur[this.dr] == 1) {
            try {
                if (this.ma.links[this.dr].startsWith("http://") || this.ma.links[this.dr].startsWith("mailto:")) {
                    this.u = new URL("" + this.ma.links[this.dr]);
                }
                else {
                    this.u = new URL(((Applet)this.ma).getCodeBase(), "" + this.ma.links[this.dr]);
                }
            }
            catch (Exception ex) {
                return true;
            }
            ((Applet)this.ma).getAppletContext().showDocument(this.u, this.ma.target_frame[this.dr]);
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.bas = true;
        this.repaint();
        return true;
    }
    
    public void prs() {
        if (this.d == 1) {
            this.c += this.inc;
            if (this.c >= this.ih) {
                this.c = this.ih;
                this.d = 0;
            }
            this.repaint();
        }
        if (this.d == 2) {
            this.c -= this.inc;
            if (this.c <= 0) {
                this.c = 0;
                this.d = 0;
            }
            this.repaint();
        }
    }
}
