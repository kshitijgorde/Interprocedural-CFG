import java.awt.Container;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.PixelGrabber;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.event.MouseEvent;
import java.awt.Frame;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Color;
import java.net.URL;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TF_Slices extends Applet implements MouseListener, Runnable
{
    private Thread N;
    private Image aa;
    private Graphics Z;
    private MemoryImageSource at;
    private int void;
    private int null;
    private int[] X;
    private int[] V;
    private String T;
    private Image M;
    private long L;
    private final long K = 16L;
    private final long J = 1500L;
    private URL H;
    private String[] G;
    private String[] ar;
    private int[] l;
    private String[] F;
    private String[] E;
    private int[] C;
    private int[] B;
    private int m;
    private Color try;
    private Color h;
    private Vector k;
    private MediaTracker j;
    private int i;
    private int g;
    private Frame f;
    private int[] e;
    private static int ah;
    private static int af;
    private static int ad;
    private boolean ac;
    private boolean b;
    private Image[] aj;
    private int A;
    private int z;
    private int ab;
    private int I;
    private int case;
    private int byte;
    private int ag;
    private int w;
    private int v;
    private static int P;
    private static int[] u;
    private int t;
    private double[] s;
    private double[] q;
    private int[] o;
    private int n;
    private int d;
    private int c;
    private boolean long;
    private int goto;
    private double[] int;
    private int[] for;
    private int[] do;
    private double[] if;
    private double[] a;
    private int[] au;
    private int[] as;
    private int[] aq;
    private int[] ap;
    private int ao;
    private int an;
    private int am;
    private int al;
    private double[] ak;
    private boolean[] ai;
    private double[] ae;
    private final int Y = 4;
    private final int W = 2;
    private final int U = 2;
    private final int S = 4;
    private final int R = 4;
    private double Q;
    private double char;
    private double new;
    private int[] else;
    private int D;
    private float O;
    private float r;
    private float p;
    
    public TF_Slices() {
        this.N = null;
        this.aa = null;
        this.Z = null;
        this.at = null;
        this.X = null;
        this.V = null;
        this.T = "";
        this.M = null;
        this.L = 8L;
        this.H = null;
        this.G = null;
        this.ar = null;
        this.l = null;
        this.F = null;
        this.E = null;
        this.C = null;
        this.B = null;
        this.try = null;
        this.h = null;
        this.k = null;
        this.i = 1;
        this.f = null;
        this.e = null;
        this.ac = false;
        this.b = false;
        this.A = 256;
        this.z = 256;
        this.case = 4;
        this.byte = 4;
        this.ag = 64;
        this.t = 1;
        this.s = null;
        this.q = null;
        this.o = null;
        this.n = 0;
        this.d = 0;
        this.c = 0;
        this.long = true;
        this.int = null;
        this.for = null;
        this.do = null;
        this.if = null;
        this.a = null;
        this.au = null;
        this.as = null;
        this.aq = null;
        this.ap = null;
        this.ak = null;
        this.ai = null;
        this.ae = null;
        this.Q = 0.0;
        this.char = 0.0;
        this.new = 0.0;
        this.else = null;
        this.O = 0.0f;
        this.r = 0.0f;
        this.p = 0.0f;
    }
    
    public void init() {
        System.out.println(this.getAppletInfo());
        this.void = this.getSize().width;
        this.null = this.getSize().height;
        this.case = TF_Slices.ah;
        this.byte = 1;
        this.addMouseListener(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.G[this.i - 1] != null && !this.G[this.i - 1].equals("")) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.G[this.i - 1]), this.ar[this.i - 1]);
            }
            catch (MalformedURLException ex) {}
        }
    }
    
    private Color a(String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == ' ') {
                sb.append("");
            }
            else {
                sb.append(char1);
            }
        }
        s = sb.toString();
        if (s != null && s.length() >= 5) {
            try {
                final int index = s.indexOf(",");
                final int int1 = Integer.parseInt(s.substring(0, index));
                s = s.substring(index + 1);
                final int index2 = s.indexOf(",");
                final int int2 = Integer.parseInt(s.substring(0, index2));
                final int int3 = Integer.parseInt(s.substring(index2 + 1));
                return new Color((int1 > 255) ? 255 : ((int1 < 0) ? 0 : int1), (int2 > 255) ? 255 : ((int2 < 0) ? 0 : int2), (int3 > 255) ? 255 : ((int3 < 0) ? 0 : int3));
            }
            catch (Exception ex) {
                return new Color(16777215);
            }
        }
        return new Color(16777215);
    }
    
    private void new() {
        this.m = 1;
        while (this.getParameter("image".concat(String.valueOf(String.valueOf(String.valueOf(this.m))))) != null) {
            ++this.m;
        }
        --this.m;
        this.E = new String[this.m];
        this.ar = new String[this.m];
        this.G = new String[this.m];
        this.l = new int[this.m];
        this.F = new String[this.m];
        for (int i = 0; i < this.m; ++i) {
            this.E[i] = this.getParameter("image".concat(String.valueOf(String.valueOf(String.valueOf(i + 1)))));
            this.G[i] = this.getParameter(String.valueOf(String.valueOf(new StringBuffer("link").append(i + 1))));
            if (this.G[i] == null) {
                this.G[i] = "-";
            }
            this.ar[i] = this.getParameter(String.valueOf(String.valueOf(new StringBuffer("target").append(i + 1))));
            if (this.ar[i] == null) {
                this.ar[i] = "_self";
            }
            this.F[i] = this.getParameter(String.valueOf(String.valueOf(new StringBuffer("status").append(i + 1))));
            if (this.F[i] == null) {
                this.F[i] = "";
            }
            try {
                this.l[i] = Integer.parseInt(this.getParameter(String.valueOf(String.valueOf(new StringBuffer("delay").append(i + 1)))));
            }
            catch (Exception ex) {
                this.l[i] = 2;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.ac) {
            if (this.M != null) {
                if (this.b && this.aj[this.i - 1] != null) {
                    this.Z.drawImage(this.aj[this.i - 1], (this.getSize().width - this.aj[this.i - 1].getWidth(this)) / 2 + 1, (this.getSize().height - this.aj[this.i - 1].getHeight(this)) / 2 + 1, this.aj[this.i - 1].getWidth(this) - 1, this.aj[this.i - 1].getHeight(this) - 1, this);
                }
                else {
                    this.Z.drawImage(this.M, 0, 0, this);
                }
                graphics.drawImage(this.aa, 0, 0, this);
            }
        }
        else if (this.try != null) {
            graphics.setColor(this.try);
            graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
            graphics.setColor(this.h);
            graphics.drawString("Loading ...", this.getSize().width / 2 - 20, this.getSize().height / 2 + 3);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        if (this.N == null) {
            (this.N = new Thread(this)).setPriority(1);
            this.N.start();
        }
    }
    
    public void stop() {
        if (this.N != null) {
            this.N.stop();
            this.N = null;
        }
        System.gc();
    }
    
    private Image a(final String s, final int n) {
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Image image;
        try {
            image = this.getImage(url);
            this.j.addImage(image, n);
            this.j.waitForID(n);
        }
        catch (InterruptedException ex2) {
            image = null;
        }
        if (this.j.isErrorID(n)) {
            image = null;
        }
        return image;
    }
    
    private Image if(final String s) {
        final MediaTracker mediaTracker = new MediaTracker(this);
        URL url = null;
        try {
            url = new URL(this.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {}
        Image image;
        try {
            image = this.getImage(url);
            mediaTracker.addImage(image, 1);
            mediaTracker.waitForID(1);
        }
        catch (InterruptedException ex2) {
            image = null;
        }
        if (mediaTracker.isErrorID(1)) {
            image = null;
        }
        return image;
    }
    
    private boolean a(final Image image, final int[] array, final int n, final int n2) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, n, n2, array, 0, n);
        try {
            if (!pixelGrabber.grabPixels()) {
                return false;
            }
        }
        catch (InterruptedException ex) {
            return false;
        }
        return (pixelGrabber.status() & 0x80) == 0x0;
    }
    
    private final boolean case() {
        this.k = new Vector();
        this.j = new MediaTracker(this);
        (this.aj = new Image[this.m])[0] = null;
        this.C = new int[this.m];
        this.B = new int[this.m];
        for (int i = 0; i < this.m; ++i) {
            this.aj[i] = this.a(this.E[i], i);
            if (this.aj[i] == null) {
                return false;
            }
            this.C[i] = this.aj[i].getWidth(this);
            this.B[i] = this.aj[i].getHeight(this);
            final int[] array = new int[this.C[i] * this.B[i]];
            if (!this.a(this.aj[i], array, this.C[i], this.B[i])) {
                return false;
            }
            this.k.addElement(array);
            System.gc();
        }
        return true;
    }
    
    public void run() {
        this.e = new int[this.null];
        for (int i = 0; i < this.null; ++i) {
            this.e[i] = i * this.void;
        }
        this.X = new int[this.void * this.null];
        this.V = new int[this.void * this.null];
        this.new();
        (this.at = new MemoryImageSource(this.void, this.null, new DirectColorModel(24, 16711680, 65280, 255), this.X, 0, this.void)).setAnimated(true);
        this.M = this.createImage(this.at);
        try {
            this.aa = this.createImage(this.void, this.null);
            this.Z = this.aa.getGraphics();
        }
        catch (Exception ex) {
            this.aa = null;
        }
        this.resize(this.void, this.null);
        int n = 0;
        final String parameter = this.getParameter("bg_image");
        if (parameter != null) {
            if (!parameter.equalsIgnoreCase("-")) {
                final Image if1 = this.if(parameter);
                if (if1 != null && if1.getWidth(this) == this.void && if1.getHeight(this) == this.null && !this.a(if1, this.V, this.void, this.null)) {
                    n = 1;
                }
            }
            else {
                n = 1;
            }
        }
        if (n == 1) {
            final String parameter2 = this.getParameter("bg_color");
            if (parameter2 == null) {
                this.try = new Color(0);
            }
            else {
                this.try = this.a(parameter2);
            }
            for (int j = 0; j < this.void * this.null; ++j) {
                this.V[j] = (this.try.getRed() << 16 | this.try.getGreen() << 8 | this.try.getBlue());
            }
        }
        final String parameter3 = this.getParameter("fg_color");
        if (parameter3 == null) {
            this.h = new Color(16777215);
        }
        else {
            this.h = this.a(parameter3);
        }
        this.repaint();
        final String parameter4 = this.getParameter("frames");
        if (parameter4 != null) {
            this.ag = Integer.parseInt(parameter4);
        }
        else {
            this.ag = 36;
        }
        final String parameter5 = this.getParameter("min_slices");
        if (parameter5 != null) {
            TF_Slices.af = Integer.parseInt(parameter5);
        }
        else {
            TF_Slices.af = 16;
        }
        final String parameter6 = this.getParameter("max_slices");
        if (parameter6 != null) {
            TF_Slices.ah = Integer.parseInt(parameter6);
        }
        else {
            TF_Slices.ah = 16;
        }
        final String parameter7 = this.getParameter("trail");
        if (parameter7 != null) {
            TF_Slices.ad = Integer.parseInt(parameter7);
        }
        else {
            TF_Slices.ad = 32;
        }
        Container container;
        for (container = this.getParent(); !(container instanceof Frame); container = ((Frame)container).getParent()) {}
        (this.f = (Frame)container).setCursor(3);
        this.case();
        this.if(this.void, this.null, this.C[0], this.B[0], this.case, this.byte);
        this.L = System.currentTimeMillis();
        final long n2 = 1500L - (System.currentTimeMillis() - this.L);
        if (n2 > 0) {
            try {
                Thread.sleep(n2);
            }
            catch (InterruptedException ex2) {}
        }
        System.gc();
        final Graphics graphics = this.getGraphics();
        if (!this.G[this.i - 1].equalsIgnoreCase("-")) {
            this.f.setCursor(12);
        }
        else {
            this.f.setCursor(0);
        }
        this.ac = true;
        this.repaint();
        while (true) {
            this.showStatus("Programmed by Tarek Fouda 'tarek@fouda.de'");
            this.null();
            System.gc();
            this.at.newPixels();
            this.Z.drawImage(this.M, 0, 0, this);
            graphics.drawImage(this.aa, 0, 0, this);
        }
    }
    
    private void null() {
        System.arraycopy(this.V, 0, this.X, 0, this.void * this.null);
        this.goto();
    }
    
    private void if(final int ab, final int i, final int a, final int z, final int n, final int n2) {
        this.A = a;
        this.z = z;
        this.ab = ab;
        this.I = i;
        this.t = this.ag;
        this.a(ab, i, a, z, n, n2);
    }
    
    private void byte() {
        if (this.d > 0) {
            this.d = 0;
        }
        for (int i = 0; i < this.w; ++i) {
            for (int j = 0; j < this.v; ++j) {
                final int n = j + i * this.v;
                switch (this.d) {
                    case 0: {
                        TF_Slices.u[n] = (int)(Math.sqrt(j + 1 * i + 1) * TF_Slices.ad + this.t);
                        break;
                    }
                    default: {
                        TF_Slices.u[j + i * this.v] = (i + j * this.w) * 4 + j + this.t;
                        break;
                    }
                }
            }
        }
        ++this.d;
    }
    
    private void int() {
        final double n = this.A / this.v;
        final double n2 = this.z / this.w;
        for (int i = 0; i < this.w; ++i) {
            for (int j = 0; j < this.v; ++j) {
                final int n3 = j + i * this.v;
                this.o[n3 * 8] = (int)((this.v - 1 - j) * n);
                this.o[n3 * 8 + 1] = (int)((this.w - 1 - i) * n2);
                this.o[n3 * 8 + 2] = (int)(n + (this.v - 1 - j) * n - 1);
                this.o[n3 * 8 + 3] = (int)((this.w - 1 - i) * n2 + n2 - 1);
                this.o[n3 * 8 + 4] = this.o[n3 * 8];
                this.o[n3 * 8 + 5] = this.o[n3 * 8 + 1];
                this.o[n3 * 8 + 6] = this.o[n3 * 8 + 2];
                this.o[n3 * 8 + 7] = this.o[n3 * 8 + 3];
            }
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int w, final int v) {
        TF_Slices.u = new int[w * v];
        this.w = w;
        this.v = v;
        this.s = new double[w * v * 6];
        this.q = new double[w * v];
        this.o = new int[w * v * 8];
        final double n5 = n3 / v;
        final double n6 = n4 / w;
        this.a(n5, n6, n, n2);
        for (int i = 0; i < this.w; ++i) {
            for (int j = 0; j < this.v; ++j) {
                final int n7 = j + i * this.v;
                this.s[n7 * 6] = (j - this.v / 2.0) * 2.0 * n5 + n5;
                this.s[n7 * 6 + 1] = (this.w / 2.0 - i) * 2.0 * n6 - n6;
                this.s[n7 * 6 + 2] = -512.0;
                this.s[n7 * 6 + 3] = this.s[n7 * 6];
                this.s[n7 * 6 + 4] = this.s[n7 * 6 + 1];
                this.s[n7 * 6 + 5] = this.s[n7 * 6 + 2];
                this.q[n7] = 0.0;
            }
        }
        this.byte();
        this.int();
    }
    
    private void goto() {
        TF_Slices.P = 0;
        for (int i = 0; i < this.w; ++i) {
            for (int j = 0; j < this.v; ++j) {
                final int n = j + i * this.v;
                this.if(this.s[n * 6 + 3], this.s[n * 6 + 4], this.s[n * 6 + 5]);
                this.char = this.q[n];
                this.new = 0.0;
                this.a(this.o, n * 8, this.else[n]);
                this.a(TF_Slices.u[n]);
                if (TF_Slices.u[n] <= this.t && TF_Slices.u[n] > 0) {
                    final double[] q = this.q;
                    final int n2 = n;
                    q[n2] += 3.141592653589793 / this.t;
                }
                else if (TF_Slices.u[n] < -1) {
                    ++TF_Slices.P;
                }
                final int[] u = TF_Slices.u;
                final int n3 = n;
                --u[n3];
            }
        }
        if (TF_Slices.P == this.w * this.v) {
            this.goto = -this.goto;
            this.for();
            this.byte();
            for (int k = 0; k < this.w * this.v; ++k) {
                this.q[k] = 0.0;
            }
            ++this.n;
            this.i = this.n % this.m + 1;
            this.showStatus(this.F[this.i - 1]);
            if (!this.G[this.i - 1].equalsIgnoreCase("-")) {
                this.f.setCursor(12);
            }
            else {
                this.f.setCursor(0);
            }
            this.b = true;
            this.repaint();
            this.case = (int)(TF_Slices.af + Math.random() * TF_Slices.ah);
            this.case = ((this.case < 1) ? 1 : ((this.case > TF_Slices.ah) ? TF_Slices.ah : this.case));
            this.byte = (int)(1 + Math.random() * 3);
            this.byte = ((this.byte < 1) ? 1 : ((this.byte > 3) ? 3 : this.byte));
            this.if(this.void, this.null, this.C[this.i - 1], this.B[this.i - 1], this.case, this.byte);
            final int n4 = this.l[this.i - 1];
            final int n5 = (n4 > 0) ? n4 : 0;
            try {
                Thread.sleep(n5 * 1000);
                this.b = false;
                this.repaint();
            }
            catch (InterruptedException ex) {
                this.b = false;
            }
        }
    }
    
    private void a(final double n, final double n2, final int n3, final int n4) {
        this.else = new int[this.w * this.v];
        this.a(n, n2);
        this.if();
        this.a(n3, n4);
    }
    
    private void for() {
        this.D = 3;
    }
    
    private void a(final int ao, final int an) {
        this.do = new int[16];
        this.au = new int[8];
        this.ai = new boolean[2];
        this.if = new double[16];
        this.a = new double[16];
        this.as = new int[an * 2];
        this.aq = new int[an * 2];
        this.ap = new int[an * 2];
        this.an = an;
        this.ao = ao;
        this.ae = new double[6];
        for (int i = 0; i < 6; ++i) {
            this.ae[i] = 0.0;
        }
    }
    
    private void a(final double n, final double n2) {
        (this.int = new double[24])[0] = -1.0;
        this.int[1] = -1.0;
        this.int[2] = 0.0;
        this.int[3] = -1.0;
        this.int[4] = -1.0;
        this.int[5] = 0.0;
        this.int[6] = 1.0;
        this.int[7] = -1.0;
        this.int[8] = 0.0;
        this.int[9] = 1.0;
        this.int[10] = -1.0;
        this.int[11] = 0.0;
        this.int[12] = 1.0;
        this.int[13] = 1.0;
        this.int[14] = 0.0;
        this.int[15] = 1.0;
        this.int[16] = 1.0;
        this.int[17] = 0.0;
        this.int[18] = -1.0;
        this.int[19] = 1.0;
        this.int[20] = 0.0;
        this.int[21] = -1.0;
        this.int[22] = 1.0;
        this.int[23] = 0.0;
        for (int i = 0; i < 4; ++i) {
            final double[] int1 = this.int;
            final int n3 = i * 6;
            int1[n3] *= n;
            final double[] int2 = this.int;
            final int n4 = i * 6 + 1;
            int2[n4] *= n2;
            final double[] int3 = this.int;
            final int n5 = i * 6 + 3;
            int3[n5] *= n;
            final double[] int4 = this.int;
            final int n6 = i * 6 + 4;
            int4[n6] *= n2;
        }
    }
    
    private void if() {
        (this.for = new int[8])[0] = 0;
        this.for[1] = 1;
        this.for[2] = 2;
        this.for[3] = 3;
        this.for[4] = 3;
        this.for[5] = 2;
        this.for[6] = 1;
        this.for[7] = 0;
        this.try();
    }
    
    private void try() {
        this.ak = new double[12];
        for (int i = 0; i < 2; ++i) {
            final int n = this.for[i * 4 + 2];
            final int n2 = this.for[i * 4 + 1];
            final int n3 = this.for[i * 4];
            final double n4 = this.int[n * 6] - this.int[n3 * 6];
            final double n5 = this.int[n * 6 + 1] - this.int[n3 * 6 + 1];
            final double n6 = this.int[n * 6 + 2] - this.int[n3 * 6 + 2];
            final double n7 = this.int[n2 * 6] - this.int[n3 * 6];
            final double n8 = this.int[n2 * 6 + 1] - this.int[n3 * 6 + 1];
            final double n9 = this.int[n2 * 6 + 2] - this.int[n3 * 6 + 2];
            final double n10 = n5 * n9 - n6 * n8;
            final double n11 = n6 * n7 - n4 * n9;
            final double n12 = n4 * n8 - n7 * n5;
            final double sqrt = Math.sqrt(n10 * n10 + n11 * n11 + n12 * n12);
            final double n13 = n10 / sqrt;
            final double n14 = n11 / sqrt;
            final double n15 = n12 / sqrt;
            this.ak[i * 6] = n13;
            this.ak[i * 6 + 1] = n14;
            this.ak[i * 6 + 2] = n15;
            this.ak[i * 6 + 3] = n13;
            this.ak[i * 6 + 4] = n14;
            this.ak[i * 6 + 5] = n15;
        }
    }
    
    private void a(final int n) {
        this.a(this.Q, this.char, this.new);
        this.long();
        if (n <= this.t && n > 0) {
            this.do(n);
        }
        else {
            this.do(0);
        }
        this.else();
        this.a();
        this.char();
    }
    
    private void char() {
        this.am = Integer.MAX_VALUE;
        this.al = Integer.MIN_VALUE;
        for (int i = 0; i < 2; ++i) {
            if (this.ai[i]) {
                for (int j = 0; j < 4; ++j) {
                    int an;
                    if (this.au[this.for[i * 4 + j] * 2 + 1] <= this.au[this.for[i * 4 + (j + 1) % 4] * 2 + 1]) {
                        an = 0;
                    }
                    else {
                        an = this.an;
                    }
                    this.a(this.au[this.for[i * 4 + j] * 2], this.au[this.for[i * 4 + j] * 2 + 1], this.au[this.for[i * 4 + (j + 1) % 4] * 2], this.au[this.for[i * 4 + (j + 1) % 4] * 2 + 1], this.as, an);
                    if (this.au[this.for[i * 4 + j] * 2 + 1] < this.am) {
                        this.am = this.au[this.for[i * 4 + j] * 2 + 1];
                    }
                    if (this.au[this.for[i * 4 + (j + 1) % 4] * 2 + 1] > this.al) {
                        this.al = this.au[this.for[i * 4 + (j + 1) % 4] * 2 + 1];
                    }
                    this.a(this.do[(i * 4 + j) * 2], this.au[this.for[i * 4 + j] * 2 + 1], this.do[(i * 4 + (j + 1) % 4) * 2], this.au[this.for[i * 4 + (j + 1) % 4] * 2 + 1], this.aq, an);
                    this.a(this.do[(i * 4 + j) * 2 + 1], this.au[this.for[i * 4 + j] * 2 + 1], this.do[(i * 4 + (j + 1) % 4) * 2 + 1], this.au[this.for[i * 4 + (j + 1) % 4] * 2 + 1], this.ap, an);
                }
                if (this.am >= this.an || this.al < 0) {
                    return;
                }
                if (this.am < 0) {
                    this.am = 0;
                }
                if (this.al >= this.an) {
                    this.al = this.an - 1;
                }
                this.if(this.n + i);
            }
        }
    }
    
    private void if(final int n) {
        final int[] array = this.k.elementAt(n % this.m);
        while (this.am < this.al) {
            int n2 = this.as[this.am] >> 16;
            int n3 = this.as[this.am + this.an] >> 16;
            int n4 = this.aq[this.am];
            final int n5 = this.aq[this.am + this.an];
            int n6 = this.ap[this.am];
            final int n7 = this.ap[this.am + this.an];
            int n8 = 0;
            int n9 = 0;
            if (n3 - n2 != 0) {
                n8 = (n5 - n4) / (n3 - n2);
                n9 = (n7 - n6) / (n3 - n2);
            }
            if (n2 < 0) {
                n4 -= n8 * n2;
                n6 -= n9 * n2;
                n2 = 0;
            }
            if (n3 >= this.ao) {
                n3 = this.ao - 1;
            }
            for (int i = this.ab * this.am + n2; i < this.ab * this.am + n3; ++i) {
                this.X[i] = array[this.A * (n6 >> 16) + (n4 >> 16)];
                n4 += n8;
                n6 += n9;
            }
            ++this.am;
        }
    }
    
    private void a() {
        for (int i = 0; i < 4; ++i) {
            this.au[i * 2] = this.ab / 2 + (int)(this.int[i * 6 + 3] * 256 / this.int[i * 6 + 5]);
            this.au[i * 2 + 1] = this.I / 2 - (int)(this.int[i * 6 + 4] * 256 / this.int[i * 6 + 5]);
        }
    }
    
    private void a(final double n, final double n2, final double n3) {
        final double sin = Math.sin(n);
        final double sin2 = Math.sin(n2);
        final double sin3 = Math.sin(n3);
        final double cos = Math.cos(n);
        final double cos2 = Math.cos(n2);
        final double cos3 = Math.cos(n3);
        this.if[0] = cos2 * cos;
        this.if[1] = -cos2 * sin;
        this.if[2] = sin2;
        this.if[3] = this.ae[3];
        this.if[4] = sin3 * sin2 * cos + sin * cos3;
        this.if[5] = cos3 * cos - sin3 * sin2 * sin;
        this.if[6] = -sin3 * cos2;
        this.if[7] = this.ae[4];
        this.if[8] = sin3 * sin - cos3 * sin2 * cos;
        this.if[9] = cos3 * sin2 * sin + sin3 * cos;
        this.if[10] = cos3 * cos2;
        this.if[11] = 0.0;
        this.if[12] = 0.0;
        this.if[13] = 0.0;
        this.if[14] = 0.0;
        this.if[15] = 1.0;
    }
    
    private void if(final double n, final double n2, final double n3) {
        this.ae[0] = (this.ae[3] = n);
        this.ae[1] = (this.ae[4] = n2);
        this.ae[2] = (this.ae[5] = n3);
    }
    
    private void else() {
        for (int i = 0; i < 2; ++i) {
            final double n = this.ak[i * 6];
            final double n2 = this.ak[i * 6 + 1];
            final double n3 = this.ak[i * 6 + 2];
            final double n4 = n * this.if[0] + n2 * this.if[1] + n3 * this.if[2];
            final double n5 = n * this.if[4] + n2 * this.if[5] + n3 * this.if[6];
            final double n6 = n * this.if[8] + n2 * this.if[9] + n3 * this.if[10];
            this.ak[i * 6 + 3] = n4 * this.a[0] + n5 * this.a[1] + n6 * this.a[2];
            this.ak[i * 6 + 4] = n4 * this.a[4] + n5 * this.a[5] + n6 * this.a[6];
            this.ak[i * 6 + 5] = n4 * this.a[8] + n5 * this.a[9] + n6 * this.a[10];
            final double n7 = this.int[this.for[i * 4 + 0] * 6 + 5];
            final double n8 = this.int[this.for[i * 4 + 1] * 6 + 5];
            final double n9 = this.int[this.for[i * 4 + 2] * 6 + 5];
            final double n10 = this.int[this.for[i * 4 + 3] * 6 + 5];
            this.ai[i] = (n7 <= 0 && n8 <= 0 && n9 <= 0 && n10 <= 0);
        }
    }
    
    private void long() {
        for (int i = 0; i < 4; ++i) {
            final double n = this.int[i * 6];
            final double n2 = this.int[i * 6 + 1];
            final double n3 = this.int[i * 6 + 2];
            this.int[i * 6 + 3] = n * this.if[0] + n2 * this.if[1] + n3 * this.if[2] + this.if[3];
            this.int[i * 6 + 4] = n * this.if[4] + n2 * this.if[5] + n3 * this.if[6] + this.if[7];
            this.int[i * 6 + 5] = n * this.if[8] + n2 * this.if[9] + n3 * this.if[10] + this.if[11];
        }
    }
    
    private void a(int n, int i, int n2, int n3, final int[] array, final int n4) {
        if (i == n3) {
            return;
        }
        if (n == n2) {
            this.a(n, i, n3, array, n4);
            return;
        }
        if (i > n3) {
            final int n5 = i;
            i = n3;
            n3 = n5;
            final int n6 = n;
            n = n2;
            n2 = n6;
        }
        final int n7 = (n2 - n << 16) / (n3 - i);
        n <<= 16;
        if (i < 0) {
            n -= n7 * i;
            i = 0;
        }
        if (n3 >= this.an) {
            n3 = this.an - 1;
        }
        while (i <= n3) {
            array[n4 + i] = n;
            n += n7;
            ++i;
        }
    }
    
    private void a(final int n, int i, int n2, final int[] array, final int n3) {
        if (i > n2) {
            final int n4 = i;
            i = n2;
            n2 = n4;
        }
        if (i < 0) {
            i = 0;
        }
        if (n2 >= this.an) {
            n2 = this.an - 1;
        }
        while (i <= n2) {
            array[n3 + i] = n << 16;
            ++i;
        }
    }
    
    private void a(final int[] array, int n, final int n2) {
        this.do[0] = array[n + 2];
        this.do[1] = array[n + 1];
        this.do[2] = array[n];
        this.do[3] = array[n + 1];
        this.do[4] = array[n];
        this.do[5] = array[n + 3];
        this.do[6] = array[n + 2];
        this.do[7] = array[n + 3];
        n += 4;
        if (n2 == 0) {
            this.do[8] = array[n];
            this.do[9] = array[n + 3];
            this.do[10] = array[n + 2];
            this.do[11] = array[n + 3];
            this.do[12] = array[n + 2];
            this.do[13] = array[n + 1];
            this.do[14] = array[n];
            this.do[15] = array[n + 1];
        }
        else if (n2 == 1 || n2 == 2) {
            this.do[8] = array[n + 2];
            this.do[9] = array[n + 1];
            this.do[10] = array[n];
            this.do[11] = array[n + 1];
            this.do[12] = array[n];
            this.do[13] = array[n + 3];
            this.do[14] = array[n + 2];
            this.do[15] = array[n + 3];
        }
    }
    
    private void do() {
        final double sin = Math.sin(this.O);
        final double sin2 = Math.sin(this.r);
        final double sin3 = Math.sin(this.p);
        final double cos = Math.cos(this.O);
        final double cos2 = Math.cos(this.r);
        final double cos3 = Math.cos(this.p);
        this.a[0] = cos2 * cos;
        this.a[1] = -cos2 * sin;
        this.a[2] = sin2;
        this.a[3] = 0.0;
        this.a[4] = sin3 * sin2 * cos + sin * cos3;
        this.a[5] = cos3 * cos - sin3 * sin2 * sin;
        this.a[6] = -sin3 * cos2;
        this.a[7] = 0.0;
        this.a[8] = sin3 * sin - cos3 * sin2 * cos;
        this.a[9] = cos3 * sin2 * sin + sin3 * cos;
        this.a[10] = cos3 * cos2;
        this.a[11] = this.ae[5];
        this.a[12] = 0.0;
        this.a[13] = 0.0;
        this.a[14] = 0.0;
        this.a[15] = 1.0;
    }
    
    private void do(final int n) {
        switch (this.c) {
            case 0: {
                this.O = 0.0f;
                this.r = (float)(-n * 2 * 3.141592653589793 / this.t);
                break;
            }
            case 1: {
                this.O = (float)(-2 * n * 2 * 3.141592653589793 / this.t);
                this.r = (float)(-n * 2 * 3.141592653589793 / this.t);
                break;
            }
            case 2: {
                this.p = (float)(-n * 2 * 3.141592653589793 / this.t);
                break;
            }
        }
        this.do();
        for (int i = 0; i < 4; ++i) {
            final double n2 = this.int[i * 6 + 3];
            final double n3 = this.int[i * 6 + 4];
            final double n4 = this.int[i * 6 + 5];
            this.int[i * 6 + 3] = n2 * this.a[0] + n3 * this.a[1] + n4 * this.a[2] + this.a[3];
            this.int[i * 6 + 4] = n2 * this.a[4] + n3 * this.a[5] + n4 * this.a[6] + this.a[7];
            this.int[i * 6 + 5] = n2 * this.a[8] + n3 * this.a[9] + n4 * this.a[10] + this.a[11];
        }
    }
    
    public String getAppletInfo() {
        return "--------------------------------\r\n\r\nClass Name : TF_Slices\r\nOwner : Tarek Fouda\r\nAuthor : Tarek Fouda\r\nContact : tarek@fouda.de\r\nHomepage : http://www.fouda.de\r\nLast Modify : 25.06.2002\r\n\r\n--------------------------------\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n";
    }
    
    static {
        TF_Slices.ah = 16;
        TF_Slices.af = 16;
        TF_Slices.ad = 32;
        TF_Slices.P = 0;
        TF_Slices.u = null;
    }
}
