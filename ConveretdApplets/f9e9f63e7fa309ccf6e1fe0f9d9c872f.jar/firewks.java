import java.awt.Event;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Color;
import java.applet.AudioClip;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class firewks extends Applet implements Runnable, ImageObserver
{
    Thread a;
    Lware b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    boolean j;
    int k;
    float l;
    private Image m;
    int n;
    int o;
    int p;
    long q;
    Frame r;
    boolean s;
    boolean t;
    AudioClip u;
    public int RocketStyleVariability;
    public int maxfireworks;
    public int fireworkspower;
    public int fireworksdensity;
    public int fireworksduration;
    public int gravity;
    private int v;
    private int w;
    private firewk[] x;
    int y;
    int z;
    int A;
    int B;
    int C;
    Color D;
    String E;
    String F;
    private Image G;
    private Graphics H;
    Image I;
    private Image J;
    private Graphics K;
    boolean L;
    boolean M;
    final String N = "\u0005\u0005'p\\0U5e\u0019\u0002\u00145uVd6>iZ'\u001cw4N3\u0002y}W\"\f";
    boolean O;
    URL P;
    boolean Q;
    Toolkit R;
    boolean S;
    int T;
    int U;
    int V;
    int W;
    long X;
    URL Y;
    int Z;
    int ba;
    int bb;
    int bc;
    int bd;
    int be;
    int bf;
    String bg;
    int bh;
    boolean bi;
    boolean bj;
    Color bk;
    Color bl;
    Font bm;
    int bn;
    int bo;
    int bp;
    int bq;
    int[] br;
    int[] bs;
    String[] bt;
    int bu;
    int bv;
    Font[] bw;
    int bx;
    int by;
    private int bz;
    int bA;
    int bB;
    int bC;
    int bD;
    int bE;
    int[] bF;
    int bG;
    int bH;
    float bI;
    float bJ;
    int bK;
    int bL;
    String bM;
    public static int bN;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.m) {
            if (n == 16) {
                this.L = true;
            }
            return true;
        }
        return true;
    }
    
    public void destroy() {
        if (this.b != null) {
            this.b.hide();
        }
        this.b = null;
        if (this.m != null) {
            this.m.flush();
        }
        this.m = null;
        if (this.J != null) {
            this.J.flush();
        }
        this.J = null;
        if (this.K != null) {
            this.K.dispose();
        }
        this.K = null;
        if (this.x != null) {
            this.x = null;
        }
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.M) {
            this.notifyAll();
            while (!this.L) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.L = false;
        }
        this.K.drawImage(this.m, this.n, this.o, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.m, this);
        for (int i = 0; i < 3; ++i) {
            this.notifyAll();
            Thread.yield();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        return this.L;
    }
    
    Image a(final String s) {
        try {
            return this.b(s);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            return this.b(s);
        }
    }
    
    synchronized Image b(final String s) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Label_0170: {
            try {
                try {
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream(url.toString());
                    if (resourceAsStream == null) {
                        break Label_0170;
                    }
                    final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                    final byte[] array = new byte[512];
                    boolean b = false;
                    byte[] byteArray;
                    try {
                        while (!b) {
                            final int read = resourceAsStream.read(array, 0, 512);
                            if (read != -1) {
                                byteArrayOutputStream.write(array, 0, read);
                                byteArrayOutputStream.flush();
                            }
                            else {
                                b = true;
                            }
                        }
                        byteArray = byteArrayOutputStream.toByteArray();
                        byteArrayOutputStream.close();
                        resourceAsStream.close();
                    }
                    catch (IOException ex2) {
                        byteArray = null;
                    }
                    System.gc();
                    if (byteArray != null) {
                        image = this.getToolkit().createImage(byteArray);
                        this.prepareImage(image, this);
                    }
                }
                catch (NoSuchMethodError noSuchMethodError) {}
            }
            catch (SecurityException ex3) {}
        }
        if (image == null) {
            int i = 0;
            while (i < 5) {
                try {
                    if (i % 2 == 0) {
                        image = Toolkit.getDefaultToolkit().getImage(url);
                    }
                    else {
                        image = this.getImage(url);
                    }
                    ++i;
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    this.notifyAll();
                    Thread.currentThread();
                    Thread.yield();
                    try {
                        mediaTracker.addImage(image, 0);
                        mediaTracker.waitForID(0);
                    }
                    catch (InterruptedException ex4) {
                        image = null;
                    }
                    if (mediaTracker.isErrorID(0)) {
                        image = null;
                    }
                    else {
                        i = 6;
                    }
                }
                catch (NullPointerException ex5) {
                    System.gc();
                }
            }
        }
        if (image == null) {
            for (int j = 0; j < 25; ++j) {
                this.showStatus(c("\r\u00186{\\d") + s + c("d\u001b8h\u0019\"\u001a\"r]e"));
                try {
                    Thread.currentThread();
                    Thread.sleep(250L);
                }
                catch (InterruptedException ex6) {}
            }
        }
        else {
            while (image.getWidth(this) < 0) {
                this.notifyAll();
                Thread.currentThread();
                Thread.yield();
                try {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex7) {}
            }
        }
        return image;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("\u0000\u001a9;Md\u00072qV2\u0010wkN3[6r_=\u00012}Tj\u00168q\u0019'\u00072xP0\u0006wpP*\u0010wuWd=\u0003Que"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void init() {
        final boolean m = firewk.M;
        this.setLayout(null);
        this.addNotify();
        this.bM = this.getParameter(c("7\u00016hL7\u0018${"));
        this.y = this.size().width;
        this.z = this.size().height;
        this.A = this.y / 2;
        this.B = this.z / 2;
        final Dimension size = this.size();
        this.d = size.width;
        this.e = size.height;
        this.f = size.width;
        this.g = size.height;
        final String parameter;
        final String s = parameter = this.getParameter(c("'\u00072xP0\u0006"));
        String c = null;
        Label_0201: {
            Label_0170: {
                Label_0166: {
                    if (!m) {
                        if (parameter == null) {
                            break Label_0166;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (m) {
                            break Label_0201;
                        }
                    }
                    if (parameter.startsWith(c("\u0005\u0005'p\\0U5e\u0019\u0002\u00145uVd6>iZ'\u001cw4N3\u0002y}W\"\f"))) {
                        break Label_0170;
                    }
                    this.a();
                    if (!m) {
                        break Label_0170;
                    }
                    int bn = firewks.bN;
                    firewks.bN = ++bn;
                }
                this.a();
            }
            (this.b = new Lware(this, c("\u0002\u001c%yN+\u0007<o\u0019%\u0005'p\\0"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("\"\u001c;y");
            }
        }
        String s3;
        try {
            s3 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s3 = "";
        }
        s3.toLowerCase();
        c.toLowerCase();
        final String parameter2;
        Label_0741: {
            Label_0732: {
                int n3 = 0;
                int startsWith = 0;
                Label_0327: {
                    Label_0317: {
                        if (!m) {
                            Label_0307: {
                                if (!c.equals(c("\"\u001c;y"))) {
                                    int n2;
                                    final int n = n2 = (startsWith = (n3 = s3.length()));
                                    if (!m) {
                                        if (n < 1) {
                                            break Label_0307;
                                        }
                                        final int n4;
                                        n2 = (n4 = (startsWith = (n3 = (s3.startsWith(c("(\u001a4}U")) ? 1 : 0))));
                                    }
                                    if (!m) {
                                        if (n != 0) {
                                            break Label_0307;
                                        }
                                        startsWith = (n2 = (n3 = (s3.equals(c("uG`2\tjEy-")) ? 1 : 0)));
                                    }
                                    if (m) {
                                        break Label_0327;
                                    }
                                    if (n2 == 0) {
                                        break Label_0317;
                                    }
                                }
                            }
                            this.O = true;
                        }
                        if (!m) {
                            break Label_0732;
                        }
                    }
                    n3 = (startsWith = (s3.startsWith(c("3\u0002 2")) ? 1 : 0));
                }
                if (!m) {
                    if (startsWith != 0) {
                        s3 = s3.substring(4);
                    }
                    n3 = s3.length();
                }
                final int n6;
                final int n5 = n6 = n3;
                if (m || n6 > 0) {
                    final char[] array = new char[n6];
                    s3.getChars(0, n5, array, 0);
                    int n7 = 0;
                    while (true) {
                        while (true) {
                            Label_0410: {
                                if (!m) {
                                    break Label_0410;
                                }
                                final char[] array2 = array;
                                final int n8 = n7;
                                if (m || array2[n8] == '0') {
                                    array2[n8] = '1';
                                }
                                n7 += 5;
                            }
                            if (n7 < n5) {
                                continue;
                            }
                            break;
                        }
                        if (m) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("6\u00100\u007fV \u0010"));
                if (m) {
                    break Label_0741;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (m) {
                        break Label_0741;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n9 = 1;
                        try {
                            int n10 = 0;
                            while (true) {
                                while (true) {
                                    Label_0506: {
                                        if (!m) {
                                            break Label_0506;
                                        }
                                        if (s4.charAt(n10) == '+') {
                                            ++n9;
                                        }
                                        ++n10;
                                    }
                                    if (n10 < s4.length()) {
                                        continue;
                                    }
                                    break;
                                }
                                if (m) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n9];
                        final int n11 = n9;
                        if (!m && n11 == 1) {
                            array3[0] = s4.length();
                            if (m) {
                                goto Label_0556;
                            }
                        }
                        else {
                            int n12 = n11;
                            try {
                                int n13 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0592: {
                                            if (!m) {
                                                break Label_0592;
                                            }
                                            if (s4.charAt(n13) == '+') {
                                                array3[n12] = n13;
                                                ++n12;
                                            }
                                            ++n13;
                                        }
                                        if (n13 < s4.length()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (m) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex4) {}
                            array3[n12] = s4.length();
                        }
                        final String[] array4 = new String[n9];
                        int n14 = 0;
                        int n15 = 0;
                        int n16;
                        while (true) {
                            while (true) {
                                Label_0672: {
                                    if (!m) {
                                        break Label_0672;
                                    }
                                    try {
                                        array4[n15] = s4.substring(n14, array3[n15]);
                                    }
                                    catch (StringIndexOutOfBoundsException ex5) {}
                                    n14 = array3[n15] + 1;
                                    ++n15;
                                }
                                if (n15 < n9) {
                                    continue;
                                }
                                break;
                            }
                            n16 = 0;
                            if (m) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            Label_0725: {
                                if (!m) {
                                    break Label_0725;
                                }
                                if (s3.equals(this.b.dr(array4[n16], 0, this.O))) {
                                    this.O = true;
                                }
                                ++n16;
                            }
                            if (n16 < n9) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
            this.getParameter(c("6\u00100pP*\u001e"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0809: {
            if (!m) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (m) {
                        break Label_0809;
                    }
                    if (!s8.equalsIgnoreCase(c("\n:"))) {
                        try {
                            this.P = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.P = null;
                        }
                    }
                }
                this.getParameter(c("6\u00100r\\3\u0013%}T!"));
            }
        }
        if (s7.equalsIgnoreCase(c("\u001d0\u0004"))) {
            this.Q = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0850: {
                    if (!m) {
                        break Label_0850;
                    }
                    final Container parent2 = ((Frame)parent).getParent();
                    parent = parent2;
                }
                if (!(parent instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.r = (Frame)parent).setCursor(3);
            final Container parent2 = this;
            if (m) {
                continue;
            }
            break;
        }
        final String parameter3;
        final String s9 = parameter3 = this.getParameter(c("+\u00032nP)\u0012"));
        Label_1024: {
            if (!m) {
                firewks firewks = null;
                Label_1016: {
                    if (parameter3 != null) {
                        final String s10 = s9;
                        if (m) {
                            break Label_1024;
                        }
                        if (!s10.equalsIgnoreCase(c("\n:"))) {
                            this.m = this.a(s9);
                            firewks = this;
                            if (m) {
                                break Label_1016;
                            }
                            if (this.m != null) {
                                final String parameter4;
                                String s11 = parameter4 = this.getParameter(c("+\u00032nP)\u0012\u000f"));
                                if (!m) {
                                    if (parameter4 == null) {
                                        s11 = "0";
                                    }
                                    this.n = Integer.valueOf(s11);
                                    this.getParameter(c("+\u00032nP)\u0012\u000e"));
                                }
                                final String s13;
                                String s12 = s13 = parameter4;
                                if (m || s13 == null) {
                                    s12 = s13;
                                }
                                this.o = Integer.valueOf(s12);
                            }
                        }
                    }
                    firewks = this;
                }
                firewks.getParameter(c("&\u001a8qJ+\u00009x"));
            }
        }
        final String s14 = parameter3;
        int equalsIgnoreCase;
        final boolean b = (equalsIgnoreCase = (s14.equalsIgnoreCase(c("\n:")) ? 1 : 0)) != 0;
        if (!m) {
            if (!b) {
                this.u = this.getAudioClip(this.getDocumentBase(), s14);
            }
            this.RocketStyleVariability = 10;
            this.maxfireworks = Integer.valueOf(this.getParameter(c(")\u0014/zP6\u0010 sK/\u0006")));
            this.fireworkspower = Integer.valueOf(this.getParameter(c("\"\u001c%yN+\u0007<oI+\u00022n")));
            this.fireworksdensity = Integer.valueOf(this.getParameter(c("\"\u001c%yN+\u0007<o]!\u001b$uM=")));
            this.fireworksduration = Integer.valueOf(this.getParameter(c("\"\u001c%yN+\u0007<o]1\u00076hP+\u001b")));
            this.gravity = Integer.valueOf(this.getParameter(c("#\u00076jP0\f")));
            this.v = this.size().width - 1;
            this.w = this.size().height - 1;
            equalsIgnoreCase = (int)(this.fireworksdensity * 3.0 / 4.0) + this.fireworksdensity / 4 + 2;
        }
        final int n17 = equalsIgnoreCase;
        this.x = new firewk[this.maxfireworks];
        this.T = 0;
        while (true) {
            while (true) {
                Label_1291: {
                    if (!m) {
                        break Label_1291;
                    }
                    this.x[this.T] = new firewk(this.v, this.w, this.gravity, n17);
                    ++this.T;
                }
                if (this.T < this.maxfireworks) {
                    continue;
                }
                break;
            }
            final String parameter5 = this.getParameter(c("&\u00144wP)\u00140y"));
            if (!m) {
                Label_1383: {
                    Label_1351: {
                        if (!m) {
                            if (parameter5 != null && !parameter5.equalsIgnoreCase(c("\n:"))) {
                                break Label_1351;
                            }
                            this.s = false;
                        }
                        if (!m) {
                            break Label_1383;
                        }
                    }
                    this.s = true;
                    this.I = this.a(parameter5);
                    firewks firewks2 = this;
                    if (!m) {
                        if (this.I != null) {
                            break Label_1383;
                        }
                        firewks2 = this;
                    }
                    firewks2.s = false;
                }
                final String parameter6 = this.getParameter(c("+\u00032nM!\r#"));
                Label_1430: {
                    Label_1425: {
                        if (!m) {
                            if (!parameter6.equalsIgnoreCase(c("\u001d0\u0004"))) {
                                break Label_1425;
                            }
                            this.j = true;
                        }
                        if (!m) {
                            break Label_1430;
                        }
                    }
                    this.j = false;
                }
                this.D = new Color(Integer.parseInt(this.getParameter(c("&\u00124sU+\u0007")), 16));
                this.E = this.getParameter(c(")\u0010:x\\(\u0014."));
                this.F = this.getParameter(c("4\u0007>sK-\u0001."));
                this.h = Integer.valueOf(this.E);
                this.i = Integer.valueOf(this.F);
                this.F = null;
                this.F = this.getParameter(c("\t\u001c9O`\n6"));
                this.p = Integer.valueOf(this.F);
                int n19;
                final int n18 = n19 = this.h;
                if (!m) {
                    if (n18 < 0) {
                        this.h = 0;
                    }
                    final int i;
                    n19 = (i = this.i);
                }
                final int n20 = 10;
                Label_1659: {
                    firewks firewks3 = null;
                    Label_1644: {
                        int bn2 = 0;
                        Label_1614: {
                            Label_1601: {
                                if (!m) {
                                    if (n18 > n20) {
                                        this.i = 10;
                                        if (!m) {
                                            break Label_1601;
                                        }
                                    }
                                    bn2 = (n19 = this.i);
                                    if (m) {
                                        break Label_1614;
                                    }
                                }
                                if (n19 < n20) {
                                    this.i = 1;
                                }
                            }
                            this.scrollinitial();
                            firewks3 = this;
                            if (m) {
                                break Label_1644;
                            }
                            bn2 = this.bn;
                        }
                        if (bn2 != 0) {
                            this.J = this.createImage(this.y, this.z + this.bK);
                            if (!m) {
                                break Label_1659;
                            }
                        }
                        firewks3 = this;
                    }
                    firewks3.J = this.createImage(this.y, this.z);
                }
                this.K = this.J.getGraphics();
                return;
            }
            continue;
        }
    }
    
    public void start() {
        if (this.a == null) {
            (this.a = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a != null && this.a.isAlive()) {
            this.a.stop();
        }
        this.a = null;
    }
    
    public void run() {
        this.R = this.getToolkit();
        this.a.setPriority(this.i);
        this.showStatus("");
        System.gc();
        this.q = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.m != null && !this.M) {
            this.M = this.CheckAniGIF();
        }
        if (this.P != null) {
            this.r.setCursor(12);
        }
        else {
            this.r.setCursor(0);
        }
        this.U = (int)(Math.random() * this.fireworkspower * 3.0 / 4.0) + this.fireworkspower / 4 + 1;
        this.V = (int)(Math.random() * this.fireworksdensity * 3.0 / 4.0) + this.fireworksdensity / 4 + 1;
        this.W = (int)(Math.random() * this.fireworksduration * 3.0 / 4.0) + this.fireworksduration / 4 + 1;
        this.X = (long)(Math.random() * 10000.0);
        this.b.dr(c("%\u001b1e"), 1, this.O);
        while (this.a != null) {
            if (++this.c == this.h) {
                System.gc();
                this.c = 0;
            }
            if (!this.s) {
                this.K.setColor(this.D);
                this.K.fillRect(0, 0, this.y, this.z);
            }
            else {
                this.K.drawImage(this.I, 0, 0, this);
            }
            if (this.bi && this.j) {
                this.scrolltext(this.K);
            }
            this.drawFireworks();
            if (this.m != null) {
                this.prepaniframe();
            }
            if (this.bi && !this.j) {
                this.scrolltext(this.K);
            }
            graphics.drawImage(this.J, 0, 0, this);
            this.waitsync();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.J, 0, 0, this);
    }
    
    public synchronized void waitsync() {
        Thread.yield();
        this.R.sync();
        final long n = 10L - (System.currentTimeMillis() - this.q);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        else {
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex2) {}
        }
        this.q = System.currentTimeMillis();
        try {
            Thread.sleep(this.p);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void drawFireworks() {
        this.S = true;
        this.T = 0;
        while (this.T < this.maxfireworks) {
            this.S = (this.S && this.x[this.T].sleep);
            ++this.T;
        }
        if (this.S && Math.random() * 100.0 < this.RocketStyleVariability) {
            this.U = (int)(Math.random() * this.fireworkspower * 3.0 / 4.0) + this.fireworkspower / 4 + 1;
            this.V = (int)(Math.random() * this.fireworksdensity * 3.0 / 4.0) + this.fireworksdensity / 4 + 1;
            this.W = (int)(Math.random() * this.fireworksduration * 3.0 / 4.0) + this.fireworksduration / 4 + 1;
            this.X = (long)(Math.random() * 10000.0);
        }
        final float n = this.maxfireworks * this.W;
        this.T = 0;
        while (this.T < this.maxfireworks) {
            if (this.x[this.T].sleep && Math.random() * n < 1.0) {
                this.x[this.T].init(this.U, this.V, this.W, this.X, this.u);
                this.x[this.T].start();
            }
            this.x[this.T].showf(this.K);
            ++this.T;
        }
    }
    
    public void scrollinitial() {
        this.bi = false;
        final String parameter = this.getParameter(c("0\u0010/hJ'\u00078pU"));
        if (parameter != null && !parameter.equalsIgnoreCase(c("\n:"))) {
            String s = this.getParameter(c("0\u0010/hM=\u00052"));
            if (s == null) {
                s = c(",\u001a%uC+\u001b#}U");
            }
            if (s.equals(c(",\u001a%uC+\u001b#}U"))) {
                this.bu = 0;
            }
            else if (s.equals(c("2\u0010%hP'\u0014;"))) {
                this.bu = 1;
            }
            else if (s.equals(c(">\u001a8qP*\u0012"))) {
                this.bu = 2;
            }
            else if (s.equals(c("-\u001b!fV+\u0018>r^"))) {
                this.bu = 3;
            }
            if (this.bu == 0) {
                this.GetTheString(parameter, 0);
                if (this.bg != null) {
                    this.bi = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.bt != null) {
                    this.bi = true;
                }
            }
        }
        if (this.bi) {
            String parameter2 = this.getParameter(c("0\u0010/hJ4\u00102x"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bh = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("0\u0010/h_+\u001b#"));
            if (s2 == null) {
                s2 = c("\u0005\u0007>}U");
            }
            int n = 0;
            if (this.getParameter(c("0\u0010/h[+\u00193")).equalsIgnoreCase(c("\u001d0\u0004"))) {
                ++n;
            }
            String s3 = this.getParameter(c("0\u0010/hP0\u0014;uZ"));
            if (s3 == null) {
                s3 = c("\n:");
            }
            if (s3.equalsIgnoreCase(c("\u001d0\u0004"))) {
                n += 2;
            }
            String s4 = this.getParameter(c("0\u0010/hJ-\u000f2"));
            if (s4 == null) {
                s4 = c("uG");
            }
            this.bm = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(c("0\u0010/hJ,\u00143sN")).equalsIgnoreCase(c("\u001d0\u0004"))) {
                this.bj = true;
            }
            else {
                this.bj = false;
            }
            this.bk = new Color(Integer.valueOf(this.getParameter(c("\u0010\u0010/hz+\u0019\u0005"))), Integer.valueOf(this.getParameter(c("\u0010\u0010/hz+\u0019\u0010"))), Integer.valueOf(this.getParameter(c("\u0010\u0010/hz+\u0019\u0015"))));
            this.bl = new Color(Integer.valueOf(this.getParameter(c("\u0010\u0010/hj\u0007\u001a;N"))), Integer.valueOf(this.getParameter(c("\u0010\u0010/hj\u0007\u001a;["))), Integer.valueOf(this.getParameter(c("\u0010\u0010/hj\u0007\u001a;^"))));
            this.Z = this.size().width;
            this.ba = this.size().height;
            if (this.bu == 0) {
                String parameter3 = this.getParameter(c("0\u0010/hV\"\u0013$yM"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.bc = Integer.valueOf(parameter3);
                if (this.bc < 0) {
                    this.bc = 0;
                }
                String parameter4 = this.getParameter(c("\u0010\u0010/hs1\u0018']T4"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.bz = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("\u0010\u0010/hs1\u0018'OI "));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bC = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("\u0010\u0010/hj-\u001b2]T4"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bn = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("\u0010\u0010/hj-\u001b2OI "));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bo = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("\u0010\u0010/hj-\u001b2]W#\u00192"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bp = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.bm);
                this.bd = fontMetrics.stringWidth(this.bg);
                this.be = fontMetrics.getHeight();
                this.bf = fontMetrics.getMaxDescent();
                this.bb = this.Z;
                if (this.bc < this.be - this.bf) {
                    this.bc = this.be - this.bf;
                }
                else if (this.bc > this.ba - this.bf) {
                    this.bc = this.ba - this.bf;
                }
                if (this.bn != 0) {
                    this.br = new int[this.Z + 360];
                    this.bs = new int[this.Z + 360];
                    for (int i = 0; i < this.Z + 360; ++i) {
                        this.br[i] = (int)(this.bn * Math.sin(this.bp * i * 3.141592653589793 / 180.0)) - this.be - this.bf + this.bc;
                        this.bs[i] = this.br[i] - this.e;
                    }
                    this.bq = 360;
                    this.bK = this.be + this.bf + 1;
                    this.bL = this.bK - 1;
                }
            }
            else {
                if (this.bu == 1) {
                    String s5 = this.getParameter(c("0\u0010/hO7\u00056\u007f\\"));
                    if (s5 == null) {
                        s5 = c("uE");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.bm);
                    this.bE = fontMetrics2.getHeight() + intValue;
                    this.bF = new int[this.bt.length];
                    for (int j = 0; j < this.bt.length; ++j) {
                        this.bF[j] = (this.Z - fontMetrics2.stringWidth(this.bt[j])) / 2;
                    }
                    this.bD = -this.bE;
                    return;
                }
                if (this.bu >= 2) {
                    String parameter9 = this.getParameter(c("0\u0010/hT-\u001b1sW0"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.bx = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(c("0\u0010/hT%\r1sW0"));
                    if (s6 == null) {
                        s6 = c("sG");
                    }
                    this.by = Integer.valueOf(s6);
                    this.bv = this.by - this.bx;
                    this.bm = null;
                    this.bw = new Font[this.bv];
                    int bx = this.bx;
                    for (int k = 0; k < this.bv; ++k) {
                        this.bw[k] = new Font(s2, n, bx++);
                    }
                    this.bI = this.Z / 2.0f;
                    this.bJ = this.ba / 2.0f;
                    if (this.bu == 3) {
                        this.bG = this.bv - 1;
                        return;
                    }
                    this.bG = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.bu) {
            case 0: {
                this.horizscroll(graphics);
            }
            case 1: {
                this.vertscroll(graphics);
            }
            default: {
                this.zoomscroll(graphics);
            }
        }
    }
    
    public void vertscroll(final Graphics graphics) {
        graphics.setFont(this.bm);
        this.bD += this.bh;
        if (this.bD > this.ba + this.bt.length * this.bE) {
            this.bD = -this.bE;
        }
        if (this.bj) {
            for (int i = 0; i < this.bt.length; ++i) {
                final String s = this.bt[i];
                final int n = this.bF[i];
                final int n2 = this.ba - this.bD + i * this.bE;
                graphics.setColor(this.bl);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bk);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bk);
        for (int j = 0; j < this.bt.length; ++j) {
            graphics.drawString(this.bt[j], this.bF[j], this.ba - this.bD + j * this.bE);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.bt[this.bH];
        graphics.setFont(this.bw[this.bG]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.bw[this.bG]);
        final int n = (int)(this.bI - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.bJ + fontMetrics.getHeight() / 4.0f);
        if (this.bj) {
            graphics.setColor(this.bl);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bk);
        graphics.drawString(s, n, n2);
        if (this.bu == 3) {
            this.bG -= this.bh;
            if (this.bG <= 1) {
                this.bG = this.bv - 1;
                ++this.bH;
                if (this.bH >= this.bt.length) {
                    this.bH = 0;
                }
            }
        }
        else {
            this.bG += this.bh;
            if (this.bG >= this.bv) {
                this.bG = 0;
                ++this.bH;
                if (this.bH >= this.bt.length) {
                    this.bH = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.bm);
        if (this.bz == 0) {
            this.bA = this.bc;
        }
        else {
            this.bB += this.bC;
            this.bA = this.bc - (int)Math.abs(this.bz * Math.sin(this.bB / 90.0 * 3.141592653589793));
        }
        if (this.bn != 0) {
            for (int i = 0; i < this.Z; ++i) {
                final int n = this.br[this.bq + i];
                graphics.copyArea(i, n, 1, this.bK, 0, this.e - n);
            }
            if (this.bj) {
                graphics.setColor(this.bl);
                graphics.drawString(this.bg, this.bb + 1, this.e + this.be + 1);
            }
            graphics.setColor(this.bk);
            graphics.drawString(this.bg, this.bb, this.e + this.be);
            for (int j = 0; j < this.Z; ++j) {
                graphics.copyArea(j, this.e, 1, this.bL, 0, this.bs[this.bq + j]);
            }
            this.bq -= this.bo;
            if (this.bq < 0) {
                this.bq += 360;
            }
        }
        else {
            if (this.bj) {
                graphics.setColor(this.bl);
                graphics.drawString(this.bg, this.bb + 1, this.bA + 1);
            }
            graphics.setColor(this.bk);
            graphics.drawString(this.bg, this.bb, this.bA);
        }
        this.bb -= this.bh;
        if (this.bb < -this.bd) {
            this.bb = this.Z;
        }
    }
    
    public void GetTheString(final String s, final int n) {
        try {
            this.GetTheString1(s, n);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.GetTheString1(s, n);
        }
    }
    
    public void GetTheString1(final String s, final int n) {
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            try {
                final DataInputStream dataInputStream = new DataInputStream(url.openStream());
                if (dataInputStream == null) {
                    return;
                }
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                final byte[] array = new byte[512];
                int n2 = 0;
                boolean b = false;
                try {
                    while (!b) {
                        final int read = dataInputStream.read(array, 0, 512);
                        if (read == -1) {
                            b = true;
                        }
                        else {
                            byteArrayOutputStream.write(array, 0, read);
                            byteArrayOutputStream.flush();
                            n2 += read;
                        }
                    }
                    final byte[] byteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    dataInputStream.close();
                    System.gc();
                    if (n == 0) {
                        for (int i = 0; i < n2; ++i) {
                            final byte b2 = byteArray[i];
                            if (b2 == 13 || b2 == 10) {
                                byteArray[i] = 32;
                            }
                        }
                        try {
                            this.bg = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bg = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.bt = new String[n3 - 1];
                    final int[] array2 = new int[n3 + 1];
                    final int[] array3 = new int[n3 + 1];
                    array2[0] = 0;
                    int n4 = 0;
                    int n5 = 0;
                    for (int k = 0; k < n2; ++k) {
                        final byte b3 = byteArray[k];
                        if (b3 == 10) {
                            array2[n4 + 1] = k + 1;
                            if (n5 == 13) {
                                array3[n4] = k - array2[n4] - 1;
                            }
                            else {
                                array3[n4] = k - array2[n4];
                            }
                            ++n4;
                        }
                        n5 = b3;
                    }
                    array3[n4] = n2 - array2[n4 + 1] - 1;
                    try {
                        for (int l = 0; l < n3 - 1; ++l) {
                            try {
                                this.bt[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.bt[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.bt = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bM);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final boolean m = firewk.M;
        firewks firewks = this;
        Label_0065: {
            if (m) {
                break Label_0065;
            }
            if (!this.O) {
                this.b.show();
                try {
                    this.b.move(100, 100);
                }
                catch (Exception ex) {}
                this.b.toFront();
                this.b.requestFocus();
                if (!m) {
                    return true;
                }
            }
            try {
                firewks firewks2 = this;
                firewks = this;
                Label_0084: {
                    if (m) {
                        break Label_0084;
                    }
                    if (firewks.P == null) {
                        return true;
                    }
                    try {
                        this.b.dck();
                        firewks firewks3 = this;
                        firewks2 = this;
                        if (!m) {
                            if (firewks2.Q) {
                                this.getAppletContext().showDocument(this.P, this.getParameter(c("6\u00100zK%\u00182rX)\u0010")));
                                if (!m) {
                                    return true;
                                }
                            }
                            firewks3 = this;
                        }
                        firewks3.getAppletContext().showDocument(this.P);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public firewks() {
        this.t = false;
        this.L = false;
        this.M = false;
        this.O = false;
        this.Q = false;
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = 'D';
                    break;
                }
                case 1: {
                    c2 = 'u';
                    break;
                }
                case 2: {
                    c2 = 'W';
                    break;
                }
                case 3: {
                    c2 = '\u001c';
                    break;
                }
                default: {
                    c2 = '9';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
