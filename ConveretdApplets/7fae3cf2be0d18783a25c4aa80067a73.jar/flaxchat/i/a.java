// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import java.awt.event.KeyEvent;
import java.awt.FontMetrics;
import java.util.StringTokenizer;
import java.awt.event.MouseEvent;
import java.awt.event.ComponentEvent;
import flaxchat.h.e;
import flaxchat.a.j;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.FocusEvent;
import java.awt.Image;
import java.awt.Font;
import java.util.Vector;
import flaxchat.h.d;
import java.awt.Component;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

public abstract class a extends Canvas implements MouseListener, MouseMotionListener, ComponentListener, KeyListener, FocusListener
{
    private boolean a;
    protected static final Color[] b;
    protected Component c;
    protected final d d;
    protected final Vector e;
    protected final Vector f;
    protected final Vector g;
    protected final Vector h;
    protected int i;
    protected int j;
    protected int k;
    protected int l;
    protected int m;
    protected int n;
    protected int o;
    protected boolean p;
    protected boolean q;
    private boolean r;
    protected Font s;
    protected flaxchat.i.d t;
    protected Image u;
    private Image v;
    private static String[] z;
    
    public a() {
        this.d = new d();
        this.e = new Vector();
        this.f = new Vector();
        this.g = new Vector();
        this.h = new Vector();
        this.l = 2;
        this.m = 2;
        this.p = false;
        this.q = true;
        this.r = true;
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addComponentListener(this);
        this.addFocusListener(this);
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        this.a = true;
        this.repaint();
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.a = false;
        this.repaint();
    }
    
    public boolean hasFocus() {
        return this.a;
    }
    
    protected void a(final Graphics graphics) {
        final boolean j = flaxchat.i.i.j;
        if (!this.r) {
            return;
        }
        final Dimension size = this.getSize();
        final int height = size.height;
        final int size2 = this.h.size();
        int n = 0;
        while (true) {
            Label_0183: {
                if (!j) {
                    break Label_0183;
                }
                final k k = this.h.elementAt(n);
                final int n2 = k.e + height - this.j;
                final int d = k.d;
                final String a = this.a(k.c);
                if (a != null) {
                    final Image f = flaxchat.d.d.f(a);
                    if (f != null) {
                        final int n3 = (this.j > f.getHeight(this)) ? this.j : f.getHeight(this);
                        if (n2 - this.i >= 0 && n2 - this.i < size.height) {
                            graphics.drawImage(f, d, n2 - this.i, f.getWidth(this), n3, this);
                        }
                    }
                }
                ++n;
            }
            if (n >= size2) {
                return;
            }
            continue;
        }
    }
    
    private String a(final int n) {
        switch (n) {
            case 0: {
                return flaxchat.i.a.z[7];
            }
            case 1: {
                return flaxchat.i.a.z[5];
            }
            case 2: {
                return flaxchat.i.a.z[6];
            }
            case 3: {
                return flaxchat.i.a.z[8];
            }
            case 4: {
                return flaxchat.i.a.z[3];
            }
            case 5: {
                return flaxchat.i.a.z[0];
            }
            case 6: {
                return flaxchat.i.a.z[5];
            }
            case 7: {
                return flaxchat.i.a.z[2];
            }
            case 8: {
                return flaxchat.i.a.z[9];
            }
            case 9: {
                return flaxchat.i.a.z[1];
            }
            case 10: {
                return flaxchat.i.a.z[7];
            }
            case 11: {
                return flaxchat.i.a.z[4];
            }
            default: {
                return null;
            }
        }
    }
    
    protected final void b(final Graphics graphics) {
        final boolean j = flaxchat.i.i.j;
        final Image o = flaxchat.d.d.o();
        if (o == null) {
            return;
        }
        final int width = o.getWidth(this);
        final int height = o.getHeight(this);
        final Dimension size = this.getSize();
        int n = 0;
        while (true) {
            Label_0083: {
                if (!j) {
                    break Label_0083;
                }
                int n2 = 0;
                while (true) {
                    Label_0066: {
                        if (!j) {
                            break Label_0066;
                        }
                        graphics.drawImage(o, n2, n, this);
                        n2 += width;
                    }
                    if (n2 < size.width) {
                        continue;
                    }
                    break;
                }
                n += height;
            }
            if (n >= size.height) {
                if (this.v != null) {
                    graphics.drawImage(this.v, size.width - 100, 10, 99, 99, this);
                }
                return;
            }
            continue;
        }
    }
    
    public final void c(final Graphics graphics) {
        final Dimension size = this.getSize();
        graphics.setColor(Color.black);
        graphics.draw3DRect(0, 0, size.width, size.height, false);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void doLayout() {
    }
    
    public final void layout() {
    }
    
    public final void validate() {
    }
    
    public final void invalidate() {
    }
    
    public void b(final int n) {
        this.i = n * this.j;
        this.repaint();
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.repaint();
    }
    
    public void setBackground(final Color background) {
        super.setBackground(background);
        this.repaint();
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.s = font;
        this.repaint();
    }
    
    protected Image a() {
        if (this.u == null) {
            final Dimension size = this.getSize();
            this.u = this.createImage(size.width, size.height);
        }
        return this.u;
    }
    
    public String b() {
        final boolean j = flaxchat.i.i.j;
        final j i = new j();
        e e = this.d.a;
        while (true) {
            Label_0058: {
                if (!j) {
                    break Label_0058;
                }
                i.a(((k)e.a).toString());
                if (e.b != null) {
                    i.a('\n');
                }
                e = e.b;
            }
            if (e == null) {
                return i.toString();
            }
            continue;
        }
    }
    
    protected static Color c(final int n) {
        if (n > -1 && n < 16) {
            return a.b[n];
        }
        return Color.white;
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public int c() {
        return this.d.b();
    }
    
    public final void d() {
        this.d.a();
        this.e.removeAllElements();
        this.g.removeAllElements();
        this.h.removeAllElements();
        this.f.removeAllElements();
        this.o = 0;
        this.f();
        this.repaint();
    }
    
    public void a(final String s) {
        final boolean j = flaxchat.i.i.j;
        final int o = this.o;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        final int o2 = this.o + stringTokenizer.countTokens();
        int n = o;
        while (true) {
            Label_0071: {
                if (!j) {
                    break Label_0071;
                }
                final k k = new k(stringTokenizer.nextToken());
                this.a(k);
                this.d.a(k);
                ++n;
            }
            if (n >= o2) {
                this.o = o2;
                this.repaint();
                return;
            }
            continue;
        }
    }
    
    public k b(final String s) {
        final boolean j = flaxchat.i.i.j;
        e e = this.d.a;
        while (true) {
            Label_0068: {
                if (!j) {
                    break Label_0068;
                }
                final k k = (k)e.a;
                if (k.equals(s)) {
                    this.d.a(e);
                    --this.o;
                    this.f();
                    this.repaint();
                    return k;
                }
                e = e.b;
            }
            if (e == null) {
                return null;
            }
            continue;
        }
    }
    
    public abstract void e();
    
    public abstract void a(final k p0);
    
    public final synchronized void f() {
        final boolean j = flaxchat.i.i.j;
        final FontMetrics fontMetrics = this.getFontMetrics(this.s);
        this.j = fontMetrics.getHeight();
        this.k = fontMetrics.getAscent();
        this.n = 0;
        this.e.setSize(0);
        this.g.setSize(0);
        this.h.setSize(0);
        this.f.setSize(0);
        final k g = this.g();
        e e = this.d.a;
        while (true) {
            Label_0101: {
                if (!j) {
                    break Label_0101;
                }
                this.a((k)e.a);
                e = e.b;
            }
            if (e == null) {
                this.b(g);
                this.e();
                return;
            }
            continue;
        }
    }
    
    public final k g() {
        final boolean j = flaxchat.i.i.j;
        e e = this.d.a;
        while (true) {
            Label_0038: {
                if (!j) {
                    break Label_0038;
                }
                final k k = (k)e.a;
                if (k.f) {
                    return k;
                }
                e = e.b;
            }
            if (e == null) {
                return null;
            }
            continue;
        }
    }
    
    public final e h() {
        final boolean j = flaxchat.i.i.j;
        e e = this.d.a;
        while (true) {
            Label_0038: {
                if (!j) {
                    break Label_0038;
                }
                if (((k)e.a).f) {
                    return e;
                }
                e = e.b;
            }
            if (e == null) {
                return null;
            }
            continue;
        }
    }
    
    public final e a(final e e) {
        final boolean j = flaxchat.i.i.j;
        boolean b = true;
        e b2 = null;
        for (e e2 = this.d.a; e2 != null; e2 = e2.b) {
            if (e2 == e) {
                if (e2.b == null) {
                    continue;
                }
                e2.b.a().f = true;
                b2 = e2.b;
                e.a().f = false;
                e2 = e.b;
                b = false;
                if (!j) {
                    continue;
                }
            }
            if (!this.p || !this.q) {
                e2.a().f = false;
            }
        }
        if (b) {
            e.a().f = true;
        }
        return b2;
    }
    
    public final e b(final e e) {
        final boolean j = flaxchat.i.i.j;
        boolean b = true;
        e e2 = null;
        for (e e3 = this.d.a; e3 != null; e3 = e3.b) {
            if (e3.b == e) {
                e2 = e3;
                e3.a().f = true;
                e3 = e3.b;
                e.a().f = false;
                b = false;
                if (!j) {
                    continue;
                }
            }
            if (!this.p || !this.q) {
                e3.a().f = false;
            }
        }
        if (b) {
            e.a().f = true;
        }
        return e2;
    }
    
    public final e i() {
        return this.a(this.h());
    }
    
    public final e j() {
        return this.b(this.h());
    }
    
    public final void c(final String s) {
        final boolean j = flaxchat.i.i.j;
        e e = this.d.a;
        while (true) {
            Label_0074: {
                if (!j) {
                    break Label_0074;
                }
                final k k = (k)e.a;
                Label_0069: {
                    if (k.equals(s)) {
                        k.f = true;
                        if (!j) {
                            break Label_0069;
                        }
                    }
                    if (!this.p || !this.q) {
                        ((k)e.a).f = false;
                    }
                }
                e = e.b;
            }
            if (e == null) {
                return;
            }
            continue;
        }
    }
    
    public final void b(final k k) {
        final boolean j = flaxchat.i.i.j;
        e e = this.d.a;
        while (true) {
            Label_0074: {
                if (!j) {
                    break Label_0074;
                }
                final k i = (k)e.a;
                Label_0069: {
                    if (i.equals(k)) {
                        i.f = true;
                        if (!j) {
                            break Label_0069;
                        }
                    }
                    if (!this.p || !this.q) {
                        ((k)e.a).f = false;
                    }
                }
                e = e.b;
            }
            if (e == null) {
                return;
            }
            continue;
        }
    }
    
    protected int a(final char c) {
        return this.getFontMetrics(this.getFont()).charWidth(c);
    }
    
    protected int a(final char[] array) {
        return this.getFontMetrics(this.getFont()).charsWidth(array, 0, array.length);
    }
    
    protected int d(final String s) {
        return this.a(s.toCharArray());
    }
    
    protected int a(final j j) {
        return this.d(j.toString());
    }
    
    public void d(final int l) {
        this.l = l;
    }
    
    public void e(final int m) {
        this.m = m;
    }
    
    public abstract void mouseClicked(final MouseEvent p0);
    
    public abstract void mousePressed(final MouseEvent p0);
    
    public abstract void mouseReleased(final MouseEvent p0);
    
    public abstract void mouseDragged(final MouseEvent p0);
    
    public abstract void componentResized(final ComponentEvent p0);
    
    public abstract void keyTyped(final KeyEvent p0);
    
    public abstract void keyPressed(final KeyEvent p0);
    
    public abstract void keyReleased(final KeyEvent p0);
    
    static {
        a.z = new String[] { z(z("IUp-~IDg%6")), z(z("IUp-~QYm:")), z(z("IUp-~[^y;*")), z(z("IUp-~NU\u007f:")), z(z("IUp-~YBg")), z(z("IUp-~I_l;<M")), z(z("IUp-~VQk.;")), z(z("IUp-~I]w%6")), z(z("IUp-~RQn9*")), z(z("IUp-~Y_p/&IU")) };
        b = new Color[] { Color.white, Color.black, new Color(0, 0, 127), new Color(0, 143, 0), new Color(255, 0, 0), new Color(127, 0, 0), new Color(158, 0, 158), new Color(255, 127, 0), new Color(255, 255, 0), new Color(0, 255, 0), new Color(0, 143, 143), new Color(0, 255, 255), new Color(0, 0, 255), new Color(255, 0, 255), new Color(127, 127, 127), new Color(206, 206, 206) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'S';
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
                    c2 = ':';
                    break;
                }
                case 1: {
                    c2 = '0';
                    break;
                }
                case 2: {
                    c2 = '\u001e';
                    break;
                }
                case 3: {
                    c2 = 'I';
                    break;
                }
                default: {
                    c2 = 'S';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
