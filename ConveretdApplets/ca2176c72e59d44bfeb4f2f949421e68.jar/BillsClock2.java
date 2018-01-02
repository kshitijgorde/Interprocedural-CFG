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
    public static final String a = "";
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
        final boolean h = b.h;
        this.g = this.getSize().width;
        this.h = this.getSize().height;
        this.p = e.getInt(this, b("ly]Z>}d"), this.g / 2);
        this.q = e.getInt(this, b("ly]Z>}e"), this.h / 2);
        this.j = this.a(e.getString(this, b("|kVK+ls_A)"), b("\fZu>K\u001f\f")));
        this.k = this.a(e.getString(this, b("bu][/j\u007f\\B4}"), b("\f_#mKL\f")));
        this.l = this.a(e.getString(this, b("gsF\\8`p\\\\"), b("\f\f#>K\u001f\f")));
        this.m = this.a(e.getString(this, b("m{PA7`n"), b("\fZuh\u001dIZ")));
        this.n = this.a(e.getString(this, b("i{PA7`n"), b("\f\f#>K\u001f\f")));
        this.b = e.getString(this, b("|e]M$by@]:hy"), b("\u007fPvo\bJ\u001c`z\u001aAX3l\u0002\u0001\u0012="));
        final String string = e.getString(this, b("m{ZC:hy"), null);
        final String string2 = e.getString(this, b("gsF\\$ltZC>|"), null);
        final String string3 = e.getString(this, b("g}_H3`iAQ8gu^K("), null);
        final int int1 = e.getInt(this, b("gsF\\7jrTZ3"), 25);
        final int int2 = e.getInt(this, b("gsF\\/guPE"), 8);
        final int int3 = e.getInt(this, b("bu][/jpV@<{t"), 40);
        final int int4 = e.getInt(this, b("bu][/jh[G8d"), 6);
        final int int5 = e.getInt(this, b("|kVK+cy]I/g"), 40);
        this.F = e.getInt(this, b("is]Z(ffV"), 9);
        this.G = e.getString(this, b("is]Z=n\u007fV"), b("\\]}}\bJNzh"));
        this.A = e.getBoole(this, b("|t\\Y(xyV^"), true);
        this.I = e.getBoole(this, b("yyAL4|y"), true);
        this.N = e.getBoole(this, b("zoVQ(ahC"), false);
        this.M = e.getInt(this, b("|rG^$\u007fsAZ"), 1200);
        this.O = e.getInt(this, b("|rG^$|}^^7jo"), 4);
        this.O = Math.max(2, this.O);
        this.B = e.getBoole(this, b("|t\\Y$k}GK"), true);
        this.C = e.getBoole(this, b("|t\\Y$nqCC"), true);
        this.i = e.getString(this, b("{u^K!`rV"), null);
        if (this.i == null) {
            this.i = TimeZone.getDefault().getID();
        }
        try {
            this.o = this.getImage(new URL(this.getCodeBase(), string));
            if (string2 != null) {
                this.J = this.getAudioClip(new URL(this.getCodeBase(), string2));
            }
            if (string3 != null) {
                this.K = this.getAudioClip(new URL(this.getCodeBase(), string3));
            }
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.o, 0);
            mediaTracker.waitForAll();
        }
        catch (Exception ex) {
            System.out.println(b("mU\u007fb\blP|m\u0010\u001d\u001cpo\u0015ASg.\u0017@]w.\u0012B]tkU"));
            System.out.println(ex.getMessage());
            this.o = null;
        }
        this.u = -1.0;
        this.v = -1;
        this.w = -1;
        this.r = new c(this.p, this.q, int5, 3);
        this.s = new b(this.p, this.q, int3, int4, 6);
        this.t = new b(this.p, this.q, int1, int2, 6);
        if (this.J != null) {
            this.J.stop();
        }
        if (this.K != null) {
            this.K.stop();
        }
        if (a.g != 0) {
            b.h = !h;
        }
    }
    
    public void start() {
        if (!this.getDocumentBase().getHost().equals(this.getCodeBase().getHost())) {
            System.out.println(b("|rG^[nLcb\u001e[\u001cpo\u0015ASg.\tZR3o\u000f\u000fH{g\b\u000fOzz\u001e\u000e"));
            return;
        }
        if (this.e == null) {
            this.L = new GregorianCalendar(TimeZone.getTimeZone(this.i));
            this.D.setCalendar(this.L);
            this.E.setCalendar(this.L);
            this.Q = false;
            this.f = false;
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
        this.s.draw(this.k, 0.104719755 * this.v, graphics);
        this.u = this.L.get(10) + this.v / 60.0;
        this.t.draw(this.l, 0.523598776 * this.u, graphics);
        final int thickness = this.s.getThickness();
        graphics.setColor(this.j);
        graphics.fillOval(this.p - thickness, this.q - thickness, thickness * 2, thickness * 2);
        this.w = this.L.get(13);
        final double n2 = 0.104719755 * this.w;
        if (this.A) {
            this.r.draw(this.j, n2, graphics);
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
        final boolean h = b.h;
        final StringBuffer sb = new StringBuffer();
        sb.append(b("%6.3F\u0012\u0001.3F\u0012\u0001.3F\u0012\u0001.3F\u0012\u0001.3F\u0012\u0001.3F\u0012\u0001.3F\u0012\u0001.\u00049FP\u007f}8CSpeI\u0001\f=?qlScw\tF[{z[\u0007\u007f:.I\u001f\f\".\u0019V\u001cQg\u0017C\u001cTg\u001eC6qi\u0012JPSe\u0018BI\u007fz\u0012BYwg\u001a\u0001_|cqXKd \u0010LQfb\u000fFQvj\u0012N\u0012pa\u0016\u0000O}z\u000bNLz"));
        sb.append(b("%6@z\u001a]Hz`\u001c\u000f~zb\u0017\\\u007f\u007fa\u0018D\u000e3O\u000b_Pvz"));
        sb.append(b("%6Pb\u0014LW3g\b\u000f_f|\tJRgb\u0002\u000fOvz[[S3j\u0012\\L\u007fo\u0002\u000f") + this.i + b("\u000fHzc\u001e\u000fF|`\u001e\u0001"));
        sb.append(b("%6Ja\u000e]\u001cpa\u0016_Igk\t\bO3b\u0014L]\u007f.\u000fFQv.\u0001@Rv.\bJY~}[[S3l\u001e\u0015\u001c") + TimeZone.getDefault().getID());
        sb.append(b("%\u0014Zh[[Tz}[FO3g\u0015LSa|\u001eLH?.\u0002@I3c\u000e\\H3h\u0012W\u001czz[FR3a\tKYa.\u001d@N"));
        sb.append(b("%H{k[LP|m\u0010\u000fH|.\u001fFOcb\u001aV\u001cgf\u001e\u000f_||\tJ_g.\u000fFQv R"));
        Label_0574: {
            if (!h) {
                if (this.N) {
                    final d d = new d();
                    final SNTPProxy sntpProxy = new SNTPProxy(this.getCodeBase().getHost(), this.M);
                    int n = 0;
                    int n2 = 0;
                    while (true) {
                    Label_0270:
                        while (true) {
                            Label_0261: {
                                if (!h) {
                                    break Label_0261;
                                }
                                try {
                                    final SNTPCorrection correction = sntpProxy.getCorrection();
                                    d.addValue(correction.getCorrection(), correction.getDelay());
                                    sb.append(b("%\u007f||\tJ_gg\u0014A\u00063") + d.format(correction.getCorrection()) + b("\u000f\u001c3J\u001eC]j4[") + d.format(correction.getDelay()));
                                    n = 0;
                                }
                                catch (Exception ex) {
                                    if (++n > 2) {
                                        if (!h) {
                                            break Label_0270;
                                        }
                                    }
                                }
                                ++n2;
                            }
                            if (n2 < this.O) {
                                continue;
                            }
                            break;
                        }
                        if (h) {
                            continue;
                        }
                        break;
                    }
                    final d d2 = d;
                    if (!h) {
                        if (d2.getCount() < 2) {
                            sb.append(b("%6@@/\u007f\u001cPa\u0015AYpz\u0012@R3K\t]Sa4[lSfb\u001f\u000fR|z[HYg.\u0016FR~{\u0016\u000fSu.\u000fXS3m\u0014ARvm\u000fFS}}U"));
                            sb.append(b("%o]Z+\u000foj`\u0018GN|`\u0012U]gg\u0014A\u001cpa\u000eCX3`\u0014[\u001cqk[JOgo\u0019CU`f\u001eK\u00103o\u0015K\u001c"));
                            sb.append(b("%Xz}\u000bC]jk\u001f\u000fHzc\u001e\u000fU`.\u0019NOvj[ZL|`[VSf|[LS~~\u000e[Ya}[LP|m\u0010\u0001"));
                            sb.append(b("%h{g\b\u000fU`.\u000b]Sqo\u0019CE3l\u001eL]f}\u001e\u000fE|{[NNv.\u0019JTz`\u001f\u000f]3h\u0012]Ydo\u0017C"));
                            sb.append(b("%Sa.\u000b]Skw[\\Yax\u001e]\u001cgf\u001a[\u001cc|\u001eYY}z\b\u000fH{k[NLcb\u001e[\u001cu|\u0014B\u001cpa\u0015AYpz\u0012A["));
                            sb.append(b("%H|.\u0014ZN3]5{l3~\t@Dj.\bJNek\t\u0001"));
                            if (!h) {
                                break Label_0574;
                            }
                        }
                        this.P = d.getMean();
                    }
                    final double sigma = d2.getSigma();
                    sb.append(b("%6@@/\u007f\u001cCo\u0018DYg}[}Ypk\u0012YYw4[") + d.getCount());
                    sb.append("\n" + (String.valueOf(new StringBuffer(String.valueOf(new StringBuffer(b("cSpo\u0017\u000f\u007f\u007fa\u0018D\u001cz}[")).append(d.format(Math.abs(this.P))).append(b("\u000fOvmU\u000f")).toString())).append((this.P > 0.0) ? b("\\P|y[") : b("I]`z[")).toString()) + b("\u0004\u0013>.") + d.format(sigma) + b("\u000fOvmU")));
                    this.P *= 1000.0;
                    if (!h) {
                        break Label_0574;
                    }
                }
                sb.append(b("%6@@/\u007f\u001c@w\u0015LTaa\u0015FFrz\u0012@R3g\b\u000f_f|\tJRgb\u0002\u000fXz}\u001aMPvjW\u000f]}j"));
            }
            sb.append(b("%Xz}\u000bC]jk\u001f\u000fHzc\u001e\u000fU`.\u0019NOvj[ZL|`[VSf|[LS~~\u000e[Ya}[LP|m\u0010\u0001"));
        }
        BillsClock2 billsClock2 = this;
        if (!h) {
            if (this.I) {
                System.out.println(sb);
            }
            billsClock2 = this;
        }
        billsClock2.Q = true;
        while (true) {
        Label_0632:
            while (true) {
                Label_0625: {
                    if (!h) {
                        break Label_0625;
                    }
                    this.repaint();
                    try {
                        Thread.yield();
                        Thread.sleep(125L);
                    }
                    catch (InterruptedException ex2) {
                        if (!h) {
                            break Label_0632;
                        }
                    }
                }
                if (!this.f) {
                    continue;
                }
                break;
            }
            this.e = null;
            if (!h) {
                return;
            }
            continue;
        }
    }
    
    public void paint(final Graphics graphics) {
        final boolean h = b.h;
        try {
            BillsClock2 billsClock2 = this;
            if (!h) {
                if (this.x == null) {
                    this.x = this.createImage(this.g, this.h);
                    (this.y = this.x.getGraphics()).setFont(new Font(this.G, 0, this.F));
                    this.z = this.y.getFontMetrics();
                }
                billsClock2 = this;
            }
            int n2;
            final int n = n2 = (billsClock2.Q ? 1 : 0);
            Label_0274: {
                if (!h) {
                    if (n != 0) {
                        this.y.setColor(this.m);
                        this.y.fillRect(0, 0, this.g, this.h);
                        this.y.drawImage(this.o, 0, 0, this.g, this.h, this);
                        this.a(this.y);
                        if (!h) {
                            break Label_0274;
                        }
                    }
                    this.y.setColor(this.m);
                    this.y.fillRect(0, 0, this.g, this.h);
                    this.y.setColor(this.n);
                    this.y.setFont(new Font(this.G, 1, this.F));
                    n2 = this.z.stringWidth(this.b);
                }
                this.y.drawString(this.b, this.g / 2 - n2 / 2, this.h / 2);
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
        this.b = b("|Hr`\u001f\u000f^j U\u0001");
        this.f = false;
        this.D = new SimpleDateFormat(b("bq^.\u001fK"));
        this.E = new SimpleDateFormat("a");
        this.H = false;
        this.Q = false;
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0094: {
                if (length > 1) {
                    break Label_0094;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = '/';
                            break;
                        }
                        case 1: {
                            c2 = '<';
                            break;
                        }
                        case 2: {
                            c2 = '\u0013';
                            break;
                        }
                        case 3: {
                            c2 = '\u000e';
                            break;
                        }
                        default: {
                            c2 = '{';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
