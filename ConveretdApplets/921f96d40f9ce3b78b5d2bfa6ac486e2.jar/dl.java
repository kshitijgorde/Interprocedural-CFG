import java.awt.event.MouseEvent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class dl extends Canvas implements MouseListener
{
    public static n p;
    public static String d;
    private static int a;
    private String n;
    private Image v;
    private Color i;
    private int l;
    private int b;
    private Image c;
    private boolean e;
    private boolean f;
    private boolean g;
    public static int h;
    public static int j;
    public static int k;
    public static int m;
    public static int o;
    private int q;
    private int r;
    public Vector s;
    
    public dl(final String s) {
        this(s, dl.h);
        this.addMouseListener(this);
    }
    
    public dl(final String s, final int n) {
        this(s, n, dl.j);
        this.addMouseListener(this);
    }
    
    public final void p(final ActionListener actionListener) {
        this.s.addElement(actionListener);
    }
    
    public final void p(final ActionEvent actionEvent) {
        for (int i = 0; i < this.s.size(); ++i) {
            ((ActionListener)this.s.elementAt(i)).actionPerformed(actionEvent);
        }
    }
    
    public dl(final String n, final int q, final int r) {
        this.g = false;
        this.s = new Vector();
        this.n = n;
        this.q = q;
        this.r = r;
        if (dl.p != null) {
            this.i = Color.white;
            this.v = dl.p.p(n, dl.d, this.i);
        }
    }
    
    public final void p() {
        if (this.v == null) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
            this.l = ((this.q == dl.h) ? dl.a : ((this.q == dl.k) ? (this.getSize().width - dl.a - fontMetrics.stringWidth(this.n)) : ((this.getSize().width - fontMetrics.stringWidth(this.n)) / 2)));
            this.b = ((this.r == dl.o) ? (this.getSize().height - dl.a - fontMetrics.getDescent()) : ((this.r == dl.m) ? (dl.a + fontMetrics.getAscent()) : ((this.getSize().height + fontMetrics.getAscent() - fontMetrics.getDescent()) / 2)));
        }
        else {
            this.l = ((this.q == dl.h) ? dl.a : ((this.q == dl.k) ? (this.getSize().width - dl.a - this.v.getWidth(this)) : ((this.getSize().width - this.v.getWidth(this)) / 2)));
            this.b = ((this.r == dl.o) ? (this.getSize().height - dl.a - this.v.getHeight(this)) : ((this.r == dl.m) ? dl.a : ((this.getSize().height - this.v.getHeight(this)) / 2)));
        }
        this.repaint();
    }
    
    public final void addNotify() {
        super.addNotify();
        this.p();
    }
    
    public final Dimension getMinimumSize() {
        final FontMetrics fontMetrics = this.getFontMetrics(this.getFont());
        if (this.v != null) {
            return new Dimension(2 + Math.max(this.v.getWidth(this), fontMetrics.stringWidth(this.n) + 2 * dl.a), 2 + Math.max(this.v.getHeight(this), fontMetrics.getHeight() + 2 * dl.a));
        }
        return new Dimension(fontMetrics.stringWidth(this.n) + 2 * dl.a, fontMetrics.getHeight() + 2 * dl.a);
    }
    
    public final Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public final String p() {
        return this.n;
    }
    
    public final void p(final String n) {
        if (!n.equals(this.n)) {
            this.n = n;
            if (dl.p != null) {
                this.v = dl.p.p(n, dl.d, this.i);
            }
            this.p();
            du.p(this);
        }
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.p();
    }
    
    public final void setSize(final int n, final int n2) {
        super.setSize(n, n2);
        this.p();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.getSize().width == 0 || this.getSize().height == 0) {
            return;
        }
        if (this.c == null || this.c.getWidth(this) < this.getSize().width || this.c.getHeight(this) < this.getSize().height) {
            if (this.c != null) {
                this.c.flush();
            }
            this.c = this.createImage(this.getSize().width, this.getSize().height);
        }
        final Graphics graphics2 = this.c.getGraphics();
        final Rectangle clipBounds = graphics.getClipBounds();
        if (clipBounds != null) {
            graphics2.clipRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        }
        if (!this.e || !this.f || this.g) {
            graphics2.setColor(this.getBackground());
            graphics2.fill3DRect(0, 0, this.getSize().width, this.getSize().height, true);
        }
        else {
            graphics2.setColor(this.getBackground().darker());
            graphics2.fill3DRect(0, 0, this.getSize().width, this.getSize().height, false);
        }
        if (this.v == null) {
            graphics2.setColor(this.getForeground());
            graphics2.drawString(this.n, this.l, this.b);
        }
        else {
            if (this.i.getRGB() != this.getForeground().getRGB() && !false) {
                this.i = this.getForeground();
                this.v = dl.p.p(this.n, dl.d, this.i);
            }
            graphics2.drawImage(this.v, this.l, this.b, this);
        }
        graphics.drawImage(this.c, 0, 0, this);
        graphics2.dispose();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final boolean e = x >= 0 && x < this.getSize().width && y >= 0 && y < this.getSize().height && !mouseEvent.isMetaDown() && !mouseEvent.isControlDown() && !mouseEvent.isShiftDown();
        if (this.e != e) {
            this.e = e;
            this.repaint();
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        this.mouseClicked(mouseEvent);
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.g) {
            return;
        }
        if (this.e && this.f && x >= 0 && x < this.getSize().width && y >= 0 && y < this.getSize().height) {
            this.p(new ActionEvent(this, 1001, this.n));
        }
        this.e = false;
        this.repaint();
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
        this.f = true;
        this.repaint();
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
        this.f = false;
        this.repaint();
    }
    
    public final String toString() {
        return "MyButton " + this.n;
    }
    
    static {
        dl.p = null;
        dl.d = "en";
        dl.a = 4;
        dl.j = 1;
        dl.k = 2;
        dl.o = 2;
    }
}
