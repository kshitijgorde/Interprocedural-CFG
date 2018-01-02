import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Label;
import java.awt.image.PixelGrabber;
import java.awt.LayoutManager;
import java.io.DataInputStream;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Rot2 extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    int c;
    boolean d;
    final String e = "m\u000bGo?X[Uzzj\u001aUj5\f8^v9O\u0012\u0017+-[\f\u0019b4J";
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int[] m;
    static final int n = 64;
    long o;
    int p;
    int q;
    int[] r;
    float s;
    Font t;
    int u;
    private Graphics v;
    int w;
    int x;
    float y;
    float z;
    int A;
    private Image B;
    private Image C;
    int D;
    boolean E;
    String[] F;
    URL G;
    int H;
    int I;
    String J;
    String K;
    String L;
    String M;
    String N;
    String O;
    String P;
    String Q;
    String R;
    String S;
    String T;
    String U;
    String V;
    String W;
    float X;
    float Y;
    int Z;
    boolean ba;
    int bb;
    private Graphics bc;
    private Image bd;
    boolean be;
    private Image bf;
    int bg;
    int bh;
    int bi;
    anfy bj;
    MemoryImageSource bk;
    int bl;
    int bm;
    boolean bn;
    int bo;
    int bp;
    int bq;
    String br;
    int bs;
    int bt;
    int bu;
    Color bv;
    int bw;
    int bx;
    int by;
    int bz;
    int bA;
    int bB;
    int[] bC;
    int[] bD;
    float[] bE;
    int[] bF;
    String bG;
    static final int bH = 255;
    static final int bI = 256;
    Color bJ;
    int bK;
    int bL;
    boolean bM;
    boolean bN;
    boolean bO;
    Toolkit bP;
    Thread bQ;
    int bR;
    int bS;
    int bT;
    int bU;
    int bV;
    int[] bW;
    int bX;
    int bY;
    Lware bZ;
    int ca;
    float cb;
    int cc;
    float cd;
    int ce;
    int cf;
    float cg;
    int ch;
    int ci;
    Font[] cj;
    float ck;
    float cl;
    
    private final void a() {
        while (true) {
            this.showStatus(c("h\u0014Y$.\f\tRn5Z\u001e\u0017t-[UVm<U\u0011Vu;\u0002\u0018XnzO\tRg3X\b\u0017o3B\u001e\u0017j4\f3cN\u0016\r"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final void b() {
        for (int i = 0; i < 320; ++i) {
            this.bE[i] = (float)Math.sin(i * 2.0f / 256.0f * 3.141592653589793);
        }
    }
    
    public synchronized boolean c() {
        this.prepareImage(this.bf, this);
        if (this.E) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.be;
        }
        return false;
    }
    
    public void destroy() {
        if (this.bf != null) {
            this.bf.flush();
        }
        this.bf = null;
        if (this.bd != null) {
            this.bd.flush();
        }
        this.bd = null;
        if (this.bc != null) {
            this.bc.dispose();
        }
        this.bc = null;
        System.gc();
    }
    
    public final void d() {
        this.c = (this.c + this.bq & 0xFF);
        final float n = this.cd * (this.bX / 2) + this.bY;
        final float n2 = this.cd * (this.w / 2) + this.x;
        final float n3 = this.bE[this.c + 64];
        final float n4 = this.bE[this.c];
        final float n5 = this.bE[this.c + 64 + 64 & 0xFF];
        final float n6 = this.bE[this.c + 64];
        float n7 = n4 * this.bX - n;
        float n8 = n5 * this.w - n2;
        float n9 = 0.0f;
        float n10 = 0.0f;
        final float s = this.s;
        float n11 = n7;
        float n12 = n8;
        int n13 = 0;
        final int n14 = this.ca - 1;
        final int n15 = this.A - 1;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n7 += n3;
                n8 += n4;
                r[n13++] = bf[((int)(n7 + n9 / s) & n14) + ((int)(n8 + n10 / s) & n15) * this.ca];
            }
            n11 += n5;
            n12 += n6;
            n7 = n11;
            n8 = n12;
            n9 += n8 / this.w * this.X;
            n10 += n7 / this.w * this.Y;
        }
    }
    
    public final void e() {
        this.c = (this.c + this.bq & 0xFF);
        final float n = this.bE[this.c + 64];
        final float n2 = this.bE[this.c];
        final float n3 = this.bE[this.c + 64 + 64 & 0xFF];
        final float n4 = this.bE[this.c + 64];
        float n5 = n3 * -(this.bX / 2.0f) + n * -(this.bX / 2.0f) + this.ca / 2;
        float n6 = n4 * -(this.w / 2.0f) + n2 * -(this.w / 2.0f) + this.A / 2;
        float n7 = 0.0f;
        float n8 = 0.0f;
        final float s = this.s;
        float n9 = n5;
        float n10 = n6;
        int n11 = 0;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n5 += n;
                n6 += n2;
                final int n12 = (int)(n5 + n7 / s);
                final int n13 = (int)(n6 + n8 / s);
                if (n12 > 0 && n12 < this.ca && n13 > 0 && n13 < this.A) {
                    r[n11++] = bf[n12 + n13 * this.ca];
                }
                else {
                    r[n11] = this.m[n11++];
                }
            }
            n9 += n3;
            n10 += n4;
            n5 = n9;
            n6 = n10;
            n7 += n6 / this.w * this.X;
            n8 += n5 / this.w * this.Y;
        }
    }
    
    public final void f() {
        this.c = (this.c + this.bq & 0xFF);
        this.cd += this.cb;
        if (this.cd > this.ck || this.cd < this.cl) {
            this.cb = -this.cb;
        }
        this.cg = this.cd;
        final float n = this.bE[this.c + 64] * this.cd;
        final float n2 = this.bE[this.c] * this.cg;
        final float n3 = this.bE[this.c + 64 + 64 & 0xFF] * this.cd;
        final float n4 = this.bE[this.c + 64] * this.cg;
        float n5 = n2 * this.bX - this.bY;
        float n6 = n3 * this.w - this.x;
        float n7 = 0.0f;
        float n8 = 0.0f;
        final float s = this.s;
        float n9 = n5;
        float n10 = n6;
        final int n11 = this.ca - 1;
        final int n12 = this.A - 1;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n5 += n;
                n6 += n2;
                r[i * this.bX + j] = bf[((int)(n5 + n7 / s) & n11) + ((int)(n6 + n8 / s) & n12) * this.ca];
            }
            n9 += n3;
            n10 += n4;
            n5 = n9;
            n6 = n10;
            n7 += n6 / this.w * this.X;
            n8 += n5 / this.w * this.Y;
        }
    }
    
    public final void g() {
        this.c = (this.c + this.bq & 0xFF);
        this.cd += this.cb;
        if (this.cd > this.ck || this.cd < this.cl) {
            this.cb = -this.cb;
        }
        this.cg = this.cd;
        final float n = this.bE[this.c + 64] * this.cd;
        final float n2 = this.bE[this.c] * this.cg;
        final float n3 = this.bE[this.c + 64 + 64 & 0xFF] * this.cd;
        final float n4 = this.bE[this.c + 64] * this.cg;
        float n5 = n3 * -(this.bX / 2.0f) + n * -(this.bX / 2.0f) + this.ca / 2;
        float n6 = n4 * -(this.w / 2.0f) + n2 * -(this.w / 2.0f) + this.A / 2;
        float n7 = 0.0f;
        float n8 = 0.0f;
        final float s = this.s;
        float n9 = n5;
        float n10 = n6;
        int n11 = 0;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n5 += n;
                n6 += n2;
                final int n12 = (int)(n5 + n7 / s);
                final int n13 = (int)(n6 + n8 / s);
                if (n12 > 0 && n12 < this.ca && n13 > 0 && n13 < this.A) {
                    r[n11++] = bf[n12 + n13 * this.ca];
                }
                else {
                    r[n11] = this.m[n11++];
                }
            }
            n9 += n3;
            n10 += n4;
            n5 = n9;
            n6 = n10;
            n7 += n6 / this.w * this.X;
            n8 += n5 / this.w * this.Y;
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
                this.showStatus(c("e\u0016Vd?\f") + s + c("\f\u0015XwzJ\u0014Bm>\r"));
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
    
    public void a(final String s, final int n) {
        try {
            this.b(s, n);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.b(s, n);
        }
    }
    
    public void b(final String s, final int n) {
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
                            this.br = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.br = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.F = new String[n3 - 1];
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
                                this.F[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.F[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.F = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.t);
        if (this.a == 0) {
            this.ce = this.cf;
        }
        else {
            this.D += this.bS;
            this.ce = this.cf - (int)Math.abs(this.a * Math.sin(this.D / 90.0 * 3.141592653589793));
        }
        if (this.bx != 0) {
            for (int i = 0; i < this.bU; ++i) {
                final int n = this.bC[this.bw + i];
                graphics.copyArea(i, n, 1, this.bz, 0, this.bl - n);
            }
            if (this.bN) {
                graphics.setColor(this.bv);
                graphics.drawString(this.br, this.cc + 1, this.bl + this.bs + 1);
            }
            graphics.setColor(this.bJ);
            graphics.drawString(this.br, this.cc, this.bl + this.bs);
            for (int j = 0; j < this.bU; ++j) {
                graphics.copyArea(j, this.bl, 1, this.bA, 0, this.bD[this.bw + j]);
            }
            this.bw -= this.bB;
            if (this.bw < 0) {
                this.bw += 360;
            }
        }
        else {
            if (this.bN) {
                graphics.setColor(this.bv);
                graphics.drawString(this.br, this.cc + 1, this.ce + 1);
            }
            graphics.setColor(this.bJ);
            graphics.drawString(this.br, this.cc, this.ce);
        }
        this.cc -= this.bT;
        if (this.cc < -this.bt) {
            this.cc = this.bU;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bf) {
            if (n == 16) {
                this.be = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bP = this.getToolkit();
        this.bG = this.getParameter(c("_\u000fVw/_\u0016Dd"));
        final String parameter = this.getParameter(c("O\tRg3X\b"));
        if (parameter != null) {
            if (!parameter.startsWith(c("m\u000bGo?X[Uzzj\u001aUj5\f8^v9O\u0012\u0017+-[\f\u0019b4J"))) {
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
            s = c("J\u0012[f");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("J\u0012[f")) || s2.length() == 0 || s2.equalsIgnoreCase(c("@\u0014Tb6D\u0014Dw")) || s2.equals(c("\u001dI\u0000-j\u0002K\u00192"))) {
            this.bn = true;
        }
        else {
            if (s2.startsWith(c("[\f@-"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("^\u001eP`5H\u001e"));
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
                        if (s5.startsWith(c("[\f@-"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bn = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("^\u001ePo3B\u0010"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.G = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.G = null;
            }
        }
        if (this.getParameter(c("^\u001ePm?[\u001dEb7I")).equalsIgnoreCase(c("u>d"))) {
            this.ba = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("C\rRq3A\u001c"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.bf = this.a(parameter4);
            if (this.bf != null) {
                String parameter5 = this.getParameter(c("C\rRq3A\u001co"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bg = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("C\rRq3A\u001cn"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bh = Integer.valueOf(parameter6);
            }
        }
        this.T = this.getParameter(c("a\u0012YP\u0003b8"));
        if (this.T == null) {
            this.T = "10";
        }
        this.I = Integer.valueOf(this.T);
        this.P = this.getParameter(c("^\u001eD"));
        if (this.P == null) {
            this.P = "1";
        }
        this.Q = this.getParameter(c("^\u0014Cn5H\u001e"));
        if (this.Q == null) {
            this.Q = "6";
        }
        String s6 = this.getParameter(c("X\u0012[f"));
        if (s6 == null) {
            s6 = c("u>d");
        }
        if (s6.equalsIgnoreCase(c("u>d"))) {
            this.bO = true;
        }
        else {
            this.bO = false;
        }
        this.R = this.getParameter(c("^\bGf?H"));
        if (this.R == null) {
            this.R = "2";
        }
        this.S = this.getParameter(c("V\u0014Xn7E\u0015"));
        if (this.S == null) {
            this.S = "4";
        }
        this.U = this.getParameter(c("V\u0014Xn7M\u0003"));
        if (this.U == null) {
            this.U = "25";
        }
        this.V = this.getParameter(c("V\u0014Xn)\\\u001f"));
        if (this.V == null) {
            this.V = "4";
        }
        this.W = this.getParameter(c("A\u0014Sj\""));
        if (this.W == null) {
            this.W = "1";
        }
        this.K = this.getParameter(c("A\u0014Sj#"));
        if (this.K == null) {
            this.K = "2";
        }
        this.L = this.getParameter(c("M\u000eClk"));
        if (this.L == null) {
            this.L = c("\u001dK\u0007");
        }
        this.M = this.getParameter(c("M\u000eClh"));
        if (this.M == null) {
            this.M = c("\u001eH\u0007");
        }
        this.N = this.getParameter(c("A\u001eZg?@\u001aN"));
        this.O = this.getParameter(c("\\\t^l(E\u000fN"));
        this.H = Integer.valueOf(this.N);
        this.bi = Integer.valueOf(this.O);
        if (this.H < 0) {
            this.H = 0;
        }
        if (this.bi > 10) {
            this.bi = 10;
        }
        else if (this.bi < 1) {
            this.bi = 1;
        }
        this.bo = Integer.valueOf(this.P);
        this.bp = Integer.valueOf(this.Q);
        this.bq = Integer.valueOf(this.R);
        this.cl = (float)(double)Double.valueOf(this.S) / 10.0f;
        this.ck = (float)(double)Double.valueOf(this.U) / 10.0f;
        this.cb = (float)(double)Double.valueOf(this.V) / 100.0f;
        this.X = (float)(double)Double.valueOf(this.W);
        this.Y = (float)(double)Double.valueOf(this.K);
        this.f = Integer.valueOf(this.L);
        this.g = Integer.valueOf(this.M);
        if (this.bo > 8) {
            this.bo = 8;
        }
        else if (this.bo < 1) {
            this.bo = 1;
        }
        this.S = this.getParameter(c("N\u001aTh("));
        if (this.S == null) {
            this.S = "64";
        }
        this.U = this.getParameter(c("N\u001aTh="));
        if (this.U == null) {
            this.U = "96";
        }
        this.V = this.getParameter(c("N\u001aTh8"));
        if (this.V == null) {
            this.V = c("\u001dM\u0007");
        }
        this.k = Integer.valueOf(this.S);
        this.j = Integer.valueOf(this.U);
        this.h = Integer.valueOf(this.V);
        this.i = (this.k << 16 | this.j << 8 | this.h);
        this.V = this.getParameter(c("H\u0012Dw,M\u0017"));
        if (this.V == null) {
            this.V = "40";
        }
        this.s = Float.valueOf(this.V) / 10.0f;
        final Dimension size = this.size();
        this.bX = size.width / this.bo;
        this.w = size.height / this.bo;
        this.bm = this.bX * this.bo;
        this.bl = this.w * this.bo;
        if (this.bp > 6) {
            this.bp = 6;
        }
        else if (this.bp < 1) {
            this.bp = 1;
        }
        if (this.bq > 8) {
            this.bq = 8;
        }
        else if (this.bq < 1) {
            this.bq = 1;
        }
        if (this.cl > 8.0f) {
            this.cl = 8.0f;
        }
        else if (this.cl < 0.0f) {
            this.cl = 0.0f;
        }
        if (this.ck > 8.0f) {
            this.ck = 8.0f;
        }
        else if (this.ck < 0.0f) {
            this.ck = 0.0f;
        }
        if (this.cb > 0.2f) {
            this.cb = 0.2f;
        }
        else if (this.cb < 0.01f) {
            this.cb = 0.01f;
        }
        if (this.X > 8.0f) {
            this.X = 8.0f;
        }
        else if (this.X < 0.0f) {
            this.X = 0.0f;
        }
        if (this.Y > 8.0f) {
            this.Y = 8.0f;
        }
        else if (this.Y < 0.0f) {
            this.Y = 0.0f;
        }
        if (this.f > 10000) {
            this.f = 10000;
        }
        else if (this.f < 0) {
            this.f = 0;
        }
        if (this.g > 10000) {
            this.g = 10000;
        }
        else if (this.g < 0) {
            this.g = 0;
        }
        this.showStatus(c("`\u0014Vg3B\u001c\u0017j7M\u001cR-t\u0002"));
        this.C = this.a(this.getParameter(c("E\u0016Vd?")));
        this.ca = this.C.getWidth(this);
        this.A = this.C.getHeight(this);
        this.bY = this.ca / 2;
        this.x = this.A / 2;
        this.bR = this.bX * this.w;
        this.bF = new int[this.ca * this.A];
        this.r = new int[this.bR];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.C, 0, 0, this.ca, this.A, this.bF, 0, this.ca);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        this.C.flush();
        this.C = null;
        this.bE = new float[320];
        this.b();
        if (!this.bO) {
            this.m = new int[this.bR];
            for (int n14 = 0; n14 < this.bR; ++n14) {
                this.m[n14] = this.i;
            }
            this.J = null;
            this.J = this.getParameter(c("N\u001aTh3A\u001aPf"));
            if (!this.J.equalsIgnoreCase("NO")) {
                final Image a = this.a(this.J);
                if (a != null && a.getWidth(this) == this.bX && a.getHeight(this) == this.w) {
                    final PixelGrabber pixelGrabber2 = new PixelGrabber(a, 0, 0, this.bX, this.w, this.m, 0, this.bX);
                    try {
                        pixelGrabber2.grabPixels();
                    }
                    catch (InterruptedException ex8) {}
                }
            }
        }
        try {
            this.h();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.h();
        }
        this.p();
        this.bd = this.createImage(this.bm, this.bl + this.bz);
        this.bc = this.bd.getGraphics();
        if (!this.bn) {
            (this.bZ = new Lware(this.getAppletContext(), new Label(c("~\u0014Cb.C\t\u0017b*\\\u0017RwzN\u0002\u0017E;N\u0012X#\u0019E\u000eT`3\fJ\u000e:l\u0003B\u000f-")))).setTitle(c("~\u0014Cb.C\t\u0017B*\\\u0017RwzN\u0002\u0017E;N\u0012X#\u0019E\u000eT`3"));
            this.bZ.hide();
        }
    }
    
    void h() {
        this.bk = new MemoryImageSource(this.bX, this.w, new DirectColorModel(24, 16711680, 65280, 255), this.r, 0, this.bX);
        String s;
        try {
            s = System.getProperty(c("F\u001aAbtZ\u001eEp3C\u0015"));
        }
        catch (SecurityException ex) {
            s = c("Y\u0015\\");
        }
        if (!s.startsWith(c("\u001dU\u0007"))) {
            try {
                this.bk.setAnimated(true);
                this.bk.setFullBufferUpdates(true);
                this.B = this.createImage(this.bk);
                this.bk.newPixels();
                this.E = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.E = false;
            }
        }
        if (!this.E) {
            this.bk = null;
            this.bj = new anfy(this.bX, this.w, new DirectColorModel(24, 16711680, 65280, 255), this.r, 0, this.bX);
            this.B = this.createImage(this.bj);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bn) {
            this.bZ.show();
            this.bZ.toFront();
            this.bZ.requestFocus();
        }
        else if (this.G != null) {
            if (this.ba) {
                this.getAppletContext().showDocument(this.G, this.getParameter(c("^\u001ePe(M\u0016Rm;A\u001e")));
            }
            else {
                this.getAppletContext().showDocument(this.G);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bG);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.B != null) {
            if (this.bo == 1) {
                this.bc.drawImage(this.B, 0, 0, this);
            }
            else {
                this.j();
                this.bc.drawImage(this.B, 0, 0, this.bm, this.bl, this);
            }
            if (this.bf != null) {
                this.i();
            }
            if (this.bM) {
                this.b(this.bc);
            }
            graphics.drawImage(this.bd, 0, 0, this);
        }
    }
    
    public synchronized void i() {
        if (this.d) {
            this.notifyAll();
            while (!this.be) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.be = false;
        }
        this.bc.drawImage(this.bf, this.bg, this.bh, this);
    }
    
    public synchronized void j() {
        int checkImage = 0;
        this.prepareImage(this.B, this.bm, this.bl, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.B, this.bm, this.bl, this);
        }
    }
    
    public final void k() {
        try {
            if (this.E) {
                this.bk.newPixels();
                return;
            }
            this.bj.startProduction(this.bj.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public Rot2() {
        this.E = false;
        this.bo = 1;
        this.ck = 3.0f;
        this.X = 1.0f;
        this.Y = 1.0f;
        this.cd = 1.0f;
        this.cg = 1.0f;
        this.be = false;
        this.d = false;
        this.bn = false;
        this.ba = false;
    }
    
    public final void l() {
        this.cd += this.cb;
        if (this.cd > this.ck || this.cd < this.cl) {
            this.cb = -this.cb;
        }
        this.cg = this.cd;
        final float n = this.cd * (this.bX / 2) + this.bY;
        final float n2 = this.cd * (this.w / 2) + this.x;
        this.c = (this.c + this.bq & 0xFF);
        final float n3 = this.bE[this.c + 64] * this.cd;
        final float n4 = this.bE[this.c] * this.cg;
        final float n5 = this.bE[this.c + 64 + 64 & 0xFF] * this.cd;
        final float n6 = this.bE[this.c + 64] * this.cg;
        float n7 = n4 * this.bX - n;
        float n8 = n5 * this.w - n2;
        float n9 = n7;
        float n10 = n8;
        int n11 = 0;
        final int n12 = this.ca - 1;
        final int n13 = this.A - 1;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n7 += n3;
                n8 += n4;
                r[n11++] = bf[((int)n7 & n12) + ((int)n8 & n13) * this.ca];
            }
            n9 += n5;
            n10 += n6;
            n7 = n9;
            n8 = n10;
        }
    }
    
    public final void m() {
        this.cd += this.cb;
        if (this.cd > this.ck || this.cd < this.cl) {
            this.cb = -this.cb;
        }
        this.cg = this.cd;
        this.c = (this.c + this.bq & 0xFF);
        final float n = this.bE[this.c + 64] * this.cd;
        final float n2 = this.bE[this.c] * this.cg;
        final float n3 = this.bE[this.c + 64 + 64 & 0xFF] * this.cd;
        final float n4 = this.bE[this.c + 64] * this.cg;
        float n5 = n3 * -(this.bX / 2.0f) + n * -(this.bX / 2.0f) + this.ca / 2;
        float n6 = n4 * -(this.w / 2.0f) + n2 * -(this.w / 2.0f) + this.A / 2;
        float n7 = n5;
        float n8 = n6;
        int n9 = 0;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n5 += n;
                n6 += n2;
                final int n10 = (int)n5;
                final int n11 = (int)n6;
                if (n10 > 0 && n10 < this.ca && n11 > 0 && n11 < this.A) {
                    r[n9++] = bf[n10 + n11 * this.ca];
                }
                else {
                    r[n9] = this.m[n9++];
                }
            }
            n7 += n3;
            n8 += n4;
            n5 = n7;
            n6 = n8;
        }
    }
    
    public final void n() {
        this.c = (this.c + this.bq & 0xFF);
        final float n = this.cd * (this.bX / 2) + this.bY;
        final float n2 = this.cd * (this.w / 2) + this.x;
        final float n3 = this.bE[this.c + 64];
        final float n4 = this.bE[this.c];
        final float n5 = this.bE[this.c + 64 + 64 & 0xFF];
        final float n6 = this.bE[this.c + 64];
        float n7 = n4 * this.bX - n;
        float n8 = n5 * this.w - n2;
        float n9 = n7;
        float n10 = n8;
        int n11 = 0;
        final int n12 = this.ca - 1;
        final int n13 = this.A - 1;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n7 += n3;
                n8 += n4;
                r[n11++] = bf[((int)n7 & n12) + ((int)n8 & n13) * this.ca];
            }
            n9 += n5;
            n10 += n6;
            n7 = n9;
            n8 = n10;
        }
    }
    
    public final void o() {
        this.c = (this.c + this.bq & 0xFF);
        final float n = this.bE[this.c + 64];
        final float n2 = this.bE[this.c];
        final float n3 = this.bE[this.c + 64 + 64 & 0xFF];
        final float n4 = this.bE[this.c + 64];
        float n5 = n3 * -(this.bX / 2.0f) + n * -(this.bX / 2.0f) + this.ca / 2;
        float n6 = n4 * -(this.w / 2.0f) + n2 * -(this.w / 2.0f) + this.A / 2;
        float n7 = n5;
        float n8 = n6;
        int n9 = 0;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n5 += n;
                n6 += n2;
                final int n10 = (int)n5;
                final int n11 = (int)n6;
                if (n10 > 0 && n10 < this.ca && n11 > 0 && n11 < this.A) {
                    r[n9++] = bf[n10 + n11 * this.ca];
                }
                else {
                    r[n9] = this.m[n9++];
                }
            }
            n7 += n3;
            n8 += n4;
            n5 = n7;
            n6 = n8;
        }
    }
    
    public void run() {
        this.bQ.setPriority(this.bi);
        this.showStatus("");
        System.gc();
        this.o = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.bf != null && !this.d) {
            this.d = this.c();
        }
        if (this.G != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.bQ != null) {
            if (this.bO) {
                if (this.bp == 1) {
                    this.l();
                }
                else if (this.bp == 2) {
                    this.n();
                }
                else if (this.bp == 3) {
                    this.r();
                }
                else if (this.bp == 4) {
                    this.d();
                }
                else if (this.bp == 5) {
                    this.f();
                }
                else if (this.bp == 6) {
                    if (this.q > 230) {
                        this.f();
                    }
                    else if (this.q > 100) {
                        this.l();
                    }
                    else {
                        this.r();
                    }
                    if (this.q > 5000) {
                        this.q = 5000;
                    }
                    ++this.q;
                }
            }
            else if (this.bp == 1) {
                this.m();
            }
            else if (this.bp == 2) {
                this.o();
            }
            else if (this.bp == 3) {
                this.s();
            }
            else if (this.bp == 4) {
                this.e();
            }
            else if (this.bp == 5) {
                this.g();
            }
            else if (this.bp == 6) {
                if (this.q > 230) {
                    this.g();
                }
                else if (this.q > 100) {
                    this.m();
                }
                else {
                    this.s();
                }
                if (this.q > 5000) {
                    this.q = 5000;
                }
                ++this.q;
            }
            if (++this.l == this.H) {
                System.gc();
                this.l = 0;
            }
            try {
                this.k();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bo == 1) {
                this.bc.drawImage(this.B, 0, 0, this);
            }
            else {
                this.j();
                this.bc.drawImage(this.B, 0, 0, this.bm, this.bl, this);
            }
            if (this.bf != null) {
                this.i();
            }
            if (this.bM) {
                this.b(this.bc);
            }
            graphics.drawImage(this.bd, 0, 0, this);
            this.q();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void p() {
        this.bM = false;
        final String parameter = this.getParameter(c("X\u001eOw)O\tXo6"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("X\u001eOw.U\u000bR"));
            if (s == null) {
                s = c("D\u0014Ej C\u0015Cb6");
            }
            if (s.equals(c("D\u0014Ej C\u0015Cb6"))) {
                this.bu = 0;
            }
            else if (s.equals(c("Z\u001eEw3O\u001a["))) {
                this.bu = 1;
            }
            else if (s.equals(c("V\u0014Xn3B\u001c"))) {
                this.bu = 2;
            }
            else if (s.equals(c("E\u0015Ay5C\u0016^m="))) {
                this.bu = 3;
            }
            if (this.bu == 0) {
                this.a(parameter, 0);
                if (this.br != null) {
                    this.bM = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.F != null) {
                    this.bM = true;
                }
            }
        }
        if (this.bM) {
            String parameter2 = this.getParameter(c("X\u001eOw)\\\u001eRg"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bT = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("X\u001eOw<C\u0015C"));
            if (s2 == null) {
                s2 = c("m\t^b6");
            }
            int n = 0;
            if (this.getParameter(c("X\u001eOw8C\u0017S")).equalsIgnoreCase(c("u>d"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("X\u001eOw3X\u001a[j9"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("u>d"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("X\u001eOw)E\u0001R"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.t = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("X\u001eOw)D\u001aSl-")).equalsIgnoreCase(c("u>d"))) {
                this.bN = true;
            }
            else {
                this.bN = false;
            }
            this.bJ = new Color(Integer.valueOf(this.getParameter(c("x\u001eOw\u0019C\u0017e"))), Integer.valueOf(this.getParameter(c("x\u001eOw\u0019C\u0017p"))), Integer.valueOf(this.getParameter(c("x\u001eOw\u0019C\u0017u"))));
            this.bv = new Color(Integer.valueOf(this.getParameter(c("x\u001eOw\to\u0014[Q"))), Integer.valueOf(this.getParameter(c("x\u001eOw\to\u0014[D"))), Integer.valueOf(this.getParameter(c("x\u001eOw\to\u0014[A"))));
            this.bU = this.size().width;
            this.bV = this.size().height;
            if (this.bu == 0) {
                String parameter5 = this.getParameter(c("X\u001eOw5J\u001dDf."));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cf = Integer.valueOf(parameter5);
                if (this.cf < 0) {
                    this.cf = 0;
                }
                String parameter6 = this.getParameter(c("x\u001eOw\u0010Y\u0016GB7\\"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("x\u001eOw\u0010Y\u0016GP*H"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bS = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("x\u001eOw\tE\u0015RB7\\"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bx = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("x\u001eOw\tE\u0015RP*H"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.bB = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("x\u001eOw\tE\u0015RB4K\u0017R"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.by = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.t);
                this.bt = fontMetrics.stringWidth(this.br);
                this.bs = fontMetrics.getHeight();
                this.p = fontMetrics.getMaxDescent();
                this.cc = this.bU;
                if (this.cf < this.bs - this.p) {
                    this.cf = this.bs - this.p;
                }
                else if (this.cf > this.bV - this.p) {
                    this.cf = this.bV - this.p;
                }
                if (this.bx != 0) {
                    this.bC = new int[this.bU + 360];
                    this.bD = new int[this.bU + 360];
                    for (int i = 0; i < this.bU + 360; ++i) {
                        this.bC[i] = (int)(this.bx * Math.sin(this.by * i * 3.141592653589793 / 180.0)) - this.bs - this.p + this.cf;
                        this.bD[i] = this.bC[i] - this.bl;
                    }
                    this.bw = 360;
                    this.bz = this.bs + this.p + 1;
                    this.bA = this.bz - 1;
                }
            }
            else {
                if (this.bu == 1) {
                    String parameter11 = this.getParameter(c("X\u001eOw,_\u000bV`?"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.t);
                    this.u = fontMetrics2.getHeight() + intValue;
                    this.bW = new int[this.F.length];
                    for (int j = 0; j < this.F.length; ++j) {
                        this.bW[j] = (this.bU - fontMetrics2.stringWidth(this.F[j])) / 2;
                    }
                    this.Z = -this.u;
                    return;
                }
                if (this.bu >= 2) {
                    String parameter12 = this.getParameter(c("X\u001eOw7E\u0015Ql4X"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bL = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("X\u001eOw7M\u0003Ql4X"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bK = Integer.valueOf(parameter13);
                    this.bb = this.bK - this.bL;
                    this.t = null;
                    this.cj = new Font[this.bb];
                    int bl = this.bL;
                    for (int k = 0; k < this.bb; ++k) {
                        this.cj[k] = new Font(s2, n, bl++);
                    }
                    this.z = this.bU / 2.0f;
                    this.y = this.bV / 2.0f;
                    if (this.bu == 3) {
                        this.ci = this.bb - 1;
                        return;
                    }
                    this.ci = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.bu) {
            case 0: {
                this.a(graphics);
            }
            case 1: {
                this.c(graphics);
            }
            default: {
                this.d(graphics);
            }
        }
    }
    
    public void start() {
        if (this.bQ == null) {
            (this.bQ = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bQ != null && this.bQ.isAlive()) {
            this.bQ.stop();
        }
        this.bQ = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.t);
        this.Z += this.bT;
        if (this.Z > this.bV + this.F.length * this.u) {
            this.Z = -this.u;
        }
        if (this.bN) {
            for (int i = 0; i < this.F.length; ++i) {
                final String s = this.F[i];
                final int n = this.bW[i];
                final int n2 = this.bV - this.Z + i * this.u;
                graphics.setColor(this.bv);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bJ);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bJ);
        for (int j = 0; j < this.F.length; ++j) {
            graphics.drawString(this.F[j], this.bW[j], this.bV - this.Z + j * this.u);
        }
    }
    
    public synchronized void q() {
        Thread.yield();
        this.bP.sync();
        final long n = 10L - (System.currentTimeMillis() - this.o);
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
        this.o = System.currentTimeMillis();
        try {
            Thread.sleep(this.I);
        }
        catch (InterruptedException ex3) {}
    }
    
    public final void r() {
        this.cd += this.cb;
        if (this.cd > this.ck || this.cd < this.cl) {
            this.cb = -this.cb;
        }
        this.cg = this.cd;
        final float n = this.cd * (this.bX / 2) + this.bY;
        final float n2 = this.cd * (this.w / 2) + this.x;
        final float n3 = this.bE[64] * this.cd;
        final float n4 = this.bE[0] * this.cg;
        final float n5 = this.bE[128] * this.cd;
        final float n6 = this.bE[64] * this.cg;
        float n7 = n4 * this.bX - n;
        float n8 = n5 * this.w - n2;
        float n9 = n7;
        float n10 = n8;
        int n11 = 0;
        final int n12 = this.ca - 1;
        final int n13 = this.A - 1;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n7 += n3;
                n8 += n4;
                r[n11++] = bf[((int)n7 & n12) + ((int)n8 & n13) * this.ca];
            }
            n9 += n5;
            n10 += n6;
            n7 = n9;
            n8 = n10;
        }
    }
    
    public final void s() {
        this.cd += this.cb;
        if (this.cd > this.ck || this.cd < this.cl) {
            this.cb = -this.cb;
        }
        this.cg = this.cd;
        final float n = this.bE[64] * this.cd;
        final float n2 = this.bE[0] * this.cg;
        final float n3 = this.bE[128] * this.cd;
        final float n4 = this.bE[64] * this.cg;
        float n5 = n3 * -(this.bX / 2.0f) + n * -(this.bX / 2.0f) + this.ca / 2;
        float n6 = n4 * -(this.w / 2.0f) + n2 * -(this.w / 2.0f) + this.A / 2;
        float n7 = n5;
        float n8 = n6;
        int n9 = 0;
        final int bx = this.bX;
        final int w = this.w;
        final int[] r = this.r;
        final int[] bf = this.bF;
        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < bx; ++j) {
                n5 += n;
                n6 += n2;
                final int n10 = (int)n5;
                final int n11 = (int)n6;
                if (n10 > 0 && n10 < this.ca && n11 > 0 && n11 < this.A) {
                    r[n9++] = bf[n10 + n11 * this.ca];
                }
                else {
                    r[n9] = this.m[n9++];
                }
            }
            n7 += n3;
            n8 += n4;
            n5 = n7;
            n6 = n8;
        }
    }
    
    public void d(final Graphics graphics) {
        final String s = this.F[this.ch];
        graphics.setFont(this.cj[this.ci]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.cj[this.ci]);
        final int n = (int)(this.z - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.y + fontMetrics.getHeight() / 4.0f);
        if (this.bN) {
            graphics.setColor(this.bv);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bJ);
        graphics.drawString(s, n, n2);
        if (this.bu == 3) {
            this.ci -= this.bT;
            if (this.ci <= 1) {
                this.ci = this.bb - 1;
                ++this.ch;
                if (this.ch >= this.F.length) {
                    this.ch = 0;
                }
            }
        }
        else {
            this.ci += this.bT;
            if (this.ci >= this.bb) {
                this.ci = 0;
                ++this.ch;
                if (this.ch >= this.F.length) {
                    this.ch = 0;
                }
            }
        }
    }
    
    private static String c(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
    Label_0010:
        while (true) {
            while (true) {
                int n2 = 0;
                char c = ',';
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
                                c = '{';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '7';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '\u0003';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'Z';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
