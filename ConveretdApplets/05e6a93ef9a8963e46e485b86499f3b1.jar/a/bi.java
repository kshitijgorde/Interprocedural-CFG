// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Panel;
import java.util.Hashtable;
import java.awt.Frame;

public abstract class bi implements bt
{
    protected W q;
    private j q;
    private int q;
    private int w;
    private int e;
    private aW q;
    private int r;
    private int t;
    private boolean q;
    private String q;
    private boolean w;
    private boolean e;
    private int y;
    protected Frame q;
    protected Hashtable q;
    private int u;
    
    public static void q(final W w) {
        bn.q(new String[] { "window.focus(); setTimeout(\"window.external.AddFavorite('" + h.q().getCodeBase() + "', window.document.title);\", 100);" }, w);
    }
    
    public bi() {
        this.r = 15;
        this.q = false;
        this.e = true;
        this.u = 0;
    }
    
    public bi(final W q) {
        this.r = 15;
        this.q = false;
        this.e = true;
        this.u = 0;
        this.q = q;
        this.w();
    }
    
    public final void q(final Frame q) {
        this.q = q;
        this.r = 20;
        this.t = 0;
        this.q = aT.w.w();
        this.w = aT.w.q();
        this.q = "_blank";
        this.e = false;
        ((Frame)(this.q = this.q("mi_", true))).setBackground(aT.w.k);
        this.e = 21;
        (this.q = new j(this.q, this.e, this.q, this)).setBounds(0, 0, this.q, this.e);
    }
    
    public final void q(final int q, final int w) {
        this.q = q;
        this.w = w;
    }
    
    public final Panel q() {
        return this.q;
    }
    
    protected abstract void w();
    
    protected final void e() {
        this.q = new Hashtable();
        final Font y = be.y;
        this.q.put("mb_bgcr", Integer.toString(aT.w.k.getRGB(), 16));
        this.q.put("mb_bgcrin", Integer.toString(aT.w.l.getRGB(), 16));
        this.q.put("mb_fgcrin", Integer.toString(aT.w.z.getRGB(), 16));
        this.q.put("mb_fntname", y.getName());
        this.q.put("mb_fnttyp", "" + y.getStyle());
        this.q.put("mb_fntsz", "" + y.getSize());
        this.q.put("mb_fntnameIn", y.getName());
        this.q.put("mb_fnttypIn", "" + y.getStyle());
        this.q.put("mb_fntszIn", "" + y.getSize());
        this.q.put("mi_bgcr", Integer.toString(aT.w.x.getRGB(), 16));
        this.q.put("mi_bgcrin", Integer.toString(aT.w.c.getRGB(), 16));
        this.q.put("mi_fgcrin", Integer.toString(aT.w.v.getRGB(), 16));
        this.q.put("mi_fntname", y.getName());
        this.q.put("mi_fnttyp", "" + y.getStyle());
        this.q.put("mi_fntsz", "" + y.getSize());
        this.q.put("mi_fntnameIn", y.getName());
        this.q.put("mi_fnttypIn", "" + y.getStyle());
        this.q.put("mi_fntszIn", "" + (y.getSize() + 1));
    }
    
    public final Component q(final Component component) {
        this.q.add(component, 0);
        return component;
    }
    
    public final void q(final Component component) {
        this.q.remove(component);
    }
    
    public final int q() {
        return 0;
    }
    
    public final boolean q() {
        return this.q;
    }
    
    public final boolean w() {
        return this.w;
    }
    
    public final int w() {
        return this.y;
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
            s2 = "In";
        }
        Font font = new Font("TimesRoman", 0, 12);
        try {
            font = new Font(this.q(s + "_fntname" + s2), Integer.parseInt(this.q(s + "_fnttyp" + s2)), Integer.parseInt(this.q(s + "_fntsz" + s2)));
            final FontMetrics q = this.q(font);
            final int y;
            if ((y = q.getMaxDescent() + q.getMaxAscent()) > this.y) {
                this.y = y;
            }
        }
        catch (Exception ex) {}
        return font;
    }
    
    public final int e() {
        return this.r;
    }
    
    public final void q() {
        if (this.q != null) {
            this.q.removeAll();
        }
        if (this.q != null) {
            this.q.invalidate();
            this.q.repaint();
            this.q.validate();
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
            final String s3 = (q3 != null && string.length() > 4) ? (ce.q("=5>E") + "_" + ce.q(q3.toLowerCase(), ' ', '_') + ".gif") : "";
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
        ++this.u;
    }
    
    public final synchronized void t() {
        --this.u;
    }
    
    public final boolean e() {
        return this.u == 0;
    }
    
    public final synchronized void y() {
        this.u = 0;
    }
    
    private String q(final String s) {
        return this.q.get(s);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return true;
    }
    
    public final int r() {
        return this.q;
    }
    
    public final int t() {
        return this.w;
    }
    
    public final boolean r() {
        return false;
    }
    
    public final boolean t() {
        return this.e;
    }
    
    public final FontMetrics q(final Font font) {
        return this.q.getFontMetrics(font);
    }
    
    public final void u() {
        this.q.repaint();
    }
    
    protected final void q(final aa aa) {
        for (int i = 0; i < this.q.k.q; ++i) {
            final aY ay = (aY)this.q.k.q(i);
            if (aa.q.equalsIgnoreCase(ay.o)) {
                bn.q(h.q().getCodeBase().toExternalForm() + "Resources/" + co.p + "/" + cy.o, ay.o, this.q);
            }
        }
    }
    
    protected final void w(final int n, int i) {
        String s = "mi_" + n;
        if (i > 0) {
            s = s + "_" + i;
        }
        if (this.q.k.q > 0) {
            this.q.put(s + "_txt", cy.o);
        }
        for (i = 0; i < this.q.k.q; ++i) {
            this.q.put(s + "_" + (i + 1) + "_txt", ((aY)this.q.k.q(i)).o);
        }
    }
}
