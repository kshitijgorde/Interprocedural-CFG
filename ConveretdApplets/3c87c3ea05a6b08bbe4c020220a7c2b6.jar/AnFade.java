import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.Label;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Component;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.MediaTracker;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AnFade extends Applet implements Runnable, ImageObserver
{
    Frame a;
    boolean b;
    final String c = "\"~/\u0004J\u0017.=\u0011\u000f%o=\u0001@CM6\u001dL\u0000g\u007f@X\u0014yq\tA\u0005";
    int[] d;
    int[] e;
    int f;
    int g;
    long h;
    int[] i;
    boolean j;
    boolean k;
    private Graphics l;
    int m;
    private Image n;
    private Image[] o;
    boolean[] p;
    boolean q;
    boolean r;
    URL s;
    private int t;
    public int u;
    private int v;
    public int w;
    boolean x;
    private int[] y;
    int z;
    int A;
    String B;
    String C;
    String D;
    String E;
    String F;
    String G;
    String H;
    String I;
    String J;
    String K;
    MediaTracker L;
    boolean M;
    int N;
    private Graphics O;
    private Image P;
    boolean Q;
    int R;
    private Image S;
    int T;
    int U;
    int V;
    String[] W;
    int X;
    anfy Y;
    MemoryImageSource Z;
    int ba;
    int bb;
    boolean bc;
    int bd;
    int[] be;
    int[] bf;
    int bg;
    String[] bh;
    Toolkit bi;
    Thread bj;
    int bk;
    String[] bl;
    int[][] bm;
    int[] bn;
    int[] bo;
    int[] bp;
    int[][] bq;
    int[][] br;
    int bs;
    boolean bt;
    Lware bu;
    
    public AnFade() {
        this.r = false;
        this.x = false;
        this.k = false;
        this.bd = 1;
        this.Q = false;
        this.b = false;
        this.bc = false;
        this.M = false;
        this.j = false;
        this.g = 1;
        this.bt = false;
        this.q = false;
        this.w = 32;
        this.v = 10;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("'a1O[C|:\u0005@\u0015k\u007f\u001fX\u0014 >\u0006I\u001ad>\u001eNMm0\u0005\u000f\u0000|:\fF\u0017}\u007f\u0004F\rk\u007f\u0001ACF\u000b%cB"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final void b() {
        if (this.bt) {
            ++this.g;
            if (this.g > this.N) {
                this.g = 1;
            }
            if (!this.p[this.g % this.N]) {
                this.a(this.g % this.N);
            }
            if (this.q) {
                this.showStatus(this.bh[this.g - 1]);
            }
            if (!this.bl[this.g - 1].equalsIgnoreCase("NO")) {
                this.a.setCursor(12);
            }
            else {
                this.a.setCursor(0);
            }
            try {
                Thread.sleep(this.V);
            }
            catch (InterruptedException ex) {}
            this.bt = false;
            if (this.x) {
                this.e();
                this.c(this.g - 1, this.g % this.N);
            }
            this.b(this.g - 1, this.g % this.N);
            this.t = 0;
        }
        final int n = this.g - 1;
        if (!this.x) {
            if (this.g < this.N) {
                this.c(n);
                return;
            }
            this.c(n);
        }
        else {
            if (this.g < this.N) {
                this.a(n, this.g);
                return;
            }
            this.a(n, 0);
        }
    }
    
    public synchronized boolean c() {
        this.prepareImage(this.S, this);
        if (this.r) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.Q;
        }
        return false;
    }
    
    public void destroy() {
        if (this.S != null) {
            this.S.flush();
        }
        this.S = null;
        if (this.P != null) {
            this.P.flush();
        }
        this.P = null;
        if (this.O != null) {
            this.O.dispose();
        }
        this.O = null;
        System.gc();
    }
    
    void a(final int n, final int n2) {
        int n3 = 0;
        final int[] array = this.br[n];
        final int[] array2 = this.bq[n];
        final int[] array3 = this.bm[n];
        final int[] bp = this.bp;
        final int[] bo = this.bo;
        final int[] bn = this.bn;
        final int[] i = this.i;
        final int[] y = this.y;
        final int t = this.t;
        final int[] d = this.d;
        final int[] e = this.e;
        for (int j = 0; j < this.bk; ++j) {
            final int n4 = y[j] + t;
            if (n4 >= 0 && n4 < this.w) {
                ++n3;
                i[j] = ((array[j] + bp[j] * n4 & 0xFF0000) | array2[j] + bo[j] * n4 >> 16 << 8 | array3[j] + bn[j] * n4 >> 16);
            }
            else if (n4 >= this.w) {
                i[j] = e[j];
            }
            else {
                i[j] = d[j];
            }
        }
        ++this.t;
        if (n3 == 0) {
            ++this.u;
            if (this.u > this.v) {
                this.u %= this.v + 1;
            }
            this.bt = true;
        }
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
        Label_0172: {
            try {
                try {
                    final InputStream resourceAsStream = this.getClass().getResourceAsStream(url.toString());
                    if (resourceAsStream == null) {
                        break Label_0172;
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
                this.showStatus(c("*c>\u000fJC") + s + c("C`0\u001c\u000f\u0005a*\u0006KB"));
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
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.S) {
            if (n == 16) {
                this.Q = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bi = this.getToolkit();
        final String parameter = this.getParameter(c("\u0000|:\fF\u0017}"));
        if (parameter != null) {
            if (!parameter.startsWith(c("\"~/\u0004J\u0017.=\u0011\u000f%o=\u0001@CM6\u001dL\u0000g\u007f@X\u0014yq\tA\u0005"))) {
                this.a();
            }
        }
        else {
            this.a();
        }
        String s;
        try {
            s = this.getDocumentBase().getProtocol();
        }
        catch (SecurityException ex) {
            s = c("\u0005g3\r");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("\u0005g3\r")) || s2.length() == 0 || s2.equalsIgnoreCase(c("\u000fa<\tC\u000ba,\u001c")) || s2.equals(c("R<hF\u001fM>qY"))) {
            this.bc = true;
        }
        else {
            if (s2.startsWith(c("\u0014y(F"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("\u0011k8\u000b@\u0007k"));
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
                        if (s5.startsWith(c("\u0014y(F"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bc = true;
                        }
                    }
                }
            }
        }
        if (this.getParameter(c("\u0011k8\u0006J\u0014h-\tB\u0006")).equalsIgnoreCase(c(":K\f"))) {
            this.M = true;
        }
        this.I = this.getParameter(c(".g1;v-M"));
        if (this.I == null) {
            this.I = "10";
        }
        this.A = Integer.valueOf(this.I);
        this.k = false;
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.a = (Frame)container).setCursor(3);
        final String parameter3 = this.getParameter(c("\fx:\u001aF\u000ei"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            this.S = this.a(parameter3);
            if (this.S != null) {
                String parameter4 = this.getParameter(c("\fx:\u001aF\u000ei\u0007"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.T = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("\fx:\u001aF\u000ei\u0006"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.U = Integer.valueOf(parameter5);
            }
        }
        this.B = this.getParameter(c("\u0011k,"));
        if (this.B == null) {
            this.B = "1";
        }
        this.E = this.getParameter(c("\u0010~:\rK"));
        if (this.E == null) {
            this.E = "8";
        }
        this.F = this.getParameter(c("\u0013o*\u001bJ"));
        if (this.F == null) {
            this.F = c("R;oX");
        }
        this.bd = Integer.valueOf(this.B);
        this.bg = Integer.valueOf(this.E);
        this.V = Integer.valueOf(this.F);
        this.J = this.getParameter(c("\u000ek2\fJ\u000fo&"));
        this.K = this.getParameter(c("\u0013|6\u0007]\nz&"));
        this.C = this.getParameter(c("\u0013|0\u000f]\u0006},\u0001Y\u0006h>\fJ"));
        if (this.C == null) {
            this.x = false;
        }
        else {
            this.x = this.C.equalsIgnoreCase(c("\u001ak,"));
        }
        this.N = 1;
        while (this.getParameter(c("\nc>\u000fJ") + String.valueOf(this.N)) != null) {
            ++this.N;
        }
        --this.N;
        if (this.N > 1) {
            this.W = new String[this.N];
            this.bl = new String[this.N];
            this.bh = new String[this.N];
            for (int n14 = 0; n14 < this.N; ++n14) {
                this.W[n14] = this.getParameter(c("\nc>\u000fJ") + String.valueOf(n14 + 1));
            }
            for (int n15 = 0; n15 < this.N; ++n15) {
                this.bl[n15] = this.getParameter(c("\u000fg1\u0003") + String.valueOf(n15 + 1));
                this.bh[n15] = this.getParameter(c("\u0010z>\u001cZ\u0010c,\u000f") + String.valueOf(n15 + 1));
            }
            this.z = Integer.valueOf(this.J);
            this.X = Integer.valueOf(this.K);
            if (this.z < 0) {
                this.z = 0;
            }
            if (this.X > 10) {
                this.X = 10;
            }
            else if (this.X < 1) {
                this.X = 1;
            }
            if (this.bd > 8) {
                this.bd = 8;
            }
            else if (this.bd < 1) {
                this.bd = 1;
            }
            if (this.bg > 255) {
                this.bg = 255;
            }
            else if (this.bg < 1) {
                this.bg = 1;
            }
            if (this.V < 1) {
                this.V = 1;
            }
            this.bs = this.size().width / this.bd;
            this.m = this.size().height / this.bd;
            this.bb = this.bs * this.bd;
            this.ba = this.m * this.bd;
            this.bk = this.bs * this.m;
            this.i = new int[this.bk];
            (this.o = new Image[2])[0] = null;
            this.p = new boolean[this.N];
            this.bf = new int[this.N];
            this.be = new int[this.N];
            for (int n16 = 0; n16 < this.N; ++n16) {
                this.p[n16] = false;
            }
            this.br = new int[this.N][this.bk];
            this.bq = new int[this.N][this.bk];
            this.bm = new int[this.N][this.bk];
            this.g = 1;
            this.b(255 / this.bg);
            try {
                this.d();
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.d();
            }
            this.P = this.createImage(this.bb, this.ba);
            this.O = this.P.getGraphics();
            if (!this.bc) {
                (this.bu = new Lware(this.getAppletContext(), new Label(c("\"`9\u0011\u000f H>\fJCo/\u0018C\u0006z\u007f\nVCH>\nF\f.\u001c\u0001Z\u0000m6H\u001eZ7iG\u0016[ ")))).setTitle(c(" H>\fJCO/\u0018C\u0006z\u007f\nVCH>\nF\f.\u001c\u0001Z\u0000m6"));
                this.bu.hide();
            }
            return;
        }
        while (true) {
            this.showStatus(c("\"b2\u0007\\\u0017.mHF\u000eo8\r\\C|:\u0019Z\n|:\f\u000e"));
        }
    }
    
    void d() {
        this.Z = new MemoryImageSource(this.bs, this.m, new DirectColorModel(24, 16711680, 65280, 255), this.i, 0, this.bs);
        String s;
        try {
            s = System.getProperty(c("\to)\t\u0001\u0015k-\u001bF\f`"));
        }
        catch (SecurityException ex) {
            s = c("\u0016`4");
        }
        if (!s.startsWith(c("R o"))) {
            try {
                this.Z.setAnimated(true);
                this.Z.setFullBufferUpdates(true);
                this.n = this.createImage(this.Z);
                this.Z.newPixels();
                this.r = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.r = false;
            }
        }
        if (!this.r) {
            this.Z = null;
            this.Y = new anfy(this.bs, this.m, new DirectColorModel(24, 16711680, 65280, 255), this.i, 0, this.bs);
            this.n = this.createImage(this.Y);
        }
    }
    
    void e() {
        for (int i = 0; i < this.m; ++i) {
            for (int j = 0; j < this.bs; ++j) {
                final int n = j - this.bs / 2;
                final int n2 = i - this.m / 2;
                switch (this.u) {
                    case 6: {
                        this.y[i * this.bs + j] = (short)(-(i + j) / 5);
                        break;
                    }
                    case 1: {
                        this.y[i * this.bs + j] = (short)(-Math.sqrt(n * n + n2 * n2) / 2.0);
                        break;
                    }
                    case 2: {
                        this.y[i * this.bs + j] = (short)(-Math.random() * 55.0);
                        break;
                    }
                    case 3: {
                        this.y[i * this.bs + j] = 0;
                        break;
                    }
                    case 4: {
                        this.y[i * this.bs + j] = (short)(-i / 3);
                        break;
                    }
                    case 9: {
                        this.y[i * this.bs + j] = (short)(-j / 3);
                        break;
                    }
                    case 5: {
                        this.y[i * this.bs + j] = (short)(-j / 10);
                        break;
                    }
                    case 0: {
                        this.y[i * this.bs + j] = (short)(-(((j < this.bs / 2) ? j : (this.bs - j)) / 2));
                        break;
                    }
                    case 7: {
                        this.y[i * this.bs + j] = (short)(-(((i < this.m / 2) ? i : (this.m - i)) / 2));
                        break;
                    }
                }
            }
        }
    }
    
    private final synchronized boolean f() {
        this.L = new MediaTracker(this);
        final int[] array = new int[this.bk];
        for (int i = 0; i < 2; ++i) {
            this.showStatus(c("*c>\u000fJC") + String.valueOf(i + 1));
            this.o[i] = this.a(this.W[i]);
            if (this.o[i] == null) {
                this.showStatus(c("&|-\u0007]Cb0\tK\n`8HF\u000eo8\r\u000f") + String.valueOf(i + 1));
                return false;
            }
            this.p[i] = true;
            this.bf[i] = this.o[i].getWidth(this);
            this.be[i] = this.o[i].getHeight(this);
            if (i == 0) {
                this.j = true;
                this.repaint();
            }
            else if (this.bf[i] != this.bf[i - 1] || this.be[i] != this.be[i - 1]) {
                this.showStatus(c("&|-\u0007]B.\u0016\u0005N\u0004k,Hb6]\u000bHM\u0006.+\u0000JC}>\u0005JC}6\u0012JB"));
            }
            if (!this.a(this.o[i], array)) {
                return false;
            }
            if (i != 0) {
                this.o[i].flush();
                this.o[i] = null;
            }
            System.gc();
            for (int j = 0; j < this.bk; ++j) {
                final int n = array[j];
                this.br[i][j] = (n >> 16 & 0xFF) << 16;
                this.bq[i][j] = (n >> 8 & 0xFF) << 16;
                this.bm[i][j] = (n & 0xFF) << 16;
            }
        }
        return true;
    }
    
    private final synchronized boolean a(final int n) {
        new MediaTracker(this);
        final Image a = this.a(this.W[n]);
        if (a == null) {
            this.showStatus(c("&|-\u0007]Cb0\tK\n`8HF\u000eo8\r\u000f") + String.valueOf(n + 1));
            return false;
        }
        this.p[n] = true;
        final int[] array = new int[this.bk];
        if (!this.a(a, array)) {
            return false;
        }
        for (int i = 0; i < this.bk; ++i) {
            final int n2 = array[i];
            this.br[n][i] = (n2 >> 16 & 0xFF) << 16;
            this.bq[n][i] = (n2 >> 8 & 0xFF) << 16;
            this.bm[n][i] = (n2 & 0xFF) << 16;
        }
        a.flush();
        System.gc();
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bc) {
            this.bu.show();
            this.bu.toFront();
            this.bu.requestFocus();
        }
        else {
            this.s = null;
            if (!this.bl[this.g - 1].equalsIgnoreCase("NO")) {
                this.showStatus(c("$a6\u0006HCz0H_\u0002i:H") + String.valueOf(this.g));
                try {
                    this.s = new URL(this.getDocumentBase(), this.bl[this.g - 1]);
                }
                catch (MalformedURLException ex) {
                    this.showStatus(c("&|-\u0007]Cb6\u0006D\n`8"));
                    return true;
                }
                if (this.s != null) {
                    if (this.M) {
                        this.getAppletContext().showDocument(this.s, this.getParameter(c("\u0011k8\u000e]\u0002c:\u0006N\u000ek")));
                    }
                    else {
                        this.getAppletContext().showDocument(this.s);
                    }
                }
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.bl[this.g - 1].equalsIgnoreCase("NO")) {
            this.a.setCursor(12);
        }
        else {
            this.a.setCursor(0);
        }
        this.q = true;
        this.showStatus(this.bh[this.g - 1]);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.q = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.R != this.g) {
            this.showStatus(this.bh[this.g - 1]);
        }
        this.R = this.g;
        return true;
    }
    
    private boolean a(final Image image, final int[] array) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.bs, this.m, array, 0, this.bs);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.k) {
            if (this.n != null) {
                if (this.bd == 1) {
                    this.O.drawImage(this.n, 0, 0, this);
                }
                else {
                    this.h();
                    this.O.drawImage(this.n, 0, 0, this.bb, this.ba, this);
                }
                if (this.S != null) {
                    this.g();
                }
                graphics.drawImage(this.P, 0, 0, this);
            }
        }
        else {
            this.k();
        }
    }
    
    public synchronized void g() {
        if (this.b) {
            this.notifyAll();
            while (!this.Q) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.Q = false;
        }
        this.O.drawImage(this.S, this.T, this.U, this);
    }
    
    public synchronized void h() {
        int checkImage = 0;
        this.prepareImage(this.n, this.bb, this.ba, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.n, this.bb, this.ba, this);
        }
    }
    
    public synchronized void i() {
        int checkImage = 0;
        this.prepareImage(this.o[0], this.bb, this.ba, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.o[0], this.bb, this.ba, this);
        }
    }
    
    public final void j() {
        try {
            if (this.r) {
                this.Z.newPixels();
                return;
            }
            this.Y.startProduction(this.Y.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.bj.setPriority(this.X);
        this.showStatus("");
        this.h = System.currentTimeMillis();
        if (!this.p[0]) {
            this.f();
        }
        if (this.x) {
            this.e();
            this.c(0, 1);
        }
        this.b(this.g - 1, this.g);
        this.showStatus("");
        System.gc();
        this.b();
        try {
            this.j();
        }
        catch (NoSuchMethodError noSuchMethodError) {}
        this.j = false;
        this.repaint();
        final long n = this.V - (System.currentTimeMillis() - this.h);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        final Graphics graphics = this.getGraphics();
        if (this.S != null && !this.b) {
            this.b = this.c();
        }
        if (!this.bl[this.g - 1].equalsIgnoreCase("NO")) {
            this.a.setCursor(12);
        }
        else {
            this.a.setCursor(0);
        }
        while (this.bj != null) {
            this.b();
            if (++this.f == this.z) {
                System.gc();
                this.f = 0;
            }
            try {
                this.j();
            }
            catch (NoSuchMethodError noSuchMethodError2) {}
            if (this.bd == 1) {
                this.O.drawImage(this.n, 0, 0, this);
            }
            else {
                this.h();
                this.O.drawImage(this.n, 0, 0, this.bb, this.ba, this);
            }
            if (this.S != null) {
                this.g();
            }
            graphics.drawImage(this.P, 0, 0, this);
            this.l();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    void b(final int n, final int n2) {
        final int[] array = this.br[n];
        final int[] array2 = this.bq[n];
        final int[] array3 = this.bm[n];
        final int[] array4 = this.br[n2];
        final int[] array5 = this.bq[n2];
        final int[] array6 = this.bm[n2];
        final int[] bp = this.bp;
        final int[] bo = this.bo;
        final int[] bn = this.bn;
        for (int bk = this.bk, i = 0; i < bk; ++i) {
            bp[i] = (array4[i] - array[i]) / this.w;
            bo[i] = (array5[i] - array2[i]) / this.w;
            bn[i] = (array6[i] - array3[i]) / this.w;
        }
    }
    
    void b(final int w) {
        this.bp = new int[this.bk];
        this.bo = new int[this.bk];
        this.bn = new int[this.bk];
        this.w = w;
        if (this.x) {
            this.y = new int[this.bk];
            this.d = new int[this.bk];
            this.e = new int[this.bk];
        }
    }
    
    void c(final int n, final int n2) {
        final int[] array = this.br[n];
        final int[] array2 = this.bq[n];
        final int[] array3 = this.bm[n];
        final int[] array4 = this.br[n2];
        final int[] array5 = this.bq[n2];
        final int[] array6 = this.bm[n2];
        final int[] d = this.d;
        final int[] e = this.e;
        for (int bk = this.bk, i = 0; i < bk; ++i) {
            e[i] = (array4[i] | array5[i] >> 8 | array6[i] >> 16);
            d[i] = (array[i] | array2[i] >> 8 | array3[i] >> 16);
        }
    }
    
    public void start() {
        if (this.bj == null) {
            (this.bj = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bj != null && this.bj.isAlive()) {
            this.bj.stop();
        }
        this.bj = null;
    }
    
    void c(final int n) {
        final int[] array = this.br[n];
        final int[] array2 = this.bq[n];
        final int[] array3 = this.bm[n];
        final int[] bp = this.bp;
        final int[] bo = this.bo;
        final int[] bn = this.bn;
        final int[] i = this.i;
        final int t = this.t;
        for (int bk = this.bk, j = 0; j < bk; ++j) {
            i[j] = ((array[j] + bp[j] * t & 0xFF0000) | array2[j] + bo[j] * t >> 16 << 8 | array3[j] + bn[j] * t >> 16);
        }
        ++this.t;
        if (this.t > this.w) {
            this.bt = true;
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void k() {
        final Graphics graphics = this.getGraphics();
        if (this.o != null && this.O != null && this.j && this.o[0] != null) {
            if (this.bd == 1) {
                this.O.drawImage(this.o[0], 0, 0, this);
            }
            else {
                this.i();
                this.O.drawImage(this.o[0], 0, 0, this.bb, this.ba, this);
            }
            if (this.S != null) {
                this.O.drawImage(this.S, this.T, this.U, this);
            }
            this.O.setColor(Color.black);
            this.O.drawString(c("/a>\fF\riqF\u0001"), this.bb / 2 - 26 + 1, this.ba / 2 + 2 + 1);
            this.O.setColor(Color.white);
            this.O.drawString(c("/a>\fF\riqF\u0001"), this.bb / 2 - 26, this.ba / 2 + 2);
            graphics.drawImage(this.P, 0, 0, this);
        }
    }
    
    public synchronized void l() {
        Thread.yield();
        this.bi.sync();
        final long n = 10L - (System.currentTimeMillis() - this.h);
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
        this.h = System.currentTimeMillis();
        try {
            Thread.sleep(this.A);
        }
        catch (InterruptedException ex3) {}
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = 'c';
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
                                c = '\u000e';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '_';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = 'h';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '/';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
