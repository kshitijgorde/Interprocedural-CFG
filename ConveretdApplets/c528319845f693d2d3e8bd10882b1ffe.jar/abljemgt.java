import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import ABLjemsty.CommandStatusPanel;
import java.awt.Cursor;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.StringSelection;
import java.awt.FontMetrics;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import ABLwidgets.utils;
import java.awt.Graphics;
import java.awt.AWTEvent;
import java.awt.Event;
import java.awt.Dimension;
import ABLwidgets.new_font;
import java.util.Hashtable;
import java.awt.datatransfer.Clipboard;
import java.awt.MenuItem;
import java.awt.Window;
import java.awt.Image;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class abljemgt extends Canvas
{
    protected boolean a;
    protected boolean b;
    protected boolean c;
    protected abljemgt d;
    protected abljemgt e;
    protected boolean f;
    private abljema g;
    private BlinkerThread h;
    private abljema i;
    private Component j;
    private boolean k;
    private boolean l;
    private boolean m;
    public boolean n;
    private char o;
    public boolean p;
    private boolean q;
    public boolean r;
    private Color s;
    private Color[] t;
    private Color[] u;
    private boolean[] v;
    private Font w;
    private char[] x;
    private boolean[] y;
    private int[] z;
    private int aa;
    private int ab;
    private Color ac;
    private Color ad;
    private Color ae;
    private Color af;
    private boolean ag;
    public boolean ah;
    private int ai;
    private final int aj = 2;
    private int ak;
    private int al;
    private int am;
    private int an;
    public int ao;
    private Color ap;
    private Color aq;
    private boolean ar;
    private Color as;
    private Color at;
    public int au;
    public String av;
    private boolean aw;
    private boolean ax;
    public char ay;
    private Image az;
    private Image a0;
    private Image a1;
    private int a2;
    private int a3;
    private int a4;
    private Color a5;
    private int a6;
    private int a7;
    private int a8;
    private int a9;
    private int ba;
    private int bb;
    private int bc;
    private int bd;
    private boolean be;
    private boolean bf;
    private String bg;
    private String bh;
    private int bi;
    private int bj;
    private int bk;
    private boolean bl;
    private Color bm;
    private Color bn;
    private boolean bo;
    private int[] bp;
    private int[] bq;
    public static abljempu br;
    private static Window bs;
    private static abljemgt bt;
    private static MenuItem bu;
    private static MenuItem bv;
    private static MenuItem bw;
    private static MenuItem bx;
    private static MenuItem by;
    private static MenuItem bz;
    private static MenuItem b0;
    private static MenuItem b1;
    private static MenuItem b2;
    private static MenuItem b3;
    protected static Clipboard b4;
    protected static int b5;
    protected static int b6;
    private static String b7;
    private static Hashtable b8;
    private static double b9;
    private static int ca;
    
    abljemgt(final abljema abljema) {
        this("", 0, abljema, null);
    }
    
    abljemgt(final abljema abljema, final Component component) {
        this("", 0, abljema, component);
    }
    
    abljemgt(final String s, final int n, final abljema abljema) {
        this(s, n, abljema, null);
    }
    
    abljemgt(final String av, final int au, final abljema g, final Component j) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.l = false;
        this.m = false;
        this.n = false;
        this.o = 'N';
        this.q = false;
        this.r = true;
        this.ag = true;
        this.ai = 2;
        this.ak = 2;
        this.al = 2 + this.ak;
        this.am = this.al;
        this.an = this.al;
        this.ao = this.am + this.an;
        this.av = "";
        this.aw = true;
        this.ax = false;
        this.ay = '*';
        this.be = false;
        this.bf = true;
        this.bg = "";
        this.bh = "";
        this.bi = -1;
        this.bl = false;
        this.bo = false;
        if (abljemeh.a()) {
            this.enableEvents(abljemeh.b());
        }
        this.g = g;
        this.j = j;
        this.au = au;
        this.av = av;
        this.k = (this instanceof abljemtf);
        this.i = (this.k ? this.g : null);
        this.l();
        this.ac = new Color(132, 132, 132);
        this.ad = new Color(66, 66, 66);
        this.ae = Color.white;
        this.af = new Color(214, 214, 206);
        this.ap = new Color(8, 33, 107);
        this.aq = Color.white;
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.ar = true;
        this.f();
    }
    
    public int n() {
        return Math.min(this.a9, this.ba);
    }
    
    public int o() {
        return Math.max(this.a9, this.ba);
    }
    
    public boolean p() {
        return this.ax;
    }
    
    public void b(final char ay) {
        this.ay = ay;
        this.ax = (this.ay != '\0');
        this.bf = true;
    }
    
    public boolean q() {
        return true;
    }
    
    public boolean r() {
        return this.q;
    }
    
    public void b(final int au, final boolean l) {
        this.au = au;
        this.l = l;
    }
    
    public void a(final Color aq, final Color ap) {
        if (aq != null) {
            this.aq = aq;
        }
        if (ap != null) {
            this.ap = ap;
        }
    }
    
    public void b(final Color at, final Color as) {
        this.ar = (at == null && as == null);
        this.f();
        if (at != null) {
            this.at = at;
        }
        if (as != null) {
            this.as = as;
        }
    }
    
    public void a(final boolean ag, final Color ac, final Color ad, final Color ae, final Color af) {
        this.ag = ag;
        if (ac != null) {
            this.ac = ac;
        }
        if (ad != null) {
            this.ad = ad;
        }
        if (ae != null) {
            this.ae = ae;
        }
        if (af != null) {
            this.af = af;
        }
    }
    
    public void a(final Color s, final Color[] t, final Color[] u, final boolean[] v) {
        this.q = true;
        this.r = true;
        this.s = s;
        this.t = t;
        this.u = u;
        this.v = v;
    }
    
    public Font getFont() {
        if (this.w != null) {
            return this.w;
        }
        return super.getFont();
    }
    
    public void setFont(Font a) {
        if (this.p && !a.isBold()) {
            a = new_font.a(a.getName(), 1, a.getSize());
        }
        this.w = a;
        this.bi = -1;
        this.bf = true;
        this.repaint();
    }
    
    public void s() {
        this.b(0, this.av.length());
    }
    
    public void b(int min, int min2) {
        min = Math.min(Math.max(0, min), this.av.length());
        min2 = Math.min(Math.max(min, min2), this.av.length());
        this.ba = min;
        this.d(min2);
        this.e();
        if (min == min2 && min == 0) {
            this.c();
        }
        this.repaint();
    }
    
    public Dimension getSize() {
        final Dimension size = super.getSize();
        if (size.width == 0) {
            size.width = 24 + Math.max(0, ((this.au > 0) ? this.au : this.av.length()) * this.getFont().getSize() / 2);
        }
        if (size.height == 0) {
            size.height = (int)(10.0 + this.getFont().getSize() * 1.15);
        }
        return size;
    }
    
    public void c(final String s) {
        this.av = ((s == null) ? "" : s);
        this.bf = true;
        this.a();
        this.d(0);
        this.ba = 0;
        this.bd = 0;
        this.repaint();
    }
    
    public void setForeground(final Color foreground) {
        super.setForeground(foreground);
        this.f();
    }
    
    public void setBackground(final Color bm) {
        this.bm = bm;
        super.setBackground(this.bn = (this.ah ? a(this.bm) : this.bm));
        this.f();
        this.bf = true;
    }
    
    public Color getBackground() {
        return this.bm;
    }
    
    public void enable() {
        this.a(true);
        super.enable();
    }
    
    public void disable() {
        this.a(false);
        super.disable();
    }
    
    public void setEnabled(final boolean enabled) {
        this.a(enabled);
        super.setEnabled(enabled);
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof abljempu && abljemgt.bt == this) {
            if (event.id == 1005) {
                this.b = false;
                this.bl = false;
                if (o instanceof String && o.equals("Hide")) {
                    this.d();
                }
                else {
                    if (this.k) {
                        this.g.fb.d8 = false;
                    }
                    this.requestFocus();
                }
            }
            else if (event.target == abljemgt.br) {
                final MenuItem a = abljemgt.br.a(event);
                if (a == abljemgt.bu) {
                    this.b(26);
                }
                if (a == abljemgt.bw) {
                    this.b(24);
                }
                if (a == abljemgt.bx) {
                    this.b(3);
                }
                if (a == abljemgt.by) {
                    this.b(22);
                }
                if (a == abljemgt.bz) {
                    this.b(127);
                }
                if (a == abljemgt.b1) {
                    this.b(1);
                }
                if (a == abljemgt.b3) {
                    this.bl = true;
                    this.g.fb.d8 = false;
                    this.g.fb.a(this);
                    return true;
                }
            }
            return true;
        }
        return super.action(event, o);
    }
    
    public boolean keyDown(final Event event, final int n) {
        if (this.k && this.g.fb.e0.a(event, this.g.fb.e1)) {
            return true;
        }
        final boolean a = this.a(event, n);
        this.e(false);
        return a;
    }
    
    private boolean a(final Event event, final int n) {
        if (n == 127) {
            switch (event.modifiers) {
                case 0: {
                    this.b(127);
                    return true;
                }
                case 1: {
                    this.b(8);
                    return true;
                }
                case 8: {
                    this.b(12);
                    return true;
                }
            }
        }
        if (n == 8 && event.modifiers == 0) {
            this.b(8);
            return true;
        }
        final int min = Math.min(this.ba, this.a9);
        final int max = Math.max(this.ba, this.a9);
        if (event.modifiers == 2) {
            if (this.b(n)) {
                return true;
            }
            if (event.id == 401) {
                return super.keyDown(event, n);
            }
        }
        if (event.id != 401) {
            if (n == 1000) {
                if (event.modifiers != 2 && event.modifiers != 1 && (event.modifiers != 0 || this.n)) {
                    return super.keyDown(event, n);
                }
                if (this.a9 > 0 || this.ba != this.a9) {
                    this.d(0);
                    this.bd = 0;
                    if (!event.shiftDown()) {
                        this.ba = this.a9;
                    }
                    this.d();
                }
            }
            else if (n == 1001) {
                if (event.modifiers != 2 && event.modifiers != 1 && (event.modifiers != 0 || this.n)) {
                    return super.keyDown(event, n);
                }
                if (this.a9 < this.av.length() || this.ba != this.a9) {
                    this.d(this.av.length());
                    if (!event.shiftDown()) {
                        this.ba = this.a9;
                    }
                    this.d();
                }
            }
            else if (n == 1006 && event.modifiers <= 1) {
                if (this.a9 > 0) {
                    if (event.shiftDown()) {
                        this.d(this.a9 - 1);
                    }
                    else {
                        this.d(this.ba = ((this.ba == this.a9) ? (this.a9 - 1) : Math.min(this.ba, this.a9)));
                    }
                    this.d();
                }
                else if (this.a9 == 0 && this.ba == this.a9 && event.modifiers == 0 && this.d != null && this.k) {
                    ((abljemtf)this.d).c = 'R';
                    this.d.requestFocus();
                }
                else if (this.a9 == 0 && this.ba != this.a9 && event.modifiers == 0) {
                    this.ba = this.a9;
                    this.d();
                }
            }
            else if (n == 1007 && event.modifiers <= 1) {
                if (this.a9 < this.av.length()) {
                    if (event.shiftDown()) {
                        this.d(this.a9 + 1);
                    }
                    else {
                        this.d(this.ba = ((this.ba == this.a9) ? (this.a9 + 1) : Math.max(this.ba, this.a9)));
                    }
                    this.d();
                }
                else if (this.a9 == this.av.length() && this.ba == this.a9 && event.modifiers == 0 && this.e != null && this.k) {
                    ((abljemtf)this.e).c = 'L';
                    this.e.requestFocus();
                }
                else if (this.a9 == this.av.length() && this.ba != this.a9 && event.modifiers == 0) {
                    this.ba = this.a9;
                    this.d();
                }
            }
            else {
                if (n != 1025 || event.modifiers != 0) {
                    return super.keyDown(event, n);
                }
                if (this.i != null) {
                    this.i.ed = !this.i.ed;
                    this.g();
                    this.d();
                }
            }
            return true;
        }
        final char[] array = { '\0' };
        if (n < 32 || event.modifiers > 1) {
            final boolean keyDown = super.keyDown(event, n);
            if (n == 10 && event.modifiers == 0 && this.j != null) {
                this.j.action(event, event.arg);
            }
            return keyDown;
        }
        array[0] = (char)n;
        final String s = new String(array);
        if (this.aw) {
            final int n2 = max + ((this.t() && max < this.av.length() && this.a9 == this.ba) ? 1 : 0);
            if (this.x != null) {
                if (this.a(n)) {
                    this.c(min + 1);
                    return true;
                }
                if (n2 > min) {
                    int n3;
                    for (n3 = min; n3 < n2 && this.y[n3]; ++n3) {}
                    if (n3 >= n2) {
                        this.b();
                        return true;
                    }
                }
            }
            this.a(min, n2, s);
            this.b();
            return !this.g.j.z || super.keyDown(event, n);
        }
        return true;
    }
    
    public void requestFocus() {
        super.requestFocus();
    }
    
    public boolean gotFocus(final Event event, final Object o) {
        this.a = true;
        this.b = false;
        this.b(true);
        this.h.a(this, true);
        this.d();
        return super.gotFocus(event, o);
    }
    
    public boolean lostFocus(final Event event, final Object o) {
        if (this.bl) {
            this.b = true;
            this.a = false;
        }
        else {
            this.b = false;
            this.a = false;
            if (this.a9 != this.ba) {
                this.e();
            }
            if (!this.k) {
                this.b(false);
            }
            else {
                this.h.a(this, false);
            }
            this.repaint();
        }
        this.e(this.bl = false);
        return event == null || super.lostFocus(event, o);
    }
    
    protected void processEvent(final AWTEvent awtEvent) {
        abljemeh.a(awtEvent);
        super.processEvent(awtEvent);
    }
    
    public boolean handleEvent(final Event event) {
        if ((event.id == 401 || event.id == 402) && (event.key == 0 || event.key == 65535)) {
            return true;
        }
        if (event.id == 401 || event.id == 403) {
            return abljemeh.a(event) == null || super.handleEvent(event);
        }
        if (event.id == 504) {
            this.g();
            return super.handleEvent(event);
        }
        if (event.modifiers == 4 && event.id == 502) {
            this.c(event.x, event.y);
            return true;
        }
        if (event.modifiers == 0) {
            if (event.id == 501) {
                this.be = true;
                this.requestFocus();
                this.c(this.e(event.x));
                this.e(false);
                return true;
            }
            if (event.id == 502) {
                final int e = this.e(event.x);
                this.be = false;
                if (e != this.a9) {
                    this.d(e);
                    this.e();
                }
                this.repaint();
                return true;
            }
            if (event.id == 506) {
                if (this.be) {
                    if (event.x > this.a8 + this.am) {
                        this.d(Math.min(this.a9 + 1, this.av.length()));
                        this.d();
                    }
                    else if (event.x < this.am) {
                        this.d(Math.max(this.a9 - 1, 0));
                        this.d();
                    }
                    else {
                        final int e2 = this.e(event.x);
                        if (this.a9 != e2) {
                            this.d(e2);
                            this.d();
                        }
                    }
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public void repaint() {
        if (!this.isVisible()) {
            return;
        }
        super.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.getSize().width;
        final int height = this.getSize().height;
        Graphics graphics2 = null;
        Graphics graphics3 = null;
        Graphics graphics4 = null;
        Color color;
        Color color2;
        if (this.aw && this.isEnabled()) {
            color = this.getForeground();
            color2 = this.bn;
            if (this.f) {
                color = this.aq;
                color2 = this.ap;
            }
        }
        else {
            color = this.at;
            color2 = this.as;
        }
        final boolean t = this.t();
        final Font font = this.getFont();
        final Font font2 = this.q ? new_font.a(font.getName(), 1, font.getSize()) : null;
        String s = this.av;
        final int length = s.length();
        if (this.q) {
            final char[] charArray = s.toCharArray();
            for (int i = 0; i < length; ++i) {
                final char c = charArray[i];
                if (c >= '\uf820' && c <= '\uf83f') {
                    charArray[i] = ' ';
                }
            }
            s = new String(charArray);
        }
        if (this.ax) {
            final StringBuffer sb = new StringBuffer(length);
            for (int j = 0; j < length; ++j) {
                sb.append(this.ay);
            }
            s = sb.toString();
        }
        if (this.bf || this.a4 != height || !color.equals(this.a5)) {
            this.az = null;
        }
        if (this.az == null) {
            this.a0 = null;
        }
        if (this.a0 == null || this.a3 != width || this.a4 != height) {
            this.a8 = width - this.am - this.an;
            this.a0 = this.a(width, height);
            if (this.a0 == null) {
                return;
            }
            graphics2 = this.a0.getGraphics();
            graphics2.setFont(font);
            utils.a(graphics2, color2, 0, 0, width, height);
            if (height > 4 && width > 4 && this.ag) {
                graphics2.setColor(this.ac);
                graphics2.drawLine(0, 0, 0, height - 2);
                graphics2.drawLine(0, 0, width - 2, 0);
                graphics2.setColor(this.ad);
                graphics2.drawLine(1, 1, 1, height - 3);
                graphics2.drawLine(1, 1, width - 3, 1);
                graphics2.setColor(this.ae);
                graphics2.drawLine(width - 1, height - 1, width - 1, 0);
                graphics2.drawLine(width - 1, height - 1, 0, height - 1);
                graphics2.setColor(this.af);
                graphics2.drawLine(width - 2, height - 2, width - 2, 1);
                graphics2.drawLine(width - 2, height - 2, 1, height - 2);
            }
            if (this.a8 > 0) {
                final int n = t ? this.a7 : this.ai;
                if (this.bp == null || this.bp.length < length + 2 || this.bp[length + 1] != this.bp[length] + n) {
                    this.az = null;
                }
                if (this.az == null) {
                    final FontMetrics fontMetrics = (font2 != null) ? graphics2.getFontMetrics(font2) : null;
                    final FontMetrics fontMetrics2 = graphics2.getFontMetrics();
                    if (this.bi == -1) {
                        this.a(fontMetrics2);
                        this.h();
                    }
                    if (this.bf) {
                        FontMetrics fontMetrics3 = fontMetrics2;
                        this.bp = new int[length + 2];
                        this.bq = new int[length + 2];
                        int n2 = -1;
                        this.bp[0] = 0;
                        for (int k = 0; k < length; ++k) {
                            this.bq[k] = n2;
                            if (this.q && !this.ax) {
                                final char c2 = (char)(this.av.charAt(k) - '\uf820');
                                if (c2 >= '\0' && c2 < ' ') {
                                    if (this.v != null && c2 < this.v.length && this.v[c2]) {
                                        fontMetrics3 = fontMetrics;
                                    }
                                    this.bq[k] = 32;
                                    n2 = c2;
                                }
                            }
                            this.bp[k + 1] = this.bp[k] + fontMetrics3.charWidth(s.charAt(k));
                        }
                        this.bq[length] = -1;
                        this.bf = false;
                    }
                    this.bp[length + 1] = this.bp[length] + n;
                    this.bq[length + 1] = -1;
                    this.a6 = this.bi + this.bj;
                    this.a2 = (height - this.a6) / 2;
                    final int n3 = this.bp[length + 1];
                    this.az = this.a((n3 == 0) ? 1 : n3, this.a6);
                    if (this.az == null) {
                        return;
                    }
                    graphics3 = this.az.getGraphics();
                    graphics3.setFont(font);
                    utils.a(graphics3, color2, 0, 0, n3, this.a6);
                    graphics3.setColor(color);
                    this.a(graphics3, s, 0, length, 0, this.bi, this.r, font2);
                }
                this.bd = Math.min(this.bd, this.bp[length]);
                final int n4 = this.am + this.bp[this.a9] - this.bd;
                final int n5 = this.bp[length + (this.m ? 0 : 1)];
                if (t && this.a9 == this.ba) {
                    this.bk = this.bp[this.a9 + 1] - this.bp[this.a9];
                    if (this.bk < 1) {
                        this.bk = Math.min(this.ai, this.a8);
                    }
                }
                else {
                    this.bk = Math.min(this.ai, this.a8);
                }
                if (this.bk < 1) {
                    this.bk = 1;
                }
                if (this.a9 == length && n5 >= this.a8) {
                    this.bd = n5 - this.a8;
                }
                else if (n5 <= this.a8) {
                    this.bd = 0;
                }
                else if (this.a3 != width) {
                    int n6;
                    for (n6 = 0; (this.bp[this.a9] - this.bp[n6]) * 2 > this.a8 && n5 - this.bp[n6] > this.a8; ++n6) {}
                    this.bd = this.bp[n6];
                }
                else if (n4 < this.am) {
                    this.bd = this.bp[this.a9];
                }
                else if (n4 + (this.m ? 0 : this.bk) > this.am + this.a8) {
                    this.bd = this.bp[this.a9] - this.a8 + (this.m ? 0 : this.bk);
                }
                else if (n5 - this.bd < this.a8) {
                    this.bd = n5 - this.a8;
                }
                graphics2.setClip(this.am, Math.max(this.a2, 2), this.a8, Math.min(this.a6, height - 4));
                if (length > 0) {
                    graphics2.drawImage(this.az, this.am - this.bd, this.a2, this);
                }
                if ((this.a || this.b) && this.ba != this.a9) {
                    final int min = Math.min(this.ba, this.a9);
                    final int max = Math.max(this.ba, this.a9);
                    final int n7 = this.am + this.bp[min] - this.bd;
                    utils.a(graphics2, this.ap, n7, this.a2, this.am + this.bp[max] - this.bd - n7, this.a6);
                    graphics2.setColor(this.aq);
                    this.a(graphics2, s, min, max, n7, this.a2 + this.bi, false, font2);
                }
                this.a1 = null;
            }
        }
        graphics.drawImage(this.a0, 0, 0, this);
        if (((this.a && this.h.a()) || (!this.a && this.b)) && this.a8 > 0) {
            int n8 = this.am + this.bp[this.a9] - this.bd;
            if (this.m) {
                n8 -= this.bk;
            }
            if (this.a1 == null) {
                if ((t && !this.m) || this.bo) {
                    this.a1 = this.a(this.bk, this.a6);
                    if (this.a1 == null) {
                        return;
                    }
                    graphics4 = this.a1.getGraphics();
                    graphics4.setFont(font);
                    utils.a(graphics4, (this.ba <= this.a9) ? this.ap : color2, 0, 0, this.bk, this.a6);
                    graphics4.setColor((this.ba <= this.a9) ? this.aq : color);
                    if (this.a9 == length) {
                        graphics4.drawString(" ", 0, this.bi);
                    }
                    else {
                        this.a(graphics4, s, this.a9, this.a9 + 1, 0, this.bi, false, font2);
                    }
                }
                else {
                    final int rgb = color2.getRGB();
                    final int rgb2 = this.ap.getRGB();
                    final int[] array = new int[this.bk * this.a6];
                    try {
                        new PixelGrabber(this.a0, n8, this.a2, this.bk, this.a6, array, 0, this.bk).grabPixels(0L);
                    }
                    catch (Throwable t2) {
                        abljem.d("Problem grabbing pixels for coloring cursor");
                        t2.printStackTrace();
                    }
                    for (int l = 0; l < array.length; ++l) {
                        final int n9 = array[l];
                        if (n9 == rgb && !this.m) {
                            array[l] = rgb2;
                        }
                        else {
                            array[l] = (-16777216 + (255 - ((n9 & 0xFF0000) >> 16) << 16) | 255 - ((n9 & 0xFF00) >> 8) << 8 | 255 - (n9 & 0xFF));
                        }
                    }
                    this.a1 = this.a(this.bk, this.a6, array);
                    if (this.a1 == null) {
                        return;
                    }
                }
            }
            graphics.setClip(this.am, Math.max(this.a2, 2), this.a8, Math.min(this.a6, height - 4));
            graphics.drawImage(this.a1, n8, this.a2, this);
        }
        this.a3 = width;
        this.a4 = height;
        this.a5 = color;
        if (graphics2 != null) {
            graphics2.dispose();
        }
        if (graphics3 != null) {
            graphics3.dispose();
        }
        if (graphics4 != null) {
            graphics4.dispose();
        }
        if (this.g.co) {
            System.gc();
        }
    }
    
    public boolean isFocusTraversable() {
        return true;
    }
    
    protected boolean t() {
        return this.i != null && this.i.ed;
    }
    
    protected void b(final boolean c) {
        if (!(this.c = c)) {
            this.h.a(this, false);
            this.repaint();
        }
    }
    
    protected int c(final char o) {
        final int length = this.av.length();
        this.o = o;
        this.a();
        return this.av.length() - length;
    }
    
    public void a(final char[] x, final boolean[] y) {
        if (x.length != this.au || y.length != this.au) {
            abljem.d("setMask lengths " + x.length + "," + y.length + " not equal field length " + this.au);
        }
        this.x = x;
        this.y = y;
        this.aa = 0;
        for (int i = 0; i < this.au; ++i) {
            if (this.y[i]) {
                ++this.aa;
            }
        }
        this.z = new int[this.au + 1];
        int j = this.au;
        int n = this.au - this.aa;
        while (j >= 0) {
            if (j < this.au && !this.y[j]) {
                --n;
            }
            this.z[j] = n;
            --j;
        }
        this.ab = this.au;
        for (int ab = this.au - 1; ab >= 0 && this.x[ab] == ' '; --ab) {
            this.ab = ab;
        }
        this.a9 = 0;
        this.c();
    }
    
    protected boolean a(final int n) {
        if (this.x == null) {
            return false;
        }
        final int n2 = this.n();
        return this.a9 == this.ba && n2 < this.x.length && this.y[n2] && ((char)n == this.x[n2] || n < 48 || n > 57);
    }
    
    public void u() {
        this.s();
        final int ba = this.ba;
        this.ba = this.a9;
        this.d(ba);
    }
    
    public void d(final boolean b) {
        if (this.d == null && this.e == null) {
            this.s();
        }
        else if (b && this.d == null && this.e != null) {
            this.u();
            this.e(true);
        }
    }
    
    public void e(final boolean b) {
        this.f = b;
        for (abljemgt abljemgt = this.e; abljemgt != null; abljemgt = abljemgt.e) {
            if (abljemgt.f != b) {
                abljemgt.f = b;
                abljemgt.d();
            }
        }
    }
    
    public void v() {
        this.az = null;
        this.a0 = null;
        this.a1 = null;
    }
    
    private void a(final Graphics graphics, final String s, final int n, final int n2, int n3, final int n4, final boolean b, final Font font) {
        if (this.q) {
            Color color2;
            final Color color = color2 = graphics.getColor();
            Color s2 = null;
            final Font font2 = graphics.getFont();
            int n5 = -1;
            int n7;
            for (int i = n; i < n2; ++i, n3 += n7) {
                final int n6 = this.bq[i];
                n7 = this.bp[i + 1] - this.bp[i];
                if (n6 == 32) {
                    s2 = this.s;
                    graphics.setFont(font2);
                }
                else if (n6 != n5) {
                    if (font != null) {
                        graphics.setFont((this.v != null && n6 < this.v.length && this.v[n6]) ? font : font2);
                    }
                    color2 = null;
                    if (this.t != null && n6 < this.t.length) {
                        color2 = this.t[n6];
                    }
                    if (color2 == null) {
                        color2 = color;
                    }
                    s2 = null;
                    if (this.u != null && n6 < this.u.length) {
                        s2 = this.u[n6];
                    }
                }
                n5 = n6;
                if (b) {
                    if (s2 != null) {
                        utils.a(graphics, s2, n3, 0, n7, this.a6);
                    }
                    graphics.setColor(color2);
                }
                graphics.drawString(s.substring(i, i + 1), n3, n4);
            }
        }
        else {
            graphics.drawString(s.substring(n, n2), n3, n4);
        }
    }
    
    private boolean b(final int n) {
        if (n == 12) {
            this.a("");
            return true;
        }
        if (n == 8 || n == 127) {
            if (this.aw) {
                boolean b = false;
                int min;
                int max;
                if (n == 8) {
                    min = ((this.ba == this.a9) ? (this.a9 - 1) : Math.min(this.ba, this.a9));
                    max = Math.max(this.ba, this.a9);
                    b = (this.ba == this.a9);
                }
                else {
                    min = Math.min(this.ba, this.a9);
                    max = ((this.ba == this.a9) ? (this.a9 + 1) : Math.max(this.ba, this.a9));
                }
                if (b && min < 0 && this.d != null && this.k) {
                    this.g.fb.a(null, 'F');
                    this.g.fb.e0.a(8);
                    ((abljemtf)this.d).c = 'R';
                    this.d.requestFocus();
                }
                else if (n == 127 && max >= this.av.length() && this.e != null && this.k) {
                    this.a(min, max, "", b);
                }
                else if (min >= 0 && max <= this.av.length()) {
                    this.a(min, max, "", b);
                }
            }
            return true;
        }
        final int min2 = Math.min(this.ba, this.a9);
        final int max2 = Math.max(this.ba, this.a9);
        switch (n) {
            case 1: {
                this.s();
                break;
            }
            case 3: {
                if (abljemgt.b4 != null && min2 != max2) {
                    abljemgt.b4.setContents(new StringSelection(this.av.substring(min2, max2)), null);
                    break;
                }
                break;
            }
            case 22: {
                if (!this.aw || abljemgt.b4 == null) {
                    break;
                }
                final String i = this.i();
                if (this.x == null) {
                    this.a(min2, max2, i);
                    break;
                }
                int n2 = 0;
                for (int n3 = min2, n4 = 0; n3 < this.x.length && n4 < i.length(); ++n3, ++n4) {
                    if (this.y[n3]) {
                        ++n2;
                        if (this.x[n3] != i.charAt(n4) && this.x[n3] != ' ' && i.charAt(n4) != ' ') {
                            this.g.a("Paste did not match fixed field characters", "Mask=" + new String(this.x) + " Paste=" + i);
                            break;
                        }
                    }
                }
                if (this.b(min2, max2, abljema.iv.substring(0, i.length() - n2))) {
                    this.c(min2, min2 + i.length(), i);
                    break;
                }
                break;
            }
            case 24: {
                if (this.aw && abljemgt.b4 != null && min2 != max2) {
                    abljemgt.b4.setContents(new StringSelection(this.av.substring(min2, max2)), null);
                    this.a(min2, max2, "");
                    break;
                }
                break;
            }
            case 26: {
                if (this.x == null) {
                    final int bb = this.bb;
                    this.bc = -1;
                    this.a(this.bb, this.bb + this.bg.length(), this.bh);
                    this.ba = bb;
                    break;
                }
                break;
            }
            default: {
                return false;
            }
        }
        return true;
    }
    
    private boolean a(final String s) {
        return this.a(0, this.av.length(), s);
    }
    
    private boolean a(final int n, final int n2, final String s) {
        return this.a(n, n2, s, false, false, false);
    }
    
    private boolean b(final int n, final int n2, final String s) {
        return this.a(n, n2, s, false, false, true);
    }
    
    private boolean c(final int n, final int n2, final String s) {
        return this.a(n, n2, s, false, true, false);
    }
    
    protected boolean a(final int n, final int n2, final String s, final boolean b) {
        return this.a(n, n2, s, b, false, false);
    }
    
    private boolean a(final int bb, int n, final String bg, final boolean b, final boolean b2, final boolean b3) {
        String bh = this.av.substring(bb, Math.min(n, this.av.length()));
        final boolean b4 = this.bh.length() > 0;
        final boolean b5 = this.bg.length() > 0;
        boolean b6 = bh.length() > 0;
        final boolean b7 = bg.length() > 0;
        final String av = this.av;
        int n2 = bb + bg.length();
        if (this.x == null || b2) {
            if (this.e == null) {
                this.av = String.valueOf(this.av.substring(0, bb)) + bg + this.av.substring(n);
            }
            else {
                int n3 = 0;
                String s = "";
                for (abljemgt e = this; e != null; e = e.e) {
                    final int au = e.au;
                    if (au > 0 && n3 > s.length()) {
                        s = String.valueOf(s) + abljema.iv.substring(0, n3 - s.length());
                    }
                    n3 += au;
                    s = String.valueOf(s) + e.av;
                    if (e.f) {
                        n = s.length();
                    }
                }
                n = Math.min(n, s.length());
                bh = s.substring(bb, n);
                b6 = (bh.length() > 0);
                String s2 = String.valueOf(s.substring(0, bb)) + bg + s.substring(n);
                if (s2.length() > n3) {
                    for (int i = n3; i < s2.length(); ++i) {
                        if (s2.charAt(i) != ' ') {
                            return false;
                        }
                    }
                    s2 = s2.substring(0, n3);
                }
                final int n4 = 0;
                int n5 = Math.min(this.au, s2.length());
                this.av = s2.substring(n4, n5);
                for (abljemgt abljemgt = this.e; abljemgt != null; abljemgt = abljemgt.e) {
                    final int n6 = n5;
                    n5 = Math.min(n6 + abljemgt.au, s2.length());
                    final String substring = s2.substring(n6, n5);
                    if (!substring.equals(abljemgt.av)) {
                        abljemgt.c(substring);
                    }
                }
            }
        }
        else {
            try {
                final String b8 = this.b(this.av);
                this.av = this.d(String.valueOf(b8.substring(0, this.z[bb])) + bg + b8.substring(this.z[n]));
                int n7 = bb;
                int j = bg.length();
                while (j > 0) {
                    if (n7 >= this.x.length) {
                        break;
                    }
                    if (this.y[n7]) {
                        ++n2;
                    }
                    else {
                        --j;
                    }
                    ++n7;
                }
            }
            catch (Throwable t) {
                abljem.d("changeText " + t.toString());
                t.printStackTrace();
                this.av = av;
                return false;
            }
        }
        if (!this.a()) {
            this.av = av;
            return false;
        }
        if (this.bc == this.a9 && (b7 || !b6 || !b5 || b4) && (!b7 || b6 || b5 || !b4) && (!b7 || !b6)) {
            this.bg = String.valueOf(this.bg) + bg;
            if (!b) {
                this.bh = String.valueOf(this.bh) + bh;
            }
            else {
                this.bh = String.valueOf(bh) + this.bh;
                --this.bb;
            }
        }
        else {
            this.bb = bb;
            this.bg = bg;
            this.bh = bh;
        }
        this.d(n2);
        this.d(Math.min(this.a9, this.av.length()));
        this.ba = this.a9;
        this.bc = this.a9;
        this.bf = true;
        if (!b3) {
            this.repaint();
        }
        return true;
    }
    
    private String b(final String s) {
        char[] charArray;
        char[] array;
        int n;
        int n2;
        for (charArray = s.toCharArray(), array = new char[charArray.length], n = 0, n2 = 0; n < charArray.length && n2 < array.length; ++n) {
            if (!this.y[n]) {
                array[n2++] = charArray[n];
            }
        }
        return new String(array, 0, n2);
    }
    
    private String d(final String s) {
        final char[] charArray = s.toCharArray();
        final char[] array = new char[Math.max(this.ab, charArray.length + this.aa)];
        int i = 0;
        int n = 0;
        while (i < array.length) {
            if (n >= charArray.length) {
                break;
            }
            if (i < this.x.length && this.y[i]) {
                array[i] = this.x[i];
            }
            else {
                array[i] = charArray[n++];
            }
            ++i;
        }
        while (i < this.ab) {
            array[i] = this.x[i];
            ++i;
        }
        return new String(array, 0, i);
    }
    
    private void a(final boolean b) {
        if (b != this.isEnabled()) {
            this.repaint();
        }
    }
    
    private boolean a() {
        if (this.o == 'N') {
            return true;
        }
        int n = this.au - this.av.length();
        if (n < 0 && this.l && (this.av.indexOf(45) >= 0 || this.av.indexOf(43) >= 0)) {
            ++n;
        }
        if (n == 0) {
            return true;
        }
        if (n < 0) {
            char[] charArray;
            int length;
            for (charArray = this.av.toCharArray(), length = charArray.length; length > 0 && n < 0 && charArray[length - 1] == ' '; --length, ++n) {}
            if (n < 0) {
                return false;
            }
            this.av = new String(charArray, 0, length);
            return this.bf = true;
        }
        else {
            if (this.o == 'T') {
                return true;
            }
            if (n > 0) {
                final StringBuffer sb = new StringBuffer(n);
                for (int i = 0; i < n; ++i) {
                    sb.append(' ');
                }
                this.av = String.valueOf(this.av) + sb.toString();
                return this.bf = true;
            }
            return false;
        }
    }
    
    private void b() {
        this.c(true);
    }
    
    private void c() {
        this.c(false);
    }
    
    private void c(final boolean b) {
        if (this.x == null) {
            return;
        }
        if (this.a9 < 0 || this.a9 >= this.x.length) {
            return;
        }
        if (!this.y[this.a9]) {
            return;
        }
        int a9;
        for (a9 = this.a9; a9 < this.x.length && this.y[a9]; ++a9) {}
        this.a(a9, b);
    }
    
    private void c(final int n) {
        this.a(n, true);
    }
    
    private void a(final int ba, final boolean b) {
        this.d(this.ba = ba);
        this.g(b);
    }
    
    private void d() {
        this.g(true);
    }
    
    private void e() {
        this.g(false);
    }
    
    private void g(final boolean b) {
        this.a0 = null;
        if (b) {
            this.repaint();
        }
    }
    
    private void d(final int a9) {
        this.a9 = a9;
        if (this.o == 'N') {
            this.m = false;
        }
        else {
            this.m = (this.a9 >= this.au);
        }
    }
    
    private int e(final int n) {
        final int n2 = n + this.bd - this.am;
        if (n <= 0) {
            return 0;
        }
        try {
            if (this.t()) {
                for (int i = 1; i <= this.av.length(); ++i) {
                    if (this.bp[i] >= n2) {
                        return i - 1;
                    }
                }
            }
            else {
                for (int j = 0; j < this.av.length(); ++j) {
                    if (this.bp[j] + this.bp[j + 1] >= n2 * 2) {
                        return j;
                    }
                }
            }
        }
        catch (Throwable t) {
            abljem.d("getCharacterIndex forced to zero");
            return 0;
        }
        return this.av.length();
    }
    
    private void f() {
        final Color foreground = this.getForeground();
        final Color bn = this.bn;
        if (!this.ar) {
            return;
        }
        this.as = bn;
        this.at = new Color((foreground.getRed() + bn.getRed()) / 2, (foreground.getGreen() + bn.getGreen()) / 2, (foreground.getBlue() + bn.getBlue()) / 2);
    }
    
    private void g() {
        this.setCursor(new Cursor(2));
    }
    
    private void a(final FontMetrics fontMetrics) {
        try {
            final String string = fontMetrics.getFont().toString();
            int[] b = abljemgt.b8.get(string);
            if (b == null) {
                b = this.b(fontMetrics);
                abljemgt.b8.put(string, b);
            }
            this.bi = b[0];
            this.bj = b[1];
            if (this.k && !(this.getParent() instanceof CommandStatusPanel)) {
                this.bj += this.g.ad;
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
            this.bi = fontMetrics.getAscent();
            this.bj = fontMetrics.getDescent();
            abljem.d("Reverted to standard ascent=" + this.bi + " descent=" + this.bj);
        }
    }
    
    private int[] b(final FontMetrics fontMetrics) {
        final Font font = fontMetrics.getFont();
        final int stringWidth = fontMetrics.stringWidth(abljemgt.b7);
        final int ascent = fontMetrics.getAscent();
        final int descent = fontMetrics.getDescent();
        int n = ascent;
        int n2 = descent;
        final int n3 = ascent + descent;
        final Image a = this.a(stringWidth, n3);
        final Graphics graphics = a.getGraphics();
        graphics.setFont(font);
        final int n4 = ascent * stringWidth;
        final int n5 = n3 * stringWidth;
        final int[] array = new int[n5];
        utils.a(graphics, Color.white, 0, 0, stringWidth, n3);
        graphics.setColor(Color.black);
        graphics.drawString(abljemgt.b7, 0, ascent);
        final PixelGrabber pixelGrabber = new PixelGrabber(a, 0, 0, stringWidth, n3, array, 0, stringWidth);
        try {
            pixelGrabber.grabPixels(0L);
            int i;
            for (i = 0; i < n4; ++i) {
                if ((array[i] & 0xFFFFFF) == 0x0) {
                    n = ascent - i / stringWidth;
                    break;
                }
            }
            if (i == n4) {
                n = ascent;
            }
            int j;
            for (j = n5 - 1; j >= n4; --j) {
                if ((array[j] & 0xFFFFFF) == 0x0) {
                    n2 = j / stringWidth - ascent + 1;
                    break;
                }
            }
            if (j == n4 - 1) {
                n2 = descent;
            }
            this.a7 = fontMetrics.stringWidth(" ");
        }
        catch (Throwable t) {
            abljem.d("Falling back to standard ascent=" + ascent + " descent=" + descent);
            t.printStackTrace();
            n = ascent;
            n2 = descent;
        }
        finally {
            graphics.dispose();
        }
        return new int[] { n, n2 };
    }
    
    private void h() {
        if (this.o == 'N') {
            this.am = this.al;
            this.an = this.al;
        }
        else {
            final int min = Math.min(2 * this.al, this.ao);
            this.am = (min + 1) / 2;
            this.an = min - this.am;
        }
    }
    
    private Image a(final int n, final int n2) {
        Image image;
        try {
            image = this.createImage(n, n2);
        }
        catch (Throwable t) {
            try {
                if (this.getPeer() == null) {
                    return null;
                }
            }
            catch (Throwable t2) {}
            abljem.d("abljemgt createImage(" + n + "," + n2 + ") failed");
            t.printStackTrace();
            return null;
        }
        return image;
    }
    
    private Image a(final int n, final int n2, final int[] array) {
        try {
            return this.createImage(new MemoryImageSource(n, n2, array, 0, n));
        }
        catch (Throwable t) {
            abljem.d("abljemgt createImage(" + n + "," + n2 + ",pixlen=" + ((array == null) ? -1 : array.length) + ") failed");
            t.printStackTrace();
            return null;
        }
    }
    
    private String i() {
        String s = "";
        try {
            final Transferable contents = abljemgt.b4.getContents(this);
            if (contents != null) {
                s = (String)contents.getTransferData(DataFlavor.stringFlavor);
            }
        }
        catch (Throwable t) {
            s = "";
        }
        return s;
    }
    
    private Window j() {
        Component parent = this;
        for (int i = 0; i < 1000; ++i) {
            if (parent instanceof Window) {
                return (Window)parent;
            }
            parent = parent.getParent();
        }
        return null;
    }
    
    private void c(final int n, final int n2) {
        try {
            abljemgt.bs.remove(abljemgt.br);
        }
        catch (Throwable t) {}
        if (this.k) {
            ((abljemtf)this).c = ' ';
        }
        final Window j = this.j();
        final Font font = this.g.j.g8.l.getFont();
        final Color background = this.g.j.g8.getBackground();
        abljemgt.bs = j;
        abljemgt.bt = this;
        abljemgt.br = new abljempu(this.g, j, 0, this);
        abljemgt.br.a = this;
        abljemgt.br.setBackground(background);
        abljemgt.br.setFont(font);
        abljemgt.bu = new MenuItem("Undo");
        abljemgt.bv = new MenuItem("-");
        abljemgt.bw = new MenuItem("Cut");
        abljemgt.bx = new MenuItem("Copy");
        abljemgt.by = new MenuItem("Paste");
        abljemgt.bz = new MenuItem("Delete");
        abljemgt.b0 = new MenuItem("-");
        abljemgt.b1 = new MenuItem("Select All");
        abljemgt.b2 = new MenuItem("-");
        abljemgt.b3 = new MenuItem(this.g.fb.e0.h ? "Stop Recording Macro" : "Macro");
        this.k();
        abljemgt.br.a(abljemgt.bu);
        abljemgt.br.a(abljemgt.bv);
        if (abljemgt.b4 != null) {
            abljemgt.br.a(abljemgt.bx);
            abljemgt.br.a(abljemgt.bw);
            abljemgt.br.a(abljemgt.by);
        }
        abljemgt.br.a(abljemgt.bz);
        abljemgt.br.a(abljemgt.b0);
        abljemgt.br.a(abljemgt.b1);
        if (this.k) {
            abljemgt.br.a(abljemgt.b2);
            abljemgt.br.a(abljemgt.b3);
        }
        this.bl = true;
        abljemgt.br.a(this, n, n2);
    }
    
    private void k() {
        final boolean[] array = new boolean[6];
        final boolean b = this.ba != this.a9;
        final boolean enabled = this.av.length() > 0;
        final boolean b2 = this.bg.length() > 0 || this.bh.length() > 0;
        final boolean b3 = this.i().length() > 0;
        abljemgt.bu.setEnabled(this.aw && b2 && this.x == null);
        abljemgt.bw.setEnabled(this.aw && b && abljemgt.b4 != null);
        abljemgt.bx.setEnabled(b && abljemgt.b4 != null);
        abljemgt.by.setEnabled(this.aw && b3 && abljemgt.b4 != null);
        abljemgt.bz.setEnabled(this.aw && b);
        abljemgt.b1.setEnabled(enabled);
        abljemgt.b3.setEnabled(this.k);
    }
    
    private void l() {
        BlinkerThread blinkerThread = (BlinkerThread)this.g.j.aa;
        this.bo = this.g.j.ac;
        if (blinkerThread == null) {
            blinkerThread = new BlinkerThread(this.g.l, this.g.j.ab);
            (this.g.j.aa = blinkerThread).setDaemon(true);
            blinkerThread.start();
        }
        this.h = blinkerThread;
    }
    
    public static void f(final boolean b) {
        if (!b) {
            abljemgt.b4 = null;
            return;
        }
        try {
            abljemgt.b4 = Toolkit.getDefaultToolkit().getSystemClipboard();
        }
        catch (Throwable t) {
            abljemgt.b4 = null;
        }
    }
    
    public static Color a(final Color color) {
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        if (red == 0 && green == 0 && blue == 0) {
            return new Color(abljemgt.ca, abljemgt.ca, abljemgt.ca);
        }
        return new Color(Math.min((int)(red / abljemgt.b9), 255), Math.min((int)(green / abljemgt.b9), 255), Math.min((int)(blue / abljemgt.b9), 255));
    }
    
    static {
        abljemgt.b5 = 500;
        abljemgt.b6 = 500;
        abljemgt.b9 = 0.7;
        abljemgt.ca = (int)(1.0 / (1.0 - abljemgt.b9));
        abljemgt.b7 = "\u00c9!@$^&()QW{}|\"ZM?`0gqty[]\\fjkl;'b/_";
        abljemgt.b8 = new Hashtable();
    }
    
    static class BlinkerThread extends Thread
    {
        private boolean a;
        private boolean b;
        private abljemgt c;
        private Object d;
        
        BlinkerThread(final String s, final boolean a) {
            super(String.valueOf(s) + "blinker");
            this.a = true;
            this.b = true;
            this.d = new Object();
            this.a = a;
        }
        
        boolean a() {
            return this.b;
        }
        
        void a(final abljemgt c, final boolean b) {
            synchronized (this.d) {
                if (b) {
                    this.c = c;
                }
                else if (this.c == c) {
                    this.c = null;
                }
            }
            // monitorexit(this.d)
        }
        
        public void run() {
            if (!this.a) {
                this.b = true;
                return;
            }
            while (true) {
                try {
                    Thread.sleep(this.b ? abljemgt.b5 : abljemgt.b6);
                }
                catch (InterruptedException ex) {}
                this.b = !this.b;
                if (this.c != null) {
                    this.c.repaint();
                }
            }
        }
    }
}
