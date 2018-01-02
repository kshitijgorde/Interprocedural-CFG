import java.awt.FontMetrics;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.DirectColorModel;
import java.awt.image.ColorModel;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Label;
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
import java.awt.Graphics;
import java.net.URL;
import java.awt.Image;
import java.awt.Font;
import java.awt.image.IndexColorModel;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Plasma3 extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    final String d = "yk~~[L;lk\u001e~zl{Q\u0018Xgg][r.:IOl sP^";
    byte[] e;
    double f;
    double g;
    double h;
    byte[] i;
    int j;
    IndexColorModel k;
    byte[] l;
    long m;
    int n;
    int[] o;
    boolean p;
    Font q;
    int r;
    double s;
    double t;
    double u;
    byte[] v;
    double w;
    double x;
    double y;
    int z;
    float A;
    float B;
    int C;
    private Image D;
    int E;
    boolean F;
    String[] G;
    URL H;
    int I;
    int J;
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
    String X;
    String Y;
    String Z;
    String ba;
    String bb;
    int bc;
    boolean bd;
    int be;
    private Graphics bf;
    private Image bg;
    int bh;
    boolean bi;
    private Image bj;
    int bk;
    int bl;
    int[] bm;
    int bn;
    anfy bo;
    MemoryImageSource bp;
    int bq;
    int br;
    byte[] bs;
    double bt;
    double bu;
    double bv;
    boolean bw;
    int bx;
    String by;
    int bz;
    int bA;
    int bB;
    Color bC;
    int bD;
    int bE;
    int bF;
    int bG;
    int bH;
    int bI;
    int[] bJ;
    int[] bK;
    double bL;
    String bM;
    double bN;
    Color bO;
    int bP;
    int bQ;
    boolean bR;
    boolean bS;
    Toolkit bT;
    Thread bU;
    int bV;
    int bW;
    int bX;
    int bY;
    int bZ;
    int[] ca;
    int cb;
    Lware cc;
    int cd;
    int ce;
    int cf;
    int cg;
    int ch;
    Font[] ci;
    
    private final void a() {
        while (true) {
            this.showStatus(c("|t`5J\u0018ik\u007fQN~.eIO5o|XAqod_\u0016xa\u007f\u001e[ikvWLh.~WV~.{P\u0018SZ_r\u0019"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.bj, this);
        if (this.F) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bi;
        }
        return false;
    }
    
    public final void c() {
        for (int i = 0; i < 256; ++i) {
            for (int j = 0; j < 256; ++j) {
                this.l[i + 256 * j] = (byte)(this.s * (2.0 + Math.cos(0.1 * Math.sqrt((i - 128) * (i - 128) + (j - 128) * (j - 128))) + Math.cos(i / this.t) + Math.cos(j / this.u)));
            }
        }
    }
    
    public void destroy() {
        if (this.bj != null) {
            this.bj.flush();
        }
        this.bj = null;
        if (this.bg != null) {
            this.bg.flush();
        }
        this.bg = null;
        if (this.bf != null) {
            this.bf.dispose();
        }
        this.bf = null;
        System.gc();
    }
    
    public final void d() {
        for (int i = 0; i < 256; ++i) {
            this.bs[i] = (byte)Math.abs(Math.sin(i * 3.141592653589793 * 2.0 / (256.0 / this.bv)) * this.bt + this.bu);
            this.v[i] = (byte)Math.abs(Math.sin(i * 3.141592653589793 * 2.0 / (256.0 / this.y)) * this.w + this.x);
            this.e[i] = (byte)Math.abs(Math.sin(i * 3.141592653589793 * 2.0 / (256.0 / this.h)) * this.f + this.g);
        }
    }
    
    public final void e() {
        this.bN += this.bL;
        if (this.bN >= 4096.0 || this.bN <= 0.0) {
            this.bL = -this.bL;
        }
        final int n = (int)(48.0 + Math.cos(this.bN / 37.0) * 47.0 + 256 * (int)(48.0 + 47.0 * Math.sin(this.bN / 31.0)));
        final int n2 = (int)(48.0 + Math.sin(this.bN / 24.0) * 47.0 + 256 * (int)(48.0 + 47.0 * Math.cos(this.bN / 19.0)) - n);
        int n3 = 0;
        final int n4 = n + n2;
        final int z = this.z;
        final int cb = this.cb;
        final byte[] i = this.i;
        final byte[] l = this.l;
        for (int j = 0; j < z; ++j) {
            final int n5 = j << 8;
            for (int k = 0; k < cb; ++k) {
                final int n6 = n5 + k;
                i[n3++] = (byte)(l[n4 + n6 & 0xFFFF] + l[n6 & 0xFFFF]);
            }
        }
    }
    
    public final void f() {
        this.bN += this.bL;
        if (this.bN >= 4096.0 || this.bN <= 0.0) {
            this.bL = -this.bL;
        }
        final int n = (int)(48.0 + Math.cos(this.bN / 37.0) * 47.0 + 256 * (int)(48.0 + 47.0 * Math.sin(this.bN / 31.0)));
        final int n2 = (int)(48.0 + Math.sin(this.bN / 24.0) * 47.0 + 256 * (int)(48.0 + 47.0 * Math.cos(this.bN / 19.0)) - n);
        int n3 = 0;
        final int n4 = n + n2;
        final int z = this.z;
        final int cb = this.cb;
        final int[] o = this.o;
        final byte[] l = this.l;
        final int[] bm = this.bm;
        for (int i = 0; i < z; ++i) {
            final int n5 = i << 8;
            for (int j = 0; j < cb; ++j) {
                final int n6 = n5 + j;
                o[n3++] = bm[l[n4 + n6 & 0xFFFF] + l[n6 & 0xFFFF] & 0xFF];
            }
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
                this.showStatus(c("qvou[\u0018") + s + c("\u0018uaf\u001e^t{|Z\u0019"));
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
                            this.by = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.by = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.G = new String[n3 - 1];
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
                                this.G[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.G[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.G = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public void a(final Graphics graphics) {
        graphics.setFont(this.q);
        if (this.a == 0) {
            this.ce = this.cf;
        }
        else {
            this.E += this.bW;
            this.ce = this.cf - (int)Math.abs(this.a * Math.sin(this.E / 90.0 * 3.141592653589793));
        }
        if (this.bE != 0) {
            for (int i = 0; i < this.bY; ++i) {
                final int n = this.bJ[this.bD + i];
                graphics.copyArea(i, n, 1, this.bG, 0, this.bq - n);
            }
            if (this.bS) {
                graphics.setColor(this.bC);
                graphics.drawString(this.by, this.cd + 1, this.bq + this.bz + 1);
            }
            graphics.setColor(this.bO);
            graphics.drawString(this.by, this.cd, this.bq + this.bz);
            for (int j = 0; j < this.bY; ++j) {
                graphics.copyArea(j, this.bq, 1, this.bH, 0, this.bK[this.bD + j]);
            }
            this.bD -= this.bI;
            if (this.bD < 0) {
                this.bD += 360;
            }
        }
        else {
            if (this.bS) {
                graphics.setColor(this.bC);
                graphics.drawString(this.by, this.cd + 1, this.ce + 1);
            }
            graphics.setColor(this.bO);
            graphics.drawString(this.by, this.cd, this.ce);
        }
        this.cd -= this.bX;
        if (this.cd < -this.bA) {
            this.cd = this.bY;
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bj) {
            if (n == 16) {
                this.bi = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bT = this.getToolkit();
        this.bM = this.getParameter(c("KoofKKv}u"));
        final String parameter = this.getParameter(c("[ikvWLh"));
        if (parameter != null) {
            if (!parameter.startsWith(c("yk~~[L;lk\u001e~zl{Q\u0018Xgg][r.:IOl sP^"))) {
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
            s = c("^rbw");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("^rbw")) || s2.length() == 0 || s2.equalsIgnoreCase(c("TtmsRPt}f")) || s2.equals(c("\t)9<\u000e\u0016+ #"))) {
            this.bw = true;
        }
        else {
            if (s2.startsWith(c("Oly<"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("J~iqQ\\~"));
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
                        if (s5.startsWith(c("Oly<"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bw = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("J~i~WVp"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.H = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.H = null;
            }
        }
        if (this.getParameter(c("J~i|[O}|sS]")).equalsIgnoreCase(c("a^]"))) {
            this.bd = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("Wmk`WU|"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.bj = this.a(parameter4);
            if (this.bj != null) {
                String parameter5 = this.getParameter(c("Wmk`WU|V"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bk = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("Wmk`WU|W"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bl = Integer.valueOf(parameter6);
            }
        }
        this.Y = this.getParameter(c("ur`AgvX"));
        if (this.Y == null) {
            this.Y = "10";
        }
        this.J = Integer.valueOf(this.Y);
        this.U = this.getParameter(c("J~}"));
        if (this.U == null) {
            this.U = "1";
        }
        this.V = this.getParameter(c("_~`#"));
        if (this.V == null) {
            this.V = "43";
        }
        this.W = this.getParameter(c("_~` "));
        if (this.W == null) {
            this.W = "31";
        }
        this.X = this.getParameter(c("_~`!"));
        if (this.X == null) {
            this.X = "36";
        }
        this.Z = this.getParameter(c("J~j#"));
        if (this.Z == null) {
            this.Z = "0";
        }
        this.ba = this.getParameter(c("J~j "));
        if (this.ba == null) {
            this.ba = c("\t)9");
        }
        this.bb = this.getParameter(c("J~j!"));
        if (this.bb == null) {
            this.bb = "1";
        }
        this.K = this.getParameter(c("_ikwP\t"));
        if (this.K == null) {
            this.K = c("\t)9");
        }
        this.L = this.getParameter(c("_ikwP\n"));
        if (this.L == null) {
            this.L = c("\t)9");
        }
        this.M = this.getParameter(c("_ikwP\u000b"));
        if (this.M == null) {
            this.M = "2";
        }
        this.N = this.getParameter(c("Zw{w\u000f"));
        if (this.N == null) {
            this.N = c("\t)9");
        }
        this.O = this.getParameter(c("Zw{w\f"));
        if (this.O == null) {
            this.O = c("\t)9");
        }
        this.P = this.getParameter(c("Zw{w\r"));
        if (this.P == null) {
            this.P = "1";
        }
        this.Q = this.getParameter(c("KkkwZ"));
        if (this.Q == null) {
            this.Q = "1";
        }
        this.R = this.getParameter(c("^rv|[LhmsN]"));
        if (this.R.equalsIgnoreCase(c("a^]"))) {
            this.p = true;
        }
        else {
            this.p = false;
        }
        this.S = this.getParameter(c("U~cv[Tzw"));
        this.T = this.getParameter(c("Hig}LQow"));
        this.I = Integer.valueOf(this.S);
        this.bn = Integer.valueOf(this.T);
        if (this.I < 0) {
            this.I = 0;
        }
        if (this.bn > 10) {
            this.bn = 10;
        }
        else if (this.bn < 1) {
            this.bn = 1;
        }
        this.bx = Integer.valueOf(this.U);
        this.s = Double.valueOf(this.V);
        this.t = Double.valueOf(this.W);
        this.u = Double.valueOf(this.X);
        this.bt = Double.valueOf(this.Z);
        this.bu = Double.valueOf(this.ba);
        this.bv = Double.valueOf(this.bb);
        this.w = Double.valueOf(this.K);
        this.x = Double.valueOf(this.L);
        this.y = Double.valueOf(this.M);
        this.f = Double.valueOf(this.N);
        this.g = Double.valueOf(this.O);
        this.h = Double.valueOf(this.P);
        this.bL = Double.valueOf(this.Q);
        if (this.bx > 8) {
            this.bx = 8;
        }
        else if (this.bx < 1) {
            this.bx = 1;
        }
        final Dimension size = this.size();
        this.cb = size.width / this.bx;
        this.z = size.height / this.bx;
        this.br = this.cb * this.bx;
        this.bq = this.z * this.bx;
        if (this.s > 2000.0) {
            this.s = 2000.0;
        }
        else if (this.s < 8.0) {
            this.s = 8.0;
        }
        if (this.t > 256.0) {
            this.t = 256.0;
        }
        else if (this.t < 3.0) {
            this.t = 3.0;
        }
        if (this.u > 256.0) {
            this.u = 256.0;
        }
        else if (this.u < 3.0) {
            this.u = 3.0;
        }
        if (this.bL > 8.0) {
            this.bL = 8.0;
        }
        else if (this.bL < 1.0) {
            this.bL = 1.0;
        }
        this.bs = new byte[256];
        this.v = new byte[256];
        this.e = new byte[256];
        this.d();
        this.k = new IndexColorModel(8, 256, this.bs, this.v, this.e);
        if (this.p) {
            this.bV = this.cb * this.z;
            this.o = new int[this.bV];
            this.bm = new int[256];
            this.C = 0;
            while (this.C < 256) {
                this.bm[this.C] = (0xFF000000 | (this.bs[this.C] & 0xFF) << 16 | (this.v[this.C] & 0xFF) << 8 | (this.e[this.C] & 0xFF));
                ++this.C;
            }
        }
        else {
            this.i = new byte[this.cb * (this.z + 1)];
        }
        this.l = new byte[65536];
        this.c();
        try {
            this.g();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.g();
        }
        this.k();
        this.bg = this.createImage(this.br, this.bq + this.bG);
        this.bf = this.bg.getGraphics();
        if (!this.bw) {
            (this.cc = new Lware(this.getAppletContext(), new Label(c("hwoaSY;obNT~z2\\A;Hs\\Qt.QWMxm{\u001e\t\"7$\u0011\u0001# ")))).setTitle(c("hwoaSY;ObNT~z2\\A;Hs\\Qt.QWMxm{"));
            this.cc.hide();
        }
    }
    
    void g() {
        if (!this.p) {
            this.bp = new MemoryImageSource(this.cb, this.z, this.k, this.i, 0, this.cb);
        }
        else {
            this.bp = new MemoryImageSource(this.cb, this.z, new DirectColorModel(24, 16711680, 65280, 255), this.o, 0, this.cb);
        }
        String s;
        try {
            s = System.getProperty(c("Rzxs\u0010N~|aWWu"));
        }
        catch (SecurityException ex) {
            s = c("Mue");
        }
        if (!s.startsWith(c("\t5>"))) {
            try {
                this.bp.setAnimated(true);
                this.bp.setFullBufferUpdates(true);
                this.D = this.createImage(this.bp);
                this.bp.newPixels();
                this.F = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.F = false;
            }
        }
        if (!this.F) {
            this.bp = null;
            if (!this.p) {
                this.bo = new anfy(this.cb, this.z, this.k, this.i, 0, this.cb);
            }
            else {
                this.bo = new anfy(this.cb, this.z, new DirectColorModel(24, 16711680, 65280, 255), this.o, 0, this.cb);
            }
            this.D = this.createImage(this.bo);
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bw) {
            this.cc.show();
            this.cc.toFront();
            this.cc.requestFocus();
        }
        else if (this.H != null) {
            if (this.bd) {
                this.getAppletContext().showDocument(this.H, this.getParameter(c("J~itLYvk|_U~")));
            }
            else {
                this.getAppletContext().showDocument(this.H);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bM);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final void paint(final Graphics graphics) {
        if (this.D != null) {
            if (this.bx == 1) {
                this.bf.drawImage(this.D, 0, 0, this);
            }
            else {
                this.i();
                this.bf.drawImage(this.D, 0, 0, this.br, this.bq, this);
            }
            if (this.bj != null) {
                this.h();
            }
            if (this.bR) {
                this.b(this.bf);
            }
            graphics.drawImage(this.bg, 0, 0, this);
        }
    }
    
    public Plasma3() {
        this.F = false;
        this.bx = 1;
        this.s = 1.0;
        this.t = 1.0;
        this.u = 1.0;
        this.p = false;
        this.bi = false;
        this.c = false;
        this.bw = false;
        this.bd = false;
    }
    
    public synchronized void h() {
        if (this.c) {
            this.notifyAll();
            while (!this.bi) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bi = false;
        }
        this.bf.drawImage(this.bj, this.bk, this.bl, this);
    }
    
    public synchronized void i() {
        int checkImage = 0;
        this.prepareImage(this.D, this.br, this.bq, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.D, this.br, this.bq, this);
        }
    }
    
    public final void j() {
        try {
            if (this.F) {
                this.bp.newPixels();
                return;
            }
            this.bo.startProduction(this.bo.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public void run() {
        this.bU.setPriority(this.bn);
        this.showStatus("");
        System.gc();
        this.m = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.bj != null && !this.c) {
            this.c = this.b();
        }
        if (this.H != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.bU != null) {
            if (!this.p) {
                this.e();
            }
            else {
                this.f();
            }
            if (++this.j == this.I) {
                System.gc();
                this.j = 0;
            }
            try {
                this.j();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.bx == 1) {
                this.bf.drawImage(this.D, 0, 0, this);
            }
            else {
                this.i();
                this.bf.drawImage(this.D, 0, 0, this.br, this.bq, this);
            }
            if (this.bj != null) {
                this.h();
            }
            if (this.bR) {
                this.b(this.bf);
            }
            graphics.drawImage(this.bg, 0, 0, this);
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
    
    public void k() {
        this.bR = false;
        final String parameter = this.getParameter(c("L~vfM[ia~R"));
        if (parameter != null && !parameter.equalsIgnoreCase("NO")) {
            String s = this.getParameter(c("L~vfJAkk"));
            if (s == null) {
                s = c("Pt|{DWuzsR");
            }
            if (s.equals(c("Pt|{DWuzsR"))) {
                this.bB = 0;
            }
            else if (s.equals(c("N~|fW[zb"))) {
                this.bB = 1;
            }
            else if (s.equals(c("Bta\u007fWV|"))) {
                this.bB = 2;
            }
            else if (s.equals(c("QuxhQWvg|Y"))) {
                this.bB = 3;
            }
            if (this.bB == 0) {
                this.a(parameter, 0);
                if (this.by != null) {
                    this.bR = true;
                }
            }
            else {
                this.a(parameter, 1);
                if (this.G != null) {
                    this.bR = true;
                }
            }
        }
        if (this.bR) {
            String parameter2 = this.getParameter(c("L~vfMH~kv"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.bX = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("L~vfXWuz"));
            if (s2 == null) {
                s2 = c("yigsR");
            }
            int n = 0;
            if (this.getParameter(c("L~vf\\Wwj")).equalsIgnoreCase(c("a^]"))) {
                ++n;
            }
            String parameter3 = this.getParameter(c("L~vfWLzb{]"));
            if (parameter3 == null) {
                parameter3 = "NO";
            }
            if (parameter3.equalsIgnoreCase(c("a^]"))) {
                n += 2;
            }
            String parameter4 = this.getParameter(c("L~vfMQak"));
            if (parameter4 == null) {
                parameter4 = "12";
            }
            this.q = new Font(s2, n, Integer.valueOf(parameter4));
            if (this.getParameter(c("L~vfMPzj}I")).equalsIgnoreCase(c("a^]"))) {
                this.bS = true;
            }
            else {
                this.bS = false;
            }
            this.bO = new Color(Integer.valueOf(this.getParameter(c("l~vf}Ww\\"))), Integer.valueOf(this.getParameter(c("l~vf}WwI"))), Integer.valueOf(this.getParameter(c("l~vf}WwL"))));
            this.bC = new Color(Integer.valueOf(this.getParameter(c("l~vfm{tb@"))), Integer.valueOf(this.getParameter(c("l~vfm{tbU"))), Integer.valueOf(this.getParameter(c("l~vfm{tbP"))));
            this.bY = this.size().width;
            this.bZ = this.size().height;
            if (this.bB == 0) {
                String parameter5 = this.getParameter(c("L~vfQ^}}wJ"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cf = Integer.valueOf(parameter5);
                if (this.cf < 0) {
                    this.cf = 0;
                }
                String parameter6 = this.getParameter(c("l~vftMv~SSH"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.a = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("l~vftMv~AN\\"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.bW = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("l~vfmQukSSH"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.bE = Integer.valueOf(parameter8);
                String parameter9 = this.getParameter(c("l~vfmQukAN\\"));
                if (parameter9 == null) {
                    parameter9 = "0";
                }
                this.bI = Integer.valueOf(parameter9);
                String parameter10 = this.getParameter(c("l~vfmQukSP_wk"));
                if (parameter10 == null) {
                    parameter10 = "0";
                }
                this.bF = Integer.valueOf(parameter10);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.q);
                this.bA = fontMetrics.stringWidth(this.by);
                this.bz = fontMetrics.getHeight();
                this.n = fontMetrics.getMaxDescent();
                this.cd = this.bY;
                if (this.cf < this.bz - this.n) {
                    this.cf = this.bz - this.n;
                }
                else if (this.cf > this.bZ - this.n) {
                    this.cf = this.bZ - this.n;
                }
                if (this.bE != 0) {
                    this.bJ = new int[this.bY + 360];
                    this.bK = new int[this.bY + 360];
                    for (int i = 0; i < this.bY + 360; ++i) {
                        this.bJ[i] = (int)(this.bE * Math.sin(this.bF * i * 3.141592653589793 / 180.0)) - this.bz - this.n + this.cf;
                        this.bK[i] = this.bJ[i] - this.bq;
                    }
                    this.bD = 360;
                    this.bG = this.bz + this.n + 1;
                    this.bH = this.bG - 1;
                }
            }
            else {
                if (this.bB == 1) {
                    String parameter11 = this.getParameter(c("L~vfHKkoq["));
                    if (parameter11 == null) {
                        parameter11 = "10";
                    }
                    final int intValue = Integer.valueOf(parameter11);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.q);
                    this.r = fontMetrics2.getHeight() + intValue;
                    this.ca = new int[this.G.length];
                    this.C = 0;
                    while (this.C < this.G.length) {
                        this.ca[this.C] = (this.bY - fontMetrics2.stringWidth(this.G[this.C])) / 2;
                        ++this.C;
                    }
                    this.bc = -this.r;
                    return;
                }
                if (this.bB >= 2) {
                    String parameter12 = this.getParameter(c("L~vfSQuh}PL"));
                    if (parameter12 == null) {
                        parameter12 = "2";
                    }
                    this.bQ = Integer.valueOf(parameter12);
                    String parameter13 = this.getParameter(c("L~vfSYch}PL"));
                    if (parameter13 == null) {
                        parameter13 = "72";
                    }
                    this.bP = Integer.valueOf(parameter13);
                    this.be = this.bP - this.bQ;
                    this.q = null;
                    this.ci = new Font[this.be];
                    int bq = this.bQ;
                    this.C = 0;
                    while (this.C < this.be) {
                        this.ci[this.C] = new Font(s2, n, bq++);
                        ++this.C;
                    }
                    this.B = this.bY / 2.0f;
                    this.A = this.bZ / 2.0f;
                    if (this.bB == 3) {
                        this.ch = this.be - 1;
                        return;
                    }
                    this.ch = 0;
                }
            }
        }
    }
    
    public void b(final Graphics graphics) {
        switch (this.bB) {
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
        if (this.bU == null) {
            (this.bU = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.bU != null && this.bU.isAlive()) {
            this.bU.stop();
        }
        this.bU = null;
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        graphics.setFont(this.q);
        this.bc += this.bX;
        if (this.bc > this.bZ + this.G.length * this.r) {
            this.bc = -this.r;
        }
        if (this.bS) {
            for (int i = 0; i < this.G.length; ++i) {
                final String s = this.G[i];
                final int n = this.ca[i];
                final int n2 = this.bZ - this.bc + i * this.r;
                graphics.setColor(this.bC);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.bO);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.bO);
        for (int j = 0; j < this.G.length; ++j) {
            graphics.drawString(this.G[j], this.ca[j], this.bZ - this.bc + j * this.r);
        }
    }
    
    public synchronized void l() {
        Thread.yield();
        this.bT.sync();
        final long n = 10L - (System.currentTimeMillis() - this.m);
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
        this.m = System.currentTimeMillis();
        try {
            Thread.sleep(this.J);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void d(final Graphics graphics) {
        final String s = this.G[this.cg];
        graphics.setFont(this.ci[this.ch]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.ci[this.ch]);
        final int n = (int)(this.B - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.A + fontMetrics.getHeight() / 4.0f);
        if (this.bS) {
            graphics.setColor(this.bC);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.bO);
        graphics.drawString(s, n, n2);
        if (this.bB == 3) {
            this.ch -= this.bX;
            if (this.ch <= 1) {
                this.ch = this.be - 1;
                ++this.cg;
                if (this.cg >= this.G.length) {
                    this.cg = 0;
                }
            }
        }
        else {
            this.ch += this.bX;
            if (this.ch >= this.be) {
                this.ch = 0;
                ++this.cg;
                if (this.cg >= this.G.length) {
                    this.cg = 0;
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
                char c = '8';
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
                                c = '\u001b';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '\u000e';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '\u0012';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '>';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
