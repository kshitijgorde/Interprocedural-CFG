import java.awt.image.PixelGrabber;
import java.io.StreamTokenizer;
import java.io.BufferedInputStream;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.util.Vector;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class madtext extends Applet implements Runnable, ImageObserver
{
    anfy a;
    MemoryImageSource b;
    boolean c;
    Toolkit d;
    int e;
    long f;
    Thread g;
    Frame h;
    Lware i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    private Image s;
    private Image t;
    private Image u;
    private Graphics v;
    int w;
    int x;
    int[] y;
    String z;
    boolean A;
    boolean B;
    final String C = "F\u007f~y\u0019s/ll\\Anl|\u0013'Lg`\u001fdf.=\u000bpx t\u0012a";
    boolean D;
    URL E;
    boolean F;
    String G;
    int H;
    int I;
    int J;
    int K;
    int L;
    String M;
    int[] N;
    int[] O;
    private int[] P;
    private int Q;
    private int R;
    private int S;
    private boolean T;
    private Vector U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int[] Z;
    private int[] ba;
    private int[] bb;
    private int[] bc;
    private int[] bd;
    private int[] be;
    private int[] bf;
    private boolean[] bg;
    private int[] bh;
    private int bi;
    private int[] bj;
    private int[][] bk;
    private int bl;
    private int bm;
    private float bn;
    private int bo;
    private int bp;
    private int bq;
    private int br;
    private int bs;
    private int bt;
    private int[][] bu;
    private int bv;
    private int bw;
    private int bx;
    private String by;
    private int bz;
    private int bA;
    private int bB;
    private int bC;
    private int bD;
    private int bE;
    private int bF;
    private int bG;
    private float bH;
    private int bI;
    private int bJ;
    private int[] bK;
    private int[] bL;
    private int[] bM;
    private int bN;
    private int bO;
    private int bP;
    private int[] bQ;
    private int bR;
    private int bS;
    private int bT;
    private float bU;
    private float bV;
    private float bW;
    private float bX;
    private float bY;
    private float bZ;
    private float ca;
    private float cb;
    private float cc;
    private float cd;
    private int ce;
    private int cf;
    private int cg;
    private int ch;
    private float ci;
    private float cj;
    public static int ck;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.t) {
            if (n == 16) {
                this.A = true;
            }
            return true;
        }
        return true;
    }
    
    public void destroy() {
        if (this.i != null) {
            this.i.hide();
        }
        this.i = null;
        if (this.t != null) {
            this.t.flush();
        }
        this.t = null;
        if (this.u != null) {
            this.u.flush();
        }
        this.u = null;
        if (this.v != null) {
            this.v.dispose();
        }
        this.v = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.B) {
            this.notifyAll();
            while (!this.A) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.A = false;
        }
        this.v.drawImage(this.t, this.w, this.x, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.t, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.A;
        }
        return false;
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
                this.showStatus(e("Nbor\u0019'") + s + e("'aaa\\a`{{\u0018&"));
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
            this.showStatus(e("C``2\b'}kx\u0013qj.b\u000bp!o{\u001a~{kt\u0011)lax\\d}kq\u0015s|.y\u0015ij.|\u0012'GZX0&"));
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
        final int ck = madtext.ck;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        this.z = this.getParameter(e("t{oa\ttb}r"));
        final String parameter = this.getParameter(e("d}kq\u0015s|"));
        String protocol;
        final String s = protocol = parameter;
        String e = null;
        Label_0117: {
            Label_0086: {
                Label_0082: {
                    if (ck == 0) {
                        if (s == null) {
                            break Label_0082;
                        }
                        final String s2;
                        protocol = (s2 = parameter);
                    }
                    if (ck != 0) {
                        break Label_0117;
                    }
                    if (s.startsWith(e("F\u007f~y\u0019s/ll\\Anl|\u0013'Lg`\u001fdf.=\u000bpx t\u0012a"))) {
                        break Label_0086;
                    }
                    this.a();
                    if (ck == 0) {
                        break Label_0086;
                    }
                }
                this.a();
            }
            (this.i = new Lware(this, e("Jnj5(bwz5\u001dw\u007fbp\b"))).hide();
            try {
                protocol = this.getDocumentBase().getProtocol();
                e = protocol;
            }
            catch (SecurityException ex) {
                e = e("afbp");
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
        e.toLowerCase();
        final String parameter2;
        Label_0650: {
            Label_0641: {
                if (ck == 0) {
                    Label_0217: {
                        if (!e.equals(e("afbp"))) {
                            int n3;
                            int startsWith;
                            int n2;
                            final int n = n2 = (startsWith = (n3 = s3.length()));
                            if (ck == 0) {
                                if (n < 1) {
                                    break Label_0217;
                                }
                                final int n4;
                                n2 = (n4 = (startsWith = (n3 = (s3.startsWith(e("k`mt\u0010")) ? 1 : 0))));
                            }
                            if (ck == 0) {
                                if (n != 0) {
                                    break Label_0217;
                                }
                                startsWith = (n2 = (n3 = (s3.equals(e("6=9;L)? $")) ? 1 : 0)));
                            }
                            if (ck == 0) {
                                if (n2 != 0) {
                                    break Label_0217;
                                }
                                n3 = (startsWith = (s3.startsWith(e("pxy;")) ? 1 : 0));
                            }
                            if (ck == 0) {
                                if (startsWith != 0) {
                                    s3 = s3.substring(4);
                                }
                                n3 = s3.length();
                            }
                            final int n6;
                            final int n5 = n6 = n3;
                            if (ck != 0 || n6 > 0) {
                                final char[] array = new char[n6];
                                s3.getChars(0, n5, array, 0);
                                int n7 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0318: {
                                            if (ck == 0) {
                                                break Label_0318;
                                            }
                                            final char[] array2 = array;
                                            final int n8 = n7;
                                            if (ck != 0 || array2[n8] == '0') {
                                                array2[n8] = '1';
                                            }
                                            n7 += 5;
                                        }
                                        if (n7 < n5) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (ck != 0) {
                                        continue;
                                    }
                                    break;
                                }
                                s3 = new String(array);
                            }
                            final String s4 = parameter2 = this.getParameter(e("ujiv\u0013cj"));
                            if (ck != 0) {
                                break Label_0650;
                            }
                            if (parameter2 == null) {
                                break Label_0641;
                            }
                            final String s5 = s4;
                            if (ck != 0) {
                                break Label_0650;
                            }
                            if (s5.length() <= 5) {
                                break Label_0641;
                            }
                            s4.toLowerCase();
                            int n9 = 1;
                            try {
                                int n10 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0413: {
                                            if (ck == 0) {
                                                break Label_0413;
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
                                    if (ck != 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex3) {}
                            final int[] array3 = new int[n9];
                            final int n11 = n9;
                            if (ck == 0 && n11 == 1) {
                                array3[0] = s4.length();
                                if (ck != 0) {
                                    goto Label_0463;
                                }
                            }
                            else {
                                int n12 = n11;
                                try {
                                    int n13 = 0;
                                    while (true) {
                                        while (true) {
                                            Label_0499: {
                                                if (ck == 0) {
                                                    break Label_0499;
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
                                        if (ck != 0) {
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
                        Label_0563_Outer:
                            while (true) {
                                while (true) {
                                    Label_0579: {
                                        if (ck == 0) {
                                            break Label_0579;
                                        }
                                        try {
                                            array4[n15] = s4.substring(n14, array3[n15]);
                                        }
                                        catch (StringIndexOutOfBoundsException ex5) {}
                                        n14 = array3[n15] + 1;
                                        ++n15;
                                    }
                                    if (n15 >= n9) {
                                        int i = 0;
                                        if (ck != 0) {
                                            if (ck != 0) {
                                                continue;
                                            }
                                        }
                                        while (i < n9) {
                                            if (s3.equals(this.i.dr(array4[i], 0, this.D))) {
                                                this.D = true;
                                            }
                                            ++i;
                                        }
                                        break Label_0641;
                                    }
                                    break;
                                }
                                continue Label_0563_Outer;
                            }
                        }
                    }
                    this.D = true;
                    if (ck != 0) {}
                }
            }
            this.getParameter(e("ujiy\u0015id"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0718: {
            if (ck == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (ck != 0) {
                        break Label_0718;
                    }
                    if (!s8.equalsIgnoreCase(e("I@"))) {
                        try {
                            this.E = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.E = null;
                        }
                    }
                }
                this.getParameter(e("uji{\u0019pi|t\u0011b"));
            }
        }
        if (s7.equalsIgnoreCase(e("^J]"))) {
            this.F = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0759: {
                    if (ck == 0) {
                        break Label_0759;
                    }
                    final Container parent2 = ((Frame)parent).getParent();
                    parent = parent2;
                }
                if (!(parent instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.h = (Frame)parent).setCursor(3);
            final Container parent2 = this;
            if (ck == 0) {
                final String parameter3;
                final String s9 = parameter3 = this.getParameter(e("hykg\u0015jh"));
                Label_0933: {
                    if (ck == 0) {
                        madtext madtext = null;
                        Label_0925: {
                            if (parameter3 != null) {
                                final String s10 = s9;
                                if (ck != 0) {
                                    break Label_0933;
                                }
                                if (!s10.equalsIgnoreCase(e("I@"))) {
                                    this.t = this.a(s9);
                                    madtext = this;
                                    if (ck != 0) {
                                        break Label_0925;
                                    }
                                    if (this.t != null) {
                                        final String parameter4;
                                        String s11 = parameter4 = this.getParameter(e("hykg\u0015jhV"));
                                        if (ck == 0) {
                                            if (parameter4 == null) {
                                                s11 = "0";
                                            }
                                            this.w = Integer.valueOf(s11);
                                            this.getParameter(e("hykg\u0015jhW"));
                                        }
                                        final String s13;
                                        String s12 = s13 = parameter4;
                                        if (ck != 0 || s13 == null) {
                                            s12 = s13;
                                        }
                                        this.x = Integer.valueOf(s12);
                                    }
                                }
                            }
                            madtext = this;
                        }
                        madtext.getParameter(e("Jf`F%IL"));
                    }
                }
                final String s14;
                String e2 = s14 = parameter3;
                Label_1007: {
                    madtext madtext2 = null;
                    final String e3;
                    Label_0998: {
                        if (ck == 0) {
                            if (s14 == null) {
                                e2 = e("6?");
                            }
                            this.e = Integer.valueOf(e2);
                            madtext2 = this;
                            e3 = e("uj}");
                            if (ck != 0) {
                                break Label_0998;
                            }
                            final String parameter5;
                            e2 = (parameter5 = this.getParameter(e3));
                        }
                        if (s14 == null) {
                            this.r = 1;
                            if (ck == 0) {
                                break Label_1007;
                            }
                        }
                        madtext2 = this;
                    }
                    madtext2.r = Integer.valueOf(e3);
                }
                final int r = this.r;
                final int n16 = 8;
                int n19 = 0;
                int l = 0;
                int n17 = 0;
                Label_1092: {
                    Label_1050: {
                        if (ck == 0) {
                            if (r > n16) {
                                this.r = 8;
                                if (ck == 0) {
                                    break Label_1050;
                                }
                            }
                            final int n18;
                            n17 = (n18 = (l = (n19 = this.r)));
                            if (ck != 0) {
                                break Label_1092;
                            }
                        }
                        if (r < n16) {
                            this.r = 1;
                        }
                    }
                    this.k = Integer.valueOf(this.getParameter(e("jjcq\u0019knw")));
                    this.l = Integer.valueOf(this.getParameter(e("w}gz\u000en{w")));
                    l = (n17 = (n19 = this.k));
                }
                if (ck == 0) {
                    if (n17 < 0) {
                        this.k = 0;
                    }
                    n19 = (l = this.l);
                }
                final int n20 = 10;
                Label_1241: {
                    Label_1148: {
                        if (ck == 0) {
                            if (l > n20) {
                                this.l = 10;
                                if (ck == 0) {
                                    break Label_1148;
                                }
                            }
                            final madtext madtext3 = this;
                            if (ck != 0) {
                                break Label_1241;
                            }
                            n19 = this.l;
                        }
                        if (n19 < n20) {
                            this.l = 1;
                        }
                    }
                    this.m = this.size().width / this.r;
                    this.n = this.size().height / this.r;
                    this.o = this.m * this.n;
                    this.p = this.m * this.r;
                    this.q = this.n * this.r;
                    this.y = new int[this.o + this.m + 1];
                    this.c();
                    try {
                        final madtext madtext3 = this;
                        madtext3.b();
                    }
                    catch (NoSuchMethodError noSuchMethodError) {
                        this.b();
                    }
                }
                this.u = this.createImage(this.p, this.q);
                this.v = this.u.getGraphics();
                return;
            }
            continue;
        }
    }
    
    void b() {
        this.b = new MemoryImageSource(this.m, this.n, new DirectColorModel(24, 16711680, 65280, 255), this.y, 0, this.m);
        String s;
        try {
            s = System.getProperty(e("mnxtRqj|f\u0015ha"));
        }
        catch (SecurityException ex) {
            s = e("rae");
        }
        if (!s.startsWith(e("6!>"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.s = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.m, this.n, new DirectColorModel(24, 16711680, 65280, 255), this.y, 0, this.m);
            this.s = this.createImage(this.a);
        }
    }
    
    public void start() {
        if (this.g == null) {
            (this.g = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.g != null && this.g.isAlive()) {
            this.g.stop();
        }
        this.g = null;
    }
    
    public void run() {
        this.g.setPriority(this.l);
        this.showStatus("");
        System.gc();
        this.f = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.t != null && !this.B) {
            this.B = this.CheckAniGIF();
        }
        if (this.E != null) {
            this.h.setCursor(12);
        }
        else {
            this.h.setCursor(0);
        }
        this.i.dr(e("fahl"), 1, this.D);
        while (this.g != null) {
            if (this.S == 0) {
                this.i();
            }
            else if (this.S == 1) {
                this.l();
            }
            else if (this.S == 2) {
                this.p();
            }
            if (++this.j == this.k) {
                System.gc();
                this.j = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.r == 1) {
                this.v.drawImage(this.s, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.v.drawImage(this.s, 0, 0, this.p, this.q, this);
            }
            if (this.t != null) {
                this.prepaniframe();
            }
            graphics.drawImage(this.u, 0, 0, this);
            this.waitsync();
        }
    }
    
    public final void producefixed() {
        try {
            if (this.c) {
                this.b.newPixels();
                return;
            }
            this.a.startProduction(this.a.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public synchronized void prepscaled() {
        int checkImage = 0;
        this.prepareImage(this.s, this.p, this.q, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.s, this.p, this.q, this);
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.s != null) {
            if (this.r == 1) {
                this.v.drawImage(this.s, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.v.drawImage(this.s, 0, 0, this.p, this.q, this);
            }
            if (this.t != null) {
                this.prepaniframe();
            }
            graphics.drawImage(this.u, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.z);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int ck = madtext.ck;
        madtext madtext = this;
        Label_0065: {
            if (ck != 0) {
                break Label_0065;
            }
            if (!this.D) {
                this.i.show();
                try {
                    this.i.move(100, 100);
                }
                catch (Exception ex) {}
                this.i.toFront();
                this.i.requestFocus();
                if (ck == 0) {
                    return true;
                }
            }
            try {
                madtext madtext2 = this;
                madtext = this;
                Label_0084: {
                    if (ck != 0) {
                        break Label_0084;
                    }
                    if (madtext.E == null) {
                        return true;
                    }
                    try {
                        this.i.dck();
                        madtext madtext3 = this;
                        madtext2 = this;
                        if (ck == 0) {
                            if (madtext2.F) {
                                this.getAppletContext().showDocument(this.E, this.getParameter(e("ujis\u000efbk{\u001djj")));
                                if (ck == 0) {
                                    return true;
                                }
                            }
                            madtext3 = this;
                        }
                        madtext3.getAppletContext().showDocument(this.E);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public synchronized void waitsync() {
        Thread.yield();
        this.d.sync();
        final long n = 10L - (System.currentTimeMillis() - this.f);
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
        this.f = System.currentTimeMillis();
        try {
            Thread.sleep(this.e);
        }
        catch (InterruptedException ex3) {}
    }
    
    void a(final URL url, final String s) {
        try {
            final InputStream openStream = new URL(url, s).openStream();
            final StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedInputStream(openStream));
            streamTokenizer.ordinaryChars(32, 255);
            streamTokenizer.wordChars(32, 255);
            streamTokenizer.nextToken();
            this.G = String.valueOf(streamTokenizer.sval) + e(")hgs");
            streamTokenizer.nextToken();
            this.I = Integer.parseInt(streamTokenizer.sval);
            streamTokenizer.nextToken();
            this.K = Integer.parseInt(streamTokenizer.sval);
            streamTokenizer.nextToken();
            this.L = Integer.parseInt(streamTokenizer.sval);
            streamTokenizer.nextToken();
            this.M = streamTokenizer.sval;
            while (streamTokenizer.nextToken() != -1) {}
            openStream.close();
        }
        catch (Exception ex) {
            System.err.println(String.valueOf(ex) + e("=/Kg\u000eh}.b\u0014nck5\u000ebnj|\u0012`/O{\u001a~/Hz\u0012s/h|\u0010b"));
        }
        final Image a = this.a(this.G);
        this.H = a.getWidth(this);
        this.J = a.getHeight(this);
        this.N = new int[this.H * this.J];
        final PixelGrabber pixelGrabber = new PixelGrabber(a, 0, 0, this.H, this.J, this.N, 0, this.H);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex2) {}
        final int length = this.M.length();
        this.O = new int[length];
        for (int i = 0; i < length; ++i) {
            this.O[i] = i * this.I % this.H + (this.K + this.L) * this.H * (i * this.I / this.H);
        }
    }
    
    private void a(final String s, final int n) {
        this.a(s, (this.m - this.I * s.length()) / 2, (this.n - this.K) / 2, n);
    }
    
    private void a(final String s, final int n, final int n2, final int n3) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            this.a(this.M.indexOf(s.substring(i, i + 1)), n + i * (n3 + this.I), n2);
        }
    }
    
    private void a(final int n, final int n2, final int n3) {
        final int m = this.m;
        final int n4 = this.n;
        final int[] y = this.y;
        final int[] n5 = this.N;
        final int[] o = this.O;
        final int i = this.I;
        final int k = this.K;
        final int h = this.H;
        if (n != -1 && n2 < m && n2 + i >= 0) {
            int n6 = o[n];
            final int n7 = n2 + i;
            for (int n8 = n3 + k, j = n3; j < n8; ++j) {
                final int n9 = n6;
                if (j < n4 && j >= 0) {
                    for (int l = n2; l < n7; ++l) {
                        if (l < m && l >= 0) {
                            final int n10 = n5[n6];
                            final int n11 = l + j * m;
                            if (n10 != -16777216) {
                                y[n11] = n10;
                            }
                        }
                        ++n6;
                    }
                }
                n6 = n9 + h;
            }
        }
    }
    
    private void c() {
        this.X = this.m / 2;
        this.Y = this.n / 2;
        this.P = new int[this.o];
        this.d();
    }
    
    private void d() {
        final String parameter;
        if ((parameter = this.getParameter(e("a``a"))) != null) {
            this.a(this.getDocumentBase(), parameter);
        }
        else {
            this.d(e("Sgk5\ff}ox\u0019sj|5\u001ahaz5\u0015t/`z\b'kks\u0015ij.4"));
        }
        final String parameter2;
        if ((parameter2 = this.getParameter(e("sjva"))) != null) {
            this.c(parameter2);
        }
        else {
            this.d(e("Sgk5\ff}ox\u0019sj|5\bbwz5\u0015t/`z\b'kks\u0015ij.4"));
        }
        this.V = this.U.size();
        final String parameter3;
        if ((parameter3 = this.getParameter(e("ehmz\u0010h}"))) != null) {
            this.Q |= Integer.parseInt(parameter3, 16);
        }
        final String parameter4;
        if ((parameter4 = this.getParameter(e("ec{g\u0019aikv\b"))) != null && parameter4.equals(e("s}{p"))) {
            this.T = true;
        }
        final String parameter5;
        if ((parameter5 = this.getParameter(e("ec{g"))) != null) {
            this.R = Math.abs(Integer.parseInt(parameter5));
        }
        final String parameter6;
        if ((parameter6 = this.getParameter(e("sjva\u0014t\u007fov\u0019"))) != null) {
            this.W = b(Integer.parseInt(parameter6), -this.I / 2, 100);
        }
        final String parameter7;
        if ((parameter7 = this.getParameter(e("enm~\u001bu`{{\u0018"))) != null && !parameter7.equalsIgnoreCase(e("i`"))) {
            final PixelGrabber pixelGrabber = new PixelGrabber(this.a(parameter7), 0, 0, this.m, this.n, this.P, 0, this.m);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {}
        }
        else {
            for (int i = 0; i < this.o; ++i) {
                this.P[i] = this.Q;
            }
        }
        final String parameter8;
        if ((parameter8 = this.getParameter(e("bihp\u001fs"))) == null) {
            this.g();
            return;
        }
        if (parameter8.equals(e("cf}a\u0013u{gz\u0012"))) {
            this.k();
            return;
        }
        if (parameter8.equals(e("u`zz\u0006h`cp\u000e"))) {
            this.o();
            return;
        }
        this.g();
    }
    
    private void c(final String s) {
        this.U = new Vector();
        try {
            final InputStream openStream = new URL(this.getDocumentBase(), s).openStream();
            final StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedInputStream(openStream));
            streamTokenizer.ordinaryChars(32, 255);
            streamTokenizer.wordChars(32, 255);
        Label_0106:
            while (true) {
                switch (streamTokenizer.nextToken()) {
                    case -3: {
                        this.U.addElement(streamTokenizer.sval);
                        continue;
                    }
                    case -1: {
                        break Label_0106;
                    }
                }
            }
            openStream.close();
        }
        catch (Exception ex) {}
        if (this.U.size() == 0) {
            this.U.addElement("");
        }
        this.U.trimToSize();
    }
    
    private void e() {
        System.arraycopy(this.P, 0, this.y, 0, this.o);
    }
    
    private void f() {
        final int[] array = new int[this.m];
        final int m = this.m;
        final int n = this.n + 1 - 1;
        final int n2 = m - 1;
        final int[] y = this.y;
        final int r = this.R;
        int i;
        for (i = 0; i < n; ++i) {
            int n3 = 0;
            final int n4 = i * m;
            int n5;
            int j;
            for (n5 = n4 + n2, j = n4; j < n5; ++j) {
                final int n6 = n3;
                n3 = y[j];
                final int n7 = ((n6 & 0xFCFCFC) >> 2) + ((y[j + 1] & 0xFCFCFC) >> 2) + ((array[j - n4] & 0xFCFCFC) >> 2) + ((y[m + j] & 0xFCFCFC) >> 2);
                final int n8 = (n7 & 0xFF0000) >> 16;
                final int n9 = (n7 & 0xFF00) >> 8;
                final int n10 = n7 & 0xFF;
                int n11;
                if (n8 > r) {
                    n11 = n8 - r;
                }
                else {
                    n11 = 0;
                }
                int n12;
                if (n9 > r) {
                    n12 = n9 - r;
                }
                else {
                    n12 = 0;
                }
                int n13;
                if (n10 > r) {
                    n13 = n10 - r;
                }
                else {
                    n13 = 0;
                }
                y[j] = (0xFF000000 | n11 << 16 | n12 << 8 | n13);
                array[j - n4] = n3;
            }
            y[j] = -16777216;
        }
        for (int k = i * m; k < i * m + m; ++k) {
            y[k] = -16777216;
        }
    }
    
    private final void d(final String s) {
        while (true) {
            this.showStatus(s);
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    static int b(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    static float a(final float n, final float n2, final float n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    private void g() {
        this.S = 0;
        final String parameter = this.getParameter(e("flmp\u0010"));
        this.bo = ((parameter != null) ? b(Integer.parseInt(parameter), 1, 256) : 64);
        final String parameter2 = this.getParameter(e("t\u007fov\u0019dgog"));
        if (parameter2 != null) {
            if (parameter2.compareTo("") == 0) {
                this.bw = -1;
            }
            else {
                this.bw = this.M.indexOf(parameter2);
            }
        }
        else {
            this.bw = this.M.indexOf(" ");
        }
        final String parameter3 = this.getParameter(e("cjbt\u0005"));
        this.bs = ((parameter3 != null) ? Math.abs(Integer.parseInt(parameter3)) : 30);
        final String parameter4 = this.getParameter(e("bcof\bnlga\u0005"));
        this.bn = ((parameter4 != null) ? a(Float.valueOf(parameter4), 0.0f, 0.98f) : 0.85f);
        this.bp = -this.n * 64;
        this.bq = (this.n - this.K) * 64;
        this.br = this.m / this.I;
        this.ba = new int[this.br];
        this.Z = new int[this.br];
        this.bb = new int[this.br];
        this.bc = new int[this.br];
        this.bd = new int[this.br];
        this.be = new int[this.br];
        this.bf = new int[this.br];
        this.bg = new boolean[this.br];
        this.bh = new int[this.br];
        this.bj = new int[this.br];
        this.bk = new int[this.br][25];
        this.h();
        this.j();
        this.bm = (int)(Math.random() * this.bl);
        for (int i = 0; i < this.br; ++i) {
            this.bj[i] = this.bk[i][this.bm];
        }
        this.bu = new int[this.V][this.br];
        for (int j = 0; j < this.V; ++j) {
            final String s = this.U.elementAt(j);
            final int length = s.length();
            for (int k = 0; k < this.br; ++k) {
                this.bu[j][k] = this.bw;
            }
            if (length < this.br) {
                final int n = (this.br - length) / 2;
                for (int l = 0; l < length; ++l) {
                    this.bu[j][n + l] = this.M.indexOf(s.substring(l, l + 1));
                }
            }
            else {
                for (int n2 = 0; n2 < this.br; ++n2) {
                    this.bu[j][n2] = this.M.indexOf(s.substring(n2, n2 + 1));
                }
            }
        }
    }
    
    private void h() {
        for (int i = 0; i < this.br; ++i) {
            this.ba[i] = this.bo;
            this.Z[i] = this.bp;
            this.bb[i] = 0;
            this.bc[i] = 0;
            this.bd[i] = 0;
            this.be[i] = 0;
            this.bf[i] = this.Z[i];
            this.bg[i] = false;
            this.bh[i] = 1;
            this.bt = this.bs;
        }
    }
    
    private void i() {
        final int bq = this.bq;
        final int i = this.I;
        final int w = this.W;
        final boolean[] bg = this.bg;
        final int[][] bu = this.bu;
        final int[] bc = this.bc;
        final int[] bb = this.bb;
        final int[] bh = this.bh;
        final int[] ba = this.ba;
        final int[] bd = this.bd;
        final int[] z = this.Z;
        final int[] bf = this.bf;
        final int[] be = this.be;
        final int[] bj = this.bj;
        if (this.T) {
            this.f();
        }
        else {
            this.e();
        }
        for (int j = 0; j < this.br; ++j) {
            if (bj[j] <= 0 && !bg[j]) {
                bc[j] = bb[j] + bh[j] * ba[j] * be[j];
                bd[j] = (int)(0.5f * (bc[j] + bb[j]) * be[j]);
                z[j] = bf[j] + bh[j] * bd[j];
                if (z[j] >= bq && bh[j] == 1) {
                    bb[j] = (int)(bc[j] * this.bn);
                    be[j] = 0;
                    bh[j] = -1;
                    bf[j] = (z[j] = bq);
                    if (bb[j] < 64.0f + 0.5f * ba[j]) {
                        bg[j] = true;
                        ++this.bi;
                    }
                }
                else if (bc[j] <= 0 && be[j] > 0 && z[j] < bq && bh[j] == -1) {
                    bb[j] = bc[j];
                    be[j] = 0;
                    bh[j] = 1;
                    bf[j] = z[j];
                }
                final int[] array = be;
                final int n = j;
                ++array[n];
            }
            this.a(bu[this.bv][j], j * (i + w), z[j] >> 6);
            final int[] array2 = bj;
            final int n2 = j;
            --array2[n2];
        }
        if (this.bi == this.br) {
            --this.bt;
            if (this.bt <= 0) {
                this.bi = 0;
                this.h();
                this.bv = (this.bv + 1) % this.V;
                this.bm = (int)(Math.random() * this.bl);
                for (int k = 0; k < this.br; ++k) {
                    this.bj[k] = this.bk[k][this.bm];
                }
            }
        }
    }
    
    private void j() {
        this.bl = 0;
        int n = this.br / 2;
        if (n != this.br / 2.0f) {
            ++n;
        }
        for (int i = 0; i < this.br; ++i) {
            this.bk[i][this.bl] = i * 4;
        }
        ++this.bl;
        for (int j = 0; j < this.br; ++j) {
            this.bk[j][this.bl] = (this.br - j - 1) * 4;
        }
        ++this.bl;
        for (int k = 0; k < this.br; ++k) {
            this.bk[k][this.bl] = (int)(Math.random() * 25.0);
        }
        ++this.bl;
        for (int l = 0; l < n; ++l) {
            this.bk[l][this.bl] = l * 3;
        }
        for (int n2 = n; n2 < this.br; ++n2) {
            this.bk[n2][this.bl] = (this.br - n2 - 1) * 3;
        }
        ++this.bl;
        for (int n3 = 0; n3 < this.br; n3 += 2) {
            this.bk[n3][this.bl] = n3 * 2;
            if (n3 + 1 < this.br) {
                this.bk[n3 + 1][this.bl] = (this.br - n3 - 1) * 2;
            }
        }
        ++this.bl;
        for (int n4 = 0; n4 < this.br; n4 += 2) {
            this.bk[n4][this.bl] = n4 * 3;
            if (n4 + 1 < this.br) {
                this.bk[n4 + 1][this.bl] = n4 * 3 + 80;
            }
        }
        ++this.bl;
        if (this.br % 2 == 0) {
            for (int n5 = 0; n5 < this.br / 2; ++n5) {
                this.bk[n5][this.bl] = n5 * 6;
                this.bk[n5 + this.br / 2][this.bl] = n5 * 6;
            }
            ++this.bl;
            for (int n6 = 0; n6 < this.br / 2; ++n6) {
                this.bk[n6][this.bl] = n6 * 6;
                this.bk[this.br - 1 - n6][this.bl] = n6 * 6;
            }
            ++this.bl;
            for (int n7 = 0; n7 < this.br; n7 += 2) {
                this.bk[n7][this.bl] = 0;
                this.bk[n7 + 1][this.bl] = 44;
            }
            ++this.bl;
        }
        if (this.br % 4 == 0) {
            for (int n8 = 0; n8 < 4; ++n8) {
                for (int n9 = 0; n9 < this.br; n9 += 4) {
                    this.bk[n8 + n9][this.bl] = n8 * 6;
                }
            }
            ++this.bl;
            for (int n10 = 0; n10 < this.br; n10 += 4) {
                this.bk[n10][this.bl] = 0;
            }
            for (int n11 = 1; n11 < this.br; n11 += 2) {
                this.bk[n11][this.bl] = 2;
            }
            for (int n12 = 2; n12 < this.br; n12 += 4) {
                this.bk[n12][this.bl] = 4;
            }
            ++this.bl;
        }
    }
    
    private void k() {
        this.S = 1;
        final String parameter = this.getParameter(e("sv~p"));
        if (parameter != null) {
            if (parameter.equals(e("tf`"))) {
                this.bx = 0;
            }
            else if (parameter.equals(e("t\u007fgg\u001dk"))) {
                this.bx = 1;
            }
            else if (parameter.equals(e("df|v\u0010b"))) {
                this.bx = 2;
            }
        }
        else {
            this.bx = 0;
        }
        final String parameter2 = this.getParameter(e("im"));
        this.bI = ((parameter2 != null) ? b(Integer.parseInt(parameter2), 0, 8) : 3);
        final String parameter3 = this.getParameter(e("\u007f}oq\u0015r|"));
        this.bF = ((parameter3 != null) ? Math.abs(Integer.parseInt(parameter3)) : 0);
        final String parameter4 = this.getParameter(e("~}oq\u0015r|"));
        this.bG = ((parameter4 != null) ? Math.abs(Integer.parseInt(parameter4)) : 0);
        final String parameter5 = this.getParameter(e("}}oq\u0015r|"));
        this.bH = ((parameter5 != null) ? a(Float.valueOf(parameter5), 0.0f, 256.0f) : 0.0f);
        final String parameter6 = this.getParameter(e("t\u007fkp\u0018"));
        this.bD = ((parameter6 != null) ? b(Integer.parseInt(parameter6), -128, 128) : 8);
        if (this.bD == 0) {
            this.bD = 1;
        }
        final String parameter7 = this.getParameter(e("anjp"));
        this.bJ = ((parameter7 != null) ? b(Integer.parseInt(parameter7), 0, 255) : 64);
        switch (this.bx) {
            case 1: {
                this.bE = 2 * this.bI * this.bF;
                break;
            }
            case 2: {
                this.bE = 4 * this.bF;
                break;
            }
            default: {
                this.bE = this.m;
                break;
            }
        }
        this.bO = this.bE;
        this.bN = this.K;
        this.bP = this.bO * this.bN;
        this.bK = new int[this.bP];
        this.n();
        this.m();
    }
    
    private void l() {
        final int[] bk = this.bK;
        final int[] bl = this.bL;
        final int[] bm = this.bM;
        final int[] y = this.y;
        final int[] p = this.P;
        final int q = this.Q;
        final int o = this.o;
        final int bj = this.bJ;
        for (int i = 0; i < this.bP; ++i) {
            bk[i] = -16777216;
        }
        if ((this.bD < 0 && this.bB < this.bC) | (this.bD > 0 && this.bB > this.bC)) {
            this.m();
        }
        this.b(this.by, this.bB >> 3, 0, this.W);
        this.bB += this.bD;
        for (int j = 0; j < o; ++j) {
            if (bl[j] != -1) {
                final int n = bk[bl[j]];
                if (n != -16777216) {
                    y[j] = n;
                }
                else if (bm[j] != -1) {
                    final int n2 = p[j];
                    if (n2 == q) {
                        final int n3 = bk[bm[j]];
                        if (n3 != -16777216) {
                            final int n4 = (n3 & 0xFF0000) >> 16;
                            final int n5 = (n3 & 0xFF00) >> 8;
                            final int n6 = n3 & 0xFF;
                            int n7;
                            if (n4 > bj) {
                                n7 = n4 - bj;
                            }
                            else {
                                n7 = 0;
                            }
                            int n8;
                            if (n5 > bj) {
                                n8 = n5 - bj;
                            }
                            else {
                                n8 = 0;
                            }
                            int n9;
                            if (n6 > bj) {
                                n9 = n6 - bj;
                            }
                            else {
                                n9 = 0;
                            }
                            y[j] = (0xFF000000 | n7 << 16 | n8 << 8 | n9);
                        }
                        else {
                            y[j] = p[j];
                        }
                    }
                    else {
                        y[j] = n2;
                    }
                }
                else {
                    y[j] = p[j];
                }
            }
            else if (bm[j] != -1) {
                final int n10 = p[j];
                if (n10 == q) {
                    final int n11 = bk[bm[j]];
                    if (n11 != -16777216) {
                        final int n12 = (n11 & 0xFF0000) >> 16;
                        final int n13 = (n11 & 0xFF00) >> 8;
                        final int n14 = n11 & 0xFF;
                        int n15;
                        if (n12 > bj) {
                            n15 = n12 - bj;
                        }
                        else {
                            n15 = 0;
                        }
                        int n16;
                        if (n13 > bj) {
                            n16 = n13 - bj;
                        }
                        else {
                            n16 = 0;
                        }
                        int n17;
                        if (n14 > bj) {
                            n17 = n14 - bj;
                        }
                        else {
                            n17 = 0;
                        }
                        y[j] = (0xFF000000 | n15 << 16 | n16 << 8 | n17);
                    }
                    else {
                        y[j] = p[j];
                    }
                }
                else {
                    y[j] = n10;
                }
            }
            else {
                y[j] = p[j];
            }
        }
    }
    
    private void m() {
        this.by = this.U.elementAt(this.bz);
        this.bz = (this.bz + 1) % this.V;
        this.bA = this.by.length();
        if (this.bD < 0) {
            this.bB = this.bO << 3;
            this.bC = -(this.bA * (this.I + this.W)) << 3;
            return;
        }
        this.bB = -(this.bA * (this.I + this.W)) << 3;
        this.bC = this.bO << 3;
    }
    
    private void n() {
        final double[] array = new double[this.bE];
        final double[] array2 = new double[this.bE];
        final double[] array3 = new double[this.bE];
        final double[] array4 = new double[this.bE];
        final double n = 3.141592653589793 / this.bE;
        final double n2 = 512.0;
        final double n3 = this.bN / 2.0;
        final double n4 = this.m / 2.0;
        final double n5 = this.n / 2.0;
        switch (this.bx) {
            case 0: {
                final double n6 = this.m / this.bE;
                final double n7 = 511.998;
                for (int i = 0; i < this.bE; ++i) {
                    final double n8 = i * n6 - n4;
                    final double sin = Math.sin(i * this.bI * n);
                    final double n9 = sin * this.bG - n3;
                    final double n10 = sin * this.bH + n7 - this.bH;
                    final double n11 = n7 / n10;
                    array[i] = n8 * n11 + n4;
                    array2[i] = n9 * n11 + n5;
                    array3[i] = (n9 + this.bN) * n11 + n5;
                    array4[i] = n10;
                }
                break;
            }
            case 1: {
                final double n12 = this.n / this.bE;
                if (this.bH == 0.0f) {
                    this.bH = 1.0E-4f;
                }
                for (int j = 0; j < this.bE; ++j) {
                    final double n13 = j * this.bI * n;
                    final double n14 = -Math.cos(n13) * this.bF;
                    final double n15 = j * n12 - n5 - n3;
                    final double n16 = -Math.sin(n13) * this.bH + n2;
                    final double n17 = n2 / n16;
                    array[j] = n14 * n17 + n4;
                    array2[j] = n15 * n17 + n5;
                    array3[j] = (n15 + this.bN) * n17 + n5;
                    array4[j] = n16;
                }
                break;
            }
            case 2: {
                if (this.bH == 0.0f) {
                    this.bH = 1.0E-4f;
                }
                for (int k = 0; k < this.bE; ++k) {
                    final double n18 = k * 2 * n;
                    final double sin2 = Math.sin(n18);
                    final double n19 = Math.cos(n18) * this.bF;
                    final double n20 = -sin2 * this.bG - n3;
                    final double n21 = sin2 * this.bH + n2;
                    final double n22 = n2 / n21;
                    array[k] = n19 * n22 + n4;
                    array2[k] = n20 * n22 + n5;
                    array3[k] = (n20 + this.bN) * n22 + n5;
                    array4[k] = n21;
                }
                break;
            }
        }
        this.bL = new int[this.o];
        this.bM = new int[this.o];
        final double[] array5 = new double[this.o];
        final double[] array6 = new double[this.o];
        for (int l = 0; l < this.o; ++l) {
            array6[l] = (array5[l] = 200000.0);
            this.bL[l] = -1;
            this.bM[l] = -1;
        }
        for (int n23 = this.bE - 1, n24 = 0; n24 < n23; ++n24) {
            double n25 = array[n24];
            double n26 = array[n24 + 1];
            double n27 = array4[n24];
            double n28 = array4[n24 + 1];
            double n29 = array2[n24];
            double n30 = array2[n24 + 1];
            double n31 = array3[n24];
            double n32 = array3[n24 + 1];
            if (n25 > n26) {
                n26 = array[n24];
                n25 = array[n24 + 1];
                n28 = array4[n24];
                n27 = array4[n24 + 1];
                n30 = array2[n24];
                n29 = array2[n24 + 1];
                n32 = array3[n24];
                n31 = array3[n24 + 1];
            }
            final double n33 = n26 - n25;
            final double n34 = (n30 - n29) / n33;
            final double n35 = (n32 - n31) / n33;
            final double n36 = (n28 - n27) / n33;
            final double n37 = this.K - 1.0;
            final int n38 = (int)Math.round(n25);
            for (int n39 = (int)Math.round(n26), n40 = n38; n40 <= n39; ++n40) {
                double n41 = 0.0;
                final double n42 = n37 / (n31 - n29);
                for (int n43 = (int)Math.round(n29); n43 < (int)Math.round(n31); ++n43) {
                    if (n40 >= 0 && n40 < this.m && n43 >= 0 && n43 < this.n) {
                        final int n44 = n40 + n43 * this.m;
                        if (n27 > 512.0 && n27 < array6[n44]) {
                            this.bM[n40 + n43 * this.m] = n24 + (int)(Math.round(n41) * this.bO);
                            array6[n44] = n27;
                        }
                        else if (n27 <= 512.0 && n27 < array5[n44]) {
                            this.bL[n44] = n24 + (int)(Math.round(n41) * this.bO);
                            array5[n44] = n27;
                        }
                    }
                    n41 += n42;
                }
                n27 += n36;
                n29 += n34;
                n31 += n35;
            }
        }
    }
    
    private void b(final String s, final int n, final int n2, final int n3) {
        final String m = this.M;
        final int[] n4 = this.N;
        final int[] o = this.O;
        final int i = this.I;
        final int k = this.K;
        final int h = this.H;
        final int bo = this.bO;
        final int[] bk = this.bK;
        final int be = this.bE;
        final int n5 = n3 + i;
        for (int ba = this.bA, j = 0; j < ba; ++j) {
            final int n6 = n + j * n5;
            if (n6 > -i && n6 < be) {
                final int index = m.indexOf(s.substring(j, j + 1));
                if (index != -1) {
                    int n7 = o[index];
                    int n8 = n6;
                    int n9 = i;
                    if (n6 < 0) {
                        n7 -= n6;
                        n8 = 0;
                        n9 += n6;
                    }
                    else if (n6 > be - i) {
                        n9 = be - n6;
                    }
                    for (int l = 0; l < k; ++l) {
                        System.arraycopy(n4, n7, bk, n8, n9);
                        n7 += h;
                        n8 += bo;
                    }
                }
            }
        }
    }
    
    private void o() {
        this.S = 2;
        final String parameter = this.getParameter(e("im|z\bf{gz\u0012"));
        this.bU = ((parameter != null) ? ((float)(Math.abs(Integer.parseInt(parameter)) * 3.141592653589793)) : 6.2831855f);
        final String parameter2 = this.getParameter(e("}`ax1fw"));
        this.ca = ((parameter2 != null) ? Math.abs(1.0f / Float.valueOf(parameter2)) : 1.0f);
        final String parameter3 = this.getParameter(e("}`ax1na"));
        this.cb = ((parameter3 != null) ? Math.abs(1.0f / Float.valueOf(parameter3)) : 5.0f);
        final String parameter4 = this.getParameter(e("t\u007fkp\u0018"));
        this.cj = ((parameter4 != null) ? ((float)(Math.abs(Integer.parseInt(parameter4)) * 0.001)) : 0.008f);
        final String parameter5 = this.getParameter(e("cjbt\u0005"));
        this.cg = ((parameter5 != null) ? Math.abs(Integer.parseInt(parameter5)) : 10);
        this.q();
        this.bW = this.m * 0.5f;
        this.bX = this.n * 0.5f;
        this.ce = 32768 * this.bS;
        this.cf = 32768 * this.bR;
        this.cd = (this.cb - this.ca) * 0.5f;
        this.cc = this.ca + this.cd;
        this.bY = 6.2831855f;
    }
    
    private void p() {
        if (this.ci > this.bY) {
            this.q();
            this.ch = 0;
            this.ci -= this.bY;
        }
        else if (this.ci > 3.141592653589793 && this.ch < this.cg) {
            ++this.ch;
            this.bV = 0.0f;
        }
        else {
            this.ci += this.cj;
            this.bZ = (float)(Math.cos(this.ci) * this.cd + this.cc) * 65536.0f;
            this.bV = (float)(Math.cos(this.ci) * this.bU + this.bU);
        }
        final int bs = this.bS;
        final int br = this.bR;
        final int m = this.m;
        final int n = this.n;
        final int[] y = this.y;
        final int[] bq = this.bQ;
        final int[] p = this.P;
        final int q = this.Q;
        final float bz = this.bZ;
        final int n2 = (int)(Math.cos(this.bV) * bz);
        final int n3 = (int)(Math.sin(this.bV) * bz);
        final int n4 = -n3;
        final int n5 = n2;
        int n6 = (int)(this.ce - this.bW * n2 - this.bX * n4);
        int n7 = (int)(this.cf - this.bW * n3 - this.bX * n5);
        int n8 = 0;
        if (this.T) {
            this.f();
            for (int i = 0; i < n; ++i) {
                int n9 = n6;
                int n10 = n7;
                for (int j = 0; j < m; ++j) {
                    final int n11 = n9 >> 16;
                    final int n12 = n10 >> 16;
                    if (n11 < bs && n12 < br && n11 > 0 && n12 > 0) {
                        final int n13 = bq[n11 + n12 * bs];
                        if (n13 != -16777216) {
                            y[n8] = n13;
                        }
                    }
                    ++n8;
                    n9 += n2;
                    n10 += n3;
                }
                n6 += n4;
                n7 += n5;
            }
            return;
        }
        for (int k = 0; k < n; ++k) {
            int n14 = n6;
            int n15 = n7;
            for (int l = 0; l < m; ++l) {
                final int n16 = n14 >> 16;
                final int n17 = n15 >> 16;
                int n18;
                if (n16 < bs && n17 < br && n16 > 0 && n17 > 0) {
                    n18 = bq[n16 + n17 * bs];
                    if (n18 == q) {
                        n18 = p[n8];
                    }
                }
                else {
                    n18 = p[n8];
                }
                y[n8] = ((y[n8] & 0xFEFEFE) >> 1) + ((n18 & 0xFEFEFE) >> 1);
                ++n8;
                n14 += n2;
                n15 += n3;
            }
            n6 += n4;
            n7 += n5;
        }
    }
    
    private void q() {
        this.by = this.U.elementAt(this.bz);
        this.bz = (this.bz + 1) % this.V;
        this.bA = this.by.length();
        this.bS = this.bA * (this.I + this.W);
        this.bR = this.K;
        this.ce = 32768 * this.bS;
        this.cf = 32768 * this.bR;
        this.bT = this.bS * this.bR;
        this.bQ = new int[this.bT];
        for (int i = 0; i < this.bT; ++i) {
            this.bQ[i] = this.Q;
        }
        this.c(this.by, 0, 0, this.W);
    }
    
    private void c(final String s, final int n, final int n2, final int n3) {
        for (int length = s.length(), i = 0; i < length; ++i) {
            this.c(this.M.indexOf(s.substring(i, i + 1)), n + i * (n3 + this.I), n2);
        }
    }
    
    private void c(final int n, final int n2, final int n3) {
        if (n != -1 && n2 < this.bS && n2 + this.I >= 0) {
            int n4 = this.O[n];
            final int n5 = n2 + this.I;
            for (int n6 = n3 + this.K, i = n3; i < n6; ++i) {
                final int n7 = n4;
                if (i < this.bR && i >= 0) {
                    final int n8 = i * this.bS;
                    for (int j = n2; j < n5; ++j) {
                        if (j < this.bS && j >= 0) {
                            final int n9 = this.N[n4];
                            if (n9 != -16777216) {
                                this.bQ[j + n8] = n9;
                            }
                        }
                        ++n4;
                    }
                }
                n4 = n7 + this.H;
            }
        }
    }
    
    public madtext() {
        this.c = false;
        this.r = 1;
        this.A = false;
        this.B = false;
        this.D = false;
        this.F = false;
        this.Q = -16777216;
        this.R = 8;
        this.T = false;
    }
    
    private static String e(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u0007';
                    break;
                }
                case 1: {
                    c2 = '\u000f';
                    break;
                }
                case 2: {
                    c2 = '\u000e';
                    break;
                }
                case 3: {
                    c2 = '\u0015';
                    break;
                }
                default: {
                    c2 = '|';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
