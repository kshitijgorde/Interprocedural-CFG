import java.awt.event.MouseEvent;
import java.util.StringTokenizer;
import java.util.Vector;
import java.awt.image.ImageObserver;
import java.applet.AudioClip;
import java.util.Hashtable;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BluffaloBill extends Applet
{
    public static final int sa = 5;
    public static final int ta = 1;
    public static final int ua = 2;
    public static final int va = 4;
    public static final int wa = 8;
    public static final int xa = 16;
    public static final int ya = 32;
    Image za;
    Graphics Aa;
    Dimension size;
    FontMetrics ia;
    Color Ba;
    Color Ca;
    Color Da;
    Font Ea;
    String[][] Fa;
    boolean Ga;
    boolean Ha;
    boolean Ia;
    boolean Ja;
    int Ka;
    int La;
    int GWc;
    int HWc;
    int IWc;
    int JWc;
    Image KWc;
    Image ka;
    Image ja;
    Image Y;
    Rectangle[] LWc;
    Rectangle MWc;
    Rectangle NWc;
    volatile[] OWc;
    volatile PWc;
    while QWc;
    _ RWc;
    a SWc;
    b TWc;
    Hashtable[] UWc;
    c bb;
    d VWc;
    AudioClip WWc;
    AudioClip XWc;
    AudioClip YWc;
    AudioClip ZWc;
    AudioClip _Xc;
    private static String Ma = "\u8ee2\u8eea\u8efc\u8efc\u8eee\u8ee8\u8eea\u8ee9\u8ee6\u8ee3\u8eea";
    private static String Na = "\u8ee2\u8eea\u8efc\u8efc\u8eee\u8ee8\u8eea\u8efc\u8ed0\u8eea\u8ee1\u8ea1\u8eeb\u8eee\u8efb";
    private static String Oa = "\u8efc\u8ee6\u8ee3\u8eea\u8ee1\u8efb\u8ea1\u8eee\u8efa";
    private static String Pa = "\u8eeb\u8ee6\u8efc\u8eec\u8eee\u8efd\u8eeb\u8ea1\u8eee\u8efa";
    private static String Ua = "\u8eec\u8eee\u8efc\u8ee7\u8efd\u8eea\u8ee8\u8ea1\u8eee\u8efa";
    private static String Va = "\u8eec\u8ee0\u8ee1\u8ee8\u8efd\u8eee\u8efb\u8ea1\u8eee\u8efa";
    private static String Wa = "\u8eec\u8efa\u8eec\u8ee4\u8ee0\u8ee0\u8ea1\u8eee\u8efa";
    private static String eb = "\u8eed\u8eee\u8eec\u8ee4\u8ecc\u8ee0\u8ee3\u8ee0\u8efd";
    private static String fb = "\u8ee9\u8ee0\u8ee1\u8efb\u8ecc\u8ee0\u8ee3\u8ee0\u8efd";
    private static String gb = "\u8ece\u8efd\u8ee6\u8eee\u8ee3";
    private static String hb = "\u8efc\u8efb\u8eee\u8efd\u8efb\u8efa\u8eff\u8ed0\u8ee2\u8efc\u8ee8";
    private static String ib = "\u8eed\u8ee3\u8efa\u8ee9\u8ee9\u8eee\u8ee3\u8ee0\u8ed0\u8eed\u8ee8\u8ea1\u8ee8\u8ee6\u8ee9";
    private static String jb = "\u8eed\u8eed\u8eec\u8eee\u8efd\u8eeb\u8ea1\u8ee8\u8ee6\u8ee9";
    private static String kb = "\u8eed\u8eed\u8eec\u8eee\u8efd\u8eeb\u8ebd\u8ea1\u8ee8\u8ee6\u8ee9";
    private static String lb = "\u8eec\u8ee0\u8ee6\u8ee1\u8ea1\u8ee8\u8ee6\u8ee9";
    private static String mb = "\u8eed\u8ee3\u8efa\u8ee9\u8ee9\u8eee\u8ee3\u8ee0\u8ea1\u8eeb\u8eee\u8efb";
    private static String nb = "\u8ee8\u8eea\u8ee1\u8eea\u8efd\u8eee\u8ee3";
    private static String ob = "\u8efd\u8ee0\u8efa\u8ee1\u8eeb\u8efc";
    private static String pb = "\u8ee6\u8ee3\u8ee3\u8eea\u8ee8\u8eee\u8ee3\u8eaf\u8ef9\u8eee\u8ee3\u8efa\u8eea\u8eaf\u8ee6\u8ee1\u8eaf\u8ee8\u8eea\u8ee1\u8eea\u8efd\u8eee\u8ee3\u8eaf\u8efc\u8eea\u8eec\u8efb\u8ee6\u8ee0\u8ee1";
    private static String qb = "\u8efd\u8ee0\u8efa\u8ee1\u8eeb";
    private static String rb = "\u8eea\u8ef7\u8eff\u8ee3\u8eee\u8ee1\u8eee\u8efb\u8ee6\u8ee0\u8ee1\u8efc";
    private static String sb = "\u8eab\u8eea\u8ef7\u8eff\u8ee3\u8eee\u8ee1\u8eee\u8efb\u8ee6\u8ee0\u8ee1";
    private static String tb = "\u8efd\u8eea\u8eee\u8eeb\u8ee6\u8ee1\u8ee8\u8eaf";
    private static String _ = "\u8eaf\u8ee7\u8eee\u8efc\u8eaf\u8ee9\u8eee\u8ee6\u8ee3\u8eea\u8eeb";
    private static String a = "\u8eac";
    private static String b = "\u8ecd\u8eca\u8edb";
    private static String c = "\u8ec9\u8ec0\u8ec3\u8ecb";
    private static String d = "\u8ee8\u8eee\u8ee2\u8ee0\u8ef9\u8eea\u8efd\u8ed0\u8ee2\u8efc\u8ee8";
    private static String e = "\u8eeb\u8ee6\u8efc\u8eec\u8eee\u8efd\u8eeb\u8ed0\u8ee2\u8efc\u8ee8";
    private static String f = "\u8eff\u8ee9\u8ee0\u8ee3\u8eeb\u8eea\u8eeb\u8ed0\u8ee2\u8efc\u8ee8";
    private static String g = "\u8efc\u8efb\u8ee6\u8ee3\u8ee3\u8ee6\u8ee1\u8ed0\u8ee2\u8efc\u8ee8";
    private static String h = "\u8eed\u8ee6\u8ee3\u8ee3\u8ee9\u8ee3\u8eeb\u8ed0\u8ee2\u8efc\u8ee8";
    private static String i = "\u8ef8\u8ee6\u8ee1\u8ee1\u8ee6\u8ee1\u8ee8\u8ed0\u8ee2\u8efc\u8ee8";
    private static String j = "\u8eed\u8ee3\u8efa\u8ee9\u8ee9\u8eee\u8ee3\u8ed0\u8ee2\u8efc\u8ee8";
    
    public void init() {
        String s = this.getParameter(BluffaloBill.Ma);
        if (s == null) {
            s = BluffaloBill.Na;
        }
        this.bb = new c(this.getCodeBase(), s);
        this.VWc = new d(this, this.bb);
        this.WWc = this.VWc._(BluffaloBill.Oa);
        this.XWc = this.VWc._(BluffaloBill.Pa);
        this.YWc = this.VWc._(BluffaloBill.Ua);
        this.ZWc = this.VWc._(BluffaloBill.Va);
        this._Xc = this.VWc._(BluffaloBill.Wa);
        this.size = this.getSize();
        this.Ba = this.VWc.a(BluffaloBill.eb, Color.white);
        this.Ca = this.VWc.a(BluffaloBill.fb, Color.black);
        this.setBackground(this.Ba);
        this.setFont(new Font(BluffaloBill.gb, 1, 12));
        this.ia = this.getFontMetrics(this.getFont());
    }
    
    private boolean j() {
        if (this.VWc.a() && !this.VWc._(d.Za)) {
            this.VWc.b(true);
            return false;
        }
        if (!this.k() || !this.l()) {
            return false;
        }
        this.za = this.createImage(this.size.width, this.size.height);
        this.Aa = this.za.getGraphics();
        this.ia = this.getFontMetrics(new Font(BluffaloBill.gb, 0, 12));
        this.setBackground(this.Ba);
        this.GWc = this.ja.getWidth(this);
        this.HWc = this.ja.getHeight(this);
        this.LWc = new Rectangle[5];
        for (int i = 0; i < this.LWc.length; ++i) {
            this.LWc[i] = new Rectangle(this.IWc + i * this.GWc, 240, this.GWc, 18);
        }
        (this.QWc = new while(new Rectangle(this.IWc, 270, 570, 20))).a(this.bb.d(BluffaloBill.hb));
        this.RWc = new _(this.Y, new Rectangle(this.IWc, 300, 95, 40));
        this.SWc = new a(new Rectangle(487, 300, 95, 40));
        this.TWc = new b(new Rectangle(0, this.size.height - 60, this.size.width, 60));
        this.MWc = new Rectangle(130, 312, 40, 20);
        this.NWc = new Rectangle(430, 312, 40, 20);
        this.Ka = -1;
        this.La = 1;
        this._();
        this.enableEvents(16L);
        return !this.VWc.g();
    }
    
    private boolean l() {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.KWc = this.getImage(this.getCodeBase(), BluffaloBill.ib));
        vector.addElement(this.ka = this.getImage(this.getCodeBase(), BluffaloBill.jb));
        vector.addElement(this.ja = this.getImage(this.getCodeBase(), BluffaloBill.kb));
        vector.addElement(this.Y = this.getImage(this.getCodeBase(), BluffaloBill.lb));
        this.VWc._(vector, 0);
        return !this.VWc.g();
    }
    
    private boolean k() {
        final String mb = BluffaloBill.mb;
        final e e = new e();
        if (e.a(this.getCodeBase(), mb)) {
            int int1;
            try {
                int1 = Integer.parseInt(e.b(BluffaloBill.nb, BluffaloBill.ob));
            }
            catch (NumberFormatException ex) {
                return this.VWc.n(BluffaloBill.pb);
            }
            this.Fa = new String[int1][5];
            for (int i = 0; i < int1; ++i) {
                this.Fa[i] = e.b(BluffaloBill.qb + (i + 1));
            }
            final String[] b = e.b(BluffaloBill.rb);
            this.UWc = new Hashtable[int1];
            for (int j = 0; j < b.length; ++j) {
                final Hashtable<String, String> hashtable = new Hashtable<String, String>(1);
                hashtable.put(BluffaloBill.sb, b[j]);
                this.UWc[j] = hashtable;
            }
            return true;
        }
        return this.VWc.n(BluffaloBill.tb + mb + BluffaloBill._);
    }
    
    private void a() {
        this.OWc = new volatile[5];
        final Rectangle rectangle = new Rectangle(this.IWc, this.JWc, this.GWc, this.HWc);
        for (int i = 0; i < this.OWc.length; ++i) {
            final String s = this.Fa[this.Ka][i];
            final StringTokenizer stringTokenizer = new StringTokenizer(s, BluffaloBill.a);
            final int[] array = new int[3];
            if (stringTokenizer.countTokens() == 3) {
                array[0] = 1;
                array[array[1] = 2] = 1;
            }
            else if (s.startsWith(BluffaloBill.a)) {
                array[0] = 2;
                array[1] = 1;
            }
            else {
                array[array[0] = 1] = 2;
            }
            final String[] array2 = new String[3];
            for (int n = 0; n < array2.length && stringTokenizer.hasMoreTokens(); ++n) {
                array2[n] = stringTokenizer.nextToken().trim();
            }
            this.OWc[i] = new volatile(this.ja, this.ka, array2, array, rectangle, i != 0);
            final Rectangle rectangle2 = rectangle;
            rectangle2.x += this.GWc;
        }
    }
    
    private void _(final volatile[] array) {
        if (this.OWc.length > 1) {
            for (int i = 0; i < array.length; ++i) {
                int j;
                do {
                    j = (int)(Math.random() * array.length);
                } while (j == i);
                final volatile volatile1 = array[i];
                array[i] = array[j];
                array[j] = volatile1;
                array[i].b(this.IWc + i * this.GWc);
                array[j].b(this.IWc + j * this.GWc);
            }
        }
    }
    
    private void _() {
        ++this.Ka;
        if (this.Ka >= 5) {
            this.La = 32;
            return;
        }
        this.a();
        this._(this.OWc);
        this.TWc._(this.OWc[0].g());
        this.RWc.b();
        if (this.WWc != null) {
            this.WWc.stop();
            this.WWc.loop();
        }
    }
    
    private void a(final Graphics graphics) {
        this.VWc.b(graphics);
        final String a = this.bb.a();
        graphics.setColor(this.Ca);
        graphics.drawString(a, this.VWc.a(a, true, graphics), this.VWc.a(a, false, graphics));
        if (this.j()) {
            this.Ga = true;
            this.VWc.b(graphics);
            this.repaint();
            return;
        }
        this.VWc.b(graphics);
        String s;
        if (this.VWc._()) {
            s = d.Xa;
        }
        else {
            s = this.bb.b();
        }
        graphics.setColor(this.Ca);
        graphics.drawString(s, this.VWc.a(s, true, graphics), this.VWc.a(s, false, graphics));
    }
    
    public void update(final Graphics graphics) {
        this.Aa.drawImage(this.KWc, 0, 0, this);
        for (int i = 0; i < this.OWc.length; ++i) {
            this.OWc[i]._(this.Aa);
            if (this.OWc[i].h()) {
                this.Aa.setColor(this.Da);
                this.Aa.fillRect(this.LWc[i].x, this.LWc[i].y, this.LWc[i].width, this.LWc[i].height);
            }
        }
        this.QWc._(this.Aa);
        this.RWc._(this.Aa);
        this.SWc._(this.Aa);
        if (this.La != 1) {
            this.TWc._(this.Aa);
        }
        this.Aa.setColor(Color.black);
        this.Aa.setFont(this.Ea);
        this.Aa.drawString(BluffaloBill.b, 141, 326);
        this.Aa.drawString(BluffaloBill.c, 436, 326);
        graphics.drawImage(this.za, 0, 0, this);
        if (this.VWc.g()) {
            this.showStatus(this.bb._());
        }
    }
    
    public void paint(final Graphics graphics) {
        if (!this.Ga) {
            this.a(graphics);
            return;
        }
        this.update(graphics);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.b(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    public boolean b(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.La == 32) {
            return true;
        }
        if (this.MWc.contains(n, n2)) {
            switch (this.La) {
                case 16: {
                    this._();
                    if (this.La == 32) {
                        this.QWc.a(this.bb.d(BluffaloBill.d));
                        this.TWc._((String)null);
                        this.i();
                        break;
                    }
                }
                case 1: {
                    this.j();
                }
                case 4: {
                    this.RWc.b();
                    d.b(this.YWc);
                }
                case 8: {
                    this.La = 2;
                    this.QWc.a(this.bb.d(BluffaloBill.e));
                    break;
                }
            }
            this.repaint();
            return true;
        }
        if ((0xC & this.La) != 0x0 && this.NWc.contains(n, n2)) {
            if (this.a(2)) {
                this.SWc.a(this.RWc.b());
            }
            else {
                this.SWc._(this.RWc.b());
            }
            this.QWc.a(this.bb._(BluffaloBill.f, this.UWc[this.Ka]));
            this.i();
            for (int i = 0; i < this.OWc.length; ++i) {
                if (!this.OWc[i].i()) {
                    this.OWc[i]._(false);
                    break;
                }
            }
            this.RWc.reset();
            this.TWc._((String)null);
            d.b(this._Xc);
            this.La = 16;
            this.repaint();
            return true;
        }
        if (this.La == 2) {
            this.PWc = null;
            for (int j = 0; j < this.OWc.length; ++j) {
                if (this.LWc[j].contains(n, n2)) {
                    (this.PWc = this.OWc[j])._(true);
                    this.repaint();
                    break;
                }
            }
            if (this.PWc != null) {
                if (this.PWc.i()) {
                    this.QWc.a(this.bb.d(BluffaloBill.g));
                    if (this.a(2)) {
                        this.QWc.a(this.bb.d(BluffaloBill.h));
                        this.La = 8;
                        d.b(this.XWc);
                    }
                    else if (this.a(1)) {
                        this.QWc.a(this.bb._(BluffaloBill.i, this.UWc[this.Ka]));
                        this.SWc.a(this.RWc.b() * 2);
                        d.b(this.ZWc);
                        this.TWc._((String)null);
                        this.RWc.reset();
                        this.La = 16;
                    }
                    else {
                        this.La = 4;
                        this.RWc.b();
                        d.b(this.XWc);
                    }
                }
                else {
                    this.QWc.a(this.bb._(BluffaloBill.j, this.UWc[this.Ka]));
                    if (!this.a(1)) {
                        this.SWc._(this.RWc.b());
                    }
                    this.RWc.reset();
                    this.i();
                    d.b(this._Xc);
                    this.TWc._((String)null);
                    this.PWc._(false);
                    this.La = 16;
                }
            }
        }
        this.repaint();
        return true;
    }
    
    private void i() {
        for (int i = 0; i < this.OWc.length; ++i) {
            this.OWc[i]._(true);
        }
    }
    
    private void j() {
        for (int i = 0; i < this.OWc.length; ++i) {
            this.OWc[i]._(false);
        }
    }
    
    private boolean a(final int n) {
        int n2 = 0;
        for (int n3 = 0; n3 < this.OWc.length && (this.OWc[n3].h() || ++n2 <= n); ++n3) {}
        return n2 == n;
    }
    
    public void destroy() {
        d.a((Object)this.WWc);
        d.a((Object)this.XWc);
        d.a((Object)this.YWc);
        d.a((Object)this.ZWc);
        d.a((Object)this._Xc);
    }
    
    public BluffaloBill() {
        this.Da = new Color(3381606);
        this.Ea = new Font(BluffaloBill.gb, 1, 11);
        this.IWc = 14;
        this.JWc = 75;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF8E8F);
        }
        return new String(array);
    }
    
    static {
        BluffaloBill.Ma = a(BluffaloBill.Ma);
        BluffaloBill.Na = a(BluffaloBill.Na);
        BluffaloBill.Oa = a(BluffaloBill.Oa);
        BluffaloBill.Pa = a(BluffaloBill.Pa);
        BluffaloBill.Ua = a(BluffaloBill.Ua);
        BluffaloBill.Va = a(BluffaloBill.Va);
        BluffaloBill.Wa = a(BluffaloBill.Wa);
        BluffaloBill.eb = a(BluffaloBill.eb);
        BluffaloBill.fb = a(BluffaloBill.fb);
        BluffaloBill.gb = a(BluffaloBill.gb);
        BluffaloBill.hb = a(BluffaloBill.hb);
        BluffaloBill.ib = a(BluffaloBill.ib);
        BluffaloBill.jb = a(BluffaloBill.jb);
        BluffaloBill.kb = a(BluffaloBill.kb);
        BluffaloBill.lb = a(BluffaloBill.lb);
        BluffaloBill.mb = a(BluffaloBill.mb);
        BluffaloBill.nb = a(BluffaloBill.nb);
        BluffaloBill.ob = a(BluffaloBill.ob);
        BluffaloBill.pb = a(BluffaloBill.pb);
        BluffaloBill.qb = a(BluffaloBill.qb);
        BluffaloBill.rb = a(BluffaloBill.rb);
        BluffaloBill.sb = a(BluffaloBill.sb);
        BluffaloBill.tb = a(BluffaloBill.tb);
        BluffaloBill._ = a(BluffaloBill._);
        BluffaloBill.a = a(BluffaloBill.a);
        BluffaloBill.b = a(BluffaloBill.b);
        BluffaloBill.c = a(BluffaloBill.c);
        BluffaloBill.d = a(BluffaloBill.d);
        BluffaloBill.e = a(BluffaloBill.e);
        BluffaloBill.f = a(BluffaloBill.f);
        BluffaloBill.g = a(BluffaloBill.g);
        BluffaloBill.h = a(BluffaloBill.h);
        BluffaloBill.i = a(BluffaloBill.i);
        BluffaloBill.j = a(BluffaloBill.j);
    }
}
