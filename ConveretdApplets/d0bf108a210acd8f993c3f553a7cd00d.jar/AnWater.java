import java.awt.Event;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.image.PixelGrabber;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Font;
import java.awt.Color;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class AnWater extends Applet implements Runnable, ImageObserver
{
    anfy a;
    MemoryImageSource b;
    boolean c;
    Toolkit d;
    int e;
    long f;
    Thread g;
    Lware h;
    int i;
    int j;
    int k;
    boolean l;
    boolean m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int[] s;
    int[] t;
    boolean u;
    int v;
    int w;
    int x;
    int y;
    private Image z;
    int A;
    int B;
    Frame C;
    float[] D;
    float[] E;
    float F;
    int G;
    int H;
    int I;
    int J;
    int K;
    double L;
    double M;
    int N;
    int O;
    int P;
    float Q;
    int R;
    boolean S;
    int T;
    int U;
    int V;
    int W;
    int X;
    int Y;
    int Z;
    boolean ba;
    int bb;
    int bc;
    boolean bd;
    int be;
    int bf;
    int bg;
    int bh;
    short[] bi;
    short[] bj;
    short[] bk;
    int bl;
    int bm;
    int bn;
    int bo;
    int bp;
    int bq;
    int br;
    int bs;
    int bt;
    int bu;
    int bv;
    int bw;
    int bx;
    int by;
    private Image bz;
    private Graphics bA;
    String bB;
    String bC;
    String bD;
    String bE;
    String bF;
    String bG;
    String bH;
    String bI;
    String bJ;
    String bK;
    String bL;
    String bM;
    String bN;
    String bO;
    private Image bP;
    private Graphics bQ;
    boolean bR;
    boolean bS;
    final String bT = "*k\u000eg`\u001f;\u001cr%-z\u001cbjKX\u0017~f\br^#r\u001clPjk\r";
    boolean bU;
    URL bV;
    boolean bW;
    String bX;
    int bY;
    int bZ;
    int ca;
    int cb;
    int cc;
    int cd;
    int ce;
    int cf;
    int cg;
    int ch;
    String ci;
    int cj;
    boolean ck;
    boolean cl;
    Color cm;
    Color cn;
    Font co;
    int cp;
    int cq;
    int cr;
    int cs;
    int[] ct;
    int[] cu;
    String[] cv;
    int cw;
    int cx;
    Font[] cy;
    int cz;
    int cA;
    private int cB;
    int cC;
    int cD;
    int cE;
    int cF;
    int cG;
    int[] cH;
    int cI;
    int cJ;
    float cK;
    float cL;
    int cM;
    int cN;
    String cO;
    int cP;
    int cQ;
    public static int cR;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.z) {
            if (n == 16) {
                this.bR = true;
            }
            return true;
        }
        return true;
    }
    
    public void destroy() {
        if (this.h != null) {
            this.h.hide();
        }
        this.h = null;
        if (this.z != null) {
            this.z.flush();
        }
        this.z = null;
        if (this.bP != null) {
            this.bP.flush();
        }
        this.bP = null;
        if (this.bQ != null) {
            this.bQ.dispose();
        }
        this.bQ = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.bS) {
            this.notifyAll();
            while (!this.bR) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bR = false;
        }
        this.bQ.drawImage(this.z, this.A, this.B, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.z, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bR;
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
                this.showStatus(c("\"v\u001fl`K") + s + c("Ku\u0011\u007f%\rt\u000beaJ"));
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
            this.showStatus(c("/t\u0010,qKi\u001bfj\u001d~^|r\u001c5\u001fec\u0012o\u001bjhEx\u0011f%\bi\u001bol\u001fh^gl\u0005~^bkKS*FIJ"));
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
        final int cr = AnWater.cR;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        this.cO = this.getParameter(c("\u0018o\u001f\u007fp\u0018v\rl"));
        final String parameter;
        final String s = parameter = this.getParameter(c("\bi\u001bol\u001fh"));
        String c = null;
        Label_0117: {
            Label_0086: {
                Label_0082: {
                    if (cr == 0) {
                        if (parameter == null) {
                            break Label_0082;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (cr != 0) {
                            break Label_0117;
                        }
                    }
                    if (parameter.startsWith(c("*k\u000eg`\u001f;\u001cr%-z\u001cbjKX\u0017~f\br^#r\u001clPjk\r"))) {
                        break Label_0086;
                    }
                    this.a();
                    if (cr == 0) {
                        break Label_0086;
                    }
                }
                this.a();
            }
            (this.h = new Lware(this, c("<z\nnwKz\u000e{i\u000eo"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("\rr\u0012n");
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
        Label_0644: {
            Label_0635: {
                int length = 0;
                Label_0254: {
                    boolean b = false;
                    Label_0241: {
                        Label_0227: {
                            if (cr == 0) {
                                Label_0217: {
                                    if (!c.equals(c("\rr\u0012n"))) {
                                        int equals;
                                        final int n = equals = ((b = (s3.length() != 0)) ? 1 : 0);
                                        if (cr == 0) {
                                            if (n < 1) {
                                                break Label_0217;
                                            }
                                            final boolean b2;
                                            equals = ((b2 = (b = s3.startsWith(c("\u0007t\u001dji")))) ? 1 : 0);
                                        }
                                        if (cr == 0) {
                                            if (n != 0) {
                                                break Label_0217;
                                            }
                                            b = ((equals = (s3.equals(c("Z)I%5E+P:")) ? 1 : 0)) != 0);
                                        }
                                        if (cr != 0) {
                                            break Label_0241;
                                        }
                                        if (equals == 0) {
                                            break Label_0227;
                                        }
                                    }
                                }
                                this.bU = true;
                            }
                            if (cr == 0) {
                                break Label_0635;
                            }
                        }
                        length = ((b = s3.startsWith(c("\u001cl\t%"))) ? 1 : 0);
                        if (cr != 0) {
                            break Label_0254;
                        }
                    }
                    if (b) {
                        s3 = s3.substring(4);
                    }
                    length = s3.length();
                }
                final int n3;
                final int n2 = n3 = length;
                if (cr != 0 || n3 > 0) {
                    final char[] array = new char[n3];
                    s3.getChars(0, n2, array, 0);
                    int n4 = 0;
                    while (true) {
                        while (true) {
                            Label_0315: {
                                if (cr == 0) {
                                    break Label_0315;
                                }
                                final char[] array2 = array;
                                final int n5 = n4;
                                if (cr != 0 || array2[n5] == '0') {
                                    array2[n5] = '1';
                                }
                                n4 += 5;
                            }
                            if (n4 < n2) {
                                continue;
                            }
                            break;
                        }
                        if (cr != 0) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("\u0019~\u0019hj\u000f~"));
                if (cr != 0) {
                    break Label_0644;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (cr != 0) {
                        break Label_0644;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n6 = 1;
                        try {
                            int n7 = 0;
                            while (true) {
                                while (true) {
                                    Label_0410: {
                                        if (cr == 0) {
                                            break Label_0410;
                                        }
                                        if (s4.charAt(n7) == '+') {
                                            ++n6;
                                        }
                                        ++n7;
                                    }
                                    if (n7 < s4.length()) {
                                        continue;
                                    }
                                    break;
                                }
                                if (cr != 0) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n6];
                        final int n8 = n6;
                        if (cr == 0 && n8 == 1) {
                            array3[0] = s4.length();
                            if (cr != 0) {
                                goto Label_0460;
                            }
                        }
                        else {
                            int n9 = n8;
                            try {
                                int n10 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0496: {
                                            if (cr == 0) {
                                                break Label_0496;
                                            }
                                            if (s4.charAt(n10) == '+') {
                                                array3[n9] = n10;
                                                ++n9;
                                            }
                                            ++n10;
                                        }
                                        if (n10 < s4.length()) {
                                            continue;
                                        }
                                        break;
                                    }
                                    if (cr != 0) {
                                        continue;
                                    }
                                    break;
                                }
                            }
                            catch (StringIndexOutOfBoundsException ex4) {}
                            array3[n9] = s4.length();
                        }
                        final String[] array4 = new String[n6];
                        int n11 = 0;
                        int n12 = 0;
                        int n13;
                        while (true) {
                            while (true) {
                                Label_0576: {
                                    if (cr == 0) {
                                        break Label_0576;
                                    }
                                    try {
                                        array4[n12] = s4.substring(n11, array3[n12]);
                                    }
                                    catch (StringIndexOutOfBoundsException ex5) {}
                                    n11 = array3[n12] + 1;
                                    ++n12;
                                }
                                if (n12 < n6) {
                                    continue;
                                }
                                break;
                            }
                            n13 = 0;
                            if (cr != 0) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            Label_0628: {
                                if (cr == 0) {
                                    break Label_0628;
                                }
                                if (s3.equals(this.h.dr(array4[n13], 0, this.bU))) {
                                    this.bU = true;
                                }
                                ++n13;
                            }
                            if (n13 < n6) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
            this.getParameter(c("\u0019~\u0019gl\u0005p"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0712: {
            if (cr == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (cr != 0) {
                        break Label_0712;
                    }
                    if (!s8.equalsIgnoreCase(c("%T"))) {
                        try {
                            this.bV = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.bV = null;
                        }
                    }
                }
                this.getParameter(c("\u0019~\u0019e`\u001c}\fjh\u000e"));
            }
        }
        if (s7.equalsIgnoreCase(c("2^-"))) {
            this.bW = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0753: {
                    if (cr == 0) {
                        break Label_0753;
                    }
                    final Container parent2 = ((Frame)parent).getParent();
                    parent = parent2;
                }
                if (!(parent instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.C = (Frame)parent).setCursor(3);
            final Container parent2 = this;
            if (cr != 0) {
                continue;
            }
            break;
        }
        final String parameter3 = this.getParameter(c("\u0004m\u001byl\u0006|"));
        String s10;
        String bc;
        final String s9 = bc = (s10 = parameter3);
        Label_0996: {
            Label_0940: {
                if (cr == 0) {
                    AnWater anWater = null;
                    Label_0932: {
                        if (s9 != null) {
                            final String s11 = parameter3;
                            if (cr != 0) {
                                break Label_0940;
                            }
                            if (!s11.equalsIgnoreCase(c("%T"))) {
                                this.z = this.a(parameter3);
                                anWater = this;
                                if (cr != 0) {
                                    break Label_0932;
                                }
                                if (this.z != null) {
                                    final String parameter4;
                                    String s12 = parameter4 = this.getParameter(c("\u0004m\u001byl\u0006|&"));
                                    if (cr == 0) {
                                        if (parameter4 == null) {
                                            s12 = "0";
                                        }
                                        this.A = Integer.valueOf(s12);
                                        this.getParameter(c("\u0004m\u001byl\u0006|'"));
                                    }
                                    final String s14;
                                    String s13 = s14 = parameter4;
                                    if (cr != 0 || s14 == null) {
                                        s13 = s14;
                                    }
                                    this.B = Integer.valueOf(s13);
                                }
                            }
                        }
                        this.bX = this.getParameter(c("&r\u0010X\\%X"));
                        anWater = this;
                    }
                    final String s15;
                    bc = (s15 = (s10 = anWater.bX));
                }
                if (cr != 0) {
                    break Label_0996;
                }
            }
            if (s9 == null) {
                this.bX = c("Z+");
            }
            this.e = Integer.valueOf(this.bX);
            this.bB = this.getParameter(c("\u0002v\u001fl`"));
            this.bC = this.getParameter(c("\u0019~\r"));
            s10 = (bc = this.bC);
        }
        Label_1090: {
            AnWater anWater2 = null;
            Label_1086: {
                if (cr == 0) {
                    if (bc == null) {
                        this.bC = "1";
                    }
                    this.n = this.size().width / this.r;
                    this.o = this.size().height / this.r;
                    this.bD = this.getParameter(c("\u0007r\u0019cq"));
                    anWater2 = this;
                    if (cr != 0) {
                        break Label_1086;
                    }
                    s10 = this.bD;
                }
                if (s10.equalsIgnoreCase(c("2^-"))) {
                    this.S = true;
                    if (cr == 0) {
                        break Label_1090;
                    }
                }
                anWater2 = this;
            }
            anWater2.S = false;
        }
        this.bD = this.getParameter(c("\rw\u000bba\u0006t\u001an"));
        final String bd = this.bD;
        Label_1159: {
            AnWater anWater3 = null;
            Label_1155: {
                if (cr == 0) {
                    if (bd == null) {
                        this.bD = c("\u0004r\u0012");
                    }
                    anWater3 = this;
                    if (cr != 0) {
                        break Label_1155;
                    }
                    final String bd2 = this.bD;
                }
                if (bd.equalsIgnoreCase(c("\u001cz\nnw"))) {
                    this.l = false;
                    if (cr == 0) {
                        break Label_1159;
                    }
                }
                anWater3 = this;
            }
            anWater3.l = true;
        }
        this.bE = this.getParameter(c("\nn\nda\u000eh\u0017lk"));
        AnWater anWater4 = this;
        Label_1207: {
            if (cr == 0) {
                if (this.bE.equalsIgnoreCase(c("2^-"))) {
                    this.bd = true;
                    if (cr == 0) {
                        break Label_1207;
                    }
                }
                anWater4 = this;
            }
            anWater4.bd = false;
        }
        this.bF = this.getParameter(c("\u000f~\u0010xl\u001fb"));
        final String bf = this.bF;
        Label_1317: {
            AnWater anWater5 = null;
            Label_1313: {
                String bh = null;
                Label_1291: {
                    if (cr == 0) {
                        if (bf == null) {
                            this.bF = "4";
                        }
                        this.bG = this.getParameter(c("\rr\rck\u001ev"));
                        final String bg;
                        bh = (bg = this.bG);
                        if (cr != 0) {
                            break Label_1291;
                        }
                    }
                    if (bf == null) {
                        this.bG = "0";
                    }
                    this.bH = this.getParameter(c("\bi\u0011xv"));
                    anWater5 = this;
                    if (cr != 0) {
                        break Label_1313;
                    }
                    bh = this.bH;
                }
                if (bh.equalsIgnoreCase(c("2^-"))) {
                    this.ba = true;
                    if (cr == 0) {
                        break Label_1317;
                    }
                }
                anWater5 = this;
            }
            anWater5.ba = false;
        }
        this.bI = this.getParameter(c("\bi\u0011xv\rz\u001d\u007fj\u0019"));
        String bi;
        final String s16 = bi = this.bI;
        if (cr == 0) {
            if (s16 == null) {
                this.bI = c("X+");
            }
            this.bJ = this.getParameter(c("\u0019z\u0017ev\u0002a\u001b"));
            final String bj;
            bi = (bj = this.bJ);
        }
        String s17 = null;
        String bl = null;
        Label_1433: {
            if (cr == 0) {
                if (s16 == null) {
                    this.bJ = "0";
                }
                this.bK = this.getParameter(c("\u0019z\u0017ec\nx\ndw"));
                bl = (bi = (s17 = this.bK));
                if (cr != 0) {
                    break Label_1433;
                }
            }
            if (bi == null) {
                this.bK = c("Z+");
            }
            this.bL = this.getParameter(c("\u0018i\u001fbk\u0018r\u0004n"));
            s17 = (bl = this.bL);
        }
        AnWater anWater6 = null;
        Label_1648: {
            if (cr == 0) {
                if (bl == null) {
                    this.bL = "0";
                }
                this.bM = this.getParameter(c("\u0018i\u001fbk\rz\u001d\u007fj\u0019"));
                anWater6 = this;
                if (cr != 0) {
                    break Label_1648;
                }
                s17 = this.bM;
            }
            if (s17 == null) {
                this.bM = c("Z+");
            }
            this.bN = this.getParameter(c("\u0006~\u0013o`\u0007z\u0007"));
            this.bO = this.getParameter(c("\u001bi\u0017dw\u0002o\u0007"));
            this.r = Integer.valueOf(this.bC);
            this.P = Integer.valueOf(this.bF);
            this.T = Integer.valueOf(this.bG);
            this.bc = Integer.valueOf(this.bI);
            this.U = Integer.valueOf(this.bJ);
            this.W = Integer.valueOf(this.bK);
            this.X = Integer.valueOf(this.bL);
            this.Z = Integer.valueOf(this.bM);
            this.j = Integer.valueOf(this.bN);
            this.k = Integer.valueOf(this.bO);
            anWater6 = this;
        }
        int n15;
        final int n14 = n15 = anWater6.j;
        if (cr == 0) {
            if (n14 < 0) {
                this.j = 0;
            }
            final int k;
            n15 = (k = this.k);
        }
        final int n16 = 10;
        AnWater anWater7 = null;
        Label_1721: {
            Label_1707: {
                if (cr == 0) {
                    if (n14 > n16) {
                        this.k = 10;
                        if (cr == 0) {
                            break Label_1707;
                        }
                    }
                    anWater7 = this;
                    if (cr != 0) {
                        break Label_1721;
                    }
                    n15 = this.k;
                }
                if (n15 < n16) {
                    this.k = 1;
                }
            }
            this.bH = this.getParameter(c("\rr\u0006ow\u0004k"));
            anWater7 = this;
        }
        final String bh2 = anWater7.bH;
        Label_1776: {
            AnWater anWater8 = null;
            Label_1772: {
                if (cr == 0) {
                    if (bh2 == null) {
                        this.bH = c("%T");
                    }
                    anWater8 = this;
                    if (cr != 0) {
                        break Label_1772;
                    }
                    final String bh3 = this.bH;
                }
                if (bh2.equalsIgnoreCase(c("2^-"))) {
                    this.u = true;
                    if (cr == 0) {
                        break Label_1776;
                    }
                }
                anWater8 = this;
            }
            anWater8.u = false;
        }
        this.bM = this.getParameter(c("\rr\u0006ow\u0004k&"));
        final String bm = this.bM;
        String s20 = null;
        String bm2 = null;
        String s18 = null;
        Label_1883: {
            if (cr == 0) {
                if (bm == null) {
                    this.bM = "0";
                }
                this.v = Integer.valueOf(this.bM);
                this.bM = this.getParameter(c("\rr\u0006ow\u0004k'"));
                final String s19;
                s18 = (s19 = (bm2 = (s20 = this.bM)));
                if (cr != 0) {
                    break Label_1883;
                }
            }
            if (bm == null) {
                this.bM = "0";
            }
            this.w = Integer.valueOf(this.bM);
            this.bM = this.getParameter(c("\rr\u0006ow\u0004k8"));
            bm2 = (s18 = (s20 = this.bM));
        }
        if (cr == 0) {
            if (s18 == null) {
                this.bM = "0";
            }
            this.x = Integer.valueOf(this.bM);
            this.ca = this.x - 10;
            this.bM = this.getParameter(c("\rr\u0006ow\u0004k-"));
            s20 = (bm2 = this.bM);
        }
        String parameter5 = null;
        Label_2120: {
            String bm4 = null;
            Label_2085: {
                String bm3 = null;
                Label_2037: {
                    if (cr == 0) {
                        if (bm2 == null) {
                            this.bM = "0";
                        }
                        this.y = Integer.valueOf(this.bM);
                        this.bM = this.getParameter(c("\u001bi\u001bxv\u001ei\u001b"));
                        bm3 = (s20 = this.bM);
                        if (cr != 0) {
                            break Label_2037;
                        }
                    }
                    if (s20 == null) {
                        this.bM = c("_+N");
                    }
                    this.Q = Float.valueOf(this.bM);
                    this.bM = this.getParameter(c("\u0006r\u0010gl\fs\n"));
                    bm4 = (bm3 = this.bM);
                    if (cr != 0) {
                        break Label_2085;
                    }
                }
                if (bm3 == null) {
                    this.bM = c("F)N");
                }
                this.bY = Integer.valueOf(this.bM);
                this.bM = this.getParameter(c("\u0006z\u0006gl\fs\n"));
                parameter5 = (bm4 = this.bM);
                if (cr != 0) {
                    break Label_2120;
                }
            }
            if (bm4 == null) {
                this.bM = c("Z+N");
            }
            this.bZ = Integer.valueOf(this.bM);
            parameter5 = this.getParameter(c("\u0002u\nnw\nx\nbs\u000e"));
        }
        final String s21 = parameter5;
        if (cr == 0 && s21 == null) {
            c("\u0005t");
            goto Label_2139;
        }
        Label_2167: {
            if (s21.equalsIgnoreCase(c("2^-"))) {
                this.m = true;
                if (cr == 0) {
                    break Label_2167;
                }
            }
            this.m = false;
        }
        int n19;
        int p;
        int n18;
        final int n17 = n18 = (p = (n19 = this.r));
        int n23;
        int n22;
        int n21;
        final int n20 = n21 = (n22 = (n23 = 8));
        Label_2242: {
            Label_2210: {
                if (cr == 0) {
                    if (n17 > n20) {
                        this.r = 8;
                        if (cr == 0) {
                            break Label_2210;
                        }
                    }
                    final int n24;
                    n18 = (n24 = (p = (n19 = this.r)));
                    final int n25;
                    n21 = (n25 = (n22 = (n23 = 1)));
                }
                if (cr != 0) {
                    break Label_2242;
                }
                if (n17 < n20) {
                    this.r = 1;
                }
            }
            this.p = this.n * this.r;
            this.q = this.o * this.r;
            p = (n18 = (n19 = this.P));
            n22 = (n21 = (n23 = 6));
        }
        int n28 = 0;
        int w = 0;
        int n27 = 0;
        Label_2323: {
            Label_2319: {
                int n26 = 0;
                Label_2306: {
                    Label_2289: {
                        Label_2279: {
                            if (cr == 0) {
                                if (n18 > n21) {
                                    this.P = 6;
                                    if (cr == 0) {
                                        break Label_2279;
                                    }
                                }
                                n19 = (p = this.P);
                                n23 = (n22 = 2);
                            }
                            if (cr != 0) {
                                break Label_2289;
                            }
                            if (p < n22) {
                                this.P = 2;
                            }
                        }
                        n26 = (n19 = (n27 = (w = (n28 = this.T))));
                        if (cr != 0) {
                            break Label_2306;
                        }
                        n23 = 2;
                    }
                    if (n19 > n23) {
                        this.T = 2;
                        if (cr == 0) {
                            break Label_2319;
                        }
                    }
                    n27 = (n26 = (w = (n28 = this.T)));
                }
                if (cr != 0) {
                    break Label_2323;
                }
                if (n26 < 0) {
                    this.T = 0;
                }
            }
            w = (n27 = (n28 = this.bc));
        }
        if (cr == 0) {
            if (n27 < 0) {
                this.bc = 0;
            }
            n28 = (w = this.W);
        }
        int n30 = 0;
        int u = 0;
        int n29 = 0;
        Label_2374: {
            if (cr == 0) {
                if (w < 0) {
                    this.W = 0;
                }
                n29 = (n28 = (u = (n30 = this.Z)));
                if (cr != 0) {
                    break Label_2374;
                }
            }
            if (n28 < 0) {
                this.Z = 0;
            }
            u = (n29 = (n30 = this.U));
        }
        int n32 = 0;
        int bd3 = 0;
        Label_2470: {
            Label_2466: {
                int n31 = 0;
                Label_2458: {
                    Label_2427: {
                        Label_2418: {
                            if (cr == 0) {
                                if (n29 > this.n) {
                                    this.U = this.n - 1;
                                    if (cr == 0) {
                                        break Label_2418;
                                    }
                                }
                                n30 = (u = this.U);
                            }
                            if (cr != 0) {
                                break Label_2427;
                            }
                            if (u < 0) {
                                this.U = 0;
                            }
                        }
                        n31 = (n30 = this.X);
                        if (cr != 0) {
                            break Label_2458;
                        }
                    }
                    if (n30 > this.n) {
                        this.X = this.n - 1;
                        if (cr == 0) {
                            break Label_2466;
                        }
                    }
                    bd3 = (n31 = (n32 = this.X));
                    if (cr != 0) {
                        break Label_2470;
                    }
                }
                if (n31 < 0) {
                    this.X = 0;
                }
            }
            n32 = (bd3 = (this.bd ? 1 : 0));
        }
        final int n33 = 1;
        if (cr == 0) {
            if (bd3 == n33) {
                this.Q = 10.0f;
                this.T = 0;
                this.U = 0;
                this.X = 0;
                this.ba = false;
            }
            this.showStatus(c("'t\u001fol\u0005|^bh\n|\u001b%+E"));
            this.bz = this.a(this.bB);
            this.F = (float)Math.pow(2.0, this.P);
            this.bh = this.n * this.o;
            n32 = this.n;
        }
        final int n34 = n32 + n33;
        final int n35 = this.bh - n34;
        final int n36 = this.bh - 1;
        this.D = new float[this.n * (this.o + 2) + n34 + 1];
        this.E = new float[this.n * (this.o + 2) + n34 + 1];
        this.s = new int[this.n * this.o];
        this.t = new int[this.n * this.o + 2];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.bz, 0, 0, this.n, this.o, this.s, 0, this.n);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex7) {}
        AnWater anWater9 = this;
        AnWater anWater10 = this;
        Label_2858: {
            if (cr != 0) {
                break Label_2858;
            }
            Label_2857: {
                if (!this.S) {
                    break Label_2857;
                }
                this.bi = new short[this.n * this.o + 2];
                this.bj = new short[this.n * this.o + 2];
                this.bk = new short[this.n * this.o + 2];
                this.R = 0;
                while (true) {
                    while (true) {
                        Label_2846: {
                            if (cr == 0) {
                                break Label_2846;
                            }
                            anWater10 = this;
                            final int n37 = anWater10.s[this.R];
                            this.bi[this.R] = (short)(n37 >> 16 & 0xFF);
                            this.bj[this.R] = (short)(n37 >> 8 & 0xFF);
                            this.bk[this.R] = (short)(n37 & 0xFF);
                            ++this.R;
                        }
                        if (this.R < this.bh) {
                            continue;
                        }
                        break;
                    }
                    try {
                        anWater9 = this;
                        anWater10 = this;
                        if (cr != 0) {
                            continue;
                        }
                        anWater9.b();
                    }
                    catch (NoSuchMethodError noSuchMethodError) {
                        this.b();
                    }
                    break;
                }
            }
        }
        this.scrollinitial();
        this.bP = this.createImage(this.p, this.q + this.cM);
        this.bQ = this.bP.getGraphics();
    }
    
    void b() {
        this.b = new MemoryImageSource(this.n, this.o, new DirectColorModel(24, 16711680, 65280, 255), this.t, 0, this.n);
        String s;
        try {
            s = System.getProperty(c("\u0001z\bj+\u001d~\fxl\u0004u"));
        }
        catch (SecurityException ex) {
            s = c("\u001eu\u0015");
        }
        if (!s.startsWith(c("Z5N"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.bz = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.n, this.o, new DirectColorModel(24, 16711680, 65280, 255), this.t, 0, this.n);
            this.bz = this.createImage(this.a);
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
        this.g.setPriority(this.k);
        this.showStatus("");
        System.gc();
        this.f = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.z != null && !this.bS) {
            this.bS = this.CheckAniGIF();
        }
        if (this.bV != null) {
            this.C.setCursor(12);
        }
        else {
            this.C.setCursor(0);
        }
        this.h.dr(c("\nu\u0018r"), 1, this.bU);
        while (this.g != null) {
            this.Water();
            if (this.bd) {
                if (this.be < 50) {
                    this.Q = 50.0f;
                    this.U = 1;
                    this.W = 5;
                }
                if (this.be < 100) {
                    this.Q = 100.0f;
                    this.U = 2;
                    this.W = 5;
                }
                else if (this.be < 200) {
                    this.Q = 200.0f;
                    this.U = 3;
                    this.W = 4;
                }
                else if (this.be < 300) {
                    this.Q = 300.0f;
                    this.U = 3;
                    this.W = 5;
                }
                else if (this.be < 400) {
                    this.Q = 350.0f;
                    this.U = 4;
                    this.W = 3;
                }
                else if (this.be < 500) {
                    this.Q = 380.0f;
                    this.U = 2;
                    this.W = 20;
                }
                else if (this.be < 600) {
                    this.Q = 400.0f;
                    this.U = 0;
                    this.T = 1;
                }
                else if (this.be < 700) {
                    this.T = 2;
                    this.X = 2;
                    this.Z = 20;
                }
                else if (this.be < 800) {
                    this.T = 1;
                    this.X = 4;
                    this.Z = 10;
                }
                else if (this.be < 900) {
                    this.T = 0;
                    this.X = 2;
                    this.Z = 20;
                }
                else if (this.be < 1000) {
                    this.U = 1;
                }
                else if (this.be < 1100) {
                    this.T = 1;
                    this.U = 2;
                    this.W = 30;
                    this.be = 1200;
                }
                ++this.be;
            }
            if (this.u && ++this.ca > this.x) {
                this.b(this.y);
                this.ca = 0;
            }
            if (!this.u) {
                if (this.T > 0) {
                    this.a(this.T);
                }
                if (this.U > 0 && this.V++ >= this.W) {
                    this.c(this.U);
                    this.V = 0;
                }
                if (this.X > 0 && this.Y++ >= this.Z) {
                    this.d(this.X);
                    this.Y = 0;
                }
                if (this.ba && this.bb++ >= this.bc) {
                    this.c();
                    this.bb = 0;
                }
            }
            if (++this.i == this.j) {
                System.gc();
                this.i = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.r == 1) {
                this.bQ.drawImage(this.bz, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.bQ.drawImage(this.bz, 0, 0, this.p, this.q, this);
            }
            if (this.z != null) {
                this.prepaniframe();
            }
            if (this.ck) {
                this.scrolltext(this.bQ);
            }
            graphics.drawImage(this.bP, 0, 0, this);
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
        this.prepareImage(this.bz, this.p, this.q, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.bz, this.p, this.q, this);
        }
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
    
    public final void paint(final Graphics graphics) {
        if (this.bz != null) {
            if (this.r == 1) {
                this.bQ.drawImage(this.bz, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.bQ.drawImage(this.bz, 0, 0, this.p, this.q, this);
            }
            if (this.z != null) {
                this.prepaniframe();
            }
            if (this.ck) {
                this.scrolltext(this.bQ);
            }
            graphics.drawImage(this.bP, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void Water() {
        final int[] t = this.t;
        final int[] s = this.s;
        float[] array = this.D;
        float[] array2 = this.E;
        if (this.J == 1) {
            array = this.E;
            array2 = this.D;
        }
        final short[] bi = this.bi;
        final short[] bj = this.bj;
        final short[] bk = this.bk;
        final int n = this.n;
        final int n2 = this.n + 1;
        final int n3 = this.bh - n2;
        final int n4 = this.bh - 1;
        final int n5 = this.n * 2;
        final int by = this.bY;
        final int bz = this.bZ;
        if (this.S) {
            final int n6 = n;
            for (int i = 0; i < n2; ++i) {
                final float n7 = array2[i + n6] - array2[i + n6 + 2];
                int n8 = (n * (int)((array2[i + n6] - array2[i + n5]) / 2.8f) + (int)(n7 / 13.0f) + i) % n4;
                if (n8 < 0) {
                    n8 = -n8;
                }
                int n9 = (int)(n7 / 1.7f) << 1;
                if (n9 < by) {
                    n9 = by;
                }
                else if (n9 > bz) {
                    n9 = bz;
                }
                int n10 = bi[n8] + n9;
                int n11 = bj[n8] + n9;
                int n12 = bk[n8] + n9;
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
                if (n12 > 255) {
                    n12 = 255;
                }
                else if (n12 < 0) {
                    n12 = 0;
                }
                t[i] = (n10 << 16 | n11 << 8 | n12);
                array[i] = 0.0f;
            }
            for (int j = n2; j < n4; ++j) {
                final float n13 = array2[j] - array2[j + 2];
                int n14 = (n * (int)((array2[j] - array2[j + n5]) / 8.0f) + (int)(n13 / 8.0f) + j) % n4;
                if (n14 < 0) {
                    n14 = -n14;
                }
                int n15 = (int)n13 << 1;
                if (n15 < by) {
                    n15 = by;
                }
                else if (n15 > bz) {
                    n15 = bz;
                }
                int n16 = bi[n14] + n15;
                int n17 = bj[n14] + n15;
                int n18 = bk[n14] + n15;
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
                if (n18 > 255) {
                    n18 = 255;
                }
                else if (n18 < 0) {
                    n18 = 0;
                }
                t[j] = (n16 << 16 | n17 << 8 | n18);
                array[j] = (array2[j + n] + array2[j - n] + array2[j + 1] + array2[j - 1]) / 2.0f - array[j];
                final float[] array3 = array;
                final int n19 = j;
                array3[n19] -= array[j] / this.F;
            }
        }
        else {
            final int n20 = n + 1;
            for (int k = 0; k < n2; ++k) {
                int n21 = (n * (int)((array2[k] - array2[k + n5]) / 8.0f) + (int)((array2[k + n20] - array2[k + n20 + 2]) / 8.0f) + k) % n4;
                if (n21 < 0) {
                    n21 = -n21;
                }
                t[k] = s[n21];
                array[k] = (array2[k + n] + array2[k + 1]) / 2.0f - array[k];
                final float[] array4 = array;
                final int n22 = k;
                array4[n22] -= array[k] / this.F;
            }
            for (int l = n2; l < n4; ++l) {
                int n23 = (n * (int)((array2[l] - array2[l + n5]) / 8.0f) + (int)((array2[l] - array2[l + 2]) / 8.0f) + l) % n4;
                if (n23 < 0) {
                    n23 = -n23;
                }
                t[l] = s[n23];
                array[l] = (array2[l + n] + array2[l - n] + array2[l + 1] + array2[l - 1]) / 2.0f - array[l];
                final float[] array5 = array;
                final int n24 = l;
                array5[n24] -= array[l] / this.F;
            }
        }
        t[n4] = s[n4];
        for (int n25 = n3 + n; n25 < this.bh; ++n25) {
            array[n25 + n] = 0.0f;
            array[n25] /= 8.0f;
        }
        if (!this.l) {
            for (int n26 = 0; n26 < n2; ++n26) {
                array2[n26] = 0.0f;
            }
            for (int n27 = n2; n27 < this.bh; ++n27) {
                array2[n27] = (array[n27 + n] + array[n27 - n] + array[n27 + 1] + array[n27 - 1]) / 2.0f - array2[n27];
                final float[] array6 = array2;
                final int n28 = n27;
                array6[n28] -= array2[n27] / this.F;
            }
            for (int n29 = n3 + n; n29 < this.bh; ++n29) {
                array2[n29 + n] = 0.0f;
                array2[n29] /= 8.0f;
            }
        }
        if (this.l) {
            this.J ^= 0x1;
        }
    }
    
    final void a(final int n) {
        final float q = this.Q;
        final float n2 = this.Q / 2.0f;
        float[] array;
        if (this.J == 1) {
            array = this.E;
        }
        else {
            array = this.D;
        }
        final int n3 = this.n / 2;
        final int n4 = this.o / 2;
        final double n5 = this.n / 3.0;
        final double n6 = this.o / 2.5;
        final int n7 = n3 + (int)(n5 * Math.cos(this.L));
        final int n8 = n4 + (int)(n6 * Math.sin(this.L * 1.2 + 1.0));
        this.L += 0.05;
        final int n9 = this.n * n8;
        array[n9 + n7] = q;
        final float[] array2 = array;
        final int n10 = this.n * (n8 - 1) + n7;
        array2[n10] += n2;
        final float[] array3 = array;
        final int n11 = this.n * (n8 + 1) + n7;
        array3[n11] += n2;
        final float[] array4 = array;
        final int n12 = n9 + n7 + this.n;
        array4[n12] += n2;
        final float[] array5 = array;
        final int n13 = n9 + n7 - this.n;
        array5[n13] += n2;
        if (n == 1) {
            return;
        }
        final int n14 = n3 + (int)(n5 * Math.cos(this.M * 1.3 + 1.0));
        final int n15 = n4 + (int)(n6 * Math.sin(this.M));
        final int n16 = this.n * n15;
        this.M += 0.05;
        array[n16 + n14] = q;
        final float[] array6 = array;
        final int n17 = this.n * (n15 - 1) + n14;
        array6[n17] += n2;
        final float[] array7 = array;
        final int n18 = this.n * (n15 + 1) + n14;
        array7[n18] += n2;
        final float[] array8 = array;
        final int n19 = n16 + n14 + this.n;
        array8[n19] += n2;
        final float[] array9 = array;
        final int n20 = n16 + n14 - this.n;
        array9[n20] += n2;
    }
    
    final synchronized void b(int n) {
        ++n;
        final float q = this.Q;
        float[] array;
        if (this.J == 1) {
            array = this.E;
        }
        else {
            array = this.D;
        }
        final float n2 = n - 1.5f;
        this.G = this.v;
        this.H = this.w;
        if (n == 2) {
            array[this.n * this.H + this.G] = (int)(Math.random() * q) % q;
            return;
        }
        this.bt = n * n;
        final float n3 = n2 * n2;
        final float n4 = this.bt - n3;
        this.bx = -n;
        while (this.bx < n) {
            this.by = this.bx * this.bx;
            this.bw = -n;
            while (this.bw < n) {
                final int n5 = this.bw * this.bw + this.by;
                if (n5 < this.bt) {
                    if (n5 > n3) {
                        final float n6 = (n5 - n3) / n4;
                        final float[] array2 = array;
                        final int n7 = this.n * (this.bx + this.H) + (this.bw + this.G);
                        array2[n7] += q - n6 * q;
                    }
                    else {
                        final float[] array3 = array;
                        final int n8 = this.n * (this.bx + this.H) + (this.bw + this.G);
                        array3[n8] += q;
                    }
                }
                ++this.bw;
            }
            ++this.bx;
        }
    }
    
    final void c(int n) {
        ++n;
        final float q = this.Q;
        float[] array;
        if (this.J == 1) {
            array = this.E;
        }
        else {
            array = this.D;
        }
        final float n2 = n - 1.5f;
        final int g = this.n - n - 1;
        final int h = this.o - n - 1;
        this.G = (int)(Math.random() * this.n);
        this.H = (int)(Math.random() * this.o);
        if (this.G < n) {
            this.G = n;
        }
        else if (this.G > g) {
            this.G = g;
        }
        if (this.H < n) {
            this.H = n;
        }
        else if (this.H > h) {
            this.H = h;
        }
        if (n == 2) {
            array[this.n * this.H + this.G] = (int)(Math.random() * q) % q;
            return;
        }
        this.bt = n * n;
        final float n3 = n2 * n2;
        final float n4 = this.bt - n3;
        this.bx = -n;
        while (this.bx < n) {
            this.by = this.bx * this.bx;
            this.bw = -n;
            while (this.bw < n) {
                final int n5 = this.bw * this.bw + this.by;
                if (n5 < this.bt) {
                    if (n5 > n3) {
                        final float n6 = (n5 - n3) / n4;
                        final float[] array2 = array;
                        final int n7 = this.n * (this.bx + this.H) + (this.bw + this.G);
                        array2[n7] += q - n6 * q;
                    }
                    else {
                        final float[] array3 = array;
                        final int n8 = this.n * (this.bx + this.H) + (this.bw + this.G);
                        array3[n8] += q;
                    }
                }
                ++this.bw;
            }
            ++this.bx;
        }
    }
    
    final void d(final int n) {
        final float q = this.Q;
        float[] array;
        if (this.J == 1) {
            array = this.E;
        }
        else {
            array = this.D;
        }
        final int g = this.n - n * 2 - 1;
        final int h = this.o - n * 2 - 1;
        this.G = (int)(Math.random() * this.n);
        this.H = (int)(Math.random() * this.o);
        if (this.G < 1) {
            this.G = 1;
        }
        else if (this.G > g) {
            this.G = g;
        }
        if (this.H < 1) {
            this.H = 1;
        }
        else if (this.H > h) {
            this.H = h;
        }
        final int n2 = n * 2;
        final int n3 = n2 - 1;
        for (int i = this.H + 1; i < this.H + n3; ++i) {
            for (int j = this.G + 1; j < this.G + n3; ++j) {
                final float[] array2 = array;
                final int n4 = this.n * i + j;
                array2[n4] += q;
            }
        }
        final float n5 = q / 2.0f;
        final int n6 = this.G + n3;
        for (int k = this.H; k < this.H + n2; ++k) {
            final float[] array3 = array;
            final int n7 = this.n * k + this.G;
            array3[n7] += n5;
        }
        for (int l = this.H; l < this.H + n2; ++l) {
            final float[] array4 = array;
            final int n8 = this.n * l + n6;
            array4[n8] += n5;
        }
        final int n9 = this.n * this.H;
        final int n10 = this.n * (this.H + n3);
        for (int g2 = this.G; g2 < this.G + n2; ++g2) {
            final float[] array5 = array;
            final int n11 = n9 + g2;
            array5[n11] += n5;
        }
        for (int g3 = this.G; g3 < this.G + n2; ++g3) {
            final float[] array6 = array;
            final int n12 = n10 + g3;
            array6[n12] += n5;
        }
    }
    
    final void c() {
        final float q = this.Q;
        float[] array;
        if (this.J == 1) {
            array = this.E;
        }
        else {
            array = this.D;
        }
        float n = 0.0f;
        this.bu = 6 + (int)(Math.random() * this.n) % (this.n - 12);
        this.bv = 6 + (int)(Math.random() * this.o) % (this.o - 14);
        for (int i = -2; i < 3; ++i) {
            this.H = 0;
            while (this.H < this.o) {
                if (i == 0) {
                    n = q;
                }
                else if (i == 1) {
                    n = q / 2.0f;
                }
                else if (i == 2) {
                    n = q / 4.0f;
                }
                else if (i == -1) {
                    n = q / 2.0f;
                }
                else if (i == -2) {
                    n = q / 4.0f;
                }
                final float[] array2 = array;
                final int n2 = this.n * this.H + this.bu + i;
                array2[n2] += n;
                ++this.H;
            }
        }
        for (int j = -2; j < 3; ++j) {
            this.G = 0;
            while (this.G < this.n) {
                if (j == 0) {
                    n = q;
                }
                else if (j == 1) {
                    n = q / 2.0f;
                }
                else if (j == 2) {
                    n = q / 4.0f;
                }
                else if (j == -1) {
                    n = q / 2.0f;
                }
                else if (j == -2) {
                    n = q / 4.0f;
                }
                final float[] array3 = array;
                final int n3 = this.n * (this.bv + j) + this.G;
                array3[n3] += n;
                ++this.G;
            }
        }
    }
    
    public void scrollinitial() {
        this.ck = false;
        final String parameter = this.getParameter(c("\u001f~\u0006\u007fv\bi\u0011gi"));
        if (parameter != null && !parameter.equalsIgnoreCase(c("%T"))) {
            String s = this.getParameter(c("\u001f~\u0006\u007fq\u0012k\u001b"));
            if (s == null) {
                s = c("\u0003t\fb\u007f\u0004u\nji");
            }
            if (s.equals(c("\u0003t\fb\u007f\u0004u\nji"))) {
                this.cw = 0;
            }
            else if (s.equals(c("\u001d~\f\u007fl\bz\u0012"))) {
                this.cw = 1;
            }
            else if (s.equals(c("\u0011t\u0011fl\u0005|"))) {
                this.cw = 2;
            }
            else if (s.equals(c("\u0002u\bqj\u0004v\u0017eb"))) {
                this.cw = 3;
            }
            if (this.cw == 0) {
                this.GetTheString(parameter, 0);
                if (this.ci != null) {
                    this.ck = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.cv != null) {
                    this.ck = true;
                }
            }
        }
        if (this.ck) {
            String parameter2 = this.getParameter(c("\u001f~\u0006\u007fv\u001b~\u001bo"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.cj = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("\u001f~\u0006\u007fc\u0004u\n"));
            if (s2 == null) {
                s2 = c("*i\u0017ji");
            }
            int n = 0;
            if (this.getParameter(c("\u001f~\u0006\u007fg\u0004w\u001a")).equalsIgnoreCase(c("2^-"))) {
                ++n;
            }
            String s3 = this.getParameter(c("\u001f~\u0006\u007fl\u001fz\u0012bf"));
            if (s3 == null) {
                s3 = c("%T");
            }
            if (s3.equalsIgnoreCase(c("2^-"))) {
                n += 2;
            }
            String s4 = this.getParameter(c("\u001f~\u0006\u007fv\u0002a\u001b"));
            if (s4 == null) {
                s4 = c("Z)");
            }
            this.co = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(c("\u001f~\u0006\u007fv\u0003z\u001adr")).equalsIgnoreCase(c("2^-"))) {
                this.cl = true;
            }
            else {
                this.cl = false;
            }
            this.cm = new Color(Integer.valueOf(this.getParameter(c("?~\u0006\u007fF\u0004w,"))), Integer.valueOf(this.getParameter(c("?~\u0006\u007fF\u0004w9"))), Integer.valueOf(this.getParameter(c("?~\u0006\u007fF\u0004w<"))));
            this.cn = new Color(Integer.valueOf(this.getParameter(c("?~\u0006\u007fV(t\u0012Y"))), Integer.valueOf(this.getParameter(c("?~\u0006\u007fV(t\u0012L"))), Integer.valueOf(this.getParameter(c("?~\u0006\u007fV(t\u0012I"))));
            this.cb = this.size().width;
            this.cc = this.size().height;
            if (this.cw == 0) {
                String parameter3 = this.getParameter(c("\u001f~\u0006\u007fj\r}\rnq"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.ce = Integer.valueOf(parameter3);
                if (this.ce < 0) {
                    this.ce = 0;
                }
                String parameter4 = this.getParameter(c("?~\u0006\u007fO\u001ev\u000eJh\u001b"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.cB = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("?~\u0006\u007fO\u001ev\u000eXu\u000f"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.cE = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("?~\u0006\u007fV\u0002u\u001bJh\u001b"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.cp = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c("?~\u0006\u007fV\u0002u\u001bXu\u000f"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.cq = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c("?~\u0006\u007fV\u0002u\u001bJk\fw\u001b"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.cr = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.co);
                this.cf = fontMetrics.stringWidth(this.ci);
                this.cg = fontMetrics.getHeight();
                this.ch = fontMetrics.getMaxDescent();
                this.cd = this.cb;
                if (this.ce < this.cg - this.ch) {
                    this.ce = this.cg - this.ch;
                }
                else if (this.ce > this.cc - this.ch) {
                    this.ce = this.cc - this.ch;
                }
                if (this.cp != 0) {
                    this.ct = new int[this.cb + 360];
                    this.cu = new int[this.cb + 360];
                    for (int i = 0; i < this.cb + 360; ++i) {
                        this.ct[i] = (int)(this.cp * Math.sin(this.cr * i * 3.141592653589793 / 180.0)) - this.cg - this.ch + this.ce;
                        this.cu[i] = this.ct[i] - this.q;
                    }
                    this.cs = 360;
                    this.cM = this.cg + this.ch + 1;
                    this.cN = this.cM - 1;
                }
            }
            else {
                if (this.cw == 1) {
                    String s5 = this.getParameter(c("\u001f~\u0006\u007fs\u0018k\u001fh`"));
                    if (s5 == null) {
                        s5 = c("Z+");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.co);
                    this.cG = fontMetrics2.getHeight() + intValue;
                    this.cH = new int[this.cv.length];
                    this.R = 0;
                    while (this.R < this.cv.length) {
                        this.cH[this.R] = (this.cb - fontMetrics2.stringWidth(this.cv[this.R])) / 2;
                        ++this.R;
                    }
                    this.cF = -this.cG;
                    return;
                }
                if (this.cw >= 2) {
                    String parameter9 = this.getParameter(c("\u001f~\u0006\u007fh\u0002u\u0018dk\u001f"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.cz = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(c("\u001f~\u0006\u007fh\nc\u0018dk\u001f"));
                    if (s6 == null) {
                        s6 = c("\\)");
                    }
                    this.cA = Integer.valueOf(s6);
                    this.cx = this.cA - this.cz;
                    this.co = null;
                    this.cy = new Font[this.cx];
                    int cz = this.cz;
                    this.R = 0;
                    while (this.R < this.cx) {
                        this.cy[this.R] = new Font(s2, n, cz++);
                        ++this.R;
                    }
                    this.cK = this.cb / 2.0f;
                    this.cL = this.cc / 2.0f;
                    if (this.cw == 3) {
                        this.cI = this.cx - 1;
                        return;
                    }
                    this.cI = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.cw) {
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
        graphics.setFont(this.co);
        this.cF += this.cj;
        if (this.cF > this.cc + this.cv.length * this.cG) {
            this.cF = -this.cG;
        }
        if (this.cl) {
            for (int i = 0; i < this.cv.length; ++i) {
                final String s = this.cv[i];
                final int n = this.cH[i];
                final int n2 = this.cc - this.cF + i * this.cG;
                graphics.setColor(this.cn);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.cm);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.cm);
        for (int j = 0; j < this.cv.length; ++j) {
            graphics.drawString(this.cv[j], this.cH[j], this.cc - this.cF + j * this.cG);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.cv[this.cJ];
        graphics.setFont(this.cy[this.cI]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.cy[this.cI]);
        final int n = (int)(this.cK - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.cL + fontMetrics.getHeight() / 4.0f);
        if (this.cl) {
            graphics.setColor(this.cn);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.cm);
        graphics.drawString(s, n, n2);
        if (this.cw == 3) {
            this.cI -= this.cj;
            if (this.cI <= 1) {
                this.cI = this.cx - 1;
                ++this.cJ;
                if (this.cJ >= this.cv.length) {
                    this.cJ = 0;
                }
            }
        }
        else {
            this.cI += this.cj;
            if (this.cI >= this.cx) {
                this.cI = 0;
                ++this.cJ;
                if (this.cJ >= this.cv.length) {
                    this.cJ = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.co);
        if (this.cB == 0) {
            this.cC = this.ce;
        }
        else {
            this.cD += this.cE;
            this.cC = this.ce - (int)Math.abs(this.cB * Math.sin(this.cD / 90.0 * 3.141592653589793));
        }
        if (this.cp != 0) {
            for (int i = 0; i < this.cb; ++i) {
                final int n = this.ct[this.cs + i];
                graphics.copyArea(i, n, 1, this.cM, 0, this.q - n);
            }
            if (this.cl) {
                graphics.setColor(this.cn);
                graphics.drawString(this.ci, this.cd + 1, this.q + this.cg + 1);
            }
            graphics.setColor(this.cm);
            graphics.drawString(this.ci, this.cd, this.q + this.cg);
            for (int j = 0; j < this.cb; ++j) {
                graphics.copyArea(j, this.q, 1, this.cN, 0, this.cu[this.cs + j]);
            }
            this.cs -= this.cq;
            if (this.cs < 0) {
                this.cs += 360;
            }
        }
        else {
            if (this.cl) {
                graphics.setColor(this.cn);
                graphics.drawString(this.ci, this.cd + 1, this.cC + 1);
            }
            graphics.setColor(this.cm);
            graphics.drawString(this.ci, this.cd, this.cC);
        }
        this.cd -= this.cj;
        if (this.cd < -this.cf) {
            this.cd = this.cb;
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
                            this.ci = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.ci = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.cv = new String[n3 - 1];
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
                                this.cv[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.cv[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.cv = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.cO);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final synchronized boolean mouseDown(final Event event, int v, int w) {
        final int cr = AnWater.cR;
        final boolean m = this.m;
        final boolean b = true;
        Label_0216: {
            if (cr == 0) {
                if (m == b) {
                    int n3;
                    int n2;
                    final int n = n2 = (n3 = v);
                    int n6;
                    int n5;
                    final int n4 = n5 = (n6 = 6);
                    Label_0064: {
                        Label_0061: {
                            if (cr == 0) {
                                if (n < n4) {
                                    v = 6;
                                    if (cr == 0) {
                                        break Label_0061;
                                    }
                                }
                                final int n7;
                                n2 = (n7 = (n3 = v));
                                final int n8;
                                n5 = (n8 = (n6 = this.n - 6));
                            }
                            if (cr != 0) {
                                break Label_0064;
                            }
                            if (n > n4) {
                                v = this.n - 6;
                            }
                        }
                        n3 = (n2 = w);
                        n6 = (n5 = 6);
                    }
                    int v2 = 0;
                    Label_0108: {
                        Label_0104: {
                            if (cr == 0) {
                                if (n2 < n5) {
                                    w = 6;
                                    if (cr == 0) {
                                        break Label_0104;
                                    }
                                }
                                v2 = (n3 = w);
                                if (cr != 0) {
                                    break Label_0108;
                                }
                                n6 = this.o - 6;
                            }
                            if (n3 > n6) {
                                w = this.o - 6;
                            }
                        }
                        v2 = this.v;
                    }
                    final int v3 = v2;
                    final int w2 = this.w;
                    final float q = this.Q;
                    this.v = v;
                    this.w = w;
                    this.Q = 400.0f;
                    this.b(3);
                    this.v = v3;
                    this.w = w2;
                    this.Q = q;
                }
                final AnWater anWater = this;
                final AnWater anWater2 = this;
                if (cr != 0) {
                    break Label_0216;
                }
                final boolean bu = this.bU;
            }
            if (m == b) {
                this.h.show();
                try {
                    this.h.move(100, 100);
                }
                catch (Exception ex) {}
                this.h.toFront();
                this.h.requestFocus();
                if (cr == 0) {
                    return true;
                }
            }
            try {
                AnWater anWater = this;
                final AnWater anWater2 = this;
                Label_0240: {
                    if (cr != 0) {
                        break Label_0240;
                    }
                    if (anWater2.bV == null) {
                        return true;
                    }
                    try {
                        this.h.dck();
                        AnWater anWater3 = this;
                        anWater = this;
                        if (cr == 0) {
                            if (anWater.bW) {
                                this.getAppletContext().showDocument(this.bV, this.getParameter(c("\u0019~\u0019mw\nv\u001bed\u0006~")));
                                if (cr == 0) {
                                    return true;
                                }
                            }
                            anWater3 = this;
                        }
                        anWater3.getAppletContext().showDocument(this.bV);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public synchronized boolean mouseMove(final Event event, int v, int w) {
        if (this.m) {
            if (v < 6) {
                v = 6;
            }
            else if (v > this.n - 6) {
                v = this.n - 6;
            }
            if (w < 6) {
                w = 6;
            }
            else if (w > this.o - 6) {
                w = this.o - 6;
            }
            final int v2 = this.v;
            final int w2 = this.w;
            final float q = this.Q;
            this.v = v;
            this.w = w;
            this.Q = 90.0f;
            this.b(2);
            this.v = v2;
            this.w = w2;
            this.Q = q;
        }
        return true;
    }
    
    public AnWater() {
        this.c = false;
        this.r = 1;
        this.M = -0.6;
        this.bd = false;
        this.bR = false;
        this.bS = false;
        this.bU = false;
        this.bW = false;
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
                    c2 = 'k';
                    break;
                }
                case 1: {
                    c2 = '\u001b';
                    break;
                }
                case 2: {
                    c2 = '~';
                    break;
                }
                case 3: {
                    c2 = '\u000b';
                    break;
                }
                default: {
                    c2 = '\u0005';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
