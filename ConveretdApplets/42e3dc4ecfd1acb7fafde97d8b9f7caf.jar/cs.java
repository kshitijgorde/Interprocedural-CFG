import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.event.FocusListener;
import java.awt.event.ComponentListener;
import java.awt.event.AdjustmentListener;
import java.awt.Font;
import javax.swing.JScrollBar;
import java.awt.Container;
import java.awt.FontMetrics;
import java.util.Vector;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JComponent;

// 
// Decompiled by Procyon v0.5.30
// 

public class cs extends JComponent implements MouseListener, MouseMotionListener
{
    public static final Color[] a;
    public static int b;
    public Color c;
    private int d;
    private int e;
    private int f;
    private Vector g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    public int n;
    private int o;
    private int[] p;
    private int q;
    private int[] r;
    private int s;
    private FontMetrics[] t;
    private int u;
    private int v;
    private Container w;
    public Color x;
    public cl y;
    public boolean z;
    public boolean aa;
    public boolean ab;
    public Vector ac;
    public int ad;
    public int ae;
    public int af;
    public int ag;
    public JScrollBar ah;
    public boolean ai;
    public int aj;
    public int ak;
    public int al;
    public boolean am;
    public int an;
    public cr ao;
    public int ap;
    private int aq;
    private Font[] ar;
    
    public cs(final cr ao, final JScrollBar ah, final cl y) {
        this.d = 4;
        this.e = 3;
        this.f = 1000;
        this.h = 0;
        this.i = 0;
        this.j = 300;
        this.k = 294;
        this.l = 200;
        this.m = 196;
        this.n = 1;
        this.o = 1;
        this.p = new int[] { 1, 1, 1, 1 };
        this.q = 1;
        this.r = new int[] { 1, 1, 1, 1 };
        this.s = 2;
        this.t = new FontMetrics[] { null, null, null, null };
        this.u = 0;
        this.v = 1;
        this.w = null;
        this.y = null;
        this.z = false;
        this.aa = false;
        this.ab = false;
        this.ac = null;
        this.ad = 2;
        this.ae = 0;
        this.af = 0;
        this.ag = 2;
        this.ah = null;
        this.ai = false;
        this.aj = 0;
        this.ak = 0;
        this.al = 14;
        this.am = true;
        this.an = 0;
        this.ao = null;
        this.ap = 12;
        this.aq = 12;
        this.ar = new Font[] { null, null, null, null };
        this.ao = ao;
        this.ah = ah;
        this.y = y;
        this.s = 1;
        super.setBackground(this.c = cs.a[16]);
        super.setForeground(this.x = cs.a[17]);
        this.b(cr.a.getName(), cr.a.getSize());
        this.g = new Vector();
        this.aj = 0;
        this.ak = 0;
        this.c();
        ah.addAdjustmentListener(new ct(this));
        this.addComponentListener(new cu(this));
        this.e();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void addNotify() {
        super.addNotify();
        if (this.w == null) {
            this.w = this.b();
            if (this.w != null) {
                this.w.addFocusListener(new dc(this));
            }
        }
    }
    
    public void a(final String s, final boolean b, final int n) {
        synchronized (this) {
            final cv cv = new cv(s, b, n);
            cv.a();
            this.g.addElement(cv);
            this.aj += cv.b;
            if (this.ak < 4) {
                this.ak = 0;
            }
            else {
                this.ak += cv.b;
            }
            if (this.ae == 0) {
                this.ae += cv.b;
            }
            else {
                this.ae = -999;
            }
            this.c();
            if (this.f >= 0 && this.g.size() > this.f) {
                this.d();
            }
        }
        this.repaint(0L);
    }
    
    private void c() {
        final int n = this.m / this.s;
        int ak = this.aj - n;
        if (ak < 0) {
            ak = 0;
        }
        if (this.ak > ak) {
            this.ak = ak;
        }
        if (this.ak < 0) {
            this.ak = 0;
        }
        int n2 = ak - this.ak;
        if (n2 < 0) {
            n2 = 0;
        }
        this.ah.setValues(n2, n, 0, this.ai ? ak : (ak + n));
    }
    
    private final void d() {
        if (this.g.isEmpty()) {
            return;
        }
        this.aj -= this.g.firstElement().b;
        this.g.removeElementAt(0);
        if (this.af > 0) {
            --this.af;
        }
        if (this.ak > this.aj) {
            this.ak = this.aj;
        }
        this.c();
    }
    
    private void e() {
        synchronized (this) {
            final Dimension size = this.getSize();
            this.j = size.width;
            this.l = size.height;
            this.k = this.j - this.d * 2;
            this.m = this.l - this.e * 2;
            this.aj = 0;
            for (int i = this.g.size() - 1; i >= 0; --i) {
                final cv cv = this.g.elementAt(i);
                cv.a();
                this.aj += cv.b;
            }
            if (this.ak > this.aj) {
                this.ak = this.aj;
            }
            this.c();
        }
        this.ab = false;
        this.invalidate();
        this.repaint();
    }
    
    public String a() {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i <= this.g.size() - 1; ++i) {
            sb.append(((cv)this.g.elementAt(i)).toString());
            sb.append("\n");
        }
        if (sb.length() == 0) {
            return null;
        }
        return sb.toString();
    }
    
    public Color getBackground() {
        return cs.a[16];
    }
    
    public Container b() {
        Container container = this.getParent();
        while (container == this.ao) {
            container = container.getParent();
            if (container == null) {
                return null;
            }
        }
        return container;
    }
    
    public Font getFont() {
        return this.ar[0];
    }
    
    public Color getForeground() {
        return cs.a[17];
    }
    
    public String a(final int n, final int n2) {
        if (this.ar[3] == null) {
            return null;
        }
        int n3 = this.l - this.e + this.ak * this.s;
        if (n2 > this.l - this.e) {
            return null;
        }
        if (n2 < this.e) {
            return null;
        }
        for (int i = this.g.size() - 1; i >= 0; --i) {
            final cv cv = this.g.elementAt(i);
            n3 -= cv.b * this.s;
            if (n2 > n3) {
                return cv.toString();
            }
        }
        return null;
    }
    
    private cw a(final Point point) {
        if (this.ar[3] == null) {
            return null;
        }
        int n = this.l - this.e + this.ak * this.s;
        if (point.y > this.l - this.e) {
            return null;
        }
        if (point.y < this.e) {
            return null;
        }
        for (int i = this.g.size() - 1; i >= 0; --i) {
            final cv cv = this.g.elementAt(i);
            n -= cv.b * this.s;
            if (point.y > n) {
                final int n2 = point.y - (n + this.u);
                final int n3 = this.o + this.q;
                final Enumeration elements = cv.c.elements();
                while (elements.hasMoreElements()) {
                    final cw cw = elements.nextElement();
                    if (cw.i == cw.h) {
                        if (point.x > cw.f && point.x < cw.g) {
                            return cw;
                        }
                        continue;
                    }
                    else if (n2 < cw.h + n3) {
                        if (point.x > cw.f) {
                            return cw;
                        }
                        continue;
                    }
                    else {
                        if (n2 < cw.i) {
                            return cw;
                        }
                        if (n2 >= cw.i) {
                            continue;
                        }
                        if (point.x < cw.g) {
                            return cw;
                        }
                        continue;
                    }
                }
                break;
            }
        }
        return null;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (0x4 == (mouseEvent.getModifiers() & 0x4)) {
            this.y.a(mouseEvent.getX(), mouseEvent.getY());
        }
        else {
            final cw a = this.a(mouseEvent.getPoint());
            if (a != null && !a.b().equals("") && this.y != null) {
                this.y.c(a.m);
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final cw a = this.a(mouseEvent.getPoint());
        if (a != null && !a.b().equals("") && this.y != null) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            return;
        }
        this.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paintComponent(final Graphics graphics) {
        if (this.ar[3] == null) {
            final Font font = this.getFont();
            if (font == null) {
                return;
            }
            this.b(font.getName(), font.getSize());
        }
        if (this.am) {
            final Enumeration<Image> elements = (Enumeration<Image>)cr.c.elements();
            while (elements.hasMoreElements()) {
                graphics.drawImage(elements.nextElement(), 1, 1, null);
            }
            this.am = false;
        }
        final int n = this.ak - this.ae;
        final int n2 = n * this.s;
        this.ae = this.ak;
        boolean b = Math.abs(n2) < this.l * 2 / 3;
        if (!this.aa && !this.z) {
            b = false;
        }
        if (!this.ab) {
            b = false;
        }
        if (this.ad == this.ag) {
            b = false;
        }
        if (!this.isShowing()) {
            b = false;
        }
        if (b && n2 != 0) {
            if (this.ad == this.an) {
                for (int n3 = n2 / this.s, n4 = 0; Math.abs(n4) < Math.abs(n2); n4 += n3) {
                    if (n4 != 0) {}
                    if (n2 < 0) {
                        graphics.copyArea(2, -n3 + 2 + (n4 - n2 + n3), this.j - 4, this.l - 4 + n3 + (n2 - n3), 0, n3);
                    }
                    else {
                        graphics.copyArea(2, 2 + n4, this.j - 4, this.l - 4 - n3 - (n2 - n3), 0, n3);
                    }
                }
            }
            else if (n2 < 0) {
                graphics.copyArea(2, -n2 + 2, this.j - 4, this.l - 4 + n2, 0, n2);
            }
            else {
                graphics.copyArea(2, 2, this.j - 4, this.l - 4 - n2, 0, n2);
            }
        }
        if (this.e > 2) {
            graphics.setColor(this.c);
            graphics.fillRect(2, this.l - this.e, this.j - 4, this.e - 2);
        }
        int a = -this.ak;
        int n5 = (this.l - 4) / this.s;
        int n6 = 0;
        if (b && n >= 0) {
            n6 = (this.l - 2 * this.e - 2 - n2) / this.s;
        }
        if (b && n < 0) {
            n5 = -n - 1;
        }
        this.af = this.g.size() - 1;
        while (this.af >= 0) {
            final cv cv = this.g.elementAt(this.af);
            if (a + cv.b - 1 < n6) {
                a += cv.b;
            }
            else {
                a = cv.a(graphics, a);
            }
            if (a > n5) {
                break;
            }
            --this.af;
        }
        this.af = 0;
        if (!b || n > 0) {
            final int n7 = this.l - this.e - a * this.s;
            if (n7 > 2) {
                graphics.setColor(this.c);
                graphics.fillRect(2, 2, this.j - 4, n7);
            }
        }
        graphics.setColor(new Color(128, 128, 128));
        graphics.drawPolyline(new int[] { 0, 0, this.j - 1 }, new int[] { this.l - 1, 0, 0 }, 3);
        graphics.setColor(new Color(0, 0, 0));
        graphics.drawPolyline(new int[] { 1, 1, this.j - 2 }, new int[] { this.l - 2, 1, 1 }, 3);
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawPolyline(new int[] { 0, this.j - 1, this.j - 1 }, new int[] { this.l - 1, this.l - 1, 0 }, 3);
        graphics.drawPolyline(new int[] { 1, this.j - 2, this.j - 2 }, new int[] { this.l - 2, this.l - 2, 1 }, 3);
        this.ab = true;
    }
    
    private void b(final String s, final int n) {
        this.ar[0] = new Font(s, 0, n);
        this.ar[1] = new Font(s, 1, n);
        this.ar[2] = new Font(s, 2, n);
        this.ar[3] = new Font(s, 3, n);
        this.s = 0;
        this.q = 0;
        this.o = 0;
        this.u = 9999;
        for (int i = 0; i < 4; ++i) {
            final FontMetrics fontMetrics = this.getFontMetrics(this.ar[i]);
            this.t[i] = fontMetrics;
            this.v += fontMetrics.stringWidth(" ");
            this.r[i] = fontMetrics.getMaxDescent();
            this.p[i] = fontMetrics.getMaxAscent();
            if (this.q < fontMetrics.getMaxDescent()) {
                this.q = fontMetrics.getMaxDescent();
            }
            if (this.o < fontMetrics.getMaxAscent()) {
                this.o = fontMetrics.getMaxAscent();
            }
            if (this.u > fontMetrics.getLeading()) {
                this.u = fontMetrics.getLeading();
            }
        }
        if (this.u < 0) {
            this.u = 0;
        }
        this.s = this.q + this.o + this.u;
        this.v = (this.v + 2) / 4;
        if (this.s < this.q + this.o + this.u) {
            this.s = this.q + this.o + this.u;
        }
        if (this.s < 17) {
            this.s = 17;
        }
        this.c();
    }
    
    public void setBackground(final Color c) {
        synchronized (this) {
            cs.a[16] = c;
            super.setBackground(this.c = c);
            this.ab = false;
        }
        this.repaint();
    }
    
    public void a(final int f) {
        synchronized (this) {
            this.f = f;
            while (f >= 0 && this.g.size() > f) {
                this.d();
            }
            this.ab = false;
        }
        this.repaint();
    }
    
    public void a(final String s, final int n) {
        synchronized (this) {
            this.b(s, n);
            this.ab = false;
        }
        this.e();
    }
    
    public void setForeground(final Color x) {
        synchronized (this) {
            cs.a[17] = x;
            super.setForeground(this.x = x);
            this.ab = false;
        }
        this.repaint();
    }
    
    private void b(final int n) {
        int n2 = this.aj - this.m / this.s;
        if (n2 < 0) {
            n2 = 0;
        }
        final int ak = this.ak;
        this.ak = n2 - n;
        if (this.ak < 0) {
            this.ak = 0;
        }
        if (ak != this.ak) {
            this.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        this.aa = true;
        this.paint(graphics);
        this.aa = false;
    }
    
    public static /* synthetic */ int a(final cs cs) {
        return cs.l;
    }
    
    public static /* synthetic */ int b(final cs cs) {
        return cs.e;
    }
    
    public static /* synthetic */ int c(final cs cs) {
        return cs.s;
    }
    
    public static /* synthetic */ int d(final cs cs) {
        return cs.j;
    }
    
    public static /* synthetic */ int e(final cs cs) {
        return cs.q;
    }
    
    public static /* synthetic */ int f(final cs cs) {
        return cs.d;
    }
    
    public static /* synthetic */ int[] g(final cs cs) {
        return cs.p;
    }
    
    public static /* synthetic */ int[] h(final cs cs) {
        return cs.r;
    }
    
    public static /* synthetic */ FontMetrics[] i(final cs cs) {
        return cs.t;
    }
    
    public static /* synthetic */ int j(final cs cs) {
        return cs.i;
    }
    
    public static /* synthetic */ Font[] k(final cs cs) {
        return cs.ar;
    }
    
    public static /* synthetic */ int a(final cs cs, final int i) {
        return cs.i = i;
    }
    
    public static /* synthetic */ int l(final cs cs) {
        return cs.h;
    }
    
    public static /* synthetic */ int b(final cs cs, final int h) {
        return cs.h = h;
    }
    
    public static /* synthetic */ int m(final cs cs) {
        return cs.aq;
    }
    
    public static /* synthetic */ int n(final cs cs) {
        return cs.k;
    }
    
    public static /* synthetic */ int o(final cs cs) {
        return cs.v;
    }
    
    static {
        cs.b = 0;
        (a = new Color[18])[0] = Color.decode("0x000000");
        cs.a[1] = Color.decode("0xF33E3E");
        cs.a[2] = Color.decode("0x7C393F");
        cs.a[3] = Color.decode("0x207303");
        cs.a[4] = Color.decode("0x336699");
        cs.a[5] = Color.decode("0xAAAAAA");
        cs.a[6] = Color.decode("0xBB00BB");
        cs.a[7] = Color.decode("0xFFAA00");
        cs.a[8] = Color.decode("0xFFFF00");
        cs.a[9] = Color.decode("0x00FF00");
        cs.a[10] = Color.decode("0x00CCCC");
        cs.a[11] = Color.decode("0x00FFFF");
        cs.a[12] = Color.decode("0x0000FF");
        cs.a[13] = Color.decode("0xFF00FF");
        cs.a[14] = Color.decode("0x404040");
        cs.a[15] = Color.decode("0x999999");
        cs.a[16] = Color.decode("0xFFFFFF");
        cs.a[17] = Color.decode("0x000000");
    }
    
    private class cw
    {
        public Vector a;
        private Color b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public Image j;
        public String k;
        public boolean l;
        public String m;
        
        public cw(final String k, final boolean b, final boolean b2, final boolean l, final int d, final int n2) {
            this.a = null;
            this.c = 0;
            this.d = 17;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = null;
            this.k = null;
            this.l = false;
            this.m = null;
            this.k = k;
            this.c = (b ? (b2 ? 3 : 1) : (b2 ? 2 : 0));
            this.l = l;
            this.b = cs.a[n2];
            this.d = d;
        }
        
        public cw(final String k, final Image j, final int n2) {
            this.a = null;
            this.c = 0;
            this.d = 17;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = null;
            this.k = null;
            this.l = false;
            this.m = null;
            this.k = k;
            this.j = j;
            this.b = cs.a[n2];
        }
        
        public cw(final String k, final boolean b, final boolean b2, final boolean l, final int d, final Color b3) {
            this.a = null;
            this.c = 0;
            this.d = 17;
            this.e = 0;
            this.f = 0;
            this.g = 0;
            this.h = 0;
            this.i = 0;
            this.j = null;
            this.k = null;
            this.l = false;
            this.m = null;
            this.k = k;
            this.c = (b ? (b2 ? 3 : 1) : (b2 ? 2 : 0));
            this.l = l;
            this.b = b3;
            this.d = d;
        }
        
        public boolean a() {
            return (this.c & 0x1) == 0x1;
        }
        
        public String b() {
            return (this.m == null) ? "" : this.m;
        }
        
        private int a(final String s, final int n) {
            for (int length = s.length(), i = n; i < length; ++i) {
                if (Character.isSpaceChar(s.charAt(i))) {
                    return i;
                }
            }
            return -1;
        }
        
        public void a(final Graphics graphics, final int n, final boolean b) {
            int f = this.f;
            int n2 = n + this.h;
            this.a(graphics, b);
            if (this.e == 0) {
                this.a(graphics, this.k, f, n2);
            }
            else {
                int n3 = 0;
                for (int i = 0; i <= this.e; ++i) {
                    int n4;
                    if (i == this.e) {
                        n4 = this.k.length();
                    }
                    else {
                        n4 = this.a.elementAt(i);
                    }
                    this.a(graphics, this.k.substring(n3, n4), f, n2);
                    f = cs.f(cs.this) + cs.this.ap;
                    n2 += cs.c(cs.this);
                    n3 = n4;
                }
            }
        }
        
        private void a(final Graphics graphics, final String s, final int n, final int n2) {
            if (this.j != null) {
                int n3 = this.j.getHeight(null);
                if (n3 == 0) {
                    n3 = cs.this.al;
                }
                final int n4 = cs.g(cs.this)[this.c];
                final int n5 = cs.h(cs.this)[this.c];
                int n6 = -(n4 - n5 + n3) / 2;
                if (n6 + n3 - 1 > n5) {
                    n6 = n5 - n3 + 1;
                }
                int n7 = this.j.getWidth(null);
                if (n7 == 0) {
                    n7 = cs.this.al;
                }
                graphics.setColor(this.b);
                graphics.fillRect(n, n2 - n4, n7, n4 + n5);
                graphics.setColor(cs.a[this.d]);
                graphics.drawImage(this.j, n, n2 + n6, null);
            }
            else {
                final int stringWidth = cs.i(cs.this)[this.c].stringWidth(s);
                final int n8 = cs.g(cs.this)[this.c];
                graphics.setColor(this.b);
                graphics.fillRect(n, n2 - n8, stringWidth, n8 + cs.h(cs.this)[this.c]);
                graphics.setColor(cs.a[this.d]);
                graphics.drawString(s, n, n2);
                if (this.l) {
                    final int stringWidth2 = cs.i(cs.this)[this.c].stringWidth(s);
                    int n9 = cs.h(cs.this)[this.c] / 4;
                    if (n9 < 1) {
                        n9 = 1;
                    }
                    graphics.drawLine(n, n2 + n9, n + stringWidth2 - 1, n2 + n9);
                    if (this.a()) {
                        graphics.drawLine(n, n2 + n9 + 1, n + stringWidth2 - 1, n2 + n9 + 1);
                    }
                }
            }
        }
        
        public void a(final Graphics graphics, final boolean b) {
            if (cs.j(cs.this) != this.c || b) {
                graphics.setFont(cs.k(cs.this)[this.c]);
                cs.a(cs.this, this.c);
            }
            if (cs.l(cs.this) != this.d || b) {
                graphics.setColor(cs.a[this.d]);
                cs.b(cs.this, this.d);
            }
        }
        
        public void a(final String m) {
            this.m = m;
            this.l = true;
            this.d = cs.m(cs.this);
        }
        
        public int a(int f, int i, int n) {
            String s = this.k;
            int n2 = 0;
            if (f >= cs.n(cs.this) - cs.o(cs.this) * 2) {
                f = cs.f(cs.this) + cs.this.ap;
                i += cs.c(cs.this);
                ++n;
            }
            this.a = null;
            this.e = 0;
            this.f = f;
            this.h = i;
            if (this.j != null) {
                this.g = f + this.j.getWidth(null);
                this.i = i;
                if (this.g > cs.d(cs.this) - cs.f(cs.this)) {
                    this.f = cs.f(cs.this) + cs.this.ap;
                    this.h += cs.c(cs.this);
                    this.g = cs.f(cs.this) + cs.this.ap + this.j.getWidth(null);
                    this.i += cs.c(cs.this);
                    ++n;
                }
                return n;
            }
            this.g = f + cs.i(cs.this)[this.c].stringWidth(s);
            this.i = i;
            if (this.j != null) {
                return n;
            }
            int n3 = 0;
            while (this.g > cs.d(cs.this) - cs.f(cs.this) && n3++ <= 100) {
                int n4 = 0;
                int a = 0;
                while ((a = this.a(s, a)) >= 0 && f + cs.i(cs.this)[this.c].stringWidth(s.substring(0, a)) <= cs.d(cs.this) - cs.f(cs.this)) {
                    ++a;
                    while (s.length() > a && Character.isSpaceChar(s.charAt(a))) {
                        ++a;
                    }
                    n4 = a;
                }
                if (n4 == 0) {
                    if (f > cs.f(cs.this) + cs.this.ap) {
                        f = cs.f(cs.this) + cs.this.ap;
                        if (this.h == this.i && this.a == null) {
                            this.f = cs.f(cs.this) + cs.this.ap;
                            this.h += cs.c(cs.this);
                        }
                        this.g = f + cs.i(cs.this)[this.c].stringWidth(s);
                        this.i += cs.c(cs.this);
                        ++n;
                        continue;
                    }
                    n4 = 1;
                    for (int j = 1; j < s.length() - 1; ++j) {
                        if (f + cs.i(cs.this)[this.c].stringWidth(s.substring(0, j + 1)) > cs.d(cs.this) - cs.f(cs.this)) {
                            n4 = j;
                            break;
                        }
                    }
                }
                if (n4 > 0 && n4 < s.length()) {
                    if (this.a == null) {
                        this.a = new Vector();
                    }
                    this.a.addElement(new Integer(n4 + n2));
                    ++this.e;
                    s = s.substring(n4);
                    n2 += n4;
                }
                f = cs.f(cs.this) + cs.this.ap;
                this.g = f + cs.i(cs.this)[this.c].stringWidth(s);
                this.i += cs.c(cs.this);
                ++n;
            }
            return n;
        }
    }
    
    private class cv
    {
        private String a;
        public int b;
        public Vector c;
        
        public cv(String substring, final boolean b, final int n) {
            this.b = 1;
            this.a = substring;
            final boolean b2 = false;
            final boolean b3 = false;
            final boolean b4 = false;
            final int n2 = 16;
            int n3 = 17;
            if (substring.length() == 0) {
                return;
            }
            this.c = new Vector();
            final int index = substring.indexOf(":");
            if (index > 0) {
                if (n != 0) {
                    n3 = n;
                }
                final Color a = p.a(substring.substring(0, index).trim());
                if (a != null) {
                    this.c.addElement(new cw(substring.substring(0, index + 1), true, false, false, n3, a));
                }
                else {
                    this.c.addElement(new cw(substring.substring(0, index + 1), true, false, false, n3, n2));
                }
                substring = substring.substring(index + 1);
                n3 = 17;
            }
            int a2 = -1;
            int n4 = 0;
            final int length = substring.length();
            for (int i = 0; i < length; ++i) {
                if (i > a2) {
                    a2 = this.a(substring, i);
                    if (b && a2 < length && i < a2 && substring.charAt(a2) == '@') {
                        if (n4 < i) {
                            this.c.addElement(new cw(substring.substring(n4, i), b2, b3, b4, n3, n2));
                        }
                        final int a3 = this.a(substring, a2 + 1);
                        String s = substring.substring(i, a3);
                        final cw cw = new cw(s, b2, b3, b4, n3, n2);
                        if (s.length() <= 7) {
                            s = "mailto:" + s;
                        }
                        else if (!s.substring(0, 7).toUpperCase().equals("MAILTO:")) {
                            s = "mailto:" + s;
                        }
                        cw.a(s);
                        this.c.addElement(cw);
                        i = a3 - 1;
                        n4 = i + 1;
                        continue;
                    }
                }
                if (b && this.b(substring, i)) {
                    if (n4 < i) {
                        this.c.addElement(new cw(substring.substring(n4, i), b2, b3, b4, n3, n2));
                    }
                    String s2 = substring.substring(i, a2);
                    final cw cw2 = new cw(s2, b2, b3, b4, n3, n2);
                    if (s2.length() >= 4 && s2.substring(0, 4).toUpperCase().equals("WWW.")) {
                        s2 = "http://" + s2;
                    }
                    cw2.a(s2);
                    this.c.addElement(cw2);
                    i = a2 - 1;
                    n4 = i + 1;
                }
                else {
                    int n5 = i + cr.b;
                    if (n5 > length) {
                        n5 = length;
                    }
                    for (String s3 = substring.substring(i, n5); s3.length() > 0; s3 = s3.substring(0, s3.length() - 1)) {
                        final Image image;
                        if ((image = cr.c.get(s3)) != null) {
                            if (n4 < i) {
                                this.c.addElement(new cw(substring.substring(n4, i), b2, b3, b4, n3, n2));
                            }
                            this.c.addElement(new cw(s3, image, n2));
                            i += s3.length() - 1;
                            n4 = i + 1;
                            break;
                        }
                    }
                }
            }
            if (n4 < length) {
                this.c.addElement(new cw(substring.substring(n4, length), b2, b3, b4, n3, n2));
            }
        }
        
        public int a(final String s, final int n) {
            final int length = s.length();
            for (int i = n; i < length; ++i) {
                final char char1 = s.charAt(i);
                if (!Character.isLetterOrDigit(char1)) {
                    switch (char1) {
                        case '#':
                        case '%':
                        case '&':
                        case '+':
                        case '-':
                        case '.':
                        case '/':
                        case ':':
                        case '=':
                        case '?':
                        case '_':
                        case '~': {
                            break;
                        }
                        default: {
                            return i;
                        }
                    }
                }
            }
            return length;
        }
        
        public boolean b(final String s, final int n) {
            final char char1 = s.charAt(n);
            return (char1 == 'h' || char1 == 'H' || char1 == 'w' || char1 == 'W' || char1 == 'f' || char1 == 'F') && s.length() >= n + 8 && (s.substring(n, n + 4).toUpperCase().equals("WWW.") || s.substring(n, n + 7).toUpperCase().equals("HTTP://") || s.substring(n, n + 8).toUpperCase().equals("HTTPS://") || s.substring(n, n + 6).toUpperCase().equals("FTP://"));
        }
        
        public int a(final Graphics graphics, final int n) {
            if (n >= -this.b + 1) {
                final int n2 = cs.a(cs.this) - cs.b(cs.this) - (n + this.b) * cs.c(cs.this);
                graphics.setColor(cs.this.c);
                graphics.fillRect(2, n2, cs.d(cs.this) - 3, this.b * cs.c(cs.this));
                final int n3 = n2 + (cs.c(cs.this) - cs.e(cs.this));
                int n4 = 0;
                final Enumeration<cw> elements = (Enumeration<cw>)this.c.elements();
                while (elements.hasMoreElements()) {
                    elements.nextElement().a(graphics, n3, n4++ == 0);
                }
            }
            return n + this.b;
        }
        
        public void a() {
            int n = cs.f(cs.this);
            int i = 0;
            int a = 0;
            final Enumeration<cw> elements = this.c.elements();
            while (elements.hasMoreElements()) {
                final cw cw = elements.nextElement();
                a = cw.a(n, i, a);
                n = cw.g;
                i = cw.i;
            }
            this.b = a + 1;
        }
        
        public String toString() {
            return this.a;
        }
    }
}
