// 
// Decompiled by Procyon v0.5.30
// 

package COM.volano;

import java.awt.Color;
import java.awt.Graphics;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.util.StringTokenizer;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Frame;
import java.applet.AppletContext;
import java.awt.Canvas;

public class ab extends Canvas
{
    private static final String[] qd;
    private static final String rd = " <>[]{}(),";
    private static final String sd = "news";
    private static final String td = "mailto";
    private static final String ud = "_self";
    private static final String vd = "_blank";
    private static final int wd = 2;
    private static final int xd = 5;
    private static final int yd = 30;
    private AppletContext lb;
    private String zd;
    private Frame zb;
    private int md;
    private int ee;
    private String fe;
    private String ge;
    private int he;
    private int ie;
    private boolean[] je;
    private String[] ke;
    private int[] le;
    private int[] me;
    
    public ab(final AppletContext appletContext) {
        this(appletContext, "");
    }
    
    public ab(final AppletContext lb, final String zd) {
        this.md = 5;
        this.lb = lb;
        this.zd = zd;
        this.nb();
    }
    
    public void addNotify() {
        super.addNotify();
        Container container;
        for (container = this.getParent(); !(container instanceof Frame) && container != null; container = container.getParent()) {}
        if (container instanceof Frame) {
            this.zb = (Frame)container;
        }
    }
    
    public Dimension minimumSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        return new Dimension(fontMetrics.stringWidth(this.zd) + 4, fontMetrics.getHeight() + 4);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public synchronized void ob(final String zd, final String ge, final String fe) {
        this.zd = zd;
        this.ge = ge;
        this.fe = fe;
        this.md = 5;
        this.nb();
        this.repaint();
    }
    
    public void ob(final String s) {
        this.ob(s, null, null);
    }
    
    private void nb() {
        final StringTokenizer stringTokenizer = new StringTokenizer(this.zd, " <>[]{}(),", true);
        final int countTokens = stringTokenizer.countTokens();
        this.je = new boolean[countTokens];
        this.ke = new String[countTokens];
        this.le = new int[countTokens];
        this.me = new int[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            this.ke[i] = stringTokenizer.nextToken();
            for (int n = 0; n < ab.qd.length && !this.je[i]; ++n) {
                this.je[i] = this.ke[i].startsWith(ab.qd[n]);
            }
        }
    }
    
    private synchronized String pb(final int n) {
        if (this.ge != null && n >= this.he && n <= this.ie) {
            return this.fe;
        }
        for (int i = 0; i < this.ke.length; ++i) {
            if (this.je[i] && n >= this.le[i] && n <= this.me[i]) {
                return this.ke[i];
            }
        }
        return null;
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (event.id == 403) {
            if (n == 1004 || n == 1006) {
                this.md -= 30;
                this.repaint();
                return true;
            }
            if (n == 1005 || n == 1007) {
                this.md += 30;
                this.repaint();
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        return this.mouseMove(event, n, n2);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        final String pb = this.pb(n);
        if (pb != null) {
            if (this.zb != null) {
                this.zb.setCursor(12);
            }
            this.lb.showStatus(pb);
        }
        else {
            if (this.zb != null) {
                this.zb.setCursor(10);
            }
            this.lb.showStatus("");
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.zb != null) {
            this.zb.setCursor(0);
        }
        this.lb.showStatus("");
        return true;
    }
    
    public boolean mouseDown(final Event event, final int ee, final int n) {
        this.ee = ee;
        this.requestFocus();
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int ee, final int n) {
        this.md += ((this.ee == -1) ? 0 : (ee - this.ee));
        this.ee = ee;
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.ee = -1;
        final String pb = this.pb(n);
        if (pb != null) {
            try {
                final URL url = new URL(pb);
                final String protocol = url.getProtocol();
                if (protocol.equals("mailto") || protocol.equals("news")) {
                    this.lb.showDocument(url, "_self");
                }
                else {
                    this.lb.showDocument(url, "_blank");
                }
            }
            catch (MalformedURLException ex) {}
        }
        return true;
    }
    
    public synchronized void paint(final Graphics graphics) {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        final int n = fontMetrics.getAscent() + 2;
        final int n2 = n + fontMetrics.getDescent();
        int md = this.md;
        if (this.ge != null) {
            this.he = md;
            final int ie = md + fontMetrics.stringWidth(this.ge);
            this.ie = ie;
            graphics.setColor(Color.blue);
            graphics.drawLine(this.he, n2, this.ie, n2);
            graphics.drawString(this.ge, this.he, n);
            md = ie + fontMetrics.stringWidth(" ");
        }
        for (int i = 0; i < this.ke.length; ++i) {
            this.le[i] = md;
            md += fontMetrics.stringWidth(this.ke[i]);
            this.me[i] = md;
            if (this.je[i]) {
                graphics.setColor(Color.blue);
                graphics.drawLine(this.le[i], n2, this.me[i], n2);
            }
            else {
                graphics.setColor(this.getForeground());
            }
            graphics.drawString(this.ke[i], this.le[i], n);
        }
    }
    
    static {
        qd = new String[] { "http://", "ftp://", "news:", "mailto:" };
    }
}
