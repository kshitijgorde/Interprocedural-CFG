import java.awt.image.ImageObserver;
import java.awt.Font;
import com.kcmultimedia.sntp.SNTPCorrection;
import com.kcmultimedia.sntp.SNTPProxy;
import java.util.Date;
import java.util.Calendar;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.TimeZone;
import java.util.GregorianCalendar;
import java.applet.AudioClip;
import java.text.SimpleDateFormat;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class BillsClock2 extends Applet implements Runnable
{
    public static final String a = "\n====================================\nBillsClock2.0.1\nCopyright (C) 2001 by Bill Giel\nbgiel@kcmultimedia.com\nwww.kcmultimedia.com/sntpapi";
    String b;
    static final double c = 0.104719755;
    static final double d = 0.523598776;
    Thread e;
    boolean f;
    int g;
    int h;
    String i;
    Color j;
    Color k;
    Color l;
    Color m;
    Color n;
    Image o;
    int p;
    int q;
    c r;
    b s;
    b t;
    double u;
    int v;
    int w;
    Image x;
    Graphics y;
    FontMetrics z;
    boolean A;
    boolean B;
    boolean C;
    SimpleDateFormat D;
    SimpleDateFormat E;
    int F;
    String G;
    boolean H;
    boolean I;
    AudioClip J;
    AudioClip K;
    GregorianCalendar L;
    int M;
    boolean N;
    int O;
    double P;
    boolean Q;
    
    public void init() {
        this.g = this.getSize().width;
        this.h = this.getSize().height;
        this.p = e.a(this, b("g:\u001f\u000f\u0011v'"), this.g / 2);
        this.q = e.a(this, b("g:\u001f\u000f\u0011v&"), this.h / 2);
        this.j = this.a(e.a(this, b("w(\u0014\u001e\u0004g0\u001d\u0014\u0006"), b("\u0007\u00197kd\u0014O")));
        this.k = this.a(e.a(this, b("i6\u001f\u000e\u0000a<\u001e\u0017\u001bv"), b("\u0007\u001ca8dGO")));
        this.l = this.a(e.a(this, b("l0\u0004\t\u0017k3\u001e\t"), b("\u0007Oakd\u0014O")));
        this.m = this.a(e.a(this, b("f8\u0012\u0014\u0018k-"), b("\u0007\u00197=2B\u0019")));
        this.n = this.a(e.a(this, b("b8\u0012\u0014\u0018k-"), b("\u0007Oakd\u0014O")));
        this.b = e.a(this, b("w&\u001f\u0018\u000bi:\u0002\b\u0015c:"), b("t\u00134:'A_\"/5J\u001bq9-\nQ\u007f"));
        final String a = e.a(this, b("f8\u0018\u0016\u0015c:"), null);
        final String a2 = e.a(this, b("l0\u0004\t\u000bg7\u0018\u0016\u0011w"), null);
        final String a3 = e.a(this, b("l>\u001d\u001d\u001ck*\u0003\u0004\u0017l6\u001c\u001e\u0007"), null);
        final int a4 = e.a(this, b("l0\u0004\t\u0018a1\u0016\u000f\u001c"), 25);
        final int a5 = e.a(this, b("l0\u0004\t\u0000l6\u0012\u0010"), 8);
        final int a6 = e.a(this, b("i6\u001f\u000e\u0000a3\u0014\u0015\u0013p7"), 40);
        final int a7 = e.a(this, b("i6\u001f\u000e\u0000a+\u0019\u0012\u0017o"), 6);
        final int a8 = e.a(this, b("w(\u0014\u001e\u0004h:\u001f\u001c\u0000l"), 40);
        this.F = e.a(this, b("b0\u001f\u000f\u0007m%\u0014"), 9);
        this.G = e.a(this, b("b0\u001f\u000f\u0012e<\u0014"), b("W\u001e?('A\r8="));
        this.A = e.a(this, b("w7\u001e\f\u0007s:\u0014\u000b"), true);
        this.I = e.a(this, b("r:\u0003\u0019\u001bw:"), true);
        this.N = e.a(this, b("q,\u0014\u0004\u0007j+\u0001"), false);
        this.M = e.a(this, b("w1\u0005\u000b\u000bt0\u0003\u000f"), 1200);
        this.O = e.a(this, b("w1\u0005\u000b\u000bw>\u001c\u000b\u0018a,"), 4);
        this.O = Math.max(2, this.O);
        this.B = e.a(this, b("w7\u001e\f\u000b`>\u0005\u001e"), true);
        this.C = e.a(this, b("w7\u001e\f\u000be2\u0001\u0016"), true);
        this.i = e.a(this, b("p6\u001c\u001e\u000ek1\u0014"), null);
        if (this.i == null) {
            this.i = TimeZone.getDefault().getID();
        }
        try {
            this.o = this.getImage(new URL(this.getCodeBase(), a));
            if (a2 != null) {
                this.J = this.getAudioClip(new URL(this.getCodeBase(), a2));
            }
            if (a3 != null) {
                this.K = this.getAudioClip(new URL(this.getCodeBase(), a3));
            }
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.o, 0);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            System.out.println(b("f\u0016=7'g\u0013>8?\u0016_2::J\u0010%{8K\u001e5{=I\u001e6>z"));
            System.out.println(ex.getMessage());
            this.o = null;
        }
        this.u = -1.0;
        this.v = -1;
        this.w = -1;
        this.r = new c(this.p, this.q, a8, 3);
        this.s = new b(this.p, this.q, a6, a7, 6);
        this.t = new b(this.p, this.q, a4, a5, 6);
        if (this.J != null) {
            this.J.stop();
        }
        if (this.K != null) {
            this.K.stop();
        }
    }
    
    public void start() {
        if (!this.getDocumentBase().getHost().equals(this.getCodeBase().getHost())) {
            System.out.println(b("w1\u0005\u000bte\u000f!71P_2::J\u0010%{&Q\u0011q: \u0004\u000b92'\u0004\f8/1\u0005"));
            return;
        }
        if (this.e == null) {
            this.L = new GregorianCalendar(TimeZone.getTimeZone(this.i));
            this.D.setCalendar(this.L);
            this.E.setCalendar(this.L);
            this.Q = false;
            (this.e = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.f = true;
    }
    
    private void a(final Graphics graphics) {
        final Date time = new Date();
        time.setTime(time.getTime() + (long)this.P);
        this.L.setTime(time);
        String s = null;
        if (this.B && this.C) {
            s = String.valueOf(this.D.format(this.L.getTime())) + " " + this.E.format(this.L.getTime());
        }
        else if (this.B && !this.C) {
            s = this.D.format(this.L.getTime());
        }
        else if (!this.B && this.C) {
            s = this.E.format(this.L.getTime());
        }
        if (s != null) {
            final int n = this.z.stringWidth(s) / 2;
            graphics.setColor(Color.black);
            graphics.drawString(s, this.p - n, this.q + 2 * this.F);
        }
        this.v = this.L.get(12);
        this.s.a(this.k, 0.104719755 * this.v, graphics);
        this.u = this.L.get(10) + this.v / 60.0;
        this.t.a(this.l, 0.523598776 * this.u, graphics);
        final int a = this.s.a();
        graphics.setColor(this.j);
        graphics.fillOval(this.p - a, this.q - a, a * 2, a * 2);
        this.w = this.L.get(13);
        final double n2 = 0.104719755 * this.w;
        if (this.A) {
            this.r.a(this.j, n2, graphics);
        }
        if (this.J != null && !this.H && this.v == 59) {
            this.H = true;
        }
        if (this.K != null && !this.H && this.v == 29) {
            this.H = true;
        }
        if (this.J != null && this.H && this.v == 0) {
            this.J.play();
            this.H = false;
        }
        if (this.K != null && this.H && this.v == 30) {
            this.K.play();
            this.H = false;
        }
    }
    
    private Color a(String substring) {
        if (substring.startsWith("#")) {
            substring = substring.substring(1);
        }
        if (substring.length() == 6) {
            return new Color(Integer.valueOf(substring.substring(0, 2), 16), Integer.valueOf(substring.substring(2, 4), 16), Integer.valueOf(substring.substring(4, 6), 16));
        }
        return Color.lightGray;
    }
    
    public void run() {
        final StringBuffer sb = new StringBuffer();
        sb.append(b(".ulfi\u0019Blfi\u0019Blfi\u0019Blfi\u0019Blfi\u0019Blfi\u0019Blfi\u0019BlQ\u0016M\u0013=(\u0017H\u001020f\nO\u007fj^g\u0010!\"&M\u00189/t\f<x{f\u0014O`{6]_\u001328H_\u001621Hu3<=A\u0013\u001107I\n=/=I\u001a525\n\u001c>6^S\b&u?G\u0012$7 M\u00124?=EQ249\u000b\f?/$E\u000f8"));
        sb.append(b(".u\u0002/5V\u000b853\u0004=878W<=47OMq\u001a$T\u00134/"));
        sb.append(b(".u\u00127;G\u0014q2'\u0004\u001c$)&A\u0011%7-\u0004\f4/tP\u0010q?=W\u000f=:-\u0004") + this.i + b("\u0004\u000b861\u0004\u0005>51\n"));
        sb.append(b(".u\b4!V_249T\n%>&\u0003\fq7;G\u001e={ M\u00124{.K\u00114{'A\u001a<(tP\u0010q91\u001e_") + TimeZone.getDefault().getID());
        sb.append(b(".W\u0018=tP\u00178(tM\fq2:G\u0010#)1G\u000b}{-K\nq6!W\u000bq==\\_8/tM\u0011q4&@\u001a#{2K\r"));
        sb.append(b(".\u000b9>tG\u0013>8?\u0004\u000b>{0M\f!75]_%31\u0004\u001c>)&A\u001c%{ M\u00124u}"));
        if (this.N) {
            final d d = new d();
            final SNTPProxy sntpProxy = new SNTPProxy(this.getCodeBase().getHost(), this.M);
            int n = 0;
            for (int i = 0; i < this.O; ++i) {
                try {
                    final SNTPCorrection correction = sntpProxy.getCorrection();
                    d.a(correction.getCorrection(), correction.getDelay());
                    sb.append(b(".<>)&A\u001c%2;JEq") + d.a(correction.getCorrection()) + b("\u0004_q\u001f1H\u001e(at") + d.a(correction.getDelay()));
                    n = 0;
                }
                catch (Exception ex) {
                    if (++n > 2) {
                        break;
                    }
                }
            }
            if (d.c() < 2) {
                sb.append(b(".u\u0002\u0015\u0000t_\u00124:J\u001a2/=K\u0011q\u001e&V\u0010#atg\u0010$70\u0004\u0011>/tC\u001a%{9M\u0011<.9\u0004\u00107{ S\u0010q8;J\u001148 M\u0010?(z"));
                sb.append(b(".,\u001f\u000f\u0004\u0004,(57L\r>5=^\u001e%2;J_24!H\u001bq5;P_3>tA\f%:6H\u0016\"31@Sq::@_"));
                sb.append(b(".\u001b8($H\u001e(>0\u0004\u000b861\u0004\u0016\"{6E\f4?tQ\u000f>5t]\u0010$)tG\u0010<+!P\u001a#(tG\u0013>8?\n"));
                sb.append(b(".+92'\u0004\u0016\"{$V\u00103:6H\u0006q91G\u001e$(1\u0004\u0006>.tE\r4{6A\u0017850\u0004\u001eq==V\u001a&:8H"));
                sb.append(b(".\u0010#{$V\u0010)\"tW\u001a#-1V_%35P_!)1R\u001a?/'\u0004\u000b9>tE\u000f!71P_7);I_24:J\u001a2/=J\u0018"));
                sb.append(b(".\u000b>{;Q\rq\b\u001ap/q+&K\u0007({'A\r'>&\n"));
            }
            else {
                this.P = d.a();
                final double b = d.b();
                sb.append(b(".u\u0002\u0015\u0000t_\u0001:7O\u001a%(tv\u001a2>=R\u001a5at") + d.c());
                sb.append("\n" + (String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(b("h\u00102:8\u0004<=47O_8(t")).append(d.a(Math.abs(this.P))).append(b("\u0004\f48z\u0004")).toString())).append((this.P > 0.0) ? b("W\u0013>,t") : b("B\u001e\"/t")).toString()) + b("\u000fP|{") + d.a(b) + b("\u0004\f48z")));
                this.P *= 1000.0;
            }
        }
        else {
            sb.append(b(".u\u0002\u0015\u0000t_\u0002\":G\u0017#4:M\u00050/=K\u0011q2'\u0004\u001c$)&A\u0011%7-\u0004\u001b8(5F\u00134?x\u0004\u001e??"));
            sb.append(b(".\u001b8($H\u001e(>0\u0004\u000b861\u0004\u0016\"{6E\f4?tQ\u000f>5t]\u0010$)tG\u0010<+!P\u001a#(tG\u0013>8?\n"));
        }
        if (this.I) {
            System.out.println(sb);
        }
        this.Q = true;
        while (!this.f) {
            this.repaint();
            try {
                Thread.yield();
                Thread.sleep(125L);
            }
            catch (InterruptedException ex2) {
                break;
            }
        }
        this.e = null;
    }
    
    public void paint(final Graphics graphics) {
        try {
            if (this.x == null) {
                this.x = this.createImage(this.g, this.h);
                (this.y = this.x.getGraphics()).setFont(new Font(this.G, 0, this.F));
                this.z = this.y.getFontMetrics();
            }
            if (this.Q) {
                this.y.setColor(this.m);
                this.y.fillRect(0, 0, this.g, this.h);
                this.y.drawImage(this.o, 0, 0, this.g, this.h, this);
                this.a(this.y);
            }
            else {
                this.y.setColor(this.m);
                this.y.fillRect(0, 0, this.g, this.h);
                this.y.setColor(this.n);
                this.y.setFont(new Font(this.G, 1, this.F));
                this.y.drawString(this.b, this.g / 2 - this.z.stringWidth(this.b) / 2, this.h / 2);
                this.y.setFont(new Font(this.G, 0, this.F));
            }
            graphics.drawImage(this.x, 0, 0, this);
        }
        catch (NullPointerException ex) {}
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public BillsClock2() {
        this.b = b("w\u000b050\u0004\u001d(uz\n");
        this.f = false;
        this.D = new SimpleDateFormat(b("i2\u001c{0@"));
        this.E = new SimpleDateFormat("a");
        this.H = false;
        this.Q = false;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '$';
                    break;
                }
                case 1: {
                    c2 = '\u007f';
                    break;
                }
                case 2: {
                    c2 = 'Q';
                    break;
                }
                case 3: {
                    c2 = '[';
                    break;
                }
                default: {
                    c2 = 'T';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
