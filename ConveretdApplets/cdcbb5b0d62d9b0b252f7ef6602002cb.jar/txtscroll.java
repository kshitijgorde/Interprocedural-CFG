import java.awt.Event;
import java.io.DataInputStream;
import java.awt.FontMetrics;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.io.InputStream;
import java.awt.Component;
import java.awt.MediaTracker;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.net.MalformedURLException;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class txtscroll extends Applet implements Runnable, ImageObserver
{
    Thread a;
    Lware b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    private Image j;
    int k;
    int l;
    int m;
    long n;
    Frame o;
    boolean p;
    boolean q;
    Color r;
    String s;
    String t;
    private Image u;
    private Graphics v;
    Image w;
    private Image x;
    private Graphics y;
    boolean z;
    boolean A;
    final String B = "<\b\u0002bA\tX\u0010w\u0004;\u0019\u0010gK];\u001b{G\u001e\u0011R&S\n\u000f\\oJ\u001b\u0001";
    boolean C;
    URL D;
    boolean E;
    Toolkit F;
    int G;
    int H;
    int I;
    int J;
    int K;
    int L;
    int M;
    String N;
    int O;
    boolean P;
    boolean Q;
    Color R;
    Color S;
    Font T;
    int U;
    int V;
    int W;
    int X;
    int[] Y;
    int[] Z;
    String[] ba;
    int bb;
    int bc;
    Font[] bd;
    int be;
    int bf;
    private int bg;
    int bh;
    int bi;
    int bj;
    int bk;
    int bl;
    int[] bm;
    int bn;
    int bo;
    float bp;
    float bq;
    int br;
    int bs;
    String bt;
    public static int bu;
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.j) {
            if (n == 16) {
                this.z = true;
            }
            return true;
        }
        return true;
    }
    
    public void destroy() {
        if (this.b != null) {
            this.b.hide();
        }
        this.b = null;
        if (this.j != null) {
            this.j.flush();
        }
        this.j = null;
        if (this.x != null) {
            this.x.flush();
        }
        this.x = null;
        if (this.y != null) {
            this.y.dispose();
        }
        this.y = null;
        System.gc();
    }
    
    public synchronized void prepaniframe() {
        if (this.A) {
            this.notifyAll();
            while (!this.z) {
                Thread.yield();
                try {
                    Thread.sleep(8L);
                }
                catch (InterruptedException ex) {}
            }
            this.z = false;
        }
        this.y.drawImage(this.j, this.k, this.l, this);
    }
    
    public synchronized boolean CheckAniGIF() {
        this.prepareImage(this.j, this);
        for (int i = 0; i < 3; ++i) {
            this.notifyAll();
            Thread.yield();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        return this.z;
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
                this.showStatus(c("4\u0015\u0013iA]") + s + c("]\u0016\u001dz\u0004\u001b\u0017\u0007`@\\"));
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
            this.showStatus(c("9\u0017\u001c)P]\n\u0017cK\u000b\u001dRyS\nV\u0013`B\u0004\f\u0017oIS\u001b\u001dc\u0004\u001e\n\u0017jM\t\u000bRbM\u0013\u001dRgJ]0&Ch\\"));
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
        final int bu = txtscroll.bu;
        this.setLayout(null);
        this.addNotify();
        this.bt = this.getParameter(c("\u000e\f\u0013zQ\u000e\u0015\u0001i"));
        final Dimension size = this.size();
        this.d = size.width;
        this.e = size.height;
        this.f = size.width;
        this.g = size.height;
        final String parameter;
        final String s = parameter = this.getParameter(c("\u001e\n\u0017jM\t\u000b"));
        String c = null;
        Label_0146: {
            Label_0115: {
                Label_0111: {
                    if (bu == 0) {
                        if (parameter == null) {
                            break Label_0111;
                        }
                        final String s2;
                        final String protocol = s2 = s;
                        if (bu != 0) {
                            break Label_0146;
                        }
                    }
                    if (parameter.startsWith(c("<\b\u0002bA\tX\u0010w\u0004;\u0019\u0010gK];\u001b{G\u001e\u0011R&S\n\u000f\\oJ\u001b\u0001"))) {
                        break Label_0115;
                    }
                    this.a();
                    if (bu == 0) {
                        break Label_0115;
                    }
                }
                this.a();
            }
            (this.b = new Lware(this, c(")\u001d\nzw\u001e\n\u001dbH]\u0019\u0002~H\u0018\f"))).hide();
            try {
                final String protocol = this.getDocumentBase().getProtocol();
                c = protocol;
            }
            catch (SecurityException ex) {
                c = c("\u001b\u0011\u001ek");
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
        Label_0689: {
            Label_0680: {
                int n3 = 0;
                int startsWith = 0;
                Label_0272: {
                    Label_0262: {
                        if (bu == 0) {
                            Label_0252: {
                                if (!c.equals(c("\u001b\u0011\u001ek"))) {
                                    int n2;
                                    final int n = n2 = (startsWith = (n3 = s3.length()));
                                    if (bu == 0) {
                                        if (n < 1) {
                                            break Label_0252;
                                        }
                                        final int n4;
                                        n2 = (n4 = (startsWith = (n3 = (s3.startsWith(c("\u0011\u0017\u0011oH")) ? 1 : 0))));
                                    }
                                    if (bu == 0) {
                                        if (n != 0) {
                                            break Label_0252;
                                        }
                                        startsWith = (n2 = (n3 = (s3.equals(c("LJE \u0014SH\\?")) ? 1 : 0)));
                                    }
                                    if (bu != 0) {
                                        break Label_0272;
                                    }
                                    if (n2 == 0) {
                                        break Label_0262;
                                    }
                                }
                            }
                            this.C = true;
                        }
                        if (bu == 0) {
                            break Label_0680;
                        }
                    }
                    n3 = (startsWith = (s3.startsWith(c("\n\u000f\u0005 ")) ? 1 : 0));
                }
                if (bu == 0) {
                    if (startsWith != 0) {
                        s3 = s3.substring(4);
                    }
                    n3 = s3.length();
                }
                final int n6;
                final int n5 = n6 = n3;
                if (bu != 0 || n6 > 0) {
                    final char[] array = new char[n6];
                    s3.getChars(0, n5, array, 0);
                    int n7 = 0;
                    while (true) {
                        while (true) {
                            Label_0355: {
                                if (bu == 0) {
                                    break Label_0355;
                                }
                                final char[] array2 = array;
                                final int n8 = n7;
                                if (bu != 0 || array2[n8] == '0') {
                                    array2[n8] = '1';
                                }
                                n7 += 5;
                            }
                            if (n7 < n5) {
                                continue;
                            }
                            break;
                        }
                        if (bu != 0) {
                            continue;
                        }
                        break;
                    }
                    s3 = new String(array);
                }
                final String s4 = parameter2 = this.getParameter(c("\u000f\u001d\u0015mK\u0019\u001d"));
                if (bu != 0) {
                    break Label_0689;
                }
                if (parameter2 != null) {
                    final String s5 = s4;
                    if (bu != 0) {
                        break Label_0689;
                    }
                    if (s5.length() > 5) {
                        s4.toLowerCase();
                        int n9 = 1;
                        try {
                            int n10 = 0;
                            while (true) {
                                while (true) {
                                    Label_0451: {
                                        if (bu == 0) {
                                            break Label_0451;
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
                                if (bu != 0) {
                                    continue;
                                }
                                break;
                            }
                        }
                        catch (StringIndexOutOfBoundsException ex3) {}
                        final int[] array3 = new int[n9];
                        final int n11 = n9;
                        if (bu == 0 && n11 == 1) {
                            array3[0] = s4.length();
                            if (bu != 0) {
                                goto Label_0501;
                            }
                        }
                        else {
                            int n12 = n11;
                            try {
                                int n13 = 0;
                                while (true) {
                                    while (true) {
                                        Label_0537: {
                                            if (bu == 0) {
                                                break Label_0537;
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
                                    if (bu != 0) {
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
                        int i;
                        while (true) {
                            while (true) {
                                Label_0617: {
                                    if (bu == 0) {
                                        break Label_0617;
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
                            i = 0;
                            if (bu != 0) {
                                if (bu != 0) {
                                    continue;
                                }
                            }
                            break;
                        }
                        while (i < n9) {
                            if (s3.equals(this.b.dr(array4[i], 0, this.C))) {
                                this.C = true;
                            }
                            ++i;
                        }
                    }
                }
            }
            this.getParameter(c("\u000f\u001d\u0015bM\u0013\u0013"));
        }
        final String s7;
        final String s6 = s7 = parameter2;
        Label_0757: {
            if (bu == 0) {
                if (s7 != null) {
                    final String s8 = s6;
                    if (bu != 0) {
                        break Label_0757;
                    }
                    if (!s8.equalsIgnoreCase(c("37"))) {
                        try {
                            this.D = new URL(this.getDocumentBase(), s6);
                        }
                        catch (MalformedURLException ex6) {
                            this.D = null;
                        }
                    }
                }
                this.getParameter(c("\u000f\u001d\u0015`A\n\u001e\u0000oI\u0018"));
            }
        }
        if (s7.equalsIgnoreCase(c("$=!"))) {
            this.E = true;
        }
        Container parent = this.getParent();
        while (true) {
            while (true) {
                Label_0798: {
                    if (bu == 0) {
                        break Label_0798;
                    }
                    final Container parent2 = ((Frame)parent).getParent();
                    parent = parent2;
                }
                if (!(parent instanceof Frame)) {
                    continue;
                }
                break;
            }
            (this.o = (Frame)parent).setCursor(3);
            final Container parent2 = this;
            if (bu == 0) {
                final String parameter3;
                final String s9 = parameter3 = this.getParameter(c("\u0012\u000e\u0017|M\u0010\u001f"));
                Label_0972: {
                    if (bu == 0) {
                        txtscroll txtscroll = null;
                        Label_0964: {
                            if (parameter3 != null) {
                                final String s10 = s9;
                                if (bu != 0) {
                                    break Label_0972;
                                }
                                if (!s10.equalsIgnoreCase(c("37"))) {
                                    this.j = this.a(s9);
                                    txtscroll = this;
                                    if (bu != 0) {
                                        break Label_0964;
                                    }
                                    if (this.j != null) {
                                        final String parameter4;
                                        String s11 = parameter4 = this.getParameter(c("\u0012\u000e\u0017|M\u0010\u001f*"));
                                        if (bu == 0) {
                                            if (parameter4 == null) {
                                                s11 = "0";
                                            }
                                            this.k = Integer.valueOf(s11);
                                            this.getParameter(c("\u0012\u000e\u0017|M\u0010\u001f+"));
                                        }
                                        final String s13;
                                        String s12 = s13 = parameter4;
                                        if (bu != 0 || s13 == null) {
                                            s12 = s13;
                                        }
                                        this.l = Integer.valueOf(s12);
                                    }
                                }
                            }
                            txtscroll = this;
                        }
                        txtscroll.getParameter(c("\u001f\u0019\u0011eM\u0010\u0019\u0015k"));
                    }
                }
                final String s14 = parameter3;
                txtscroll txtscroll2 = null;
                Label_1040: {
                    Label_1039: {
                        Label_1007: {
                            if (bu == 0) {
                                if (s14 != null && !s14.equalsIgnoreCase(c("37"))) {
                                    break Label_1007;
                                }
                                this.p = false;
                            }
                            if (bu == 0) {
                                break Label_1039;
                            }
                        }
                        this.p = true;
                        this.w = this.a(s14);
                        txtscroll2 = this;
                        if (bu != 0) {
                            break Label_1040;
                        }
                        if (this.w == null) {
                            this.p = false;
                        }
                    }
                    txtscroll2 = this;
                }
                this.r = new Color(Integer.valueOf(txtscroll2.getParameter(c("\u001f\u0019\u0011ev"))), Integer.valueOf(this.getParameter(c("\u001f\u0019\u0011ec"))), Integer.valueOf(this.getParameter(c("\u001f\u0019\u0011ef"))));
                this.s = this.getParameter(c("\u0010\u001d\u001fjA\u0011\u0019\u000b"));
                this.t = this.getParameter(c("\r\n\u001baV\u0014\f\u000b"));
                this.h = Integer.valueOf(this.s);
                this.i = Integer.valueOf(this.t);
                this.t = null;
                this.t = this.getParameter(c("0\u0011\u001c]}3;"));
                this.m = Integer.valueOf(this.t);
                int n17;
                final int n16 = n17 = this.h;
                if (bu == 0) {
                    if (n16 < 0) {
                        this.h = 0;
                    }
                    final int j;
                    n17 = (j = this.i);
                }
                final int n18 = 10;
                Label_1323: {
                    txtscroll txtscroll3 = null;
                    Label_1308: {
                        int u = 0;
                        Label_1278: {
                            Label_1265: {
                                if (bu == 0) {
                                    if (n16 > n18) {
                                        this.i = 10;
                                        if (bu == 0) {
                                            break Label_1265;
                                        }
                                    }
                                    u = (n17 = this.i);
                                    if (bu != 0) {
                                        break Label_1278;
                                    }
                                }
                                if (n17 < n18) {
                                    this.i = 1;
                                }
                            }
                            this.scrollinitial();
                            txtscroll3 = this;
                            if (bu != 0) {
                                break Label_1308;
                            }
                            u = this.U;
                        }
                        if (u != 0) {
                            this.x = this.createImage(this.f, this.g + this.br);
                            if (bu == 0) {
                                break Label_1323;
                            }
                        }
                        txtscroll3 = this;
                    }
                    txtscroll3.x = this.createImage(this.f, this.g);
                }
                this.y = this.x.getGraphics();
                return;
            }
            continue;
        }
    }
    
    public void start() {
        if (this.a == null) {
            (this.a = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.a != null && this.a.isAlive()) {
            this.a.stop();
        }
        this.a = null;
    }
    
    public void run() {
        this.F = this.getToolkit();
        this.a.setPriority(this.i);
        this.showStatus("");
        System.gc();
        this.n = System.currentTimeMillis();
        final Graphics graphics = this.getGraphics();
        if (this.j != null && !this.A) {
            this.A = this.CheckAniGIF();
        }
        if (this.D != null) {
            this.o.setCursor(12);
        }
        else {
            this.o.setCursor(0);
        }
        this.b.dr(c("\u001c\u0016\u0014w"), 1, this.C);
        while (this.a != null) {
            if (++this.c == this.h) {
                System.gc();
                this.c = 0;
            }
            if (!this.p) {
                this.y.setColor(this.r);
                this.y.fillRect(0, 0, this.f, this.g);
            }
            else {
                this.y.drawImage(this.w, 0, 0, this);
            }
            this.scrolltext(this.y);
            if (this.j != null) {
                this.prepaniframe();
            }
            graphics.drawImage(this.x, 0, 0, this);
            this.waitsync();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.x, 0, 0, this);
    }
    
    public synchronized void waitsync() {
        Thread.yield();
        this.F.sync();
        final long n = 10L - (System.currentTimeMillis() - this.n);
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
        this.n = System.currentTimeMillis();
        try {
            Thread.sleep(this.m);
        }
        catch (InterruptedException ex3) {}
    }
    
    public void scrollinitial() {
        this.P = false;
        final String parameter = this.getParameter(c("\t\u001d\nzW\u001e\n\u001dbH"));
        if (parameter != null && !parameter.equalsIgnoreCase(c("37"))) {
            String s = this.getParameter(c("\t\u001d\nzP\u0004\b\u0017"));
            if (s == null) {
                s = c("\u0015\u0017\u0000g^\u0012\u0016\u0006oH");
            }
            if (s.equals(c("\u0015\u0017\u0000g^\u0012\u0016\u0006oH"))) {
                this.bb = 0;
            }
            else if (s.equals(c("\u000b\u001d\u0000zM\u001e\u0019\u001e"))) {
                this.bb = 1;
            }
            else if (s.equals(c("\u0007\u0017\u001dcM\u0013\u001f"))) {
                this.bb = 2;
            }
            else if (s.equals(c("\u0014\u0016\u0004tK\u0012\u0015\u001b`C"))) {
                this.bb = 3;
            }
            if (this.bb == 0) {
                this.GetTheString(parameter, 0);
                if (this.N != null) {
                    this.P = true;
                }
            }
            else {
                this.GetTheString(parameter, 1);
                if (this.ba != null) {
                    this.P = true;
                }
            }
        }
        if (this.P) {
            String parameter2 = this.getParameter(c("\t\u001d\nzW\r\u001d\u0017j"));
            if (parameter2 == null) {
                parameter2 = "0";
            }
            this.O = Integer.valueOf(parameter2);
            String s2 = this.getParameter(c("\t\u001d\nzB\u0012\u0016\u0006"));
            if (s2 == null) {
                s2 = c("<\n\u001boH");
            }
            int n = 0;
            if (this.getParameter(c("\t\u001d\nzF\u0012\u0014\u0016")).equalsIgnoreCase(c("$=!"))) {
                ++n;
            }
            String s3 = this.getParameter(c("\t\u001d\nzM\t\u0019\u001egG"));
            if (s3 == null) {
                s3 = c("37");
            }
            if (s3.equalsIgnoreCase(c("$=!"))) {
                n += 2;
            }
            String s4 = this.getParameter(c("\t\u001d\nzW\u0014\u0002\u0017"));
            if (s4 == null) {
                s4 = c("LJ");
            }
            this.T = new Font(s2, n, Integer.valueOf(s4));
            if (this.getParameter(c("\t\u001d\nzW\u0015\u0019\u0016aS")).equalsIgnoreCase(c("$=!"))) {
                this.Q = true;
            }
            else {
                this.Q = false;
            }
            this.R = new Color(Integer.valueOf(this.getParameter(c(")\u001d\nzg\u0012\u0014 "))), Integer.valueOf(this.getParameter(c(")\u001d\nzg\u0012\u00145"))), Integer.valueOf(this.getParameter(c(")\u001d\nzg\u0012\u00140"))));
            this.S = new Color(Integer.valueOf(this.getParameter(c(")\u001d\nzw>\u0017\u001e\\"))), Integer.valueOf(this.getParameter(c(")\u001d\nzw>\u0017\u001eI"))), Integer.valueOf(this.getParameter(c(")\u001d\nzw>\u0017\u001eL"))));
            this.G = this.size().width;
            this.H = this.size().height;
            if (this.bb == 0) {
                String parameter3 = this.getParameter(c("\t\u001d\nzK\u001b\u001e\u0001kP"));
                if (parameter3 == null) {
                    parameter3 = "0";
                }
                this.J = Integer.valueOf(parameter3);
                if (this.J < 0) {
                    this.J = 0;
                }
                String parameter4 = this.getParameter(c(")\u001d\nzn\b\u0015\u0002OI\r"));
                if (parameter4 == null) {
                    parameter4 = "0";
                }
                this.bg = Integer.valueOf(parameter4);
                String parameter5 = this.getParameter(c(")\u001d\nzn\b\u0015\u0002]T\u0019"));
                if (parameter5 == null) {
                    parameter5 = "0";
                }
                this.bj = Integer.valueOf(parameter5);
                String parameter6 = this.getParameter(c(")\u001d\nzw\u0014\u0016\u0017OI\r"));
                if (parameter6 == null) {
                    parameter6 = "0";
                }
                this.U = Integer.valueOf(parameter6);
                String parameter7 = this.getParameter(c(")\u001d\nzw\u0014\u0016\u0017]T\u0019"));
                if (parameter7 == null) {
                    parameter7 = "0";
                }
                this.V = Integer.valueOf(parameter7);
                String parameter8 = this.getParameter(c(")\u001d\nzw\u0014\u0016\u0017OJ\u001a\u0014\u0017"));
                if (parameter8 == null) {
                    parameter8 = "0";
                }
                this.W = Integer.valueOf(parameter8);
                final FontMetrics fontMetrics = this.getGraphics().getFontMetrics(this.T);
                this.K = fontMetrics.stringWidth(this.N);
                this.L = fontMetrics.getHeight();
                this.M = fontMetrics.getMaxDescent();
                this.I = this.G;
                if (this.J < this.L - this.M) {
                    this.J = this.L - this.M;
                }
                else if (this.J > this.H - this.M) {
                    this.J = this.H - this.M;
                }
                if (this.U != 0) {
                    this.Y = new int[this.G + 360];
                    this.Z = new int[this.G + 360];
                    final double n2 = 0.017453292519943295;
                    for (int i = 0; i < this.G + 360; ++i) {
                        this.Y[i] = (int)(this.U * Math.sin(this.W * i * n2) - this.L - this.M + this.J);
                        this.Z[i] = this.Y[i] - this.e;
                    }
                    this.X = 360;
                    this.br = this.L + this.M + 1;
                    this.bs = this.br - 1;
                }
            }
            else {
                if (this.bb == 1) {
                    String s5 = this.getParameter(c("\t\u001d\nzR\u000e\b\u0013mA"));
                    if (s5 == null) {
                        s5 = c("LH");
                    }
                    final int intValue = Integer.valueOf(s5);
                    final FontMetrics fontMetrics2 = this.getGraphics().getFontMetrics(this.T);
                    this.bl = fontMetrics2.getHeight() + intValue;
                    this.bm = new int[this.ba.length];
                    for (int j = 0; j < this.ba.length; ++j) {
                        this.bm[j] = (this.G - fontMetrics2.stringWidth(this.ba[j])) / 2;
                    }
                    this.bk = -this.bl;
                    return;
                }
                if (this.bb >= 2) {
                    String parameter9 = this.getParameter(c("\t\u001d\nzI\u0014\u0016\u0014aJ\t"));
                    if (parameter9 == null) {
                        parameter9 = "2";
                    }
                    this.be = Integer.valueOf(parameter9);
                    String s6 = this.getParameter(c("\t\u001d\nzI\u001c\u0000\u0014aJ\t"));
                    if (s6 == null) {
                        s6 = c("JJ");
                    }
                    this.bf = Integer.valueOf(s6);
                    this.bc = this.bf - this.be;
                    this.T = null;
                    this.bd = new Font[this.bc];
                    int be = this.be;
                    for (int k = 0; k < this.bc; ++k) {
                        this.bd[k] = new Font(s2, n, be++);
                    }
                    this.bp = this.G / 2.0f;
                    this.bq = this.H / 2.0f;
                    if (this.bb == 3) {
                        this.bn = this.bc - 1;
                        return;
                    }
                    this.bn = 0;
                }
            }
        }
    }
    
    public void scrolltext(final Graphics graphics) {
        switch (this.bb) {
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
        graphics.setFont(this.T);
        this.bk += this.O;
        if (this.bk > this.H + this.ba.length * this.bl) {
            this.bk = -this.bl;
        }
        if (this.Q) {
            for (int i = 0; i < this.ba.length; ++i) {
                final String s = this.ba[i];
                final int n = this.bm[i];
                final int n2 = this.H - this.bk + i * this.bl;
                graphics.setColor(this.S);
                graphics.drawString(s, n + 1, n2 + 1);
                graphics.setColor(this.R);
                graphics.drawString(s, n, n2);
            }
            return;
        }
        graphics.setColor(this.R);
        for (int j = 0; j < this.ba.length; ++j) {
            graphics.drawString(this.ba[j], this.bm[j], this.H - this.bk + j * this.bl);
        }
    }
    
    public void zoomscroll(final Graphics graphics) {
        final String s = this.ba[this.bo];
        graphics.setFont(this.bd[this.bn]);
        final FontMetrics fontMetrics = graphics.getFontMetrics(this.bd[this.bn]);
        final int n = (int)(this.bp - fontMetrics.stringWidth(s) / 2.0f);
        final int n2 = (int)(this.bq + fontMetrics.getHeight() / 4.0f);
        if (this.Q) {
            graphics.setColor(this.S);
            graphics.drawString(s, n + 1, n2 + 1);
        }
        graphics.setColor(this.R);
        graphics.drawString(s, n, n2);
        if (this.bb == 3) {
            this.bn -= this.O;
            if (this.bn <= 1) {
                this.bn = this.bc - 1;
                ++this.bo;
                if (this.bo >= this.ba.length) {
                    this.bo = 0;
                }
            }
        }
        else {
            this.bn += this.O;
            if (this.bn >= this.bc) {
                this.bn = 0;
                ++this.bo;
                if (this.bo >= this.ba.length) {
                    this.bo = 0;
                }
            }
        }
    }
    
    public void horizscroll(final Graphics graphics) {
        graphics.setFont(this.T);
        if (this.bg == 0) {
            this.bh = this.J;
        }
        else {
            this.bi += this.bj;
            this.bh = this.J - (int)Math.abs(this.bg * Math.sin(this.bi / 90.0 * 3.141592653589793));
        }
        if (this.U != 0) {
            for (int i = 0; i < this.G; ++i) {
                final int n = this.Y[this.X + i];
                graphics.copyArea(i, n, 1, this.br, 0, this.e - n);
            }
            if (this.Q) {
                graphics.setColor(this.S);
                graphics.drawString(this.N, this.I + 1, this.e + this.L + 1);
            }
            graphics.setColor(this.R);
            graphics.drawString(this.N, this.I, this.e + this.L);
            for (int j = 0; j < this.G; ++j) {
                graphics.copyArea(j, this.e, 1, this.bs, 0, this.Z[this.X + j]);
            }
            this.X -= this.V;
            if (this.X < 0) {
                this.X += 360;
            }
        }
        else {
            if (this.Q) {
                graphics.setColor(this.S);
                graphics.drawString(this.N, this.I + 1, this.bh + 1);
            }
            graphics.setColor(this.R);
            graphics.drawString(this.N, this.I, this.bh);
        }
        this.I -= this.O;
        if (this.I < -this.K) {
            this.I = this.G;
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
                            this.N = new String(byteArray);
                            return;
                        }
                        catch (NoSuchMethodError noSuchMethodError) {
                            this.N = new String(byteArray, 0);
                            return;
                        }
                    }
                    int n3 = 1;
                    for (int j = 0; j < n2; ++j) {
                        if (byteArray[j] == 10) {
                            ++n3;
                        }
                    }
                    this.ba = new String[n3 - 1];
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
                                this.ba[l] = new String(byteArray, array2[l], array3[l]);
                            }
                            catch (NoSuchMethodError noSuchMethodError2) {
                                this.ba[l] = new String(byteArray, 0, array2[l], array3[l]);
                            }
                        }
                    }
                    catch (StringIndexOutOfBoundsException ex) {
                        this.ba = null;
                    }
                }
                catch (IOException ex2) {}
            }
            catch (IOException ex3) {}
        }
        catch (MalformedURLException ex4) {}
    }
    
    public final boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus(this.bt);
        return true;
    }
    
    public final boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        return true;
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        final int bu = txtscroll.bu;
        txtscroll txtscroll = this;
        Label_0065: {
            if (bu != 0) {
                break Label_0065;
            }
            if (!this.C) {
                this.b.show();
                try {
                    this.b.move(100, 100);
                }
                catch (Exception ex) {}
                this.b.toFront();
                this.b.requestFocus();
                if (bu == 0) {
                    return true;
                }
            }
            try {
                txtscroll txtscroll2 = this;
                txtscroll = this;
                Label_0084: {
                    if (bu != 0) {
                        break Label_0084;
                    }
                    if (txtscroll.D == null) {
                        return true;
                    }
                    try {
                        this.b.dck();
                        txtscroll txtscroll3 = this;
                        txtscroll2 = this;
                        if (bu == 0) {
                            if (txtscroll2.E) {
                                this.getAppletContext().showDocument(this.D, this.getParameter(c("\u000f\u001d\u0015hV\u001c\u0015\u0017`E\u0010\u001d")));
                                if (bu == 0) {
                                    return true;
                                }
                            }
                            txtscroll3 = this;
                        }
                        txtscroll3.getAppletContext().showDocument(this.D);
                    }
                    catch (Exception ex2) {}
                }
            }
            catch (Exception ex3) {}
        }
        return true;
    }
    
    public txtscroll() {
        this.q = false;
        this.z = false;
        this.A = false;
        this.C = false;
        this.E = false;
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
                    c2 = '}';
                    break;
                }
                case 1: {
                    c2 = 'x';
                    break;
                }
                case 2: {
                    c2 = 'r';
                    break;
                }
                case 3: {
                    c2 = '\u000e';
                    break;
                }
                default: {
                    c2 = '$';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
