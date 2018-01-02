// 
// Decompiled by Procyon v0.5.30
// 

package daeja4.filter.msg;

import java.awt.Graphics2D;
import java.awt.Graphics;
import ji.image.c2;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import ji.v1event.a6;
import ji.image.ds;
import javax.swing.text.EditorKit;
import java.awt.EventQueue;
import java.awt.Toolkit;
import ji.util.np;
import ji.res.s;
import ji.util.i;
import javax.swing.text.Document;
import java.io.InputStream;
import ji.io.a5;
import ji.v1event.af;
import ji.io.ac;
import java.awt.Insets;
import ji.io.h;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import ji.image.dx;
import javax.swing.JEditorPane;
import javax.swing.text.html.HTMLDocument;
import javax.swing.JFrame;
import ji.document.ad;

public class nm
{
    private ad a;
    private nd b;
    private JFrame c;
    private HTMLDocument d;
    private nn e;
    private JEditorPane f;
    private boolean g;
    private boolean h;
    private int i;
    private int j;
    private int k;
    private boolean l;
    private double m;
    
    public nm(final dx dx, final ad a, final nd b, final int j) throws Exception {
        this.l = false;
        this.b = b;
        this.g = false;
        this.c = new JFrame("Outlook Message:  ".concat(String.valueOf(String.valueOf(dx.f))));
        this.f = new JEditorPane();
        this.a = a;
        this.j = j;
        this.k = -1;
        this.h = true;
        try {
            final JPanel panel = new JPanel();
            final JScrollPane scrollPane = new JScrollPane();
            this.f.setEditable(false);
            scrollPane.setMaximumSize(new Dimension(dx.m, dx.n));
            scrollPane.getViewport().add(this.f, "Center");
            panel.setLayout(new BorderLayout());
            panel.add(scrollPane, "Center");
            this.c.getContentPane().setLayout(new BorderLayout());
            this.c.getContentPane().add(panel, "Center");
            this.c.pack();
            final Insets insets = this.c.getInsets();
            this.i = j + insets.left + insets.right;
            this.c.setBounds(0, 0, this.i, 100);
        }
        catch (Exception ex) {
            ji.io.h.a(a.getId(), ex);
        }
    }
    
    public void a(final ac ac, final dx dx, final String s, final String s2, final af af) throws Exception {
        if (this.g) {
            return;
        }
        ac.a(0L);
        final a5 a5 = new a5(ac, this.a);
        final no a6 = this.a(s);
        this.e.read(a5, a6, 0);
        a5.close();
        if (s2 != null) {
            a6.a(s2);
        }
        if (ji.util.i.c(5)) {
            ji.io.h.c(this.a.getId(), "document length = ".concat(String.valueOf(String.valueOf(a6.getLength()))));
        }
        if (this.a(a6)) {
            this.h = true;
            this.a((Document)a6);
            return;
        }
        this.h = false;
        throw new Exception(s.a(1324, this.a.getId()));
    }
    
    private boolean a(final no no) {
        return no.getLength() <= this.a.bl(3);
    }
    
    private void a(final Document document) {
        this.f.setDocument(document);
        this.f.setEditable(false);
        this.c.setBounds(0, 0, this.i, 100);
        this.c.validate();
        if (!np.a(3000L, this.a.getId())) {
            Toolkit.getDefaultToolkit().getSystemEventQueue().push(new EventQueue());
            np.a(3000L, this.a.getId());
        }
        this.j = this.f.getWidth();
        this.k = this.f.getHeight();
        this.g = true;
    }
    
    private no a(final String s) {
        this.e = new nn(this.b, this.a.bi(31), this.a);
        this.f.setEditorKit(this.e);
        final no no = (no)this.e.createDefaultDocument();
        no.putProperty("content-type", s);
        no.putProperty("stream", null);
        no.putProperty("IgnoreCharsetDirective", Boolean.TRUE);
        return no;
    }
    
    public int a() {
        return this.j;
    }
    
    public int b() {
        return this.k;
    }
    
    public void a(final dx dx) {
        this.b.c();
        if (this.e instanceof nn) {
            this.e.a();
        }
        this.e = null;
        this.f = null;
        this.d = null;
        this.g = false;
        this.a = null;
        if (this.c != null) {
            this.c.dispose();
        }
    }
    
    public void a(final String s, final ds ds, final dx dx, final af af, final ad ad) throws Exception {
        if (this.g) {
            if (this.h) {
                this.b(s, ds, dx, af, ad);
            }
            else {
                this.c(s, ds, dx, af, ad);
            }
            return;
        }
        throw new IllegalStateException("content not set");
    }
    
    private void b(final String s, final ds ds, final dx dx, final af af, final ad ad) throws Exception {
        int p5 = 2097152 / (int)(dx.o * 4 * this.m);
        double n = 0.0;
        double n2 = 0.0;
        int n3 = 0;
        if (p5 < 1) {
            p5 = 1;
        }
        else if (p5 > dx.p) {
            p5 = dx.p;
        }
        int n4 = (int)(p5 * this.m);
        int n5 = dx.p / p5;
        final double n6 = 100.0 / n5;
        final a6 a6 = new a6(this, 4, "0");
        int n7 = n5 * n4;
        int n8 = 0;
        if (dx.p % p5 != 0) {
            n7 += (int)((dx.p - n5 * p5) * this.m);
            ++n5;
        }
        if (n7 < dx.n) {
            ++n4;
            n = (n7 + n5 - dx.n) / n5;
        }
        BufferedImage bufferedImage = new BufferedImage(dx.m, n4, 1);
        final Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics.scale(this.m, this.m);
        ds.a(4, false, ad);
        ds.j = dx.m;
        ds.k = dx.n;
        final c2 c2 = new c2(ad.getId(), ad, dx, null, bufferedImage.getSource(), 0, 0, (int)ds.j, (int)ds.k, 0, ds, 1);
        c2.a(dx.ak);
        try {
            for (int i = 0; i < n5; ++i) {
                c2.h();
                graphics.setClip(0, n8, dx.o, p5);
                this.f.paint(graphics);
                final boolean a7 = c2.a(0L, true);
                a6.a(Integer.toString((int)(i * n6)));
                af.a(a6);
                if (a7) {
                    if ((c2.b() | 0x40) != 0x40) {
                        if ((c2.b() | 0x80) == 0x80) {}
                    }
                }
                n8 += p5;
                graphics.translate(0, -p5);
                if (n > 0 && (int)n2 > (int)(n2 - n)) {
                    c2.a(0, n4 - 1);
                    ++n3;
                }
                else {
                    c2.a(0, n4);
                }
                if (n8 + p5 > dx.p && i + 1 < n5) {}
                n2 += n;
            }
            if (c2.f()) {
                dx.am = 24;
            }
        }
        catch (Exception ex) {
            ji.io.h.a(ad.getId(), ex);
        }
        finally {
            if (bufferedImage != null) {
                bufferedImage.flush();
                bufferedImage = null;
            }
            if (c2 != null) {
                c2.imageComplete(c2.b());
                c2.i();
            }
            if (dx.am > 1) {
                ds.e(ad);
            }
            if (bufferedImage != null) {
                bufferedImage.flush();
            }
        }
    }
    
    private void c(final String s, final ds ds, final dx dx, final af af, final ad ad) {
    }
    
    void a(final double m) {
        this.m = m;
    }
}
