import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.util.NoSuchElementException;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.URLEncoder;
import java.awt.Event;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Component;
import java.net.URL;
import java.util.StringTokenizer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.Font;
import java.math.BigInteger;
import java.awt.Cursor;
import java.util.Vector;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jads2 extends Applet implements Runnable
{
    private static int F;
    private static int G;
    private static int H;
    private static int I;
    private static int J;
    private static int K;
    private Image L;
    private Graphics M;
    private int N;
    private int O;
    private String P;
    private String Q;
    private String R;
    private String S;
    private String T;
    private String U;
    private String V;
    private Color W;
    private int X;
    private String[] Y;
    private String[] Z;
    private String[] a;
    private d[] b;
    private int[] c;
    private int[] d;
    private int[] e;
    private boolean f;
    private Image g;
    private Vector[] h;
    private Vector i;
    private int j;
    private float k;
    private float l;
    private int m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private boolean v;
    private Cursor w;
    private String x;
    private int y;
    private c[] z;
    private int[] \u00c0;
    private boolean \u00c1;
    private int \u00c2;
    private boolean \u00c3;
    private String \u00c4;
    private String \u00c5;
    private String \u00c6;
    private boolean \u00c7;
    private boolean \u00c8;
    private int \u00c9;
    private String \u00ca;
    private String \u00cb;
    private BigInteger \u00cc;
    private BigInteger \u00cd;
    private static final int \u00ce = 48;
    protected static final char[] \u00cf;
    protected static final byte[] \u00d0;
    private int \u00d1;
    private int \u00d2;
    private String[] \u00d3;
    private String[] \u00d4;
    private int[] \u00d5;
    private int[] \u00d6;
    private Thread \u00d8;
    private int \u00d9;
    private int \u00da;
    private int \u00db;
    
    public void init() {
        this.N = this.size().width;
        this.O = this.size().height;
        this.\u00db = Math.round(1000 / Math.abs(Integer.parseInt(this.U)));
        this.W = new Color(Integer.parseInt(this.V, 16));
        final String r = this.R();
        if (this.\u00c3) {
            System.err.println("Debug : Version = " + r);
        }
        if (!this.\u00c8) {
            this.w = new Cursor(12);
            this.T();
        }
        this.L = this.createImage(this.N, this.O);
        (this.M = this.L.getGraphics()).setFont(new Font("Helvetica", 1, 12));
        final String parameter = this.getParameter("config");
        if (parameter == null) {
            this.\u00c4 = "jads.ini";
        }
        else {
            this.\u00c4 = parameter;
        }
        this.Y = new String[jads2.K];
        this.Z = new String[jads2.K];
        this.a = new String[jads2.K];
        String n = "";
        try {
            n = this.N(this.\u00c4, true);
        }
        catch (MalformedURLException ex) {
            this.x = "Error : Cannot load " + this.\u00c4;
            if (this.\u00c3) {
                System.err.println("Debug : Load parameters : " + ex.getMessage());
            }
        }
        catch (IOException ex2) {
            this.x = "Error : Cannot load " + this.\u00c4;
            if (this.\u00c3) {
                System.err.println("Debug : Load parameters : " + ex2.getMessage());
            }
        }
        if (this.x == null) {
            String o = null;
            for (StringTokenizer stringTokenizer = new StringTokenizer(n, ";"); stringTokenizer.hasMoreTokens() && o == null; o = this.O(stringTokenizer.nextToken())) {}
            this.x = o;
        }
        if (this.x == null) {
            boolean b = false;
            while (!b) {
                if (this.a[this.t] == null) {
                    b = true;
                }
                else {
                    ++this.t;
                }
            }
            if (this.\u00c3) {
                System.err.println("Debug : Banners : " + this.t);
                System.err.println("Debug : Wait : " + this.X);
                System.err.println("Debug : Fps : " + Math.round(1000 / this.\u00db));
            }
            if (this.t > 1) {
                URL url = null;
                this.e = new int[this.N * this.O];
                this.d = new int[this.N * this.O];
                this.h = new Vector[this.t];
                this.z = new c[this.t];
                this.\u00c0 = new int[this.t];
                try {
                    for (int i = 0; i < this.t; ++i) {
                        url = new URL(this.getDocumentBase(), this.a[i]);
                        this.z[i] = new c(url);
                    }
                }
                catch (MalformedURLException ex3) {
                    if (this.\u00c3) {
                        System.err.println("Malformed Url : " + url);
                    }
                    this.x = "Error : Illegal filename !";
                }
            }
            else {
                this.x = "Error : Two or more banners needed !";
            }
        }
        if (this.Q.equals("AUTOFAST")) {
            this.\u00d1 = jads2.J;
            return;
        }
        if (this.R.equals("AUTO") || this.Q.equals("AUTO") || this.P == null) {
            this.\u00d1 = jads2.H;
            return;
        }
        this.\u00d1 = jads2.I;
    }
    
    private String V(final int n) {
        Vector x = null;
        int n2 = 0;
        if (this.z[n].k()) {
            final b b = new b(this);
            try {
                x = b.X(this.z[n].j());
                this.\u00c0[n] = b.h();
            }
            catch (Exception ex) {
                return this.z[n].i();
            }
            this.h[n] = x;
            n2 = 1;
        }
        if (n2 == 1) {
            if (n == 0) {
                this.i = x;
                this.c = new int[this.N * this.O];
                this.g = this.createImage(new MemoryImageSource(this.N, this.O, this.c, 0, this.N));
                final int n3 = 0xFF000000 | this.W.getRGB();
                for (int i = 0; i < this.N * this.O; ++i) {
                    this.c[i] = n3;
                }
                for (int j = 1; j < this.t; ++j) {
                    this.z[j].start();
                }
            }
            if (this.\u00c3) {
                System.err.println("Debug : Image_" + this.u + " -> OK");
            }
            ++this.u;
        }
        return null;
    }
    
    private void P() {
        if (this.x == null) {
            if (this.\u00d1 == jads2.I) {
                this.\u00d2 = jads2.F;
                this.b = new d[this.n * this.o];
                this.K();
            }
            else {
                int n = (int)Math.round((this.\u00d6.length - 1) * Math.random());
                if (this.\u00d1 == jads2.J) {
                    while (this.\u00d3[n].equals("FADE")) {
                        n = (int)Math.round((this.\u00d6.length - 1) * Math.random());
                    }
                }
                this.o = this.\u00d5[0];
                this.n = this.\u00d6[0];
                this.Q = this.\u00d3[0];
                this.R = this.\u00d4[0];
                this.b = new d[this.n * this.o];
                this.\u00d2 = jads2.F;
                this.K();
                this.o = this.\u00d5[n];
                this.n = this.\u00d6[n];
                this.Q = this.\u00d3[n];
                this.R = this.\u00d4[n];
                this.\u00d2 = jads2.G;
                this.K();
            }
            this.g.flush();
            this.M.drawImage(this.g, 0, 0, this.N, this.O, null);
        }
    }
    
    private void K() {
        this.k = this.N / this.n;
        this.l = this.O / this.o;
        this.m = this.n * this.o;
        int i = 0;
        if (this.R.equals("UPDOWNLEFTRIGHT")) {
            int n = 0;
            int n2 = 0;
            while (i < this.m) {
                this.M(i++, n, n2, i);
                if (n < this.n - 1) {
                    ++n;
                }
                else {
                    ++n2;
                    n = 0;
                }
            }
            return;
        }
        if (this.R.equals("UPDOWNRIGHTLEFT")) {
            int n3 = this.n - 1;
            int n4 = 0;
            while (i < this.m) {
                this.M(i++, n3, n4, i);
                if (n3 > 0) {
                    --n3;
                }
                else {
                    ++n4;
                    n3 = this.n - 1;
                }
            }
            return;
        }
        if (this.R.equals("DOWNUPLEFTRIGHT")) {
            int n5 = 0;
            int n6 = this.o - 1;
            while (i < this.m) {
                this.M(i++, n5, n6, i);
                if (n5 < this.n - 1) {
                    ++n5;
                }
                else {
                    --n6;
                    n5 = 0;
                }
            }
            return;
        }
        if (this.R.equals("DOWNUPRIGHTLEFT")) {
            int n7 = this.n - 1;
            int n8 = this.o - 1;
            while (i < this.m) {
                this.M(i++, n7, n8, i);
                if (n7 > 0) {
                    --n7;
                }
                else {
                    --n8;
                    n7 = this.n - 1;
                }
            }
            return;
        }
        int n9 = 0;
        int n10 = 0;
        while (i < this.m) {
            this.M(i++, n9, n10, 0);
            if (n9 < this.n - 1) {
                ++n9;
            }
            else {
                ++n10;
                n9 = 0;
            }
        }
    }
    
    private void M(final int n, final int n2, final int n3, final int n4) {
        final int round = Math.round(n2 * this.k);
        final int round2 = Math.round(n3 * this.l);
        if (this.\u00d2 == jads2.F) {
            this.b[n] = new d(this.e, this.d, this.c, this.N, round, round2, Math.round((n2 + 1) * this.k) - round, Math.round((n3 + 1) * this.l) - round2, this.Q, n4);
            return;
        }
        this.b[n].p(this.e, this.d, this.c, this.N, round, round2, Math.round((n2 + 1) * this.k) - round, Math.round((n3 + 1) * this.l) - round2, this.Q, n4);
    }
    
    public void start() {
        if (this.\u00d8 == null) {
            this.\u00d8 = new Thread(this);
            this.\u00d9 = 0;
            this.\u00d8.start();
        }
    }
    
    public void stop() {
        if (this.\u00d8 != null) {
            this.\u00d8.stop();
            this.\u00d8 = null;
        }
    }
    
    public void run() {
        while (true) {
            this.update(this.getGraphics());
            try {
                Thread.sleep(this.\u00da);
            }
            catch (InterruptedException ex) {
                if (!this.\u00c3) {
                    continue;
                }
                System.err.println("Thread Interrupted !");
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.L != null) {
            graphics.drawImage(this.L, 0, 0, this);
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.x != null) {
            this.M.setColor(Color.black);
            this.M.fillRect(0, 0, this.N, this.O);
            this.M.setColor(Color.cyan);
            this.M.drawString("J-Ads V2.0 , JavaZOOM 1999, from http://javazoom.hypermart.net", this.N / 2 - graphics.getFontMetrics().stringWidth("J-Ads V2.0 , JavaZOOM 1999, from http://javazoom.hypermart.net") / 2, this.O / 2 - 12);
            this.M.setColor(Color.white);
            this.M.drawString(this.x, this.N / 2 - graphics.getFontMetrics().stringWidth(this.x) / 2, this.O / 2 + 6);
            if (this.v) {
                this.showStatus("http://javazoom.hypermart.net");
            }
            this.paint(graphics);
            this.\u00da = this.\u00db;
            return;
        }
        if (this.u == -1) {
            this.M.setColor(this.W);
            this.M.fillRect(0, 0, this.N, this.O);
            this.paint(graphics);
            if (this.\u00c5 != null) {
                String n = "";
                try {
                    n = this.N(this.\u00c5, false);
                }
                catch (MalformedURLException ex) {
                    this.x = "Error : Cannot load " + this.\u00c5;
                    if (this.\u00c3) {
                        System.err.println("Debug : Load regFile : " + ex.getMessage());
                    }
                }
                catch (IOException ex2) {
                    this.x = "Error : Cannot load " + this.\u00c5;
                    if (this.\u00c3) {
                        System.err.println("Debug : Load regFile : " + ex2.getMessage());
                    }
                }
                if (this.x == null) {
                    if (!this.\u00c8 && this.getCodeBase().getProtocol().equals("http")) {
                        this.\u00c6 = new String(this.Q("VU5SRUdJU1RFUkVE".getBytes()));
                        try {
                            final String s = new String(this.S(n.getBytes()));
                            if (this.\u00c3) {
                                System.err.println("Registered to :" + s);
                            }
                            final String string = this.getCodeBase().toString();
                            for (int i = 0; i < string.length() - s.length(); ++i) {
                                if (string.regionMatches(true, i, s, 0, s.length())) {
                                    this.\u00c6 = "";
                                    break;
                                }
                            }
                        }
                        catch (Exception ex3) {
                            this.x = "Error : Invalid regfile (" + this.\u00c5 + ")";
                            if (this.\u00c3) {
                                System.err.println("Debug : Invalid regfile : " + ex3.getMessage());
                            }
                        }
                    }
                    else {
                        this.\u00c6 = "";
                    }
                }
            }
            else if (!this.\u00c8) {
                this.\u00c6 = new String(this.Q("VU5SRUdJU1RFUkVE".getBytes()));
            }
            else {
                this.\u00c6 = "UNREGISTERED";
            }
            this.z[0].start();
            if (this.\u00c3) {
                System.err.println("Debug : Init=" + this.u);
            }
            this.u = 0;
            return;
        }
        if (this.u < this.t) {
            this.x = this.V(this.u);
        }
        if (this.f) {
            ++this.\u00c9;
            boolean b = true;
            this.g.flush();
            for (int j = 0; j < this.m; ++j) {
                this.b[j].t();
                b &= this.b[j].l();
            }
            if (b) {
                if (this.\u00c3) {
                    System.err.println("Debug : Finishing : " + this.r + " -> " + this.s);
                }
                if (this.r < this.t - 1) {
                    ++this.r;
                }
                else {
                    this.r = 0;
                }
                if (this.s < this.t - 1) {
                    ++this.s;
                }
                else {
                    this.s = 0;
                }
                this.f = false;
                this.\u00c9 = 0;
            }
            this.M.drawImage(this.g, 0, 0, this.N, this.O, null);
            this.M.setColor(Color.black);
            this.M.drawString(this.\u00c6, 2, 13);
            this.M.setColor(Color.red);
            this.M.drawString(this.\u00c6, 1, 12);
            this.paint(graphics);
            this.\u00da = this.\u00db;
            return;
        }
        if (!this.f && this.u > 0) {
            if (this.v) {
                this.showStatus(this.Y[this.r]);
            }
            final a a = this.i.elementAt(this.\u00d9);
            this.M.drawImage(a.F(), a.I(), a.A(), null);
            this.M.setColor(Color.black);
            this.M.drawString(this.\u00c6, 2, 13);
            this.M.setColor(Color.red);
            this.M.drawString(this.\u00c6, 1, 12);
            this.paint(graphics);
            this.\u00da = a.E() * 10;
            if (this.\u00da == 0) {
                this.\u00da = this.X;
            }
            if (this.\u00c3) {
                System.err.println("Debug : Painted " + this.j + "," + this.\u00d9 + " and Waiting " + this.\u00da + " ms");
            }
            if (this.v) {
                this.showStatus(this.Y[this.r]);
            }
            if (this.\u00d9 < this.i.size() - 1) {
                ++this.\u00d9;
                return;
            }
            if (this.\u00c1) {
                if (this.\u00c2 < this.\u00c0[this.j]) {
                    this.\u00d9 = 0;
                    ++this.\u00c2;
                    if (this.\u00c3) {
                        System.err.println("Debug : loop = " + this.\u00c2);
                    }
                    return;
                }
                this.\u00c2 = 1;
            }
            if (this.u < this.t) {
                if (this.u - 1 <= this.j) {
                    return;
                }
            }
            else if (this.u == this.t) {
                for (int k = 0; k < this.t; ++k) {
                    this.z[k] = null;
                }
                System.gc();
                if (this.\u00c3) {
                    System.err.println("Debug : Transition state Over !");
                }
                ++this.u;
            }
            final int n2 = 0xFF000000 | this.W.getRGB();
            for (int l = 0; l < this.N * this.O; ++l) {
                this.e[l] = n2;
            }
            final PixelGrabber pixelGrabber = new PixelGrabber(this.L, 0, 0, this.N, this.O, this.e, 0, this.N);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex4) {
                System.err.println("Incorrect Image format ");
            }
            if (this.j < this.h.length - 1) {
                ++this.j;
            }
            else {
                this.j = 0;
            }
            this.i = this.h[this.j];
            this.\u00d9 = 0;
            final a a2 = this.i.elementAt(this.\u00d9);
            this.M.setColor(this.W);
            this.M.fillRect(0, 0, this.N, this.O);
            this.M.drawImage(a2.F(), a2.I(), a2.A(), null);
            this.M.setColor(Color.black);
            this.M.drawString(this.\u00c6, 2, 13);
            this.M.setColor(Color.red);
            this.M.drawString(this.\u00c6, 1, 12);
            for (int n3 = 0; n3 < this.N * this.O; ++n3) {
                this.d[n3] = n2;
            }
            final PixelGrabber pixelGrabber2 = new PixelGrabber(this.L, 0, 0, this.N, this.O, this.d, 0, this.N);
            try {
                pixelGrabber2.grabPixels();
            }
            catch (InterruptedException ex5) {
                System.err.println("Incorrect Image format ");
            }
            if (!this.\u00c7) {
                this.P();
                this.\u00c7 = true;
            }
            if (this.\u00d1 == jads2.H || this.\u00d1 == jads2.J) {
                int n4 = (int)Math.round((this.\u00d6.length - 1) * Math.random());
                if (this.\u00d1 == jads2.J) {
                    while (this.\u00d3[n4].equals("FADE")) {
                        n4 = (int)Math.round((this.\u00d6.length - 1) * Math.random());
                    }
                }
                this.o = this.\u00d5[n4];
                this.n = this.\u00d6[n4];
                this.Q = this.\u00d3[n4];
                this.R = this.\u00d4[n4];
                this.\u00d2 = jads2.G;
                this.K();
            }
            for (int n5 = 0; n5 < this.m; ++n5) {
                this.b[n5].r(this.e);
                this.b[n5].m(this.d);
                this.b[n5].o();
            }
            this.f = true;
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.u > 0 && this.\u00c9 < 4) {
            if (this.S != null && this.x == null) {
                final String s = this.Y[this.r];
                if (s != null) {
                    final String encode = URLEncoder.encode(s);
                    try {
                        final DataOutputStream dataOutputStream = new DataOutputStream(new Socket(this.getCodeBase().getHost(), this.y).getOutputStream());
                        if (this.T == null) {
                            dataOutputStream.writeBytes("GET " + this.S + "/jads.cgi?url=" + encode + " HTTP/1.0\n\n");
                        }
                        else {
                            dataOutputStream.writeBytes("GET " + this.S + "/jads.cgi?url=" + encode + "&id=" + URLEncoder.encode(this.T) + " HTTP/1.0\n\n");
                        }
                        dataOutputStream.close();
                    }
                    catch (Exception ex) {
                        if (this.\u00c3) {
                            System.err.println(ex.getMessage());
                        }
                    }
                }
            }
            try {
                String s2 = this.Y[this.r];
                String s3 = this.Z[this.r];
                if (this.x != null) {
                    s2 = "http://javazoom.hypermart.net";
                    s3 = "_blank";
                }
                if (s2 != null) {
                    final URL url = new URL(this.getDocumentBase(), s2);
                    if (s3 != null) {
                        this.getAppletContext().showDocument(url, s3);
                    }
                    else {
                        this.getAppletContext().showDocument(url);
                    }
                }
            }
            catch (MalformedURLException ex2) {
                if (this.\u00c3) {
                    System.err.println(ex2.getMessage());
                }
            }
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.v = true;
        if (!this.\u00c8) {
            this.setCursor(this.w);
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.v = false;
        this.showStatus(null);
        return true;
    }
    
    private String O(final String s) {
        if (s.startsWith("#") || s.equals("")) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "=");
        String trim;
        String trim2;
        try {
            trim = stringTokenizer.nextToken().trim();
            trim2 = stringTokenizer.nextToken().trim();
        }
        catch (NoSuchElementException ex) {
            return "Invalid line in config file !";
        }
        if (trim != null) {
            final String upperCase = trim.toUpperCase();
            if (upperCase.equals("BGCOLOR")) {
                if (trim2.length() <= 0) {
                    return "Error : bgcolor value missing !";
                }
                this.W = new Color(Integer.parseInt(trim2, 16));
            }
            if (upperCase.equals("GRID")) {
                if (trim2.length() <= 0) {
                    return "Error : grid value missing !";
                }
                this.P = trim2.toUpperCase();
                if (this.P.indexOf("X") == -1) {
                    this.q = true;
                }
                else {
                    this.o = Math.abs(Integer.parseInt(this.P.substring(0, this.P.indexOf("X"))));
                    if (this.o == 0) {
                        this.o = 1;
                    }
                    this.n = Math.abs(Integer.parseInt(this.P.substring(this.P.indexOf("X") + 1, this.P.length())));
                    if (this.n == 0) {
                        this.n = 1;
                    }
                    this.q = false;
                }
            }
            if (upperCase.equals("WAIT")) {
                if (trim2.length() <= 0) {
                    return "Error : wait value missing !";
                }
                this.X = Math.round(Math.abs(Integer.parseInt(trim2)));
            }
            if (upperCase.equals("TRANSITION")) {
                if (trim2.length() <= 0) {
                    return "Error : transition value missing !";
                }
                this.Q = trim2.toUpperCase();
            }
            if (upperCase.equals("DIRECTION")) {
                if (trim2.length() <= 0) {
                    return "Error : direction value missing !";
                }
                this.R = trim2.toUpperCase();
            }
            if (upperCase.equals("REGFILE")) {
                if (trim2.length() <= 0) {
                    return "Error : regfile value missing !";
                }
                this.\u00c5 = trim2;
            }
            if (upperCase.equals("CLICKCOUNTER")) {
                if (trim2.length() <= 0) {
                    return "Error : ClickCounter value missing !";
                }
                this.S = trim2;
            }
            if (upperCase.equals("PORT")) {
                if (trim2.length() <= 0) {
                    return "Error : port value missing !";
                }
                this.y = Math.abs(Integer.parseInt(trim2));
            }
            if (upperCase.equals("ID")) {
                if (trim2.length() <= 0) {
                    return "Error : ID value missing !";
                }
                this.T = trim2;
            }
            if (upperCase.equals("DEBUG")) {
                if (trim2.length() <= 0) {
                    return "Error : debug value missing !";
                }
                if (trim2.toUpperCase().equals("YES")) {
                    this.\u00c3 = true;
                }
                else {
                    this.\u00c3 = false;
                }
            }
            if (upperCase.equals("USEGIFITERATION")) {
                if (trim2.length() <= 0) {
                    return "Error : useGIFIteration value missing !";
                }
                if (trim2.toUpperCase().equals("YES")) {
                    this.\u00c1 = true;
                }
                else {
                    this.\u00c1 = false;
                }
            }
            if (upperCase.equals("FPS")) {
                if (trim2.length() <= 0) {
                    return "Error : fps value missing !";
                }
                this.\u00db = Math.round(1000 / Math.abs(Integer.parseInt(trim2)));
            }
            if (upperCase.startsWith("IMAGE_")) {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(upperCase, "_");
                stringTokenizer2.nextToken();
                String nextToken;
                try {
                    nextToken = stringTokenizer2.nextToken();
                }
                catch (NoSuchElementException ex2) {
                    return "Image number Missing !";
                }
                final int abs = Math.abs(Integer.parseInt(nextToken));
                if (abs > jads2.K - 1) {
                    return "Too many banners !";
                }
                if (trim2.length() <= 0) {
                    return upperCase + " parameter empty !";
                }
                this.a[abs] = trim2;
            }
            if (upperCase.startsWith("ALINK_")) {
                final StringTokenizer stringTokenizer3 = new StringTokenizer(upperCase, "_");
                stringTokenizer3.nextToken();
                String nextToken2;
                try {
                    nextToken2 = stringTokenizer3.nextToken();
                }
                catch (NoSuchElementException ex3) {
                    return "Link number Missing !";
                }
                final int abs2 = Math.abs(Integer.parseInt(nextToken2));
                if (abs2 > jads2.K - 1) {
                    return "Too many links !";
                }
                if (trim2.length() <= 0) {
                    return upperCase + " parameter empty !";
                }
                this.Y[abs2] = trim2;
            }
            if (upperCase.startsWith("FRAME_")) {
                final StringTokenizer stringTokenizer4 = new StringTokenizer(upperCase, "_");
                stringTokenizer4.nextToken();
                String nextToken3;
                try {
                    nextToken3 = stringTokenizer4.nextToken();
                }
                catch (NoSuchElementException ex4) {
                    return "Frame number Missing !";
                }
                final int abs3 = Math.abs(Integer.parseInt(nextToken3));
                if (abs3 > jads2.K - 1) {
                    return "Too many frames !";
                }
                if (trim2.length() <= 0) {
                    return upperCase + " parameter empty !";
                }
                this.Z[abs3] = trim2;
            }
        }
        return null;
    }
    
    private String N(final String s, final boolean b) throws IOException, MalformedURLException {
        String s2 = "";
        final DataInputStream dataInputStream = new DataInputStream(new URL(this.getDocumentBase(), s).openStream());
        if (dataInputStream != null) {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
            final byte[] array = new byte[512];
            int n = 0;
            boolean b2 = false;
            while (!b2) {
                final int read = dataInputStream.read(array, 0, 512);
                if (read == -1) {
                    b2 = true;
                }
                else {
                    byteArrayOutputStream.write(array, 0, read);
                    byteArrayOutputStream.flush();
                    n += read;
                }
            }
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            dataInputStream.close();
            System.gc();
            int n2 = 0;
            if (b) {
                for (int i = 0; i < n; ++i) {
                    final byte b3 = byteArray[i];
                    if (b3 != 13) {
                        if (b3 == 10) {
                            byteArray[n2] = 59;
                        }
                        else {
                            byteArray[n2] = byteArray[i];
                        }
                        ++n2;
                    }
                }
            }
            else {
                for (int j = 0; j < n; ++j) {
                    byteArray[n2++] = byteArray[j];
                }
            }
            s2 = new String(byteArray, 0, 0, n2);
        }
        return s2;
    }
    
    private byte[] Q(final byte[] array) {
        int n = 0;
        final int length = array.length;
        int n2 = 0;
        for (int i = length - 1; i >= 0; --i) {
            if (array[i] > 32) {
                ++n2;
            }
            if (array[i] == 61) {
                ++n;
            }
        }
        if (n2 % 4 != 0) {
            throw new IllegalArgumentException("Length not a multiple of 4");
        }
        final byte[] array2 = new byte[n2 / 4 * 3 - n];
        int j = 0;
        final byte[] array3 = new byte[4];
        int n3 = 0;
        int n4 = 0;
        final byte[] array4 = array3;
        final int n5 = 0;
        final byte[] array5 = array3;
        final int n6 = 1;
        final byte[] array6 = array3;
        final int n7 = 2;
        final byte[] array7 = array3;
        final int n8 = 3;
        final byte b = 61;
        array6[n7] = (array7[n8] = b);
        array4[n5] = (array5[n6] = b);
        while (j < length) {
            final byte b2 = array[j++];
            if (b2 > 32) {
                array3[n4++] = b2;
            }
            if (n4 == 4) {
                n3 += this.Q(array2, n3, array3[0], array3[1], array3[2], array3[3]);
                n4 = 0;
                final byte[] array8 = array3;
                final int n9 = 0;
                final byte[] array9 = array3;
                final int n10 = 1;
                final byte[] array10 = array3;
                final int n11 = 2;
                final byte[] array11 = array3;
                final int n12 = 3;
                final byte b3 = 61;
                array10[n11] = (array11[n12] = b3);
                array8[n9] = (array9[n10] = b3);
            }
        }
        if (n4 > 0) {
            this.Q(array2, n3, array3[0], array3[1], array3[2], array3[3]);
        }
        return array2;
    }
    
    private byte[] Q(final String s) throws IllegalArgumentException {
        final int length = s.length();
        final byte[] array = new byte[length];
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            if (char1 > '\u00ff') {
                return null;
            }
            array[i] = (byte)char1;
        }
        return this.Q(array);
    }
    
    private int Q(final byte[] array, int n, final byte b, final byte b2, final byte b3, final byte b4) {
        final byte b5 = jads2.\u00d0[b];
        final byte b6 = jads2.\u00d0[b2];
        final byte b7 = jads2.\u00d0[b3];
        final byte b8 = jads2.\u00d0[b4];
        if (b5 == -1 || b6 == -1 || (b7 == -1 && b3 != 61) || (b8 == -1 && b4 != 61)) {
            throw new IllegalArgumentException("Invalid character [" + (b & 0xFF) + ", " + (b2 & 0xFF) + ", " + (b3 & 0xFF) + ", " + (b4 & 0xFF) + "]");
        }
        array[n++] = (byte)(b5 << 2 | b6 >>> 4);
        if (b3 == 61) {
            return 1;
        }
        array[n++] = (byte)(b6 << 4 | b7 >>> 2);
        if (b4 == 61) {
            return 2;
        }
        array[n++] = (byte)(b7 << 6 | b8);
        return 3;
    }
    
    private byte[] U(final BigInteger[] array, final BigInteger bigInteger, final BigInteger bigInteger2) {
        final BigInteger[] array2 = new BigInteger[array.length];
        try {
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = array[i].modPow(bigInteger, bigInteger2);
            }
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
        final byte[] array3 = new byte[array2.length];
        for (int j = 0; j < array3.length; ++j) {
            array3[j] = (byte)array2[j].intValue();
        }
        return array3;
    }
    
    private byte[] S(final byte[] array) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array2 = new byte[1024];
        final GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(this.Q(new String(array))));
        while (true) {
            final int read = gzipInputStream.read(array2, 0, array2.length);
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(array2, 0, read);
        }
        gzipInputStream.close();
        byteArrayOutputStream.close();
        return this.U(this.L(byteArrayOutputStream.toByteArray()), this.\u00cc, this.\u00cd);
    }
    
    private BigInteger[] L(final byte[] array) {
        short n = 0;
        int n2 = 0;
        int n3;
        for (n3 = 0; n < array.length; n = (short)(n + (short)(((array[n] & 0xFF) | ((array[n + 1] & 0xFF) << 8 & 0xFF00)) & 0xFFFF) + 2), ++n3) {}
        final BigInteger[] array2 = new BigInteger[n3];
        int i = 0;
        while (i < array.length) {
            final short n4 = (short)(((array[i] & 0xFF) | ((array[i + 1] & 0xFF) << 8 & 0xFF00)) & 0xFFFF);
            i += 2;
            final byte[] array3 = new byte[n4];
            for (int j = 0; j < array3.length; ++j) {
                array3[j] = array[i++];
            }
            try {
                array2[n2++] = new BigInteger(1, array3);
            }
            catch (Exception ex) {
                System.err.println(ex.getMessage());
                return null;
            }
        }
        return array2;
    }
    
    private void T() {
        this.\u00cd = new BigInteger(this.\u00cb);
        this.\u00cc = new BigInteger(this.\u00ca);
    }
    
    private String R() {
        String property;
        try {
            property = System.getProperty("java.version");
        }
        catch (SecurityException ex) {
            property = "unk";
        }
        if (!property.startsWith("1.0")) {
            this.\u00c8 = false;
        }
        else {
            this.\u00c8 = true;
        }
        return property;
    }
    
    public jads2() {
        this.Q = "AUTOFAST";
        this.R = "AUTO";
        this.U = "15";
        this.V = "FFFFFF";
        this.X = 3000;
        this.f = false;
        this.k = 1.0f;
        this.l = 1.0f;
        this.n = 1;
        this.o = 10;
        this.p = true;
        this.q = true;
        this.s = 1;
        this.u = -1;
        this.v = false;
        this.y = 80;
        this.\u00c1 = false;
        this.\u00c2 = 1;
        this.\u00c3 = false;
        this.\u00c6 = "";
        this.\u00c7 = false;
        this.\u00c8 = false;
        this.\u00ca = "65537";
        this.\u00cb = "9461444737833700518697178996806794331267507832911412918873124210888189544398631156385572715329610946098218545552742663600433267988187077186180937893791893";
        this.\u00d1 = jads2.H;
        this.\u00d2 = jads2.F;
        this.\u00d3 = new String[] { "FADE", "ROLLDOWN", "UPDOWN", "LEFTRIGHT", "FADE", "ROLLUP", "UPDOWN", "LEFTRIGHT", "FADE", "UPDOWN", "FADE", "CIRCLEOUT", "CIRCLEOUT", "CIRCLEOUT" };
        this.\u00d4 = new String[] { "UPDOWNLEFTRIGHT", "UPDOWNLEFTRIGHT", "UPDOWNLEFTRIGHT", "UPDOWNLEFTRIGHT", "UPDOWNRIGHTLEFT", "UPDOWNLEFTRIGHT", "DOWNUPLEFTRIGHT", "UPDOWNRIGHTLEFT", "UPDOWNRIGHTLEFT", "DOWNUPRIGHTLEFT", "DOWNUPRIGHTLEFT", "ISO", "ISO", "ISO" };
        this.\u00d5 = new int[] { 1, 1, 10, 1, 1, 1, 10, 1, 40, 10, 40, 2, 4, 4 };
        this.\u00d6 = new int[] { 90, 1, 1, 60, 90, 1, 1, 60, 1, 1, 1, 10, 20, 20 };
    }
    
    static {
        jads2.G = 1;
        jads2.H = 2;
        jads2.I = 3;
        jads2.J = 4;
        jads2.K = 32;
        \u00cf = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/' };
        \u00d0 = new byte[] { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
    }
}
