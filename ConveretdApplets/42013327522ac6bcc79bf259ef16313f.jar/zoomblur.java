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
import java.util.Random;
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

public class zoomblur extends Applet implements Runnable, ImageObserver
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
    final String C = "VcH\u001b\"c3Z\u000egQrZ\u001e(7PQ\u0002$tz\u0018_0`d\u0016\u0016)q";
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
    private int[] Q;
    private int[] R;
    private int[] S;
    private int T;
    private int U;
    private Vector V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int ba;
    private int bb;
    private int bc;
    private int bd;
    private float be;
    private int bf;
    private int bg;
    private int bh;
    private int bi;
    private int bj;
    private int bk;
    private String bl;
    private int bm;
    private int bn;
    private static double bo;
    private Random bp;
    private int bq;
    private String br;
    private int bs;
    private int bt;
    private int bu;
    private int bv;
    private int bw;
    private int bx;
    private int by;
    private int bz;
    private int bA;
    private int bB;
    private int bC;
    private int bD;
    private int bE;
    private int bF;
    private float[][] bG;
    private int bH;
    private int bI;
    private int bJ;
    private int bK;
    private int bL;
    private int bM;
    private int bN;
    private int bO;
    private int bP;
    private float bQ;
    private float bR;
    public static int bS;
    
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
                this.showStatus(e("^~Y\u0010\"7") + s + e("7}W\u0003gq|M\u0019#6"));
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
            this.showStatus(e("S|VP37a]\u001a(av\u0018\u00000`=Y\u0019!ng]\u0016*9pW\u001agta]\u0013.c`\u0018\u001b.yv\u0018\u001e)7[l:\u000b6"));
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
        final int bs = zoomblur.bS;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        this.z = this.getParameter(e("dgY\u00032d~K\u0010"));
        final String parameter = this.getParameter(e("ta]\u0013.c`"));
        String protocol;
        final String s = protocol = parameter;
        String e = null;
        Label_0117: {
            Label_0086: {
                Label_0082: {
                    if (bs == 0) {
                        if (s == null) {
                            break Label_0082;
                        }
                        final String s2;
                        protocol = (s2 = parameter);
                    }
                    if (bs != 0) {
                        break Label_0117;
                    }
                    if (s.startsWith(e("VcH\u001b\"c3Z\u000egQrZ\u001e(7PQ\u0002$tz\u0018_0`d\u0016\u0016)q"))) {
                        break Label_0086;
                    }
                    this.a();
                    if (bs == 0) {
                        break Label_0086;
                    }
                }
                this.a();
            }
            (this.i = new Lware(this, e("M|W\u001a\u0005{fJW&gcT\u00123"))).hide();
            try {
                protocol = this.getDocumentBase().getProtocol();
                e = protocol;
            }
            catch (SecurityException ex) {
                e = e("qzT\u0012");
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
                if (bs == 0) {
                    Label_0217: {
                        if (!e.equals(e("qzT\u0012"))) {
                            int n3;
                            int startsWith;
                            int n2;
                            final int n = n2 = (startsWith = (n3 = s3.length()));
                            if (bs == 0) {
                                if (n < 1) {
                                    break Label_0217;
                                }
                                final int n4;
                                n2 = (n4 = (startsWith = (n3 = (s3.startsWith(e("{|[\u0016+")) ? 1 : 0))));
                            }
                            if (bs == 0) {
                                if (n != 0) {
                                    break Label_0217;
                                }
                                startsWith = (n2 = (n3 = (s3.equals(e("&!\u000fYw9#\u0016F")) ? 1 : 0)));
                            }
                            if (bs == 0) {
                                if (n2 != 0) {
                                    break Label_0217;
                                }
                                n3 = (startsWith = (s3.startsWith(e("`dOY")) ? 1 : 0));
                            }
                            if (bs == 0) {
                                if (startsWith != 0) {
                                    s3 = s3.substring(4);
                                }
                                n3 = s3.length();
                            }
                            final int n6;
                            final int n5 = n6 = n3;
                            if (bs != 0 || n6 > 0) {
                                final char[] array = new char[n6];
                                s3.getChars(0, n5, array, 0);
                                int n7 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0318: {
                                            if (bs == 0) {
                                                break Label_0318;
                                            }
                                            final char[] array2 = array;
                                            final int n8 = n7;
                                            if (bs != 0 || array2[n8] == '0') {
                                                array2[n8] = '1';
                                            }
                                            n7 += 5;
                                        }
                                        if (n7 < n5) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (bs != 0) {
                                        continue;
                                    }
                                    break;
                                }
                                s3 = new String(array);
                            }
                            final String s4 = parameter2 = this.getParameter(e("ev_\u0014(sv"));
                            if (bs != 0) {
                                break Label_0650;
                            }
                            if (parameter2 == null) {
                                break Label_0641;
                            }
                            final String s5 = s4;
                            if (bs != 0) {
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
                                            if (bs == 0) {
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
                                    if (bs != 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex3) {}
                            final int[] array3 = new int[n9];
                            final int n11 = n9;
                            if (bs == 0 && n11 == 1) {
                                array3[0] = s4.length();
                                if (bs != 0) {
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
                                                if (bs == 0) {
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
                                        if (bs != 0) {
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
                                        if (bs == 0) {
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
                                        if (bs != 0) {
                                            if (bs != 0) {
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
                    if (bs != 0) {}
                }
            }
            this.getParameter(e("ev_\u001b.yx"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0718: {
            if (bs == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (bs != 0) {
                        break Label_0718;
                    }
                    if (!s8.equalsIgnoreCase(e("Y\\"))) {
                        try {
                            this.E = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.E = null;
                        }
                    }
                }
                this.getParameter(e("ev_\u0019\"`uJ\u0016*r"));
            }
        }
        if (s7.equalsIgnoreCase(e("NVk"))) {
            this.F = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0759: {
                    if (bs == 0) {
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
            if (bs == 0) {
                final String parameter3;
                final String s9 = parameter3 = this.getParameter(e("xe]\u0005.zt"));
                Label_0933: {
                    if (bs == 0) {
                        zoomblur zoomblur = null;
                        Label_0925: {
                            if (parameter3 != null) {
                                final String s10 = s9;
                                if (bs != 0) {
                                    break Label_0933;
                                }
                                if (!s10.equalsIgnoreCase(e("Y\\"))) {
                                    this.t = this.a(s9);
                                    zoomblur = this;
                                    if (bs != 0) {
                                        break Label_0925;
                                    }
                                    if (this.t != null) {
                                        final String parameter4;
                                        String s11 = parameter4 = this.getParameter(e("xe]\u0005.zt`"));
                                        if (bs == 0) {
                                            if (parameter4 == null) {
                                                s11 = "0";
                                            }
                                            this.w = Integer.valueOf(s11);
                                            this.getParameter(e("xe]\u0005.zta"));
                                        }
                                        final String s13;
                                        String s12 = s13 = parameter4;
                                        if (bs != 0 || s13 == null) {
                                            s12 = s13;
                                        }
                                        this.x = Integer.valueOf(s12);
                                    }
                                }
                            }
                            zoomblur = this;
                        }
                        zoomblur.getParameter(e("ZzV$\u001eYP"));
                    }
                }
                final String s14;
                String e2 = s14 = parameter3;
                Label_1007: {
                    zoomblur zoomblur2 = null;
                    final String e3;
                    Label_0998: {
                        if (bs == 0) {
                            if (s14 == null) {
                                e2 = e("&#");
                            }
                            this.e = Integer.valueOf(e2);
                            zoomblur2 = this;
                            e3 = e("evK");
                            if (bs != 0) {
                                break Label_0998;
                            }
                            final String parameter5;
                            e2 = (parameter5 = this.getParameter(e3));
                        }
                        if (s14 == null) {
                            this.r = 1;
                            if (bs == 0) {
                                break Label_1007;
                            }
                        }
                        zoomblur2 = this;
                    }
                    zoomblur2.r = Integer.valueOf(e3);
                }
                final int r = this.r;
                final int n16 = 8;
                int n19 = 0;
                int l = 0;
                int n17 = 0;
                Label_1092: {
                    Label_1050: {
                        if (bs == 0) {
                            if (r > n16) {
                                this.r = 8;
                                if (bs == 0) {
                                    break Label_1050;
                                }
                            }
                            final int n18;
                            n17 = (n18 = (l = (n19 = this.r)));
                            if (bs != 0) {
                                break Label_1092;
                            }
                        }
                        if (r < n16) {
                            this.r = 1;
                        }
                    }
                    this.k = Integer.valueOf(this.getParameter(e("zvU\u0013\"{rA")));
                    this.l = Integer.valueOf(this.getParameter(e("gaQ\u00185~gA")));
                    l = (n17 = (n19 = this.k));
                }
                if (bs == 0) {
                    if (n17 < 0) {
                        this.k = 0;
                    }
                    n19 = (l = this.l);
                }
                final int n20 = 10;
                Label_1241: {
                    Label_1148: {
                        if (bs == 0) {
                            if (l > n20) {
                                this.l = 10;
                                if (bs == 0) {
                                    break Label_1148;
                                }
                            }
                            final zoomblur zoomblur3 = this;
                            if (bs != 0) {
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
                        final zoomblur zoomblur3 = this;
                        zoomblur3.b();
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
            s = System.getProperty(e("}rN\u0016iavJ\u0004.x}"));
        }
        catch (SecurityException ex) {
            s = e("b}S");
        }
        if (!s.startsWith(e("&=\b"))) {
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
        this.i.dr(e("v}^\u000e"), 1, this.D);
        while (this.g != null) {
            this.e();
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
        final int bs = zoomblur.bS;
        zoomblur zoomblur = this;
        Label_0065: {
            if (bs != 0) {
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
                if (bs == 0) {
                    return true;
                }
            }
            try {
                zoomblur zoomblur2 = this;
                zoomblur = this;
                Label_0084: {
                    if (bs != 0) {
                        break Label_0084;
                    }
                    if (zoomblur.E == null) {
                        return true;
                    }
                    try {
                        this.i.dck();
                        zoomblur zoomblur3 = this;
                        zoomblur2 = this;
                        if (bs == 0) {
                            if (zoomblur2.F) {
                                this.getAppletContext().showDocument(this.E, this.getParameter(e("ev_\u00115v~]\u0019&zv")));
                                if (bs == 0) {
                                    return true;
                                }
                            }
                            zoomblur3 = this;
                        }
                        zoomblur3.getAppletContext().showDocument(this.E);
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
            this.G = String.valueOf(streamTokenizer.sval) + e("9tQ\u0011");
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
            System.err.println(String.valueOf(ex) + e("-3}\u00055xa\u0018\u0000/~\u007f]W5rr\\\u001e)p3y\u0019!n3~\u0018)c3^\u001e+r"));
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
    
    void a(final String s, final int n, final int n2) {
        final int n3 = (this.m - this.I * s.length()) / 2;
        final int n4 = (this.n - this.K) / 2;
        if (n2 != 0) {
            this.b(s, n3, n4, n);
            return;
        }
        this.a(s, n3, n4, n);
    }
    
    void a(final String s, final int n, final int n2, final int n3, final int n4) {
        if (n4 != 0) {
            this.b(s, n, n2, n3);
            return;
        }
        this.a(s, n, n2, n3);
    }
    
    void a(final String s, final int n, final int n2, final int n3) {
        final int length = s.length();
        final int m = this.m;
        final int n4 = this.n;
        final int[] y = this.y;
        final int[] n5 = this.N;
        final int[] o = this.O;
        final int i = this.I;
        final int k = this.K;
        final int h = this.H;
        for (int j = 0; j < length; ++j) {
            final int n6 = n + j * (n3 + i);
            final int index = this.M.indexOf(s.substring(j, j + 1));
            if (index != -1 && n6 < m && n6 + i > 0) {
                int n7 = o[index];
                for (int l = 0; l < k; ++l) {
                    final int n8 = n2 + l;
                    if (n8 < n4 && n8 > 0) {
                        final int n9 = n6 + n8 * m;
                        for (int n10 = 0; n10 < i; ++n10) {
                            final int n11 = n6 + n10;
                            if (n11 < m && n11 > 0) {
                                final int n12 = n5[n7 + n10];
                                if (n12 != -16777216) {
                                    y[n9 + n10] = n12;
                                }
                            }
                        }
                    }
                    n7 += h;
                }
            }
        }
    }
    
    void b(final String s, final int n, final int n2, final int n3) {
        final int length = s.length();
        final int m = this.m;
        final int n4 = this.n;
        final int[] y = this.y;
        final int[] n5 = this.N;
        final int[] o = this.O;
        final int i = this.I;
        final int k = this.K;
        final int h = this.H;
        for (int j = 0; j < length; ++j) {
            final int n6 = n + j * (n3 + i);
            final int index = this.M.indexOf(s.substring(j, j + 1));
            if (index != -1 && n6 < m && n6 + i > 0) {
                int n7 = o[index];
                for (int l = 0; l < k; ++l) {
                    final int n8 = n2 + l;
                    if (n8 < n4 && n8 > 0) {
                        final int n9 = n6 + n8 * m;
                        for (int n10 = 0; n10 < i; ++n10) {
                            final int n11 = n6 + n10;
                            if (n11 < m && n11 > 0) {
                                final int n12 = n5[n7 + n10];
                                if (n12 != -16777216) {
                                    final int n13 = (y[n9 + n10] & 0xFEFEFF) + (n12 & 0xFEFEFF);
                                    final int n14 = n13 & 0x1010100;
                                    y[n9 + n10] = (0xFF000000 | n13 | n14 - (n14 >> 8));
                                }
                            }
                        }
                    }
                    n7 += h;
                }
            }
        }
    }
    
    private void c() {
        this.bm = this.m / 2;
        this.bn = this.n / 2;
        this.d();
        if (this.bg == 1) {
            this.P = new int[this.o];
            for (int i = 0; i < this.o; ++i) {
                this.P[i] = this.U;
            }
        }
        this.Q = new int[this.o];
        this.R = new int[this.m];
        this.S = new int[this.n];
        final int a = a(this.bm + this.Z, 0, this.m);
        final int a2 = a(this.bn + this.ba, 0, this.n);
        for (int j = 0; j < this.m; ++j) {
            this.R[j] = Math.round(a + (j - a) * this.be);
        }
        for (int k = 0; k < this.n; ++k) {
            this.S[k] = Math.round(a2 + (k - a2) * this.be);
        }
        if (this.bj == 1) {
            if (this.bd < 0) {
                this.bN = 0;
            }
            else {
                this.bN = this.m << 3;
            }
            this.bL = this.bN;
            this.bK = (this.n - this.K) / 2;
        }
        else if (this.bj == 2) {
            if (this.bd < 0) {
                this.bO = this.n << 3;
                this.bP = -this.K << 3;
            }
            else {
                this.bO = -this.K << 3;
                this.bP = this.n << 3;
            }
            this.bM = this.bP;
        }
        else if (this.bj == 3) {
            this.bR = -this.K / 2.0f + this.bn;
        }
        this.bq = this.V.size();
        this.bG = new float[this.bk][3];
        for (int l = 0; l < this.bk; ++l) {
            this.bG[l][0] = (int)((this.bp.nextFloat() * 2.0f - 1.0f) * this.bm);
            this.bG[l][1] = (int)((this.bp.nextFloat() * 2.0f - 1.0f) * this.bn);
            this.bG[l][2] = -500.0f - this.bp.nextFloat() * 1000.0f;
        }
        this.bH = this.bl.length() * this.I / 2;
        this.bI = this.K / 2;
        this.bw = (int)(this.bp.nextFloat() * 20.0f + 1.0f);
        this.bx = (int)(this.bp.nextFloat() * 20.0f + 1.0f);
    }
    
    private void d() {
        final String parameter;
        if ((parameter = this.getParameter(e("q|V\u0003"))) != null) {
            this.a(this.getDocumentBase(), parameter);
        }
        else {
            this.d(e("C{]W!x}LW7vaY\u001a\"cvJW.d3V\u001837w]\u0011.yv\\Wf"));
        }
        final String parameter2;
        if ((parameter2 = this.getParameter(e("cv@\u0003"))) != null) {
            this.c(parameter2);
        }
        else {
            this.d(e("C{]W3rkLW7vaY\u001a\"cvJW.d3V\u001837w]\u0011.yv\\Wf"));
        }
        final String parameter3;
        if ((parameter3 = this.getParameter(e("u\u007fM\u0005"))) != null) {
            this.T = Math.abs(Integer.parseInt(parameter3));
        }
        final String parameter4;
        if ((parameter4 = this.getParameter(e("ut[\u0018+xa"))) != null) {
            this.U |= Integer.parseInt(parameter4, 16);
        }
        final String parameter5;
        if ((parameter5 = this.getParameter(e("szK\u0007+vjU\u0018#r"))) != null && parameter5.equalsIgnoreCase(e("xe]\u0005"))) {
            this.bh = 1;
        }
        final String parameter6;
        if ((parameter6 = this.getParameter(e("dgY\u0005#~`H\u001b&n~W\u0013\""))) != null && parameter6.equalsIgnoreCase(e("y|J\u001a&{"))) {
            this.bi = 0;
        }
        final String parameter7;
        if ((parameter7 = this.getParameter(e("svT\u0016>"))) != null) {
            this.X = Math.abs(Integer.parseInt(parameter7));
        }
        final String parameter8;
        if ((parameter8 = this.getParameter(e("cv@\u0003/dcY\u0014\""))) != null) {
            this.W = a(Integer.parseInt(parameter8), -this.I / 2, 100);
        }
        final String parameter9;
        if ((parameter9 = this.getParameter(e("svT\u0016>&"))) != null) {
            this.Y = Math.abs(Integer.parseInt(parameter9));
            if (this.Y > this.X) {
                this.Y = this.X;
            }
        }
        else {
            this.Y = this.X;
        }
        final String parameter10;
        if ((parameter10 = this.getParameter(e("mqU\u00181r"))) != null) {
            if (parameter10.equalsIgnoreCase(e("tzJ\u0014+r"))) {
                this.bf = 1;
            }
            else if (parameter10.equalsIgnoreCase(e("erV\u0013(z"))) {
                this.bf = 2;
            }
        }
        final String parameter11;
        if ((parameter11 = this.getParameter(e("mqU\u0018#r"))) != null) {
            if (parameter11.equalsIgnoreCase(e("y|"))) {
                this.bg = 1;
            }
            else if (parameter11.equalsIgnoreCase(e("y|Z\u001b2e"))) {
                this.bg = 2;
            }
            else if (parameter11.equalsIgnoreCase(e("y|B\u0018(z"))) {
                this.bg = 3;
            }
        }
        final String parameter12;
        if ((parameter12 = this.getParameter(e("sk"))) != null) {
            this.Z = a(Integer.parseInt(parameter12), -this.bm, this.bm);
        }
        final String parameter13;
        if ((parameter13 = this.getParameter(e("sj"))) != null) {
            this.ba = a(Integer.parseInt(parameter13), -this.bn, this.bn);
        }
        final String parameter14;
        if ((parameter14 = this.getParameter(e("mq[\u0018\"q"))) != null) {
            this.be = a(Integer.parseInt(parameter14), 1, 100) / 100.0f;
        }
        final String parameter15;
        if ((parameter15 = this.getParameter(e("dc]\u0012#"))) != null) {
            this.bd = Integer.parseInt(parameter15);
        }
        final String parameter16;
        if ((parameter16 = this.getParameter(e("yqK\u0003&e`"))) != null) {
            this.bk = Math.abs(Integer.parseInt(parameter16));
        }
        final String parameter17;
        if ((parameter17 = this.getParameter(e("dgY\u0005"))) != null) {
            this.bl = parameter17;
        }
        else {
            this.bk = 0;
        }
        final String parameter18;
        if ((parameter18 = this.getParameter(e("ckL\u001a(av"))) != null) {
            if (parameter18.equalsIgnoreCase(e("\u007f|J\u001e=x}L\u0016+"))) {
                this.bj = 1;
                return;
            }
            if (parameter18.equalsIgnoreCase(e("avJ\u0003.trT"))) {
                this.bj = 2;
                return;
            }
            if (parameter18.equalsIgnoreCase(e("r\u007fT\u001e7dv"))) {
                this.bj = 3;
                final String parameter19;
                if ((parameter19 = this.getParameter(e("ek"))) != null) {
                    this.bb = Integer.parseInt(parameter19);
                }
                final String parameter20;
                if ((parameter20 = this.getParameter(e("ej"))) != null) {
                    this.bc = Integer.parseInt(parameter20);
                }
            }
        }
    }
    
    private void e() {
        switch (this.bg) {
            case 0: {
                this.g();
                this.h();
                this.j();
                break;
            }
            case 1: {
                System.arraycopy(this.P, 0, this.y, 0, this.o);
                this.g();
                break;
            }
            case 2: {
                this.g();
                this.h();
                break;
            }
            case 3: {
                this.g();
                this.j();
                break;
            }
        }
        this.f();
    }
    
    private void f() {
        switch (this.bj) {
            default: {
                if (this.bD == 0) {
                    this.bt = (this.bt + 1) % this.bq;
                    this.i();
                }
                else if (this.bD < this.Y) {
                    this.a(this.V.elementAt(this.bt), this.W, this.bh);
                }
                this.bD = (this.bD + 1) % this.X;
            }
            case 1: {
                if ((this.bd < 0 && this.bL + this.bs <= this.bN) | (this.bd >= 0 && this.bL >= this.bN)) {
                    this.bt = (this.bt + 1) % this.bq;
                    this.br = this.V.elementAt(this.bt);
                    this.bs = this.br.length() * (this.I + this.W) << 3;
                    if (this.bd < 0) {
                        this.bL = this.m << 3;
                    }
                    else {
                        this.bL = -this.bs;
                    }
                    this.i();
                }
                this.bL += this.bd;
                this.a(this.br, this.bL >> 3, this.bK, this.W, this.bh);
            }
            case 2: {
                if ((this.bd < 0 && this.bM <= this.bP) | (this.bd >= 0 && this.bM >= this.bP)) {
                    this.bt = (this.bt + 1) % this.bq;
                    this.br = this.V.elementAt(this.bt);
                    this.bs = this.br.length() * (this.I + this.W);
                    this.bJ = (this.m - this.bs) / 2;
                    this.bM = this.bO;
                    this.i();
                }
                this.bM += this.bd;
                this.a(this.br, this.bJ, this.bM >> 3, this.W, this.bh);
            }
            case 3: {
                if (this.bD == 0) {
                    this.bt = (this.bt + 1) % this.bq;
                    this.br = this.V.elementAt(this.bt);
                    this.bs = this.br.length() * (this.I + this.W);
                    this.bQ = -this.bs / 2.0f + this.bm;
                    this.i();
                }
                else if (this.bD < this.Y) {
                    final double n = Math.cos(zoomblur.bo * this.by) * this.bb;
                    final double n2 = Math.sin(zoomblur.bo * this.by) * Math.sin(zoomblur.bo * this.bA) * this.bc;
                    final double cos = Math.cos(zoomblur.bo * this.bz);
                    final double sin = Math.sin(zoomblur.bo * this.bz);
                    this.bL = (int)Math.round(this.bQ + n * cos - n2 * sin);
                    this.bM = (int)Math.round(this.bR + n * sin + n2 * cos);
                    this.a(this.br, this.bL, this.bM, this.W, this.bh);
                    this.by = (this.by + this.bd) % 3600;
                    if (this.by < 0) {
                        this.by += 3600;
                    }
                    this.bz = (this.bz + this.bB) % 3600;
                    this.bA = (this.bA + this.bC) % 1800;
                }
                this.bD = (this.bD + 1) % this.X;
            }
        }
    }
    
    private void g() {
        final float n = 50.0f;
        for (int i = 0; i < this.bk; ++i) {
            final float[] array2;
            final float[] array = array2 = this.bG[i];
            final int n2 = 2;
            array2[n2] += Math.abs((50.0f - array[2]) / 20.0f);
            if (array[2] < 50.0f) {
                final float n3 = n / (50.0f - array[2]);
                final int n4 = Math.round(array[0] * n3) + this.bm - this.bH;
                final int n5 = Math.round(array[1] * n3) + this.bn - this.bI;
                if (n4 + 2 * this.bH > 0 && n5 + this.K > 0 && n4 < this.m && n5 < this.n) {
                    this.a(this.bl, n4, n5, 0, this.bi);
                }
                else {
                    array[0] = (int)((this.bp.nextFloat() * 2.0f - 1.0f) * this.bm);
                    array[1] = (int)((this.bp.nextFloat() * 2.0f - 1.0f) * this.bn);
                    array[2] = -500.0f - this.bp.nextFloat() * 1000.0f;
                }
            }
        }
    }
    
    private void h() {
        final int[] r = this.R;
        final int[] s = this.S;
        final int m = this.m;
        final int n = this.n;
        if (this.bf != 0) {
            this.bu = (this.bu + this.bw) % this.bE;
            this.bv = (this.bv + this.bx) % this.bF;
            final double n2 = this.bm + this.bm * Math.cos(zoomblur.bo * this.bu);
            final double n3 = this.bn + this.bn * Math.sin(zoomblur.bo * this.bv);
            for (int i = 0; i < m; ++i) {
                r[i] = (int)Math.round(n2 + (i - n2) * this.be);
            }
            for (int j = 0; j < n; ++j) {
                s[j] = (int)Math.round(n3 + (j - n3) * this.be);
            }
        }
        int n4 = 0;
        final int[] q = this.Q;
        for (int k = 0; k < n; ++k) {
            for (int l = 0; l < m; ++l) {
                q[n4++] = this.y[s[k] * m + r[l]];
            }
        }
        System.arraycopy(this.Q, 0, this.y, 0, this.o);
    }
    
    void i() {
        if (this.bf == 2) {
            final int n = 1800 * (int)(this.bp.nextFloat() * 2.0f + 1.0f);
            switch ((int)(6.0f * this.bp.nextFloat())) {
                case 0: {
                    this.bu = 0;
                    this.bv = 0;
                    this.bw = Math.round(n / this.X);
                    this.bx = 0;
                }
                case 1: {
                    this.bu = 900;
                    this.bv = 900;
                    this.bw = 0;
                    this.bx = Math.round(n / this.X);
                }
                case 2: {
                    this.bu = 1800;
                    this.bv = 0;
                    this.bw = -Math.round(n / this.X);
                    this.bx = 0;
                }
                case 3: {
                    this.bu = 900;
                    this.bv = 2700;
                    this.bw = 0;
                    this.bx = -Math.round(n / this.X);
                }
                case 4: {
                    this.bu = 0;
                    this.bv = 0;
                    this.bw = Math.round(2 * n / this.X);
                    this.bx = this.bw;
                }
                case 5: {
                    this.bu = 3600;
                    this.bv = 3600;
                    this.bw = -Math.round(2 * n / this.X);
                    this.bx = this.bw;
                }
            }
        }
    }
    
    void j() {
        final int[] array = new int[this.m];
        final int m = this.m;
        final int n = this.n + 1 - 1;
        final int n2 = m - 1;
        final int[] y = this.y;
        final int t = this.T;
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
                if (n8 > t) {
                    n11 = n8 - t;
                }
                else {
                    n11 = 0;
                }
                int n12;
                if (n9 > t) {
                    n12 = n9 - t;
                }
                else {
                    n12 = 0;
                }
                int n13;
                if (n10 > t) {
                    n13 = n10 - t;
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
    
    private void c(final String s) {
        this.V = new Vector();
        try {
            final InputStream openStream = new URL(this.getDocumentBase(), s).openStream();
            final StreamTokenizer streamTokenizer = new StreamTokenizer(new BufferedInputStream(openStream));
            streamTokenizer.ordinaryChars(32, 255);
            streamTokenizer.wordChars(32, 255);
        Label_0106:
            while (true) {
                switch (streamTokenizer.nextToken()) {
                    case -3: {
                        this.V.addElement(streamTokenizer.sval);
                        continue;
                    }
                    case -1: {
                        break Label_0106;
                    }
                }
            }
            openStream.close();
        }
        catch (Exception ex) {
            System.err.println(String.valueOf(ex) + e("-3}\u00055xa\u0018\u0000/~\u007f]W5rr\\\u001e)p3L\u000f37uQ\u001b\""));
        }
        if (this.V.size() == 0) {
            this.V.addElement("");
        }
        this.V.trimToSize();
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
    
    private static int a(int n, final int n2, final int n3) {
        if (n < n2) {
            n = n2;
        }
        else if (n > n3) {
            n = n3;
        }
        return n;
    }
    
    public zoomblur() {
        this.c = false;
        this.r = 1;
        this.A = false;
        this.B = false;
        this.D = false;
        this.F = false;
        this.T = 8;
        this.U = -16777216;
        this.X = 100;
        this.bd = -8;
        this.be = 0.95f;
        this.bi = 1;
        this.bl = "";
        this.bp = new Random();
        this.bt = -1;
        this.bw = 10;
        this.bx = 15;
        this.bB = 3;
        this.bC = 2;
        this.bE = 3600;
        this.bF = 3600;
    }
    
    static {
        zoomblur.bo = 0.0017453292519943296;
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
                    c2 = '\u0017';
                    break;
                }
                case 1: {
                    c2 = '\u0013';
                    break;
                }
                case 2: {
                    c2 = '8';
                    break;
                }
                case 3: {
                    c2 = 'w';
                    break;
                }
                default: {
                    c2 = 'G';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
