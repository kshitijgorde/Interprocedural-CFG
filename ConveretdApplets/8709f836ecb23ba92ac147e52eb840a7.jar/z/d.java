// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Iterator;
import java.awt.event.MouseEvent;
import java.awt.Composite;
import java.awt.AlphaComposite;
import java.awt.image.ImageObserver;
import java.awt.Stroke;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.util.EventObject;
import javax.swing.Action;
import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.border.Border;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JCheckBox;
import java.awt.Color;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class d extends JPanel implements MouseListener
{
    private final String a;
    private final int b;
    private int c;
    private aW d;
    private final Color e;
    private I f;
    private JCheckBox g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private static Image l;
    private static float m;
    private long n;
    private static /* synthetic */ boolean o;
    
    public d(final bg bg, final String a, final ImageIcon imageIcon, final aW d, final int b, final boolean k) {
        this.h = false;
        this.j = false;
        this.n = -1L;
        if (!z.d.o && a == null) {
            throw new AssertionError();
        }
        if (!z.d.o && b < 0) {
            throw new AssertionError();
        }
        this.a = a;
        this.i = false;
        this.b = b;
        this.c = bg.b("doubleClickTimeoutMillisec");
        this.d = d;
        this.k = k;
        z.d.l = V.a(bg.j("tooLargeOverlayImage")).getImage();
        z.d.m = bg.h("tooLargeOverlayImageOpacity");
        if (d == aW.a) {
            this.setToolTipText(G.c("DOUBLE_CLICK_FOLDER_TOOLTIP"));
        }
        this.e = bg.d("borderColorSelected");
        this.setBorder(BorderFactory.createLineBorder(bg.d("borderColorRegular"), 1));
        this.setPreferredSize(bg.e("size"));
        this.a(bg, imageIcon);
        this.addMouseListener(this);
    }
    
    private void a(bg iterator, final ImageIcon imageIcon) {
        this.setLayout(new BoxLayout(this, 1));
        iterator = (bg)iterator.a().iterator();
        while (((Iterator)iterator).hasNext()) {
            final bg bg;
            JPanel panel;
            if ((bg = ((Iterator<bg>)iterator).next()).b().equals("gradientPanel")) {
                final H h = (H)(panel = new H());
                h.setOpaque(false);
                h.setBorder(null);
                if (this.k) {
                    h.a(bg.d("backcolor0Invalid"));
                    h.b(bg.d("backcolor1Invalid"));
                }
                else {
                    h.a(bg.d("backcolor0"));
                    h.b(bg.d("backcolor1"));
                }
                h.a(bg.k("isVertical"));
                h.setPreferredSize(new Dimension(1, bg.b("height")));
                h.setAlignmentX(0.5f);
                this.add(h);
            }
            else {
                if (!bg.b().equals("panel")) {
                    throw new RuntimeException("Unexpected UI node: " + bg.b());
                }
                final JPanel panel2 = panel = new JPanel();
                panel2.setOpaque(true);
                panel2.setBorder(null);
                panel2.setBackground(bg.d("backcolor"));
                panel2.setPreferredSize(new Dimension(1, bg.b("height")));
                panel2.setAlignmentX(0.5f);
                this.add(panel2);
            }
            if (bg.a("id")) {
                final String c;
                if ((c = bg.c("id")).equals("thumb")) {
                    final JPanel panel3 = panel;
                    final bg f = bg.f("thumbnail");
                    final JPanel panel4 = panel3;
                    final JPanel panel5;
                    (panel5 = new JPanel()).setOpaque(false);
                    panel5.setLayout(new BoxLayout(panel5, 1));
                    final JLabel label;
                    (label = new JLabel(imageIcon)).setAlignmentX(0.5f);
                    label.setAlignmentY(0.5f);
                    label.setName("thumb");
                    if (this.d == aW.b) {
                        label.setBorder(BorderFactory.createLineBorder(f.d("frameColor"), 1));
                    }
                    panel5.add(Box.createVerticalGlue());
                    panel5.add(label);
                    panel5.add(Box.createVerticalGlue());
                    panel4.setLayout(new BorderLayout());
                    panel4.add(panel5);
                }
                else {
                    if (!c.equals("filename")) {
                        throw new RuntimeException("Unexpected panel ID: " + c);
                    }
                    final JPanel panel6 = panel;
                    final bg bg2 = bg;
                    final JPanel panel7 = panel6;
                    panel7.setLayout(new BorderLayout());
                    final int n = bg2.b("height") - 8;
                    String s;
                    if ((s = au.a(this.a)).startsWith("$")) {
                        s = s.substring(1);
                    }
                    if (this.a.endsWith(":\\")) {
                        s = this.a;
                    }
                    final JLabel b = al.b(bg2.c("style"), s);
                    if (this.d == aW.a) {
                        final Dimension dimension = new Dimension(this.getPreferredSize().width - 30, n);
                        b.setMaximumSize(dimension);
                        b.setPreferredSize(dimension);
                        b.setHorizontalAlignment(0);
                        panel7.add(b, "Center");
                    }
                    else {
                        final JPanel panel8;
                        (panel8 = new JPanel()).setLayout(new BoxLayout(panel8, 0));
                        panel8.setOpaque(false);
                        final JCheckBox g;
                        (g = new JCheckBox(new bn(this, this))).setOpaque(false);
                        (this.g = g).setSelected(this.h);
                        if (this.j) {
                            this.g.setEnabled(false);
                        }
                        final Dimension dimension2 = new Dimension(this.getPreferredSize().width - g.getWidth() - 30, n);
                        b.setMaximumSize(dimension2);
                        b.setPreferredSize(dimension2);
                        if (this.k) {
                            Color foreground;
                            try {
                                foreground = bg2.d("tooLargeTextColor");
                            }
                            catch (Throwable t) {
                                foreground = Color.white;
                            }
                            b.setForeground(foreground);
                            b.setHorizontalAlignment(0);
                        }
                        else {
                            panel8.add(g);
                        }
                        panel8.add(Box.createHorizontalGlue());
                        panel8.add(b);
                        panel8.add(Box.createHorizontalGlue());
                        panel7.add(panel8);
                    }
                }
            }
        }
    }
    
    public final boolean a() {
        return this.d == aW.a;
    }
    
    public final String b() {
        return this.a;
    }
    
    public final int c() {
        return this.b;
    }
    
    public final boolean d() {
        if (this.g != null && !z.d.o && this.g.isSelected() != this.h) {
            throw new AssertionError();
        }
        return this.h;
    }
    
    protected final void e() {
        this.h = this.g.isSelected();
        if (this.f != null) {
            this.f.a(new ac(this));
        }
    }
    
    public final void a(final I f) {
        this.f = f;
    }
    
    public final void f() {
        this.a(!this.d());
    }
    
    public final boolean g() {
        return this.k;
    }
    
    public final void a(final boolean b) {
        if (this.j) {
            return;
        }
        if (this.h != b) {
            this.h = b;
            if (this.g != null && this.g.isSelected() != b) {
                this.g.setSelected(b);
                this.e();
            }
        }
    }
    
    public final void b(final boolean i) {
        this.i = i;
        this.repaint();
    }
    
    public void paintChildren(final Graphics graphics) {
        super.paintChildren(graphics);
        final Graphics2D graphics2D = (Graphics2D)graphics;
        if (this.i) {
            graphics2D.setColor(this.e);
            graphics2D.setStroke(new BasicStroke(6));
            graphics2D.drawRect(0, 0, this.getWidth(), this.getHeight());
        }
        if (this.k) {
            final d d = this;
            final Graphics2D graphics2D2 = graphics2D;
            this = d;
            final int height = d.getComponents()[0].getHeight();
            final int height2 = z.d.l.getHeight(null);
            final int width = z.d.l.getWidth(null);
            final int width2;
            final int n = (width2 = this.getWidth()) / width * height2;
            final int n2 = width;
            final int n3 = height2;
            final int n4 = height - n;
            final int n5 = width2;
            final int n6 = height;
            graphics2D2.setComposite(AlphaComposite.getInstance(3, z.d.m));
            graphics2D2.drawImage(z.d.l, 0, n4, n5, n6, 0, 0, n2, n3, null);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.k) {
            return;
        }
        final long n = System.currentTimeMillis() - this.n;
        this.n = System.currentTimeMillis();
        if (this.f != null) {
            if (n < this.c) {
                this.f.a(new m(this));
                return;
            }
            this.f.a(new c(this));
        }
    }
    
    public final void h() {
        if (this.g != null) {
            if (this.d()) {
                this.a(false);
            }
            this.g.setEnabled(false);
        }
        this.j = true;
    }
    
    static {
        d.o = !d.class.desiredAssertionStatus();
        d.l = null;
    }
}
