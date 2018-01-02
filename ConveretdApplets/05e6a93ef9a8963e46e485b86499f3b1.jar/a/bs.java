// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Component;
import java.awt.Window;

public abstract class bs implements bt, cB
{
    protected W q;
    private Q q;
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
    private static bs q;
    
    public bs(final Component component, final W w) {
        this(component, 12632319, w);
    }
    
    private bs(final Component q, final int n, final W q2) {
        this.q = 15;
        this.q = new Hashtable();
        this.r = 0;
        this.q = q;
        this.q = q2;
        this.e();
        this.w();
    }
    
    protected bs() {
        this.q = 15;
        this.q = new Hashtable();
        this.r = 0;
    }
    
    protected final void w() {
        this.q = 18;
        this.w = 10;
        aT.w.w();
        this.q = aT.w.q();
        this.q = "_blank";
        this.q = this.q("mi_", true);
        this.q = new Q(this.q, this, true, null);
    }
    
    protected abstract void e();
    
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
        (this.q = aL.q(this.q, this.q)).setLocation(n, n2);
        this.q.setSize(0, 0);
        this.q((Component)this.q);
        final int n3 = this.q.q * this.q + this.q.w * 3 + 4 + (W.e() ? 0 : this.q);
        this.q.setBounds(n + this.q.getLocationOnScreen().x, n2 + this.q.getLocationOnScreen().y, this.q.e, n3);
        this.q.setVisible(true);
        this.q.pack();
        this.q.requestFocus();
        this.q.q(n3);
    }
    
    private synchronized void q(final bs bs) {
        if (bs.q == null) {
            bs.q = bs;
            return;
        }
        bs.q.q();
        bs.q = bs;
    }
    
    private synchronized void w(final bs bs) {
        if (bs.q == bs) {
            bs.q = null;
        }
    }
    
    public final int q() {
        return this.w;
    }
    
    public final boolean q() {
        return false;
    }
    
    public final boolean w() {
        return this.q;
    }
    
    public final int w() {
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
    
    public final int e() {
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
            final String s3 = (q3 != null) ? (ce.q("=5>E") + "_" + ce.q(q3.toLowerCase(), ' ', '_') + ".gif") : null;
            if (q4 == null) {
                q4 = s3;
            }
            Image image;
            if (q4 != null && q4.trim().length() > 0) {
                image = h.q().getImage(h.q().getCodeBase(), "Resources/" + ce.q("=5>E") + "/" + q4);
            }
            else {
                image = null;
            }
            Object o;
            if (q != null) {
                o = new y();
            }
            else {
                o = new aa(q3, this.q(string), s2, b, image, booleanValue);
            }
            if (this.q(s + n + "_1")) {
                ((aa)o).q(this.q(s + n + "_", false));
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
    
    public final synchronized void r() {
        ++this.r;
    }
    
    public final synchronized void t() {
        --this.r;
    }
    
    public final boolean e() {
        return this.r == 0;
    }
    
    public final synchronized void y() {
        this.r = 0;
    }
    
    private String q(final String s) {
        return this.q.get(s);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
    
    public final int r() {
        return 0;
    }
    
    public final int t() {
        return 0;
    }
    
    public final boolean r() {
        return true;
    }
    
    public final FontMetrics q(final Font font) {
        return this.q.getFontMetrics(font);
    }
    
    public final boolean t() {
        return false;
    }
    
    static {
        bs.q = null;
    }
}
