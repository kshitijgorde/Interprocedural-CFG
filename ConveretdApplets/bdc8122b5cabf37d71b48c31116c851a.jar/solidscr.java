import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Event;
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
import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.net.URL;
import java.awt.image.IndexColorModel;
import java.awt.Color;
import java.awt.Image;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class solidscr extends Applet implements Runnable, ImageObserver
{
    private int a;
    Frame b;
    boolean c;
    final String d = ":\u001cN1]\u000fL\\$\u0018=\r\\4W[/W([\u0018\u0005\u001euO\f\u001b\u0010<V\u001d";
    private Image e;
    private Color f;
    int g;
    private int h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    long x;
    static int y;
    private int z;
    private int A;
    private int B;
    private int C;
    private int D;
    private String E;
    private IndexColorModel F;
    private int G;
    private int H;
    private int I;
    private int J;
    private int K;
    static int L;
    URL M;
    private float N;
    private float O;
    int P;
    private float Q;
    private float R;
    int S;
    private int T;
    private int U;
    private int V;
    boolean W;
    private Graphics X;
    private Image Y;
    boolean Z;
    private int ba;
    private Image bb;
    int bc;
    int bd;
    private int be;
    private PixelGrabber bf;
    protected int[] bg;
    int bh;
    int bi;
    int bj;
    boolean bk;
    String bl;
    private int bm;
    public Image bn;
    private String bo;
    private float[] bp;
    private float[] bq;
    private int br;
    private int bs;
    protected Image bt;
    private String bu;
    private String bv;
    private String bw;
    private Image bx;
    private String by;
    private String bz;
    private String bA;
    private String bB;
    private int bC;
    private String bD;
    private String bE;
    private String bF;
    private String bG;
    private String bH;
    private String bI;
    private String bJ;
    private String bK;
    private String bL;
    private String bM;
    private String bN;
    private String bO;
    Toolkit bP;
    Thread bQ;
    private int[] bR;
    private int bS;
    private Image[] bT;
    private int bU;
    private int bV;
    private int bW;
    private int bX;
    private int bY;
    private int bZ;
    Lware ca;
    private int cb;
    private int cc;
    private int cd;
    private int ce;
    private int cf;
    private int cg;
    
    static {
        solidscr.y = 1;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("?\u0003PzL[\u001e[0W\r\t\u001e*O\fB_3^\u0002\u0018[<UU\u000fQ0\u0018\u0018\u001e[9Q\u000f\u001f\u001e1Q\u0015\t\u001e4V[$j\u0010tZ"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public synchronized boolean b() {
        this.prepareImage(this.bb, this);
        for (int i = 0; i < 3; ++i) {
            this.notifyAll();
            Thread.yield();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        return this.Z;
    }
    
    public void destroy() {
        if (this.bb != null) {
            this.bb.flush();
        }
        this.bb = null;
        if (this.Y != null) {
            this.Y.flush();
        }
        this.Y = null;
        if (this.X != null) {
            this.X.dispose();
        }
        this.X = null;
        System.gc();
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
                this.showStatus(c("2\u0001_:][") + s + c("[\u0002Q)\u0018\u001d\u0003K3\\Z"));
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
    
    public Image c() {
        return this.bt;
    }
    
    public float d() {
        float n = this.bp[0];
        for (int i = 0; i < this.bS + this.bV - 1; ++i) {
            if (this.bp[i] > n) {
                n = this.bp[i];
            }
        }
        return n;
    }
    
    public float e() {
        float n = this.bp[0];
        for (int i = 0; i < this.bS + this.bV - 1; ++i) {
            if (this.bp[i] < n) {
                n = this.bp[i];
            }
        }
        return n;
    }
    
    public int f() {
        return this.bR[this.h];
    }
    
    public int[] g() {
        return this.bg;
    }
    
    public int h() {
        return this.A;
    }
    
    public int i() {
        return this.bo.length() * this.C;
    }
    
    public float[] j() {
        return this.bp;
    }
    
    public String a(final String s, final int n) {
        String b = "";
        try {
            b = this.b(s, n);
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.b(s, n);
        }
        return b;
    }
    
    public String b(final String s, final int n) {
        String s2 = "";
        try {
            final URL url = new URL(this.getDocumentBase(), s);
            try {
                final DataInputStream dataInputStream = new DataInputStream(url.openStream());
                if (dataInputStream != null) {
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
                        int n3 = 0;
                        if (n == 0) {
                            for (int i = 0; i < n2; ++i) {
                                final byte b2 = byteArray[i];
                                if (b2 != 13) {
                                    if (b2 == 10) {
                                        byteArray[n3] = 32;
                                    }
                                    else {
                                        byteArray[n3] = byteArray[i];
                                    }
                                    ++n3;
                                }
                            }
                            s2 = new String(byteArray, 0, 0, n3);
                        }
                        else {
                            for (int j = 0; j < n2; ++j) {
                                final byte b3 = byteArray[j];
                                if (b3 != 13) {
                                    if (b3 == 10) {
                                        byteArray[n3] = 30;
                                    }
                                    else {
                                        byteArray[n3] = byteArray[j];
                                    }
                                    ++n3;
                                }
                            }
                            s2 = new String(byteArray, 0, 0, n3);
                        }
                    }
                    catch (IOException ex) {}
                    return s2;
                }
                return s2;
            }
            catch (IOException ex2) {}
        }
        catch (MalformedURLException ex3) {}
        return s2;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bb) {
            if (n == 16) {
                this.Z = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bP = this.getToolkit();
        this.bl = this.getParameter(c("\b\u0018_)M\b\u0001M:"));
        final String parameter = this.getParameter(c("\u0018\u001e[9Q\u000f\u001f"));
        if (parameter != null) {
            if (!parameter.startsWith(c(":\u001cN1]\u000fL\\$\u0018=\r\\4W[/W([\u0018\u0005\u001euO\f\u001b\u0010<V\u001d"))) {
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
            s = c("\u001d\u0005R8");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("\u001d\u0005R8")) || s2.length() == 0 || s2.equalsIgnoreCase(c("\u0017\u0003]<T\u0013\u0003M)")) || s2.equals(c("J^\ts\bU\\\u0010l"))) {
            this.bk = true;
        }
        else {
            if (s2.startsWith(c("\f\u001bIs"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("\t\tY>W\u001f\t"));
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
                        if (s5.startsWith(c("\f\u001bIs"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bk = true;
                        }
                    }
                }
            }
        }
        final String parameter3 = this.getParameter(c("\t\tY1Q\u0015\u0007"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            try {
                this.M = new URL(this.getDocumentBase(), parameter3);
            }
            catch (MalformedURLException ex6) {
                this.M = null;
            }
        }
        if (this.getParameter(c("\t\tY3]\f\nL<U\u001e")).equalsIgnoreCase(c("\")m"))) {
            this.W = true;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.b = (Frame)container).setCursor(3);
        final String parameter4 = this.getParameter(c("\u0014\u001a[/Q\u0016\u000b"));
        if (parameter4 != null && !parameter4.equalsIgnoreCase("NO")) {
            this.bb = this.a(parameter4);
            if (this.bb != null) {
                String parameter5 = this.getParameter(c("\u0014\u001a[/Q\u0016\u000bf"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bc = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c("\u0014\u001a[/Q\u0016\u000bg"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.bd = Integer.valueOf(parameter6);
            }
        }
        final String parameter7 = this.getParameter(c("\u0016\tS9]\u0017\rG"));
        final String parameter8 = this.getParameter(c("\u000b\u001eW2J\u0012\u0018G"));
        this.P = Integer.valueOf(parameter7);
        this.bh = Integer.valueOf(parameter8);
        this.S = Integer.valueOf(this.getParameter(c("6\u0005P\u000ea5/")));
        if (this.P < 0) {
            this.P = 0;
        }
        if (this.bh > 10) {
            this.bh = 10;
        }
        else if (this.bh < 1) {
            this.bh = 1;
        }
        this.bK = this.getParameter(c("\u000f\tF)L\u0002\u001c["));
        if (this.bK == null) {
            this.bK = this.t;
        }
        this.bK = this.bK.toUpperCase();
        this.bN = this.getParameter(c("\f\rH8U\u0014\b["));
        if (this.bN == null) {
            this.bN = this.n;
        }
        this.bN = this.bN.toUpperCase();
        this.bG = this.getParameter(c("\u000f\tF)K\u0018\u001eQ1T"));
        if (this.bG == null) {
            this.bF = this.p;
        }
        else if (this.bK.equals(c("-)l\tq8-r"))) {
            this.bF = this.a(this.bG, 1);
        }
        else {
            this.bF = this.a(this.bG, 0);
        }
        this.bF = this.bF.toUpperCase();
        this.bH = this.getParameter(c("/\tF)r\u000e\u0001N\u001cU\u000b"));
        if (this.bH == null) {
            this.bH = this.q;
        }
        this.I = Integer.parseInt(this.bH);
        this.bI = this.getParameter(c("/\tF)r\u000e\u0001N\u000eH\u001f"));
        if (this.bI == null) {
            this.bI = this.r;
        }
        this.K = Integer.parseInt(this.bI);
        this.bJ = this.getParameter(c("\u000f\tF)W\u001d\nM8L"));
        if (this.bJ == null) {
            this.bJ = this.s;
        }
        this.cd = Integer.parseInt(this.bJ);
        this.bL = this.getParameter(c("\u000f\tF)N\b\u001c_>]"));
        if (this.bL == null) {
            this.bL = this.u;
        }
        this.br = Integer.parseInt(this.bL);
        this.bB = this.getParameter(c("\u0016\u0005L/W\t\u0001Q9]"));
        if (this.bB == null) {
            this.bB = this.m;
        }
        this.bB = this.bB.toUpperCase();
        this.bA = this.getParameter(c("\u0016\u0005L/W\t\u0004[4_\u0013\u0018"));
        if (this.bA == null) {
            this.bA = this.l;
        }
        this.T = Math.abs(Integer.parseInt(this.bA));
        this.bv = this.getParameter(c("\u001d\u0003P)Q\u0015\b[%"));
        if (this.bv == null) {
            this.bv = this.j;
        }
        this.bz = this.a(this.bv, 1);
        if (this.bz != null) {
            final int n14 = 0;
            final int index = this.bz.indexOf(30, n14);
            this.bw = this.bz.substring(n14, index);
            this.bw = String.valueOf(this.bw) + c("U\u000bW;");
            final int n15 = index + 1;
            final int index2 = this.bz.indexOf(30, n15);
            this.B = Math.abs(Integer.parseInt(this.bz.substring(n15, index2)));
            final int n16 = index2 + 1;
            final int index3 = this.bz.indexOf(30, n16);
            this.z = Math.abs(Integer.parseInt(this.bz.substring(n16, index3)));
            final int n17 = index3 + 1;
            final int index4 = this.bz.indexOf(30, n17);
            this.cg = Math.abs(Integer.parseInt(this.bz.substring(n17, index4)));
            final int n18 = index4 + 1;
            int n19 = this.bz.indexOf(30, n18);
            if (n19 == -1) {
                n19 = this.bz.length();
            }
            this.bv = this.bz.substring(n18, n19);
        }
        this.bE = this.getParameter(c("\b\u001c[8\\"));
        if (this.bE == null) {
            this.bE = this.o;
        }
        this.bm = Math.abs(Integer.parseInt(this.bE));
        this.bM = this.getParameter(c("\f\rH8L\u0002\u001c["));
        if (this.bM == null) {
            this.bM = this.v;
        }
        this.bM = this.bM.toUpperCase();
        this.bO = this.getParameter(c("\f\rH8K\u000b\t[9"));
        if (this.bO == null) {
            this.bO = this.w;
        }
        this.bW = Math.abs(Integer.parseInt(this.bO));
        this.bu = this.getParameter(c("\u0019\u000b]2T\u0014\u001e"));
        if (this.bu == null) {
            this.bu = this.i;
        }
        this.f = new Color(Integer.parseInt(this.bu, 16));
        this.bD = this.getParameter(c("\t\tH8J\b\tX%"));
        if (this.bD == null) {
            this.bD = this.k;
        }
        this.bD = this.bD.toUpperCase();
        if (this.bD.equals(c("\")m"))) {
            this.D = solidscr.y;
        }
        else {
            this.D = solidscr.L;
        }
        if (this.bK.equals(c(",-h\u0014v<"))) {
            if (this.bN.equals(c("3#l\u0014b4\"j\u001ct"))) {
                this.cb = this.size().width + 2 * this.B;
                this.ba = 2 * this.B;
            }
            else {
                this.cb = this.size().width;
                this.ba = 0;
            }
        }
        else {
            this.cb = this.size().width;
            this.ba = 0;
        }
        if (this.bB.equals(c("?%m\u001cz7)z"))) {
            this.T = 0;
        }
        this.bZ = this.size().height + this.z;
        this.cc = 0;
        this.ce = this.bZ - this.bm;
        if (this.cd < 0) {
            this.cd = 0;
        }
        if (this.cd > this.bZ - 2 * this.z - this.T) {
            this.cd = this.bZ - 2 * this.z - this.T;
        }
        this.V = Math.round(this.cb / this.B) + 1;
        for (int n20 = 0; n20 < this.V; ++n20) {
            this.E = String.valueOf(this.E) + " ";
        }
        if (this.bK.equals(c(",-h\u0014v<"))) {
            if (this.bN.equals(c("3#l\u0014b4\"j\u001ct"))) {
                this.bs = 180;
                this.bY = this.z;
                this.a = this.B;
            }
            else {
                this.bs = this.cb;
                this.bY = this.cb;
                this.a = (this.bZ - this.z - this.T) / 2 - this.z / 2;
            }
            if (this.bM.equals(c("(;w\u0013\u007f"))) {
                final int[] array8 = { this.bs };
                final int a = this.a;
                this.a(array8, this.bY, -a, a, this.bW, this.D);
                this.bq = this.j();
            }
            else if (this.bM.equals(c("($q\u000fl"))) {
                final int[] array9 = { this.bs, this.bs / 2, this.bs / 3 };
                final int a2 = this.a;
                this.a(array9, this.bY, -a2, a2, this.bW, this.D);
                this.bq = this.j();
            }
            else if (this.bM.equals(c("7#p\u001a"))) {
                final int[] array10 = { 2 * this.bs, this.bs };
                final int a3 = this.a;
                this.a(array10, this.bY, -a3, a3, this.bW, this.D);
                this.bq = this.j();
            }
            else if (this.bM.equals(c("8 \u007f\u000ek2/"))) {
                final int[] array11 = { this.bs / 2 };
                final int a4 = this.a;
                this.a(array11, this.bY, -a4, a4, this.bW, this.D);
                this.bq = this.j();
            }
            else if (this.bM.equals(c("(!q\u0012l3"))) {
                final int[] array12 = { 4 * this.bs, this.bs / 2 };
                final int a5 = this.a;
                this.a(array12, this.bY, -a5, a5, this.bW, this.D);
                this.bq = this.j();
            }
            else {
                final int[] array13 = { 2 * this.bs, this.bs / 4 };
                final int a6 = this.a;
                this.a(array13, this.bY, -a6, a6, this.bW, this.D);
                this.bq = this.j();
            }
        }
        this.Y = this.createImage(this.cb, this.bZ);
        this.X = this.Y.getGraphics();
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.bx = this.getImage(this.getDocumentBase(), this.bw), 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (Exception ex7) {}
        mediaTracker.isErrorAny();
        if (this.bK.equals(c("-)l\tq8-r"))) {
            int n21 = 0;
            int n22 = 1;
            while (n22 == 1) {
                final int index5 = this.bF.indexOf(30, n21);
                n21 = index5 + 1;
                if (index5 == -1) {
                    n22 = 0;
                }
                else {
                    ++this.U;
                }
            }
            this.bT = new Image[++this.U];
            int n23 = 0;
            for (int n24 = 0; n24 < this.U; ++n24) {
                int n25 = this.bF.indexOf(30, n23);
                if (n25 == -1) {
                    n25 = this.bF.length();
                }
                if (n23 == n25) {
                    ++n23;
                    this.bT[n24] = this.b(this.bv, this.bx, this.B, this.z, this.cg, " ", this.f);
                }
                else {
                    this.bT[n24] = this.b(this.bv, this.bx, this.B, this.z, this.cg, this.bF.substring(n23, n25), this.f);
                    n23 = n25 + 1;
                }
            }
        }
        else {
            this.a(this.bv, this.bx, this.B, this.z, this.cg, String.valueOf(this.E) + this.bF + this.E, this.f);
            this.e = this.c();
            this.bC = this.i();
        }
        if (!this.bk) {
            (this.ca = new Lware(this.getAppletContext(), new Label(c("(\u0003R4\\[?]/W\u0017\u0000[/\u0018\u0019\u0015\u001e\u001bY\u0019\u0005Q}{\u0012\u0019]>Q[]\u0007d\u0001U")))).setTitle(c("(\u0003R4\\[?]/W\u0017\u0000[/\u0018\u0019\u0015\u001e\u001bY\u0019\u0005Q}{\u0012\u0019]>Q"));
            this.ca.hide();
        }
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bk) {
            this.ca.show();
            this.ca.toFront();
            this.ca.requestFocus();
        }
        else if (this.M != null) {
            if (this.W) {
                this.getAppletContext().showDocument(this.M, this.getParameter(c("\t\tY;J\u001a\u0001[3Y\u0016\t")));
            }
            else {
                this.getAppletContext().showDocument(this.M);
            }
        }
        return true;
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bl);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.Y, 0, 0, this);
    }
    
    public synchronized void k() {
        if (this.c) {
            this.notifyAll();
            while (!this.Z) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.Z = false;
        }
        this.X.drawImage(this.bb, this.bc, this.bd, this);
    }
    
    public void l() {
        if (this.h < this.be - this.bU) {
            this.h += this.bU;
            return;
        }
        this.h = 0;
    }
    
    public void run() {
        this.bQ.setPriority(this.bh);
        this.showStatus("");
        System.gc();
        this.x = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.bb != null && !this.c) {
            this.c = this.b();
        }
        if (this.M != null) {
            this.b.setCursor(12);
        }
        else {
            this.b.setCursor(0);
        }
        while (this.bQ != null) {
            this.m();
            if (++this.g == this.P) {
                System.gc();
                this.g = 0;
            }
            if (this.bb != null) {
                this.k();
            }
            graphics.drawImage(this.Y, 0, 0, this);
            this.n();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    public void m() {
        if (this.bK.equals(c("-)l\tq8-r"))) {
            this.X.setColor(this.f);
            this.X.fillRect(0, 0, this.cb, this.bZ - this.z);
            for (int n = 0; n < this.U && this.ce + n * (this.z + this.br) <= this.bZ - this.z; ++n) {
                this.X.drawImage(this.bT[n], this.cb / 2 - this.bT[n].getWidth(this) / 2, this.ce + n * (this.z + this.br), this.bT[n].getWidth(this), this.z, this);
            }
            if (this.ce > -(this.U * (this.z + this.br))) {
                this.ce -= this.bm;
            }
            else {
                final int n2 = this.bZ - this.bm;
                this.ce = n2;
                this.ce = n2;
            }
        }
        else if (this.bK.equals(c("3#l\u0014b4\"j\u001ct"))) {
            if (this.z != this.bZ - this.z) {
                this.X.setColor(this.f);
                this.X.fillRect(0, 0, this.cb, this.bZ - this.z);
            }
            this.X.drawImage(this.e, this.cc, this.cd, this.bC, this.z, this);
        }
        else if (this.bK.equals(c("19s\rq5+"))) {
            this.X.setColor(this.f);
            this.X.fillRect(0, 0, this.cb, this.bZ - this.z);
            int cd;
            if (this.I == 0) {
                cd = this.cd;
            }
            else {
                this.J += this.K;
                cd = this.cd - (int)Math.abs(this.I * Math.sin(this.J / 90.0 * 3.141592653589793));
            }
            this.X.drawImage(this.e, this.cc, cd, this.bC, this.z, this);
        }
        else {
            this.X.drawImage(this.e, this.cc, this.bZ - this.z, this.bC, this.z, this);
            if (this.bN.equals(c("3#l\u0014b4\"j\u001ct"))) {
                if (this.z != this.bZ - this.z) {
                    this.X.setColor(this.f);
                    this.X.fillRect(0, 0, this.cb, this.bZ - this.z);
                }
                for (int i = 0; i < this.z; ++i) {
                    this.X.copyArea(0, this.bZ - this.z + i, this.cb, 1, -this.B + Math.round(this.bq[i + this.f()]), -(this.bZ - this.z) + this.cd);
                }
            }
            else {
                this.X.setColor(this.f);
                this.X.fillRect(0, 0, this.cb, this.bZ - this.z);
                for (int j = 0; j < this.cb; ++j) {
                    this.X.copyArea(j, this.bZ - this.z, 1, this.z, 0, -((this.bZ - this.z + this.T) / 2) - this.z / 2 + Math.round(this.bq[j + this.f()]));
                }
            }
            this.l();
        }
        if (this.bB.equals(c("5#l\u0010y7"))) {
            for (int k = 0; k <= this.bZ - this.z - this.T; ++k) {
                final int n3 = k;
                final int n4 = n3 + k + 1;
                if (k >= this.T || this.bZ - this.z - this.T - n3 < 0) {
                    break;
                }
                this.X.copyArea(0, this.bZ - this.z - this.T - n3 - 1, this.cb - this.ba, 1, 0, n4);
            }
        }
        else {
            for (int round = 0, n5 = 0; n5 < this.T && this.bZ - this.z - this.T - round >= 0; ++n5) {
                if (this.T - 1 > 30) {
                    round = (int)Math.round((this.bZ - this.z - this.T - 1) * Math.pow(n5, 0.65) / Math.pow(this.T - 1, 0.65));
                }
                else {
                    round = Math.round((this.bZ - this.z - this.T - 1) * n5 / (this.T - 1));
                }
                this.X.copyArea(0, this.bZ - this.z - this.T - round - 1, this.cb - this.ba, 1, 0, round + n5 + 1);
            }
        }
        if (this.cc > -this.bC + this.cb + this.bm) {
            this.cc -= this.bm;
            return;
        }
        this.cc = 0;
    }
    
    public solidscr() {
        this.Z = false;
        this.c = false;
        this.bk = false;
        this.W = false;
        this.m = c("?%m\u001cz7)z");
        this.l = "0";
        this.u = "10";
        this.s = "0";
        this.r = "0";
        this.q = "0";
        this.t = c("\f\rH4V\u001c");
        this.n = c("\r\tL)Q\u0018\rR");
        this.p = c("5#\u001e\t}#8\u001e\ry)-s\u0018l>>\u001e|\u0018[L");
        this.o = "3";
        this.v = c("\b\u001bW3_");
        this.w = "5";
        this.k = c("\")m");
        this.j = c("\u0019\rLo^\u0014\u0002JsY\u001d\u0018");
        this.i = c("K\u0014\u000em\bK\\\u000e");
        this.E = " ";
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
    
    public void a(final String s, final Image bx, final int c, final int a, final int cf, final String bo, final Color color) {
        this.C = c;
        this.A = a;
        this.cf = cf;
        this.bo = bo;
        this.bx = bx;
        this.H = this.bx.getWidth(this);
        this.G = this.bx.getHeight(this);
        this.bg = new int[this.bo.length() * c * a];
        int n = 0;
        int n2;
        for (n2 = 0; n2 < s.length() && s.charAt(n2) != ' '; ++n2) {}
        if (n2 < s.length()) {
            n = n2;
        }
        for (int i = 0; i < this.bo.length(); ++i) {
            int n3 = 0;
            final int n4 = (int)Math.rint(this.H / c);
            while (n3 < s.length() && this.bo.charAt(i) != s.charAt(n3)) {
                ++n3;
            }
            int n7;
            int n8;
            if (n3 < s.length()) {
                final int n5 = n3 % n4;
                final int n6 = n3 - n5;
                n7 = n5 * c;
                n8 = n6 / n4 * a + n6 / n4 * this.cf;
            }
            else {
                final int n9 = n % n4;
                final int n10 = n - n9;
                n7 = n9 * c;
                n8 = n10 / n4 * a + n10 / n4 * this.cf;
            }
            this.bf = new PixelGrabber(this.bx, n7, n8, c, a, this.bg, i * c, this.bo.length() * c);
            try {
                this.bf.grabPixels();
            }
            catch (InterruptedException ex) {}
        }
        final int n11 = 0xFF000000 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
        for (int j = 0; j < this.bo.length() * c * a; ++j) {
            if ((this.bg[j] & 0xFFFFFF) == 0x0) {
                this.bg[j] = n11;
            }
        }
        this.bt = this.createImage(new MemoryImageSource(this.bo.length() * c, a, this.bg, 0, this.bo.length() * c));
    }
    
    public Image b(final String s, final Image bx, final int c, final int a, final int cf, final String bo, final Color color) {
        this.C = c;
        this.A = a;
        this.cf = cf;
        this.bo = bo;
        this.bx = bx;
        this.H = this.bx.getWidth(this);
        this.G = this.bx.getHeight(this);
        this.bg = new int[this.bo.length() * c * a];
        int n = 0;
        int n2;
        for (n2 = 0; n2 < s.length() && s.charAt(n2) != ' '; ++n2) {}
        if (n2 < s.length()) {
            n = n2;
        }
        for (int i = 0; i < this.bo.length(); ++i) {
            int n3 = 0;
            final int n4 = (int)Math.rint(this.H / c);
            while (n3 < s.length() && this.bo.charAt(i) != s.charAt(n3)) {
                ++n3;
            }
            int n7;
            int n8;
            if (n3 < s.length()) {
                final int n5 = n3 % n4;
                final int n6 = n3 - n5;
                n7 = n5 * c;
                n8 = n6 / n4 * a + n6 / n4 * this.cf;
            }
            else {
                final int n9 = n % n4;
                final int n10 = n - n9;
                n7 = n9 * c;
                n8 = n10 / n4 * a + n10 / n4 * this.cf;
            }
            this.bf = new PixelGrabber(this.bx, n7, n8, c, a, this.bg, i * c, this.bo.length() * c);
            try {
                this.bf.grabPixels();
            }
            catch (InterruptedException ex) {}
        }
        final int n11 = 0xFF000000 | color.getRed() << 16 | color.getGreen() << 8 | color.getBlue();
        for (int j = 0; j < this.bo.length() * c * a; ++j) {
            if ((this.bg[j] & 0xFFFFFF) == 0x0) {
                this.bg[j] = n11;
            }
        }
        return this.createImage(new MemoryImageSource(this.bo.length() * c, a, this.bg, 0, this.bo.length() * c));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void n() {
        Thread.yield();
        this.bP.sync();
        final long n = 10L - (System.currentTimeMillis() - this.x);
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
        this.x = System.currentTimeMillis();
        try {
            Thread.sleep(this.S);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void a(final int[] array, final int bv, final float q, final float n, final int bu, final int n2) {
        this.bS = array[0];
        this.bV = bv;
        this.Q = q;
        this.N = n;
        this.bp = new float[this.bS + this.bV - 1];
        for (int i = 0; i < array.length; ++i) {
            final float n3 = 6.2831855f / array[i];
            for (int j = 0; j < this.bS + this.bV - 1; ++j) {
                this.bp[j] += (float)Math.sin(n3 * j);
            }
        }
        this.O = this.bp[0];
        this.R = this.bp[0];
        for (int k = 0; k < this.bS + this.bV - 1; ++k) {
            if (this.bp[k] > this.O) {
                this.O = this.bp[k];
            }
            if (this.bp[k] < this.R) {
                this.R = this.bp[k];
            }
        }
        final float n4 = (this.N - this.Q) / (this.O - this.R);
        final float n5 = this.Q - n4 * this.R;
        for (int l = 0; l < this.bS + this.bV - 1; ++l) {
            this.bp[l] = n4 * this.bp[l] + n5;
        }
        this.h = 0;
        this.bU = bu;
        if (n2 == solidscr.L) {
            this.be = this.bS;
            this.bR = new int[this.bS];
            for (int n6 = 0; n6 < this.bS; ++n6) {
                this.bR[n6] = n6;
            }
        }
        if (n2 == solidscr.y) {
            this.be = 360;
            this.bR = new int[360];
            final int round = Math.round((this.bS - 3) / 2);
            final int n7 = 1 + round;
            for (int n8 = 0; n8 < 360; ++n8) {
                this.bR[n8] = (int)Math.round(round * Math.cos(n8 * 3.1415927f / 180.0f) + n7);
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
                char c = '{';
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
                                c = 'l';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '>';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = ']';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '8';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
