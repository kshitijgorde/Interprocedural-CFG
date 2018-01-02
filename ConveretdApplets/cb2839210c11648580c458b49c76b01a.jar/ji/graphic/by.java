// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.util.Vector;
import ji.io.h;
import ji.v1event.ar;
import ji.v1event.c9;
import ji.v1event.d8;
import ji.v1event.a9;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.AdjustmentListener;
import java.awt.Polygon;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ColorModel;
import java.awt.Dimension;
import java.awt.Insets;
import ji.v1event.as;
import java.awt.SystemColor;
import java.awt.Component;
import ji.util.e;
import ji.util.i;
import ji.awt.bb;
import ji.util.d;
import java.awt.event.AdjustmentEvent;
import ji.v1event.g9;
import ji.v1event.a2;
import java.awt.Cursor;
import ji.v1event.br;
import java.awt.Image;
import java.awt.Color;
import java.awt.Point;
import ji.v1base.bz;
import java.awt.Rectangle;
import ji.awt.c;
import ji.v1event.ak;
import java.awt.event.MouseMotionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.Adjustable;
import ji.v1base.jiPanel;

public class by extends jiPanel implements Adjustable, MouseListener, KeyListener, FocusListener, MouseMotionListener, ak
{
    private int a;
    private int b;
    private c c;
    private int d;
    private int e;
    private int f;
    private int g;
    private s6 h;
    private s7 i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r;
    private boolean s;
    private boolean t;
    private int u;
    private int v;
    private int w;
    private int x;
    private boolean y;
    private int z;
    private boolean aa;
    private boolean ab;
    private Rectangle ac;
    private Rectangle ad;
    private Rectangle ae;
    private Rectangle af;
    private Rectangle ag;
    private boolean ah;
    private b3 ai;
    private b3 aj;
    private bz ak;
    private bz al;
    private Point am;
    private Point an;
    private Point ao;
    private Color ap;
    private Color aq;
    private Color ar;
    private Color as;
    private Color at;
    private Color au;
    private boolean av;
    private boolean aw;
    private boolean ax;
    private boolean ay;
    private boolean az;
    private boolean a0;
    private Image a1;
    private Image a2;
    private Image a3;
    private Image a4;
    private Image a5;
    private Image a6;
    private Image a7;
    private Image a8;
    private Image a9;
    private Image ba;
    private Image bb;
    private Image bc;
    private Image bd;
    private boolean be;
    private boolean bf;
    private Image bg;
    private Image bh;
    private Image bi;
    private int bj;
    private int bk;
    private int bl;
    private int bm;
    private int bn;
    private boolean bo;
    private boolean bp;
    private long bq;
    private int br;
    private Image bs;
    private Image bt;
    private br bu;
    private c bv;
    private String bw;
    private String bx;
    private static final Cursor by;
    private static boolean bz;
    private v4 b0;
    private String b1;
    private a2 b2;
    private ui b3;
    
    public by(final int n, final int a, final String s) {
        super(s);
        this.a = 1;
        this.d = 0;
        this.e = 10;
        this.f = 0;
        this.g = 100;
        this.h = null;
        this.i = null;
        this.j = false;
        this.k = 300;
        this.l = 60;
        this.m = 1;
        this.n = 1;
        this.o = 0;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = false;
        this.t = false;
        this.u = Math.max(17, 18);
        this.v = this.u - 2;
        this.w = 601;
        this.x = 1;
        this.y = true;
        this.z = -1;
        this.aa = false;
        this.ab = true;
        this.ac = new Rectangle(0, 0, 0, 0);
        this.ad = new Rectangle(0, 0, 0, 0);
        this.ae = new Rectangle(0, 0, 0, 0);
        this.af = new Rectangle(0, 0, 0, 0);
        this.ag = new Rectangle(0, 0, 0, 0);
        this.ah = false;
        this.ai = new b3();
        this.aj = new b3();
        this.ak = null;
        this.al = null;
        this.am = null;
        this.an = null;
        this.ao = new Point(0, 0);
        this.ap = null;
        this.aq = null;
        this.ar = null;
        this.as = null;
        this.at = null;
        this.au = null;
        this.av = true;
        this.aw = false;
        this.ax = false;
        this.ay = false;
        this.az = false;
        this.a0 = false;
        this.a1 = null;
        this.a2 = null;
        this.a3 = null;
        this.a4 = null;
        this.a5 = null;
        this.a6 = null;
        this.a7 = null;
        this.a8 = null;
        this.a9 = null;
        this.ba = null;
        this.bb = null;
        this.bc = null;
        this.bd = null;
        this.be = true;
        this.bf = false;
        this.bg = null;
        this.bh = null;
        this.bi = null;
        this.bj = 0;
        this.bk = 0;
        this.bl = 0;
        this.bm = 0;
        this.bn = 1;
        this.bo = false;
        this.bp = false;
        this.bq = 0L;
        this.br = 9;
        this.bs = null;
        this.bt = null;
        this.bu = null;
        this.bv = null;
        this.bw = null;
        this.bx = null;
        this.b0 = null;
        this.b1 = null;
        this.b2 = null;
        this.b3 = null;
        this.a = a;
        this.b(s);
    }
    
    public final void a(final g9 g9) {
        try {
            if (g9.a() < 0) {
                if (this.d > 0) {
                    this.a();
                }
            }
            else if (this.d < this.g) {
                this.b();
            }
        }
        catch (Exception ex) {}
    }
    
    public int getWidth() {
        return this.getSize().width;
    }
    
    public final void a() {
        try {
            if (this.d > 0) {
                this.a(this.d - this.m, 601, 2, true);
                this.a(new AdjustmentEvent(this, 601, this.x, this.z));
                this.y = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void b() {
        try {
            if (this.d < this.g) {
                this.a(this.d + this.m, 601, 1, true);
                this.a(new AdjustmentEvent(this, 601, this.x, this.z));
                this.y = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public boolean c() {
        return this.t;
    }
    
    public final void a(final boolean b) {
        if (ji.util.d.a8() && this.aw != (ji.graphic.by.bz = b)) {
            if (!(this.aw = b)) {
                this.d();
            }
            else if (this.b0 == null) {
                this.b0 = new v4();
                new bb(this.b1, this.b0).start();
            }
        }
    }
    
    private final Color k() {
        if (ji.util.i.c(7) && ji.util.e.t()) {
            return ji.util.e.a1();
        }
        return ji.util.e.a0();
    }
    
    public final void d() {
        if (ji.util.d.a8()) {
            if (this.aw) {
                this.ap = this.k();
            }
            else {
                this.ap = this.getBackground();
            }
            this.repaint();
        }
    }
    
    public boolean isDoubleBuffered() {
        return false;
    }
    
    public void repaint() {
        super.repaint();
    }
    
    private void b(final String b1) {
        try {
            this.b1 = b1;
            if (ji.util.d.b()) {
                return;
            }
            this.setOpaque(false);
            this.ak = new bz(b1);
            this.al = new bz(b1);
            if (this.a == 2) {
                this.u = Math.max(17, 18);
                this.v = this.u - 6;
            }
            else if (this.a == 1) {
                this.u = Math.max(17, 18);
                this.v = this.u - 2;
            }
            this.setAcceptFocus(true);
            this.ak.setBorderStyle(0);
            this.al.setBorderStyle(0);
            ji.util.e.a(this);
            ji.util.e.a(this.ak);
            ji.util.e.a(this.al);
            this.setBorderStyle(0);
            this.addMouseMotionListener(this);
            this.addMouseListener(this);
            this.addKeyListener(this);
            this.addFocusListener(this);
            this.aj.a(2);
            this.ai.a(2);
            if (ji.util.e.g != null) {
                this.ai.b(ji.util.e.g);
            }
            else {
                this.ai.b(SystemColor.controlDkShadow);
            }
            this.ap = this.getBackground();
            this.aq = this.ap;
            if (ji.util.e.g != null) {
                this.au = ji.util.e.g;
            }
            else {
                this.au = SystemColor.controlShadow;
            }
            this.av = true;
            this.b = 1;
            this.l();
            if (ji.util.d.am()) {
                (this.b2 = new a2("jiScrollbarEvents", b1)).b(this);
            }
            try {
                if (this.b3 == null) {
                    this.b3 = new ui();
                }
                this.addWheelListener(this.b3, b1);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        catch (Exception ex2) {
            ex2.printStackTrace();
        }
    }
    
    private final boolean a(final Component component, final int n, final int n2, final int n3, final int n4) {
        return ji.util.e.a(component, n, n2, n3, n4);
    }
    
    public final void a(final String bw) {
        this.bw = bw;
        if (this.isSwing()) {
            this.setToolTipText(ji.util.d.e(this.bw, this.b1));
        }
    }
    
    public final String e() {
        if (this.bx == null) {
            this.bx = ji.util.d.e(this.bw, this.b1);
        }
        return this.bx;
    }
    
    public final void setBackground(final Color background) {
        try {
            super.setBackground(background);
            ji.util.e.a(this, background);
            this.av = true;
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    public final Color getBackground() {
        Color color = super.getBackground();
        if (ji.util.e.ao() != null) {
            color = ji.util.e.ao();
        }
        return color;
    }
    
    public final void setEnabled(final boolean ab) {
        try {
            if (this.ab != ab) {
                this.be = true;
            }
            this.ab = ab;
            this.repaint();
        }
        catch (Exception ex) {}
    }
    
    public final boolean isEnabled() {
        return super.isEnabled();
    }
    
    public final boolean f() {
        return this.ab;
    }
    
    public final int getOrientation() {
        return this.b;
    }
    
    private final void l() {
        try {
            final Rectangle bounds = this.getBounds();
            final Insets insets = new Insets(1, 1, 1, 1);
            if (bounds.width > bounds.height) {
                this.b = 0;
            }
            else {
                this.b = 1;
            }
            if (this.b == 1) {
                final int n = bounds.width - insets.left - insets.right + 1;
                if (this.s) {
                    this.ad.x = insets.left;
                    this.ad.y = insets.top - 1;
                    this.ad.width = n;
                    this.ad.height = this.v + 1;
                    this.ae.x = insets.left;
                    this.ae.y = this.ad.y + this.ad.height;
                    this.ae.width = n;
                    this.ae.height = this.v + 1;
                    this.ag.x = insets.left;
                    this.ag.y = bounds.height - insets.bottom - this.v;
                    this.ag.width = n;
                    this.ag.height = this.v + 1;
                    this.af.x = insets.left;
                    this.af.y = this.ag.y - (this.v + 1);
                    this.af.width = n;
                    this.af.height = this.v + 1;
                }
                else {
                    this.ae.x = insets.left;
                    this.ae.y = insets.top - 1;
                    this.ae.width = n;
                    this.ae.height = this.v + 1;
                    this.af.x = insets.left;
                    this.af.y = bounds.height - insets.bottom - this.v;
                    this.af.width = n;
                    this.af.height = this.v + 1;
                }
            }
            else {
                final int n2 = bounds.height - insets.top - insets.bottom + 1;
                if (this.s) {
                    this.ad.x = insets.left - 1;
                    this.ad.y = insets.top;
                    this.ad.width = this.v + 1;
                    this.ad.height = n2;
                    this.ae.x = this.ad.x + this.ad.width;
                    this.ae.y = insets.top;
                    this.ae.width = this.v + 1;
                    this.ae.height = n2;
                    this.ag.x = bounds.width - insets.right - this.v;
                    this.ag.y = insets.top;
                    this.ag.width = this.v + 1;
                    this.ag.height = n2;
                    this.af.x = this.ag.x - (this.v + 1);
                    this.af.y = insets.top;
                    this.af.width = this.v + 1;
                    this.af.height = n2;
                }
                else {
                    this.ae.x = insets.left - 1;
                    this.ae.y = insets.top;
                    this.ae.width = this.v + 1;
                    this.ae.height = n2;
                    this.af.x = bounds.width - insets.right - this.v;
                    this.af.y = insets.top;
                    this.af.width = this.v + 1;
                    this.af.height = n2;
                }
            }
            if (this.a == 2) {
                this.ae.x = 0;
                this.ae.y = 0;
                this.ae.width = 0;
                this.ae.height = 0;
                this.af.x = 0;
                this.af.y = 0;
                this.af.width = 0;
                this.af.height = 0;
            }
        }
        catch (Exception ex) {}
        this.n();
    }
    
    private final void m() {
        try {
            final Dimension screenSize = this.getToolkit().getScreenSize();
            this.ac.width = Math.min(this.ac.width, screenSize.width);
            this.ac.height = Math.min(this.ac.height, screenSize.height);
            this.ac.width = Math.max(this.ac.width, 1);
            this.ac.height = Math.max(this.ac.height, 1);
            this.ac.x = Math.max(this.ac.x, -1000);
            this.ac.y = Math.max(this.ac.y, -1000);
        }
        catch (Exception ex) {}
    }
    
    private final void n() {
        boolean b = false;
        try {
            final Rectangle bounds = this.getBounds();
            final Rectangle rectangle = new Rectangle(this.ac);
            this.m();
            final Insets insets = new Insets(1, 1, 1, 1);
            final double n = (this.d - this.f) / (this.g - this.f);
            double n2 = this.e / (this.g - this.f);
            if (this.e == this.g - this.f) {
                n2 /= 2;
            }
            if (this.b == 1) {
                final int n3 = bounds.width - insets.left - insets.right;
                int u;
                if (this.s) {
                    u = (int)(n2 * (bounds.height - insets.top - insets.bottom - 4 * this.v));
                }
                else {
                    u = (int)(n2 * (bounds.height - insets.top - insets.bottom - 2 * this.v));
                }
                if (u < 25) {
                    u = 25;
                    double n4;
                    if (this.s) {
                        n4 = u / (bounds.height - insets.top - insets.bottom - 4 * this.v);
                    }
                    else {
                        n4 = u / (bounds.height - insets.top - insets.bottom - 2 * this.v);
                    }
                    this.e = (int)(n4 * (this.g - this.f));
                }
                if (this.a == 2) {
                    u = this.u;
                }
                int y;
                if (this.s) {
                    y = insets.top + 2 * this.v + 1 + (int)(n * (-u + bounds.height - insets.top - insets.bottom - 4 * this.v - 2));
                }
                else {
                    y = insets.top + this.v + (int)(n * (-u + bounds.height - insets.top - insets.bottom - 2 * this.v - 1));
                }
                if (this.a == 2) {
                    y = (int)(n * (bounds.height - this.v));
                }
                if (n3 > 0 && u > 0) {
                    if (this.s) {
                        this.o = insets.top + 2 * this.v + 1;
                    }
                    else {
                        this.o = insets.top + this.v;
                    }
                    if (this.s) {
                        this.p = bounds.height - insets.bottom - 2 * this.v - u - 2;
                    }
                    else {
                        this.p = bounds.height - insets.bottom - this.v - u - 1;
                    }
                    if (this.a == 2) {
                        this.o = insets.top;
                        this.p = bounds.height - this.v - 1;
                    }
                    this.ac.x = insets.left;
                    this.ac.y = y;
                    this.ac.width = n3 + 1;
                    if (this.s) {
                        this.ac.height = u;
                    }
                    else {
                        this.ac.height = u + 1;
                    }
                    if (this.a == 2) {
                        this.ac.height = this.v;
                    }
                    this.m();
                    if (this.ac.height - insets.top - insets.bottom != this.bk) {
                        this.bj = this.ac.width - insets.left - insets.right;
                        this.bk = this.ac.height - insets.top - insets.bottom;
                        this.av = true;
                    }
                }
            }
            else {
                final int n5 = bounds.height - insets.top - insets.bottom;
                int u2;
                if (this.s) {
                    u2 = (int)(n2 * (bounds.width - insets.left - insets.right - 4 * this.v));
                }
                else {
                    u2 = (int)(n2 * (bounds.width - insets.left - insets.right - 2 * this.v));
                }
                if (u2 < 25) {
                    u2 = 25;
                    double n6;
                    if (this.s) {
                        n6 = u2 / (bounds.width - insets.left - insets.right - 4 * this.v);
                    }
                    else {
                        n6 = u2 / (bounds.width - insets.left - insets.right - 2 * this.v);
                    }
                    this.e = (int)(n6 * (this.g - this.f));
                }
                if (this.a == 2) {
                    u2 = this.u;
                }
                int x;
                if (this.s) {
                    x = insets.left + 2 * this.v + 1 + (int)(n * (-u2 + bounds.width - insets.left - insets.right - 4 * this.v - 2));
                }
                else {
                    x = insets.left + this.v + (int)(n * (-u2 + bounds.width - insets.left - insets.right - 2 * this.v - 1));
                }
                if (this.a == 2) {
                    x = (int)(n * (bounds.width - this.v));
                }
                if (u2 > 0 && n5 > 0) {
                    if (this.s) {
                        this.q = insets.left + 2 * this.v + 1;
                    }
                    else {
                        this.q = insets.left + this.v;
                    }
                    if (this.s) {
                        this.r = bounds.width - insets.right - 2 * this.v - u2 - 2;
                    }
                    else {
                        this.r = bounds.width - insets.right - this.v - u2 - 1;
                    }
                    if (this.a == 2) {
                        this.q = insets.left;
                        this.r = bounds.width - this.v;
                    }
                    this.ac.x = x;
                    this.ac.y = insets.top;
                    if (this.s) {
                        this.ac.width = u2;
                    }
                    else {
                        this.ac.width = u2 + 1;
                    }
                    if (this.a == 2) {
                        this.ac.width = this.v;
                    }
                    this.ac.height = n5 + 1;
                    this.m();
                    if (this.ac.width - insets.left - insets.right != this.bj) {
                        this.bj = this.ac.width - insets.left - insets.right;
                        this.bk = this.ac.height - insets.top - insets.bottom;
                        this.av = true;
                    }
                }
            }
            if (!rectangle.equals(this.ac)) {
                b = true;
            }
        }
        catch (Exception ex) {}
        if (b) {
            try {
                this.repaint();
            }
            catch (Exception ex2) {}
        }
    }
    
    private final Image a(final Color color, final Color color2, final Color color3, final boolean b) {
        try {
            if (this.bj > 0 && this.bk > 0) {
                this.ai.a(2);
                this.ai.a();
                final int[] array = new int[this.bj * this.bk];
                final int j = ji.util.d.j(color);
                if (ji.util.i.c(7) && ji.util.e.t() && b && ji.util.d.ar()) {
                    ji.util.d.a(array, new Rectangle(0, 0, this.bj, this.bk), color, color2, this.b == 0);
                }
                else {
                    for (int i = 0; i < array.length; ++i) {
                        array[i] = j;
                    }
                }
                final int k = ji.util.d.j(color.darker().darker().darker());
                int n;
                if (ji.util.e.h != null) {
                    n = ji.util.d.j(ji.util.e.h);
                }
                else {
                    n = ji.util.d.j(ji.util.e.c(ji.util.e.c(color.brighter())));
                }
                final int n2 = 4;
                int n3 = 0;
                int n4 = n;
                for (int l = this.bn; l < this.bk - this.bn; ++l) {
                    final int n5 = l * this.bj;
                    for (int n6 = n3 + this.bn; n6 < this.bj - this.bn; n6 += n2) {
                        array[n5 + n6] = n4;
                    }
                    if (++n3 == n2) {
                        n3 = 0;
                    }
                    if (n4 == k) {
                        n4 = n;
                    }
                    else {
                        n4 = k;
                    }
                }
                return this.createImage(new MemoryImageSource(this.bj, this.bk, ColorModel.getRGBdefault(), array, 0, this.bj));
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private final void o() {
        this.p();
        if (this.a != 2) {
            if (ji.util.e.g != null) {
                this.a1 = this.a(this.getBackground(), null, ji.util.e.g, false);
                this.a2 = this.a(this.getBackground(), null, SystemColor.controlText, false);
                this.a3 = this.a(this.k(), ji.util.e.a2(), ji.util.e.g, true);
                this.a4 = this.a(this.k(), ji.util.e.a2(), SystemColor.controlText, true);
            }
            else {
                this.a1 = this.a(this.getBackground(), null, SystemColor.controlShadow, false);
                this.a2 = this.a(this.getBackground(), null, SystemColor.controlText, false);
                this.a3 = this.a(this.k(), ji.util.e.a2(), SystemColor.controlShadow, true);
                this.a4 = this.a(this.k(), ji.util.e.a2(), SystemColor.controlText, true);
            }
        }
    }
    
    private final Image a(final Color color, final Color color2, final int n, final boolean b, final boolean b2) {
        try {
            if (this.bl > 0 && this.bm > 0) {
                this.aj.a();
                final int[] array = new int[this.bl * this.bm];
                final int j = ji.util.d.j(color);
                if (ji.util.i.c(7) && ji.util.e.t() && b2 && ji.util.d.ar()) {
                    ji.util.d.a(array, new Rectangle(0, 0, this.bl, this.bm), color, color.brighter().brighter().brighter(), true);
                }
                else {
                    for (int i = 0; i < array.length; ++i) {
                        array[i] = j;
                    }
                }
                final int k = ji.util.d.j(color2);
                final int n2 = 4;
                switch (n) {
                    case 0: {
                        int max = this.bl / 2 - 1;
                        int min = max + 2;
                        final int n3 = (this.bm - n2) / 2;
                        for (int n4 = n3 + n2, l = n3; l < n4; ++l) {
                            final int n5 = l * this.bl;
                            for (int n6 = max; n6 < min; ++n6) {
                                array[n5 + n6] = k;
                            }
                            max = Math.max(max - 1, 0);
                            min = Math.min(min + 1, this.bl);
                        }
                        if (b) {
                            for (int n7 = max + 1; n7 < min - 1; ++n7) {
                                array[(n3 - 1) * this.bl + n7] = k;
                            }
                            break;
                        }
                        break;
                    }
                    case 90: {
                        int min2 = (this.bm - n2 - 4) / 2;
                        int max2 = min2 + n2 + 4;
                        final int n8 = 1 + (this.bl - n2) / 2;
                        final int n9 = n8 + n2;
                        for (int n10 = n8; n10 < n9; ++n10) {
                            for (int n11 = min2; n11 < max2; ++n11) {
                                array[n10 + n11 * this.bl] = k;
                            }
                            min2 = Math.min(min2 + 1, this.bm);
                            max2 = Math.max(max2 - 1, 0);
                        }
                        final int n12 = (this.bm - n2 - 4) / 2;
                        final int n13 = n12 + n2 + 4;
                        if (b) {
                            for (int n14 = n12; n14 < n13; ++n14) {
                                array[n9 + n14 * this.bl] = k;
                            }
                            break;
                        }
                        break;
                    }
                    case 180: {
                        int max3 = this.bl / 2 - 1;
                        int min3 = max3 + 2;
                        int n15;
                        int n17;
                        int n16;
                        for (n15 = (this.bm - n2) / 2 - 1, n16 = (n17 = n15 + n2); n17 > n15; --n17) {
                            final int n18 = n17 * this.bl;
                            for (int n19 = max3; n19 < min3; ++n19) {
                                array[n18 + n19] = k;
                            }
                            max3 = Math.max(max3 - 1, 0);
                            min3 = Math.min(min3 + 1, this.bl);
                        }
                        if (b) {
                            for (int n20 = max3 + 1; n20 < min3 - 1; ++n20) {
                                array[(n16 + 1) * this.bl + n20] = k;
                            }
                            break;
                        }
                        break;
                    }
                    case 270: {
                        int min4 = (this.bm - n2 - 4) / 2;
                        int max4 = min4 + n2 + 4;
                        final int n21 = (this.bl - n2) / 2 - 1;
                        for (int n22 = n21 + n2; n22 > n21; --n22) {
                            for (int n23 = min4; n23 < max4; ++n23) {
                                array[n22 + n23 * this.bl] = k;
                            }
                            min4 = Math.min(min4 + 1, this.bm);
                            max4 = Math.max(max4 - 1, 0);
                        }
                        final int n24 = (this.bm - n2 - 4) / 2;
                        final int n25 = n24 + n2 + 4;
                        if (b) {
                            for (int n26 = n24; n26 < n25; ++n26) {
                                array[n21 + n26 * this.bl] = k;
                            }
                            break;
                        }
                        break;
                    }
                }
                return this.createImage(new MemoryImageSource(this.bl, this.bm, ColorModel.getRGBdefault(), array, 0, this.bl));
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private final void p() {
        try {
            if (this.a1 != null) {
                this.a1.flush();
            }
            if (this.a2 != null) {
                this.a2.flush();
            }
            if (this.a3 != null) {
                this.a3.flush();
            }
            if (this.a4 != null) {
                this.a4.flush();
            }
            this.a1 = null;
            this.a2 = null;
            this.a3 = null;
            this.a4 = null;
        }
        catch (Exception ex) {}
    }
    
    private final void q() {
        try {
            if (this.bs != null) {
                this.bs.flush();
            }
            this.bs = null;
            if (this.bt != null) {
                this.bt.flush();
            }
            this.bt = null;
        }
        catch (Exception ex) {}
    }
    
    private final void r() {
        try {
            this.q();
            final Rectangle bounds = this.getBounds();
            if (bounds != null && bounds.width > 0 && bounds.height > 0) {
                this.bs = this.createImage(bounds.width, bounds.height);
                this.bt = this.createImage(bounds.width, bounds.height);
                if (this.bs.getGraphics() != null) {
                    if (this.bs != null) {
                        this.a(this.bs.getGraphics(), true);
                    }
                }
                else {
                    this.q();
                    this.av = true;
                }
                if (this.bt.getGraphics() != null) {
                    if (this.bt != null) {
                        this.a(this.bt.getGraphics(), false);
                    }
                }
                else {
                    this.q();
                    this.av = true;
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void s() {
        if (this.a8 != null) {
            this.a8.flush();
        }
        if (this.a9 != null) {
            this.a9.flush();
        }
        if (this.ba != null) {
            this.ba.flush();
        }
        if (this.a5 != null) {
            this.a5.flush();
        }
        if (this.a6 != null) {
            this.a6.flush();
        }
        if (this.a7 != null) {
            this.a7.flush();
        }
        if (this.bb != null) {
            this.bb.flush();
        }
        if (this.bc != null) {
            this.bc.flush();
        }
        if (this.bd != null) {
            this.bd.flush();
        }
        if (this.bg != null) {
            this.bg.flush();
        }
        if (this.bh != null) {
            this.bh.flush();
        }
        if (this.bi != null) {
            this.bi.flush();
        }
        this.a8 = null;
        this.a9 = null;
        this.ba = null;
        this.a5 = null;
        this.a6 = null;
        this.a7 = null;
        this.bb = null;
        this.bc = null;
        this.bd = null;
        this.bg = null;
        this.bh = null;
        this.bi = null;
        this.bl = this.ae.width - 2;
        this.bm = this.ae.height - 2;
        final SystemColor windowText = SystemColor.windowText;
        Color color = SystemColor.control;
        Color color2 = SystemColor.controlShadow;
        if (ji.util.e.g != null) {
            color2 = this.getBackground();
            color = this.getBackground();
        }
        if (this.b == 1) {
            this.a5 = this.a(this.getBackground(), windowText, 0, true, false);
            this.a6 = this.a(ji.util.e.a0(), windowText, 0, true, true);
            this.a7 = this.a(color, color2, 0, true, false);
            this.a8 = this.a(this.getBackground(), windowText, 0, false, false);
            this.a9 = this.a(ji.util.e.a0(), windowText, 0, false, true);
            this.ba = this.a(color, color2, 0, false, false);
            this.bb = this.a(this.getBackground(), windowText, 180, false, false);
            this.bc = this.a(ji.util.e.a0(), windowText, 180, false, true);
            this.bd = this.a(color, color2, 180, false, false);
            this.bg = this.a(this.getBackground(), windowText, 180, true, false);
            this.bh = this.a(ji.util.e.a0(), windowText, 180, true, true);
            this.bi = this.a(color, color2, 180, true, false);
        }
        else {
            this.a5 = this.a(this.getBackground(), windowText, 270, true, false);
            this.a6 = this.a(ji.util.e.a0(), windowText, 270, true, true);
            this.a7 = this.a(color, color2, 270, true, false);
            this.a8 = this.a(this.getBackground(), windowText, 270, false, false);
            this.a9 = this.a(ji.util.e.a0(), windowText, 270, false, true);
            this.ba = this.a(color, color2, 270, false, false);
            this.bb = this.a(this.getBackground(), windowText, 90, false, false);
            this.bc = this.a(ji.util.e.a0(), windowText, 90, false, true);
            this.bd = this.a(color, color2, 90, false, false);
            this.bg = this.a(this.getBackground(), windowText, 90, true, false);
            this.bh = this.a(ji.util.e.a0(), windowText, 90, true, true);
            this.bi = this.a(color, color2, 90, true, false);
        }
    }
    
    private final void t() {
        try {
            if (this.isSwing()) {
                this.repaint();
            }
            else {
                this.paintComponent(this.getGraphics());
            }
        }
        catch (Exception ex) {}
    }
    
    public void paint(final Graphics graphics) {
        if (this.isSwing()) {
            this.paintComponent(graphics);
        }
        else {
            this.paintComponent(graphics);
        }
    }
    
    public void paintComponent(final Graphics graphics) {
        if (!this.isVisible()) {
            return;
        }
        final boolean av = this.av;
        this.av = false;
        if (av) {
            this.o();
            if (this.a == 2) {
                this.r();
            }
            else if (this.a8 == null) {
                this.s();
            }
        }
        if (!this.ah && graphics != null) {
            try {
                this.ah = true;
                if (this.a == 2) {
                    this.c(graphics);
                }
                else {
                    this.a(graphics);
                }
            }
            catch (Exception ex) {}
            finally {
                this.ah = false;
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        if (graphics != null) {
            if (this.isSwing()) {
                super.update(graphics);
            }
            else {
                this.paintComponent(graphics);
            }
        }
    }
    
    private void a(final Graphics graphics) {
        try {
            this.be = false;
            this.b(graphics);
        }
        catch (Exception ex) {}
    }
    
    private void b(final Graphics graphics) {
        graphics.setColor(this.getForeground());
        if (this.s) {
            this.a(this.al, this.ad.x, this.ad.y, this.ad.width, this.ad.height);
            this.aj.a(this.ad.x, this.ad.y);
            this.aj.d(this.al, graphics);
        }
        this.a(this.al, this.ae.x, this.ae.y, this.ae.width, this.ae.height);
        this.aj.a(this.ae.x, this.ae.y);
        this.aj.d(this.al, graphics);
        this.a(this.al, this.af.x, this.af.y, this.af.width, this.af.height);
        this.aj.a(this.af.x, this.af.y);
        this.aj.d(this.al, graphics);
        if (this.s) {
            this.a(this.al, this.ag.x, this.ag.y, this.ag.width, this.ag.height);
            this.aj.a(this.ag.x, this.ag.y);
            this.aj.d(this.al, graphics);
        }
        final Insets a = this.aj.a();
        graphics.setColor(this.aq);
        if (this.s) {
            if (this.a5 != null) {
                if (this.ab) {
                    if (this.az) {
                        graphics.drawImage(this.a6, this.ad.x + a.left, this.ad.y + a.top, null);
                    }
                    else {
                        graphics.drawImage(this.a5, this.ad.x + a.left, this.ad.y + a.top, null);
                    }
                }
                else {
                    graphics.drawImage(this.a7, this.ad.x + a.left, this.ad.y + a.top, null);
                }
            }
            else {
                graphics.fillRect(this.ad.x + a.left, this.ad.y + a.top, this.ad.width - a.left - a.right, this.ad.height - a.top - a.bottom);
            }
        }
        if (this.a8 != null) {
            if (this.ab) {
                if (this.ax) {
                    graphics.drawImage(this.a9, this.ae.x + a.left, this.ae.y + a.top, null);
                }
                else {
                    graphics.drawImage(this.a8, this.ae.x + a.left, this.ae.y + a.top, null);
                }
            }
            else {
                graphics.drawImage(this.ba, this.ae.x + a.left, this.ae.y + a.top, null);
            }
        }
        else {
            graphics.fillRect(this.ae.x + a.left, this.ae.y + a.top, this.ae.width - a.left - a.right, this.ae.height - a.top - a.bottom);
        }
        if (this.bb != null) {
            if (this.ab) {
                if (this.ay) {
                    graphics.drawImage(this.bc, this.af.x + a.left, this.af.y + a.top, null);
                }
                else {
                    graphics.drawImage(this.bb, this.af.x + a.left, this.af.y + a.top, null);
                }
            }
            else {
                graphics.drawImage(this.bd, this.af.x + a.left, this.af.y + a.top, null);
            }
        }
        else {
            graphics.fillRect(this.af.x + a.left, this.af.y + a.top, this.af.width - a.left - a.right, this.af.height - a.top - a.bottom);
        }
        if (this.s) {
            if (this.bg != null) {
                if (this.ab) {
                    if (this.a0) {
                        graphics.drawImage(this.bh, this.ag.x + a.left, this.ag.y + a.top, null);
                    }
                    else {
                        graphics.drawImage(this.bg, this.ag.x + a.left, this.ag.y + a.top, null);
                    }
                }
                else {
                    graphics.drawImage(this.bi, this.ag.x + a.left, this.ag.y + a.top, null);
                }
            }
            else {
                graphics.fillRect(this.ag.x + a.left, this.ag.y + a.top, this.ag.width - a.left - a.right, this.ag.height - a.top - a.bottom);
            }
        }
        graphics.setColor(this.getBackground());
        final int n = 1;
        this.m();
        if (this.b == 1) {
            final int n2 = this.ac.y - this.o;
            if (n2 > 0) {
                graphics.fillRect(this.ac.x, this.o, this.ac.width - n, n2);
            }
            int n3;
            if (this.s) {
                n3 = this.getSize().height - this.ac.y - this.ac.height - 2 * this.v - 2;
            }
            else {
                n3 = this.getSize().height - this.ac.y - this.ac.height - this.v - 1;
            }
            if (n3 > 0) {
                graphics.fillRect(this.ac.x, this.ac.y + this.ac.height, this.ac.width - n, n3);
            }
        }
        else {
            if (this.ac.x - this.q > 0) {
                graphics.fillRect(this.q, this.ac.y, Math.max(this.ac.x - this.q, 0), Math.max(this.ac.height - n, 0));
            }
            int n4;
            if (this.s) {
                n4 = this.getSize().width - this.ac.x - this.ac.width - 2 * this.v - 2;
            }
            else {
                n4 = this.getSize().width - this.ac.x - this.ac.width - this.v - 1;
            }
            if (n4 > 0) {
                graphics.fillRect(this.ac.x + this.ac.width, this.ac.y, n4, this.ac.height - n);
            }
        }
        graphics.setColor(this.getForeground());
        this.ai.a(2);
        this.a(this.ak, this.ac.x, this.ac.y, this.ac.width, this.ac.height);
        this.ai.a(this.ac.x, this.ac.y);
        this.ai.d(this.ak, graphics);
        final Insets a2 = this.ai.a();
        graphics.setColor(this.ap);
        if (this.ab && this.a1 != null) {
            if (this.aw || this.bo) {
                graphics.drawImage(this.a3, this.ac.x + a2.left, this.ac.y + a2.top, null);
            }
            else {
                graphics.drawImage(this.a1, this.ac.x + a2.left, this.ac.y + a2.top, null);
            }
        }
        else {
            graphics.setColor(this.getBackground());
            graphics.fillRect(this.ac.x + a2.left, this.ac.y + a2.top, this.ac.width - a2.left - a2.right, this.ac.height - a2.top - a2.bottom);
        }
        graphics.setColor(this.au);
        if (this.b == 1) {
            graphics.drawLine(0, 0, 0, this.getSize().height);
            if (this.ae.y + this.ae.height < this.ac.y - 1) {
                graphics.drawLine(this.getSize().width - 1, this.ae.y + this.ae.height, this.getSize().width - 1, this.ac.y - 1);
            }
            if (this.ac.y + this.ac.height < this.getSize().height - this.v - 2) {
                graphics.drawLine(this.getSize().width - 1, this.ac.y + this.ac.height, this.getSize().width - 1, this.getSize().height - this.v - 2);
            }
        }
        else {
            graphics.drawLine(0, 0, this.getSize().width, 0);
            if (this.v + 1 < this.ac.x - 1) {
                graphics.drawLine(this.v + 1, this.ac.y + this.ac.height - 1, this.ac.x - 1, this.ac.y + this.ac.height - 1);
            }
            if (this.ac.x + this.ac.width < this.getSize().width - this.v - 2) {
                graphics.drawLine(this.ac.x + this.ac.width, this.ac.y + this.ac.height - 1, this.getSize().width - this.v - 2, this.ac.y + this.ac.height - 1);
            }
        }
        ji.util.d.ew();
    }
    
    private void c(final Graphics graphics) {
        if (this.ab) {
            if (this.bs != null) {
                graphics.drawImage(this.bs, 0, 0, null);
            }
        }
        else if (this.bt != null) {
            graphics.drawImage(this.bt, 0, 0, null);
        }
        if (this.b == 1) {
            final int n = this.ac.x + 2;
            final int y = this.ac.y;
            final int height = this.ac.height;
            final int n2 = 1 + this.ac.width / 4;
            final int n3 = y + height / 2;
            final int n4 = n - 1 + this.ac.width - 2;
            final int n5 = y + this.v - 1;
            final Polygon polygon = new Polygon();
            polygon.addPoint(n4, n5);
            polygon.addPoint(n + n2, n5);
            polygon.addPoint(n, n3);
            polygon.addPoint(n + n2, y);
            polygon.addPoint(n4, y);
            if (this.aw || this.bo) {
                graphics.setColor(this.ap);
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.fillPolygon(polygon);
            final Polygon polygon2 = new Polygon();
            polygon2.addPoint(n, n3);
            polygon2.addPoint(n + n2, y);
            polygon2.addPoint(n4, y);
            polygon2.addPoint(n + n2, y);
            Color color;
            if (ji.util.e.h != null) {
                color = ji.util.e.h;
            }
            else {
                color = SystemColor.controlLtHighlight;
            }
            graphics.setColor(color);
            graphics.drawPolygon(polygon2);
            final Polygon polygon3 = new Polygon();
            polygon3.addPoint(n, n3);
            polygon3.addPoint(n + n2, n5);
            polygon3.addPoint(n4, n5);
            polygon3.addPoint(n4, y);
            polygon3.addPoint(n4, n5);
            polygon3.addPoint(n + n2, n5);
            Color color2;
            if (ji.util.e.g != null) {
                color2 = ji.util.e.g.darker();
            }
            else {
                color2 = SystemColor.controlShadow.darker();
            }
            graphics.setColor(color2);
            graphics.drawPolygon(polygon3);
        }
        else {
            final int x = this.ac.x;
            final int n6 = this.ac.y + 1;
            final int width = this.ac.width;
            final int n7 = 2 + this.ac.height / 4;
            final int n8 = n6 + 1;
            final int n9 = n6 + this.ac.height - 2;
            final Polygon polygon4 = new Polygon();
            polygon4.addPoint(x, n9);
            polygon4.addPoint(x, n6 + n7);
            polygon4.addPoint(x + width / 2, n8);
            polygon4.addPoint(x + width - 1, n6 + n7);
            polygon4.addPoint(x + width - 1, n9);
            if (this.aw || this.bo) {
                graphics.setColor(this.ap);
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.fillPolygon(polygon4);
            final Polygon polygon5 = new Polygon();
            polygon5.addPoint(x, n9);
            polygon5.addPoint(x, n6 + n7);
            polygon5.addPoint(x + width / 2, n8);
            polygon5.addPoint(x, n6 + n7);
            Color color3;
            if (ji.util.e.h != null) {
                color3 = ji.util.e.h;
            }
            else {
                color3 = SystemColor.controlLtHighlight;
            }
            graphics.setColor(this.getBackground());
            graphics.fillPolygon(polygon5);
            graphics.setColor(color3);
            graphics.drawPolygon(polygon5);
            final Polygon polygon6 = new Polygon();
            polygon6.addPoint(x + width / 2, n8);
            polygon6.addPoint(x + width - 1, n6 + n7);
            polygon6.addPoint(x + width - 1, n9);
            polygon6.addPoint(x, n9);
            polygon6.addPoint(x + width - 1, n9);
            polygon6.addPoint(x + width - 1, n6 + n7);
            Color color4;
            if (ji.util.e.g != null) {
                color4 = ji.util.e.g.darker();
            }
            else {
                color4 = SystemColor.controlShadow.darker();
            }
            graphics.setColor(color4);
            graphics.drawPolygon(polygon6);
        }
        ji.util.d.ew();
    }
    
    private void a(final Graphics graphics, final boolean b) {
        graphics.setColor(this.getBackground());
        final int n = 0;
        if (this.b == 1) {
            final int n2 = this.ac.y - this.o;
            if (n2 > 0) {
                graphics.fillRect(this.ac.x, this.o, this.ac.width - n, n2);
            }
            final int n3 = this.getSize().height - this.ac.y - this.ac.height - 1;
            if (n3 > 0) {
                graphics.fillRect(this.ac.x, this.ac.y + this.ac.height, this.ac.width - n, n3);
            }
        }
        else {
            if (this.ac.x - this.q > 0) {
                graphics.fillRect(this.q, this.ac.y, this.ac.x - this.q, this.ac.height - n);
            }
            final int n4 = this.getSize().width - this.ac.x - this.ac.width - 1;
            if (n4 > 0) {
                graphics.fillRect(this.ac.x + this.ac.width, this.ac.y, n4, this.ac.height - n);
            }
        }
        final int n5 = 3;
        if (this.b == 1) {
            final int n6 = 2;
            final int n7 = this.getSize().height - this.o;
            final int n8 = n6 + this.ac.x + (this.ac.width - n5) / 2;
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, this.o, n8, n7);
            graphics.fillRect(n8 + n5, this.o, n8 - 1, n7);
            graphics.fillRect(0, 0, this.getSize().width, this.o);
            graphics.fillRect(0, this.getSize().height - 1, this.getSize().width, 1);
            this.ai.a(1);
            this.a(this.ak, n8, this.o, n5, n7);
            this.ai.a(n8, this.o);
            this.ai.d(this.ak, graphics);
            if (b) {
                graphics.setColor(this.getBackground().darker().darker());
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.fillRect(n8 + 1, this.o + 1, n5 - 2, n7 - 2);
            graphics.setColor(this.getBackground().darker().darker());
            final int n9 = (this.getSize().height - this.o) / 2;
            final int n10 = n9 / 10;
            for (int i = n9 - n10; i > this.o; i -= n10) {
                graphics.drawLine(1, i, 1, i);
            }
            for (int j = n9 + n10; j < n7 - n10; j += n10) {
                graphics.drawLine(1, j, 1, j);
            }
            if (b) {
                graphics.setColor(this.getForeground());
            }
            else {
                graphics.setColor(this.getBackground().darker());
            }
            graphics.drawLine(0, this.v / 2, 2, this.v / 2);
            graphics.drawLine(0, n9, 2, n9);
            graphics.drawLine(0, this.getSize().height - 2 - this.v / 2, 2, this.getSize().height - 2 - this.v / 2);
        }
        else {
            final int n11 = 2;
            final int n12 = this.getSize().width - this.q;
            final int n13 = n11 + this.ac.y + (this.ac.height - n5) / 2;
            graphics.setColor(this.getBackground());
            graphics.fillRect(this.q, 0, n12, n13);
            graphics.fillRect(this.q, n13 + n5, n12, n13 - 1);
            graphics.fillRect(0, 0, this.q, this.getSize().height);
            graphics.fillRect(this.getSize().width - 1, 0, 1, this.getSize().height);
            this.ai.a(1);
            this.a(this.ak, this.q, n13, n12, n5);
            this.ai.a(this.q, n13);
            this.ai.d(this.ak, graphics);
            if (b) {
                graphics.setColor(this.getBackground().darker().darker());
            }
            else {
                graphics.setColor(this.getBackground());
            }
            graphics.fillRect(this.q + 1, n13 + 1, n12 - 2, n5 - 2);
            if (b) {
                graphics.setColor(this.getBackground().darker().darker());
            }
            else {
                graphics.setColor(this.getBackground().darker());
            }
            final int n14 = this.v / 2 + (this.getSize().width - this.v) / 2;
            final int n15 = n14 / this.br;
            for (int k = n14 - n15; k > this.q; k -= n15) {
                graphics.drawLine(k, 1, k, 1);
            }
            for (int l = n14 + n15; l < n12 - n15; l += n15) {
                graphics.drawLine(l, 1, l, 1);
            }
            if (b) {
                graphics.setColor(this.getForeground());
            }
            else {
                graphics.setColor(this.getBackground().darker());
            }
            graphics.drawLine(this.v / 2, 0, this.v / 2, 2);
            graphics.drawLine(n14, 0, n14, 2);
            graphics.drawLine(this.getSize().width - 2 - this.v / 2, 0, this.getSize().width - 2 - this.v / 2, 2);
        }
    }
    
    public final int g() {
        return this.br;
    }
    
    public final void setBounds(final Rectangle bounds) {
        if (!this.getBounds().equals(bounds)) {
            this.be = true;
        }
        super.setBounds(bounds);
        this.l();
        this.av = true;
        this.repaint();
    }
    
    public final void setBounds(final int n, final int n2, final int n3, final int n4) {
        final Rectangle rectangle = new Rectangle(n, n2, n3, n4);
        final Rectangle bounds = this.getBounds();
        if (!rectangle.equals(bounds)) {
            this.be = true;
            super.setBounds(n, n2, n3, n4);
        }
        if (rectangle.width != bounds.width || rectangle.height != bounds.height) {
            this.l();
            this.av = true;
        }
    }
    
    public void removeAdjustmentListener(final AdjustmentListener adjustmentListener) {
        if (this.c != null && this.c.a(adjustmentListener)) {
            this.c.b(adjustmentListener);
        }
    }
    
    public void setValue(final int n) {
        this.a(n, 601, 5, true);
    }
    
    private void a(int min, final int w, final int x, final boolean b) {
        min = Math.min(Math.max(min, this.f), this.g);
        if (this.d != min) {
            this.be = true;
            this.d = min;
            this.w = w;
            this.x = x;
            if (b) {
                this.n();
            }
            else {
                if (this.a == 2) {
                    this.n();
                }
                this.t();
            }
            ji.util.d.ew();
            this.a(new AdjustmentEvent(this, w, x, this.d));
            this.y = false;
        }
    }
    
    public void a(final int value, final int visibleAmount, final int minimum, final int maximum) {
        this.setMaximum(maximum);
        this.setMinimum(minimum);
        this.setVisibleAmount(visibleAmount);
        this.setValue(value);
    }
    
    public int getValue() {
        return this.d;
    }
    
    public void setVisibleAmount(final int e) {
        this.e = e;
        this.u();
        this.n();
    }
    
    private final void u() {
        this.e = Math.min(this.e, this.g);
        this.e = Math.max(this.e, this.f);
        this.e = Math.min(this.e, this.g - this.f);
    }
    
    public int getVisibleAmount() {
        return this.e;
    }
    
    public void setMinimum(final int n) {
        this.f = Math.min(n, this.g);
        this.d = Math.min(this.d, this.g);
        this.d = Math.max(this.d, this.f);
        this.u();
        this.m = Math.min(this.m, this.g - this.f);
        this.n = Math.min(this.n, this.g - this.f);
        this.m = Math.max(this.m, 1);
        this.n = Math.max(this.n, 1);
        this.n();
    }
    
    public int getMinimum() {
        return this.f;
    }
    
    public void setMaximum(final int n) {
        this.g = Math.max(n, this.f);
        this.d = Math.min(this.d, this.g);
        this.d = Math.max(this.d, this.f);
        this.u();
        this.m = Math.min(this.m, this.g - this.f);
        this.n = Math.min(this.n, this.g - this.f);
        this.m = Math.max(this.m, 1);
        this.n = Math.max(this.n, 1);
        this.n();
    }
    
    public int getMaximum() {
        return this.g;
    }
    
    public final boolean h() {
        return this.j;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.a((InputEvent)mouseEvent);
    }
    
    private void b(final MouseEvent mouseEvent) {
        this.c("mousePressed");
        if (!this.ab) {
            return;
        }
        ji.util.d.mt.x = System.currentTimeMillis();
        ji.util.d.mt.y = 0L;
        this.j = true;
        this.be = true;
        ji.util.d.bt = System.currentTimeMillis();
        if (ji.util.e.at()) {
            this.bf = true;
            ji.util.e.b(this);
            this.bo = false;
        }
        if (mouseEvent.getSource() instanceof by) {
            final Point point = mouseEvent.getPoint();
            final Rectangle bounds = this.getBounds();
            if (this.ad.contains(mouseEvent.getPoint())) {
                this.e(mouseEvent);
            }
            else if (this.ae.contains(mouseEvent.getPoint())) {
                this.b(true, mouseEvent);
            }
            else if (this.af.contains(mouseEvent.getPoint())) {
                this.a(true, mouseEvent);
            }
            else if (this.ag.contains(mouseEvent.getPoint())) {
                this.c(mouseEvent);
            }
            else if (this.ac.contains(mouseEvent.getPoint())) {
                this.k(mouseEvent);
                this.aw = true;
                this.a(new AdjustmentEvent(this, 601, 5, this.d));
                this.y = false;
                this.repaint();
            }
            else if (this.b == 1) {
                if (this.a == 2) {
                    if (point.y > 0 && point.y < this.ac.y) {
                        this.d(true);
                    }
                    else if (point.y > this.ac.y + this.ac.height && point.y < this.getSize().height) {
                        this.e(true);
                    }
                }
                else if (point.y > this.v && point.y < this.ac.y) {
                    this.d(true);
                }
                else if (point.y > this.ac.y + this.ac.height && point.y < bounds.height - this.v) {
                    this.e(true);
                }
            }
            else if (this.a == 2) {
                if (point.x > 0 && point.x < this.ac.x) {
                    this.f(true);
                }
                else if (point.x > this.ac.x + this.ac.width && point.x < this.getSize().width) {
                    this.g(true);
                }
            }
            else if (point.x > this.v && point.x < this.ac.x) {
                this.f(true);
            }
            else if (point.x > this.ac.x + this.ac.width && point.x < bounds.width - this.v) {
                this.g(true);
            }
        }
    }
    
    private final void a(final boolean b, final MouseEvent mouseEvent) {
        this.c("processDownPressed");
        if (ji.util.d.a8()) {
            if (this.k(mouseEvent)) {
                this.repaint();
            }
        }
        else {
            this.ar = ji.util.e.a0();
            this.ay = true;
            this.repaint();
        }
        if (this.d < this.g) {
            this.a(this.d + this.m, 601, 1, true);
            if (b) {
                this.a(this.m);
            }
        }
    }
    
    private final void c(final MouseEvent mouseEvent) {
        if (ji.util.d.a8()) {
            if (this.k(mouseEvent)) {
                this.repaint();
            }
        }
        else {
            this.at = ji.util.e.a0();
            this.a0 = true;
            this.repaint();
        }
        if (this.d < this.g) {
            this.a(this.g, 601, 1, true);
        }
    }
    
    private final void d(final MouseEvent mouseEvent) {
        if (ji.util.d.a8()) {
            if (this.k(mouseEvent)) {
                this.repaint();
            }
        }
        else {
            this.at = ji.util.e.a0();
            this.a0 = true;
            this.repaint();
        }
        if (this.d != this.g / 2) {
            this.a(this.g / 2, 601, 1, true);
        }
    }
    
    private final void e(final MouseEvent mouseEvent) {
        if (ji.util.d.a8()) {
            if (this.k(mouseEvent)) {
                this.repaint();
            }
        }
        else {
            this.as = ji.util.e.a0();
            this.az = true;
            this.repaint();
        }
        if (this.d > this.f) {
            this.a(this.f, 601, 2, true);
        }
    }
    
    private final void b(final boolean b, final MouseEvent mouseEvent) {
        this.c("processUpPressed");
        if (ji.util.d.a8()) {
            if (this.k(mouseEvent)) {
                this.repaint();
            }
        }
        else {
            this.aq = ji.util.e.a0();
            this.ax = true;
            this.repaint();
        }
        if (this.d > this.f) {
            this.a(this.d - this.m, 601, 2, true);
            if (b) {
                this.a(-this.m);
            }
        }
    }
    
    private final void d(final boolean b) {
        this.c("processUpBlockPressed");
        this.a(this.d - this.n, 601, 4, true);
        if (b) {
            this.a(-this.n);
        }
    }
    
    private final void e(final boolean b) {
        this.c("processDownBlockPressed");
        this.a(this.d + this.n, 601, 2, true);
        if (b) {
            this.a(this.n);
        }
    }
    
    private final void f(final boolean b) {
        this.c("processLeftBlockPressed");
        this.a(Math.max(this.d - this.n, this.f), 601, 4, true);
        if (b) {
            this.a(-this.n);
        }
    }
    
    private final void g(final boolean b) {
        this.c("processRightBlockPressed");
        this.a(Math.max(this.d + this.n, this.f), 601, 3, true);
        if (b) {
            this.a(this.n);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.a((InputEvent)mouseEvent);
    }
    
    private void f(final MouseEvent mouseEvent) {
        this.c("mouseReleased");
        ji.util.d.mt.z = System.currentTimeMillis();
        ji.util.d.mt.aa = 0L;
        this.a(mouseEvent);
    }
    
    public void a(final MouseEvent mouseEvent) {
        this.c("processReleased");
        boolean k = false;
        this.j = false;
        this.be = true;
        this.x();
        if (!this.ab) {
            return;
        }
        ji.util.d.bt = System.currentTimeMillis();
        if (mouseEvent != null) {
            k = this.k(mouseEvent);
        }
        if (this.aw) {
            if (!ji.util.d.a8()) {
                this.ap = this.getBackground();
                this.aw = false;
                k = true;
            }
            this.n();
        }
        if (!ji.util.d.a8()) {
            if (this.az) {
                this.as = this.getBackground();
                this.az = false;
                k = true;
            }
            if (this.ax) {
                this.aq = this.getBackground();
                this.ax = false;
                k = true;
            }
            if (this.ay) {
                this.ar = this.getBackground();
                this.ay = false;
                k = true;
            }
            if (this.a0) {
                this.at = this.getBackground();
                this.a0 = false;
                k = true;
            }
        }
        if (k) {
            this.repaint();
        }
        this.t = false;
        this.am = null;
        this.an = null;
        if (!this.y) {
            this.a(new AdjustmentEvent(this, 601, this.x, this.z));
            this.y = true;
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.a((InputEvent)mouseEvent);
    }
    
    private void g(final MouseEvent mouseEvent) {
        if (!this.ab) {
            return;
        }
        ji.util.d.bt = System.currentTimeMillis();
        if (mouseEvent.getSource() instanceof by) {
            if (!this.t) {
                if (this.i == null) {
                    this.am = new Point(0, 0);
                    this.an = new Point(0, 0);
                    this.ao.x = mouseEvent.getPoint().x;
                    this.ao.y = mouseEvent.getPoint().y;
                    this.h(mouseEvent);
                    this.t = true;
                }
            }
            else {
                if (this.b == 1) {
                    if (this.a == 2) {
                        this.ac.y = Math.min(Math.max(mouseEvent.getPoint().y - this.an.y + this.am.y, 0), this.p);
                    }
                    else {
                        this.ac.y = Math.min(Math.max(mouseEvent.getPoint().y - this.an.y + this.am.y, this.o), this.p);
                    }
                    int n = 0;
                    if (this.ac.height <= 30 && this.g < 5) {
                        n = 12;
                    }
                    final int n2 = this.f + (this.g - this.f) * (this.ac.y - this.o + n) / (this.p - this.o);
                    if (n2 == this.d || mouseEvent.getPoint().y < this.o || mouseEvent.getPoint().y > this.p) {
                        this.h(mouseEvent);
                    }
                    if (n2 != this.d) {
                        this.a(n2, 601, 5, false);
                    }
                }
                else {
                    if (this.a == 2) {
                        this.ac.x = Math.min(Math.max(mouseEvent.getPoint().x - this.an.x + this.am.x, 0), this.r);
                    }
                    else {
                        this.ac.x = Math.min(Math.max(mouseEvent.getPoint().x - this.an.x + this.am.x, this.q), this.r);
                    }
                    int n3 = 0;
                    if (this.ac.width <= 30 && this.g < 5) {
                        n3 = 12;
                    }
                    final int n4 = this.f + (this.g - this.f) * (this.ac.x - this.q + n3) / (this.r - this.q);
                    if (n4 == this.d || mouseEvent.getPoint().x < this.q || mouseEvent.getPoint().x > this.r) {
                        this.h(mouseEvent);
                    }
                    if (n4 != this.d) {
                        this.a(n4, 601, 5, false);
                    }
                    else {
                        this.repaint();
                    }
                }
                this.ao.x = mouseEvent.getPoint().x;
                this.ao.y = mouseEvent.getPoint().y;
            }
        }
    }
    
    private final void h(final MouseEvent mouseEvent) {
        this.am.x = this.ac.x;
        this.am.y = this.ac.y;
        this.an.x = mouseEvent.getPoint().x;
        this.an.y = mouseEvent.getPoint().y;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.a((InputEvent)mouseEvent);
    }
    
    private void i(final MouseEvent mouseEvent) {
        if (this.k(mouseEvent)) {
            this.repaint();
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.a((InputEvent)mouseEvent);
    }
    
    private void j(final MouseEvent mouseEvent) {
        if (this.k(mouseEvent)) {
            this.repaint();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.a((InputEvent)mouseEvent);
    }
    
    private final boolean k(final MouseEvent mouseEvent) {
        boolean b = false;
        if (mouseEvent == null) {
            return b;
        }
        if (ji.util.d.a8()) {
            final boolean b2 = mouseEvent.getID() == 505;
            try {
                if (this.ac.contains(mouseEvent.getPoint())) {
                    if (b2) {
                        this.ap = this.getBackground();
                        this.aw = false;
                        b = true;
                    }
                    else {
                        this.ap = this.k();
                        this.aw = true;
                        b = true;
                    }
                }
                else if (this.ap == this.k()) {
                    this.ap = this.getBackground();
                    this.aw = false;
                    b = true;
                }
            }
            catch (Exception ex) {}
            try {
                if (this.af.contains(mouseEvent.getPoint())) {
                    if (b2) {
                        this.ar = this.getBackground();
                        this.ay = false;
                        b = true;
                    }
                    else {
                        this.ar = ji.util.e.a0();
                        this.ay = true;
                        b = true;
                    }
                }
                else if (this.ar == ji.util.e.a0()) {
                    this.ar = this.getBackground();
                    this.ay = false;
                    b = true;
                }
            }
            catch (Exception ex2) {}
            try {
                if (this.ae.contains(mouseEvent.getPoint())) {
                    if (b2) {
                        this.aq = this.getBackground();
                        this.ax = false;
                        b = true;
                    }
                    else {
                        this.aq = ji.util.e.a0();
                        this.ax = true;
                        b = true;
                    }
                }
                else if (this.aq == ji.util.e.a0()) {
                    this.aq = this.getBackground();
                    this.ax = false;
                    b = true;
                }
            }
            catch (Exception ex3) {}
            try {
                if (this.ad.contains(mouseEvent.getPoint())) {
                    if (b2) {
                        this.as = this.getBackground();
                        this.az = false;
                        b = true;
                    }
                    else {
                        this.as = ji.util.e.a0();
                        this.az = true;
                        b = true;
                    }
                }
                else if (this.as == ji.util.e.a0()) {
                    this.as = this.getBackground();
                    this.az = false;
                    b = true;
                }
            }
            catch (Exception ex4) {}
            try {
                if (this.ag.contains(mouseEvent.getPoint())) {
                    if (b2) {
                        this.at = this.getBackground();
                        this.a0 = false;
                        b = true;
                    }
                    else {
                        this.at = ji.util.e.a0();
                        this.a0 = true;
                        b = true;
                    }
                }
                else if (this.at == ji.util.e.a0()) {
                    this.at = this.getBackground();
                    this.a0 = false;
                    b = true;
                }
            }
            catch (Exception ex5) {}
        }
        return b;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.a((InputEvent)mouseEvent);
    }
    
    private void l(final MouseEvent mouseEvent) {
    }
    
    public void setUnitIncrement(final int n) {
        this.m = Math.max(n, 1);
        this.m = Math.min(this.m, this.g - this.f);
    }
    
    public int getUnitIncrement() {
        return this.m;
    }
    
    public void setBlockIncrement(final int n) {
        this.n = Math.max(n, 1);
        this.n = Math.min(this.n, this.g - this.f);
    }
    
    public int getBlockIncrement() {
        return this.n;
    }
    
    public final Dimension getMinimumSize() {
        return new Dimension(this.u, this.u);
    }
    
    public final Dimension getPreferredSize() {
        return this.getMinimumSize();
    }
    
    public int i() {
        return this.u;
    }
    
    public void focusGained(final FocusEvent focusEvent) {
        if (!this.bo) {
            this.be = true;
            if (!this.bf) {
                this.ap = this.k();
                this.bo = true;
            }
            this.bf = false;
            this.v();
            this.repaint();
        }
    }
    
    private final void v() {
        if (this.b == 1) {
            ji.util.d.a(38, this);
            ji.util.d.a(40, this);
            ji.util.d.a(36, this);
            ji.util.d.a(35, this);
            ji.util.d.a(155, this);
        }
        else {
            ji.util.d.a(37, this);
            ji.util.d.a(39, this);
            ji.util.d.a(36, this);
            ji.util.d.a(35, this);
            ji.util.d.a(155, this);
        }
    }
    
    private final void w() {
        if (this.b == 1) {
            ji.util.d.b(38, this);
            ji.util.d.b(40, this);
            ji.util.d.b(36, this);
            ji.util.d.b(35, this);
            ji.util.d.b(155, this);
        }
        else {
            ji.util.d.b(37, this);
            ji.util.d.b(39, this);
            ji.util.d.b(36, this);
            ji.util.d.b(35, this);
            ji.util.d.b(155, this);
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        this.bf = false;
        if (this.bo) {
            this.be = true;
            this.ap = this.getBackground();
            this.bo = false;
            this.repaint();
        }
        this.w();
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.a((InputEvent)keyEvent);
    }
    
    private void b(final KeyEvent keyEvent) {
        final int modifiers = keyEvent.getModifiers();
        if ((modifiers & 0x8) == 0x8) {
            keyEvent.consume();
            return;
        }
        if ((modifiers & 0x2) == 0x2) {
            keyEvent.consume();
            return;
        }
        if ((modifiers & 0x1) == 0x1) {
            keyEvent.consume();
            return;
        }
        if (this.b == 1) {
            switch (keyEvent.getKeyCode()) {
                case 38: {
                    keyEvent.consume();
                    this.b(false, null);
                    break;
                }
                case 40: {
                    keyEvent.consume();
                    this.a(false, null);
                    break;
                }
                case 36: {
                    keyEvent.consume();
                    this.e((MouseEvent)null);
                    this.a(new AdjustmentEvent(this, 601, this.x, this.z));
                    break;
                }
                case 35: {
                    keyEvent.consume();
                    this.c((MouseEvent)null);
                    this.a(new AdjustmentEvent(this, 601, this.x, this.z));
                    break;
                }
                case 155: {
                    keyEvent.consume();
                    this.d((MouseEvent)null);
                    this.a(new AdjustmentEvent(this, 601, this.x, this.z));
                    break;
                }
            }
        }
        else {
            switch (keyEvent.getKeyCode()) {
                case 37: {
                    keyEvent.consume();
                    this.b(false, null);
                    break;
                }
                case 39: {
                    keyEvent.consume();
                    this.a(false, null);
                    break;
                }
                case 36: {
                    keyEvent.consume();
                    this.e((MouseEvent)null);
                    this.a(new AdjustmentEvent(this, 601, this.x, this.z));
                    break;
                }
                case 35: {
                    keyEvent.consume();
                    this.c((MouseEvent)null);
                    this.a(new AdjustmentEvent(this, 601, this.x, this.z));
                    break;
                }
                case 155: {
                    keyEvent.consume();
                    this.d((MouseEvent)null);
                    this.a(new AdjustmentEvent(this, 601, this.x, this.z));
                    break;
                }
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.a((InputEvent)keyEvent);
    }
    
    public void a(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 38 || keyEvent.getKeyCode() == 40 || keyEvent.getKeyCode() == 37 || keyEvent.getKeyCode() == 39) {
            this.a((MouseEvent)null);
        }
    }
    
    public void b(final boolean aa) {
        this.aa = aa;
    }
    
    public int j() {
        return this.z;
    }
    
    public void c(final boolean s) {
        if (this.a != 2) {
            this.s = s;
        }
    }
    
    public void addAdjustmentListener(final AdjustmentListener adjustmentListener) {
        if (!this.bp) {
            if (this.c == null) {
                this.c = new c("jiScrollbar1", 2);
            }
            if (!this.c.a(adjustmentListener)) {
                this.c.c(adjustmentListener);
            }
        }
    }
    
    public final void a(final a9 a9) {
        if (a9.d() instanceof d8) {
            this.a((d8)a9.d());
        }
    }
    
    private void a(final AdjustmentEvent adjustmentEvent) {
        if (this.c != null) {
            final c c = this.c;
            for (int b = c.b(), i = 0; i < b; ++i) {
                ((AdjustmentListener)c.b(i)).adjustmentValueChanged(adjustmentEvent);
            }
        }
    }
    
    private void a(final int n) {
        this.c("startScrollTimer");
        this.x();
        this.i = new s7(n, this);
        new bb(this.b1, this.i).start();
    }
    
    private void x() {
        this.c("stopScrollTimer");
        if (this.i != null) {
            this.c("stopScrollTimer - stopped");
            this.i.a();
            this.i = null;
        }
    }
    
    public void addToolTipListener(final c9 c9) {
        if (!this.isSwing()) {
            this.y();
            if (this.bv == null) {
                this.bv = new c("jiScrollbar1", 2);
            }
            if (!this.bv.a(c9)) {
                this.bv.c(c9);
            }
        }
    }
    
    public void removeToolTipListener(final c9 c9) {
        if (!this.isSwing() && this.bv != null && this.bv.a(c9)) {
            this.bv.b(c9);
        }
    }
    
    public void fireTipEvent(final d8 d8) {
        if (ji.util.d.am()) {
            if (this.b2 != null) {
                this.b2.a(new a9(this, d8, false));
            }
        }
        else {
            this.a(d8);
        }
    }
    
    public void a(final d8 d8) {
        if (!this.isSwing() && this.bv != null) {
            for (int i = 0; i < this.bv.b(); ++i) {
                if (!d8.f()) {
                    ((c9)this.bv.b(i)).a(d8);
                }
            }
        }
    }
    
    private final void y() {
        if (!this.isSwing() && this.bu == null) {
            this.addMouseMotionListener(this.bu = new br(this.b1, this));
            this.addMouseListener(this.bu);
        }
    }
    
    public void releaseResources() {
        if (!this.bp) {
            try {
                this.bp = true;
                if (this.h != null) {
                    this.h.a();
                    this.h = null;
                }
                if (this.i != null) {
                    this.i.a();
                    this.i = null;
                }
                this.w();
                this.q();
                try {
                    if (this.b3 != null) {
                        this.removeWheelListener(this.b3);
                        this.b3 = null;
                    }
                }
                catch (Exception ex2) {}
                if (this.a1 != null) {
                    this.a1.flush();
                }
                if (this.a2 != null) {
                    this.a2.flush();
                }
                if (this.a3 != null) {
                    this.a3.flush();
                }
                if (this.a4 != null) {
                    this.a4.flush();
                }
                if (this.a5 != null) {
                    this.a5.flush();
                }
                if (this.a6 != null) {
                    this.a6.flush();
                }
                if (this.a7 != null) {
                    this.a7.flush();
                }
                if (this.a8 != null) {
                    this.a8.flush();
                }
                if (this.a9 != null) {
                    this.a9.flush();
                }
                if (this.ba != null) {
                    this.ba.flush();
                }
                if (this.bg != null) {
                    this.bg.flush();
                }
                if (this.bh != null) {
                    this.bh.flush();
                }
                if (this.bi != null) {
                    this.bi.flush();
                }
                if (this.bb != null) {
                    this.bb.flush();
                }
                if (this.bc != null) {
                    this.bc.flush();
                }
                if (this.bd != null) {
                    this.bd.flush();
                }
                this.a1 = null;
                this.a2 = null;
                this.a3 = null;
                this.a4 = null;
                this.a8 = null;
                this.a9 = null;
                this.ba = null;
                this.a5 = null;
                this.a6 = null;
                this.a7 = null;
                this.bg = null;
                this.bh = null;
                this.bi = null;
                this.bb = null;
                this.bc = null;
                this.bd = null;
                ji.util.e.a(this.b1, this, false);
                this.removeMouseListener(this);
                this.removeKeyListener(this);
                this.removeFocusListener(this);
                if (this.ak != null) {
                    this.ak.releaseResources();
                    this.ak = null;
                }
                if (this.al != null) {
                    this.al.releaseResources();
                    this.al = null;
                }
                if (this.b2 != null) {
                    this.b2.a(this);
                    this.b2.g();
                    this.b2 = null;
                }
                if (this.bu != null) {
                    this.removeMouseMotionListener(this.bu);
                    this.removeMouseListener(this.bu);
                    this.bu.a();
                    this.bu = null;
                }
                if (this.c != null) {
                    this.c.c();
                    this.c = null;
                }
                super.releaseResources();
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
        }
    }
    
    public void finalize() {
        try {
            this.releaseResources();
            super.finalize();
        }
        catch (Exception ex) {}
    }
    
    private void c(final String s) {
        if (ji.util.i.c(168)) {
            ji.io.h.d(this.b1, "jiScrollbar - ".concat(String.valueOf(String.valueOf(s))));
        }
    }
    
    private synchronized void a(final InputEvent inputEvent) {
        if (this.h == null) {
            this.h = new s6((aek)null);
            new bb(this.b1, this.h).start();
        }
        this.h.a(inputEvent);
    }
    
    static {
        by = new Cursor(0);
        ji.graphic.by.bz = false;
    }
    
    private class s6 implements Runnable
    {
        private boolean a;
        private Object b;
        private Vector c;
        
        private s6() {
            this.a = false;
            this.b = new Object();
            this.c = new Vector();
        }
        
        private synchronized void a() {
            this.a = true;
            synchronized (this.b) {
                this.b.notifyAll();
            }
            // monitorexit(this.b)
        }
        
        private synchronized boolean b() {
            return this.a;
        }
        
        public void run() {
            while (!this.b()) {
                try {
                    while (this.c.size() > 0) {
                        this.b(this.c.elementAt(0));
                        this.c.removeElementAt(0);
                    }
                    if (!this.b()) {
                        if (Thread.currentThread().isAlive()) {
                            synchronized (this.b) {
                                try {
                                    this.b.wait(5000L);
                                }
                                catch (InterruptedException ex2) {
                                }
                                // monitorexit(this.b)
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    continue;
                }
                break;
            }
        }
        
        public void a(final InputEvent inputEvent) {
            synchronized (this.b) {
                this.c.addElement(inputEvent);
                this.b.notifyAll();
            }
            // monitorexit(this.b)
        }
        
        private void b(final InputEvent inputEvent) {
            synchronized (this.b) {
                if (inputEvent instanceof MouseEvent) {
                    switch (inputEvent.getID()) {
                        case 501: {
                            ji.graphic.by.this.b((MouseEvent)inputEvent);
                            break;
                        }
                        case 502: {
                            ji.graphic.by.this.f((MouseEvent)inputEvent);
                            break;
                        }
                        case 506: {
                            ji.graphic.by.this.g((MouseEvent)inputEvent);
                            break;
                        }
                        case 504: {
                            ji.graphic.by.this.i((MouseEvent)inputEvent);
                            break;
                        }
                        case 505: {
                            ji.graphic.by.this.j((MouseEvent)inputEvent);
                            break;
                        }
                        case 500: {
                            ji.graphic.by.this.l((MouseEvent)inputEvent);
                            break;
                        }
                    }
                }
                else if (inputEvent instanceof KeyEvent) {
                    switch (inputEvent.getID()) {
                        case 401: {
                            ji.graphic.by.this.b((KeyEvent)inputEvent);
                            break;
                        }
                        case 402: {
                            ji.graphic.by.this.a((KeyEvent)inputEvent);
                            break;
                        }
                    }
                }
            }
            // monitorexit(this.b)
        }
    }
    
    class s7 implements Runnable
    {
        int a;
        Adjustable b;
        boolean c;
        
        private synchronized void a() {
            this.c = true;
        }
        
        private synchronized boolean b() {
            return this.c;
        }
        
        public s7(final int a, final Adjustable b) {
            this.a = 1;
            this.b = null;
            this.c = false;
            this.b = b;
            this.a = a;
        }
        
        public void run() {
            try {
                ji.graphic.by.this.c(String.valueOf(String.valueOf(new StringBuffer("jiScrollTimer - abort = ").append(this.b()).append(" - 1"))));
                for (int n = 0; !this.b() && n < ji.graphic.by.this.k / 10; ++n) {
                    ji.util.d.b(10, 141, ji.graphic.by.this.b1);
                }
                ji.graphic.by.this.c(String.valueOf(String.valueOf(new StringBuffer("jiScrollTimer - abort = ").append(this.b()).append(" - 2"))));
                while (!this.b() && this.equals(ji.graphic.by.this.i)) {
                    ji.util.d.b(ji.graphic.by.this.l, 142, ji.graphic.by.this.b1);
                    ji.graphic.by.this.c(String.valueOf(String.valueOf(new StringBuffer("jiScrollTimer.setValue - value= ").append(ji.graphic.by.this.d).append(", increment=").append(this.a).append(" - 3"))));
                    ji.graphic.by.this.a(ji.graphic.by.this.d + this.a, 601, 5, true);
                    if (ji.graphic.by.this.d == ji.graphic.by.this.f || ji.graphic.by.this.d == ji.graphic.by.this.g) {
                        ji.graphic.by.this.c(String.valueOf(String.valueOf(new StringBuffer("jiScrollTimer.min/max value=").append(ji.graphic.by.this.d).append(" - 4"))));
                        ji.graphic.by.this.a(new AdjustmentEvent(this.b, 601, 5, ji.graphic.by.this.z));
                        ji.graphic.by.this.y = true;
                        break;
                    }
                }
            }
            finally {
                ji.graphic.by.this.c(String.valueOf(String.valueOf(new StringBuffer("jiScrollTimer - abort = ").append(this.b()).append(" - 5"))));
                ji.graphic.by.this.i = null;
                this.b = null;
            }
        }
    }
    
    class v4 implements Runnable
    {
        public void run() {
            try {
                if (ji.graphic.by.this.aw) {
                    ji.util.d.b(200, 140, ji.graphic.by.this.b1);
                }
                ji.graphic.by.this.d();
            }
            catch (Exception ex) {}
            finally {
                ji.graphic.by.this.b0 = null;
            }
        }
    }
    
    class ui implements as
    {
        public void mouseWheelMoved(final g9 g9) {
            try {
                ji.graphic.by.this.a(g9);
            }
            catch (Exception ex) {}
        }
    }
    
    interface aek
    {
    }
}
