import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Rectangle;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.applet.AudioClip;
import java.util.Hashtable;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Crashpad extends Applet implements Runnable
{
    public static final int Cyb = 0;
    public static final int Dyb = 1;
    public static final int Eyb = 2;
    public static final int Fyb = 3;
    public static final int Gyb = 4;
    public static final int Hyb = 5;
    private String Iyb;
    Image Jyb;
    Image Kyb;
    Graphics Lyb;
    Graphics Myb;
    Dimension size;
    FontMetrics Nyb;
    Font Oyb;
    Font Pyb;
    Thread Qyb;
    int Ryb;
    int Syb;
    int Tyb;
    int Uyb;
    Color Vyb;
    Color Wyb;
    Color o;
    String Xyb;
    String Yyb;
    boolean Zyb;
    boolean _zb;
    boolean azb;
    f[][] bzb;
    g czb;
    g dzb;
    h ezb;
    i fzb;
    j gzb;
    k hzb;
    l[] izb;
    m[] jzb;
    n[] kzb;
    Image[] lzb;
    Image[] mzb;
    Image[] nzb;
    Image[] ozb;
    Image pzb;
    Image qzb;
    Image rzb;
    Image szb;
    Image tzb;
    Hashtable uzb;
    AudioClip vzb;
    AudioClip wzb;
    AudioClip xzb;
    AudioClip yzb;
    AudioClip zzb;
    AudioClip Azb;
    AudioClip Bzb;
    o za;
    p rb;
    private static String w = "\u87d2\u87da\u87cc\u87cc\u87de\u87d8\u87da\u87d9\u87d6\u87d3\u87da";
    private static String x = "\u87d2\u87da\u87cc\u87cc\u87de\u87d8\u87da\u87cc\u87e0\u87da\u87d1\u8791\u87db\u87de\u87cb";
    private static String y = "\u87cc\u87dc\u87d0\u87cd\u87da\u87ec\u87d0\u87ca\u87d1\u87db";
    private static String z = "\u87d3\u87d0\u87cc\u87da\u87ec\u87d0\u87ca\u87d1\u87db";
    private static String A = "\u87d3\u87da\u87c9\u87da\u87d3\u87ec\u87d0\u87ca\u87d1\u87db";
    private static String B = "\u87dc\u87d3\u87d6\u87dc\u87d4\u87ec\u87d0\u87ca\u87d1\u87db";
    private static String C = "\u87dd\u87d0\u87d1\u87ca\u87cc\u87ec\u87d0\u87ca\u87d1\u87db";
    private static String T = "\u87cc\u87ca\u87cf\u87dd\u87d0\u87ec\u87d0\u87ca\u87d1\u87db";
    private static String U = "\u87cc\u87d6\u87d3\u87da\u87d1\u87cb\u8791\u87de\u87ca";
    private static String V = "\u87dd\u87de\u87dc\u87d4\u87dc\u87d0\u87d3\u87d0\u87cd";
    private static String W = "\u87d9\u87d0\u87d1\u87cb\u87dc\u87d0\u87d3\u87d0\u87cd";
    private static String X = "\u87fe\u87cd\u87d6\u87de\u87d3";
    private static String Y = "\u87d9\u87c8\u87da\u87d1\u87db\u8791\u87dc\u87d0\u87d2";
    private static String Z = "\u87d9\u87de\u87d6\u87d3\u87da\u87db\u879f\u87cb\u87d0\u879f\u87cd\u87da\u87cb\u87cd\u87d6\u87da\u87c9\u87da\u879f\u87d7\u87d6\u87cc\u87dc\u87d0\u87cd\u87da\u87cc";
    private static String _a = "\u87dc\u87d3\u87d6\u87dc\u87d4\u87d2\u87da\u87e0\u87d3\u87de\u87dd";
    private static String aa = "\u87c8\u87d6\u87d1\u87d1\u87d6\u87d1\u87d8\u87e0\u87d2\u87cc\u87d8";
    private static String ba = "\u87e4";
    private static String ca = "\u87db\u87d0\u87ca\u87dd\u87d3\u87da\u87cc\u87e0\u87d3\u87de\u87dd";
    private static String da = "\u87e2";
    private static String ea = "\u87cb\u87cd\u87d6\u87cf\u87d3\u87da\u87cc\u87e0\u87d3\u87de\u87dd";
    private static String fa = "\u87cc\u87cf\u87da\u87da\u87db\u87d3\u87dd\u87e0\u87d3\u87de\u87dd";
    private static String ga = "\u87dc\u87cd\u87de\u87cc\u87d7\u87cf\u87de\u87db\u87e0\u87cc\u87d7\u87da\u87da\u87cb\u8791\u87d8\u87d6\u87d9";
    private static String ha = "\u87dc\u87cd\u87d0\u87cf\u87cf\u87d6\u87d1\u87d8\u879f\u87d0\u87d9\u879f\u87cb\u87d7\u87da\u879f\u87cc\u87d7\u87da\u87da\u87cb\u879f\u87d7\u87de\u87cc\u879f\u87dd\u87da\u87da\u87d1\u879f\u87d6\u87d1\u87cb\u87da\u87cd\u87cd\u87ca\u87cf\u87cb\u87da\u87db";
    private static String ia = "\u87d8\u87de\u87d2\u87d0\u87c9\u87da\u87cd\u87e0\u87d2\u87cc\u87d8";
    private static String ja = "\u87dd\u87d2\u87cf\u87c8\u87de\u87cd\u87d1\u87e0\u87d2\u87cc\u87d8";
    private static String ka = "\u87dd\u87d3\u87d3\u87c8\u87de\u87cd\u87d1\u87e0\u87d2\u87cc\u87d8";
    private static String la = "\u87d6\u87d1\u87d9\u87cb\u87c7\u87cb\u878e\u87e0\u87d2\u87cc\u87d8";
    private static String ma = "\u87d6\u87d1\u87d9\u87cb\u87c7\u87cb\u878d\u87e0\u87d2\u87cc\u87d8";
    private static String na = "\u87d6\u87d1\u87d9\u87cb\u87c7\u87cb\u878c\u87e0\u87d2\u87cc\u87d8";
    private static String oa = "\u87d7\u87cb\u87cb\u87cf\u8785\u8790\u8790\u87c8\u87c8\u87c8\u8791\u87d9\u87c8\u87da\u87d1\u87db\u8791\u87dc\u87d0\u87d2\u8790\u87cc\u87dc\u87cd\u87d6\u87cf\u87cb\u87cc\u8790\u87d7\u87d6\u87cc\u87dc\u87d0\u87cd\u87da\u87cc\u8790\u87d7\u87d6\u87cc\u87dc\u87d0\u87cd\u87da\u87cc\u8791\u87cf\u87d7\u87cf\u8780\u87d8\u87de\u87d2\u87da\u8782\u87dc\u87cd\u87de\u87cc\u87d7\u87cf\u87de\u87db";
    
    public void init() {
        String s = this.getParameter(Crashpad.w);
        if (s == null) {
            s = Crashpad.x;
        }
        this.za = new o(this.getCodeBase(), s);
        this.rb = new p(this, this.za);
        this.vzb = this.rb.a(Crashpad.y);
        this.wzb = this.rb.a(Crashpad.z);
        this.xzb = this.rb.a(Crashpad.A);
        this.yzb = this.rb.a(Crashpad.B);
        this.zzb = this.rb.a(Crashpad.C);
        this.Azb = this.rb.a(Crashpad.T);
        if (this.rb._()) {
            (this.Bzb = this.rb.a(Crashpad.U)).loop();
        }
        this.size = this.getSize();
        this.Vyb = this.rb.a(Crashpad.V, Color.black);
        this.Wyb = this.rb.a(Crashpad.W, Color.white);
        this.setBackground(this.Vyb);
        this.setFont(new Font(Crashpad.X, 1, 12));
        this.Nyb = this.getFontMetrics(this.getFont());
    }
    
    public void start() {
        m._b = 0;
        n._b = 0;
        l._b = 0;
        m.Za = 0;
        n.Za = 0;
        if (this.rb.b() && this.rb.a(Crashpad.Y)) {
            this._(this.Iyb);
        }
    }
    
    private void _(final String s) {
        BufferedReader bufferedReader = null;
        try {
            final URLConnection openConnection = new URL(s).openConnection();
            openConnection.setDoOutput(false);
            openConnection.setDoInput(true);
            openConnection.setUseCaches(false);
            openConnection.setAllowUserInteraction(false);
            bufferedReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));
            System.out.println(bufferedReader.readLine().trim());
            System.out.println(s);
        }
        catch (Exception ex) {
            System.out.println(Crashpad.Z);
        }
        finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                }
                catch (IOException ex2) {}
            }
        }
    }
    
    public boolean h() {
        if (this.rb.b() && !this.rb.a(p.wa)) {
            this.rb.b(true);
            return false;
        }
        if (!this.i() || !this.j()) {
            return false;
        }
        this.Jyb = this.createImage(this.size.width, this.size.height);
        this.Lyb = this.Jyb.getGraphics();
        this.Kyb = this.createImage(this.size.width, this.size.height);
        this.Myb = this.Kyb.getGraphics();
        this.Oyb = new Font(Crashpad.X, 1, 12);
        this.Pyb = new Font(Crashpad.X, 1, 20);
        this.Lyb.setFont(this.Oyb);
        this.Xyb = this.za.m(Crashpad._a);
        this.Yyb = this.za.m(Crashpad.aa);
        l.eb = Crashpad.ba + this.za.m(Crashpad.ca) + Crashpad.da;
        l.fb = Crashpad.ba + this.za.m(Crashpad.ea) + Crashpad.da;
        l.gb = this.rb.a(l.eb, true, this.Lyb);
        l.hb = this.rb.a(l.fb, true, this.Lyb);
        this.czb = new g(0, 0, 15, 15);
        this.fzb = new i(2, 46, this.size.width - 4, this.size.height - 20);
        this.ezb = new h(0, this.size.height - 48, 58, 11);
        this.gzb = new j(this.size.width, this.rb, this.za);
        this.hzb = new k(new Rectangle(100, 230, 140, 40), this.tzb, this.za.m(Crashpad.fa), this.rb);
        this.Uyb = this.ezb.E.width / 2 + 10;
        this.k();
        this.requestFocus();
        this.enableEvents(48L);
        return !this.rb.g();
    }
    
    private boolean i() {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.pzb = this.getImage(this.getCodeBase(), Crashpad.ga));
        this.rb._(vector, 0);
        return !this.rb.g();
    }
    
    private boolean j() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final ImageProducer source = this.pzb.getSource();
        this.lzb = new Image[15];
        for (int i = 0; i < 13; ++i) {
            mediaTracker.addImage(this.lzb[i] = this.createImage(new FilteredImageSource(source, new CropImageFilter(i * 30, 0, 30, 30))), 0);
        }
        this.lzb[13] = this.lzb[12];
        this.lzb[14] = this.lzb[12];
        this.mzb = new Image[11];
        for (int j = 0; j < 11; ++j) {
            mediaTracker.addImage(this.mzb[j] = this.createImage(new FilteredImageSource(source, new CropImageFilter(j * 30, 30, 30, 30))), 0);
        }
        this.ozb = new Image[2];
        for (int k = 0; k < 2; ++k) {
            mediaTracker.addImage(this.ozb[k] = this.createImage(new FilteredImageSource(source, new CropImageFilter(k * 15, 60, 15, 15))), 0);
        }
        this.nzb = new Image[2];
        for (int l = 0; l < 2; ++l) {
            mediaTracker.addImage(this.nzb[l] = this.createImage(new FilteredImageSource(source, new CropImageFilter(l * 58, 88, 58, 28))), 0);
        }
        mediaTracker.addImage(this.szb = this.createImage(new FilteredImageSource(source, new CropImageFilter(0, 132, 320, 35))), 0);
        mediaTracker.addImage(this.tzb = this.createImage(new FilteredImageSource(source, new CropImageFilter(145, 75, 180, 40))), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {
            return this.rb.d(Crashpad.ha);
        }
        return !this.rb.g();
    }
    
    private void b(final Graphics graphics) {
        this.rb._(graphics);
        final String b = this.za.b();
        graphics.setColor(this.Wyb);
        graphics.drawString(b, this.rb.a(b, true, graphics), this.rb.a(b, false, graphics));
        if (this.h()) {
            this.Zyb = true;
            this.rb._(graphics);
            this.repaint();
            return;
        }
        this.rb._(graphics);
        String s;
        if (this.rb.a()) {
            s = p.ua;
        }
        else {
            s = this.za._();
        }
        graphics.setColor(this.Wyb);
        graphics.drawString(s, this.rb.a(s, true, graphics), this.rb.a(s, false, graphics));
    }
    
    public void update(final Graphics graphics) {
        if (!this.Zyb) {
            this.b(graphics);
            (this.Qyb = new Thread(this)).start();
            return;
        }
        this.Lyb.drawImage(this.Kyb, 0, 0, this);
        this.Lyb.setFont(this.Oyb);
        this.l();
        this.m();
        this.o();
        if (this.Ryb != 0) {
            this.Lyb.setColor(Color.white);
            this.Lyb.setFont(this.Oyb);
            this.Lyb.drawString(this.Xyb, this.rb.a(this.Xyb, true, this.Lyb), 330);
            switch (this.Ryb) {
                case 3: {
                    this.Lyb.setFont(this.Pyb);
                    this.Lyb.setColor(Color.blue);
                    final String m = this.za.m(Crashpad.ia);
                    this.Lyb.drawString(m, this.rb.a(m, true, this.Lyb), (this.size.height - 60) / 2);
                    break;
                }
                case 5: {
                    this.Lyb.setFont(this.Pyb);
                    this.Lyb.setColor(Color.yellow);
                    this.Lyb.drawString(this.Yyb, this.rb.a(this.Yyb, true, this.Lyb), (this.size.height - 60) / 2);
                    break;
                }
                case 2: {
                    this.p();
                    break;
                }
            }
            this.q();
        }
        else {
            this.Lyb.drawImage(this.rzb, this.czb.E.x, this.czb.E.y, this);
            if (this.dzb != null) {
                this.Lyb.drawImage(this.rzb, this.dzb.E.x, this.dzb.E.y, this);
            }
            this.Lyb.drawImage(this.qzb, this.ezb.E.x, this.ezb.E.y, this);
        }
        this.Lyb.setFont(this.Oyb);
        this.gzb.a(this.Lyb);
        this.hzb.a(this.Lyb);
        graphics.drawImage(this.Jyb, 0, 0, this);
    }
    
    private void q() {
        if (!this._zb && this.gzb.j() == 3) {
            this.Lyb.setColor(Color.yellow);
            final String m = this.za.m(Crashpad.ja);
            this.Lyb.drawString(m, this.rb.a(m, true, this.Lyb), 310);
            return;
        }
        if (!this.azb && this.gzb.j() == 6) {
            this.Lyb.setColor(Color.yellow);
            final String i = this.za.m(Crashpad.ka);
            this.Lyb.drawString(i, this.rb.a(i, true, this.Lyb), 310);
        }
    }
    
    private void m() {
        if (n._b > 0) {
            if (this.Ryb == 0) {
                ++n.Za;
            }
            for (int i = 0; i < n._b; ++i) {
                final n n = this.kzb[i];
                if (n.ib) {
                    this.Lyb.setColor(Color.green);
                    final String b = n.b(n.a());
                    final Rectangle e = n.E;
                    this.Lyb.drawString(b, p.b(b, true, e, this.Lyb) + 1, e.y + 17);
                }
                else if (n.cb-- > 0) {
                    this.Lyb.setColor(Color.yellow);
                    final String value = String.valueOf(n.a());
                    final Rectangle e2 = n.E;
                    this.Lyb.drawString(value, p.b(value, true, e2, this.Lyb), e2.y + 17);
                }
            }
        }
    }
    
    private void l() {
        if (m._b > 0) {
            if (this.Ryb == 0) {
                ++m.Za;
            }
            for (int i = 0; i < m._b; ++i) {
                final m m = this.jzb[i];
                if (m.ib || m.cb > 0) {
                    final int a = m.a();
                    if (m.ib) {
                        this.Lyb.setColor(Color.blue);
                    }
                    else if (m.cb-- > 0) {
                        this.Lyb.setColor(Color.yellow);
                    }
                    final String value = String.valueOf(a);
                    final Rectangle e = m.E;
                    if (a < 20) {
                        this.Lyb.drawString(value, e.x + 5, e.y + 17);
                    }
                    else if (a < 100) {
                        this.Lyb.drawString(value, e.x + 6, e.y + 17);
                    }
                    else {
                        this.Lyb.drawString(value, p.b(value, true, e, this.Lyb), e.y + 17);
                    }
                }
            }
        }
    }
    
    private void o() {
        if (l._b > 0) {
            this.Lyb.setColor(Color.red);
            final String value = String.valueOf(l.db);
            for (int i = 0; i < l._b; ++i) {
                final Rectangle e = this.izb[i].E;
                this.Lyb.drawString(value, e.x + 9, e.y + 17);
            }
            if (l.db > 1) {
                this.Lyb.setColor(Color.magenta);
                if (l.db == 2) {
                    this.Lyb.drawString(l.eb, l.gb, 290);
                    return;
                }
                if (l.db == 3) {
                    this.Lyb.drawString(l.fb, l.hb, 290);
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void p() {
        this.Lyb.setFont(this.Oyb);
        this.Lyb.setColor(Color.red);
        final String m = this.za.m(Crashpad.la);
        this.Lyb.drawString(m, this.rb.a(m, true, this.Lyb), 140);
        final String i = this.za.m(Crashpad.ma);
        this.Lyb.drawString(i, this.rb.a(i, true, this.Lyb), 170);
        final String j = this.za.m(Crashpad.na);
        this.Lyb.drawString(j, this.rb.a(j, true, this.Lyb), 200);
        this.Lyb.drawImage(this.ozb[1], 170, 90, this);
        this.Lyb.drawImage(this.mzb[3], 50, 60, this);
        this.Lyb.drawImage(this.mzb[6], 280, 260, this);
        this.Lyb.drawImage(this.lzb[2], 260, 85, this);
        this.Lyb.drawImage(this.lzb[4], 35, 240, this);
    }
    
    private void k() {
        this.rb._(this.Myb);
        if (this.bzb != null) {
            for (int i = 0; i < this.Tyb; ++i) {
                for (int j = 0; j < this.Syb; ++j) {
                    final f f = this.bzb[i][j];
                    if (f != null && f.ib) {
                        this.Myb.drawImage(this.lzb[f.Ayb], f.E.x - 3, f.E.y - 3, this);
                    }
                }
            }
        }
        this.Myb.drawImage(this.szb, 15, 5, this);
        this.Myb.setColor(this.o);
        this.Myb.fillRect(0, 0, this.size.width, 2);
        this.Myb.fillRect(0, 44, this.size.width, 2);
        this.Myb.fillRect(0, 0, 2, this.size.height);
        this.Myb.fillRect(this.size.width - 2, 0, 2, this.size.height - 2);
    }
    
    public void r() {
        final String[] _ = q._(this.gzb.j());
        this.bzb = new f[this.Tyb][this.Syb];
        for (int i = 0; i < this.Tyb; ++i) {
            int j = 0;
        Label_0395_Outer:
            while (j < this.Syb) {
                final int n = Integer.parseInt(String.valueOf(_[j].charAt(i)), 16) - 1;
                while (true) {
                    switch (n) {
                        default: {
                            this.bzb[i][j] = new f(22 + i * 30, 63 + j * 30, 25, 25, n);
                        }
                        case -1: {
                            ++j;
                            continue Label_0395_Outer;
                        }
                        case 11: {
                            this.bzb[i][j] = new r(22 + i * 30, 63 + j * 30, 25, 25, n);
                            continue;
                        }
                        case 12: {
                            this.bzb[i][j] = new l(22 + i * 30, 63 + j * 30, 25, 25, n);
                            this.izb[l._b++] = (l)this.bzb[i][j];
                            continue;
                        }
                        case 13: {
                            this.bzb[i][j] = new m(22 + i * 30, 63 + j * 30, 25, 25, n, 99 - j * 11);
                            this.jzb[m._b++] = (m)this.bzb[i][j];
                            continue;
                        }
                        case 14: {
                            this.bzb[i][j] = new n(22 + i * 30, 63 + j * 30, 25, 25, n, 99 - j * 11);
                            this.kzb[n._b++] = (n)this.bzb[i][j];
                            continue;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    private void a(final g g) {
        g.E.x = this.ezb.E.x + this.ezb.E.width / 2;
        g.E.y = this.size.height - 60;
        g.ryb = -1;
        g.F = -1;
        if ((int)(Math.random() * 2.0) == 0) {
            g.e();
        }
        g.b(this.gzb.b());
    }
    
    public void stop() {
        if (this.Qyb != null && this.Qyb.isAlive()) {
            this.Qyb.stop();
        }
        this.Qyb = null;
    }
    
    public void run() {
        final Toolkit toolkit = this.getToolkit();
        while (this.Qyb != null) {
            if (this.Ryb == 0) {
                this.sleep(this.hzb.b());
                this._(this.czb);
                this.czb.h();
                if (this.Ryb == 0 && this.dzb != null) {
                    this._(this.dzb);
                    this.dzb.h();
                }
            }
            else {
                this.sleep(10L);
            }
            toolkit.sync();
            this.repaint();
        }
    }
    
    private final void sleep(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {}
    }
    
    public boolean _(final g g) {
        int n = 0;
        boolean b = false;
    Label_0443:
        for (int i = 0; i < this.Tyb; ++i) {
            for (int j = 0; j < this.Syb; ++j) {
                final f f = this.bzb[i][j];
                if (f != null) {
                    if (f.ib) {
                        if (f._(g)) {
                            if (!f.D) {
                                this.Myb.clearRect(f.E.x - 3, f.E.y - 3, f.E.width + 6, f.E.height + 6);
                                if (f.Ayb == 13) {
                                    p.b(this.zzb);
                                    final n n2 = (n)f;
                                    n2.stop();
                                    this.gzb.m(n2.a());
                                }
                                else if (f.Ayb == 14) {
                                    p.b(this.Azb);
                                    final n n3 = (n)f;
                                    n3.stop();
                                    this.gzb.m(n3.a());
                                }
                                else {
                                    this.Myb.drawImage(this.mzb[f.Ayb], f.E.x - 3, f.E.y - 3, this);
                                    p.b(this.vzb);
                                    this.gzb.c();
                                }
                                g.b(this.gzb.b());
                                if (f.Ayb == 10 && this.dzb == null) {
                                    (this.dzb = new g(f.E.x + 7, f.E.y + 7, 15, 15)).b(this.gzb.b());
                                }
                                b = true;
                                break Label_0443;
                            }
                            if (f.Ayb == 12) {
                                p.b(this.yzb);
                            }
                        }
                        else if (!f.D) {
                            ++n;
                        }
                    }
                    else if (f.zyb) {
                        f.zyb = false;
                        this.Myb.clearRect(f.E.x, f.E.y, f.E.width, f.E.height);
                    }
                }
            }
        }
        if (!b) {
            if (n == 0) {
                p.b(this.xzb);
                if (this.gzb.j() == 11) {
                    this.Ryb = 5;
                    for (int k = 2; k < this.Tyb - 2; ++k) {
                        for (int l = 2; l < this.Syb; ++l) {
                            if (this.bzb[k][l] != null) {
                                this.bzb[k][l].ib = false;
                            }
                        }
                    }
                    this.k();
                    l._b = 0;
                    m._b = 0;
                    m.Za = 0;
                    n._b = 0;
                    n.Za = 0;
                    this.hzb.ib = true;
                    this.gzb.l(this.gzb.j() * 100);
                    this.gzb.l(this.gzb.i() * 500);
                }
                else {
                    this.gzb.d();
                    this.Ryb = 4;
                    l._b = 0;
                    m._b = 0;
                    m.Za = 0;
                    n._b = 0;
                    n.Za = 0;
                    this.r();
                    this.gzb.a();
                    this.gzb.l(this.gzb.j() * 100);
                    this.k();
                }
            }
            else {
                final boolean _;
                if (!(_ = this.ezb._(g)) && !this.fzb._(g)) {
                    if (g.E.y > this.size.height) {
                        this.Ryb = 1;
                        p.b(this.wzb);
                        this.gzb.a(-1);
                        if (this.gzb.i() < 1) {
                            this.Ryb = 3;
                            for (int n4 = 2; n4 < this.Tyb - 2; ++n4) {
                                for (int n5 = 2; n5 < this.Syb; ++n5) {
                                    if (this.bzb[n4][n5] != null) {
                                        this.bzb[n4][n5].ib = false;
                                    }
                                }
                            }
                            this.hzb.ib = true;
                            l._b = 0;
                            m._b = 0;
                            m.Za = 0;
                            n._b = 0;
                            n.Za = 0;
                            this.k();
                        }
                    }
                }
                else if (_) {
                    this.gzb._(this.ezb.n);
                    g.b(this.gzb.b());
                }
            }
        }
        return true;
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this.a(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            case 502: {
                this.b(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    public boolean a(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.Ryb != 0) {
            if (this.hzb.ib && this.hzb.contains(n, n2)) {
                if (this.hzb.contains(n, n2)) {
                    this.hzb.jb = true;
                    this.hzb._(n);
                }
                return true;
            }
            this.qzb = this.nzb[this.gzb.i() % 2];
            this.rzb = this.ozb[this.gzb.i() % 2];
            if (this.Ryb == 4) {
                this.dzb = null;
            }
            else if (this.Ryb != 1) {
                this.Lyb.clipRect(this.fzb.E.x, this.fzb.E.y, this.fzb.E.width, this.fzb.E.height);
                this.dzb = null;
                l._b = 0;
                m._b = 0;
                m.Za = 0;
                n._b = 0;
                n.Za = 0;
                this.gzb.reset();
                this.r();
                this.k();
                this.hzb.ib = false;
            }
            this.c(n);
            this.a(this.czb);
            this.dzb = null;
            System.gc();
            this.Ryb = 0;
            this.repaint();
        }
        return true;
    }
    
    public boolean b(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.hzb.ib) {
            this.hzb.jb = false;
        }
        if (!this._zb && this.gzb.j() == 3) {
            this._zb = true;
        }
        else if (!this.azb && this.gzb.j() == 6) {
            this.azb = true;
        }
        return true;
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                if (this.hzb.jb) {
                    this.hzb.a(mouseEvent.getX());
                    this.repaint();
                    return;
                }
                break;
            }
            case 503: {
                if (this.Ryb == 0) {
                    this.c(mouseEvent.getX());
                    this.repaint();
                    return;
                }
                break;
            }
            default: {
                mouseEvent.consume();
                break;
            }
        }
    }
    
    private final void c(final int n) {
        this.ezb.n(n);
        if (n < this.Uyb) {
            this.ezb.n(this.Uyb);
            return;
        }
        if (n > this.size.width - this.Uyb) {
            this.ezb.n(this.size.width - this.Uyb);
        }
    }
    
    public void destroy() {
        p._((Object)this.vzb);
        p._((Object)this.wzb);
        p._((Object)this.xzb);
        p._((Object)this.yzb);
        p._((Object)this.zzb);
        p._((Object)this.Azb);
        p._((Object)this.Bzb);
        p._((Object)this.Lyb);
        p._((Object)this.Myb);
        p._(this.Jyb);
        p._(this.Kyb);
        p._(this.pzb);
        System.gc();
    }
    
    public Crashpad() {
        this.Iyb = Crashpad.oa;
        this.Ryb = 2;
        this.Syb = 6;
        this.Tyb = 10;
        this.o = new Color(3381759);
        this.izb = new l[4];
        this.jzb = new m[this.Tyb * this.Syb];
        this.kzb = new n[25];
    }
    
    private static String b(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u87bf');
        }
        return new String(array);
    }
    
    static {
        Crashpad.w = b(Crashpad.w);
        Crashpad.x = b(Crashpad.x);
        Crashpad.y = b(Crashpad.y);
        Crashpad.z = b(Crashpad.z);
        Crashpad.A = b(Crashpad.A);
        Crashpad.B = b(Crashpad.B);
        Crashpad.C = b(Crashpad.C);
        Crashpad.T = b(Crashpad.T);
        Crashpad.U = b(Crashpad.U);
        Crashpad.V = b(Crashpad.V);
        Crashpad.W = b(Crashpad.W);
        Crashpad.X = b(Crashpad.X);
        Crashpad.Y = b(Crashpad.Y);
        Crashpad.Z = b(Crashpad.Z);
        Crashpad._a = b(Crashpad._a);
        Crashpad.aa = b(Crashpad.aa);
        Crashpad.ba = b(Crashpad.ba);
        Crashpad.ca = b(Crashpad.ca);
        Crashpad.da = b(Crashpad.da);
        Crashpad.ea = b(Crashpad.ea);
        Crashpad.fa = b(Crashpad.fa);
        Crashpad.ga = b(Crashpad.ga);
        Crashpad.ha = b(Crashpad.ha);
        Crashpad.ia = b(Crashpad.ia);
        Crashpad.ja = b(Crashpad.ja);
        Crashpad.ka = b(Crashpad.ka);
        Crashpad.la = b(Crashpad.la);
        Crashpad.ma = b(Crashpad.ma);
        Crashpad.na = b(Crashpad.na);
        Crashpad.oa = b(Crashpad.oa);
    }
}
