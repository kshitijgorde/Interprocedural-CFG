// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.JOptionPane;
import java.awt.event.MouseEvent;
import javax.swing.SwingUtilities;
import java.util.Observable;
import java.util.Iterator;
import javax.swing.Box;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.awt.Rectangle;
import java.awt.Component;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Observer;
import java.awt.event.MouseListener;

public class aS extends H implements MouseListener, Observer
{
    private int a;
    private bg b;
    private JPanel c;
    private JPanel d;
    private JPanel e;
    private boolean f;
    private x g;
    private JLabel h;
    private JLabel i;
    private JLabel j;
    private JLabel k;
    private ab l;
    private boolean m;
    private String n;
    private static /* synthetic */ boolean o;
    
    public aS(final w w, final bg b, final int n, int b2) {
        this.a = -1;
        this.f = false;
        this.m = false;
        if (!aS.o && b == null) {
            throw new AssertionError();
        }
        if (!aS.o && n <= 0) {
            throw new AssertionError();
        }
        if (!aS.o && b2 <= 0) {
            throw new AssertionError();
        }
        this.b = b;
        w.addObserver(this);
        this.setBounds(0, 0, n, b2);
        this.a(b.d("backcolorTop"));
        this.b(b.d("backcolorBottom"));
        this.a(true);
        this.setOpaque(false);
        this.setLayout(null);
        this.setBorder(new EtchedBorder());
        final JLabel label;
        (label = new JLabel()).setOpaque(false);
        label.addMouseListener(this);
        label.setBounds(0, 0, 9, b.b("top"));
        this.add(label);
        b2 = 0;
        if (b.a("tableTitleHeight")) {
            b2 = b.b("tableTitleHeight");
        }
        final Rectangle bounds = new Rectangle(10, 5, this.getWidth(), b.b("top") - b2 - 5);
        final Rectangle bounds2 = new Rectangle(10, b.b("top") - b2 - 5, this.getWidth(), b2);
        (this.d = new JPanel()).setBounds(bounds);
        this.d.setLayout(new BoxLayout(this.d, 0));
        this.d.setOpaque(false);
        this.d.setVisible(false);
        this.a(b.f("header"), this.d);
        this.d.add(Box.createHorizontalGlue());
        this.add(this.d);
        if (b2 > 0) {
            (this.c = new JPanel()).setBounds(bounds2);
            this.c.setLayout(null);
            this.c.setOpaque(false);
            this.c.setVisible(false);
            this.add(this.c);
        }
        (this.e = new JPanel()).setLayout(new BoxLayout(this.e, 0));
        this.e.setOpaque(false);
        this.e.setBounds(new Rectangle(0, this.getHeight() - b.b("bottom"), this.getWidth(), b.b("bottom")));
        this.add(this.e);
        this.a(b.f("footer"), this.e);
    }
    
    private void a(final bg bg, final JPanel panel) {
        final Iterator<bg> iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            if ((bg2 = iterator.next()).b().equals("horizGap")) {
                final String c;
                if ((c = bg2.c("size")).equals("*")) {
                    panel.add(Box.createHorizontalGlue());
                }
                else {
                    panel.add(Box.createHorizontalStrut(Integer.parseInt(c)));
                }
            }
            else if (bg2.b().equals("label")) {
                final JLabel a = al.a(bg2);
                panel.add(a);
                final String c2;
                if ((c2 = bg2.c("caption")).equals("#TIME_REMAINING")) {
                    (this.i = a).setText("");
                }
                else if (c2.equals("#PHOTOS_COMPLETED")) {
                    (this.k = a).setText("");
                }
                else if (c2.equals("#TRANSFER_RATE")) {
                    (this.j = a).setText("");
                }
                else {
                    if (!c2.equals("$ESTIMATED_TIME")) {
                        continue;
                    }
                    this.n = a.getText();
                    (this.h = a).setVisible(false);
                }
            }
            else {
                if (!bg2.b().equals("progressBar")) {
                    throw new RuntimeException("Unexpected item under <footer>: " + bg2.b());
                }
                panel.add(this.g = al.c(bg2));
            }
        }
    }
    
    public final Rectangle a() {
        return new Rectangle(this.b.b("left"), this.b.b("top"), this.getWidth() - this.b.b("left") - this.b.b("right"), this.getHeight() - this.b.b("top") - this.b.b("bottom"));
    }
    
    public final void a(final int a) {
        if (!aS.o && a != 1 && a != 2) {
            throw new AssertionError();
        }
        if (a == this.a) {
            return;
        }
        if (a == 2) {
            if (!this.m) {
                this.b(this.l);
            }
            this.d.setVisible(false);
        }
        else if (a == 1) {
            if (this.c != null) {
                this.c.setVisible(false);
            }
            if (this.f) {
                this.d.setVisible(true);
            }
        }
        this.a = a;
    }
    
    public final void b() {
        if (this.c != null) {
            this.c.setVisible(true);
        }
    }
    
    private void b(final ab ab) {
        if (!aS.o && ab == null) {
            throw new AssertionError();
        }
        final int n = this.b.b("left") + 3;
        final Iterator<v> iterator = ab.c().iterator();
        while (iterator.hasNext()) {
            final v v;
            if ((v = iterator.next()).a != "") {
                final JLabel b;
                (b = al.b(this.b.f("header").c("style"), v.a)).setBounds(new Rectangle(n + v.b, this.b.f("header").b("titleOffsetVert"), b.getPreferredSize().width, b.getPreferredSize().height));
                this.c.add(b);
            }
        }
        this.m = true;
    }
    
    private void d() {
        this.f = true;
        this.d.setVisible(true);
    }
    
    public final void c() {
        this.f = false;
        this.e.setVisible(false);
    }
    
    public final void a(final ab l) {
        if (!aS.o && l == null) {
            throw new AssertionError();
        }
        if (!aS.o && this.l != null) {
            throw new AssertionError();
        }
        this.l = l;
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof D) {
            final Y a;
            if ((a = ((D)o).a) != Y.a) {
                if (a == Y.b) {
                    this.d();
                    this.h.setVisible(true);
                    this.h.setText(G.c("INIT_UPLOAD"));
                }
                return;
            }
            this.d();
            this.h.setVisible(true);
            this.h.setText(G.c("PREPARING_PHOTOS"));
        }
        else {
            if (o instanceof bl) {
                SwingUtilities.invokeLater(new ai(this, o));
                return;
            }
            if (o instanceof aU) {
                SwingUtilities.invokeLater(new ak(this));
            }
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        JOptionPane.showMessageDialog(this, new Object[] { V.a("/images/egg-sm.png", "egg"), "<HTML><h2><i><b>Easy Uploader</b></i><h2>\n(c) PNI Digital\nwww.photochannel.com\nwritten by Todd White, 2007" }, "Easy Uploader", -1);
    }
    
    static {
        aS.o = !aS.class.desiredAssertionStatus();
    }
}
