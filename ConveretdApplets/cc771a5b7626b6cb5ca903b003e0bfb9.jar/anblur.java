import java.awt.Event;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Label;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Component;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class anblur extends Applet implements Runnable, ImageObserver
{
    Frame a;
    boolean b;
    boolean c;
    boolean d;
    int e;
    int f;
    Image[] g;
    String h;
    String i;
    final String j = "/<0\u007f\\\u001al\"j\u0019(-\"zVN\u000f)fZ\r%`;N\u0019;nrW\b";
    private static int k;
    Image[] l;
    Image[] m;
    int[] n;
    boolean o;
    boolean p;
    String q;
    boolean r;
    int s;
    int t;
    URL u;
    MediaTracker v;
    boolean w;
    int x;
    String y;
    Graphics z;
    Image A;
    boolean B;
    private Image C;
    int D;
    int E;
    int F;
    boolean G;
    int H;
    int I;
    boolean J;
    int K;
    boolean L;
    int[] M;
    int[] N;
    int[] O;
    String P;
    AudioClip Q;
    AudioClip R;
    int S;
    Thread T;
    Image[] U;
    int V;
    int W;
    Lware X;
    
    public anblur() {
        this.B = false;
        this.b = false;
        this.L = false;
        this.w = false;
        this.J = false;
        this.p = false;
    }
    
    void a() {
        if (++this.s == this.x) {
            this.s = 0;
        }
        this.S = this.s;
        if (this.s > 0) {
            --this.s;
        }
        else {
            this.s = this.x - 1;
        }
        anblur.k = 2;
        this.repaint();
        try {
            Thread.sleep(this.t);
        }
        catch (InterruptedException ex) {}
        anblur.k = 1;
        this.repaint();
        try {
            Thread.sleep(this.t);
        }
        catch (InterruptedException ex2) {}
        this.s = this.S;
        anblur.k = 3;
        this.repaint();
        try {
            Thread.sleep(this.t);
        }
        catch (InterruptedException ex3) {}
        anblur.k = 2;
    }
    
    void b() {
        if (anblur.k == 2) {
            if (++this.s == this.x - 1) {
                this.S = this.s;
                --this.s;
                anblur.k = 2;
                this.repaint();
                try {
                    Thread.sleep(this.t);
                }
                catch (InterruptedException ex) {}
                anblur.k = 1;
                this.repaint();
                try {
                    Thread.sleep(this.t);
                }
                catch (InterruptedException ex2) {}
                if (this.d) {
                    this.R.play();
                }
                this.s = this.S;
                anblur.k = 3;
                this.repaint();
                try {
                    Thread.sleep(this.H);
                }
                catch (InterruptedException ex3) {}
                --this.s;
                anblur.k = 1;
            }
        }
        else if (--this.s == 0) {
            anblur.k = 1;
            this.repaint();
            try {
                Thread.sleep(this.t);
            }
            catch (InterruptedException ex4) {}
            anblur.k = 2;
            this.repaint();
            try {
                Thread.sleep(this.t);
            }
            catch (InterruptedException ex5) {}
            if (this.c) {
                this.Q.play();
            }
            anblur.k = 3;
            this.repaint();
            try {
                Thread.sleep(this.F);
            }
            catch (InterruptedException ex6) {}
            ++this.s;
            anblur.k = 2;
        }
        if (anblur.k == 2) {
            this.S = this.s;
            --this.s;
            this.repaint();
            try {
                Thread.sleep(this.t);
            }
            catch (InterruptedException ex7) {}
            anblur.k = 1;
            this.repaint();
            try {
                Thread.sleep(this.t);
            }
            catch (InterruptedException ex8) {}
            this.s = this.S;
            anblur.k = 3;
            this.repaint();
            try {
                Thread.sleep(this.t);
            }
            catch (InterruptedException ex9) {}
            anblur.k = 2;
            return;
        }
        if (anblur.k == 1) {
            this.repaint();
            try {
                Thread.sleep(this.t);
            }
            catch (InterruptedException ex10) {}
            anblur.k = 2;
            this.repaint();
            try {
                Thread.sleep(this.t);
            }
            catch (InterruptedException ex11) {}
            anblur.k = 3;
            this.repaint();
            try {
                Thread.sleep(this.t);
            }
            catch (InterruptedException ex12) {}
            anblur.k = 1;
        }
    }
    
    private final void c() {
        while (true) {
            this.showStatus(b("*#.4MN>%~V\u0018)`dN\u0019b!}_\u0017&!eX@//~\u0019\r>%wP\u001a?`\u007fP\u0000)`zWN\u0004\u0014^uO"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean d() {
        this.prepareImage(this.C, this);
        for (int i = 0; i < 3; ++i) {
            this.notifyAll();
            Thread.yield();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        return this.B;
    }
    
    private void a(final int n) {
        if (!this.J) {
            this.a(this.N, this.O, 86);
            try {
                this.l[n] = this.createImage(new MemoryImageSource(this.W, this.V, new DirectColorModel(32, 16711680, 65280, 255), this.n, 0, this.W));
            }
            catch (Exception ex) {}
            if (this.o) {
                this.f(n);
            }
            else {
                this.b(n);
            }
            this.a(this.N, this.O, 172);
            try {
                this.m[n] = this.createImage(new MemoryImageSource(this.W, this.V, new DirectColorModel(24, 16711680, 65280, 255), this.n, 0, this.W));
            }
            catch (Exception ex2) {}
            if (this.o) {
                this.g(n);
                return;
            }
            this.c(n);
        }
        else {
            this.a(this.O, this.N, 86);
            try {
                this.l[n] = this.createImage(new MemoryImageSource(this.W, this.V, new DirectColorModel(32, 16711680, 65280, 255), this.n, 0, this.W));
            }
            catch (Exception ex3) {}
            if (this.o) {
                this.f(n);
            }
            else {
                this.b(n);
            }
            this.a(this.O, this.N, 172);
            try {
                this.m[n] = this.createImage(new MemoryImageSource(this.W, this.V, new DirectColorModel(24, 16711680, 65280, 255), this.n, 0, this.W));
            }
            catch (Exception ex4) {}
            if (this.o) {
                this.g(n);
                return;
            }
            this.c(n);
        }
    }
    
    private boolean a(final int n, final int[] array) throws InterruptedException {
        if (this.J) {
            this.J = false;
        }
        else {
            this.J = true;
        }
        final PixelGrabber pixelGrabber = new PixelGrabber(this.g[n].getSource(), 0, 0, this.W, this.V, array, 0, this.W);
        try {
            if (!pixelGrabber.grabPixels(0L)) {
                pixelGrabber.grabPixels(0L);
            }
            if ((pixelGrabber.status() & 0x80) != 0x0) {
                throw new InterruptedException(b("+>2|KN%.3i\u00074%\u007f~\u001c-\"q\\\u001c.%a\u0003N\r\u0002\\k:"));
            }
        }
        catch (InterruptedException ex) {
            throw new InterruptedException(b(">%8vU)>!q[\u000b>\"vKN\t2aV\u001cv") + ex + b("N\u001f4rM\u001b?z3") + pixelGrabber.status());
        }
        this.g[n].flush();
        try {
            this.g[n] = this.createImage(new MemoryImageSource(this.W, this.V, new DirectColorModel(24, 16711680, 65280, 255), array, 0, this.W));
        }
        catch (Exception ex2) {}
        if (this.o) {
            this.e(n);
        }
        else {
            this.d(n);
        }
        if (n == 0) {
            try {
                System.arraycopy(array, 0, this.M, 0, this.W * this.V);
            }
            catch (ArrayStoreException ex3) {}
        }
        else if (n == this.x - 1) {
            this.a(n - 1);
            if (this.J) {
                this.J = false;
            }
            else {
                this.J = true;
            }
            this.a(this.M, array, 86);
            try {
                this.l[this.x - 1] = this.createImage(new MemoryImageSource(this.W, this.V, new DirectColorModel(32, 16711680, 65280, 255), this.n, 0, this.W));
            }
            catch (Exception ex4) {}
            if (this.o) {
                this.f(this.x - 1);
            }
            else {
                this.b(this.x - 1);
            }
            this.a(this.M, array, 172);
            try {
                this.m[this.x - 1] = this.createImage(new MemoryImageSource(this.W, this.V, new DirectColorModel(24, 16711680, 65280, 255), this.n, 0, this.W));
            }
            catch (Exception ex5) {}
            if (this.o) {
                this.g(this.x - 1);
            }
            else {
                this.c(this.x - 1);
            }
        }
        else {
            this.a(n - 1);
        }
        return true;
    }
    
    public void destroy() {
        if (this.C != null) {
            this.C.flush();
        }
        this.C = null;
        if (this.A != null) {
            this.A.flush();
        }
        this.A = null;
        if (this.z != null) {
            this.z.dispose();
        }
        this.z = null;
        System.gc();
    }
    
    Image a(final String s) {
        URL url = null;
        Image image = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        try {
            final InputStream resourceAsStream = this.getClass().getResourceAsStream(url.toString());
            if (resourceAsStream != null) {
                final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
                final byte[] array = new byte[512];
                boolean b = false;
                byte[] byteArray = null;
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
                    image = null;
                }
                System.gc();
                if (byteArray != null) {
                    image = this.getToolkit().createImage(byteArray);
                }
            }
        }
        catch (NoSuchMethodError noSuchMethodError) {
            image = null;
        }
        if (image == null) {
            int i = 0;
            while (i < 5) {
                try {
                    image = Toolkit.getDefaultToolkit().getImage(url);
                    final MediaTracker mediaTracker = new MediaTracker(this);
                    try {
                        mediaTracker.addImage(image, 0);
                        mediaTracker.waitForID(0);
                    }
                    catch (InterruptedException ex3) {
                        image = null;
                    }
                    if (mediaTracker.isErrorID(0)) {
                        image = null;
                    }
                    i = 6;
                }
                catch (NullPointerException ex4) {
                    ++i;
                    System.gc();
                }
            }
        }
        while (image == null) {
            this.showStatus(b("'!!t\\N") + s + b("N\"/g\u0019\b#5}]O"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex5) {}
        }
        return image;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.C) {
            if (n == 16) {
                this.B = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.r = false;
        this.P = this.getParameter(b("\u001d8!gL\u001d!3t"));
        final String parameter = this.getParameter(b("\r>%wP\u001a?"));
        if (parameter != null) {
            if (!parameter.startsWith(b("/<0\u007f\\\u001al\"j\u0019(-\"zVN\u000f)fZ\r%`;N\u0019;nrW\b"))) {
                this.c();
            }
        }
        else {
            this.c();
        }
        String s;
        try {
            s = this.getDocumentBase().getProtocol();
        }
        catch (SecurityException ex) {
            s = b("\b%,v");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(b("\b%,v")) || s2.length() == 0 || s2.equalsIgnoreCase(b("\u0002##rU\u0006#3g")) || s2.equals(b("_~w=\t@|n\""))) {
            this.L = true;
        }
        else {
            if (s2.startsWith(b("\u0019;7="))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(b("\u001c)'pV\n)"));
            if (parameter2 != null && !parameter2.equals("NO") && parameter2.length() > 10) {
                int n = 1;
                try {
                    for (int i = 0; i < parameter2.length(); ++i) {
                        if (parameter2.charAt(i) == '+') {
                            ++n;
                        }
                    }
                }
                catch (StringIndexOutOfBoundsException ex3) {}
                final int[] array = new int[n];
                if (n == 1) {
                    array[0] = parameter2.length();
                }
                else {
                    int n2 = 0;
                    try {
                        for (int j = 0; j < parameter2.length(); ++j) {
                            if (parameter2.charAt(j) == '+') {
                                array[n2] = j;
                                ++n2;
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex4) {}
                    array[n2] = parameter2.length();
                }
                final String[] array2 = new String[n];
                int n3 = 0;
                for (int k = 0; k < n; ++k) {
                    try {
                        array2[k] = parameter2.substring(n3, array[k]);
                    }
                    catch (StringIndexOutOfBoundsException ex5) {}
                    n3 = array[k] + 1;
                }
                for (int l = 0; l < n; ++l) {
                    final int n4 = array2[l].length() - 8;
                    final byte[] array3 = new byte[n4];
                    final byte[] array4 = new byte[8];
                    array2[l].getBytes(0, n4, array3, 0);
                    array2[l].getBytes(n4, n4 + 8, array4, 0);
                    int n5 = n4 % 7;
                    final int n6 = n4 % 3;
                    for (int n7 = 0; n7 < n4; ++n7) {
                        final byte b = array3[n7];
                        if (b >= 48 && b <= 57) {
                            array3[n7] = this.a(b, n5, 48, 57);
                        }
                        else if (b >= 65 && b <= 90) {
                            array3[n7] = this.a(b, n5, 65, 90);
                        }
                        else if (b >= 97 && b <= 122) {
                            array3[n7] = this.a(b, n5, 97, 122);
                        }
                        else if (b == 45) {
                            array3[n7] = 46;
                        }
                        else if (b == 46) {
                            array3[n7] = 45;
                        }
                        if ((n5 += n6) > 7) {
                            n5 = 1;
                        }
                    }
                    byte b2 = 0;
                    byte b3 = 0;
                    for (int n8 = 0; n8 < 4; ++n8) {
                        final byte[] array5 = array4;
                        final int n9 = n8;
                        array5[n9] -= 52;
                    }
                    for (int n10 = 4; n10 < 8; ++n10) {
                        final byte[] array6 = array4;
                        final int n11 = n10;
                        array6[n11] -= 55;
                    }
                    for (int n12 = 0; n12 < n4; n12 += 2) {
                        b2 += array3[n12];
                    }
                    for (int n13 = 1; n13 < n4; n13 += 2) {
                        b3 += array3[n13];
                    }
                    String s3 = String.valueOf(b2);
                    String s4 = String.valueOf(b3);
                    while (s3.length() < 4) {
                        s3 = "0" + s3;
                    }
                    while (s4.length() < 4) {
                        s4 = "0" + s4;
                    }
                    final byte[] array7 = new byte[8];
                    s3.getBytes(0, 4, array7, 0);
                    s4.getBytes(0, 4, array7, 4);
                    if (new String(array7, 0).equals(new String(array4, 0))) {
                        final String s5 = new String(array3, 0);
                        String substring;
                        if (s5.startsWith(b("\u0019;7="))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.L = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(b("\u001c)'\u007fP\u0000'"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.u = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.u = null;
            }
        }
        if (this.getParameter(b("\u001c)'}\\\u0019*2rT\u000b")).equalsIgnoreCase(b("7\t\u0013"))) {
            this.w = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.a = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(b("\u0001:%aP\u0003+"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.C = this.a(parameter4);
            if (this.C != null) {
                String parameter5 = this.getParameter(b("\u0001:%aP\u0003+\u0018"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.D = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(b("\u0001:%aP\u0003+\u0019"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.E = Integer.valueOf(parameter6);
            }
        }
        final Dimension size = this.size();
        this.f = size.width;
        this.e = size.height;
        final String parameter7 = this.getParameter(b("\u0007!'`"));
        this.y = ((parameter7 != null) ? parameter7 : b("\u0007!!t"));
        final String parameter8 = this.getParameter(b("\u0000%-tJ"));
        this.x = ((parameter8 != null) ? Integer.valueOf(parameter8) : 3);
        final String parameter9 = this.getParameter(b("\b#2~X\u001a"));
        this.q = ((parameter9 != null) ? parameter9 : b("@\u000b\tU"));
        final String parameter10 = this.getParameter(b("\u001d<%v]"));
        this.t = ((parameter10 != null) ? Integer.valueOf(parameter10) : 0);
        final String parameter11 = this.getParameter(b("\u001e#.tI\u000f93v"));
        this.H = ((parameter11 != null) ? Integer.valueOf(parameter11) : 0);
        final String parameter12 = this.getParameter(b("\u001e%.tI\u000f93v"));
        this.F = ((parameter12 != null) ? Integer.valueOf(parameter12) : 0);
        final String parameter13 = this.getParameter(b("\u001e%.tI\u0001\"'"));
        if (parameter13 == null) {
            this.G = false;
        }
        else if (parameter13.equalsIgnoreCase(b("7\t\u0013"))) {
            this.G = true;
        }
        this.c = true;
        this.h = this.getParameter(b("\u000f9$zV\u001e%.t"));
        if (this.h == null) {
            this.c = false;
        }
        else if (this.h.equalsIgnoreCase("NO")) {
            this.c = false;
        }
        this.d = true;
        this.i = this.getParameter(b("\u000f9$zV\u001e#.t"));
        if (this.h == null) {
            this.d = false;
        }
        else if (this.i.equalsIgnoreCase("NO")) {
            this.d = false;
        }
        this.v = new MediaTracker(this);
        final MediaTracker mediaTracker = new MediaTracker(this);
        final Image image = this.getImage(this.getDocumentBase(), String.valueOf(this.y) + 1 + this.q);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex7) {}
        this.W = image.getWidth(this);
        this.V = image.getHeight(this);
        image.flush();
        if (this.W == this.f && this.V == this.e) {
            this.o = false;
        }
        else {
            this.o = true;
        }
        this.I = Integer.valueOf(this.getParameter(b("\u001e>)|K\u000789")));
        if (this.I > 10) {
            this.I = 10;
        }
        else if (this.I < 1) {
            this.I = 1;
        }
        this.A = this.createImage(this.f, this.e);
        this.z = this.A.getGraphics();
        this.g = new Image[this.x];
        this.l = new Image[this.x];
        this.m = new Image[this.x];
        this.n = new int[this.W * this.V];
        this.M = new int[this.W * this.V];
        this.N = new int[this.W * this.V];
        this.O = new int[this.W * this.V];
        if (!this.L) {
            (this.X = new Lware(this.getAppletContext(), new Label(b("/\"&j\u0019, 5a\u0019\u000f<0\u007f\\\u001al\"j\u0019(-\"zVN\u000f)fZ\r%`\"\u0000Wzo*\u0001@")))).setTitle(b(", 5a\u0019/<0\u007f\\\u001al\"j\u0019(-\"zVN\u000f)fZ\r%"));
            this.X.hide();
        }
    }
    
    private boolean e() {
        this.showStatus(b("9-)g\u0019\u0002#!wP\u0000+n=\u0017N"));
        final URL documentBase = this.getDocumentBase();
        for (int i = 0; i < this.x; ++i) {
            this.K = i + 1;
            this.showStatus(b("\"#!wP\u0000+`uK\u000f!%3") + (i + 1));
            if (i == 0) {
                this.g[i] = this.getImage(documentBase, String.valueOf(this.y) + (i + 1) + this.q);
                this.v.addImage(this.g[i], 0);
            }
            try {
                this.v.waitForID(0);
            }
            catch (InterruptedException ex) {
                return false;
            }
            if (i < this.x - 1) {
                this.g[i + 1] = this.getImage(documentBase, String.valueOf(this.y) + (i + 2) + this.q);
                this.v.addImage(this.g[i + 1], 0);
            }
            try {
                if (this.J) {
                    this.a(i, this.N);
                }
                else {
                    this.a(i, this.O);
                }
            }
            catch (Exception ex2) {}
            this.s = i;
            if (this.o) {
                this.z.drawImage(this.g[i], 0, 0, this.f, this.e, this);
                this.e(i);
            }
            else {
                this.z.drawImage(this.g[i], 0, 0, this);
            }
            this.p = true;
            this.repaint();
        }
        if (this.c) {
            this.Q = this.getAudioClip(documentBase, this.h);
        }
        if (this.d) {
            this.R = this.getAudioClip(documentBase, this.i);
        }
        this.v = null;
        this.s = 0;
        anblur.k = 3;
        this.r = true;
        this.repaint();
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.L) {
            this.X.show();
            this.X.toFront();
            this.X.requestFocus();
        }
        else if (this.u != null) {
            if (this.w) {
                this.getAppletContext().showDocument(this.u, this.getParameter(b("\u001c)'uK\u000f!%}X\u0003)")));
            }
            else {
                this.getAppletContext().showDocument(this.u);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.P);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (this.p && this.r) {
            if (this.o) {
                if (anblur.k == 3) {
                    this.z.drawImage(this.g[this.s], 0, 0, this.f, this.e, this);
                    this.e(this.s);
                }
                else if (anblur.k == 2) {
                    this.z.drawImage(this.l[this.s], 0, 0, this.f, this.e, this);
                    this.f(this.s);
                }
                else if (anblur.k == 1) {
                    this.z.drawImage(this.m[this.s], 0, 0, this.f, this.e, this);
                    this.g(this.s);
                }
            }
            else if (anblur.k == 3) {
                this.z.drawImage(this.g[this.s], 0, 0, this);
            }
            else if (anblur.k == 2) {
                this.z.drawImage(this.l[this.s], 0, 0, this);
            }
            else if (anblur.k == 1) {
                this.z.drawImage(this.m[this.s], 0, 0, this);
            }
        }
        if (this.C != null) {
            this.f();
        }
        graphics.drawImage(this.A, 0, 0, this);
    }
    
    public synchronized void f() {
        if (this.b) {
            this.notifyAll();
            while (!this.B) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.B = false;
        }
        this.z.drawImage(this.C, this.D, this.E, this);
    }
    
    public synchronized void b(final int n) {
        int checkImage = 0;
        this.prepareImage(this.l[n], this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.l[n], this);
        }
    }
    
    public synchronized void c(final int n) {
        int checkImage = 0;
        this.prepareImage(this.m[n], this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.m[n], this);
        }
    }
    
    public synchronized void d(final int n) {
        int checkImage = 0;
        this.prepareImage(this.g[n], this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.g[n], this);
        }
    }
    
    public synchronized void e(final int n) {
        int checkImage = 0;
        this.prepareImage(this.g[n], this.f, this.e, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.g[n], this.f, this.e, this);
        }
    }
    
    public synchronized void f(final int n) {
        int checkImage = 0;
        this.prepareImage(this.l[n], this.f, this.e, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.l[n], this.f, this.e, this);
        }
    }
    
    public synchronized void g(final int n) {
        int checkImage = 0;
        this.prepareImage(this.m[n], this.f, this.e, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.m[n], this.f, this.e, this);
        }
    }
    
    public void run() {
        this.T.setPriority(this.I);
        this.showStatus("");
        this.s = 1;
        if (!this.r && !(this.r = this.e())) {
            this.showStatus(b("+>2|KN /r]\u0007\"'3_\u001c--vJOl") + this.K);
            this.stop();
            return;
        }
        this.showStatus("");
        anblur.k = 2;
        System.gc();
        if (this.C != null && !this.b) {
            this.b = this.d();
        }
        if (this.u != null) {
            this.a.setCursor(12);
        }
        else {
            this.a.setCursor(0);
        }
        while (this.T != null) {
            if (this.G) {
                this.b();
            }
            else {
                this.a();
            }
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public synchronized void start() {
        if (this.T == null) {
            (this.T = new Thread(this)).start();
        }
    }
    
    public synchronized void stop() {
        if (this.T != null && this.T.isAlive()) {
            this.T.stop();
            this.T = null;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void a(final int[] array, final int[] array2, final int n) {
        final int n2 = this.W * this.V;
        final int n3 = 255 - n;
        for (int i = 0; i < n2; ++i) {
            if (array[i] == array2[i]) {
                this.n[i] = array2[i];
            }
            else {
                this.n[i] = (((array[i] >> 16 & 0xFF) * n >> 8) + ((array2[i] >> 16 & 0xFF) * n3 >> 8) << 16 | ((array[i] >> 8 & 0xFF) * n >> 8) + ((array2[i] >> 8 & 0xFF) * n3 >> 8) << 8 | ((array[i] & 0xFF) * n >> 8) + ((array2[i] & 0xFF) * n3 >> 8));
            }
        }
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = 'n';
                char[] array2;
                char[] array = array2 = charArray;
                int n4;
                int n3 = n4 = n;
                while (true) {
                    array[n3] = (char)(c ^ array2[n4]);
                    Label_0047: {
                        Label_0039: {
                            Label_0029: {
                            Label_0021:
                                while (true) {
                                    ++n;
                                    ++n2;
                                    if (length == n) {
                                        break Label_0010;
                                    }
                                    switch (n2) {
                                        case 5: {
                                            continue Label_0010;
                                        }
                                        case 1: {
                                            break Label_0021;
                                        }
                                        case 2: {
                                            break Label_0029;
                                        }
                                        case 3: {
                                            break Label_0039;
                                        }
                                        case 4: {
                                            break Label_0047;
                                        }
                                        default: {
                                            continue;
                                        }
                                    }
                                }
                                c = 'L';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '@';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '\u0013';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '9';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
