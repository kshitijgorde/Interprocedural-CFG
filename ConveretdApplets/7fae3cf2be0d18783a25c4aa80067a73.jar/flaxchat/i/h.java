// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Image;
import java.awt.Font;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Button;
import java.util.Vector;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.Panel;

public class h extends Panel implements MouseListener, FocusListener, KeyListener, ComponentListener
{
    public static final Color a;
    public static final Color b;
    public static final Color c;
    public static final Color d;
    public static final Color e;
    public static final Color f;
    public static final Color g;
    public static final Color h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private String q;
    private Cursor r;
    private Cursor s;
    private Vector t;
    private Panel u;
    private Button v;
    private CardLayout w;
    private static String[] z;
    
    public h() {
        this(0);
    }
    
    public h(final int m) {
        this.w = new CardLayout();
        this.i = -1;
        this.p = false;
        this.m = 0;
        this.a(6, 6);
        this.q = null;
        this.r = new Cursor(0);
        this.s = new Cursor(3);
        this.t = new Vector();
        this.j = 1;
        this.k = 1;
        this.l = 1;
        this.m = m;
        try {
            this.a();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final int o, final int n) {
        if (o > -1) {
            this.o = o;
        }
        if (n > -1) {
            this.n = n;
        }
    }
    
    private void a() throws Exception {
        this.setLayout(new BorderLayout());
        this.addComponentListener(this);
        this.addMouseListener(this);
        this.add(this.v = new Button(flaxchat.i.h.z[0]));
        this.v.addKeyListener(this);
        this.v.addFocusListener(this);
        this.add(this.u = new Panel(this.w), flaxchat.i.h.z[2]);
        Font font = this.u.getFont();
        if (font == null) {
            font = new Font(flaxchat.i.h.z[1], 0, 12);
        }
        this.setFont(new Font(flaxchat.i.h.z[1], 0, 12));
        this.u.setFont(font);
        this.setBackground(Color.gray);
        this.u.setBackground(Color.gray);
    }
    
    public int a(final Component component, final String s) {
        return this.a(component, s, null);
    }
    
    public int a(final Component component, final String s, final Image image) {
        final int size = this.t.size();
        final l l = new l(this, this, component, s, image);
        this.t.addElement(l);
        this.u.add(component, s);
        if (size == 0) {
            this.i = 0;
            this.w.show(this.u, s);
        }
        l.a(this.getFontMetrics(this.getFont()));
        this.j = Math.max(l.e.height, this.j);
        this.k = Math.max(l.e.width, this.k);
        this.b();
        this.repaint();
        return size;
    }
    
    public Dimension getPreferredSize() {
        final boolean j = flaxchat.i.i.j;
        final Dimension preferredSize = this.getPreferredSize();
        if (this.m == 1) {
            preferredSize.height = Math.max(preferredSize.height, this.j * this.t.size() + 1);
            if (!j) {
                return preferredSize;
            }
        }
        int n = 0;
        int n2 = 0;
        while (true) {
            Label_0084: {
                if (!j) {
                    break Label_0084;
                }
                n += ((l)this.t.elementAt(n2)).e.width;
                ++n2;
            }
            if (n2 < this.t.size()) {
                continue;
            }
            break;
        }
        preferredSize.width = Math.max(preferredSize.width, n + 1);
        return preferredSize;
    }
    
    public Insets getInsets() {
        Insets insets = super.getInsets();
        if (this.m == 1) {
            insets = new Insets(insets.top + this.n, insets.left + this.l * this.k - 2 + this.o, insets.bottom + this.n, insets.right + this.o);
            if (!flaxchat.i.i.j) {
                return insets;
            }
        }
        insets = new Insets(insets.top + this.l * this.j - 2 + this.n, insets.left + this.o, insets.bottom + this.n, insets.right + this.o);
        return insets;
    }
    
    public void update(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        if (bounds.width < 1 || bounds.height < 1) {
            return;
        }
        final Image image = this.createImage(bounds.width, bounds.height);
        Graphics graphics2 = null;
        Label_0054: {
            if (image != null) {
                graphics2 = image.getGraphics();
                if (!flaxchat.i.i.j) {
                    break Label_0054;
                }
            }
            graphics2 = graphics;
        }
        this.paint(graphics2);
        if (image != null) {
            graphics.drawImage(image, 0, 0, this);
        }
    }
    
    public void paint(final Graphics graphics) {
        final boolean j = flaxchat.i.i.j;
        super.paint(graphics);
        final Rectangle bounds = this.getBounds();
        bounds.x = 0;
        bounds.y = 0;
        final Rectangle rectangle = new Rectangle(bounds);
        Label_0127: {
            if (this.m == 1) {
                final Rectangle rectangle2 = rectangle;
                rectangle2.x += this.k * this.l - 2;
                final Rectangle rectangle3 = rectangle;
                rectangle3.width -= this.k * this.l - 2;
                if (!j) {
                    break Label_0127;
                }
            }
            final Rectangle rectangle4 = rectangle;
            rectangle4.y += this.j * this.l - 2;
            final Rectangle rectangle5 = rectangle;
            rectangle5.height -= this.j * this.l - 2;
        }
        final Rectangle rectangle6 = rectangle;
        --rectangle6.width;
        final Rectangle rectangle7 = rectangle;
        --rectangle7.height;
        graphics.setColor(flaxchat.i.h.d);
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        graphics.setColor(flaxchat.i.h.c);
        graphics.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + 1, rectangle.y + rectangle.height - 2);
        graphics.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + rectangle.width - 2, rectangle.y + 1);
        final Font font2;
        final Font font = font2 = this.getFont();
        int n = this.t.size() - 1;
        while (true) {
            Label_0777: {
                if (!j) {
                    break Label_0777;
                }
                final l l = this.t.elementAt(n);
                Label_0355: {
                    if (n == this.i) {
                        if (this.m == 1) {
                            l.d(graphics);
                            if (!j) {
                                break Label_0355;
                            }
                        }
                        l.c(graphics);
                        if (!j) {
                            break Label_0355;
                        }
                    }
                    if (this.m == 1) {
                        l.b(graphics);
                        if (!j) {
                            break Label_0355;
                        }
                    }
                    l.a(graphics);
                }
                graphics.setColor(this.getForeground());
                final Rectangle rectangle8 = new Rectangle(l.f);
                Label_0408: {
                    if (this.m == 1) {
                        rectangle8.width = this.k;
                        if (!j) {
                            break Label_0408;
                        }
                    }
                    rectangle8.height = this.j;
                }
                if (l.d != null) {
                    final int width = l.d.getWidth(this);
                    final Rectangle rectangle9 = rectangle8;
                    rectangle9.x += width + l.j;
                    final Rectangle rectangle10 = rectangle8;
                    rectangle10.width -= width + l.j;
                }
                final Rectangle rectangle11 = rectangle8;
                ++rectangle11.y;
                FontMetrics fontMetrics = null;
                Label_0599: {
                    if (n == this.i) {
                        graphics.setFont(font2);
                        fontMetrics = graphics.getFontMetrics(font2);
                        if (!this.p) {
                            break Label_0599;
                        }
                        final int n2 = fontMetrics.stringWidth(l.c) + 6;
                        final int n3 = fontMetrics.getHeight() + 1;
                        this.a(graphics, rectangle8.x + (rectangle8.width - n2) / 2, rectangle8.y + (this.j - 2 - n3) / 2 + 1, n2, n3);
                        if (!j) {
                            break Label_0599;
                        }
                    }
                    graphics.setFont(font);
                    fontMetrics = graphics.getFontMetrics(font);
                }
                int n4 = rectangle8.x + (rectangle8.width - fontMetrics.stringWidth(l.c)) / 2;
                int n5 = rectangle8.y + rectangle8.height - (rectangle8.height - fontMetrics.getHeight()) / 2 - fontMetrics.getMaxDescent();
                --n5;
                if (n != this.i) {
                    ++n4;
                    ++n5;
                }
                graphics.drawString(l.c, n4, n5);
                if (l.d != null) {
                    final int height = l.d.getHeight(this);
                    int n6 = l.f.x + l.j;
                    int n7 = l.f.y + (this.j - height) / 2;
                    if (n != this.i) {
                        ++n6;
                        ++n7;
                    }
                    graphics.drawImage(l.d, n6, n7, this);
                }
                --n;
            }
            if (n < 0) {
                if (this.q != null) {
                    this.w.show(this.u, this.q);
                    this.q = null;
                    this.setCursor(this.r);
                }
                return;
            }
            continue;
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        this.b(graphics, n, n2, n + n3 - 1, n2);
        this.b(graphics, n + n3 - 1, n2, n + n3 - 1, n2 + n4 - 1);
        this.b(graphics, n + n3 - 1, n2 + n4 - 1, n, n2 + n4 - 1);
        this.b(graphics, n, n2 + n4 - 1, n, n2);
    }
    
    private void b(final Graphics graphics, int n, int n2, int n3, int n4) {
        final boolean j = flaxchat.i.i.j;
        if (n == n3 && n2 == n4) {
            this.a(graphics, n, n2);
            return;
        }
        if (n > n3) {
            final int n5 = n;
            n = n3;
            n3 = n5;
        }
        if (n2 > n4) {
            final int n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        if (n3 - n > n4 - n2) {
            final double n7 = (n4 - n2) / (n3 - n);
            int n8 = n;
            while (true) {
                Label_0118: {
                    if (!j) {
                        break Label_0118;
                    }
                    this.a(graphics, n8, (int)Math.rint(n2 + (n8 - n) * n7));
                    ++n8;
                }
                if (n8 > n3) {
                    return;
                }
                continue;
            }
        }
        else {
            final double n9 = (n3 - n) / (n4 - n2);
            int n10 = n2;
            while (true) {
                Label_0175: {
                    if (!j) {
                        break Label_0175;
                    }
                    this.a(graphics, (int)Math.rint(n + (n10 - n2) * n9), n10);
                    ++n10;
                }
                if (n10 > n4) {
                    return;
                }
                continue;
            }
        }
    }
    
    private void a(final Graphics graphics, final int n, final int n2) {
        if ((n + n2) % 2 == 0) {
            graphics.drawLine(n, n2, n, n2);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean j = flaxchat.i.i.j;
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int size = this.t.size();
        int i = 0;
        while (true) {
            Label_0097: {
                if (!j) {
                    break Label_0097;
                }
                final l l = this.t.elementAt(i);
                if (l.f.contains(x, y)) {
                    this.v.requestFocus();
                    this.i = i;
                    this.q = l.c;
                    this.setCursor(this.s);
                    this.repaint();
                    return;
                }
                ++i;
            }
            if (i >= size) {
                return;
            }
            continue;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        int i = this.i;
        final int keyCode = keyEvent.getKeyCode();
        Label_0046: {
            if (keyCode == 40 || keyCode == 39) {
                ++i;
                if (!flaxchat.i.i.j) {
                    break Label_0046;
                }
            }
            if (keyCode == 38 || keyCode == 37) {
                --i;
            }
        }
        if (i >= this.t.size()) {
            i = this.t.size() - 1;
        }
        if (i < 0) {
            i = 0;
        }
        if (this.i != i) {
            this.i = i;
            this.q = ((l)this.t.elementAt(i)).c;
            this.setCursor(this.s);
            this.repaint();
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (this.p) {
            return;
        }
        this.p = true;
        this.repaint();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        if (!this.p) {
            return;
        }
        this.p = false;
        this.repaint();
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.b();
        this.doLayout();
        this.u.validate();
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    private void b() {
        final boolean j = flaxchat.i.i.j;
        int n = 1;
        final Rectangle bounds = this.getBounds();
        bounds.x = 0;
        bounds.y = 0;
        if (bounds.width < 1 || bounds.height < 1) {
            return;
        }
        final int size = this.t.size();
        Label_0642: {
            if (this.m == 1) {
                n = bounds.height / this.j;
                this.l = (size + n - 1) / n;
                int x = this.l * this.k;
                int y = 0;
                int n2 = 0;
                while (true) {
                    Label_0225: {
                        if (!j) {
                            break Label_0225;
                        }
                        if (n2 % n == 0) {
                            x -= this.k;
                            y = 0;
                        }
                        final l l = this.t.elementAt(n2);
                        l.f.x = x;
                        l.f.y = y;
                        l.f.width = this.k * (n2 / n + 1);
                        l.f.height = this.j;
                        l.g = n2 / n;
                        if (l.g > 0) {
                            final Rectangle f = l.f;
                            f.width -= 2;
                        }
                        y += this.j;
                        ++n2;
                    }
                    if (n2 < size) {
                        continue;
                    }
                    break;
                }
                if (!j) {
                    break Label_0642;
                }
            }
            this.l = 1;
            int n3 = 0;
            int n4 = 0;
            while (true) {
                Label_0313: {
                    if (!j) {
                        break Label_0313;
                    }
                    final l i = this.t.elementAt(n4);
                    if (n3 + i.e.width > bounds.width) {
                        n3 = 0;
                        ++this.l;
                    }
                    n3 += i.e.width;
                    ++n4;
                }
                if (n4 < size) {
                    continue;
                }
                break;
            }
            int x2 = 0;
            int y2 = this.l * this.j;
            int g = 0;
            int n5 = 0;
            int n6 = 0;
            while (true) {
                Label_0636: {
                    if (!j) {
                        break Label_0636;
                    }
                    if (n6 == n5) {
                        x2 = 0;
                        y2 -= this.j;
                        n3 = 0;
                        n5 = n6;
                    Label_0446:
                        while (true) {
                            Label_0440: {
                                if (!j) {
                                    break Label_0440;
                                }
                                final l k = this.t.elementAt(n5);
                                if (n5 > n6 && n3 + k.e.width > bounds.width) {
                                    break Label_0446;
                                }
                                n3 += k.e.width;
                                k.g = g;
                                ++n5;
                            }
                            if (n5 < size) {
                                continue;
                            }
                            break;
                        }
                        n = n5 - n6;
                        ++g;
                    }
                    final l m = this.t.elementAt(n6);
                    m.f.x = x2;
                    m.f.y = y2;
                    m.f.width = m.e.width;
                    if (this.l > 1 && g < this.l) {
                        final Rectangle f2 = m.f;
                        f2.width += (bounds.width - n3 - 1) / n;
                        final Rectangle f3 = m.f;
                        f3.width += ((n5 - n6 <= (bounds.width - n3 - 1) % n) ? 1 : 0);
                    }
                    m.f.height = this.j * g;
                    if (m.g > 0) {
                        final Rectangle f4 = m.f;
                        f4.height -= 2;
                    }
                    x2 += m.f.width;
                    ++n6;
                }
                if (n6 < size) {
                    continue;
                }
                break;
            }
        }
        this.repaint();
    }
    
    static {
        flaxchat.i.h.z = new String[] { z(z("A%Ub\u0004")), z(z("C#W{\u0018`")), z(z("D/Xc\u0012u")) };
        a = Color.lightGray;
        b = Color.black;
        c = Color.white;
        d = Color.darkGray;
        e = new Color(128, 128, 128);
        f = Color.black;
        g = Color.lightGray;
        h = Color.darkGray;
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'w';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0007';
                    break;
                }
                case 1: {
                    c2 = 'J';
                    break;
                }
                case 2: {
                    c2 = '6';
                    break;
                }
                case 3: {
                    c2 = '\u0017';
                    break;
                }
                default: {
                    c2 = 'w';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
