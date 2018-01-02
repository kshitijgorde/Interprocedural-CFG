import java.awt.Event;
import java.awt.image.PixelGrabber;
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

public class bookflip extends Applet implements Runnable, ImageObserver
{
    anfy a;
    MemoryImageSource b;
    boolean c;
    Toolkit d;
    int e;
    long f;
    int g;
    int h;
    int i;
    int j;
    Thread k;
    Lware l;
    int m;
    int n;
    int o;
    private Image p;
    int q;
    int r;
    boolean s;
    Frame t;
    int u;
    int v;
    String[] w;
    String[] x;
    int y;
    int z;
    int A;
    int B;
    int C;
    int D;
    int[] E;
    int[][] F;
    private Image G;
    private Graphics H;
    String I;
    String J;
    String K;
    String L;
    String M;
    String N;
    String O;
    int P;
    String[] Q;
    private Image R;
    private Graphics S;
    int T;
    int U;
    int[] V;
    int W;
    int X;
    int Y;
    int Z;
    int[] ba;
    int[] bb;
    int[] bc;
    int[] bd;
    float[] be;
    int bf;
    int bg;
    int bh;
    int bi;
    int bj;
    int bk;
    int bl;
    int[] bm;
    int[] bn;
    Image[] bo;
    boolean[] bp;
    boolean bq;
    boolean br;
    final String bs = "\u001a3frd/ctg!\u001d\"twn{\u0000\u007fkb8*66v,48\u007fo=";
    boolean bt;
    URL bu;
    boolean bv;
    int bw;
    String bx;
    boolean by;
    int bz;
    int[] bA;
    boolean bB;
    int bC;
    public static int bD;
    
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
                this.showStatus(c("\u0012.wyd{") + s + c("{-yj!=,cpez"));
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
    
    public void init() {
        final int i = bookflip.bD;
        this.setLayout(null);
        this.addNotify();
        this.d = this.getToolkit();
        final String parameter = this.getParameter(c("81szh/0"));
        String protocol;
        final String s = protocol = parameter;
        String c = null;
        Label_0104: {
            Label_0073: {
                Label_0069: {
                    if (i == 0) {
                        if (s == null) {
                            break Label_0069;
                        }
                        final String s2;
                        protocol = (s2 = parameter);
                    }
                    if (i != 0) {
                        break Label_0104;
                    }
                    if (s.startsWith(c("\u001a3frd/ctg!\u001d\"twn{\u0000\u007fkb8*66v,48\u007fo="))) {
                        break Label_0073;
                    }
                    this.c();
                    if (i == 0) {
                        break Label_0073;
                    }
                }
                this.c();
            }
            (this.l = new Lware(this, c("\u0019,yuG7*f>`+3z{u"))).hide();
            try {
                protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("=*z{");
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
        Label_0631: {
            Label_0622: {
                int n3 = 0;
                int startsWith = 0;
                Label_0223: {
                    Label_0214: {
                        if (i == 0) {
                            Label_0204: {
                                if (!c.equals(c("=*z{"))) {
                                    int n2;
                                    final int n = n2 = (startsWith = (n3 = s3.length()));
                                    if (i == 0) {
                                        if (n < 1) {
                                            break Label_0204;
                                        }
                                        final int n4;
                                        n2 = (n4 = (startsWith = (n3 = (s3.startsWith(c("7,u\u007fm")) ? 1 : 0))));
                                    }
                                    if (i == 0) {
                                        if (n != 0) {
                                            break Label_0204;
                                        }
                                        startsWith = (n2 = (n3 = (s3.equals(c("jq!01us8/")) ? 1 : 0)));
                                    }
                                    if (i != 0) {
                                        break Label_0223;
                                    }
                                    if (n2 == 0) {
                                        break Label_0214;
                                    }
                                }
                            }
                            this.bt = true;
                        }
                        if (i == 0) {
                            break Label_0622;
                        }
                    }
                    n3 = (startsWith = (s3.startsWith(c(",4a0")) ? 1 : 0));
                }
                if (i == 0) {
                    if (startsWith != 0) {
                        s3 = s3.substring(4);
                    }
                    n3 = s3.length();
                }
                final int n6;
                final int n5 = n6 = n3;
                if (i != 0 || n6 > 0) {
                    final char[] array = new char[n6];
                    s3.getChars(0, n5, array, 0);
                    int n7 = 0;
                    while (true) {
                        while (true) {
                            Label_0302: {
                                if (i == 0) {
                                    break Label_0302;
                                }
                                final char[] array2 = array;
                                final int n8 = n7;
                                if (i != 0 || array2[n8] == '0') {
                                    array2[n8] = '1';
                                }
                                n7 += 5;
                            }
                            if (n7 < n5) {
                                continue;
                            }
                            break;
                        }
                        if (i != 0) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c(")&q}n?&"));
                if (i != 0) {
                    break Label_0631;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (i != 0) {
                        break Label_0631;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n9 = 1;
                        try {
                            int n10 = 0;
                            while (true) {
                                while (true) {
                                    Label_0397: {
                                        if (i == 0) {
                                            break Label_0397;
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
                                if (i != 0) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n9];
                        final int n11 = n9;
                        if (i == 0 && n11 == 1) {
                            array3[0] = s4.length();
                            if (i != 0) {
                                goto Label_0447;
                            }
                        }
                        else {
                            int n12 = n11;
                            try {
                                int n13 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0483: {
                                            if (i == 0) {
                                                break Label_0483;
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
                                    if (i != 0) {
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
                        int n16;
                        while (true) {
                            while (true) {
                                Label_0563: {
                                    if (i == 0) {
                                        break Label_0563;
                                    }
                                    try {
                                        array4[n15] = s4.substring(n14, array3[n15]);
                                    }
                                    catch (StringIndexOutOfBoundsException ex5) {}
                                    n14 = array3[n15] + 1;
                                    ++n15;
                                }
                                if (n15 < n9) {
                                    continue;
                                }
                                break;
                            }
                            n16 = 0;
                            if (i != 0) {
                                continue;
                            }
                            break;
                        }
                        while (true) {
                            Label_0615: {
                                if (i == 0) {
                                    break Label_0615;
                                }
                                if (s3.equals(this.l.dr(array4[n16], 0, this.bt))) {
                                    this.bt = true;
                                }
                                ++n16;
                            }
                            if (n16 < n9) {
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
            this.getParameter(c(")&qpd,%d\u007fl>"));
        }
        final String s6 = parameter2;
        Container parent = null;
        Label_0713: {
            if (i == 0) {
                if (s6.equalsIgnoreCase(c("\u0002\u0006E"))) {
                    this.bv = true;
                }
                this.bx = this.getParameter(c("\u0016*xMX\u0015\u0000"));
                parent = this;
                if (i != 0) {
                    break Label_0713;
                }
                final String bx = this.bx;
            }
            if (s6 == null) {
                this.bx = c("js");
            }
            this.e = Integer.valueOf(this.bx);
            this.s = false;
            parent = this.getParent();
        }
        Container container = parent;
        while (true) {
            while (true) {
                Label_0730: {
                    if (i == 0) {
                        break Label_0730;
                    }
                    final Container parent2 = ((Frame)container).getParent();
                    container = parent2;
                }
                if (!(container instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.t = (Frame)container).setCursor(3);
            final Container parent2 = this;
            if (i != 0) {
                continue;
            }
            break;
        }
        final String parameter3 = this.getParameter(c("45slh6$"));
        String s8;
        String j;
        final String s7 = j = (s8 = parameter3);
        Label_0943: {
            Label_0917: {
                if (i == 0) {
                    bookflip bookflip = null;
                    Label_0909: {
                        if (s7 != null) {
                            final String s9 = parameter3;
                            if (i != 0) {
                                break Label_0917;
                            }
                            if (!s9.equalsIgnoreCase(c("\u0015\f"))) {
                                this.p = this.a(parameter3);
                                bookflip = this;
                                if (i != 0) {
                                    break Label_0909;
                                }
                                if (this.p != null) {
                                    final String parameter4;
                                    String s10 = parameter4 = this.getParameter(c("45slh6$N"));
                                    if (i == 0) {
                                        if (parameter4 == null) {
                                            s10 = "0";
                                        }
                                        this.q = Integer.valueOf(s10);
                                        this.getParameter(c("45slh6$O"));
                                    }
                                    final String s12;
                                    String s11 = s12 = parameter4;
                                    if (i != 0 || s12 == null) {
                                        s11 = s12;
                                    }
                                    this.r = Integer.valueOf(s11);
                                }
                            }
                        }
                        this.I = this.getParameter(c(")&e"));
                        bookflip = this;
                    }
                    final String s13;
                    j = (s13 = (s8 = bookflip.I));
                }
                if (i != 0) {
                    break Label_0943;
                }
            }
            if (s7 == null) {
                this.I = "1";
            }
            this.J = this.getParameter(c("(3s{e"));
            s8 = (j = this.J);
        }
        bookflip bookflip2 = null;
        Label_1088: {
            if (i == 0) {
                if (j == null) {
                    this.J = "8";
                }
                this.K = this.getParameter(c("+\"cmd"));
                bookflip2 = this;
                if (i != 0) {
                    break Label_1088;
                }
                s8 = this.K;
            }
            if (s8 == null) {
                this.K = c("jv&.");
            }
            this.D = Integer.valueOf(this.I);
            this.bj = Integer.valueOf(this.J);
            this.C = Integer.valueOf(this.K);
            this.N = this.getParameter(c("6&{zd7\"o"));
            this.O = this.getParameter(c("+1\u007fqs27o"));
            this.n = Integer.valueOf(this.N);
            this.o = Integer.valueOf(this.O);
            bookflip2 = this;
        }
        int n18;
        final int n17 = n18 = bookflip2.n;
        if (i == 0) {
            if (n17 < 0) {
                this.n = 0;
            }
            final int o;
            n18 = (o = this.o);
        }
        final int n19 = 10;
        bookflip bookflip3 = null;
        Label_1148: {
            Label_1147: {
                if (i == 0) {
                    if (n17 > n19) {
                        this.o = 10;
                        if (i == 0) {
                            break Label_1147;
                        }
                    }
                    bookflip3 = this;
                    if (i != 0) {
                        break Label_1148;
                    }
                    n18 = this.o;
                }
                if (n18 < n19) {
                    this.o = 1;
                }
            }
            bookflip3 = this;
        }
        bookflip3.P = 1;
        while (true) {
            while (true) {
                Label_1167: {
                    if (i == 0) {
                        break Label_1167;
                    }
                    final bookflip bookflip4 = this;
                    final bookflip bookflip5 = this;
                    bookflip4.P = bookflip5.P + 1;
                }
                if (this.getParameter(c("2.wyd") + String.valueOf(this.P)) != null) {
                    continue;
                }
                break;
            }
            --this.P;
            final bookflip bookflip4 = this;
            final bookflip bookflip5 = this;
            if (i != 0) {
                continue;
            }
            break;
        }
        final int p = this.P;
        final int n20 = 1;
        if (i == 0) {
            if (p <= n20) {
                do {
                    this.showStatus(c("\u001a/{qr/c$>h6\"q{r{1sot21sz "));
                } while (i == 0);
            }
            this.Q = new String[this.P];
            this.w = new String[this.P];
            this.x = new String[this.P];
            final int p2 = this.P;
        }
        final int[] array5 = new int[p + n20];
        int n21 = 0;
        int n22;
        while (true) {
            while (true) {
                Label_1331: {
                    if (i == 0) {
                        break Label_1331;
                    }
                    this.Q[n21] = this.getParameter(c("2.wyd") + String.valueOf(n21 + 1));
                    ++n21;
                }
                if (n21 < this.P) {
                    continue;
                }
                break;
            }
            n22 = 0;
            if (i != 0) {
                continue;
            }
            break;
        }
        while (true) {
            while (true) {
                Label_1468: {
                    if (i == 0) {
                        break Label_1468;
                    }
                    this.w[n22] = this.getParameter(c("7*xu") + String.valueOf(n22 + 1));
                    final bookflip bookflip6 = this;
                    bookflip6.x[n22] = this.getParameter(c("(7wjt(.ey") + String.valueOf(n22 + 1));
                    array5[n22] = Integer.valueOf(this.getParameter(c("=/\u007fn") + String.valueOf(n22 + 1)));
                    ++n22;
                }
                if (n22 < this.P) {
                    continue;
                }
                break;
            }
            array5[this.P] = array5[0];
            this.N = this.getParameter(c(">;bl`3"));
            final bookflip bookflip6 = this;
            if (i != 0) {
                continue;
            }
            break;
        }
        final String n23 = this.N;
        bookflip bookflip7 = null;
        Label_1589: {
            if (i == 0) {
                if (n23 == null) {
                    this.N = "0";
                }
                this.i = Integer.valueOf(this.N);
                this.N = this.getParameter(c("=/\u007fnb.1`{"));
                bookflip7 = this;
                if (i != 0) {
                    break Label_1589;
                }
                final String n24 = this.N;
            }
            if (n23 == null) {
                this.N = "0";
            }
            this.bf *= Integer.valueOf(this.N);
            bookflip7 = this;
        }
        final int bf = bookflip7.bf;
        final int n25 = 500;
        bookflip bookflip9 = null;
        bookflip bookflip10 = null;
        Label_1686: {
            bookflip bookflip8 = null;
            Label_1654: {
                Label_1635: {
                    if (i == 0) {
                        if (bf > n25) {
                            this.bf = 500;
                            if (i == 0) {
                                break Label_1635;
                            }
                        }
                        bookflip8 = this;
                        if (i != 0) {
                            break Label_1654;
                        }
                        final int bf2 = this.bf;
                    }
                    if (bf < n25) {
                        this.bf = 50;
                    }
                }
                this.N = this.getParameter(c("(+wzh5$"));
                bookflip9 = this;
                bookflip10 = this;
                bookflip8 = this;
                if (i != 0) {
                    break Label_1686;
                }
            }
            if (bookflip8.N == null) {
                this.N = "4";
            }
            this.g = Integer.valueOf(this.N) * 64 - 1;
            bookflip9 = this;
            bookflip10 = this;
        }
        if (i == 0) {
            if (bookflip10.g < 0) {
                this.g = 0;
            }
            this.h = (int)(this.g / 1.5f);
            this.N = this.getParameter(c("9\"uus"));
            bookflip9 = this;
        }
        final String n26 = bookflip9.N;
        if (i == 0) {
            if (n26 == null) {
                this.N = c("mw");
            }
            final String n27 = this.N;
        }
        final int intValue = Integer.valueOf(n26);
        this.N = this.getParameter(c("9\"uuf"));
        final String n28 = this.N;
        if (i == 0) {
            if (n28 == null) {
                this.N = c("bu");
            }
            final String n29 = this.N;
        }
        final int intValue2 = Integer.valueOf(n28);
        this.N = this.getParameter(c("9\"uuc"));
        final String n30 = this.N;
        if (i == 0) {
            if (n30 == null) {
                this.N = c("ju&");
            }
            final String n31 = this.N;
        }
        this.U = (intValue << 16 | intValue2 << 8 | Integer.valueOf(n30));
        this.N = this.getParameter(c(">;bl`3"));
        bookflip bookflip11 = this;
        if (i == 0) {
            if (this.N == null) {
                this.N = "0";
            }
            this.i = Integer.valueOf(this.N);
            this.y = this.size().width / this.D;
            this.z = this.size().height / this.D;
            this.A = this.y * this.D;
            this.B = this.z * this.D;
            this.W = this.y;
            this.X = this.z - this.i;
            this.u = this.y * this.z;
            this.v = this.W * this.X;
            this.bl = (this.z - this.X) / 2;
            bookflip11 = this;
        }
        bookflip11.V = new int[this.u];
        int n32 = 0;
        while (true) {
            while (true) {
                Label_2068: {
                    if (i == 0) {
                        break Label_2068;
                    }
                    this.V[n32] = this.U;
                    ++n32;
                }
                if (n32 < this.u) {
                    continue;
                }
                break;
            }
            this.E = new int[this.u];
            try {
                System.arraycopy(this.V, 0, this.E, 0, this.u);
                if (i != 0) {
                    continue;
                }
            }
            catch (ArrayIndexOutOfBoundsException ex6) {}
            catch (ArrayStoreException ex7) {}
            break;
        }
        try {
            this.a();
        }
        catch (NoSuchMethodError noSuchMethodError) {
            this.a();
        }
        int n33 = 0;
        this.Y = this.P * 2;
        this.ba = new int[this.Y];
        this.bb = new int[this.Y];
        int n34 = 0;
        int n37 = 0;
        while (true) {
            while (true) {
                Label_2347: {
                    if (i == 0) {
                        break Label_2347;
                    }
                    final int n35 = array5[n34];
                    final int n36 = n37;
                    final int n38 = array5[n34 + 1];
                    this.ba[n33] = n36;
                    final int n39 = n36;
                    Label_2266: {
                        if (i == 0 && n39 < 4) {
                            this.bb[n33++] = n34;
                            if (i != 0) {
                                goto Label_2226;
                            }
                        }
                        else {
                            if (n39 > 0) {
                                this.bb[n33++] = n34 - 1;
                                if (i == 0) {
                                    break Label_2266;
                                }
                            }
                            this.bb[n33++] = this.P - 1;
                        }
                    }
                    this.ba[n33] = 8;
                    final int n40 = n38;
                    final int n41 = 4;
                    Label_2344: {
                        if (i == 0) {
                            if (n40 < n41) {
                                this.bb[n33++] = n34;
                                if (i == 0) {
                                    break Label_2344;
                                }
                            }
                            final int n42 = this.P - 1;
                        }
                        if (n40 < n41) {
                            this.bb[n33++] = n34 + 1;
                            if (i == 0) {
                                break Label_2344;
                            }
                        }
                        this.bb[n33++] = 0;
                    }
                    ++n34;
                }
                if (n34 < this.P) {
                    continue;
                }
                break;
            }
            this.bp = new boolean[this.P];
            this.bm = new int[this.P];
            this.bn = new int[this.P];
            n37 = 0;
            if (i != 0) {
                continue;
            }
            break;
        }
        int n43 = n37;
        while (true) {
            while (true) {
                Label_2410: {
                    if (i == 0) {
                        break Label_2410;
                    }
                    this.bp[n43] = false;
                    ++n43;
                }
                if (n43 < this.P) {
                    continue;
                }
                break;
            }
            this.bz = 1;
            this.j = this.bz - 1;
            this.createTab();
            this.bo = new Image[2];
            this.F = new int[this.P][this.v];
            this.R = this.createImage(this.A, this.B);
            this.S = this.R.getGraphics();
            if (i == 0) {
                return;
            }
            continue;
        }
    }
    
    void a() {
        this.b = new MemoryImageSource(this.y, this.z, new DirectColorModel(24, 16711680, 65280, 255), this.E, 0, this.y);
        String s;
        try {
            s = System.getProperty(c("1\"`\u007f/-&dmh4-"));
        }
        catch (SecurityException ex) {
            s = c(".-}");
        }
        if (!s.startsWith(c("jm&"))) {
            try {
                this.b.setAnimated(true);
                this.b.setFullBufferUpdates(true);
                this.G = this.createImage(this.b);
                this.b.newPixels();
                this.c = true;
            }
            catch (NoSuchMethodError noSuchMethodError) {
                this.c = false;
            }
        }
        if (!this.c) {
            this.b = null;
            this.a = new anfy(this.y, this.z, new DirectColorModel(24, 16711680, 65280, 255), this.E, 0, this.y);
            this.G = this.createImage(this.a);
        }
    }
    
    private final synchronized boolean b() {
        new MediaTracker(this);
        for (int i = 0; i < 2; ++i) {
            this.bo[i] = this.a(this.Q[i]);
            if (this.bo[i] == null) {
                this.showStatus(c("\u001e1dqs{/y\u007fe2-q>h6\"q{!") + String.valueOf(i + 1));
                return false;
            }
            this.bp[i] = true;
            this.bm[i] = this.bo[i].getWidth(this);
            this.bn[i] = this.bo[i].getHeight(this);
            if (i != 0 && (this.bm[i] != this.bm[i - 1] || this.bn[i] != this.bn[i - 1])) {
                this.showStatus(c("\u001e1dqszc_s`<&e>L\u000e\u0010B>c>cbvd{0wsd{0\u007fddz"));
            }
            if (!this.a(this.bo[i], this.F[i])) {
                return false;
            }
            if (i == 0) {
                this.copytoBack(0);
                try {
                    System.arraycopy(this.V, 0, this.E, 0, this.u);
                }
                catch (ArrayIndexOutOfBoundsException ex) {}
                catch (ArrayStoreException ex2) {}
                try {
                    this.producefixed();
                }
                catch (NoSuchMethodError noSuchMethodError) {}
                this.repaint();
            }
        }
        this.bo[1].flush();
        this.bo[1] = null;
        System.gc();
        return true;
    }
    
    private final synchronized boolean a(final int n) {
        new MediaTracker(this);
        final Image a = this.a(this.Q[n]);
        if (a == null) {
            this.showStatus(c("\u001e1dqs{/y\u007fe2-q>h6\"q{!") + String.valueOf(n + 1));
            return false;
        }
        this.bp[n] = true;
        if (!this.a(a, this.F[n])) {
            return false;
        }
        a.flush();
        System.gc();
        return true;
    }
    
    private boolean a(final Image image, final int[] array) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.W, this.X, array, 0, this.W);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            return false;
        }
        return true;
    }
    
    public void start() {
        if (this.k == null) {
            (this.k = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.k != null && this.k.isAlive()) {
            this.k.stop();
        }
        this.k = null;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.p) {
            if (n == 16) {
                this.bq = true;
            }
            return true;
        }
        return true;
    }
    
    public void destroy() {
        if (this.l != null) {
            this.l.hide();
        }
        this.l = null;
        if (this.p != null) {
            this.p.flush();
        }
        this.p = null;
        if (this.R != null) {
            this.R.flush();
        }
        this.R = null;
        if (this.S != null) {
            this.S.dispose();
        }
        this.S = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.br) {
            this.notifyAll();
            while (!this.bq) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.bq = false;
        }
        this.S.drawImage(this.p, this.q, this.r, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.p, this);
        if (this.c) {
            for (int i = 0; i < 3; ++i) {
                this.notifyAll();
                Thread.yield();
                try {
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            return this.bq;
        }
        return false;
    }
    
    private final void c() {
        while (true) {
            this.showStatus(c("\u001f,x9u{1ssn-&6iv,mwpg\"7s\u007flu ys!81szh/06rh5&6wo{\u000bBSMz"));
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
    
    public void run() {
        this.k.setPriority(this.o);
        this.showStatus("");
        this.f = System.currentTimeMillis();
        if (!this.bp[0]) {
            this.b();
        }
        this.showStatus("");
        this.bk = this.W;
        System.gc();
        this.repaint();
        final long n = this.C - (System.currentTimeMillis() - this.f);
        if (n > 0L) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
        final Graphics graphics = this.getGraphics();
        if (this.p != null && !this.br) {
            this.br = this.CheckAniGIF();
        }
        if (!this.w[this.j].equalsIgnoreCase(c("\u0015\f"))) {
            this.t.setCursor(12);
        }
        else {
            this.t.setCursor(0);
        }
        this.l.dr(c(":-pg"), 1, this.bt);
        while (this.k != null) {
            if (this.by && this.bw > 0) {
                try {
                    Thread.sleep(this.C);
                }
                catch (InterruptedException ex2) {}
                this.by = false;
            }
            ++this.bw;
            try {
                System.arraycopy(this.V, 0, this.E, 0, this.u);
            }
            catch (ArrayIndexOutOfBoundsException ex3) {}
            catch (ArrayStoreException ex4) {}
            this.DoProc();
            if (this.g > 0) {
                switch (this.bi) {
                    case 1: {
                        this.setpoints1();
                        break;
                    }
                    case 2: {
                        this.setpoints2();
                        break;
                    }
                    case 3: {
                        this.setpoints3();
                        break;
                    }
                    case 4: {
                        this.setpoints4();
                        break;
                    }
                }
            }
            else {
                switch (this.bi) {
                    case 1: {
                        this.Nsetpoints1();
                        break;
                    }
                    case 2: {
                        this.Nsetpoints2();
                        break;
                    }
                    case 3: {
                        this.Nsetpoints3();
                        break;
                    }
                    case 4: {
                        this.Nsetpoints4();
                        break;
                    }
                }
            }
            if (++this.m == this.n) {
                System.gc();
                this.m = 0;
            }
            try {
                this.producefixed();
            }
            catch (NoSuchMethodError noSuchMethodError) {}
            if (this.D == 1) {
                this.S.drawImage(this.G, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.S.drawImage(this.G, 0, 0, this.A, this.B, this);
            }
            if (this.p != null) {
                this.prepaniframe();
            }
            graphics.drawImage(this.R, 0, 0, this);
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
        this.prepareImage(this.G, this.A, this.B, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.G, this.A, this.B, this);
        }
    }
    
    public synchronized void prepscaled0() {
        int checkImage = 0;
        this.prepareImage(this.bo[0], this.A, this.B, this);
        this.notifyAll();
        while ((checkImage & 0xF0) == 0x0) {
            Thread.yield();
            checkImage = this.checkImage(this.bo[0], this.A, this.B, this);
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
        if (this.G != null) {
            if (this.D == 1) {
                this.S.drawImage(this.G, 0, 0, this);
            }
            else {
                this.prepscaled();
                this.S.drawImage(this.G, 0, 0, this.A, this.B, this);
            }
            if (this.p != null) {
                this.prepaniframe();
            }
            graphics.drawImage(this.R, 0, 0, this);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void createTab() {
        this.bg = this.bf / 2;
        this.bc = new int[this.bf];
        this.bd = new int[this.bf];
        final double n = 3.141592653589793 / (this.bf * 2);
        final double n2 = 4.71238898038469;
        for (int i = 0; i < this.bf; ++i) {
            this.bc[i] = (int)(Math.cos(i * n + n2) * this.bg);
            this.bd[i] = (int)(this.bg + Math.sin(i * n + n2) * this.bg);
        }
        this.be = new float[this.bf];
        final int n3 = this.bf / 2;
        final float n4 = this.g / n3;
        for (int j = 0; j < n3; ++j) {
            this.be[j] = (255.0f - j * n4) / 255.0f;
        }
        for (int k = n3 - 1; k > -1; --k) {
            this.be[n3 + k] = this.be[n3 - k - 1];
        }
        final float n5 = this.h / n3;
        for (int l = 0; l < n3; ++l) {
            this.be[l] = (255.0f - l * n5) / 255.0f;
        }
    }
    
    public void setpoints1() {
        final int n = this.bk + this.bf;
        final int n2 = this.bk + this.bg;
        final int n3 = this.bg - this.bk - this.bf + this.bl;
        final float[] be = this.be;
        final int[] e = this.E;
        final int[] ba = this.bA;
        final int[] bc = this.bc;
        final int[] bd = this.bd;
        for (int i = this.z - 1; i > -1; --i) {
            for (int j = 0; j < this.y; ++j) {
                if (j - i > this.bk) {
                    if (j - i > n) {
                        final int n4 = i + n2;
                        final int n5 = j + n3;
                        if (n4 < this.W && n4 > -1 && n5 < this.z && n5 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                            e[n4 + n5 * this.y] = ba[j + i * this.W];
                        }
                    }
                    else {
                        final int n6 = j - i - this.bk;
                        if (n6 <= this.bf - 1) {
                            final int n7 = i + this.bk + bc[n6];
                            final int n8 = i + bd[n6] + this.bl;
                            if (n7 < this.W && n7 > -1 && n8 < this.z && n8 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                                final int n9 = ba[j + i * this.W];
                                e[n7 + n8 * this.y] = (((int)((n9 & 0xFF0000) * be[n6]) & 0xFF0000) | ((int)((n9 & 0xFF00) * be[n6]) & 0xFF00) | ((int)((n9 & 0xFF) * be[n6]) & 0xFF));
                            }
                        }
                    }
                }
                else {
                    final int n10 = j;
                    final int n11 = i + this.bl;
                    if (n10 < this.W && n10 > -1 && n11 < this.z && n11 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                        e[n10 + n11 * this.y] = ba[j + i * this.W];
                    }
                }
            }
        }
    }
    
    public void setpoints3() {
        final int n = this.bk + this.bf;
        final int n2 = this.bk + this.bg;
        final int n3 = this.bg - this.bk - this.bf + this.bl;
        final int n4 = this.y - 1;
        final int n5 = this.W - 1;
        final float[] be = this.be;
        final int[] e = this.E;
        final int[] ba = this.bA;
        final int[] bc = this.bc;
        final int[] bd = this.bd;
        for (int i = this.z - 1; i > -1; --i) {
            for (int j = 0; j < this.y; ++j) {
                if (j - i > this.bk) {
                    if (j - i > n) {
                        final int n6 = i + n2;
                        final int n7 = j + n3;
                        if (n6 < this.W && n6 > -1 && n7 < this.z && n7 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                            e[n4 - n6 + (this.z - 1 - n7) * this.y] = ba[n5 - j + (this.X - 1 - i) * this.W];
                        }
                    }
                    else {
                        final int n8 = j - i - this.bk;
                        if (n8 <= this.bf - 1) {
                            final int n9 = i + this.bk + bc[n8];
                            final int n10 = i + bd[n8] + this.bl;
                            if (n9 < this.W && n9 > -1 && n10 < this.z && n10 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                                final int n11 = ba[n5 - j + (this.X - 1 - i) * this.W];
                                e[n4 - n9 + (this.z - 1 - n10) * this.y] = (((int)((n11 & 0xFF0000) * be[n8]) & 0xFF0000) | ((int)((n11 & 0xFF00) * be[n8]) & 0xFF00) | ((int)((n11 & 0xFF) * be[n8]) & 0xFF));
                            }
                        }
                    }
                }
                else {
                    final int n12 = j;
                    final int n13 = i + this.bl;
                    if (n12 < this.W && n12 > -1 && n13 < this.z && n13 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                        e[n4 - n12 + (this.z - n13 - 1) * this.y] = ba[n5 - j + (this.X - 1 - i) * this.W];
                    }
                }
            }
        }
    }
    
    public void setpoints4() {
        final int n = this.bk + this.bf;
        final int n2 = this.bk + this.bg;
        final int n3 = this.bg - this.bk - this.bf + this.bl;
        final int n4 = this.y - 1;
        final int n5 = this.W - 1;
        final float[] be = this.be;
        final int[] e = this.E;
        final int[] ba = this.bA;
        final int[] bc = this.bc;
        final int[] bd = this.bd;
        for (int i = this.z - 1; i > -1; --i) {
            for (int j = 0; j < this.y; ++j) {
                if (j - i > this.bk) {
                    if (j - i > n) {
                        final int n6 = i + n2;
                        final int n7 = j + n3;
                        if (n6 < this.W && n6 > -1 && n7 < this.z && n7 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                            e[n4 - n6 + n7 * this.y] = ba[n5 - j + i * this.W];
                        }
                    }
                    else {
                        final int n8 = j - i - this.bk;
                        if (n8 <= this.bf - 1) {
                            final int n9 = i + this.bk + bc[n8];
                            final int n10 = i + bd[n8] + this.bl;
                            if (n9 < this.W && n9 > -1 && n10 < this.z && n10 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                                final int n11 = ba[n5 - j + i * this.W];
                                e[n4 - n9 + n10 * this.y] = (((int)((n11 & 0xFF0000) * be[n8]) & 0xFF0000) | ((int)((n11 & 0xFF00) * be[n8]) & 0xFF00) | ((int)((n11 & 0xFF) * be[n8]) & 0xFF));
                            }
                        }
                    }
                }
                else {
                    final int n12 = j;
                    final int n13 = i + this.bl;
                    if (n12 < this.W && n12 > -1 && n13 < this.z && n13 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                        e[n4 - n12 + n13 * this.y] = ba[n5 - j + i * this.W];
                    }
                }
            }
        }
    }
    
    public void setpoints2() {
        final int n = this.bk + this.bf;
        final int n2 = this.bk + this.bg;
        final int n3 = this.bg - this.bk - this.bf + this.bl;
        final float[] be = this.be;
        final int[] e = this.E;
        final int[] ba = this.bA;
        final int[] bc = this.bc;
        final int[] bd = this.bd;
        for (int i = this.z - 1; i > -1; --i) {
            for (int j = 0; j < this.y; ++j) {
                if (j - i > this.bk) {
                    if (j - i > n) {
                        final int n4 = i + n2;
                        final int n5 = j + n3;
                        if (n4 < this.W && n4 > -1 && n5 < this.z && n5 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                            e[n4 + (this.z - n5 - 1) * this.y] = ba[j + (this.X - 1 - i) * this.W];
                        }
                    }
                    else {
                        final int n6 = j - i - this.bk;
                        if (n6 <= this.bf - 1) {
                            final int n7 = i + this.bk + bc[n6];
                            final int n8 = i + bd[n6] + this.bl;
                            if (n7 < this.W && n7 > -1 && n8 < this.z && n8 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                                final int n9 = ba[j + (this.X - 1 - i) * this.W];
                                e[n7 + (this.z - 1 - n8) * this.y] = (((int)((n9 & 0xFF0000) * be[n6]) & 0xFF0000) | ((int)((n9 & 0xFF00) * be[n6]) & 0xFF00) | ((int)((n9 & 0xFF) * be[n6]) & 0xFF));
                            }
                        }
                    }
                }
                else {
                    final int n10 = j;
                    final int n11 = i + this.bl;
                    if (n10 < this.W && n10 > -1 && n11 < this.z && n11 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                        e[n10 + (this.z - n11 - 1) * this.y] = ba[j + (this.X - 1 - i) * this.W];
                    }
                }
            }
        }
    }
    
    public void Nsetpoints1() {
        final int n = this.bk + this.bf;
        final int n2 = this.bk + this.bg;
        final int n3 = this.bg - this.bk - this.bf + this.bl;
        final int[] e = this.E;
        final int[] ba = this.bA;
        final int[] bc = this.bc;
        final int[] bd = this.bd;
        for (int i = this.z - 1; i > -1; --i) {
            for (int j = 0; j < this.y; ++j) {
                if (j - i > this.bk) {
                    if (j - i > n) {
                        final int n4 = i + n2;
                        final int n5 = j + n3;
                        if (n4 < this.W && n4 > -1 && n5 < this.z && n5 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                            e[n4 + n5 * this.y] = ba[j + i * this.W];
                        }
                    }
                    else {
                        final int n6 = j - i - this.bk;
                        if (n6 <= this.bf - 1) {
                            final int n7 = i + this.bk + bc[n6];
                            final int n8 = i + bd[n6] + this.bl;
                            if (n7 < this.W && n7 > -1 && n8 < this.z && n8 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                                e[n7 + n8 * this.y] = ba[j + i * this.W];
                            }
                        }
                    }
                }
                else {
                    final int n9 = j;
                    final int n10 = i + this.bl;
                    if (n9 < this.W && n9 > -1 && n10 < this.z && n10 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                        e[n9 + n10 * this.y] = ba[j + i * this.W];
                    }
                }
            }
        }
    }
    
    public void Nsetpoints3() {
        final int n = this.bk + this.bf;
        final int n2 = this.bk + this.bg;
        final int n3 = this.bg - this.bk - this.bf + this.bl;
        final int n4 = this.y - 1;
        final int n5 = this.W - 1;
        final int[] e = this.E;
        final int[] ba = this.bA;
        final int[] bc = this.bc;
        final int[] bd = this.bd;
        for (int i = this.z - 1; i > -1; --i) {
            for (int j = 0; j < this.y; ++j) {
                if (j - i > this.bk) {
                    if (j - i > n) {
                        final int n6 = i + n2;
                        final int n7 = j + n3;
                        if (n6 < this.W && n6 > -1 && n7 < this.z && n7 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                            e[n4 - n6 + (this.z - 1 - n7) * this.y] = ba[n5 - j + (this.X - 1 - i) * this.W];
                        }
                    }
                    else {
                        final int n8 = j - i - this.bk;
                        if (n8 <= this.bf - 1) {
                            final int n9 = i + this.bk + bc[n8];
                            final int n10 = i + bd[n8] + this.bl;
                            if (n9 < this.W && n9 > -1 && n10 < this.z && n10 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                                e[n4 - n9 + (this.z - 1 - n10) * this.y] = ba[n5 - j + (this.X - 1 - i) * this.W];
                            }
                        }
                    }
                }
                else {
                    final int n11 = j;
                    final int n12 = i + this.bl;
                    if (n11 < this.W && n11 > -1 && n12 < this.z && n12 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                        e[n4 - n11 + (this.z - n12 - 1) * this.y] = ba[n5 - j + (this.X - 1 - i) * this.W];
                    }
                }
            }
        }
    }
    
    public void Nsetpoints4() {
        final int n = this.bk + this.bf;
        final int n2 = this.bk + this.bg;
        final int n3 = this.bg - this.bk - this.bf + this.bl;
        final int n4 = this.y - 1;
        final int n5 = this.W - 1;
        final int[] e = this.E;
        final int[] ba = this.bA;
        final int[] bc = this.bc;
        final int[] bd = this.bd;
        for (int i = this.z - 1; i > -1; --i) {
            for (int j = 0; j < this.y; ++j) {
                if (j - i > this.bk) {
                    if (j - i > n) {
                        final int n6 = i + n2;
                        final int n7 = j + n3;
                        if (n6 < this.W && n6 > -1 && n7 < this.z && n7 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                            e[n4 - n6 + n7 * this.y] = ba[n5 - j + i * this.W];
                        }
                    }
                    else {
                        final int n8 = j - i - this.bk;
                        if (n8 <= this.bf - 1) {
                            final int n9 = i + this.bk + bc[n8];
                            final int n10 = i + bd[n8] + this.bl;
                            if (n9 < this.W && n9 > -1 && n10 < this.z && n10 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                                e[n4 - n9 + n10 * this.y] = ba[n5 - j + i * this.W];
                            }
                        }
                    }
                }
                else {
                    final int n11 = j;
                    final int n12 = i + this.bl;
                    if (n11 < this.W && n11 > -1 && n12 < this.z && n12 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                        e[n4 - n11 + n12 * this.y] = ba[n5 - j + i * this.W];
                    }
                }
            }
        }
    }
    
    public void Nsetpoints2() {
        final int n = this.bk + this.bf;
        final int n2 = this.bk + this.bg;
        final int n3 = this.bg - this.bk - this.bf + this.bl;
        final int[] e = this.E;
        final int[] ba = this.bA;
        final int[] bc = this.bc;
        final int[] bd = this.bd;
        for (int i = this.z - 1; i > -1; --i) {
            for (int j = 0; j < this.y; ++j) {
                if (j - i > this.bk) {
                    if (j - i > n) {
                        final int n4 = i + n2;
                        final int n5 = j + n3;
                        if (n4 < this.W && n4 > -1 && n5 < this.z && n5 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                            e[n4 + (this.z - n5 - 1) * this.y] = ba[j + (this.X - 1 - i) * this.W];
                        }
                    }
                    else {
                        final int n6 = j - i - this.bk;
                        if (n6 <= this.bf - 1) {
                            final int n7 = i + this.bk + bc[n6];
                            final int n8 = i + bd[n6] + this.bl;
                            if (n7 < this.W && n7 > -1 && n8 < this.z && n8 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                                e[n7 + (this.z - 1 - n8) * this.y] = ba[j + (this.X - 1 - i) * this.W];
                            }
                        }
                    }
                }
                else {
                    final int n9 = j;
                    final int n10 = i + this.bl;
                    if (n9 < this.W && n9 > -1 && n10 < this.z && n10 > -1 && j > -1 && j < this.W && i > -1 && i < this.X) {
                        e[n9 + (this.z - n10 - 1) * this.y] = ba[j + (this.X - 1 - i) * this.W];
                    }
                }
            }
        }
    }
    
    public void copytoBack(final int n) {
        final int n2 = (this.y - this.W) / 2 + (this.z - this.X) / 2 * this.y;
        final int[] array = this.F[n];
        final int[] v = this.V;
        final int v2 = this.v;
        final int n3 = n2;
        try {
            System.arraycopy(array, 0, v, n3, v2);
        }
        catch (ArrayIndexOutOfBoundsException ex) {}
        catch (ArrayStoreException ex2) {}
        this.bh = -1;
        this.by = true;
    }
    
    public void DoProc() {
        if (this.bh == -1) {
            if (!this.bp[this.bb[this.Z]]) {
                this.a(this.bb[this.Z]);
            }
            switch (this.ba[this.Z]) {
                case 0: {
                    this.bh = 21;
                    this.bi = 1;
                    this.T = this.bb[this.Z];
                    break;
                }
                case 1: {
                    this.bh = 21;
                    this.bi = 2;
                    this.T = this.bb[this.Z];
                    break;
                }
                case 2: {
                    this.bh = 21;
                    this.bi = 3;
                    this.T = this.bb[this.Z];
                    break;
                }
                case 3: {
                    this.bh = 21;
                    this.bi = 4;
                    this.T = this.bb[this.Z];
                    break;
                }
                case 4: {
                    this.bh = 11;
                    this.bi = 1;
                    this.T = this.bb[this.Z];
                    break;
                }
                case 5: {
                    this.bh = 11;
                    this.bi = 2;
                    this.T = this.bb[this.Z];
                    break;
                }
                case 6: {
                    this.bh = 11;
                    this.bi = 3;
                    this.T = this.bb[this.Z];
                    break;
                }
                case 7: {
                    this.bh = 11;
                    this.bi = 4;
                    this.T = this.bb[this.Z];
                    break;
                }
                case 8: {
                    this.copytoBack(this.bb[this.Z]);
                    break;
                }
            }
            ++this.Z;
            if (this.Z >= this.Y) {
                this.Z = 0;
            }
            this.bz = this.Z / 2 + 1;
            if (this.bB) {
                this.showStatus(this.x[this.j]);
            }
            if (!this.w[this.j].equalsIgnoreCase(c("\u0015\f"))) {
                this.t.setCursor(12);
            }
            else {
                this.t.setCursor(0);
            }
            this.bA = this.F[this.T];
        }
        if (this.bh == 11) {
            this.bk = this.W;
            this.bh = 1;
        }
        if (this.bh == 1) {
            this.bk -= this.bj;
            if (this.bk < -1 * this.X) {
                this.j = this.bz - 1;
            }
            if (this.bk < -2 * this.X) {
                this.bh = -1;
            }
        }
        if (this.bh == 21) {
            this.bk = -2 * this.X;
            this.bh = 2;
        }
        if (this.bh == 2) {
            this.bk += this.bj;
            if (this.bk > this.y / 2) {
                this.j = this.bz - 1;
            }
            if (this.bk > this.y) {
                this.bh = -1;
            }
        }
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        if (!this.w[this.j].equalsIgnoreCase(c("\u0015\f"))) {
            this.t.setCursor(12);
        }
        else {
            this.t.setCursor(0);
        }
        this.bB = true;
        this.showStatus(this.x[this.j]);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.bB = false;
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseMove(final Event event, final int n, final int n2) {
        if (this.bC != this.j) {
            this.showStatus(this.x[this.j]);
        }
        this.bC = this.j;
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int bd = bookflip.bD;
        final boolean bt = this.bt;
        boolean equalsIgnoreCase = false;
        Label_0086: {
            if (bd != 0) {
                break Label_0086;
            }
            if (!bt) {
                this.l.show();
                try {
                    this.l.move(100, 100);
                }
                catch (Exception ex) {}
                this.l.toFront();
                this.l.requestFocus();
                if (bd == 0) {
                    break Label_0086;
                }
            }
            try {
                this.bu = null;
                equalsIgnoreCase = this.w[this.j].equalsIgnoreCase(c("\u0015\f"));
                if (bd != 0) {
                    return equalsIgnoreCase;
                }
                if (!bt) {
                    try {
                        this.bu = new URL(this.getDocumentBase(), this.w[this.j]);
                    }
                    catch (MalformedURLException ex2) {
                        this.showStatus(c("\u001e1dqs{/\u007fpj2-q"));
                        return true;
                    }
                    bookflip bookflip = this;
                    Label_0153: {
                        if (bd != 0) {
                            break Label_0153;
                        }
                        if (this.bu == null) {
                            break Label_0086;
                        }
                        try {
                            this.l.dck();
                            bookflip bookflip2 = this;
                            bookflip = this;
                            if (bd == 0) {
                                if (bookflip.bv) {
                                    this.getAppletContext().showDocument(this.bu, this.getParameter(c(")&qxs:.sp`6&")));
                                    if (bd == 0) {
                                        break Label_0086;
                                    }
                                }
                                bookflip2 = this;
                            }
                            bookflip2.getAppletContext().showDocument(this.bu);
                        }
                        catch (Exception ex3) {}
                    }
                }
            }
            catch (Exception ex4) {}
        }
        return equalsIgnoreCase;
    }
    
    public bookflip() {
        this.c = false;
        this.s = false;
        this.D = 1;
        this.Z = 1;
        this.bf = 50;
        this.bh = -1;
        this.bi = 9;
        this.bq = false;
        this.br = false;
        this.bt = false;
        this.bv = false;
        this.by = false;
        this.bz = 1;
        this.bB = false;
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
                    c2 = '[';
                    break;
                }
                case 1: {
                    c2 = 'C';
                    break;
                }
                case 2: {
                    c2 = '\u0016';
                    break;
                }
                case 3: {
                    c2 = '\u001e';
                    break;
                }
                default: {
                    c2 = '\u0001';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
