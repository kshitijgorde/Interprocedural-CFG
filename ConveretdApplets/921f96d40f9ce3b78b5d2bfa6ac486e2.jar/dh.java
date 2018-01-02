import java.util.StringTokenizer;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Color;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.event.MouseListener;
import java.awt.event.AdjustmentListener;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class dh extends Panel implements AdjustmentListener, MouseListener
{
    boolean p;
    int d;
    Vector a;
    dj n;
    dm v;
    FontMetrics i;
    FontMetrics l;
    Image b;
    Hashtable c;
    Color e;
    int f;
    int g;
    int h;
    int j;
    int k;
    boolean m;
    dq o;
    Point q;
    long r;
    
    public final Point p() {
        return this.q;
    }
    
    public dh(final dq dq, final a a, final int n, final int n2, final boolean p5) {
        this(dq, a, n, n2);
        this.p = p5;
    }
    
    public dh(final dq o, final a a, final int d, final int n) {
        this.p = true;
        this.q = new Point();
        this.r = System.currentTimeMillis();
        this.o = o;
        this.d = d;
        this.i = this.getFontMetrics(dw.l);
        this.l = this.getFontMetrics(dw.b);
        this.a = new Vector();
        this.c = new Hashtable();
        if (a != null) {
            final Vector p4 = a.p();
            final Vector d2 = a.d();
            for (int i = 0; i < d2.size(); ++i) {
                this.c.put(p4.elementAt(i), d2.elementAt(i));
            }
        }
        this.setLayout(new BorderLayout(0, 0));
        (this.v = new dm(0, 200, 0, 0)).addAdjustmentListener(this);
        this.add(this.v, "East");
        this.addMouseListener(this);
    }
    
    public final void setBackground(final Color background) {
        super.setBackground(background);
        this.v.setBackground(background.darker());
    }
    
    public final void p(final Color e) {
        this.e = e;
    }
    
    public final void p(final int f, final int g) {
        this.f = f;
        this.g = g;
    }
    
    public final Dimension getPreferredSize() {
        return new Dimension(this.f, this.g);
    }
    
    public final void invalidate() {
        super.invalidate();
        this.m = true;
    }
    
    public final void setSize(final int h, final int n) {
        super.setSize(h, n);
        this.h = h;
        this.m = true;
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int j) {
        super.setBounds(n, n2, n3, j);
        this.j = j;
        this.m = true;
    }
    
    public void d() {
        this.a.removeAllElements();
        this.m = true;
        this.repaint();
    }
    
    public final void d(final String s) {
        for (int i = 0; i < this.a.size(); ++i) {
            final String p = this.p((dj)this.a.elementAt(i));
            if (p != null && s.equalsIgnoreCase(p)) {
                this.a.removeElementAt(i);
                --i;
            }
        }
        this.m = true;
        this.repaint();
    }
    
    final void p() {
        if (System.currentTimeMillis() > this.r + 20000L) {
            this.m = true;
            this.r = System.currentTimeMillis();
        }
        if (!this.m) {
            return;
        }
        if (this.getSize().width <= 0) {
            return;
        }
        final Insets insets = this.getInsets();
        this.j = this.getSize().height - insets.top - insets.bottom - 3;
        this.h = this.getSize().width - insets.left - insets.right - 15;
        this.k = 4;
        for (int i = 0; i < this.a.size(); ++i) {
            final dj dj = this.a.elementAt(i);
            dj.p(this);
            if (dj.p() == null) {
                dj.p(this.i);
            }
            dj.d(this.h);
            this.k += dj.p();
        }
        final int value = this.v.getValue();
        if (this.k >= this.j) {
            if (value > this.k - this.j) {
                this.v.setValues(this.k - this.j, this.j, 0, this.k);
            }
            else {
                this.v.setValues(value, this.j, 0, this.k);
            }
        }
        else {
            this.v.setValues(0, this.j, 0, this.j);
        }
        if (this.n != null) {
            this.n.d(this.h);
        }
        this.v.setUnitIncrement(this.i.getHeight());
        this.v.setBlockIncrement(this.j);
        this.m = false;
        this.repaint();
    }
    
    public final void i(final String s) {
        if (s == null) {
            return;
        }
        try {
            if (this.a.size() == this.d) {
                for (int i = 0; i < this.d / 2; ++i) {
                    this.k -= ((dj)this.a.elementAt(i)).p();
                    this.a.setElementAt(this.a.elementAt(this.d / 2 + i), i);
                }
                for (int j = this.d - 1; j >= this.d / 2; --j) {
                    this.a.removeElementAt(j);
                }
            }
            final dr dr = new dr(s, this.c, this.i, this, this.h);
            this.a.addElement(dr);
            this.d(dr);
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public final void p(final String s) {
        if (s == null) {
            this.n = null;
        }
        else {
            this.n = new dr("<12>* " + s, this.c, this.i, this, this.h);
        }
        this.repaint();
    }
    
    final void p(final dj dj) {
        this.k -= dj.p();
        if (this.k >= this.j) {
            if (this.p) {
                this.v.setValues(this.k - this.j, this.j, 0, this.k);
            }
            else {
                this.v.setValues(this.v.getValue(), this.j, 0, this.k);
            }
        }
        else {
            this.v.setValues(0, this.j, 0, this.j);
        }
        this.repaint();
    }
    
    final void d(final dj dj) {
        this.k += dj.p();
        if (this.k >= this.j) {
            if (this.p) {
                this.v.setValues(this.k - this.j, this.j, 0, this.k);
            }
            else {
                this.v.setValues(this.v.getValue(), this.j, 0, this.k);
            }
        }
        else {
            this.v.setValues(0, this.j, 0, this.j);
        }
        this.repaint();
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        try {
            if (this.b == null || this.b.getWidth(this) < this.getSize().width || this.b.getHeight(this) < this.getSize().height) {
                if (this.b != null) {
                    this.b.flush();
                }
                this.b = this.createImage(this.getSize().width, this.getSize().height);
            }
            final Graphics graphics2 = this.b.getGraphics();
            final Color darker = this.getBackground().darker();
            graphics2.setFont(this.i.getFont());
            final Rectangle clipBounds = graphics.getClipBounds();
            if (clipBounds != null) {
                graphics2.clipRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
            }
            graphics2.setColor(this.getBackground());
            graphics2.fillRect(0, 0, this.getSize().width, this.getSize().height);
            this.p();
            graphics2.setColor(Color.black);
            for (int n = 4 - this.v.getValue(), n2 = 0; n2 < this.a.size() && n <= this.j; ++n2) {
                final dj dj = this.a.elementAt(n2);
                if (n + dj.p() < 2) {
                    n += dj.p();
                }
                else {
                    dj.p(graphics2, 6, n, dj.p() ? darker : null);
                    n += dj.p();
                }
            }
            if (this.n != null) {
                this.n.p(graphics2, 6, 2, (this.e == null) ? this.getBackground().darker() : this.e);
            }
            graphics2.setColor(this.getBackground().darker());
            graphics2.fillRect(0, 0, this.getSize().width, 2);
            graphics2.fillRect(0, 0, 2, this.getSize().height);
            graphics.drawImage(this.b, 0, 0, this);
            graphics2.dispose();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public void a(final dj dj) {
        if (this.o != null) {
            this.o.v(dj.toString());
            this.o.p();
            this.requestFocus();
        }
    }
    
    public final String p(final dj dj) {
        final String string = dj.toString();
        if (!string.startsWith("<")) {
            return null;
        }
        int index = string.indexOf(">");
        if (index == -1) {
            return null;
        }
        ++index;
        if (!string.startsWith("[", index) && !string.startsWith("!", index) && !string.startsWith("{", index)) {
            return null;
        }
        ++index;
        if (string.startsWith("from ", index)) {
            index += 5;
        }
        else if (string.startsWith("to ", index)) {
            index += 3;
        }
        final int index2 = string.indexOf(" ", index);
        if (index2 == -1) {
            return null;
        }
        if (!string.startsWith("]", index2 - 1) && !string.startsWith(":", index2 - 1) && !string.startsWith("}", index2 - 1)) {
            return null;
        }
        return string.substring(index, index2 - 1);
    }
    
    public void n(final dj dj) {
        this.o.p();
        final String p = this.p(dj);
        if (p == null) {
            return;
        }
        final Point locationOnScreen = this.getLocationOnScreen();
        locationOnScreen.translate(this.q.x, this.q.y);
        this.o.d(p, locationOnScreen);
    }
    
    public final void requestFocus() {
        if (this.o != null) {
            this.o.requestFocus();
        }
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        return super.imageUpdate(image, n, n2, n3, n4, n5);
    }
    
    public final void adjustmentValueChanged(final AdjustmentEvent adjustmentEvent) {
        this.m = false;
        this.repaint();
        this.requestFocus();
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
        this.q.setLocation(mouseEvent.getX(), mouseEvent.getY());
        if (this.n != null && mouseEvent.getY() < this.n.p() + 2) {
            return;
        }
        int n = mouseEvent.getY() + this.v.getValue();
        for (int i = 0; i < this.a.size(); ++i) {
            final dj dj = this.a.elementAt(i);
            n -= dj.p();
            if (n < 0) {
                if (this.o != null && dj.p() != null) {
                    this.o.p(dj.p());
                }
                else if (this.o != null && dj.p() != null) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(dj.p(), "&");
                    while (stringTokenizer.hasMoreTokens()) {
                        if (!this.o.d(du.p(stringTokenizer.nextToken(), '+', ' '))) {
                            break;
                        }
                        du.p(1000);
                    }
                }
                else {
                    if (mouseEvent.getClickCount() == 2) {
                        this.a(dj);
                        break;
                    }
                    if (mouseEvent.getClickCount() == 1) {
                        this.n(dj);
                        break;
                    }
                }
            }
        }
        mouseEvent.consume();
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
        mouseEvent.consume();
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
}
