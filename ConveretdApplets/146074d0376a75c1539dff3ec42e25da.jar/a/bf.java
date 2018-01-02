// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import com.spilka.client.muc.AppletAbstract;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Component;
import java.awt.Window;

public abstract class bf implements aH, bh
{
    protected cV w;
    private aK q;
    private aW q;
    private Window q;
    protected Component q;
    private int q;
    private int w;
    private String q;
    private boolean q;
    private int e;
    protected Hashtable q;
    private int r;
    private static bf q;
    
    public bf(final Component component, final cV cv) {
        this(component, 12632319, cv);
    }
    
    private bf(final Component q, final int n, final cV w) {
        this.q = 15;
        this.q = new Hashtable();
        this.r = 0;
        this.q = q;
        this.w = w;
        this.t();
        this.y();
    }
    
    protected bf() {
        this.q = 15;
        this.q = new Hashtable();
        this.r = 0;
    }
    
    protected final void y() {
        this.q = 18;
        this.w = 10;
        cf.w.w();
        this.q = cf.w.q();
        this.q = "_blank";
        this.q = this.q("mi_", true);
        this.q = new aK(this.q, this, true, null);
    }
    
    protected abstract void t();
    
    public final Component q(final Component component) {
        this.q.add(component, 0);
        return component;
    }
    
    public final void q(final Component component) {
        this.q.remove(component);
    }
    
    protected final void q(final int n, final int n2) {
        this.q(this);
        this.q.requestFocus();
        (this.q = eb.q(this.q, this.q)).setLocation(n, n2);
        this.q.setSize(0, 0);
        this.q((Component)this.q);
        final int n3 = this.q.q() * this.q + this.q.w() * 3 + 4 + (cV.s() ? 0 : this.q);
        this.q.setBounds(n + this.q.getLocationOnScreen().x, n2 + this.q.getLocationOnScreen().y, this.q.e(), n3);
        this.q.setVisible(true);
        this.q.pack();
        this.q.requestFocus();
        this.q.q(n3);
    }
    
    private synchronized void q(final bf bf) {
        if (bf.q == null) {
            bf.q = bf;
            return;
        }
        bf.q.q();
        bf.q = bf;
    }
    
    private synchronized void w(final bf bf) {
        if (bf.q == bf) {
            bf.q = null;
        }
    }
    
    public final int q() {
        return this.w;
    }
    
    public final boolean q() {
        return false;
    }
    
    public final boolean e() {
        return this.q;
    }
    
    public final int t() {
        return this.e;
    }
    
    public final Color q(final String s, final int n, final int n2) {
        String s2;
        if (n == 0) {
            s2 = "";
        }
        else {
            s2 = "in";
        }
        Color color = new Color(0, 0, 0);
        try {
            if (n2 == 0) {
                color = new Color(Integer.parseInt(this.q(s + "_bgcr" + s2), 16));
            }
            else if (n2 == 1) {
                color = new Color(Integer.parseInt(this.q(s + "_fgcr" + s2), 16));
            }
        }
        catch (Exception ex) {}
        return color;
    }
    
    public final Font q(final String s, final int n) {
        String s2;
        if (n == 0) {
            s2 = "";
        }
        else {
            s2 = "in";
        }
        Font font = new Font("TimesRoman", 0, 12);
        try {
            font = new Font(this.q(s + "_fntname" + s2), Integer.parseInt(this.q(s + "_fnttyp" + s2)), Integer.parseInt(this.q(s + "_fntsz" + s2)));
            final FontMetrics q = this.q(font);
            final int e;
            if ((e = q.getMaxDescent() + q.getMaxAscent()) > this.e) {
                this.e = e;
            }
        }
        catch (Exception ex) {}
        return font;
    }
    
    public final int r() {
        return this.q;
    }
    
    public final void q() {
        if (this.q != null) {
            if (this.q != null) {
                this.q.removeAll();
                this.q((Component)this.q);
            }
            this.q.setVisible(false);
            this.w(this);
        }
    }
    
    private aW q(final String s, final boolean b) {
        int n = 1;
        final aW aw = new aW();
        while (this.q(s + n)) {
            final String string = s + n;
            String s2;
            if ((s2 = this.q(string + "_tr")) == null) {
                s2 = this.q;
            }
            final String q = this.q(string + "_line");
            final String q2 = this.q(string + "_mark");
            boolean booleanValue = false;
            if (q2 != null) {
                booleanValue = Boolean.valueOf(q2);
            }
            final String q3 = this.q(string + "_txt");
            String q4 = this.q(string + "_I");
            final String s3 = (q3 != null) ? (dV.q("=5>E") + "_" + dV.q(q3.toLowerCase(), ' ', '_') + ".gif") : null;
            if (q4 == null) {
                q4 = s3;
            }
            Image image;
            if (q4 != null && q4.trim().length() > 0) {
                image = AppletAbstract.q().getImage(AppletAbstract.q().getCodeBase(), "Resources/" + dV.q("=5>E") + "/" + q4);
            }
            else {
                image = null;
            }
            Object o;
            if (q != null) {
                o = new aY();
            }
            else {
                o = new aX(q3, this.q(string), s2, b, image, booleanValue);
            }
            if (this.q(s + n + "_1")) {
                ((aX)o).q(this.q(s + n + "_", false));
            }
            ++n;
            aw.q(o);
        }
        return aw;
    }
    
    private URL q(String q) {
        URL url = null;
        try {
            if ((q = this.q(q + "_url")) != null) {
                url = new URL(q);
            }
        }
        catch (MalformedURLException ex) {}
        return url;
    }
    
    private boolean q(final String s) {
        return this.q(s + "_txt") != null || this.q(s + "_line") != null;
    }
    
    public final synchronized void w() {
        ++this.r;
    }
    
    public final synchronized void e() {
        --this.r;
    }
    
    public final boolean t() {
        return this.r == 0;
    }
    
    public final synchronized void r() {
        this.r = 0;
    }
    
    private String q(final String s) {
        return this.q.get(s);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
    
    public final int w() {
        return 0;
    }
    
    public final int e() {
        return 0;
    }
    
    public final boolean w() {
        return true;
    }
    
    public final FontMetrics q(final Font font) {
        return this.q.getFontMetrics(font);
    }
    
    public final boolean r() {
        return false;
    }
    
    static {
        bf.q = null;
    }
}
