import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
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

public class AnWater extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    double c;
    double d;
    boolean e;
    final String f = "!\u0013\r\r\u000f\u0014C\u001f\u0018J&\u0002\u001f\b\u0005@ \u0014\u0014\t\u0003\n]I\u001d\u0017\u0014S\u0000\u0004\u0006";
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    boolean o;
    int p;
    int q;
    int r;
    long s;
    int t;
    int u;
    boolean v;
    int[] w;
    int x;
    int y;
    boolean z;
    int A;
    int B;
    int C;
    int D;
    Font E;
    int F;
    private Graphics G;
    int H;
    float[] I;
    float[] J;
    float K;
    float L;
    int M;
    int N;
    private Image O;
    boolean P;
    int Q;
    boolean R;
    int S;
    boolean T;
    String[] U;
    URL V;
    int W;
    int X;
    int Y;
    int Z;
    String ba;
    String bb;
    String bc;
    String bd;
    String be;
    String bf;
    String bg;
    String bh;
    String bi;
    String bj;
    String bk;
    String bl;
    String bm;
    String bn;
    String bo;
    int bp;
    int bq;
    int br;
    boolean bs;
    int bt;
    int bu;
    int bv;
    private Graphics bw;
    private Image bx;
    boolean by;
    boolean bz;
    int bA;
    int bB;
    private Image bC;
    int bD;
    int bE;
    int bF;
    int bG;
    float bH;
    int bI;
    int bJ;
    anfy bK;
    MemoryImageSource bL;
    int bM;
    int bN;
    int bO;
    int bP;
    int bQ;
    float bR;
    int bS;
    int bT;
    boolean bU;
    int bV;
    int bW;
    String bX;
    int bY;
    int bZ;
    int ca;
    Color cb;
    int cc;
    int cd;
    int ce;
    int cf;
    int cg;
    int ch;
    int[] ci;
    int[] cj;
    int[] ck;
    int cl;
    int cm;
    int cn;
    String co;
    Color cp;
    int cq;
    int cr;
    boolean cs;
    boolean ct;
    Toolkit cu;
    Thread cv;
    int cw;
    int cx;
    int cy;
    int cz;
    int cA;
    int cB;
    short[] cC;
    short[] cD;
    short[] cE;
    int[] cF;
    int cG;
    Lware cH;
    int cI;
    int cJ;
    int cK;
    int cL;
    int cM;
    int cN;
    int cO;
    int cP;
    Font[] cQ;
    
    public AnWater() {
        this.R = false;
        this.bV = 1;
        this.d = -0.6;
        this.v = false;
        this.bz = false;
        this.e = false;
        this.bU = false;
        this.bs = false;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("$\f\u0013F\u001e@\u0011\u0018\f\u0005\u0016\u0006]\u0016\u001d\u0017M\u001c\u000f\f\u0019\t\u001c\u0017\u000bN\u0000\u0012\fJ\u0003\u0011\u0018\u0005\u0003\u0014\u0010]\r\u0003\u000e\u0006]\b\u0004@+),&A"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.bC, this);
        if (this.R) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bz;
        }
        return false;
    }
    
    final void c() {
        final float bh = this.bH;
        float[] array;
        if (this.M == 1) {
            array = this.J;
        }
        else {
            array = this.I;
        }
        float n = 0.0f;
        this.bM = 6 + (int)(Math.random() * this.cG) % (this.cG - 12);
        this.bN = 6 + (int)(Math.random() * this.H) % (this.H - 14);
        for (int i = -2; i < 3; ++i) {
            this.cK = 0;
            while (this.cK < this.H) {
                if (i == 0) {
                    n = bh;
                }
                else if (i == 1) {
                    n = bh / 2.0f;
                }
                else if (i == 2) {
                    n = bh / 4.0f;
                }
                else if (i == -1) {
                    n = bh / 2.0f;
                }
                else if (i == -2) {
                    n = bh / 4.0f;
                }
                final float[] array2 = array;
                final int n2 = this.cG * this.cK + this.bM + i;
                array2[n2] += n;
                ++this.cK;
            }
        }
        for (int j = -2; j < 3; ++j) {
            this.cI = 0;
            while (this.cI < this.cG) {
                if (j == 0) {
                    n = bh;
                }
                else if (j == 1) {
                    n = bh / 2.0f;
                }
                else if (j == 2) {
                    n = bh / 4.0f;
                }
                else if (j == -1) {
                    n = bh / 2.0f;
                }
                else if (j == -2) {
                    n = bh / 4.0f;
                }
                final float[] array3 = array;
                final int n3 = this.cG * (this.bN + j) + this.cI;
                array3[n3] += n;
                ++this.cI;
            }
        }
    }
    
    public void destroy() {
        if (this.bC != null) {
            this.bC.flush();
        }
        this.bC = null;
        if (this.bx != null) {
            this.bx.flush();
        }
        this.bx = null;
        if (this.bw != null) {
            this.bw.dispose();
        }
        this.bw = null;
        System.gc();
    }
    
    final synchronized void a(int n) {
        ++n;
        final float bh = this.bH;
        float[] array;
        if (this.M == 1) {
            array = this.J;
        }
        else {
            array = this.I;
        }
        final float n2 = n - 1.5f;
        this.cI = this.C;
        this.cK = this.D;
        if (n == 2) {
            array[this.cG * this.cK + this.cI] = (int)(Math.random() * bh) % bh;
            return;
        }
        this.bW = n * n;
        final float n3 = n2 * n2;
        final float n4 = this.bW - n3;
        this.q = -n;
        while (this.q < n) {
            this.r = this.q * this.q;
            this.p = -n;
            while (this.p < n) {
                final int n5 = this.p * this.p + this.r;
                if (n5 < this.bW) {
                    if (n5 > n3) {
                        final float n6 = (n5 - n3) / n4;
                        final float[] array2 = array;
                        final int n7 = this.cG * (this.q + this.cK) + (this.p + this.cI);
                        array2[n7] += bh - n6 * bh;
                    }
                    else {
                        final float[] array3 = array;
                        final int n8 = this.cG * (this.q + this.cK) + (this.p + this.cI);
                        array3[n8] += bh;
                    }
                }
                ++this.p;
            }
            ++this.q;
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
                this.showStatus(c(")\u000e\u001c\u0006\u000f@") + s + c("@\r\u0012\u0015J\u0006\f\b\u000f\u000eA"));
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
                            this.bX = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.bX = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.U = new String[n3 - 1];
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
                                this.U[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.U[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.U = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.E);
        if (this.a == 0) {
            this.cL = this.cM;
        }
        else {
            this.Q += this.cx;
            this.cL = this.cM - (int)Math.abs(this.a * Math.sin(this.Q / 90.0 * 3.141592653589793));
        }
        if (this.cd != 0) {
            for (int i = 0; i < this.cz; ++i) {
                final int n = this.ci[this.cc + i];
                graphics.copyArea(i, n, 1, this.cf, 0, this.bS - n);
            }
            if (this.ct) {
                graphics.setColor(this.cb);
                graphics.drawString(this.bX, this.cJ + 1, this.bS + this.bY + 1);
            }
            graphics.setColor(this.cp);
            graphics.drawString(this.bX, this.cJ, this.bS + this.bY);
            for (int j = 0; j < this.cz; ++j) {
                graphics.copyArea(j, this.bS, 1, this.cg, 0, this.cj[this.cc + j]);
            }
            this.cc -= this.ch;
            if (this.cc < 0) {
                this.cc += 360;
            }
        }
        else {
            if (this.ct) {
                graphics.setColor(this.cb);
                graphics.drawString(this.bX, this.cJ + 1, this.cL + 1);
            }
            graphics.setColor(this.cp);
            graphics.drawString(this.bX, this.cJ, this.cL);
        }
        this.cJ -= this.cy;
        if (this.cJ < -this.bZ) {
            this.cJ = this.cz;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bC) {
            if (n == 16) {
                this.bz = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.cu = this.getToolkit();
        this.co = this.getParameter(c("\u0013\u0017\u001c\u0015\u001f\u0013\u000e\u000e\u0006"));
        final String parameter = this.getParameter(c("\u0003\u0011\u0018\u0005\u0003\u0014\u0010"));
        if (parameter != null) {
            if (!parameter.startsWith(c("!\u0013\r\r\u000f\u0014C\u001f\u0018J&\u0002\u001f\b\u0005@ \u0014\u0014\t\u0003\n]I\u001d\u0017\u0014S\u0000\u0004\u0006"))) {
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
            s = c("\u0006\n\u0011\u0004");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("\u0006\n\u0011\u0004")) || s2.length() == 0 || s2.equalsIgnoreCase(c("\f\f\u001e\u0000\u0006\b\f\u000e\u0015")) || s2.equals(c("QQJOZNSSP"))) {
            this.bU = true;
        }
        else {
            if (s2.startsWith(c("\u0017\u0014\nO"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("\u0012\u0006\u001a\u0002\u0005\u0004\u0006"));
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
                        if (s5.startsWith(c("\u0017\u0014\nO"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bU = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("\u0012\u0006\u001a\r\u0003\u000e\b"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.V = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.V = null;
            }
        }
        if (this.getParameter(c("\u0012\u0006\u001a\u000f\u000f\u0017\u0005\u000f\u0000\u0007\u0005")).equalsIgnoreCase(c("9&."))) {
            this.bs = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("\u000f\u0015\u0018\u0013\u0003\r\u0004"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.bC = this.a(parameter4);
            if (this.bC != null) {
                String parameter5 = this.getParameter(c("\u000f\u0015\u0018\u0013\u0003\r\u0004%"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bD = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("\u000f\u0015\u0018\u0013\u0003\r\u0004$"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bE = Integer.valueOf(parameter6);
            }
        }
        this.bl = this.getParameter(c("-\n\u001323. "));
        if (this.bl == null) {
            this.bl = "10";
        }
        this.Z = Integer.valueOf(this.bl);
        this.ba = this.getParameter(c("\t\u000e\u001c\u0006\u000f"));
        this.bb = this.getParameter(c("\u0012\u0006\u000e"));
        if (this.bb == null) {
            this.bb = "1";
        }
        this.cG = this.size().width / this.bV;
        this.H = this.size().height / this.bV;
        this.bg = this.getParameter(c("\f\n\u001a\t\u001e"));
        if (this.bg.equalsIgnoreCase(c("9&."))) {
            this.T = true;
        }
        else {
            this.T = false;
        }
        this.bg = this.getParameter(c("\u0006\u000f\b\b\u000e\r\f\u0019\u0004"));
        if (this.bg == null) {
            this.bg = c("\u000f\n\u0011");
        }
        if (this.bg.equalsIgnoreCase(c("\u0017\u0002\t\u0004\u0018"))) {
            this.by = false;
        }
        else {
            this.by = true;
        }
        this.bh = this.getParameter(c("\u0001\u0016\t\u000e\u000e\u0005\u0010\u0014\u0006\u0004"));
        if (this.bh.equalsIgnoreCase(c("9&."))) {
            this.v = true;
        }
        else {
            this.v = false;
        }
        this.bi = this.getParameter(c("\u0004\u0006\u0013\u0012\u0003\u0014\u001a"));
        if (this.bi == null) {
            this.bi = "4";
        }
        this.bj = this.getParameter(c("\u0006\n\u000e\t\u0004\u0015\u000e"));
        if (this.bj == null) {
            this.bj = "0";
        }
        this.bk = this.getParameter(c("\u0003\u0011\u0012\u0012\u0019"));
        if (this.bk.equalsIgnoreCase(c("9&."))) {
            this.o = true;
        }
        else {
            this.o = false;
        }
        this.bm = this.getParameter(c("\u0003\u0011\u0012\u0012\u0019\u0006\u0002\u001e\u0015\u0005\u0012"));
        if (this.bm == null) {
            this.bm = "30";
        }
        this.bn = this.getParameter(c("\u0012\u0002\u0014\u000f\u0019\t\u0019\u0018"));
        if (this.bn == null) {
            this.bn = "0";
        }
        this.bo = this.getParameter(c("\u0012\u0002\u0014\u000f\f\u0001\u0000\t\u000e\u0018"));
        if (this.bo == null) {
            this.bo = "10";
        }
        this.bc = this.getParameter(c("\u0013\u0011\u001c\b\u0004\u0013\n\u0007\u0004"));
        if (this.bc == null) {
            this.bc = "0";
        }
        this.bd = this.getParameter(c("\u0013\u0011\u001c\b\u0004\u0006\u0002\u001e\u0015\u0005\u0012"));
        if (this.bd == null) {
            this.bd = "10";
        }
        this.be = this.getParameter(c("\r\u0006\u0010\u0005\u000f\f\u0002\u0004"));
        this.bf = this.getParameter(c("\u0010\u0011\u0014\u000e\u0018\t\u0017\u0004"));
        this.bV = Integer.valueOf(this.bb);
        this.t = Integer.valueOf(this.bi);
        this.y = Integer.valueOf(this.bj);
        this.n = Integer.valueOf(this.bm);
        this.bP = Integer.valueOf(this.bn);
        this.bO = Integer.valueOf(this.bo);
        this.cm = Integer.valueOf(this.bc);
        this.cl = Integer.valueOf(this.bd);
        this.X = Integer.valueOf(this.be);
        this.bJ = Integer.valueOf(this.bf);
        if (this.X < 0) {
            this.X = 0;
        }
        if (this.bJ > 10) {
            this.bJ = 10;
        }
        else if (this.bJ < 1) {
            this.bJ = 1;
        }
        this.bk = this.getParameter(c("\u0006\n\u0005\u0005\u0018\u000f\u0013"));
        if (this.bk == null) {
            this.bk = "NO";
        }
        if (this.bk.equalsIgnoreCase(c("9&."))) {
            this.z = true;
        }
        else {
            this.z = false;
        }
        this.bd = this.getParameter(c("\u0006\n\u0005\u0005\u0018\u000f\u0013%"));
        if (this.bd == null) {
            this.bd = "0";
        }
        this.C = Integer.valueOf(this.bd);
        this.bd = this.getParameter(c("\u0006\n\u0005\u0005\u0018\u000f\u0013$"));
        if (this.bd == null) {
            this.bd = "0";
        }
        this.D = Integer.valueOf(this.bd);
        this.bd = this.getParameter(c("\u0006\n\u0005\u0005\u0018\u000f\u0013;"));
        if (this.bd == null) {
            this.bd = "0";
        }
        this.A = Integer.valueOf(this.bd);
        this.x = this.A - 10;
        this.bd = this.getParameter(c("\u0006\n\u0005\u0005\u0018\u000f\u0013."));
        if (this.bd == null) {
            this.bd = "0";
        }
        this.B = Integer.valueOf(this.bd);
        this.bd = this.getParameter(c("\u0010\u0011\u0018\u0012\u0019\u0015\u0011\u0018"));
        if (this.bd == null) {
            this.bd = c("TSM");
        }
        this.bH = Float.valueOf(this.bd);
        this.bd = this.getParameter(c("\r\n\u0013\r\u0003\u0007\u000b\t"));
        if (this.bd == null) {
            this.bd = c("MQM");
        }
        this.Y = Integer.valueOf(this.bd);
        this.bd = this.getParameter(c("\r\u0002\u0005\r\u0003\u0007\u000b\t"));
        if (this.bd == null) {
            this.bd = c("QSM");
        }
        this.W = Integer.valueOf(this.bd);
        String parameter7 = this.getParameter(c("\t\r\t\u0004\u0018\u0001\u0000\t\b\u001c\u0005"));
        if (parameter7 == null) {
            parameter7 = "no";
        }
        if (parameter7.equalsIgnoreCase(c("9&."))) {
            this.P = true;
        }
        else {
            this.P = false;
        }
        if (this.bV > 8) {
            this.bV = 8;
        }
        else if (this.bV < 1) {
            this.bV = 1;
        }
        this.bT = this.cG * this.bV;
        this.bS = this.H * this.bV;
        if (this.t > 6) {
            this.t = 6;
        }
        else if (this.t < 2) {
            this.t = 2;
        }
        if (this.y > 2) {
            this.y = 2;
        }
        else if (this.y < 0) {
            this.y = 0;
        }
        if (this.n < 0) {
            this.n = 0;
        }
        if (this.bO < 0) {
            this.bO = 0;
        }
        if (this.cl < 0) {
            this.cl = 0;
        }
        if (this.bP > this.cG) {
            this.bP = this.cG - 1;
        }
        else if (this.bP < 0) {
            this.bP = 0;
        }
        if (this.cm > this.cG) {
            this.cm = this.cG - 1;
        }
        else if (this.cm < 0) {
            this.cm = 0;
        }
        if (this.v) {
            this.bH = 10.0f;
            this.y = 0;
            this.bP = 0;
            this.cm = 0;
            this.o = false;
        }
        this.showStatus(c(",\f\u001c\u0005\u0003\u000e\u0004]\b\u0007\u0001\u0004\u0018ODN"));
        this.O = this.a(this.ba);
        this.bR = (float)Math.pow(2.0, this.t);
        this.cw = this.cG * this.H;
        final int n14 = this.cG + 1;
        final int n15 = this.cw - n14;
        final int n16 = this.cw - 1;
        this.I = new float[this.cG * (this.H + 2) + n14 + 1];
        this.J = new float[this.cG * (this.H + 2) + n14 + 1];
        this.ck = new int[this.cG * this.H];
        this.w = new int[this.cG * this.H + 2];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.O, 0, 0, this.cG, this.H, this.ck, 0, this.cG);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        if (this.T) {
            this.cE = new short[this.cG * this.H + 2];
            this.cD = new short[this.cG * this.H + 2];
            this.cC = new short[this.cG * this.H + 2];
            this.N = 0;
            while (this.N < this.cw) {
                final int n17 = this.ck[this.N];
                this.cE[this.N] = (short)(n17 >> 16 & 0xFF);
                this.cD[this.N] = (short)(n17 >> 8 & 0xFF);
                this.cC[this.N] = (short)(n17 & 0xFF);
                ++this.N;
            }
        }
        try {
            this.d();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.d();
        }
        this.h();
        this.bx = this.createImage(this.bT, this.bS + this.cf);
        this.bw = this.bx.getGraphics();
        if (!this.bU) {
            (this.cH = new Lware(this.getAppletContext(), new Label(c("7\u0002\t\u0004\u0018@\u0002\r\u0011\u0006\u0005\u0017]\u0003\u0013@%\u001c\u0003\u0003\u000fC>\b\u001f\u0003\u0000\u0014A[YZKNSXM")))).setTitle(c("7\u0002\t\u0004\u0018@\"\r\u0011\u0006\u0005\u0017]\u0003\u0013@%\u001c\u0003\u0003\u000fC>\b\u001f\u0003\u0000\u0014"));
            this.cH.hide();
        }
    }
    
    void d() {
        this.bL = new MemoryImageSource(this.cG, this.H, new DirectColorModel(24, 16711680, 65280, 255), this.w, 0, this.cG);
        String s;
        try {
            s = System.getProperty(c("\n\u0002\u000b\u0000D\u0016\u0006\u000f\u0012\u0003\u000f\r"));
        }
        catch (SecurityException ex) {
            s = c("\u0015\r\u0016");
        }
        if (!s.startsWith(c("QMM"))) {
            try {
                this.bL.setAnimated(true);
                this.bL.setFullBufferUpdates(true);
                this.O = this.createImage(this.bL);
                this.bL.newPixels();
                this.R = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.R = false;
            }
        }
        if (!this.R) {
            this.bL = null;
            this.bK = new anfy(this.cG, this.H, new DirectColorModel(24, 16711680, 65280, 255), this.w, 0, this.cG);
            this.O = this.createImage(this.bK);
        }
    }
    
    public final synchronized boolean mouseDown(final Event event, int c, int d) {
        if (this.P) {
            if (c < 6) {
                c = 6;
            }
            else if (c > this.cG - 6) {
                c = this.cG - 6;
            }
            if (d < 6) {
                d = 6;
            }
            else if (d > this.H - 6) {
                d = this.H - 6;
            }
            final int c2 = this.C;
            final int d2 = this.D;
            final float bh = this.bH;
            this.C = c;
            this.D = d;
            this.bH = 400.0f;
            this.a(3);
            this.C = c2;
            this.D = d2;
            this.bH = bh;
        }
        if (!this.bU) {
            this.cH.show();
            this.cH.toFront();
            this.cH.requestFocus();
        }
        else if (this.V != null) {
            if (this.bs) {
                this.getAppletContext().showDocument(this.V, this.getParameter(c("\u0012\u0006\u001a\u0007\u0018\u0001\u000e\u0018\u000f\u000b\r\u0006")));
            }
            else {
                this.getAppletContext().showDocument(this.V);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.co);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public synchronized boolean mouseMove(final Event event, int c, int d) {
        if (this.P) {
            if (c < 6) {
                c = 6;
            }
            else if (c > this.cG - 6) {
                c = this.cG - 6;
            }
            if (d < 6) {
                d = 6;
            }
            else if (d > this.H - 6) {
                d = this.H - 6;
            }
            final int c2 = this.C;
            final int d2 = this.D;
            final float bh = this.bH;
            this.C = c;
            this.D = d;
            this.bH = 90.0f;
            this.a(2);
            this.C = c2;
            this.D = d2;
            this.bH = bh;
        }
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.O != null) {
            if (this.bV == 1) {
                this.bw.drawImage(this.O, 0, 0, this);
            }
            else {
                this.f();
                this.bw.drawImage(this.O, 0, 0, this.bT, this.bS, this);
            }
            if (this.bC != null) {
                this.e();
            }
            if (this.cs) {
                this.b(this.bw);
            }
            graphics.drawImage(this.bx, 0, 0, this);
        }
    }
    
    final void b(int n) {
        ++n;
        final float bh = this.bH;
        float[] array;
        if (this.M == 1) {
            array = this.J;
        }
        else {
            array = this.I;
        }
        final float n2 = n - 1.5f;
        final int ci = this.cG - n - 1;
        final int ck = this.H - n - 1;
        this.cI = (int)(Math.random() * this.cG);
        this.cK = (int)(Math.random() * this.H);
        if (this.cI < n) {
            this.cI = n;
        }
        else if (this.cI > ci) {
            this.cI = ci;
        }
        if (this.cK < n) {
            this.cK = n;
        }
        else if (this.cK > ck) {
            this.cK = ck;
        }
        if (n == 2) {
            array[this.cG * this.cK + this.cI] = (int)(Math.random() * bh) % bh;
            return;
        }
        this.bW = n * n;
        final float n3 = n2 * n2;
        final float n4 = this.bW - n3;
        this.q = -n;
        while (this.q < n) {
            this.r = this.q * this.q;
            this.p = -n;
            while (this.p < n) {
                final int n5 = this.p * this.p + this.r;
                if (n5 < this.bW) {
                    if (n5 > n3) {
                        final float n6 = (n5 - n3) / n4;
                        final float[] array2 = array;
                        final int n7 = this.cG * (this.q + this.cK) + (this.p + this.cI);
                        array2[n7] += bh - n6 * bh;
                    }
                    else {
                        final float[] array3 = array;
                        final int n8 = this.cG * (this.q + this.cK) + (this.p + this.cI);
                        array3[n8] += bh;
                    }
                }
                ++this.p;
            }
            ++this.q;
        }
    }
    
    final void c(final int n) {
        final float bh = this.bH;
        float[] array;
        if (this.M == 1) {
            array = this.J;
        }
        else {
            array = this.I;
        }
        final int ci = this.cG - n * 2 - 1;
        final int ck = this.H - n * 2 - 1;
        this.cI = (int)(Math.random() * this.cG);
        this.cK = (int)(Math.random() * this.H);
        if (this.cI < 1) {
            this.cI = 1;
        }
        else if (this.cI > ci) {
            this.cI = ci;
        }
        if (this.cK < 1) {
            this.cK = 1;
        }
        else if (this.cK > ck) {
            this.cK = ck;
        }
        final int n2 = n * 2;
        final int n3 = n2 - 1;
        for (int i = this.cK + 1; i < this.cK + n3; ++i) {
            for (int j = this.cI + 1; j < this.cI + n3; ++j) {
                final float[] array2 = array;
                final int n4 = this.cG * i + j;
                array2[n4] += bh;
            }
        }
        final float n5 = bh / 2.0f;
        final int n6 = this.cI + n3;
        for (int k = this.cK; k < this.cK + n2; ++k) {
            final float[] array3 = array;
            final int n7 = this.cG * k + this.cI;
            array3[n7] += n5;
        }
        for (int l = this.cK; l < this.cK + n2; ++l) {
            final float[] array4 = array;
            final int n8 = this.cG * l + n6;
            array4[n8] += n5;
        }
        final int n9 = this.cG * this.cK;
        final int n10 = this.cG * (this.cK + n3);
        for (int ci2 = this.cI; ci2 < this.cI + n2; ++ci2) {
            final float[] array5 = array;
            final int n11 = n9 + ci2;
            array5[n11] += n5;
        }
        for (int ci3 = this.cI; ci3 < this.cI + n2; ++ci3) {
            final float[] array6 = array;
            final int n12 = n10 + ci3;
            array6[n12] += n5;
        }
    }
    
    public synchronized void e() {
        if (this.e) {
            this.notifyAll();
            while (!this.bz) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bz = false;
        }
        this.bw.drawImage(this.bC, this.bD, this.bE, this);
    }
    
    public synchronized void f() {
        int checkImage = 0;
        this.prepareImage(this.O, this.bT, this.bS, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.O, this.bT, this.bS, this);
        }
    }
    
    public final void g() {
        try {
            if (this.R) {
                this.bL.newPixels();
                return;
            }
            this.bK.startProduction(this.bK.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.cv.setPriority(this.bJ);
        this.showStatus("");
        System.gc();
        this.s = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.bC != null && !this.e) {
            this.e = this.b();
        }
        if (this.V != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.cv != null) {
            this.j();
            if (this.v) {
                if (this.k < 50) {
                    this.bH = 50.0f;
                    this.bP = 1;
                    this.bO = 5;
                }
                if (this.k < 100) {
                    this.bH = 100.0f;
                    this.bP = 2;
                    this.bO = 5;
                }
                else if (this.k < 200) {
                    this.bH = 200.0f;
                    this.bP = 3;
                    this.bO = 4;
                }
                else if (this.k < 300) {
                    this.bH = 300.0f;
                    this.bP = 3;
                    this.bO = 5;
                }
                else if (this.k < 400) {
                    this.bH = 350.0f;
                    this.bP = 4;
                    this.bO = 3;
                }
                else if (this.k < 500) {
                    this.bH = 380.0f;
                    this.bP = 2;
                    this.bO = 20;
                }
                else if (this.k < 600) {
                    this.bH = 400.0f;
                    this.bP = 0;
                    this.y = 1;
                }
                else if (this.k < 700) {
                    this.y = 2;
                    this.cm = 2;
                    this.cl = 20;
                }
                else if (this.k < 800) {
                    this.y = 1;
                    this.cm = 4;
                    this.cl = 10;
                }
                else if (this.k < 900) {
                    this.y = 0;
                    this.cm = 2;
                    this.cl = 20;
                }
                else if (this.k < 1000) {
                    this.bP = 1;
                }
                else if (this.k < 1100) {
                    this.y = 1;
                    this.bP = 2;
                    this.bO = 30;
                    this.k = 1200;
                }
                ++this.k;
            }
            if (this.z && ++this.x > this.A) {
                this.a(this.B);
                this.x = 0;
            }
            if (!this.z) {
                if (this.y > 0) {
                    this.d(this.y);
                }
                if (this.bP > 0 && this.bQ++ >= this.bO) {
                    this.b(this.bP);
                    this.bQ = 0;
                }
                if (this.cm > 0 && this.cn++ >= this.cl) {
                    this.c(this.cm);
                    this.cn = 0;
                }
                if (this.o && this.m++ >= this.n) {
                    this.c();
                    this.m = 0;
                }
            }
            if (++this.g == this.X) {
                System.gc();
                this.g = 0;
            }
            try {
                this.g();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bV == 1) {
                this.bw.drawImage(this.O, 0, 0, this);
            }
            else {
                this.f();
                this.bw.drawImage(this.O, 0, 0, this.bT, this.bS, this);
            }
            if (this.bC != null) {
                this.e();
            }
            if (this.cs) {
                this.b(this.bw);
            }
            graphics.drawImage(this.bx, 0, 0, this);
            this.i();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void h() {
        this.cs = false;
        final String parameter = this.getParameter(c("\u0014\u0006\u0005\u0015\u0019\u0003\u0011\u0012\r\u0006"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("\u0014\u0006\u0005\u0015\u001e\u0019\u0013\u0018"));
            if (s == null) {
                s = c("\b\f\u000f\b\u0010\u000f\r\t\u0000\u0006");
            }
            if (s.equals(c("\b\f\u000f\b\u0010\u000f\r\t\u0000\u0006"))) {
                this.ca = 0;
            }
            else if (s.equals(c("\u0016\u0006\u000f\u0015\u0003\u0003\u0002\u0011"))) {
                this.ca = 1;
            }
            else if (s.equals(c("\u001a\f\u0012\f\u0003\u000e\u0004"))) {
                this.ca = 2;
            }
            else if (s.equals(c("\t\r\u000b\u001b\u0005\u000f\u000e\u0014\u000f\r"))) {
                this.ca = 3;
            }
            if (this.ca == 0) {
                this.a(parameter, 0);
                if (this.bX != null) {
                    this.cs = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.U != null) {
                    this.cs = true;
                }
            }
        }
        if (this.cs) {
            String parameter2 = this.getParameter(c("\u0014\u0006\u0005\u0015\u0019\u0010\u0006\u0018\u0005"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.cy = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("\u0014\u0006\u0005\u0015\f\u000f\r\t"));
            if (s2 == null) {
                s2 = c("!\u0011\u0014\u0000\u0006");
            }
            int n = 0;
            if (this.getParameter(c("\u0014\u0006\u0005\u0015\b\u000f\u000f\u0019")).equalsIgnoreCase(c("9&."))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("\u0014\u0006\u0005\u0015\u0003\u0014\u0002\u0011\b\t"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("9&."))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("\u0014\u0006\u0005\u0015\u0019\t\u0019\u0018"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.E = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("\u0014\u0006\u0005\u0015\u0019\b\u0002\u0019\u000e\u001d")).equalsIgnoreCase(c("9&."))) {
                this.ct = true;
            }
            else {
                this.ct = false;
            }
            this.cp = new Color(Integer.valueOf(this.getParameter(c("4\u0006\u0005\u0015)\u000f\u000f/"))), Integer.valueOf(this.getParameter(c("4\u0006\u0005\u0015)\u000f\u000f:"))), Integer.valueOf(this.getParameter(c("4\u0006\u0005\u0015)\u000f\u000f?"))));
            this.cb = new Color(Integer.valueOf(this.getParameter(c("4\u0006\u0005\u00159#\f\u00113"))), Integer.valueOf(this.getParameter(c("4\u0006\u0005\u00159#\f\u0011&"))), Integer.valueOf(this.getParameter(c("4\u0006\u0005\u00159#\f\u0011#"))));
            this.cz = this.size().width;
            this.cA = this.size().height;
            if (this.ca == 0) {
                String parameter5 = this.getParameter(c("\u0014\u0006\u0005\u0015\u0005\u0006\u0005\u000e\u0004\u001e"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cM = Integer.valueOf(parameter5);
                if (this.cM < 0) {
                    this.cM = 0;
                }
                String parameter6 = this.getParameter(c("4\u0006\u0005\u0015 \u0015\u000e\r \u0007\u0010"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("4\u0006\u0005\u0015 \u0015\u000e\r2\u001a\u0004"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.cx = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("4\u0006\u0005\u00159\t\r\u0018 \u0007\u0010"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.cd = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("4\u0006\u0005\u00159\t\r\u00182\u001a\u0004"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.ch = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("4\u0006\u0005\u00159\t\r\u0018 \u0004\u0007\u000f\u0018"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.ce = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.E);
                this.bZ = fontMetrics.stringWidth(this.bX);
                this.bY = fontMetrics.getHeight();
                this.u = fontMetrics.getMaxDescent();
                this.cJ = this.cz;
                if (this.cM < this.bY - this.u) {
                    this.cM = this.bY - this.u;
                }
                else if (this.cM > this.cA - this.u) {
                    this.cM = this.cA - this.u;
                }
                if (this.cd != 0) {
                    this.ci = new int[this.cz + 360];
                    this.cj = new int[this.cz + 360];
                    for (int i = 0; i < this.cz + 360; ++i) {
                        this.ci[i] = (int)(this.cd * Math.sin(this.ce * i * 3.141592653589793 / 180.0)) - this.bY - this.u + this.cM;
                        this.cj[i] = this.ci[i] - this.bS;
                    }
                    this.cc = 360;
                    this.cf = this.bY + this.u + 1;
                    this.cg = this.cf - 1;
                }
            }
            else {
                if (this.ca == 1) {
                    String parameter11 = this.getParameter(c("\u0014\u0006\u0005\u0015\u001c\u0013\u0013\u001c\u0002\u000f"));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.E);
                    this.F = fontMetrics2.getHeight() + intValue;
                    this.cF = new int[this.U.length];
                    this.N = 0;
                    while (this.N < this.U.length) {
                        this.cF[this.N] = (this.cz - fontMetrics2.stringWidth(this.U[this.N])) / 2;
                        ++this.N;
                    }
                    this.bq = -this.F;
                    return;
                }
                if (this.ca >= 2) {
                    String parameter12 = this.getParameter(c("\u0014\u0006\u0005\u0015\u0007\t\r\u001b\u000e\u0004\u0014"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.cr = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("\u0014\u0006\u0005\u0015\u0007\u0001\u001b\u001b\u000e\u0004\u0014"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.cq = Integer.valueOf(parameter13);
                    this.bu = this.cq - this.cr;
                    this.E = null;
                    this.cQ = new Font[this.bu];
                    int cr = this.cr;
                    this.N = 0;
                    while (this.N < this.bu) {
                        this.cQ[this.N] = new Font(s2, n, cr++);
                        ++this.N;
                    }
                    this.L = this.cz / 2.0f;
                    this.K = this.cA / 2.0f;
                    if (this.ca == 3) {
                        this.cP = this.bu - 1;
                        return;
                    }
                    this.cP = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.ca) {
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
        if (this.cv == null) {
            (this.cv = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.cv != null && this.cv.isAlive()) {
            this.cv.stop();
        }
        this.cv = null;
    }
    
    final void d(final int n) {
        final float bh = this.bH;
        final float n2 = this.bH / 2.0f;
        float[] array;
        if (this.M == 1) {
            array = this.J;
        }
        else {
            array = this.I;
        }
        final int n3 = this.cG / 2;
        final int n4 = this.H / 2;
        final double n5 = this.cG / 3.0;
        final double n6 = this.H / 2.5;
        final int n7 = n3 + (int)(n5 * Math.cos(this.c));
        final int n8 = n4 + (int)(n6 * Math.sin(this.c * 1.2 + 1.0));
        this.c += 0.05;
        final int n9 = this.cG * n8;
        array[n9 + n7] = bh;
        final float[] array2 = array;
        final int n10 = this.cG * (n8 - 1) + n7;
        array2[n10] += n2;
        final float[] array3 = array;
        final int n11 = this.cG * (n8 + 1) + n7;
        array3[n11] += n2;
        final float[] array4 = array;
        final int n12 = n9 + n7 + this.cG;
        array4[n12] += n2;
        final float[] array5 = array;
        final int n13 = n9 + n7 - this.cG;
        array5[n13] += n2;
        if (n == 1) {
            return;
        }
        final int n14 = n3 + (int)(n5 * Math.cos(this.d * 1.3 + 1.0));
        final int n15 = n4 + (int)(n6 * Math.sin(this.d));
        final int n16 = this.cG * n15;
        this.d += 0.05;
        array[n16 + n14] = bh;
        final float[] array6 = array;
        final int n17 = this.cG * (n15 - 1) + n14;
        array6[n17] += n2;
        final float[] array7 = array;
        final int n18 = this.cG * (n15 + 1) + n14;
        array7[n18] += n2;
        final float[] array8 = array;
        final int n19 = n16 + n14 + this.cG;
        array8[n19] += n2;
        final float[] array9 = array;
        final int n20 = n16 + n14 - this.cG;
        array9[n20] += n2;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.E);
        this.bq += this.cy;
        if (this.bq > this.cA + this.U.length * this.F) {
            this.bq = -this.F;
        }
        if (this.ct) {
            for (int i = 0; i < this.U.length; ++i) {
                final String s = this.U[i];
                final int n = this.cF[i];
                final int n2 = this.cA - this.bq + i * this.F;
                graphics.setColor(this.cb);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.cp);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.cp);
        for (int j = 0; j < this.U.length; ++j) {
            graphics.drawString(this.U[j], this.cF[j], this.cA - this.bq + j * this.F);
        }
    }
    
    public synchronized void i() {
        Thread.yield();
        this.cu.sync();
        final long n = 10L - (System.currentTimeMillis() - this.s);
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
        this.s = System.currentTimeMillis();
        try {
            Thread.sleep(this.Z);
        }
        catch (InterruptedException ex3) {}
    }
    
    public final void j() {
        final int[] w = this.w;
        final int[] ck = this.ck;
        float[] array = this.I;
        float[] array2 = this.J;
        if (this.M == 1) {
            array = this.J;
            array2 = this.I;
        }
        final short[] ce = this.cE;
        final short[] cd = this.cD;
        final short[] cc = this.cC;
        final int cg = this.cG;
        final int n = this.cG + 1;
        final int n2 = this.cw - n;
        final int n3 = this.cw - 1;
        final int n4 = this.cG * 2;
        final int y = this.Y;
        final int w2 = this.W;
        if (this.T) {
            final int n5 = cg;
            for (int i = 0; i < n; ++i) {
                final float n6 = array2[i + n5] - array2[i + n5 + 2];
                int n7 = (cg * (int)((array2[i + n5] - array2[i + n4]) / 2.8f) + (int)(n6 / 13.0f) + i) % n3;
                if (n7 < 0) {
                    n7 = -n7;
                }
                int n8 = (int)(n6 / 1.7f) << 1;
                if (n8 < y) {
                    n8 = y;
                }
                else if (n8 > w2) {
                    n8 = w2;
                }
                int n9 = ce[n7] + n8;
                int n10 = cd[n7] + n8;
                int n11 = cc[n7] + n8;
                if (n9 > 255) {
                    n9 = 255;
                }
                else if (n9 < 0) {
                    n9 = 0;
                }
                if (n10 > 255) {
                    n10 = 255;
                }
                else if (n10 < 0) {
                    n10 = 0;
                }
                if (n11 > 255) {
                    n11 = 255;
                }
                else if (n11 < 0) {
                    n11 = 0;
                }
                w[i] = (n9 << 16 | n10 << 8 | n11);
                array[i] = 0.0f;
            }
            for (int j = n; j < n3; ++j) {
                final float n12 = array2[j] - array2[j + 2];
                int n13 = (cg * (int)((array2[j] - array2[j + n4]) / 8.0f) + (int)(n12 / 8.0f) + j) % n3;
                if (n13 < 0) {
                    n13 = -n13;
                }
                int n14 = (int)n12 << 1;
                if (n14 < y) {
                    n14 = y;
                }
                else if (n14 > w2) {
                    n14 = w2;
                }
                int n15 = ce[n13] + n14;
                int n16 = cd[n13] + n14;
                int n17 = cc[n13] + n14;
                if (n15 > 255) {
                    n15 = 255;
                }
                else if (n15 < 0) {
                    n15 = 0;
                }
                if (n16 > 255) {
                    n16 = 255;
                }
                else if (n16 < 0) {
                    n16 = 0;
                }
                if (n17 > 255) {
                    n17 = 255;
                }
                else if (n17 < 0) {
                    n17 = 0;
                }
                w[j] = (n15 << 16 | n16 << 8 | n17);
                array[j] = (array2[j + cg] + array2[j - cg] + array2[j + 1] + array2[j - 1]) / 2.0f - array[j];
                final float[] array3 = array;
                final int n18 = j;
                array3[n18] -= array[j] / this.bR;
            }
        }
        else {
            final int n19 = cg + 1;
            for (int k = 0; k < n; ++k) {
                int n20 = (cg * (int)((array2[k] - array2[k + n4]) / 8.0f) + (int)((array2[k + n19] - array2[k + n19 + 2]) / 8.0f) + k) % n3;
                if (n20 < 0) {
                    n20 = -n20;
                }
                w[k] = ck[n20];
                array[k] = (array2[k + cg] + array2[k + 1]) / 2.0f - array[k];
                final float[] array4 = array;
                final int n21 = k;
                array4[n21] -= array[k] / this.bR;
            }
            for (int l = n; l < n3; ++l) {
                int n22 = (cg * (int)((array2[l] - array2[l + n4]) / 8.0f) + (int)((array2[l] - array2[l + 2]) / 8.0f) + l) % n3;
                if (n22 < 0) {
                    n22 = -n22;
                }
                w[l] = ck[n22];
                array[l] = (array2[l + cg] + array2[l - cg] + array2[l + 1] + array2[l - 1]) / 2.0f - array[l];
                final float[] array5 = array;
                final int n23 = l;
                array5[n23] -= array[l] / this.bR;
            }
        }
        w[n3] = ck[n3];
        for (int n24 = n2 + cg; n24 < this.cw; ++n24) {
            array[n24 + cg] = 0.0f;
            array[n24] /= 8.0f;
        }
        if (!this.by) {
            for (int n25 = 0; n25 < n; ++n25) {
                array2[n25] = 0.0f;
            }
            for (int n26 = n; n26 < this.cw; ++n26) {
                array2[n26] = (array[n26 + cg] + array[n26 - cg] + array[n26 + 1] + array[n26 - 1]) / 2.0f - array2[n26];
                final float[] array6 = array2;
                final int n27 = n26;
                array6[n27] -= array2[n26] / this.bR;
            }
            for (int n28 = n2 + cg; n28 < this.cw; ++n28) {
                array2[n28 + cg] = 0.0f;
                array2[n28] /= 8.0f;
            }
        }
        if (this.by) {
            this.M ^= 0x1;
        }
    }
    
    public void d(final Graphics graphics) {
        final String s = this.U[this.cO];
        graphics.setFont(this.cQ[this.cP]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.cQ[this.cP]);
        final int n = (int)(this.L - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.K + fontMetrics.getHeight() / 4.0f);
        if (this.ct) {
            graphics.setColor(this.cb);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.cp);
        graphics.drawString(s, n, n2);
        if (this.ca == 3) {
            this.cP -= this.cy;
            if (this.cP <= 1) {
                this.cP = this.bu - 1;
                ++this.cO;
                if (this.cO >= this.U.length) {
                    this.cO = 0;
                }
            }
        }
        else {
            this.cP += this.cy;
            if (this.cP >= this.bu) {
                this.cP = 0;
                ++this.cO;
                if (this.cO >= this.U.length) {
                    this.cO = 0;
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
                char c = '`';
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
                                c = 'c';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '}';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = 'a';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = 'j';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
