import java.net.ProtocolException;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.DataOutputStream;
import java.net.URLConnection;
import java.io.DataInputStream;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.net.URLEncoder;
import java.lang.reflect.Method;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Button;
import java.awt.FontMetrics;
import java.awt.Font;
import java.util.Hashtable;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class personalclock2 extends Applet implements Runnable
{
    private Thread a;
    private int b;
    private int c;
    private boolean d;
    private long e;
    private long f;
    private int g;
    private Color h;
    private static final Color i;
    private static final Color j;
    private static final Color k;
    private static final Color l;
    private static final Color m;
    private static final Color n;
    private static final Color o;
    private static final Color p;
    private static final Color q;
    private static final Color r;
    private boolean s;
    private boolean t;
    private boolean u;
    private boolean v;
    private int w;
    private static Hashtable x;
    private String y;
    private static final String[] z;
    private int A;
    private long B;
    private long C;
    private long D;
    private String E;
    private Font F;
    private Font G;
    private Font H;
    private Font I;
    private Font J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private String Q;
    private char[] R;
    private char[] S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private FontMetrics aa;
    private Button ab;
    private Button ac;
    private Button ad;
    private Button ae;
    private Button af;
    private Panel ag;
    private Panel ah;
    private Label ai;
    private long aj;
    private Image ak;
    private Image al;
    private Graphics am;
    private Graphics an;
    private b ao;
    private boolean ap;
    private boolean aq;
    private int ar;
    private int as;
    private int[] at;
    private int[] au;
    private String av;
    private a[] aw;
    private a ax;
    private a[] ay;
    private a az;
    private a aA;
    private a[] aB;
    private int[] aC;
    private int[] aD;
    
    static {
        i = new Color(232, 232, 232);
        j = new Color(238, 238, 238);
        k = new Color(245, 245, 245);
        l = new Color(204, 204, 221);
        m = new Color(0, 0, 0);
        n = new Color(0, 0, 160);
        o = new Color(0, 0, 0);
        p = new Color(160, 255, 160);
        q = new Color(255, 238, 238);
        r = new Color(102, 255, 102);
        z = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "AM", "PM", "Midnight", "Noon" };
    }
    
    public personalclock2() {
        this.a = null;
        this.g = 0;
        this.h = null;
        this.s = true;
        this.t = true;
        this.u = true;
        this.v = false;
        this.w = 0;
        this.B = 0L;
        this.C = 0L;
        this.Q = null;
        this.S = new char[] { 'E', 'E', 'E', ' ', 'H', 'H', ':', 'm', 'm', ':', 's', 's' };
        this.T = 0;
        this.ag = new Panel();
        this.ah = new Panel();
        this.ai = new Label();
        this.ao = null;
        this.at = new int[50];
        this.au = new int[50];
        this.av = "";
        this.az = new a();
        this.aC = new int[] { 1, 1, 2, 3, 2, 3, 3, 4, 4, 3, 5, 6, 6, 5, 5, 5, 6, 6, 6, 5, 5, 6, 6, 6, 6, 5, 7, 7, 7, 6, 6 };
        this.aD = new int[] { 1, 1, 2, 1, 2, 2, 2, 2, 2, 3, 2, 3, 3, 4, 3, 3, 4, 3, 3, 4, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5 };
    }
    
    public void init() {
        this.aw = new a[50];
        this.ay = new a[50];
        this.a();
        this.setBackground(personalclock2.i);
        this.setFont(this.G);
        this.A = this.a("ftz", 0);
        this.a(this.getParameter("fmm"));
        this.a("wcc", 1);
        this.a("wct", 3);
        this.a("wco", 0);
        this.a("fti", 1);
        this.a("fds", 1);
        this.a("fdl", 1);
        this.a("loggedin", 0);
        for (int i = 0; i < 11; ++i) {
            final String parameter;
            if ((parameter = this.getParameter("d" + i)) != null) {
                personalclock2.z[i] = parameter;
            }
        }
        for (int j = 0; j < 25; ++j) {
            final String string = "p" + j;
            final int a;
            if ((a = this.a(string, 0)) == 0) {
                break;
            }
            final a a2 = new a(a);
            (this.ay[j] = a2).b(this.getParameter(String.valueOf(string) + "a"));
            a2.c(this.getParameter(String.valueOf(string) + "c"));
        }
        if (this.A > 0) {
            (this.ax = new a(this.A)).b(this.getParameter("fhm"));
        }
        this.aB = a.a(this.ay);
        this.aA = this.az.j();
        this.b = this.size().width;
        this.c = this.size().height;
        try {
            this.ak = this.createImage(this.b, this.c);
            a(this.am = this.ak.getGraphics());
        }
        catch (Exception ex) {
            this.am = this.getGraphics();
        }
        try {
            this.al = this.createImage(this.b, this.c);
            a(this.an = this.al.getGraphics());
        }
        catch (Exception ex2) {
            this.an = this.getGraphics();
        }
        this.ag.setLayout(new FlowLayout());
        this.ac = a(this.ag, "Info");
        this.af = a(this.ag, "Share");
        this.ab = a(this.ag, "Setup...");
        this.ae = a(this.ag, "Synchronize");
        this.ad = a(this.ag, "Save");
        this.ah.setLayout(new BorderLayout());
        this.ah.add("West", this.ai);
        this.ah.add("East", this.ag);
        this.setLayout(new BorderLayout());
        this.add("South", this.ah);
        this.c();
    }
    
    private static Button a(final Panel panel, final String s) {
        final Button button;
        (button = new Button(s)).disable();
        panel.add(button);
        return button;
    }
    
    private final void a(String s) {
        if (s == null || s.length() == 0) {
            s = "EEE HH:mm";
        }
        this.R = s.toCharArray();
    }
    
    private final String a(final String s, final boolean b) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 25; ++i) {
            final a a;
            if ((a = this.ay[i]) != null) {
                sb.append(a.a).append(s);
            }
        }
        if (b && this.A > 0) {
            sb.append(this.A).append(s);
        }
        final String string;
        if ((string = sb.toString()).length() > 0) {
            return string.substring(0, string.length() - 1);
        }
        return "";
    }
    
    private final void a() {
        this.I = a.a(16, false);
        this.F = a.a(16, false);
        this.G = a.a(14, false);
        this.H = a.a(11, false);
        this.I = a.a(16, false);
        this.J = a.a(12, false);
        final Graphics graphics;
        (graphics = this.getGraphics()).setFont(this.G);
        this.aa = graphics.getFontMetrics();
    }
    
    final void a(final a a, final a[] array) {
        this.ay = a.a(array);
        this.az = a.j();
        final boolean b;
        if (b = (a.a(this.ay, this.aB) && this.az.a(this.aA))) {
            this.a("Settings unchanged", null);
            this.ad.disable();
        }
        else {
            this.av = "Settings changed, but not saved: Click save";
            this.a(null, (Color)null);
            this.ad.enable();
            if (this.h == null) {
                this.h = this.ad.getBackground();
            }
            this.g = 10;
        }
        this.u = true;
        this.t = true;
    }
    
    private static void a(final Graphics graphics) {
        try {
            final Method[] methods = Class.forName("java2ext").getMethods();
            for (int i = 0; i < methods.length; ++i) {
                if (methods[i].getName().equals("setAntiAlias")) {
                    methods[i].invoke(null, graphics);
                    return;
                }
            }
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private final void a(final String s, final String s2, final Exception ex) {
        String message = "";
        if (ex != null) {
            message = ex.getMessage();
            ex.printStackTrace();
        }
        this.a(String.valueOf(s) + " (Error code: " + s2 + ")", personalclock2.q);
        this.Q = "Error code " + s2 + ":\n" + s + "\n\n" + message;
    }
    
    private final void a(String av, Color i) {
        if (av == null) {
            av = this.av;
        }
        this.ai.setText(av);
        if ("".equals(av)) {
            av = "www.timeanddate.com - Personal World Clock";
        }
        this.showStatus(av);
        if (i == null) {
            i = personalclock2.i;
        }
        else {
            this.aj = System.currentTimeMillis() + 30000L;
        }
        this.ai.setBackground(i);
        this.ah.validate();
    }
    
    private final long b() {
        final long currentTimeMillis;
        if ((currentTimeMillis = System.currentTimeMillis()) < this.f) {
            this.a("System clock changed, synchronization recommended", personalclock2.q);
        }
        else if (this.f > 0L && currentTimeMillis > this.f + 60000L) {
            this.a("Synchronization recommended", personalclock2.q);
        }
        return this.f = currentTimeMillis;
    }
    
    private final int a(final String s, final int n) {
        final String parameter;
        final int n2 = ((parameter = this.getParameter(s)) == null) ? n : Integer.parseInt(parameter);
        this.az.a(s, new Integer(n2));
        return n2;
    }
    
    private final void c() {
        this.D = this.b() + this.B;
    }
    
    private static String a(final long n, final char[] array) {
        int n2 = 0;
        final long n5;
        final int n4;
        final int n3 = (n4 = (int)((n5 = n / 1000L) % 86400L)) / 3600;
        final int n6 = n4 % 3600 / 60;
        final int n7 = n4 % 60;
        final int n8 = ((int)(n5 / 86400L) + 4) % 7;
        final StringBuffer sb = new StringBuffer();
        for (int length = array.length, i = 0; i < length; ++i) {
            final char c = array[i];
            if (n2 != 0) {
                if (c == '\'') {
                    n2 = 0;
                }
                else {
                    sb.append(c);
                }
            }
            else {
                boolean b = true;
                int n9;
                for (n9 = i + 1; n9 < length && array[n9] == c; ++n9) {}
                final int n10 = n9 - i;
                String s = null;
                switch (c) {
                    case 'E': {
                        if (n10 == 1) {
                            s = personalclock2.z[n8].substring(0, 1);
                        }
                        else {
                            s = personalclock2.z[n8];
                        }
                        b = false;
                        break;
                    }
                    case 'a': {
                        if (n3 < 12) {
                            s = personalclock2.z[7];
                        }
                        else {
                            s = personalclock2.z[8];
                        }
                        b = false;
                        break;
                    }
                    case 'H': {
                        s = Integer.toString(n3);
                        break;
                    }
                    case 'K': {
                        s = Integer.toString(n3 % 12);
                        break;
                    }
                    case 'h': {
                        s = Integer.toString((n3 + 11) % 12 + 1);
                        break;
                    }
                    case 'k': {
                        s = Integer.toString((n3 + 23) % 24 + 1);
                        break;
                    }
                    case 'm': {
                        s = Integer.toString(n6);
                        break;
                    }
                    case 's': {
                        s = Integer.toString(n7);
                        break;
                    }
                    case '\'': {
                        n2 = ((n2 == 0) ? 1 : 0);
                        continue;
                    }
                    default: {
                        sb.append(c);
                        continue;
                    }
                }
                if (b) {
                    int j = s.length();
                    while (j < n10) {
                        ++j;
                        sb.append('0');
                    }
                }
                if (n10 > 1) {
                    i += n10 - 1;
                }
                sb.append(s);
            }
        }
        return sb.toString();
    }
    
    private final String a(final long n) {
        return a(n, this.R);
    }
    
    private final int a(final int n) {
        return 24 + n * this.V + this.au[n];
    }
    
    private final Font a(final Graphics graphics, final int n, final int n2, final String s, Font a, final int n3) {
        int stringWidth = n3 + 1;
        FontMetrics fontMetrics;
        while (stringWidth > n3 && (stringWidth = (fontMetrics = graphics.getFontMetrics()).stringWidth(s)) > n3) {
            final int size;
            if ((size = a.getSize()) < 10) {
                a.a(graphics, n, n2 - 5, a.a(s, fontMetrics, n3), size);
                return a;
            }
            a = a.a(size - 1, false);
            graphics.setFont(a);
            this.d = true;
        }
        graphics.drawString(s, n - stringWidth / 2, n2);
        return a;
    }
    
    private final void a(final StringBuffer sb, final String s, final int n) {
        a(sb, s, Integer.toString(n));
    }
    
    private static void a(final StringBuffer sb, final String s, final String s2) {
        if (s2 != null) {
            sb.append('&');
            sb.append(s);
            sb.append('=');
            for (int length = s2.length(), i = 0; i < length; ++i) {
                final char char1;
                if ((char1 = s2.charAt(i)) > '\u00ff' || char1 < '\0') {
                    sb.append(URLEncoder.encode(s2.substring(0, i)));
                    final StringBuffer sb2 = new StringBuffer();
                    while (i < length) {
                        final char char2;
                        if ((char2 = s2.charAt(i)) > '\u00ff') {
                            sb2.append("&#").append((int)char2).append(';');
                        }
                        else if (char2 < '\0') {
                            sb2.append("&#").append(char2 + 65536).append(';');
                        }
                        else {
                            sb2.append(char2);
                        }
                        ++i;
                    }
                    sb.append(URLEncoder.encode(sb2.toString()));
                    return;
                }
            }
            sb.append(URLEncoder.encode(s2));
        }
    }
    
    private void b(final int w) {
        if (this.w != w) {
            this.w = w;
        }
        else {
            this.w = 0;
        }
        this.s = true;
        this.repaint();
    }
    
    public boolean handleEvent(final Event event) {
        String string = null;
        String s = null;
        final int x = event.x;
        final int y = event.y;
        if (this.w == 0) {
            for (int i = 0; i < this.X; ++i) {
                final int a;
                final int n = (a = this.a(i)) + this.V + this.at[i];
                for (int j = 0; j < this.W; ++j) {
                    final int n2 = 7 + j * this.U;
                    final a a2;
                    if (x >= n2 && x < n2 + this.U && y >= a && y < n && (a2 = this.aw[i * this.W + j]) != null) {
                        s = "http://www.timeanddate.com/worldclock/city.html?n=" + a2.a;
                        string = "Click for more information about " + a2.h();
                    }
                }
            }
        }
        if (this.w == 2 && y > 47 && y < 67) {
            s = this.E;
            string = "Click to go to linkable/shared version";
        }
        if (y < 19) {
            s = "http://www.timeanddate.com/";
            string = "Click to go to timeanddate.com home page";
        }
        if (event.id == 1001) {
            if (this.ac.equals(event.target)) {
                this.b(1);
                return true;
            }
            if (this.af.equals(event.target)) {
                this.b(2);
                return true;
            }
            if (this.ab.equals(event.target)) {
                if (this.ao == null) {
                    this.ao = new b(this, this.az);
                }
                this.ao.a(this.az, this.aw);
                this.ao.show();
                this.ao.toFront();
            }
            if (this.ae.equals(event.target)) {
                this.t = true;
                this.repaint();
            }
            if (this.ad.equals(event.target)) {
                this.i();
                return true;
            }
        }
        if (event.id == 501) {
            if (s != null) {
                if (this.w != 1) {
                    if (this.ad.isEnabled()) {
                        return true;
                    }
                }
                try {
                    this.getAppletContext().showDocument(new URL(s));
                }
                catch (MalformedURLException ex) {}
            }
            return true;
        }
        if (this.w > 0 && this.equals(event.target) && (event.id == 501 || event.id == 401 || event.id == 1004)) {
            this.w = 0;
            this.s = true;
            this.repaint();
            return true;
        }
        if (event.id == 503 && !this.ad.isEnabled()) {
            this.a(string, null);
        }
        return false;
    }
    
    public void update(final Graphics graphics) {
        this.c();
        this.f();
        this.paint(graphics);
        if (this.g > 0) {
            --this.g;
            this.ad.setBackground((this.g % 2 == 1) ? personalclock2.p : this.h);
        }
        if (this.aj > 0L && this.aj < this.b()) {
            this.ai.setBackground(personalclock2.i);
            this.aj = 0L;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.al, 0, 0, null);
    }
    
    private final void d() {
        this.V = (this.N - this.O) / this.X;
        this.as = this.V - 29;
        this.ar = 21;
        if (this.g() > 0) {
            this.as -= 12;
            this.ar += 12;
        }
        if (this.ap) {
            this.as -= 16;
        }
        if (this.as < 20) {
            this.aq = false;
            this.ap = true;
        }
        if (this.as > this.Y) {
            this.as = this.Y;
        }
    }
    
    private final void a(final Graphics graphics, final int n, int n2, int n3, int n4, int n5, final boolean b, final boolean b2) {
        if (n4 > n5) {
            n2 += (n4 - n5) / 2;
            n4 = n5;
        }
        if (n5 > n4) {
            n3 += (n5 - n4) / 2;
            n5 = n4;
        }
        ++n2;
        ++n3;
        n4 -= 2;
        n5 -= 2;
        n4 = n4 / 2 * 2;
        n5 = n5 / 2 * 2;
        final int n6 = n2 + n4 / 2;
        final int n7 = n3 + n5 / 2;
        if (!b) {
            graphics.setColor(Color.white);
            graphics.fillOval(n2, n3, n4 + 1, n5 + 1);
            graphics.setColor(Color.black);
            graphics.drawOval(n2, n3, n4, n5);
            graphics.setColor(Color.black);
            for (int i = 0; i < 60; ++i) {
                if (i % 5 == 0 || n5 >= 200) {
                    if (i % 15 == 0 || n5 >= 30) {
                        a(graphics, n6, n7, n5 / 2 + 1, 3.141592653589793 * i / 30.0, a(0, (i % 15 == 0) ? 15 : ((i % 5 == 0) ? 30 : 150), n5 / 3), false);
                    }
                }
            }
        }
        if (b) {
            final int n8 = n % 86400;
            graphics.setColor(Color.black);
            final double n9 = 0.28f * n5;
            a(graphics, n6, n7, n9, 3.141592653589793 * n8 / 21600.0, a(3, 7, n9), true);
            graphics.setColor(Color.blue);
            final double n10 = 0.45f * n5;
            a(graphics, n6, n7, n10, 3.141592653589793 * n8 / 1800.0, a(2, 20, n10), true);
            if (b2) {
                graphics.setColor(Color.lightGray);
                final double n11 = 0.48f * n5;
                a(graphics, n6, n7, n11, 3.141592653589793 * n8 / 30.0, a(1, 40, n11), true);
            }
        }
    }
    
    private static double[] a(int n, int n2, final double n3) {
        if (n2 > n3) {
            n2 = (int)n3;
        }
        final Integer n4 = new Integer(n2 * 10 + n);
        if (personalclock2.x == null) {
            personalclock2.x = new Hashtable();
        }
        final double[] array;
        if ((array = personalclock2.x.get(n4)) != null) {
            return array;
        }
        if (n > 4 || n < 0) {
            n = 1;
        }
        int n5 = 8;
        double n6 = 1.0;
        if (n == 0) {
            n6 = 1.0;
        }
        if (n <= 1) {
            n5 = 4;
        }
        if (n == 3) {
            n6 = 0.5;
        }
        if (n == 4) {
            n6 = 0.0;
        }
        final double[] array2 = new double[n5];
        personalclock2.x.put(n4, array2);
        double n7 = Math.sqrt(n2 * n2 + n6 * n6);
        array2[0] = n7 / n2;
        array2[1] = Math.atan(n6 / n2);
        if (n == 0) {
            final double n8;
            n7 = Math.sqrt((n8 = n2 - n6 - n6) * n8 + n6 * n6);
            array2[2] = n7 / n2;
            array2[3] = Math.atan(n6 / n8);
        }
        if (n == 1) {
            array2[2] = 1.414212942123413 / n7;
            array2[3] = 2.356194019317627;
        }
        if (n > 1) {
            final double n9 = 1.1547000408172607 / n2;
            array2[2] = n9;
            array2[3] = 1.0471999645233154;
            array2[4] = n9;
            array2[5] = 1.8322999477386475;
            array2[6] = n9;
            array2[7] = 2.617990016937256;
        }
        return array2;
    }
    
    private static void a(final Graphics graphics, final int n, final int n2, double n3, final double n4, final double[] array, final boolean b) {
        final int length;
        final int[] array2 = new int[length = array.length];
        final int[] array3 = new int[length];
        for (int n5 = length / 2, i = 0; i < n5; ++i) {
            final int n6 = length - i - 1;
            final double n7 = array[i * 2] * n3;
            final double n8 = array[i * 2 + 1] + n4;
            array2[i] = n + (int)(n7 * Math.sin(n8));
            array3[i] = n2 + (int)(-n7 * Math.cos(n8));
            final double n9 = -array[i * 2 + 1] + n4;
            array2[n6] = n + (int)(n7 * Math.sin(n9));
            array3[n6] = n2 + (int)(-n7 * Math.cos(n9));
        }
        graphics.fillPolygon(array2, array3, length);
        if (b) {
            --n3;
            graphics.drawLine(n, n2, n + (int)(n3 * Math.sin(n4)), n2 + (int)(-n3 * Math.cos(n4)));
        }
    }
    
    private final void e() {
        this.s = false;
        this.M = this.c - 20 - this.ag.size().height;
        this.L = this.M + 14;
        this.N = this.M - 25;
        final int n = this.b - 10;
        this.P = this.b - 140;
        this.am.setColor(personalclock2.i);
        this.am.fillRect(0, 0, this.b, this.M);
        this.am.setColor(personalclock2.l);
        this.am.fillRect(0, this.M, this.b, this.c);
        this.am.setColor(personalclock2.n);
        this.am.setFont(this.G);
        this.am.drawString("www.timeanddate.com - Personal World Clock", 5, 18);
        this.am.setColor(personalclock2.o);
        this.am.setFont(this.G);
        if (this.v) {
            this.am.drawString("UTC/GMT time:", this.P - this.aa.stringWidth("UTC/GMT time:"), this.L);
        }
        if (this.ax != null && this.ax.a()) {
            this.y = String.valueOf(this.ax.d()) + " time:";
            this.am.drawString(this.y, 5, this.L);
        }
        this.am.setColor(personalclock2.n);
        this.am.setFont(this.F);
        if (this.Q != null) {
            this.am.drawString("There was an error: ", 5, 200);
            this.am.setFont(this.H);
            a(this.am, 5, 220, this.Q);
            return;
        }
        if (this.w == 1) {
            this.am.drawString("How to use this clock", 5, 45);
            this.am.setFont(this.G);
            this.am.setColor(personalclock2.o);
            a(this.am, 10, 70, "The Info Button shows this text\nPress Setup to change cities and configuration\nPress Synchronize to get current UTC time from server\nPress Save to change your configuration\n\nThis applet may NOT be copied or used other places than www.timeanddate.com\nYou use this applet entirely on your own risk.");
            if (this.C > 0L) {
                this.am.drawString("Synchronize status: At loading time, the time differed by less than " + this.C / 1000.0f + " second(s) from atomic time", 5, 260);
            }
            else {
                this.am.drawString("Synchronize status: Clock is NOT synchronized, using local computer clock", 5, 260);
            }
            a(this.am, 5, 285, "If you or a program change your local computer clock, these clocks will no longer be synchronized,\nso please click the synchronize button afterwards.");
            return;
        }
        if (this.w == 2) {
            this.am.drawString("How to share your Personal World Clock", 5, 45);
            this.E = "http://www.timeanddate.com/worldclock/personalapplet.html?cities=" + this.a(",", false);
            a(this.am, 5, 90, "The above URL can be sent to other people, and they will see the same cities as you are.\nClick on the URL above for bookmarking or to copy the address.");
            if (this.ad.isEnabled()) {
                this.am.drawString("Please Save before following the link above", 5, 130);
            }
            if (this.az.d("loggedin") == 1) {
                this.am.drawString("Note that personal comments and alternative names will not be available through the link", 5, 150);
            }
            this.a(this.am, this.b / 2, 65, this.E, this.G, this.b);
            return;
        }
        if (!this.t && this.T == 0) {
            this.am.setColor(personalclock2.o);
            a(this.am, 30, 75, "Welcome to the Personal World Clock - Applet version\n\nYou must add some cities first...\n\nPress the \"Setup...\" Button to do this");
            return;
        }
        final int n2 = this.az.d("wct") & 0x3;
        this.ap = (n2 != 2);
        this.aq = (n2 != 1);
        this.W = this.az.d("wco");
        if (this.W > 0) {
            if (this.W > this.T) {
                this.W = this.T;
            }
        }
        else if (this.aq) {
            this.W = this.aC[this.T];
        }
        else {
            this.W = this.aD[this.T];
        }
    Label_1043:
        do {
            this.X = (this.T + this.W - 1) / this.W;
            if (this.X < 1) {
                this.X = 1;
            }
            this.U = n / this.W;
            this.Y = this.U - 4;
            int i = this.J.getSize();
            while (true) {
                while (i > 11) {
                    this.O = this.a(this.am.getFontMetrics(this.J), this.at);
                    this.d();
                    if (this.as < 0 && i > 8) {
                        this.J = a.a(i, false);
                        --i;
                    }
                    else {
                        while (this.as < 0 && this.O > 0) {
                            int n3 = this.at[0];
                            int n4 = 0;
                            for (int j = 1; j < this.X; ++j) {
                                if (this.at[j] > n3) {
                                    n3 = this.at[j];
                                    n4 = j;
                                }
                            }
                            this.O += this.as;
                            final int[] at = this.at;
                            final int n5 = n4;
                            at[n5] += this.as;
                            this.d();
                        }
                        if (this.as < 0) {
                            ++this.W;
                            continue Label_1043;
                        }
                        continue Label_1043;
                    }
                }
                continue;
            }
        } while (this.as < 0 && this.W < 8);
        int n6 = this.at[0];
        for (int k = 1; k < this.X; ++k) {
            this.au[k] = n6;
            n6 += this.at[k];
        }
        for (int l = 0; l < this.X; ++l) {
            this.Z = this.V - 4 + this.at[l];
            for (int n7 = 0; n7 < this.W; ++n7) {
                final a a;
                if ((a = this.aw[l * this.W + n7]) != null) {
                    final int n8 = 7 + n7 * this.U;
                    final int a2 = this.a(l);
                    final int n9 = n8 + this.U / 2 - 2;
                    this.am.setColor(personalclock2.j);
                    this.am.fillRect(n8, a2, this.Y, this.Z);
                    this.am.setColor(personalclock2.k);
                    this.am.fillRect(n8 + 2, a2 + 2, this.Y - 4, this.Z - 4);
                    this.am.clipRect(n8 + 1, a2 + 1, this.Y - 2, this.Z - 2);
                    this.am.setColor(personalclock2.n);
                    final String d = a.d();
                    this.am.setFont(this.F);
                    this.a(this.am, n9, a2 + 17, d, this.F, this.Y);
                    final int g;
                    if ((g = this.g()) > 0) {
                        String s;
                        if (g == 3) {
                            s = a.e();
                        }
                        else {
                            s = a.f();
                        }
                        this.am.setFont(this.H);
                        this.a(this.am, n9, a2 + 29, s, this.H, this.Y);
                    }
                    int n10 = a2 + this.ar;
                    if (this.aq) {
                        final int n11 = this.as / 2 - 2;
                        this.a(this.am, 0, n9 - n11, n10, n11 * 2, n11 * 2, false, true);
                        n10 += this.as + 4;
                    }
                    this.am.setColor(Color.black);
                    this.am.setFont(this.J);
                    a.a(this.am, n9, n10 + 15 + this.K);
                    this.am.setClip(null);
                }
            }
        }
    }
    
    private final void f() {
        this.d = false;
        final boolean b = this.az.b("wct", 8);
        final boolean b2 = !this.az.b("wct", 16);
        final boolean b3 = this.az.b("wct", 32);
        if (this.s) {
            this.e();
        }
        this.an.drawImage(this.ak, 0, 0, this);
        this.an.setFont(this.G);
        this.an.setColor(personalclock2.m);
        this.an.setFont(this.F);
        if (this.v) {
            this.an.drawString(a(this.D, this.S), this.P + 5, this.L);
        }
        if (this.ax != null && this.ax.a()) {
            this.an.drawString(this.a(this.ax.a(this.D)), 11 + this.aa.stringWidth(this.y), this.L);
        }
        if (this.w > 0) {
            return;
        }
        for (int i = 0; i < this.X; ++i) {
            for (int j = 0; j < this.W; ++j) {
                final a a;
                if ((a = this.aw[i * this.W + j]) != null) {
                    final int n = 7 + j * this.U;
                    final int a2 = this.a(i);
                    final int n2 = n + this.U / 2 - 2;
                    final long a3;
                    final int n3 = (int)((a3 = a.a(this.D)) / 1000L);
                    int n4 = a2 + this.ar;
                    if (a3 > 0L) {
                        if (this.aq) {
                            final int n5 = this.as / 2 - 2;
                            this.a(this.an, n3, n2 - n5, n4, n5 * 2, n5 * 2, true, b3);
                            n4 += this.as;
                        }
                        if (this.ap) {
                            String s = this.a(a3);
                            if (b) {
                                s = String.valueOf(s) + " " + a.b(this.D);
                            }
                            if (b2 && a.c(this.D)) {
                                s = String.valueOf(s) + "*";
                            }
                            this.an.setColor(personalclock2.o);
                            this.an.setFont(this.I);
                            this.I = this.a(this.an, n2, n4 + this.K, s, this.I, this.Y);
                        }
                    }
                }
            }
        }
        if (this.d) {
            this.f();
        }
    }
    
    private final int a(final FontMetrics fontMetrics, final int[] array) {
        this.O = 0;
        final int height = fontMetrics.getHeight();
        for (int i = 0; i < this.X; ++i) {
            int n = 0;
            for (int j = 0; j < this.W; ++j) {
                final a a;
                final int a2;
                if ((a = this.aw[i * this.W + j]) != null && (a2 = a.a(fontMetrics, this.Y)) > n) {
                    n = a2;
                }
            }
            final int n2 = n * height;
            array[i] = n2;
            this.O += n2;
        }
        this.K = fontMetrics.getAscent();
        return this.O;
    }
    
    private final int g() {
        return this.az.d("wcc") & 0x3;
    }
    
    private static void a(final Graphics graphics, final int n, int n2, String substring) {
        final int height = graphics.getFontMetrics().getHeight();
        while (substring.length() > 0) {
            final int index = substring.indexOf(10);
            String substring2 = substring;
            if (index >= 0) {
                substring2 = substring.substring(0, index);
                substring = substring.substring(index + 1);
            }
            else {
                substring = "";
            }
            graphics.drawString(substring2, n, n2);
            n2 += height;
        }
    }
    
    private static long b(final String s) {
        return (long)(Double.valueOf(s) * 1000.0);
    }
    
    private static String c(final String s) {
        int i;
        if ((i = s.indexOf("&#")) >= 0) {
            final StringBuffer sb = new StringBuffer();
            int index = 0;
            do {
                sb.append(s.substring(index, i));
                if ((index = s.indexOf(";", i)) <= 0) {
                    index = i;
                    break;
                }
                sb.append((char)Integer.parseInt(s.substring(i + 2, index)));
                i = s.indexOf("&#", index++);
            } while (i >= 0);
            sb.append(s.substring(index));
            return sb.toString();
        }
        return s;
    }
    
    private final void h() {
        this.s = true;
        this.Q = null;
        this.repaint();
        String string = "";
        if (this.u) {
            this.a("Loading... please wait...", null);
            string = "cities=" + this.a("_", true) + "&wct=" + this.az.d("wct") + "&fti=" + this.az.d("fti") + "&fds=" + this.az.d("fds") + "&fdl=" + this.az.d("fdl") + "&";
        }
        else {
            this.a("Synchronizing... please wait...", null);
        }
        this.repaint();
        final long b = this.b();
        this.e = b + 86400000L;
        try {
            final URLConnection openConnection;
            (openConnection = new URL("http://www.timeanddate.com/worldclock/utc2.php?" + string + "dummy=" + b).openConnection()).setUseCaches(false);
            final DataInputStream dataInputStream;
            final String line;
            if ((line = (dataInputStream = new DataInputStream(openConnection.getInputStream())).readLine()) != null) {
                final long b2 = this.b();
                this.C = (b2 - b) / 2L;
                final long n = b2 - this.C;
                this.C += 100L;
                this.B = b(line) - n;
                this.v = true;
            }
            if (this.u) {
                String line2;
                while ((line2 = dataInputStream.readLine()) != null) {
                    final String c;
                    final int index;
                    if ((index = (c = c(line2)).indexOf(" ")) > 0) {
                        final String substring = c.substring(0, index);
                        final String substring2 = c.substring(index + 1);
                        if (Character.isDigit(substring.charAt(0))) {
                            final int int1 = Integer.parseInt(substring);
                            for (int i = 0; i < 25; ++i) {
                                if (this.ay[i] != null && this.ay[i].a == int1) {
                                    this.ay[i].a(substring2);
                                }
                            }
                            if (this.A != int1) {
                                continue;
                            }
                            this.ax.a(substring2);
                        }
                        else if ("et".equals(substring)) {
                            this.e = 1000L * Long.parseLong(substring2);
                        }
                        else {
                            if (!"fmm".equals(substring)) {
                                continue;
                            }
                            this.a(substring2);
                        }
                    }
                }
                final a[] aw = new a[this.aw.length];
                int t = 0;
                for (int j = 0; j < 25; ++j) {
                    if (this.ay[j] != null && this.ay[j].a()) {
                        aw[t++] = this.ay[j];
                    }
                }
                this.aw = aw;
                this.T = t;
                dataInputStream.close();
            }
        }
        catch (MalformedURLException ex) {
            this.a("Error loading cities", "D1", ex);
            return;
        }
        catch (Exception ex2) {
            this.a("Error loading cities", "D2", ex2);
        }
        if (this.Q == null) {
            if (this.u) {
                this.a(null, (Color)null);
            }
            else {
                this.a("Clock was synchronized", null);
            }
            this.ac.enable();
            this.ae.enable();
            this.ab.enable();
            this.af.enable();
        }
        this.t = false;
        this.u = false;
        this.s = true;
        this.repaint();
    }
    
    private static String a(final URLConnection urlConnection, final String s) throws IOException {
        urlConnection.setDoInput(true);
        if (s != null) {
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("Content-Length", Integer.toString(s.length()));
            final DataOutputStream dataOutputStream;
            (dataOutputStream = new DataOutputStream(urlConnection.getOutputStream())).writeBytes(s);
            dataOutputStream.flush();
            dataOutputStream.close();
        }
        final DataInputStream dataInputStream = new DataInputStream(urlConnection.getInputStream());
        String substring = "-1";
        String line;
        while ((line = dataInputStream.readLine()) != null) {
            final int index;
            if ((index = line.indexOf("RESULT:")) >= 0) {
                substring = line.substring(index + 7);
            }
        }
        dataInputStream.close();
        return substring;
    }
    
    private final void i() {
        final StringBuffer sb = new StringBuffer("sel=" + this.a(";", false));
        this.a(sb, "wct", this.az.d("wct"));
        this.a(sb, "wcc", this.az.d("wcc"));
        this.a(sb, "wco", this.az.d("wco"));
        final String s = "http://www.timeanddate.com/custom/savepersonal.php?";
        try {
            if (this.az.d("loggedin") == 1) {
                for (int i = 0; i < 25; ++i) {
                    final a a;
                    if ((a = this.ay[i]) != null) {
                        a(sb, "p" + i + "a", a.g());
                        a(sb, "p" + i + "c", a.i());
                    }
                }
                final String string = String.valueOf(s) + "from=inapplet&part=1";
                String s2 = "-1";
                String s3 = sb.toString();
                this.Q = null;
                try {
                    final HttpURLConnection httpURLConnection;
                    (httpURLConnection = (HttpURLConnection)new URL(string).openConnection()).setRequestMethod("POST");
                    s2 = a(httpURLConnection, s3);
                }
                catch (ClassCastException ex4) {
                    for (int j = 1; j < 20; ++j) {
                        if (s3.length() <= 0) {
                            break;
                        }
                        int n = s3.length();
                        if (s3.length() > 900 && (n = s3.lastIndexOf(38, 900)) < 0 && (n = s3.indexOf(38, 900)) < 0) {
                            n = 900;
                        }
                        if ((s2 = a(new URL(String.valueOf(string) + "&subpart=" + j + "&cnt&" + s3.substring(0, n)).openConnection(), null)).length() != 0) {
                            break;
                        }
                        s3 = s3.substring(n);
                    }
                }
                if (s2.length() == 0) {
                    this.av = "Settings were saved";
                    this.ad.disable();
                    this.aB = a.a(this.ay);
                    this.aA = this.az.j();
                    this.a(null, personalclock2.r);
                }
                else {
                    this.a("Failed to save, please try again later", "S" + s2, null);
                }
            }
            else {
                this.getAppletContext().showDocument(new URL(String.valueOf(s) + "from=applet&part=1&" + (Object)sb));
            }
            if (this.g > 1) {
                this.g = 1;
            }
        }
        catch (MalformedURLException ex) {
            this.a("Error during save", "S1", ex);
        }
        catch (ProtocolException ex2) {
            this.a("Error during save", "S2", ex2);
        }
        catch (IOException ex3) {
            this.a("Error during save", "S3", ex3);
        }
    }
    
    public void start() {
        if (this.a == null) {
            (this.a = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.ao != null) {
            this.ao.hide();
        }
        this.a = null;
    }
    
    public void run() {
        long n = 0L;
        while (this.a != null) {
            this.c();
            if (this.e > 0L && this.D > this.e) {
                this.u = true;
                this.t = true;
            }
            if (this.t) {
                this.h();
            }
            final long n2;
            if ((n2 = this.D / 1000L) != n) {
                this.repaint();
                n = n2;
            }
            this.c();
            try {
                Thread.sleep((int)(1001L - this.D % 1000L));
            }
            catch (InterruptedException ex) {}
        }
        this.a = null;
    }
}
