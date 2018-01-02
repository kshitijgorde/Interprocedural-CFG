import java.awt.image.ImageConsumer;
import java.awt.image.PixelGrabber;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Container;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.net.URL;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Frame;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class morphmenu extends Applet implements Runnable, ImageObserver
{
    Frame a;
    boolean b;
    final String c = "I/onD|\u007f}{\u0001I1y{\u0001\\:~o\u0001 (hu\u000fi1y{";
    int d;
    int e;
    int f;
    Color g;
    morphmenub h;
    int i;
    int[] j;
    long k;
    int l;
    int[] m;
    int n;
    int o;
    boolean p;
    boolean q;
    int r;
    int s;
    int t;
    Color u;
    Frame v;
    private Graphics w;
    private Image x;
    private Graphics y;
    String z;
    boolean A;
    URL B;
    static int[] C;
    int D;
    int E;
    int F;
    int G;
    int H;
    int I;
    int J;
    int K;
    int L;
    Graphics M;
    Image N;
    static morphmenuc[] O;
    Font P;
    int Q;
    private boolean R;
    int[][] S;
    int[] T;
    int U;
    int V;
    int[] W;
    private int X;
    private int Y;
    boolean Z;
    boolean ba;
    int bb;
    int bc;
    float[] bd;
    float[] be;
    Color bf;
    boolean bg;
    static int[][] bh;
    int bi;
    private Graphics bj;
    private Image bk;
    boolean bl;
    int bm;
    private Image bn;
    int bo;
    int bp;
    int bq;
    anfy br;
    MemoryImageSource bs;
    int bt;
    static float[] bu;
    int bv;
    int bw;
    boolean bx;
    String by;
    String bz;
    int[] bA;
    int bB;
    int[] bC;
    int bD;
    String bE;
    int bF;
    long bG;
    int bH;
    Toolkit bI;
    Thread bJ;
    static int[] bK;
    int bL;
    Lware bM;
    private Graphics bN;
    private Image bO;
    float[] bP;
    float[] bQ;
    
    final int a(final String s, int n) {
        morphmenu.O[this.F] = new morphmenuc(s, this.J, this.H, this.F);
        final Image image = this.createImage(this.J, this.H);
        final Graphics graphics = image.getGraphics();
        graphics.setFont(this.P);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.P);
        String property = "";
        if (s.equals(c("4r2/"))) {
            n = 0;
            try {
                property = System.getProperty(c("j-puRm-1tDf;pp"));
            }
            catch (SecurityException ex) {
                property = "";
            }
        }
        if (s.equals(c("4r2/")) && this.A && !property.startsWith(c("F:kqBi/z"))) {
            final int stringWidth = fontMetrics.stringWidth(s);
            final int[] array = new int[3];
            final int[] array2 = new int[3];
            array[0] = this.J / 2 - stringWidth / 2;
            array2[0] = this.H / 2;
            array[1] = this.J / 2 + stringWidth / 2;
            array2[1] = this.H / 2 - this.s / 2;
            array[2] = this.J / 2 + stringWidth / 2;
            array2[2] = this.H / 2 + this.s / 2;
            graphics.fillPolygon(array, array2, 3);
        }
        else {
            final int stringWidth2 = fontMetrics.stringWidth(s);
            switch (this.n) {
                case 0: {
                    graphics.drawString(s, 1, (this.H - this.s) / 2 + this.s);
                    if (n == 1) {
                        graphics.drawLine(1, (this.H - this.s) / 2 + this.s + 2, 1 + stringWidth2, (this.H - this.s) / 2 + this.s + 2);
                        break;
                    }
                    break;
                }
                case 1: {
                    graphics.drawString(s, (this.J - stringWidth2) / 2, (this.H - this.s) / 2 + this.s);
                    if (n == 1) {
                        graphics.drawLine((this.J - stringWidth2) / 2, (this.H - this.s) / 2 + this.s + 2, (this.J - stringWidth2) / 2 + stringWidth2, (this.H - this.s) / 2 + this.s + 2);
                        break;
                    }
                    break;
                }
                case 2: {
                    graphics.drawString(s, this.J - stringWidth2, (this.H - this.s) / 2 + this.s);
                    if (n == 1) {
                        graphics.drawLine(this.J - stringWidth2, (this.H - this.s) / 2 + this.s + 2, this.J - stringWidth2 + stringWidth2, (this.H - this.s) / 2 + this.s + 2);
                        break;
                    }
                    break;
                }
            }
        }
        this.a(image, 0, 0, this.J, this.H, morphmenu.O[this.F].a, 0, this.J);
        final int n2 = this.H * this.J;
        final int n3 = morphmenu.O[this.F].a[0];
        for (int i = 0; i < n2; ++i) {
            if (n3 != morphmenu.O[this.F].a[i]) {
                final int n4 = morphmenu.O[this.F].a[i];
                break;
            }
        }
        for (int j = 0; j < n2; ++j) {
            if (morphmenu.O[this.F].a[j] == n3) {
                morphmenu.O[this.F].a[j] = 0;
            }
            else {
                morphmenu.O[this.F].a[j] = -1;
            }
        }
        for (int k = 0; k < n2; ++k) {
            morphmenu.O[this.F].j[k] = (morphmenu.O[this.F].a[k] &= 0xFFFFFF);
        }
        ++this.F;
        return this.F - 1;
    }
    
    private final void a() {
        while (true) {
            this.showStatus(c("L0q%U(-zoN~:?uV\u007fq~lGq+zcL&<po\u0001k-zfH|,?nHf:?kO(\u0017KOm)"));
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void calc_rand() {
        for (int i = 0; i < 3713; ++i) {
            morphmenu.bu[i] = (float)Math.random();
        }
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.bn, this);
        if (this.A) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bl;
        }
        return false;
    }
    
    private void b() {
        this.m = new int[this.J * this.H];
        for (int i = 0; i < this.J * this.H; ++i) {
            this.m[i] = 0;
        }
        this.m[this.J / 2 + this.H / 2 * this.J] = -1;
    }
    
    public void createMenus() {
        for (int i = 0; i < 20; ++i) {
            for (int j = 0; j < 20; ++j) {
                morphmenu.bh[i][j] = -1;
            }
        }
        for (int k = 0; k < 20; ++k) {
            morphmenu.bK[k] = (morphmenu.C[k] = -1);
        }
        final byte[] array = { 0 };
        for (int l = 0; l < 20; ++l) {
            for (int n = 0; n < 20; ++n) {
                if (l < 10) {
                    array[0] = (byte)(l + 48);
                }
                else {
                    array[0] = (byte)(l + 55);
                }
                String s;
                if (!this.A) {
                    s = new String(array, 0);
                }
                else {
                    s = new String(array);
                }
                if (n < 10) {
                    array[0] = (byte)(n + 48);
                }
                else {
                    array[0] = (byte)(n + 55);
                }
                String s2;
                if (!this.A) {
                    s2 = new String(array, 0);
                }
                else {
                    s2 = new String(array);
                }
                final String parameter = this.getParameter(c("E:qwum'k") + s + s2);
                if (parameter != null) {
                    int g;
                    if (this.getParameter(c("E:qwma1t") + s + s2) != null) {
                        g = (morphmenu.bh[l][n] = this.a(parameter, 1));
                    }
                    else {
                        g = (morphmenu.bh[l][n] = this.a(parameter, 0));
                    }
                    if (morphmenu.C[l] == -1) {
                        morphmenu.C[l] = g;
                    }
                    if (morphmenu.bK[l] == -1) {
                        morphmenu.bK[l] = g;
                    }
                    else {
                        morphmenu.O[morphmenu.bK[l]].g = g;
                        morphmenu.bK[l] = g;
                    }
                }
            }
        }
        for (int n2 = 0; n2 < 20; ++n2) {
            for (int n3 = 0; n3 < 20; ++n3) {
                if (n2 < 10) {
                    array[0] = (byte)(n2 + 48);
                }
                else {
                    array[0] = (byte)(n2 + 55);
                }
                String s3;
                if (!this.A) {
                    s3 = new String(array, 0);
                }
                else {
                    s3 = new String(array);
                }
                if (n3 < 10) {
                    array[0] = (byte)(n3 + 48);
                }
                else {
                    array[0] = (byte)(n3 + 55);
                }
                String s4;
                if (!this.A) {
                    s4 = new String(array, 0);
                }
                else {
                    s4 = new String(array);
                }
                if (this.getParameter(c("E:qwum'k") + s3 + s4) != null) {
                    final String parameter2 = this.getParameter(c("E:qwma1t") + s3 + s4);
                    if (parameter2 != null) {
                        int intValue = 0;
                        if (n3 < 10) {
                            array[0] = (byte)(n3 + 48);
                        }
                        else {
                            array[0] = (byte)(n3 + 55);
                        }
                        if (!this.A) {
                            s3 = new String(array, 0);
                        }
                        else {
                            s3 = new String(array);
                        }
                        try {
                            intValue = Integer.valueOf(parameter2);
                        }
                        catch (NumberFormatException ex) {
                            if (parameter2.equalsIgnoreCase("a")) {
                                intValue = 10;
                            }
                            if (parameter2.equalsIgnoreCase("b")) {
                                intValue = 11;
                            }
                            if (parameter2.equalsIgnoreCase("c")) {
                                intValue = 12;
                            }
                            if (parameter2.equalsIgnoreCase("d")) {
                                intValue = 13;
                            }
                            if (parameter2.equalsIgnoreCase("e")) {
                                intValue = 14;
                            }
                            if (parameter2.equalsIgnoreCase("f")) {
                                intValue = 15;
                            }
                            if (parameter2.equalsIgnoreCase("g")) {
                                intValue = 16;
                            }
                            if (parameter2.equalsIgnoreCase("h")) {
                                intValue = 17;
                            }
                            if (parameter2.equalsIgnoreCase("i")) {
                                intValue = 18;
                            }
                            if (parameter2.equalsIgnoreCase("j")) {
                                intValue = 19;
                            }
                        }
                        morphmenu.O[morphmenu.bh[n2][n3]].b = morphmenu.C[intValue];
                    }
                    final String parameter3 = this.getParameter(c("E:qwtz3") + s3 + s4);
                    if (parameter3 != null) {
                        try {
                            morphmenu.O[morphmenu.bh[n2][n3]].c = new URL(this.getDocumentBase(), parameter3);
                        }
                        catch (Exception ex2) {}
                    }
                    final String parameter4 = this.getParameter(c("E:qwui-xgU") + s3 + s4);
                    if (parameter4 != null) {
                        morphmenu.O[morphmenu.bh[n2][n3]].k = new String(parameter4);
                    }
                    else {
                        morphmenu.O[morphmenu.bh[n2][n3]].k = null;
                    }
                }
            }
        }
    }
    
    public void destroy() {
        if (this.bn != null) {
            this.bn.flush();
        }
        this.bn = null;
        if (this.bk != null) {
            this.bk.flush();
        }
        this.bk = null;
        if (this.bj != null) {
            this.bj.dispose();
        }
        this.bj = null;
        System.gc();
    }
    
    public final void doublebuf(final Graphics graphics) {
        this.bj.drawImage(this.x, 0, 0, this);
        if (this.bn != null) {
            this.prepaniframe();
        }
        this.f();
        graphics.drawImage(this.bk, 0, 0, this);
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
                this.showStatus(c("A2~eD(") + s + c("(1pv\u0001n0jlE)"));
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
    
    void c() {
        final Insets insets = this.v.insets();
        this.v.setResizable(true);
        this.v.resize(this.r + insets.left + insets.right, this.o + insets.bottom + insets.top);
        this.v.repaint();
        this.v.validate();
        this.v.setResizable(false);
        this.v.move(Integer.valueOf(this.getParameter(c("n3pcUp"))), Integer.valueOf(this.getParameter(c("n3pcUq"))));
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.bn) {
            if (n == 16) {
                this.bl = true;
            }
            return true;
        }
        return true;
    }
    
    public void init() {
        this.setLayout(null);
        this.addNotify();
        this.bI = this.getToolkit();
        final String parameter = this.getParameter(c("k-zfH|,"));
        if (parameter != null) {
            if (!parameter.startsWith(c("I/onD|\u007f}{\u0001I1y{\u0001\\:~o\u0001 (hu\u000fi1y{"))) {
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
            s = c("n6sg");
        }
        String s2;
        try {
            s2 = this.getDocumentBase().getHost();
        }
        catch (SecurityException ex2) {
            s2 = "";
        }
        if (s.equalsIgnoreCase(c("n6sg")) || s2.length() == 0 || s2.equalsIgnoreCase(c("d0|cM`0lv")) || s2.equals(c("9m(,\u0011&o13"))) {
            this.bx = true;
        }
        else {
            if (s2.startsWith(c("\u007f(h,"))) {
                s2 = s2.substring(4);
            }
            final String parameter2 = this.getParameter(c("z:xaNl:"));
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
                        if (s5.startsWith(c("\u007f(h,"))) {
                            substring = s5.substring(4);
                        }
                        else {
                            substring = s5;
                        }
                        if (s2.equalsIgnoreCase(substring)) {
                            this.bx = true;
                        }
                    }
                }
            }
        }
        if (this.getParameter(c("z:xlD\u007f9mcLm")).equalsIgnoreCase(c("Q\u001aL"))) {
            this.bg = true;
        }
        final String parameter3 = this.getParameter(c("g)zpHe8"));
        if (parameter3 != null && !parameter3.equalsIgnoreCase("NO")) {
            this.bn = this.a(parameter3);
            if (this.bn != null) {
                String parameter4 = this.getParameter(c("g)zpHe8G"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.bo = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c("g)zpHe8F"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bp = Integer.valueOf(parameter5);
            }
        }
        String parameter6 = this.getParameter(c("n3pcUe0{g"));
        if (parameter6 == null) {
            parameter6 = "NO";
        }
        if (parameter6.equalsIgnoreCase(c("Q\u001aL"))) {
            this.q = true;
            this.r = Integer.valueOf(this.getParameter(c("n3pcU\u007f")));
            this.o = Integer.valueOf(this.getParameter(c("n3pcU`")));
            String parameter7 = this.getParameter(c("n3pcUa1ypNf+"));
            if (parameter7 == null) {
                parameter7 = "NO";
            }
            if (parameter7.equalsIgnoreCase(c("Q\u001aL"))) {
                this.p = true;
            }
        }
        else {
            this.q = false;
        }
        if (this.q) {
            this.bw = this.r;
            this.bv = this.o;
        }
        else {
            this.bw = this.size().width;
            this.bv = this.size().height;
        }
        this.bL = this.bw * this.bv;
        this.bA = new int[this.bL];
        for (int n14 = 0; n14 < this.bL; ++n14) {
            this.bA[n14] = this.f;
        }
        this.J = this.bw;
        this.H = Integer.valueOf(this.getParameter(c("n0qv~`"))) + 8;
        this.K = 0;
        this.L = Integer.valueOf(this.getParameter(c("e:qw~q")));
        final String parameter8 = this.getParameter(c("|:gv@d6xl"));
        this.n = (parameter8.equals(c("z6xjU")) ? 2 : parameter8.equals(c("k:qvDz")));
        try {
            this.d();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.d();
        }
        this.P = new Font(this.getParameter(c("n0qvoi2z")), 1, this.s = Integer.valueOf(this.getParameter(c("n0qv~`"))));
        this.bk = this.createImage(this.bw, this.bv);
        this.bj = this.bk.getGraphics();
        this.N = this.createImage(this.J, this.H);
        (this.M = this.N.getGraphics()).setFont(this.P);
        this.bO = this.createImage(256, 256);
        this.bN = this.bO.getGraphics();
        this.by = this.getParameter(c("e:rfDd>f"));
        this.bz = this.getParameter(c("x-vmSa+f"));
        this.E = Integer.valueOf(this.by);
        this.bq = Integer.valueOf(this.bz);
        if (this.E < 0) {
            this.E = 0;
        }
        if (this.bq > 10) {
            this.bq = 10;
        }
        else if (this.bq < 1) {
            this.bq = 1;
        }
        String parameter9 = this.getParameter(c("E6qQxF\u001c"));
        if (parameter9 == null) {
            parameter9 = "10";
        }
        this.Q = Integer.valueOf(parameter9);
        this.t = Integer.valueOf(this.getParameter(c("|:gvBg3pp")), 16);
        if (this.t == 0) {
            this.t = 1;
        }
        this.bB = Integer.valueOf(this.getParameter(c("{:sgB|:{aNd0m")), 16);
        if (this.bB == 0) {
            this.bB = 1;
        }
        this.u = new Color(20, 20, 20);
        this.g = new Color(0, 0, 0);
        this.bf = new Color(this.t >> 16 & 0xFF, this.t >> 8 & 0xFF, this.t & 0xFF);
        this.f = Integer.valueOf(this.getParameter(c("j>|iBg3pp")), 16);
        this.G = (this.getParameter(c("e:qw~n'")).equals(c("z>qfNe")) ? 1 : 0);
        this.bF = Integer.valueOf(this.getParameter(c("{+~vT{=~p~q")));
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        this.a = (Frame)container;
        final String parameter10 = this.getParameter(c("j>|i~n'"));
        if (parameter10.equals(c("k0smS"))) {
            this.e = 0;
        }
        else if (parameter10.equals(c("a2~eD"))) {
            this.e = 1;
            this.z = this.getParameter(c("j>|i~a2~eD"));
        }
        else {
            try {
                this.h = (morphmenub)Class.forName(parameter10).newInstance();
                this.e = 2;
            }
            catch (Exception ex6) {
                this.e = 0;
            }
        }
        this.j = new int[this.bL];
        for (int n15 = 0; n15 < this.bL; ++n15) {
            this.j[n15] = this.f;
        }
        if (this.e == 1) {
            final Image a = this.a(this.z);
            if (a != null) {
                this.a(a, 0, 0, this.bw, this.bv, this.j, 0, this.bw);
            }
        }
        if (morphmenu.bh == null) {
            morphmenu.bh = new int[20][20];
        }
        if (morphmenu.bK == null) {
            morphmenu.bK = new int[20];
        }
        if (morphmenu.C == null) {
            morphmenu.C = new int[20];
        }
        if (morphmenu.bu == null) {
            morphmenu.bu = new float[3713];
            this.calc_rand();
        }
        if (this.e == 2) {
            this.h.prep_back(this);
        }
        if (morphmenu.O == null) {
            morphmenu.O = new morphmenuc[100];
        }
        try {
            this.createMenus();
        }
        catch (NoSuchMethodError noSuchMethodError2) {
            this.createMenus();
        }
        this.I = morphmenu.C[0];
        this.b();
        if (this.q) {
            (this.v = new Frame(this.getParameter(c("n3pcUa+sg")))).add(c("K:qvDz"), this);
        }
        if (!this.bx) {
            (this.bM = new Lware(this.getAppletContext(), new Label(c("E0mrI(\u0012zlT(>orMm+?`X(\u001eqdX(\u000bzcL(n&;\u0019'f&,")))).setTitle(c("E0mrI(\u0012zlT(\u001eorMm+?`X(\u001eqdX(\u000bzcL"));
            this.bM.hide();
        }
    }
    
    private void a(final int n, final int n2, final int bm) {
        this.bm = bm;
        int n3 = 0;
        for (int i = n; i != -1; i = morphmenu.O[i].g) {
            ++n3;
        }
        int n4 = 0;
        for (int j = n2; j != -1; j = morphmenu.O[j].g) {
            ++n4;
        }
        int v;
        if (n3 > n4) {
            v = n3;
        }
        else {
            v = n4;
        }
        this.S = new int[v][];
        for (int k = 0; k < v; ++k) {
            this.S[k] = new int[this.J * this.H];
        }
        this.W = new int[v];
        this.T = new int[v];
        int g = n;
        int g2 = n2;
        for (int l = 0; l < v; ++l) {
            if ((this.W[l] = g) != -1) {
                g = morphmenu.O[g].g;
            }
            if ((this.T[l] = g2) != -1) {
                g2 = morphmenu.O[g2].g;
            }
            if (this.W[l] != -1) {
                System.arraycopy(morphmenu.O[this.W[l]].a, 0, this.S[l], 0, this.J * this.H);
            }
            else {
                for (int n5 = 0; n5 < this.J * this.H; ++n5) {
                    this.S[l][n5] = 0;
                }
            }
        }
        this.U = 0;
        this.V = v;
        this.g();
    }
    
    void d() {
        this.bs = new MemoryImageSource(this.bw, this.bv, new DirectColorModel(24, 16711680, 65280, 255), this.bA, 0, this.bw);
        String s;
        try {
            s = System.getProperty(c("b>ic\u000f~:mqHg1"));
        }
        catch (SecurityException ex) {
            s = c("}1t");
        }
        if (!s.startsWith(c("9q/"))) {
            try {
                this.bs.setAnimated(true);
                this.bs.setFullBufferUpdates(true);
                this.x = this.createImage(this.bs);
                this.bs.newPixels();
                this.A = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.A = false;
            }
        }
        if (!this.A) {
            this.bs = null;
            this.br = new anfy(this.bw, this.bv, new DirectColorModel(24, 16711680, 65280, 255), this.bA, 0, this.bw);
            this.x = this.createImage(this.br);
        }
    }
    
    public void menu_normal(final int n) {
        final int[] a = morphmenu.O[n].a;
        final int[] j = morphmenu.O[n].j;
        for (int i = 0; i < this.H; ++i) {
            for (int k = 0; k < this.J; ++k) {
                if (a[i * this.J + k] != 0) {
                    j[i * this.J + k] = 16777215;
                }
            }
        }
    }
    
    public void menu_rand(final int n) {
        final int[] a = morphmenu.O[n].a;
        final int[] j = morphmenu.O[n].j;
        for (int n2 = this.H * this.J, i = 0; i < n2; ++i) {
            j[i] = 0;
        }
        for (int k = 1; k < this.H - 1; ++k) {
            for (int l = 1; l < this.J - 1; ++l) {
                if (a[(k + (1 - (int)(2.99 * this.rand()))) * this.J + l + (1 - (int)(2.99 * this.rand()))] != 0) {
                    j[k * this.J + l] = 16777215;
                }
            }
        }
    }
    
    public morphmenu() {
        this.A = false;
        this.q = false;
        this.p = false;
        this.bE = "";
        this.bl = false;
        this.b = false;
        this.bx = false;
        this.bg = false;
        this.Z = false;
        this.ba = true;
        this.J = 100;
        this.H = 30;
        this.R = false;
        this.Y = 5;
        this.bm = -1;
    }
    
    public synchronized boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.bx) {
            this.bM.show();
            this.bM.toFront();
            this.bM.requestFocus();
        }
        else if (this.D != -1) {
            this.Z = true;
        }
        else {
            this.Z = false;
        }
        return true;
    }
    
    public synchronized boolean mouseMove(final Event event, final int bb, final int bc) {
        this.bb = bb;
        this.bc = bc;
        return true;
    }
    
    private void e() {
        final int n = this.J * this.H;
        final int[] array = this.S[this.U];
        for (int i = 0; i < n; ++i) {
            array[i] = 0;
        }
        for (int j = 0; j < this.bi; ++j) {
            final float[] bd = this.bd;
            final int n2 = j;
            bd[n2] += this.bP[j];
            final float[] be = this.be;
            final int n3 = j;
            be[n3] += this.bQ[j];
            array[(int)this.be[j] * this.J + (int)this.bd[j]] = this.t;
        }
        ++this.X;
        if (this.X >= this.Y) {
            if (this.T[this.U] != -1) {
                System.arraycopy(morphmenu.O[this.T[this.U]].a, 0, this.S[this.U], 0, n);
            }
            else {
                for (int k = 0; k < n; ++k) {
                    this.S[this.U][k] = 0;
                }
            }
            ++this.U;
            this.X = 0;
            if (this.U >= this.V) {
                this.R = false;
                if (morphmenu.O[this.bm].b != -1) {
                    this.I = morphmenu.O[this.bm].b;
                }
                this.bm = -1;
                return;
            }
            this.g();
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.x != null) {
            this.doublebuf(graphics);
        }
    }
    
    void a(final Image image, final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, n, n2, n3, n4, array, n5, n6);
        image.getSource().addConsumer(pixelGrabber);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            return;
        }
    }
    
    public synchronized void prepaniframe() {
        if (this.b) {
            this.notifyAll();
            while (!this.bl) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bl = false;
        }
        this.bj.drawImage(this.bn, this.bo, this.bp, this);
    }
    
    public void print_menu(final int n, final int n2, final int n3, final int n4) {
        int[] array;
        int n5;
        if (n4 == 0) {
            array = morphmenu.O[n].a;
            n5 = this.t;
        }
        else if (n4 == 1) {
            array = morphmenu.O[n].j;
            n5 = this.bB;
        }
        else {
            array = this.S[n];
            n5 = this.t;
        }
        final int[] ba = this.bA;
        for (int i = 0; i < this.H; ++i) {
            for (int j = 0; j < this.J; ++j) {
                if (array[i * this.J + j] != 0) {
                    ba[(n3 + i) * this.bw + n2 + j] = n5;
                }
            }
        }
    }
    
    public void print_table(int g, final int x, int y) {
        if (!this.R) {
            this.D = -1;
            while (g != -1 && this.bv - y >= this.H) {
                morphmenu.O[g].i.x = x;
                morphmenu.O[g].i.y = y;
                int n;
                if (morphmenu.O[g].i.inside(this.bb, this.bc)) {
                    n = 1;
                    this.D = g;
                }
                else {
                    n = 0;
                }
                this.print_menu(g, x, y, n);
                y += this.H;
                g = morphmenu.O[g].g;
            }
            if (this.D == -1) {
                this.a.setCursor(0);
                this.showStatus(this.bE = c("f0kjHf8?qDd:|vDl"));
            }
            else if (morphmenu.O[this.D].b != -1 || morphmenu.O[this.D].c != null) {
                this.a.setCursor(12);
                if (morphmenu.O[this.D].b != -1) {
                    this.bE = c("e:qw\u0001{:sgB|:{");
                }
                else {
                    this.bE = morphmenu.O[this.D].c.toString();
                }
                this.showStatus(this.bE);
            }
            else {
                this.a.setCursor(0);
                this.showStatus(this.bE = c("f0?nHf4"));
            }
        }
        if (this.R) {
            this.a.setCursor(0);
            for (int n2 = 0; n2 < this.V && this.bv - y >= this.H; y += this.H, ++n2) {
                this.print_menu(n2, x, y, 2);
            }
            this.e();
            return;
        }
        if (this.D != -1) {
            if (this.G == 0) {
                this.menu_normal(this.D);
            }
            else if (this.G == 1) {
                this.menu_rand(this.D);
            }
            if (this.Z) {
                if (morphmenu.O[this.D].b != -1) {
                    this.a(this.I, morphmenu.O[this.D].b, this.D);
                }
                else if (morphmenu.O[this.D].c != null) {
                    if (morphmenu.O[this.D].k == null) {
                        if (this.bg) {
                            this.getAppletContext().showDocument(morphmenu.O[this.D].c, this.getParameter(c("z:xdSi2zl@e:")));
                        }
                        else {
                            this.getAppletContext().showDocument(morphmenu.O[this.D].c);
                        }
                    }
                    else {
                        this.getAppletContext().showDocument(morphmenu.O[this.D].c, morphmenu.O[this.D].k);
                    }
                }
                this.Z = false;
            }
        }
    }
    
    public final void producefixed() {
        try {
            if (this.A) {
                this.bs.newPixels();
                return;
            }
            this.br.startProduction(this.br.getConsumer());
        }
        catch (NoSuchMethodError noSuchMethodError) {}
    }
    
    public float rand() {
        if (this.bt >= 3713) {
            this.bt = 0;
        }
        return morphmenu.bu[this.bt++];
    }
    
    public void run() {
        if (this.q) {
            this.c();
        }
        Thread.currentThread().setPriority(this.bq);
        this.bH = 0;
        final Graphics graphics = this.getGraphics();
        if (this.bn != null && !this.b) {
            this.b = this.CheckAniGIF();
        }
        System.gc();
        while (this.bJ != null) {
            ++this.bH;
            if (this.e == 2) {
                this.h.animate();
            }
            else {
                System.arraycopy(this.j, 0, this.bA, 0, this.bL);
            }
            this.print_table(this.I, this.K, this.L);
            if (this.e == 2) {
                this.h.animate2();
            }
            if (++this.i == this.E) {
                System.gc();
                this.i = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            this.doublebuf(graphics);
            this.waitsync();
        }
    }
    
    byte a(final int n, final int n2, final int n3, final int n4) {
        final int n5 = n - n2;
        if (n5 >= n3) {
            return (byte)n5;
        }
        return (byte)(n4 - (n3 - n5 - 1));
    }
    
    void f() {
        this.bj.setColor(this.bf);
        this.bj.drawString(this.bE, 0, this.bF);
    }
    
    public synchronized void start() {
        if (this.bJ == null) {
            (this.bJ = new Thread(this)).start();
            if (this.q) {
                this.c();
                this.v.show();
            }
        }
    }
    
    private void g() {
        final int bd = this.W[this.U];
        final int l = this.T[this.U];
        int[] array;
        if (bd != -1) {
            array = morphmenu.O[bd].a;
        }
        else {
            array = this.m;
        }
        int[] array2;
        if (l != -1) {
            array2 = morphmenu.O[l].a;
        }
        else {
            array2 = this.m;
        }
        this.R = true;
        this.X = 0;
        final int[] array3 = new int[this.H];
        final int[] array4 = new int[this.H];
        final int[][] array5 = new int[this.H][this.J];
        final int[][] array6 = new int[this.H][this.J];
        final int[][] array7 = new int[this.H][this.J];
        final int[][] array8 = new int[this.H][this.J];
        for (int i = 0; i < this.H; ++i) {
            array4[i] = (array3[i] = 0);
            final int n = i * this.J;
            for (int j = 0; j < this.J; ++j) {
                if (array[n + j] != 0) {
                    array5[i][array3[i]] = j;
                    array6[i][array3[i]] = i;
                    final int[] array9 = array3;
                    final int n2 = i;
                    ++array9[n2];
                }
                if (array2[n + j] != 0) {
                    array7[i][array4[i]] = j;
                    array8[i][array4[i]] = i;
                    final int[] array10 = array4;
                    final int n3 = i;
                    ++array10[n3];
                }
            }
        }
        int n4 = 0;
        int n5 = 0;
        for (int k = 0; k < this.H; ++k) {
            if (array3[k] > 0) {
                ++n4;
            }
            if (array4[k] > 0) {
                ++n5;
            }
        }
        int n6;
        if (n4 > n5) {
            n6 = n4;
        }
        else {
            n6 = n5;
        }
        if (n5 == 0) {
            final int n7 = this.H / 2;
            array4[n7] = 1;
            n5 = 1;
            array7[n7][array4[n7]] = this.J / 2;
            array8[n7][array4[n7]] = this.H / 2;
        }
        final int[] array11 = new int[n6];
        final int[] array12 = new int[n4];
        final int[] array13 = new int[n5];
        int n8 = 0;
        int n9 = 0;
        for (int n10 = 0; n10 < this.H; ++n10) {
            if (array3[n10] > 0) {
                array12[n8] = n10;
                ++n8;
            }
            if (array4[n10] > 0) {
                array13[n9] = n10;
                ++n9;
            }
        }
        final float n11 = n4 / n6;
        final float n12 = n5 / n6;
        final float[] array14 = new float[n6];
        final float[] array15 = new float[n6];
        int n13 = 0;
        for (int n14 = 0; n14 < n6; ++n14) {
            if (array3[array12[(int)(n14 * n11)]] > array4[array13[(int)(n14 * n12)]]) {
                array11[n14] = array3[array12[(int)(n14 * n11)]];
            }
            else {
                array11[n14] = array4[array13[(int)(n14 * n12)]];
            }
            array14[n14] = array3[array12[(int)(n14 * n11)]] / array11[n14];
            array15[n14] = array4[array13[(int)(n14 * n12)]] / array11[n14];
            n13 += array11[n14];
        }
        this.bd = null;
        this.be = null;
        this.bP = null;
        this.bQ = null;
        this.bd = new float[n13];
        this.be = new float[n13];
        this.bP = new float[n13];
        this.bQ = new float[n13];
        final float[] array16 = new float[n13];
        final float[] array17 = new float[n13];
        int bi = 0;
        for (int n15 = 0; n15 < n6; ++n15) {
            for (int n16 = 0; n16 < array11[n15]; ++n16) {
                final int n17 = (int)(n12 * n15);
                array16[bi] = array7[array13[n17]][(int)(n16 * array15[n15])];
                array17[bi] = array8[array13[n17]][(int)(n16 * array15[n15])];
                final int n18 = (int)(n11 * n15);
                this.bd[bi] = array5[array12[n18]][(int)(n16 * array14[n15])];
                this.be[bi] = array6[array12[n18]][(int)(n16 * array14[n15])];
                ++bi;
            }
        }
        for (int n19 = 0; n19 < bi; ++n19) {
            this.bP[n19] = (array16[n19] - this.bd[n19]) / this.Y;
            this.bQ[n19] = (array17[n19] - this.be[n19]) / this.Y;
        }
        this.X = 0;
        this.R = true;
        this.bi = bi;
        this.bD = bd;
        this.l = l;
        this.e();
    }
    
    public synchronized void stop() {
        if (this.bJ != null && this.bJ.isAlive()) {
            this.bJ.stop();
        }
        if (this.q) {
            this.v.hide();
        }
        this.bJ = null;
    }
    
    public final void update(final Graphics graphics) {
        if (this.x != null) {
            this.doublebuf(graphics);
        }
    }
    
    public synchronized void waitsync() {
        Thread.yield();
        this.bI.sync();
        final long n = 10L - (System.currentTimeMillis() - this.k);
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
        this.k = System.currentTimeMillis();
        try {
            Thread.sleep(this.Q);
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
                char c = '\b';
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
                                c = '_';
                                array = (array2 = charArray);
                                n3 = (n4 = n);
                                continue;
                            }
                            c = '\u001f';
                            array = (array2 = charArray);
                            n3 = (n4 = n);
                            continue;
                        }
                        c = '\u0002';
                        array = (array2 = charArray);
                        n3 = (n4 = n);
                        continue;
                    }
                    c = '!';
                    array = (array2 = charArray);
                    n3 = (n4 = n);
                }
            }
            break;
        }
        return new String(charArray);
    }
}
