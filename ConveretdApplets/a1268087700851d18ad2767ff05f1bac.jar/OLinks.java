import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Polygon;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class OLinks
{
    private Vector links;
    private OCAD apl;
    private OMaps maps;
    private OCanv canv;
    private OLink link;
    Polygon hsPoly;
    Color hsColor;
    Rectangle hsRect;
    
    public OLinks() {
        this.links = new Vector();
        this.hsRect = new Rectangle(0, 0, 0, 0);
    }
    
    public OLinks(final OCAD apl, final OMaps maps, final OCanv canv) {
        this.links = new Vector();
        this.hsRect = new Rectangle(0, 0, 0, 0);
        this.apl = apl;
        this.maps = maps;
        this.canv = canv;
    }
    
    public void resetHotspot() {
        this.hsPoly = new Polygon();
    }
    
    public void setHotspotColor(final int red, final int green, final int blue) {
        this.hsColor = new Color(red, green, blue);
    }
    
    public void drawHotspot(final int x, final int y) {
        this.hsPoly.addPoint(x, y);
        this.hsRect.setBounds(this.hsPoly.getBounds());
    }
    
    public void clearHotspots() {
        this.links.setSize(0);
    }
    
    public void addHotspot(final double x, final double y, final String s) {
        this.links.addElement(new OLink(x, y, s));
    }
    
    public OLink getLink(final int i) {
        return this.links.elementAt(i);
    }
    
    private void paintHotspot(final Graphics g, final int i) {
        this.link = this.getLink(i);
        final int x0 = this.canv.worldToScreenX(this.link.x);
        final int y0 = this.canv.worldToScreenY(this.link.y);
        if (this.hsPoly != null && this.hsRect.x + x0 <= this.canv.width && this.hsRect.x + this.hsRect.width + x0 >= 0 && this.hsRect.y + y0 <= this.canv.height && this.hsRect.y + this.hsRect.height + y0 >= 0) {
            this.hsPoly.translate(x0, y0);
            g.setColor(this.hsColor);
            g.fillPolygon(this.hsPoly);
            this.hsPoly.translate(-x0, -y0);
        }
    }
    
    public void paintHotspots(final Graphics g) {
        for (int i = 0; i < this.links.size(); ++i) {
            this.paintHotspot(g, i);
        }
    }
    
    private boolean inside(final int x, final int y) {
        boolean in = false;
        for (int i = 0; i < this.hsPoly.npoints; ++i) {
            final int x2 = this.hsPoly.xpoints[i];
            final int y2 = this.hsPoly.ypoints[i];
            int x3;
            int y3;
            if (i < this.hsPoly.npoints - 1) {
                x3 = this.hsPoly.xpoints[i + 1];
                y3 = this.hsPoly.ypoints[i + 1];
            }
            else {
                x3 = this.hsPoly.xpoints[0];
                y3 = this.hsPoly.ypoints[0];
            }
            if ((y >= y2 ^ y >= y3) && x - x2 > (x3 - x2) / (y3 - y2) * (y - y2)) {
                in = !in;
            }
        }
        return in;
    }
    
    public boolean cursorHot(final int x, final int y) {
        boolean found = false;
        if (this.hsPoly != null) {
            for (int i = 0; i < this.links.size() && !found; ++i) {
                this.link = this.getLink(i);
                final int x2 = this.canv.worldToScreenX(this.link.x);
                final int y2 = this.canv.worldToScreenY(this.link.y);
                if (this.inside(x - x2, y - y2)) {
                    found = true;
                }
            }
        }
        return found;
    }
    
    public boolean showDoc(final int x, final int y) {
        if (this.cursorHot(x, y)) {
            String s;
            if (this.link.string.startsWith("http:") || this.link.string.startsWith("ftp:") || this.link.string.startsWith("file:") || this.link.string.startsWith("mailto:")) {
                s = this.link.string;
            }
            else {
                s = String.valueOf(String.valueOf(this.apl.getCodeBase())).concat(String.valueOf(String.valueOf(this.link.string)));
            }
            try {
                int p;
                for (p = 0; p < s.length() && s.charAt(p) != ',' && s.charAt(p) != ';' && s.charAt(p) != ' '; ++p) {}
                final URL url = new URL(s.substring(0, p));
                while (p < s.length() && (s.charAt(p) == ',' || s.charAt(p) == ';' || s.charAt(p) == ' ')) {
                    ++p;
                }
                if (p < s.length()) {
                    this.apl.getAppletContext().showDocument(url, s.substring(p, s.length()));
                }
                else {
                    this.apl.getAppletContext().showDocument(url, "_blank");
                }
                return true;
            }
            catch (MalformedURLException ex) {
                this.apl.showStatus("URL not correct");
                return false;
            }
            return false;
        }
        return false;
    }
}
